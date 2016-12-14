<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Video, FFmpeg, JavaEE" />
<meta name="author" content="Lei Xiaohua" />
<meta name="description" content="The simplest video website based on JavaEE and FFmpeg" />

<title>视频编辑</title>
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

<!-- validationEngine -->
<link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css"/>
<script src="js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#videoform1").validationEngine();
});
</script> 

</head>

<body id="subpage">

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
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right margin-top cl-effect-2">
							<li><a href="${basePath }room/findRoom"><span
									data-hover="About">视频直播</span></a></li>
							<li><a href="movie/listUI"><span
									data-hover="About">网络影院</span></a></li>
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
					<script src="js/menu_jquery.js"></script>
				</div>
			</c:if> </nav>
			<!--/script-->
			<div class="clearfix"></div>
		</div>
		<!-- Top Navigation -->
		<div class="banner banner5">
			<div class="container">
				<h2>视频编辑</h2>
			</div>
		</div><!-- END of svw_header_wrapper -->

		<div id="svw_main" style="width:966px">
		
			<!-- 视频编辑核心代码  -->
			
			<div id="content" style="width:700px">
		    	<div class="post">
		    	
		    		<!-- 如果视频列表为空，则显示添加视频的标题，否则显示编辑视频-视频名字的标题 -->
		    		<c:choose>
		    			<c:when test="${empty video}">
		    				<h2>添加视频</h2>
		    			</c:when>
		    			<c:otherwise>
		    				<h2>编辑视频 - ${video.name}</h2>
		    			</c:otherwise>
		    		</c:choose>
		    		
		            <div class="float_l">
		                <div id="contact_form">
			                <form id="videoform1" method="post" 
							                <c:choose>
								    			<c:when test="${!empty video}">
								    				name="update" action="movie/update"
								    			</c:when>
								    			<c:otherwise>
								    				name="add" action="movie/add"
								    			</c:otherwise>
							    			</c:choose>
			    							 enctype ="multipart/form-data">
			                 	<label for="name">名称:</label> 
			                 	<input  type="hidden" name="id" value="${video.id }"/>
			                 	<input type="text" id="name" name="name" value="${video.name}" class="validate[required] required input_field" />
			                    <div class="cleaner h10"></div>
			    					<!-- 如果视频列表为空，显示视频文件上传 -->
						    		<c:if test="${empty video}">
					    				<label for="videofile">文件:</label> 
					    				<input type="file" id="file" name="file" class="validate[required] required input_field" />
					                    <div class="cleaner h10"></div>
					    			</c:if>
						    			
			                        <label for="intro">简介:</label> 
			                        <textarea id="intro" name="intro" rows="100" cols="0" class="required">${video.intro}</textarea>
			                        <div class="cleaner h10"></div>
			                        <input type="submit" value="提交" id="submit" name="submit" class="submit_btn float_l" />
			                    </form>
		                 </div>
		             </div>
		        </div>
		        <div class="cleaner"></div>
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
		
				<!-- 显示5个最近添加    <s:action name="SidebarRecent" executeResult="true">
		           	<s:param name="num">5</s:param>
		        </s:action>-->
		      
		    </div> 
		    <div class="cleaner"></div>
		    
		    <!-- 视频编辑核心代码结束 -->
		    
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