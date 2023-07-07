package com.sothearithcompany.spring_homework_restapi2.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDto {
    private int userId;
    private String email;
}
