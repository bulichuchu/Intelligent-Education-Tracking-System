package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @GetMapping("/studentMenu")//菜单
    public String showStudentMenu(@RequestParam("studentId") String studentId, Model model) {
        model.addAttribute("studentId", studentId);
        model.addAttribute("studentName", studentsService.getById(studentId).getStudentName());
        return "studentMenu";
    }
    @GetMapping("/studentInformation")//显示个人信息
    public ModelAndView getById(@RequestParam("studentId") String studentId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentInformation");
        mv.addObject("studentInformation", studentsService.getById(studentId));
        return mv;
    }
    @PostMapping("/updateStudent")//更新个人信息
    public String updateStudent(@ModelAttribute Students student) {
        studentsService.updateStudent(student);
        studentsService.updateStudentClassName(student.getStudentId(), student.getStudentClass().getClassName());
        return "redirect:/students/studentInformation?studentId=" + student.getStudentId();
    }
    @GetMapping("/assignmentDetails")//查询作业详情
    public String getAssignmentDetails(@RequestParam("studentId") String studentId,
                                       @RequestParam(value = "subjectName", required = false) String subjectName,
                                       Model model) {
        List<assignmentDetails> assignmentDetailsList = studentsService.getAssignmentDetails(studentId, subjectName);
        boolean anyGraded = assignmentDetailsList.stream().anyMatch(a -> (Integer)a.getGrade() != null || a.getComment() != null);
        model.addAttribute("assignmentDetails", assignmentDetailsList);
        model.addAttribute("studentId", studentId);
        model.addAttribute("anyGraded", anyGraded);
        return "assignmentDetails";
    }
}
