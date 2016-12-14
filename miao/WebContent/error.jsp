<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误处理页面</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"> 
		window.onload=function(){
			runCount(5);
			function runCount(t){
		  		if(t>0){
		    		document.getElementById('second').innerHTML = t;
		    		t--;
		    		setTimeout(function(){runCount(t);},1000);
		  		}else{
		    		window.location.href="index.jsp";
		  		}
			}

		} 
</script>
</head>
<body>
		<div style="margin-left:200px">
		<img src="images/qqq.png"/>
		</div>
		<div style="margin-left:400px">
		<h3>没有发现您要找的页面，经砖家仔细研究结果如下：</h3>
		<div  style="margin-left:50px">
			<p>输入地址时可能存在键入错误</p>
			<p>小蜗牛把页面落在家里忘记了</p>
			<p>电信网通那头接口生锈了</p>
		</div>
		<h1 style="margin-top:60px; color:green;">
		<span id="second"></span>秒后自动跳转到首页。。。。。
		</h1>
		</div>

	</body>
</html>