package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.ClassNotifications;
import com.IntelligentEducationTrackingSystem.PO.StudentErrorLogs;
import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.pojo.StudentCourses;
import com.IntelligentEducationTrackingSystem.pojo.StudentLearning;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import java.io.OutputStream;
import java.util.List;

public interface StudentsService{
    public Students getById(String studentId);
    public void updateStudent(Students student);
    public void updateStudentClassName(String studentId, String className);
    public String findAssignmentNameByAssignmentId(String assignmentId);
    public List<assignmentDetails> getAssignmentDetails(String studentId, String subjectName);
    public void updateAssignment(String studentId, String assignmentId, String filePath);
    public List<ClassNotifications> getClassNotifications(String studentId);
    public List<StudentCourses> getStudentCourses(String studentId);
    public List<StudentLearning> getStudentLearning(String subjectName,String resourceType);
    public List<StudentErrorLogs> getStudentErrorLogs(String studentId);
    public void updateErrorLog(StudentErrorLogs errorLog);
    public void addErrorLog(StudentErrorLogs errorLog);
    public List<assignmentDetails> getAssignmentsByStudentId(String studentId);
    public void deleteErrorLog(int errorLogId);
}
