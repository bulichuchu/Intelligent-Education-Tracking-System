package com.intelligenteducationtrackingsystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "classnotifications")
public class ClassNotifications {

    @Id
    private String NotificationId; // 对应数据表中的 notificationid 列，作为主键

    private String ClassId; // 对应数据表中的 classid 列，关联班级编号
    private String NotificationTitle; // 对应数据表中的 notificationtitle 列，存储通知标题
    private String NotificationContent; // 对应数据表中的 notificationcontent 列，存放通知具体内容
    private String ReleaseTime; // 对应数据表中的 releasetime 列，记录通知发布时间

    // 默认构造函数
    public ClassNotifications() {}

    public ClassNotifications(String notificationId, String classId, String notificationTitle, String notificationContent, String releaseTime) {
        this.NotificationId = notificationId;
        this.ClassId = classId;
        this.NotificationTitle = notificationTitle;
        this.NotificationContent = notificationContent;
        this.ReleaseTime = releaseTime;
    }

    public String getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(String notificationId) {
        this.NotificationId = notificationId;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        this.ClassId = classId;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.NotificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return NotificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.NotificationContent = notificationContent;
    }

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.ReleaseTime = releaseTime;
    }
}