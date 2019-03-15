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
            url:'${ctxA}/order/overdue/overdueList',
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


    function optionformater(value,row,index){

        var opStr='';
        <shiro:hasPermission name="order:overdue:edit">
        opStr+='<a class="si-option-a" href="#"  data-toggle="modal" data-target="#myModal" onclick="getDerateOverInfo('+row.id+')">编辑</a>';
        </shiro:hasPermission>
        return opStr;
    }
    function getDerateOverInfo(borrowId){
        $("#derateAmount").val("");
        $("#registerProductName").html("");
        $("#registerMobile").html("");
        $("#waitRepayOverdueAmount").html("");
        $("#borrowId").val(borrowId);
        jQuery.post('${ctxA}/order/overdue/form',{'id':$("#borrowId").val()},
            function(data) {
                if (data.code ==1) {
                    $("#registerProductName").html($("#productId option:selected").text());
                    $("#registerMobile").html(data.userInfo.userName);
                    var waitRepayOverdueAmount = data.borrow.overdueAmount - data.borrow.sumOverdueAmount - data.borrow.derateOverdue;
					$("#waitRepayOverdueAmount").html(waitRepayOverdueAmount);
                } else {
                    top.layer.alert("系统异常", {icon: 5});
                }

            }, "json");
    }

    function derate(){
        var borrowId = $("#borrowId").val();
        var waitRepayOverdueAmount = $("#waitRepayOverdueAmount").html();
        var derateAmount = $("#derateAmount").val();

        if (waitRepayOverdueAmount < derateAmount){
            top.layer.alert("减免金额不能大于待还逾期费", {icon: 5});
            return false;
        }
        jQuery.post('${ctxA}/order/overdue/derate', {'id':$("#borrowId").val(),"derateAmount":derateAmount},
            function(data) {
                if (data.code ==1) {
                    top.layer.alert("减免成功", {
                        icon: 6,
                        end: function(){
                            window.location.href="${ctxA}/order/overdue/list?productId="+$("#productId").val();
                        }
                    });
                } else {
                    top.layer.alert("系统异常", {icon: 5});
                }

            }, "json");
    }


    function overdueAmountformater(value,row,index){
       return row.overdueAmount - row.sumOverdueAmount - row.derateOverdue
    }

</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">逾期借款订单</a></li>
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
			<select id="productId" name="productId" class="selectpicker show-tick form-control">
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
			<th style="width: 15%" data-options="field:'borrowNo',width:120,align:'center',halign:'center',fixed:true">服务订单号</th>
			<th style="width: 10%" data-options="field:'productName',width:160,align:'center',halign:'center',fixed:true">产品（平台）名称</th>
			<th style="width: 7%" data-options="field:'realName',width:160,align:'center',halign:'center',fixed:true">姓名</th>
			<th style="width: 10%" data-options="field:'userName',width:160,align:'center',halign:'center',fixed:true">注册手机号</th>
			<th style="width: 7%" data-options="field:'amount',width:45,align:'center',halign:'center',fixed:true">借款金额</th>
			<th style="width: 15%" data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">申请时间</th>
			<th style="width: 7%" data-options="field:'sumOverdueAmount',width:45,align:'center',halign:'center',fixed:true">已还逾期费</th>
			<th style="width: 7%" data-options="field:'waitRepayOverdueAmount',width:160,align:'center',halign:'center',fixed:true,formatter:overdueAmountformater">未还逾期费</th>
			<th style="width: 7%" data-options="field:'derateOverdue',width:45,align:'center',halign:'center',fixed:true">已减免金额</th>
			<th style="width: 15%"  data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-body">
				<input type="hidden" id="borrowId" value="">
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">注册产品：</label>
					<div class="controls" style="display: inline-block">
						<label class="control-label" style="text-align: center" id="registerProductName"></label>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">注册手机号：</label>
					<div class="controls" style="display: inline-block">
						<label class="control-label" style="text-align: center" id="registerMobile"></label>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">未还逾期费：</label>
					<div class="controls" style="display: inline-block">
						<label class="control-label" style="text-align: center" id="waitRepayOverdueAmount"></label>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">减免金额：</label>
					<div class="controls" style="display: inline-block">
						<input  class="selectpicker show-tick form-control" id="derateAmount" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" onclick="derate()" >提交</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
</html>