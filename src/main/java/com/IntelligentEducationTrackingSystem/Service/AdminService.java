package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Users;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    public List<Users> showAllUsers();
}
