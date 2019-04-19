<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date"/>

<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
    var pager;
    var pageSize=${cfg:getPageSize()};
    var pageList=[pageSize,30,50];
    var pageNum =1;
    $(function(){
        var productLists ='${productLists}';
        var productList = eval("("+productLists+")");
        var productId = '${productId}';
        for( var i = 0;i<productList.length;i++){
            $("#productId").append("<option value='"+productList[i].id+"'>"+productList[i].productName+"</option>");
        }
        if( productId!=''){
            $("#productId").val(productId);
        }else{
            $("#productId").val(productList[0].id);
        }

        pager=$('#tt').datagrid('getPager');
        pager.pagination({
            onSelectPage:function(number, size){
                pageSize = size;
                pageNum = number;
                getData();
            }
        });
        $('.datagrid-pager .pagination-num').hide();
        //加载第一页数据
        getData();
        $('#search').click(function(){
            pageNum = 1;
            getData();
        });

    });
    function getData(){
        var data = {
            'borrowNo':$('#borrowNo').val(),
			'userName':$("#userName").val(),
			'productId':$("#productId").val(),
			'page':pageNum,
			'pagesize':pageSize,
      /*      'startDate':$('#startDate').val(),
            'endDate':$('#endDate').val()*/

		}
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/order/borrow/borrowCashList',
            data:data,
            dataType:'json',
            success:function(data){
                if(data.code==1){
                    $('#tt').datagrid('loadData', data.list);
                    pager.pagination({
                        pageSize: pageSize,//每页显示的记录条数，默认为10
                        pageList: pageList,//可以设置每页记录条数的列表
                        pageNumber:pageNum,
                        layout:['list','sep','first','prev','links','next','last','sep','manual'],
                        beforePageText: '',
                        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                        afterPageText: ' 共 {pages} 页'
					})
                }else{
                    top.$.jBox.tip(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                $('#tt').datagrid('loaded');
            }
        });
    }
    function borrowStateformatter(value,row,index){
        if(value=='0'){
            return "申请";
        }else if(value=='1'){
            return "已结清";
        }else if(value=='2') {
            return "打款中";
        }else if(value=='3') {
            return "打款失败";
        }else if(value=='4') {
            return "关闭";
        }else if(value=='5') {
            return "待还款";
        }
        return '未知';
    }

    function riskStateformatter(value,row,index){
        if(value=='0'){
            return "申请";
        }else if(value=='1'){
            return "风控同意";
        }else if(value=='2') {
            return "机审中";
        }else if(value=='3') {
            return "人审中";
        }else if(value=='4') {
            return "风控拒绝";
        }
        return '未知';
    }


    function amountformatter(value,row,index){
		if (value == null || value == ''){
		    return 0.00;
		}else{
            return (value/100).toFixed(2);
		}
    }
    function optionformatter(value,row,index){

        var opStr='';
        <shiro:hasPermission name="order:borrow:view">
			opStr+='<a class="si-option-a" href="${ctxA}/order/borrow/form?id='+row.id+'&productId='+ row.productId+'">查看详情</a>';
        </shiro:hasPermission>
        return opStr;
    }

   
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">借款订单</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">

		<li>
			<label>服务订单号：</label>
			<input id="borrowNo" lass="input-large" type="text" value=""  />
		</li>

		<li>
			<label>注册手机号：</label>
			<input id="userName" class="input-large" type="text" value="" />
		</li>

		<li>
			<label>产品名称：</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control" onchange="getData()">
			</select>
		</li>


		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th style="width: 10%" data-options="field:'borrowNo',width:120,align:'center',halign:'center',fixed:true">服务订单号</th>
			<th style="width: 10%" data-options="field:'productName',width:160,align:'center',halign:'center',fixed:true">产品（平台）名称</th>
			<th style="width: 5%" data-options="field:'userId',width:160,align:'center',halign:'center',fixed:true">user_id</th>
			<th style="width: 5%" data-options="field:'realName',width:160,align:'center',halign:'center',fixed:true">姓名</th>
			<th style="
			width: 10%" data-options="field:'userName',width:160,align:'center',halign:'center',fixed:true">注册手机号</th>
			<th style="width: 7%" data-options="field:'amount',width:45,align:'center',halign:'center',fixed:true,formatter:amountformatter">借款金额</th>
			<th style="width: 5%" data-options="field:'borrowDays',width:160,align:'center',halign:'center',fixed:true">天数</th>
			<th style="width: 7%" data-options="field:'poundage',width:45,align:'center',halign:'center',fixed:true,formatter:amountformatter">交易服务费</th>
			<th style="width: 7%" data-options="field:'arrivalAmount',width:160,align:'center',halign:'center',fixed:true,formatter:amountformatter">到账金额</th>
			<th style="width: 7%" data-options="field:'borrowState',width:45,align:'center',halign:'center',fixed:true,formatter:borrowStateformatter">借款状态</th>
			<th style="width: 7%" data-options="field:'riskState',width:160,align:'center',halign:'center',fixed:true,formatter:riskStateformatter">风控状态</th>
			<th style="width: 10%" data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">申请时间</th>
			<th style="width: 10%"  data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformatter">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>