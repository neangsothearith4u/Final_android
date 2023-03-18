package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Category;
import com.sothearithcompany.spring_homework_restapi2.model.request.CategoryRequest;
import com.sothearithcompany.spring_homework_restapi2.model.response.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.service.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(
                new ApiResponse<List<Category>>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully fetched authors",
                        categoryService.getAllCategory()
                )
        );
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok(
                new ApiResponse<Category>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully fetched author",
                        categoryService.getCategoryById(id)
                )
        );
    }

    @PostMapping("/category")
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(
                new ApiResponse<Category>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully added category",
                        categoryService.addNewCategory(categoryRequest)
                )
        );
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Integer id,@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(
                new ApiResponse<Category>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully update category",
                        categoryService.updateCategory(id,categoryRequest)
                )
        );
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully delete category",
                        null
                )
        );
    }
}
