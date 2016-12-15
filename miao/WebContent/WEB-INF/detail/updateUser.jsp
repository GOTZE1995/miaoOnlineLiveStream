<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新用户</title>

<%@include file="/common/header.jsp"%>		

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="modify/style/js/jquery.js"></script>
<script type="text/javascript" src="modify/style/js/page_common.js"></script>
<link href="modify/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="modify/style/css/index_1.css" />
</head>
<script type="text/javascript">
    
    	//check数据回显 
    	function doCheck(){
			$("#cb${userInfo.role.roleId}").attr("checked",true);
    	}
    	window.onload= doCheck
</script>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			
				
					<img border="0" width="13" height="13" src="modify/style/images/title_arrow.gif"/> 更新用户
				
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="user/update.do" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="modify/style/images/item_point.gif"> 用户信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="80px">用户名</td>
							<td><input type="text" name="userName" class="InputStyle" value="${userInfo.userName }"/>
								
                             *<input type="hidden" name="id" value="${userInfo.id }"/></td>
						</tr>
						<tr>
							<td width="80px">昵称</td>
							<td><input type="text" name="nickName" class="InputStyle" value="${userInfo.nickName }"/> *</td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="text" name="password" class="InputStyle" value="${userInfo.password }"/> *</td>
						</tr>
                        <tr>
							<td>邮箱</td>
							<td><input type="text" name="email" class="InputStyle" value="${userInfo.email }"/> *</td>
						</tr>
						
						<tr>
							<td>班级</td>
							<td><input type="text" name="className" class="InputStyle" value="${userInfo.className }"/> *</td>
						</tr>
						<tr>
							<td>直播间</td>
							<td>${userInfo.room.roomName }</td>
						</tr>

						<tr>
							<td>角色</td>
							<td>
								<c:forEach var="role" items="${roleList }">
										<input type="radio" name="roleId" id="cb${role.roleId }" value="${role.roleId }"/>${role.roleName }
								</c:forEach>
			                </td>
						</tr>

						<tr>
							<td width="80px">头像</td>
							<td>
								<c:if test="${not empty userInfo.headImg }">
									<img style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;' 
									src="${userInfo.headImg }">
								</c:if>
								<input type="hidden" name="headImg" value="${userInfo.headImg }">
								<input type="file" name="file"/> *
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">		
			<input type="submit" value="修改" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>