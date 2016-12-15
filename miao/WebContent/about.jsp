<%@page import="com.miao.chat.controller.ChatServer"%>
<%@page import="org.springframework.web.context.annotation.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<title>直播间</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="alternate icon" href="modify/assets/i/favicon.png">
<link rel="stylesheet" href="modify/assets/css/amazeui.min.css">
<link rel="stylesheet" href="modify/assets/css/app.css">

<!-- umeditor css -->
<link href="modify/umeditor/themes/default/css/umeditor.css" rel="stylesheet">

<style>
.title {
	text-align: center;
}

.chat-content-container {
	height: 29rem;
	overflow-y: scroll;
	border: 1px solid silver;
}
</style>




<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="keywords"
	content="Education Tutorial Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<script type="text/javascript">


	function swapLine1() {
		document.forms[0].action = "room/viewRoom?url=${url}&ip=192.168.200.129";
		document.forms[0].submit();
	}
	function swapLine2() {
		document.forms[0].action = "room/viewRoom?url=${url}&ip=192.168.200.128";
		document.forms[0].submit();
	}
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

	function openNewWindow() {
		window.open("check.jsp", "newwindow",
				"height=380, width=400,top=80,left=200")
		$.ajax({
			url : 'attendence/checkAttendentce.do',
			data : {},
			type : 'post',
			async : false,
		})
	}
	
</script>

<script type="text/javascript">
	function check() {
		alert("请您先登录");
	}
</script>

<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>

<link href="modify/css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">

<link href="modify/css/style.css" rel="stylesheet" type="text/css" />
<link href="modify/css/text.css" rel="stylesheet" type="text/css" />

<script src="modify/js/jquery-1.11.0.min.js"></script>
<script src="modify/js/bootstrap.js"></script>
<script type="text/javascript" src="modify/js/move-top.js"></script>
<script type="text/javascript" src="modify/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 900);
		});
	});
</script>

</head>
<div id="main"
	style="width: 1349px; height: 886px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	<body>
		<!--header-->
		<div class="header" id="home">
			<nav class="navbar navbar-default"> <c:if test="${user==null}">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
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
									data-hover="About">视频直播</span></a></li>													<li><a href="movie/findMovie">
