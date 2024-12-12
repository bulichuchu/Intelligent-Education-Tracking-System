package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @RequestMapping("/studentInformation")
    public ModelAndView getById() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentInformation");
        mv.addObject("studentInformation", studentsService.getById());
        return mv;
    }
}
