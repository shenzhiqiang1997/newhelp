package com.uestc.newhelp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private AuthorizationDao authorizationDao;

	@Override
	public void add(Teacher teacher) {
		teacherDao.add(teacher);
		authorizationDao.add(teacher.getTeacherId());
	}

	@Override
	public void delete(String teacherId) {
		teacherDao.delete(teacherId);
		authorizationDao.delete(teacherId);
	}

}
