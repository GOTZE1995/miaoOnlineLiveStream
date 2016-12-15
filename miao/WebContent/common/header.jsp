<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<base href="<%= basePath %>"/>
<script type="text/javascript" src="${basePath}modify/js/jquery-1.11.0.min.js"></script>