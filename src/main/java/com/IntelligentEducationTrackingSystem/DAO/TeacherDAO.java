package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.*;
import com.IntelligentEducationTrackingSystem.pojo.StudentProgressDetail;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    @Select("SELECT c.className FROM classes c " +
            "JOIN teaching tc ON c.classID = tc.classID " +
            "WHERE tc.teacherId = #{teacherId}")
    List<String> getTeacherClasses(String teacherId);

    @Select("SELECT * FROM assignments WHERE teacherId = #{teacherId}")
    List<Assignments> getTeacherAssignments(String teacherId);

    @Select("""
            SELECT\s
                ss.submissionstatusId,\s
                ss.assignmentId,\s
                ss.studentId,
                ss.submissiontime,\s
                ss.status,\s
                s.studentname,
                a.assignmentname,\s
                c.classname,
                ag.path,
                ag.grade
            FROM submissionstatus ss
            JOIN students s ON ss.studentId = s.studentId
            JOIN assignments a ON ss.assignmentId = a.assignmentId
            JOIN classes c ON s.classId = c.classId
            LEFT JOIN assignmentgrades ag ON ss.assignmentId = ag.assignmentId\s
                AND ss.studentId = ag.studentId
            WHERE ss.assignmentId = #{assignmentId}
            """)
    List<SubmissionDetails> getSubmissionsByAssignmentId(String assignmentId);

    @Insert("""
            INSERT INTO teachercomments (commentid, studentid, assignmentid, 
                                       comment, commenttime)
            VALUES (#{commentId}, #{studentId}, #{assignmentId}, 
                   #{comment}, #{commentTime})
            """)
    void saveTeacherComment(TeacherComments comment);

    @Insert("""
    INSERT INTO assignmentgrades (
        gradeID, 
        assignmentid, 
        studentid, 
        grade, 
        teacherID, 
        gradingTime
    ) VALUES (
        #{gradeId}, 
        #{assignmentId}, 
        #{studentId}, 
        #{grade}, 
        #{teacherId}, 
        DATEADD(HOUR, 8, GETUTCDATE())
    )
""")
    void insertAssignmentGrade(@Param("gradeId") String gradeId,
                               @Param("assignmentId") String assignmentId,
                               @Param("studentId") String studentId,
                               @Param("grade") int grade,
                               @Param("teacherId") String teacherId);
    @Select("""
    SELECT a.assignmentid 
    FROM assignments a 
    JOIN submissionstatus s ON a.assignmentid = s.assignmentid 
    WHERE s.studentid = #{studentId} 
    AND s.status = '已提交' 
    AND NOT EXISTS (
        SELECT 1 FROM teachercomments tc 
        WHERE tc.studentid = s.studentid 
        AND tc.assignmentid = s.assignmentid
    )
    ORDER BY a.releasetime DESC 
    LIMIT 1
""")
    String getLatestAssignmentForStudent(@Param("studentId") String studentId);
    @Select("""
    SELECT DISTINCT s.studentId 
    FROM students s 
    JOIN classes c ON s.classId = c.classId 
    JOIN teaching tc ON c.classId = tc.classId 
    WHERE tc.teacherId = #{teacherId}
""")
    List<String> getStudentsByTeacher(String teacherId);

    @Insert("""
    INSERT INTO submissionstatus (
        submissionstatusid, 
        assignmentid, 
        studentid, 
        status
    ) VALUES (
        #{submissionStatusId}, 
        #{assignmentId}, 
        #{studentId}, 
        #{status}
    )
""")
    void insertSubmissionStatus(SubmissionStatus status);

    @Insert("""
            INSERT INTO assignmentgrades(
            gradeID,assignmentid, 
                    studentid, grade,teacherID,path)
                    values (
                    #{gradeId},
                    #{assignmentId},
                    #{studentId},null,
                    #{teacherId},null
                    )
            """)
    void insertAssignmentGrades(AssignmentGrades assignmentGrades);

    @Update("""
    UPDATE submissionstatus 
    SET status = #{status}, 
        submissionTime = DATEADD(HOUR, 8, GETUTCDATE())
    WHERE studentId = #{studentId} 
    AND assignmentId = #{assignmentId}
""")
    void updateSubmissionStatus(@Param("studentId") String studentId,
                                @Param("assignmentId") String assignmentId,
                                @Param("status") String status);


    @Select("""
   SELECT slp.*, s.studentName
                       FROM studentlearningprogress slp
                       JOIN students s ON slp.studentid = s.studentid
                       WHERE slp.studentid IN (
                           SELECT studentid
                           FROM students
                           WHERE classid = (
                               SELECT classid
                               FROM teaching
                               WHERE teacherid = #{teacherId}
                           )
                       );
                       
                     
""")
    @Results({
            @Result(property = "progressId", column = "progressId"),
            @Result(property = "studentId", column = "studentId"),
            @Result(property = "studyDuration", column = "studyDuration"),
            @Result(property = "completedChapters", column = "completedChapters"),
            @Result(property = "studentName", column = "studentName"),
    })
    List<StudentProgressDetail> getLearningProgress(@Param("teacherId") String teacherId);


    @Insert("""
    INSERT INTO learningresources (
        resourceId, resourceName, resourceType, 
        subjectId, teacherId, uploadTime, url
    ) VALUES (
        #{resourceId}, #{resourceName}, #{resourceType}, 
        #{subjectId}, #{teacherId}, #{uploadTime}, #{url}
    )
    """)
    void insertResource(LearningResources resource);

    @Delete("DELETE FROM learningresources WHERE resourceId = #{resourceId}")
    void deleteResource(String resourceId);

    @Update("""
    UPDATE learningresources SET 
        resourceName = #{resourceName},
        resourceType = #{resourceType},
        url = #{url}
    WHERE resourceId = #{resourceId}
    """)
    void updateResource(LearningResources resource);

    @Select("""
    SELECT lr.* FROM learningresources lr
    JOIN teachers t ON lr.subjectId = t.subjectId
    WHERE t.teacherId = #{teacherId}
    AND (#{resourceType} IS NULL OR lr.resourceType = #{resourceType})
    ORDER BY lr.uploadTime DESC
    """)
    List<LearningResources> getResourcesByTeacher(
            @Param("teacherId") String teacherId,
            @Param("resourceType") String resourceType
    );

    @Select("SELECT * FROM learningresources WHERE resourceId = #{resourceId}")
    LearningResources getResourceById(String resourceId);

    @Insert("""
    INSERT INTO classnotifications (
        notificationId, classId, notificationTitle, 
        notificationContent, releaseTime
    ) VALUES (
        #{notificationId}, #{classId}, #{notificationTitle}, 
        #{notificationContent}, #{releaseTime}
    )
""")
    void insertClassNotification(ClassNotifications notification);

    @Select("""
    SELECT cn.*, c.className 
    FROM classnotifications cn
    JOIN teaching t ON cn.classId = t.classId
    JOIN classes c ON cn.classId = c.classId
    WHERE t.teacherId = #{teacherId}
    ORDER BY cn.releaseTime DESC
""")
    @Results({
            @Result(property = "notificationId", column = "notificationId"),
            @Result(property = "classId", column = "classId"),
            @Result(property = "notificationTitle", column = "notificationTitle"),
            @Result(property = "notificationContent", column = "notificationContent"),
            @Result(property = "releaseTime", column = "releaseTime"),
            @Result(property = "className", column = "className")
    })
    List<Map<String, Object>> getNotificationsByTeacher(String teacherId);

    @Select("""
    SELECT c.classId, c.className 
    FROM classes c
    JOIN teaching t ON c.classId = t.classId
    WHERE t.teacherId = #{teacherId}
""")
    List<Map<String, String>> getTeacherClassList(String teacherId);

    @Delete("DELETE FROM classnotifications WHERE notificationId = #{notificationId}")
    void deleteClassNotification(String notificationId);

    @Select("SELECT * FROM classnotifications WHERE notificationId = #{notificationId}")
    ClassNotifications getNotificationById(String notificationId);

    @Select("""
    SELECT DISTINCT c.classId, c.className, s.subjectName
    FROM classes c
    JOIN teaching t ON c.classId = t.classId
    JOIN teachers tc ON t.teacherId = tc.teacherId
    JOIN subjects s ON tc.subjectId = s.subjectId
    WHERE t.teacherId = #{teacherId}
""")
    @Results({
            @Result(property = "classId", column = "classId"),
            @Result(property = "className", column = "className"),
            @Result(property = "subjectName", column = "subjectName")
    })
    List<Map<String, Object>> getTeacherSchedule(String teacherId);
    @Update("""
    UPDATE assignmentgrades 
    SET grade = #{grade}
    WHERE assignmentId = #{assignmentId} 
    AND studentId = #{studentId}
    AND teacherId = #{teacherId}
""")
    void updateAssignmentGrade(
            @Param("assignmentId") String assignmentId,
            @Param("studentId") String studentId,
            @Param("grade") int grade,
            @Param("teacherId") String teacherId
    );
}
