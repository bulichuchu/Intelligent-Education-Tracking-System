package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "learningresources")
public class LearningResources {

    @Id
    private String ResourceId; // 对应数据表中的 resourceid 列，作为主键

    private String ResourceName; // 对应数据表中的 resourcename 列，存储资源名称
    private String ResourceType; // 对应数据表中的 resourcetype 列，记录资源类型，如视频、文档、音频等
    private String SubjectId; // 对应数据表中的 subjectid 列，关联所属学科编号
    private String TeacherId; // 对应数据表中的 teacherid 列，关联上传资源的教师编号
    private String UploadTime; // 对应数据表中的 uploadtime 列，记录资源上传时间
    private String Url;//对应数据表中url列，即记录资源链接

    // 默认构造函数
    public LearningResources() {}

    public LearningResources(String resourceId, String resourceName, String resourceType, String subjectId, String teacherId, String uploadTime,String url) {
        this.ResourceId = resourceId;
        this.ResourceName = resourceName;
        this.ResourceType = resourceType;
        this.SubjectId = subjectId;
        this.TeacherId = teacherId;
        this.UploadTime = uploadTime;
        this.Url= url;
    }

    public String getResourceId() {
        return ResourceId;
    }

    public void setResourceId(String resourceId) {
        this.ResourceId = resourceId;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public void setResourceName(String resourceName) {
        this.ResourceName = resourceName;
    }

    public String getResourceType() {
        return ResourceType;
    }

    public void setResourceType(String resourceType) {
        this.ResourceType = resourceType;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        this.SubjectId = subjectId;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        this.TeacherId = teacherId;
    }

    public String getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.UploadTime = uploadTime;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}