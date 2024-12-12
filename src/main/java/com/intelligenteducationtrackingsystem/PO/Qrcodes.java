package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "qrcodes")
public class Qrcodes{

    @Id
    private String qrcodeId; // 对应数据表中的 qrcodeid 列，作为主键

    private String studentId; // 对应数据表中的 studentid 列，关联学生编号
    private Date generationTime; // 对应数据表中的 generationtime 列，记录二维码生成时间

    // 默认构造函数
    public Qrcodes() {}

    public Qrcodes(String qrcodeId, String studentId, Date generationTime) {
        this.qrcodeId = qrcodeId;
        this.studentId = studentId;
        this.generationTime = generationTime;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Date generationTime) {
        this.generationTime = generationTime;
    }
}