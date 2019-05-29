<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>
    <!--  -->
    <style type="text/css">
        .upload_button{list-style:none}

    </style>

</head>
<style>
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/channel/style/list?id=${object.id}">样式列表</a></li>
    <li class="active">
        <shiro:hasPermission name="channel:style:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}样式</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="channel:style:edit">
            <a href="javascript:void(0);">查看样式列表</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="channelStyle" action="${ctxA}/channel/style/save" method="post" class="form-horizontal">
        <div class="control-group">
            <label class="control-label">样式id：</label>
            <div class="controls">
                <input type="text" disabled class="form-control" type="text" value="${channelStyle.id}"/>
                <input type="hidden" name="id" id="id" value="${not empty channelStyle.id?channelStyle.id:''}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">样式名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请输入样式名称" placeholder="最多输入12个字"  type="text" name="styleName" id="styleName" maxlength="12" value="${channelStyle.styleName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">注册页按钮模板：</label>
            <div class="controls">
                <select  name="buttonTemplate" id="buttonTemplate" class="selectpicker show-tick form-control" style="width: 15%;">
                <c:forEach items="${buttonTempTypeList}" var="buttonTemplate">
                    <option value="${buttonTemplate.dicVal}" ${not empty channelStyle && channelStyle.buttonTemplate==buttonTemplate.dicVal?"selected":''}>${buttonTemplate.dicCode}</option>
                </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">注册页按钮文案：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请输入文案" placeholder="最多输入10个字" type="text" name="buttonText" id="buttonText" maxlength="32" value="${channelStyle.buttonText}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">注册页按钮颜色：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请输入色号" placeholder="请输入色号" type="text" name="buttonBackground" id="buttonBackground" value="${channelStyle.buttonBackground}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">注册页背景图：</label>
            <div class="controls">
                <input type="hidden" value="${channelStyle.imageUrl}" name="imageUrl" id="imageUrl"  class="valid" descripe="请上传图片">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon1" filename="imageUrl" sort="1" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty channelStyle.imageUrl?channelStyle.imageUrl:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
                        </li>
                    </ul>
                    <ul style="margin-left: 3%;float: left">
                        <small class="help-block owner_ID">点击图片即可重新上传</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：5M以内</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">下载页链接：</label>
            <div class="controls">
                <select  name="downloadPageType" id="downloadPageType" class="selectpicker show-tick form-control valid" style="width: 15%;" ONCLICK="updateDownLoadPageConfig()">
                    <c:forEach items="${downloadPageTypeList}" var="downloadPageType">
                        <option value="${downloadPageType.dicVal}" ${not empty downloadPageType && channelStyle.downloadPageType==downloadPageType.dicVal?"selected":''}>${downloadPageType.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="control-group" id="downloadPageUrlDiv" >
            <label class="control-label">自定义连接：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="请输入链接" type="text" name="downloadPageUrl" id="downloadPageUrl" value="${channelStyle.downloadPageUrl}"></input>
            </div>
        </div>

        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="取 消" onclick="window.location.href='${ctxA}/channel/style/list?id=${object.id}'"/>
            <shiro:hasPermission name="channel:promotion:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="提 交" style="width: 3%"/>&nbsp;
            </shiro:hasPermission>
        </div>
    </form:form>


</div>
</body>
<script type="text/javascript">
    $(function(){
        updateDownLoadPageConfig();

        $(".upload_button").each(function() {
            var self = $(this);
            var s = self.attr('sort');
            var filename = self.attr('filename');
            $("#uploadImgIcon"+s).upload({
                action:"${ctxA}/common/uploadIcon?ImgFileSize=5120", //表单提交的地址
                name:"imageFile",
                onComplete:function (data) { //提交表单之后
                    if(data!=""){
                        var obj = JSON.parse(JSON.parse(data));
                        if(obj.code == 1){
                            $("#inputForm #"+filename).val(obj.url);
                            $("#uploadImgIcon" + s + " img").attr("src",obj.url);
                        }else{
                            top.layer.alert(obj.msg, {icon: 5});
                        }
                    }
                }

            });
        })
    })
    function updateDownLoadPageConfig() {
        var downloadPageConfig = $("#downloadPageType").val();
        if (downloadPageConfig == 1){
            $("#downloadPageUrl").val(null);
            $("#downloadPageUrlDiv").hide();
        }else if (downloadPageConfig == 2) {
            $("#downloadPageUrlDiv").show();
        }
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
        if($("#downloadPageType").val() == 2){
            if ($("downloadPageUrl").val()==""){
                top.layer.alert("请输入连接", {icon: 5});
                return false;
            }
        }
        if(!checkIsColor($("#buttonBackground").val())){
            top.layer.alert("请输入正确色号", {icon: 5});
            return false
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
                        window.location.href="${ctxA}/channel/style/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
    function checkIsColor(bgVal) {
        var type = "";
        var re = new RegExp(type);
        if (bgVal.match(re) == null) {
            type = "^[rR][gG][Bb][\(]([\\s]*(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?)[\\s]*,){2}[\\s]*(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)[\\s]*[\)]{1}$";
            re = new RegExp(type);
            if (bgVal.match(re) == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
</script>
</html>