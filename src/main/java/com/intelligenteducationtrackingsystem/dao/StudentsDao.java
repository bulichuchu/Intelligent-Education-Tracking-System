package com.intelligenteducationtrackingsystem.dao;

import com.intelligenteducationtrackingsystem.po.Students;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StudentsDao {
    @Select("select * from students where studentid = #{studentid}")
    public Students getById(String studentid);
}
