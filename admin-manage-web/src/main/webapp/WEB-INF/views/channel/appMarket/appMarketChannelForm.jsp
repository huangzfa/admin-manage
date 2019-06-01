<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
    <!--  -->
    <style type="text/css">
    </style>

</head>
<style>
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/channel/appMarket/list?id=${object.id}">应用市场推广渠道列表</a></li>
    <li class="active">
        <shiro:hasPermission name="channel:appMarket:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}推广渠道</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="channel:appMarket:edit">
            <a href="javascript:void(0);">查看推广渠道</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="appMarketChannel" action="${ctxA}/channel/appMarket/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" id="id" value="${not empty promotionChannel.id?promotionChannel.id:''}">
        <div class="control-group">
            <label class="control-label">渠道名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请输入投放渠道" placeholder="渠道来源，必填（限定32字）" type="text" name="channelName" id="channelName" maxlength="32" value="${promotionChannel.channelName}"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">渠道编码：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请输入编码" placeholder="编码，必填（限定32字）" onkeyup="this.value=this.value.replace(/[^\w_]/g,'');" type="text" name="channelCode" id="channelCode" maxlength="32" value="${promotionChannel.channelCode}" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">状态：</label>
            <div class="controls">
                <select  name="channelState" id="channelState" class="selectpicker show-tick form-control valid" style="width: 15%;">
                    <c:forEach items="${channelStatusList}" var="channelStatus">
                        <option value="${channelStatus.dicVal}" ${not empty promotionChannel && promotionChannel.channelState==channelStatus.dicVal?"selected":''}>${channelStatus.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">审核状态：</label>
            <div class="controls">
                <select  name="approveStatus" id="approveStatus" class="selectpicker show-tick form-control valid" style="width: 15%;">
                    <c:forEach items="${approveStatusList}" var="approveStatus">
                        <option value="${approveStatus.dicVal}" ${not empty promotionChannel && promotionChannel.approveStatus==approveStatus.dicVal?"selected":''}>${approveStatus.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="取 消" onclick="window.location.href='${ctxA}/channel/appMarket/list?id=${object.id}'"/>
            <shiro:hasPermission name="channel:appMarket:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="提 交" style="width:4%;"/>&nbsp;
            </shiro:hasPermission>
        </div>
    </form:form>


</div>
</body>
<script type="text/javascript">
    $(function(){

    });
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
        var form=$("#inputForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/channel/appMarket/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
</script>
</html>