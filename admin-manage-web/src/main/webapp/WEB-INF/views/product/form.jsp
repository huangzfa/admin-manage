<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <style type="text/css">
        .sub_file_li{float:left;position:relative;margin-left:2%;margin-top:0px;list-style:none;}
        .btn-group-sm>.btn, .btn-sm{
            padding: 7px 10px;
        }
        .file_del{position: absolute; left:80%; bottom:17px;background: #eb847c;color: #fff;padding: 0 5px;min-width: 22px;height: 22px;line-height: 22px;text-align: center;vertical-align: middle;border-radius: 20px;font-size: 14px;font-weight: normal;ont-style: normal;cursor:pointer;}
        .gray{color: #cccccc;}
        .duobei-product table th, .duobei-product table td{
            padding:3px;
        }
    </style>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui"/>
    <!--  -->
</head>
<body>
<jsp:include page="/WEB-INF/include/authConfigDialog.jsp"/>
<jsp:include page="/WEB-INF/include/channelDialog.jsp"/>
<jsp:include page="/WEB-INF/include/zfbAccountDialog.jsp"/>
<jsp:include page="/WEB-INF/include/authConfigDialog2.jsp"/>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/product/list">产品列表</a></li>
    <li class="active">
        <shiro:hasPermission name="product:list:edit">
                    <c:choose>
                        <c:when test="${product.id!=''}">
                            <a href="javascript:void(0);">编辑产品</a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:void(0);">添加产品</a>
                        </c:otherwise>
                    </c:choose>

        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <form  class="form-horizontal" id="productForm">
        <input type="hidden" id="id" name="id" value="${product.id}">
        <input type="hidden" id="dataVersion" name="dataVersion" value="${product.dataVersion}">
        <div class="control-group">
            <label class="control-label"><h5>基本信息</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label">产品名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写产品名称" name="productName" id="productName" maxlength="5" value="${product.productName}" ></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">最低借款额度：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写最低借款额度" name="lowQuota" id="lowQuota" maxlength="8" value="${product.lowQuota}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">协议类型:</label>
            <div class="controls">
                <c:forEach items="${protocols}" var="protocol">
                    <label class="radio-inline">
                        <input type="radio"  value="${protocol.id}" name="protocolId"  ${not empty product && product.protocolId==protocol.id?"checked":''}><span>${protocol.protocolName}</span>
                    </label>
                </c:forEach>

            </div>
        </div>
        <div class="control-group">
            <label class="control-label">还款方式:</label>
            <div class="controls">
                <select  name="repaymentType" id="repaymentType" class="selectpicker show-tick form-control valid"  descripe="请选择还款方式">
                    <c:forEach items="${repaymentTypes}" var="type">
                        <option value="${type.dicVal}" ${not empty product && product.repaymentType==type.dicVal?"selected":''}>${type.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">逾期费率：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写逾期费费率"  name="overdueRate" id="overdueRate" maxlength="6" value="${product.overdueRate}" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">逾期费公式:</label>
            <div class="controls">
                <select  name="overdueFormulaCode" id="overdueFormulaCode" class="selectpicker show-tick form-control valid"  descripe="请选择逾期费公式" >
                    <c:forEach items="${overdueFormulas}" var="formula">
                        <option value="${formula.formulaCode}" title="${formula.description}" ${not empty product && product.overdueFormulaCode==formula.formulaCode?"selected":''}>${formula.formulaName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品描述：</label>
            <div class="controls">
                <input type="text" class="form-control valid" style="width: 43%" descripe="请填写产品描述" type="text" name="description" id="description" maxlength="20" value="${product.description}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><h5>营销文案配置</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label">首页文案：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写首页文案" name="showTips1" id="showTips1" maxlength="6" value="${showTips1}"></input>
                <input type="text" class="form-control valid" descripe="请填写首页文案" name="showTips2" id="showTips2" maxlength="6" value="${showTips2}"></input>
                <input type="text" class="form-control valid" descripe="请填写首页文案" name="showTips3" id="showTips3" maxlength="10" value="${showTips3}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">未获取额度下最高可借：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写最高额度"  name="showMaxQuota" id="showMaxQuota" maxlength="8" value="${product.showMaxQuota}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
    </form>
    <br>
    <form  class="form-horizontal" id="basicAuthForm">
        <div class="control-group">
            <label class="control-label" style="width: 500px;"><h5>认证项配置<small>*是否必填认证以及认证顺序修改后请点击页面下方的保存才可生效。</small></h5></label>
        </div>
        <div class="control-group">
            <label class="control-label"><h6>基础认证项</h6></label>
        </div>
        <div class="control-group">
            <div class="controls">
                <table class="table table-hover" >
                    <thead>
                        <th>认证项名称</th>
                        <th>是否可用</th>
                        <th>是否必须认证</th>
                        <th>认证顺序</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${basicAuths}" var="auth">
                            <tr class="${auth.authState==0?'gray':''}" authId ='${auth.id}' authGroup="${auth.authGroup}" authCode="${auth.authCode}" authType="1" id="${auth.productAuthId}" authName="${auth.authName}" authState="${auth.authState}">
                                <td>${auth.authName}</td>
                                <td>${auth.authState==1?'可用':'不可用'}</td>
                                <td>
                                    <input type="checkbox" ${auth.authState==0?'disabled':''} id="basicCheckbox" <c:if test="${auth.isRequired==1}">checked</c:if>/>
                                </td>
                                <td><input type="text" style="background-color:${auth.authState==0?'#e5e5e5':''}" value="${auth.authSort}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
    <form  class="form-horizontal" id="supplyForm">
        <div class="control-group">
            <label class="control-label"><h6>补充认证</h6></label>
        </div>
        <div class="control-group">
            <label class="control-label"><a href="javascript:selectAuthConfig('${product.id}')" class="btn btn-primary">添加补充认证</a></label>
        </div>
        <div class="control-group">
            <div class="controls">
                <table class="table table-hover">
                    <thead>
                        <th>认证项名称</th>
                        <th>是否可用</th>
                        <th>是否必须认证</th>
                        <th>认证顺序</th>
                    </thead>
                    <tbody id="supplyTable">
                        <c:forEach items="${pSupplyAuth}" var="supply">
                            <tr class="${supply.authState==0?'gray':''}" authId ='${supply.authId}' authGroup="${supply.authGroup}" authCode="${supply.authCode}" authType="2" id ='${supply.id}' authName="${supply.authName}" authState="${supply.authState}">
                                <td>${supply.authName}</td>
                                <td>${supply.authState==1?'可用':'不可用'}</td>
                                <td>
                                    <input type="checkbox"  id="supplyCheckbox" <c:if test="${supply.isRequired==1}">checked</c:if>/>
                                </td>
                                <td><input type="text"  value="${supply.authSort}" style="background-color:${supply.authState==0?'#e5e5e5':''}"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">启用M选N认证:</label>
            <div class="controls">
                <label class="radio-inline">
                    <input type="checkbox"  value="1" name="seleteEnable" id="seleteEnable" ${product.seleteEnable==1?"checked":''}><span></span>
                </label>
                <input type="text" class="form-control" name="select1" ${product.seleteEnable!=1?"disabled":''} id="select1" value="${select1}"  maxlength="6"  onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>选
                <input type="text" class="form-control" name="select2" ${product.seleteEnable!=1?"disabled":''} id="select2" value="${select2}" maxlength="6"  onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">备选认证范围:</label>
            <div class="col-sm-5" id="bxrz">
                <a id="selectButton" ${product.seleteEnable!=1?"disabled":''} onClick="selectBxrz()" class="btn btn-default btn-sm" style="margin-left: 3%">
                    <span class="glyphicon glyphicon-plus">添加</span>
                </a>
                <c:forEach items="${authGroup}" var="group">
                    <li class='sub_file_li' authGroup="${group.authGroup}" authCode="${group.authCode}">
                        <a class='btn  btn-default' >${group.authName}</a>
                        <span class='file_del' onclick="_delBxrz(this,'${group.authCode}')">×</span>
                    </li>
                </c:forEach>
            </div>
        </div>
    </form>
    <form  class="form-horizontal" id="riskForm">
        <input type="hidden" value="" name="dataVersion" id="dataVersion">
        <input type="hidden" value="" name="channelCode" id="channelCode">
        <input type="hidden" value="" name="id" id="id">
        <div class="control-group">
            <label class="control-label col-sm-1"><h5>风控场景配置</h5></label>
        </div>
        <div class="control-group" >
            <label class="control-label col-sm-1"><a href="javascript:selectChannel(${product.id})" class="btn btn-primary">添加特殊渠道</a></label>
            <div class="col-sm-5" id="channelGroup">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">额度风控场景编码：</label>
            <input type="text" class="form-control valid" descripe="请填写额度风控场景编码" name="quotaSceneCode" id="quotaSceneCode" maxlength="32" value=""></input>
            <a href="javascript:validSceneId('quotaSceneCode')">校验场景id</a>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">借款风控场景编码-首次新用户：</label>
            <input type="text" class="form-control valid" descripe="请填写借款风控场景编码-首次新用户" name="borrowSceneCodeFirst" id="borrowSceneCodeFirst" maxlength="32" value=""></input>
            <a href="javascript:validSceneId('borrowSceneCodeFirst')">校验场景id</a>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">借款风控场景编码-非首次老用户：</label>
            <input type="text" class="form-control valid" descripe="请填写借款风控场景编码-非首次老用户" name="borrowSceneCode" id="borrowSceneCode" maxlength="32" value=""></input>
            <a href="javascript:validSceneId('borrowSceneCode')">校验场景id</a>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1"><h5>利率期限配置</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label">默认期限:</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写默认期限" name="borrowNper" id="borrowNper" maxlength="20" value=""  onkeyup='this.value=this.value.replace(/[^0-9,]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <table class="table table-hover">
                    <thead>
                        <th >利率下限（包含）</th>
                        <th>利率上限（不包含）</th>
                        <th>可借期限</th>
                        <th></th>
                    </thead>
                    <tbody id="rangeTable">

                    </tbody>
                </table>
            </div>
            <div class="control-group">
                <label class="control-label"><a href="javascript:addRange()" class="btn btn-primary">添加区间</a></label>
            </div>
        </div>
        <div class="control-group">
            <input type="hidden" name="zfbAccountId" value="" id="zfbAccountId" class="valid" descripe="请选择支付宝">
            <label class="control-label col-sm-1"><h5>还款支付宝</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">选择支付宝:</label>
            <div class="col-sm-5">
                <a  onClick="selectZfb()" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-plus">添加</span>
                </a>
                <li class="sub_file_li selectZfb" style="display: none;margin-right: 1%">
                    <a class="btn  btn-default disabled"></a>
                </li>
            </div>
        </div>
    </form>
    <div class="form-actions">
        <shiro:hasPermission name="product:list:edit">
            <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/product/list'"/>
    </div>
</div>
<script>
    var _data = null;//定义全局风控表单数据变量
    var channelCode = "0";
    $(function(){
         var channels = '${channels}';
         _data = eval("("+channels+")");
        initChannel(_data[getIndex("0")]);//展示默认渠道
        for(var i=0;i<_data.length;i++){
            _addChannel(_data[i]);//展示渠道标签
        }

        $("#seleteEnable").bind("click",function(){
            if($(this).prop("checked")){
                $("#supplyForm #select1").attr("disabled",false);
                $("#supplyForm #select2").attr("disabled",false);
                $("#supplyForm #selectButton").attr("disabled",false);
            }else{
                $("#supplyForm #select1").attr("disabled",true);
                $("#supplyForm #select2").attr("disabled",true);
                $("#supplyForm #selectButton").attr("disabled",true);
            }
        })
        /****************** 渠道点击时，保存每个渠道的数据 **************************/

    })

    function initChannel(_d){
        $("#riskForm #id").val(_d.channel.id);
        $("#riskForm #channelCode").val(_d.channel.channelCode);
        $("#riskForm #dataVersion").val(_d.channel.dataVersion);
        $("#riskForm #quotaSceneCode").val(_d.channel.quotaSceneCode);
        $("#riskForm #borrowSceneCodeFirst").val(_d.channel.borrowSceneCodeFirst);
        $("#riskForm #borrowSceneCode").val(_d.channel.borrowSceneCode);
        $("#riskForm #borrowNper").val(_d.channel.borrowNper);
        if( typeof(_d.channel.zfbAccountId)!="undefined"){
            $("#riskForm .selectZfb").css("display","block");
            $("#riskForm #zfbAccountId").val(_d.channel.zfbAccountId);
            $("#riskForm .selectZfb a").text(_d.channel.zfbAccountName);
        }
        for(var j=0;j<_d.list.length;j++){
             _addRange(_d.list[j]);//展示利率区间
        }
    }

    function getIndex(code) {
        if( _data ){
            for(var i=0;i<_data.length;i++){
                if(_data[i].channel.channelCode ==code){
                    return i;
                }
            }
        }
        return -1;
    }

    function save(){
        var bool = true;
        /*******  验证表单必填项目   ****************/
        $(".valid").each(function() {
            var descripe  = $(this).attr("descripe");
            if( ($(this).val()=="" || $(this).val()==null) && descripe!=""){
                top.layer.alert(descripe, {icon: 5});
                bool = false;
                return false;
            }
        })
        //是否启用M选N认证
        var select1 = $("#supplyForm #select1").val();
        var select2 = $("#supplyForm #select2").val();
        if( $("#supplyForm #seleteEnable").prop("checked")){
            if(select1=='' || select2==''){
                top.layer.alert("请填写M选N认证", {icon: 5});
                return false;
            }
            if( parseInt(select2) > parseInt(select1) ){
                top.layer.alert("M选N认证不合法", {icon: 5});
                return false
            }
            var n = 0;
            $("#bxrz li").each(function(){
                if($(this).css("display")!='none'){
                    n++;
                }
            })
            if(n < select1 || select2>n){
                top.layer.alert("M选N认证不合法", {icon: 5});
                return false;
            }
        }
        if( bool ){
            //验证协议
            var protocolId = $('input[name="protocolId"]:checked').val();
            if( protocolId == null || protocolId == ''){
                top.layer.alert("请选择协议类型", {icon: 5});
                return false;
            }
            if( $("#showMaxQuota").val()=="0" || $("#lowQuota")=="0"){
                top.layer.alert("额度不能为0", {icon: 5});
                return false;
            }
            if( !checkNumber($("#showMaxQuota").val())|| !checkNumber($("#lowQuota").val())){
                top.layer.alert("请输入整数金额", {icon: 5});
                return false;
            }
            if( $("#showMaxQuota").val() >10000000){
                top.layer.alert("未获取额度下最高可借10000000", {icon: 5});
                return false;
            }
            //判断该渠道编码是否存在
            var index = getIndex(channelCode);
            var object = {};//定义新的渠道对象
            if( index == -1 ){//如果这是一个新增渠道
                top.layer.alert("找不到渠道编码", {icon: 5});
                return false;
            }
            object.channel = getChannelForm();//获取渠道form表单
            var list = getRange();//定义利率区间
            if( list== false){
                top.layer.alert("请添加利率区间", {icon: 5});
                return false;
            }
            if( bool ){
                if(cycleRepeat(list)){
                    top.layer.alert("该期限已存在，请重新填", {icon: 5});
                    return false;
                }
                if( !rateInclude(list)){
                    top.layer.alert("配置的利率区间下限必须等于上一个利率区间的上限，请重新填", {icon: 5});
                    return false;
                }
                object.list = list;//获取区间list
                _data[index] = object;//重新赋值
                var data = Object.assign(productFormSubmit(), authFormSubmit(), channelFormSubmit());
                top.layer.load();
                $("#btnSubmit").attr("disabled",true);
                jQuery.post("${ctxA}/product/save", data,function(result) {
                    top.layer.closeAll('loading');//关闭loading
                    $("#btnSubmit").attr("disabled",false);
                    if (result.code ==1) {
                        top.layer.alert("操作成功", {
                            icon: 6,
                            end: function(){
                                window.location.href="${ctxA}/product/list";
                            }
                        });
                    } else {
                        top.layer.alert(result.msg, {icon: 5});
                    }
                },"json")
            }
        }

    }

    //产品表单提交
    function productFormSubmit(){
        var seleteEnable = 0;
        var seleteRule = null;
        if( $("#supplyForm #seleteEnable").prop("checked")){
            seleteEnable = 1;
            seleteRule = $("#supplyForm #select1").val()+"s"+$("#supplyForm #select2").val()
        }else{
            seleteRule = "";
        }
        return {
            "id":$("#productForm #id").val(),
            "dataVersion":$("#productForm #dataVersion").val(),
            "productName":$("#productForm #productName").val(),
            "description":$("#productForm #description").val(),
            'overdueRate':$("#productForm #overdueRate").val(),
            "protocolId":$('input[name="protocolId"]:checked').val(),
            "lowQuota":$("#productForm #lowQuota").val(),
            "showMaxQuota":$("#productForm #showMaxQuota").val(),
            "showTips":$("#productForm #showTips1").val()+","+$("#productForm #showTips2").val()+","+$("#productForm #showTips3").val(),
            "repaymentType":$("#productForm #repaymentType").val(),
            "seleteEnable":seleteEnable,
            'seleteRule':seleteRule,
            "overdueFormulaCode":$("#productForm #overdueFormulaCode").val(),
        }
    }

    //认证表单提交
    function authFormSubmit() {
        //基础认证表单提交
        var configLists = [];
        $("#basicAuthForm tbody tr,#supplyForm tbody tr").each(function(){
            var obj = $(this);
            var object ={};
            object.id = obj.attr("id");
            object.authCode = obj.attr("authCode");
            object.authType = obj.attr("authType");
            object.authSort = obj.find("input[type='text']").val();
            object.authId = obj.attr("authId");
            object.authGroup = obj.attr("authGroup");
            if( obj.find("input[type='checkbox']").prop("checked")){
                object.isRequired=1;
            }else{
                object.isRequired=0;
            }
            if( $("#supplyForm #seleteEnable").prop("checked")){
                //M选N认证组
                $("#bxrz li").each(function(){
                    if($(this).css("display")!='none'){
                        //匹配到相同渠道，且是添加项
                        if(object.authCode == $(this).attr("authCode") && $(this).attr("authGroup")=="0"){
                            object.authGroup="0";
                            return false;
                        }
                    }
                })
            }else{
                object.authGroup = 1;
            }
            configLists.push(object);
        });
        return {
            'configLists':JSON.stringify(configLists),
        }
    }

    //渠道表单提交
    function channelFormSubmit() {
         return {
             'channels':JSON.stringify(_data),
             '_delchannelId':_delchannelId.join(","),
             '_delChannelVd':_delChannelVd.join(","),
             '_delChannelRangeId':_delChannelRangeId.join(",")
         }
    }

    function delOptions(obj){
        $(obj).parent().hide();
    }

    //添加备选认证
    function selectBxrz(){
        //将页面认证项数据组织起来，传给对话框
        if( !$("#supplyForm #seleteEnable").prop("checked") ){
            return false;
        }
        var configLists = [];
        $("#basicAuthForm tbody tr,#supplyForm tbody tr").each(function(){
            var object ={};
            object.authState = $(this).attr("authState");
            object.authCode = $(this).attr("authCode");
            object.authName = $(this).attr("authName");
            object.checked = false;
            $("#bxrz li").each(function(){
                if($(this).css("display")!='none'){
                   if(object.authCode == $(this).attr("authCode")){
                       object.checked = "checked";
                       return false;
                   }
                }
            })
            configLists.push(object);
        });
        modalAuthConfig2.open({
            configLists:configLists,
            callback:function(data){
                if( data && data.list.length>0 ){
                        for(var i = 0;i<data.list.length;i++){
                            var add = true;
                            $("#bxrz li").each(function(){//遍历添加项
                                if($(this).css("display")!='none'){
                                    if($(this).attr("authCode") == data.list[i].authCode){//添加过的不能再添加
                                        add = false;
                                        return false;
                                    }
                                }
                            })
                            if( add){
                                $("#bxrz").append("<li class='sub_file_li' authCode ="+data.list[i].authCode+" authGroup='0'>"
                                    +"<a class='btn  btn-default' >"+data.list[i].authName+"</a>"
                                    +"<span class='file_del' onclick='delBxrz(this)'>×</span>"
                                    +"</li>");
                            }
                        }
                }
                $("#modalAuthConfig2").modal("hide");
            }
        })
    }

    //选认证项控件调用
    function selectAuthConfig(productId){
        modalAuthConfig.open({
            productId:productId,
            callback:function(data){
                if( data && data.list.length >0 ){
                    for(var i = 0;i<data.list.length;i++){
                        var auth = data.list[i];
                        var add = true;
                        $("#supplyTable tr").each(function(){
                            if( $(this).attr("authCode") == auth.authCode){//不能重复添加
                                add = false;
                                return false;
                            }
                        })
                        if( add ){
                            $("#supplyForm #supplyTable").append("<tr authType='2' authId="+auth.authId+" authCode="+auth.authCode+" id=-1 authName="+auth.authName+" authState="+auth.authState+" authGroup='1'>" +
                                "<td>"+auth.authName+"</td>" +
                                "<td>"+(auth.authState==1?"是":"否")+"</td>" +
                                "<td><input type='checkbox'  id='supplyCheckbox' value=''/> </td>" +
                                "<td><input type='text' value='1'></td>" +
                                "</tr>");
                        }
                    }
                }
                $("#modalAuthConfig").modal("hide");
            }
        });
    };

    //添加渠道
    function selectChannel(productId){
        modalChannel.open({
            productId:productId,
            callback:function(data){
                if( data && data.list.length >0 ){
                    for(var i = 0;i<data.list.length;i++){
                        var index = getIndex(data.list[i].channelCode);
                        if( index ==-1 ){
                            var object = {'channel':{'id':-1,'channelName':data.list[i].channelName,'dataVersion':1,'channelCode':data.list[i].channelCode}};
                            object.list = [];
                            $("#riskForm channelCode").val(data.list[i].channelCode);
                            _data.push(object);
                            $("#channelGroup li").eq(0).before("<li class='sub_file_li' channelCode="+data.list[i].channelCode+" >"
                                +"<a class='btn  btn-default' onclick='switchChannel(this,\""+data.list[i].channelCode+"\")'>"+data.list[i].channelName+"</a>"
                                +"<span class='file_del' onclick='delChannel(this,\""+data.list[i].channelCode+"\")'>×</span>"
                                +"</li>");
                        }
                    }
                }
                $("#modalChannel").modal("hide");
            }
        });
    }

    //添加区间
    function addRange(){
        //获取最后一个有效tr的第二个input的值,传给下一个
        var low_bound = $("#riskForm #rangeTable tr:visible").eq(-1).find("input").eq(1).val();
        if( typeof(low_bound) == "undefined") low_bound="";
        $("#rangeTable").append("<tr id=-1>"
            +"<td ><input type='text' maxlength='6' value='" + low_bound + "'/>至</td>"
            +"<td ><input type='text' class='form-control' value=''  maxlength='6'/> &nbsp;</td>"
            +"<td ><input type='text' maxlength='30'  value='' onkeyup='this.value=this.value.replace(/[^0-9,]/g,\"\")' /></td>"
            +"<td ><a href='javascript:void(0)' onclick='delAddRange(this)' >删除</a></td>"
            +"</tr>");
    }

    //添加支付宝
    function selectZfb(){
        modalZfbAccount.open({
            callback:function(data){
                if(data){
                    $("#riskForm .selectZfb").css("display","block");
                    $("#riskForm #zfbAccountId").val(data.zfbAccountId);
                    $("#riskForm .selectZfb a").text(data.name);
                }
                $("#modalZfbAccount").modal("hide");
            }
        });
    }

    //编辑渠道
    function _addChannel(_a){
        var x = '';
        if( _a.channel.channelCode != '0'){
            x = "<span class='file_del' onclick='_delChannel(this,"+_a.channel.id+","+_a.channel.dataVersion+")'>×</span>";
        }
        $("#channelGroup").append("<li class='sub_file_li' channelCode="+_a.channel.channelCode+" >"
            +"<a class='btn  "+(_a.channel.channelCode == '0'?"btn-primary":"btn-default")+"' onclick='switchChannel(this,\""+_a.channel.channelCode+"\")'>"+_a.channel.channelName+"</a>"
            + x
            +"</li>");
    }

    //编辑区间
    function _addRange(_a){
        $("#rangeTable").append("<tr id='"+_a.id+"'>"
            +"<td ><input type='text' maxlength='6' value='"+_a.upBound+"' />至</td>"
            +"<td ><input type='text' class='form-control' value='"+_a.lowBound+"'  maxlength='6'/> &nbsp;</td>"
            +"<td ><input type='text' maxlength='30'  value='"+_a.borrowNper+"' onkeyup='this.value=this.value.replace(/[^0-9,]/g,\"\")' /></td>"
            +"<td ><a href='javascript:void(0)'onclick=' _delAddRange(this,"+_a.id+")' >删除</a></td>"
            +"</tr>");
    }

    //删除未保存认证备选组
    function delBxrz(obj){
        $(obj).parent().hide();
    }
    //删除已保存认证备选组
    function _delBxrz(obj,code){
        //将认证组清空
        $("#basicAuthForm tbody tr,#supplyForm tbody tr").each(function(){
            if( $(this).attr("authCode") == code){
                $(this).attr("authGroup","1");
                return false;
            }
        })
        $(obj).parent().hide();
    }
    //删除数据库保存渠道
    var _delchannelId = [];//定义渠道被删除id
    var _delChannelVd = [];//定义渠道被删除版本标志
    function _delChannel(obj,code,dataVersion){
        _delchannelId.push(code);
        _delChannelVd.push(dataVersion);
        $(obj).parent().hide();
    }
    //删除尚未保存的渠道
    function delChannel(obj,code){
        var n = getIndex(code)
        if( n == -1){
            top.layer.alert("找不到渠道编码", {icon: 5});
            return false;
        }
        _data.splice(n, 1);
        $(obj).parent().hide();
    }
    //删除尚未保存区间
    function delAddRange(obj){
        var n = 0;
        var a = $("#riskForm #rangeTable tr").each(function (){
            if($(this).css("display")!='none'){
                n ++ ;
            }
        })
        if( n == 1){
            top.layer.alert("最后一个区间不能删除", {icon: 5});
            return false;
        }
        $(obj).parent().parent().hide();
    }

    //删除数据库区间
    var _delChannelRangeId = [];//删除区间id
    function _delAddRange(obj,id){
        var n = 0;
        var a = $("#riskForm #rangeTable tr").each(function (){
            if($(this).css("display")!='none'){
               n ++ ;
            }
        })
        if( n == 1){
            top.layer.alert("最后一个区间不能删除", {icon: 5});
            return false;
        }
        _delChannelRangeId.push(id);
        $(obj).parent().parent().hide();
    }

    //渠道切换事件
    function switchChannel(obj,code){
        if( code == channelCode){
            return false;
        }
        top.layer.load();
        var bool = true;
        /*******  验证表单必填项目   ****************/
        $("#riskForm .valid").each(function() {
            var descripe  = $(this).attr("descripe");
            if( $(this).val()=="" && descripe!=""){
                top.layer.closeAll('loading');//关闭loading
                top.layer.alert(descripe, {icon: 5});
                bool = false;
                return false;
            }
        })
        if( bool ){
            //判断该渠道编码是否存在
            var index = getIndex(channelCode);
            var object = {};//定义新的渠道对象
            var list = getRange();//定义利率区间
            if( list== false){
                top.layer.closeAll('loading');//关闭loading
                return false;
            }
            if( index == -1 ){//如果这是一个新增渠道
                top.layer.alert("找不到渠道编码", {icon: 5});
                return false;
            }
            object.channel = getChannelForm();//获取渠道form表单

            if( bool){
                if(cycleRepeat(list)){
                    top.layer.alert("该期限已存在，请重新填", {icon: 5});
                    top.layer.closeAll('loading');//关闭loading
                    return false;
                }
                if( !rateInclude(list)){
                    top.layer.alert("配置的利率区间下限必须等于上一个利率区间的上限，请重新填", {icon: 5});
                    top.layer.closeAll('loading');//关闭loading
                    return false;
                }
                object.list = list;//获取区间list
                _data[index] = object;//重新赋值
                top.layer.closeAll('loading');//关闭loading
                channelCode = code;
                $("#riskForm #channelCode").val(channelCode);
                clearRiskForm();
                $("#riskForm #channelGroup").find("a").removeClass("btn-primary");
                $(obj).addClass("btn-primary");
                top.layer.closeAll('loading');//关闭loading
                initChannel(_data[getIndex(code)]);//查询渠道数据
            }
        }
    }
    //返回渠道表单
    function getChannelForm(){
        return {
            'id':$("#riskForm #id").val(),
            'channelCode':$("#riskForm #channelCode").val(),
            'quotaSceneCode':$("#riskForm #quotaSceneCode").val(),
            'borrowSceneCodeFirst':$("#riskForm #borrowSceneCodeFirst").val(),
            'borrowSceneCode':$("#riskForm #borrowSceneCode").val(),
            'zfbAccountId':$("#riskForm #zfbAccountId").val(),
            'zfbAccountName':$("#riskForm .selectZfb a").text(),
            'dataVersion':$("#riskForm #dataVersion").val(),
            'channelName':$("#channelGroup li .btn-primary").text(),
            'borrowNper':$("#riskForm #borrowNper").val()
        }
    }

    //清空风控表单
    function clearRiskForm(){
        $(':input','#riskForm').not(':button,:submit,:reset').val('').removeAttr('checked').removeAttr('selected');
        $("#rangeTable").children().remove();
        $("#riskForm .selectZfb").css("display","none");
        $("#riskForm .selectZfb a").text('');
    }

    function validSceneId(sceneCodeId){
        jQuery.post("${ctxA}/product/validSceneCode", {'code':$("#"+sceneCodeId).val()},function(result) {
            if (result.code ==1) {
                top.layer.alert("校验成功", {
                    icon: 6,
                    end: function(){

                    }
                });
            } else {
                top.layer.alert(result.msg, {icon: 5});
            }
        },"json")
    }

    /**
     * 判断区间期限是否重复
     * @param rangeList
     * @returns {boolean}
     */
    function cycleRepeat(rangeList) {
        bool = false;
        for(var i = 0;i<rangeList.length;i++){
            for(var j = i+1 ;j<rangeList.length;j++) {
                if (   rangeList[i].borrowNper == rangeList[j].borrowNper) {
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }
    /**
     * 判断区间利率是否上下包含
     * @param rangeList
     * @returns {boolean}
     */
    function rateInclude(rangeList) {
        bool = true;
        for(var i = 0;i<rangeList.length;i++){
            if( i< rangeList.length-1){
                if ( rangeList[i].lowBound != rangeList[i+1].upBound) {
                    bool = false;
                    break;
                }
            }
        }
        return bool;
    }

    function checkNumber(input) {
        var re = /^[1-9]+[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
        if (!re.test(input)) {
            return false;
        }else{
            return true
        }
    }

    function checkTerm(input) {
        if( input == null || input ==''){
            return false;
        }
       var arr = input.split(",");
        for(s in arr){
            if(!checkNumber(arr[s])){
                return false;
            }
        }
        return true;
    }
    function getRange(){
        var list = [];
        if(!checkTerm($("#borrowNper").val())){
            top.layer.alert("默认期限格式不正确", {icon: 5});
            return false;
        }
        var success = true;
        $("#riskForm #rangeTable tr").each(function(){

            if($(this).css("display")!='none'){

                var range = {};
                range.upBound = $(this).find("input").eq(0).val();
                range.lowBound = $(this).find("input").eq(1).val();
                range.borrowNper = $(this).find("input").eq(2).val();
                range.id = $(this).attr("id");
                if( range.upBound == '' || range.lowBound == '' || range.borrowNper==''){
                    top.layer.alert("请填写完成区间内容", {icon: 5});
                    list = false;
                    success =false;
                    return false;
                }
                if(!checkTerm(range.borrowNper)){
                    top.layer.alert("期限格式不正确", {icon: 5});
                    success =false;
                    return false;
                }
                list.push(range);
            }
        })
        if( !success){
            return false;
        }
        return list;
    }
</script>
</body>
</html>