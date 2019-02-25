<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!--  关联轮播model -->
<div id="modalChannel" class="modal fade modalChannel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header" style="height: 30px;">
                <h4 class="modal-title text-muted" style="float: left;">添加新渠道</h4>
            </div>
            <form class="bs-example bs-example-form  channelDialog" role="form" action="${ctxA}/product/channel/getList">
                <div class="input-group" style="margin-left: 14px;margin-bottom: -24px;">
                    <input type="hidden" name="productId" id="productId" value="">
                    <input type="text" class="form-control" name="channelName" id="channelName" placeholder="请输入渠道名称" style="margin-top: 5px;">
                    <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" onclick="modalChannel.getChannelList()">查询</button>
                            </span>
                </div><!-- /input-group -->
            </form>
            <div class="modal-body duobei-product">
                <table class="table table-bordered">

                    <thead>
                    <tr>
                        <th >渠道名称</th>
                        <th>渠道</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="rows">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="modalChannel.save()">确定</button>
                <button class="btn btn-default" type="button" onclick="modalChannel.close()">取消</button>
            </div>
        </div>
    </div>
</div>
<script>
    //禁用回车事件
    $(".channelDialog input[name='channelName']").bind('keypress',function(event){
        if(event.keyCode == "13")
        {
            return false;
        }
    });
    var modalChannel={};
    var list = [];
    //控件初始化star
    modalChannel.open = function(optional){
        list = [];
        $(".channelDialog input[name='channelName']").val("");
        //如果有参数传进来，可以此处赋值
        if(optional.callback){
            modalChannel.callback = optional.callback;
            $(".channelDialog #productId").val(optional.productId);
        }
        modalChannel.getChannelList();
        $("#modalChannel").modal({});
    }
    //打印log
    modalChannel.callback = function(data){
        console.log(data);
    };
    //banner查询
    modalChannel.getChannelList=function (){
        $("#modalChannel .table #rows").children().html('');
        var form = $('.channelDialog');
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action, data,function(result) {
            for(j = 0,len=result.list.length; j < len; j++) {
                var channel = result.list[j];
                var disabled = "";
                if(channel.channelState == 0 || channel.checked=="checked"){
                    disabled = "disabled";
                }
                $("#modalChannel .table #rows").append("<tr>"
                    +"<td>"+channel.name+"</td>"
                    +"<td>"+channel.channelCode+"</td>"
                    +"<td><input type='checkbox' "+channel.checked+" "+disabled+" channelName='"+channel.name+"' channelCode='"+channel.channelCode+"'></td>"
                    +"</tr>")
            }
            return;
        },"json");
    }

    //保存回调
    modalChannel.save=function(){
        $("#modalChannel .table input[type=checkbox]").each(function(){
            if(!$(this).prop("disabled") && $(this).attr("checked")){
                var object = {
                    channelCode:$(this).attr("channelCode"),
                    channelName:$(this).attr("channelName")
                }
                list.push(object);
            }
        });
        modalChannel.callback({'list':list});
    }
    modalChannel.close=function () {
        $("#modalChannel").modal("hide");
    }
</script>

