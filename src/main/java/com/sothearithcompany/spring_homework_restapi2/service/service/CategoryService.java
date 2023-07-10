package com.sothearithcompany.spring_homework_restapi2.service.service;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Category;
import com.sothearithcompany.spring_homework_restapi2.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(Integer id);

    Category addNewCategory(CategoryRequest categoryRequest);

    Category updateCategory(Integer id, CategoryRequest categoryRequest);

    void deleteCategoryById(Integer id);
}
