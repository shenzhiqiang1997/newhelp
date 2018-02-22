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
import com.uestc.newhelp.entity.HelpType;
import com.uestc.newhelp.service.HelpTypeService;

/**
 * @author ZhiqiangShen
 * 与帮扶类型有关的后端控制器
 */
@Controller("BackendHelpTypeController")
@RequestMapping("/backend")
public class HelpTypeController {
	@Autowired
	private HelpTypeService helpTypeService;
	
	@Log("后台查看帮扶类型列表")
	@RequestMapping(path="/helpTypes",method=RequestMethod.GET)
	public String list(Model model) {
		try {
			List<HelpType> helpTypes=helpTypeService.list();
			model.addAttribute("helpTypes", helpTypes);
			return "helptypelist";
		} catch (Exception e) {
			model.addAttribute("message", Message.GET_FAILURE);
			return "error";
		}
	}
	
	@Log("后台删除帮扶类型")
	@RequestMapping(path="/helpType/{helpTypeId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable Long helpTypeId,Model model) {
		try {
			helpTypeService.delete(helpTypeId);
			return "redirect:/backend/helpTypes";
		} catch (DuplicateKeyException e) {
			model.addAttribute("message", Message.DELETE_FAILURE);
			return "error";
		}
		
	}
	
	@Log("后台新增帮扶类型")
	@RequestMapping(path="/helpType",method=RequestMethod.POST)
	public String add(HelpType helpType,Model model) {
		try {
			helpTypeService.add(helpType);
			return "redirect:/backend/helpTypes";
		} catch (DuplicateKeyException e) {
			model.addAttribute("message", Message.ADD_REPEAT_FAILURE);
			return "error";
		}
		
	}
}
