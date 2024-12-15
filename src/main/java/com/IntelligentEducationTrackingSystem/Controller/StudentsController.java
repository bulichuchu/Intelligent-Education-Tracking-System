package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import com.IntelligentEducationTrackingSystem.pojo.StudentCourses;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @GetMapping("/studentMenu")//菜单
    public String showStudentMenu(@RequestParam("studentId") String studentId, Model model, HttpSession session) {
        session.setAttribute("studentId", studentId);
        model.addAttribute("studentId", studentId);
        model.addAttribute("studentName", studentsService.getById(studentId).getStudentName());
        return "studentMenu";
    }
    @GetMapping("/studentInformation")//显示个人信息
    public ModelAndView getById(HttpSession session) {
        String studentId = (String) session.getAttribute("studentId");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentInformation");
        mv.addObject("studentInformation", studentsService.getById(studentId));
        return mv;
    }
    @GetMapping("/updateStudent")//更新个人信息
    public String updateStudent(@ModelAttribute Students student) {
        studentsService.updateStudent(student);
        studentsService.updateStudentClassName(student.getStudentId(), student.getStudentClass().getClassName());
        return "redirect:/students/studentInformation";
    }
    @GetMapping("/assignmentDetails")//查询作业详情
    public String getAssignmentDetails(@RequestParam("studentId") String studentId,
                                       @RequestParam(value = "subjectName", required = false) String subjectName,
                                       Model model) {
        List<assignmentDetails> assignmentDetailsList = studentsService.getAssignmentDetails(studentId, subjectName);
        boolean anyGraded = assignmentDetailsList.stream().anyMatch(a -> a.getGrade() != null || (a.getComment() != null && !a.getComment().isEmpty()));
        model.addAttribute("assignmentDetails", assignmentDetailsList);
        model.addAttribute("studentId", studentId);
        model.addAttribute("anyGraded", anyGraded);
        return "assignmentDetails";
    }
    @GetMapping("/classNotifications")//查询班级通知
    public String getClassNotifications(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        model.addAttribute("classNotifications", studentsService.getClassNotifications(studentId));
        model.addAttribute("studentId", studentId);
        return "classNotifications";
    }
    @GetMapping("/studentCourses")//查询课程
    public String getStudentCourses(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        model.addAttribute("studentCourses", studentsService.getStudentCourses(studentId));
        model.addAttribute("studentId", studentId);
        return "studentCourses";
    }

}
