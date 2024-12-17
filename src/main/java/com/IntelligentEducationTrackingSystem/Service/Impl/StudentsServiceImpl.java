package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.StudentsDao;
import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.ClassNotifications;
import com.IntelligentEducationTrackingSystem.PO.StudentErrorLogs;
import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import com.IntelligentEducationTrackingSystem.pojo.StudentCourses;
import com.IntelligentEducationTrackingSystem.pojo.StudentLearning;
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
    public String findAssignmentNameByAssignmentId(String assignmentId) {
        return studentsDao.findAssignmentNameByAssignmentId(assignmentId);
    }
    public List<assignmentDetails> getAssignmentDetails(String studentId, String subjectName) {
        return studentsDao.getAssignmentDetails(studentId, subjectName);
    }
    public void updateAssignment(String studentId, String assignmentId, String filePath) {
        studentsDao.updateAssignmentgrades(assignmentId, filePath, studentId);
        studentsDao.updateSubmissionstatus(studentId, assignmentId);
    }
    public List<ClassNotifications> getClassNotifications(String studentId) {
        return studentsDao.getClassNotifications(studentId);
    }
    public List<StudentCourses> getStudentCourses(String studentId) {
        return studentsDao.getStudentCourses(studentId);
    }
    public List<StudentLearning> getStudentLearning(String subjectName, String resourceType) {
        return studentsDao.getStudentLearning(subjectName,resourceType);
    }
    public List<StudentErrorLogs> getStudentErrorLogs(String studentId) {
        return studentsDao.getStudentErrorLogs(studentId);
    }
    public void updateErrorLog(StudentErrorLogs errorLog) {
        studentsDao.updateStudentErrorLogs(errorLog.getErrorLogId(),
                errorLog.getErrorQuestion(), errorLog.getErrorAnswer(), errorLog.getCorrectAnswer());
    }
    public void addErrorLog(StudentErrorLogs errorLog) {
        studentsDao.insertStudentErrorLog(errorLog.getStudentId(), errorLog.getAssignmentId(),
                errorLog.getErrorQuestion(), errorLog.getErrorAnswer(), errorLog.getCorrectAnswer());
    }
    public List<assignmentDetails> getAssignmentsByStudentId(String studentId) {
        return studentsDao.getAssignmentsByStudentId(studentId);
    }
    public void deleteErrorLog(int errorLogId) {
        studentsDao.deleteStudentErrorLog(errorLogId);
    }
}
