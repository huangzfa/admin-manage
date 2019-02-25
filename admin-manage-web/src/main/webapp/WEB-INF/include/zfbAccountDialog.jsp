<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!--  关联轮播model -->
<div id="modalZfbAccount" class="modal fade modalZfbAccount" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header" style="height: 30px;">
                <h4 class="modal-title text-muted" style="float: left;">添加支付宝</h4>
            </div>
            <form class="bs-example bs-example-form  zfbDialog" role="form" action="${ctxA}/product/zfbAccount/getList">
                <div class="input-group" style="margin-left: 14px;margin-bottom: -24px;">
                    <input type="text" class="form-control" name="accountName" id="accountName" placeholder="请输入支付宝名称" style="margin-top: 5px;">
                    <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" onclick="modalZfbAccount.getZfbAccountList()">查询</button>
                            </span>
                </div><!-- /input-group -->
            </form>
            <div class="modal-body duobei-product">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th width="100px">支付宝账号名称</th>
                        <th>是否可用</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="rows">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="modalZfbAccount.save()">确定</button>
                <button class="btn btn-default" type="button" onclick="modalZfbAccount.close()">取消</button>
            </div>
        </div>
    </div>
</div>
<script>
    //禁用回车事件
    $(".zfbDialog input[name='accountName']").bind('keypress',function(event){
        if(event.keyCode == "13")
        {
            return false;
        }
    });
    var modalZfbAccount={};
    var zfbAccountId = null;
    var name = null;
    //控件初始化star
    modalZfbAccount.open = function(optional){
        zfbAccountId =null;
        name = null;
        $(".zfbDialog input[name='accountName']").val("");
        modalZfbAccount.getZfbAccountList();
        //如果有参数传进来，可以此处赋值
        if(optional.callback){
            modalZfbAccount.callback = optional.callback;
        }
        $("#modalZfbAccount").modal({});
    }
    //打印log
    modalZfbAccount.callback = function(data){
        console.log(data);
    };
    //banner查询
    modalZfbAccount.getZfbAccountList=function (){
        $("#modalZfbAccount .table #rows").children().html('');
        var form = $('.zfbDialog');
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action, data,function(result) {
            for(j = 0,len=result.list.length; j < len; j++) {
                var zfb = result.list[j];
                var disabled = (zfb.accountState == 0?"disabled":"");
                $("#modalZfbAccount .table #rows").append("<tr>"
                    +"<td>"+zfb.name+"</td>"
                    +"<td>"+(zfb.accountState==1?"是":"否")+"</td>"
                    +"<td><input type='checkbox' name = "+zfb.name+" zfbAccountId='"+zfb.id+"'  "+disabled+" onclick='modalZfbAccount.checkBox(this)'></td>"
                    +"</tr>");
            }
            return;
        },"json");
    }
    //checkbox按钮事件
    modalZfbAccount.checkBox=function(obj){
        if( $(obj).prop("checked") == true){
            $("#modalZfbAccount  input[type='checkbox']").removeAttr('checked');
            $(obj).prop('checked',true);
        }

    }

    //保存回调
    modalZfbAccount.save=function(){
        $("#modalZfbAccount  input[type='checkbox']").each(function () {
            if( $(this).prop("checked") == true){
                zfbAccountId = $(this).attr("zfbAccountId");
                name = $(this).attr("name");
            }
        })
        if( zfbAccountId == null ){
            top.layer.alert("请选择一个支付宝账号", {icon: 5});
            return false;
        }
        modalZfbAccount.callback({'zfbAccountId':zfbAccountId,'name':name});
    }
    modalZfbAccount.close=function () {
        $("#modalZfbAccount").modal("hide");
    }
</script>

