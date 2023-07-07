package com.sothearithcompany.spring_homework_restapi2.jwt.jwtModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtResponse<T> {
    private T payload;
    private String jwtToken;
    private LocalDateTime date;
    private boolean success;
}
