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
}
