package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "classnotifications")
public class ClassNotifications {

    @Id
    private String notificationId; // 对应数据表中的 notificationid 列，作为主键

    private String classId; // 对应数据表中的 classid 列，关联班级编号
    private String notificationTitle; // 对应数据表中的 notificationtitle 列，存储通知标题
    private String notificationContent; // 对应数据表中的 notificationcontent 列，存放通知具体内容
    private String releaseTime; // 对应数据表中的 releasetime 列，记录通知发布时间

    // 默认构造函数
    public ClassNotifications() {}

    public ClassNotifications(String notificationId, String classId, String notificationTitle, String notificationContent, String releaseTime) {
        this.notificationId = notificationId;
        this.classId = classId;
        this.notificationTitle = notificationTitle;
        this.notificationContent = notificationContent;
        this.releaseTime = releaseTime;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}