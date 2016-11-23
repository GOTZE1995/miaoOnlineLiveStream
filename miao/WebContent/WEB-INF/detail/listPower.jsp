<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	<%@include file="/common/header.jsp"%>		
<title>权限管理</title>

<script type="text/javascript">
	function doSearch(){
		document.forms[0].action = "power/listUI.do" ;
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
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 权限列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="power/listUI.do" method="post">
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
				<td>权限编号</td>
				<td>权限名</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->    
        <tbody id="TableData">
		<c:if test="${not empty powerList }">
			<c:forEach var="power" varStatus="stu"  items="${page.list}">
				<tr  align="center" class="TableDetail1">
					<td>${power.powerId }&nbsp;</td>
					<td>${power.powerName}&nbsp;</td>
					<td>
						<a href="power/updateUI.do?id=${power.powerId }"  class="FunctionButton">更新</a>				
						<a href="power/delete.do?id=${power.powerId }" onClick="return delConfirm();"class="FunctionButton">删除</a>				
					</td>
				</tr>
			</c:forEach>        
        </c:if>
        
        <c:if test="${empty powerList }">
        	<tr  align="center" class="TableDetail1">
				<td colspan="6">没有查到信息&nbsp;</td>
			</tr>
        </c:if>        
        </tbody>
    </table>
    <table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">
                 	总共${page.totalCount }条记录，当前第 ${page.currentPageNum } 页，共 ${page.totalPageNum } 页 &nbsp;&nbsp;
                            <a href="power/listUI.do?currentPage=${page.prePageNum }">上一页</a>&nbsp;&nbsp;<a href="power/listUI.do?currentPage=${page.nextPageNum }">下一页</a>
					&nbsp;&nbsp;
			    </td>
			</tr>
		</table>	
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="power/addUI.do">添加</a></div>
    </div> 
</div>
</body>
</html>
