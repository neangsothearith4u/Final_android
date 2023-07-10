package com.sothearithcompany.spring_homework_restapi2.service.service;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Book;
import com.sothearithcompany.spring_homework_restapi2.model.request.BookRequest;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Integer id);

    Book addNewBook(BookRequest bookRequest);

    Book updateBookById(Integer id, BookRequest bookRequest);

    void deleteBookById(Integer id);
}
