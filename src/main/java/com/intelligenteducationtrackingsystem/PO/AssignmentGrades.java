package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "assignmentgrades")
public class AssignmentGrades {

    @Id
    private String GradeId; // 对应数据表中的 gradeid 列，作为主键

    private String AssignmentId; // 对应数据表中的 assignmentid 列，关联作业编号
    private String StudentId; // 对应数据表中的 studentid 列，关联学生编号
    private int Grade; // 对应数据表中的 grade 列，存储学生此次作业获得的成绩
    private String TeacherId; // 对应数据表中的 teacherid 列，存放教师id
    private Date GradingTime; // 存放打分时间，对应数据表中的 gradingtime 列
    private String Path; // 存放作业提交路径，对应数据表中的 path 列

    // 默认构造函数
    public AssignmentGrades() {}

    public AssignmentGrades(String gradeId, String assignmentId, String studentId, int grade, String teacherId, Date gradingTime, String path) {
        this.GradeId = gradeId;
        this.AssignmentId = assignmentId;
        this.StudentId = studentId;
        this.Grade = grade;
        this.TeacherId = teacherId;
        this.GradingTime = gradingTime;
        this.Path = path;
    }

    public String getGradeId() {
        return GradeId;
    }

    public void setGradeId(String gradeId) {
        this.GradeId = gradeId;
    }

    public String getAssignmentId() {
        return AssignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.AssignmentId = assignmentId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        this.StudentId = studentId;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        this.Grade = grade;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        this.TeacherId = teacherId;
    }

    public Date getGradingTime() {
        return GradingTime;
    }

    public void setGradingTime(Date gradingTime) {
        this.GradingTime = gradingTime;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        this.Path = path;
    }
}