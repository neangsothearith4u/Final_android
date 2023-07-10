package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Category;
import com.sothearithcompany.spring_homework_restapi2.model.request.CategoryRequest;
import com.sothearithcompany.spring_homework_restapi2.model.response.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(summary = "Fetch all categories")
    @RequestMapping(value = { "/admin/category"}, method = RequestMethod.GET)
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


//    @GetMapping("/category/{id}")
@Operation(summary = "Fetch category by id")
    @RequestMapping(value = { "/library/category/{id}"}, method = RequestMethod.GET)
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

    @Operation(summary = "Add new category")
    @RequestMapping(value = { "/admin/category"}, method = RequestMethod.POST)
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

    @Operation(summary = "Update category by id")
    @RequestMapping(value = { "/admin/category/{id}"}, method = RequestMethod.PUT)
//    @PutMapping("/category/{id}")
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

    @Operation(summary = "Delete category by id ")
    @RequestMapping(value = { "/admin/category/{id}"}, method = RequestMethod.DELETE)
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