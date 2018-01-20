package com.uestc.newhelp.service;

import java.io.IOException;
import java.util.List;


import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;

public interface ArchiveStudentService {
	//��ѯָ����ʦ�İ��ѧ���б�
	public List<ArchiveStudent> list(String teacherId);
	//����ָ����ʦ���ѧ���б�
	public List<ArchiveStudent> search(ArchiveStudent archiveStudent);
	//���Ӱ��ѧ��
	public void add(ArchiveStudent archiveStudent)throws NotPointOutStudentIdException,NoSuchStudentException,ArchiveStudentHadExistException;
	//ɾ�����ѧ��
	public void delete(ArchiveStudent archiveStudent);
	//���°��ѧ��
	public void update(ArchiveStudent archiveStudent);
	//��ѯ���ѧ����ϸ��Ϣ
	public ArchiveStudent get(Long studentId);
	//����ָ��ѧ������������Word�ļ���
	public byte[] exportArchiveToWordFile(Long studentId)
			throws IOException, NotChoseExportObjectException, NoSuchStudentException, Exception;
}
