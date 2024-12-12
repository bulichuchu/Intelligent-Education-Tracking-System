package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "assignments")
public class Assignments {

    @Id
    private String AssignmentId; // 对应数据表中的 assignmentid 列，作为主键
    private String AssignmentName; // 对应数据表中的 assignmentname 列，存储作业标题
    private String SubjectId; // 对应数据表中的 subjectid 列，关联学科编号
    private String TeacherId; // 对应数据表中的 teacherid 列，关联布置作业的教师编号
    private Date ReleaseTime; // 对应数据表中的 releasetime 列，记录作业发布时间
    private Date DueTime; // 对应数据表中的 duetime 列，设置作业截止时间

    // 默认构造函数
    public Assignments() {}

    public Assignments(String assignmentId, String assignmentName,String subjectId, String teacherId,  Date releaseTime, Date dueTime) {
        this.AssignmentId = assignmentId;
        this.SubjectId = subjectId;
        this.TeacherId = teacherId;
        this.AssignmentName = assignmentName;
        this.ReleaseTime = releaseTime;
        this.DueTime = dueTime;
    }

    public String getAssignmentId() {
        return AssignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.AssignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return AssignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        AssignmentName = assignmentName;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        this.SubjectId = subjectId;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        this.TeacherId = teacherId;
    }

    public Date getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.ReleaseTime = releaseTime;
    }

    public Date getDueTime() {
        return DueTime;
    }

    public void setDueTime(Date dueTime) {
        this.DueTime = dueTime;
    }
}