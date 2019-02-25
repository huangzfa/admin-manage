<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,layer" />
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
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
	        url:'${ctxA}/operation/cityHouse/listData',  
	        data:'&page='+pageNum+'&pagesize='+pageSize,
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
	
	$('#search').click(function(){
		getData(1,pageSize);
	});
	
});
function nameformater(value,row,index){
	var opStr='<a class="si-title-a" href="${ctxA}/operation/cityHouse/form?cityHouseId='+row.cityHouseId+'">'+value+'</a>';
	return opStr;
}
function thumbformater(value,row,index){
	return '<img src="'+row.titleImg+'" style="width: 120px;height: 80px;margin: 5px 0;"/>';	
}
function optionformater(value,row,index){
	var opStr='';
	<shiro:hasPermission name="operation:cityHouse:edit">
	opStr+='<a class="si-option-a" href="${ctxA}/operation/cityHouse/form?cityHouseId='+row.cityHouseId+'">修改</a>';
	opStr+='<a class="si-option-a" href="${ctxA}/operation/cityHouse/delete?cityHouseId='+row.cityHouseId+'" onclick="return confirmx(\'确定要删除该城市房源吗？\', this.href)">删除</a>';
	</shiro:hasPermission>
	return opStr;
}

</script>
</head>
<body>
	<ul class="nav nav-tabs" style="margin-bottom:3px;">
		<li class="active"><a href="javascript:void(0);">城市房源列表</a></li>
		<shiro:hasPermission name="operation:cityHouse:edit">
		<li><a href="${ctxA}/operation/cityHouse/form">添加城市房源</a></li>
		</shiro:hasPermission>
	</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="刷新缓存" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'cityHouseId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'titleImg',width:130,halign:'center',align:'center',fixed:true,formatter:thumbformater">主图</th>
				<th data-options="field:'shortName',width:120,halign:'center',formatter:nameformater">城市</th>
				<th data-options="field:'pinYin',width:120,halign:'center'">城市拼音</th>
				<th data-options="field:'labels',width:200,halign:'center',fixed:true">房源标签</th>
				<th data-options="field:'sort',width:60,halign:'center',fixed:true">排序</th>
				<th data-options="field:'createTime',width:130,halign:'center',fixed:true">创建时间</th>
				<th data-options="field:'option',width:140,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>