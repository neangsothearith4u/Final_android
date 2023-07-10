package com.sothearithcompany.spring_homework_restapi2.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorRequest {

    private String authorName;
    private String gender;
}
