<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>电子点名</title>
<script src="assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	var timer;
	function changeStatus(obj){
		if(obj.value=="开始"){
			changeProperty();
			obj.value="结束";
		}else{
			stop();
			obj.value="开始";
		}
	}
	
	function changeProperty(){
        timer = setInterval(showNum,50);
	}
	
	function showNum(){
		var n='${fn:length(sessionScope.stus)}';
		var k=Math.floor(Math.random()*n);
		$.ajax({
			url :'attendence/getStudent.do',
			data : {'k':k},
			type : 'post',
			async : false,
			success : function(result) {
				var s=result.split("|");
				var h=document.getElementsByTagName("h2")[0];
				h.innerHTML=s[0];
				document.getElementsByTagName('img')[0].setAttribute('src',s[1]);
			}
		})
	}
	
	function stop(){
		clearInterval(timer);
	}
</script>
</head>
<body>
	<div style="margin-left:80px">
		<div style="margin-top:20px">
			<img src="images/miao.jpg" width="180" height="180"/>
		</div>
		<div style="margin-top:30px;margin-left:30px">
			<h2>准备好了吗</h2>
		</div>
		<div style="margin-top:20px;margin-left:40px">
		<input type="submit" id="state" value="开始" onclick="changeStatus(this)"/>	
		</div>
	</div>
</body>
</html>