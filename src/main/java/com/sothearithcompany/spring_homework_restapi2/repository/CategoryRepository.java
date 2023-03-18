package com.sothearithcompany.spring_homework_restapi2.repository;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Category;
import com.sothearithcompany.spring_homework_restapi2.model.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Locale;

@Mapper
public interface CategoryRepository {

    @Select("""
            SELECT * FROM category
            """)
    @Results(id = "mapping", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_Name")
    })
    List<Category> getAllCategory();

    @Select("""
            SELECT * FROM category where category_id = #{id}
            """)
    @ResultMap("mapping")
    Category getCategoryById(Integer id);


    @Select("""
            INSERT INTO category (category_name) values (#{category.categoryName}) returning *
            """)
    @ResultMap("mapping")
    Category addNewCategory(@Param("category") CategoryRequest categoryRequest);

    @Select("""
            UPDATE category SET category_name = #{category.categoryName} where category_id = #{id} returning *
            """)
    @ResultMap("mapping")
    Category updateCategory(Integer id, @Param("category") CategoryRequest categoryRequest);

    @Delete("""
            DELETE FROM category where category_id =5
            """)
    void deleteCategoryById(Integer id);


    @Select("""
            SELECT c.category_id,c.category_name,bc.book_id FROM category c 
            INNER JOIN book_category bc on c.category_id = bc.category_id where
            bc.book_id = #{id}
            """)
    @ResultMap("mapping")
    List<Category> getAllCategoryById(Integer id);

}
