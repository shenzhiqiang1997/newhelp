package com.uestc.newhelp.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.uestc.newhelp.annotation.Log;
import com.uestc.newhelp.dao.LogDao;
import com.uestc.newhelp.dto.Result;
import com.uestc.newhelp.entity.Teacher;
@Aspect
public class LogAspect {
	@Pointcut("@annotation(com.uestc.newhelp.annotation.Log)")
	private void logPointcut() {}
	@Autowired
	private LogDao logDao;
	
	@AfterReturning(value="logPointcut()",returning="result")
	public void after(JoinPoint joinPoint,Object result) throws Exception {
		com.uestc.newhelp.entity.Log log=new com.uestc.newhelp.entity.Log();
		log.setTeacherId(getTeacherId());
		log.setContent(getLog(joinPoint));
		log.setResult(operateSuccess(result));
		log.setMessage(getMessage(joinPoint, result));
		log.setOperateTime(new Date());
		logDao.add(log);
	}
	
	//获取拦截方法上的日志内容
	private String getLog(JoinPoint joinPoint) throws Exception {
		//从切入的注解获取方法签名对象
		MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
		//从方法签名对象获取到方法
		Method method=methodSignature.getMethod();
		//从方法上获取操作日志注解
		Log log=method.getDeclaredAnnotation(Log.class);
		//返回注解内容即操作内容
		return log.value();
	}
	
	//获取操作的账号id
	private String getTeacherId() {
		//获取request
		RequestAttributes ra=RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra=(ServletRequestAttributes)ra;
		HttpServletRequest request=sra.getRequest();
		
		//从request或者session中获取teacherId
		String teacherId=(String)request.getAttribute("teacherId");
		if(teacherId==null)
			teacherId=((Teacher)(request.getSession().getAttribute("user"))).getTeacherId();
		
		return teacherId;
	}
	
	//查看操作是否成功
	private Byte operateSuccess(Object resultObject) {
		Class<?> resultClass=resultObject.getClass();
		if (resultClass==String.class) {
			//后台返回的是字符串
			String result=(String) resultObject;
			//如果返回错误页面则操作失败
			if (result.equals("error")) {
				return 0;
			}
		//前台返回Result 或者ResponseEntity
		}else if(resultClass==Result.class){
			Result<?> result=(Result<?>) resultObject;
			//如果返回结果中success为false 则失败
			if (result.isSuccess()==false) {
				return 0;
			}
		}else if(resultClass==ResponseEntity.class) {
			//对于文件响应结果来说 如果文件响应结果泛型为String 则失败
			ResponseEntity<?> result=(ResponseEntity<?>)resultObject;
			if (result.getBody()!=null&&result.getBody().getClass()==String.class) {
				return 0;
			}
		}
		//其他情况为成功
		return 1;
	}
	
	private String getMessage(JoinPoint joinPoint,Object resultObject) {
		//如果是返回前台结果且是DTO结果
		if (resultObject.getClass()==Result.class) {
			Result<?> result=(Result<?>) resultObject;
			//如果消息不为空则返回消息
			if (result.getMessage()!=null) {
				return result.getMessage();
			}
		}else if(resultObject.getClass()==ResponseEntity.class) {
			//如果返回前台结果为响应文件结果
			ResponseEntity<?> result=(ResponseEntity<?>)resultObject;
			//且是因为响应失败导致响应内容为文字 则文字就是失败消息
			if (result.getBody()!=null&&result.getBody().getClass()==String.class) {
				return String.valueOf(result.getBody());
			}
		}else {
			//否则就是后台结果 要寻找BindingAwareModelMap类参数对象
			Object[] args=joinPoint.getArgs();
			
			for (Object object : args) {
				//如果从后台返回绑定结果中找到了消息则返回消息
				if (object.getClass()==BindingAwareModelMap.class) {
					BindingAwareModelMap map=(BindingAwareModelMap) object;
					String message=(String) map.get("message");
					if (message!=null) {
						return message;
					}
				}
			}
		}
		
		//如果最终消息没找到返回null
		return null;
		
	}
}
