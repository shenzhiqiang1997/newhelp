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
	
	//��ȡ���ط����ϵ���־����
	private String getLog(JoinPoint joinPoint) throws Exception {
		//�������ע���ȡ����ǩ������
		MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
		//�ӷ���ǩ�������ȡ������
		Method method=methodSignature.getMethod();
		//�ӷ����ϻ�ȡ������־ע��
		Log log=method.getDeclaredAnnotation(Log.class);
		//����ע�����ݼ���������
		return log.value();
	}
	
	//��ȡ�������˺�id
	private String getTeacherId() {
		//��ȡrequest
		RequestAttributes ra=RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra=(ServletRequestAttributes)ra;
		HttpServletRequest request=sra.getRequest();
		
		//��request����session�л�ȡteacherId
		String teacherId=(String)request.getAttribute("teacherId");
		if(teacherId==null)
			teacherId=((Teacher)(request.getSession().getAttribute("user"))).getTeacherId();
		
		return teacherId;
	}
	
	//�鿴�����Ƿ�ɹ�
	private Byte operateSuccess(Object resultObject) {
		Class<?> resultClass=resultObject.getClass();
		if (resultClass==String.class) {
			//��̨���ص����ַ���
			String result=(String) resultObject;
			//������ش���ҳ�������ʧ��
			if (result.equals("error")) {
				return 0;
			}
		//ǰ̨����Result ����ResponseEntity
		}else if(resultClass==Result.class){
			Result<?> result=(Result<?>) resultObject;
			//������ؽ����successΪfalse ��ʧ��
			if (result.isSuccess()==false) {
				return 0;
			}
		}else if(resultClass==ResponseEntity.class) {
			//�����ļ���Ӧ�����˵ ����ļ���Ӧ�������ΪString ��ʧ��
			ResponseEntity<?> result=(ResponseEntity<?>)resultObject;
			if (result.getBody()!=null&&result.getBody().getClass()==String.class) {
				return 0;
			}
		}
		//�������Ϊ�ɹ�
		return 1;
	}
	
	private String getMessage(JoinPoint joinPoint,Object resultObject) {
		//����Ƿ���ǰ̨�������DTO���
		if (resultObject.getClass()==Result.class) {
			Result<?> result=(Result<?>) resultObject;
			//�����Ϣ��Ϊ���򷵻���Ϣ
			if (result.getMessage()!=null) {
				return result.getMessage();
			}
		}else if(resultObject.getClass()==ResponseEntity.class) {
			//�������ǰ̨���Ϊ��Ӧ�ļ����
			ResponseEntity<?> result=(ResponseEntity<?>)resultObject;
			//������Ϊ��Ӧʧ�ܵ�����Ӧ����Ϊ���� �����־���ʧ����Ϣ
			if (result.getBody()!=null&&result.getBody().getClass()==String.class) {
				return String.valueOf(result.getBody());
			}
		}else {
			//������Ǻ�̨��� ҪѰ��BindingAwareModelMap���������
			Object[] args=joinPoint.getArgs();
			
			for (Object object : args) {
				//����Ӻ�̨���ذ󶨽�����ҵ�����Ϣ�򷵻���Ϣ
				if (object.getClass()==BindingAwareModelMap.class) {
					BindingAwareModelMap map=(BindingAwareModelMap) object;
					String message=(String) map.get("message");
					if (message!=null) {
						return message;
					}
				}
			}
		}
		
		//���������Ϣû�ҵ�����null
		return null;
		
	}
}
