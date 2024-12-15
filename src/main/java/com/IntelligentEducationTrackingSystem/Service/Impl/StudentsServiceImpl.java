package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.StudentsDao;
import com.IntelligentEducationTrackingSystem.PO.ClassNotifications;
import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import com.IntelligentEducationTrackingSystem.pojo.StudentCourses;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
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
    public List<ClassNotifications> getClassNotifications(String studentId) {
        return studentsDao.getClassNotifications(studentId);
    }
    public List<StudentCourses> getStudentCourses(String studentId) {
        return studentsDao.getStudentCourses(studentId);
    }

}
