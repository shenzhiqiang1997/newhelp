package com.uestc.newhelp.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.ImportSetting;
import com.uestc.newhelp.service.ImportSettingService;

@RestController
@RequestMapping("/api")
public class ImportSettingController {
	@Autowired
	private ImportSettingService importSettingService;
	
	@Log("查看导入设置")
	@GetMapping("/importSetting/{teacherId}")
	@ResponseBody
	public Result<ImportSetting> get(@PathVariable String teacherId) {
		try {
			ImportSetting importSetting=importSettingService.get(teacherId);
			return new Result<ImportSetting>(true, importSetting);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
	@Log("更新导入设置")
	@PutMapping("/importSetting")
	@ResponseBody
	public Result<ImportSetting> update(@RequestBody ImportSetting importSetting){
		try {
			importSettingService.update(importSetting);
			return new Result<>(true,Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.UPDATE_FAILURE);
		}
	}
	
}
