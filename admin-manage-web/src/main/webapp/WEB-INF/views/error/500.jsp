<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache"/> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
<title></title>
<style type="text/css">
div {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -100px 0 0 -100px;
	width: 300px;
	height: 200px;
	font-family: Microsoft YaHei;
	color: gray;
}
</style>
</head>
<body>
	<div class="error_page">
		<h2 >服务器太忙，请重试！</h2>
	</div>

</body>
</html>
