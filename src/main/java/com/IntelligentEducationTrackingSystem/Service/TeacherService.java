package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.*;
import com.IntelligentEducationTrackingSystem.pojo.StudentProgressDetail;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<SubmissionDetails> getSubmissionsByTeacher(String teacherId, String assignmentId, String className);
    List<String> getTeacherClasses(String teacherId);
    List<Assignments> getTeacherAssignments(String teacherId);
    void addAssignment(String assignmentId, String assignmentName, String subjectId, String teacherId, Date releaseTime, Date dueTime);

    List<SubmissionDetails> getSubmissionsByAssignment(String assignmentId);
    void gradeAssignment(String studentId, String assignmentId, int grade, String comment,String teacherId);
    Map<String, String> parseScanCode(String qrCode);
    @Transactional
    public void updateSubmissionStatus(String studentId, String assignmentId, String status) ;
    List<StudentProgressDetail> getLearningProgress(String teacherId, String subjectName);
    void addLearningResource(LearningResources resource);
    void deleteLearningResource(String resourceId, String teacherId);
    void updateLearningResource(LearningResources resource, String teacherId);
    List<LearningResources> getResourcesByTeacher(String teacherId, String resourceType);
    LearningResources getResourceById(String resourceId);
}
