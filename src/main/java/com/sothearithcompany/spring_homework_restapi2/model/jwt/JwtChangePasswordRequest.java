package com.sothearithcompany.spring_homework_restapi2.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtChangePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
