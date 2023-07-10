package com.sothearithcompany.spring_homework_restapi2.repository;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Category;
import com.sothearithcompany.spring_homework_restapi2.model.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {


    // get all categories
    @Select("""
            SELECT * FROM category
            """)
    @Results(id = "mapping", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_Name")
    })
    List<Category> getAllCategory();

    // get category by id
    @Select("""
            SELECT * FROM category where category_id = #{id}
            """)
    @ResultMap("mapping")
    Category getCategoryById(Integer id);

    // add new category
    @Select("""
            INSERT INTO category (category_name) values (#{category.categoryName}) returning *
            """)
    @ResultMap("mapping")
    Category addNewCategory(@Param("category") CategoryRequest categoryRequest);


    // update category by id
    @Select("""
            UPDATE category SET category_name = #{category.categoryName} where category_id = #{id} returning *
            """)
    @ResultMap("mapping")
    Category updateCategory(Integer id, @Param("category") CategoryRequest categoryRequest);

    // delete category by id
    @Delete("""
            DELETE FROM category where category_id =5
            """)
    void deleteCategoryById(Integer id);

    // get category from book_category table. use for many to table book
    @Select("""
            SELECT c.category_id,c.category_name,bc.book_id FROM category c
            INNER JOIN book_category bc on c.category_id = bc.category_id where
            bc.book_id = #{id}
            """)
    @ResultMap("mapping")
    List<Category> getAllCategoryById(Integer id);

}
