<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>shellidea</title>
<link href="static/plugin/shellidea/css/shellidea-menu.css" rel="stylesheet" type="text/css" />
<link href="static/plugin/shellidea/css/shellidea-layout.css" rel="stylesheet" type="text/css" />
<link href="static/webfont/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="static/css/index.css" rel="stylesheet" type="text/css" />
<link href="static/plugin/easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
<link href="static/plugin/easyui/themes/icon.css" rel="stylesheet" type="text/css">
<script src="static/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="static/plugin/shellidea/js/shellidea-menu.js" type="text/javascript"></script>
<script src="static/plugin/shellidea/js/shellidea-layout.js" type="text/javascript"></script>
<script src="static/plugin/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<style type="text/css">
	html,body{
		margin: 0;
		padding: 0;
		background: #FFF;
		color: #D5D6E2;
		font-weight: 500;
		font-size: 1.05em;
		font-family: tahoma,\5FAE\8F6F\96C5\9ED1;
		overflow: hidden;
	}
	iframe{ 
	 	display: block; 
	 	margin: 0;
	}
</style>

<script type="text/javascript">
$(function(){
	//布局
	$("#lrLayout").shellidea_layout_LR({
		leftWidth:200,
		hasHideBar:true,
		onHideBarAfter:function(){
			$('#shellidea-tabs').tabs('resize');
		}
	});
	//顶部菜单
	$("#shellidea-header").shellideaHeader({click:function(){
		$("#shellidea-menu").children("ul").hide();
		$("#shellidea-menu").children("ul[code='"+$(this).attr("code")+"']").show();
		$("#shellidea-menu").children("ul[code='"+$(this).attr("code")+"']").children("li:first").children("a:first").click();
	}});
	//左侧菜单
	$("#shellidea-menu").shellideaMenu({
		click:function(){
		var menu=$(this).children("a:first");
		var title=menu.text();
		var mainTabs=$('#shellidea-tabs');
		if(!mainTabs.tabs('exists',title)){
			var content='<iframe src="'+menu.attr("url")+'" width="100%" height="100%" frameborder="no" border="0" scrolling="auto" ></iframe>';
			mainTabs.tabs('add',{
				title: title,
				content: content,
				closable: true
			});
		}else{
			mainTabs.tabs('select',title);
		}
	}});
	
	//菜单标签页
	$('#shellidea-tabs').tabs({
		border:false,
		plain:true,
		fit:true,
		tabHeight:28
	});
	//初始化首页
	$("#shellidea-header").children(".nav-bar").children("a:first").click();
	
});

</script>
</head>
<body>
	<div id="shellidea-header" class="shellidea-header">
		<div class="logo">
			shellidea <i class="fa fa-thumbs-o-up"></i>
		</div>
		<div class="nav-bar">
			<a class="menu-shake" code="01" href="javascript:void(0);">主页</a>
			<a class="menu-shake" code="02" href="javascript:void(0);">系统管理</a>
			<a class="menu-shake" code="03" href="javascript:void(0);">运货管理</a>
			<a class="menu-shake" code="04" href="javascript:void(0);">货主管理</a>
			<a class="menu-shake" code="05" href="javascript:void(0);">实时监控</a>
			<a class="menu-shake" code="06" href="javascript:void(0);">调度中心</a>
			<a class="menu-shake" code="07" href="javascript:void(0);">工作流</a>
			<span class="clear"></span>
		</div>
    </div>
    <div id="lrLayout" class="si-layout-box" style="top: 40px;bottom: 30px;">
		<div class="si-layout-left">
			<div id="shellidea-menu" class="shellidea-menu red">
				<div class="shellidea-menu-header">
					<div class="user_head">
						<img src="static/plugin/shellidea/img/user_head.png"/>
					</div>
				</div>
				<ul code="01">
				    <li class="active"><a href="javascript:void(0);" url="test/mainPage"><i class="fa fa-bar-chart"></i>数据统计</a></li>
				    <li><a href="javascript:void(0);"><i class="fa fa-table"></i>报表</a>
						<ul class="submenu">
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单1</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单2</a><span class="shellidea-menu-label">101 </span></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单3</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单4</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单5</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>${message}</a></li>
						</ul>
					</li>
					<li><a href="javascript:void(0);"><i class="fa fa-commenting"></i>消息</a>
						<span class="shellidea-menu-label">12 </span>
					</li>
					<li><a href="javascript:void(0);"><i class="fa fa-files-o"></i>文件管理</a></li>
					<li><a href="javascript:void(0);"><i class="fa fa-bell"></i>其他</a></li>
				</ul>
				<ul code="02">
				    <li class="active"><a href="javascript:void(0);"><i class="fa fa-bar-chart"></i>组织架构</a></li>
				    <li><a href="javascript:void(0);"><i class="fa fa-table"></i>权限管理</a>
						<ul class="submenu">
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单1</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单2</a><span class="shellidea-menu-label">101 </span></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单3</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单4</a></li>
							<li><a href="javascript:void(0);"><i class="fa fa-arrow-circle-right"></i>子菜单5</a></li>
						</ul>
					</li>
					<li><a href="javascript:void(0);"><i class="fa fa-files-o"></i>数据字典</a></li>
				</ul>
			</div>
			
		</div>
		<div class="si-layout-right">
			<div id="shellidea-tabs" style="margin-top: 2px"></div>
		</div>
	</div>
    <div class="shellidea-footer-layout">
    	CopyRight© 2015-2020 Inc.All Rights Reserved shellidea 版权所有<shiro:hasPermission name="test:hehe">sssss</shiro:hasPermission>
    </div>
</body>
</html>