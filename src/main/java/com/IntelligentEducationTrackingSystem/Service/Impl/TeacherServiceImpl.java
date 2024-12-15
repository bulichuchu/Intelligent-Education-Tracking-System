package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.TeacherDAO;
import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import com.IntelligentEducationTrackingSystem.Service.TeacherService;
import com.IntelligentEducationTrackingSystem.pojo.SubmissionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDAO assignmentDAO;

    public void addAssignment(String assignmentId, String assignmentName, String subjectId, String teacherId, Date releaseTime, Date dueTime) {
        Assignments assignment = new Assignments();
        assignment.setAssignmentId(assignmentId);
        assignment.setAssignmentName(assignmentName);
        assignment.setSubjectId(subjectId);
        assignment.setTeacherId(teacherId);
        assignment.setReleaseTime(releaseTime);
        assignment.setDueTime(dueTime);

        assignmentDAO.insertAssignment(assignment);
    }

    @Override
    public List<SubmissionDetails> getSubmissionsByTeacher(String teacherId, String assignmentId, String className) {
        return assignmentDAO.getSubmissionsByTeacher(teacherId, assignmentId, className);
    }

    @Override
    public List<String> getTeacherClasses(String teacherId) {
        return assignmentDAO.getTeacherClasses(teacherId);
    }

    @Override
    public List<Assignments> getTeacherAssignments(String teacherId) {
        return assignmentDAO.getTeacherAssignments(teacherId);
    }


}