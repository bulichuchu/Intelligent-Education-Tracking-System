package com.intelligenteducationtrackingsystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studenterrorlogs")
public class StudentErrorLogs {

    @Id
    private String ErrorLogId; // 对应数据表中的 errorlogid 列，作为主键

    private String StudentId; // 对应数据表中的 studentid 列，关联出现错误的学生编号
    private String AssignmentId; // 对应数据表中的 assignmentid 列，关联作业编号
    private String ErrorQuestion; // 对应数据表中的 errorquestion 列，记录出现错误的题目内容描述
    private String ErrorAnswer; // 对应数据表中的 erroranswer 列，记录学生答错的答案内容
    private String CorrectAnswer; // 对应数据表中的 correctanswer 列，记录正确答案内容

    // 默认构造函数
    public StudentErrorLogs() {}

    public StudentErrorLogs(String errorLogId, String studentId, String assignmentId, String errorQuestion, String errorAnswer, String correctAnswer) {
        this.ErrorLogId = errorLogId;
        this.StudentId = studentId;
        this.AssignmentId = assignmentId;
        this.ErrorQuestion = errorQuestion;
        this.ErrorAnswer = errorAnswer;
        this.CorrectAnswer = correctAnswer;
    }

    public String getErrorLogId() {
        return ErrorLogId;
    }

    public void setErrorLogId(String errorLogId) {
        this.ErrorLogId = errorLogId;
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


    public String getErrorQuestion() {
        return ErrorQuestion;
    }

    public void setErrorQuestion(String errorQuestion) {
        this.ErrorQuestion = errorQuestion;
    }

    public String getErrorAnswer() {
        return ErrorAnswer;
    }

    public void setErrorAnswer(String errorAnswer) {
        this.ErrorAnswer = errorAnswer;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.CorrectAnswer = correctAnswer;
    }
}