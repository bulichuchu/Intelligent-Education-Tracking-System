package com.IntelligentEducationTrackingSystem.pojo;

public class StudentProgressDetail {
    private String progressId;
    private String studentId;
    private String studentName;

    private int studyDuration;
    private String completedChapters;

    // 默认构造函数
    public StudentProgressDetail() {}

    // 带参数的构造函数
    public StudentProgressDetail(String progressId, String studentId, String studentName,
                                 int studyDuration, String completedChapters) {
        this.progressId = progressId;
        this.studentId = studentId;
        this.studentName = studentName;

        this.studyDuration = studyDuration;
        this.completedChapters = completedChapters;
    }

    // Getters and Setters
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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