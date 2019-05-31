<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0"> 
	<link rel="shortcut icon" href="/static/img/icon.png">
	<title>登录界面</title>
    <link href="/static/layui_login/css/default.css" rel="stylesheet" type="text/css" />
	<!--必要样式-->
    <link href="/static/layui_login/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="/static/layui_login/css/demo.css" rel="stylesheet" type="text/css" />
    <link href="/static/layui_login/css/loaders.css" rel="stylesheet" type="text/css" />
    
    <link href="/static/layui_login/layui/css/layui.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="/static/layui_login/js/jquery.min.js"></script>
	<script type="text/javascript" src="/static/layui_login/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src='/static/layui_login/js/stopExecutionOnTimeout.js?t=1'></script>
    <script type="text/javascript" src="/static/layui_login/layui/layui.js"></script>
    <script type="text/javascript" src="/static/layui_login/js/Particleground.js"></script>
    <script type="text/javascript" src="/static/layui_login/js/Treatment.js"></script>
    <script type="text/javascript" src="/static/layui_login/js/jquery.mockjax.js"></script>
<script type="text/javascript">
if(window != top){
	top.location.href = location.href; 
}
</script>
</head>
<body>
<div class='login'>
  <div class='login_fields'>
	<div class='login_fields__user'>
	  <div class='icon'>
		<img alt="" src='/static/layui_login/img/user_icon_copy.png'>
	  </div>
	  <input id="username" name="username" placeholder='用户名或手机号' maxlength="16" type='text' autocomplete="off"/>
		<div class='validation'>
		  <img alt="" src='/static/layui_login/img/tick.png'>
		</div>
	</div>
	<div class='login_fields__password'>
	  <div class='icon'>
		<img alt="" src='/static/layui_login/img/key.png'>
	  </div>
	  <input id="validateCode" name="validateCode" placeholder='短信验证码' maxlength="4" type='text' autocomplete="off">
	  <div class='validation' style="opacity: 1; right: 10px;top: 3px;">
		<button id="sendSmsBtn" class="layui-btn layui-btn-sm layui-btn-normal layui-btn-radius" style="height: 32px;line-height: 32px;">获取验证码</button>
	  </div>
	</div>
	<div class='login_fields__submit'>
	  <input id="loginBtn" type='button' value='登录'>
	</div>
  </div>
  <div class='success'></div>
</div>
<div class='authent'>
  <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
	<div class="loader-inner ball-clip-rotate-multiple">
		<div></div>
		<div></div>
		<div></div>
	</div>
	</div>
  <p>登录中...</p>
</div>
<div class="OverWindows"></div>

<div id="smsDiv" class="layui-form" >
	<div class="layui-form-item">
		<div class="layui-inline" style="margin-top: 12px;">
		  <label class="layui-form-label" style="color: black;">图形验证码</label>
		  <div class="layui-input-inline" style="width: 90px;">
			<input type="text" id="imgVCodeVal" autocomplete="off" class="layui-input" style="width: 80px;" maxlength="4">
		  </div>
		  <div class="layui-inline">
			  <img id="imgVCode" height="36px"/>
			</div>
		</div>

	  </div>
</div>

