<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Video, FFmpeg, JavaEE" />
<meta name="author" content="Lei Xiaohua" />
<meta name="description"
	content="The simplest video website based on JavaEE and FFmpeg" />
	
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr.custom.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>


<link href="css/svw_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script> 
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script type="text/javascript" src="js/showhide.js"></script>  
<script type="text/JavaScript" src="js/jquery.mousewheel.js"></script> 
	
<script type="text/javascript">
	function check() {
		alert("请您先登录");
	}
	
	function renewUsernameState() {
		document.getElementById("userSpan").innerHTML = "";
		document.getElementById("submitSpan").innerHTML = "";
	}
	

	 function loginCheckUserName() {
		var username = $('#username').val();
		$.ajax({
			url : 'user/checkUsername.do',
			data : {
				'username' : username
			},
			type : 'post',
			async : false,
			success : function(result) {
				if (result == "pass") {
					document.getElementById("userSpan").innerHTML = "该用户名不存在";
					document.getElementById("username").value = "";
				}
			}
		})
	}

	function loginCheckUserNameAndPwd() {
		var username = $('#username').val();
		var password = $('#password').val();
		$.ajax({
			url : 'user/loginCheckUserNameAndPwd.do',
			data : {
				'password' : password,
				'username' : username
			},
			type : 'post',
			async : false,
			success : function(result) {
				if (result != "pass") {
					document.getElementById("submitSpan").innerHTML = "用户名与密码不匹配";
					document.getElementById("username").value = "";
					document.getElementById("password").value = "";
				}
			}
		})
	}
	
	
</script>
<title>点播列表</title>
	<!-- Flow Player -->
	<script type="text/javascript"
		src="videoplayer/flowplayer-3.2.8.min.js"></script>
</head>

