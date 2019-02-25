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
    function updateStatus(id,status) {
        $('#tt').datagrid('loading');
        var title = "确定启用？";
        if(status == 1){
            title = "确定禁用？";
        }
        top.$.jBox.confirm(title,'系统提示',function(v,h,f){
            if(v=='ok'){
                $.ajax({
                    type: "POST",
                    url: '${ctxA}/oc/config/edtiStatus',
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    data: {'id':id,'status':status,"name":$("#operName").val()},
                    dataType: "json",
                    success: function(data){
                        $('#tt').datagrid('loaded');
                        if (data.code == 1){
                            top.layer.alert("设置成功",{icon: 6, end: function(){
                                window.location.href="${ctxA}/oc/config/info?functionCode="+data.functionCode+"&name="+data.name;
                                }
                            });
                        }else{
                            top.layer.alert(data.msg,{icon: 5});
                        }

                    },
                    error: function(msg){
                        $('#tt').datagrid('loaded');
                        top.layer.alert("设置失败",{icon: 5});
                    }
                });
            }
        })
    }
</script>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/oc/config/operConfigList">运营化配置</a></li>
    <li><a href="#">${name}</a></li>
</ul>
<div class="si-warp">
    <div class="div_back_color">
        <br>
        <input type="hidden" id="operName" value="${name}">
        <table class="config_info" style="width:98%;margin-left: 1%">
            <thead>
            <th style="text-align: center;"class="list_th">客户端</th>
            <th style="text-align: center;"class="list_th">启用状态</th>
            <th style="text-align: center;"class="list_th">操作</th>
            </thead>
            <tbody id="config">
            <c:forEach items="${list}" var="operConfig">
                <tr id ='${operConfig.id}'>
                    <td>
                        <c:if test="${operConfig.deviceType == 'ios'}">IOS</c:if>
                        <c:if test="${operConfig.deviceType == 'android'}">安卓</c:if>
                    </td>
                    <td>
                    <c:if test="${operConfig.functionSwitch == 'OFF'}">
                        禁用
                    </c:if>
                    <c:if test="${operConfig.functionSwitch ==  'ON'}">
                        启用
                    </c:if>
                    </td>
                    <td>
                    <c:if test="${operConfig.functionSwitch ==  'OFF'}">
                        <a href="#" onclick="updateStatus(${operConfig.id},0)">启用</a>
                    </c:if>
                    <c:if test="${operConfig.functionSwitch == 'ON'}">
                        <a href="#" onclick="updateStatus(${operConfig.id},1)">禁用</a>
                    </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
    </div>
</div>
</body>
</html>