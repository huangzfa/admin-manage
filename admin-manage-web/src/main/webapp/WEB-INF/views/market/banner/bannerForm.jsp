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
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/market/banner/list?appId=${banner.appId}">轮播图列表</a></li>
    <li class="active">
        <shiro:hasPermission name="market:banner:edit">
            <a href="javascript:void(0);">${not empty banner.id?'修改':'添加'}轮播图</a>
        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="bannerForm" modelAttribute="banner"  action="${ctxA}/market/banner/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty banner.id?banner.id:''}">
        <input type="hidden" id = "appId" name="appId" value="${banner.appId}">
        <div class="control-group">
            <label class="control-label">轮播名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写轮播名称" type="text" name="bannerTitle" id="bannerTitle" maxlength="20" value="${banner.bannerTitle}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">轮播位置：</label>
            <div class="controls">
                <select  name="bannerType" id="bannerType" class="selectpicker show-tick form-control valid" descripe="请选择轮播位置" style="width: 15%;">
                    <c:forEach items="${bannerType}" var="type">
                        <option value="${type.dicVal}" ${not empty banner && banner.bannerType==type.dicVal?"selected":''}>${type.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">链接类型:</label>
            <div class="controls">
                <c:forEach items="${redirectType}" var="type">
                    <label class="radio-inline">
                        <input type="radio"  style="width: 30px" value="${type.dicVal}" name="redirectType"  ${(not empty banner.redirectType && banner.redirectType==type.dicVal)?"checked":''}><span>${type.dicCode}</span>
                    </label>
                </c:forEach>

            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否需要登录:</label>
            <div class="controls">
                    <label class="radio-inline">
                        <input type="radio"  value="0"  style="width: 30px"  name="isNeedLogin"  ${(empty banner.isNeedLogin || banner.isNeedLogin=='0')?"checked":''}><span>不需要</span>
                    </label>
                    <label class="radio-inline">
                        <input type="radio"  value="1"  style="width: 30px" name="isNeedLogin"  ${not empty banner && banner.isNeedLogin=='1'?"checked":''}><span>需要</span>
                    </label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">跳转链接：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" name="redirectUrl" id="redirectUrl" maxlength="255" value="${banner.redirectUrl}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">上传图片：</label>
            <div class="controls">
                <input type="hidden" value="${banner.imgUrl}" name="imgUrl" id="imgUrl"  class="valid" descripe="请上传图片">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon1" filename="imgUrl" sort="1" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty banner.imgUrl?banner.imgUrl:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
                        </li>
                    </ul>
                    <ul style="margin-left: 3%;float: left">
                        <small class="help-block owner_ID">点击图片即可重新上传</small>
                        <small class="help-block owner_ID">建议尺寸：702*300</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：200kb以内</small>
                    </ul>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">轮播描述：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="remark" id="remark" maxlength="100" value="${banner.remark}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">序号：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" placeholder="不填默认为0" name="sort" id="sort" maxlength="6" value="${banner.sort}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' ></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="market:banner:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/market/banner/list?appId=${banner.appId}'"/>
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
                action:"${ctxA}/common/uploadIcon?ImgFileSize=200", //表单提交的地址
                name:"imageFile",
                onComplete:function (data) { //提交表单之后
                    if(data!=""){
                        var obj = JSON.parse(JSON.parse(data));
                        if(obj.code == 1){
                            $("#bannerForm #"+filename).val(obj.url);
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
        if( $("#sort").val()!='' && !checkNumber($("#sort").val())){
            top.layer.alert("请输入正确数字", {icon: 5});
            return false;
        }
        var redirectType = $('input[name="redirectType"]:checked').val();
        var redirectUrl = $('#redirectUrl').val();
        if( typeof(redirectType) =='undefined' || redirectType=='' ){
            top.layer.alert("请选择链接类型", {icon: 5});
            return false
        }
        if( redirectType == 'url' && redirectUrl == ''){
            top.layer.alert("请填写链接地址", {icon: 5});
            return false;
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#bannerForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/market/banner/list?appId="+$("#appId").val();
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
    function checkNumber(input) {
        var re = /^[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
        if (!re.test(input)) {
            return false;
        }else{
            return true
        }
    }
</script>
</html>