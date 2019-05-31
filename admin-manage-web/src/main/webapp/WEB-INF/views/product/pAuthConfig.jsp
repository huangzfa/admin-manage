<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,angular,common"/>
    <!--  -->
</head>
<body>
<jsp:include page="/WEB-INF/include/authConfigDialog.jsp"/>
<div ng-app="formApp" ng-controller="formCtrl">
    <form  class="form-horizontal" id="authForm" >
        <input type="hidden" value="loan.id">
        <div class="control-group">
            <label class="control-label"><h5>借贷基本配置</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label">每日放款金额：</label>
            <div class="controls">
                <input type="text" class="form-control valid" ng-model="loan.dayAmountLimit" descripe="请填写每日放款金额" maxlength="8"  onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">借款额度范围：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写借款额度范围" ng-model="loan.showMinAmount" maxlength="8" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
                <input type="text" class="form-control valid" descripe="请填写借款额度范围" ng-model="loan.showMaxAmount" maxlength="8"  onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></input>
            </div>
        </div>
        <%--<div class="control-group">
            <label class="control-label">央行基准年化利率：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写央行基准年化利率" ng-model="loan.baseBankRate" maxlength="8"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"></input>
            </div>
        </div>--%>
        <div class="control-group">
            <label class="control-label">手续费率（日）：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写手续费率" ng-model="loan.poundageRate" maxlength="8"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">逾期手续费率（日）：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写手续费率" ng-model="loan.overdueRate" maxlength="8"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"></input>
            </div>
        </div>
    </form>
    <form  class="form-horizontal" id="basicAuthForm">
        <div class="control-group">
            <label class="control-label"><h5>认证项配置</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label"><a href="javascript:void(0)" ng-click="selectAuthConfig()" class="btn btn-primary">添加认证</a></label>
        </div>
        <div class="control-group">
            <div class="controls">
                <table class="table table-hover" >
                    <thead>
                    <th>认证项名称</th>
                    <th>是否可用</th>
                    <th>是否必须认证</th>
                    <th>认证有效期</th>
                    <th>认证顺序</th>
                    </thead>
                    <tbody>
                            <tr ng-repeat="x in auths">
                                <td>{{x.authName}}</td>
                                <td>{{x.isEnable == 1?'可用':'不可用'}}</td>
                                <td>
                                    <input type="checkbox" ng-model="x.isRequired" ng-checked="{{x.isRequired==1}}" ng-disabled="{{x.isEnable ==0}}"  ng-true-value="1" ng-false-value="0"/>
                                </td>
                                <td><input type="text" descripe="有效期只能为正整数" ng-model="x.validVal" ng-disabled="{{x.isEnable ==0}}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'>{{x.validUnit==0?'小时':'天'}}</td>
                                <td><input type="text" descripe="排序只能为正整数" style="background-color:${auth.authState==0?'#e5e5e5':''}" ng-model="x.authSort" ng-disabled="{{x.isEnable ==0}}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></td>
                                <td><button id="deleteGoods" class="btn btn-warning"  ng-click="deleteGoods('{{x.id}}')" >删除</button></td>

                            </tr>
                    </tbody>
                </table>
            </div>
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
    var authConfigs = '${authConfigs}';
    var consumeLoanConfig = '${consumeLoanConfig}';
    if( authConfigs != ''){
        auths = eval("("+authConfigs+")");
    }
    if( consumeLoanConfig !=''){
        loan = eval("("+consumeLoanConfig+")");
    }
    //创建应用
    var formApp = angular.module('formApp', []);
    //创建controller
    formApp.controller('formCtrl', function($scope, $http) {
        $scope.loan = loan;
        $scope.auths = auths;
        $scope.deleteGoods = function(id) {
            //删除此条记录
            $scope.auths.splice($.inArray(id, $scope.auths), 1);
        };
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
            $("table input[type='text']").each(function() {
                var descripe  = $(this).attr("descripe");
                if( !isNumber($(this).val())){
                    top.layer.alert(descripe, {icon: 5});
                    bool = false;
                    return false;
                }
            })
            if( bool){
                if( !isNumber($scope.loan.dayAmountLimit)){
                    top.layer.alert("请填写正确放款金额", {icon: 5});
                    return false;
                }
                if( !isNumber($scope.loan.showMinAmount) || !isNumber($scope.loan.showMaxAmount)){
                    top.layer.alert("请填写正确借款额度范围", {icon: 5});
                    return false;
                }
                if( !is01($scope.loan.baseBankRate)){
                    top.layer.alert("请填写正确央行基准年化利率", {icon: 5});
                    return false;
                }
                if( !is01($scope.loan.poundageRate)){
                    top.layer.alert("请填写正确手续费率", {icon: 5});
                    return false;
                }
                if( !is01($scope.loan.overdueRate)){
                    top.layer.alert("请填写正确逾期手续费率", {icon: 5});
                    return false;
                }
                $("#btnSubmit").attr("disabled",true);
                $http({
                    method:'post',
                    url:"auth/save",
                    data:{'loan':JSON.stringify($scope.loan),'auths':JSON.stringify($scope.auths)},
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
        $scope.selectAuthConfig = function () {
            modalAuthConfig.open({
                productId: $scope.loan.productId,
                callback: function (data) {
                    for( var  i = 0; i < data.list.length ; i++){
                        if( getIndex(data.list[i].authId,$scope.auths) ==-1){
                            $scope.auths.push(data.list[i]);
                            $scope.$apply();//刷新数据
                        }
                    }

                    $("#modalAuthConfig").modal("hide");
                }
            });
        }
    });


    //判断是否已经存在了该配置项
    function getIndex(authId,auths) {
        var index = -1;
        for(var i=0;i<auths.length;i++){
            if(auths[i].authId==authId){
                index =  i;
                return false;
            }
        }
        return index;
    }
</script>

</html>

