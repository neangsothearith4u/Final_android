package com.sothearithcompany.spring_homework_restapi2.service;


import com.sothearithcompany.spring_homework_restapi2.model.user.User;
import com.sothearithcompany.spring_homework_restapi2.model.user.UserRequest;

public interface UserService {
    User updateUser(Integer id, UserRequest userRequest);
}
