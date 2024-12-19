package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.TeacherDAO;
import com.IntelligentEducationTrackingSystem.PO.*;
import com.IntelligentEducationTrackingSystem.Service.TeacherService;
import com.IntelligentEducationTrackingSystem.pojo.StudentProgressDetail;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDAO teacherDAO;

    public void addAssignment(String assignmentId, String assignmentName, String subjectId, String teacherId, Date releaseTime, Date dueTime) {
        Assignments assignment = new Assignments();
        assignment.setAssignmentId(assignmentId);
        assignment.setAssignmentName(assignmentName);
        assignment.setSubjectId(subjectId);
        assignment.setTeacherId(teacherId);
        assignment.setReleaseTime(releaseTime);
        assignment.setDueTime(dueTime);

        teacherDAO.insertAssignment(assignment);
        List<String> studentIds = teacherDAO.getStudentsByTeacher(teacherId);

        for (String studentId : studentIds) {
            SubmissionStatus status = new SubmissionStatus();
            status.setSubmissionStatusId(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
            status.setAssignmentId(assignmentId);
            status.setStudentId(studentId);
            status.setStatus("未提交");
            teacherDAO.insertSubmissionStatus(status);}
    }

    @Override
    public List<SubmissionDetails> getSubmissionsByTeacher(String teacherId, String assignmentId, String className) {
        return teacherDAO.getSubmissionsByTeacher(teacherId, assignmentId, className);
    }

    @Override
    public List<String> getTeacherClasses(String teacherId) {
        return teacherDAO.getTeacherClasses(teacherId);
    }

    @Override
    public List<Assignments> getTeacherAssignments(String teacherId) {
        return teacherDAO.getTeacherAssignments(teacherId);
    }


    @Override
    public List<SubmissionDetails> getSubmissionsByAssignment(String assignmentId) {
        return teacherDAO.getSubmissionsByAssignmentId(assignmentId);
    }

    @Override
    @Transactional
    public void gradeAssignment(String studentId, String assignmentId, int grade, String comment,String teacherId) {
        // 生成评语ID和成绩ID
        String commentId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        String gradeId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

        // 创建教师评语记录
        TeacherComments teacherComment = new TeacherComments();
        teacherComment.setCommentId(commentId);
        teacherComment.setStudentId(studentId);
        teacherComment.setAssignmentId(assignmentId);
        teacherComment.setComment(comment);
        teacherComment.setCommentTime(new Date());

        // 保存评语
        teacherDAO.saveTeacherComment(teacherComment);

        // 插入成绩记录
        teacherDAO.insertAssignmentGrade(gradeId, assignmentId, studentId, grade, teacherId);
    }

    @Override
    public Map<String, String> parseScanCode(String qrCode) {
        Map<String, String> result = new HashMap<>();
        try {
            // 验证二维码内容是否符合学生ID格式
            if (qrCode != null && qrCode.startsWith("S")) {
                result.put("studentId", qrCode);
                // 可以根据需要从数据库查询该学生当前需要批改的作业
                String assignmentId = teacherDAO.getLatestAssignmentForStudent(qrCode);
                if (assignmentId != null) {
                    result.put("assignmentId", assignmentId);
                } else {
                    throw new IllegalArgumentException("未找到该学生需要批改的作业");
                }
            } else {
                throw new IllegalArgumentException("无效的学生ID格式");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("二维码解析失败: " + e.getMessage());
        }
        return result;
    }

    public void updateSubmissionStatus(String studentId, String assignmentId, String status){
        teacherDAO.updateSubmissionStatus(studentId, assignmentId, status);
    }
    @Override
    public List<StudentProgressDetail> getLearningProgress(String teacherId, String subjectName) {
        try {
            return teacherDAO.getLearningProgress(teacherId);
        } catch (Exception e) {
            // 记录错误日志
            e.printStackTrace();
            // 返回空列表
            return new ArrayList<>();
        }
    }

    @Override
    public void addLearningResource(LearningResources resource) {
        // 获取教师所教学科
        String subjectId = teacherDAO.getSubjectIDByTeacherID(resource.getTeacherId());
        if (subjectId == null) {
            throw new RuntimeException("未找到教师所教学科信息");
        }

        // 设置资源信息
        resource.setResourceId(UUID.randomUUID().toString().substring(0, 10));
        resource.setSubjectId(subjectId);
        resource.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));

        teacherDAO.insertResource(resource);
    }

    @Override
    public void deleteLearningResource(String resourceId, String teacherId) {
        // 验证该资源是否属于教师所教学科
        LearningResources resource = teacherDAO.getResourceById(resourceId);
        String teacherSubject = teacherDAO.getSubjectIDByTeacherID(teacherId);

        if (resource == null || !resource.getSubjectId().equals(teacherSubject)) {
            throw new RuntimeException("无权删除此资源");
        }

        teacherDAO.deleteResource(resourceId);
    }

    @Override
    public void updateLearningResource(LearningResources resource, String teacherId) {
        // 验证该资源是否属于教师所教学科
        String teacherSubject = teacherDAO.getSubjectIDByTeacherID(teacherId);
        LearningResources existingResource = teacherDAO.getResourceById(resource.getResourceId());

        if (existingResource == null || !existingResource.getSubjectId().equals(teacherSubject)) {
            throw new RuntimeException("无权修改此资源");
        }

        teacherDAO.updateResource(resource);
    }

    @Override
    public List<LearningResources> getResourcesByTeacher(String teacherId, String resourceType) {
        return teacherDAO.getResourcesByTeacher(teacherId, resourceType);
    }

    @Override
    public LearningResources getResourceById(String resourceId) {
        return teacherDAO.getResourceById(resourceId);
    }
    @Override
    public List<Map<String, Object>> getNotificationsByTeacher(String teacherId) {
        return teacherDAO.getNotificationsByTeacher(teacherId);
    }

    @Override
    public void addClassNotification(String title, String content, String teacherId, String classId) {
        // 验证该班级是否属于这个教师
        List<Map<String, String>> teacherClasses = teacherDAO.getTeacherClassList(teacherId);
        boolean isValidClass = teacherClasses.stream()
                .anyMatch(c -> c.get("classId").equals(classId));

        if (!isValidClass) {
            throw new RuntimeException("无权向该班级发布通知");
        }

        ClassNotifications notification = new ClassNotifications();
        notification.setNotificationId(UUID.randomUUID().toString().substring(0, 10));
        notification.setNotificationTitle(title);
        notification.setNotificationContent(content);
        notification.setClassId(classId);
        notification.setReleaseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        teacherDAO.insertClassNotification(notification);
    }

    @Override
    public void deleteClassNotification(String notificationId, String teacherId) {
        // 验证该通知是否属于教师所教班级
        List<Map<String, Object>> teacherNotifications = getNotificationsByTeacher(teacherId);
        boolean hasPermission = teacherNotifications.stream()
                .anyMatch(n -> n.get("notificationId").equals(notificationId));

        if (!hasPermission) {
            throw new RuntimeException("无权删除此通知");
        }
        teacherDAO.deleteClassNotification(notificationId);
    }

    @Override
    public List<Map<String, String>> getTeacherClassList(String teacherId) {
        return teacherDAO.getTeacherClassList(teacherId);
    }


}