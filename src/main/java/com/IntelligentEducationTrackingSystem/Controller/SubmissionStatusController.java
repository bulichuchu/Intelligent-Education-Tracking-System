package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import com.IntelligentEducationTrackingSystem.Service.SubmissionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SubmissionStatusController {
    @Autowired
    private SubmissionStatusService submissionStatusService;

    // 显示所有提交状态
    @GetMapping("/submissionStatus")
    public String showSubmissionStatus(Model model) {
        List<SubmissionStatus> submissionStatuses = submissionStatusService.getAllSubmissionStatus();
        model.addAttribute("submissionStatuses", submissionStatuses);
        return "submissionStatus"; // 返回 Thymeleaf 模板页面
    }
}
