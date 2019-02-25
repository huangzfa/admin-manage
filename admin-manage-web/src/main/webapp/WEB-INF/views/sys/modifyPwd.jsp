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
    <form id="upPassForm" modelAttribute="credential" action="/updatePass" method="post" class="form-horizontal">
        <input id="opId" name="opId" type="hidden" value="${opId}"/>
        <input id="mobile" name="mobile" type="hidden" value="${phone}"/>
        <div class="control-group">
            <label class="control-label" style="width: 100%;text-align:left">账号有较大的安全隐患，请设置新的登录密码，手机号不正确请联系超级管理员，发送短信验证码至<span >${mobile}</span></label>
        </div>
        <div class="control-group">
            <label class="control-label">验证码：</label>
            <div class="input-group">
                <input type="text" maxlength="4" class="form-control" id="code" name="code" style="margin-left: 18px;width: 39%;">
                <span class="help-inline"><font color="red">*</font> </span>
                <span class="input-group-btn">
                   <button class="btn btn-default" type="button" id="sendSmsBtn2" onclick="modifyPwdModel.sendSmsBtn()">获取短信验证码</button>
                </span>
            </div><!-- /input-group -->
        </div>
        <div class="control-group">
            <label class="control-label">新密码：</label>
            <div class="controls">
                <input id="newPassword" name="newPassword" type="password" value=""/>
                <span class="help-inline"><font color="red">8-16位包含大小写英文字母、数字、特殊字符</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">确认新密码：</label>
            <div class="controls">
                <input id="confirmNewPassword" name="confirmNewPassword" type="password" value=""/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="button" onclick="modifyPwdModel.update()" value="保 存"/>
        </div>
    </form>
</div>
</body>
<script>
    var modifyPwdModel={};
    modifyPwdModel.update=function (){
        var code =$("#code").val();
        var newPassword = $("#newPassword").val();
        var agpassword = $("#confirmNewPassword").val();

        var regx = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*])[\da-zA-Z~!@#$%^&*]{8,}$/;
        if (code.trim() == ""){
            top.layer.alert("请输入验证码", {icon: 5});
            return;
        }
        if(code.length != 4){
            top.layer.alert("请输入正确验证码", {icon: 5});
            return;
        }
        if (newPassword.trim() == ''){
            top.layer.alert("请输入密码", {icon: 5});
            return
        }
        if (newPassword.length > 20 || newPassword < 6 ||newPassword.match(regx) == null){
            top.layer.alert("新密码格式不正确，请重新输入", {icon: 5});
            return
        }
        if (agpassword.trim() == ''){
            top.layer.alert("请再次输入密码", {icon: 5});
            return
        }
        if(newPassword != agpassword){
            top.layer.alert("两次输入密码不一致", {icon: 5});
            return
        }
        var opId=$("#opId").val();
        var mobile=$("#mobile").val();
        var url = encodeURI("/updatePass?code="+code+"&newPassword="+newPassword+"&opId="+opId+"&mobile="+mobile);
        $.ajax({
            dataType:"json",
            url:url,
            success: function(result) {
                if (result.code == 1) {
                    top.layer.alert("修改成功", {
                        icon: 6,
                        end: function () {
                            cancel();
                            parent.window.location.reload();
                        }
                    });
                } else {
                    top.layer.alert(result.msg, {icon: 5});
                }
            }
        });
    }

    var djs=60;
    modifyPwdModel.sendSmsBtn=function () {
        if($("#sendSmsBtn2").hasClass('btn-primary')){
            return;
        }
        if(modifyPwdModel.isMobile($("#mobile").val())){
            modifyPwdModel.openSendSms();
        }else{
            top.layer.alert('请输入正确的用户名或手机号', {icon: 5});
        }
    }
    var djsInterval;
    modifyPwdModel.openSendSms=function(){
        $("#sendSmsBtn2").addClass('btn-primary');
        $.ajax({
            dataType:"json",
            url:"/updatePass/send/sms",
            data: {'mobile':$("#mobile").val()},
            success: function(data) {
                clearInterval(djsInterval);
                if(data.code==1){
                    djsInterval = window.setInterval(modifyPwdModel.numTimer,1000);
                    top.layer.msg('短信发送成功');
                }else{
                    $("#sendSmsBtn2").removeClass('t');
                    top.layer.msg(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                $("#sendSmsBtn2").removeClass('btn-primary');
            }
        });
    }

    //定时更新btn文案函数
    modifyPwdModel.numTimer=function(){
        if(djs<=1){
            clearInterval(djsInterval);
            $("#sendSmsBtn2").html('获取验证码');
            djs=60;
            $("#sendSmsBtn2").removeClass('btn-primary');
        }else{
            djs--;
            $("#sendSmsBtn2").html(djs+'s');
        }
    }

    modifyPwdModel.isMobile=function(val){

        return /^1\d{10}$/.test(val);
    }

    function cancel(){
        var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
</script>
