package com.IntelligentEducationTrackingSystem.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPassword") String userPassword,
                        @RequestParam("studentId") String studentId,
                        HttpSession session,
                        Model model) {
        if ("U001".equals(userId) && "12345".equals(userPassword)) {
            session.setAttribute("studentId", studentId);
            return "redirect:/students/studentMenu?studentId=" + studentId;
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }
}