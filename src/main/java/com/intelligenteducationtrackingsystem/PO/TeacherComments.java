package com.intelligenteducationtrackingsystem.PO;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teachercomments")
public class TeacherComments {

    @Id
    private String CommentId; // 对应数据表中的 commentid 列，作为主键

    private String StudentId; // 对应数据表中的 studentid 列
    private String AssignmentId; // 对应数据表中的 assignmentid 列
    private String Comment; // 教师的评语内容，对应数据表中的 comment 列
    private Date CommentTime; // 评语的时间，对应数据表中的 commenttime 列

    // 默认构造函数
    public TeacherComments() {}

    public TeacherComments(String commentId, String studentId, String assignmentId, String comment, Date commentTime) {
        this.CommentId = commentId;
        this.StudentId = studentId;
        this.AssignmentId = assignmentId;
        this.Comment = comment;
        this.CommentTime = commentTime;
    }

    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String commentId) {
        this.CommentId = commentId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        this.StudentId = studentId;
    }

    public String getAssignmentId() {
        return AssignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.AssignmentId = assignmentId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public Date getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.CommentTime = commentTime;
    }
}