<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/quartz/list">任务列表</a></li>
    <li class="active">
        <shiro:hasPermission name="quartz:list:edit">
            <a href="javascript:void(0);">${not empty authConfig.id?'修改':'添加'}任务</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="quartzForm" modelAttribute="quartz"   action="${ctxA}/quartz/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty quartz.id?quartz.id:''}">
        <input type="hidden" name="code" value="${not empty quartz.code?quartz.code:''}">
        <div class="control-group">
            <label class="control-label">任务名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写任务名称" type="text" name="name" id="name" maxlength="20" value="${quartz.name}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">执行周期：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写执行周期" type="text" name="cycle" id="cycle" maxlength="25" value="${quartz.cycle}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">执行类地址：</label>
            <div class="controls">
                <input type="text" style="width: 38%" class="form-control valid" descripe="请填写执行类地址" type="text" name="className" id="className" maxlength="70" value="${quartz.className}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否启用：</label>
            <select  id="state" name="state" class="selectpicker show-tick form-control" >
                <option value="1" ${quartz.state == 1?'selected':''}>启用</option>
                <option value="0" ${quartz.state == 0?'selected':''}>禁用</option>
            </select>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="quartz:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/quartz/list'"/>
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
        var form=$("#quartzForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/quartz/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>