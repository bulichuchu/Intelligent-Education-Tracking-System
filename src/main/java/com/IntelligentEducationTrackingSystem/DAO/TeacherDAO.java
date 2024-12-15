package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
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


    @Select("SELECT ss.submissionStatusId, ss.assignmentId, ss.studentId, ss.submissionTime, ss.status, " +
            "s.studentName, a.assignmentName, c.className " +
            "FROM submissionstatus ss " +
            "JOIN students s ON ss.studentId = s.studentId " +
            "JOIN assignments a ON ss.assignmentId = a.assignmentId " +
            "JOIN classes c ON s.classId = c.classId " +
            "WHERE a.teacherId = #{teacherId} " +
            "AND (#{assignmentId} IS NULL OR #{assignmentId} = '' OR ss.assignmentId = #{assignmentId}) " +
            "AND (#{className} IS NULL OR #{className} = '' OR c.className = #{className})")
    @Results({
            @Result(property = "submissionStatusId", column = "submissionStatusId"),
            @Result(property = "assignmentId", column = "assignmentId"),
            @Result(property = "studentId", column = "studentId"),
            @Result(property = "submissionTime", column = "submissionTime"),
            @Result(property = "status", column = "status"),
            @Result(property = "studentName", column = "studentName"),
            @Result(property = "assignmentName", column = "assignmentName"),
            @Result(property = "className", column = "className")
    })
    List<SubmissionDetails> getSubmissionsByTeacher(@Param("teacherId") String teacherId,
                                                    @Param("assignmentId") String assignmentId,
                                                    @Param("className") String className);

    @Select("SELECT DISTINCT c.className FROM classes c " +
            "JOIN classes tc ON c.classId = tc.classId " +
            "WHERE tc.teacherId = #{teacherId}")
    List<String> getTeacherClasses(String teacherId);

    @Select("SELECT * FROM assignments WHERE teacherId = #{teacherId}")
    List<Assignments> getTeacherAssignments(String teacherId);
}
