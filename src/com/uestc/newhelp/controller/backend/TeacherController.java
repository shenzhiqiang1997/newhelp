package com.uestc.newhelp.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uestc.newhelp.constant.Message;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.Teacher;

@Controller
@RequestMapping("/backend")
public class TeacherController {
	@Autowired
	private TeacherDao teacherDao;
	
	@RequestMapping(path="/teachers",method=RequestMethod.GET)
	public String list(Model model) {
		try {
			List<Teacher> teachers=teacherDao.list();
			model.addAttribute("teachers",teachers);
			return "teacherlist";
		} catch (Exception e) {
			model.addAttribute("message", Message.GET_FAILURE);
			return "error";
		}
		
	}
	
	@RequestMapping(path="/teacher/{teacherId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable String teacherId,Model model) {
		try {
			teacherDao.delete(teacherId);
			return "redirect:/backend/teachers";
		} catch (Exception e) {
			model.addAttribute("message",Message.DELETE_FAILURE);
			return "error";
		}
	}
	
	@RequestMapping(path="/teacher",method=RequestMethod.POST)
	public String add(Teacher teacher,Model model) {
		try {
			teacherDao.add(teacher);
			return "redirect:/backend/teachers";
		} catch (DuplicateKeyException e) {
			model.addAttribute("message",Message.ADD_REPEAT_FAILURE);
			return "error";
		}
	}
	
	@RequestMapping(path="/teacher",method=RequestMethod.PUT)
	public String update(Teacher teacher,Model model) {
		try {
			teacherDao.update(teacher);
			return "redirect:/backend/teachers";
		} catch (Exception e) {
			model.addAttribute("message",Message.UPDATE_FAILURE);
			return "error";
		}
	}
}
