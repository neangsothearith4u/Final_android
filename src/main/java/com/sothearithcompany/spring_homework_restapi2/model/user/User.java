package com.sothearithcompany.spring_homework_restapi2.model.user;

import com.sothearithcompany.spring_homework_restapi2.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String role;
    private Boolean isActive;

}
