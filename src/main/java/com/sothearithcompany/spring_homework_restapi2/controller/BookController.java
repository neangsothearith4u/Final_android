package com.sothearithcompany.spring_homework_restapi2.controller;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Book;
import com.sothearithcompany.spring_homework_restapi2.model.request.BookRequest;
import com.sothearithcompany.spring_homework_restapi2.model.response.ApiResponse;
import com.sothearithcompany.spring_homework_restapi2.service.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
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

    @GetMapping("/book/{id}")
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

    @PostMapping("/book")
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

    @PutMapping("/book/{id}")
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

    @DeleteMapping("/book/{id}")
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
