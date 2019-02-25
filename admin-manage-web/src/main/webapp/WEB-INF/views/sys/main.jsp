<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>多贝业务后台</title>
<link rel="shortcut icon" href="/static/img/icon.png">
<sys:jscss jscss="jquery,webfont,bootstrap,css,siMenu,siLayout,easyui,layer,jbox,si"/>
<!--  -->
<link href="${ctxStatic}/css/index.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.si-iframe-warp1{
position:relative;
width: 100%;
height: 100%;
}
.si-iframe-warp2{
position:absolute;
top: 3px;
left: 0;
right: 0;
bottom: 0;
}
</style>
<script type="text/javascript">
$(function(){
	//顶部菜单
	$("#shellidea-header").shellideaHeader({click:function(){
		$("#shellidea-menu").children("ul").hide();
		var menuUL=$("#shellidea-menu").children("ul[code='"+$(this).attr("code")+"']");
		menuUL.show();
		if(menuUL.attr("isShow")!='true'){
			menuUL.attr("isShow","true");
			siMenu.showSubmenu(menuUL.children("li:first"));
		}
	}});
	resizeLayout();
	$(window).resize(function(){
		resizeLayout();
	});
	function resizeLayout(){
		$("#lrLayout").css({top:$("#shellidea-header").outerHeight(true)});
	}
	
	//布局
	$("#lrLayout").shellidea_layout_LR({
		leftWidth:180,
		hasHideBar:true,
		onHideBarAfter:function(){
			$('#shellidea-tabs').tabs('resize');
		}
	});
	var mainTabs=$('#shellidea-tabs');
	//左侧菜单
	var siMenu=$("#shellidea-menu").shellideaMenu(
	{
		singleOpen:false,
		click:function(){
			var menu=$(this).children("a:first");
			var title=$.trim(menu.text());
			if($.trim(menu.attr("url"))==""){
				return false;
			}
			title='<span menuTag="'+menu.attr("menuTag")+'">'+title+'</span>';
			if(!mainTabs.tabs('exists',title)){
				var menuUrl=menu.attr("url");
				if('p'==menu.attr("menuOpenType")){
					menuUrl="/a/common/encrypt/menu?url="+menuUrl;
				}
				var content='<div class="si-iframe-warp1"><div class="si-iframe-warp2"><iframe src="'+menuUrl+'" width="100%" height="100%" frameborder="no" border="0" scrolling="auto" style="display: block;margin: 0;"></iframe></div></div>';
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
		tabHeight:25,
		showHeader:true,
		onSelect:function(title,index){
			if(0==index){
				$('#dataCenterIframe').attr('src', $('#dataCenterIframe').attr('src'));
			}
		}
	});
	//初始化首页
	var headFirstMenu=$("#shellidea-header").children(".nav-bar").find(".menuA:first");
	headFirstMenu.click();
	/**
	$("#shellidea-menu").children("ul[code='"+headFirstMenu.attr("code")+"']").find(".submenu").children("li:first").click();
	*/
	/**
	mainTabs.tabs('add',{
		title: '信息中心',
		content: '<div class="si-iframe-warp1"><div class="si-iframe-warp2"><iframe id="dataCenterIframe" src="${ctxA}/sys/sys/dataCenter" width="100%" height="100%" frameborder="no" border="0" scrolling="auto" style="display: block;margin: 0;"></iframe></div></div>',
		closable: false
	});
	*/
	
	window.ms56CustomAddTab=function(title,url){
		if(!mainTabs.tabs('exists',title)){
			var content='<div class="si-iframe-warp1"><div class="si-iframe-warp2"><iframe src="'+url+'" width="100%" height="100%" frameborder="no" border="0" scrolling="auto" style="display: block;margin: 0;"></iframe></div></div>';
			mainTabs.tabs('add',{
				title: title,
				content: content,
				closable: true
			});
		}else{
			mainTabs.tabs('select',title);
		}
	}
});

</script>
</head>
<body>
	<div id="shellidea-header" class="shellidea-header">
		<div class="nav-bar">
			<div class="info">
				<span class="loginout"><a href="/logout">退出</a></span>
				<span class="loginName"><i class="fa fa-user"></i> 你好：${user.realName }</span>
			</div>
			<div class="menuBox">
				<span class="logo">多贝业务后台</span>
				<c:if test="${not empty menus}">
					<c:forEach items="${menus}" var="m" varStatus="mStatus">
						<a class="menuA" code="${m.menuTag}" href="javascript:void(0);">${m.menuName}</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
    </div>
    <div id="lrLayout" class="si-layout-box" style="bottom: 30px;">
		<div class="si-layout-left style2">
			<div id="shellidea-menu" class="shellidea-menu">
				<!--
				<div class="shellidea-menu-header">
					<div class="user_head">
						<img src="${ctxStatic}/plugin/shellidea/img/user_head.png"/>
					</div>
				</div>
				-->
				<c:if test="${not empty menus}">
					<c:forEach items="${menus}" var="m" varStatus="mStatus">
						<c:if test="${not empty m.childs}">
							<ul code="${m.menuTag}">
								<c:forEach items="${m.childs}" var="two" varStatus="twoStatus">
									<li><a href="javascript:void(0);" url="${empty two.menuUrl?'':ctx}${two.menuUrl}">
										<c:if test="${not empty two.menuIcon}">
											<i class="${two.menuIcon}"></i>
										</c:if>
										<c:if test="${empty two.menuIcon}">
											<i class="fa fa-paw"></i>
										</c:if>
										${two.menuName}</a>
										<c:if test="${not empty two.childs}">
											<ul class="submenu">
												<c:forEach items="${two.childs}" var="three" varStatus="threeStatus">
													<li>
														<a href="javascript:void(0);" url="${empty three.menuUrl?'':ctx}${three.menuUrl}" menuTag="${three.menuTag}" menuOpenType="${three.menuOpenType}" >
														<c:if test="${not empty three.menuIcon}">
															<i class="${three.menuIcon}"></i>
														</c:if>
														<c:if test="${empty three.menuIcon}">
															<i class="fa fa-caret-right"></i>
														</c:if>
														${three.menuName}</a>
													</li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="si-layout-right">
			<div id="shellidea-tabs"></div>
		</div>
	</div>
    <div class="shellidea-footer-layout">
    	CopyRight© 2015-2020 Inc.All Rights Reserved 
    </div>
</body>
</html>