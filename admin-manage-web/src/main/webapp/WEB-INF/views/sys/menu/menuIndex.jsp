<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,siMenu,siLayout,easyui,layer,jbox,zTree"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
	//布局
	$("#lrLayout").shellidea_layout_LR({
		leftWidth:180,
		hasHideBar:true,
		onHideBarAfter:function(){
		}
	});
	
	function refreshTree(){
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/menu/allMenus?menuType=m',  
	        dataType:'json',  
	        success:function(data){  
	        	if(data.code==1){
	        		top.$.jBox.tip(data.msg);
	        		/**
	        		$.each(data.menus, function(idx, obj) {
	        			obj.open=true;
	        		});
	        		*/
		        	//初始化ztree
		        	$.fn.zTree.init($("#ztree"), {
		        		check: {
		        			enable: false
		        		},
		        		data: {
		        			key: {
		        				name: "menuName",
		        				children: "childs"
		        			},
		        			simpleData: {
		        				enable: true,
		        				idKey: "menuId",
		        				pIdKey: "parentId",
		        				rootPId: -1
		        			}
		        		},
		        		callback: {
		        			onClick: function(event, treeId, treeNode) {
		        			    //alert(treeNode.tId + ", " + treeNode.menuName);
		        			}
		        		}
		        	},data.menus).expandAll(true);
	        	}else{
	        		top.$.jBox.tip(data.msg);
	        	}
	        }
	    });
	}
	$("#refreshTree").click(refreshTree);
	refreshTree();
});

</script>
</head>
<body>
<div id="lrLayout" class="si-layout-box">
	<div class="si-layout-left border" >
		<div class="si-title">
			菜单树 <i id="refreshTree" class="icon-refresh pull-right accordion-toggle"></i>
		</div>
		<ul id="ztree" class="ztree"></ul>
	</div>
	<div class="si-layout-right">
		<iframe src="${ctxA}/sys/menu/list" width="100%" height="100%" frameborder="no" border="0" scrolling="auto" style="display: block;margin: 0;"></iframe>
	</div>
</div>
</body>
</html>