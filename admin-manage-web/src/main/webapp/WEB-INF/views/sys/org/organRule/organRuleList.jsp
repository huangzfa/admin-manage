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
        url:'${ctxA}/sys/organRule/allOrganRules',  
        dataType:'json',  
        success:function(data){
        	$('#tt').datagrid('loaded');
        	if(data.code==1){
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
function isSystemFormater(value,row,index){
	if(value){
		return "系统数据";
	}
	return "用户数据";
}
function optionformater(value,row,index){
	var opStr='';
	<shiro:hasPermission name="sys:organRule:edit">
	if(!row.isSystem){
		opStr+='<a class="si-option-a" href="${ctxA}/sys/organRule/form?organRuleId='+row.organRuleId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/sys/organRule/delete?organRuleId='+row.organRuleId+'" onclick="return confirmx(\'确定要删除该组织规则吗？\', this.href)">删除</a>';
	}
	</shiro:hasPermission>
	return opStr;
}
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="active"><a href="javascript:void(0);">组织规则列表</a></li>
  <shiro:hasPermission name="sys:organRule:edit">
  <li><a href="${ctxA}/sys/organRule/form">添加组织规则</a></li>
  </shiro:hasPermission>
</ul>
<div class="si-warp">
	<sys:message content="${message}" isShowBox="false"/>
	<table id="tt" class="easyui-datagrid"
	        data-options="idField:'organRuleId',singleSelect:true,striped:true,fit:true,fitColumns:true">   
	    <thead>   
	        <tr>   
	        	<th data-options="field:'supOrganTypeCode',width:120,align:'center',halign:'center',fixed:true">上级组织类型编码</th>
	            <th data-options="field:'supOrganTypeName',width:120,align:'center',halign:'center',fixed:true">上级组织类型名称</th>
	            <th data-options="field:'subOrganTypeCode',width:120,align:'center',halign:'center',fixed:true">下级组织类型编码</th>
	            <th data-options="field:'subOrganTypeName',width:100,align:'center',halign:'center',fixed:true">下级组织类型名称</th>
	            <th data-options="field:'isSystem',width:90,align:'center',halign:'center',fixed:true,formatter:isSystemFormater">数据类型</th> 
	            <th data-options="field:'remark',width:200">说明</th>  
	            <th data-options="field:'option',width:120,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
	        </tr>   
	    </thead>   
	</table>  
</div>
</body>
</html>