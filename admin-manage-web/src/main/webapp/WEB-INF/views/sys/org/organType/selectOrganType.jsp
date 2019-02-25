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
var organTypeId="${organTypeId}";
$(function(){
	$('#tt').datagrid({onSelect:function(rowIndex, rowData){
		$("input[name='organTypeIdRadio']").attr("checked", false);
		$("input[name='organTypeIdRadio'][value='"+rowData.organTypeId+"']").attr("checked", true);
	}});
	$('#tt').datagrid('loading');
	hjnUtils.ajax({  
        type:'post',      
        url:'${ctxA}/sys/organType/allOrganTypes',  
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
function radioFormater(value,row,index){
	var ck='';
	if(organTypeId==value){
		ck='checked="checked"';
	}
	return '<input type="radio" name="organTypeIdRadio" value="'+value+'" organTypeName="'+row.organTypeName+'" '+ck+'/>';
}
</script>
</head>
<body>

<div class="si-warp6">
	<table id="tt" class="easyui-datagrid"
	        data-options="idField:'organTypeId',singleSelect:true,striped:true,fit:true,fitColumns:true">   
	    <thead>   
	        <tr>   
	        	<th data-options="field:'organTypeId',width:30,align:'center',halign:'center',fixed:true,formatter:radioFormater"></th>
	            <th data-options="field:'organTypeCode',width:120,align:'center',halign:'center',fixed:true">组织类型代码</th>
	            <th data-options="field:'organTypeName',width:160,align:'center',halign:'center',fixed:true">组织类型名称</th>
	        </tr>   
	    </thead>   
	</table>  
</div>
</body>
</html>