package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Classes {

    @Id
    private String classId; // 对应数据表中的 classid 列，作为主键

    private String className; // 对应数据表中的 classname 列，存储班级名称
    private String grade; // 对应数据表中的 grade 列，记录班级所在年级
    private String teacherId; // 对应数据表中的 teacherid 列，关联授课教师编号

    // 默认构造函数
    public Classes() {}

    public Classes(String classId, String className, String grade, String teacherId) {
        this.classId = classId;
        this.className = className;
        this.grade = grade;
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}