package com.sothearithcompany.spring_homework_restapi2.service.serviceImp;

import com.sothearithcompany.spring_homework_restapi2.exception.InternalServerErrorException;
import com.sothearithcompany.spring_homework_restapi2.exception.NotFoundException;
import com.sothearithcompany.spring_homework_restapi2.model.user.User;
import com.sothearithcompany.spring_homework_restapi2.model.user.UserRequest;
import com.sothearithcompany.spring_homework_restapi2.repository.AppUserRepository;
import com.sothearithcompany.spring_homework_restapi2.service.UserService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;

@Service
public class UserServiceImplV1 implements UserService {
    private final AppUserRepository appUserRepository;

    public UserServiceImplV1(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public User updateUser(Integer id, UserRequest userRequest) {
        // check user exist
        if (!appUserRepository.checkIfUserExist(id)){
            throw new NotFoundException("User not found.");
        }
        // update user
        try {
            return appUserRepository.updateUser(id, userRequest);
        } catch (Exception e){
           throw new InternalServerErrorException("Error: "+ e.getMessage());
        }
    }

    @Override
    public String deleteUser(Integer id) {
        // check user exist
        if (!appUserRepository.checkIfUserExist(id)){
            throw new NotFoundException("User not found.");
        }
        // delete user
        try {
            return appUserRepository.deleteUser(id);
        } catch (Exception e){
            throw new InternalServerErrorException("Error: "+ e.getMessage());
        }
    }
}
