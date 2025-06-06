package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity               // 将此类标识为实体类
@Table(name = "teaching") // 映射到数据库中的 teaching 表
public class Teaching {
    @Id // 表示此字段为主键，这里假设teacherid和classid的组合作为主键，你可以根据实际数据库设计调整
    private String teacherId; // 数据表中的 teacherid 列
    @Id
    private String classId; // 数据表中的 classid 列

    // 默认构造函数
    public Teaching() {}

    public Teaching(String teacherId, String classId) {
        this.teacherId = teacherId;
        this.classId = classId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}