package com.intelligenteducationtrackingsystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "submissionstatus")
public class SubmissionStatus {

    @Id
    private String SubmissionStatusId; // 对应数据表中的 submissionstatusid 列，作为主键

    private String AssignmentId; // 对应数据表中的 assignmentid 列
    private String StudentId; // 对应数据表中的 studentId 列
    private Date SubmissionTime; // 对应数据表中的 submissiontime 列
    private String Status; // 对应数据表中的 status 列，例如 "已提交"、"未提交" 等状态描述

    // 默认构造函数
    public SubmissionStatus() {}

    public SubmissionStatus(String submissionStatusId, String assignmentId, String studentId, Date submissionTime, String status) {
        this.SubmissionStatusId = submissionStatusId;
        this.AssignmentId = assignmentId;
        this.StudentId = studentId;
        this.SubmissionTime = submissionTime;
        this.Status = status;
    }

    public String getSubmissionStatusId() {
        return SubmissionStatusId;
    }

    public void setSubmissionStatusId(String submissionStatusId) {
        this.SubmissionStatusId = submissionStatusId;
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

    public Date getSubmissionTime() {
        return SubmissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.SubmissionTime = submissionTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
}