<script type="text/javascript">

	var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
	var ajaxmockjax = 1;//是否启用虚拟Ajax的请求响 0 不启用  1 启用
	//默认账号密码

	var CodeVal = 0;
	$(document).keypress(function (e) {
		// 回车键事件
		if (e.which == 13) {
			return false;
			//$('input[type="button"]').click();
		}
	});
	//粒子背景特效
	$('body').particleground({
		dotColor: '#E8DFE8',
		lineColor: '#133b88'
	});
	$('input[name="pwd"]').focus(function () {
		$(this).attr('type', 'password');
	});
	$('input[type="text"]').focus(function () {
		$(this).prev().animate({ 'opacity': '1' }, 200);
	});
	$('input[type="text"],input[type="password"]').blur(function () {
		$(this).prev().animate({ 'opacity': '.5' }, 200);
	});
	$('input[name="login"],input[name="pwd"]').keyup(function () {
		var Len = $(this).val().length;
		if (!$(this).val() == '' && Len >= 5) {
			$(this).next().animate({
				'opacity': '1',
				'right': '30'
			}, 200);
		} else {
			$(this).next().animate({
				'opacity': '0',
				'right': '20'
			}, 200);
		}
	});
	var open = 0;
	layui.use('layer', function () {
		//非空验证
		$('#loginBtn').click(function () {
			var username = $('#username').val();
			var validateCode = $('#validateCode').val();
			if ($.trim(username) == '') {
				layer.msg('请输入您的账号');
			} else if ($.trim(validateCode) == '' || validateCode.length != 4) {
				layer.msg('输入短信验证码');
			} else {
				//认证中..
				$('.login').addClass('test'); //倾斜特效
				setTimeout(function () {
					$('.login').addClass('testtwo'); //平移特效
				}, 300);
				setTimeout(function () {
					$('.authent').show().animate({ right: -320 }, {
						easing: 'easeOutQuint',
						duration: 600,
						queue: false
					});
					$('.authent').animate({ opacity: 1 }, {
						duration: 200,
						queue: false
					}).addClass('visible');
				}, 500);

				$.ajax({
					dataType:"json",
					url:"/login",
					data: {username:username,validateCode:validateCode},
					success: function(data) {
						//认证完成
					   $('.authent').show().animate({ right: 90 }, {
							easing: 'easeOutQuint',
							duration: 600,
							queue: false
						});
					   $('.authent').animate({ opacity: 0 }, {
							duration: 200,
							queue: false
					   }).addClass('visible');
					   $('.login').removeClass('testtwo'); //平移特效
						setTimeout(function () {
							$('.authent').hide();
							if(data.code==1){
								 window.location.href=data.mainUrl;
							}else{
								$('.login').removeClass('test');
								//layer.msg(data.msg);
								$('.success').html("登录失败："+data.msg);
							}
						}, 2400);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
					}
				});
			}
		})
	})

	function resetImgVCode(){
		$('#imgVCode').attr('src','/servlet/validateCodeServlet?'+new Date().getTime());
	}
	function isMobile(val){
		return /^1\d{10}$/.test(val);
	}
	$(function(){
		var djsInterval;
		var djs=60;
		var $sendSmsBtn=$('#sendSmsBtn');

		function numTimer(){
			if(djs<=1){
				clearInterval(djsInterval);
				$sendSmsBtn.html('获取验证码');
				djs=60;
				$sendSmsBtn.removeClass('t');
			}else{
				djs--;
				$sendSmsBtn.html(djs+'s');
			}
		}

		function openSendSms(){
			$('#imgVCodeVal').val('');
			resetImgVCode();
			layer.open({
				type: 1
				,title :'获取短信验证码'
				,offset: 'auto'
				,area: ['500px', '180px']
				,id: 'layerSms' //防止重复弹出
				,content: $('#smsDiv')
				,btn: '确定'
				,btnAlign: 'c' //按钮居中
				,shade: 0 //不显示遮罩
				,yes: function(){
					var imgVCodeVal=$('#imgVCodeVal').val();
					if($.trim(imgVCodeVal)==''||imgVCodeVal.length!=4){
						layer.msg('请输入4位图形验证码');
					}else{
						$sendSmsBtn.addClass('t');
						$.ajax({
							dataType:"json",
							url:"/login/send/sms",
							data: {mn:$('#username').val(),ivc:imgVCodeVal},
							success: function(data) {
								clearInterval(djsInterval);
								if(data.code==1){
									djsInterval = window.setInterval(numTimer,1000);
									layer.msg('短信发送成功');
									layer.closeAll();
								}else{
									$sendSmsBtn.removeClass('t');
									layer.msg(data.msg);
									//resetImgVCode();
								}
							},
							error:function(XMLHttpRequest, textStatus, errorThrown){
								$sendSmsBtn.removeClass('t');
							}
						});

					}
				}
			  });
		}

		$sendSmsBtn.click(function(){
			if($sendSmsBtn.hasClass('t')){
				return;
			}
			if(isMobile($('#username').val())){
				openSendSms();
			}else{
				layer.msg('请输入正确的用户名或手机号');
			}
		});
		$('#imgVCode').click(function(){
			resetImgVCode();
		});
	});
</script>

</body>
</html>
