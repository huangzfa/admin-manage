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
</script>
<body>
<ul class="nav nav-tabs">
    <li><a href="#">运营化配置</a></li>
</ul>
<div class="si-warp">
    <div class="div_back_color">
        <br>
        <table class="config_info" style="width:98%;margin-left: 1%">
            <thead>
            <th style="text-align: center;"class="list_th">配置项</th>
            <th style="text-align: center;"class="list_th">配置</th>
            </thead>
            <tbody id="config">
            <c:forEach items="${list}" var="operConfig">
                <tr id ='${operConfig.id}'>
                    <td>${operConfig.name}</td>
                    <td><a href="${ctxA}/oc/config/info?functionCode=${operConfig.functionCode}&name=${operConfig.name}">配置</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
    </div>
</div>
</body>
</html>