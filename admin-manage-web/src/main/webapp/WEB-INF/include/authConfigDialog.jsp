<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!--  关联轮播model -->
<div id="modalAuthConfig" class="modal fade modalAuthConfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header" style="height: 30px;">
                <h4 class="modal-title text-muted" style="float: left;">添加补充认证</h4>
            </div>
            <form class="bs-example bs-example-form authConfigDialog" role="form" action="${ctxA}/product/authConfig/getList">
                <div class="input-group" style="margin-left: 14px;margin-bottom: -24px;">
                    <input type="hidden" name="productId" id="productId" value="">
                    <input type="text" class="form-control" name="authName" id="authName" placeholder="请输入认证项名称" style="margin-top: 5px;">
                    <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" onclick="modalAuthConfig.getAuthConfigList()">查询</button>
                            </span>
                </div>
            </form>
            <div class="modal-body duobei-product">
                <table class="table table-bordered">
                    <thead>
                        <th >认证项名称</th>
                        <th>是否可用</th>
                        <th>操作</th>
                    </thead>
                    <tbody id="rows" >

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="modalAuthConfig.save()">确定</button>
                <button class="btn btn-default" type="button" onclick="modalAuthConfig.close()">取消</button>
            </div>
        </div>
    </div>
</div>
<script>
    //禁用回车事件
    $("#modalAuthConfig input[name='authName']").bind('keypress',function(event){
        if(event.keyCode == "13")
        {
            return false;
        }
    });
    var modalAuthConfig={};
    var list = [];
    //控件初始化star
    modalAuthConfig.open = function(optional){
        list = [];
        $(".authConfigDialog input[name='authName']").val("");
        //如果有参数传进来，可以此处赋值
        if(optional.callback){
            modalAuthConfig.callback = optional.callback;
            $("#modalAuthConfig .authConfigDialog #productId").val(optional.productId);
        }
        modalAuthConfig.getAuthConfigList();
        $("#modalAuthConfig").modal({});
    }
    //打印log
    modalAuthConfig.callback = function(data){
        console.log(data);
    };
    //banner查询
    modalAuthConfig.getAuthConfigList=function (){
        $("#modalAuthConfig  #rows").children().html('');
        var form = $('#modalAuthConfig .authConfigDialog');
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action, data,function(result) {
            for(j = 0,len=result.list.length; j < len; j++) {
                var auth = result.list[j];
                var disabled = "";
                if(auth.authState == 0 || auth.checked=="checked"){
                    disabled = "disabled";
                }
                $("#modalAuthConfig #rows").append("<tr>"
                    +"<td width='30%'>"+auth.authName+"</td>"
                    +"<td>"+(auth.authState==1?"是":"否")+"</td>"
                    +"<td><input type='checkbox' "+auth.checked+" "+disabled+" authName='"+auth.authName+"' authId='"+auth.id+"' authCode='"+auth.authCode+"' authState='"+auth.authState+"'  ></td>"
                    +"</tr>")
            }
            return;
        },"json");
    }

    //保存回调
    modalAuthConfig.save=function(){
        var list = [];
        $("#modalAuthConfig .table #rows input[type=checkbox]").each(function(){
            if(!$(this).prop("disabled") && $(this).attr("checked")){
                var object = {
                    authId:$(this).attr("authId"),
                    authCode:$(this).attr("authCode"),
                    authState:$(this).attr("authState"),
                    authName:$(this).attr("authName")
                }
                list.push(object);
            }
        })
        modalAuthConfig.callback({'list':list});
    }
    modalAuthConfig.close=function () {
        $("#modalAuthConfig").modal("hide");
    }
</script>