<span data-hover="About">网络影院</span></a></li>
							<li><a href="about.jsp" onclick="check()"><span
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
					<script src="modify/js/menu_jquery.js"></script>
				</div>
			</c:if> <c:if test="${user!=null}">
				<div class="container" style="width: 1300px">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"> </span> <span class="icon-bar"> </span> <span
								class="icon-bar"> </span>
						</button>
						<h1>
							<a class="navbar-brand" href="index.jsp">MiaoEducation<br /></a>
						</h1>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1"
						style="width: 780px; margin-left: 60px">
						<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
							<c:if test="${user.role.roleName=='教师'}">
							<li onclick="openNewWindow()"><a><span>电子点名</span></a></li>
							</c:if> 
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
					<script src="modify/js/menu_jquery.js"></script>
				</div>
			</c:if> 
			</nav>
			<div class="clearfix"></div>
		</div>
		<!-- Top Navigation -->
		<div class="banner banner5">
			<div class="container">
				<h2>我的直播间</h2>
			</div>
		</div>
		<!--header-->
		<!-- About -->
		<div class="about">
			<div class="container">
				<div class="">
				
					<div class="col-md-5 abt-pic">
						<h3>直播间</h3>
						 线路1<input type="button" id="mainLine" name="line" onclick="swapLine1()" value="line1"/> 
						 线路2<input type="button" id="backLine" name="line" onclick= "swapLine2()" value="line2"/>
						 <c:if test="${ip=='192.168.200.129'}">当前为线路1</c:if>
						 <c:if test="${ip=='192.168.200.128'}">当前为线路2</c:if>
						 
						<object id="videoFrame" width='700' height='500' id='StrobeMediaPlayback'
							name='StrobeMediaPlayback' type='application/x-shockwave-flash'
							classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'>
							<param name='movie' value='swfs/StrobeMediaPlayback.swf' />
							<param name='quality' value='high' />
							<param name='bgcolor' value='#000000' />
							<param name='allowfullscreen' value='true' />
							<param id="paramLink" name='flashvars'
								value="&src=rtmp://${ip}${url }&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true" />
							<embed id="embedLink" src='swfs/StrobeMediaPlayback.swf' width='720'
								height='510' id='StrobeMediaPlayback' quality='high'
								bgcolor='#000000' name='StrobeMediaPlayback'
								allowfullscreen='true'
								pluginspage='http://www.adobe.com/go/getflashplayer'
								flashvars="&src=rtmp://${ip}${url }&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true"
								type='application/x-shockwave-flash'>
							</embed>
						</object>
					</div>

					<div class="text1" style="float: none; margin-right: 10px;">
						<div class="title">
							<div class="am-g am-g-fixed">
								<div class="am-u-sm-12" style="width:350px">
									<h3 style="width:270px;">聊天室</h3>
									<div class="chat-content" style="width: 350px; margin-left: 500px">
									<div class="am-g am-g-fixed chat-content-container"
										 style="height: 366px;margin-right:400px;margin-left:-400px;margin-top:0px">
									<div class="am-u-sm-12">
									<ul id="message-list"
										class="am-comments-list am-comments-list-flip">
									</ul>
								</div>
							</div>
						</div>

						<div class="message-input am-margin-top" style="margin-top: 0px">
							<div class="am-g am-g-fixed">
								<div class="am-u-sm-12">
									<form class="am-form">
										<div class="am-form-group" style="width: 350px; margin-left: 100px">
											<script type="text/plain" id="myEditor" style="width: 350px; height: 6rem;"></script>
										</div>
									</form>
								</div>
							</div>
							
							<div class="am-g am-g-fixed am-margin-top">
								<c:if test="${user==null}">
									<input id="nickname" value="游客<%=(int) (Math.random() * 100)%>" type="hidden" />
								</c:if>
								<c:if test="${user!=null}">
									<input id="nickname" value="${user.userName}" type="hidden" />
								</c:if>
								<div class="am-u-sm-6"
									style="width: 113px; margin-left: 321px; margin-top: -50px;">
									<button id="send" type="button" style="width:352px;height:35px;margin-left:-222px">
										<i class="am-icon-send"></i>发送
									</button>
								</div>
							</div>
						</div>
					</div>
				  </div>
				</div>

				<script src="modify/assets/js/jquery.min.js"></script>

				<script charset="utf-8" src="modify/umeditor/umeditor.config.js"></script>
				<script charset="utf-8" src="modify/umeditor/umeditor.min.js"></script>
				<script src="modify/umeditor/lang/zh-cn/zh-cn.js"></script>
				
				<c:if test="${user.headImg!=null}">
					<script>
						$(function() {
	
							// 初始化消息输入框
							var um = UM.getEditor('myEditor');
							
							// 新建WebSocket对象，最后的/chatroom跟服务器端的@ServerEndpoint("/chatroom")对应
							var socket = new WebSocket(
									'ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/chatroom');
							// 处理服务器端发送的数据
							socket.onmessage = function(event) {
								addMessage(event.data);
							};
							// 点击Send按钮时的操作
							$('#send')
									.on(
											'click',
											function() {
												var nickname = $(
														'#nickname').val();
												if (!um.hasContents()) { // 判断消息输入框是否为空
													// 消息输入框获取焦点
													um.focus();
									
													// 添加抖动效果
													$('.edui-container').addClass('am-animation-shake');
												}else{
													// 发送消息
													socket.send(JSON.stringify({
																		content : um.getContent(),
																		nickname : nickname,
																		headImg : '${user.headImg}'
																	}));
													// 清空消息输入框
													um.setContent('');
													// 消息输入框获取焦点
													um.focus();
												}
											});
	
							// 把消息添加到聊天内容中
							function addMessage(message) {
								message = JSON.parse(message);
								
								
								var messageItem = '<li class="am-comment">'
										+ '<a href="javascript:void(0)" ><img src=" '+message.headImg+' " alt="" class="am-comment-avatar" width="48" height="48"/></a>'
										+ '<div class="am-comment-main"><header class="am-comment-hd"><div class="am-comment-meta">'
										+ '<a href="javascript:void(0)" class="am-comment-author">'
										+ message.nickname + '</a> <time>'
										+ message.date
										+ '</time></div></header>'
										+ '<div class="am-comment-bd">'
										+ message.content
										+ '</div></div></li>';
									
										
								$(messageItem).appendTo('#message-list');
								// 把滚动条滚动到底部
								$(".chat-content-container")
										.scrollTop(
												$(".chat-content-container")[0].scrollHeight);
							}
						});
					</script>
				</c:if>
				
				<c:if test="${user.headImg==null}">
					<script>
						$(function() {
	
							// 初始化消息输入框
							var um = UM.getEditor('myEditor');
							
							// 新建WebSocket对象，最后的/chatroom跟服务器端的@ServerEndpoint("/chatroom")对应
							var socket = new WebSocket(
									'ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/chatroom');
							// 处理服务器端发送的数据
							socket.onmessage = function(event) {
								addMessage(event.data);
							};
							// 点击Send按钮时的操作
							$('#send')
									.on(
											'click',
											function() {
												var nickname = $(
														'#nickname').val();
												if (!um.hasContents()) { // 判断消息输入框是否为空
													// 消息输入框获取焦点
													um.focus();
									
													// 添加抖动效果
													$('.edui-container').addClass('am-animation-shake');
												}else{
													// 发送消息
													socket.send(JSON.stringify({
																		content : um.getContent(),
																		nickname : nickname,
																		headImg : 'images/miao.jpg'
																	}));
													// 清空消息输入框
													um.setContent('');
													// 消息输入框获取焦点
													um.focus();
												}
											});
	
							// 把消息添加到聊天内容中
							function addMessage(message) {
								message = JSON.parse(message);
								
								var messageItem = '<li class="am-comment">'
										+ '<a href="javascript:void(0)" ><img src=" '+message.headImg+' " alt="" class="am-comment-avatar" width="48" height="48"/></a>'
										+ '<div class="am-comment-main"><header class="am-comment-hd"><div class="am-comment-meta">'
										+ '<a href="javascript:void(0)" class="am-comment-author">'
										+ message.nickname + '</a> <time>'
										+ message.date
										+ '</time></div></header>'
										+ '<div class="am-comment-bd">'
										+ message.content
										+ '</div></div></li>';
									
								$(messageItem).appendTo('#message-list');
								// 把滚动条滚动到底部
								$(".chat-content-container")
										.scrollTop(
												$(".chat-content-container")[0].scrollHeight);
							}
						});
					</script>
				</c:if>

					</div>

				</div>
			</div>
		</div>
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
			
		</div>
	</body>
</div>
</html>


