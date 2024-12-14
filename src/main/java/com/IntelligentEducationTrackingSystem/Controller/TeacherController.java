package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.DAO.TeacherDAO;
import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.Subjects;
import com.IntelligentEducationTrackingSystem.PO.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.auth.Subject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherDAO teacherDAO;

    @GetMapping("/teacherMenu")//菜单
    public String showStudentMenu(HttpSession session, Model model) {
        model.addAttribute("teacherId", ((Users) session.getAttribute("teacher")).getUserId());
        model.addAttribute("teacherName", ((Users) session.getAttribute("teacher")).getUserName());
        return "teacherMenu";
    }

    @GetMapping("/createAssignment")
    public String createAssignmentForm(@RequestParam(value = "teacherId", required = false) String teacherId,
                                       HttpSession session,
                                       Model model) {
        // 获取教师用户信息
        Users teacher = (Users) session.getAttribute("teacher");

        // 如果 session 中没有教师信息，并且 URL 参数 teacherId 也为空，则重定向到登录页面
        if (teacher == null && teacherId == null) {
            return "redirect:/login";
        }

        // 如果 session 中没有教师，但有 URL 参数 teacherId，则手动创建用户对象
        if (teacher == null) {
            teacher = new Users();
            teacher.setUserId(teacherId);
            session.setAttribute("teacher", teacher); // 将 teacher 存入 session
        }

        // 获取当前教师 ID
        String currentTeacherId = teacher.getUserId();

        // 查询作业信息
        List<Assignments> assignments;
        try {
            assignments = teacherDAO.getAssignmentByTeacherId(currentTeacherId);
        } catch (Exception e) {
            model.addAttribute("error", "获取作业列表失败，请稍后重试。");
            return "error"; // 返回错误页面
        }

        // 查询科目信息
        List<Subjects> subjects;
//        List<Map<String, Object>> result = teacherDAO.getRawAssignmentData("T002");
        try {
            String subjectId = teacherDAO.getSubjectIDByTeacherID(currentTeacherId);
            String subjectName = teacherDAO.getSubjectNameBySubjectID(subjectId);
            subjects = List.of(new Subjects(subjectId, subjectName));
        } catch (Exception e) {
            model.addAttribute("error", "获取科目信息失败，请稍后重试。");
            return "error"; // 返回错误页面
        }

        // 将数据添加到 Model
        model.addAttribute("assignments", assignments);
        model.addAttribute("subjects", subjects);

        return "createAssignment"; // 返回前端页面
    }

    @PostMapping("/createAssignment")
    public String handleCreateAssignment(
            @RequestParam("assignmentId") String assignmentId,
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("subjectId") String subjectId,
            @RequestParam("teacherId") String teacherId,
            @RequestParam("releaseTime") String releaseTime,
            @RequestParam("dueTime") String dueTime,
            HttpSession session,
            Model model
    ) {
        try {
            // 转换日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date releaseDate = dateFormat.parse(releaseTime);
            Date dueDate = dateFormat.parse(dueTime);

            teacherDAO.insertAssignment(new Assignments(
                    assignmentId,
                    assignmentName,
                    subjectId,
                    teacherId,
                    releaseDate,
                    dueDate
            ));

            return "redirect:/teachers/createAssignment?teacherId=" + teacherId;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "作业添加失败：" + e.getMessage());
            return "error";
        }
    }



    @GetMapping("/assignments")
    public String viewAssignments(Model model) {
        // 查询作业列表并添加到模型中
//        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "viewAssignments"; // 查看提交情况页面
    }

    @GetMapping("/gradeAssignment")
    public String gradeAssignmentForm() {
        return "gradeAssignment"; // 批改作业页面
    }

    @GetMapping("/learningProgress")
    public String viewLearningProgress(Model model) {
        // 查询学习进度并添加到模型中
//        model.addAttribute("progressList", learningService.getAllLearningProgress());
        return "learningProgress"; // 查看学生学习进度页面
    }

    @GetMapping("/resources")
    public String manageResources(Model model) {
        // 查询学习资源列表
//        model.addAttribute("resources", learningResourceService.getAllResources());
        return "manageResources"; // 管理学习资源页面
    }

    @GetMapping("/classSchedule")
    public String viewClassSchedule(Model model) {
        // 查询课程安排
//        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "classSchedule"; // 查看课程安排页面
    }

    @GetMapping("/notifications")
    public String postNotificationForm() {
        return "postNotification"; // 发布通知页面
    }
}
