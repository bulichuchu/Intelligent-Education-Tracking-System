package com.IntelligentEducationTrackingSystem.pojo;

import java.util.Date;

public class SubmissionDetails {
    private String submissionStatusId;
    private String assignmentId;
    private String studentId;
    private Date submissionTime;
    private String status;
    // 额外的显示字段
    private String studentName;
    private String assignmentName;
    private String className;
    private String path;

    public SubmissionDetails() {
    }

    public String getSubmissionStatusId() {
        return submissionStatusId;
    }

    public void setSubmissionStatusId(String submissionStatusId) {
        this.submissionStatusId = submissionStatusId;
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

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
