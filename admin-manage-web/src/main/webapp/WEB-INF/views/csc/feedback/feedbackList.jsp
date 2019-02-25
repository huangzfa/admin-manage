<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date" />
<!--  -->
<style type="text/css">
.userFeedbackTableTitle{
	font-size: 19px;
}
</style>

</head>
<body>
<div class="breadcrumb form-search" style="height:17%">
	<ul class="ul-form">
		<li>
			<label>用户账号：</label>
			<input id="userName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>处理状态：</label>
			<select id="status">
				<option value="">全部</option>
			</select>
		</li>
	</ul>

	<ul class="ul-form">
		<li>
			<label>还款时间：</label>
			<input id="startTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" />
		</li>
		<li>
			<label>至</label>
			<input id="endTime" class="input-medium" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="position: relative;width: 100%;height:75%">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true" >
		<thead>
		<tr>
			<th style="width: 10%" data-options="field:'userName',width:30,align:'center',halign:'center',fixed:true">用户账号</th>
			<th style="width: 25%"  data-options="field:'detail',width:45,align:'center',halign:'center',fixed:true">反馈内容</th>
			<th style="width: 15%" data-options="field:'gmtCreate',width:40,align:'center',halign:'center',fixed:true">反馈时间</th>
			<th style="width: 10%" data-options="field:'status',width:30,align:'center',halign:'center',fixed:true,formatter:statusformater">处理状态</th>
			<th style="width: 15%" data-options="field:'option',width:60,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>

<div id="userFeedbackInfo" class="easyui-dialog" title="反馈详情" data-options="closed:true,modal:true" style="width:60%;padding:10px"  >
	<table class="userFeedbackTable" style="width:96%;margin-left: 2%">
		<tr>
			<td class="userFeedbackTableTitle">反馈账号：</td>
			<td id = "info_userName" class="userFeedbackTableValue"></td>
		</tr>
		<tr>
			<td class="userFeedbackTableTitle">反馈时间：</td>
			<td id = "info_gmtCreate" class="userFeedbackTableValue"></td>
		</tr>

		<tr>
			<td class="userFeedbackTableTitle">反馈状态：</td>
			<td id = "info_status" class="userFeedbackTableValue"></td>
		</tr>

		<tr>
			<td class="userFeedbackTableTitle">反馈内容：</td>
			<td id = "info_detail" class="userFeedbackTableValue"></td>
		</tr>

		<tr>
			<td class="userFeedbackTableTitle">处理意见：</td>
			<td id = "info_handleContent" class="userFeedbackTableValue"></td>
		</tr>

		<tr>
			<td class="userFeedbackTableTitle">处理时间：</td>
			<td id = "info_gmtModified" class="userFeedbackTableValue"></td>
		</tr>


	</table>
</div>

<div id="dealWithInfo" class="easyui-dialog" title="处理反馈" data-options="closed:true,modal:true" style="width:40%;padding:10px"  >
	<div style="text-align: center">
		处理说明： <textarea id="handleContent" style="width: 80%;"></textarea>
	</div>
	<div style="text-align: right">
		<input type="hidden" id ="dealWithUserId">
		<button style="background-color: #f0f0f0;border: 1px solid #f0f0f0" onclick="dealWithInfoClose()" >关闭</button>
		<button style="background-color:#5a87b0;color: white;border: 1px solid #f0f0f0" onclick="submitDealWithInfo()">确定</button>
	</div>

