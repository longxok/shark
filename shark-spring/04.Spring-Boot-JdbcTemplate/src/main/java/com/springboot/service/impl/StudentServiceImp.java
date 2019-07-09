package com.springboot.service.impl;

import com.springboot.bean.Student;
import com.springboot.dao.StudentDao;
import com.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public int add(Student student) {
		return this.studentDao.add(student);
	}

	@Override
	public int update(Student student) {
		return this.studentDao.update(student);
	}

	@Override
	public int deleteBysno(String sno) {
		return this.studentDao.deleteBysno(sno);
	}

	@Override
	public List<Map<String, Object>> queryStudentListMap() {
		return this.studentDao.queryStudentsListMap();
	}

	@Override
	public Student queryStudentBySno(String sno) {
		return this.studentDao.queryStudentBySno(sno);
	}

}
