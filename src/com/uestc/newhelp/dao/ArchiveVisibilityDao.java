package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ArchiveVisibility;

public interface ArchiveVisibilityDao {
	// ���ӽ�ʦ�Ŀɼ�����ĵ�
	public void add(@Param("archiveVisibility")ArchiveVisibility archiveVisibility);
	// ɾ����ʦ��ָ����������Ŀɼ���
	public void delete(@Param("archiveVisibility")ArchiveVisibility archiveVisibility);
	// ɾ��ָ���������id�Ŀɼ���
	public void deleteByArchiveId(@Param("archiveId")Long archiveId);
	// ��ѯָ����ʦ�Ŀɼ�����ĵ�
	public List<Long> list(@Param("teacherId")String teacherId);
	// ��ѯָ����������Ŀɼ���ʦid�б�
	public List<String> listTeacherIds(@Param("archiveId")Long archiveId);
}
