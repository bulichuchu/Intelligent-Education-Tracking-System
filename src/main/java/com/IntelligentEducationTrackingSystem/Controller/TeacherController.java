package com.IntelligentEducationTrackingSystem.Controller;

import com.IntelligentEducationTrackingSystem.DAO.TeacherDAO;
import com.IntelligentEducationTrackingSystem.PO.*;
import com.IntelligentEducationTrackingSystem.Service.TeacherService;
import com.IntelligentEducationTrackingSystem.pojo.StudentProgressDetail;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private TeacherService teacherService;

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

            teacherService.addAssignment(
                    assignmentId,
                    assignmentName,
                    subjectId,
                    teacherId,
                    releaseDate,
                    dueDate
            );

            return "redirect:/teachers/createAssignment?teacherId=" + teacherId;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "作业添加失败：" + e.getMessage());
            return "error";
        }
    }



    @GetMapping("/assignments")
    public String checkSubmissions(@RequestParam("teacherId") String teacherId,
                                   @RequestParam(value = "assignmentId", required = false) String assignmentId,
                                   @RequestParam(value = "className", required = false) String className,
                                   Model model) {

        List<SubmissionDetails> submissions = teacherService.getSubmissionsByTeacher(teacherId, assignmentId, className);
        List<String> teacherClasses = teacherService.getTeacherClasses(teacherId);
        List<Assignments> teacherAssignments = teacherService.getTeacherAssignments(teacherId);

        model.addAttribute("submissions", submissions);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("classes", teacherClasses);
        model.addAttribute("assignments", teacherAssignments);
        int totalSubmissions = submissions.size();
        int submittedCount = (int) submissions.stream().filter(s -> "已提交".equals(s.getStatus().strip())).count();
        int notSubmittedCount = totalSubmissions - submittedCount;
        double submissionRate = totalSubmissions == 0 ? 0 : (submittedCount * 1.0 / totalSubmissions);

        model.addAttribute("submittedCount", submittedCount);
        model.addAttribute("notSubmittedCount", notSubmittedCount);
        model.addAttribute("submissionRate", submissionRate);

        return "assignments";
    }

    @GetMapping("/gradeAssignment")
    public String gradeAssignmentForm(@RequestParam("teacherId") String teacherId,
                                      @RequestParam(value = "assignmentId", required = false) String assignmentId,
                                      Model model) {
        // 获取该教师的所有作业
        List<Assignments> teacherAssignments = teacherService.getTeacherAssignments(teacherId);
        model.addAttribute("assignments", teacherAssignments);

        // 如果指定了作业ID，获取该作业的所有提交情况
        if (assignmentId != null) {
            List<SubmissionDetails> submissions = teacherService.getSubmissionsByAssignment(assignmentId);
            model.addAttribute("submissions", submissions);
        }

        model.addAttribute("teacherId", teacherId);
        return "gradeAssignment";
    }

    @PostMapping("/submitGrade")
    public String submitGrade(@RequestParam("teacherId") String teacherId,
                              @RequestParam("studentId") String studentId,
                              @RequestParam("assignmentId") String assignmentId,
                              @RequestParam("grade") int grade,
                              @RequestParam("comment") String comment,
                              RedirectAttributes redirectAttributes) {
        try {
            teacherService.gradeAssignment(studentId, assignmentId, grade, comment,teacherId);
            redirectAttributes.addFlashAttribute("successMessage", "评分提交���功！");
            return "redirect:/teachers/gradeAssignment?teacherId=" + teacherId + "&assignmentId=" + assignmentId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "评分提交失败：" + e.getMessage());
            return "redirect:/teachers/gradeAssignment?teacherId=" + teacherId + "&assignmentId=" + assignmentId;
        }
    }

    @PostMapping("/scanCode")
    public String handleScanCode(@RequestParam("qrCode") String qrCode,
                                 @RequestParam("teacherId") String teacherId,
                                 @RequestParam("assignmentId") String assignmentId,
                                 RedirectAttributes redirectAttributes) {
        try {

            // 更新作业提交状态
            teacherService.updateSubmissionStatus(qrCode, assignmentId, "已提交");

            redirectAttributes.addFlashAttribute("successMessage", "扫描成功并更新状态！");
            return "redirect:/teachers/gradeAssignment?teacherId=" + teacherId + "&assignmentId=" + assignmentId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "扫描失败：" + e.getMessage());
            return "redirect:/teachers/gradeAssignment?teacherId=" + teacherId;
        }
    }

    @GetMapping("/learningProgress")
    public String viewLearningProgress(@RequestParam("teacherId") String teacherId,
                                       @RequestParam(required = false) String subjectName,
                                       Model model) {
        List<StudentProgressDetail> progressList = teacherService.getLearningProgress(teacherId, subjectName);

        model.addAttribute("progressList", progressList);
        model.addAttribute("teacherId", teacherId);

        return "learningProgress";
    }

    // 在现有代码后添加以下方法
    @GetMapping("/resources")
    public String manageResources(
            @RequestParam("teacherId") String teacherId,
            @RequestParam(required = false) String resourceType,
            Model model
    ) {
        String subjectId = teacherDAO.getSubjectIDByTeacherID(teacherId);
        String subjectName = teacherDAO.getSubjectNameBySubjectID(subjectId);

        List<LearningResources> resources = teacherService.getResourcesByTeacher(teacherId, resourceType);

        model.addAttribute("resources", resources);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectName", subjectName);
        return "manageResources";
    }

    @PostMapping("/resources/add")
    public String addResource(
            @ModelAttribute LearningResources resource,
            RedirectAttributes redirectAttributes
    ) {
        try {
            teacherService.addLearningResource(resource);
            redirectAttributes.addFlashAttribute("message", "资源添加成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teachers/resources?teacherId=" + resource.getTeacherId();
    }

    @PostMapping("/resources/delete")
    public String deleteResource(
            @RequestParam String resourceId,
            @RequestParam String teacherId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            teacherService.deleteLearningResource(resourceId, teacherId);
            redirectAttributes.addFlashAttribute("message", "资源删除成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teachers/resources?teacherId=" + teacherId;
    }

    @PostMapping("/resources/update")
    public String updateResource(
            @ModelAttribute LearningResources resource,
            @RequestParam String teacherId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            teacherService.updateLearningResource(resource, teacherId);
            redirectAttributes.addFlashAttribute("message", "资源更新成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teachers/resources?teacherId=" + teacherId;
    }

    @GetMapping("/classSchedule")
    public String viewClassSchedule(Model model) {
        // 查询课程安排
//        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "classSchedule"; // 查看课程安排页面
    }

    @GetMapping("/notifications")
    public String showNotifications(
            @RequestParam("teacherId") String teacherId,
            Model model
    ) {
        List<Map<String, Object>> notifications = teacherService.getNotificationsByTeacher(teacherId);
        List<Map<String, String>> classList = teacherService.getTeacherClassList(teacherId);

        model.addAttribute("notifications", notifications);
        model.addAttribute("classList", classList);
        model.addAttribute("teacherId", teacherId);
        return "notifications";
    }

    @PostMapping("/notifications/add")
    public String addNotification(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("teacherId") String teacherId,
            @RequestParam("classId") String classId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            teacherService.addClassNotification(title, content, teacherId, classId);
            redirectAttributes.addFlashAttribute("message", "通知发布成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teachers/notifications?teacherId=" + teacherId;
    }

    @PostMapping("/notifications/delete")
    public String deleteNotification(
            @RequestParam("notificationId") String notificationId,
            @RequestParam("teacherId") String teacherId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            teacherService.deleteClassNotification(notificationId, teacherId);
            redirectAttributes.addFlashAttribute("message", "通知删除成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teachers/notifications?teacherId=" + teacherId;
    }
}
