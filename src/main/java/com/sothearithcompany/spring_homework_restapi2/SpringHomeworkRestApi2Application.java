package com.sothearithcompany.spring_homework_restapi2;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
        name = "bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearerAuth"
)
public class SpringHomeworkRestApi2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringHomeworkRestApi2Application.class, args);
    }

}
