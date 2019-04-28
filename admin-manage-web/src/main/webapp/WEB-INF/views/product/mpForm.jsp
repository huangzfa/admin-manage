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
    <li><a href="${ctxA}/product/mpList">产品列表</a></li>
    <li class="active">
        <shiro:hasPermission name="product:list:edit">
            <a href="javascript:void(0);">${not empty product.id?'修改':'添加'}产品</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="productForm" modelAttribute="product"   action="${ctxA}/product/mpSave" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty product.id?product.id:''}">
        <input type="hidden" name="state" value="${not empty product.state?product.state:''}">
        <input type="hidden" name="productCode" value="${not empty product.productCode?product.productCode:''}">
        <input type="hidden" name="bizCodes" id="bizCodes">
        <div class="control-group">
            <label class="control-label">产品名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写产品名称" type="text" name="productName" id="productName" maxlength="32" value="${product.productName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">所属商户：</label>
            <div class="controls">
                <select  name="merchantId" id="merchantId" class="selectpicker show-tick form-control valid" descripe="请选择商户">
                    <option value="">请选择</option>
                    <c:forEach items="${merchants}" var="merchant">
                        <option value="${merchant.id}" ${not empty product && product.merchantId==merchant.id?"selected":''}>${merchant.merchantName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">业务类型：</label>
            <div class="controls">
                <table class="table table-hover" style="width: 40%">
                    <thead>
                        <th></th>
                        <th>业务类型</th>
                        <th>已关联服务</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${listBusin}" var="data">
                        <tr>
                            <td><input type="checkbox" ${data.checked} value="${data.bizCode}" ></td>
                            <td>${data.bizName}</td>
                            <td>${data.serviceName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="product:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/product/mpList'"/>
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
        var bizCode = new Array();
        $("#productForm  input[type=checkbox]").each(function(){
            if($(this).prop("checked")){
                bizCode.push($(this).val());
            }
        })
        $("#bizCodes").val(bizCode.join(","));
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
                        window.location.href="${ctxA}/product/mpList";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>