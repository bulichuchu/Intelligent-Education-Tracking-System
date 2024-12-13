package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.StudentsDao;
import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentsService")
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentsDao studentsDao;
    public Students getById(String studentId) {
        return studentsDao.getById(studentId);
    }
    public void updateStudent(Students student) {
        studentsDao.updateStudent(student);
    }

    public void updateStudentClassName(String studentId, String className) {
        studentsDao.updateStudentClassName(studentId, className);
    }
    public List<assignmentDetails> getAssignmentDetails(String studentId, String subjectName) {
        return studentsDao.getAssignmentDetails(studentId, subjectName);
    }
}
