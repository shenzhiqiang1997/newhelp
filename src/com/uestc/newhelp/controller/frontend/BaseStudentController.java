package com.uestc.newhelp.controller.frontend;


import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.BaseStudentsWithPage;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.dto.StudentIdsParam;
import com.uestc.newhelp.dto.UpdateBaseStudentParam;
import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.ExposeSetting;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.FormatException;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoSettingException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.PasswordNotMatchException;
import com.uestc.newhelp.exception.StudentIdFormatException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.BaseStudentService;
import com.uestc.newhelp.util.FileUtil;

@RestController
@RequestMapping("/api")
public class BaseStudentController {
	@Autowired
	private BaseStudentService baseStudentService;
	
	@GetMapping("/baseStudents")
	@ResponseBody
	public Result<List<BaseStudent>> list(){
		try {
			List<BaseStudent> baseStudents=baseStudentService.list();
			return new Result<List<BaseStudent>>(true, baseStudents);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
	@PostMapping("/baseStudents/{teacherId}/{pageSize}/{currentPage}/{classSort}")
	@ResponseBody
	public Result<BaseStudentsWithPage> search(
			@PathVariable("teacherId")String teacherId,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("currentPage") Integer currentPage,
			@PathVariable("classSort") Integer classSort,
			@RequestBody BaseStudent baseStudent){
		try {
			BaseStudentsWithPage baseStudentsWithPage=baseStudentService.search(baseStudent,teacherId,pageSize,currentPage,classSort);
			return new Result<BaseStudentsWithPage>(true, baseStudentsWithPage);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.SEARCH_FAILURE);
		}
	}
	
	@GetMapping("/baseStudent/all/{studentId}")
	@ResponseBody
	public Result<BaseStudent> getAllInfo(@PathVariable Long studentId){
		try {
			BaseStudent baseStudent=baseStudentService.getAllInfo(studentId);
			return new Result<BaseStudent>(true, baseStudent);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
	@GetMapping("/baseStudent/personal/{studentId}")
	@ResponseBody
	public Result<BaseStudent> getPersonalInfo(@PathVariable Long studentId){
		try {
			BaseStudent baseStudent=baseStudentService.getPersonalInfo(studentId);
			return new Result<BaseStudent>(true, baseStudent);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@GetMapping("/baseStudent/family/{studentId}")
	@ResponseBody
	public Result<BaseStudent> getFamilyInfo(@PathVariable Long studentId){
		try {
			BaseStudent baseStudent=baseStudentService.getFamilyInfo(studentId);
			return new Result<BaseStudent>(true, baseStudent);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@GetMapping("/baseStudent/archive/{studentId}")
	@ResponseBody
	public Result<BaseStudent> getArchiveInfo(@PathVariable Long studentId){
		try {
			BaseStudent baseStudent=baseStudentService.getArchiveInfo(studentId);
			return new Result<BaseStudent>(true, baseStudent);
		}catch (ArchiveStudentHadExistException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@GetMapping("/baseStudent/studentIds/{name}")
	@ResponseBody
	public Result<List<Long>> listIdByName(@PathVariable String name){
		try {
			List<Long> studentIds=baseStudentService.listIdByName(name);
			return new Result<>(true, studentIds);
		} catch (NoSuchStudentException e) {
			e.printStackTrace();
			return new Result<>(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
	@PostMapping(value="/baseStudent")
	@ResponseBody
	public Result<BaseStudent> update(String json,
			@RequestParam(value="photo",required=false) MultipartFile multipartFile,HttpServletRequest httpRequest){
		try {
			ObjectMapper mapper=new ObjectMapper();
			UpdateBaseStudentParam param=mapper.readValue(json, UpdateBaseStudentParam.class);
			baseStudentService.update(param.getBaseStudent(), param.getTeacher(), multipartFile,httpRequest);
			return new Result<>(true,Message.UPDATE_SUCCESS);
		} catch (PasswordNotMatchException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (FileTypeNotMatchException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.UPDATE_FAILURE);
		}
		
	}
	
	@GetMapping("/download/baseStudentTemplate")
	@ResponseBody
	public ResponseEntity<?> downloadTemplate(){
		byte[] body;
		try {
			body = FileUtil.getBytes(Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_EXCEL_TEMPLATE_NAME);
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", FileName.BASE_STUDENT_EXCEL_TEMPLATE_NAME, Charset.forName("UTF-8"));
			ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
			return responseEntity;
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(Message.DOWNLOAD_FAILURE,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/import/baseStudent")
	@ResponseBody
	public Result<BaseStudent> importBaseStudents(Teacher teacher,String settings,@RequestParam("file")MultipartFile multipartFile){
		try {
			ObjectMapper mapper=new ObjectMapper();
			//从绑定参数中得到导入设置
			ExposeSetting exposeSetting=mapper.readValue(settings, ExposeSetting.class);
			baseStudentService.importBaseStudentsFromExcelFile(multipartFile,teacher,exposeSetting);
			return new Result<BaseStudent>(true, Message.IMPORT_SUCCESS);
		} catch (FileTypeNotMatchException e) {
			e.printStackTrace();
			return new Result<>(false, e.getMessage());
		} catch (StudentIdFormatException e) {
			return new Result<>(false, e.getMessage());
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch(NoAuthorityException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (FormatException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.IMPORT_FAILURE);
		}
	}
	
	@PostMapping("/export/baseStudent")
	@ResponseBody
	public ResponseEntity<?> exportBaseStudents(@RequestParam("json")String json,@RequestParam("settings")String settings){
		try {
			ObjectMapper mapper=new ObjectMapper();
			//从绑定参数中得到导出的学生id和教师id
			StudentIdsParam studentIdsParam=mapper.readValue(json,StudentIdsParam.class);
			//从绑定参数中得到导出设置
			ExposeSetting exposeSetting=mapper.readValue(settings, ExposeSetting.class);
			
			byte[] body=baseStudentService.exportBaseStudentsToExcelFile(studentIdsParam.getStudentIds(),studentIdsParam.getTeacherId(),exposeSetting);
			
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			headers.setContentDispositionFormData("attachment", FileName.BASE_STUDENT_LIST+"-"+sdf.format(new Date())+".xlsx");
			ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
			return responseEntity;
		} catch (NotChoseExportObjectException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(Message.EXPORT_FAILURE,HttpStatus.BAD_REQUEST);
		} catch (NoSettingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (NoAuthorityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
