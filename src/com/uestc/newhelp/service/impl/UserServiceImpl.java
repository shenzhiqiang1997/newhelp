package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.dao.TokenDao;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.entity.Token;
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
	@Override
	public Teacher login(Teacher teacher)throws NoSuchUserException,PasswordErrorException {
		//����û���Ϊ�����׳��쳣
		if(teacher.getTeacherId()==null||teacher.getTeacherId().equals("")) {
			throw new NoSuchUserException("���û���������");
		}
		//��ȡָ����ʦ������
		String password=teacherDao.getPassword(teacher.getTeacherId());
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
}
