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
<title>权限列表</title>
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
	function update(){
		var checkboxs=$(":checkbox");
		for(var i=0;i<checkboxs.length;i++){
			var checkbox=checkboxs[i];
			
			if($(checkbox).is(':checked'))
				$(checkbox).val(1);
			else
				$(checkbox).val(0);
			
			$(checkbox).attr('type','hidden');
		}
		
		$("#updateForm").submit();
	}
	function pick(){
		var checkButton=$("#checkButton");
		var buttonText=$(checkButton).text();
		var checkboxs=$(":checkbox");
		if(buttonText=='全选'){
			for(var i=0;i<checkboxs.length;i++){
				var checkbox=checkboxs[i];
				$(checkbox).attr('checked',true);
			}
			$(checkButton).text('取消');
		}else{
			for(var i=0;i<checkboxs.length;i++){
				var checkbox=checkboxs[i];
				$(checkbox).attr('checked',false);
			}
			$(checkButton).text('全选');
		}
		
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
<h1 class="text-center">权限列表</h1>
<br>

<div align="center">
	<span style="font-size:20px">${authorization.teacherId}的权限</span>
</div>
<br>
<div align="right">
		<button class="btn btn-default" type="button" onclick="pick()" id="checkButton">全选</button>
	</div>
<br>
<form action="<%=path%>/backend/authorization" method="POST" id="updateForm">
	<input type="hidden" name="_method" value="PUT"/>
	<input type="hidden" name="teacherId" value="${authorization.teacherId }"/>
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th>权限名称</th>
          <th>权限状态</th>
	    </tr>
	  </thead>
	  <tbody>
		<tr>
			<td>后台管理</td>
			<td><input type="checkbox" ${authorization.backEndHandle eq 1?"checked=\"checked\"":""} name="backEndHandle"/></td>
		</tr>
		<tr>
			<td>基本学生信息查看</td>
			<td><input type="checkbox" ${authorization.baseStudentSee eq 1?"checked=\"checked\"":""} name="baseStudentSee"/></td>
		</tr>
		<tr>
			<td>基本学生信息修改</td>
			<td><input type="checkbox" ${authorization.baseStudentEdit eq 1?"checked=\"checked\"":""} name="baseStudentEdit"/></td>
		</tr>
		<tr>
			<td>基本学生信息导入Excel列表</td>
			<td><input type="checkbox" ${authorization.baseStudentImport eq 1?"checked=\"checked\"":""} name="baseStudentImport"/></td>
		</tr>
		<tr>
			<td>基本学生信息导出Excel列表</td>
			<td><input type="checkbox" ${authorization.baseStudentExport eq 1?"checked=\"checked\"":""} name="baseStudentExport"/></td>
		</tr>
		<tr>
			<td>困难学生档案查看</td>
			<td><input type="checkbox" ${authorization.archiveStudentSee eq 1?"checked=\"checked\"":""} name="archiveStudentSee"/></td>
		</tr>
		<tr>
			<td>困难学生档案修改</td>
			<td><input type="checkbox" ${authorization.archiveStudentEdit eq 1?"checked=\"checked\"":""} name="archiveStudentEdit"/></td>
		</tr>
		<tr>
			<td>困难学生档案变更</td>
			<td><input type="checkbox" ${authorization.archiveStudentChange eq 1?"checked=\"checked\"":""} name="archiveStudentChange"/></td>
		</tr>
		<tr>
			<td>困难学生档案建档</td>
			<td><input type="checkbox" ${authorization.archiveStudentBuild eq 1?"checked=\"checked\"":""} name="archiveStudentBuild"/></td>
		</tr>
		<tr>
			<td>困难学生档案除档</td>
			<td><input type="checkbox" ${authorization.archiveStudentDestory eq 1?"checked=\"checked\"":""} name="archiveStudentDestory"/></td>
		</tr>
		<tr>
			<td>困难学生档案导出整本</td>
			<td><input type="checkbox" ${authorization.archiveStudentExport eq 1?"checked=\"checked\"":""} name="archiveStudentExport"/></td>
		</tr>
		<tr>
			<td>困难学生记录查看</td>
			<td><input type="checkbox" ${authorization.archiveRecordSee eq 1?"checked=\"checked\"":""} name="archiveRecordSee"/></td>
		</tr>
		<tr>
			<td>困难学生记录修改</td>
			<td><input type="checkbox" ${authorization.archiveRecordEdit eq 1?"checked=\"checked\"":""} name="archiveRecordEdit"/></td>
		</tr>
		<tr>
			<td>困难学生记录新增</td>
			<td><input type="checkbox" ${authorization.archiveRecordAdd eq 1?"checked=\"checked\"":""} name="archiveRecordAdd"/></td>
		</tr>
		<tr>
			<td>困难学生记录删除</td>
			<td><input type="checkbox" ${authorization.archiveRecordDelete eq 1?"checked=\"checked\"":""} name="archiveRecordDelete"/></td>
		</tr>
		<tr>
			<td>困难学生记录导出</td>
			<td><input type="checkbox" ${authorization.archiveRecordExport eq 1?"checked=\"checked\"":""} name="archiveRecordExport"/></td>
		</tr>
		<tr>
			<td>困难学生历史档案查看</td>
			<td><input type="checkbox" ${authorization.historyArchiveSee eq 1?"checked=\"checked\"":""} name="historyArchiveSee"/></td>
		</tr>
		<tr>
			<td>困难学生历史记录查看</td>
			<td><input type="checkbox" ${authorization.historyRecordSee eq 1?"checked=\"checked\"":""} name="historyRecordSee"/></td>
		</tr>
	  </tbody>
	</table>
	
	<div align="center">
		<button class="btn btn-default" type="button" onclick="update()">保存</button>
	</div>
	
</form>

</body>
</html>