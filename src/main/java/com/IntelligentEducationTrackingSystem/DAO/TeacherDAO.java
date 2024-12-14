package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherDAO {

    @Select("SELECT subjectID from teachers where teacherID= #{teacherID}")
    String getSubjectIDByTeacherID(String teacherID);

    @Select("SELECT subjectName from subjects where subjectID= #{subjectID}")
    String getSubjectNameBySubjectID(String subjectID);

    // 插入新的作业信息
    @Insert("INSERT INTO assignments (assignmentId, assignmentName, subjectId, teacherId, releaseTime, dueTime) " +
            "VALUES (#{assignmentId}, #{assignmentName}, #{subjectId}, #{teacherId}, #{releaseTime}, #{dueTime})")
    void insertAssignment(Assignments assignment);

    // 查询所有作业
    @Select("SELECT * FROM assignments")
    List<Assignments> getAllAssignments();

    // 根据 assignmentId 查询作业
    @Select("SELECT * FROM assignments WHERE assignmentId = #{assignmentId}")
    Assignments getAssignmentById(String assignmentId);

    // 根据 teacherId 查询作业
    @Select("SELECT assignmentId, CAST(assignmentName AS NVARCHAR) AS assignmentName, subjectId, teacherId, releaseTime, dueTime " +
            "FROM assignments WHERE teacherId = #{teacherId}")
    @Results({
            @Result(property = "assignmentId", column = "assignmentId"),
            @Result(property = "assignmentName", column = "assignmentName"),
            @Result(property = "subjectId", column = "subjectId"),
            @Result(property = "teacherId", column = "teacherId"),
            @Result(property = "releaseTime", column = "releaseTime"),
            @Result(property = "dueTime", column = "dueTime")
    })
    List<Assignments> getAssignmentByTeacherId(@Param("teacherId") String teacherId);


    // 更新作业信息
    @Update("UPDATE assignments SET assignmentName = #{assignmentName}, subjectId = #{subjectId}, teacherId = #{teacherId}, " +
            "releaseTime = #{releaseTime}, dueTime = #{dueTime} WHERE assignmentId = #{assignmentId}")
    void updateAssignment(Assignments assignments);

    // 删除作业信息
    @Delete("DELETE FROM assignments WHERE assignmentId = #{assignmentId}")
    void deleteAssignment(String assignmentId);
}
