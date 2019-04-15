<style type="text/css">
    .file_del{ left:70%;background: red; font-weight:bold;color: white;padding: 0 8px;border-radius: 14px;font-size:21px;cursor:pointer;}
</style>
<br>
<form class="form-horizontal" id="activity_form" action="/activity/h5/saveOrUpdate"">
<input type="hidden" name="actId" id="actId" value="$!{activity.actId}">
<input type="hidden" name="code" id="code" value="$!{activity.code}">
<input type="hidden" name="btnImg" id="btnImg" value="$!{static.btnImg}">
<div class="form-group">
    <label class="col-sm-2 control-label">设置模板:</label>
    <div class="col-sm-5">
        <select id="atCode" name="atCode" descripe="请填选择模板" class="selectpicker show-tick form-control valid" #if($!{activity}) disabled #end>
            <option value="">请选择模板</option>
            #foreach($type in $!{typeList})
            <option value="$!{type.atCode}" #if($!{activity.atCode}== $!{type.atCode}) selected #end>$!{type.atName}</option>
            #end
        </select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-2 control-label">活动名称:</label>
    <div class="col-sm-5">
        <input type="text" class="form-control valid" descripe="请填写活动名称" id="actName" name="actName" placeholder="活动名称,必填(限定12字)" value="$!{activity.actName}" maxlength="12">
    </div>
</div>
<div class="form-group exchange" style="display: #if($!{exchange}) block #else none #end">
    <label class="col-sm-2 control-label">可用时间:</label>
    <div class="col-sm-5">
        <div class="input-group">
            <input type="text" class="form-control" id="input_axis" maxlength="5" placeholder="例：14:00，最多可添加4个">
            <span class="input-group-btn">
                       <button class="btn btn-default" type="button" onclick="addAxis()">添加</button>
                </span>
        </div>
        <table style="margin-top: 10px;" id="table_axis">
            #foreach( ${axis} in ${timeAxis})
            <tr >
                <td width='30%' align='center'><input type='text' class='form-control' value='${axis}' maxlength="5"/></td>
                <td align='left'><span class='file_del' onclick="delAxis(this)" style='margin-left: 10px;'>-</span></td>
            </tr>
            #end
        </table>

    </div>
</div>
<div class="form-group dayInitTimes" style="display: #if($!{hongbao} || $!{dazhuanpan} || $!{static}) block #else none #end">
    <label class="col-sm-2 control-label">初始次数:</label>
    <div class="col-sm-5">
        <input type="text" maxlength="2" class="form-control valid" #if($!{static}) descripe="" #else descripe="请填写初始次数" #end id="dayInitTimes" name="dayInitTimes" placeholder="必填" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
               onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'1')}else{this.value=this.value.replace(/\D/g,'')}"
               #if($!{hongbao})
               value="$!{hongbao.dayInitTimes}"
               #elseif($!{dazhuanpan})
               value="$!{dazhuanpan.dayInitTimes}"
               #elseif($!{static})
               value="$!{static.dayInitTimes}"
               #else
               value="1"
               #end">
    </div>
    <div class="col-sm-1 static" style="display:#if($!{static}) block #else none #end">
        <label class="checkbox-inline">
            <input type="checkbox"  value="0" name="timesEnable" #if($!{static.timesEnable}==0)checked #end><span>不限制</span>
        </label>
    </div>
</div>

<div class="form-group">
    <label class="col-sm-2 control-label">活动规则:</label>
    <div class="col-sm-5">
        <textarea type="text" rows="7"  name="rule" id="rule" class="form-control valid"  descripe="请填写活动规则" id="activityUrl" placeholder=""  maxlength="300">$!{activity.rule}</textarea>
    </div>
    <div class="col-sm-1 static" style="display:#if($!{static}) block #else none #end">
        <label class="checkbox-inline">
            <input type="checkbox"  value="0" name="ruleEnable" #if($!{static.ruleEnable}==0)checked #end><span>不显示</span>
        </label>
    </div>
