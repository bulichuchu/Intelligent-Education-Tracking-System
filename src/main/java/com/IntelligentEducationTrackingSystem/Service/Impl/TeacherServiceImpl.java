package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.TeacherDAO;
import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import com.IntelligentEducationTrackingSystem.PO.TeacherComments;
import com.IntelligentEducationTrackingSystem.Service.TeacherService;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



}