package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studentlearningprogress")
public class StudentLearningProgress {

    @Id
    private String ProgressId; // 对应数据表中的 progressid 列，作为主键

    private String StudentId; // 对应数据表中的 studentid 列，关联学生编号
    private int StudyDuration; // 对应数据表中的 studyduration 列，学习时长
    private String CompletedChapters; // 对应数据表中的 completedchapters 列，已完成章节内容

    // 默认构造函数
    public StudentLearningProgress() {}

    public StudentLearningProgress(String progressId, String studentId, int studyDuration, String completedChapters) {
        this.ProgressId = progressId;
        this.StudentId = studentId;
        this.StudyDuration = studyDuration;
        this.CompletedChapters = completedChapters;
    }

    public String getProgressId() {
        return ProgressId;
    }

    public void setProgressId(String progressId) {
        this.ProgressId = progressId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        this.StudentId = studentId;
    }

    public int getStudyDuration() {
        return StudyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        this.StudyDuration = studyDuration;
    }

    public String getCompletedChapters() {
        return CompletedChapters;
    }

    public void setCompletedChapters(String completedChapters) {
        this.CompletedChapters = completedChapters;
    }
}