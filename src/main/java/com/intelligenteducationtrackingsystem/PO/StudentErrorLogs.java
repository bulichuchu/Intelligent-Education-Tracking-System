package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "studenterrorlogs")
public class StudentErrorLogs {

    @Id
    private int errorLogId; // 对应数据表中的 errorlogid 列，作为主键

    private String studentId; // 对应数据表中的 studentid 列，关联出现错误的学生编号
    private String assignmentId; // 对应数据表中的 assignmentid 列，关联作业编号
    @Transient
    private String assignmentName; // 对应数据表中没有的列，记录作业标题
    private String errorQuestion; // 对应数据表中的 errorquestion 列，记录出现错误的题目内容描述
    private String errorAnswer; // 对应数据表中的 erroranswer 列，记录学生答错的答案内容
    private String correctAnswer; // 对应数据表中的 correctanswer 列，记录正确答案内容

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getErrorLogId() {
        return errorLogId;
    }

    public void setErrorLogId(int errorLogId) {
        this.errorLogId = errorLogId;
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

    public String getErrorQuestion() {
        return errorQuestion;
    }

    public void setErrorQuestion(String errorQuestion) {
        this.errorQuestion = errorQuestion;
    }

    public String getErrorAnswer() {
        return errorAnswer;
    }

    public void setErrorAnswer(String errorAnswer) {
        this.errorAnswer = errorAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "StudentErrorLogs{" +
                "errorLogId='" + errorLogId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", errorQuestion='" + errorQuestion + '\'' +
                ", errorAnswer='" + errorAnswer + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
