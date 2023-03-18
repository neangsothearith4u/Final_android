package com.sothearithcompany.spring_homework_restapi2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

    private Integer authorId;
    private String authorName;
    private String gender;
}
