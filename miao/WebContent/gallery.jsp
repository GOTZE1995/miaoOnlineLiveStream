<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>Education Tutorial a Educational Category Flat Bootstrap
	Responsive Website Template | Gallery :: w3layouts</title>

<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = "room/findRoom.do";
		document.forms[0].submit();
	}
</script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Education Tutorial Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<script type="text/javascript">
	function check() {
		alert("请您先登录");
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
					alert("该用户不存在");
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
					alert("用户名与密码不匹配");
				}
			}
		})
	}

	function doSubmit() {
		if ($('#username').val() != "" && $('#password').val() != "") {
			document.forms[0].submit();
		} else {
			alert("用户名或密码不能为空");
		}
	}
</script>

<script type="application/x-javascript">
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 



</script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
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
	style="width: 1349px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	<body>
		<div class="header" id="home">
			<nav class="navbar navbar-default"> <c:if test="${user==null}">
				<div class="container">
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
									data-hover="About">视频直播</span></a></li>
							<li><a href="${basePath }room/findRoom" onclick="check()"><span
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
												placeholder="用户名" onblur="loginCheckUserName()" />
										</fieldset>
										<fieldset>
											<label for="password">密码</label> <input type="password"
												id="password" class="password" placeholder="密码"
												onblur="loginCheckUserNameAndPwd()" name="password">
										</fieldset>
										<input type="submit" id="login" value="登录"
											onclick="doSubmit()"> <label for="checkbox"><input
											type="checkbox" id="checkbox"> <i>记住密码</i> </label>
									</fieldset>
									<span><a href="register.jsp">点我快速注册</a></span>
								</form>
							</div>
						</div>
					</div>
					<script src="js/menu_jquery.js"></script>
				</div>
			</c:if> <c:if test="${user!=null}">
				<div class="container" style="width: 1300px">
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
						id="bs-example-navbar-collapse-1"
						style="width: 780px; margin-left: 60px">
						<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
							<li><a href="${basePath }room/findRoom"><span
									data-hover="About">视频直播</span></a></li>
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
		<div class="banner banner5">
			<div class="container">
				<h2>Live Streaming</h2>
			</div>
		</div>
		<div class="gallery" style="width: 1349px;">
			<div>
				<form method="post" action="room/findRoom.do"
					style="margin-left: 990px">
					<input type="text" name="searchName" value="${searchName}"
						placeholder="输入课程名"> <input type="submit" value="搜索"
						onclick="doSearch()">
				</form>
			</div>
			<div class="content">
				<div class="container" style="width: 1200px; align: center;">
					<div class="gallery" style="width: 1200px; align: center;">
						<c:if test="${not empty rooms}">
							<c:forEach var="room" items="${page.list}">
								<c:if test="${room.status=='1'}">
									<div class="view view-tenth"
										style="width: 270px; height: 182px">
										<img src="${room.user.headImg}" alt="" />
										<div class="mask" style="width: 249px; height: 163px">
											<h3>课程简介</h3>
											<p>
												课程：${room.roomName}<br /> 主讲教师:${room.user.userName}
											</p>
											<br /> <a href="room/viewRoom?url=${room.url }">
												<p>进入课程</p>
											</a>
										</div>
									</div>
									&nbsp;&nbsp;
								</c:if>
							</c:forEach>
							<c:forEach var="room" items="${page.list}">
								<c:if test="${room.status=='0'}">
									<div class="view view-tenth"
										style="width: 270px; height: 182px">
										<img src="${basePath }images/miao.jpg" alt="" />
										<div class="mask" style="width: 249px; height: 163px">
											<h3>课程简介</h3>
											<p>
												课程：${room.roomName}<br /> 主讲教师:${room.user.userName}
											</p>
											<br />
											<p>休息中...</p>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty rooms }">
							<tr align="center" class="TableDetail1">
								<td colspan="6">没有查到直播间信息&nbsp;</td>
							</tr>
						</c:if>
						<table width="97%" class="pageDown" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td align="right">共${page.totalCount }条记录，第
									${page.currentPageNum } 页，共 ${page.totalPageNum } 页
									&nbsp;&nbsp; <a
									href="room/findRoom.do?currentPage=${page.prePageNum }">上一页</a>&nbsp;&nbsp;
									<a href="room/findRoom.do?currentPage=${page.nextPageNum }">下一页</a>&nbsp;&nbsp;
								</td>
							</tr>
						</table>
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
			<script type="text/javascript">
				$(document).ready(function() {
					$().UItoTop({
						easingType : 'easeOutQuart'
					});
				});
			</script>
			<a href="#to-top" id="toTop" style="display: block;"> <span
				id="toTopHover" style="opacity: 1;"> </span></a>
		</div>
	</body>
</div>
</html>
