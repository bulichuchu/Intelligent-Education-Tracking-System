package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Students;

public interface StudentsService{
    public Students getById(String studentId);
    public void updateStudent(Students student);
    public void updateStudentClassName(String studentId, String className);
}
