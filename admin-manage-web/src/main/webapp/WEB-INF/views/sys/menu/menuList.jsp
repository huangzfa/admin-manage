<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
	hjnUtils.ajax({
        type:'post',      
        url:'${ctxA}/sys/menu/allMenus',  
        dataType:'json',  
        success:function(data){
        	if(data.code==1){
        		$.each(data.menus, function(idx, obj) {
        			if(obj.parentId!=0){
        				obj._parentId=obj.parentId;
        				if( obj.parentId == 1){
                            obj.state = "closed";
						}
        			}
        		});
        		var menus={"total":data.menus.length,"rows":data.menus};
        		//alert(JSON.stringify(menus));
        		$('#tt').datagrid('loadData', menus); 
        		$('span.tree-icon').width(0).hide();
        		
        	}else{
        		top.$.jBox.tip(data.msg);
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	$('#tt').datagrid('loaded');
        }
    });
});
function menuNameformater(value,row,index){
	var opStr='';
	if(row.menuId!=1){
		opStr+='<a class="si-title-a" href="${ctxA}/sys/menu/form?menuId='+row.menuId+'">'+value+'</a>';
	}else{
		opStr=value;
	}
	return opStr;
}
function optionformater(value,row,index){
	var opStr='';
		<shiro:hasPermission name="sys:menu:edit">
		if(row.parentId==0){
			opStr+='<a class="si-option-a" href="${ctxA}/sys/menu/form?parentId='+row.menuId+'">添加下级菜单</a>';
		}else{
			opStr+='<a class="si-option-a" href="${ctxA}/sys/menu/form?menuId='+row.menuId+'">修改</a>';
			opStr+='<a class="si-option-a" href="${ctxA}/sys/menu/delete?menuId='+row.menuId+'" onclick="return confirmx(\'确定要删除该菜单项吗？\', this.href)">删除</a>';
			if('m'==row.menuType){
				opStr+='<a class="si-option-a" href="${ctxA}/sys/menu/form?parentId='+row.menuId+'">添加下级菜单</a>';
			}
		}
		</shiro:hasPermission>
	return opStr;
}
function menuTypeformater(value,row,index){
	if('m'==value){
		return "菜单";
	}else if('mo'==value){
		return "权限项";
	}
	return "";
}
function menuStateformater(value,row,index){
	if('10'==value){
		return "启用";
	}else if('20'==value){
		return "停用";
	}
	return "";
}
function levelformater(value,row,index){
	if(0==value){
		return "顶级";
	}
	return value+"级";
}
function menuIconformater(value,row,index){
	if(row.menuLevel!=0){
		if(row.menuLevel==1){
			
		}else if(row.menuLevel==2){
			if($.trim(value)==""){
				return '<i class="fa fa-paw"></i>';
			}
		}else if(row.menuLevel==3){
			if($.trim(value)==""){
				return '<i class="fa fa-arrow-circle-right"></i>';
			}
		}
	}
	return '<i class="'+value+'"></i>';;
}
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
  <li class="active"><a href="javascript:void(0);">菜单列表</a></li>
  <shiro:hasPermission name="sys:menu:edit">
  	<li><a href="${ctxA}/sys/menu/form">添加菜单</a></li>
  </shiro:hasPermission>
</ul>
<div class="si-warp">
	<sys:message content="${message}" isShowBox="false"/>
	<table id="tt" class="easyui-treegrid"
	        data-options="idField:'menuId',treeField:'menuName',animate:true,singleSelect:true,striped:false,fit:true,fitColumns:true">
	    <thead>   
	        <tr>   
	            <th data-options="field:'menuName',width:100,formatter:menuNameformater">菜单名称</th>
	            <th data-options="field:'menuIcon',width:25,align:'center',formatter:menuIconformater">图标</th> 
	            <th data-options="field:'menuType',width:30,align:'center',formatter:menuTypeformater">类型</th>
	            <th data-options="field:'menuSort',width:30,align:'center'">排序</th> 
	            <th data-options="field:'authTag',width:60">权限标识</th> 
	            <th data-options="field:'menuUrl',width:140">菜单URL</th> 
	            <th data-options="field:'state',width:30,align:'center',formatter:menuStateformater">状态</th> 
	            <!-- <th data-options="field:'menuTag',width:80">菜单标记</th> -->
	            <th data-options="field:'menuLevel',width:30,align:'center',formatter:levelformater">层级</th> 
	            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
	        </tr>   
	    </thead>   
	</table>  
</div>
</body>
</html>