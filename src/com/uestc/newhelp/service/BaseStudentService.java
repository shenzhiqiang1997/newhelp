package com.uestc.newhelp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.dto.BaseStudentsWithPage;
import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.ExposeSetting;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.FormatException;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoDataToImportException;
import com.uestc.newhelp.exception.NoSettingException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.PasswordNotMatchException;
import com.uestc.newhelp.exception.StudentIdFormatException;
//�����ѧ����Ϣ�йص�ҵ���߼�
public interface BaseStudentService {
	//��ѯ����ѧ���б�
	public List<BaseStudent> list();
	//��������ѧ���б�
	public BaseStudentsWithPage search(BaseStudent baseStudent,String teacherId,Integer pageSize,Integer currentPage,Integer classSort);
	//��ѯ����ѧ��������Ϣ
	public BaseStudent getAllInfo(Long studentId);
	//��ѯ����ѧ��������Ϣ
	public BaseStudent getPersonalInfo(Long studentId);
	//��ѯ����ѧ����ͥ��Ϣ
	public BaseStudent getFamilyInfo(Long studentId);
	//��ѯ����ѧ����ͥ��Ϣ
	public BaseStudent getArchiveInfo(Long studentId)throws ArchiveStudentHadExistException;
	//��������������Ӧѧ��
	public List<Long> listIdByName(String name) throws NoSuchStudentException;
	//����ѧ��������Ϣ,��Ҫ��Ȩ���
	public void update(BaseStudent baseStudent,Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest) throws IOException,
	FileTypeNotMatchException,PasswordNotMatchException;
	//��Excel�ļ��е������ѧ����Ϣ
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile,Teacher teacher,ExposeSetting exposeSetting)throws FileTypeNotMatchException, IOException,IllegalStateException,NoAuthorityException, StudentIdFormatException, FormatException, NoDataToImportException ;
	//��������ѧ���б�Excel�ļ���
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds,String teacherId,ExposeSetting exposeSetting) throws NotChoseExportObjectException,IOException, NoSettingException, NoAuthorityException;

}
