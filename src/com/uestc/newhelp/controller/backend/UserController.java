package com.uestc.newhelp.controller.backend;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoSuchUserException;
import com.uestc.newhelp.exception.PasswordErrorException;
import com.uestc.newhelp.service.UserService;

/**
 * @author ZhiqiangShen
 * ���¼�йصĺ�˿�����
 */
@Controller(value="BackendUserController")
@RequestMapping("/backend")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String login(Teacher teacher,Model model,HttpSession session) {
		try {
			Teacher t = userService.backendLogin(teacher);
			session.setAttribute("user", t);
			session.setAttribute("backendHandleAuthorization", true);
			return "redirect:/backend/teachers";
		} catch (NoSuchUserException | PasswordErrorException | NoAuthorityException e) {
			model.addAttribute("message",e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(path="/logout",method=RequestMethod.DELETE)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("backendHandleAuthorization");
		return "redirect:/index.jsp";
	}
}
