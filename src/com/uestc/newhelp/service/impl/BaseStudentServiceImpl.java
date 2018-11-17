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
		//��ѯ����ѧ���б�
		List<BaseStudent> baseStudents=baseStudentDao.list();
		return baseStudents;
	}

	@Override
	public BaseStudentsWithPage search(BaseStudent baseStudent,String teacherId,Integer pageSize,Integer currentPage,Integer classSort) {
		//��������ѧ���б�
		
		if(classSort!=1&&classSort!=0) {
			classSort=0;
		}
		//��ȡ��Ӧ��ʦ�꼶Ȩ��
		Teacher teacher=teacherDao.getInfo(teacherId);
		//�����ʦ������ ��ֱ�ӷ��ؿ�
		if(teacher==null) {
			return null;
		}
		
		Integer recordNum=baseStudentDao.searchRecordNumByCondition(baseStudent,teacher.getGrade(),"�ڶ�","��ѧ");
		Page page=new Page(currentPage, pageSize, recordNum);
		List<BaseStudent> baseStudents=baseStudentDao.searchByCondition(baseStudent,teacher.getGrade(),page,classSort,"�ڶ�","��ѧ");
		BaseStudentsWithPage baseStudentsWithPage=new BaseStudentsWithPage(baseStudents, page);
		return baseStudentsWithPage;
	}
	
	@Override
	public BaseStudentsWithPage searchHistory(BaseStudent baseStudent,String teacherId,Integer pageSize,Integer currentPage,Integer classSort) {
		//��������ѧ���б�
		
		if(classSort!=1&&classSort!=0) {
			classSort=0;
		}
		//��ȡ��Ӧ��ʦ�꼶Ȩ��
		Teacher teacher=teacherDao.getInfo(teacherId);
		//�����ʦ������ ��ֱ�ӷ��ؿ�
		if(teacher==null) {
			return null;
		}
		
		Integer recordNum=baseStudentDao.searchRecordNumByCondition(baseStudent,teacher.getGrade(),"��ѧ","��ҵ");
		Page page=new Page(currentPage, pageSize, recordNum);
		List<BaseStudent> baseStudents=baseStudentDao.searchByCondition(baseStudent,teacher.getGrade(),page,classSort,"��ѧ","��ҵ");
		BaseStudentsWithPage baseStudentsWithPage=new BaseStudentsWithPage(baseStudents, page);
		return baseStudentsWithPage;
	}
	
	@Override
	public List<BaseStudent> searchWithOutPage(BaseStudent baseStudent,String teacherId) {
		//��������ѧ���б�
		
		//��ȡ��Ӧ��ʦ�꼶Ȩ��
		Teacher teacher=teacherDao.getInfo(teacherId);
		//�����ʦ������ ��ֱ�ӷ��ؿ�
		if(teacher==null) {
			return null;
		}
		//���û��ָ��ѧ����ѧҵ״̬ ��Ĭ��ֻ��ѯ�ڶ���ѧ��
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
		//�жϸ�ѧ���Ƿ��Ѿ�����
		String teacherId=archiveStudentDao.check(studentId);
		if(teacherId==null) {
			BaseStudent baseStudent=baseStudentDao.getArchiveInfo(studentId);
			return baseStudent;
		}else {
			Teacher teacher=teacherDao.getInfo(teacherId);
			throw new ArchiveStudentHadExistException("��ѧ���ѱ�"+teacher.getName()+"��ʦ���");
		}
		
	}
	
	@Override
	public List<Long> listIdByName(String name) throws NoSuchStudentException {
		List<Long> studentIds=baseStudentDao.listStudentIdByName(name);
		if(studentIds==null||studentIds.size()==0) {
			throw new NoSuchStudentException("��ǰѧ����δ��¼��������Ϣ��");
		}
		return studentIds;
	}


	@Override
	public void update(BaseStudent baseStudent, Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest)throws IOException,FileTypeNotMatchException,PasswordNotMatchException {
		//��ѯָ����ʦ����
		String password=teacherDao.getPassword(teacher.getTeacherId()).getPassword();
		//��֤����
		if(password.equals(teacher.getPassword())) {
			if(multipartFile!=null) {
				//��ȡͼƬ��ʽ
				String fileType=MultipartFileUtil.getType(multipartFile);
				//�ж�ͼƬ��ʽ�Ƿ�ƥ��
				if(".jpg".equals(fileType)){
						//���ϴ���ͼƬ���浽��Ŀָ��Ŀ¼��
						ServletContext servletContext=httpServletRequest.getServletContext();
						String filePath=MultipartFileUtil.storeMultipartFile(multipartFile, servletContext.getRealPath(Path.STUDENT_PHOTO_PATH_UNDER_ARCHIVE), String.valueOf(baseStudent.getStudentId())+".jpg");
						//�������ͼƬURL�������ݿ�
						//���洢·��ת��Ϊ�ⲿ���Է��ʵ�URL
						int index=filePath.indexOf(servletContext.getContextPath().substring(1));
						filePath=filePath.substring(index);
						filePath=filePath.replaceAll("\\\\", "/");
						filePath=Path.HOST_PATH+"/"+filePath;
						//����URL��ŵ����ݿ���
						baseStudent.setPhotoUrl(filePath);
						baseStudentDao.update(baseStudent);
				}else {
					//���ͼƬ��ʽ����ȷ���׳���ʽ�����쳣
					throw new FileTypeNotMatchException("�뱣֤ͼƬ��ʽΪjpg");
				}
			}else {
				baseStudentDao.update(baseStudent);
			}
		}else {
			//�����֤����������׳���������쳣
			throw new PasswordNotMatchException("�������");
		}
	}

	@Override
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile,Teacher teacher,ExposeSetting exposeSetting)throws FileTypeNotMatchException, IOException,IllegalStateException,NoAuthorityException, StudentIdFormatException, FormatException, NoDataToImportException {
		if(teacher==null||teacher.getTeacherId()==null) {
			throw new NoAuthorityException("����Ȩ�޵�������,����ϵ���й���ԱȨ�޵��û�����");
		}
		Teacher teacher1=teacherDao.getPassword(teacher.getTeacherId());
		if(!(teacher1.getPassword().equals(teacher.getPassword()))) {
			throw new NoAuthorityException("��֤ʧ�� �������� �޷����� ");
		}
		
		//��ȡ�ļ�������
		String fileType=MultipartFileUtil.getType(multipartFile);
		//�������Ŀ���ļ����׳��ļ����Ͳ�ƥ���쳣
		if(!".xlsx".equals(fileType)) {
			throw new FileTypeNotMatchException("�뱣֤�ϴ����ļ���ʽΪ.xlsx");
		}

		//����ʢ�Ž�������ѧ������
		List<BaseStudent> baseStudents=new ArrayList<>();
		//���ڸ�ʽ��,�������ڵĸ�ʽ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/M/dd");
		SimpleDateFormat sdf3=new SimpleDateFormat("yyyy/M/d");
		//���ϴ����ļ��õ�������
		XSSFWorkbook workbook=new XSSFWorkbook(multipartFile.getInputStream());
		//ֻ��ȡ��һ��sheet
		Sheet sheet=workbook.getSheetAt(0);
		int rowIndex=0;
		int colIndex=0;
		//����ÿ�� ���ݵ�������ѡȡ������Ϣ
			for (Row row : sheet) {
				colIndex=0;
				rowIndex++;
				try {
					//�Թ���ͷ
					if(row.getRowNum()==0) continue;
					//���ѧ��Ϊ�ջ��ʽ����,���׳�ѧ�Ÿ�ʽ���쳣
					Long studentId;
					try {
						colIndex++;
						String studentIdString=POIUtil.getStringCellValue(row, 0);
						if(!studentIdString.matches(Regex.STUDENT_ID)) {
							throw new FormatException("�����ѧ��ѧ��Ϊ�ջ�ѧ�Ÿ�ʽ����,��������µ���,��������"+rowIndex+"��,"+"ѧ����");
						}
						studentId=Long.valueOf(studentIdString);
						
					} catch (NumberFormatException e) {
						throw new StudentIdFormatException("�����ѧ��ѧ��Ϊ�ջ�ѧ�Ÿ�ʽ����,��������µ���,��������"+rowIndex+"��,"+"ѧ����");
					}finally {
						workbook.close();
					}
					//��������������ָ�ʽת���쳣��Ĭ��Ϊnull,������ַ�������Ĭ��Ϊ"��"
					String name = null;
					colIndex++;
					if(exposeSetting.getExposeName()==(byte)1) {
						name=POIUtil.getStringCellValue(row, 1);
						if(!name.matches(Regex.NAME)) {
							throw new FormatException("�����ѧ��������ʽ����,��������µ���,��������"+rowIndex+"��,"+"������");
						}
					}
					
					Short grade = null;
					colIndex++;
					if(exposeSetting.getExposeGrade()==(byte)1) {
						try {
							String gradeString=POIUtil.getStringCellValue(row, 2);
							if(!gradeString.matches(Regex.GRADE)) {
								throw new FormatException("�����ѧ���꼶��ʽ����,��������µ���,��������"+rowIndex+"��,"+"�꼶��");
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
								throw new FormatException("�����ѧ���༶��ʽ����,��������µ���,��������"+rowIndex+"��,"+"�༶��");
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
							throw new FormatException("�����ѧ���Ա��ʽ����,��������µ���,��������"+rowIndex+"��,"+"�Ա���");
						}
					}
					String duty = null;
					colIndex++;
					if(exposeSetting.getExposeDuty()==(byte)1) {
						duty=POIUtil.getStringCellValue(row, 5);
						if(!duty.matches(Regex.DUTY)) {
							throw new FormatException("�����ѧ��ְ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"ְ����");
						}
					}
					
					String dormitory = null;
					colIndex++;
					if(exposeSetting.getExposeDormitory()==(byte)1) {
						dormitory=POIUtil.getStringCellValue(row, 6);
						if(!dormitory.matches(Regex.DORMITORY)) {
							throw new FormatException("�����ѧ�������ʽ����,��������µ���,��������"+rowIndex+"��,"+"������");
						}
					}
					String contactWay = null;
					colIndex++;
					if(exposeSetting.getExposeContactWay()==(byte)1) {
						contactWay=POIUtil.getStringCellValue(row, 7);
						if(!contactWay.matches(Regex.CONTACT_WAY)) {
							throw new FormatException("�����ѧ����ϵ��ʽ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"��ϵ��ʽ��");
						}
					}
					String idCardNumber = null;
					colIndex++;
					if(exposeSetting.getExposeIdCardNumber()==(byte)1) {
						idCardNumber=POIUtil.getStringCellValue(row, 8);
						if(!idCardNumber.matches(Regex.ID_CARD_NUMBER)) {
							throw new FormatException("�����ѧ�����֤�Ÿ�ʽ����,��������µ���,��������"+rowIndex+"��,"+"���֤����");
						}
					}
					
					Long qqNumber = null;
					colIndex++;
					if(exposeSetting.getExposeQqNumber()==(byte)1) {
						try {
							String qqNumberString=POIUtil.getStringCellValue(row, 9);
							if(!qqNumberString.matches(Regex.QQ_NUMBER)) {
								throw new FormatException("�����ѧ��qq�Ÿ�ʽ����,��������µ���,��������"+rowIndex+"��,"+"qq����");
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
							throw new FormatException("�����ѧ�������ʽ����,��������µ���,��������"+rowIndex+"��,"+"������");
						}
					}
					
					Date birthday = null;
					colIndex++;
					if(exposeSetting.getExposeBirthday()==(byte)1) {
						String birthdayString=POIUtil.getStringCellValue(row, 11);
						if(!birthdayString.matches(Regex.BIRTHDAY)) {
							throw new FormatException("�����ѧ�����ո�ʽ����,��������µ���,��������"+rowIndex+"��,"+"������");
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
								throw new FormatException("�����ѧ����߸�ʽ����,��������µ���,��������"+rowIndex+"��,"+"�����");
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
							throw new FormatException("�����ѧ��רҵ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"רҵ��");
						}
					}
					
					String politicalStatus = null;
					colIndex++;
					if(exposeSetting.getExposePoliticalStatus()==(byte)1) {
						politicalStatus=POIUtil.getStringCellValue(row, 14);
						if(!politicalStatus.matches(Regex.POLITICAL_STATUS)) {
							throw new FormatException("�����ѧ��������ò��ʽ����,��������µ���,��������"+rowIndex+"��,"+"������ò��");
						}
					}
					String ethnicGroup = null;
					colIndex++;
					if(exposeSetting.getExposeEthnicGroup()==(byte)1) {
						ethnicGroup=POIUtil.getStringCellValue(row, 15);
						if(!ethnicGroup.matches(Regex.ETHNIC_GROUP)) {
							throw new FormatException("�����ѧ�������ʽ����,��������µ���,��������"+rowIndex+"��,"+"������");
						}
					}
					String birthOrigin = null;
					colIndex++;
					if(exposeSetting.getExposeBirthOrigin()==(byte)1) {
						birthOrigin=POIUtil.getStringCellValue(row, 16);
						if(!birthOrigin.matches(Regex.BIRTH_ORIGIN)) {
							throw new FormatException("�����ѧ����Դ�ظ�ʽ����,��������µ���,��������"+rowIndex+"��,"+"��Դ����");
						}
					}
					
					String collegeEntranceExamScore = null;
					colIndex++;
					if(exposeSetting.getExposeCollegeEntranceExamScore()==(byte)1) {
						collegeEntranceExamScore=POIUtil.getStringCellValue(row, 17);
						if(!collegeEntranceExamScore.matches(Regex.COLLEGE_ENTRANCE_EXAM_SCORE)) {
							throw new FormatException("�����ѧ���߿��ɼ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"�߿��ɼ���");
						}
					}
					
					String collegeEntranceExamEnglishScore = null;
					colIndex++;
					if(exposeSetting.getExposeCollegeEntranceExamEnglishScore()==(byte)1) {
						collegeEntranceExamEnglishScore=POIUtil.getStringCellValue(row, 18);
						if(!collegeEntranceExamEnglishScore.matches(Regex.COLLEGE_ENTRANCE_EXAM_ENGLISH_SCORE)) {
							throw new FormatException("�����ѧ���߿�Ӣ��ɼ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"�߿�Ӣ��ɼ���");
						}
					}
					
					Byte entranceExamEnglishScore = null;
					colIndex++;
					if(exposeSetting.getExposeEntranceExamEnglishScore()==(byte)1) {
						try {
							String entranceExamEnglishScoreString=POIUtil.getStringCellValue(row, 19);
							if(!entranceExamEnglishScoreString.matches(Regex.ENTRANCE_EXAM_ENGLISH_SCORE)) {
								throw new FormatException("�����ѧ��Ӣ����ѧ���Գɼ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"Ӣ����ѧ���Գɼ���");
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
							throw new FormatException("�����ѧ���������ڻ�վ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"�������ڻ�վ��");
						}
					}
					
					String province = null;
					colIndex++;
					if(exposeSetting.getExposeProvince()==(byte)1) {
						province=POIUtil.getStringCellValue(row, 21);
						if(!province.matches(Regex.PROVINCE)) {
							throw new FormatException("�����ѧ��ʡ�ݸ�ʽ����,��������µ���,��������"+rowIndex+"��,"+"ʡ����");
						}
					}
					
					String city = null;
					colIndex++;
					if(exposeSetting.getExposeCity()==(byte)1) {
						city=POIUtil.getStringCellValue(row, 22);
						if(!city.matches(Regex.CITY)) {
							throw new FormatException("�����ѧ�����ڳ��и�ʽ����,��������µ���,��������"+rowIndex+"��,"+"���ڳ�����");
						}
					}
					
					String familyAddress = null;
					colIndex++;
					if(exposeSetting.getExposeFamilyAddress()==(byte)1) {
						familyAddress=POIUtil.getStringCellValue(row, 23);
						if(!familyAddress.matches(Regex.FAMILY_ADDRESS)) {
							throw new FormatException("�����ѧ����ͥ��ϸ��ַ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"��ͥ��ϸ��ַ��");
						}
					}
					
					String familyTelNumber = null;
					colIndex++;
					if(exposeSetting.getExposeFamilyTelNumber()==(byte)1) {
						familyTelNumber=POIUtil.getStringCellValue(row, 24);
						if(!familyTelNumber.matches(Regex.FAMILY_TEL_NUMBER)) {
							throw new FormatException("�����ѧ����ͥ�绰��ʽ����,��������µ���,��������"+rowIndex+"��,"+"��ͥ�绰��");
						}
					}
					String postcode = null;
					colIndex++;
					if(exposeSetting.getExposePostcode()==(byte)1) {
						postcode=POIUtil.getStringCellValue(row, 25);
						if(!postcode.matches(Regex.POSTCODE)) {
							throw new FormatException("�����ѧ�����������ʽ����,��������µ���,��������"+rowIndex+"��,"+"����������");
						}
					}
					
					String specialty = null;
					colIndex++;
					if(exposeSetting.getExposeSpecialty()==(byte)1) {
						specialty=POIUtil.getStringCellValue(row, 26);
						if(!specialty.matches(Regex.SPECIALTY)) {
							throw new FormatException("�����ѧ���س���ʽ����,��������µ���,��������"+rowIndex+"��,"+"�س���");
						}
					}
					String dutyInHighSchool = null;
					colIndex++;
					if(exposeSetting.getExposeDutyInHighSchool()==(byte)1) {
						dutyInHighSchool=POIUtil.getStringCellValue(row, 27);
						if(!dutyInHighSchool.matches(Regex.DUTY_IN_HIGH_SCHOOL)) {
							throw new FormatException("�����ѧ����������ְ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"��������ְ����");
						}
					}
					String awardInHighSchool = null;
					colIndex++;
					if(exposeSetting.getExposeAwardInHighSchool()==(byte)1) {
						awardInHighSchool=POIUtil.getStringCellValue(row, 28);
						if(!awardInHighSchool.matches(Regex.AWARD_IN_HIGH_SCHOOL)) {
							throw new FormatException("�����ѧ��������������ʽ����,��������µ���,��������"+rowIndex+"��,"+"������������");
						}
					}
					
					String isHadTechnologyCompetitionAward = null;
					colIndex++;
					if(exposeSetting.getExposeIsHadTechnologyCompetitionAward()==(byte)1) {
						isHadTechnologyCompetitionAward=POIUtil.getStringCellValue(row, 29);
						if(!awardInHighSchool.matches(Regex.AWARD_IN_HIGH_SCHOOL)) {
							throw new FormatException("�����ѧ���Ƿ��пƼ�������񽱸�ʽ����,��������µ���,��������"+rowIndex+"��,"+"�Ƿ��пƼ����������");
						}
					}
					
					String fatherName = null;
					colIndex++;
					if(exposeSetting.getExposeFatherName()==(byte)1) {
						fatherName=POIUtil.getStringCellValue(row, 30);
						if(!fatherName.matches(Regex.NAME)) {
							throw new FormatException("�����ѧ������������ʽ����,��������µ���,��������"+rowIndex+"��,"+"����������");
						}
					}
					String fatherWorkUnit = null;
					colIndex++;
					if(exposeSetting.getExposeFatherWorkUnit()==(byte)1) {
						fatherWorkUnit=POIUtil.getStringCellValue(row, 31);
						if(!fatherWorkUnit.matches(Regex.WORK_UNIT)) {
							throw new FormatException("�����ѧ�����׹�����λ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"���׹�����λ��");
						}
					}
					String fatherWorkUnitAddress = null;
					colIndex++;
					if(exposeSetting.getExposeFatherWorkUnitAddress()==(byte)1) {
						fatherWorkUnitAddress=POIUtil.getStringCellValue(row, 32);
						if(!fatherWorkUnitAddress.matches(Regex.WORK_UNIT_DETAIL)) {
							throw new FormatException("�����ѧ�����׹�����λ��ϸ��ַ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"���׹�����λ��ϸ��ַ��");
						}
					}
					String fatherDuty = null;
					colIndex++;
					if(exposeSetting.getExposeFatherDuty()==(byte)1) {
						fatherDuty=POIUtil.getStringCellValue(row, 33);
						if(!fatherDuty.matches(Regex.DUTY)) {
							throw new FormatException("�����ѧ������ְ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"����ְ����");
						}
					}
					
					String fatherPostcode=null;
					colIndex++;
					if(exposeSetting.getExposeFatherPostcode()==(byte)1) {
						fatherPostcode=POIUtil.getStringCellValue(row, 34);
						if(!fatherPostcode.matches(Regex.POSTCODE)) {
							throw new FormatException("�����ѧ�������ʱ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"�����ʱ���");
						}
					}
					
					String fatherTelNumber = null;
					colIndex++;
					if(exposeSetting.getExposeFatherTelNumber()==(byte)1) {
						fatherTelNumber=POIUtil.getStringCellValue(row, 35);
						if(!fatherTelNumber.matches(Regex.CONTACT_WAY)) {
							throw new FormatException("�����ѧ�����׵绰��ʽ����,��������µ���,��������"+rowIndex+"��,"+"���׵绰��");
						}
					}
					
					String motherName = null;
					colIndex++;
					if(exposeSetting.getExposeMotherName()==(byte)1) {
						motherName=POIUtil.getStringCellValue(row, 36);
						if(!motherName.matches(Regex.NAME)) {
							throw new FormatException("�����ѧ��ĸ��������ʽ����,��������µ���,��������"+rowIndex+"��,"+"ĸ��������");
						}
					}
					
					String motherWorkUnit = null;
					colIndex++;
					if(exposeSetting.getExposeMotherWorkUnit()==(byte)1) {
						motherWorkUnit=POIUtil.getStringCellValue(row, 37);
						if(!motherWorkUnit.matches(Regex.WORK_UNIT)) {
							throw new FormatException("�����ѧ��ĸ�׹�����λ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"ĸ�׹�����λ��");
						}
					}
					
					String motherWorkUnitAddress = null;
					colIndex++;
					if(exposeSetting.getExposeMotherWorkUnitAddress()==(byte)1) {
						motherWorkUnitAddress=POIUtil.getStringCellValue(row, 38);
						if(!motherWorkUnitAddress.matches(Regex.WORK_UNIT_DETAIL)) {
							throw new FormatException("�����ѧ��ĸ�׹�����λ��ϸ��ַ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"ĸ�׹�����λ��ϸ��ַ��");
						}
					}
					
					String motherDuty = null;
					colIndex++;
					if(exposeSetting.getExposeMotherDuty()==(byte)1) {
						motherDuty=POIUtil.getStringCellValue(row, 39);
						if(!motherDuty.matches(Regex.DUTY)) {
							throw new FormatException("�����ѧ��ĸ��ְ���ʽ����,��������µ���,��������"+rowIndex+"��,"+"ĸ��ְ����");
						}
					}
					
					String motherPostcode=null;
					colIndex++;
					if(exposeSetting.getExposeMotherPostcode()==(byte)1) {
						motherPostcode=POIUtil.getStringCellValue(row, 40);
						if(!motherPostcode.matches(Regex.POSTCODE)) {
							throw new FormatException("�����ѧ��ĸ���ʱ��ʽ����,��������µ���,��������"+rowIndex+"��,"+"ĸ���ʱ���");
						}
					}
					
					String motherTelNumber = null;
					colIndex++;
					if(exposeSetting.getExposeMotherTelNumber()==(byte)1) {
						motherTelNumber=POIUtil.getStringCellValue(row, 41);
						if(!motherTelNumber.matches(Regex.CONTACT_WAY)) {
							throw new FormatException("�����ѧ��ĸ�׵绰��ʽ����,��������µ���,��������"+rowIndex+"��,"+"ĸ�׵绰��");
						}
					}
					
					BaseStudent baseStudent=new BaseStudent(studentId, name, grade, studentClass, sex, duty, dormitory, contactWay, idCardNumber, qqNumber, email, birthday, height, major, politicalStatus, ethnicGroup, birthOrigin, collegeEntranceExamScore, collegeEntranceExamEnglishScore, entranceExamEnglishScore, hometownRailwayStation, province, city, familyAddress, familyTelNumber, postcode, specialty, dutyInHighSchool, awardInHighSchool,isHadTechnologyCompetitionAward, fatherName, fatherWorkUnit, fatherWorkUnitAddress, fatherDuty, fatherPostcode, fatherTelNumber, motherName, motherWorkUnit, motherWorkUnitAddress, motherDuty, motherPostcode, motherTelNumber);
					
					baseStudents.add(baseStudent);
				} catch (IllegalStateException e) {
					//���;�������˷��ı���ʽ,����ת���������Ȼ���׳��쳣,��ʱ�����û�����Ԫ������Ϊ�ı�����
					throw new IllegalStateException("��ȷ����¼����Ϣǰ,�����е�Ԫ��ĸ�ʽ����Ϊ�ı�����,��������"+rowIndex+"��,"+colIndex+"��");
				}
			}
			workbook.close();
			if(baseStudents.size()>0) {
				baseStudentDao.addBatch(baseStudents,exposeSetting);
			}else {
				throw new NoDataToImportException("��ȷ�������Execl�������ݿ����ϴ�");
			}
		
	}
	


	@Override
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds,String teacherId,ExposeSetting exposeSetting) throws NotChoseExportObjectException,IOException, NoSettingException, NoAuthorityException {
		//���û��ѡ�񵼳��������׳��쳣
		if(studentIds==null) {
			throw new NotChoseExportObjectException("��δѡ��Ҫ�����Ķ���,����ѡ����ٵ���");
		}
		//��ȡ��ʦ���꼶Ȩ��
		Teacher teacher=teacherDao.getInfo(teacherId);
		if(teacher==null) {
			throw new NoAuthorityException("�����˺Ų�����,�������Ա��ȡ�˺�");
		}
		//�����ݿ��л�ȡ��Ҫ������ѧ�� ���studentIds�ĳ���Ϊ0��ôĬ�ϵ������и��꼶ѧ��������
		//ͬʱ���ǽ�ʦ��Ȩ�� ����꼶Ȩ����0����1�򵼳����� ���򵼳����꼶ѧ��
		//���ݵ������ò�ѯѧ����Ϣ
		List<BaseStudent> baseStudents=baseStudentDao.listByIds(studentIds,exposeSetting,teacher.getGrade());
		//��Ҫ�����Ļ���ѧ����Ϣ����Map��
		Map<Integer, Object[]> row=new HashMap<>();
		for(int i=0;i<baseStudents.size();i++) {
			BaseStudent baseStudent=baseStudents.get(i);
			//��ÿ��Ϊ�յ�������Ϊ�մ�
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
		//���ù�����,��ȡ�ļ�����
		byte[] body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_EXCEL_TEMPLATE_NAME);
		return body;
	}

	@Override
	public void studentDrop(DropParam dropParam) throws NoSuchUserException, PasswordNotMatchException {
		Teacher teacher = teacherDao.getPassword(dropParam.getTeacher().getTeacherId());
		if (teacher==null) {
			throw new NoSuchUserException("��ǰ��ʦ������");
		}
		if(!StringUtils.equals(teacher.getPassword(), dropParam.getTeacher().getPassword())) {
			throw new PasswordNotMatchException("���벻��ȷ");
		}
		
		BaseStudent baseStudent = new BaseStudent();
		baseStudent.setStudentId(dropParam.getStudentId());
		baseStudent.setStudyCondition("��ѧ");
		baseStudentDao.update(baseStudent);
		
	}

	@Override
	public BaseStudentCount count(BaseStudent baseStudent,HttpServletRequest httpRequest) {
		String teacherId = (String) httpRequest.getAttribute("teacherId");
		Teacher teacher = teacherDao.getInfo(teacherId);
		
		Integer currentStuNum = baseStudentDao.count(baseStudent, teacher.getGrade());
		baseStudent.setStudyCondition("��ѧ");
		Integer suspendedStuNum = baseStudentDao.count(baseStudent, teacher.getGrade());
		baseStudent.setStudyCondition("��ѧ");
		Integer dropoutStuNum = baseStudentDao.count(baseStudent, teacher.getGrade());
		
		baseStudent.setStudyCondition(null);
		baseStudent.setSex("��");
		Integer male = baseStudentDao.count(baseStudent, teacher.getGrade());
		baseStudent.setSex("Ů");
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
			throw new NoSuchUserException("��ǰ��ʦ������");
		}
		if(!StringUtils.equals(teacher.getPassword(), password)) {
			throw new PasswordNotMatchException("���벻��ȷ");
		}
		
		for(MultipartFile photo:photos) {
			//���ϴ���ͼƬ���浽��Ŀָ��Ŀ¼��
			ServletContext servletContext=httpRequest.getServletContext();
			String photoName = photo.getOriginalFilename();
			String filePath=MultipartFileUtil.storeMultipartFile(photo, servletContext.getRealPath(Path.STUDENT_PHOTO_PATH_UNDER_ARCHIVE), photoName.toLowerCase());
			//�������ͼƬURL�������ݿ�
			//���洢·��ת��Ϊ�ⲿ���Է��ʵ�URL
			int index=filePath.indexOf(servletContext.getContextPath().substring(1));
			filePath=filePath.substring(index);
			filePath=filePath.replaceAll("\\\\", "/");
			filePath=Path.HOST_PATH+"/"+filePath;
			
			String studentIdString = photoName.substring(0, photoName.indexOf("."));
			Long studentId = null;
			try {
				studentId = Long.parseLong(studentIdString);
			} catch (Exception e) {
				throw new FormatException("����ѧ������ͼƬ");
			}
			
			//����URL��ŵ����ݿ���
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
