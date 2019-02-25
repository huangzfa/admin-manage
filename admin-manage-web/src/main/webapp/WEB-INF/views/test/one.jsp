<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!doctype html>
<html lang="zh">
<head>
<base href="<%=basePath%>"></base>
<meta charset="utf-8" />
<title></title>
</head>
<body>
	<img src="static/plugin/shellidea/img/main.png"
		style="width: 99%; margin-top: 3px;" />
</body>
</html>
