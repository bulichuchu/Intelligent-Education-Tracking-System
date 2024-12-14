package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.DAO.LoginDAO;
import com.IntelligentEducationTrackingSystem.PO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginDAO userMapper;

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 处理登录逻辑并根据用户名首字母重定向
    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPassword") String userPassword,
                        HttpSession session,
                        Model model) {
        Users user = userMapper.validateUser(userId, userPassword);

        if (user != null) {
            // 根据用户名首字母导航到不同页面
            char firstLetter = userId.toUpperCase().charAt(0); // 获取用户名首字母并转换为大写
            // 如果首字母不匹配，返回登录页面并显示错误信息
            return switch (firstLetter) {// 如果用户名以 'A' 开头，跳转到管理员界面
                case 'A' -> "redirect:/admin/dashboard";
                case 'T' -> {
                    session.setAttribute("teacherID", userId);
                    yield "redirect:/teachers/teacherMenu?teacherID=" + userId;
                }
                case 'S' -> {
                    session.setAttribute("studentId", userId);
                    yield "redirect:/students/studentMenu?studentId=" + userId;
                }
                default -> {
                    model.addAttribute("error", "用户名无效：无法确定用户类型");
                    yield "login";
                }
            };
        }else {
            // 登录失败
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }
}
