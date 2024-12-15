package com.IntelligentEducationTrackingSystem.pojo;

import java.util.Date;

public class StudentLearning {
    private String resourceName;
    private String resourceType;
    private String subjectName;
    private String teacherName;
    private Date uploadTime;
    private String url;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "StudentLearning{" +
                "resourceName='" + resourceName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", uploadTime=" + uploadTime +
                ", url='" + url + '\'' +
                '}';
    }
}