</div>
<div class="static" style="display:#if($!{static}) block #else none #end">
    <div class="form-group">
        <label class="col-sm-2 control-label">按钮模板:</label>
        <div class="col-sm-5">
            <select id="btnType" name="btnType"  class="selectpicker show-tick form-control">
                <option value="">请选择</option>
                <option value="square" #if($!{static.btnType}=='square') selected #end>方角模板</option>
                <option value="fillet" #if($!{static.btnType}=='fillet') selected #end>圆角模板</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">按钮文案:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control"  id="btnText" name="btnText"  value="$!{static.btnText}" maxlength="12">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">按钮背景:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control"  id="btnColour" name="btnColour"  value="$!{static.btnColour}" maxlength="12">
        </div>
    </div>
    <div class="form-group" style="margin: 0 0 20px;">
        <label class="col-sm-1">背景图:</label>
        <div class="col-sm-10">
            <table >
                <tr>
                    <td>
                        <input name="file" data-value="$!{static.btnImg}" id="iconUrl" type="file"/>
                    </td>
                    <td >
                        <input id="submitButton" type="button" value="上传图片" onclick="uploadFile()"/>
                    </td>
                </tr>
            </table>
            <img src="$!{static.btnImg}"
                 #if(!${static.btnImg})
                 style="display:none"
                 #end
                 id="uploadIcon" height="50px" width="50px"/>
            <ul style="margin-left: 6%;">
                <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                <small class="help-block owner_ID">图片大小：500kb以内</small>
            </ul>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">跳转配置:</label>
        <div class="col-sm-5">
            <select id="jumpType" name="jumpType" class="selectpicker show-tick form-control" >
                <option value="">请选择</option>
                <option value="app" #if($!{static.jumpType}=='app') selected #end>下载app</option>
                <option value="other" #if($!{static.jumpType}=='other') selected #end>其他链接</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">跳转地址:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control"  id="jumpLink" name="jumpLink"  value="$!{static.jumpLink}" maxlength="1000">
        </div>
    </div>
</div>
<div class="form-group timeEnable" style="margin: 0 0 20px;">
    <label class="col-sm-1 control-label">时间限制:</label>
    #*<label class="radio-inline">
    <input type="radio"  value="0" name="timeEnable" #if($!{activity.timeEnable}==0)checked #end><span>不限制</span>
</label>*#
    <label class="radio-inline">
        <input type="radio" value="1" name="timeEnable" #if($!{activity.timeEnable}==1 or !$!{activity} )checked #end><span>限制</span>
    </label>
    <div class="col-sm-2" >
        <input type="text" name="startTime"autocomplete="off"  id="startTime" class="form-control"  value="$date.formatWithDateTimeShort($!{activity.startTime})" />
    </div>
    <div style="width: 10px; padding: 0;" class="col-sm-1">
        <span>至</span>
    </div>
    <div class="col-sm-2" >
        <input type="text" name="endTime"autocomplete="off"  id="endTime"  class="form-control" id="endTime" value="$date.formatWithDateTimeShort($!{activity.endTime})" />
    </div>
</div>
<div class="form-group dazhuanpan" style="display:#if($!{dazhuanpan} || $!{hongbao}) block #else none #end ">
    <label class="col-sm-2 control-label">设置奖品及概率:</label>
    <div class="col-sm-5">
        <div class="input-group">
            <select id="prizeId" name="prizeId" class="selectpicker show-tick form-control" >
                #foreach( ${prize} in ${prize_list1})
                <option value="${prize.prizeId}">${prize.prizeName}</option>
                #end
                #foreach( ${prize} in ${prize_list2})
                <option value="${prize.prizeId}">${prize.prizeName}</option>
                #end
            </select>
            <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="addPrize()">添加</button>
            </span>
        </div>
        <table style="margin-top: 10px;">
            #foreach( ${prize_rel} in ${przie_rel_list})
            <tr  prizeId=${prize_rel.prizeId} actPrizeId=${prize_rel.actPrizeId}>
                <td width='20%' ><button class='btn btn-default'  type='button' disabled>${prize_rel.prizeName}</button></td>
                <td width='15%' align='right'><label class='control-label' >获奖概率(%):</label></td>
                <td width='30%' align='center'><input type='text' class='form-control' value='${prize_rel.chance}' onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="3"/></td>
                <td align='left'><span class='file_del' onclick="delPrize(this,${prize_rel.actPrizeId})" style='margin-left: 10px;'>-</span></td>
            </tr>
            #end
        </table>

    </div>
