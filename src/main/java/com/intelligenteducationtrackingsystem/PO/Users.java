package com.intelligenteducationtrackingsystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity               // 将此类标识为实体类
@Table(name = "users") // 映射到数据库中的 user 表
public class Users {
    @Id // 表示此字段为主键
    private  String UserId;//数据表中的 userid 列
    private String UserName; // 用户名，数据表中的 username 列
    private String UserPassword;//用户密码，数据表中userpassword列
    private String UserType;//用户类型
    // 默认构造函数
    public Users() {}
    public Users(String userId, String userName, String userPassword, String userType) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserPassword = userPassword;
        this.UserType = userType;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}


