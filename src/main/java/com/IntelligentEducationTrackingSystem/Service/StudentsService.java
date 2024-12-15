package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.ClassNotifications;
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
    public List<assignmentDetails> getAssignmentDetails(String studentId, String subjectName);
    public List<ClassNotifications> getClassNotifications(String studentId);
    public List<StudentCourses> getStudentCourses(String studentId);
    public List<StudentLearning> getStudentLearning(String subjectName,String resourceType);
}
