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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.HistoryArchiveIdsParam;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.service.HistoryArchiveService;

@RestController
@RequestMapping("/api")
public class HistoryArchiveController {
	@Autowired
	private HistoryArchiveService historyArchiveService;
	
	@Log("前台查看历史帮扶学生列表")
	@GetMapping("/historyArchives/{teacherId}")
	@ResponseBody
	public Result<List<HistoryArchive>> list(@PathVariable String teacherId){
		try {
			List<HistoryArchive> historyArchives=historyArchiveService.list(teacherId);
			return new Result<>(true,historyArchives);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@Log("前台搜索历史帮扶学生列表")
	@PostMapping("/historyArchives")
	@ResponseBody
	public Result<List<HistoryArchive>> search(@RequestBody HistoryArchive historyArchive){
		try {
			List<HistoryArchive> historyArchives=historyArchiveService.search(historyArchive);
			return new Result<>(true,historyArchives);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@Log("前台查看历史帮扶学生档案")
	@GetMapping("/historyArchive/{historyArchiveId}")
	@ResponseBody
	public Result<HistoryArchive> get(@PathVariable Long historyArchiveId){
		try {
			HistoryArchive historyArchive=historyArchiveService.get(historyArchiveId);
			return new Result<>(true,historyArchive);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@Log("前台删除历史帮扶学生档案")
	@DeleteMapping("/historyArchives")
	@ResponseBody
	public Result<HistoryArchive> deleteBatch(@RequestBody HistoryArchiveIdsParam historyArchiveIdsParam){
		try {
			historyArchiveService.deleteBatch(historyArchiveIdsParam.getHistoryArchiveIds());
			return new Result<>(true,Message.DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.DELETE_FAILURE);
		}
	}
	
	@Log("前台导出历史帮扶学生档案word文件")
	@PostMapping("/export/historyArchive/{historyArchiveId}")
	@ResponseBody
	public ResponseEntity<?> exportArchive(@PathVariable Long historyArchiveId/*,@RequestParam("token")String token*/){
		try {
			byte[] body=historyArchiveService.exportHistoryArchiveToWordFile(historyArchiveId);
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			headers.setContentDispositionFormData("attachment", FileName.ARCHIVE_BOOK+"-"+historyArchiveId+"-"+sdf.format(new Date())+".doc",Charset.forName("UTF-8"));
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
