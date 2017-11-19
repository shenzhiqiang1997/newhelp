package com.uestc.newhelp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.PasswordNotMatchException;
//�����ѧ����Ϣ�йص�ҵ���߼�
public interface BaseStudentService {
	//��ѯ����ѧ���б�
	public List<BaseStudent> list();
	//��������ѧ���б�
	public List<BaseStudent> search(BaseStudent baseStudent);
	//��ѯ����ѧ��������Ϣ
	public BaseStudent getAllInfo(Long studentId);
	//��ѯ����ѧ��������Ϣ
	public BaseStudent getPersonalInfo(Long studentId);
	//��ѯ����ѧ����ͥ��Ϣ
	public BaseStudent getFamilyInfo(Long studentId);
	//��ѯ����ѧ����ͥ��Ϣ
	public BaseStudent getArchiveInfo(Long studentId)throws ArchiveStudentHadExistException;
	//��������������Ӧѧ��
	public List<Long> listIdByName(String name);
	//����ѧ��������Ϣ,��Ҫ��Ȩ���
	public void update(BaseStudent baseStudent,Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest) throws IOException,
	FileTypeNotMatchException,PasswordNotMatchException;
	//��Excel�ļ��е������ѧ����Ϣ
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile)throws FileTypeNotMatchException, IOException,IllegalStateException ;
	//��������ѧ���б�Excel�ļ���
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds) throws NotChoseExportObjectException,IOException;
}
