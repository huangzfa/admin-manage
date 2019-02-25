<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>组织架构</title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,siMenu,siLayout,easyui,layer,jbox,zTree"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
var treeObj;
$(function(){
	//布局
	$("#lrLayout").shellidea_layout_LR({
		leftWidth:180,
		hasHideBar:true,
		onHideBarAfter:function(){
		}
	});
	
	$("#refreshTree").click(refreshTree);
	refreshTree();
});
function refreshTree(){
	hjnUtils.ajax({  
        type:'post',      
        url:'${ctxA}/sys/user/treeOrgans',  
        dataType:'json',  
        success:function(data){  
        	if(data.code==1){
        		if (data.list) {
        			$.each(data.list, function(idx, obj) {
        				if(obj.parentOrganId==0){//单位
							obj.icon="/static/img/icons/house.png";
						}else if(obj.organTypeId==1){//公司
							obj.icon="/static/img/icons/house.png";
						}else if(obj.organTypeId==2){//部门
							obj.icon="/static/img/icons/group.png";
						}else if(obj.organTypeId==3){//岗位
							obj.icon="/static/img/icons/user.png";
						}
            		});
  			    }
	        	//初始化ztree
	        	treeObj=$.fn.zTree.init($("#ztree"), {
	        		check: {
	        			enable: false
	        		},
	        		data: {
	        			key: {
	        				name: "organName"
	        			},
	        			simpleData: {
	        				enable: true,
	        				idKey: "organId",
	        				pIdKey: "parentOrganId",
	        				rootPId: 0
	        			}
	        		},
	        		callback: {
	        			onClick: function(event, treeId, treeNode) {
	        				$("#userList").attr("src","${ctxA}/sys/user/list?selectOrganId="+treeNode.organId);
	        			}
	        		}
	        	},data.list);
	        	treeObj.expandAll(true);
	        	var rootNode = treeObj.getNodeByParam("parentOrganId", 0, null);
	        	treeObj.selectNode(rootNode);
	        	$("#userList").attr("src","${ctxA}/sys/user/list?selectOrganId="+rootNode.organId);
        	}else{
        		top.$.jBox.tip(data.msg);
        	}
        }
    });
}
</script>
</head>
<body>
<div id="lrLayout" class="si-layout-box">
	<div class="si-layout-left border" >
		<div class="si-title">
			组织架构树 <i id="refreshTree" class="icon-refresh pull-right accordion-toggle"></i>
		</div>
		<ul id="ztree" class="ztree"></ul>
	</div>
	<div class="si-layout-right">
		<iframe id="userList" src="" width="100%" height="100%" frameborder="no" border="0" scrolling="auto" style="display: block;margin: 0;"></iframe>
	</div>
</div>
</body>
</html>