<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,select2,validation,ocupload"/>
    <!--  -->
    <style type="text/css">
        .upload_button{list-style:none}
    </style>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/merchant/list">产品列表</a></li>
    <li class="active">
        <shiro:hasPermission name="product:list:edit">
            <a href="javascript:void(0);">${not empty product.id?'修改':'添加'}产品</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="productForm" modelAttribute="product"   action="${ctxA}/product/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty product.id?product.id:''}">
        <div class="control-group">
            <label class="control-label">商户名称：</label>
            <div class="controls">
                <input type="text" class="form-control" disabled type="text"  value="${mechantName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品名称：</label>
            <div class="controls">
                <input type="text" class="form-control" disabled type="text"  value="${product.productName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品域名：</label>
            <div class="controls">
                <input type="text" class="form-control" disabled type="text"  value=""></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="product:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/product/pList'"/>
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
        var form=$("#productForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/product/pList";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>