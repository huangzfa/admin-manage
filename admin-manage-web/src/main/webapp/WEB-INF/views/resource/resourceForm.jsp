<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>

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
        <div class="control-group">
            <label class="control-label">资源名称：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="name" id="name" maxlength="20" value="${resource.name}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">资源类型：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="type" id="type" maxlength="50" value="${resource.type}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">附类型：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="secType" id="secType" maxlength="20" value="${resource.secType}"></input>
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
                <input type="text" class="form-control"  type="text" name="des" id="des" maxlength="100" value="${resource.des}" ></input>
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
        if( $("#type").val() == ""){
            top.layer.alert("资源类型不能为空", {icon: 5});
            return false;
        }
        if( !checkMath() ){
            top.layer.alert("请输入正确的数字", {icon: 5});
            return false;
        }
        if( checkNumber($("#stringValue").val()) || checkNumber($("#stringValue1").val()) || checkNumber($("#stringValue2").val())){
            top.layer.alert("文本框不能为全数字", {icon: 5});
            return false;
        }
        if($("#decimalValue").val()!='' && !checkFloat($("#decimalValue").val())){
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

    //数字框不能输入字符串
    function checkMath(){
        var check = true;
        var intValue = $("#intValue").val();
        if( intValue !='' && check == true) {
            check = checkNumber(intValue);
        }
        var longValue = $("#longValue").val();
        if( longValue !='' && check == true) {
            check = checkNumber(longValue);
        }
        return check;
    }

    function checkNumber(input) {
        var re = /^[1-9]+[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
        if (!re.test(input)) {
            return false;
        }else{
            return true
        }
    }

    /**
     * 判断是不是小数
     * @param input
     * @param s  保留几位小数
     * @returns {boolean}
     */
    function checkFloat(input,s) {
        var p = input.split(".");
        if( p .length <2 ){
            return false;
        }
        var re = /^[0-9]*]*$/;
        if( !re.test(p[0])){
            return false;
        }
         re = /^[0-9]*]*$/;
        if (!re.test(p[1])) {
            return false;
        }
        if(p[1].length>4){
            return false;
        }
        return true;
    }
</script>
</body>
</html>