package com.uestc.newhelp.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dao.LogDao;
import com.uestc.newhelp.entity.Log;

@Controller
@RequestMapping("/backend")
public class LogController {
	@Autowired
	private LogDao logDao;
	
	@com.uestc.newhelp.annotation.Log("后台查看操作日志列表")
	@RequestMapping(path="/logs",method=RequestMethod.GET)
	public String list(Model model) {
		try {
			List<Log> logs=logDao.list();
			model.addAttribute("logs",logs);
			return "loglist";
		} catch (Exception e) {
			model.addAttribute("message",Message.GET_FAILURE);
			return "error";
		}
	}
	
	@com.uestc.newhelp.annotation.Log("后台删除操作日志")
	@RequestMapping(path="/log/{logId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable Long logId,Model model) {
		try {
			logDao.delete(logId);
			return "redirect:/backend/logs";
		} catch (Exception e) {
			model.addAttribute("message",Message.DELETE_FAILURE);
			return "error";
		}
	}
}
