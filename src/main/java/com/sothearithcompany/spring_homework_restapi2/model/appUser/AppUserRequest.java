package com.sothearithcompany.spring_homework_restapi2.model.appUser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
}
