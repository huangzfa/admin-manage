<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<style type="text/css">
    .duobei-product table th, .duobei-product table td{
        padding:3px;
    }
</style>
<!--  关联轮播model -->
<div id="modalGoodsDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header" style="height: 30px;">
                <h4 class="modal-title text-muted" style="float: left;">添加补充认证</h4>
            </div>
            <form class="bs-example bs-example-form modalGoodsForm" role="form" action="${ctxA}/goods/getList">
                <input type="hidden" name="productId" id="productId" value="">
            </form>
            <div class="modal-body duobei-product">
                <table class="table table-bordered">
                    <thead>
                        <td></td>
                        <th >商品编码</th>
                        <th >商品名称</th>
                        <th>商品图片</th>
                    </thead>
                    <tbody id="rows" >

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="modalGoods.save()">确定</button>
                <button class="btn btn-default" type="button" onclick="modalGoods.close()">取消</button>
            </div>
        </div>
    </div>
</div>
<script>
    var modalGoods={};
    var list = [];
    //控件初始化star
    modalGoods.open = function(optional){
        list = [];
        //如果有参数传进来，可以此处赋值
        if(optional.callback){
            modalGoods.callback = optional.callback;
            $(".modalGoodsForm  #productId").val(optional.productId);
        }
        modalGoods.getGoodsList();
        $("#modalGoodsDialog").modal({});
    }
    //打印log
    modalGoods.callback = function(data){
        console.log(data);
    };
    //banner查询
    modalGoods.getGoodsList=function (){
        $("#modalGoodsDialog  #rows").children().html('');
        var form = $('.modalGoodsForm');
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action, data,function(result) {
            for(j = 0,len=result.list.length; j < len; j++) {
                var goods = result.list[j];
                var disabled = "";
                if(goods.state == 0 || goods.checked=="checked"){
                    disabled = "disabled";
                }
                $("#modalGoodsDialog #rows").append("<tr>"
                    +"<td><input type='checkbox' state="+goods.state+" "+goods.checked+" "+disabled+" goodsId='"+goods.id+"' goodsName='"+goods.goodsName+"' goodsNo='"+goods.goodsNo+"' sort='"+goods.sort+"'  ></td>"
                    +"<td width='30%'>"+goods.goodsNo+"</td>"
                    +"<td width='30%'>"+goods.goodsName+"</td>"
                    +"<td><img src='"+goods.thumbnailIcon+"' style='height: 33px'></td>"
                    +"</tr>")
            }
            return;
        },"json");
    }

    //保存回调
    modalGoods.save=function(){
        var list = [];
        $("#modalGoodsDialog .table #rows input[type=checkbox]").each(function(){
            if($(this).prop("checked")){
                var object = {
                    goodsId:$(this).attr("goodsId"),
                    goodsName:$(this).attr("goodsName"),
                    goodsNo:$(this).attr("goodsNo"),
                    state:$(this).attr("goodsName"),
                    sort:$(this).attr("sort"),
                    productId:$("#modalGoodsDialog #productId").val()
                }
                list.push(object);
            }
        })
        modalGoods.callback({'list':list});
    }
    modalGoods.close=function () {
        $("#modalGoodsDialog").modal("hide");
    }
</script>
