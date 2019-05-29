<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,angular,common"/>
    <style type="text/css">
        .file_del{ left:70%;background: red; font-weight:bold;color: white;padding: 0 8px;border-radius: 14px;font-size:21px;cursor:pointer;}
    </style>
    <!--  -->
</head>
<body>

<div ng-app="formApp" ng-controller="formCtrl">
    <form class="form-horizontal" >
    <input type="hidden" value="activity.actId">
    <input type="hidden" value="static.btnImg">
    <input type="hidden" value="activity.code">
    <div class="control-group">
        <label class="col-sm-2 control-label">设置模板:</label>
        <div class="col-sm-5">
            <select ng-model="activity.atCode"   onchange="angular.element(this).scope().typeChange(this)" descripe="请填选择模板" class="selectpicker show-tick form-control valid" >
                <option ng-repeat="x in typeList" value="{{x.atCode}}">{{x.atName}}</option>
            </select>
        </div>
    </div>
    <div class="control-group">
        <label class="col-sm-2 control-label">活动名称:</label>
        <div class="col-sm-5">
            <input type="text" ng-model="activity.actName" class="form-control valid" descripe="请填写活动名称"  placeholder="活动名称,必填(限定12字)"  maxlength="12">
        </div>
    </div>
    <div class="control-group" ng-show="exchange_show">
        <label class="col-sm-2 control-label">可用时间:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" id="input_axis" maxlength="5" placeholder="例：14:00，最多可添加4个">
                <span class="input-group-btn">
                           <button class="btn btn-default" type="button" onclick="addAxis()">添加</button>
                    </span>
            </div>
            <table style="margin-top: 10px;" id="table_axis">

                <tr ng-repeat="x in timeAxiss">
                    <td  align='center'><input type='text' class='form-control' ng-model="x.axis" maxlength="5"/></td>
                    <td align='left'><span class='file_del' onclick="delAxis(this)" style='margin-left: 10px;'>-</span></td>
                </tr>

            </table>

        </div>
    </div>
    <div class="control-group dayInitTimes" >
        <label class="col-sm-2 control-label">初始次数:</label>
        <div class="col-sm-5">
            <input type="text" maxlength="2" class="form-control valid" descripe="请填写初始次数" ng-model="activity.dayInitTimes" placeholder="必填" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'>
        </div>

    </div>

    <div class="control-group">
        <label class="col-sm-2 control-label">活动规则:</label>
        <div class="col-sm-5">
            <textarea type="text" rows="7"  name="rule" class="form-control valid"  descripe="请填写活动规则"   maxlength="300">{{activity.rule}}</textarea>
            <input type="checkbox"  value="-1" ng-model="static.ruleEnable" ng-checked="{{static.ruleEnable==-1}}" ><span>不显示</span>

        </div>
    </div>
    <div class="control-group" ng-show="static_show">
        <label class="col-sm-2 control-label">按钮模板:</label>
        <div class="col-sm-5">
            <select ng-model="static.btnType"  class="selectpicker show-tick form-control valid" descripe="请选择按钮模板">
                <option value="">请选择</option>
                <option value="square" >方角模板</option>
                <option value="fillet" >圆角模板</option>
            </select>
        </div>
    </div>
    <div class="control-group" ng-show="static_show">
        <label class="col-sm-2 control-label">按钮文案:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control valid" ng-model="static.btnText" maxlength="12" descripe="请填写按钮文案">
        </div>
    </div>
    <div class="control-group" ng-show="static_show">
        <label class="col-sm-2 control-label">按钮背景:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control valid"   ng-model="static.btnColour" maxlength="12" descripe="请填写按钮背景">
        </div>
    </div>
    <div class="control-group" ng-show="static_show">
        <label class="col-sm-2 control-label">背景图:</label>
        <div class="col-sm-5">
            <table >
                <tr>
                    <td>
                        <input name="file" data-value="{{static.btnImg}}" id="iconUrl" type="file"/>
                    </td>
                    <td >
                        <input id="submitButton" type="button" value="上传图片" ng-click="uploadFile()"/>
                    </td>
                </tr>
            </table>
            <img src="{{static.btnImg}}" ng-show="btnImg-show" id="uploadIcon" height="50px" width="50px"/>
            <ul style="margin-left: 12%;">
                <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                <small class="help-block owner_ID">图片大小：500kb以内</small>
            </ul>
        </div>
    </div>
    <div class="control-group" ng-show="static_show">
        <label class="col-sm-2 control-label">跳转配置:</label>
        <div class="col-sm-5">
            <select ng-model = "static.jumpType" class="selectpicker show-tick form-control valid" descripe="请选择跳转配置" >
                <option value="">请选择</option>
                <option value="app" >下载app</option>
                <option value="other" >其他链接</option>
            </select>
        </div>
    </div>
    <div class="control-group" ng-show="static_show">
        <label class="col-sm-2 control-label">跳转地址:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control"  ng-model="static.jumpLink" maxlength="1000">
        </div>
    </div>


    <div class="control-group" ng-show="hongbao_show || zhuanpan_show">
        <label class="col-sm-2 control-label">设置奖品及概率:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <select   class="selectpicker show-tick form-control" id="prizeList">
                    <option ng-repeat="x in prizeList" value="{{x.prizeId}}">{{x.prizeName}}</option>
                </select>
                <span class="input-group-btn">
                        <button class="btn btn-default" type="button" ng-click="addPrize()">添加</button>
                </span>
            </div>
            <table style="margin-top: 10px;margin-left:12%">
                <tr   ng-repeat="(k,x) in prizeRelList track by $index">
                    <td ><button class='btn btn-default'  type='button' disabled>{{x.prizeName}}</button></td>
                    <td  align='left'>获奖概率(%):</td>
                    <td align='left'><input type='text' style="width:50%;" class='form-control' ng-model="x.chance"  onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="3"/></td>
                    <td align='left'><span class='file_del' ng-click="delPrize($index)" style='margin-left: 10px;'>-</span></td>
                </tr>
            </table>

        </div>
    </div>
    <div class="control-group hongbao" ng-show="hongbao_show">
        <label class="col-sm-2 control-label">设置额外奖品及概率:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <select class="selectpicker show-tick form-control" id="prizeHongbaoList">
                    <option ng-repeat="x in prizeList" value="{{x.prizeId}}">{{x.prizeName}}</option>
                </select>
                <span class="input-group-btn">
                        <button class="btn btn-default" type="button" ng-click="addHongbao()">添加</button>
                </span>
            </div>
            <table style="margin-top: 10px;margin-left:12%">
                <tr  ng-repeat="(k,x) in prizeHongbaoList track by $index">
                    <td ><button class='btn btn-default'  type='button' disabled>{{x.prizeName}}</button></td>
                    <td align='right'><label class='control-label' >累计天数:</label></td>
                    <td align='center'><input type='text' class='form-control' ng-model="x.days" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="3"/></td>
                    <td align='left'><span class='file_del' ng-click="delOtherPrize($index)" style='margin-left: 10px;'>-</span></td>
                </tr>
            </table>

        </div>
    </div>
    <div class="control-group" ng-show="exchange_show">
        <label class="col-sm-2 control-label">设置奖品及兑换数量:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <select ng-options="prize.prizeId as prize.prizeName for prize in prizeList" class="selectpicker show-tick form-control" >

                </select>
                <span class="input-group-btn">
                                <button class="btn btn-default" type="button" ng-click="addExchange()">添加</button>
                        </span>
            </div>
            <table style="margin-top: 10px;" class="prize_exchange">
                <tr  ng-repeat="x in prizeExchangeList">
                    <td width='20%' ><button class='btn btn-default'  type='button' disabled>{{x.prizeName}}</button></td>
                    <td width='15%' align='right'><label class='control-label' >兑换数量:</label></td>
                    <td width='30%' align='center'><input type='text' class='form-control' ng-model="x.exchangeNum" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="6"/></td>
                    <td align='left'><span class='file_del' onclick="delExchangePrize(this,{{x.actPrizeId}})" style='margin-left: 10px;'>-</span></td>
                </tr>
            </table>

        </div>
    </div>
    <div class="control-group" ng-show="activity.actId > 0">
        <label class="col-lg-1 control-label">选择模板:</label>
        <div class="col-lg-5">
            <div class="input-group">
                <select ng-options="link.value as link.name for link in links" class="selectpicker show-tick form-control" >
                </select>
                <span class="input-group-btn">
                         <button class="btn btn-default" type="button" onclick="addLink()">生成</button>
                    </span>
            </div>
        </div>
    </div>
    <div class="control-group"  ng-show="activity.actId > 0">
        <label class="col-sm-2 control-label">发布链接:</label>
        <label class="col-sm-5 control-label releaseLink"></label>
    </div>
    <div class="control-group" style="margin: 0 0 20px;">
        <div class="col-sm-4" style="margin-top:20px;">
            <a  class="btn btn-success btn-sm" ng-click="save()">
                <span class="glyphicon"> 保存</span>
            </a>
            <a  ng-click="returnBack()" class="btn btn-info btn-sm">
                <span class="glyphicon">返回</span>
            </a>
        </div>
    </div>

    </form>
