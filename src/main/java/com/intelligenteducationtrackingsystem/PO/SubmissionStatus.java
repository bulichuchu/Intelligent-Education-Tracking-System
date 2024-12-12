package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "submissionstatus")
public class SubmissionStatus {

    @Id
    private String submissionStatusId; // 对应数据表中的 submissionstatusid 列，作为主键

    private String assignmentId; // 对应数据表中的 assignmentid 列
    private String studentId; // 对应数据表中的 studentId 列
    private Date submissionTime; // 对应数据表中的 submissiontime 列
    private String status; // 对应数据表中的 status 列，例如 "已提交"、"未提交" 等状态描述

    // 默认构造函数
    public SubmissionStatus() {}

    public SubmissionStatus(String submissionStatusId, String assignmentId, String studentId, Date submissionTime, String status) {
        this.submissionStatusId = submissionStatusId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.submissionTime = submissionTime;
        this.status = status;
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
}