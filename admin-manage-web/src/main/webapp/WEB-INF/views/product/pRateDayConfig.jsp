<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,common"/>
    <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.min.js"></script>
    <!--  -->
</head>
<body>
<div ng-app="rateFormApp" ng-controller="rateFormCtrl">

    <form  class="form-horizontal" id="rateForm" >
        <div class="control-group">
            <label class="control-label"><h5>借款利率对应天数配置</h5></label>
        </div>
        <div class="control-group" ng-repeat="x in rateDay">
            <label class="control-label col-sm-1">利率{{x.dayRate}}：</label>
            <input type="text" class="form-control valid checkTerm" descripe="请填写借款利率对应天数配置"  ng-model="x.borrowDays"  maxlength="12" value="" onkeyup=this.value=this.value.replace(/[^\d\,]/g,'')></input>
        </div>
        <div class="control-group">
            <label class="control-label"><h5>续借配置</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">允许续借天数（天）：</label>
            <input type="text" class="form-control"  ng-model="loan.renewalDay"  maxlength="3" value="" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">可续借日期限制（天）：</label>
            <input type="text" class="form-control"   ng-model="loan.canRenewalDayLimit"  maxlength="2" value="" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
        </div>
        <div class="control-group">
            <label class="control-label col-sm-1">最低续借金额（元）：</label>
            <input type="text" class="form-control valid" descripe="请填写续借最低续借金额"  ng-model="loan.renewalAmount"  maxlength="4" value="" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
        </div>
        <div class="control-group" ng-repeat="x in renewalConfig">
            <label class="control-label col-sm-1">{{x.configName}}：</label>
            <input type="text" class="form-control valid renewalConfig" descripe="请填写续借最低本金比例"  ng-model="x.renewalCapitalRate"  maxlength="4" value="" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"></input>
        </div>
    </form>
    <div class="form-actions">
        <shiro:hasPermission name="product:list:edit">
            <input id="btnSubmit" class="btn btn-primary" ng-click="save()"  value="保 存" style="width: 50px;"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.parent.location.href='${ctxA}/product/pList'"/>
    </div>
</div>
</body>
<script>
    var consumeLoanConfig = '${consumeLoanConfig}';
    if( consumeLoanConfig !=''){
        loan = eval("("+consumeLoanConfig+")");
    }
    var rateDays = '${rateDays}';
    if( rateDays != ''){
        rateDay = eval("("+rateDays+")");
    }
    var renewalConfigs = '${renewalConfigs}';
    if( renewalConfigs != ''){
        renewalConfig = eval("("+renewalConfigs+")");
    }
    //创建应用
    var formApp = angular.module('rateFormApp', []);
    //创建controller
    formApp.controller('rateFormCtrl', function($scope, $http) {
        $scope.loan = loan;
        $scope.rateDay = rateDay;
        $scope.renewalConfig = renewalConfig;
        $scope.save = function() {
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
            if( bool){
                $(".renewalConfig").each(function() {
                    if( !is01($(this).val()) ){
                        top.layer.alert("续借本金比例格式不正确", {icon: 5});
                        bool = false;
                        return false;
                    }
                })
            }
            if( bool){
                $(".checkTerm").each(function() {
                    if( !checkTerm($(this).val()) ){
                        top.layer.alert("借款利率对应天数格式不正确", {icon: 5});
                        bool = false;
                        return false;
                    }
                })
            }
            if( bool){
                if( !isNumber($scope.loan.renewalDay)){
                    top.layer.alert("请填写正确允许续借天数", {icon: 5});
                    return false;
                }
                if( !isNumber($scope.loan.canRenewalDayLimit)){
                    top.layer.alert("请填写正确日期限制", {icon: 5});
                    return false;
                }
                if( !is01($scope.loan.renewalAmount)){
                    top.layer.alert("请填写正确最低续借金额", {icon: 5});
                    return false;
                }
                $("#btnSubmit").attr("disabled",true);
                $http({
                    method:'post',
                    url:"rateDay/save",
                    data:{'loan':JSON.stringify($scope.loan),'rateDays':JSON.stringify($scope.rateDay),'renewalConfigs':JSON.stringify($scope.renewalConfig)},
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
        }
    });

    function checkTerm(input) {
        if( input == null || input ==''){
            return false;
        }
        var arr = input.split(",");
        for(s in arr){
            if(!isNumber(arr[s])){
                return false;
            }
        }
        return true;
    }
</script>
</html>