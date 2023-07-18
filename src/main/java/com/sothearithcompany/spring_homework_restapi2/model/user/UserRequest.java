package com.sothearithcompany.spring_homework_restapi2.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String role;
    private Boolean isActive;

}