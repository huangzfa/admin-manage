<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload,common"/>
    <!--  -->
    <style type="text/css">
        .upload_button{list-style:none}

    </style>

</head>
<body>

<div class="si-warp">
    <br/>
    <form:form id="offlineForm" modelAttribute="offline"  action="${ctxA}/order/repayment/offline/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty offline.id?offline.id:''}">
        <div class="control-group">
            <label class="control-label">是否平账：</label>
            <div class="controls">
                <label class="radio-inline">
                    <input type="radio"  value="1"  style="width: 30px"  name="offline" checked disabled><span>平账</span>
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="0"  style="width: 30px" name="offline"  disabled><span>不平账</span>
                </label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">平账方式：</label>
            <div class="controls">
                <select  name="balanceType" id="balanceType" class="selectpicker show-tick form-control valid"  style="width: 15%;">
                    <option value="1">常规平账</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">还款方式：</label>
            <div class="controls">
                <label class="radio-inline">
                    <input type="radio"  value="2"  style="width: 30px"  name="repayType"  ${(empty offline.accountType || offline.accountType=='zfb')?"checked":''} ><span>支付宝</span>
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="1"  style="width: 30px" name="repayType"  ${not empty offline && offline.accountType=='bank'?"checked":''}><span>银行卡</span>
                </label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">还款卡号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写还款卡号"  type="text" name="repayCardNo" id="repayCardNo" maxlength="20" value="${offline.accountNo}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">用户账号：</label>
            <div class="controls">
                <input type="text" class="form-control"  disabled type="text" style="background-color:#eee" name="phone" id="phone" maxlength="100" value="${phone}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">用户姓名：</label>
            <div class="controls">
                <input type="text" class="form-control" disabled  type="text" style="background-color:#eee" name="submitterName" id="submitterName" maxlength="100" value="${offline.submitterName}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">待还金额：</label>
            <div class="controls">
                <input type="text" class="form-control" disabled  style="background-color:#eee" type="text" name="amount" id="amount" maxlength="100" value="${offline.amount}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">实还金额：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="actualAmount" id="actualAmount" maxlength="10" value="${offline.repayAmount}" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">减免金额：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="deductAmount" id="deductAmount" maxlength="100" value="${offline.derateAmount}" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">还款账号：</label>
            <div class="controls">
                <select  name="zfbAccountId" id="zfbAccountId" class="selectpicker show-tick form-control valid"  style="width: 15%;">
                    <c:forEach items="${zfbList}" var="zfb">
                        <option value="${zfb.id}"  ${( zfb.id==offline.zfbAccountId)?"selected":''}>${zfb.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">还款流水号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写还款流水号"  type="text" name="repayNo" id="repayNo"  maxlength="100" value="${offline.tradeNo}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">备注信息：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text" name="remark" id="remark" maxlength="100" value="${offline.remark}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">还款凭证：</label>
            <div class="controls">
                <input type="hidden" value="${offline.imgUrl}" name="repayImgUrl" id="repayImgUrl"  class="valid" descripe="请上传图片">
                <div class="thumbImgBox">
                    <ul style="float: left">
                        <li class="upload_button" id="uploadImgIcon1" filename="repayImgUrl" sort="1" style="width: 300px;height: 400px;">
                            <a target="_blank" ><img src="${not empty offline.imgUrl?offline.imgUrl:'/static/img/upload.png'}" class="img-thumbnail"  width="300px" height="400px"></a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="repayment:offline:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" ${offline.state==1?'disabled':''} value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/order/repayment/offline/list'"/>
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
                action:"${ctxA}/common/uploadIcon?ImgFileSize=8000", //表单提交的地址
                name:"imageFile",
                onComplete:function (data) { //提交表单之后
                    if(data!=""){
                        var obj = JSON.parse(JSON.parse(data));
                        if(obj.code == 1){
                            $("#offlineForm #"+filename).val(obj.url);
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
        if( $("#deductAmount").val()!="" && !checkFloat($("#deductAmount").val(),2)){
            top.layer.alert("请填写正确减免金额", {icon: 5});
            return false;
        }
        if( $("#actualAmount").val()=="" || !checkFloat($("#deductAmount").val(),2)){
            top.layer.alert("请填写正确实还金额", {icon: 5});
            return false;
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#offlineForm");
        var action = form[0].action;
        var data = form.serialize();
        top.layer.load();
        jQuery.post(action,data, function(data) {
            top.layer.closeAll('loading');//关闭loading
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/order/repayment/offline/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>