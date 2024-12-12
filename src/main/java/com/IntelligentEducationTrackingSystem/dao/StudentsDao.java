package com.IntelligentEducationTrackingSystem.dao;

import com.IntelligentEducationTrackingSystem.po.Students;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentsDao {
    @Select("select * from students where studentid = #{studentid}")
    public Students getById(String studentid);
}
