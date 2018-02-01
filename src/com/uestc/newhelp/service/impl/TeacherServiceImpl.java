package com.uestc.newhelp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.StillHasArchiveStudentException;
import com.uestc.newhelp.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private AuthorizationDao authorizationDao;
	@Autowired
	private ArchiveStudentDao archiveStudentDao;
	@Override
	public void add(Teacher teacher) {
		teacherDao.add(teacher);
		authorizationDao.add(teacher.getTeacherId());
	}

	@Override
	public void delete(String teacherId) throws StillHasArchiveStudentException{
		//查看该教师是否还有帮扶的学生 如果还有则抛出异常并提醒管理者
		int count=archiveStudentDao.count(teacherId);
		System.out.println(count);
		if(count!=0)
			throw new StillHasArchiveStudentException(Message.STILL_HAS_ARCHIVE_STUDENT);
		teacherDao.delete(teacherId);
		authorizationDao.delete(teacherId);
	}

}
