<script type="text/javascript" src="../../js/ocupload/ocupload.js"></script>
<style type="text/css">
    .btn-group-sm>.btn, .btn-sm{
        padding: 7px 10px;
    }
    .img-thumbnail{
        max-width: 94%;
    }
</style>
<br>
<form class="form-horizontal" id="prize_form" action="/activity/h5/prize/saveOrUpdate">
    <input type="hidden" id="prizeId" name="prizeId" value="$!{prize.prizeId}">
    <input type="hidden" name="imgUrl" id="imgUrl" class="valid" descripe="请上传图片" value="$!{prize.imgUrl}">
    <div class="form-group">
        <label class="col-sm-1 control-label">设置奖品类型:</label>
        <div class="col-sm-5">
            <label class="radio-inline">
                <input type="radio"  value="bzj" name="prizeType" #if($!{prize.prizeType}=='bzj' || !$!{prize}) checked #end><span>谢谢参与</span>
            </label>
            <label class="radio-inline">
                <input type="radio"  value="other" name="prizeType" #if($!{prize.prizeType}=='other') checked #end><span>自由品类</span>
            </label>
            <label class="radio-inline">
                <input type="radio" couponType="6" value="jkq" name="prizeType" #if($!{prize.prizeType}=='jkq')checked #end><span>借款券</span>
            </label>
            <label class="radio-inline">
                <input type="radio" couponType="0" value="hkq" name="prizeType" #if($!{prize.prizeType}=='hkq')checked #end><span>还款券</span>
            </label>
        </div>
    </div>
    <div class="form-group jp_name" id="jp_name" style="display: #if($!{prize.prizeType}=='other' || !${prize}) block #else none #end">
        <label class="col-lg-1 control-label">奖品名称:</label>
        <div class="col-lg-5">
            <input type="text" name="prizeName" id="prizeName" class="form-control valid" descripe="请填写奖品名称"  placeholder="" value="$!{prize.prizeName}" maxlength="10">
        </div>
    </div>
    <div class="form-group xz_prize" style="display: #if($!{prize.prizeType}=='other' || $!{prize.prizeType}=='bzj' || !${prize}) none #else block #end">
        <label class="col-lg-1 control-label">选择奖品:</label>
        <div class="col-lg-5">
            <select id="couponId" name="couponId" class="selectpicker show-tick form-control" >

            </select>
            <small class="xz_prize" style="display: #if($!{prize.prizeType}=='other' || $!{prize.prizeType}=='bzj' || !${prize}) none #else block #end">* 仅支持添加<运营优惠券管理-优惠券管理>中已添加的优惠券</small>
        </div>
    </div>

    #*<div class="form-group jp_name" style="display: #if($!{prize.prizeType}=='other' || !${prize}) block #else none #end">
    <label class="col-lg-1 control-label">奖品金额:</label>
    <div class="col-lg-5">
        <div class="input-group">
            <input type="text" value="$!{prize.money}" id="money" name="money" class="form-control valid" descripe="请填写奖品金额" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}">
            <span class="input-group-addon">.00</span>
        </div>
    </div>
</div>*#
    <div class="form-group jp_name" style="display: #if($!{prize.prizeType}=='other') block #else none #end">
        <label class="col-lg-1 control-label">跳转链接:</label>
        <div class="col-lg-5">
            <input type="text" value="$!{prize.link}" name="link" id="link" class="form-control" id="activityUrl" placeholder="选填"  ></input>
        </div>
    </div>

    <div class="form-group" style="margin: 0 0 20px;">
        <label class="col-sm-1">上传图片:</label>
        <div class="col-sm-10">
            #* <ul >
            <li class="upload_button"  id="uploadImgIcon1" sort="1" style="width: 100px;height: 100px;">
                <a target="_blank" ><img src="#if($!{prize}) $!{prize.imgUrl} #else /imgs/upload.png #end" class="img-thumbnail" width="100px" height="100px"></a>
            </li>
        </ul>*#
            <table >
                <tr>
                    <td>
                        <input name="file" data-value="$!{prize.imgUrl}" id="iconUrl" type="file"/>
                    </td>
                    <td >
                        <input id="submitButton2" type="button" value="上传图片" onclick="uploadFile()"/>
                    </td>
                </tr>
            </table>
            <img src="$!{prize.imgUrl}"
                 #if(!${prize.imgUrl})
                 style="display:none"
                 #end
                 id="uploadIcon" height="50px" width="50px"/>
            <ul style="margin-left: 6%;">
                <small class="help-block owner_ID">建议尺寸：140*140px</small>
                <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                <small class="help-block owner_ID">图片大小：100kb以内</small>
            </ul>
        </div>
    </div>
    <div class="form-group" >
        <label class="col-lg-1 control-label">是否有效:</label>
        <div class="col-lg-5">
            <select id="isDelete" name="isDelete" class="selectpicker show-tick form-control" >
                <option value="0" #if($!{prize.isDelete}==0) selected #end>有效</option>
                <option value="1" #if($!{prize.isDelete}==1) selected #end>无效</option>
            </select>
        </div>
    </div>
    <div class="form-group" style="margin: 0 0 20px;">
        <div class="col-sm-4" style="margin-top:20px;">
            <a  class="btn btn-success btn-sm" onclick="save(this)">
                <span class="glyphicon"> 保存</span>
            </a>
            <a href="/activity/h5/listPrize.vm" class="btn btn-info btn-sm">
                <span class="glyphicon">返回</span>
            </a>
        </div>
    </div>

