<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
<%@include file="/common/header.jsp"%>		
<title>直播间管理</title>

<script type="text/javascript">
	function doSearch(){
		document.forms[0].action = "room/listUI.do" ;
		document.forms[0].submit();
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/page_common.js"></script>
<link href="style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 直播间列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="room/listUI.do" method="get">
			<input type="text" name="searchName" value="${searchName}">
			<input type="button" value="搜索" onclick="doSearch()">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>房间编号</td>
				<td>所属用户</td>
				<td>房间名</td>
				<td>创建日期</td>
				<td>状态</td>
                <td>url</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:if test="${not empty roomList }">
			<c:forEach var="room" varStatus="stu"  items="${page.list}">
				<tr  align="center" class="TableDetail1">
					<td>${stu.count }&nbsp;</td>
					<td>${room.user.userName }&nbsp;</td>
					<td>${room.roomName }&nbsp;</td>
					<td>${room.beginDate }&nbsp;</td>
	                <td>${room.status }&nbsp;</td>
					<td>${room.url }&nbsp;</td>
					<td>
						<a href="room/updateUI.do?id=${room.id }"  class="FunctionButton">更新</a>				
						<a href="room/delete.do?id=${room.id}" onClick="return delConfirm();"class="FunctionButton">删除</a>					
					</td>
				</tr>
			</c:forEach>        
        </c:if>
        
        <c:if test="${empty roomList }">
        	<tr  align="center" class="TableDetail1">
				<td colspan="6">没有查到直播间信息&nbsp;</td>
			</tr>
        </c:if>
			
        
        </tbody>
    </table>
    
    <table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">
                 	总共${page.totalCount }条记录，当前第 ${page.currentPageNum } 页，共 ${page.totalPageNum } 页 &nbsp;&nbsp;
                            <a href="room/listUI.do?currentPage=${page.prePageNum }">上一页</a>&nbsp;&nbsp;<a href="room/listUI.do?currentPage=${page.nextPageNum }">下一页</a>
					&nbsp;&nbsp;
			    </td>
			</tr>
		</table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="room/addUI.do">添加</a></div>
    </div> 
</div>
</body>
</html>
