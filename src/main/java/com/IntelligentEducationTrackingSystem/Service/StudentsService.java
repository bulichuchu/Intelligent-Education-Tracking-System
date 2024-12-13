package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentsService{
    public Students getById(String studentId);
    public void updateStudent(Students student);
    public void updateStudentClassName(String studentId, String className);
    public List<assignmentDetails> getAssignmentDetails(String studentId, String subjectName);
}
