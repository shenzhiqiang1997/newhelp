package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.dao.TokenDao;
import com.uestc.newhelp.dto.TeacherUpdatePasswordParam;
import com.uestc.newhelp.entity.Authorization;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.entity.Token;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoSuchUserException;
import com.uestc.newhelp.exception.PasswordErrorException;
import com.uestc.newhelp.service.UserService;
import com.uestc.newhelp.util.TokenUtil;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private AuthorizationDao authorizationDao;
	
	@Override
	public Teacher backendLogin(Teacher teacher)throws NoSuchUserException,PasswordErrorException, NoAuthorityException {
		//如果用户名为空则抛出异常
		if(teacher.getTeacherId()==null||teacher.getTeacherId().equals("")) {
			throw new NoSuchUserException("该用户名不存在");
		}
		//获取登录教师的密码
		Teacher teacherToLogin=teacherDao.getPassword(teacher.getTeacherId());
		//如果该教师不存在 则抛出异常
		if(teacherToLogin==null) {
			throw new NoSuchUserException("该用户名不存在");
		}
		//获取指定教师的密码
		String password=teacherToLogin.getPassword();
		//如果获取到指定教师的密码则继续验证,否则抛出用户不存在异常
		if(password!=null) {
			//验证密码,如果成功返回教师信息,否则抛出密码错误异常
			if(password.equals(teacher.getPassword())) {
				Teacher t=teacherDao.getInfo(teacher.getTeacherId());
				//获取教师权限
				Authorization authorization=authorizationDao.get(teacher.getTeacherId());
				
				if(authorization.getBackEndHandle()!=1) {
					throw new NoAuthorityException("您尚无管理员权限,无法登录后台,或联系维护人员给予管理员权限");
				}
				return t;
			}else {
				throw new PasswordErrorException("密码错误");
			}
		}else {
			throw new NoSuchUserException("该用户名不存在");
		}
		
	}
	
	public Teacher login(Teacher teacher)throws NoSuchUserException,PasswordErrorException {
		//如果用户名为空则抛出异常
		if(teacher.getTeacherId()==null||teacher.getTeacherId().equals("")) {
			throw new NoSuchUserException("该用户名不存在");
		}
		//获取指定教师的密码
		String password=teacherDao.getPassword(teacher.getTeacherId()).getPassword();
		//如果获取到指定教师的密码则继续验证,否则抛出用户不存在异常
		if(password!=null) {
			//验证密码,如果成功返回教师信息,否则抛出密码错误异常
			if(password.equals(teacher.getPassword())) {
				Teacher t=teacherDao.getInfo(teacher.getTeacherId());
				//如果验证成功为用户签发令牌
				String token=TokenUtil.getToken(teacher.getTeacherId());
				//将对应教师token存放到数据库中
				tokenDao.add(new Token(teacher.getTeacherId(), token));
				//返回信息加入token
				t.setToken(token);
				return t;
			}else {
				throw new PasswordErrorException("密码错误");
			}
		}else {
			throw new NoSuchUserException("该用户名不存在");
		}
		
	}
	
	@Override
	public List<Teacher> list(String teacherId) {
		List<Teacher> teachers=teacherDao.listTeacherIdAndName(teacherId);
		return teachers;
	}
	@Override
	public void logout(String teacherId) {
		tokenDao.delete(teacherId);
	}
	@Override
	public void updatePassword(TeacherUpdatePasswordParam teacherUpdatePasswordParam) throws NoSuchUserException,PasswordErrorException {
		Teacher oldTeacher=teacherDao.getPassword(teacherUpdatePasswordParam.getTeacherId());
		if(oldTeacher==null) {
			throw new NoSuchUserException("该用户名不存在");
		}
		if(oldTeacher.getPassword()==null||!oldTeacher.getPassword().equals(teacherUpdatePasswordParam.getOldPassword())) {
			throw new PasswordErrorException("旧密码输入错误");
		}
		teacherDao.updatePassword(new Teacher(teacherUpdatePasswordParam.getTeacherId(),teacherUpdatePasswordParam.getNewPassword()));
	} 
}
