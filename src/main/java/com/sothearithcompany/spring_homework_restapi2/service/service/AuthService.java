package com.sothearithcompany.spring_homework_restapi2.service.service;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Auth;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Auth insertUser(AuthRequest authRequest);
}
