package com.sothearithcompany.spring_homework_restapi2.service;


import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUserDto;
import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUserRequest;
import com.sothearithcompany.spring_homework_restapi2.model.jwt.JwtChangePasswordRequest;

public interface JwtUserDetailsService {
    AppUserDto insertUser(AppUserRequest appUserRequest);

    boolean getVerifyEmail(String email);

    AppUserDto changePassword(JwtChangePasswordRequest request);

    String forgetPassword(Integer otp, String email, String newPassword);
}
