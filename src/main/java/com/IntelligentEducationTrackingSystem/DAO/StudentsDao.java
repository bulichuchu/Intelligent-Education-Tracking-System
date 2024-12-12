package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Students;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentsDao {
    @Select("SELECT s.studentId, s.studentName, s.sex, s.age, s.address, s.phoneNumber, " +
            "c.className " +
            "FROM students s " +
            "JOIN classes c ON s.classId = c.classId " +
            "WHERE s.studentId = #{studentId}")
    @Results({
            @Result(property = "studentId", column = "studentId"),
            @Result(property = "studentName", column = "studentName"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age"),
            @Result(property = "address", column = "address"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "studentClass.className", column = "className")
    })
    public Students getById(String studentId);
}
