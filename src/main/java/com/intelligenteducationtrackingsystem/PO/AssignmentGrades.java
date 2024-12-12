package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "assignmentgrades")
public class AssignmentGrades {

    @Id
    private String gradeId; // 对应数据表中的 gradeid 列，作为主键

    private String assignmentId; // 对应数据表中的 assignmentid 列，关联作业编号
    private String studentId; // 对应数据表中的 studentid 列，关联学生编号
    private int grade; // 对应数据表中的 grade 列，存储学生此次作业获得的成绩
    private String teacherId; // 对应数据表中的 teacherid 列，存放教师id
    private Date gradingTime; // 存放打分时间，对应数据表中的 gradingtime 列
    private String path; // 存放作业提交路径，对应数据表中的 path 列

    // 默认构造函数
    public AssignmentGrades() {}

    public AssignmentGrades(String gradeId, String assignmentId, String studentId, int grade, String teacherId, Date gradingTime, String path) {
        this.gradeId = gradeId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.grade = grade;
        this.teacherId = teacherId;
        this.gradingTime = gradingTime;
        this.path = path;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getGradingTime() {
        return gradingTime;
    }

    public void setGradingTime(Date gradingTime) {
        this.gradingTime = gradingTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}