<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%= basePath %>"/>
<title>米奥首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Education Tutorial Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<script type="text/javascript" >
	function check(){
		alert("请您先登录");
	}
	
	function loginCheckUserName(){
		var username = $('#username').val();
		$.ajax({
			url:'user/checkUsername.do',
			data:{'username':username},
			type:'post',
			async:false,
			success:function(result){
				if (result == "pass") {
					alert("该用户不存在");
				}
			}
		})
	}
	
	function loginCheckUserNameAndPwd(){
		var username = $('#username').val();
		var password = $('#password').val();
		$.ajax({
			url:'user/loginCheckUserNameAndPwd.do',
			data:{'password':password,'username':username},
			type:'post',
			async:false,
			success:function(result){
				if (result != "pass") {
					alert("用户名与密码不匹配");
				}
			}
		})
	}
	
	function doSubmit(){
		if($('#username').val()!=""&&$('#password').val()!=""){
			document.forms[0].submit();
		}else{
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

<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr.custom.js"></script>
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
<style>
.border {
	margin-left: 100px;
	margin-bottom: 10px;
}

.border2 {
	margin-left: 100px;
	margin-bottom: 20px;
}

.texts {
	font-size: 20px;
	font-weight: bold;
}

.border3 {
	margin-left: 100px;
	margin-top: 10px;
}

.border4 {
	margin-left: 100px;
	margin-top: 20px;
}
</style>

</head>
<div id="main" style="width:1349px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
	<body style="width:1349px">
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
					<!-- <li><a href="myinfo.jsp"><span data-hover="Shortcodes">个人信息</span></a></li> -->
					<li><a href="" onclick="check()"><span data-hover="Shortcodes">个人信息</span></a></li>
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
									<label for="email">用户名</label> <input type="text" name="userName" class="username" id="username" placeholder="用户名" onblur="loginCheckUserName()"/>
								</fieldset>
								<fieldset>
									<label for="password">密码</label> <input type="password" id="password" class="password" placeholder="密码" onblur="loginCheckUserNameAndPwd()" name="password">
								</fieldset>
								<input type="submit" id="login" value="登录" onclick="doSubmit()"> <label
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
		<div class="clearfix"></div>
	</div>

	<div class="banner">
	<div class="container">
	<script src="js/responsiveslides.min.js"></script>
 <script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
  </script>
<div class="slider">
		   <div class="callbacks_container">
			  <ul class="rslides" id="slider">
				 <li>	    
				 	<h3>视频直播</h3>      
					 <p>视频直播是指利用互联网及流媒体技术进行直播，视频因融合了图像、文字、声音等丰富元素，声形并茂，效果极佳，逐渐成为互联网的主流表达方式。使用该网站，可实现学生端和教师端的视频交互。</p>		          
					<div class="readmore">
				 	<a href="gallery.jsp">马上体验<i class="glyphicon glyphicon-menu-right"> </i></a>
				 	</div>
				 </li>
				 <li>	
				 	<h3>聊天室</h3>            
					 <p>聊天室(chat room)是一个网上空间，为了保证谈话的焦点，聊天室通常有一定的谈话主题。任何一个联入Internet、使用正确的聊天软件，并且渴望谈论的人都可以享受其乐趣。在课上学生和教师能有更多的学术交流</p>		          
				 <div class="readmore">
				 <a href="about.jsp">马上体验<i class="glyphicon glyphicon-menu-right"> </i></a>
				 </div>
				 </li>
				 <li>	
				 	<h3>网络影院</h3>            
					 <p>随着Internet和Intranet向宽带、高速、多媒体化方向迅速发展，以计算机网络为基础的现代教育手段将得到广泛应用。要促进基于Internet的远程教育，需要一大批网络课程，开发高质量的网络课程，是当前现代教育技术工作的重要内容。</p>		          
				<div class="readmore">
				 <a href="movie.jsp">马上体验<i class="glyphicon glyphicon-menu-right"> </i></a>
				 </div>
				 </li>
			  </ul>
		  </div>
	  </div>
</div>			
	</div>
<!--header-->
<!--weelcome-->
<div class="welcome" style="width:1349px;">
	<div class="container">
		<h2>Welcome To Miao Education System</h2>
		<p>Education began in the earliest prehistory, as adults trained the young in the knowledge and skills deemed necessary in their society. In pre-literate societies this was achieved orally and through imitation. Story-telling passed knowledge,</p>
	</div>
</div>
<!--effect-grid-->
<div class="effect-grid" style="width:1349px">
<div class="border">
<p>----------------------------------------------------------------------------------------------------------------------------------------------</p>
</div>
<div class="border2">
<span  class="texts">网络影院</span>
</div>
	<div class="container" style="width:1200px">
		<ul class="grid cs-style-5">
				<li>
					<figure>
						<img src="images/mysql.jpg" alt="img03">
						<figcaption>
							<h3>数据库分析设计</h3>
							<span>小明</span>
							<a href="about.jsp">进入学习</a>
						</figcaption>
					</figure>
				</li>
				<li>
					<figure>
						<img src="images/a2.jpg" alt="img04">
						<figcaption>
							<h3>JAVA与大数据</h3>
							<span>小明</span>
							<a href="about.jsp">进入学习</a>
						</figcaption>
					</figure>
				</li>
				<li>
					<figure>
						<img src="images/java.jpg" alt="img01">
						<figcaption>
							<h3>Java研究学习</h3>
							<span>小明</span>
							<a href="about.jsp">进入学习</a>
						</figcaption>
					</figure>
				</li>
				<li>
					<figure>
						<img src="images/php.jpg" alt="img02">
						<figcaption>
							<h3>PHP研究和学习</h3>
							<span>小明</span>
							<a href="about.jsp">进入学习</a>
						</figcaption>
					</figure>
				</li>
				<li>
					<figure>
						<img src="images/h5.jpg" alt="img06">
						<figcaption>
							<h3>HTML5学习</h3>
							<span>小明</span>
							<a href="about.jsp">进入学习</a>
						</figcaption>
					</figure>
				</li>
				<li>
					<figure>
						<img src="images/c++.jpg" alt="img05">
						<figcaption>
							<h3>C++语言</h3>
							<span>小明</span>
							<a href="about.jsp">进入学习</a>
						</figcaption>
					</figure>
				</li>
			</ul>
	</div>
<div class="border3" style="margin-left:1200px">
<span  class="texts"><a href="movie.jsp">更多</a></span>
</div>
<div class="border4">
<p>----------------------------------------------------------------------------------------------------------------------------------------------</p>
</div>
</div>
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
