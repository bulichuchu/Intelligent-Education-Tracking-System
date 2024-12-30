package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.StudentErrorLogs;
import com.IntelligentEducationTrackingSystem.PO.Students;
import com.IntelligentEducationTrackingSystem.Service.StudentsService;
import com.IntelligentEducationTrackingSystem.pojo.StudentCourses;
import com.IntelligentEducationTrackingSystem.pojo.assignmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    public void setStudentName(String studentId,Model model){
        Students student = studentsService.getById(studentId);
        String studentName = student.getStudentName();
        model.addAttribute("studentName", studentName);
    }
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
    public String getAssignmentDetails(HttpSession session,
                                       @RequestParam(value = "subjectName", required = false) String subjectName,
                                       Model model) {
        String studentId = (String) session.getAttribute("studentId");
        List<assignmentDetails> assignmentDetailsList = studentsService.getAssignmentDetails(studentId, subjectName);
        setStudentName(studentId, model);
        model.addAttribute("assignmentDetails", assignmentDetailsList);
        model.addAttribute("studentId", studentId);
        return "assignmentDetails";
    }
    @PostMapping("/uploadAssignment")
    public String uploadAssignment(@RequestParam("file") MultipartFile file, @RequestParam("studentId") String studentId, @RequestParam("assignmentId") String assignmentId, Model model) {
        if (!file.isEmpty()) {
            try {
                // 确保上传目录存在
                File uploadDir = new File("src/main/resources/uploads");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // 处理文件名冲突
                String originalFilename = file.getOriginalFilename();
                Path destinationPath = new File(uploadDir, originalFilename).toPath();
                int counter = 1;
                while (Files.exists(destinationPath)) {
                    String newFilename = originalFilename.replace(".", "_" + counter + ".");
                    destinationPath = new File(uploadDir, newFilename).toPath();
                    counter++;
                }

                // 复制文件到本地
                Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                String pathStr = destinationPath.toString();
                String newPath = pathStr.replace("\\","/");//修改为可用的路径
                // 更新数据库中的path和status字段
                studentsService.updateAssignment(studentId, assignmentId, newPath);

                // 获取更新后的作业详情
                List<assignmentDetails> assignmentDetails = studentsService.getAssignmentDetails(studentId, null);
                model.addAttribute("assignmentDetails", assignmentDetails);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "assignmentDetails";
    }
    @GetMapping("/classNotifications")//查询班级通知
    public String getClassNotifications(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        setStudentName(studentId,model);
        model.addAttribute("classNotifications", studentsService.getClassNotifications(studentId));
        model.addAttribute("studentId", studentId);
        return "classNotifications";
    }
    @GetMapping("/studentCourses")//查询课程
    public String getStudentCourses(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        setStudentName(studentId,model);
        model.addAttribute("studentCourses", studentsService.getStudentCourses(studentId));
        model.addAttribute("studentId", studentId);
        return "studentCourses";
    }
    @GetMapping("/studentLearning")//查询学习资料
    public String getStudentLearning(@RequestParam(value = "subjectName", required = false,defaultValue = "") String subjectName,
                                     @RequestParam(value = "resourceType", required = false,defaultValue = "") String resourceType,
                                     Model model,HttpSession session) {
        String studentId = (String) session.getAttribute("studentId");
        setStudentName(studentId,model);
        model.addAttribute("studentLearning", studentsService.getStudentLearning(subjectName,resourceType));
        return "studentLearning";
    }
    @GetMapping("/studentErrorLogs")//查询错题集
    public String getStudentErrorLogs(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        List<StudentErrorLogs> studentErrorLogs = studentsService.getStudentErrorLogs(studentId);

        // 为每个错题集设置作业名称
        for (StudentErrorLogs errorLog : studentErrorLogs) {
            String assignmentName = studentsService.findAssignmentNameByAssignmentId(errorLog.getAssignmentId());
            errorLog.setAssignmentName(assignmentName);
        }
        setStudentName(studentId,model);
        model.addAttribute("studentErrorLogs", studentErrorLogs);
        model.addAttribute("studentId", studentId);
        return "studentErrorLogs";
    }
    @PostMapping("/updateErrorLog")
    public ResponseEntity<String> updateErrorLog(@RequestBody StudentErrorLogs errorLog) {
        studentsService.updateErrorLog(errorLog);
        return ResponseEntity.ok("更新成功");
    }
    @PostMapping("/addErrorLog")
    public ResponseEntity<String> addErrorLog(@RequestBody StudentErrorLogs errorLog) {
        studentsService.addErrorLog(errorLog);
        return ResponseEntity.ok("添加成功");
    }
    @GetMapping("/addErrorLogPage")
    public String showAddErrorLogPage(@RequestParam("studentId") String studentId, Model model) {
        List<assignmentDetails> assignments = studentsService.getAssignmentsByStudentId(studentId);
        setStudentName(studentId,model);
        model.addAttribute("assignments", assignments);
        model.addAttribute("studentId", studentId);
        return "addErrorLog";
    }
    @PostMapping("/deleteErrorLog")
    public ResponseEntity<String> deleteErrorLog(@RequestBody Map<String, Integer> payload) {
        int errorLogId = payload.get("errorLogId");
        studentsService.deleteErrorLog(errorLogId);
        return ResponseEntity.ok("删除成功");
    }
    @GetMapping("/getAssignments")//查询学生作业id
    public ResponseEntity<List<assignmentDetails>> getAssignments(@RequestParam("studentId") String studentId) {
        List<assignmentDetails> assignments = studentsService.getAssignmentsByStudentId(studentId);
        return ResponseEntity.ok(assignments);
    }
}
