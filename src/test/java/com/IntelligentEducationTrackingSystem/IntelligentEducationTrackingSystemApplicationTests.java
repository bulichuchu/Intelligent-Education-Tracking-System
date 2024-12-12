package com.IntelligentEducationTrackingSystem;

import com.IntelligentEducationTrackingSystem.dao.StudentsDao;
import com.IntelligentEducationTrackingSystem.PO.Students;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntelligentEducationTrackingSystemApplicationTests {
    @Autowired
    private StudentsDao studentsDao;
    @Test
    void contextLoads() {
        Students students = studentsDao.getById("S001");
        System.out.println(students);
    }

}
