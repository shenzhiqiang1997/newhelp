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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.RecordIdsParam;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.NoDataToImportException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutRecordNameException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;
import com.uestc.newhelp.exception.RecordTypeNotMatchException;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.RecordService;
import com.uestc.newhelp.util.FileUtil;

@RestController
@RequestMapping("/api")
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	@Log("前台查看帮扶记录列表")
	@GetMapping("/records/{recordName}/{studentId}")
	@ResponseBody
	public Result<List<Record>> list(@PathVariable("recordName")String recordName,
			@PathVariable("studentId")Long studentId){
		try {
			Record record=new Record(studentId, recordName);
			List<Record> records=recordService.list(record);
			return new Result<List<Record>>(true, records);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<List<Record>>(false, Message.GET_FAILURE);
		}
	}
	
	@Log("前台新增帮扶记录")
	@PostMapping("/record")
	@ResponseBody
	public Result<Record> add(@RequestBody Record record){
		try {
			recordService.add(record);
			return new Result<Record>(true, Message.ADD_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.ADD_FAILURE);
		}
	}
	
	@Log("前台删除帮扶记录")
	@DeleteMapping("/records")
	@ResponseBody
	public Result<Record> deleteBatch(@RequestBody RecordIdsParam recordIdsParam){
		try {
			recordService.deleteBatch(recordIdsParam.getRecordIds());
			return new Result<>(true,Message.DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.DELETE_FAILURE);
		}
	}
	
	@Log("前台更新帮扶记录")
	@PutMapping("/record")
	@ResponseBody
	public Result<Record> update(@RequestBody Record record){
		try {
			recordService.update(record);
			return new Result<>(true,Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.UPDATE_FAILURE);
		}
	}
	
	@Log("前台查看帮扶记录")
	@GetMapping("/record/{recordId}")
	@ResponseBody
	public Result<Record> get(@PathVariable Long recordId){
		try {
			Record record=recordService.get(recordId);
			return new Result<Record>(true, record);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	/*@Log("下载帮扶记录模板excel文件")
	@GetMapping("/download/recordTemplate/{recordName}")
	@ResponseBody
	public ResponseEntity<?> downloadTemplate(@PathVariable String recordName){
		byte[] body;
		try {
			body=FileUtil.getBytes(Path.TEMPLATE_BASE_PATH+recordName+".xlsx");
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", recordName+".xlsx", Charset.forName("UTF-8"));
			ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
			return responseEntity;
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(Message.DOWNLOAD_FAILURE,HttpStatus.BAD_REQUEST);
		}
	}
	
	@Log("导入帮扶记录excel文件")
	@PostMapping("/import/record")
	@ResponseBody
	public Result<Record> importRecord(Record record,
			@RequestParam("file") MultipartFile multipartFile){
		try {
			recordService.importRecordFromExcelFile(record, multipartFile);
			return new Result<Record>(true, Message.IMPORT_SUCCESS);
		} catch (FileTypeNotMatchException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (NoDataToImportException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (NotPointOutRecordNameException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (NotPointOutStudentIdException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new Result<Record>(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.IMPORT_FAILURE);
		} 
	}*/
	
	//导出帮扶记录文件
	@Log("前台导出帮扶记录excel文件")
	@PostMapping("/export/record/{recordName}")
	@ResponseBody
	public ResponseEntity<?> exportRecord(@PathVariable String recordName,String json){
		try {
			ObjectMapper mapper=new ObjectMapper();
			RecordIdsParam recordIdsParam=mapper.readValue(json,RecordIdsParam.class);
			byte[] body=recordService.exportReocrdToExcelFile(recordName, recordIdsParam.getRecordIds());
			Record record=recordService.get(recordIdsParam.getRecordIds().get(0));
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			headers.setContentDispositionFormData("attachment", recordName+"-"+record.getStudentId()+"-"+sdf.format(new Date())+".xlsx",Charset.forName("UTF-8"));
			ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
			return responseEntity;
		} catch (NotChoseExportObjectException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (RecordTypeNotMatchException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(Message.EXPORT_FAILURE,HttpStatus.BAD_REQUEST);
		}
	}
	
}
