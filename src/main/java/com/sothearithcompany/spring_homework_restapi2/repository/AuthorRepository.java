package com.sothearithcompany.spring_homework_restapi2.repository;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Author;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorRepository {


    // get all author
    @Select("""
            SELECT * FROM author
            """)
    @Results(id = "mapping", value = {
            @Result(property = "authorId",column = "author_id"),
            @Result(property = "authorName",column = "author_Name")
    })
    List<Author> getAllAuthor();


    // get  author by id
    @Select("""
            SELECT * FROM author where author_id = #{id}
            """)
    @ResultMap("mapping")
    Author getAuthorById(Integer id);


    // add new author
    @Select("""
            INSERT INTO author (author_name, gender) values (#{authorName},#{gender}) 
            returning *
            """)
    @ResultMap("mapping")
    Author addNewAuthor(AuthorRequest authorRequest);


    // update author
    @Select("""
            UPDATE author SET author_name = #{author.authorName},gender=#{author.gender} where author_id = #{id} returning *
            """)
    @ResultMap("mapping")
    Author updateAuthorById(Integer id,@Param("author") AuthorRequest authorRequest);


    // delete author
    @Delete("""
            DELETE FROM author where author_id = #{id} 
            """)
    void deleteAuthorById(Integer id);
}
