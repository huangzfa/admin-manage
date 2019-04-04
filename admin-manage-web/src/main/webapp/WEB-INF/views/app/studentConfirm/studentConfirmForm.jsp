<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ajaxfileupload"/>
    <!--  -->
    <style type="text/css">

        table.config_info {
            background-color: rgba(242, 242, 242, 1);
            margin-top:15px;
            border-collapse:collapse;
            border:1px solid #aaa;
            width: 100%;
            margin-left:2%;

        }
        table.config_info th {
            vertical-align:text-top;
            padding:6px 15px 6px 6px;
            border:1px solid #aaa;
        }
        table.config_info td {
            text-align: center;
            vertical-align:text-top;
            padding:6px 15px 6px 6px;
            border:1px solid #aaa;
            background-color: white;
            width: 5%;
        }
    </style>

</head>
<style>
</style>
<script>
    $(function(){
        var appLists ='${appLists}';
        var appList = eval("("+appLists+")");
        var appId = '${appId}';
        for( var i = 0;i<appList.length;i++){
            $("#appId").append("<option value='"+appList[i].id+"'>"+appList[i].appName+"</option>");
        }
        if( appId!=''){
            $("#appId").val(appId);
        }else{
            $("#appId").val(appList[0].id);
        }
        //加载第一页数据
        getData();
        $('#search').click(function(){
            pageNum = 1;
            getData();
        });

    });
    function getData(){
        var data = {
            'appId':$('#appId').val(),
        }
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/app/studentConfirm/studentConfirmList',
            data:data,
            dataType:'json',
            success:function(data){
                if(data.code==1){
                    $('#tt').datagrid('loadData', data.list);
                }else{
                    top.$.jBox.tip(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                $('#tt').datagrid('loaded');
            }
        });
    }
    function editState(id,status,switchId) {
        $('#tt').datagrid('loading');
        var title = "确定启用？";
        if(status == 1){
            title = "确定禁用？";
        }
        top.$.jBox.confirm(title,'系统提示',function(v,h,f){
            $('#tt').datagrid('loaded');
            if(v=='ok'){
                jQuery.post('${ctxA}/app/studentConfirm/editState',{'isEnable':status,'switchId':switchId,'appId':$("#appId").val()},
                    function(data) {
                        if (data.code ==1) {
                            top.layer.alert("操作完成", {
                                icon: 6,
                                end: function(){
                                    window.location.href="${ctxA}/app/studentConfirm/list?appId="+$("#appId").val();
                                }
                            });
                        } else {
                            top.layer.alert(data.msg, {icon: 5});
                        }
                        return;
                    }, "json");
            }
        })

    }
    function appOsformatter(value,row,index){
      if (value == 'ios'){
          return "ios";
      }else if(value == "android"){
          return "安卓";
      }
      return "未知";
    }

    function isEnableformatter(value,row,index){
        if (value == '0'){
            return "禁用";
        }else if(value == "1"){
            return "启用";
        }
        return "未知";
    }

    function optionformatter(value,row,index){
        var opStr='';
        var isEnable = 0;
        if( row.isEnable == 0) isEnable = 1;
        if (value == null){
            value = '';
        }
        opStr+="<a class='si-option-a' href='javascript:editState(\""+value+"\",\""+isEnable+"\" ,\""+row.id+"\"   )'>"+(row.isEnable==0?"启用":"禁用")+"</a>";
        return opStr;
    }
</script>
<body>
<%--<ul class="nav nav-tabs">
    <li><a href="${ctxA}/oc/config/operConfigList">运营化配置</a></li>
</ul>--%>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form _clearfix">

        <li>
            <label>应用名称：</label>
            <select id="appId" name="appId" class="selectpicker show-tick form-control">
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
            <th style="width:30%;" data-options="field:'appOsType',width:80,align:'center',halign:'center',fixed:true,formatter:appOsformatter"> 客户端</th>
            <th style="width:50%;" data-options="field:'isEnable',width:120,align:'center',halign:'center',fixed:true,formatter:isEnableformatter">启用状态</th>
            <th style="width:20%;" data-options="field:'switchAppId',width:160,align:'left',halign:'center',fixed:true,formatter:optionformatter">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>