<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>
    <style type="text/css">
        .upload_button{list-style:none}
      /*  .controls input{
            width: 30%;
        }*/
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/market/startupPage/form?appId=${appId}">启动页配置</a></li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="startupPageForm" modelAttribute="startupPage" action="${ctxA}/market/startupPage/update" method="post" class="form-horizontal">
        <input type="hidden" name="id" id="id" value="${not empty startupPage.id?startupPage.id:''}">
<%--        <input type="hidden" name="appId" id="appId" value="${not empty startupPage.appId?startupPage.appId:''}">--%>
        <div class="control-group">
            <label class="control-label">选择应用：</label>
            <div class="controls">
                <select  name="appId" id="appId" class="selectpicker show-tick form-control valid" onchange="flush()"  style="width: 315px;">
                    <c:forEach items="${appList}" var="app">
                        <option value="${app.id}" ${app.id==appId?"selected":''}>${app.appName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">广告URL：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" name="redirectUrl" id="redirectUrl" maxlength="255" value="${startupPage.redirectUrl}" style="width: 315px;"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">广告的图片：</label>
            <div class="controls">
                <input type="hidden" value="${startupPage.imgUrl}" name="imgUrl" id="imgUrl"  class="valid" descripe="请上传图片" >
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon1" filename="imgUrl" sort="1" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty startupPage.imgUrl?startupPage.imgUrl:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
                        </li>
                    </ul>
                    <ul style="margin-left: 3%;float: left">
                        <small class="help-block owner_ID">点击图片即可重新上传（必传）</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：1M以内</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            <c:if test="${startupPage.isEnable == 0}">
                <input id="opertionSubmit" class="btn btn-primary" onclick="updateStatus(${startupPage.id},1)" value="启 用" style="width: 50px;"/>&nbsp;
            </c:if>
            <c:if test="${startupPage.isEnable == 1}">
                <input id="opertionSubmit" class="btn btn-primary" onclick="updateStatus(${startupPage.id},0)" value="禁 用" style="width: 50px;"/>&nbsp;
            </c:if>
        </div>
    </form:form>
</div>
<script>
    $(function(){
        $(".upload_button").each(function() {
            var self = $(this);
            var s = self.attr('sort');
            var filename = self.attr('filename');
            $("#uploadImgIcon"+s).upload({
                action:"${ctxA}/common/uploadIcon?ImgFileSize=1024", //表单提交的地址
                name:"imageFile",
                onComplete:function (data) { //提交表单之后
                    if(data!=""){
                        var obj = JSON.parse(JSON.parse(data));
                        if(obj.code == 1){
                            $("#startupPageForm #"+filename).val(obj.url);
                            $("#uploadImgIcon" + s + " img").attr("src",obj.url);
                        }else{
                            top.layer.alert(obj.msg, {icon: 5});
                        }
                    }
                }

            });
        })
    })
    function flush() {
        window.location.href="${ctxA}/market/startupPage/form?appId="+$("#appId").val();
    }
    function save(){
        if($("#redirectUrl").val() == null || $("#redirectUrl").val() == '' || $("#redirectUrl").val().length <= 0){
            top.layer.alert("请输入广告url", {icon: 5});
            return false
        }
        if($("#imgUrl").val() == null || $("#imgUrl").val() == '' || $("#imgUrl").val().length <= 0){
            top.layer.alert("请上传广告图片", {icon: 5});
            return false
        }
        var form=$("#startupPageForm");
        var action = form[0].action;
        var data = form.serialize();
        $("#btnSubmit").attr("disabled",true);
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/market/startupPage/form?appId="+$("#appId").val();
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
    function updateStatus(id,isEnable){
        var msg = "确定禁用该启动页吗";
        if( isEnable == 1){
            msg = "确定启用该启动页吗";
        }
        var appId = $("#appId").val();
        top.$.jBox.confirm(msg,'系统提示',function(v,h,f){
            if(v=='ok'){
                jQuery.post("${ctxA}/market/startupPage/updateStatus", {'id':id,'isEnable':isEnable,'appId':appId},
                    function(data) {
                        if (data.code ==1) {
                            top.layer.alert("操作完成", {
                                icon: 6,
                                end: function(){
                                    window.location.href="${ctxA}/market/startupPage/form?appId="+appId;
                                }
                            });
                        } else {
                            top.layer.alert(data.msg, {icon: 5});
                        }
                        return;
                    }, "json");
            }
        })
    }
/*
    function isNumber(val){
        var regPos = /^\d+(\.\d+)?$/; //非负浮点数
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        if(regPos.test(val) || regNeg.test(val)){
            return true;
        }else{
            return false;
        }
    }*/
</script>