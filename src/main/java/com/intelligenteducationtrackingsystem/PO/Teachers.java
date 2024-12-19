package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity               // 将此类标识为实体类
@Table(name = "teachers") // 映射到数据库中的 teachers 表
public class Teachers {
    @Id
    private String teacherId; // 数据表中的 teacherid 列
    private String teacherName; // 教师姓名，对应数据表中的 teachername 列
    private String subjectId; // 教授学科的编号，对应数据表中的 subjectid 列
    private String email; // 教师邮箱，对应数据表中的 email 列
    private String tphoneNumber; // 教师电话号码，对应数据表中的 tphonenumber 列

    // 默认构造函数
    public Teachers() {}

    public Teachers(String teacherId, String teacherName, String subjectId, String email, String tphonenumber) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subjectId = subjectId;
        this.email = email;
        this.tphoneNumber = tphonenumber;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTphoneNumber() {
        return tphoneNumber;
    }

    public void setTphoneNumber(String tphonenumber) {
        this.tphoneNumber = tphonenumber;
    }
}