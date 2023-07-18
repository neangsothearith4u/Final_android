package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.model.user.User;
import com.sothearithcompany.spring_homework_restapi2.model.user.UserRequest;
import com.sothearithcompany.spring_homework_restapi2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Patch user")
    @RequestMapping(value = {"admin/user/{id}"}, method = RequestMethod.PATCH)
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest){
        ApiResponse<User> response = ApiResponse.<User>builder()
                .status(HttpStatus.OK.value())
                .message("Updated user")
                .data(userService.updateUser(id, userRequest))
                .date(String.valueOf(LocalDateTime.now()))
                .build();
        return ResponseEntity.ok(response);
    }
}
