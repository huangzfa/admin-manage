<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
<!--  -->
<style type="text/css">
.userFeedbackTableTitle{
	font-size: 19px;
}
</style>

</head>
<body>
<div class="breadcrumb form-search">
	<ul class="ul-form">
		<li>
			<label>用户账号：</label>
			<input id="userName"  class="input-medium" type="text" value="" maxlength="50" />
		</li>
        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询" />
        </li>
	</ul>

</div>
<div class="si-warp" >
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true" >
		<thead>
		<tr>
			<th style="width: 8%" data-options="field:'userName',width:30,align:'center',halign:'center',fixed:true">用户账号</th>
			<th style="width: 8%"  data-options="field:'realName',width:45,align:'center',halign:'center',fixed:true">用户姓名</th>
			<th style="width: 8%" data-options="field:'name',width:40,align:'center',halign:'center',fixed:true">身份证姓名</th>
			<th style="width: 15%" data-options="field:'frontUrl',width:30,align:'center',halign:'center',fixed:true,formatter:imgformater">身份证正面(可点击)</th>
			<th style="width: 15%" data-options="field:'behindUrl',width:60,align:'center',halign:'center',fixed:true,formatter:imgformater">身份证反面(可点击)</th>
			<th style="width: 15%" data-options="field:'faceU_url',width:30,align:'center',halign:'center',fixed:true,formatter:imgformater">人脸识别(可点击)</th>
			<th style="width: 15%" data-options="field:'faceEnvUrl',width:60,align:'center',halign:'center',fixed:true,formatter:imgformater">人脸识别-半身照(可点击)</th>
			<th style="width: 15%" data-options="field:'faceAct1Url',width:30,align:'center',halign:'center',fixed:true,formatter:imgformater">人脸识别-动图1(可点击)</th>
			<th style="width: 15%" data-options="field:'faceAct2Url',width:60,align:'center',halign:'center',fixed:true,formatter:imgformater">人脸识别-动图2(可点击)</th>
			<th style="width: 15%" data-options="field:'faceAct3Url',width:30,align:'center',halign:'center',fixed:true,formatter:imgformater">人脸识别-动图3(可点击)</th>
		</tr>
		</thead>
	</table>
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
        $('#tt').datagrid('loading');
        var userName = $('#userName').val();
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/csc/userUpdate/userUpdateList',
            data:{'userName':userName ,'page':pageNum,'pageSize':pageSize},
            dataType:'json',
            success:function(data){
                $('#tt').datagrid('loaded');
                if(data.code == 1){
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
    function imgformater(value,row,index){
		if (value != null){
		    return "<img src ='"+value+"'style='width:100%' onclick='window.open(\""+value+"\")'>"
		} else{
		    return null;
		}
    }
   function imgOnClick(url){
       window.open = url
    }





</script>
</html>