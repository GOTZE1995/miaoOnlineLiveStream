<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Education Tutorial a Educational Category Flat Bootstrap Responsive Website Template | Gallery :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Education Tutorial Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<script type="text/javascript" >
	function check(){
		alert("请您先登录");
	}
</script>

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--bootstrap-->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<!--coustom css-->
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<!--script-->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- js -->
<script src="js/bootstrap.js"></script>
<!-- /js -->
<!--fonts-->
<!--/fonts-->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<!--script-->
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
				});
			});
</script>
<!--/script-->
</head>
<div id="main" style="width:1349px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	<body>
<!--header-->
		<div class="header" id="home">
			<nav class="navbar navbar-default">
				<c:if test="${sessionScope.myName==null}">
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
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
					<li><a href="gallery.jsp"><span data-hover="About">视频直播</span></a></li>
					<li><a href="gallery.jsp" onclick="check()"><span data-hover="Shortcodes">个人信息</span></a></li>

				</ul>
				<div class="clearfix"></div>
			</div>

			<div class="login-pop">
				<div id="loginpop">
					<a href="#" id="loginButton"><span>登录</span></a>
					<div id="loginBox">
						<form action="${basePath }user/login" id="loginForm" method="post">
							<fieldset id="body">
								<fieldset>
									<label for="email">用户名</label> <input type="text" name="userName" id="userName">
								</fieldset>
								<fieldset>
									<label for="password">密码</label> <input type="password"
										name="password" id="password">
								</fieldset>
								<input type="submit" id="login" value="登录"> <label
									for="checkbox"><input type="checkbox" id="checkbox">
									<i>记住密码</i></label>
							</fieldset>
							<span><a href="register.jsp">点我快速注册</a></span>
						</form>
					</div>
				</div>
			</div>
			<script src="js/menu_jquery.js"></script>
		</div>
		</c:if>
		<c:if test="${sessionScope.myName!=null}">
			<div class="container" style="width:1300px">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"> </span>
						<span class="icon-bar"> </span>
						<span class="icon-bar"> </span>
					</button>
					<h1><a class="navbar-brand" href="index.jsp">MiaoEducation<br/></a></h1>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="width:780px;margin-left:60px">
							<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
								<li><a href="gallery.jsp"><span data-hover="About">视频直播</span></a></li>
								<li><a href="myinfo.jsp"><span data-hover="Shortcodes">个人信息</span></a></li>

							</ul>
							<div class="clearfix"> </div>
						</div><!-- /.navbar-collapse -->
				<!-- /.container-fluid -->
				<p style="font-size:16px;color:#6B4226; font-family:YouYuan; margin-top:27px; width:1160px; margin-right:140px">欢迎,${myName}</p>
				<div class="login-pop" style="margin-right:30px; width:50px; height:40px; margin-top:-40px">
						<div id="loginpop" style="width:70px; height:40px; margin-right:0px; margin-top:10px">
							<form action="${basePath}user/logout">
								<input type="submit" style="font-size:18px;color:black;font-family:YouYuan" value="退出"/>
							</form>					
					    </div>
					    </div><script src="js/menu_jquery.js"></script>
			    </div>
		</c:if>
			</nav>
<!--/script-->
		   <div class="clearfix"> </div>
</div>
<!-- Top Navigation -->
<div class="banner banner5">
	<div class="container">
	<h2>Live Streaming</h2>
	</div>
</div>
<!--header-->
<!-- gallery -->
		<div class="gallery">
			<!-- Page Starts Here -->
			<div>
				<form method="get" action="" style="margin-left:990px">
         			<input type="text" class="box" name="s" id="s" class="inputText" placeholder="搜一搜" x-webkit-speech>
          			<input type="button" value="搜索"> 
				</form>
			</div>
			<div class="content">
				<div class="container">
					<div class="gallery">
						<div class="gallery-top">
							<div class="view view-tenth">
								<img src="images/g1.jpg" alt=""/>
								
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：Java与大数据<br/>主讲教师:夏雨荷</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
								
							</div>
							<div class="view view-tenth">
								<img src="images/g2.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：php与云计算<br/>主讲教师：俞敏洪</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g3.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：Html5<br/>主讲教师：张雪峰</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g4.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：计算机导论<br/>主讲教师：马云</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g5.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：数据库原理<br/>主讲教师：亮亮</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g6.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：C++<br/>主讲教师：小花</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g7.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：Java与移动智能设备<br/>主讲教师：大圣</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g8.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：数据结构<br/>主讲教师：天蓬</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g9.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：计算机组成原理<br/>主讲教师：小草</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g10.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：算法分析<br/>主讲教师：嫦娥</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g11.jpg" alt=""/>
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：R语言<br/>主讲教师：玉兔</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/g12.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>课程：软件测试基础<br/>主讲教师：花木兰</p>
									<br/>
									<a href="about.jsp">
										<p>
											进入课程
										</p>
									</a>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
			</div>
			<!-- Page Ends Here -->
		</div>
		<!-- //gallery -->
<!--footer-->
<div class="footer" style="height:230px">
		<!-- container -->
		<div class="container">
			<div class="col-md-6 footer-left" style="margin-top:-10px">
				<ul>
					<li><a href="gallery.jsp">视频直播</a></li>
					<li><a href="myinfo.jsp">个人信息</a></li>
				</ul>
				<form>
					<input type="text" placeholder="意见或建议请在这里反馈给小编哦" required="">
					<input type="submit" value="提交">
				</form>
			</div>
			<div class="col-md-3 footer-middle">
				
			</div>
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
<div class="copyright">
		<!-- container -->
		<div class="container">
			<div class="copyright-left">
			<p> © 2016 米奥视频直播 | Design by 咖啡知音</p>
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
			<div class="clearfix"> </div>
			
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
		$().UItoTop({ easingType: 'easeOutQuart' });
});
</script>
<a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!----> 
	</div>
<!--/copy-rights-->
	</body>
	</div>
</html>
