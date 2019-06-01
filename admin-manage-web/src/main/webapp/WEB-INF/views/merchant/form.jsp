<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,select2"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/merchant/list">商户列表</a></li>
    <li class="active">
        <shiro:hasPermission name="merchant:list:edit">
            <a href="javascript:void(0);">${not empty authConfig.id?'修改':'添加'}商户</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="merchantForm" modelAttribute="merchant"   action="${ctxA}/merchant/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty merchant.id?merchant.id:''}">
        <input type="hidden" name="merchantNo" value="${not empty merchant.merchantNo?merchant.merchantNo:''}">
        <input type="hidden" name="state" value="${not empty merchant.state?merchant.state:''}">
        <div class="control-group">
            <label class="control-label">商户名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写商户名称" type="text" name="merchantName" id="merchantName" maxlength="32" value="${merchant.merchantName}"></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="merchant:list:edit">
                <a id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;">保存</a>
            </shiro:hasPermission>
            <a id="btnCancel" class="btn" value="返 回" onclick="window.location.href='${ctxA}/merchant/list'">返回</a>
        </div>
    </form:form>


</div>
</body>
<script>

    function save(){
        var bool = true;
        /*******  验证表单必填项目   ****************/
        $(".valid").each(function() {
            var descripe  = $(this).attr("descripe");
            if( $(this).val()=="" && descripe!=""){
                top.layer.alert(descripe, {icon: 5});
                bool = false;
                return false;
            }
        })
        if( !bool ){
            return false;
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#merchantForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/merchant/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>