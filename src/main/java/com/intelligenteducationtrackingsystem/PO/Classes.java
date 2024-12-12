package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Classes {

    @Id
    private String ClassId; // 对应数据表中的 classid 列，作为主键

    private String ClassName; // 对应数据表中的 classname 列，存储班级名称
    private String Grade; // 对应数据表中的 grade 列，记录班级所在年级
    private String TeacherId; // 对应数据表中的 teacherid 列，关联授课教师编号

    // 默认构造函数
    public Classes() {}

    public Classes(String classId, String className, String grade, String teacherId) {
        this.ClassId = classId;
        this.ClassName = className;
        this.Grade = grade;
        this.TeacherId = teacherId;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        this.ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        this.ClassName = className;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        this.Grade = grade;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        this.TeacherId = teacherId;
    }
}