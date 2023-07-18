package com.sothearithcompany.spring_homework_restapi2.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String role;
    private Boolean isActive;
}
