package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.ClassNotifications;
import com.IntelligentEducationTrackingSystem.PO.StudentErrorLogs;
import com.IntelligentEducationTrackingSystem.PO.Students;

import com.IntelligentEducationTrackingSystem.pojo.StudentCourses;
import com.IntelligentEducationTrackingSystem.pojo.StudentLearning;
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
    //查询作业名称
    @Select("SELECT a.assignmentName FROM Assignments a WHERE a.assignmentId = #{assignmentId}")
    String findAssignmentNameByAssignmentId(@Param("assignmentId") String assignmentId);
    //查询作业id
    @Select("SELECT assignmentId FROM assignmentDetails WHERE studentId = #{studentId}")
    List<assignmentDetails> getAssignmentsByStudentId(@Param("studentId") String studentId);
    //查询作业信息
    @Select("SELECT * FROM assignmentDetails " +
            "WHERE studentId = #{studentId} " +
            "AND (#{subjectName} IS NULL OR #{subjectName} = '' OR subjectName = #{subjectName})")
    public List<assignmentDetails> getAssignmentDetails(@Param("studentId") String studentId,
                                                          @Param("subjectName") String subjectName);
    //查看通知
    @Select("SELECT cn.* FROM classnotifications cn " +
            "JOIN students s ON cn.classId = s.classId " +
            "WHERE s.studentId = #{studentId}")
    public List<ClassNotifications> getClassNotifications(@Param("studentId") String studentId);

    //查询课程
    @Select("SELECT * FROM StudentCourses WHERE studentId = #{studentId}")
    public List<StudentCourses> getStudentCourses(@Param("studentId") String studentId);
    //浏览学习资源
    @Select("SELECT * FROM StudentLearning WHERE (#{subjectName} ='' OR subjectName = #{subjectName}) AND (#{resourceType} ='' OR resourceType = #{resourceType})")
    public List<StudentLearning> getStudentLearning(@Param("subjectName") String subjectName, @Param("resourceType") String resourceType);
    //查看错题集
    @Select("SELECT * FROM studenterrorlogs WHERE studentId = #{studentId}")
    public List<StudentErrorLogs> getStudentErrorLogs(@Param("studentId") String studentId);
    //修改错题集
    @Update("UPDATE studenterrorlogs SET errorQuestion=#{errorQuestion},errorAnswer=#{errorAnswer},correctAnswer=#{correctAnswer} WHERE errorLogId=#{errorLogId}")
    void updateStudentErrorLogs(@Param("errorLogId") int errorLogId, @Param("errorQuestion") String errorQuestion, @Param("errorAnswer") String errorAnswer, @Param("correctAnswer") String correctAnswer);
    //添加错题集
    @Insert("INSERT INTO studenterrorlogs (studentId, assignmentId, errorQuestion, errorAnswer, correctAnswer) VALUES (#{studentId}, #{assignmentId}, #{errorQuestion}, #{errorAnswer}, #{correctAnswer})")
    void insertStudentErrorLog(String studentId, String assignmentId, String errorQuestion, String errorAnswer, String correctAnswer);
    //删除错题集
    @Delete("DELETE FROM studenterrorlogs WHERE errorLogId = #{errorLogId}")
    void deleteStudentErrorLog(@Param("errorLogId") int errorLogId);
}

