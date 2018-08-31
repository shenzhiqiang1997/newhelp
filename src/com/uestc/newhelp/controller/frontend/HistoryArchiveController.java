package com.uestc.newhelp.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.HistoryArchiveIdsParam;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.service.HistoryArchiveService;

@RestController
@RequestMapping("/api")
public class HistoryArchiveController {
	@Autowired
	private HistoryArchiveService historyArchiveService;
	
	@Log("ǰ̨�鿴��ʷ���ѧ���б�")
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
	
	@Log("ǰ̨������ʷ���ѧ���б�")
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
	
	@Log("ǰ̨�鿴��ʷ���ѧ������")
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
	
	@Log("ǰ̨ɾ����ʷ���ѧ������")
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
}
