package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Teacher;

public interface TeacherDao {
	//��ѯָ����ʦ�û�����
	public Teacher getPassword(String teacherId);
	//��ѯָ����ʦ��Ϣ
	public Teacher getInfo(String teacherId);
	//��ѯָ����ʦȨ��
	public Byte  getAuthority(String teacherId);
	//��ѯ����ǰ��ʦ�����н�ʦ���û���������
	public List<Teacher> listTeacherIdAndName(String teacherId);
	//����ָ����ʦ������
	public void updatePassword(@Param("teacher")Teacher teacher);
	
	//��ѯ��ʦ�б�
	public List<Teacher> list();
	//ɾ��ָ����ʦ
	public void delete(String teacherId);
	//����ָ����ʦ
	public void update(@Param("teacher")Teacher teacher);
	//���ӽ�ʦ
	public void add(@Param("teacher")Teacher teacher);
}
