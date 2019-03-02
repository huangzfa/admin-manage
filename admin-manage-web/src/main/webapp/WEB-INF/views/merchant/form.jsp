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
    <li><a href="${ctxA}/merchant/list">商户列表</a></li>
    <li class="active">
        <shiro:hasPermission name="merchant:list:edit">
            <a href="javascript:void(0);">${not empty authConfig.id?'修改':'添加'}商户</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="merchantForm" modelAttribute="merchant"   action="${ctxA}/merchant/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty merchant.id?merchant.id:''}">
        <div class="control-group">
            <label class="control-label">商户名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写商户名称" type="text" name="merchantName" id="merchantName" maxlength="32" value="${authConfig.authName}"></input>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">未认证图标：</label>
            <div class="controls">
                <input type="hidden" value="${authConfig.unImg}" name="unImg" id="unImg" class="valid" descripe="请上传未认证图标">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon1" filename="unImg" sort="1" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty authConfig.unImg?authConfig.unImg:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
                        </li>
                    </ul>
                    <ul style="margin-left: 3%;float:left">
                        <small class="help-block owner_ID">点击图片即可重新上传</small>
                        <small class="help-block owner_ID">建议尺寸：60*60</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">认证成功图标：</label>
            <div class="controls">
                <input type="hidden" value="${authConfig.successImg}" name="successImg" id="successImg" class="valid" descripe="请上传认证成功图标">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon2" filename="successImg" sort="2" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty authConfig.successImg?authConfig.successImg:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
                        </li>
                    </ul>
                    <ul style="margin-left: 3%;float: left">
                        <small class="help-block owner_ID">点击图片即可重新上传</small>
                        <small class="help-block owner_ID">建议尺寸：60*60</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
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

    $(function(){
        $(".upload_button").each(function() {
            var self = $(this);
            var s = self.attr('sort');
            var filename = self.attr('filename');
            $("#uploadImgIcon"+s).upload({
                action:"${ctxA}/common/uploadIcon?ImgFileSize=100", //表单提交的地址
                name:"imageFile",
                onComplete:function (data) { //提交表单之后
                    if(data!=""){
                        var obj = JSON.parse(JSON.parse(data));
                        if(obj.code == 1){
                            $("#authConfigForm #"+filename).val(obj.url);
                            $("#uploadImgIcon" + s + " img").attr("src",obj.url);
                        }else{
                            top.layer.alert(obj.msg, {icon: 5});
                        }
                    }
                }

            });
        })
    })
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
        var authName = $("#authName").val();
        if( !regChinese(authName)){
            top.layer.alert("认证项名称请输入汉字", {icon: 5});
            return false;
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#authConfigForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/merchant/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>