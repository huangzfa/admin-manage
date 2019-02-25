<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>

<!--  关联轮播model -->
<div id="modalAuthConfig2" class="modal fade modalAuthConfig2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header" style="height: 30px;">
                <h4 class="modal-title text-muted" style="float: left;">添加备选认证</h4>
            </div>
            <div class="modal-body duobei-product">
                <table class="table table-bordered">
                    <thead>
                        <tr >
                            <th>认证项名称</th>
                            <th>是否可用</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="rows" >

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="modalAuthConfig2.save()">确定</button>
                <button class="btn btn-default" type="button" onclick="modalAuthConfig2.close()">取消</button>
            </div>
        </div>
    </div>
</div>
<script>
    var modalAuthConfig2={};
    var configLists = [];
    //控件初始化star
    modalAuthConfig2.open = function(optional){
        configLists = [];
        //如果有参数传进来，可以此处赋值
        if(optional.callback){
            modalAuthConfig2.callback = optional.callback;
            configLists = optional.configLists;
        }
        modalAuthConfig2.getAuthConfigList();
        $("#modalAuthConfig2").modal({});
    }
    //打印log
    modalAuthConfig2.callback = function(data){
        console.log(data);
    };
    //banner查询
    modalAuthConfig2.getAuthConfigList=function (){
        $("#modalAuthConfig2 .table #rows").children().html('');
        for(j = 0,len=configLists.length; j < len; j++) {
            var auth = configLists[j];
            var disabled = "";
            if(auth.authState == 0 || auth.checked=="checked"){
                disabled = "disabled";
            }
            $("#modalAuthConfig2 .table #rows").append("<tr align='center'>"
                +"<td width='30%' >"+auth.authName+"</td>"
                +"<td >"+(auth.authState==1?"是":"否")+"</td>"
                +"<td ><input type='checkbox' "+auth.checked+" "+disabled+"  authName='"+auth.authName+"'  authCode='"+auth.authCode+"' ></td>"
                +"</tr>")
        }
    }

    //保存回调
    modalAuthConfig2.save=function(){
        var list = [];
        $("#modalAuthConfig2 .table input[type=checkbox]").each(function(){
            if(!$(this).prop("disabled") && $(this).attr("checked")){
                var object = {
                    authCode:$(this).attr("authCode"),
                    authName:$(this).attr("authName")
                }
                list.push(object);
            }
        })
        modalAuthConfig2.callback({'list':list});
    }
    modalAuthConfig2.close=function () {
        $("#modalAuthConfig2").modal("hide");
    }
</script>
