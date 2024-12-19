package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.UserDAO;
import com.IntelligentEducationTrackingSystem.PO.Users;
import com.IntelligentEducationTrackingSystem.Service.AdminService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserDAO userDAO;
    public List<Users> showAllUsers() {
        return userDAO.showAllUsers();
    }
    public Users check(String userId) {
        return userDAO.check(userId);
    }
    public void updateUsers(String userType, String userId) {
        userDAO.updateUsers(userType, userId);
    }
    public void addUsers(String userId, String userName, String userPassword, String userType) {
        userDAO.addUsers(userId,userName,userPassword,userType);
    }
    public void deleteUsers(String userId) {
        userDAO.deleteUsers(userId);
    }
}
