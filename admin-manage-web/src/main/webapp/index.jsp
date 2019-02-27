<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8" />
<title>业务后台</title>
<link rel="shortcut icon" href="/static/img/icon.png">
<link rel="stylesheet" type="text/css" href="/static/css/common/iview.css">
<link rel="stylesheet" type="text/css" href="/static/plugin/layer/mobile/need/layer.css">
<link href="/static/layui_login/css/loaders.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/static/css/index.css">
<link rel="stylesheet" type="text/css" href="/static/plugin/bootstrap/2.3.1/css_default/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/static/plugin/bootstrap/2.3.1/css_default/bootstrap-responsive.min.css">
<script type="text/javascript" src="/static/js/common/vue.min.js"></script>
<script type="text/javascript" src="/static/js/common/iview.min.js"></script>
<script type="text/javascript" src="/static/js/common/axios.min.js"></script>
<script type="text/javascript" src="/static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/plugin/bootstrap/2.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/plugin/layer/layer.js"></script>
<style type="text/css">
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{
		height: 32px;
	}
</style>
</head>
<body id="login-body">
	<div id="app">
		<Card style="width:350px" id="login-card">
			<p slot="title">欢迎登录业务后台</p>
			<i-input v-model="userName" maxlength="24" @keyup.enter.native="doLogin">
				<span slot="prepend">账号：</span>
			</i-input> 
			<i-input v-model="password" type="password" maxlength="24" style="margin-top:25px;" @keyup.enter.native="doLogin">
				<span slot="prepend">密码：</span> 
			</i-input> 
			<i-button @click="doLogin" type="primary" long style="margin-top:30px;">登录</i-button>
			<p id="tips">@2018 杭州蒲公英数据科技有限公司</p>
		</Card>
	</div>
</body>
<script type="text/javascript" src="/static/js/index.js"></script>
<script>
	function modifyPwd(phone,opId){
		top.layer.open({
			type: 2,
			title: '修改密码',
			shadeClose: true,
			shade: 0.8,
			area: ['680px', '50%'],
			content: '/modifyPwd?phone='+phone+'&opId='+opId
		});
	}

</script>
</html>