</div>
<div class="form-group hongbao" style="display: #if($!{hongbao}) block #else none #end">
    <label class="col-sm-2 control-label">设置额外奖品及概率:</label>
    <div class="col-sm-5">
        <div class="input-group">
            <select id="other_prizeId" name="other_prizeId" class="selectpicker show-tick form-control" >
                #foreach( ${prize} in ${prize_list1})
                <option value="${prize.prizeId}">${prize.prizeName}</option>
                #end
                #foreach( ${prize} in ${prize_list2})
                <option value="${prize.prizeId}">${prize.prizeName}</option>
                #end
            </select>
            <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="addHongbao()">添加</button>
            </span>
        </div>
        <table style="margin-top: 10px;">
            #foreach( ${prize_hongbao} in ${prize_hongbao_list})
            <tr  prizeId=${prize_hongbao.prizeId} actPrizeId=${prize_hongbao.actPrizeId}>
                <td width='20%' ><button class='btn btn-default'  type='button' disabled>${prize_hongbao.prizeName}</button></td>
                <td width='15%' align='right'><label class='control-label' >累计天数:</label></td>
                <td width='30%' align='center'><input type='text' class='form-control' value='${prize_hongbao.days}' onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="3"/></td>
                <td align='left'><span class='file_del' onclick="delOtherPrize(this,${prize_hongbao.actPrizeId})" style='margin-left: 10px;'>-</span></td>
            </tr>
            #end
        </table>

    </div>
</div>
<div class="form-group exchange" style="display: #if($!{exchange}) block #else none #end">
    <label class="col-sm-2 control-label">设置奖品及兑换数量:</label>
    <div class="col-sm-5">
        <div class="input-group">
            <select id="exchange_prizeId" name="exchange_prizeId" class="selectpicker show-tick form-control" >
                #foreach( ${prize} in ${prize_list1})
                <option value="${prize.prizeId}">${prize.prizeName}</option>
                #end
                #foreach( ${prize} in ${prize_list2})
                <option value="${prize.prizeId}">${prize.prizeName}</option>
                #end
            </select>
            <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="addExchange()">添加</button>
                    </span>
        </div>
        <table style="margin-top: 10px;" class="prize_exchange">
            #foreach( ${prize_exchange} in ${prize_exchange_list})
            <tr  prizeId=${prize_exchange.prizeId} actPrizeId=${prize_exchange.actPrizeId}>
                <td width='20%' ><button class='btn btn-default'  type='button' disabled>${prize_exchange.prizeName}</button></td>
                <td width='15%' align='right'><label class='control-label' >兑换数量:</label></td>
                <td width='30%' align='center'><input type='text' class='form-control' value='${prize_exchange.exchangeNum}' onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="6"/></td>
                <td align='left'><span class='file_del' onclick="delExchangePrize(this,${prize_exchange.actPrizeId})" style='margin-left: 10px;'>-</span></td>
            </tr>
            #end
        </table>

    </div>
</div>
#if( $!{activity})
<div class="form-group">
    <label class="col-lg-1 control-label">选择模板:</label>
    <div class="col-lg-5">
        <div class="input-group">
            <select id="style" name="style" class="selectpicker show-tick form-control" >
                <option value="">选择样式</option>
                #foreach( ${link} in ${links})
                <option value="${link.value}">${link.name}</option>
                #end
            </select>
            <span class="input-group-btn">
                     <button class="btn btn-default" type="button" onclick="addLink()">生成</button>
                </span>
        </div>
    </div>
</div>
<div class="form-group ">
    <label class="col-sm-2 control-label">发布链接:</label>
    <label class="col-sm-5 control-label releaseLink"></label>
</div>
#end
<div class="form-group" style="margin: 0 0 20px;">
    <div class="col-sm-4" style="margin-top:20px;">
        <a  class="btn btn-success btn-sm" onclick="save(this)">
            <span class="glyphicon"> 保存</span>
        </a>
        <a  onclick="returnBack()" class="btn btn-info btn-sm">
            <span class="glyphicon">返回</span>
        </a>
    </div>
</div>

</form>
<script>
    //获取连接
    function addLink(){
        var styleLink = $("#style option:selected").val();
        if( styleLink!="" ){
            var link = styleLink+"?code="+"$!{activity.code}";
            $(".releaseLink").text(link);
        }
    }

