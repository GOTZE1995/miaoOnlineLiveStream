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
<title>个人信息页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Education Tutorial Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!--bootstrap-->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!--coustom css-->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css-info/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!--script-->
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- js -->
<script src="js/bootstrap.js"></script>
<!-- /js -->
<!--fonts-->

<!--/fonts-->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<!--script-->
<style>
#spanEmail {
	margin-top: 10px;
	color: red;
	display: block;
}

#spanPassword {
	margin-top: 10px;
	color: red;
	display: block;
}

#spanRoom {
	color: red;
}
</style>
<script>
	function renewEmailState() {
		document.getElementById("submitSpan").innerHTML = "";
		document.getElementById("email").value = "";
		document.getElementById("spanEmail").innerHTML = "";
	}
	function renewPwdState() {
		document.getElementById("password").value = "";
		document.getElementById("spanPassword").innerHTML = "";
	}
	/*校验新的email，获取result值，当result为空值，提示不能为空，
	 * 当result为repeat，代表邮箱重复，则提示重复，并将input中的值清空
	 */
	function checkNewEmail() {
		var newEmail = $('#email').val();
		$.ajax({
					url : 'user/checkNewEmail.do',
					data : {
						'newEmail' : newEmail
					},
					type : 'post',
					async : false,
					success : function(result) {
						if (result == "null") {
							document.getElementById("spanEmail").innerHTML = "邮箱不能为空";
						} else if (result == "repeat") {
							document.getElementById("spanEmail").innerHTML = "该邮箱已经被注册";
							document.getElementById("email").value = "";
						} else {
							document.getElementById("spanEmail").innerHTML = "";
						}
					}
				})
	}
	function checkNowPwd() {
		var nowPwd = $('#password').val();
		$
				.ajax({
					url : 'user/checkNowPwd.do',
					data : {
						'nowPwd' : nowPwd
					},
					type : 'post',
					async : false,
					success : function(result) {
						if (result == "null") {
							document.getElementById("spanPassword").innerHTML = "密码不能为空";
						} else if (result == "false") {
							document.getElementById("spanPassword").innerHTML = "与原密码不一致";
						} else {
							document.getElementById("spanPassword").innerHTML = "";
						}
					}
				})
	}
	function doSubmit() {
		if ($('#email').val() != "" && $('#nickName').val() != "") {
			document.forms[2].submit();
		} else {
			document.getElementById("submitSpan").innerHTML = "对不起，请填完所有信息再进行更新";
		}
	}

	function checkRoom() {
		var roleName="${user.role.roleName}";
		if(roleName=="管理员"||roleName=="教师"){
			$.ajax({
				url : 'room/checkRoom.do',
				data : {},
				type : 'post',
				async : false,
				success : function(result) {
					if (result == "true") {
						window.location.href = "tv-register.jsp";
					} else {
						document.getElementById("spanRoom").innerHTML = "对不起,你已经注册了直播间";
					}
				}
			})
		}else{
			document.getElementById("spanRoom").innerHTML="对不起，你没有该权限";
		}
	}
</script>
<script>
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true, // 100% fit in a container
			closed : 'accordion', // Start closed if in accordion view
			activate : function(event) { // Callback function if tab is switched
				var $tab = $(this);
				var $info = $('#tabInfo');
				var $name = $('span', $info);
				$name.text($tab.text());
				$info.show();
			}
		});
	});
</script>
<!--/script-->
</head>
<div id="main"
	style="width: 1349px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	<body>
		<!--header-->
		<div class="header" id="home">
			<nav class="navbar navbar-default">
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
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1"
					style="width: 780px; margin-left: 60px">
					<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
						<li><a href="${basePath }room/findRoom"><span
								data-hover="About">视频直播</span></a></li>
						<li><a href="movie/findMovie"><span
									data-hover="About">网络影院</span></a></li>
						<li><a href="myinfo.jsp"><span data-hover="Shortcodes">个人信息</span></a></li>

					</ul>
					<div class="clearfix"></div>
				</div>
				<!-- /.navbar-collapse -->
				<!-- /.container-fluid -->
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
			</nav>
			<!--/script-->
			<div class="clearfix"></div>
		</div>
		<!-- Top Navigation -->
		<div class="banner banner5">
			<div class="container">
				<h2>Personal Information</h2>
			</div>
		</div>
		<!--header-->

		<div class="main">
			<div class="content">
				<div class="sap_tabs">
					<div id="horizontalTab"
						style="display: block; width: 100%; margin: 0px;">
						<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
						<script type="text/javascript">
							$(document).ready(function() {
								$('#horizontalTab').easyResponsiveTabs({
									type : 'default', //Types: default, vertical, accordion           
									width : 'auto', //auto or any width like 600px
									fit : true, // 100% fit in a container
									closed : 'accordion', // Start closed if in accordion view
									activate : function(event) { // Callback function if tab is switched
										var $tab = $(this);
										var $info = $('#tabInfo');
										var $name = $('span', $info);
										$name.text($tab.text());
										$info.show();
									}
								});

								$('#verticalTab').easyResponsiveTabs({
									type : 'vertical',
									width : 'auto',
									fit : true
								});
							});
						</script>

						<style>
