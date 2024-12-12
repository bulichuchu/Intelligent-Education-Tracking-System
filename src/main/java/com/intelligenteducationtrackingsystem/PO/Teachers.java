package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity               // 将此类标识为实体类
@Table(name = "teachers") // 映射到数据库中的 teachers 表
public class Teachers {
    @Id
    private String TeacherId; // 数据表中的 teacherid 列
    private String TeacherName; // 教师姓名，对应数据表中的 teachername 列
    private String SubjectId; // 教授学科的编号，对应数据表中的 subjectid 列
    private String Email; // 教师邮箱，对应数据表中的 email 列
    private String TphoneNumber; // 教师电话号码，对应数据表中的 tphonenumber 列

    // 默认构造函数
    public Teachers() {}

    public Teachers(String teacherId, String teacherName, String subjectId, String email, String tphonenumber) {
        this.TeacherId = teacherId;
        this.TeacherName = teacherName;
        this.SubjectId = subjectId;
        this.Email = email;
        this.TphoneNumber = tphonenumber;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        this.TeacherId = teacherId;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        this.TeacherName = teacherName;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        this.SubjectId = subjectId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getTphonenumber() {
        return TphoneNumber;
    }

    public void setTphonenumber(String tphonenumber) {
        this.TphoneNumber = tphonenumber;
    }
}