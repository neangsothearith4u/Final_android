package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Book;
import com.sothearithcompany.spring_homework_restapi2.model.request.BookRequest;
import com.sothearithcompany.spring_homework_restapi2.model.response.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.service.service.BookService;
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
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Fetch all books. user can get all")
    @RequestMapping(value = { "/library/book"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(
                new ApiResponse<List<Book>>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully fetched books",
                        bookService.getAllBooks()

                )
        );
    }

    @Operation(summary = "Fetch category by id")
    @RequestMapping(value = { "/library/book/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(
                new ApiResponse<Book>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully fetched book",
                        bookService.getBookById(id)

                )
        );
    }

    @Operation(summary = "Add new book")
    @RequestMapping(value = { "/admin/book"}, method = RequestMethod.POST)
    public ResponseEntity<?> addNewBook(@RequestBody BookRequest bookRequest){
        return ResponseEntity.ok(
                new ApiResponse<Book>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully add book",
                        bookService.addNewBook(bookRequest)
                )
        );
    }

//    @PutMapping("/book/{id}")
@Operation(summary = "Update book by id")
    @RequestMapping(value = { "/library/book/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<?> updateBookById(@PathVariable Integer id,@RequestBody BookRequest bookRequest){
        return ResponseEntity.ok(
                new ApiResponse<Book>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully update book",
                        bookService.updateBookById(id,bookRequest)
                )
        );
    }

//    @DeleteMapping("/book/{id}")
@Operation(summary = "Delete book by id")
    @RequestMapping(value = { "/library/book/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBookById(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Successfully delete book",
                        null
                )
        );
    }
}
