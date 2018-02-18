package com.uestc.newhelp.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.AttentionType;
import com.uestc.newhelp.service.AttentionTypeService;

@RestController
@RequestMapping("/api")
public class AttentionTypeController {
	@Autowired
	private AttentionTypeService attentionTypeService;
	
	@Log("查看关注类型列表")
	@GetMapping("/attentionTypes")
	@ResponseBody
	public Result<List<AttentionType>> list(){
		try {
			List<AttentionType> attentionTypes=attentionTypeService.list();
			return new Result<>(true, attentionTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.GET_FAILURE);
		}
	}
	
/*	@Log("新增关注类型")
	@PostMapping("/attentionType")
	@ResponseBody
	public Result<AttentionType> add(@RequestBody AttentionType attentionType){
		try {
			attentionTypeService.add(attentionType);
			return new Result<>(true,Message.ADD_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.ADD_FAILURE);
		}
	}
	
	@Log("删除关注类型")
	@DeleteMapping("/attentionType/{attentionTypeId}")
	@ResponseBody
	public Result<AttentionType> delete(@PathVariable Long attentionTypeId){
		try {
			attentionTypeService.delete(attentionTypeId);
			return new Result<>(true,Message.DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.DELETE_FAILURE);
		}
	}
	
	@Log("更新关注类型")
	@PutMapping("/attentionType")
	@ResponseBody
	public Result<AttentionType> update(@RequestBody AttentionType attentionType){
		try {
			attentionTypeService.update(attentionType);
			return new Result<>(true,Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.UPDATE_FAILURE);
		}
	}*/
}
