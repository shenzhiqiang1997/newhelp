package com.uestc.newhelp.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.ExposeSetting;
import com.uestc.newhelp.service.ExposeSettingService;

@RestController
@RequestMapping("/api")
public class ExposeSettingController {
	@Autowired
	private ExposeSettingService exposeSettingService;
	
	@GetMapping("/exposeSetting/{teacherId}")
	@ResponseBody
	public Result<ExposeSetting> get(@PathVariable String teacherId) {
		try {
			ExposeSetting exposeSetting=exposeSettingService.get(teacherId);
			return new Result<ExposeSetting>(true, exposeSetting);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
	@PutMapping("/exposeSetting")
	@ResponseBody
	public Result<ExposeSetting> update(@RequestBody ExposeSetting exposeSetting){
		try {
			exposeSettingService.update(exposeSetting);
			return new Result<>(true,Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.UPDATE_FAILURE);
		}
	}
	
}
