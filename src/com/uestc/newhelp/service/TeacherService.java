package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.Teacher;

//���ʦ�˻��йص�ҵ���߼�
public interface TeacherService {
	//��ӽ�ʦ�˺�
	public void add(Teacher teacher);
	//ɾ����ʦ�˺�
	public void delete(String teacherId);
}
