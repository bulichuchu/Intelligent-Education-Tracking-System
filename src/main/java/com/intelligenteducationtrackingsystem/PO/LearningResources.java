package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "learningresources")
public class LearningResources {

    @Id
    private String resourceId; // 对应数据表中的 resourceid 列，作为主键

    private String resourceName; // 对应数据表中的 resourcename 列，存储资源名称
    private String resourceType; // 对应数据表中的 resourcetype 列，记录资源类型，如视频、文档、音频等
    private String subjectId; // 对应数据表中的 subjectid 列，关联所属学科编号
    private String teacherId; // 对应数据表中的 teacherid 列，关联上传资源的教师编号
    private String uploadTime; // 对应数据表中的 uploadtime 列，记录资源上传时间
    private String url;//对应数据表中url列，即记录资源链接

    // 默认构造函数
    public LearningResources() {}

    public LearningResources(String resourceId, String resourceName, String resourceType, String subjectId, String teacherId, String uploadTime,String url) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.uploadTime = uploadTime;
        this.url= url;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}