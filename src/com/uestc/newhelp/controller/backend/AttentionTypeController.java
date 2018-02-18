package com.uestc.newhelp.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.entity.AttentionType;
import com.uestc.newhelp.service.AttentionTypeService;

@Controller("BackendAttentionTypeController")
@RequestMapping("/backend")
public class AttentionTypeController {
	@Autowired
	private AttentionTypeService attentionTypeService;
	
	@Log("查看关注类型列表")
	@RequestMapping(path="/attentionTypes",method=RequestMethod.GET)
	public String list(Model model) {
		try {
			List<AttentionType> attentionTypes=attentionTypeService.list();
			model.addAttribute("attentionTypes",attentionTypes);
			return "attentiontypelist";
		} catch (Exception e) {
			model.addAttribute("message",Message.GET_FAILURE);
			return "error";
		}
	}
	
	@Log("删除关注类型")
	@RequestMapping(path="/attentionType/{attentionTypeId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable Long attentionTypeId,Model model) {
		try {
			attentionTypeService.delete(attentionTypeId);
			return "redirect:/backend/attentionTypes";
		} catch (Exception e) {
			model.addAttribute("message",Message.DELETE_FAILURE);
			return "error";
		}
	}
	
	@Log("新增关注类型")
	@RequestMapping(path="/attentionType",method=RequestMethod.POST)
	public String add(AttentionType attentionType,Model model) {
		try {
			attentionTypeService.add(attentionType);
			return "redirect:/backend/attentionTypes";
		} catch (DuplicateKeyException e) {
			model.addAttribute("message",Message.ADD_REPEAT_FAILURE);
			return "error";
		}
	}
	
	@Log("更新关注类型")
	@RequestMapping(path="/attentionType",method=RequestMethod.PUT)
	public String update(AttentionType attentionType,Model model) {
		try {
			attentionTypeService.update(attentionType);
			return "redirect:/backend/attentionTypes";
		} catch (Exception e) {
			model.addAttribute("message",Message.UPDATE_FAILURE);
			return "error";
		}
	}
}
