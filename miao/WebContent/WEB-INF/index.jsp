﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<base href="<%= basePath %>"/>
<script type="text/javascript" src="modify/js/jquery-1.11.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>米奥课堂后台管理</title>
</head>
<frameset rows="100px,*,19px" framespacing="0" border="0"
	frameborder="0">
	<frame src="sys/top.do" scrolling="no" noresize />
	<frameset cols="178px,*">
		<frame noresize src="sys/left.do" scrolling="yes" />
		<frame noresize name="right" src="sys/login_back.do" scrolling="yes" />
	</frameset>
	<frame noresize name="status_bar" scrolling="no" src="sys/bottom.do" />
</frameset>
<noframes>
	<body>
		你的浏览器不支持框架布局，推荐你使用
		<a href="http://www.firefox.com.cn/download/"
			style="text-decoration: none;">火狐浏览器</a>,
		<a href="http://www.google.cn/intl/zh-CN/chrome/"
			style="text-decoration: none;">谷歌浏览器</a>
	</body>
</noframes>
</html>