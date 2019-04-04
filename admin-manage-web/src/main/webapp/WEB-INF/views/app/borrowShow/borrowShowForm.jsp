<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>
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
        <table class="config_info" style="width:98%;margin-left: 1%">
            <thead>
            <th style="text-align: center;"class="list_th">配置项</th>
            <th style="text-align: center;"class="list_th">配置</th>
            </thead>
            <tbody id="config">
                <tr>
                    <td>
                        默认额度
                    </td>
                    <td>
                        <input type="text" id="showMinAmount"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  value="${not empty borrowShow.showMinAmount?cfg:amountLongToBigDecimal(borrowShow.showMinAmount):""}" placeholder="最小值"/>
                        ～
                        <input type="text" id="showMaxAmount" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  value="${not empty borrowShow.showMaxAmount?cfg:amountLongToBigDecimal(borrowShow.showMaxAmount):""}" placeholder="最大值">
                    </td>
                </tr>
                <tr>
                    <td>
                        默认期限
                    </td>
                    <td>

                        <input type="text" id="day1" value="${borrowShow.day1}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="10" />天&nbsp;&nbsp;&nbsp;
                        <input type="text"  id="day2" value="${borrowShow.day2}"onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="10" />天&nbsp;&nbsp;&nbsp;
                        <input type="text" id="day3" value="${borrowShow.day3}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="10" />天&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="form-actions" style="text-align: center">
            <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px"/>&nbsp;
        </div>
    </form:form>
</div>
<script>
    
        $(".amountInput").keyup(function () {
            var reg = $(this).val().match(/\d+\.?\d{0,2}/);
            var txt = '';
            if (reg != null) {
                txt = reg[0];
            }
            $(this).val(txt);
        }).change(function () {
            $(this).keypress();
            var v = $(this).val();
            if (/\.$/.test(v))
            {
                $(this).val(v.substr(0, v.length - 1));
            }
        });

    function flush() {
        window.location.href="${ctxA}/app/borrowShow/form?productId="+$("#productId").val();
    }
    function save(){
        debugger;
        var minAmount = $("#showMinAmount").val();
        var maxAmount = $("#showMaxAmount").val();
        if(minAmount == 0 || !(Number(minAmount) % 100 == 0)){
            top.layer.alert("最小值必须>0，且为100的倍数", {icon: 5});
            return false;
        }
        if (minAmount > maxAmount ||  !(Number(maxAmount) % 100 == 0) ){
            top.layer.alert("最大值需要大于最小值，且为100的倍数", {icon: 5});
            return false;
        }
        var day1 = $("#day1").val();
        var day2 = $("#day2").val();
        var day3 = $("#day3").val();
        if (day1 == '' && day2 == '' && day3 == ''){
            top.layer.alert("天数至少填写1个", {icon: 5});
            return false
        }
        //var action = form[0].action;
        var data = {'id':$("#id").val(),'productId':$("#productId").val()
            ,'saveMinAmount':minAmount,'saveMaxAmount':maxAmount
            ,'day1':day1,'day2':day2,'day3':day3
        }


        $("#btnSubmit").attr("disabled",true);
        jQuery.post("${ctxA}/app/borrowShow/update",data, function(data) {
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