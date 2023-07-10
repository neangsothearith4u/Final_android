package com.sothearithcompany.spring_homework_restapi2.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Category {
    private Integer categoryId;
    private String categoryName;
}
