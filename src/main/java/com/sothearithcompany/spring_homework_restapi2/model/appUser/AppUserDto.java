package com.sothearithcompany.spring_homework_restapi2.model.appUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// used for transferring data without exposing info
public class AppUserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
}
