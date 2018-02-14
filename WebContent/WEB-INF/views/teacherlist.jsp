<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师帐号列表</title>
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css"/>
<style type="text/css">
td  
{  
    text-align:center;  
}  
th  
{  
    text-align:center;  
}  
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function deleteTeacher(teacherId){
		var deleteFlag=confirm("确定删除");
		if(deleteFlag){
			var deleteForm=$("#deleteForm");
			var action=deleteForm.attr("action");
			deleteForm.attr("action",action+teacherId);
			deleteForm.submit();
			return false;
		}
		
	}
	function updateTeacher(teacherId,password,name,duty,grade){
		$("#updateTeacherId").val(teacherId);
		$("#updatePassword").val(password);
		$("#updateName").val(name);
		$("#updateDuty").val(duty);
		$("#updateGrade").val(grade);
	}
</script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand">帮扶系统后台</a>
	</div>
	<div>
		<ul class="nav navbar-nav navbar-left">
			<li class="active"><a href="<%=path%>/backend/teachers">教师账号</a></li>
			<li><a href="<%=path%>/backend/helpTypes">帮扶类型</a></li>
			<li><a href="<%=path%>/backend/attentionTypes">关注类型</a></li>
			<li><a href="<%=path%>/backend/logs">操作日志</a></li>
		</ul>
		<form class="navbar-form navbar-right"  action="<%=path%>/backend/logout" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<span>${user.name }</span>
            <button type="submit" class="btn btn-default">退出登录</button>
        </form>
	</div>
	</div>
</nav>
<br/>
<h1 class="text-center">教师帐号列表</h1>
<br>
<span style="color:red">教师年级：0为所有年级 其他为所在年级</span>
<button class="btn btn-default" data-toggle="modal" data-target="#addModal" style="float:right;">增加</button>
<br>
<form action="<%=path%>/backend/teacher/" method="POST" id="deleteForm">
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th>教师用户名</th>
          <th>密码</th>
          <th>教师姓名</th>
          <th>教师职务</th>
      	  <th>教师年级</th>
      	  <th>查看权限</th>
          <th>更改</th>
          <th>删除</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${teachers}" var="teacher">
    		<tr>
    		  <td>${teacher.teacherId}</td>
		      <td>${teacher.password}</td>
		      <td>${teacher.name}</td>
		      <td>${teacher.duty}</td>
		      <td>${teacher.grade}</td>
		      <td><a href="<%=path%>/backend/authorization/${teacher.teacherId}"><button class="btn btn-default" type="button" >查看</button></a></td>
		      <td><button class="btn btn-default" type="button" data-toggle="modal" data-target="#updateModal" onclick="updateTeacher('${teacher.teacherId}','${teacher.password}','${teacher.name}','${teacher.duty}',${teacher.grade})">更改</button></td>
		      <td><button class="btn btn-default" type="button" onclick="deleteTeacher('${teacher.teacherId}')">删除</button></td>
	    	</tr>
    	</c:forEach>
	  </tbody>
	</table>
</form>
<!-- 增加模态框  -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加教师账号</h4>
            </div>
            <form action="<%=path%>/backend/teacher" method="post">
	            <div class="modal-body">
	            	教师用户名<input name="teacherId" type="text"/>
	            	密码<input name="password" type="text"/><br/><br/>
	            	教师姓名<input name="name" type="text"/>
	            	教师职务<input name="duty" type="text"/><br/><br/>
	            	教师年级<input name="grade" type="text"/>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="submit" class="btn btn-primary">提交</button>
	            </div>
            </form>
        </div>
    </div>
</div>
<!-- 更新模态框  -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">更改帮扶类型</h4>
            </div>
            <form action="<%=path%>/backend/teacher" method="post">
	            <div class="modal-body">
	            	<input type="hidden" name="_method" value="put"/>
            	        教师用户名<input name="teacherId" type="text" id="updateTeacherId" readonly="readonly"/>
	            	密码<input name="password" type="text" id="updatePassword"/><br/><br/>
	            	教师姓名<input name="name" type="text" id="updateName"/>
	            	教师职务<input name="duty" type="text" id="updateDuty"/><br/><br/>
	            	教师年级<input name="grade" type="text" id="updateGrade"/>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="submit" class="btn btn-primary">提交</button>
	            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>