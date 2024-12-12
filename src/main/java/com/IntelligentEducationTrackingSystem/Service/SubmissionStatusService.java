package com.IntelligentEducationTrackingSystem.Service;

import com.IntelligentEducationTrackingSystem.DAO.SubmissionStatusMapper;
import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionStatusService {
    @Autowired
    private SubmissionStatusMapper submissionStatusMapper;

    // 获取所有的提交状态
    public List<SubmissionStatus> getAllSubmissionStatus() {
        return submissionStatusMapper.getAllSubmissionStatus();
    }
}
