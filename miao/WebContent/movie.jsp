<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>网络影院</title>
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
	media="all">
<!--coustom css-->
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 900);
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
						<li><a href="gallery.jsp"><span data-hover="About">视频直播</span></a></li>
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
				<h2>Movie of Excellent Courses</h2>
			</div>
		</div>
		<!--header-->
		<!-- gallery -->
		<div class="gallery">
			<!-- Page Starts Here -->
			<div>
				<form method="get" action="" style="margin-left: 990px">
					<input type="text" class="box" name="s" id="s" class="inputText"
						placeholder="搜一搜" x-webkit-speech> <input type="button"
						value="搜索">
				</form>
			</div>
			<div class="content">
				<div class="container">
					<div class="gallery">
						<div class="gallery-top">
							<div class="view view-tenth">
								<img src="images/c1.jpg" alt="" />

								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师:夏雨荷
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>

							</div>
							<div class="view view-tenth">
								<img src="images/c2.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：俞敏洪
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c3.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：张雪峰
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c4.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：马云
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c5.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：亮亮
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c6.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：小花
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c7.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：大圣
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c8.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：天蓬
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c9.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：小草
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c10.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：嫦娥
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c11.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：玉兔
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="view view-tenth">
								<img src="images/c12.jpg" alt="" />
								<div class="mask">
									<h3>课程简介</h3>
									<p>
										课程：公开课<br />主讲教师：花木兰
									</p>
									<br /> <a href="about.jsp">
										<p>进入课程</p>
									</a>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- Page Ends Here -->
		</div>
		<!-- //gallery -->
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
			<!-- //container -->
		</div>
		<!--/footer-->
		<!--copy-rights-->
		<div class="copyright">
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
