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
    <div class="form-group">
        <label class="col-sm-2 control-label">设置模板:</label>
        <div class="col-sm-5">
            <select ng-model="activity.atCode" ng-options="type.atCode as type.atName for type in types"  descripe="请填选择模板" class="selectpicker show-tick form-control valid" >
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">活动名称:</label>
        <div class="col-sm-5">
            <input type="text" ng-model="activity.actName" class="form-control valid" descripe="请填写活动名称"  placeholder="活动名称,必填(限定12字)"  maxlength="12">
        </div>
    </div>
    <div class="form-group exchange" ng-show="exchange_show">
        <label class="col-sm-2 control-label">可用时间:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" id="input_axis" maxlength="5" placeholder="例：14:00，最多可添加4个">
                <span class="input-group-btn">
                           <button class="btn btn-default" type="button" onclick="addAxis()">添加</button>
                    </span>
            </div>
            <table style="margin-top: 10px;" id="table_axis">

                <tr ng-repeat="x in timeAxis">
                    <td width='30%' align='center'><input type='text' class='form-control' ng-model="x.axis" maxlength="5"/></td>
                    <td align='left'><span class='file_del' onclick="delAxis(this)" style='margin-left: 10px;'>-</span></td>
                </tr>

            </table>

        </div>
    </div>
    <div class="form-group dayInitTimes" >
        <label class="col-sm-2 control-label">初始次数:</label>
        <div class="col-sm-5">
            <input type="text" maxlength="2" class="form-control valid" descripe="请填写初始次数" ng-model="activity.dayInitTimes" placeholder="必填" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'>
        </div>
        <div class="col-sm-1" >
            <label class="checkbox-inline">
                <input type="checkbox"  value="0"><span>不限制</span>
            </label>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">活动规则:</label>
        <div class="col-sm-5">
            <textarea type="text" rows="7"  name="rule" class="form-control valid"  descripe="请填写活动规则"   maxlength="300">{{activity.rule}}</textarea>
        </div>
        <div class="col-sm-1 static" ng-show="rule-show">
            <label class="checkbox-inline">
                <input type="checkbox"  value="0" ng-model="static.ruleEnable" ng-checked="{{static.ruleEnable==0}}" ><span>不显示</span>
            </label>
        </div>
    </div>
    <div class="static" ng-show="static_show">
        <div class="form-group">
            <label class="col-sm-2 control-label">按钮模板:</label>
            <div class="col-sm-5">
                <select ng-model="static.btnType"  class="selectpicker show-tick form-control">
                    <option value="">请选择</option>
                    <option value="square" >方角模板</option>
                    <option value="fillet" >圆角模板</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">按钮文案:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" ng-model="static.btnText" maxlength="12">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">按钮背景:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control"   ng-model="static.btnColour" maxlength="12">
            </div>
        </div>
        <div class="form-group" style="margin: 0 0 20px;">
            <label class="col-sm-1">背景图:</label>
            <div class="col-sm-10">
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
                <ul style="margin-left: 6%;">
                    <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                    <small class="help-block owner_ID">图片大小：500kb以内</small>
                </ul>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">跳转配置:</label>
            <div class="col-sm-5">
                <select ng-model = "static.jumpType" class="selectpicker show-tick form-control" >
                    <option value="">请选择</option>
                    <option value="app" >下载app</option>
                    <option value="other" >其他链接</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">跳转地址:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control"  ng-model="static.jumpLink" maxlength="1000">
            </div>
        </div>
    </div>

    <div class="form-group zhuanpan" ng-show="zhuanpan_show">
        <label class="col-sm-2 control-label">设置奖品及概率:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <select  ng-options="prize.prizeId as prize.prizeName for prize in prizeList" class="selectpicker show-tick form-control" >

                </select>
                <span class="input-group-btn">
                        <button class="btn btn-default" type="button" ng-click="addPrize()">添加</button>
                </span>
            </div>
            <table style="margin-top: 10px;">
                <tr   ng-repeat="x in przieRelList">
                    <td width='20%' ><button class='btn btn-default'  type='button' disabled>{{x.prizeName}}</button></td>
                    <td width='15%' align='right'><label class='control-label' >获奖概率(%):</label></td>
                    <td width='30%' align='center'><input type='text' class='form-control' ng-model="x.chance"  onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="3"/></td>
                    <td align='left'><span class='file_del' ng-click="delPrize(this,{{x.actPrizeId}})" style='margin-left: 10px;'>-</span></td>
                </tr>
            </table>

        </div>
    </div>
    <div class="form-group hongbao" ng-show="hongbao_show">
        <label class="col-sm-2 control-label">设置额外奖品及概率:</label>
        <div class="col-sm-5">
            <div class="input-group">
                <select ng-options="prize.prizeId as prize.prizeName for prize in prizeList" class="selectpicker show-tick form-control" >

                </select>
                <span class="input-group-btn">
                        <button class="btn btn-default" type="button" ng-click="addHongbao()">添加</button>
                </span>
            </div>
            <table style="margin-top: 10px;">
                <tr  ng-repeat="x in prizeHongbaoList">
                    <td width='20%' ><button class='btn btn-default'  type='button' disabled>{{x.prizeName}}</button></td>
                    <td width='15%' align='right'><label class='control-label' >累计天数:</label></td>
                    <td width='30%' align='center'><input type='text' class='form-control' ng-model="x.days" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' onafterpaste='this.value=this.value.replace(/[^0-9]/g,"")' maxlength="3"/></td>
                    <td align='left'><span class='file_del' ng-click="delOtherPrize(this,{{x.actPrizeId}})" style='margin-left: 10px;'>-</span></td>
                </tr>
            </table>

        </div>
    </div>
    <div class="form-group exchange" ng-show="exchange_show">
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
                    <td align='left'><span class='file_del' ng-click="delExchangePrize(this,{{x.actPrizeId}})" style='margin-left: 10px;'>-</span></td>
                </tr>
                #end
            </table>

        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-1 control-label">选择模板:</label>
        <div class="col-lg-5">
            <div class="input-group">
                <select ng-options="link.value as link.name for link in links" class="selectpicker show-tick form-control" >
                </select>
                <span class="input-group-btn">
                         <button class="btn btn-default" type="button" gn-click="addLink()">生成</button>
                    </span>
            </div>
        </div>
    </div>
    <div class="form-group "  ng-show="activity_show">
        <label class="col-sm-2 control-label">发布链接:</label>
        <label class="col-sm-5 control-label releaseLink"></label>
    </div>
    <div class="form-group" style="margin: 0 0 20px;">
        <div class="col-sm-4" style="margin-top:20px;">
            <a  class="btn btn-success btn-sm" ng-click="save(this)">
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
    var exchane_show = false;//兑换活动div是否显示
    var hongbao_show = false;//红包div是否显示
    var zhuapan_show = false;//转盘div是否显示

    var activitys = '${activitys}';//活动主题
    var prizeHongbaoLists = '${prizeHongbaoLists}';//红包奖品
    var przieRelLists = '${przieRelLists}';//红包，转盘活动和奖品关联表
    var prizeExchangeLists = '${prizeExchangeLists}';//兑换活动和奖品关联表
    var prizeLists = '${prizeLists}';//奖品列表
    if( activitys != ''){
        activity = eval("("+activitys+")");
        activity_show = true;
    }
    if( prizeHongbaoLists !=''){
        prizeHongbaoList= eval("("+prizeHongbaoLists+")");
    }
    if( przieRelLists !=''){
        przieRelList= eval("("+przieRelLists+")");
    }
    if( prizeExchangeLists !=''){
        prizeExchangeList= eval("("+prizeExchangeLists+")");
    }
    if( prizeLists !=''){
        prizeList= eval("("+prizeLists+")");
    }
    //创建应用
    var formApp = angular.module('formApp', []);
    //创建controller
    formApp.controller('formCtrl', function($scope, $http) {
        $scope.activity = activity;
        $scope.prizeHongbaoList = prizeHongbaoList;
        $scope.przieRelList = przieRelList;
        $scope.prizeExchangeList = prizeExchangeList;
        $scope.prizeList = prizeList;
    })
</script>