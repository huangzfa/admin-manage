<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,layer,dict" />
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
	var pageSize=${cfg:getPageSize()};
	var pageList=[pageSize,30,50];
	var pager=$('#tt').datagrid('getPager');
	var curPageNumber=1;
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
	        curPageNumber=pageNumber;
	    	getData(pageNumber,pageSize);
		}
	});
	$('.datagrid-pager .pagination-num').hide();
	//加载第一页数据
	getData(1,pageSize);
	
	function getData(pageNum, pageSize){
		var data={};
		data.page=pageNum;
		data.pagesize=pageSize;
		data.orderId=$('#orderId').val();
		data.fdMobile=$('#fdMobile').val();
		data.fkMobile=$('#fkMobile').val();
		data.inDate=$('#inDate').val();
		data.outDate=$('#outDate').val();
		
		$('#tt').datagrid('loading');
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/order/refund/orderListData',  
	        data:data,
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
function houseTitleformater(value,row,index){
	return value;
}
function orderStateZdformater(value,row,index){
	return dict.orderState(value);
}
function inoutDateformater(value,row,index){
	var inDate=row.inDate+"";
	var inDateStr= inDate.substring(0,4)+"-"+inDate.substring(4,6)+"-"+inDate.substring(6);
	var outDate=row.outDate+"";
	var outDateStr= outDate.substring(0,4)+"-"+outDate.substring(4,6)+"-"+outDate.substring(6);
	return inDateStr+" ~ "+outDateStr;
}
function priceformater(value,row,index){
	return value/100.0;
}
function orderIdformater(value,row,index){
	return '<a class="si-option-a noML" href="${ctxA}/order/refund/info?orderId='+value+'">'+value+'</a>';
}
</script>
</head>
<body>
	<ul class="nav nav-tabs" style="margin-bottom:3px;">
		<li class="active"><a href="javascript:void(0);">订单列表</a></li>
	</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">
		<li>
			<label>订单编号：</label>
			<input id="orderId" placeholder="输入订单号" class="input-large" type="text" value="" style="width:150px;"/>
			<label>房东号码：</label>
			<input id="fdMobile" placeholder="输入手机号" class="input-small" type="text" value="" maxlength="50" />
			<label>房客号码：</label>
			<input id="fkMobile" placeholder="输入手机号" class="input-small" type="text" value="" maxlength="50" />
			<label>入住时间：</label>
			<input id="inDate" placeholder="入住日期" class="input-small" type="text" value="" maxlength="50" />
			-
			<input id="outDate" placeholder="退房日期 " class="input-small" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'orderId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true,nowrap:false">
		<thead>
			<tr>
				<th data-options="field:'orderId',width:140,halign:'center',fixed:true,formatter:orderIdformater">订单编号</th>
				<th data-options="field:'orderStateZd',width:120,halign:'center',align:'center',fixed:true,formatter:orderStateZdformater">订单状态</th>
				<th data-options="field:'houseTitle',width:120,halign:'center',align:'center',fixed:true,formatter:houseTitleformater">房源名称</th>
				<th data-options="field:'fdRealName',width:100,halign:'center',align:'center',fixed:true">房东姓名</th>
				<th data-options="field:'fdMobile',width:100,halign:'center',align:'center',fixed:true">房东手机号</th>
				<th data-options="field:'fkNickname',width:100,halign:'center',align:'center',fixed:true">房客昵称</th>
				<th data-options="field:'fkMobile',width:100,halign:'center',align:'center',fixed:true">房客手机号</th>
				<th data-options="field:'inDate',width:160,halign:'center',align:'center',fixed:true,formatter:inoutDateformater">入住-退房日期</th>
				<th data-options="field:'rzrNum',width:80,halign:'center',align:'center',fixed:true">入住人数</th>
				<th data-options="field:'total',width:100,halign:'center',align:'center',fixed:true,formatter:priceformater">房费总额(元)</th>
				<th data-options="field:'yajinPrice',width:80,halign:'center',align:'center',fixed:true,formatter:priceformater">押金(元)</th>
				<th data-options="field:'createTime',width:130,halign:'center',fixed:true">下单时间</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>