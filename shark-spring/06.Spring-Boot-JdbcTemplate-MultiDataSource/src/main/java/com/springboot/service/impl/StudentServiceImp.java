package com.springboot.service.impl;

import com.springboot.dao.MysqlStudentDao;
import com.springboot.dao.OracleStudentDao;
import com.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImp implements StudentService{
	@Autowired
	private OracleStudentDao oracleStudentDao;
	@Autowired
	private MysqlStudentDao mysqlStudentDao;
	
	@Override
	public List<Map<String, Object>> getAllStudentsFromOralce() {
		return this.oracleStudentDao.getAllStudents();
	}

	@Override
	public List<Map<String, Object>> getAllStudentsFromMysql() {
		return this.mysqlStudentDao.getAllStudents();
	}

}
