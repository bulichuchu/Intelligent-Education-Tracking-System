package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teachercomments")
public class TeacherComments {

    @Id
    private String commentId; // 对应数据表中的 commentid 列，作为主键

    private String studentId; // 对应数据表中的 studentid 列
    private String assignmentId; // 对应数据表中的 assignmentid 列
    private String comment; // 教师的评语内容，对应数据表中的 comment 列
    private Date commentTime; // 评语的时间，对应数据表中的 commenttime 列

    // 默认构造函数
    public TeacherComments() {}

    public TeacherComments(String commentId, String studentId, String assignmentId, String comment, Date commentTime) {
        this.commentId = commentId;
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.comment = comment;
        this.commentTime = commentTime;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}