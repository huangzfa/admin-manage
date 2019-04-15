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
        var state = [];
        $(function(){
            $("#myModal").hide();
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
            var stateList = eval("("+'${stateList}'+")");
            for( var i = 0;i<stateList.length;i++){
                $("#stateQuery").append("<option value='"+stateList[i].dicVal+"'>"+stateList[i].dicCode+"</option>");
            }
            for( var i = 0;i<stateList.length;i++){
                $("#state").append("<option value='"+stateList[i].dicVal+"'>"+stateList[i].dicCode+"</option>");
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
                'orderNo':$('#orderNo').val(),
                'productId':$("#productId").val(),
                'userId':$("#userId").val(),
                'logisticsNo':$("#logisticsNo").val(),
                'startTime':$("#startTime").val(),
                'endTime':$("#endTime").val(),
                'state':$("#stateQuery").val(),
                'page':pageNum,
                'pagesize':pageSize,
                /*      'startDate':$('#startDate').val(),
                      'endDate':$('#endDate').val()*/

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/order/consumdebt/consumdebtOrderList',
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
        function stateformatter(value,row,index){
            if(value=='0'){
                return "新建";
            }else if(value=='1'){
                return "已收货";
            }else if(value=='2') {
                return "待发货";
            }else if(value=='3') {
                return "已发货";
            }else if(value=='4') {
                return "关闭";
            }
            return '未知';
        }
        function iconurlformatter(value,row,index){
            if(value!="" && value!=null){

                return opStr='<img src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'

            }else{

                return '';
            }
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
            <shiro:hasPermission name="order:consumdebt:view">
            opStr+='<a class="si-option-a" href="#"  data-toggle="modal" data-target="#myModal" onclick="form('+row.id+',1)">查看详情</a>';
            </shiro:hasPermission>
            if(row.state == '2') {
                <shiro:hasPermission name="order:consumdebt:edit">
                opStr+='<a class="si-option-a" href="#"  data-toggle="modal" data-target="#myModal" onclick="form('+row.id+',2)">发货</a>';
                </shiro:hasPermission>
            }

            return opStr;
        }

        function form(id,opertionState){
            $("#updateButton").hide();
            $("#id").val("");
            $("#state").val("")
            $("#consigneeMobile").val("")
            $("#consignee").val("")
            $("#address").val("")
            $("#remark").val("")
            jQuery.post('${ctxA}/order/consumdebt/form',{'id':id},
                function(data) {
                    if (data.code ==1) {
                        $("#id").val(data.consumdebtOrder.id);
                        $("#state").val(data.consumdebtOrder.state)
                        $("#consigneeMobile").val(data.consumdebtOrder.consigneeMobile)
                        $("#consignee").val(data.consumdebtOrder.consignee)
                        $("#address").val(data.consumdebtOrder.address)
                        $("#remark").val(data.consumdebtOrder.remark)
                        if (opertionState == 1){
                            getInfo(data);
                        }else if (opertionState == 2) {
                            delivery(data)
                        }
                    } else {
                        top.layer.alert("系统异常", {icon: 5});
                    }

                }, "json");
        }

        function getInfo(data){
            $("#updateButton").hide();
            $("#state").prop('disabled', true)
            $("#consigneeMobile").prop('disabled', true)
            $("#consignee").prop('disabled', true)
            $("#address").prop('disabled', true)
            $("#remark").prop('disabled', true)
        }
        function delivery(data){
            $("#updateButton").show();
            $("#state").removeAttr('disabled')
            $("#consigneeMobile").removeAttr('disabled')
            $("#consignee").removeAttr('disabled')
            $("#address").removeAttr('disabled')
            $("#remark").removeAttr('disabled')
        }

        function update() {
            top.$.jBox.confirm("确定修改？",'系统提示',function(v,h,f){
                if(v=='ok'){
                    var id =  $("#id").val();
                    var consigneeMobile = $("#consigneeMobile").val();
                    var consignee = $("#consignee").val();
                    var address = $("#address").html();
                    if (consigneeMobile == "" || consigneeMobile == null){
                        top.layer.alert("收货手机号码不能为空", {icon: 5});
                        return false;
                    }
                    if (consignee == "" || consignee == null){
                        top.layer.alert("收货人不能为空", {icon: 5});
                        return false;
                    }
                    if (address == "" || address == null){
                        top.layer.alert("收货地址不能为空", {icon: 5});
                        return false;
                    }
                    var data ={'id':id,"state":$("#state").val(),"consigneeMobile":consigneeMobile
                        ,"consignee":consignee,"address":address,"remark":$("remark").html()}
                    jQuery.post('${ctxA}/order/consumdebt/update', data,
                        function(data) {
                            if (data.code ==1) {
                                top.layer.alert("修改成功", {
                                    icon: 6,
                                    end: function(){
                                        window.location.href="${ctxA}/order/consumdebt/list?productId="+$("#productId").val();
                                    }
                                });
                            } else {
                                top.layer.alert("系统异常", {icon: 5});
                            }

                        }, "json");
                }
            })
        }

        function exportCosumdebtOrder(){
            top.$.jBox.confirm("确定导出？",'系统提示',function(v,h,f) {
                if (v == 'ok') {
                    var data = {
                        'orderNo':$('#orderNo').val(),
                        'productId':$("#productId").val(),
                        'userId':$("#userId").val(),
                        'logisticsNo':$("#logisticsNo").val(),
                        'startTime':$("#startTime").val(),
                        'endTime':$("#endTime").val(),
                        'state':$("#stateQuery").val(),
                    }
                    var url =  'orderNo='+$('#orderNo').val()+
                        '&productId='+$("#productId").val()+
                        '&userId'+$("#userId").val()+
                        '&logisticsNo'+$("#logisticsNo").val()+
                        '&startTime'+$("#startTime").val()+
                        '&endTime'+$("#endTime").val()+
                        '&state'+$("#stateQuery").val();
                    jQuery.post('${ctxA}/order/consumdebt/export', data,
                        function(data) {
                            if (data.code ==1) {
                                window.open("${ctxA}/order/consumdebt/exportConsumdebtOrderList?"+url);
                            } else {
                                top.layer.alert("系统异常", {icon: 5});
                            }

                        }, "json");
                }
            })
        }


	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">借贷商品订单</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;height: 16%">
	<ul class="ul-form _clearfix">

		<li>
			<label>借贷商品订单号：</label>
			<input id="orderNo" lass="input-large" type="text" value=""  />
		</li>

		<li>
			<label>产品名称：</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control">
			</select>
		</li>
		<li>
			<label>user_id：</label>
			<input id="userId" class="input-large" type="text" value="" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'/>
		</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label>快递单号：</label>
			<input id="logisticsNo" class="input-large" type="text" value="" />
		</li>
		<li>
			<label>创建时间：</label>
			<input id="startTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" />
		</li>
		<li>
			<label>至</label>
			<input id="endTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})" />
		</li>
		<li>
			<label>状态：</label>
			<select id="stateQuery" name="stateQuery" class="selectpicker show-tick form-control">
				<option value="">全部</option>
			</select>
		</li>
	</ul>
	<ul class="ul-form">

		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="btns">
			<input id="export" class="btn btn-primary" type="submit" value="导出CSV格式报表" onclick="exportCosumdebtOrder()" />
		</li>
		<li class="btns">
			<input id="batchDelivery" class="btn btn-primary" type="submit" value="批量发货" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div  style="height: 75%">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th style="width: 5%" data-options="field:'userId',width:120,align:'center',halign:'center',fixed:true">user_id</th>
			<th style="width: 8%" data-options="field:'orderNo',width:160,align:'center',halign:'center',fixed:true">借贷商品订单号</th>
			<th style="width: 10%;" data-options="field:'goodsIcon',width:120,align:'center',halign:'center',fixed:true,formatter:iconurlformatter">图片</th>
			<th style="width: 3%" data-options="field:'productName',width:160,align:'center',halign:'center',fixed:true">产品（平台）名称</th>
			<th style="width: 3%" data-options="field:'priceAmount',width:45,align:'center',halign:'center',fixed:true,formatter:amountformatter">金额</th>
			<th style="width: 5%" data-options="field:'consignee',width:160,align:'center',halign:'center',fixed:true">收件人</th>
			<th style="width: 5%" data-options="field:'consigneeMobile',width:160,align:'center',halign:'center',fixed:true">联系电话</th>
			<th style="width: 10%" data-options="field:'address',width:160,align:'center',halign:'center',fixed:true">收货地址</th>
			<th style="width: 7%" data-options="field:'gmtDeliver',width:160,align:'center',halign:'center',fixed:true">发货时间</th>
			<th style="width: 10%" data-options="field:'logisticsNo',width:160,align:'center',halign:'center',fixed:true">快递单号</th>
			<th style="width: 5%" data-options="field:'logisticsCompany',width:160,align:'center',halign:'center',fixed:true">快递公司</th>
			<th style="width: 8%" data-options="field:'borrowNo',width:160,align:'center',halign:'center',fixed:true">关联借款订单服务订单号</th>
			<th style="width: 7%" data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">创建时间</th>
			<th style="width: 5%" data-options="field:'state',width:160,align:'center',halign:'center',fixed:true,formatter:stateformatter">订单状态</th>
			<th style="width: 10%"  data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformatter">操作</th>
		</tr>
		</thead>
	</table>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-body">
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">订单id：</label>
					<div class="controls" style="display: inline-block" >
						<input type="text" class="control-label"  id="id" disabled/>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">订单状态：</label>
					<div class="controls" style="display: inline-block">
						<select class="control-label" id="state"></select>
					</div>
				</div>

				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">手机号码：</label>
					<div class="controls" style="display: inline-block">
						<input type="text" class="control-label"  id="consigneeMobile" disabled/>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">收货人：</label>
					<div class="controls" style="display: inline-block">
						<input type="text" class="control-label"  id="consignee" disabled/>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">收货地址：</label>
					<div class="controls" style="display: inline-block">
						<textarea  class="control-label"  id="address" ></textarea>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">备注信息：</label>
					<div class="controls" style="display: inline-block">
						<textarea  class="control-label" id="remark" ></textarea>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="update()" id="updateButton">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
</html>