//package com.sothearithcompany.spring_homework_restapi2.controller.category;
//
//import com.sothearithcompany.spring_homework_restapi2.model.ApiResponse;
//import com.sothearithcompany.spring_homework_restapi2.model.category.Category;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@SecurityRequirement(name = "bearer")
//@RequestMapping("/api/v1")
//public class CategoryController {
//    @RequestMapping(value = { "/admin/category"}, method = RequestMethod.POST)
//    public ResponseEntity<?> insertCategory(@RequestParam String name){
//        ApiResponse<Category> response = ApiResponse.<Category>builder()
//                .status(HttpStatus.CREATED.value())
//                .message("Successfully created category")
//                .data(new Category(1, "Test"))
//                .date(new Date().toString())
//                .build();
//        return ResponseEntity.ok(response);
//    }
//
//    @RequestMapping(value = { "/library/category"}, method = RequestMethod.GET)
//    public ResponseEntity<?> getCategory(){
//        ApiResponse<Category> response = ApiResponse.<Category>builder()
//                .status(HttpStatus.OK.value())
//                .message("Successfully fetched category")
//                .data(new Category(1, "Test"))
//                .date(new Date().toString())
//                .build();
//        return ResponseEntity.ok(response);
//    }
//}
