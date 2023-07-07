package com.sothearithcompany.spring_homework_restapi2.jwt.jwtModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtRequest {
    private String email;
    private String password;
}
