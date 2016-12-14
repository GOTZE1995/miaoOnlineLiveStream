<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <!-- 包含公共的JSP代码片段 -->
<%@include file="/common/header.jsp"%>		
<title>添加新用户</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/page_common.js"></script>
<link href="style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/css/index_1.css" />
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			
				
				
					<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 添加新用户
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="user/add.do" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="style/images/item_point.gif"> 用户信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="80px">用户名</td>
							<td><input type="text" name="userName" class="InputStyle" />
                            
                             *</td>
						</tr>
						<tr>
							<td width="80px">昵称</td>
							<td><input type="text" name="nickName" class="InputStyle" /> *</td>
						</tr>
						<tr>
							<td width="80px">密码</td>
							<td><input type="text" name="password" class="InputStyle" /> *</td>
						</tr>
						<tr>
							<td>邮箱</td>
							<td><input type="text" name="email" class="InputStyle" /> *</td>
						</tr>	
						<tr>
							<td>班级</td>
							<td><input type="text" name="className" class="InputStyle"/> *</td>
						</tr>		
						<tr>
							<td>角色</td>
							<td>
								<c:forEach var="role" items="${roleList }">
									<input type="radio" name="roleId" value="${role.roleId }"/>${role.roleName }
								</c:forEach>
			                </td>
						</tr>
						<tr>
							<td width="80px">头像</td>
							<td>
								
								<input type="file" name="file"/> 
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
					 <input type="submit" value="添加" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
