package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

import com.sothearithcompany.spring_homework_restapi2.exception.BlankFieldExceptionHandler;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Category;
import com.sothearithcompany.spring_homework_restapi2.model.request.CategoryRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.CategoryRepository;
import com.sothearithcompany.spring_homework_restapi2.service.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    private CategoryRequest handleString(){
//        CategoryRequest categoryRequest = new CategoryRequest();
//        if (categoryRequest.getCategoryName().isBlank()){
//            throw new BlankFieldExceptionHandler("Field name is blank");
//        }
//        if (categoryRequest.getCategoryName().isEmpty()){
//            throw new BlankFieldExceptionHandler("Field name is empty");
//        }
//        if (categoryRequest.getCategoryName().equals("string")){
//            throw new BlankFieldExceptionHandler("Field name is string");
//        }
//        return categoryRequest;
//    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> category = categoryRepository.getAllCategory();
        if (category == null){
            throw new NotFoundException("The database is empty....(^-^)");
        }
        return category;
    }

    @Override
    public Category getCategoryById(Integer id) {

        Category category = categoryRepository.getCategoryById(id);
        if (category == null){
            throw new NotFoundException("Category with  id : \' "+id+" \' not found....(~_~)");
        }
        return category;
    }

    @Override
    public Category addNewCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.getCategoryName().isBlank()){
            throw new BlankFieldExceptionHandler("Field name is blank");
        }
        if (categoryRequest.getCategoryName().isEmpty()){
            throw new BlankFieldExceptionHandler("Field name is empty");
        }
        if (categoryRequest.getCategoryName().equals("string")){
            throw new BlankFieldExceptionHandler("Field name is string");
        }
        return categoryRepository.addNewCategory(categoryRequest);
    }

    @Override
    public Category updateCategory(Integer id, CategoryRequest categoryRequest) {

        if (getCategoryById(id) == null){
            throw new NotFoundException("Category with  id : \' "+id+" \' not found....(~_~)");
        }
        if (categoryRequest.getCategoryName().isBlank()){
            throw new BlankFieldExceptionHandler("Field name is blank");
        }
        if (categoryRequest.getCategoryName().isEmpty()){
            throw new BlankFieldExceptionHandler("Field name is empty");
        }
        if (categoryRequest.getCategoryName().equals("string")){
            throw new BlankFieldExceptionHandler("Field name is string");
        }
        return categoryRepository.updateCategory(id,categoryRequest);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if (getCategoryById(id) == null){
            throw new NotFoundException("Category with  id : \' "+id+" \' not found....(~_~)");
        }
        categoryRepository.deleteCategoryById(id);
    }
}
