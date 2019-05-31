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
<jsp:include page="/WEB-INF/include/goodsDialog.jsp"/>
<div ng-app="loanFormApp" ng-controller="loanFormCtrl">
    <form  class="form-horizontal" id="loanForm" >
        <input type="hidden" ng-model="loan.id">
        <div class="control-group">
            <label class="control-label"><h5>借贷产品</h5></label>
        </div>
        <div class="control-group">
            <label class="control-label"><a href="javascript:void(0)" ng-click="selectGoods()" class="btn btn-primary">选择产品</a></label>
        </div>
        <div class="control-group">
            <div class="controls">
                <table class="table table-hover" >
                    <thead>
                        <th>商品编码</th>
                        <th>商品名称</th>
                        <th>排序</th>
                        <th>展示状态</th>
                        <th>操作</th>
                    </thead>
                    <tbody>
                    <tr ng-repeat="x in goods">
                        <td>{{x.goodsNo}}</td>
                        <td>{{x.goodsName}}</td>
                        <td><input type="text" descripe="排序只能输入正整数" ng-model="x.sort" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'></td>
                        <td>{{x.state==0?'下架':'上架'}}</td>
                        <td><button id="deleteGoods" class="btn btn-warning"  ng-click="deleteGoods('{{x.id}}')" >删除</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <form  class="form-horizontal" id="channelForm" >
            <div class="control-group">
                <label class="control-label"><h5>渠道和风控配置</h5></label>
            </div>
            <div class="control-group">
                <label class="control-label col-sm-1">额度风控场景编码：</label>
                <input type="text" class="form-control valid" descripe="请填写额度风控场景编码" id="quotaSceneCode" ng-model="loan.quotaSceneCode"  maxlength="32" value="" ></input>
                <a href="javascript:void(0)" ng-click="validSceneId('quotaSceneCode')">校验场景id</a>
            </div>
            <div class="control-group">
                <label class="control-label col-sm-1">借款风控场景编码-首次新用户：</label>
                <input type="text" class="form-control valid" descripe="请填写借款风控场景编码-首次新用户" id="borrowSceneCodeFirst" ng-model="loan.borrowSceneCodeFirst" maxlength="32" value="" ></input>
                <a href="javascript:void(0)" ng-click="validSceneId('borrowSceneCodeFirst')">校验场景id</a>
            </div>
            <div class="control-group">
                <label class="control-label col-sm-1">借款风控场景编码-非首次老用户：</label>
                <input type="text" class="form-control valid" descripe="请填写借款风控场景编码-非首次老用户" id="borrowSceneCode" ng-model="loan.borrowSceneCode"maxlength="32" value="" ></input>
                <a href="javascript:void(0)" ng-click="validSceneId('borrowSceneCode')">校验场景id</a>
            </div>

        </form>
    </form>
    <div class="form-actions">
        <shiro:hasPermission name="product:list:edit">
            <input id="btnSubmit" class="btn btn-primary" ng-click="save()" value="保 存" ng-disabled="{{btnState}}" style="width: 50px;"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.parent.location.href='${ctxA}/product/pList'"/>
    </div>
</div>
</body>
<script>
        //借贷配置
        var consumeLoanConfig = '${consumeLoanConfig}';
        if( consumeLoanConfig !=''){
            loan = eval("("+consumeLoanConfig+")");
        }
        //产品商品关联数据
        var productGoods = '${productGoods}';
        if( productGoods !=''){
            goods = eval("("+productGoods+")");
        }
        //创建应用
        var loanFormApp = angular.module('loanFormApp', []);
        //创建controller
        loanFormApp.controller('loanFormCtrl', function($scope, $http) {
            $scope.loan = loan;
            $scope.goods = goods;
            $scope.btnState = false;
            $scope.deleteGoods = function(id) {
                //删除此条记录
                $scope.goods.splice($.inArray(id, $scope.goods), 1);
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
                    $scope.btnState = true;
                    $http({
                        method:'post',
                        url:"loan/save",
                        data:{'loan':JSON.stringify($scope.loan),'goods':JSON.stringify($scope.goods)},
                        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                        transformRequest: function(obj) {
                            var str = [];
                            for (var p in obj) {
                                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                            }
                            return str.join("&");
                        }
                    }).success(function(result){
                        $scope.btnState = false;
                        if (result.code == 1) {
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
            $scope.validSceneId = function (code) {
                $http({
                    method:'post',
                    url:"validSceneCode",
                    data:{'productId':$scope.loan.productId,'code':$("#"+code).val()},
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    transformRequest: function(obj) {
                        var str = [];
                        for (var p in obj) {
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                }).success(function(result){
                    $scope.btnState = false;
                    if (result.code == 1) {
                        top.layer.alert("验证成功", {
                            icon: 6,
                            end: function(){
                                window.location.reload();
                            }
                        });
                    } else {
                        top.layer.alert(result.msg, {icon: 5});
                    }
                })
            };
            $scope.selectGoods = function () {
                modalGoods.open({
                    productId: $scope.loan.productId,
                    callback: function (data) {
                        for( var  i = 0; i < data.list.length ; i++){
                            if( getIndex(data.list[i].goodsId,$scope) ==-1){
                                //$apply()用于传播模型的变化。在外部改变了作用域，如果想显示改变后的值，必须调用$apply。
                                $scope.goods.push(data.list[i]);
                                $scope.$apply();//刷新数据
                            }
                        }
                        $("#modalGoodsDialog").modal("hide");
                    }
                });
            }
        });

        //判断是否已经存在了该配置项
        function getIndex(goodsId,scope) {
            var index = -1;
            for(var i=0;i<scope.goods.length;i++){
                if(scope.goods[i].goodsId==goodsId){
                    index = i;
                    return false;
                }
            }
            return index;
        }
</script>
</html>