.fileInputContainer {
	height: 250px;
	background: url(${user.headImg });
	position: relative;
	width: 270px;
	background-size:100% 100%;
	background-repeat: no-repeat;
}

.fileInput {
	height: 250px;
	overflow: hidden;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
}
</style>
						<div>
							<div class="port-left">
								<ul class="resp-tabs-list">
									<form action="" method="post">
										<div class="fileInputContainer">
											<input class="fileInput" type="file" name="">
										</div>
									</form>
									<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>基本信息</span></li>
									<li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>修改密码</span></li>
									<li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>功能相关</span></li>
								</ul>
							</div>

							<div class="port-right">
								<div class="resp-tabs-container">
									<!-- Tab 0 -->
									<div class="tab-1 resp-tab-content"
										aria-labelledby="tab_item-0">
										<div class="profile-content">
											<h3>${user.userName}</h3>
											<form
												action="${pageContext.request.contextPath}/user/edit.do"
												method="post" id="editMyInfo" enctype="multipart/form-data">
												<h4>邮箱账号</h4>
												<div class="email-group">
													<div class="email-form">
														<input type="text" id="email" name="email"
															value="${user.email}" onblur="checkNewEmail()"
															onfocus="renewEmailState()" /> <br />
														<span id="spanEmail"></span>
													</div>
													<div class="clear"></div>
												</div>
												<h4>昵称</h4>
												<div class="phone-group">
													<div class="cell-form">
														<input id="nickName" type="text" name="nickName"
															value="${user.nickName}" onfocus="this.value = '';"
															onblur="if (this.value == '') {this.value = '${user.nickName}';}">
													</div>
													<div class="clear"></div>
												</div>
												<h4>上传头像</h4>
												<div class="phone-group">
													<div class="cell-form">
														<input type="file" name="file" /> 
														<img src="${user.headImg }" widht='100' height='90'  style='display:none'/>
													</div>
												</div>
												<br/>
												<div style="margin-top:30px;">
												 <input type="button" value="更新"
													onclick="doSubmit()" /> <span id="submitSpan"></span>
												</div>
											</form>
										</div>
										<div class="clear"></div>

									</div>

									<!-- Tab 1 -->
									<div class="tab-1 resp-tab-content"
										aria-labelledby="tab_item-1">
										<div class="social">
											<h3>Mark Carter</h3>
											<form
												action="${pageContext.request.contextPath}/user/editPwd.do"
												method="post" id="editMyInfo">
												<div class="password-info">

													<h4>当前密码：</h4>
													<div class="cell-form" style="width: 446px">
														<input type="password" id="password" name="nowPwd"
															onblur="checkNowPwd()" onfocus="renewPwdState()" /><span
															id="spanPassword"></span>
													</div>



													<h4>新的密码：</h4>
													<div class="cell-form" style="width: 446px">
														<input type="password" name="newPwd" />
													</div>

													<div class="nulls"></div>

													<div class="sub">
														<input type="submit" value="更新密码" />
													</div>
												</div>
											</form>
										</div>
										<div class="clear"></div>
									</div>

								</div>

								<!-- Tab 2 -->
								<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
									<div class="hobby">
										<h3>${user.userName}</h3>

										<div class="hobby-info" margin-top="80px">
											<a href="#" onclick="checkRoom()">
												<h5>申请直播间</h5>
											</a>
											<div style="margin-top: 20px;">
												<span id="spanRoom"></span>
											</div>
											<br /> <br /> <a href="myTVinfo.jsp">
												<h5>编辑直播间</h5>
											</a> <br /> <br />
											<div style="margin-left: 30px;">
												<form
													action="${pageContext.request.contextPath}/room/editRoomState.do"
													method="post">
													<c:if test="${user.room.status=='0'}">
														<input type="submit" value="开始直播" />
													</c:if>
													<c:if test="${user.room.status=='1'}">
														<input type="submit" value="结束直播" />
													</c:if>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
</div>


<!--footer-->
<div class="footer" style="width: 1349px">
	<!-- container -->
	<div class="container">
		<div class="col-md-6 footer-left">
			<ul>
				<li><a href="${basePath }room/findRoom">视频直播</a></li>
				<li><a href="contact.jsp">个人信息管理</a></li>
			</ul>
			<form>
				<input type="text" placeholder="意见或建议请在这里反馈给小编哦" required="">
				<div margin-left="5px">
					<input type="submit" value="提交">
				</div>
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
	<!-- //container -->
</div>
<!--/footer-->
<!--copy-rights-->
<div class="copyright" style="width: 1349px">
	<!-- container -->
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
	<!-- //container -->
	<!---->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
			containerID: 'toTop', // fading element id
			containerHoverID: 'toTopHover', // fading element hover id
			scrollSpeed: 1200,
			easingType: 'linear' 
			};
			 */
			$().UItoTop({
				easingType : 'easeOutQuart'
			});
		});
	</script>
	<a href="#to-top" id="toTop" style="display: block;"> <span
		id="toTopHover" style="opacity: 1;"> </span></a>
	<!---->
</div>
<!--/copy-rights-->
</body>
</div>
</html>