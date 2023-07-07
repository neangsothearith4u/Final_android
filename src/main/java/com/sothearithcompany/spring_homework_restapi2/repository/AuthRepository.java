package com.sothearithcompany.spring_homework_restapi2.repository;

import com.sothearithcompany.spring_homework_restapi2.model.entity.Auth;
import com.sothearithcompany.spring_homework_restapi2.model.request.AuthRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthRepository {
    @Select("""
             Insert into user_tb(email,password,role,first_name,last_name,phone_number)
            Values(#{user.email},#{user.password},#{user.role},#{user.firstName},#{user.lastName},#{user.phoneNumber})
            RETURNING *
            """)
    @Result(property = "userId", column = "id")
    @Result(property = "firstName", column = "first_name")
    @Result(property = "lastName", column = "last_name")
    @Result(property = "phoneNumber", column = "phone_number")
    Auth insertUser(@Param("user") AuthRequest authRequest);

    @Select("""
            Select * from user_tb where email= #{email}
            """)
    @Result(property = "userId", column = "id")
    @Result(property = "firstName", column = "first_name")
    @Result(property = "lastName", column = "last_name")
    @Result(property = "phoneNumber", column = "phone_number")
    @Result(property = "roleId", column = "role")
    Auth findUserByEmail(String email);

    @Select("""
            select id from user_tb where email=#{email}
            """)
    @Result(property = "userId", column = "id")
    Integer getIdByEmail(String email);

    @Select("""
            SELECT exists(SELECT * FROM user_tb WHERE email = #{email});
            """)
    boolean checkForDuplicateEmail(String email);

    @Select("""
            SELECT id         AS userId,
                   first_name AS firstName,
                   last_name  AS lastName,
                   email,
                   password,
                   phone_number AS phoneNumber,
                   role
            FROM user_tb
            WHERE email = #{username};
            """)
    Auth getUserByEmail(String username);
}