<body id="subpage">

	<div class="header" id="home">
		<nav class="navbar navbar-default"> <c:if
			test="${user==null || user.userName==null || user.password==null || user.userName=='' || user.password==''}">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"> </span> <span class="icon-bar"> </span> <span
							class="icon-bar"> </span>
					</button>
					<h1>
						<a class="navbar-brand" href="index.jsp">MiaoEducation<br /></a>
					</h1>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
						<li><a href="${basePath }room/findRoom"><span
								data-hover="About">视频直播</span></a></li>
						<li><a href="movie/listUI"><span
								data-hover="About">网络影院</span></a></li>
						<li><a href="" onclick="check()"><span
								data-hover="Shortcodes">个人信息</span></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>

				<div class="login-pop">
						<div id="loginpop">
							<a href="#" id="loginButton"><span>登录</span></a>
							<div id="loginBox">
								<form action="${basePath }user/login" id="loginForm"
									method="post">
									<fieldset id="body">
										<fieldset>
											<label for="email">用户名</label> <input type="text"
												name="userName" class="username" id="username"
												placeholder="用户名" onblur="loginCheckUserName()" 
												onfocus="renewUsernameState()"/>
												<span id="userSpan" style="font-size:12px;color:red"></span>
										</fieldset>
										<fieldset>
											<label for="password">密码</label> <input type="password"
												id="password" class="password" placeholder="密码"
												onblur="loginCheckUserNameAndPwd()" name="password">
											<span id="submitSpan" style="font-size:12px;color:red"></span>
										</fieldset>
										<input type="submit" id="login" value="登录"> 
										<label for="checkbox"><input
											type="checkbox" id="checkbox"> <i>记住密码</i></label>
									</fieldset>
									<span><a href="register.jsp">点我快速注册</a></span>
								</form>
							</div>
						</div>
				</div>
				<script src="js/menu_jquery.js"></script>
			</div>
		</c:if> <c:if
			test="${user!=null && user.userName!=null && user.userName!='' && user.password!=null && user.password!=''}">
			<div class="container" style="width: 1300px">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"> </span> <span class="icon-bar"> </span> <span
							class="icon-bar"> </span>
					</button>
					<h1>
						<a class="navbar-brand" href="index.jsp">MiaoEducation<br /></a>
					</h1>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1"
					style="width: 780px; margin-left: 60px">
					<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
						<li><a href="${basePath }room/findRoom"><span
								data-hover="About">视频直播</span></a></li>
						<li><a href="movie/listUI"><span
								data-hover="About">网络影院</span></a></li>
						<li><a href="myinfo.jsp"><span data-hover="Shortcodes">个人信息</span></a></li>

					</ul>
					<div class="clearfix"></div>
				</div>
				<p
					style="font-size: 16px; color: #6B4226; font-family: YouYuan; margin-top: 27px; width: 1160px; margin-right: 140px">欢迎,${user.userName}</p>
				<div class="login-pop"
					style="margin-right: 30px; width: 50px; height: 40px; margin-top: -40px">
					<div id="loginpop"
						style="width: 70px; height: 40px; margin-right: 0px; margin-top: 10px">
						<form action="${basePath}user/logout">
							<input type="submit"
								style="font-size: 18px; color: black; font-family: YouYuan"
								value="退出" />
						</form>
					</div>
				</div>
				<script src="js/menu_jquery.js"></script>
			</div>
		</c:if> </nav>
		<div class="clearfix"></div>
	</div>
	<!-- END of svw_header_wrapper -->
	<div class="banner banner5">
		<div class="container">
			<h2>我的影院</h2>
		</div>
	</div>

	<div id="svw_main" style="width: 966px">

		<!-- 网络影院视频内容核心代码 -->

		<div id="content" style="width: 700px">
			<div class="post">
				<h2>视频 ${video.name}</h2>
				<div id="player_window">
					<a id="player"
						href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/${video.url}"></a>

					<!-- 导入播放器   -->
					<script>
						flowplayer("player", "videoplayer/flowplayer-3.2.8.swf");
					</script>
				</div>

				<div class="meta">
					<span class="content"> <a
						href="movie/viewRoom/${video.id}">内容</a>
					</span> <span class="edit"> <a
						href="movie/editUI/${video.id}">编辑</a>
					</span> <span class="delete"> <a
						href="javascript:if(confirm('Are you sure to delete?'))location='movie/delete/${video.id}'">删除</a>
					</span>
					<div class="cleaner"></div>
				</div>
			</div>

			<div class="cleaner h20"></div>
			<h3>简介</h3>
			<div id="comment_section">
				<c:choose>
					<c:when test="${empty video.intro}">
						<p>快来添加简介吧~</p>
					</c:when>
					<c:otherwise>
						<p>${video.intro}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="cleaner h20"></div>
		</div>

		<div id="sidebar">
			<ul class="svw_list">
				<li><a href="movie/listUI">返回</a></li>
				<li><a href="movie/viewRoom/${video.id}">内容</a></li>
				<li><a href="movie/editUI/${video.id}">编辑</a></li>
				<li><a
					href="javascript:if(confirm('Are you sure to Delete?'))location='movie/delete/${video.id}'">删除</a></li>
			</ul>
			<div class="cleaner h30"></div>

			<!-- 显示5条最近添加 	<s:action name="SidebarRecent" executeResult="true">
				<s:param name="num">5</s:param>
			</s:action>-->
		
		</div>
		<div class="cleaner"></div>

		<!-- 网络影院视频内容核心代码结束 -->

	</div>

	<div class="footer" style="height: 230px">
		<div class="container">
			<div class="col-md-6 footer-left" style="margin-top: -10px">
				<ul>
					<li><a href="${basePath}/room/findRoom">视频直播</a></li>
					<li><a href="myinfo.jsp">个人信息</a></li>
				</ul>
				<form>
					<input type="text" placeholder="意见或建议请在这里反馈给小编哦" required="">
						<input type="submit" value="提交">
				</form>
			</div>
			<div class="col-md-3 footer-middle"></div>
			<div class="col-md-3 footer-right">
				<h3>Address</h3>
				<div class="address">
					<p>
						<span>石家庄南二环东路20号</span>
					</p>
				</div>
				<div class="phone">
					<p>168168168</p>
				</div>
			</div>
		</div>
	</div>
	<div class="copyright">
		<div class="container">
			<div class="copyright-left">
				<p>© 2016 米奥视频直播 | Design by 咖啡知音</p>
			</div>
			<div class="copyright-right">
				<ul>
					<li><a href="#" class="twitter"> </a></li>
					<li><a href="#" class="twitter facebook"> </a></li>
					<li><a href="#" class="twitter chrome"> </a></li>
					<li><a href="#" class="twitter pinterest"> </a></li>
					<li><a href="#" class="twitter linkedin"> </a></li>
					<li><a href="#" class="twitter dribbble"> </a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				$().UItoTop({
					easingType : 'easeOutQuart'
				});
			});
		</script>
	</div>
</body>
</div>
</html>