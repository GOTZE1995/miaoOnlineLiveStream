<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%= basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录页</title>
<link href="./Wopop_files/style_log.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="Wopop_files/style.css">
<link rel="stylesheet" type="text/css" href="Wopop_files/userpanel.css">
<link rel="stylesheet" type="text/css"
	href="Wopop_files/jquery.ui.all.css">
</head>
<body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_boder">
			<form action="${basePath}sys/login_back" method="post">
				<div class="login_padding" id="login_model">
					<h2>管理员帐号</h2>
					<label> <input type="text" id="username"
						class="txt_input txt_input2" name="userName">
					</label>
					<h2>密码</h2>
					<input type="password" name="password" id="userpwd"
						class="txt_input">
					<p class="forgot">
						<a id="iforget" href="javascript:void(0);">忘记密码?</a>
					</p>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="checkbox" id="save_me"> <label
								for="checkbox">记住密码</label>
						</div>
						<label> <input type="submit" class="sub_button"
							name="button" id="button" value="登录" style="opacity: 0.7;">
						</label>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>