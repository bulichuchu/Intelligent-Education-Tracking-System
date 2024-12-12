package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subjects {

    @Id
    private String SubjectId; // 对应数据表中的 subjectid 列，作为主键

    private String SubjectName; // 对应数据表中的 subjectname 列，存储学科名称

    // 默认构造函数
    public Subjects() {}

    public Subjects(String subjectId, String subjectName) {
        this.SubjectId = subjectId;
        this.SubjectName = subjectName;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        this.SubjectId = subjectId;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        this.SubjectName = subjectName;
    }
}