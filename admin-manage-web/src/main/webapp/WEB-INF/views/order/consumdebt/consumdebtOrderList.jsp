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
	<script type="text/javascript" src="/static/plugin/uploadFile/ajaxfileupload.js"></script>
	<script type="text/javascript">
        var pager;
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum =1;
        var state = [];
        $(function(){
            $("#myModal").hide();
            $("#myModal1").hide();

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
                    var address = $("#address").val();
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
        function openUrl(obj){
			debugger;
            var url=obj.value;
            window.open(encodeURI(url));
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
                            debugger;
                            if (data.code ==1) {

                                window.open("${ctxA}/order/consumdebt/exportConsumdebtOrderList?"+url);
                            } else {
                                top.layer.alert(data.msg, {icon: 5});
                            }

                        }, "json");
                }
            })
        }


        //上传文件路径
        function uploadFile(){
            var src = "${ctxA}/order/consumdebt/uploadFile";
            if($('#filePath').val()==''){
                top.layer.alert("请选择上传文件", {icon: 5});
                return false;
            }
            var loading = top.layer.load(2);
            jQuery.ajaxFileUpload({
                url:src, //需要链接到服务器地址
                secureuri:false,
                fileElementId:"filePath", //文件选择框的id属性
                dataType: 'json',  //服务器返回的格式类型
                success: function (data, status){

                    var dataMap = eval("("+data+")");

                    top.layer.close(loading);

                    if(dataMap.success){
                        $('#filePath').attr('data-value',dataMap.url);
                        top.layer.alert(dataMap.msg, {icon: 6});
                    }else{
                        top.layer.alert(dataMap.msg, {icon: 5});
                    }
                },
                error: function (data, status, e){
                    var dataMap = eval("("+data+")");
                    top.layer.alert(dataMap.msg, {icon: 5});
                }
            })
        }
        //批量添加发货信息
        function doBatchDelivery() {
            var src = "${ctxA}/order/consumdebt/doBatchDelivery";
            var txt = "filePath=" +  $("#filePath").attr('data-value');
            if($("#filePath").attr('data-value')==''){
                top.layer.alert('请选择上传文件', {icon: 5});
                return false;
            }
            var loading = top.layer.load(2);
            jQuery.ajax({
                url:src,
                data:"filePath="+$("#filePath").attr('data-value'),
                dataType: 'json',
                success: function (data, status){
                    top.layer.close(loading);
                    var dataMap = eval("("+data+")");
                    if(dataMap.success){
                        $('#successCount').attr('value',dataMap.successCount);
                        $('#failCount').attr('value',dataMap.failCount);
                        $('#failFilePath').val(dataMap.failCount);
                        $('#failFilePath').attr('value',dataMap.failFilePath);
                    }else{
                        top.layer.alert(dataMap.msg, {icon: 5});
                    }
                    $('#page-selection-post').css('display','none');
                    $("#batchOrderModal").modal("show");
                },
                error: function (data, status, e){
                    var dataMap = eval("("+data+")");
                    top.layer.close(loading);
                    top.layer.alert("error" + dataMap.msg, {icon: 5});
                }
            })
        }
        function batchDelivery() {
            $('#filePath').val('');
            $('#page-selection-post').css('display','none');
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
			<select id="productId" name="productId" class="selectpicker show-tick form-control" onchange="getData()">
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
			<input id="batchDelivery" class="btn btn-primary"  data-toggle="modal" data-target="#myModal1"  type="submit" onclick="batchDelivery()" value="批量发货" />
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content radius">
			<div class="modal-header" style="height: 60px;">
				<h3 class="modal-title" style="float: left;">批量发货</h3>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<font color="#FF0000">批量发货需导入物流单号文件</font>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" type="button" onclick="doBatchDelivery()">已上传直接导入</button>
				</div>
				<div class="form-group">
					<input name="file" data-value="${filePath}" id="filePath" type="file"/><br>
					<button class="btn btn-primary" type="button" onclick="uploadFile()">上传物流单号文件</button>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>

		</div>
	</div><!-- /.modal-content -->
	<form class="form-horizontal" id="batchOrderForm" enctype="multipart/form-data" role="form">
		<div id="batchOrderModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content radius">
					<div class="modal-header" style="height: 60px;">
						<h3 class="modal-title" style="float: left;">导入发货</h3>
						<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();" style="float: right; width: 20px; height: 20px;">×</a>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-1" style="width:100px">导入成功:</label><input type="text" value="$!{successCount}" id="successCount" name="successCount" disabled="true"/>条
						</div>
						<div class="form-group">
							<label class="col-sm-1" style="width:100px">导入失败:</label><input type="text" value="$!{failCount}" id="failCount" name="failCount" disabled="true"/>条
						</div>
						<div class="form-group">
							<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
							<button class="btn btn-primary" type="button" value="$!{failFilePath}" id="failFilePath" name="failFilePath" onclick="openUrl(this)">导出失败数据</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div><!-- /.modal -->
</body>
</html>