package com.uestc.newhelp.service;

import java.util.List;

import com.uestc.newhelp.dto.TeacherUpdatePasswordParam;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoSuchUserException;
import com.uestc.newhelp.exception.PasswordErrorException;
//���û��йص�ҵ���߼�
public interface UserService {
	//��¼��֤
	public Teacher login (Teacher teacher)throws NoSuchUserException,PasswordErrorException;
	//�ǳ�
	public void logout(String teacherId);
	//���Ľ�ʦ����
	public void updatePassword(TeacherUpdatePasswordParam teacherUpdatePasswordParam) throws NoSuchUserException,PasswordErrorException;
	//��ó���ǰ�û��������û����������û���
	public List<Teacher> list(String teacherId);
	//��̨��¼��֤
	public Teacher backendLogin(Teacher teacher) throws NoSuchUserException, PasswordErrorException, NoAuthorityException;
}
