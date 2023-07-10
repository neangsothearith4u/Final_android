package com.sothearithcompany.spring_homework_restapi2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    private Integer BookId;
    private String title;
    private Timestamp publishedDate;
    private Author authorId;
    private String image;
    private List<Category> categories;
}
