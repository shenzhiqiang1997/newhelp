package com.uestc.newhelp.controller.frontend;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.service.ArchiveStudentService;

/**
 * 与学生基本信息有关的控制器
 *
 */
@RestController
@RequestMapping("/api")

public class ArchiveStudentController {
	@Autowired
	private ArchiveStudentService archiveStudentService;
	
	@Log("前台查看帮扶学生列表")
	@GetMapping("/archiveStudents/{teacherId}")
	@ResponseBody
	public Result<List<ArchiveStudent>> list(@PathVariable String teacherId){
		try {
			List<ArchiveStudent> archiveStudents=archiveStudentService.list(teacherId);
			return new Result<>(true,archiveStudents);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@Log("前台搜索帮扶学生列表")
	@PostMapping("/archiveStudents")
	@ResponseBody
	public Result<List<ArchiveStudent>> search(@RequestBody ArchiveStudent archiveStudent){
		try {
			List<ArchiveStudent> archiveStudents=archiveStudentService.search(archiveStudent);
			return new Result<>(true,archiveStudents);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.SEARCH_FAILURE);
		}
	}
	
	@Log("前台新增帮扶学生及帮扶档案")
	@PostMapping("/archiveStudent")
	@ResponseBody
	public Result<ArchiveStudent> add(@RequestBody ArchiveStudent archiveStudent){
		try {
			archiveStudentService.add(archiveStudent);
			return new Result<>(true,Message.ADD_SUCCESS);
		} catch(NotPointOutStudentIdException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (NoSuchStudentException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch(ArchiveStudentHadExistException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.ADD_FAILURE);
		}
	}
	
	@Log("前台删除帮扶学生及帮扶档案")
	@DeleteMapping("/archiveStudent")
	@ResponseBody
	public Result<ArchiveStudent> delete(@RequestBody ArchiveStudent archiveStudent){
		try {
			archiveStudentService.delete(archiveStudent);
			return new Result<>(true,Message.DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.DELETE_FAILURE);
		}
		
	}
	
	@Log("前台更新帮扶学生档案")
	@PutMapping("/archiveStudent")
	@ResponseBody
	public Result<ArchiveStudent> update(@RequestBody ArchiveStudent archiveStudent){
		try {
			archiveStudentService.update(archiveStudent);
			return new Result<>(true,Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.UPDATE_FAILURE);
		}
	}
	
	@Log("前台查看帮扶学生档案")
	@GetMapping("/archiveStudent/{studentId}")
	@ResponseBody
	public Result<ArchiveStudent> get(@PathVariable Long studentId){
		try {
			ArchiveStudent archiveStudent=archiveStudentService.get(studentId);
			return new Result<>(true,archiveStudent);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@Log("前台导出帮扶学生档案word文件")
	@PostMapping("/export/archive/{studentId}")
	@ResponseBody
	public ResponseEntity<?> exportArchive(@PathVariable Long studentId/*,@RequestParam("token")String token*/){
		try {
			byte[] body=archiveStudentService.exportArchiveToWordFile(studentId);
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			headers.setContentDispositionFormData("attachment", FileName.ARCHIVE_BOOK+"-"+studentId+"-"+sdf.format(new Date())+".doc",Charset.forName("UTF-8"));
			ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
			return responseEntity;
		} catch (NotChoseExportObjectException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NoSuchStudentException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
