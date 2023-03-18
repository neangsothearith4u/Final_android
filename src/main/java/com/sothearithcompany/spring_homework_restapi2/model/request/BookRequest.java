package com.sothearithcompany.spring_homework_restapi2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {
    private String title;
    private Integer authorId;
    private List<Integer> categoryId;
}
