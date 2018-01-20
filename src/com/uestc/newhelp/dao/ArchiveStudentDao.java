package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.Teacher;

public interface ArchiveStudentDao {
	//���ӵ���ѧ����Ϣ
	public void add(@Param("archiveStudent")ArchiveStudent archiveStudent);
	//ɾ��ָ������ѧ����Ϣ
	public void delete(Long studentId);
	//����ָ������ѧ����Ϣ
	public void update(@Param("archiveStudent")ArchiveStudent archiveStudent);
	//����ָ������ѧ������¼ʱ��Ϊnull
	public void updateLastRecordTimeToNull(@Param("archiveStudent")ArchiveStudent archiveStudent);
	//��ѯָ������ѧ����Ϣ
	public ArchiveStudent get(Long studentId);
	//��ѯָ����ʦ�ĵ���ѧ����Ϣ
	public List<ArchiveStudent> list(@Param("teacher")Teacher teacher);
	//����ָ����ʦ�ĵ���ѧ����Ϣ
	public List<ArchiveStudent> search(@Param("teacher")Teacher teacher,@Param("archiveStudent")ArchiveStudent archiveStudent);
	//�鿴��ѧ���Ƿ��Ѿ�������
	public String check(Long studentId);
}
