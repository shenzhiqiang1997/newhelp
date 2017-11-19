package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.BaseStudent;

public interface BaseStudentDao {
	//��ѯָ��ѧ������������Ϣ
	public BaseStudent getAll(Long studentId);
	//��ѯָ��ѧ������������Ϣ
	public BaseStudent getPersonalInfo(Long studentId);
	//��ѯָ��ѧ��������ͥ��Ϣ
	public BaseStudent getFamilyInfo(Long studentId);
	//��ѯָ��ѧ�����ڽ�������Ϣ
	public BaseStudent getArchiveInfo(Long studentId);
	//���ݰ����ʦ�ı�¶��������ѯѧ��������Ϣ�б�
	public List<BaseStudent> list();
	//���ݰ����ʦ�ı�¶���ú�������������ѧ��������Ϣ�б�
	public List<BaseStudent> search(@Param("baseStudent")BaseStudent baseStudent);
	//��ѯָ��������ѧ��ѧ��
	public List<Long> listStudentIdByName(String name);
	//��������ѧ��������Ϣ
	public void addBatch(@Param("baseStudents")List<BaseStudent> baseStudents);
	//����ָ��ѧ���Ļ�����Ϣ
	public void update(@Param("baseStudent")BaseStudent baseStudent);
	//����ѧ��������ѯѧ��������Ϣ
	public List<BaseStudent> listByIds(@Param("studentIds")List<Long> studentIds);
}
