package com.uestc.newhelp.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dao.LogDao;
import com.uestc.newhelp.entity.Log;
import com.uestc.newhelp.service.LogService;

@Controller
@RequestMapping("/backend")
public class LogController {
	@Autowired
	private LogDao logDao;
	@Autowired
	private LogService logService;
	
	@com.uestc.newhelp.annotation.Log("后台查看操作日志列表")
	@RequestMapping(path="/logs/{pageSize}/{pageNum}",method=RequestMethod.GET)
	public String list(@PathVariable("pageSize")int pageSize,
			@PathVariable("pageNum")int pageNum,Model model) {
		try {
			PageInfo<Log> pageInfo=logService.list(pageNum, pageSize);
			List<Log> logs=pageInfo.getList();
			model.addAttribute("logs",logs);
			//表示当前未在搜索
			model.addAttribute("isSearch",false);
			model.addAttribute("currentPage",pageInfo.getPageNum());
			model.addAttribute("totalPages",pageInfo.getPages());
			return "loglist";
		} catch (Exception e) {
			model.addAttribute("message",Message.GET_FAILURE);
			return "error";
		}
	}
	
	@com.uestc.newhelp.annotation.Log("后台搜索操作日志列表")
	@RequestMapping(path="/logs",method=RequestMethod.POST)
	public String search(int pageSize,int pageNum,Log log,Model model) {
		try {
			PageInfo<Log> pageInfo=logService.search(log,pageNum, pageSize);
			List<Log> logs=pageInfo.getList();
			model.addAttribute("logs",logs);
			//回显搜索参数
			model.addAttribute("log",log);
			//表示当前正在搜索
			model.addAttribute("isSearch",true);
			model.addAttribute("currentPage",pageInfo.getPageNum());
			model.addAttribute("totalPages",pageInfo.getPages());
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
