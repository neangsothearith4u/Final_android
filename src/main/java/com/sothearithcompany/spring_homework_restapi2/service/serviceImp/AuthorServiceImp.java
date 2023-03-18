package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

import com.sothearithcompany.spring_homework_restapi2.exception.BlankFieldExceptionHandler;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.entity.Author;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthorRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.AuthorRepository;
import com.sothearithcompany.spring_homework_restapi2.service.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

//    private void HandleString(){
//        AuthorRequest authorRequest = new AuthorRequest();
//        if (authorRequest.getAuthorName().isBlank()){
//            throw new BlankFieldExceptionHandler("Field name is blank");
//        }
//        if (authorRequest.getAuthorName().isEmpty()){
//            throw new BlankFieldExceptionHandler("Field name is empty");
//        }
//        if (authorRequest.getAuthorName().equals("string")){
//            throw new BlankFieldExceptionHandler("Field name is string");
//        }
//        if (authorRequest.getGender().isBlank()){
//            throw new BlankFieldExceptionHandler("Field gender is blank");
//        }
//        if (authorRequest.getGender().isEmpty()){
//            throw new BlankFieldExceptionHandler("Field gender is empty");
//        }
//        if (authorRequest.getGender().equals("string")){
//            throw new BlankFieldExceptionHandler("Field gender is string");
//        }
//    }

    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.getAllAuthor();
    }

    @Override
    public Author getAuthorById(Integer id) {
        Author author = authorRepository.getAuthorById(id);
        if(author == null){
            throw new NotFoundException("Author with  id : \' "+id+" \' not found....(~_~)");
        }
        return author;
    }

    @Override
    public Author addNewAuthor(AuthorRequest authorRequest) {
        if (authorRequest.getAuthorName().isBlank()){
            throw new BlankFieldExceptionHandler("Field name is blank");
        }
        if (authorRequest.getAuthorName().isEmpty()){
            throw new BlankFieldExceptionHandler("Field name is empty");
        }
        if (authorRequest.getAuthorName().equals("string")){
            throw new BlankFieldExceptionHandler("Field name is string");
        }
        if (authorRequest.getGender().isBlank()){
            throw new BlankFieldExceptionHandler("Field gender is blank");
        }
        if (authorRequest.getGender().isEmpty()){
            throw new BlankFieldExceptionHandler("Field gender is empty");
        }
        if (authorRequest.getGender().equals("string")){
            throw new BlankFieldExceptionHandler("Field gender is string");
        }
       return authorRepository.addNewAuthor(authorRequest);
    }

    @Override
    public Author updateAuthorById(Integer id, AuthorRequest authorRequest) {
        if (getAuthorById(id) == null){
            throw new NotFoundException("Author with  id : \' "+id+" \' not found....(~_~)");
        }
        if (authorRequest.getAuthorName().isBlank()){
            throw new BlankFieldExceptionHandler("Field name is blank");
        }
        if (authorRequest.getAuthorName().isEmpty()){
            throw new BlankFieldExceptionHandler("Field name is empty");
        }
        if (authorRequest.getAuthorName().equals("string")){
            throw new BlankFieldExceptionHandler("Field name is string");
        }
        if (authorRequest.getGender().isBlank()){
            throw new BlankFieldExceptionHandler("Field gender is blank");
        }
        if (authorRequest.getGender().isEmpty()){
            throw new BlankFieldExceptionHandler("Field gender is empty");
        }
        if (authorRequest.getGender().equals("string")){
            throw new BlankFieldExceptionHandler("Field gender is string");
        }
        Author author =authorRepository.updateAuthorById(id,authorRequest);
        if(author == null){
            throw new NotFoundException("Author with  id : \' "+id+" \' not found....(~_~)");
        }
        return author;
    }

    @Override
    public void deleteAuthorById(Integer id) {
        if (getAuthorById(id)== null){
            throw new NotFoundException("Author with  id : \' "+id+" \' not found....(~_~)");
        }else {

            authorRepository.deleteAuthorById(id);
        }

    }

}
