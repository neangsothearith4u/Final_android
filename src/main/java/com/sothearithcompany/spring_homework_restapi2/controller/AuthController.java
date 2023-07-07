package com.sothearithcompany.spring_homework_restapi2.controller;

//import com.group2.group02_spring_mini_project001.jwt.JwtTokenUtil;
//import com.group2.group02_spring_mini_project001.jwt.jwtModel.JwtRequest;
//import com.group2.group02_spring_mini_project001.jwt.jwtModel.JwtResponse;
//import com.group2.group02_spring_mini_project001.model.entity.AuthDto;
//import com.group2.group02_spring_mini_project001.model.request.AuthRequest;
//import com.group2.group02_spring_mini_project001.model.response.ApiResponse;
//import com.group2.group02_spring_mini_project001.services.serviceImp.AuthServiceImp;
import com.sothearithcompany.spring_homework_restapi2.jwt.JwtTokenUtil;
import com.sothearithcompany.spring_homework_restapi2.jwt.jwtModel.JwtRequest;
import com.sothearithcompany.spring_homework_restapi2.jwt.jwtModel.JwtResponse;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Auth;
import com.sothearithcompany.spring_homework_restapi2.model.entity.AuthDto;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthRequest;
import com.sothearithcompany.spring_homework_restapi2.model.response.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.service.serviceImp.AuthServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImp authServiceImp;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @PostMapping("/register")
    public ResponseEntity<?> insertUser(@Valid @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(new ApiResponse<AuthRequest>(
                LocalDate.now().atStartOfDay(),
                200,
                "You have registered successfully",
                authServiceImp.insertUser(authRequest)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        System.out.println(jwtRequest);
        final UserDetails userDetails = authServiceImp.loadUserByUsername(jwtRequest.getEmail());
        System.out.println(userDetails);
        Integer id = authServiceImp.getUserByEmail(jwtRequest.getEmail());
        AuthDto authDto = new AuthDto();
//        System.out.println(jwtRequest);
        authDto.setEmail(jwtRequest.getEmail());
        authDto.setUserId(id);

        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(token);
        System.out.println(authDto);
        return ResponseEntity.ok(
                new JwtResponse(
                        authDto,
                        token,
                        LocalDateTime.now(),
                        true
                ));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
//            System.out.println("email : " + email);
//            System.out.println("password : " + password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}
