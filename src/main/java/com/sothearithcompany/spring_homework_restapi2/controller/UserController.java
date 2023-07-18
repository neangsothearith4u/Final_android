package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.model.user.User;
import com.sothearithcompany.spring_homework_restapi2.model.user.UserRequest;
import com.sothearithcompany.spring_homework_restapi2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Edit user")
    @RequestMapping(value = {"admin/user/{id}"}, method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest){
        ApiResponse<User> response = ApiResponse.<User>builder()
                .status(HttpStatus.OK.value())
                .message("Updated user")
                .data(userService.updateUser(id, userRequest))
                .date(String.valueOf(LocalDateTime.now()))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user")
    @RequestMapping(value = {"admin/user/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Deleted user")
                .data(userService.deleteUser(id))
                .date(String.valueOf(LocalDateTime.now()))
                .build();
        return ResponseEntity.ok(response);
    }
}
