package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Users;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<Users> showAllUsers();
    Users check(String userId);
    void updateUsers(String userType, String userId);
    void addUsers(String userId, String userName, String userPassword, String userType);
    void deleteUsers(String userId);
}