</div>
</body>
<script type="text/javascript">
    $(function(){
        $("#userFeedBacnkInfo").dialog("close");
        $("#dealWithInfo").dialog("close");
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



        $('#search').click(function(){
            getData(1,pageSize);
        });
    });
    function getData(pageNum, pageSize){
        var status = $("#status").val()
        $('#tt').datagrid('loading');
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/csc/feedback/feedbackList',
            data:{'userName':$('#userName').val(),'status': status ,'startTime':$('#startTime').val()
                ,'endTime':$('#endTime').val() ,'page':pageNum,'pagesize':pageSize},
            dataType:'json',
            success:function(data){
                $('#tt').datagrid('loaded');
                if (data.code == 1){
                $($('#status')).html("");
                $("<option value=''>全部</option>").appendTo($('#status'));
                for (var i = 0 ;i <data.status.length ; i++){
                    var option = ""
                    if(data.status[i].dicVal == status){
                        option = "<option value= '"+data.status[i].dicVal +"' selected = 'selected'>"+ data.status[i].dicCode + "</option>"
                    }else{
                        option = "<option value= '"+data.status[i].dicVal + "'>"+ data.status[i].dicCode + "</option>"
                    }
                    $(option).appendTo($('#status'));
                }
                    $('#tt').datagrid('loadData', data.list);
                }else{
                    top.layer.alert(data.msg,{icon: 5})
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                top.layer.alert("查询失败",{icon: 5})
                $('#tt').datagrid('loaded');
            }
        });
    }
    function statusformater(value,row,index){
        if(value=='0'){
            return "待处理";
        }else if(value=='1'){
            return "已处理";
        }
        return '未知';
    }
    function userFeedbackInfo(id) {
        $('#tt').datagrid('loading');
        $(".userFeedbackTableValue").html("");
        $.ajax({
            type: "POST",
            url: '${ctxA}/csc/feedback/getInfoById',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data:{"id":id},
            dataType: "json",
            success: function(data){
                $('#tt').datagrid('loaded');
                $("#userFeedbackInfo").dialog("open")
				$("#info_userName").html(data.userName);
				$("#info_gmtCreate").html(formatDateTime(data.gmtCreate));
				if (data.status == 0){
                    $("#info_status").html("待处理");
                }else if(data.status ==1){
                    $("#info_status").html("已处理");
                }
                $("#info_detail").html(data.detail);
                $("#info_handleContent").html(data.handleContent);
                if (data.gmtModified == null){
                    $("#info_gmtModified").html("");
                }else{
                    $("#info_gmtModified").html(formatDateTime(data.gmtModified));
                }
            },
            error: function(msg){
                $('#tt').datagrid('loaded');
                top.layer.alert("查询失败",{icon: 5})
            }
        });
    }
    function dealWithWindow(id) {
        $("#handleContent").html(null)
        $("#handleContent").val("")
		$("#dealWithUserId").val(id)
        $("#dealWithInfo").dialog("open")
    }
    function dealWithInfoClose() {
        $("#dealWithInfo").dialog("close")
    }
	function submitDealWithInfo() {
        $('#tt').datagrid('loading');
        $.ajax({
            type: "POST",
            url: '${ctxA}/csc/feedback/submitDealWith',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data:{"id":$("#dealWithUserId").val(),"handleContent":$("#handleContent").val()},
            dataType: "json",
            success: function(data){
                $('#tt').datagrid('loaded');
                if (data.code == 1) {
                    dealWithInfoClose()
                    $("#dealWithUserId").val(null)
                    top.layer.alert("处理成功",{icon: 6,end:function(){
                            var pageSize=${cfg:getPageSize()};
                            var grid = $('#tt');
                            var options = grid.datagrid('getPager').data("pagination").options;
                            var curr = options.pageNumber;
                            getData(curr,pageSize);
						}})

                }else{
                    top.layer.alert(data.msg,{icon: 5})
                }

            },
            error: function(msg){
                $('#tt').datagrid('loaded');
                top.layer.alert("处理失败",{icon: 5})
            }
        });
    }

    function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="bpo:stageBorrow:view">
        opStr+='<a class="si-option-a" href="#" onclick="userFeedbackInfo('+row.id+')">查看详情</a>';
        </shiro:hasPermission>
		if(row.status == 0){
        opStr+='<a class="si-option-a" href="#" onclick="dealWithWindow('+row.id+')">处理</a>';
        }
        return opStr;
    }
    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };

</script>
</html>