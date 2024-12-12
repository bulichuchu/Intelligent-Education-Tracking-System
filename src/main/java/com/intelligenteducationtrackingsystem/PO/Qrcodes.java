package com.intelligenteducationtrackingsystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "qrcodes")
public class Qrcodes{

    @Id
    private String QrcodeId; // 对应数据表中的 qrcodeid 列，作为主键

    private String StudentId; // 对应数据表中的 studentid 列，关联学生编号
    private Date GenerationTime; // 对应数据表中的 generationtime 列，记录二维码生成时间

    // 默认构造函数
    public Qrcodes() {}

    public Qrcodes(String qrcodeId, String studentId, Date generationTime) {
        this.QrcodeId = qrcodeId;
        this.StudentId = studentId;
        this.GenerationTime = generationTime;
    }

    public String getQrcodeId() {
        return QrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.QrcodeId = qrcodeId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        this.StudentId = studentId;
    }

    public Date getGenerationTime() {
        return GenerationTime;
    }

    public void setGenerationTime(Date generationTime) {
        this.GenerationTime = generationTime;
    }
}