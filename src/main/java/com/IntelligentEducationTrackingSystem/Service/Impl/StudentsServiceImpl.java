package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.StudentsDao;
import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
