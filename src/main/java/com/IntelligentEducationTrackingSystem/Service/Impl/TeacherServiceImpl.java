package com.IntelligentEducationTrackingSystem.Service.Impl;

import com.IntelligentEducationTrackingSystem.DAO.TeacherDAO;
import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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


}