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
<title>网络影院</title>
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
<link rel="stylesheet" href="css/slimbox2.css" type="text/css" media="screen" /> 
<script type="text/JavaScript" src="js/jquery.mousewheel.js"></script> 
<script type="text/JavaScript" src="js/slimbox2.js"></script> 
<script type="text/javascript">
function doSearch() {
	document.forms[2].action = "movie/findMovie.do";
	document.forms[2].submit();
}
</script>
</head>

<body id="subpage">
	<div class="header" id="home">
			<nav class="navbar navbar-default"> 
			<c:if test="${user==null || user.userName==null || user.password==null || user.userName=='' || user.password==''}">
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
												placeholder="用户名" onblur="loginCheckUserName()" />
										</fieldset>
										<fieldset>
											<label for="password">密码</label> <input type="password"
												id="password" class="password" placeholder="密码"
												onblur="loginCheckUserNameAndPwd()" name="password"/>
										</fieldset>
										<input type="submit" id="login" value="登录"
											onclick="doSubmit()"/> <label for="checkbox"><input
											type="checkbox" id="checkbox"/> <i>记住密码</i></label>
									</fieldset>
									<span><a href="register.jsp">点我快速注册</a></span>
								</form>
							</div>
						</div>
					</div>
					<script src="js/menu_jquery.js"></script>
				</div>
			</c:if> <c:if test="${user!=null && user.userName!=null && user.userName!='' && user.password!=null && user.password!=''}">
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
		
		
		
		<div id="svw_main" style="width:966px">

			<!-- 点播列表实现核心代码  -->
			
			<h2>视频列表</h2>
            <div class="post">
	            <div class="meta">
		            <span>
		            	<b>视频管理</b>
		            </span>
		            <c:if test="${user.role.roleName=='教师'||user.role.roleName=='管理员'}">
		            <span class="add">
		            	<a href="movie/editUI/0">添加</a>
		            </span>
		            </c:if>
		         </div> 
	         </div>
	         
	         <div>
				<form method="post" action="movie/findMovie.do"
					style="margin-left: 735px">
					<input type="text" name="searchName" value="${searchName}" placeholder="输入视频名"/> 
					<input type="submit" value="搜索" onclick="doSearch()"/>
				</form>
				<br/>
			</div>
	         
	         <!-- 没有视频的提示信息 -->
	         <c:if test="${empty page}">
	         	<p>还没有视频哦，快来点击添加按钮进行视频上传吧</p>
	         	<div style="height:300px;"></div>
	         </c:if>
	         
	         <!-- 点播视频列表的循环显示  -->
	         <c:forEach items="${page.list}" var="video">
			 	<div class="col one_fourth gallery_box" style="${video.videostate.cssstyle}">
			        <a href="movie/viewRoom/${video.id}">
			        	<img src="${video.thumbnailurl}" alt="thumbnail" class="image_frame"/>
			        </a>
			        <h5>
			        	<a href="movie/viewRoom/${video.id}">${video.name}</a>
			        </h5>
			        <p>
			        	编辑时间:${video.edittime}
			        </p>
			        <c:if test="${user.role.roleName=='教师'||user.role.roleName=='管理员'}">
			        <p>
				        <a href="movie/editUI/${video.id}">编辑</a>|
				        <a href="javascript:if(confirm('Are you sure to Delete?'))location='${pageContext.request.contextPath}/movie/delete/${video.id}'">删除</a>
				    </p>
				    </c:if>
				    <c:if test="${user.role.roleName=='学生'||user.role.roleName=='游客'}">
				    <p>
				    	欢迎观看视频！
				    </p>
				    </c:if>
				 </div>
	          </c:forEach>
            
              <!-- 点播列表实现核心代码结束  -->
   		 <div class="cleaner"></div>
   		 <div style="font-size:18px;">
   		 <table width="97%" class="pageDown" border="0" cellspacing="0"
							cellpadding="0">
			<tr>
				<td align="right">共${page.totalCount }条记录，第
									${page.currentPageNum } 页，共 ${page.totalPageNum } 页
									&nbsp;&nbsp; 
					<a href="movie/findMovie.do?currentPage=${page.prePageNum }">上一页</a>&nbsp;&nbsp;
					<a href="movie/findMovie.do?currentPage=${page.nextPageNum }">下一页</a>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		</div>
	</div> <!-- END of svw_main -->
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