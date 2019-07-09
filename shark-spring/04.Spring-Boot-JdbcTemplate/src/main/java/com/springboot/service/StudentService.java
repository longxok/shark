package com.springboot.service;

import com.springboot.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
	int add(Student student);
    int update(Student student);
    int deleteBysno(String sno);
    List<Map<String, Object>> queryStudentListMap();
    Student queryStudentBySno(String sno);
}
