package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

//import com.group2.group02_spring_mini_project001.exception.FieldBlankExceptionHandler;
//import com.group2.group02_spring_mini_project001.model.entity.Auth;
//import com.group2.group02_spring_mini_project001.model.entity.AuthDto;
//import com.group2.group02_spring_mini_project001.model.request.AuthRequest;
//import com.group2.group02_spring_mini_project001.repository.AuthRepository;
import com.sothearithcompany.spring_homework_restapi2.exception.BadRequestException;
import com.sothearithcompany.spring_homework_restapi2.exception.ConflictException;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Auth;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.AuthRepository;
import com.sothearithcompany.spring_homework_restapi2.service.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthServiceImp implements UserDetailsService, AuthService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    public AuthServiceImp(BCryptPasswordEncoder passwordEncoder, AuthRepository authRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
    }

    @Override
    public AuthRequest insertUser(AuthRequest authRequest) {
        // check for valid role
        authRequest.setEmail(authRequest.getEmail().toLowerCase().trim());
        authRequest.setRole(authRequest.getRole().toUpperCase().trim());
        System.out.println(authRequest.getRole());
        if (!(Objects.equals(authRequest.getRole(), "ADMIN") || Objects.equals(authRequest.getRole(), "USER"))){
            throw new BadRequestException("Role can only be ADMIN or USER.");
        }
        if (!(validateEmail(authRequest.getEmail()))){
            throw new BadRequestException("Please follow email format.");
        }
        authRequest.setEmail(authRequest.getEmail().toLowerCase().trim());
        authRequest.setPassword(passwordEncoder.encode((authRequest.getPassword())));
        Auth appUser = null;
        if (authRepository.checkForDuplicateEmail(authRequest.getEmail())){
            throw new ConflictException("Email is already registered.");
        }
        appUser = authRepository.insertUser(authRequest);

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = authRepository.getUserByEmail(username);
        System.out.println(user);
        if (user == null){
            throw new NotFoundException("Email not found.");
        }
        return user;
    }
    public boolean validateEmail(final String email) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Integer getUserByEmail(String email) {
        return authRepository.getIdByEmail(email);
    }
}
