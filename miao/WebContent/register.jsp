<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>用户注册页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="assets/css/reset.css">
<link rel="stylesheet" href="assets/css/supersized.css">
<link rel="stylesheet" href="assets/css/style.css">

<style>
	span{
	margin-top:10px;
	color:red;
	display:block;
	}
</style>
<script>
function renewUsernameState(){
	document.getElementById("userSpan").innerHTML = "";
}
function renewEmailState(){
	document.getElementById("emailSpan").innerHTML = "";
}

function checkUsername(){
		var username = $('#username').val();
		$.ajax({
			url:'user/checkUsername.do',
			data:{'username':username},
			type:'post',
			async:false,
			success:function(result){
				if (result != "pass") {
					document.getElementById("userSpan").innerHTML = "该用户名已经被注册";
					document.getElementById("username").value="";
				}
			}
		})
}

function checkEmail(){
	var email=$('#email').val();
	$.ajax({
		url:'user/checkEmail.do',
		data:{'email':email},
		type:'post',
		async:false,
		success:function(result){
			if (result != "pass") {
				document.getElementById("emailSpan").innerHTML = "该邮箱已经被注册";
				document.getElementById("email").value="";
			}
		}
	})
}

function doSubmit(){
	if($('#username').val()!=""&&$('#email').val()!=""){
		document.forms[0].submit();
	}else{
		document.getElementById("submitSpan").innerHTML = "请填完所有的用户信息再进行提交";
	}
}

</script>

</head>
<body>
	<div class="page-container">
		<h1>米奥课堂</h1>
		<form action="${pageContext.request.contextPath}/user/regist.do" method="post">
			<div>
			<input type="text" name="userName" class="username" id="username" placeholder="用户名" onblur="checkUsername()" onfocus="renewUsernameState()"/>
			<span id="userSpan"></span>
			</div>
			<input type="text" name="nickName" class="nickname" placeholder="昵称"/>
			<div>
			<input type="password" name="password" class="password" placeholder="密码"/>
		    <input type="email" name="email" class="email" id="email" placeholder="邮箱" onblur="checkEmail()" onfocus="renewEmailState()"/>
		    <span id="emailSpan"></span>    
		    </div>
		    <input type="radio" name="myrole"
				value="teacher" checked style="width: 35px; height: 10px"/>教师
				<br/>
			 <input type="radio" name="myrole" value="student"
				style="width: 35px; height: 10px"/>学生
			 <input type="button" value="提交" onclick="doSubmit()" />
			 <div>
			 <span id="submitSpan"></span>
			 </div>
			<div class="error">
				<span>+</span>
			</div>
		</form>
		<div class="connect">
			<p>欢迎加入米奥课堂</p>
			<br /> <br /> <br /> <br /> <br /> <br />
		</div>
	</div>
	<script src="assets/js/jquery-1.8.2.min.js"></script>
	<script src="assets/js/supersized.3.2.7.min.js"></script>
	<script src="assets/js/supersized-init.js"></script>
	<script src="assets/js/scripts.js"></script>
</body>
</html>