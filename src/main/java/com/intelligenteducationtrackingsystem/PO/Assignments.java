package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "assignments")
public class Assignments {

    @Id
    private String assignmentId; // 对应数据表中的 assignmentid 列，作为主键
    private String assignmentName; // 对应数据表中的 assignmentname 列，存储作业标题
    private String subjectId; // 对应数据表中的 subjectid 列，关联学科编号
    private String teacherId; // 对应数据表中的 teacherid 列，关联布置作业的教师编号
    private Date releaseTime; // 对应数据表中的 releasetime 列，记录作业发布时间
    private Date dueTime; // 对应数据表中的 duetime 列，设置作业截止时间

    // 默认构造函数
    public Assignments() {}

    public Assignments(String assignmentId, String assignmentName,String subjectId, String teacherId,  Date releaseTime, Date dueTime) {
        this.assignmentId = assignmentId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.assignmentName = assignmentName;
        this.releaseTime = releaseTime;
        this.dueTime = dueTime;
    }

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
        assignmentName = assignmentName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
}