</script>
<script>
    var f = "#activity_form";

    var deleteId=[];
    var otherDeleteId=[];
    $(function(){
        //模板切换事件
        $(f+ " #atCode").bind("click",function(){
            if($(this).val()=="static"){
                $(f+" .timeEnable").css("display","none");
                $(f+" .dazhuanpan").css("display","none");
                $(f+" .exchange").css("display","none");
                $(f+" .hongbao").css("display","none");
                $(f+" .static").css("display","block");
                $(f+" .dayInitTimes").attr("descripe","");
                $(f+" #dayInitTimes").css("display","block");
            }else if($(this).val()=="exchange"){
                $(f+" .dazhuanpan").css("display","none");
                $(f+" .hongbao").css("display","none");
                $(f+" .static").css("display","none");
                $(f+" .dayInitTimes").css("display","none");
                $(f+" .exchange").css("display","block");
                $(f+" .timeEnable").css("display","block");
                $(f+" #dayInitTimes").attr("descripe","");
            }
            else if($(this).val()=="hongbao" || $(this).val()=="zhuanpan"){
                $(f+" .static").css("display","none");
                $(f+" .exchange").css("display","none");
                $(f+" .dayInitTimes").css("display","block");
                $(f+" #dayInitTimes").attr("descripe","请填写初始次数");
                $(f+" .timeEnable").css("display","block");
                $(f+" .dazhuanpan").css("display","block");
                if($(this).val()=="hongbao"){
                    $(f+" .hongbao").css("display","block");
                    $(f+" .rule").attr("descripe","");
                }else if($(this).val()=="zhuanpan"){
                    $(f+" .hongbao").css("display","none");
                    $(f+" .rule").attr("descripe","请填写活动规则");
                }
            }

        })
        //单选按钮点击时间
        /*$(f+ " input[type='radio']").bind("click",function(){
         if($(this).val()=="1"){
         $(f+ " .timeEnable").css("display","block");
         }else{
         $(f+ " .timeEnable").css("display","none");
         }
         })*/
        $('#startTime').datetimepicker({
            format: 'yyyy-mm-dd',
            minView:'month',
            todayBtn: true,//今日按钮
            autoclose:true //选择日期后自动关闭
        });
        $('#endTime').datetimepicker({
            format: 'yyyy-mm-dd',
            minView:'month',
            todayBtn: true,//今日按钮
            autoclose:true //选择日期后自动关闭
        });
    })
    /**
     * 活动表单保存或修改
     * @param obj
     */
    function save(obj){
        if( $(f +" #atCode").val() == "static"){
            var action = "/activity/h5/toEditStatic";
            var data={
                'code':$(f+" #code").val(),
                'actId':$(f +" #actId").val(),
                'atCode':$(f +" #atCode").val(),
                'actName':$(f +" #actName").val(),
                'rule':$(f +" #rule").val(),
                'dayInitTimes':$(f +" #dayInitTimes").val(),
                'btnType':$(f +" #btnType").val(),
                'btnText':$(f +" #btnText").val(),
                'btnImg':$(f +" #btnImg").val(),
                'btnColour':$(f +" #btnColour").val(),
                'jumpType':$(f +" #jumpType").val(),
                'jumpLink':$(f +" #jumpLink").val()
            }
            if($("input[name='timesEnable']").prop("checked")){
                data.timesEnable = 0;
            }else{
                data.timesEnable = 1;
            }
            if($("input[name='ruleEnable']").prop("checked")){
                data.ruleEnable = 0;
            }else{
                data.ruleEnable = 1;
            }
            sendPost(action,data,obj);
        }else if($(f +" #atCode").val() == "exchange"){
            var action = "/activity/h5/toEditExchange";
            var index = 0;
            $(f+" #table_axis tr").each(function (){
                if($(this).css("display")!='none'){
                    index++;
                }
            });
            //最多只能添加8个
            if( index == 0){
                bootbox.alert("可用时间最少添加一个");
                return false;
            }
            index = 0;
            $(f+" .prize_exchange  tr").each(function (){
                if($(this).css("display")!='none'){
                    index++;
                }
            });
            //最多只能添加8个
            if( index ==0 ){
                bootbox.alert("设置奖品最少添加一个");
                return false;
            }
            var bool_exchange = false;
            var actPrizeId=[],prizeId=[],exchangeNum=[];//奖品关联id,奖品id,兑换数量
            $(f+" .prize_exchange  tr").each(function () {
                if($(this).css("display")!='none'){
                    actPrizeId.push($(this).attr("actPrizeId"));
                    prizeId.push($(this).attr("prizeId"));
                    if( $(this).find("input").val() == ""){
                        bool_exchange = true;
                        return ;
                    }
                    exchangeNum.push($(this).find("input").val());
                }
            });
            if( bool_exchange ){
                bootbox.alert("请输入兑换数量");
                return false;
            }
            var timeAxis=[];//活动时间段
            $(f+" #table_axis  tr").each(function () {
                if($(this).css("display")!='none'){
                    var value= $(this).find("input").val();
                    if( value == "" || value.indexOf(":")==-1){
                        bool_exchange = true;
                        return;
                    }
                    timeAxis.push(value);
                }
            });
            if( bool_exchange){
                bootbox.alert("请输入正确时间格式");
                return false;
            }
            var data = {
                'code':$(f+" #code").val(),
                'actId':$(f +" #actId").val(),
                'atCode':$(f +" #atCode").val(),
                'actName':$(f +" #actName").val(),
                'timeEnable':$(f+" input[name='timeEnable']:checked").val(),
                'activityStartTime':$(f +" #startTime").val(),
                'activityEndTime':$(f +" #endTime").val(),
                'rule':$(f +" #rule").val(),
                'actPrizeIds':actPrizeId.join(","),
                'prizeIds':prizeId.join(","),
                'exchangeNums':exchangeNum.join(","),
                'deleteId':deleteId.join(","),
                'timeAxiss':timeAxis.join(",")
            }
            sendPost(action,data,obj);
        }
        else{
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
            if( !bool ){
                return false;
            }
            if($(f+" input[name='timeEnable']:checked").val()=="1" && ($("#startTime").val()=="" || $("#endTime").val()=="")){
                bootbox.alert("请填写相应时间");
                return false;
            }
            var prizeId = [], chance = [],actPrizeId=[];
            var other_prizeId=[],day = [],actHongbaoId=[];
            var percent = 0;//定义概率总和
            var is_empty = true;//概率不能填写为空；
            $(f+" .dazhuanpan table tr").each(function (){
                if($(this).css("display")!='none'){
                    actPrizeId.push($(this).attr("actPrizeId"));
                    prizeId.push($(this).attr("prizeId"));
                    if($(this).find("input").val()==""){
                        is_empty = false;
                        return false;
                    }
                    chance.push($(this).find("input").val());
                    percent += parseInt($(this).find("input").val());
                }
            });
            $(f+" .hongbao table tr").each(function () {
                if($(this).css("display")!='none'){
                    actHongbaoId.push($(this).attr("actPrizeId"));
                    other_prizeId.push($(this).attr("prizeId"));
                    if($(this).find("input").val()==""){
                        is_empty = false;
                        return false;
                    }
                    day.push($(this).find("input").val());
                }

            });
            if(!is_empty){
                bootbox.alert("请填完必填内容");
                return false;
            }
            /*******  验证奖品数是否3到8个   ****************/
            if( prizeId.length < 3 || prizeId.length>8){
                bootbox.alert("请添加3到7个奖品");
                return false;
            }
            if( $(f+ " #atCode").val()=="hongbao" && day.length < 3 ){
                bootbox.alert("请添加至少3个额外奖励");
                return false;
            }
            /*******  验证奖品概率是不是等于100   ****************/
            if( percent>100 || percent<100){
                bootbox.alert("奖品的概率总和需为100%，请修改后重试");
                return false;
            }
            /***********验证时间********************/
            if( $(f +" #timeEnable").val()=="1" ){
                if($(f +" #startTime").val()=="" || $(f +" #startTime").val()==""){
                    bootbox.alert("请填写活动开始结束时间");
                    return false;
                }
            }
            var form = $(f);
            $(obj).attr("disabled",true);
            var action = form[0].action;
            var data={
                'code':$(f+" #code").val(),
                'actPrizeIds':actPrizeId.join(","),
                'actHongbaoIds':actHongbaoId.join(","),
                'prizeIds':prizeId.join(","),
                'chances':chance.join(","),
                'deleteId':deleteId.join(","),
                'otherDeleteId':otherDeleteId.join(","),
                'days':day.join(","),
                'otherPrizeIds':other_prizeId.join(","),
                'actId':$(f +" #actId").val(),
                'atCode':$(f +" #atCode").val(),
                'actName':$(f +" #actName").val(),
                'timeEnable':$(f+" input[name='timeEnable']:checked").val(),
                'startTime':$(f +" #startTime").val(),
                'endTime':$(f +" #endTime").val(),
                'rule':$(f +" #rule").val(),
                'dayInitTimes':$(f +" #dayInitTimes").val(),
                'dayShareAddTimes':$(f +" #dayShareAddTimes").val(),
                'dayShareLimit':$(f +" #dayShareLimit").val()
            }
            sendPost(action,data,obj);
        }

    }
    function sendPost(action,data,obj){
        jQuery.post(action, data,function(result) {
            $(obj).attr("disabled",false);
            if(result.code == 200){
                bootbox.confirm({
                    title : 'success',
                    message: result.msg,
                    callback: function(result) {
                        window.location.href="/activity/h5/listActivity?currentPage=1";
                    }
                })
            }else{
                bootbox.alert(result.msg);
            }
        },"json");
    }
    /**
     * 控件添加操作
     */
    function addPrize() {
        var prizeName = $(f+" #prizeId option:selected").text();
        var prizeId = $(f+" #prizeId option:selected").val();
        if(prizeId==null || prizeId==""){
            return false;
        }
        if($(f+" #atCode").val()==""){
            bootbox.alert("请选择模板");
            return false;
        }
        var index = 0;//定义添加的个数
        $(f+" .dazhuanpan table tr").each(function (){
            if($(this).css("display")!='none'){
                index++;
            }
        });
        //最多只能添加8个
        if( index>=8){
            return false;
        }
        var rate = 1;
        $(".dazhuanpan table").append(getPrizeHtml(prizeName,rate,prizeId));
    }
    /**
     * 控件添加操作
     */
    function addHongbao() {
        var prizeName = $(f+" #other_prizeId option:selected").text();
        var prizeId = $(f+" #other_prizeId option:selected").val();
        if(prizeId==null || prizeId==""){
            return false;
        }
        var rate = 1;
        $(".hongbao table").append(getHongbaoHtml(prizeName,rate,prizeId));
    }
    /*
     * 添加奖品及兑换数量html脚本
     * */
    function addExchange() {
        var prizeName = $(f+" #exchange_prizeId option:selected").text();
        var prizeId = $(f+" #exchange_prizeId option:selected").val();
        if(prizeId==null || prizeId==""){
            return false;
        }
        var index = 0;//定义添加的个数
        $(f+" .prize_exchange  tr").each(function (){
            if($(this).css("display")!='none'){
                index++;
            }
        });
        //最多只能添加8个
        if( index>=8){
            return false;
        }
        var html= "<tr  prizeId= " + prizeId + " actPrizeId=-1>"
            +"<td width='20%' ><button class='btn btn-default'  type='button' disabled>" + prizeName + "</button></td>"
            +"<td width='15%' align='right'><label class='control-label' >兑换数量:</label></td>"
            +"<td width='30%' align='center'><input type='text' maxlength='6' onkeyup='this.value=this.value.replace(/[^0-9]/g,\"\")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,\"\")' placeholder='请输入大于0整数' class='form-control' value='1'/></td>"
            +"<td align='left'><span class='file_del' onclick='del(this)' style='margin-left: 10px;'>-</span></td>"
            +"</tr>";
        $(".prize_exchange").append(html);
    }

    //返回操作
    function  returnBack() {
        if( deleteId.length>0 || deleteId.length> 0){
            bootbox.confirm("修改的内容没有保存，确定放弃吗", function(result){
                if(result){
                    window.location.href="/activity/h5/listActivity.vm";
                }
            });
        }else{
            window.location.href="/activity/h5/listActivity.vm";
        }

    }
    //删除额外奖品配置
    function delOtherPrize(obj,actPrizeId){
        bootbox.confirm("是否确认删除?", function(result){
            if(result){
                $(obj).parent().parent().hide();
                otherDeleteId.push(actPrizeId);
            }
        })
    }
    /**
     * 控件删除方法
     * @param obj
     */
    function delPrize(obj,actPrizeId){

        bootbox.confirm("是否确认删除?,删除后概率不为100%", function(result){
            if(result){
                $(obj).parent().parent().hide();
                deleteId.push(actPrizeId);
            }
        })
    }
    function delExchangePrize(obj,actPrizeId){
        bootbox.confirm("是否确认删除?", function(result){
            if(result){
                $(obj).parent().parent().hide();
                deleteId.push(actPrizeId);
            }
        })
    }
    /**
     * 添加兑换模板可用时间轴
     *
     */
    function addAxis() {
        var index = 0;
        $(f+" #table_axis tr").each(function (){
            if($(this).css("display")!='none'){
                index++;
            }
        });
        //最多只能添加8个
        if( index>=4){
            bootbox.alert("最多只能添加4个");
            return false;
        }
        var value = $("#input_axis").val();
        if( value == "" || value.indexOf(":")==-1){
            bootbox.alert("请输入正确时间格式");
            return false;
        }
        $(f +" #table_axis").append("<tr >"
            +"<td width='30%' align='center'><input type='text' class='form-control' value='"+value+"' /></td>"
            +"<td align='left'><span class='file_del' onclick='delAxis(this)' style='margin-left: 10px;'>-</span></td>"
            +"</tr>");
    }
    /**
     * 删除兑换模板时间轴
     */
    function delAxis(obj){
        bootbox.confirm("是否确认删除?", function(result){
            if(result){
                $(obj).parent().parent().hide();
            }
        })
    }
    /**
     * 控件删除方法
     * @param obj
     */
    function del(obj){
        $(obj).parent().parent().hide();
    }
    /**
     * 控件html代码
     * @param prizeName   奖品名称
     * @param rate        中奖概率
     * @param prizeId     奖品id
     * @returns {string}
     */
    function getPrizeHtml(prizeName,rate,prizeId){
        var html= "<tr  prizeId= " + prizeId + " actPrizeId=-1>"
            +"<td width='20%' ><button class='btn btn-default'  type='button' disabled>" + prizeName + "</button></td>"
            +"<td width='15%' align='right'><label class='control-label' >获奖概率(%):</label></td>"
            +"<td width='30%' align='center'><input type='text' maxlength='3' onkeyup='this.value=this.value.replace(/[^0-9]/g,\"\")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,\"\")' placeholder='请输入大于0整数' class='form-control' value='" + rate + "'/></td>"
            +"<td align='left'><span class='file_del' onclick='del(this)' style='margin-left: 10px;'>-</span></td>"
            +"</tr>"
        return html;
    }
    /**
     * 控件html代码
     * @param prizeName   奖品名称
     * @param day        领取天数条件
     * @param prizeId     奖品id
     * @returns {string}
     */
    function getHongbaoHtml(prizeName,days,prizeId){
        var html= "<tr  prizeId= " + prizeId + " actPrizeId=-1>"
            +"<td width='20%' ><button class='btn btn-default'  type='button' disabled>" + prizeName + "</button></td>"
            +"<td width='15%' align='right'><label class='control-label' >累计天数:</label></td>"
            +"<td width='30%' align='center'><input type='text' maxlength='3' onkeyup='this.value=this.value.replace(/[^0-9]/g,\"\")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,\"\")' placeholder='请输入大于0整数' class='form-control' value='" + days + "'/></td>"
            +"<td align='left'><span class='file_del' onclick='del(this)' style='margin-left: 10px;'>-</span></td>"
            +"</tr>"
        return html;
    }
    //上传文件
    function uploadFile(){
        var src = "uploadFile.htm";
        if($('#iconUrl').val()==''){
            bootbox.alert('请选择上传文件');
            return false;
        }
        jQuery.ajaxFileUpload({
            url:"/activity/h5/uploadIcon?ImgFileSize=500", //需要链接到服务器地址
            secureuri:false,
            fileElementId:"iconUrl", //文件选择框的id属性
            dataType: 'json',  //服务器返回的格式类型
            success: function (data, status){
                if(data.code == 200){
                    $('#iconUrl').attr('data-value',data.data.url);
                    $('#btnImg').val(data.data.url);
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