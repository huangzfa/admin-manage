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
		/*height:calc(100% - 50px)*/
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

	    var reviewState = $('#reviewStateQuery').val();
        var borrowState = $('#borrowStateQuery').val();
        var overdueState =$('#overdueStateQuery').val();
        var re = $('#realName').val()
		hjnUtils.ajax({
	        type:'post',
	        url:'${ctxA}/bpo/stageBorrow/stageBorrowList',
            data:{'borrowId':$('#borrowId').val(),'borrowNo':$('#borrowNo').val(),'userName':$('#userName').val(),'realName':$('#realName').val()
				,'borrowAmount':$('#borrowAmount').val(),'arriveAccount':$('#arriveAccount').val(),'upsOrderMo':$('#upsOrderMo').val(),'productName':$('#productName').val()
				,'reviewState': reviewState ,'borrowState':borrowState,'overdueState':overdueState,'nper':$('#nper').val()
            	,'borrowStartTime':$('#borrowStartTime').val(),'borrowEndTime':$('#borrowEndTime').val() ,'page':pageNum,'pagesize':pageSize},
	        dataType:'json',
	        success:function(data){
	            if (data.code == 1) {
                    $('#reviewStateQuery').html("")
                    $("<option value =''>全部</option>").appendTo($("#reviewStateQuery"));
                    for (var i = 0; i < data.reviewState.length; i++) {
                        var option = ""
                        if (data.reviewState[i].dicVal == reviewState) {
                            option = "<option value= '" + data.reviewState[i].dicVal + "'selected = 'selected'>" + data.reviewState[i].dicCode + "</option>"
                        } else {
                            option = "<option value= '" + data.reviewState[i].dicVal + "'>" + data.reviewState[i].dicCode + "</option>"
                        }
                        $(option).appendTo($("#reviewStateQuery"));
                    }

                    $('#borrowStateQuery').html("")
                    $("<option value=''>全部</option>").appendTo($("#borrowStateQuery"));
                    for (var i = 0; i < data.borrowState.length; i++) {
                        var option = ""
                        if (data.borrowState[i].dicVal == borrowState) {
                            option = "<option value= '" + data.borrowState[i].dicVal + "'selected = 'selected'>" + data.borrowState[i].dicCode + "</option>"
                        } else {
                            option = "<option value= '" + data.borrowState[i].dicVal + "'>" + data.borrowState[i].dicCode + "</option>"
                        }
                        $(option).appendTo($("#borrowStateQuery"));
                    }

                    $('#overdueStateQuery').html("")
                    $("<option value=''>全部</option>").appendTo($("#overdueStateQuery"));
                    for (var i = 0; i < data.overdueState.length; i++) {
                        var option = ""
                        if (data.overdueState[i].dicVal == overdueState) {
                            option = "<option value= '" + data.overdueState[i].dicVal + "'selected = 'selected'>" + data.overdueState[i].dicCode + "</option>"
                        } else {
                            option = "<option value= '" + data.overdueState[i].dicVal + "'>" + data.overdueState[i].dicCode + "</option>"
                        }
                        $(option).appendTo($("#overdueStateQuery"));
                    }
                        $('#tt').datagrid('loadData', data.list);
                }else{
                    top.layer.alert("系统异常",{icon: 5})
                }
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
                top.layer.alert("系统异常",{icon: 5})
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
	<shiro:hasPermission name="bpo:stageBorrow:view">
		opStr+='<a class="si-option-a" href="${ctxA}/bpo/stageBorrow/form?returnUrlType=-1&id='+row.id+'">查看</a>';
	</shiro:hasPermission>
    opStr+='<a class="si-option-a" href="#">重新回调</a>';
	return opStr;
}
function nperformater(value,row,index) {
    var str= "";
    if (row.borrowNperUnit ==1){
        str = row.nper +"天";
    } else if (row.borrowNperUnit ==2) {
        str = row.nper + "期";
    }
    return str;
}
function clearNoNum(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
    obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

}
</script>
</head>
<body>
<div>
	<div>
<div class="breadcrumb form-search">
	<ul class="ul-form">
		<li>
			<label>订单id：</label>
			<input id="borrowId" class="input-medium" type="text" value="" maxlength="20"  oninput = "value=value.replace(/[^\d]/g,'')" />
		</li>
		<li>
			<label>流水号：</label>
			<input id="borrowNo"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>用户账号：</label>
			<input id="userName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>用户姓名：</label>
			<input id="realName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label>借款金额：</label>
			<input id="borrowAmount" class="input-medium" type="text" value="" maxlength="50"onkeyup="clearNoNum(this)" />
		</li>
		<li>
			<label>收款账号：</label>
			<input id="arriveAccount"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>第三方流水单号：</label>
			<input id="upsOrderMo" class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>产品名称：</label>
			<input id="productName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label>审批状态：</label>
			<select id="reviewStateQuery">
                <option value="">全部</option>
			</select>
		</li>
		<li>
			<label>借款状态：</label>
			<select id="borrowStateQuery">
				<option value="">全部</option>
			</select>
		</li>
		<li>
			<label>逾期状态：</label>
			<select id="overdueStateQuery">
				<option value="">全部</option>
			</select>
		</li>
		<li>
			<label>周期：</label>
			<input id="nper"  class="input-large" type="text" value="" maxlength="8" oninput = "value=value.replace(/[^\d]/g,'')" />
		</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label>申请时间：</label>
			<input id="borrowStartTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'borrowEndTime\')}'})" />
		</li>
		<li>
			<label>至</label>
			<input id="borrowEndTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'borrowStartTime\')}'})" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
	</div>
			<sys:message content="${message}" isShowBox="false" />
			<table id="tt" class="easyui-datagrid"
				   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true" >
				<thead>
				<tr>
					<th style="width: 5%" data-options="field:'id',width:30,align:'center',halign:'center',fixed:true">订单id</th>
					<th style="width: 10%"  data-options="field:'borrowNo',width:45,align:'center',halign:'center',fixed:true">借款流水号</th>
					<th style="width: 5%" data-options="field:'productName',width:40,align:'center',halign:'center',fixed:true">产品名称</th>
					<th style="width: 5%" data-options="field:'realName',width:30,align:'center',halign:'center',fixed:true">姓名</th>
					<th style="width: 6%" data-options="field:'userName',width:40,align:'center',halign:'center',fixed:true">用户账号</th>
					<th style="width: 5%" data-options="field:'amount',width:30,align:'center',halign:'center',fixed:true">借款本金</th>
					<th style="width: 5%" data-options="field:'rateAmount',width:30,align:'center',halign:'center',fixed:true">利息金额</th>
					<th style="width: 5%" data-options="field:'nper',width:20,align:'center',halign:'center',fixed:true,formatter:nperformater">借款周期</th>
					<th style="width: 5%" data-options="field:'arrivalAmount',width:30,align:'center',halign:'center',fixed:true">到账金额</th>
					<th style="width: 8%" data-options="field:'reviewState',width:30,align:'center',halign:'center',fixed:true,formatter:reviewStateformater">风控状态</th>
					<th style="width: 8%" data-options="field:'borrowState',width:30,align:'center',halign:'center',fixed:true,formatter:borrowStateformater">放款状态</th>
					<th style="width: 15%" data-options="field:'addTime',width:50,align:'center',halign:'center',fixed:true">申请时间</th>
					<th style="width: 10%" data-options="field:'option',width:60,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
				</tr>
				</thead>
			</table>

	</div>
</div>
</body>
</html>