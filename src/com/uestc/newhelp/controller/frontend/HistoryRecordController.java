package com.uestc.newhelp.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.service.HistoryRecordService;

@RestController
@RequestMapping("/api")
public class HistoryRecordController {
	@Autowired
	private HistoryRecordService historyRecordService;
	
	@Log("�鿴��ʷ�����¼�б�")
	@GetMapping("/historyRecords/{recordName}/{historyArchiveId}")
	@ResponseBody
	public Result<List<HistoryRecord>> list(@PathVariable("recordName") String recordName,
			@PathVariable("historyArchiveId") Long historyArchiveId){
		try {
			HistoryRecord historyRecord=new HistoryRecord(historyArchiveId, recordName);
			List<HistoryRecord> historyRecords=historyRecordService.list(historyRecord);
			return new Result<>(true,historyRecords);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
	@Log("�鿴��ʷ�����¼�б�")
	@GetMapping("/historyRecord/{historyRecordId}")
	public Result<HistoryRecord> get(@PathVariable Long historyRecordId){
		try {
			HistoryRecord historyRecord=historyRecordService.get(historyRecordId);
			return new Result<>(true,historyRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
}
