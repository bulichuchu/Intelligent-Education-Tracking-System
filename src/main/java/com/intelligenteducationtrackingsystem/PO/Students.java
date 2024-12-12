package com.IntelligentEducationTrackingSystem.PO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Students {

    @Id
    private String StudentId; // 对应数据表中的 studentid 列，作为主键

    private String StudentName; // 对应数据表中的 studentname 列，存储学生姓名
    private String ClassId; // 对应数据表中的 classid 列，代表所在班级编号
    private String Sex; // 对应数据表中的 sex 列，标识学生性别
    private int Age; // 对应数据表中的 age 列，记录学生年龄
    private String Address; // 对应数据表中的 address 列，存放学生地址
    private String PhoneNumber; // 对应数据表中的 phonenumber 列，为学生电话号码

    // 默认构造函数
    public Students() {
    }

    public Students(String studentId, String studentName, String classId, String sex, int age, String address, String phoneNumber) {
        this.StudentId = studentId;
        this.StudentName = studentName;
        this.ClassId = classId;
        this.Sex = sex;
        this.Age = age;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        this.StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        this.StudentName = studentName;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        this.ClassId = classId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentid='" + StudentId + '\'' +
                ", studentname='" + StudentName + '\'' +
                ", classid='" + ClassId + '\'' +
                ", sex='" + Sex + '\'' +
                ", age=" + Age +
                ", address='" + Address + '\'' +
                ", phonenumber='" + PhoneNumber + '\'' +
                '}';
    }
}