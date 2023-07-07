package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

//import com.group2.group02_spring_mini_project001.exception.FieldBlankExceptionHandler;
//import com.group2.group02_spring_mini_project001.model.entity.Auth;
//import com.group2.group02_spring_mini_project001.model.entity.AuthDto;
//import com.group2.group02_spring_mini_project001.model.request.AuthRequest;
//import com.group2.group02_spring_mini_project001.repository.AuthRepository;
import com.sothearithcompany.spring_homework_restapi2.exception.FieldBlankExceptionHandler;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Auth;
import com.sothearithcompany.spring_homework_restapi2.model.entity.AuthDto;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor

public class AuthServiceImp implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final PasswordEncoder beanConfig;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println(email);
//        Auth auth = new Auth();
//        auth = authRepository.findUserByEmail(email);
//        System.out.println(auth);
//        System.out.println(authRepository.findUserByEmail(email));
            return authRepository.findUserByEmail(email);

    }

    public Integer getUserByEmail(String email){
        System.out.println(authRepository.getIdByEmail(email));
        return authRepository.getIdByEmail(email);
    }
    public AuthRequest insertUser(AuthRequest authRequest) {
        System.out.println(authRequest);
        if (authRequest.getFirstName().isBlank()){
            throw new FieldBlankExceptionHandler("First name can not blank");
        }
        if (authRequest.getFirstName().isEmpty()){
            throw new FieldBlankExceptionHandler("First name can not empty");
        }
        if (authRequest.getLastName().isBlank()){
            throw new FieldBlankExceptionHandler("Last name can not blank");
        }

        if (authRequest.getEmail().isEmpty()){
            throw new FieldBlankExceptionHandler("Email can not empty");
        }
        if (authRequest.getEmail().isBlank()){
            throw new FieldBlankExceptionHandler("Email can not blank");
        }
        if (authRequest.getEmail().equals("string")){
            throw new FieldBlankExceptionHandler(("Email can not be word string"));
        }



        String regex =  "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(authRequest.getEmail());
//        System.out.println(matcher.matches());
//        System.out.println(Pattern.matches(regex,authRequest.getEmail()));
        if (!matcher.matches()){
            throw new FieldBlankExceptionHandler(("Email is not correct.Example rith@gmail.com"));
        }

        String password="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern pattern1=Pattern.compile(password);
        Matcher matcher1=pattern1.matcher(authRequest.getPassword());
        if (!matcher1.matches()){
            throw new FieldBlankExceptionHandler(("Password must be character,number, sign and greater than 7 digit!"));
        }
        if (authRequest.getPassword().isEmpty()){
            throw new FieldBlankExceptionHandler("Email can not empty");
        }
        if (authRequest.getPassword().isBlank()){
            throw new FieldBlankExceptionHandler("Email can not blank");
        }
        if (authRequest.getPassword().equals("string")){
            throw new FieldBlankExceptionHandler(("Email can not be word string"));
        }
        if (authRequest.getPhoneNumber().isBlank()){
            throw new FieldBlankExceptionHandler("phone number can not blank");
        }
        if (!isValid(authRequest.getPhoneNumber())){
            throw new FieldBlankExceptionHandler("Invalid phone number");
        }
        if(!(authRequest.getRoleId() == 1 || authRequest.getRoleId() == 2)){
            throw new FieldBlankExceptionHandler("Role id must be 1 or 2");
        }

//        System.out.println(authRequest.getEmail().equals(authRepository.findUserByEmail(authRequest.getEmail())));
        if (authRepository.findUserByEmail(authRequest.getEmail())!=null) {
            throw new FieldBlankExceptionHandler(("Email already token!"));
        }

        String pass = passwordEncoder.encode(authRequest.getPassword());
        authRequest.setPassword(pass);
        Auth auth = authRepository.insertUser(authRequest);

        AuthDto authDto = new AuthDto();
        authDto.setUserId(auth.getUserId());
        authDto.setEmail(auth.getEmail());
        return authRequest;
    }
    public static boolean isValid(String input) {
        Pattern pattern = Pattern.compile("^0[1-9][0-9]{7,8}$");
        return pattern.matcher(input).matches();
    }
}
