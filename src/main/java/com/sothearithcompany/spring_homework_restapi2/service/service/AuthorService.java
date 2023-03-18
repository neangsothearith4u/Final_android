package com.sothearithcompany.spring_homework_restapi2.service.service;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Author;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthorRequest;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthor();

    Author getAuthorById(Integer id);

    Author addNewAuthor(AuthorRequest authorRequest);

    Author updateAuthorById(Integer id, AuthorRequest authorRequest);

    void deleteAuthorById(Integer id);
}
