package com.IntelligentEducationTrackingSystem.pojo;

public class StudentCourses {
    private String courseName;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhone;
    private String sudtentId;
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSudtentId() {
        return sudtentId;
    }

    public void setSudtentId(String sudtentId) {
        this.sudtentId = sudtentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", sudtentId='" + sudtentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
