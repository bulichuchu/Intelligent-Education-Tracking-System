package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {
    @Select("SELECT * FROM users")
    List<Users> showAllUsers();
    @Select("SELECT * FROM users WHERE userId = #{userId}")
    Users check(@Param("userId") String userId);
    @Update("UPDATE users SET userType = #{userType} WHERE userId = #{userId}")
    void updateUsers(@Param("userType") String userType, @Param("userId") String userId);
    @Insert("INSERT INTO users VALUES (#{userId},#{userName},#{userPassword},#{userType})")
    void addUsers(@Param("userId") String userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") String userType);
    @Delete("DELETE FROM users WHERE userId = #{userId}")
    void deleteUsers(@Param("userId") String userId);
}
