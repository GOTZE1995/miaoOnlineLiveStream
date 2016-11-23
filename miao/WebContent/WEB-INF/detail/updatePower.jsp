<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/common/header.jsp"%>		
<title>修改权限</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/page_common.js"></script>
<link href="style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/css/index_1.css" />
</head>
<body>

<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
					<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/>更新权限
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<div id="MainArea">
	<form action="power/update.do" method="post">
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="style/images/item_point.gif"> 权限信息&nbsp;
        </div>
       <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="80px">权限名</td>
							<td><input type="text" name="powerName" class="InputStyle" value="${powerInfo.powerName}"/>
                         
                             *<input type="hidden" name="powerId" value="${powerInfo.powerId }" /></td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<div id="InputDetailBar">
					 <input type="submit" value="修改" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
