package com.IntelligentEducationTrackingSystem.pojo;

import java.util.Date;

public class assignmentDetails {
    private String assignmentId; // 对应数据表中的 assignmentid 列，作为主键
    private String assignmentName; // 对应数据表中的 assignmentname 列，存储作业标题

    private String teacherId; // 对应数据表中的 teacherid 列，关联布置作业的教师编号
    private String teacherName; // 教师姓名，对应数据表中的 teachername 列
    private Date releaseTime; // 对应数据表中的 releasetime 列，记录作业发布时间
    private Date dueTime; // 对应数据表中的 duetime 列，设置作业截止时间

    private String studentId; // 对应数据表中的 studentid 列，作为主键
    private String studentName; // 对应数据表中的 studentname 列，存储学生姓名
    private String subjectName; // 对应数据表中的 subjectname 列，存储学科名称

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "assignmentDetails{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", releaseTime=" + releaseTime +
                ", dueTime=" + dueTime +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
