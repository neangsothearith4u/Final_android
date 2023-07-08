package com.sothearithcompany.spring_homework_restapi2.service.implement;


import com.sothearithcompany.spring_homework_restapi2.exception.BadRequestException;
import com.sothearithcompany.spring_homework_restapi2.exception.ConflictException;
import com.sothearithcompany.spring_homework_restapi2.exception.InternalServerErrorException;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUser;
import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUserDto;
import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUserRequest;
import com.sothearithcompany.spring_homework_restapi2.model.jwt.JwtChangePasswordRequest;
import com.sothearithcompany.spring_homework_restapi2.model.otp.Otp;
import com.sothearithcompany.spring_homework_restapi2.repository.AppUserRepository;
import com.sothearithcompany.spring_homework_restapi2.repository.OtpRepository;
import com.sothearithcompany.spring_homework_restapi2.service.JwtUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, JwtUserDetailsService {

    private final AppUserRepository appUserRepository;
    private final OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public JwtUserDetailsServiceImpl(AppUserRepository appUserRepository, OtpRepository otpRepository) {
        this.appUserRepository = appUserRepository;
        this.otpRepository = otpRepository;
    }

    Boolean checkDuplicatePhone(String phone, Integer roleId) {
        Boolean isExistInUserPhone = false;
        Boolean isExistInUserInfo = false;

        if (roleId == 1) {
            isExistInUserPhone = appUserRepository.checkPhoneNumberFromDistributorPhone(phone);
            isExistInUserInfo = appUserRepository.checkPhoneNumberFromDistributorDetail(phone);
        } else {
            isExistInUserPhone = appUserRepository.checkPhoneNumberFromRetailerPhone(phone);
            isExistInUserInfo = appUserRepository.checkPhoneNumberFromRetailerDetail(phone);
        }
        if (isExistInUserPhone || isExistInUserInfo) {
            return true;
        }
        return false;
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean validateEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = appUserRepository.findDistributorUserByEmail(email);
        if (user == null) {
            throw new BadRequestException("Invalid email address. Please input valid email address.");
        }
        return user;
    }

    @Override
    public AppUserDto insertUser(AppUserRequest appUserRequest) {
        appUserRequest.setRole(appUserRequest.getRole().toUpperCase().trim());
        if (!(Objects.equals(appUserRequest.getRole(), "ADMIN") || Objects.equals(appUserRequest.getRole(), "USER"))) {
            throw new BadRequestException("Invalid role. Role need to be ADMIN or USER.");
        }
        if (!(validateEmail(appUserRequest.getEmail()))) {
            throw new BadRequestException("Please follow email format.");
        }
        appUserRequest.setEmail(appUserRequest.getEmail().toLowerCase().trim());
        appUserRequest.setPassword(passwordEncoder.encode((appUserRequest.getPassword())));
        AppUser appUser = null;
        if (appUserRepository.checkDuplicateUser(appUserRequest.getEmail())){
            throw new ConflictException("Email is already exist.");
        }
        appUser = appUserRepository.insertUser(appUserRequest);
        return modelMapper.map(appUser, AppUserDto.class);
    }

    @Override
    public boolean getVerifyEmail(String email) {
        Boolean isVerified = appUserRepository.getVerifyDistributorEmail(email);
        if (isVerified == null) {
            isVerified = appUserRepository.getVerifyRetailerEmail(email);
        }
        if (isVerified == null) {
            throw new BadRequestException("Email does not exist.");
        }
        return isVerified;
    }

    @Override
    public AppUserDto changePassword(JwtChangePasswordRequest request) {
        request.setEmail(request.getEmail().toLowerCase().trim());
        Boolean isDistributor = true;
        AppUser appUser = appUserRepository.findDistributorUserByEmail(request.getEmail());
        if (appUser == null) {
            isDistributor = false;
            appUser = appUserRepository.findRetailerUserByEmail(request.getEmail());
        }
        if (appUser == null) {
            throw new NotFoundException("Not found. Invalid email.");
        }
        // verify password with encrypted password
        if (!(passwordEncoder.matches(request.getOldPassword(), appUser.getPassword()))) {
            throw new NotFoundException("Old password is incorrect. Please input correct password.");
        }
        // if match encryot new password and update database password
        request.setNewPassword(passwordEncoder.encode(request.getNewPassword()));
        AppUser newAppUser = new AppUser();
        if (isDistributor) {
            newAppUser = appUserRepository.updateDistributorUser(request);
        } else {
            newAppUser = appUserRepository.updateRetailerUser(request);
        }
        return modelMapper.map(newAppUser, AppUserDto.class);
    }

    @Override
    public String forgetPassword(Integer otp, String email, String newPassword) {
        email = email.toLowerCase().trim();
        // check if user is exists
        Boolean isDistributor = true;
        Otp otpObj = null;
        AppUser appUser = appUserRepository.findDistributorUserByEmail(email);
        otpObj = otpRepository.getDistributorOtpByEmail(email);
        if (appUser == null || otpObj == null) {
            isDistributor = false;
            appUser = appUserRepository.findRetailerUserByEmail(email);
            otpObj = otpRepository.getRetailerOtpByEmail(email);
        }
        if (appUser == null) {
            throw new NotFoundException("Not found. Invalid email.");
        }
        if (otpObj == null) {
            throw new BadRequestException("This OTP does not exist");
        }
        // check if request and database of OTP matches
        if (!Objects.equals(appUser.getEmail(), otpObj.getEmail())) {
            throw new BadRequestException("Email not match");
        } else if (!Objects.equals(otpObj.getOtpCode(), otp)) {
            throw new BadRequestException("OTP code not match");
        }
        // check timeout of 3 minutes
        else if (!lessThan3MinutesCheck(otpObj.getCreatedDate())) {
            throw new BadRequestException("OTP Expired");
        }
        // update new password
        String updatedPassword = "null";
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        if (isDistributor) {
            updatedPassword = appUserRepository.updateForgetDistributorUser(email, encodedNewPassword);
        } else {
            updatedPassword = appUserRepository.updateForgetRetailerUser(email, encodedNewPassword);
        }
        if (Objects.equals(updatedPassword, "null")) {
            throw new InternalServerErrorException("Fail to update new password.");
        }
        return "New password updated. Your new password is: " + newPassword;
    }


    public Integer getRoleIdByMail(String email) {
        Integer roleId = appUserRepository.getRoleIdByMail(email);
        if (roleId == null) {
            roleId = appUserRepository.getRoleIdByMailRetailer(email);
        }
        return roleId;
    }

    public Boolean lessThan3MinutesCheck(Date createdDate) {
        Date currentDate = new Date();
        long diffInMillis = Math.abs(currentDate.getTime() - createdDate.getTime());
        long diffInMinutes = TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
        return diffInMinutes < 2;
    }

    public Integer getUserIdByMail(String email) {
        Integer userId = appUserRepository.getUserIdByEmail(email);
        if (userId == null) {
            throw new NotFoundException("Email not found.");
        }
        return userId;
    }
}
