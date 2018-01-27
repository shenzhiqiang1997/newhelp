package com.uestc.newhelp.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.entity.Authorization;
import com.uestc.newhelp.message.Message;

@Controller
@RequestMapping("/backend/authorization")
public class AuthorizationController {
	@Autowired
	private AuthorizationDao authorizationDao;
	
	@RequestMapping(value="/{teacherId}",method=RequestMethod.GET)
	public String get(@PathVariable("teacherId")String teacherId,Model model) {
		try {
			Authorization authorization=authorizationDao.get(teacherId);
			model.addAttribute("authorization",authorization);
			return "authorizationlist";
		} catch (Exception e) {
			model.addAttribute("message",Message.GET_FAILURE);
			return "error";
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String update(Authorization authorization,Model model) {
		try {
			authorizationDao.update(authorization);
			return "redirect:/backend/authorization/"+authorization.getTeacherId();
		} catch (Exception e) {
			model.addAttribute("message", Message.UPDATE_FAILURE);
			return "error";
		}
	}
	
	
}
