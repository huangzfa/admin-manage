<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,common"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/resource/list">资源列表</a></li>
    <li class="active">
        <shiro:hasPermission name="resource:list:edit">
            <c:choose>
                <c:when test="${resource.id!=''}">
                    <a href="javascript:void(0);">编辑资源</a>
                </c:when>
                <c:otherwise>
                    <a href="javascript:void(0);">添加资源</a>
                </c:otherwise>
            </c:choose>

        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="resourceForm" modelAttribute="resource" action="${ctxA}/resource/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" id="id" value="${not empty resource.id?resource.id:''}">
        <input type="hidden" name="productId" id="productId" value="${productId}">
        <div class="control-group">
            <label class="control-label">选择应用：</label>
            <div class="controls">
                <select  name="appId" id="couponType" class="selectpicker show-tick form-control valid" descripe="请选择优惠券类型"  style="width: 315px;">
                    <option value="">全部</option>
                    <c:forEach items="${appList}" var="app">
                        <option value="${app.id}" ${not empty coupon && coupon.appId==app.id?"selected":''}>${app.appName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">资源名称：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="name" id="name" maxlength="20" value="${resource.name}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">资源类型：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="resType" id="resType" maxlength="50" value="${resource.resType}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">附类型：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="resTypeSec" id="resTypeSec" maxlength="20" value="${resource.resTypeSec}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">数值1配置：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="intValue" id="intValue" maxlength="10" value="${resource.intValue}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">数值2配置：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="longValue" id="longValue" maxlength="10" value="${resource.longValue}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">文本配置：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="stringValue" id="stringValue" maxlength="255" value="${resource.stringValue}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">文本配置1：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="stringValue1" id="stringValue1" maxlength="255" value="${resource.stringValue1}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">文本配置2：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="stringValue2" id="stringValue2" maxlength="255" value="${resource.stringValue2}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">小数配置：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="支持4位小数" type="text" name="decimalValue" id="decimalValue" maxlength="10" value="${resource.decimalValue}" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">描述：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="remark" id="remark" maxlength="100" value="${resource.remark}" ></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="resource:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/resource/list'"/>
        </div>
    </form:form>
</div>
<script>
    function save(){
        if( $("#name").val() == ""){
            top.layer.alert("资源名称不能为空", {icon: 5});
            return false;
        }
        if( $("#resType").val() == ""){
            top.layer.alert("资源类型不能为空", {icon: 5});
            return false;
        }
        if( $("#intValue").val()!='' && !isNumber($("#intValue").val())){
            top.layer.alert("请输入正确的数字", {icon: 5});
            return false;
        }
        if($("#longValue").val()!='' && !isNumber($("#longValue").val())){
            top.layer.alert("请输入正确的数字", {icon: 5});
            return false;
        }
        if( isNumber($("#stringValue").val()) || isNumber($("#stringValue1").val()) || isNumber($("#stringValue2").val())){
            top.layer.alert("文本框不能为全数字", {icon: 5});
            return false;
        }
        if($("#decimalValue").val()!='' && !checkFloat($("#decimalValue").val(),4)){
            top.layer.alert("请输入合法小数", {icon: 5});
            return false;
        }
        var form=$("#resourceForm");
        var action = form[0].action;
        var data = form.serialize();
        $("#btnSubmit").attr("disabled",true);
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/resource/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</body>
</html>