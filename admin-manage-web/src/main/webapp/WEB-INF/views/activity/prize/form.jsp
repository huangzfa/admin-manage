<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,select2,ocupload"/>
    <!--  -->
    <style type="text/css">
        .upload_button{list-style:none}
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/activity/prize/list">奖品列表</a></li>
    <li class="active">
        <shiro:hasPermission name="activity:list:edit">
            <a href="javascript:void(0);">${not empty prize.id?'修改':'添加'}奖品</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="prizeForm" modelAttribute="prize"   action="${ctxA}/activity/prize/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty prize.id?prize.id:''}">
        <input type="hidden" name="imgUrl" value="${prize.imgUrl}">
        <div class="control-group">
            <label class="control-label">奖品类型：</label>
            <div class="controls">
                <label class="radio-inline">
                    <input type="radio"  value="bzj" name="prizeType" ${not empty prize && prize.prizeType=='bzj'?"checked":''} ><span>谢谢参与</span>
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="zyjp" name="prizeType" ${prize.prizeType=='zyjp'?"checked":''}><span>自由品类</span>
                </label>
                <label class="radio-inline">
                    <input type="radio" value="jk" name="prizeType" ${prize.prizeType=='jk'?"checked":''}><span>借款券</span>
                </label>
                <label class="radio-inline">
                    <input type="radio" value="hk" name="prizeType" ${prize.prizeType=='hk'?"checked":''}><span>还款券</span>
                </label>
            </div>
        </div>
        <div class="form-group jp_name" id="jp_name" style="display: ${prize.prizeType=='jk' || prize.prizeType=='hk'?none:block}">
            <label class="control-label">奖品名称:</label>
            <div class="controls">
                <input type="text" name="prizeName" id="prizeName" class="form-control valid" descripe="请填写奖品名称"  placeholder="" value="$!{prize.prizeName}" maxlength="10">
            </div>
        </div>
        <div class="form-group xz_prize" style="display: ${prize.prizeType=='jk' || prize.prizeType=='hk'?block:none}">
            <label class="control-label">选择奖品:</label>
            <div class="controls">
                <select id="couponId" name="couponId" class="selectpicker show-tick form-control" >

                </select>
                <small class="xz_prize" style="display: #if($!{prize.prizeType}=='other' || $!{prize.prizeType}=='bzj' || !${prize}) none #else block #end">* 仅支持添加<运营优惠券管理-优惠券管理>中已添加的优惠券</small>
            </div>
        </div>
        <div class="form-group jp_name" style="display: ${prize.prizeType=='zypl'?none:block}">
            <label class="control-label">跳转链接:</label>
            <div class="controls">
                <input type="text" value="${prize.link}" name="link" id="link" class="form-control" id="activityUrl" placeholder="选填"  ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">上传图片：</label>
            <div class="controls">
                <table >
                    <tr>
                        <td>
                            <input name="file" data-value="${prize.imgUrl}" id="iconUrl" type="file"/>
                        </td>
                        <td >
                            <input id="submitButton2" type="button" value="上传图片" onclick="uploadFile()"/>
                        </td>
                    </tr>
                </table>
                <img src="${prize.imgUrl}" style="display: ${prize.imgUrl==''?none:block}" id="uploadIcon" height="50px" width="50px"/>
                <ul style="margin-left: 6%;">
                    <small class="help-block owner_ID">建议尺寸：140*140px</small>
                    <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                    <small class="help-block owner_ID">图片大小：100kb以内</small>
                </ul>
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
    /***********如果表单编辑 类型是优惠券************/
    if("${prize.prizeType}"=='jk' || "${prize.prizeType}"=='hk'){
        getCouponList("${prize.couponId}","${prize.prizeType}");
    }
    /**************  按钮点击事件，切换显示 ******************/
    $(f+ " input[type='radio']").bind("click",function(){
        var couponType = $(this).attr("couponType");
        if($(this).val()=="jk" || $(this).val()=="hk"){//优惠券不显示
            $(f+" .xz_prize").css("display","block");
            $(f+" .jp_name").css("display","none");//奖品名称不显示
            $(f+" #prizeName").attr("descripe","");//不需要必填
            $(f+" #link").val("");
            getCouponList(null,couponType);
        }else if($(this).val()=="bzj"){
            if("${prize}"!='' && "${prize.prizeType}"=='bzj'){//如果类型是不中奖
                $(f+" #prizeName").val("${prize.prizeName}");
            }else{
                $(f+" #prizeName").val("");
            }
            $(f+" #prizeName").attr("descripe","请填写奖品名称");//需要必填
            $(f+" .xz_prize").css("display","none");//选择奖品不显示
            $(f+" .jp_name").css("display","none");//奖品名称不显示
            $(f+" #jp_name").css("display","block");
        }else{
            if("${prize}"!='' && "${prize.prizeType}"=='zyjp'){
                $(f+" #prizeName").val("${prize.prizeName}");
                $(f+" #link").val("${prize.link}");
            }else{
                $(f+" #prizeName").val("");
                $(f+" #link").val("");
                $(f+" #link").val("${prize.link}");
            }
            $(f+" #prizeName").attr("descripe","请填写奖品名称");//需要必填
            $(f+" .xz_prize").css("display","none");//选择奖品不显示
            $(f+" .jp_name").css("display","block");//奖品名称不显示
        }
        if( $(this).val() == "${prize.prizeType}"){
            $(f+" .upload_button img").attr("src","${prize.imgUrl}");
        }
    })


    //上传文件
    function uploadFile(){
        if($('#iconUrl').val()==''){
            top.layer.alert("请上传文件", {icon: 5});
            return false;
        }
        jQuery.ajaxFileUpload({
            url:"${ctxA}/common/uploadIcon?ImgFileSize=100", //需要链接到服务器地址
            secureuri:false,
            fileElementId:"iconUrl", //文件选择框的id属性
            dataType: 'json',  //服务器返回的格式类型
            success: function (data, status){
                    if(data.code == 1){
                        $('#iconUrl').attr('data-value',data.url);
                        $('#imgUrl').val(data.url);
                        $('#uploadIcon').css('display', 'block');
                        $('#uploadIcon').attr('src', data.url);
                    }else{
                        top.layer.alert(data.msg, {icon: 5});
                    }
            },
            error: function (data, status, e){
                top.layer.alert(data.msg, {icon: 5});
            }
        })
    }

    /******* 查询优惠券，并赋值，couponType优惠券类型*******/
    function getCouponList(value,couponType){
        $(f+" #couponId").empty();
        jQuery.post("/activity/prize/getCouponList", {'couponType':couponType},function(result) {
            if( result.data ){
                for( i in result.data){
                    $(f+" #couponId").append("<option value='"+result.data[i].rid+"' name="+result.data[i].name+">"+result.data[i].name+"</option>");
                }
                $(f+" #couponId").val(value);
            }

        },"json");
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
        $("#btnSubmit").attr("disabled",true);
        var form=$("#merchantForm");
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