</form>
<script>
    var  intiBtnCount = 1;
    var f = "#prize_form";
</script>
<script>

    /***********如果表单编辑 类型是优惠券************/
    if("$!{prize}"!='' && "$!{prize.prizeType}"!='other'){
        var couponType = 6;
        if("$!{prize.prizeType}"=="hkq"){
            couponType = 0;
        }
        getCouponList("$!{prize.couponId}",couponType);
    }
    /**************  按钮点击事件，切换显示 ******************/
    $(f+ " input[type='radio']").bind("click",function(){
        var couponType = $(this).attr("couponType");
        if($(this).val()=="jkq" || $(this).val()=="hkq"){//优惠券不显示
            $(f+" .xz_prize").css("display","block");
            $(f+" .jp_name").css("display","none");//奖品名称不显示
            $(f+" #prizeName").attr("descripe","");//不需要必填
            $(f+" #link").val("");
            getCouponList(null,couponType);
        }else if($(this).val()=="bzj"){
            if("$!{prize}"!='' && "$!{prize.prizeType}"=='bzj'){//如果类型是不中奖
                $(f+" #prizeName").val("$!{prize.prizeName}");
            }else{
                $(f+" #prizeName").val("");
            }
            $(f+" #prizeName").attr("descripe","请填写奖品名称");//需要必填
            $(f+" .xz_prize").css("display","none");//选择奖品不显示
            $(f+" .jp_name").css("display","none");//奖品名称不显示
            $(f+" #jp_name").css("display","block");
        }else{
            if("$!{prize}"!='' && "$!{prize.prizeType}"=='other'){
                $(f+" #prizeName").val("$!{prize.prizeName}");
                $(f+" #link").val("$!{prize.link}");
            }else{
                $(f+" #prizeName").val("");
                $(f+" #link").val("");
                $(f+" #link").val("$!{prize.link}");
            }
            $(f+" #prizeName").attr("descripe","请填写奖品名称");//需要必填
            $(f+" .xz_prize").css("display","none");//选择奖品不显示
            $(f+" .jp_name").css("display","block");//奖品名称不显示
        }
        if( $(this).val() == "$!{prize.prizeType}"){
            $(f+" .upload_button img").attr("src","$!{prize.imgUrl}");
        }
    })
    /******* 查询优惠券，并赋值，couponType优惠券类型*******/
    function getCouponList(value,couponType){
        $(f+" #couponId").empty();
        jQuery.post("/activity/h5/getCouponList", {'couponType':couponType},function(result) {
            if( result.data ){
                for( i in result.data){
                    $(f+" #couponId").append("<option value='"+result.data[i].rid+"' name="+result.data[i].name+">"+result.data[i].name+"</option>");
                }
                $(f+" #couponId").val(value);
            }

        },"json");
    }
    /****************** 表单保存  ************************/
    function save(obj){
        var bool = true;
        /*******  验证表单必填项目   ****************/
        $(".valid").each(function() {
            var descripe  = $(this).attr("descripe");
            if( $(this).val()=="" && descripe!=""){
                bootbox.alert(descripe);
                bool = false;
                return false;
            }
        })
        if( bool ){
            var form = $(f);
            $(obj).attr("disabled",true);
            var action = form[0].action;
            var data = form.serialize();
            jQuery.post(action, data,function(result) {
                $(obj).attr("disabled",false);
                if(result.code == 200){
                    bootbox.confirm({
                        title : 'success',
                        message: result.msg,
                        callback: function(result) {
                            window.location.href="/activity/h5/listPrize?currentPage=1";
                        }
                    })
                }else{
                    bootbox.alert(result.msg);
                }
            },"json");
        }
    }
    //上传文件
    function uploadFile(){
        var src = "uploadFile.htm";
        if($('#iconUrl').val()==''){
            bootbox.alert('请选择上传文件');
            return false;
        }
        jQuery.ajaxFileUpload({
            url:"/activity/h5/uploadIcon?ImgFileSize=100", //需要链接到服务器地址
            secureuri:false,
            fileElementId:"iconUrl", //文件选择框的id属性
            dataType: 'json',  //服务器返回的格式类型
            success: function (data, status){
                if(data.code == 200){
                    $('#iconUrl').attr('data-value',data.data.url);
                    $('#imgUrl').val(data.data.url);
                    $('#uploadIcon').css('display', 'block');
                    $('#uploadIcon').attr('src', data.data.url);
                    bootbox.alert(data.msg);
                }else{
                    bootbox.alert(data.msg);
                }
            },
            error: function (data, status, e){
                bootbox.alert("error" + data.msg);
            }
        })
    }
</script>
