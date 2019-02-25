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
	$('#tt').datagrid('loading');
	hjnUtils.ajax({  
        type:'post',      
        url:'${ctxA}/sys/organType/allOrganTypes',  
        dataType:'json',  
        success:function(data){
        	$('#tt').datagrid('loaded');
        	if(data.code==1){
        		//{"total":data.list.length,"rows":data.list};
        		$('#tt').datagrid('loadData', data.list); 
        	}else{
        		top.$.jBox.tip(data.msg);
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	$('#tt').datagrid('loaded');
        }
    });
});
function nameFormater(value,row,index){
	return '<a class="si-title-a" href="${ctxA}/sys/organType/form?organTypeId='+row.organTypeId+'">'+value+'</a>';
}
function isSystemFormater(value,row,index){
	if(value){
		return "系统数据";
	}
	return "用户数据";
}
function optionformater(value,row,index){
	var opStr='';
	<shiro:hasPermission name="sys:organType:edit">
	if(!row.isSystem){
		opStr+='<a class="si-option-a" href="${ctxA}/sys/organType/form?organTypeId='+row.organTypeId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/sys/organType/delete?organTypeId='+row.organTypeId+'" onclick="return confirmx(\'确定要删除该组织类型吗？\', this.href)">删除</a>';
	}
	</shiro:hasPermission>
	return opStr;
}
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="active"><a href="javascript:void(0);">组织类型列表</a></li>
  <shiro:hasPermission name="sys:organType:edit">
  	<li><a href="${ctxA}/sys/organType/form">添加组织类型</a></li>
  </shiro:hasPermission>
</ul>
<div class="si-warp">
	<sys:message content="${message}" isShowBox="false"/>
	<table id="tt" class="easyui-datagrid"
	        data-options="idField:'organTypeId',singleSelect:true,striped:true,fit:true,fitColumns:true">   
	    <thead>   
	        <tr>   
	            <th data-options="field:'organTypeName',width:180,align:'center',halign:'center',fixed:true,formatter:nameFormater">组织类型名称</th>
	            <th data-options="field:'organTypeCode',width:140,align:'center',halign:'center',fixed:true">组织类型代码</th>
	            <th data-options="field:'isSystem',width:140,fixed:true,align:'center',halign:'center',formatter:isSystemFormater">数据类型</th> 
	            <th data-options="field:'option',width:120,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
	        </tr>   
	    </thead>   
	</table>  
</div>
</body>
</html>