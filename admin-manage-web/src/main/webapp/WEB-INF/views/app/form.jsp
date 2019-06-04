<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,select2,validation"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/app/list">应用列表</a></li>
    <li class="active">
        <shiro:hasPermission name="app:list:edit">
            <a href="javascript:void(0);">${not empty app.id?'修改':'添加'}应用</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="appForm" modelAttribute="app"   action="${ctxA}/app/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty app.id?app.id:''}">
        <div class="control-group">
            <label class="control-label">所属商户：</label>
            <div class="controls">
                <select  name="merchantId" id="merchantId" class="selectpicker show-tick form-control valid" descripe="请选择商户" onchange="merchanChange(this)">
                    <c:forEach items="${merchantList}" var="merchant">
                        <option value="${merchant.id}" ${app.merchantId==merchant.id?"selected":''}>${merchant.merchantName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">所属产品：</label>
            <div class="controls">
                <select  name="productId" id="productId" class="selectpicker show-tick form-control valid" descripe="请选择产品">

                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">应用名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" id="appName" name="appName" descripe="请填写应用名称"   type="text"  value="${app.appName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">唯一key：</label>
            <div class="controls">
                <input type="text" class="form-control valid" id="appKey" name="appKey" descripe="请填写唯一key"  type="text"  value="${app.appKey}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">安卓渠道下载链接：</label>
            <div class="controls">
                <input type="text" class="form-control valid" id="androidUrl" name="androidUrl"  descripe="请填写安卓渠道下载链接" type="text"  value="${app.androidUrl}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">ios渠道下载链接：</label>
            <div class="controls">
                <input type="text" class="form-control valid" id="iosUrl" name="iosUrl" descripe="请填写ios渠道下载链接" type="text"  value="${app.iosUrl}"></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="product:list:edit">
                <a id="btnSubmit" class="btn btn-primary" onclick="save()" >保存</a>
            </shiro:hasPermission>
            &nbsp;<a id="btnCancel" class="btn" type="button" onclick="window.location.href='${ctxA}/app/list'">返回</a>
        </div>
    </form:form>


</div>
</body>
<script>
    $(function () {
        merchanChange($("#merchantId"));
    })
    /*
     * 商户下拉事件
     * */
    function merchanChange(obj) {
        $("#productId").html('');
        jQuery.post("${ctxA}/app/getProductByMerchantId", {'merchantId':$(obj).val()},function(result) {
            $("#productId").append("<option value=''>请选择产品</option>");
            for(j = 0,len=result.list.length; j < len; j++) {
                var data = result.list[j];
                var selected = "";
                if( "${productId}" == data.id + ""){
                    selected = "selected";
                }
                $("#productId").append("<option value="+ data.id +" "+selected+">"+ data.productName +"</option>")
            }
            return;
        },"json");
    }

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
        var androidUrl = $("#androidUrl").val();
        if (androidUrl.indexOf("http://") == -1 && androidUrl.indexOf("https://") == -1) {
            top.layer.alert("请填写正确安卓连接地址", {icon: 5});
            return false;
        }
        var iosUrl = $("#iosUrl").val();
        if (iosUrl.indexOf("http://") == -1 && iosUrl.indexOf("https://") == -1) {
            top.layer.alert("请填写正确ios连接地址", {icon: 5});
            return false;
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#appForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/app/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>