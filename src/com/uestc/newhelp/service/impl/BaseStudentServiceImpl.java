package com.uestc.newhelp.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.BaseStudentDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.NoDataToImportException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.PasswordNotMatchException;
import com.uestc.newhelp.exception.StudentIdFormatException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.BaseStudentService;
import com.uestc.newhelp.util.MultipartFileUtil;
import com.uestc.newhelp.util.POIUtil;
@Service
public class BaseStudentServiceImpl implements BaseStudentService {
	
	@Autowired
	private BaseStudentDao baseStudentDao;
	@Autowired
	private ArchiveStudentDao archiveStudentDao;
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public List<BaseStudent> list() {
		//查询基本学生列表
		List<BaseStudent> baseStudents=baseStudentDao.list();
		return baseStudents;
	}

	@Override
	public List<BaseStudent> search(BaseStudent baseStudent) {
		//搜索基本学生列表
		List<BaseStudent> baseStudents=baseStudentDao.search(baseStudent);
		return baseStudents;
	}

	@Override
	public BaseStudent getAllInfo(Long studentId) {
		BaseStudent baseStudent=baseStudentDao.getAll(studentId);
		return baseStudent;
	}

	@Override
	public BaseStudent getPersonalInfo(Long studentId) {
		BaseStudent baseStudent=baseStudentDao.getPersonalInfo(studentId);
		return baseStudent;
	}

	@Override
	public BaseStudent getFamilyInfo(Long studentId) {
		BaseStudent baseStudent=baseStudentDao.getFamilyInfo(studentId);
		return baseStudent;
	}

	@Override
	public BaseStudent getArchiveInfo(Long studentId)throws ArchiveStudentHadExistException{
		//判断该学生是否已经建档
		String teacherId=archiveStudentDao.check(studentId);
		if(teacherId==null) {
			BaseStudent baseStudent=baseStudentDao.getArchiveInfo(studentId);
			return baseStudent;
		}else {
			Teacher teacher=teacherDao.getInfo(teacherId);
			throw new ArchiveStudentHadExistException("该学生已被"+teacher.getName()+"老师帮扶");
		}
		
	}
	
	@Override
	public List<Long> listIdByName(String name) {
		List<Long> studentIds=baseStudentDao.listStudentIdByName(name);
		if(studentIds==null||studentIds.size()==0) {
			throw new NoSuchStudentException("当前学生尚未收录到基本信息库");
		}
		return studentIds;
	}


	@Override
	public void update(BaseStudent baseStudent, Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest)throws IOException,FileTypeNotMatchException,PasswordNotMatchException {
		//查询指定教师密码
		String password=teacherDao.getPassword(teacher.getTeacherId());
		//验证密码
		if(password.equals(teacher.getPassword())) {
			if(multipartFile!=null) {
				//获取图片格式
				String fileType=MultipartFileUtil.getType(multipartFile);
				//判断图片格式是否匹配
				if(".jpg".equals(fileType)){
						//将上传的图片保存到项目指定目录中
						ServletContext servletContext=httpServletRequest.getServletContext();
						String filePath=MultipartFileUtil.storeMultipartFile(multipartFile, servletContext.getRealPath(Path.STUDENT_PHOTO_PATH_UNDER_ARCHIVE), String.valueOf(baseStudent.getStudentId())+".jpg");
						//将保存的图片URL存入数据库
						//将存储路径转化为外部可以访问的URL
						int index=filePath.indexOf(servletContext.getContextPath().substring(1));
						filePath=filePath.substring(index);
						filePath=filePath.replaceAll("\\\\", "/");
						filePath=Path.HOST_PATH+"/"+filePath;
						//将该URL存放到数据库中
						baseStudent.setPhotoUrl(filePath);
						baseStudentDao.update(baseStudent);
				}else {
					//如果图片格式不正确则抛出格式错误异常
					throw new FileTypeNotMatchException("请保证图片格式为jpg");
				}
			}else {
				baseStudentDao.update(baseStudent);
			}
		}else {
			//如果验证密码错误则抛出密码错误异常
			throw new PasswordNotMatchException("密码错误");
		}
	}

