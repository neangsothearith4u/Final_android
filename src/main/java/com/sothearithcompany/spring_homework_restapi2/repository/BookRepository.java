package com.sothearithcompany.spring_homework_restapi2.repository;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Book;
import com.sothearithcompany.spring_homework_restapi2.model.request.BookRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookRepository {

    // Get All Books
    @Select("""
            SELECT * FROM book
            """)
    @Result(property = "bookId",column = "book_id")
    @Result(property = "publishedDate",column = "published_date")
    // one from author table
    @Result(property = "authorId",column = "author_id",
    one = @One(select = "com.sothearithcompany.spring_homework_restapi2.repository.AuthorRepository.getAuthorById")
    )
    // many from category table
    @Result(property = "categories",column = "book_id",
        many = @Many(select = "com.sothearithcompany.spring_homework_restapi2.repository.CategoryRepository.getAllCategoryById")
    )
    List<Book> getAllBooks();

    // Get Book By id
    @Select("""
            SELECT * FROM book where book_id=#{id}
            """)
    @Result(property = "bookId",column = "book_id")
    @Result(property = "publishedDate",column = "published_date")
    // one from author table
    @Result(property = "authorId",column = "author_id",
            one = @One(select = "com.sothearithcompany.spring_homework_restapi2.repository.AuthorRepository.getAuthorById")
    )

    // many from category table
    @Result(property = "categories",column = "book_id",
            many = @Many(select = "com.sothearithcompany.spring_homework_restapi2.repository.CategoryRepository.getAllCategoryById")
    )
    Book getBookById(Integer id);

    // Add New Book
    @Select("""
            INSERT INTO book (title, author_id) values (#{book.title},#{book.authorId},#{book.image}) returning book_id
            """)
    @Result(property = "bookId",column = "book_id")
    @Result(property = "publishedDate",column = "published_date")
    Integer addNewBook(@Param("book") BookRequest bookRequest);
    @Insert("""
            INSERT INTO book_category (book_id, category_id) values (#{bookId},#{categoryId})
            """)
    void insertBookCategory(Integer bookId,Integer categoryId);

    // Update Book
    @Update("""
            UPDATE book SET title=#{book.title},author_id =#{book.authorId} where book_id = #{id}
            """)
    @Result(property = "bookId",column = "book_id")
    @Result(property = "publishedDate",column = "published_date")
    void updateBookById(Integer id,@Param("book") BookRequest bookRequest);
    @Delete("""
            DELETE FROM book_category WHERE book_id = #{id}
            """)
    void deleteBookCategoryByAuthorId(Integer id);




    // Delete book
    @Select("""
            DELETE FROM book WHERE book_id = #{id}
            """)
    Integer deleteBookById(Integer id);








}
