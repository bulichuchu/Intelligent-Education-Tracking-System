package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subjects {

    @Id
    private String subjectId; // 对应数据表中的 subjectid 列，作为主键

    private String subjectName; // 对应数据表中的 subjectname 列，存储学科名称

    // 默认构造函数
    public Subjects() {}

    public Subjects(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}