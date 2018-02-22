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
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.HelpType;
import com.uestc.newhelp.service.HelpTypeService;

@RestController
@RequestMapping("/api")
public class HelpTypeController {
	@Autowired
	private HelpTypeService helpTypeService;
	
	@Log("前台查看帮扶类型列表")
	@GetMapping("/helpTypes")
	@ResponseBody
	public Result<List<HelpType>> list(){
		try {
			List<HelpType> helpTypes=helpTypeService.list();
			return new Result<List<HelpType>>(true, helpTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
	/*@Log("新增帮扶类型")
	@PostMapping("/helpType")
	@ResponseBody
	public Result<HelpType> add(@RequestBody HelpType helpType){
		try {
			helpTypeService.add(helpType);
			return new Result<>(true, Message.ADD_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.ADD_FAILURE);
		}
	}
	
	@Log("删除帮扶类型")
	@DeleteMapping("/helpType/{helpTypeId}")
	@ResponseBody
	public Result<HelpType> delete(@PathVariable Long helpTypeId){
		try {
			helpTypeService.delete(helpTypeId);
			return new Result<>(true, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.DELETE_FAILURE);
		}
	}*/
	
}
