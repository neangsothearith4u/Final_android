package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

//import com.sothearithcompany.spring_homework_restapi2.exception.BlankFieldExceptionHandler;
import com.sothearithcompany.spring_homework_restapi2.exception.BadRequestException;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Author;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Book;
import com.sothearithcompany.spring_homework_restapi2.model.request.BookRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.AuthorRepository;
import com.sothearithcompany.spring_homework_restapi2.repository.BookRepository;
import com.sothearithcompany.spring_homework_restapi2.repository.CategoryRepository;
import com.sothearithcompany.spring_homework_restapi2.service.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    public BookServiceImp(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }



    // get all books
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        // validate on book if book table is empty
        if (books == null){
            throw new NotFoundException("The database is empty...!");
        }
        return books;
    }

    // get book by id
    @Override
    public Book getBookById(Integer id) {
        Book book = bookRepository.getBookById(id);
        // validate book if can not find book id
        if (book == null){
            throw new NotFoundException("Book with  id : \' "+id+" \' not found....(~_~)");
        }
        return book;
    }

    @Override
    public Book addNewBook(BookRequest bookRequest) {

        // validate on title field
        if (bookRequest.getTitle().isBlank()){
            throw new BadRequestException("Field name is blank");
        }
        if (bookRequest.getTitle().isEmpty()){
            throw new BadRequestException("Field name is empty");
        }
        if (bookRequest.getTitle().equals("string")){
            throw new BadRequestException("Field name is string");
        }
        // validate on author id
        Author author = authorRepository.getAuthorById(bookRequest.getAuthorId());
        if (author == null){
            throw new NotFoundException("author with  id : \' "+bookRequest.getAuthorId()+" \' not found....(~_~)");
        }
        // validate on categoy id
        if (bookRequest.getCategoryId().isEmpty()){
            throw new BadRequestException("Category ID is empty");
        }
        // Insert category id to book_category table
        Integer bookId = bookRepository.addNewBook(bookRequest);
        for (Integer categoryId : bookRequest.getCategoryId()){
            //validate on insert category id
            if (categoryRepository.getCategoryById(categoryId) == null){
                throw new NotFoundException("category with  id : \' "+categoryId+" \' not found....(~_~)");
            }
            bookRepository.insertBookCategory(bookId,categoryId);
        }
        // return Book after insert
       return bookRepository.getBookById(bookId);

    }


    // Update Book
    @Override
    public Book updateBookById(Integer id, BookRequest bookRequest) {
        // validate book if can not find book id
        if (getBookById(id) == null){
            throw new NotFoundException("Book with  id : \' "+id+" \' not found....(~_~)");
        }
        // validate on title field
        if (bookRequest.getTitle().isBlank()){
            throw new BadRequestException("Field name is blank");
        }
        if (bookRequest.getTitle().isEmpty()){
            throw new BadRequestException("Field name is empty");
        }
        if (bookRequest.getTitle().equals("string")){
            throw new BadRequestException("Field name is string");
        }
        // validate on author id
        Author author = authorRepository.getAuthorById(bookRequest.getAuthorId());
        if (author == null){
            throw new NotFoundException("Book with  id : \' "+bookRequest.getAuthorId()+" \' not found....(~_~)");
        }
        // validate on categoy id
        if (bookRequest.getCategoryId().isEmpty()){
            throw new BadRequestException("Category ID is empty");
        }
        bookRepository.updateBookById(id,bookRequest);
        bookRepository.deleteBookCategoryByAuthorId(id);
        for (Integer categoryId: bookRequest.getCategoryId()){
            //validate on insert category id
            if (categoryRepository.getCategoryById(categoryId) == null){
                throw new NotFoundException("category with  id : \' "+categoryId+" \' not found....(~_~)");
            }
            bookRepository.insertBookCategory(id,categoryId);
        }
        return bookRepository.getBookById(id);
    }

    @Override
    public void deleteBookById(Integer id) {
        // validate book if can not find book id
        if (getBookById(id) == null){
            throw new NotFoundException("Book with  id : \' "+id+" \' not found....(~_~)");
        }
        bookRepository.deleteBookById(id);
        bookRepository.deleteBookCategoryByAuthorId(id);
    }


}
