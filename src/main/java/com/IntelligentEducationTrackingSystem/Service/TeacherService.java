package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.Subjects;
import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;

import java.util.Date;
import java.util.List;

public interface TeacherService {
    List<SubmissionDetails> getSubmissionsByTeacher(String teacherId, String assignmentId, String className);
    List<String> getTeacherClasses(String teacherId);
    List<Assignments> getTeacherAssignments(String teacherId);
    public void addAssignment(String assignmentId, String assignmentName, String subjectId, String teacherId, Date releaseTime, Date dueTime);
}
