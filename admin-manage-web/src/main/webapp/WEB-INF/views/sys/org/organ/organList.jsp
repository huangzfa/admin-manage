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
var selectOrganId="${selectOrganId}";
$(function(){
	$('#toOrganForm').click(function(){
		if(window.parent.treeObj.getSelectedNodes().length>0){
			var selectOrgan=window.parent.treeObj.getSelectedNodes()[0];
			hjnUtils.ajax({
		        type:'post',      
		        url:'${ctxA}/sys/organ/checkByOrganRule', 
		        data: "selectOrganId="+selectOrganId,
		        dataType:'json',  
		        success:function(data){
		        	if(data.code==1){
		        		if(data.flag){
		        			window.location="${ctxA}/sys/organ/form?selectOrganId="+selectOrganId;
		        		}else{
		        			top.$.jBox.tip("按照组织规则，此组织下无法添加下级组织");
		        		}
		        	}else{
		        		top.$.jBox.tip(data.msg);
		        	}
		        }
		    });
		}else{
			top.$.jBox.tip("请选择上级组织");
			//top.layer.msg("请选择上级组织", {time: 2000, icon:0});
		}
	});
	
	var pageSize=${cfg:getPageSize()};
	var pageList=[pageSize,30,50];
	var pager=$('#tt').datagrid('getPager');
	pager.pagination({ 
	    pageSize: pageSize,//每页显示的记录条数，默认为10 
	    pageList: pageList,//可以设置每页记录条数的列表 
	    layout:['list','sep','first','prev','links','next','last','sep','manual'],
	    beforePageText: '',
	    afterPageText: ' 共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    onSelectPage:function(pageNumber, pageSize){
	    	pageSize=pageSize;
	        //alert("pageNumber:"+pageNumber+";pageSize:"+pageSize);
	    	getData(pageNumber,pageSize);
		}
	});
	$('.datagrid-pager .pagination-num').hide();
	//加载第一页数据
	getData(1,pageSize);
	
	function getData(pageNum, pageSize){
		$('#tt').datagrid('loading');
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/organ/organList',  
	        data:'selectOrganId='+selectOrganId+'&page='+pageNum+'&pagesize='+pageSize,
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
	}
});
function nameformater(value,row,index){
	var opStr='';
	if(row.parentOrganId!=0){
		opStr='<a class="si-title-a" href="${ctxA}/sys/organ/form?organId='+row.organId+'&selectOrganId='+selectOrganId+'">'+value+'</a>';
	}else{
		opStr=value;
	}
	return opStr;
}
function optionformater(value,row,index){
	var opStr='';
	<shiro:hasPermission name="sys:organ:edit">
	if(row.parentOrganId!=0){
		opStr+='<a class="si-option-a" href="${ctxA}/sys/organ/form?organId='+row.organId+'&selectOrganId='+selectOrganId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/sys/organ/delete?organId='+row.organId+'&selectOrganId='+selectOrganId+'" onclick="return confirmx(\'确定要删除该组织吗？\', this.href)">删除</a>';
	}
	</shiro:hasPermission>
	return opStr;
}
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="active"><a href="javascript:void(0);">组织列表</a></li>
  <shiro:hasPermission name="sys:organ:edit">
  <li><a id="toOrganForm" href="javascript:void(0);">添加组织</a></li>
  </shiro:hasPermission>
</ul>
<div class="si-warp">
	<sys:message content="${message}" isShowBox="false"/>
	<table id="tt" class="easyui-datagrid"
	        data-options="idField:'organId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">   
	    <thead>   
	        <tr>   
	        	<th data-options="field:'organCode',width:120,align:'center',halign:'center',fixed:true,formatter:nameformater">组织编码</th>
	            <th data-options="field:'organName',width:200,align:'center',halign:'center',fixed:true,formatter:nameformater">组织名称</th>
	            <th data-options="field:'organTypeCode',width:120,align:'center',halign:'center',fixed:true">组织类型编码</th>
	            <th data-options="field:'organTypeName',width:120,align:'center',halign:'center',fixed:true">组织类型</th>
	            <th data-options="field:'option',width:120,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
	        </tr>
	    </thead>   
	</table>  
</div>
</body>
</html>