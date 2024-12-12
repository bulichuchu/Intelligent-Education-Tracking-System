package com.IntelligentEducationTrackingSystem.PO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Students {

    @Id
    private String studentId; // 对应数据表中的 studentid 列，作为主键

    private String studentName; // 对应数据表中的 studentname 列，存储学生姓名
    private String classId; // 对应数据表中的 classid 列，代表所在班级编号
    private String sex; // 对应数据表中的 sex 列，标识学生性别
    private int age; // 对应数据表中的 age 列，记录学生年龄
    private String address; // 对应数据表中的 address 列，存放学生地址
    private String phoneNumber; // 对应数据表中的 phonenumber 列，为学生电话号码

    // 默认构造函数
    public Students() {
    }

    public Students(String studentId, String studentName, String classId, String sex, int age, String address, String phoneNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.classId = classId;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentid='" + studentId + '\'' +
                ", studentname='" + studentName + '\'' +
                ", classid='" + classId + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phonenumber='" + phoneNumber + '\'' +
                '}';
    }
}