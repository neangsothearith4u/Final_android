package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

import com.sothearithcompany.spring_homework_restapi2.exception.BlankFieldExceptionHandler;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Author;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Book;
import com.sothearithcompany.spring_homework_restapi2.model.request.BookRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.BookRepository;
import com.sothearithcompany.spring_homework_restapi2.service.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books == null){
            throw new NotFoundException("The database is empty...!");
        }
        return books;
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = bookRepository.getBookById(id);
        if (book == null){
            throw new NotFoundException("Book with  id : \' "+id+" \' not found....(~_~)");
        }
        return book;
    }

    @Override
    public Book addNewBook(BookRequest bookRequest) {
        Integer bookId = bookRepository.addNewBook(bookRequest);

        if (bookRequest.getTitle().isBlank()){
            throw new BlankFieldExceptionHandler("Field name is blank");
        }
        if (bookRequest.getTitle().isEmpty()){
            throw new BlankFieldExceptionHandler("Field name is empty");
        }
        if (bookRequest.getTitle().equals("string")){
            throw new BlankFieldExceptionHandler("Field name is string");
        }

        for (Integer authorId : bookRepository.getAuthorId()){
            if (bookRequest.getAuthorId() != authorId){
                throw new NotFoundException("Book with  id : \' "+bookRequest.getAuthorId()+" \' not found....(~_~)");
            }
        }

        for (Integer categoryId : bookRequest.getCategoryId()){
//            if (Pattern.matches("[0-9]+",String.valueOf(categoryId))){
//                throw new BlankFieldExceptionHandler("Input only number...!");
//            }
            bookRepository.insertBookCategory(bookId,categoryId);
        }
       return bookRepository.getBookById(bookId);

    }

    @Override
    public Book updateBookById(Integer id, BookRequest bookRequest) {
        Integer bookId=  bookRepository.updateBookById(id,bookRequest);
        bookRepository.deleteBookCategoryByAuthorId(id);
        for (Integer categoryId: bookRequest.getCategoryId()){
            bookRepository.insertBookCategory(id,categoryId);
        }
        return bookRepository.getBookById(id);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteBookById(id);
        bookRepository.deleteBookCategoryByAuthorId(id);
    }


}
