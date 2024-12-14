package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.PO.Assignments;
import com.IntelligentEducationTrackingSystem.PO.Subjects;

import java.util.Date;

public interface TeacherService {
    public void addAssignment(String assignmentId, String assignmentName, String subjectId, String teacherId, Date releaseTime, Date dueTime);
}
