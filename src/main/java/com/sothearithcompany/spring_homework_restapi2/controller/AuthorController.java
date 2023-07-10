package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Author;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthorRequest;
import com.sothearithcompany.spring_homework_restapi2.model.response.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.service.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
@RequestMapping("/api/v1/")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Fetch all authors")
    @RequestMapping(value = { "/admin/authors"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllAuthor(){
        return ResponseEntity.ok(
                new ApiResponse<List<Author>>(
                    LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully fetched authors",
                        authorService.getAllAuthor()

                )
        );
    }

//    @GetMapping("/authors/{id}")
@Operation(summary = "Fetch author by id")
    @RequestMapping(value = { "/admin/authors/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id){
        return ResponseEntity.ok(
                new ApiResponse<Author>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully fetched author",
                        authorService.getAuthorById(id)
                )
        );
    }

//    @PostMapping("/authors")
@Operation(summary = "Add new authors")
    @RequestMapping(value = { "/admin/authors"}, method = RequestMethod.POST)
    public ResponseEntity<?> addNewAuthor(@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(
                new ApiResponse<Author>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully added author",
                        authorService.addNewAuthor(authorRequest)

                )
        );
    }

//    @PutMapping("/authors/{id}")
@Operation(summary = "Update author by id")
    @RequestMapping(value = { "/admin/authors/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<?> updateAuthorById(@PathVariable Integer id,@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(
                new ApiResponse<Author>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully updated author",
                        authorService.updateAuthorById(id,authorRequest)

                )
        );
    }

//    @DeleteMapping("/authors/{id}")
@Operation(summary = "Delete author by id ")
    @RequestMapping(value = { "/admin/authors/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAuthorById(@PathVariable Integer id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully deleted author",
                        null
                )
        );
    }
}
