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
    <li><a href="${ctxA}/business/list">业务列表</a></li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="businessForm" modelAttribute="business"   action="${ctxA}/business/save" method="post" class="form-horizontal">
        <input type="hidden" name="bizCode" id="bizCode" value="${bizCode}">
        <input type="hidden" name="serviceCodes" id="serviceCodes">
        <div class="control-group">
            <label class="control-label">服务类型：</label>
            <div class="controls">
                <div class="form-group">
                    <c:forEach items="${services}" var="data">
                        <label class="checkbox-inline">
                            <input type="checkbox" ${data.checked} value="${data.serviceCode}" >${data.serviceName}
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="merchant:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/merchant/list'"/>
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
        var serviceCode = new Array();
        $("#businessForm  input[type=checkbox]").each(function(){
            if($(this).prop("checked")){
                serviceCode.push($(this).val());
            }
        })
        $("#serviceCodes").val(serviceCode.join(","));
        $("#btnSubmit").attr("disabled",true);
        var form=$("#businessForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/business/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>