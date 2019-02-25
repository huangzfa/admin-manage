<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date" />
<!--  -->
<style type="text/css">
	.datagrid-pager,.pagination{
		width:100% !important;
		position: absolute !important;
		bottom:0 !important;
	}
	.datagrid-wrap{
		height: 500px;
	}
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

        var repayState = $('#repayStateQuery').val();
		$('#tt').datagrid('loading');
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/bpo/repay/repayList',
	        data:{'repayNo':$('#repayNo').val(),'borrowNo':$('#borrowNo').val(),'userName':$('#userName').val(),'accountNo':$('#accountNo').val()
            ,'upsOrderMo':$('#upsOrderMo').val(),'repayStartTime':$('#repayStartTime').val(),'repayEndTime':$('#repayEndTime').val() ,'repayState': repayState
            ,'productName':$('#productName').val() ,'page':pageNum,'pagesize':pageSize},
	        dataType:'json',  
	        success:function(data){
                $('#tt').datagrid('loaded');
                if(data.code==1){
                $('#repayStateQuery').html("")
                $("<option value =''>全部</option>").appendTo($("#repayStateQuery"));

                for (var i = 0 ;i <data.repayState.length ; i++){
                    var option = ""
                    if(data.repayState[i].dicVal == repayState){
                        option = "<option value= '"+data.repayState[i].dicVal+"'selected = 'selected'>"+ data.repayState[i].dicCode + "</option>"
                    }else{
                        option = "<option value= '"+data.repayState[i].dicVal+"'>"+ data.repayState[i].dicCode + "</option>"
                    }
                    $(option).appendTo($("#repayStateQuery"));
                }
	        		$('#tt').datagrid('loadData', data.list);
                }else{
                    top.layer.alert(data.msg,{icon: 5})
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
                top.layer.alert(data.msg,{icon: 5})
	        	$('#tt').datagrid('loaded');
	        }
	    });
	}
	
	$('#search').click(function(){
		getData(1,pageSize);
	});
});

function reviewStateformater(value,row,index){
	if(value=='0'){
		return "申请/未审核";
	}else if(value=='1'){
		return "风控同意";
	}else if (value == '2'){
        return "机审中";
	} else if (value == '3'){
        return "人审中";
    }else if (value == '4'){
        return "风控拒绝";
    }
    return '未知';
}
function borrowStateformater(value,row,index){
    if(value=='0'){
        return "申请/未审核";
    }else if(value=='1'){
        return "已结清";
    }else if (value == '2'){
        return "打款中";
    } else if (value == '3'){
        return "打款失败";
    }else if (value == '4'){
        return "关闭";
    }else if (value == '5'){
        return "已经打款/待还款";
    }
    return '未知';
}

function optionformater(value,row,index){
	var opStr='';
	<shiro:hasPermission name="bpo:repay:view">
		opStr+='<a class="si-option-a" href="${ctxA}/bpo/repay/form?id='+row.id+'">详情</a>';
	</shiro:hasPermission>
	return opStr;
}
function nperformater(value,row,index) {
    var str= "";
    if (row.borrowNperUnit ==1){
        str = row.borrowNperInterval +"天";
    } else if (row.borrowNperUnit ==2) {
        str = row.nper + row.borrowNperUnit + "期";
    }
    return str;
}

</script>
</head>
<body>
<div class="breadcrumb form-search">
	<ul class="ul-form">
		<li>
			<label>流水号：</label>
			<input id="repayNo"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>借款编号：</label>
			<input id="borrowNo"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>用户账号：</label>
			<input id="userName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>还款账号：</label>
			<input id="accountNo"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label>第三方流水单号：</label>
			<input id="upsOrderMo" class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>还款时间：</label>
            <input id="repayStartTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'repayEndTime\')}'})" />
		</li>
		<li>
			<label>至</label>
            <input id="repayEndTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'repayStartTime\')}'})" />
		</li>
		<li>
            <label>还款状态：</label>
            <select id="repayStateQuery">
                <option value="">全部</option>
            </select>
		</li>

	</ul>
	<ul class="ul-form">
		<li>
			<label>产品名称：</label>
			<input id="productName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
	<sys:message content="${message}" isShowBox="false" />
	<table  id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true" >
		<thead>
		<tr>
			<th style="width: 5%" data-options="field:'userName',width:30,align:'center',halign:'center',fixed:true">用户账号</th>
			<th style="width: 5%"  data-options="field:'realName',width:45,align:'center',halign:'center',fixed:true">用户姓名</th>
			<th style="width: 5%" data-options="field:'productName',width:40,align:'center',halign:'center',fixed:true">产品名称</th>
			<th style="width: 7%" data-options="field:'borrowNo',width:30,align:'center',halign:'center',fixed:true">借款编号</th>
			<th style="width: 8%" data-options="field:'borrowAddTime',width:40,align:'center',halign:'center',fixed:true">借款时间</th>
			<th style="width: 5%" data-options="field:'arrearsAmount',width:30,align:'center',halign:'center',fixed:true">到期应还</th>
			<th style="width: 7%" data-options="field:'realArrearsAmount',width:30,align:'center',halign:'center',fixed:true">当时到期实际应还</th>
			<th style="width: 5%" data-options="field:'nperName',width:20,align:'center',halign:'center'">借款周期</th>
			<th style="width: 5%" data-options="field:'repayAmount',width:30,align:'center',halign:'center',fixed:true">还款金额</th>
			<th style="width: 5%" data-options="field:'rebateAmount',width:30,align:'center',halign:'center'">余额抵扣</th>
			<th style="width: 5%" data-options="field:'couponAmount',width:30,align:'center',halign:'center'">优惠券抵扣</th>
			<th style="width: 5%" data-options="field:'realAmount',width:50,align:'center',halign:'center'">实际支付</th>
			<th style="width: 5%" data-options="field:'accountTypeName',width:30,align:'center',halign:'center'">还款方式</th>
			<th style="width: 8%" data-options="field:'repayNo',width:30,align:'center',halign:'center'">还款编号</th>
			<th style="width: 5%" data-options="field:'accountNo',width:30,align:'center',halign:'center'">还款账号</th>
			<th style="width: 8%" data-options="field:'addTime',width:30,align:'center',halign:'center'">还款时间</th>
			<th style="width: 5%" data-options="field:'repayStateName',width:50,align:'center',halign:'center',fixed:true">还款结果</th>
			<th style="width: 5%" data-options="field:'failReason',width:30,align:'center',halign:'center'">失败原因</th>
			<th style="width: 7%" data-options="field:'upsOrderNo',width:50,align:'center',halign:'center',fixed:true">统一还款流水</th>
			<th style="width: 5%" data-options="field:'option',width:60,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
<script>

</script>
</html>