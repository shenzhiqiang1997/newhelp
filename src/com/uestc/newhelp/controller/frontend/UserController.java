package com.uestc.newhelp.controller.frontend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.uestc.newhelp.dto.TeacherUpdatePasswordParam;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoSuchUserException;
import com.uestc.newhelp.exception.PasswordErrorException;
import com.uestc.newhelp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Log("登录前台")
	@PostMapping("/login")
	@ResponseBody
	public Result<Teacher> login(@RequestBody Teacher teacher,HttpServletRequest request) {
		try {
			Teacher t=userService.login(teacher);
			//将teacherId放到request中 便于记录日志
			request.setAttribute("teacherId", teacher.getTeacherId());
			return new Result<>(true, t, Message.LOGIN_SUCCESS);
		} catch (NoSuchUserException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		} catch (PasswordErrorException e) {
			e.printStackTrace();
			return new Result<>(false,e.getMessage());
		}
	}
	
	@Log("登出前台")
	@DeleteMapping("/logout/{teacherId}")
	public Result<?> logout(@PathVariable String teacherId){
		try {
			userService.logout(teacherId);
			return new Result<>(true,"退出成功");
		} catch (Exception e) {
			return new Result<>(false, "退出失败");
		}
	}
	
	@Log("修改密码")
	@PutMapping("/teacher")
	public Result<?> updatePassword(@RequestBody TeacherUpdatePasswordParam teacherUpdatePasswordParam){
		try {
			userService.updatePassword(teacherUpdatePasswordParam);
			return new Result<>(true, Message.PASSWORD_UPDATE_SUCCESS);
		} catch (NoSuchUserException e) {
			e.printStackTrace();
			return new Result<>(false, e.getMessage());
		} catch(PasswordErrorException e) {
			e.printStackTrace();
			return new Result<>(false, e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			return new Result<>(false, Message.PASSWORD_UPDATE_FAILURE);
		}
	}
	
	@Log("查看教师列表")
	@GetMapping("/teachers/{teacherId}")
	@ResponseBody
	public Result<List<Teacher>> list(@PathVariable String teacherId){
		try {
			List<Teacher> teachers=userService.list(teacherId);
			return new Result<>(true,teachers);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false,Message.GET_FAILURE);
		}
	}
	
}