	@Override
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile)throws FileTypeNotMatchException, IOException,IllegalStateException {
		//获取文件的类型
		String fileType=MultipartFileUtil.getType(multipartFile);
		//如果不是目标文件则抛出文件类型不匹配异常
		if(!".xlsx".equals(fileType)) {
			throw new FileTypeNotMatchException("请保证上传的文件格式为.xlsx");
		}
		//用于盛放解析出的学生对象
		List<BaseStudent> baseStudents=new ArrayList<>();
		//日期格式器,用于日期的格式化
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//用上传的文件得到工作簿
		XSSFWorkbook workbook=new XSSFWorkbook(multipartFile.getInputStream());
		//只获取第一个sheet
		Sheet sheet=workbook.getSheetAt(0);
		//遍历每行
		for (Row row : sheet) {
			try {
				//略过表头
				if(row.getRowNum()==0) continue;
				//如果学号为空或格式不对,则抛出学号格式化异常
				Long studentId;
				try {
					studentId=Long.valueOf(POIUtil.getStringCellValue(row, 0));
				} catch (NumberFormatException e) {
					throw new StudentIdFormatException("导入的学生存在部分学号为空或学号格式错误,请检查后重新导入");
				}finally {
					workbook.close();
				}
				//对于其他如果出现格式转化异常则默认为null,如果是字符类型则默认为"无"
				String name=POIUtil.getStringCellValue(row, 1);
				Short grade;
				try {
					grade=Short.valueOf(POIUtil.getStringCellValue(row, 2));
				} catch (NumberFormatException e) {
					grade=null;
				}
				Integer studentClass;
				try {
					studentClass=Integer.valueOf(POIUtil.getStringCellValue(row, 3));
				}  catch (NumberFormatException e) {
					studentClass=null;
				}
				String sex=POIUtil.getStringCellValue(row, 4);
				String duty=POIUtil.getStringCellValue(row, 5);
				String dormitory=POIUtil.getStringCellValue(row, 6);
				String contactWay=POIUtil.getStringCellValue(row, 7);
				String idCardNumber=POIUtil.getStringCellValue(row, 8);
				Long qqNumber;
				try {
					qqNumber=Long.valueOf(POIUtil.getStringCellValue(row, 9));
				} catch (NumberFormatException e) {
					qqNumber=null;
				}
				String email=POIUtil.getStringCellValue(row, 10);
				Date birthday;
				try {
					birthday=sdf.parse(POIUtil.getStringCellValue(row, 11));
				} catch (ParseException e) {
					birthday=null;
				}
				Float height;
				try {
					height=Float.valueOf(POIUtil.getStringCellValue(row, 12));
				} catch (NumberFormatException e) {
					height=null;
				}
				String major=POIUtil.getStringCellValue(row, 13);
				String politicalStatus=POIUtil.getStringCellValue(row, 14);
				String ethnicGroup=POIUtil.getStringCellValue(row, 15);
				String birthOrigin=POIUtil.getStringCellValue(row, 16);
				String collegeEntranceExamScore=POIUtil.getStringCellValue(row, 17);
				String collegeEntranceExamEnglishScore=POIUtil.getStringCellValue(row, 18);
				Byte entranceExamEnglishScore;
				try {
					entranceExamEnglishScore=Byte.valueOf(POIUtil.getStringCellValue(row, 19));
				} catch (NumberFormatException e) {
					entranceExamEnglishScore=null;
				}
				String hometownRailwayStation=POIUtil.getStringCellValue(row, 20);
				String province=POIUtil.getStringCellValue(row, 21);
				String city=POIUtil.getStringCellValue(row, 22);
				String familyAddress=POIUtil.getStringCellValue(row, 23);
				String familyTelNumber=POIUtil.getStringCellValue(row, 24);
				String postcode=POIUtil.getStringCellValue(row, 25);
				String specialty=POIUtil.getStringCellValue(row, 26);
				String dutyInHighSchool=POIUtil.getStringCellValue(row, 27);
				String awardInHighSchool=POIUtil.getStringCellValue(row, 28);
				String isHadTechnologyCompetitionAward=POIUtil.getStringCellValue(row, 29);
				String fatherName=POIUtil.getStringCellValue(row, 30);
				String fatherWorkUnit=POIUtil.getStringCellValue(row, 31);
				String fatherWorkUnitAddress=POIUtil.getStringCellValue(row, 32);
				String fatherDuty=POIUtil.getStringCellValue(row, 33);
				String fatherPostcode=POIUtil.getStringCellValue(row, 34);
				String fatherTelNumber=POIUtil.getStringCellValue(row, 35);
				String motherName=POIUtil.getStringCellValue(row, 36);
				String motherWorkUnit=POIUtil.getStringCellValue(row, 37);
				String motherWorkUnitAddress=POIUtil.getStringCellValue(row, 38);
				String motherDuty=POIUtil.getStringCellValue(row, 39);
				String motherPostcode=POIUtil.getStringCellValue(row, 40);
				String motherTelNumber=POIUtil.getStringCellValue(row, 41);
				BaseStudent baseStudent=new BaseStudent(studentId, name, grade, studentClass, sex, duty, dormitory, contactWay, idCardNumber, qqNumber, email, birthday, height, major, politicalStatus, ethnicGroup, birthOrigin, collegeEntranceExamScore, collegeEntranceExamEnglishScore, entranceExamEnglishScore, hometownRailwayStation, province, city, familyAddress, familyTelNumber, postcode, specialty, dutyInHighSchool, awardInHighSchool,isHadTechnologyCompetitionAward, fatherName, fatherWorkUnit, fatherWorkUnitAddress, fatherDuty, fatherPostcode, fatherTelNumber, motherName, motherWorkUnit, motherWorkUnitAddress, motherDuty, motherPostcode, motherTelNumber);
				baseStudents.add(baseStudent);
				System.out.println(baseStudent);
			} catch (IllegalStateException e) {
				//如果途中遇到了非文本格式,由于转换的问题必然会抛出异常,此时提醒用户将单元格设置为文本类型
				throw new IllegalStateException("请确保在录入信息前,将所有单元格的格式设置为文本类型");
			}
		}
		workbook.close();
		if(baseStudents.size()>0) {
			baseStudentDao.addBatch(baseStudents);
		}else {
			throw new NoDataToImportException("请确保导入的Execl中有数据可以上传");
		}
		
	}
	


	@Override
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds) throws NotChoseExportObjectException,IOException {
		//如果没有选择导出对象则抛出异常
		if(studentIds==null||studentIds.size()==0) {
			throw new NotChoseExportObjectException("尚未选择要导出的对象,请先选择后再导出");
		}
		//从数据库中获取到要导出的基本学生数据
		List<BaseStudent> baseStudents=baseStudentDao.listByIds(studentIds);
		//将要导出的基本学生信息放入Map中
		Map<Integer, Object[]> row=new HashMap<>();
		for(int i=0;i<baseStudents.size();i++) {
			BaseStudent baseStudent=baseStudents.get(i);
			Object[] rowValues=new Object[] {baseStudent.getStudentId(),baseStudent.getName(),
					baseStudent.getGrade(),baseStudent.getStudentClass(),baseStudent.getSex(),
					baseStudent.getDuty(),baseStudent.getDormitory(),
					baseStudent.getContactWay(),baseStudent.getIdCardNumber(),baseStudent.getQqNumber(),
					baseStudent.getEmail(),baseStudent.getBirthday(),baseStudent.getHeight(),
					baseStudent.getMajor(),baseStudent.getPoliticalStatus(),baseStudent.getEthnicGroup(),
					baseStudent.getBirthOrigin(),baseStudent.getCollegeEntranceExamScore(),
					baseStudent.getCollegeEntranceExamEnglishScore(),baseStudent.getEntranceExamEnglishScore(),
					baseStudent.getHometownRailwayStation(),baseStudent.getProvince(),
					baseStudent.getCity(),baseStudent.getFamilyAddress(),baseStudent.getFamilyTelNumber(),
					baseStudent.getPostcode(),baseStudent.getSpecialty(),baseStudent.getDutyInHighSchool(),
					baseStudent.getAwardInHighSchool(),
					baseStudent.getIsHadTechnologyCompetitionAward(),baseStudent.getFatherName(),
					baseStudent.getFatherWorkUnit(),baseStudent.getFatherWorkUnitAddress(),
					baseStudent.getFatherDuty(),baseStudent.getFatherPostcode(),baseStudent.getFatherTelNumber(),
					baseStudent.getMotherName(),baseStudent.getMotherWorkUnit(),baseStudent.getMotherWorkUnitAddress(),
					baseStudent.getMotherDuty(),baseStudent.getMotherPostcode(),baseStudent.getMotherTelNumber()};
			row.put(i+1, rowValues);
		}
		//调用工具类,获取文件数组
		byte[] body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_EXCEL_TEMPLATE_NAME);
		return body;
	}
	
	
	
	
	

}
