<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
    var pager;
    var pageSize=${cfg:getPageSize()};
    var pageList=[pageSize,30,50];
    var pageNum =1;

$(function(){
    pager=$('#tt').datagrid('getPager');
    pager.pagination({
        onSelectPage:function(number, size){
            pageSize = size;
            pageNum = number;
            getData();
        }
    });
    $('.datagrid-pager .pagination-num').hide();

    pager.pagination({
        pageSize: pageSize,//每页显示的记录条数，默认为10
        pageList: pageList,//可以设置每页记录条数的列表
        pageNumber:pageNum,
		total:0,
        layout:['list','sep','first','prev','links','next','last','sep','manual'],
        beforePageText: '',
        afterPageText: ' 共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
    });
    $('#search').click(function(){
        pageNum = 1;
        getData();
    });
});


    function getData(){
        var data = {'userId':$('#userId').val(),'globalUserId':$('#globalUserId').val(),
            'userName':$('#userName').val(),'realName':$('#realName').val(),
            'page':pageNum,'pagesize':pageSize}
        $('#tt').datagrid('loading');
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/customer/userFiles/userList',
            data:data,
            dataType:'json',
            success:function(data){
                $('#tt').datagrid('loaded');
                if(data.code==1){
                    $('#tt').datagrid('loadData', data.list);

                }else{
                    top.layer.alert(data.msg,{icon: 5});
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                $('#tt').datagrid('loaded');
                top.layer.alert("查询失败",{icon: 5});
            }
        });
    }

function optionformatter(value,row,index){
	var opStr='';
	<shiro:hasPermission name="customer:userFiles:view">
		opStr+='<a class="si-option-a" href="${ctxA}/customer/userFiles/form?id='+row.id+'">查看详情</a>';
	</shiro:hasPermission>
	return opStr;
}
function lastLoginTimeformatter(value,row,index) {
   return row.lastLogVo.addTime;

}
function lastLoginSysformatter(value,row,index) {
        return row.lastLogVo.osType;

}
function lastLoginEditionformatter(value,row,index) {
        return row.lastLogVo.appVersion;
}
function  lastLoginPhoneTypeformatter(value,row,index) {
        return row.lastLogVo.phoneType;
}
function  lastLoginAppformatter(value,row,index) {
        return row.lastLogVo.appName;
    }

</script>
</head>
<body>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
        <li>
            <label>用户id：</label>
            <input id="userId" class="input-large" type="text" value="" maxlength="50" />
        </li>
		<li>
			<label>平台id：</label>
			<input id="globalUserId" class="input-large" type="text" value="" maxlength="50" />
		</li>
        <li>
            <label>注册手机号：</label>
            <input id="userName"  class="input-large" type="text" value="" maxlength="50" />
        </li>
		<li>
			<label>用户姓名：</label>
			<input id="realName"  class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:50px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
			<tr>
				<th style="width: 5%" data-options="field:'id',align:'center',align:'center'">用户ID</th>
                <th style="width: 16%"  data-options="field:'globalUserId',align:'center',align:'center'">平台ID</th>
                <th style="width: 5%"  data-options="field:'productName',align:'center',align:'center'">产品名称</th>
				<th style="width: 13%"  data-options="field:'userName',align:'center',align:'center'">注册手机号</th>
				<th style="width: 5%"  data-options="field:'realName',align:'center',align:'center'">用户姓名</th>
				<th style="width: 15%"  data-options="field:'lastLoginTime',align:'center',align:'center',formatter:lastLoginTimeformatter">最近登录时间</th>
                <th style="width: 10%"  data-options="field:'lastLoginApp',align:'center',align:'center',formatter:lastLoginAppformatter">最近登录应用</th>
				<th style="width: 10%"  data-options="field:'lastLoginSys',align:'center',align:'center',formatter:lastLoginSysformatter">客户端系统</th>
				<th style="width: 5%"  data-options="field:'lastLoginEdition',align:'center',align:'center',formatter:lastLoginEditionformatter">客户端版本</th>
				<th style="width: 10%"  data-options="field:'lastLoginPhoneType',align:'center',align:'center',formatter:lastLoginPhoneTypeformatter">手机型号</th>
				<th style="width: 7%"  data-options="field:'option',align:'left',align:'center',fixed:true,formatter:optionformatter">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>