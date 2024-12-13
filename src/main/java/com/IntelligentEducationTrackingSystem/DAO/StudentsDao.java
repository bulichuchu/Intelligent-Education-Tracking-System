package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.Students;

import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentsDao {
    //查询学生信息
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
    //更新学生信息
    @Update("UPDATE students SET studentName=#{studentName}, sex=#{sex}, age=#{age}, address=#{address}, phoneNumber=#{phoneNumber} WHERE studentId=#{studentId}")
    void updateStudent(Students student);

    @Update("UPDATE classes SET className=#{className} WHERE classId=(SELECT classId FROM students WHERE studentId=#{studentId})")
    void updateStudentClassName(@Param("studentId") String studentId, @Param("className") String className);
    //查询作业信息
    @Select("SELECT * FROM assignmentDetails " +
            "WHERE studentId = #{studentId} " +
            "AND (#{subjectName} IS NULL OR #{subjectName} = '' OR subjectName = #{subjectName})")
    public List<assignmentDetails> getAssignmentDetails(@Param("studentId") String studentId,
                                                          @Param("subjectName") String subjectName);

}
