<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>

</head>
<body>
    <div class="si-warp">
        <br/>
        <form:form id="loanForm" modelAttribute="loan"  method="post" class="form-horizontal">
            <input type="hidden" name="id" id="id" value="${not empty loan.id?loan.id:''}">
            <div class="control-group">
                <label class="control-label">页面位置：</label>
                <div class="controls">
                    <select  name="type" id="type" class="selectpicker show-tick form-control" disabled style="width: 315px;">
                        <c:forEach items="${loanType}" var="type">
                            <option value="${type.dicVal}" ${not empty banner && loan.type==type.dicVal?"selected":''}>${type.dicCode}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">URL：</label>
                <div class="controls">
                    <input type="text" class="form-control"  type="text" name="value" id="value" maxlength="500" value="${loan.value}" style="width: 300px;"></input>
                </div>
            </div>
            <div class="form-actions">
                <shiro:hasPermission name="market:banner:edit">
                    <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
                </shiro:hasPermission>
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/loan/list'"/>
            </div>
        </form:form>
    </div>
<script>
    function save(){
        var value = $("#loanForm #value").val();
        if(value == ""){
            top.layer.alert("页面地址不能为空", {icon: 5});
            return false;
        }
        if(value.indexOf("www.")!=0 && value.indexOf("http://")!=0 && value.indexOf("https://")!=0){
            top.layer.alert("地址不合法，请重新再输", {icon: 5});
            return false;
        }
        $("#btnSubmit").attr("disabled",true);
        jQuery.post("${ctxA}/loan/save",{'value':value,'type':$("#type").val(),'id':$("#id").val()}, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/loan/list";
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