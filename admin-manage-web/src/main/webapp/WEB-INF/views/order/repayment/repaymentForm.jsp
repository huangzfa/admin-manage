<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
    <!--  -->
    <style type="text/css">
    </style>
    <script type="text/javascript">


      $(function(){
     /*   $("#inputForm").validate({
          apppages: {
            menuName:{
              required:true,
            },
            pageTemplet:{
              required:true
            },
            menuSort:{
              required:true
            },
            iconUrl:{
              required:true
            },
            menuVal:{
              required:true
            },
            selectIconUrl:{
              required:true
            }
          },
          messages: {
            menuName:{required : "必填信息"},
            pageTemplet:{required : "必填信息"},
            menuSort:{required : "必填信息"},
            iconUrl:{required : "必填信息"},
            menuVal:{required : "必填信息"},
            selectIconUrl:{required : "必填信息"}
          },
          submitHandler: function(form){
            loading('正在提交，请稍等...');
            form.submit();
          },
          errorPlacement: function(error, element) {
            if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
              error.appendTo(element.parent().parent());
            } else {
              error.insertAfter(element);
            }
          }
        });*/
      });
    </script>
</head>
<style>
     .info_form_name{
        background-color:#e5e5e5;
    }
    table.dataintable {
        margin-left: 2%;
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width:96%;

    }
    table.dataintable th {

        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.dataintable td {
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        width: 3%;
    }
    table.bill_table {
        margin-left: 17%;
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;

    }
    table.bill_table th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.bill_table td {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        width: 5%;
    }

    .bill_list_th{
        background-color:#e5e5e5;
        vertical-align:text-top;
        border:1px solid #aaa;
        width: 5%;
    }
    .update{
        padding: 9px 15px;
        font-size: 12px;
        border-radius: 3px;
        display: inline-block;
        line-height: 1;
        white-space: nowrap;
        cursor: pointer;
        background: #409EFF;
        border: 1px solid #dcdfe6;
        color: #fff;
        -webkit-appearance: none;
        text-align: center;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        outline: 0;
        margin: 0;
        -webkit-transition: .1s;
        transition: .1s;
        font-weight: 500;
        padding: 12px 20px;
        font-size: 14px;
        border-radius: 4px;
    }
    h4{
        margin-top: 2%;
        margin-bottom: 1%;
    }
    .imgPic{
        width:100px;
        height:100px;
        display:block;
        float:left;
    }
    .thumbImgBox{
        position:relative;
        padding: 2px;
        border: 1px solid #E8E2D9;
        float: left;
        margin-right: 15px;
        line-height:100px;
    }
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/order/borrow/list?productId=${userInfo.product.id}">借款订单列表</a></li>
</ul>
<div class="si-warp">
    <br/>
   <%-- <sys:message content="${message}"/>--%>
  <%--  <form:form id="inputForm" modelAttribute="stageBorrow" action="${ctxA}/ac/apppage/save" method="post" class="form-horizontal">
        <form:hidden path="id" />--%>
     <div class="control-group">
         <div class="user_info" style="margin:0 auto">
          <h4>借款人信息</h4>
                <table class="dataintable" style="width: 50%">
                    <thead class="info_form_name">
                        <th>user_id</th>
                        <th>用户唯一标识</th>
                        <th>产品</th>
                        <th>应用</th>
                        <th>用户姓名</th>
                        <th>注册手机号</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${userInfo.id}</td>
                        <td>${userInfo.globalUserId}</td>
                        <td>${userInfo.product.productName}</td>
                        <td>${userInfo.app.appName}</td>
                        <td>${userInfo.realName}</td>
                        <td>${userInfo.userName}</td>
                    </tr>
                    </tbody>
                </table>
                <%--<form:input path="menuName" htmlEscape="false" maxlength="50" class="input-xlarge"/>--%>
            </div>
            <div class="borrow_info" style="margin:0 auto">
            <h4>借款信息</h4>
                <table class="dataintable">
                    <thead class="info_form_name">
                    <th>服务订单号</th>
                    <th>借款金额</th>
                    <th>天数</th>
                    <th>交易服务费</th>
                    <th>优惠券抵扣</th>
                    <th>到账金额</th>
                    <th>放款结果</th>
                    <th>关闭原因</th>
                    <th>申请时间</th>
                    <th>借款地点</th>
                    <th>逾期天数</th>
                    <th>逾期费</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${borrowCash.borrowNo}</td>
                        <td>${borrowCash.amount}</td>
                        <td>${borrowCash.borrowDays}</td>
                        <td>${borrowCash.poundage}</td>
                        <td>${borrowCash.couponAmount}</td>
                        <td>${borrowCash.arrivalAmount}</td>
                        <td>${cfg:getDictLabel('borrowState',borrowCash.borrowState)}</td>
                        <td>${borrowCash.closeReason}</td>
                        <td><fmt:formatDate value="${borrowCash.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${borrowCash.province}${borrowCash.city}${borrowCash.county}${borrowCash.address}</td>
                        <td>${borrowCash.overdueDay}</td>
                        <td>${borrowCash.overdueAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
         <div class="consumdebt_order_info" style="margin:0 auto">
             <h4>借贷商品订单信息</h4>
             <table class="dataintable">
                 <thead class="info_form_name">
                 <th>借贷商品订单号</th>
                 <th>订单状态</th>
                 <th>支付状态</th>
                 <th>商品名称</th>
                 <th>价格</th>
                 <th>优惠后价格</th>
                 <th>收货人</th>
                 <th>联系电话</th>
                 <th>收货地址</th>
                 <th>发货物流公司</th>
                 <th>发货物流单号</th>
                 <th>发货时间</th>
                 <th>关闭时间</th>
                 <th>关闭原因</th>
                 </thead>
                 <tbody>
                 <tr>
                     <td>${consumdebtOrder.orderNo}</td>
                     <td>${cfg:getDictLabel('consumdebtOrderState',consumdebtOrder.state)}</td>
                     <td>${cfg:getDictLabel('payState',consumdebtOrder.payState)}</td>
                     <td>${consumdebtOrder.goodsName}</td>
                     <td>${consumdebtOrder.priceAmount}</td>
                     <td>${consumdebtOrder.saleAmount}</td>
                     <td>${consumdebtOrder.consignee}</td>
                     <td>${consumdebtOrder.consigneeMobile}</td>
                     <td>${consumdebtOrder.address}</td>
                     <td>${consumdebtOrder.logisticsCompany}</td>
                     <td>${consumdebtOrder.logisticsNo}</td>
                     <td><fmt:formatDate value="${consumdebtOrder.gmtDeliver}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                     <td><fmt:formatDate value="${consumdebtOrder.gmtClosed}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                     <td>${consumdebtOrder.closedReason}</td>
                 </tr>
                 </tbody>

             </table>
         </div>

         <div class="approve_info" style="margin:0 auto">
             <h4>审核信息</h4>
             <table class="dataintable" style="width: 50%">
                 <thead class="info_form_name">
                 <th>审核类型</th>
                 <th>审核编号</th>
                 <th>审核结果</th>
                 <th>审核说明</th>
                 <th>审核人</th>
                 <th>审核时间</th>
                 </thead>

             </table>
             <%--<form:input path="menuName" htmlEscape="false" maxlength="50" class="input-xlarge"/>--%>
         </div>
        </div>


        <div class="form-actions" style="text-align: center">

            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${returnUrl}'"/>
        </div>
 <%--   </form:form>--%>


</div>
</body>
<script>
    $(function () {
        $("#dlg").dialog("close")
    })


</script>
</html>