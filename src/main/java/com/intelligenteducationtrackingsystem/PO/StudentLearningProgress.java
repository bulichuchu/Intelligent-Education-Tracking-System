package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studentlearningprogress")
public class StudentLearningProgress {

    @Id
    private String progressId; // 对应数据表中的 progressid 列，作为主键

    private String studentId; // 对应数据表中的 studentid 列，关联学生编号
    private int studyDuration; // 对应数据表中的 studyduration 列，学习时长
    private String completedChapters; // 对应数据表中的 completedchapters 列，已完成章节内容

    // 默认构造函数
    public StudentLearningProgress() {}

    public StudentLearningProgress(String progressId, String studentId, int studyDuration, String completedChapters) {
        this.progressId = progressId;
        this.studentId = studentId;
        this.studyDuration = studyDuration;
        this.completedChapters = completedChapters;
    }

    public String getProgressId() {
        return progressId;
    }

    public void setProgressId(String progressId) {
        this.progressId = progressId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        this.studyDuration = studyDuration;
    }

    public String getCompletedChapters() {
        return completedChapters;
    }

    public void setCompletedChapters(String completedChapters) {
        this.completedChapters = completedChapters;
    }
}