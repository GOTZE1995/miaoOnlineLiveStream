<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册直播间页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Education Tutorial Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css-info/style.css" rel="stylesheet" type="text/css"
	media="all" />
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
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
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1"
					style="width: 780px; margin-left: 60px">
					<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
						<li><a href="gallery.jsp"><span data-hover="About">视频直播</span></a></li>
						<li><a href="movie/findMovie"><span
									data-hover="About">网络影院</span></a></li>
						<li><a href="myinfo.jsp"><span data-hover="Shortcodes">个人信息</span></a></li>

					</ul>
					<div class="clearfix"></div>
				</div>
				<p
					style="font-size: 16px; color: #6B4226; font-family: YouYuan; margin-top: 27px; width: 1160px; margin-right: 140px">欢迎,${myName}</p>
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
				<h2>TV Information</h2>
			</div>
		</div>
		<!--header-->

		<div class="main">
			<div class="content" style="height: 600px; width: 880px">
				<div class="sap_tabs">
					<div id="horizontalTab"
						style="display: block; width: 100%; margin: 0px;">
						<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
						<script type="text/javascript">
							$(document).ready(function() {
								$('#horizontalTab').easyResponsiveTabs({
									type : 'default',            
									width : 'auto', 
									fit : true,
									closed : 'accordion', 
									activate : function(event) { 
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
						<div>
							<div class="port-left">
								<ul class="resp-tabs-list">
									<img src="images-info/2.png" alt="" />
									<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>申请信息</span></li>
								</ul>
							</div>

							<div class="port-right">

								<div class="resp-tabs-container">

									<div class="tab-1 resp-tab-content"
										aria-labelledby="tab_item-0">
										<div class="profile-content">
											<h3>${user.userName}</h3>
											<form
												action="${pageContext.request.contextPath}/room/registMyRoom.do"
												method="post">
												<h4>房间名称</h4>
												<div class="phone-group">
													<div class="cell-form">

														<input type="text" name="roomName" value="xx直播间"
															onfocus="this.value = '';"
															onblur="if (this.value == '') {this.value = 'xx直播间';}">

													</div>

													<div class="clear"></div>
												</div>
												<h4>联系电话</h4>
												<div class="email-group">
													<div class="email-form">

														<input type="text" name="phone" class="fb-ico"
															value="12345678" onfocus="this.value = '';"
															onblur="if (this.value == '') {this.value = 'markcarter@gmail.com';}">

													</div>
													<div class="clear"></div>
												</div>
												<h4>房间公告</h4>
												<div class="phone-group">
													<div class="cell-form">
														<textarea name="memo" rows="4" cols="49"
															placeholder="可以输入多行文本"></textarea>
													</div>
													<div class="clear"></div>
												</div>
												<!-- 直播间的所属用户 -->
												<input type="hidden" name="user.userName" class="InputStyle"
													value="${user.userName}" />
												<h4>直播分类</h4>
												<div class="phone-group">
													<div class="cell-form">
														<select>
															<option>Java</option>
															<option>php</option>
															<option>c</option>
															<option>html5</option>
															<option>测试</option>
															<option>操作系统</option>
														</select> <input style="margin-left: 0px" type="submit"
															value="确认申请" />
													</div>
												</div>
											</form>
										</div>
										<div class="clear"></div>

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
		<div class="footer" style="height: 230px">
			<!-- container -->
			<div class="container">
				<div class="col-md-6 footer-left" style="margin-top: -10px">
					<ul>
						<li><a href="gallery.jsp">视频直播</a></li>
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