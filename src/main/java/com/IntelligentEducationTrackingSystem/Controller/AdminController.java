package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.PO.Users;
import com.IntelligentEducationTrackingSystem.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/dashboard")//管理员菜单
    public String showDashboard(Model model) {
        List<Users> usersList = adminService.showAllUsers();
        model.addAttribute("usersList",usersList);
        return "adminMenu";
    }
    @GetMapping("/updateUserType")//更新用户信息
    public String updateUser(@RequestParam("userType") String userType, @RequestParam("userId") String userId) {
        adminService.updateUsers(userType, userId);
        return "redirect:/admin/dashboard";
    }
    @GetMapping("/addUsers")
    public String addUsers(@RequestParam("userId") String userId, @RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, @RequestParam("userType") String userType, Model model) {
        Users users = adminService.check(userId);
        if(users == null) {
            adminService.addUsers(userId,userName,userPassword,userType);
            return "redirect:/admin/dashboard";
        }
        else {
            model.addAttribute("userExists", true);
            return "redirect:/admin/dashboard";
        }
    }
    @GetMapping("/deleteUsers")
    public String deleteUsers(@RequestParam("userId") String userId) {
        adminService.deleteUsers(userId);
        return "redirect:/admin/dashboard";
    }
}