</div>

<script>
    var activity_show = false;//活动div是否显示
    var exchange_show = false;//兑换活动div是否显示
    var hongbao_show = false;//红包div是否显示
    var zhuanpan_show = false;//转盘div是否显示
    var static_show = false;
    var activitys = '${activitys}';//活动主题
    var prizeHongbaoLists = '${prizeHongbaoLists}';//红包奖品
    var prizeRelLists = '${prizeRelLists}';//红包，转盘活动和奖品关联表
    var prizeExchangeLists = '${prizeExchangeLists}';//兑换活动和奖品关联表
    var statics = '${statics}';
    var prizeLists = '${prizeLists}';//奖品列表
    var types = '${types}';//活动类型
    var timeAxis = '${timeAxis}';
    var exchanges ='${exchanges}';
    if( activitys != ''){
        activity_show = true;
    }
    activity = activitys ==''?{} : eval("("+activitys+")");
    if( activity.atCode == 'zhuanpan'){
        zhuanpan_show = true;
    }
    if( prizeHongbaoLists !=''){
        hongbao_show = true;
    }
    prizeHongbaoList = prizeHongbaoLists == ''?[]:eval("("+prizeHongbaoLists+")");
    if( prizeExchangeLists !=''){
        exchange_show = true;
    }
    if( statics !=''){
        static_show = true;
    }
    static = statics == ''?{} : eval("("+statics+")");
    typeList = types == ''?[] : eval("("+types+")");
    timeAxiss = timeAxis == ''?[] : eval("("+timeAxis+")");
    exchange = exchanges == ''?{} : eval("("+exchanges+")");
    prizeList = prizeLists == ''?[] :eval("("+prizeLists+")");
    prizeRelList = prizeRelLists == ''?[] : eval("("+prizeRelLists+")");
    prizeExchangeList = prizeExchangeLists == ''?[] : eval("("+prizeExchangeLists+")");
    //创建应用
    var formApp = angular.module('formApp', []);
    //创建controller
    formApp.controller('formCtrl', function($scope, $http) {
        $scope.activity = activity;
        $scope.timeAxiss = timeAxiss;
        $scope.exchange = exchange;
        $scope.prizeHongbaoList = prizeHongbaoList;
        $scope.prizeRelList = prizeRelList;
        $scope.prizeExchangeList = prizeExchangeList;
        $scope.prizeList = prizeList;
        $scope.exchange_show = exchange_show;
        $scope.hongbao_show = hongbao_show;
        $scope.activity_show = activity_show;
        $scope.static = static;
        $scope.static_show = static_show;
        $scope.typeList = typeList;
        $scope.save = function() {
            var bool = true;
            /*******  验证表单必填项目   ****************/
            $(".valid").each(function() {
                var descripe  = $(this).attr("descripe");
                var display = $(this).parent().parent().css("display");
                if( display!="none" && ($(this).val()=="" || $(this).val()==null) && descripe!=""){
                    top.layer.alert(descripe, {icon: 5});
                    bool = false;
                    return false;
                }
            })
            if( bool ){
                var action = "";
                var data = {
                    'activity':JSON.stringify($scope.activity)
                };
                if( $scope.activity.atCode == "static"){
                    action = "toEditStatic";
                    data.static = JSON.stringify($scope.static);

                }else if($scope.activity.atCode == "exchange"){
                    action = "toEditExchange";
                    if( $scope.timeAxiss == null || $scope.timeAxiss.length < 1){
                        top.layer.alert("可用时间最少添加一个", {icon: 5});
                        return false;
                    }
                    if( $scope.prizeExchangeList == null || $scope.prizeExchangeList.length <1){
                        top.layer.alert("设置奖品最少添加一个", {icon: 5});
                        return false;
                    }
                    data.exchange = JSON.stringify($scope.exchange);
                    data.prizeExchangeList = JSON.stringify($scope.prizeExchangeList);
                }else{
                    if( $scope.activity.atCode == "hongbao"){
                        if( $scope.prizeHongbaoList.length <1 ){
                            top.layer.alert("额外奖品最少添加1个", {icon: 5});
                            return false;
                        }
                        data.prizeHongbaoList = JSON.stringify($scope.prizeHongbaoList);
                    }else if( $scope.activity.atCode == "zhuanpan"){
                        data.exchange = JSON.stringify($scope.exchange);
                        data.timeAxiss = JSON.stringify($scope.timeAxiss);
                    }else{
                        top.layer.alert("请选择模板", {icon: 5});
                        return false;
                    }
                    if( $scope.prizeRelList.length <3 ){
                        top.layer.alert("设置奖品最少添加3个", {icon: 5});
                        return false;
                    }
                    data.prizeRelList = JSON.stringify($scope.prizeRelList);
                }
                action = "save";
                $("#btnSubmit").attr("disabled",true);
                $http({
                    method:'post',
                    url:action,
                    data:data,
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    transformRequest: function(obj) {
                        var str = [];
                        for (var p in obj) {
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                }).success(function(result){
                    $("#btnSubmit").attr("disabled",false);
                    if (result.code ==1) {
                        top.layer.alert("操作成功", {
                            icon: 6,
                            end: function(){
                                window.location.reload();
                            }
                        });
                    } else {
                        top.layer.alert(result.msg, {icon: 5});
                    }
                })
            }
        };
        $scope.typeChange = function (obj) {
             if( obj.value == "hongbao"  ){
                $scope.hongbao_show = true;
                $scope.static_show = false;
                $scope.exchange_show = false;
                $scope.zhuanpan_show = false;
            }else if( obj.value == "static"){
                $scope.static_show = true;
                $scope.exchange_show = false;
                $scope.hongbao_show = false;
                $scope.zhuanpan_show = false;
            }else if(obj.value == "exchange"){
                $scope.exchange_show = true;
                $scope.hongbao_show = false;
                $scope.static_show = false;
                $scope.zhuanpan_show = false;
            }else if(obj.value == "zhuanpan" ){
                 $scope.hongbao_show = false;
                 $scope.static_show = false;
                 $scope.exchange_show = false;
                 $scope.zhuanpan_show = true;
             }
        };
        $scope.addPrize = function () {
            if( $scope.prizeRelList.length > 7){
                return false;
            }
            var prizeName = $("#prizeList :selected").text();
            var prizeId = $("#prizeList :selected").val();
            var data = {
                'actPrizeId':0,
                'prizeName':prizeName,
                'chance':0,
                'prizeId':prizeId
            }
            $scope.prizeRelList.push(data);
        };
        $scope.addHongbao = function () {
            if ($scope.prizeHongbaoList.length > 2) {
                return false;
            }
            var prizeName = $("#prizeList :selected").text();
            var prizeId = $("#prizeList :selected").val();
            var data = {
                'actPrizeId': 0,
                'prizeName': prizeName,
                'days': 0,
                'prizeId': prizeId
            }
            $scope.prizeHongbaoList.push(data);
        };
        $scope.delPrize = function (index) {
            $scope.prizeRelList.splice(index,1);
        };
        $scope.delOtherPrize = function (index) {
            $scope.prizeHongbaoList.splice(index,1);
        }
    })
</script>