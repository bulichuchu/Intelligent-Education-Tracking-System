package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDAO {

    // 根据用户名查询用户信息
    @Select("SELECT * FROM users WHERE userId = #{userId}")
    Users getUserById(@Param("userId") String userId);

    // 验证用户名和密码
    @Select("SELECT * FROM users WHERE userId = #{userId} AND userPassword = #{password}")
    Users validateUser(@Param("userId") String userId, @Param("password") String password);
}
