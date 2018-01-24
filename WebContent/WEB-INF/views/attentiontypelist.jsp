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
<title>关注类型列表</title>
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
	function deleteAttentionType(attentionTypeId){
		var deleteFlag=confirm("确定删除");
		if(deleteFlag){
			var deleteForm=$("#deleteForm");
			var action=deleteForm.attr("action");
			deleteForm.attr("action",action+attentionTypeId);
			deleteForm.submit();
			return false;
		}
		
	}
	function updateAttentionType(attentionTypeId,attentionTypeName,remindRecordInterval){
		$("#updateAttentionTypeId").val(attentionTypeId);
		$("#updateAttentionTypeName").val(attentionTypeName);
		$("#updateRemindRecordInterval").val(remindRecordInterval);
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
		<ul class="nav navbar-nav">
			<li><a href="<%=path%>/backend/teachers">教师账号</a></li>
			<li><a href="<%=path%>/backend/helpTypes">帮扶类型</a></li>
			<li class="active"><a href="<%=path%>/backend/attentionTypes">关注类型</a></li>
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
<h1 class="text-center">关注类型列表</h1>
<br>
<button class="btn btn-default" data-toggle="modal" data-target="#addModal" style="float:right;">增加</button>
<br>
<form action="<%=path%>/backend/attentionType/" method="POST" id="deleteForm">
	<input type="hidden" name="_method" value="DELETE"/>
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th>关注类型id</th>
	      <th>关注类型名称</th>
      	  <th>提醒周数</th>
          <th>更改</th>
          <th>删除</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${attentionTypes}" var="attentionType">
    		<tr>
    		  <td>${attentionType.attentionTypeId}</td>
		      <td>${attentionType.attentionTypeName}</td>
		      <td>${attentionType.remindRecordInterval}</td>
		      <td><button class="btn btn-default" type="button" data-toggle="modal" data-target="#updateModal" onclick="updateAttentionType(${attentionType.attentionTypeId},'${attentionType.attentionTypeName}',${attentionType.remindRecordInterval})">更改</button></td>
		      <td><button class="btn btn-default" type="button" onclick="deleteAttentionType(${attentionType.attentionTypeId})">删除</button></td>
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
                <h4 class="modal-title" id="myModalLabel">添加帮扶类型</h4>
            </div>
            <form action="<%=path%>/backend/attentionType" method="post">
	            <div class="modal-body">
	            	关注类型名称<input name="attentionTypeName" type="text"/>
	            	提醒周数<input name="remindRecordInterval" type="text"/>
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
            <form action="<%=path%>/backend/attentionType" method="post">
	            <div class="modal-body">
	            	<input type="hidden" name="_method" value="put"/>
	            	<input type="hidden" name="attentionTypeId" id="updateAttentionTypeId"/>
            	        关注类型名称<input name="attentionTypeName" type="text" id="updateAttentionTypeName" readonly="readonly"/>
	            	提醒周数<input name="remindRecordInterval" type="text" id="updateRemindRecordInterval"/>
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