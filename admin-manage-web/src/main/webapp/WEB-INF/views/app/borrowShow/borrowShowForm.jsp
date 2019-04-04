<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>
    <style type="text/css">
        .upload_button{list-style:none}
      /*  .controls input{
            width: 30%;
        }*/
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/app/borrowShow/form?productId=${productId}">借钱页默认配置</a></li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="borrowShowForm" modelAttribute="borrowShow" action="${ctxA}/app/borrowShow/update" method="post" class="form-horizontal">
        <input type="hidden" name="id" id="id" value="${not empty borrowShow.id?borrowShow.id:''}">
<%--        <input type="hidden" name="appId" id="appId" value="${not empty borrowShow.appId?borrowShow.appId:''}">--%>
        <div class="control-group">
            <label class="control-label">选择产品：</label>
            <div class="controls">
                <select  name="productId" id="productId" class="selectpicker show-tick form-control valid" onchange="flush()"  style="width: 315px;">
                    <c:forEach items="${productList}" var="product">
                        <option value="${product.id}" ${product.id==productId?"selected":''}>${product.productName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">广告URL：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" name="redirectUrl" id="redirectUrl" maxlength="255" value="${borrowShow.redirectUrl}" style="width: 315px;"></input>
            </div>
        </div>

        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
        </div>
    </form:form>
</div>
<script>
    function flush() {
        window.location.href="${ctxA}/app/borrowShow/form?productId="+$("#productId").val();
    }
    function save(){

        var form=$("#borrowShowForm");
        var action = form[0].action;
        var data = form.serialize();
        $("#btnSubmit").attr("disabled",true);
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/app/borrowShow/form?productId="+$("#productId").val();
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

/*
    function isNumber(val){
        var regPos = /^\d+(\.\d+)?$/; //非负浮点数
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        if(regPos.test(val) || regNeg.test(val)){
            return true;
        }else{
            return false;
        }
    }*/
</script>