package com.IntelligentEducationTrackingSystem.DAO;

import com.IntelligentEducationTrackingSystem.PO.SubmissionStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SubmissionStatusMapper {
    @Select("SELECT * FROM submissionstatus")
    List<SubmissionStatus> getAllSubmissionStatus();
}
