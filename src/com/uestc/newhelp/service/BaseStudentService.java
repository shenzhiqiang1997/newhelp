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
//与基本学生信息有关的业务逻辑
public interface BaseStudentService {
	//查询基本学生列表
	public List<BaseStudent> list();
	//搜索基本学生列表
	public BaseStudentsWithPage search(BaseStudent baseStudent,String teacherId,Integer pageSize,Integer currentPage,Integer classSort);
	//查询基本学生所有信息
	public BaseStudent getAllInfo(Long studentId);
	//查询基本学生个人信息
	public BaseStudent getPersonalInfo(Long studentId);
	//查询基本学生家庭信息
	public BaseStudent getFamilyInfo(Long studentId);
	//查询档案学生家庭信息
	public BaseStudent getArchiveInfo(Long studentId)throws ArchiveStudentHadExistException;
	//根据姓名查找相应学号
	public List<Long> listIdByName(String name) throws NoSuchStudentException;
	//更新学生基本信息,需要授权完成
	public void update(BaseStudent baseStudent,Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest) throws IOException,
	FileTypeNotMatchException,PasswordNotMatchException;
	//从Excel文件中导入基本学生信息
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile,Teacher teacher,ExposeSetting exposeSetting)throws FileTypeNotMatchException, IOException,IllegalStateException,NoAuthorityException, StudentIdFormatException, FormatException, NoDataToImportException ;
	//导出基本学生列表到Excel文件中
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds,String teacherId,ExposeSetting exposeSetting) throws NotChoseExportObjectException,IOException, NoSettingException, NoAuthorityException;

}
