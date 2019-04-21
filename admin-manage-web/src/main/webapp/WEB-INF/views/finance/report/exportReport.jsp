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
            var reportTypes = '${reportType}';
            var reportType = eval("("+reportTypes+")");
            for( var i = 0;i<reportType.length;i++){
                $("#reportType").append("<option value='"+reportType[i].dicVal+"'>"+reportType[i].dicCode+"</option>");
            }
            $("#reportType").val(reportType[0].dicVal);
            var borrowStateList = eval("("+'${borrowStateList}'+")");
            for( var i = 0;i<borrowStateList.length;i++){
                $("#state").append("<option value='"+borrowStateList[i].dicVal+"' stateType = '1' >"+borrowStateList[i].dicCode+"</option>");
            }
            var repayStateList = eval("("+'${repayStateList}'+")");
            for( var i = 0;i<repayStateList.length;i++){
                $("#state").append("<option value='"+repayStateList[i].dicVal+"' stateType = '2' style='display: none'>"+repayStateList[i].dicCode+"</option>");
            }
            var renewalStateList = eval("("+'${renewalStateList}'+")");
            for( var i = 0;i<renewalStateList.length;i++){
                $("#state").append("<option value='"+renewalStateList[i].dicVal+"' stateType = '3' style='display: none'>"+renewalStateList[i].dicCode+"</option>");
            }
			$("#startTime").val('${startTime}');
            $("#endTime").val('${endTime}');
            updateStateSelect();
        });
		function updateStateSelect() {
			var reportType = $("#reportType").val();

            $("#state option").each(function() {
                var stateType = $(this).attr("stateType")
				if (stateType == reportType){
                    $(this).show();
				}else{
                    $(this).hide();
				}
            })
            $("#state option[value='']").show();
            $("#state").val("");
        }

        function exportCosumdebtOrder(){
            top.$.jBox.confirm("确定导出？",'系统提示',function(v,h,f) {
                if (v == 'ok') {
                    var data = {
                        'reportType':$('#reportType').val(),
                        'productId':$("#productId").val(),
                        'startTime':$("#startTime").val(),
                        'endTime':$("#endTime").val(),
                        'state':$("#stateQuery").val(),
                    }
                    var dataString = 'reportType=' + $('#reportType').val()+
                        '&productId=' + $("#productId").val()+
                        '&startTime=' + $("#startTime").val()+
                        '&endTime=' + $("#endTime").val()+
                        '&state=' + $("#state").val();
                    window.open("${ctxA}/finance/report/export?"+dataString);
                   /* jQuery.get('${ctxA}/finance/report/export', data,
                        function(data) {
                            debugger;
                            if (data.code ==1) {
                                window.open(+url);
                            } else {
                                top.layer.alert(data.msg, {icon: 5});
                            }

                        }, "json");*/
                }
            })
        }

	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">财务报表导出</a></li>
</ul>
<div class="form-search" style="margin-bottom:0;height: 16%">
	<ul class="ul-form _clearfix">

		<li>
			<label>选择产品</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control" >
			</select>
		</li>

	</ul>
	<ul class="ul-form _clearfix">

		<li>
			<label>报表类型</label>
			<select id="reportType" name="reportType" class="selectpicker show-tick form-control" onchange="updateStateSelect()">
			</select>
		</li>

		<li>
			<label>状态</label>
			<select id="state" name="state" class="selectpicker show-tick form-control">
				<option value="">全部</option>
			</select>
		</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label style="">时间：&nbsp&nbsp&nbsp&nbsp</label>
			<input id="startTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" />
		</li>
		<li>
			<label>至</label>
			<input id="endTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})" />
		</li>
		<li class="btns">
			<input id="export" class="btn btn-primary" type="submit" value="导出报表" onclick="exportCosumdebtOrder()" />
		</li>

	</ul>
</div>



</body>
</html>