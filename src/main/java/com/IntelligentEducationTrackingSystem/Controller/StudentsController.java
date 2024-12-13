package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @GetMapping("/studentInformation")
    public ModelAndView getById(@RequestParam("studentId") String studentId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentInformation");
        mv.addObject("studentInformation", studentsService.getById(studentId));
        return mv;
    }
    @PostMapping("/updateStudent")//更新
    public String updateStudent(@ModelAttribute Students student) {
        studentsService.updateStudent(student);
        studentsService.updateStudentClassName(student.getStudentId(), student.getStudentClass().getClassName());
        return "redirect:/students/studentInformation?studentId=" + student.getStudentId();
    }

}
