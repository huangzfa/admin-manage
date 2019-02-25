<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!--  关联轮播model -->
<div id="loanOperateModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"style="width: 750px;">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header" style="height: 30px;">
                <h4 class="modal-title text-muted" style="float: left;">操作记录</h4>
            </div>

            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                        <th>时间</th>
                        <th>操作人账号</th>
                        <th>页面位置</th>
                        <th>操作</th>
                    </thead>
                    <tbody id="rows" >

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" onclick="loanOperateModal.close()">关闭</button>
            </div>
        </div>
    </div>
</div>
<script>

    var loanOperateModal={};
    //控件初始化star
    var pageId = null;
    var type = null;
    loanOperateModal.open = function(optional){
        //如果有参数传进来，可以此处赋值
        if(optional.callback){
            loanOperateModal.callback = optional.callback;
            pageId = optional.pageId;
            type = optional.type;
        }
        loanOperateModal.getOperaLogList();
        $("#loanOperateModal").modal({});
    }
    //打印log
    loanOperateModal.callback = function(data){
        console.log(data);
    };
    //log查询
    loanOperateModal.getOperaLogList=function (){
        $("#loanOperateModal  #rows").children().html('');
        jQuery.post("${ctxA}/loan/operatLog", {'pageId':pageId},function(result) {
            for(j = 0,len=result.list.length; j < len; j++) {
                var log = result.list[j];
                $("#loanOperateModal #rows").append("<tr>"
                    +"<td>"+log.addTime+"</td>"
                    +"<td>"+log.addOperatorName+"</td>"
                    +"<td>"+type+"</td>"
                    +"<td>"+log.log+"</td>"
                    +"</tr>")
            }
            return;
        },"json");
    }

    loanOperateModal.close=function () {
        $("#loanOperateModal").modal("hide");
    }
</script>
