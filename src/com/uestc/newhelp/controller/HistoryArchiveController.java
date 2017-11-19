package com.uestc.newhelp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.message.Message;
import com.uestc.newhelp.service.HistoryArchiveService;

@RestController
@RequestMapping("/api")
public class HistoryArchiveController {
	@Autowired
	private HistoryArchiveService historyArchiveService;
	
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
}
