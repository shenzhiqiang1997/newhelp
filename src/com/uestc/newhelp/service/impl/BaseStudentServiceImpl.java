package com.uestc.newhelp.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.constant.Constant;
import com.uestc.newhelp.constant.Regex;
import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.BaseStudentDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.dto.BaseStudentCount;
import com.uestc.newhelp.dto.BaseStudentsWithPage;
import com.uestc.newhelp.dto.DropParam;
import com.uestc.newhelp.dto.IdNameParam;
import com.uestc.newhelp.dto.Page;
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
import com.uestc.newhelp.exception.NoSuchUserException;
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
	public BaseStudentsWithPage search(BaseStudent baseStudent,String teacherId,Integer pageSize,Integer currentPage,Integer classSort) {
		//搜索基本学生列表
		
		if(classSort!=1&&classSort!=0) {
			classSort=0;
		}
		//获取对应老师年级权限
		Teacher teacher=teacherDao.getInfo(teacherId);
		//如果教师不存在 则直接返回空
		if(teacher==null) {
			return null;
		}
		
		Integer recordNum=baseStudentDao.searchRecordNumByCondition(baseStudent,teacher.getGrade(),"在读","休学");
		Page page=new Page(currentPage, pageSize, recordNum);
		List<BaseStudent> baseStudents=baseStudentDao.searchByCondition(baseStudent,teacher.getGrade(),page,classSort,"在读","休学");
		BaseStudentsWithPage baseStudentsWithPage=new BaseStudentsWithPage(baseStudents, page);
		return baseStudentsWithPage;
	}
	
	@Override
	public BaseStudentsWithPage searchHistory(BaseStudent baseStudent,String teacherId,Integer pageSize,Integer currentPage,Integer classSort) {
		//搜索基本学生列表
		
		if(classSort!=1&&classSort!=0) {
			classSort=0;
		}
		//获取对应老师年级权限
		Teacher teacher=teacherDao.getInfo(teacherId);
		//如果教师不存在 则直接返回空
		if(teacher==null) {
			return null;
		}
		
		Integer recordNum=baseStudentDao.searchRecordNumByCondition(baseStudent,teacher.getGrade(),"退学","毕业");
		Page page=new Page(currentPage, pageSize, recordNum);
		List<BaseStudent> baseStudents=baseStudentDao.searchByCondition(baseStudent,teacher.getGrade(),page,classSort,"退学","毕业");
		BaseStudentsWithPage baseStudentsWithPage=new BaseStudentsWithPage(baseStudents, page);
		return baseStudentsWithPage;
	}
	
	@Override
	public List<BaseStudent> searchWithOutPage(BaseStudent baseStudent,String teacherId) {
		//搜索基本学生列表
		
		//获取对应老师年级权限
		Teacher teacher=teacherDao.getInfo(teacherId);
		//如果教师不存在 则直接返回空
		if(teacher==null) {
			return null;
		}
		//如果没有指定学生的学业状态 则默认只查询在读的学生
		if(baseStudent.getStudyCondition()==null||baseStudent.getStudyCondition().equals("")) {
			baseStudent.setStudyCondition(Constant.DEFAULT_STUDY_CONDITION);
		}
		List<BaseStudent> baseStudents=baseStudentDao.searchWithoutPage(baseStudent,teacher.getGrade());
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
	public List<Long> listIdByName(String name) throws NoSuchStudentException {
		List<Long> studentIds=baseStudentDao.listStudentIdByName(name);
		if(studentIds==null||studentIds.size()==0) {
			throw new NoSuchStudentException("当前学生尚未收录到基本信息库");
		}
		return studentIds;
	}


	@Override
	public void update(BaseStudent baseStudent, Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest)throws IOException,FileTypeNotMatchException,PasswordNotMatchException {
		//查询指定教师密码
		String password=teacherDao.getPassword(teacher.getTeacherId()).getPassword();
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
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile,Teacher teacher,ExposeSetting exposeSetting)throws FileTypeNotMatchException, IOException,IllegalStateException,NoAuthorityException, StudentIdFormatException, FormatException, NoDataToImportException {
		if(teacher==null||teacher.getTeacherId()==null) {
			throw new NoAuthorityException("你无权限导入数据,请联系具有管理员权限的用户导入");
		}
		Teacher teacher1=teacherDao.getPassword(teacher.getTeacherId());
		if(!(teacher1.getPassword().equals(teacher.getPassword()))) {
			throw new NoAuthorityException("验证失败 密码有误 无法导入 ");
		}
		
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
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/M/dd");
		SimpleDateFormat sdf3=new SimpleDateFormat("yyyy/M/d");
		//用上传的文件得到工作簿
		XSSFWorkbook workbook=new XSSFWorkbook(multipartFile.getInputStream());
		//只获取第一个sheet
		Sheet sheet=workbook.getSheetAt(0);
		int rowIndex=0;
		int colIndex=0;
		//遍历每行 根据导入设置选取部分信息
			for (Row row : sheet) {
				colIndex=0;
				rowIndex++;
				try {
					//略过表头
					if(row.getRowNum()==0) continue;
					//如果学号为空或格式不对,则抛出学号格式化异常
					Long studentId;
					try {
						colIndex++;
						String studentIdString=POIUtil.getStringCellValue(row, 0);
						if(!studentIdString.matches(Regex.STUDENT_ID)) {
							throw new FormatException("导入的学生学号为空或学号格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"学号列");
						}
						studentId=Long.valueOf(studentIdString);
						
					} catch (NumberFormatException e) {
						throw new StudentIdFormatException("导入的学生学号为空或学号格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"学号列");
					}finally {
						workbook.close();
					}
					//对于其他如果出现格式转化异常则默认为null,如果是字符类型则默认为"无"
					String name = null;
					colIndex++;
					if(exposeSetting.getExposeName()==(byte)1) {
						name=POIUtil.getStringCellValue(row, 1);
						if(!name.matches(Regex.NAME)) {
							throw new FormatException("导入的学生姓名格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"姓名列");
						}
					}
					
					Short grade = null;
					colIndex++;
					if(exposeSetting.getExposeGrade()==(byte)1) {
						try {
							String gradeString=POIUtil.getStringCellValue(row, 2);
							if(!gradeString.matches(Regex.GRADE)) {
								throw new FormatException("导入的学生年级格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"年级列");
							}
							grade=Short.valueOf(gradeString);
						} catch (NumberFormatException e) {
							grade=null;
						}
					}
					
					Integer studentClass = null;
					colIndex++;
					if(exposeSetting.getExposeStudentClass()==(byte)1) {
						try {
							String studentClassString=POIUtil.getStringCellValue(row, 3);
							if(!studentClassString.matches(Regex.STUDENT_CLASS)) {
								throw new FormatException("导入的学生班级格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"班级列");
							}
							studentClass=Integer.valueOf(studentClassString);
						}  catch (NumberFormatException e) {
							studentClass=null;
						}
					}
					
					String sex = null;
					colIndex++;
					if(exposeSetting.getExposeSex()==(byte)1) {
						sex=POIUtil.getStringCellValue(row, 4);
						if(!sex.matches(Regex.SEX)) {
							throw new FormatException("导入的学生性别格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"性别列");
						}
					}
					String duty = null;
					colIndex++;
					if(exposeSetting.getExposeDuty()==(byte)1) {
						duty=POIUtil.getStringCellValue(row, 5);
						if(!duty.matches(Regex.DUTY)) {
							throw new FormatException("导入的学生职务格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"职务列");
						}
					}
					
					String dormitory = null;
					colIndex++;
					if(exposeSetting.getExposeDormitory()==(byte)1) {
						dormitory=POIUtil.getStringCellValue(row, 6);
						if(!dormitory.matches(Regex.DORMITORY)) {
							throw new FormatException("导入的学生宿舍格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"宿舍列");
						}
					}
					String contactWay = null;
					colIndex++;
					if(exposeSetting.getExposeContactWay()==(byte)1) {
						contactWay=POIUtil.getStringCellValue(row, 7);
						if(!contactWay.matches(Regex.CONTACT_WAY)) {
							throw new FormatException("导入的学生联系方式格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"联系方式列");
						}
					}
					String idCardNumber = null;
					colIndex++;
					if(exposeSetting.getExposeIdCardNumber()==(byte)1) {
						idCardNumber=POIUtil.getStringCellValue(row, 8);
						if(!idCardNumber.matches(Regex.ID_CARD_NUMBER)) {
							throw new FormatException("导入的学生身份证号格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"身份证号列");
						}
					}
					
					Long qqNumber = null;
					colIndex++;
					if(exposeSetting.getExposeQqNumber()==(byte)1) {
						try {
							String qqNumberString=POIUtil.getStringCellValue(row, 9);
							if(!qqNumberString.matches(Regex.QQ_NUMBER)) {
								throw new FormatException("导入的学生qq号格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"qq号列");
							}
							qqNumber=Long.valueOf(qqNumberString);
						} catch (NumberFormatException e) {
							qqNumber=null;
						}
					}
					
					String email = null;
					colIndex++;
					if(exposeSetting.getExposeEmail()==(byte)1) {
						email=POIUtil.getStringCellValue(row, 10);
						if(!email.matches(Regex.EMAIL)) {
							throw new FormatException("导入的学生邮箱格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"邮箱列");
						}
					}
					
					Date birthday = null;
					colIndex++;
					if(exposeSetting.getExposeBirthday()==(byte)1) {
						String birthdayString=POIUtil.getStringCellValue(row, 11);
						if(!birthdayString.matches(Regex.BIRTHDAY)) {
							throw new FormatException("导入的学生生日格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"生日列");
						}
						try {
							birthday=sdf.parse(birthdayString);
						} catch (ParseException e) {
							try {
								birthday=sdf1.parse(birthdayString);
							} catch (ParseException e1) {
								try {
									birthday=sdf2.parse(birthdayString);
								} catch (ParseException e2) {
									try {
										birthday=sdf3.parse(birthdayString);
									} catch (ParseException e3) {
										birthday=null;
									}
								}
							}
						}
					}
					
					Float height = null;
					colIndex++;
					if(exposeSetting.getExposeHeight()==(byte)1) {
						try {
							String heightString=POIUtil.getStringCellValue(row, 12);
							if(!heightString.matches(Regex.HEIGHT)) {
								throw new FormatException("导入的学生身高格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"身高列");
							}
							height=Float.valueOf(heightString);
						} catch (NumberFormatException e) {
							height=null;
						}
					}
					
					String major = null;
					colIndex++;
					if(exposeSetting.getExposeMajor()==(byte)1) {
						major=POIUtil.getStringCellValue(row, 13);
						if(!major.matches(Regex.MAJOR)) {
							throw new FormatException("导入的学生专业格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"专业列");
						}
					}
					
					String politicalStatus = null;
					colIndex++;
					if(exposeSetting.getExposePoliticalStatus()==(byte)1) {
						politicalStatus=POIUtil.getStringCellValue(row, 14);
						if(!politicalStatus.matches(Regex.POLITICAL_STATUS)) {
							throw new FormatException("导入的学生政治面貌格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"政治面貌列");
						}
					}
					String ethnicGroup = null;
					colIndex++;
					if(exposeSetting.getExposeEthnicGroup()==(byte)1) {
						ethnicGroup=POIUtil.getStringCellValue(row, 15);
						if(!ethnicGroup.matches(Regex.ETHNIC_GROUP)) {
							throw new FormatException("导入的学生民族格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"民族列");
						}
					}
					String birthOrigin = null;
					colIndex++;
					if(exposeSetting.getExposeBirthOrigin()==(byte)1) {
						birthOrigin=POIUtil.getStringCellValue(row, 16);
						if(!birthOrigin.matches(Regex.BIRTH_ORIGIN)) {
							throw new FormatException("导入的学生生源地格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"生源地列");
						}
					}
					
					String collegeEntranceExamScore = null;
					colIndex++;
					if(exposeSetting.getExposeCollegeEntranceExamScore()==(byte)1) {
						collegeEntranceExamScore=POIUtil.getStringCellValue(row, 17);
						if(!collegeEntranceExamScore.matches(Regex.COLLEGE_ENTRANCE_EXAM_SCORE)) {
							throw new FormatException("导入的学生高考成绩格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"高考成绩列");
						}
					}
					
					String collegeEntranceExamEnglishScore = null;
					colIndex++;
					if(exposeSetting.getExposeCollegeEntranceExamEnglishScore()==(byte)1) {
						collegeEntranceExamEnglishScore=POIUtil.getStringCellValue(row, 18);
						if(!collegeEntranceExamEnglishScore.matches(Regex.COLLEGE_ENTRANCE_EXAM_ENGLISH_SCORE)) {
							throw new FormatException("导入的学生高考英语成绩格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"高考英语成绩列");
						}
					}
					
					Byte entranceExamEnglishScore = null;
					colIndex++;
					if(exposeSetting.getExposeEntranceExamEnglishScore()==(byte)1) {
						try {
							String entranceExamEnglishScoreString=POIUtil.getStringCellValue(row, 19);
							if(!entranceExamEnglishScoreString.matches(Regex.ENTRANCE_EXAM_ENGLISH_SCORE)) {
								throw new FormatException("导入的学生英语入学考试成绩格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"英语入学考试成绩列");
							}
							entranceExamEnglishScore=Byte.valueOf(entranceExamEnglishScoreString);
						} catch (NumberFormatException e) {
							entranceExamEnglishScore=null;
						}
					}
					
					String hometownRailwayStation = null;
					colIndex++;
					if(exposeSetting.getExposeHometownRailwayStation()==(byte)1) {
						hometownRailwayStation=POIUtil.getStringCellValue(row, 20);
						if(!hometownRailwayStation.matches(Regex.HOMETOWN_RAILWAY_STATION)) {
							throw new FormatException("导入的学生家乡所在火车站格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"家乡所在火车站列");
						}
					}
					
					String province = null;
					colIndex++;
					if(exposeSetting.getExposeProvince()==(byte)1) {
						province=POIUtil.getStringCellValue(row, 21);
						if(!province.matches(Regex.PROVINCE)) {
							throw new FormatException("导入的学生省份格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"省份列");
						}
					}
					
					String city = null;
					colIndex++;
					if(exposeSetting.getExposeCity()==(byte)1) {
						city=POIUtil.getStringCellValue(row, 22);
						if(!city.matches(Regex.CITY)) {
							throw new FormatException("导入的学生所在城市格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"所在城市列");
						}
					}
					
					String familyAddress = null;
					colIndex++;
					if(exposeSetting.getExposeFamilyAddress()==(byte)1) {
						familyAddress=POIUtil.getStringCellValue(row, 23);
						if(!familyAddress.matches(Regex.FAMILY_ADDRESS)) {
							throw new FormatException("导入的学生家庭详细地址格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"家庭详细地址列");
						}
					}
					
					String familyTelNumber = null;
					colIndex++;
					if(exposeSetting.getExposeFamilyTelNumber()==(byte)1) {
						familyTelNumber=POIUtil.getStringCellValue(row, 24);
						if(!familyTelNumber.matches(Regex.FAMILY_TEL_NUMBER)) {
							throw new FormatException("导入的学生家庭电话格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"家庭电话列");
						}
					}
					String postcode = null;
					colIndex++;
					if(exposeSetting.getExposePostcode()==(byte)1) {
						postcode=POIUtil.getStringCellValue(row, 25);
						if(!postcode.matches(Regex.POSTCODE)) {
							throw new FormatException("导入的学生邮政编码格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"邮政编码列");
						}
					}
					
					String specialty = null;
					colIndex++;
					if(exposeSetting.getExposeSpecialty()==(byte)1) {
						specialty=POIUtil.getStringCellValue(row, 26);
						if(!specialty.matches(Regex.SPECIALTY)) {
							throw new FormatException("导入的学生特长格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"特长列");
						}
					}
					String dutyInHighSchool = null;
					colIndex++;
					if(exposeSetting.getExposeDutyInHighSchool()==(byte)1) {
						dutyInHighSchool=POIUtil.getStringCellValue(row, 27);
						if(!dutyInHighSchool.matches(Regex.DUTY_IN_HIGH_SCHOOL)) {
							throw new FormatException("导入的学生高中曾任职务格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"高中曾任职务列");
						}
					}
					String awardInHighSchool = null;
					colIndex++;
					if(exposeSetting.getExposeAwardInHighSchool()==(byte)1) {
						awardInHighSchool=POIUtil.getStringCellValue(row, 28);
						if(!awardInHighSchool.matches(Regex.AWARD_IN_HIGH_SCHOOL)) {
							throw new FormatException("导入的学生高中曾获奖励格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"高中曾获奖励列");
						}
					}
					
					String isHadTechnologyCompetitionAward = null;
					colIndex++;
					if(exposeSetting.getExposeIsHadTechnologyCompetitionAward()==(byte)1) {
						isHadTechnologyCompetitionAward=POIUtil.getStringCellValue(row, 29);
						if(!awardInHighSchool.matches(Regex.AWARD_IN_HIGH_SCHOOL)) {
							throw new FormatException("导入的学生是否有科技竞赛类获奖格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"是否有科技竞赛类获奖列");
						}
					}
					
					String fatherName = null;
					colIndex++;
					if(exposeSetting.getExposeFatherName()==(byte)1) {
						fatherName=POIUtil.getStringCellValue(row, 30);
						if(!fatherName.matches(Regex.NAME)) {
							throw new FormatException("导入的学生父亲姓名格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"父亲姓名列");
						}
					}
					String fatherWorkUnit = null;
					colIndex++;
					if(exposeSetting.getExposeFatherWorkUnit()==(byte)1) {
						fatherWorkUnit=POIUtil.getStringCellValue(row, 31);
						if(!fatherWorkUnit.matches(Regex.WORK_UNIT)) {
							throw new FormatException("导入的学生父亲工作单位格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"父亲工作单位列");
						}
					}
					String fatherWorkUnitAddress = null;
					colIndex++;
					if(exposeSetting.getExposeFatherWorkUnitAddress()==(byte)1) {
						fatherWorkUnitAddress=POIUtil.getStringCellValue(row, 32);
						if(!fatherWorkUnitAddress.matches(Regex.WORK_UNIT_DETAIL)) {
							throw new FormatException("导入的学生父亲工作单位详细地址格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"父亲工作单位详细地址列");
						}
					}
					String fatherDuty = null;
					colIndex++;
					if(exposeSetting.getExposeFatherDuty()==(byte)1) {
						fatherDuty=POIUtil.getStringCellValue(row, 33);
						if(!fatherDuty.matches(Regex.DUTY)) {
							throw new FormatException("导入的学生父亲职务格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"父亲职务列");
						}
					}
					
					String fatherPostcode=null;
					colIndex++;
					if(exposeSetting.getExposeFatherPostcode()==(byte)1) {
						fatherPostcode=POIUtil.getStringCellValue(row, 34);
						if(!fatherPostcode.matches(Regex.POSTCODE)) {
							throw new FormatException("导入的学生父亲邮编格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"父亲邮编列");
						}
					}
					
					String fatherTelNumber = null;
					colIndex++;
					if(exposeSetting.getExposeFatherTelNumber()==(byte)1) {
						fatherTelNumber=POIUtil.getStringCellValue(row, 35);
						if(!fatherTelNumber.matches(Regex.CONTACT_WAY)) {
							throw new FormatException("导入的学生父亲电话格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"父亲电话列");
						}
					}
					
					String motherName = null;
					colIndex++;
					if(exposeSetting.getExposeMotherName()==(byte)1) {
						motherName=POIUtil.getStringCellValue(row, 36);
						if(!motherName.matches(Regex.NAME)) {
							throw new FormatException("导入的学生母亲姓名格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"母亲姓名列");
						}
					}
					
					String motherWorkUnit = null;
					colIndex++;
					if(exposeSetting.getExposeMotherWorkUnit()==(byte)1) {
						motherWorkUnit=POIUtil.getStringCellValue(row, 37);
						if(!motherWorkUnit.matches(Regex.WORK_UNIT)) {
							throw new FormatException("导入的学生母亲工作单位格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"母亲工作单位列");
						}
					}
					
					String motherWorkUnitAddress = null;
					colIndex++;
					if(exposeSetting.getExposeMotherWorkUnitAddress()==(byte)1) {
						motherWorkUnitAddress=POIUtil.getStringCellValue(row, 38);
						if(!motherWorkUnitAddress.matches(Regex.WORK_UNIT_DETAIL)) {
							throw new FormatException("导入的学生母亲工作单位详细地址格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"母亲工作单位详细地址列");
						}
					}
					
					String motherDuty = null;
					colIndex++;
					if(exposeSetting.getExposeMotherDuty()==(byte)1) {
						motherDuty=POIUtil.getStringCellValue(row, 39);
						if(!motherDuty.matches(Regex.DUTY)) {
							throw new FormatException("导入的学生母亲职务格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"母亲职务列");
						}
					}
					
					String motherPostcode=null;
					colIndex++;
					if(exposeSetting.getExposeMotherPostcode()==(byte)1) {
						motherPostcode=POIUtil.getStringCellValue(row, 40);
						if(!motherPostcode.matches(Regex.POSTCODE)) {
							throw new FormatException("导入的学生母亲邮编格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"母亲邮编列");
						}
					}
					
					String motherTelNumber = null;
					colIndex++;
					if(exposeSetting.getExposeMotherTelNumber()==(byte)1) {
						motherTelNumber=POIUtil.getStringCellValue(row, 41);
						if(!motherTelNumber.matches(Regex.CONTACT_WAY)) {
							throw new FormatException("导入的学生母亲电话格式错误,请检查后重新导入,错误发生在"+rowIndex+"行,"+"母亲电话列");
						}
					}
					
					BaseStudent baseStudent=new BaseStudent(studentId, name, grade, studentClass, sex, duty, dormitory, contactWay, idCardNumber, qqNumber, email, birthday, height, major, politicalStatus, ethnicGroup, birthOrigin, collegeEntranceExamScore, collegeEntranceExamEnglishScore, entranceExamEnglishScore, hometownRailwayStation, province, city, familyAddress, familyTelNumber, postcode, specialty, dutyInHighSchool, awardInHighSchool,isHadTechnologyCompetitionAward, fatherName, fatherWorkUnit, fatherWorkUnitAddress, fatherDuty, fatherPostcode, fatherTelNumber, motherName, motherWorkUnit, motherWorkUnitAddress, motherDuty, motherPostcode, motherTelNumber);
					
					baseStudents.add(baseStudent);
				} catch (IllegalStateException e) {
					//如果途中遇到了非文本格式,由于转换的问题必然会抛出异常,此时提醒用户将单元格设置为文本类型
					throw new IllegalStateException("请确保在录入信息前,将所有单元格的格式设置为文本类型,错误发生在"+rowIndex+"行,"+colIndex+"列");
				}
			}
			workbook.close();
			if(baseStudents.size()>0) {
				baseStudentDao.addBatch(baseStudents,exposeSetting);
			}else {
				throw new NoDataToImportException("请确保导入的Execl中有数据可以上传");
			}
		
	}
	


	@Override
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds,String teacherId,ExposeSetting exposeSetting) throws NotChoseExportObjectException,IOException, NoSettingException, NoAuthorityException {
		//如果没有选择导出对象则抛出异常
		if(studentIds==null) {
			throw new NotChoseExportObjectException("尚未选择要导出的对象,请先选择后再导出");
		}
		//获取教师的年级权限
		Teacher teacher=teacherDao.getInfo(teacherId);
		if(teacher==null) {
			throw new NoAuthorityException("您的账号不存在,请向管理员获取账号");
		}
		//从数据库中获取到要导出的学生 如果studentIds的长度为0那么默认导出所有该年级学生的数据
		//同时考虑教师的权限 如果年级权限是0或者1则导出所有 否则导出该年级学生
		//根据导出设置查询学生信息
		List<BaseStudent> baseStudents=baseStudentDao.listByIds(studentIds,exposeSetting,teacher.getGrade());
		//将要导出的基本学生信息放入Map中
		Map<Integer, Object[]> row=new HashMap<>();
		for(int i=0;i<baseStudents.size();i++) {
			BaseStudent baseStudent=baseStudents.get(i);
			//将每行为空的数据置为空串
			Object[] rowValues=new Object[] {
					baseStudent.getStudentId(),
					baseStudent.getName()==null?"":baseStudent.getName(),
					baseStudent.getGrade()==null?"":baseStudent.getGrade(),
					baseStudent.getStudentClass()==null?"":baseStudent.getStudentClass(),
					baseStudent.getSex()==null?"":baseStudent.getSex(),
					baseStudent.getDuty()==null?"":baseStudent.getDuty(),
					baseStudent.getDormitory()==null?"":baseStudent.getDormitory(),
					baseStudent.getContactWay()==null?"":baseStudent.getContactWay(),
					baseStudent.getIdCardNumber()==null?"":baseStudent.getIdCardNumber(),
					baseStudent.getQqNumber()==null?"":baseStudent.getQqNumber(),
					baseStudent.getEmail()==null?"":baseStudent.getEmail(),
					baseStudent.getBirthday()==null?"":baseStudent.getBirthday(),
					baseStudent.getHeight()==null?"":baseStudent.getHeight(),
					baseStudent.getMajor()==null?"":baseStudent.getMajor(),
					baseStudent.getPoliticalStatus()==null?"":baseStudent.getPoliticalStatus(),
					baseStudent.getEthnicGroup()==null?"":baseStudent.getEthnicGroup(),
					baseStudent.getBirthOrigin()==null?"":baseStudent.getBirthOrigin(),
					baseStudent.getCollegeEntranceExamScore()==null?"":baseStudent.getCollegeEntranceExamScore(),
					baseStudent.getCollegeEntranceExamEnglishScore()==null?"":baseStudent.getCollegeEntranceExamEnglishScore(),
					baseStudent.getEntranceExamEnglishScore()==null?"":baseStudent.getEntranceExamEnglishScore(),
					baseStudent.getHometownRailwayStation()==null?"":baseStudent.getHometownRailwayStation(),
					baseStudent.getProvince()==null?"":baseStudent.getProvince(),
					baseStudent.getCity()==null?"":baseStudent.getCity(),
					baseStudent.getFamilyAddress()==null?"":baseStudent.getFamilyAddress(),
					baseStudent.getFamilyTelNumber()==null?"":baseStudent.getFamilyTelNumber(),
					baseStudent.getPostcode()==null?"":baseStudent.getPostcode(),
					baseStudent.getSpecialty()==null?"":baseStudent.getSpecialty(),
					baseStudent.getDutyInHighSchool()==null?"":baseStudent.getDutyInHighSchool(),
					baseStudent.getAwardInHighSchool()==null?"":baseStudent.getAwardInHighSchool(),
					baseStudent.getIsHadTechnologyCompetitionAward()==null?"":baseStudent.getIsHadTechnologyCompetitionAward(),
					baseStudent.getFatherName()==null?"":baseStudent.getFatherName(),
					baseStudent.getFatherWorkUnit()==null?"":baseStudent.getFatherWorkUnit(),
					baseStudent.getFatherWorkUnitAddress()==null?"":baseStudent.getFatherWorkUnitAddress(),
					baseStudent.getFatherDuty()==null?"":baseStudent.getFatherDuty(),
					baseStudent.getFatherPostcode()==null?"":baseStudent.getFatherPostcode(),
					baseStudent.getFatherTelNumber()==null?"":baseStudent.getFatherTelNumber(),
					baseStudent.getMotherName()==null?"":baseStudent.getMotherName(),
					baseStudent.getMotherWorkUnit()==null?"":baseStudent.getMotherWorkUnit(),
					baseStudent.getMotherWorkUnitAddress()==null?"":baseStudent.getMotherWorkUnitAddress(),
					baseStudent.getMotherDuty()==null?"":baseStudent.getMotherDuty(),
					baseStudent.getMotherPostcode()==null?"":baseStudent.getMotherPostcode(),
					baseStudent.getMotherTelNumber()==null?"":baseStudent.getMotherTelNumber()};
			row.put(i+1, rowValues);
		}
		//调用工具类,获取文件数组
		byte[] body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_EXCEL_TEMPLATE_NAME);
		return body;
	}

	@Override
	public void studentDrop(DropParam dropParam) throws NoSuchUserException, PasswordNotMatchException {
		Teacher teacher = teacherDao.getPassword(dropParam.getTeacher().getTeacherId());
		if (teacher==null) {
			throw new NoSuchUserException("当前教师不存在");
		}
		if(!StringUtils.equals(teacher.getPassword(), dropParam.getTeacher().getPassword())) {
			throw new PasswordNotMatchException("密码不正确");
		}
		
		BaseStudent baseStudent = new BaseStudent();
		baseStudent.setStudentId(dropParam.getStudentId());
		baseStudent.setStudyCondition("退学");
		baseStudentDao.update(baseStudent);
		
	}

	@Override
	public BaseStudentCount count(BaseStudent baseStudent,HttpServletRequest httpRequest) {
		String teacherId = (String) httpRequest.getAttribute("teacherId");
		Teacher teacher = teacherDao.getInfo(teacherId);
		
		Integer currentStuNum = baseStudentDao.count(baseStudent, teacher.getGrade());
		baseStudent.setStudyCondition("休学");
		Integer suspendedStuNum = baseStudentDao.count(baseStudent, teacher.getGrade());
		baseStudent.setStudyCondition("退学");
		Integer dropoutStuNum = baseStudentDao.count(baseStudent, teacher.getGrade());
		
		baseStudent.setStudyCondition(null);
		baseStudent.setSex("男");
		Integer male = baseStudentDao.count(baseStudent, teacher.getGrade());
		baseStudent.setSex("女");
		Integer female = baseStudentDao.count(baseStudent, teacher.getGrade());
		
		BaseStudentCount baseStudentCount = new BaseStudentCount();
		baseStudentCount.setCurrentStuNum(currentStuNum-suspendedStuNum-dropoutStuNum);
		baseStudentCount.setSuspendedStuNum(suspendedStuNum);
		baseStudentCount.setDropoutStuNum(dropoutStuNum);
		baseStudentCount.setMale(male);
		baseStudentCount.setFemale(female);
		
		return baseStudentCount;
	}

	@Override
	public void uploadPhotos(MultipartFile[] photos,String teacherId, String password, HttpServletRequest httpRequest) throws IOException, FormatException, NoSuchUserException, PasswordNotMatchException {
		Teacher teacher = teacherDao.getPassword(teacherId);
		if (teacher==null) {
			throw new NoSuchUserException("当前教师不存在");
		}
		if(!StringUtils.equals(teacher.getPassword(), password)) {
			throw new PasswordNotMatchException("密码不正确");
		}
		
		for(MultipartFile photo:photos) {
			//将上传的图片保存到项目指定目录中
			ServletContext servletContext=httpRequest.getServletContext();
			String photoName = photo.getOriginalFilename();
			String filePath=MultipartFileUtil.storeMultipartFile(photo, servletContext.getRealPath(Path.STUDENT_PHOTO_PATH_UNDER_ARCHIVE), photoName.toLowerCase());
			//将保存的图片URL存入数据库
			//将存储路径转化为外部可以访问的URL
			int index=filePath.indexOf(servletContext.getContextPath().substring(1));
			filePath=filePath.substring(index);
			filePath=filePath.replaceAll("\\\\", "/");
			filePath=Path.HOST_PATH+"/"+filePath;
			
			String studentIdString = photoName.substring(0, photoName.indexOf("."));
			Long studentId = null;
			try {
				studentId = Long.parseLong(studentIdString);
			} catch (Exception e) {
				throw new FormatException("请以学号命名图片");
			}
			
			//将该URL存放到数据库中
			BaseStudent baseStudent = new BaseStudent();
			baseStudent.setStudentId(studentId);
			baseStudent.setPhotoUrl(filePath);
			baseStudentDao.update(baseStudent);
		}
		
	}

	@Override
	public List<BaseStudent> searchByIdAndName(IdNameParam idNameParam) {
		List<BaseStudent> baseStudents = baseStudentDao.searchByIdName(idNameParam.getStudentId(), idNameParam.getName());
		return baseStudents;
	}


}
