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
		//��ѯ����ѧ���б�
		List<BaseStudent> baseStudents=baseStudentDao.list();
		return baseStudents;
	}

	@Override
	public List<BaseStudent> search(BaseStudent baseStudent) {
		//��������ѧ���б�
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
	public List<Long> listIdByName(String name) {
		List<Long> studentIds=baseStudentDao.listStudentIdByName(name);
		if(studentIds==null||studentIds.size()==0) {
			throw new NoSuchStudentException("��ǰѧ����δ��¼��������Ϣ��");
		}
		return studentIds;
	}


	@Override
	public void update(BaseStudent baseStudent, Teacher teacher,MultipartFile multipartFile,HttpServletRequest httpServletRequest)throws IOException,FileTypeNotMatchException,PasswordNotMatchException {
		//��ѯָ����ʦ����
		String password=teacherDao.getPassword(teacher.getTeacherId());
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
	public void importBaseStudentsFromExcelFile(MultipartFile multipartFile)throws FileTypeNotMatchException, IOException,IllegalStateException {
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
		//���ϴ����ļ��õ�������
		XSSFWorkbook workbook=new XSSFWorkbook(multipartFile.getInputStream());
		//ֻ��ȡ��һ��sheet
		Sheet sheet=workbook.getSheetAt(0);
		//����ÿ��
		for (Row row : sheet) {
			try {
				//�Թ���ͷ
				if(row.getRowNum()==0) continue;
				//���ѧ��Ϊ�ջ��ʽ����,���׳�ѧ�Ÿ�ʽ���쳣
				Long studentId;
				try {
					studentId=Long.valueOf(POIUtil.getStringCellValue(row, 0));
				} catch (NumberFormatException e) {
					throw new StudentIdFormatException("�����ѧ�����ڲ���ѧ��Ϊ�ջ�ѧ�Ÿ�ʽ����,��������µ���");
				}finally {
					workbook.close();
				}
				//��������������ָ�ʽת���쳣��Ĭ��Ϊnull,������ַ�������Ĭ��Ϊ"��"
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
				//���;�������˷��ı���ʽ,����ת���������Ȼ���׳��쳣,��ʱ�����û�����Ԫ������Ϊ�ı�����
				throw new IllegalStateException("��ȷ����¼����Ϣǰ,�����е�Ԫ��ĸ�ʽ����Ϊ�ı�����");
			}
		}
		workbook.close();
		if(baseStudents.size()>0) {
			baseStudentDao.addBatch(baseStudents);
		}else {
			throw new NoDataToImportException("��ȷ�������Execl�������ݿ����ϴ�");
		}
		
	}
	


	@Override
	public byte[] exportBaseStudentsToExcelFile(List<Long> studentIds) throws NotChoseExportObjectException,IOException {
		//���û��ѡ�񵼳��������׳��쳣
		if(studentIds==null||studentIds.size()==0) {
			throw new NotChoseExportObjectException("��δѡ��Ҫ�����Ķ���,����ѡ����ٵ���");
		}
		//�����ݿ��л�ȡ��Ҫ�����Ļ���ѧ������
		List<BaseStudent> baseStudents=baseStudentDao.listByIds(studentIds);
		//��Ҫ�����Ļ���ѧ����Ϣ����Map��
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
		//���ù�����,��ȡ�ļ�����
		byte[] body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_EXCEL_TEMPLATE_NAME);
		return body;
	}
	
	
	
	
	

}
