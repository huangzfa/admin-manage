<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,97Date,common"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/market/coupon/list">优惠券列表</a></li>
    <li class="active">
        <shiro:hasPermission name="market:coupon:edit">
            <a href="javascript:void(0);">${not empty coupon.id?'修改':'添加'}优惠券</a>
        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="couponForm" modelAttribute="coupon"   action="${ctxA}/market/coupon/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${coupon.id}">
        <input type="hidden" name="productId" id="productId" value="${productId}">
        <div class="control-group">
            <label class="control-label">优惠券名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写优惠券名称" type="text" name="couponName" id="couponName" maxlength="10" value="${coupon.couponName}" style="width: 300px;"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">优惠券类型：</label>
            <div class="controls">
                <select  name="couponType" id="couponType" class="selectpicker show-tick form-control valid" descripe="请选择优惠券类型"  style="width: 315px;">
                    <option value="">请选择</option>
                    <c:forEach items="${couponType}" var="type">
                        <option value="${type.dicVal}" ${not empty coupon && coupon.couponType==type.dicVal?"selected":''}>${type.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">面值：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写面值" type="text" name="amount" id="amount" maxlength="20" value="${coupon.amount}" style="width: 300px;" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">发行量：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写发行量" type="text" name="quota" id="quota" maxlength="20" value="${coupon.quota}" style="width: 300px;" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="col-sm-1 control-label">使用条件:</label>
            <div class="controls">
                <label class="radio-inline">
                    <input type="radio"  value="0"  name="limitType"
                    <c:choose>
                        <c:when test="${coupon.limitAmount == null || coupon.limitAmount==0}">
                               checked
                        </c:when>
                    </c:choose>
                    ><span>不限制</span>
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="1" name="limitType" ${ coupon.limitAmount>0?'checked':''}><span>满</span>
                </label>
                <input type="text" class="form-control"  type="text" name="limitAmount"  id="limitAmount" value="${coupon.limitAmount==0?'':coupon.limitAmount}" style="width: 100px;" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>

            </div>
        </div>
        <div class="control-group">
            <label class="control-label">领取限制：</label>
            <div class="controls">
                <input type="text" class="form-control"  placeholder="每个人限制领取张数，不填不限制" type="text" name="personLimitCount" id="personLimitCount" maxlength="4" value="${coupon.personLimitCount==-1?'':coupon.personLimitCount}" style="width: 300px;" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'>次/人
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">有效时间：</label>
            <div class="controls">
                <label class="radio-inline">
                     <input type="radio" name="expiryType"  value="1"
                     <c:choose>
                         <c:when test="${coupon.expiryType == null || coupon.expiryType==1}">
                                checked
                         </c:when>
                     </c:choose>
                     ><span>按日期限制</span>
                </label>
                <input id="gmtStart" name="gmtStart" placeholder="开始时间" value="${gmtStart}" class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                -
                <input id="gmtEnd" name="gmtEnd"   placeholder="结束时间" value="${gmtEnd}" class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                <label class="radio-inline">
                    <input type="radio" name="expiryType"  value="0" ${ coupon.expiryType==0?'checked':''}><span>领取优惠券后</span>
                    <input type="text" class="form-control" maxlength="3" type="text" id="validDays" name="validDays"  value="${coupon.validDays==-1?'':coupon.validDays}" style="width: 100px;" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'>天
                </label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">使用说明：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" name="useExplain" id="useExplain" maxlength="20" value="${coupon.useExplain}" style="width: 300px;">
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="market:coupon:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/market/coupon/list'"/>
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
        var expiryType = $('input[name="expiryType"]:checked').val();
        if( expiryType == '1'){
            var gmtStart = $("#gmtStart").val();
            var gmtEnd = $("#gmtEnd").val();
            if( gmtEnd == '' || gmtStart==''){
                top.layer.alert("请填有效期限", {icon: 5});
                return false;
            }
        }else{
            var validDays = $("#validDays").val();
            if( validDays=='' && !isNumber(validDays)){
                top.layer.alert("请输入正确有效天数", {icon: 5});
                return false;
            }
            $("#gmtStart,#gmtEnd").val("");
        }
        if( $("#amount").val()!='' && !isNumber($("#amount").val())){
            top.layer.alert("请输入正确面值", {icon: 5});
            return false;
        }
        if( $("#quota").val()!='' && !isNumber($("#quota").val())){
            top.layer.alert("请输入正确发行量", {icon: 5});
            return false;
        }
        if( $("#personLimitCount").val()!='' && !isNumber($("#personLimitCount").val())){
            top.layer.alert("请输入有效领取限制次数", {icon: 5});
            return false;
        }
        var limitType = $('input[name="limitType"]:checked').val();
        if( limitType == '1'){
            var limitAmount = $("#limitAmount").val();
            if( limitAmount=='' || !isNumber(limitAmount)){
                top.layer.alert("请输入有效金额", {icon: 5});
                return false;
            }
        }else{
            $("#limitAmount").val(0);
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#couponForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/market/coupon/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>