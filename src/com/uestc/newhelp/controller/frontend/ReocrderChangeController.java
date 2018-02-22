package com.uestc.newhelp.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.RecorderChange;
import com.uestc.newhelp.service.RecorderChangeService;

@RestController
@RequestMapping("/api")
public class ReocrderChangeController {
	@Autowired
	private RecorderChangeService recorderChangeService;
	
	@Log("前台变更帮扶学生至其他帮扶教师")
	@PostMapping("/change/{newTeacherId}")
	@ResponseBody
	public Result<RecorderChange> add(@PathVariable("newTeacherId") String newTeacherId,
			@RequestBody RecorderChange recorderChange){
		try {
			recorderChangeService.add(recorderChange, newTeacherId);
			return new Result<>(true,Message.CHANGE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.CHANGE_FAILURE);
		}
	}
}
