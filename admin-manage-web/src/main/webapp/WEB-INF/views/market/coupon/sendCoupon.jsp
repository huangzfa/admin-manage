<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ajaxfileupload"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a >优惠券发送</a></li>
</ul>
<div class="si-warp">
    <br/>
    <form:form    method="post" class="form-horizontal">
        <div class="control-group">
            <label class="control-label">用户来源：</label>
            <div class="controls">
                <select  name="userSource" id="userSource" class="selectpicker show-tick form-control" onchange="sourceChange(this)">
                    <option value="">请选择用户来源</option>
                    <option value="phone">手机号输入</option>
                    <option value="import">批量导入</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">应用产品：</label>
            <div class="controls">
                <select  id="productId" class="selectpicker show-tick form-control" onchange="productChange()">
                    <c:forEach items="${productList}" var="product">
                        <option value="${product.id}" >${product.productName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">优惠券选择：</label>
            <div class="controls">
                <select id="couponId"  class="selectpicker show-tick form-control" >
                    <c:forEach items="${couponList}" var="coupon">
                        <option value="${product.id}" >${product.productName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="control-group phone" style="display:none;" >
            <label class="control-label">手机号：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text"id="phone" maxlength="11" value="${coupon.amount}" ></input>
            </div>
        </div>
        <div class="control-group import" style="display:none;" >
            <label class="control-label">批量导入：</label>
            <div class="controls">
                <div class="uploadExcle">
                    <input type="file"  id="importexcel"    name="importexcel" />
                </div>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="market:sendCoupon:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="提 交" />
            </shiro:hasPermission>
        </div>
    </form:form>


</div>
</body>
<script>

    $(function () {
        productChange();
    })
    function sourceChange(obj) {
        if( $(obj).val()=='phone' ){
            $(".phone").css("display","block");
            $(".import").css("display","none");
        }else if( $(obj).val()=='import' ){
            $(".import").css("display","block");
            $(".phone").css("display","none");
        }else{
            $(".import").css("display","none");
            $(".phone").css("display","none");
        }
    }

    function productChange() {
        $("#couponId").children().remove();
        jQuery.post("${ctxA}/market/coupon/getValidCoupon",{'productId':$("#productId").val()}, function(data) {
            for(var i = 0 ;i< data.list.length;i++){
                $("#couponId").append("<option value='"+data.list[i].id+"'>"+data.list[i].couponName+"</option>");
            }

        }, "json");
    }



    function save(){
        if( $("#userSource").val() == ''){
            top.layer.alert("请选择用户来源", {icon: 5});
            return false;
        }
        if($("#userSource").val()=='phone' ){
            var tx = "phone="+$("#phone").val()+"&couponId="+$("#couponId").val()
            jQuery.post("${ctxA}/market/coupon/senCoupon",tx, function(data) {
                if (data.code ==1) {
                    top.layer.alert("发送成功", {icon: 6});
                    $("#phone").val("");
                } else {
                    top.layer.alert(data.msg, {icon: 5});
                }
            }, "json");
        }else{
            uploadExcel();
        }
    };

    function uploadExcel(){
        top.layer.load();
        $("#btnSubmit").attr("disabled",true);
        $.ajaxFileUpload({
            url: "${ctxA}/market/coupon/import?&couponId="+$("#couponId").val(),
            secureuri: false,
            fileElementId: "importexcel",
            dataType: "json",
            success: function(f) {
                top.layer.closeAll('loading');//关闭loading
                $("#btnSubmit").attr("disabled",false);
                var data = eval("("+f+")");
                if(data.code == 1){
                    if( data.map.failCount == 0){
                        var msg = "操作完成！优惠券剩余 "+data.map.couponCount+" 张,本次优惠券发送成功 "+data.map.successCount+"条";
                        top.layer.alert(msg, {
                            icon: 6,
                            end: function(){
                            }
                        });
                    }else{
                        var msg ="操作完成！优惠券剩余 "+data.map.couponCount+" 张,本次优惠券发送成功 "+data.map.successCount+"条,发送失败"+data.map.failCount+"条";
                        var index = top.layer.confirm(msg, {
                            btn: ['确定','导出失败数据'] //按钮
                        }, function(){
                            top.layer.close(index)
                        }, function(){
                            window.location.href="${ctxA}/market/coupon/export";
                        });
                    }

                }else{
                    top.layer.alert(data.msg,{icon: 5});
                }
            },
            error: function(g, f, h) {
                $("#btnSubmit").attr("disabled",false);
                top.layer.alert(h,{icon: 5});
            }
        });
    }
</script>
</html>