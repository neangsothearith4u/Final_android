package com.sothearithcompany.spring_homework_restapi2.repository;

import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUser;
import com.sothearithcompany.spring_homework_restapi2.model.appUser.AppUserRequest;
import com.sothearithcompany.spring_homework_restapi2.model.jwt.JwtChangePasswordRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppUserRepository {
    @Select("""
            INSERT INTO user_tb
            VALUES (DEFAULT, #{email}, #{firstName}, #{lastName}, #{phone}, #{password}, #{role},DEFAULT)
            RETURNING id, email, password, role, phone_number AS phone, first_name AS firstName, last_name AS lastName;
            """)
    AppUser insertUser(AppUserRequest appUserRequest);

    @Select("""
            INSERT INTO tb_retailer_account
            VALUES (
                    DEFAULT, #{user.roleId}, #{user.email}, #{user.password}, DEFAULT, DEFAULT, DEFAULT, DEFAULT
                   )
            RETURNING *;
            """)
    @Result(property = "isVerified", column = "is_verified")
    @Result(property = "isActive", column = "is_active")
    @Result(property = "roleId", column = "role_id")
    AppUser insertRetailerUser(@Param("user") AppUserRequest appUserRequest);

    @Select("""
            SELECT id,
                   first_name AS firstName,
                   last_name  AS lastName,
                   email,
                   password,
                   phone_number as phone,
                   role
            FROM user_tb
            WHERE email = #{email};
            """)
    AppUser findDistributorUserByEmail(String email);

    @Select("""
            SELECT ta.id as id, email, password, tr.name as role, role_id as roleId, is_verified as isVerified, is_active as isActive FROM tb_distributor_account as ta
            JOIN tb_role tr ON ta.role_id = tr.id
            WHERE ta.id = #{id};
            """)
    @Result(property = "userId", column = "user_id")
    AppUser findDistributorUserById(Integer id);

    @Select("""
            SELECT ta.id as id, email, password, tr.name as role, role_id as roleId, is_verified as isVerified, is_active as isActive 
            FROM tb_retailer_account as ta
            JOIN tb_role tr ON ta.role_id = tr.id
            WHERE email = #{email};
            """)
    @Result(property = "userId", column = "user_id")
    AppUser findRetailerUserByEmail(String email);


    @Select("""
            SELECT exists(SELECT phone_number FROM tb_distributor_phone WHERE phone_number = #{phone});
            """)
    Boolean checkPhoneNumberFromDistributorPhone(String phone);

    @Select("""
            SELECT exists(SELECT primary_phone_number FROM tb_distributor_info WHERE primary_phone_number = #{phone});
            """)
    Boolean checkPhoneNumberFromDistributorDetail(String phone);

    @Select("""
            SELECT exists(SELECT phone_number FROM tb_retailer_phone WHERE phone_number = #{phone});
            """)
    Boolean checkPhoneNumberFromRetailerPhone(String phone);

    @Select("""
            SELECT exists(SELECT primary_phone_number FROM tb_retailer_info WHERE primary_phone_number = #{phone});
            """)
    Boolean checkPhoneNumberFromRetailerDetail(String phone);

    @Select("""
            SELECT role_id FROM tb_distributor_account
            WHERE email = #{email};
            """)
    Integer getRoleIdByMail(String email);

    @Select("""
            SELECT role_id FROM tb_retailer_account
            WHERE email = #{email};
            """)
    Integer getRoleIdByMailRetailer(String email);

    @Select("""
            SELECT is_verified FROM tb_distributor_account
            WHERE email = #{email};
            """)
    Boolean getVerifyDistributorEmail(String email);

    @Select("""
            SELECT is_verified FROM tb_retailer_account
            WHERE email = #{email};
            """)
    Boolean getVerifyRetailerEmail(String email);

    @Select("""
            update tb_distributor_account
            set password = #{newPassword}
            where email = #{email}
            returning *;
            """)
    @Result(property = "isVerified", column = "is_verified")
    @Result(property = "isActive", column = "is_active")
    @Result(property = "roleId", column = "role_id")
    AppUser updateDistributorUser(JwtChangePasswordRequest request);

    @Select("""
            update tb_retailer_account
            set password = #{newPassword}
            where email = #{email}
            returning *;
            """)
    @Result(property = "isVerified", column = "is_verified")
    @Result(property = "isActive", column = "is_active")
    @Result(property = "roleId", column = "role_id")
    AppUser updateRetailerUser(JwtChangePasswordRequest request);

    @Select("""
            update tb_distributor_account
            set password = #{newPassword}
            where email = #{email}
            returning password;
            """)
    String updateForgetDistributorUser(String email, String newPassword);

    @Select("""
            update tb_retailer_account
            set password = #{newPassword}
            where email = #{email}
            returning password;
            """)
    String updateForgetRetailerUser(String email, String newPassword);

    @Select("""
            SELECT id FROM user_tb
            WHERE email = #{email};
            """)
    Integer getUserIdByEmail(String email);

    @Select("""
            SELECT id FROM tb_retailer_account
            WHERE email = #{email};
            """)
    Integer getUserIdByMailRetailer(String email);

    @Select("""
            SELECT EXISTS(SELECT * FROM user_tb WHERE email ILIKE '${email}');
            """)
    boolean checkDuplicateUser(String email);
}
