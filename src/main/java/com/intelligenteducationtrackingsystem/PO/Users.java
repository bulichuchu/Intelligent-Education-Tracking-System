package com.IntelligentEducationTrackingSystem.PO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity               // 将此类标识为实体类
@Table(name = "users") // 映射到数据库中的 user 表
public class Users {
    @Id // 表示此字段为主键
    private  String userId;//数据表中的 userid 列
    private String userName; // 用户名，数据表中的 username 列
    private String userPassword;//用户密码，数据表中userpassword列
    private String userType;//用户类型
    // 默认构造函数
    public Users() {}
    public Users(String userId, String userName, String userPassword, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}


