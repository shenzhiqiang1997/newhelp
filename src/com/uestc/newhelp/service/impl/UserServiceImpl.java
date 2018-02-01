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
		//����û���Ϊ�����׳��쳣
		if(teacher.getTeacherId()==null||teacher.getTeacherId().equals("")) {
			throw new NoSuchUserException("���û���������");
		}
		//��ȡ��¼��ʦ������
		Teacher teacherToLogin=teacherDao.getPassword(teacher.getTeacherId());
		//����ý�ʦ������ ���׳��쳣
		if(teacherToLogin==null) {
			throw new NoSuchUserException("���û���������");
		}
		//��ȡָ����ʦ������
		String password=teacherToLogin.getPassword();
		//�����ȡ��ָ����ʦ�������������֤,�����׳��û��������쳣
		if(password!=null) {
			//��֤����,����ɹ����ؽ�ʦ��Ϣ,�����׳���������쳣
			if(password.equals(teacher.getPassword())) {
				Teacher t=teacherDao.getInfo(teacher.getTeacherId());
				//��ȡ��ʦȨ��
				Authorization authorization=authorizationDao.get(teacher.getTeacherId());
				
				if(authorization.getBackEndHandle()!=1) {
					throw new NoAuthorityException("�����޹���ԱȨ��,�޷���¼��̨,����ϵά����Ա�������ԱȨ��");
				}
				return t;
			}else {
				throw new PasswordErrorException("�������");
			}
		}else {
			throw new NoSuchUserException("���û���������");
		}
		
	}
	
	public Teacher login(Teacher teacher)throws NoSuchUserException,PasswordErrorException {
		//����û���Ϊ�����׳��쳣
		if(teacher.getTeacherId()==null||teacher.getTeacherId().equals("")) {
			throw new NoSuchUserException("���û���������");
		}
		//��ȡָ����ʦ������
		String password=teacherDao.getPassword(teacher.getTeacherId()).getPassword();
		//�����ȡ��ָ����ʦ�������������֤,�����׳��û��������쳣
		if(password!=null) {
			//��֤����,����ɹ����ؽ�ʦ��Ϣ,�����׳���������쳣
			if(password.equals(teacher.getPassword())) {
				Teacher t=teacherDao.getInfo(teacher.getTeacherId());
				//�����֤�ɹ�Ϊ�û�ǩ������
				String token=TokenUtil.getToken(teacher.getTeacherId());
				//����Ӧ��ʦtoken��ŵ����ݿ���
				tokenDao.add(new Token(teacher.getTeacherId(), token));
				//������Ϣ����token
				t.setToken(token);
				return t;
			}else {
				throw new PasswordErrorException("�������");
			}
		}else {
			throw new NoSuchUserException("���û���������");
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
			throw new NoSuchUserException("���û���������");
		}
		if(oldTeacher.getPassword()==null||!oldTeacher.getPassword().equals(teacherUpdatePasswordParam.getOldPassword())) {
			throw new PasswordErrorException("�������������");
		}
		teacherDao.updatePassword(new Teacher(teacherUpdatePasswordParam.getTeacherId(),teacherUpdatePasswordParam.getNewPassword()));
	} 
}
