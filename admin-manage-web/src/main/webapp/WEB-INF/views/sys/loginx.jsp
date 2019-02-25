<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<sys:jscss jscss="jquery,bootstrap,si,layer"/>
<!--  -->
<style type="text/css">
html,body {
	margin: 0;
	padding: 0;
	width: 100%;
	height:100%;
	overflow:hidden;
}
.version {
	position:absolute;
	width: 100%;
	text-align: center;
	color: #ffffff;
	bottom: 30px;
}

.login_warp {
	width: 100%;
	height:100%;
	background: url(${ctxStatic}/img/login/sh.jpg) no-repeat;
	background-size:100% 100%;
	text-align:center;
}
.feichuan{
	position:absolute;
	width:100px;
	height:35px;
	background: url(${ctxStatic}/img/login/feichuan.png) no-repeat;
	background-size:100% 100%;
	z-index: 5;
}
.loginDiv{
	width:380px;
	height:220px;
	position:absolute;
	overflow:hidden;
	z-index: 10;
	background-color:white;
	color:black;
	-moz-border-radius: 5px; /* Firefox */
    -webkit-border-radius: 5px; /* Safari 和 Chrome */
    border-radius: 5px; /* Opera 10.5+, 以及使用了IE-CSS3的IE浏览器 */
}
.loginBtn {
    background-color: #0083de;
    height: 35px;
    width: 100%;
    display: block;
    text-align: center;
    line-height: 35px;
    font-size: 15px;
    border-radius: 5px;
    color: #fff;
    font-family: "微软雅黑";
    cursor: pointer;
}
table {
	font-size:14px;
	font-family: "微软雅黑";
}
.error{
	font-size:14px;
	color: #111;
	font-weight: 600;
}
</style>
<script type="text/javascript">
if(window != top){
	top.location.href = location.href; 
}
</script>
<script type="text/javascript">
$(function(){
	function dwLoginDiv(){
		var top=($("body").outerHeight(true)-$(".loginDiv").outerHeight(true))/2
		var left=($("body").outerWidth(true)-$(".loginDiv").outerWidth(true))/2;
		$(".loginDiv").offset({ top: top, left: left });
	}
	dwLoginDiv();
	$(window).resize(dwLoginDiv);
	
	$(".loginBtn").click(function(){
		submitForm();
	});
	$(document).keypress(function(e) { 
		if(e.keyCode ==13){
			submitForm();
		  }
	}); 
	$('#loginName').focus();
	$(".feichuan").css({
		top: $(window).height()*0.6,
		left: $(window).width(),
		width:'160px',
		height:'56px'
	});
	$(".feichuan").animate({
		top: '-120px',
		left: '-580px',
		width:'120px',
		height:'42px'
	}, 15000);
});
function submitForm(){
	$("#form").submit();
}
</script>
</head>
<body>
<div class="login_warp">
	<div class="feichuan"></div>
		<form id="form" action="${ctx}/login" method="post">
			<div class="loginDiv">
				   <span class="error">${error}</span>
				   
			       <table style="margin: 0 auto;margin-top: 10px;">
			       	<tr>
			       		<td>用户名：</td>
			       		<td>
			       			<input type="text" name="username" id="loginName" maxlength="32"/>
			       		</td>
			       	</tr>
			       	<tr>
			       	    <td>图形码：</td>
			       		<td>
			       			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
			       		</td>
			       	</tr>
			       	<tr>
			       	    <td>验证码：</td>
			       		<td>
			       			<input type="text" id="smsCode" name="smsCode" maxlength="5" class="txt" style="font-weight:bold;width:60px;margin-bottom:0;">
			       		</td>
			       	</tr>
			       	<tr>
			       		<td colspan="2"><span class="loginBtn">立即登陆</span></td>
			       	</tr>
			       </table>
			</div>
		</form>
</div>
</body>
</html>
