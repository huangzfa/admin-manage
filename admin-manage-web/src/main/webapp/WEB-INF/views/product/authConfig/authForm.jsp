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
    <li><a href="${ctxA}/product/authConfig/list">认证项列表</a></li>
    <li class="active">
        <shiro:hasPermission name="product:authConfig:edit">
            <a href="javascript:void(0);">${not empty authConfig.id?'修改':'添加'}认证项</a>
        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="authConfigForm" modelAttribute="authConfig"   action="${ctxA}/product/authConfig/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty authConfig.id?authConfig.id:''}">
        <div class="control-group">
            <label class="control-label">跳转类型：</label>
            <div class="controls">
                <select  name="pageType" id="pageType" class="selectpicker show-tick form-control valid" descripe="请选择跳转类型" onchange="pageTypeChange(this)">
                    <c:forEach items="${pageTypes}" var="type">
                        <option value="${type.dicVal}" ${not empty authConfig && authConfig.pageType==type.dicVal?"selected":''}>${type.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <%--<div class="control-group manufacturer" style="display:<c:if test="${authConfig.pageType==2}">none</c:if>">
            <label class="control-label">厂商名称：</label>
            <div class="controls">
                <select class="selectpicker show-tick form-control valid" descripe="请选择跳转类型">
                    <option>请选择</option>
                </select>
            </div>
        </div>--%>
        <div class="control-group">
            <label class="control-label">认证类型：</label>
            <div class="controls">
                <select  name="authType" id="authType" class="selectpicker show-tick form-control valid" descripe="请选择认证类型" >
                    <option value="">请选择</option>
                    <c:forEach items="${authTypes}" var="type">
                        <option value="${type.dicVal}" ${authConfig.authType==type.dicVal?"selected":''}>${type.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <%--<div class="control-group">
            <label class="control-label">认证中跳转：</label>
            <div class="controls">
                <input type="text" class="form-control valid"  type="text"  maxlength="6" value="${authConfig.authName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">认证成功跳转：</label>
            <div class="controls">
                <input type="text" class="form-control valid" type="text"  maxlength="6" value="${authConfig.authName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">认证失败跳转：</label>
            <div class="controls">
                <input type="text" class="form-control valid"  type="text"  maxlength="6" value="${authConfig.authName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">特殊匹配值：</label>
            <div class="controls">
                <input type="text" class="form-control valid"  type="text"  maxlength="6" value="${authConfig.authName}"></input>
            </div>
            <label class="control-label">匹配方式：</label>
            <div class="controls">
                <select  name="pageType" id="pageType" class="selectpicker show-tick form-control valid" descripe="请选择跳转类型">
                    <option>请选择</option>
                </select>
            </div>
        </div>--%>
        <div class="control-group">
            <label class="control-label">认证项名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写认证项名称" type="text" name="authName" id="authName" maxlength="6" value="${authConfig.authName}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">认证有效期：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写认证有效期" type="text" name="validVal" id="validVal" maxlength="3" value="${authConfig.validVal}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>天
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
        <div class="control-group">
            <label class="control-label">认证中图标：</label>
            <div class="controls">
                <input type="hidden" value="${authConfig.ingImg}" name="ingImg" id="ingImg" class="valid" descripe="请上传认证中图标">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon3" filename="ingImg" sort="3" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty authConfig.ingImg?authConfig.ingImg:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
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
        <div class="control-group">
            <label class="control-label">认证失败图标：</label>
            <div class="controls">
                <input type="hidden" value="${authConfig.failImg}" name="failImg" id="failImg"  class="valid" descripe="请上传认证失败图标">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon4" filename="failImg" sort="4" style="width: 100px;height: 100px;">
                            <a target="_blank" ><img src="${not empty authConfig.failImg?authConfig.failImg:'/static/img/upload.png'}" class="img-thumbnail"  width="100px" height="100px"></a>
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
        <div class="control-group">
            <label class="control-label">序号：</label>
            <div class="controls">
                <input type="text" class="form-control" descripe="不填默认为1" type="text" name="authSort" maxlength="3" value="${authConfig.authSort}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <%--<div class="control-group">
            <label class="control-label">匹配类型：</label>
            <div class="controls">
                <select  name="matchType" id="matchType" class="selectpicker show-tick form-control"  >
                    <option value="">请选择匹配类型</option>
                    <c:forEach items="${matchTypes}" var="type">
                        <option value="${type.dicVal}" ${authConfig.matchType==type.dicVal?"selected":''}>${type.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">匹配字符串：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" id="matchStr" name="matchStr" maxlength="32" value="${authConfig.matchStr}" ></input>
            </div>
        </div>--%>
        <div class="form-actions">
            <shiro:hasPermission name="product:authConfig:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/product/authConfig/list'"/>
        </div>
    </form:form>


</div>
</body>
<script>
    /*function pageTypeChange(obj){
       if($(obj).val()==1){
           $(".manufacturer").css("display","block");
       }else{
           $(".manufacturer").css("display","none");
       }
    }*/
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
                        window.location.href="${ctxA}/product/authConfig/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
    function regChinese(input){
        if( input  == null || input ==''){
            return false;
        }
        var reg=/^[\u2E80-\u9FFF]+$/;//Unicode编码中的汉字范围
        if(reg.test(input)){
            return true;
        }else{
            return false;
        }
    }
</script>
</html>