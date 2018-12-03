package com.uestc.newhelp.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoSuchUserException;
import com.uestc.newhelp.exception.PasswordErrorException;
import com.uestc.newhelp.service.UserService;

/**
 * @author ZhiqiangShen
 * 与登录有关的后端控制器
 */
@Controller(value="BackendUserController")
@RequestMapping("/backend")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Log("登录后台")
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
	
	@Log("退出后台")
	@RequestMapping(path="/logout",method=RequestMethod.DELETE)
	public String logout(HttpSession session,HttpServletRequest request) {
		//便于在记录日志时获取到操作人
		request.setAttribute("teacherId", ((Teacher)session.getAttribute("user")).getTeacherId());
				
		session.removeAttribute("user");
		session.removeAttribute("backendHandleAuthorization");
		
		
		return "redirect:/index.jsp";
	}
}
