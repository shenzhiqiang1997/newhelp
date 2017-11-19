package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Teacher;

public interface TeacherDao {
	//��ѯָ����ʦ�û�����
	public String getPassword(String teacherId);
	//��ѯָ����ʦ��Ϣ
	public Teacher getInfo(String teacherId);
	//��ѯָ����ʦȨ��
	public Byte  getAuthority(String teacherId);
	//��ѯ����ǰ��ʦ�����н�ʦ���û���������
	public List<Teacher> listTeacherIdAndName(String teacherId);
	//����ָ����ʦ������
	public void updatePassword(@Param("teacher")Teacher teacher);
}
