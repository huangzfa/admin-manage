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
	        url:'${ctxA}/operation/content/listData',  
	        data:'title='+$('#title').val()+'&contentTypeZd='+$('#contentTypeZd').val()+'&page='+pageNum+'&pagesize='+pageSize,
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
	var opStr='<a class="si-title-a" href="${ctxA}/operation/content/form?contentId='+row.contentId+'">'+value+'</a>';
	return opStr;
}
function thumbformater(value,row,index){
	return '<img src="'+row.img+'" style="width: 120px;height: 80px;margin: 5px 0;"/>';	
}
function contentTypeformater(value,row,index){
	if(row.contentTypeZd == 1){
		return 'APP首页活动内容素材';
	}else if(row.contentTypeZd == 2){
		return '房东知识';
	}
	return '未知';
}
function optionformater(value,row,index){
	var opStr='';
	<shiro:hasPermission name="operation:content:edit">
	opStr+='<a class="si-option-a" href="${ctxA}/operation/content/form?contentId='+row.contentId+'">修改</a>';
	opStr+='<a class="si-option-a" href="${ctxA}/operation/content/delete?contentId='+row.contentId+'" onclick="return confirmx(\'确定要删除该内容吗？\', this.href)">删除</a>';
	</shiro:hasPermission>
	return opStr;
}

</script>
</head>
<body>
	<ul class="nav nav-tabs" style="margin-bottom:3px;">
		<li class="active"><a href="javascript:void(0);">内容列表</a></li>
		<shiro:hasPermission name="operation:content:edit">
		<li><a href="${ctxA}/operation/content/form">添加内容</a></li>
		</shiro:hasPermission>
	</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>内容标题：</label>
			<input id="title" placeholder="内容标题" class="input-large" type="text" value="" maxlength="50" style="width:150px;"/>
			<label>内容类型：</label>
			<select id="contentTypeZd" style="width:180px;">
				<option value="">请选择</option>
				<c:forEach items="${cfg:getDictList('contentType')}" var="o">
					<option value="${o.dicVal}">${o.dicCode}</option>
				</c:forEach>
			</select>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'contentId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'contentId',width:130,halign:'center',align:'center',fixed:true">内容编号</th>
				<th data-options="field:'img',width:130,halign:'center',align:'center',fixed:true,formatter:thumbformater">主图</th>
				<th data-options="field:'mainTitle',width:120,halign:'center',formatter:nameformater">主标题</th>
				<th data-options="field:'subTitle',width:120,halign:'center'">副标题</th>
				<th data-options="field:'contentTypeZd',width:140,halign:'center',fixed:true,formatter:contentTypeformater">内容类型</th>
				<th data-options="field:'url',width:200,halign:'center',fixed:true">h5页面url</th>
				<th data-options="field:'createTime',width:130,halign:'center',fixed:true">创建时间</th>
				<th data-options="field:'option',width:220,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>