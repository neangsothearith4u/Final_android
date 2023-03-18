package com.sothearithcompany.spring_homework_restapi2.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
    private LocalDateTime dateTime;
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}
