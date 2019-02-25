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
    </script>
</head>
<style>
    .repay_info_form_name{
        background-color:#e5e5e5;
    }
    table.dataintable {
        margin-left: 17%;
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width:70%;

    }
    table.dataintable th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.dataintable td {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        width: 20%;
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
        width: 4%;
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
    <li><a href="${ctxA}/bpo/repay/list">还款订单列表</a></li>
</ul>
<div class="si-warp">
    <br/>
   <%-- <sys:message content="${message}"/>--%>
  <%--  <form:form id="inputForm" modelAttribute="repay" action="${ctxA}/ac/apppage/save" method="post" class="form-horizontal">
        <form:hidden path="id" />--%>
    <div class="control-group">
        <div class="borrow_info" style="margin:0 auto">
            <h4>基本信息</h4>
            <table class="dataintable">
                <tr>
                    <td style="text-align: center;"class="repay_info_form_name">用户姓名</td>
                    <td  style="text-align: center" >${repay.realName}</td>
                    <td style="text-align: center;"class="repay_info_form_name">用户账号</td>
                    <td  style="text-align: center" >${repay.userName}</td>
                </tr>
              <tr>
                    <td style="text-align: center;"class="repay_info_form_name">用户来源</td>
                    <td  style="text-align: center;color: red;" >${repay.borrow.channelName}</td>
                    <td style="text-align: center;"class="repay_info_form_name">借款时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${repay.borrow.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                </tr>
                  <tr>
                      <td style="text-align: center;"class="repay_info_form_name">金融产品名称</td>
                      <td  style="text-align: center" >${repay.borrow.productName}</td>
                      <td style="text-align: center;"class="repay_info_form_name">借款本金</td>
                      <td  style="text-align: center" >${repay.borrow.amount}</td>

                  </tr>
                    <tr>
                      <td style="text-align: center;"class="repay_info_form_name">借款周期</td>
                      <td  style="text-align: center" >${repay.borrow.nperName}</td>
                      <td style="text-align: center;"class="repay_info_form_name">借款流水号</td>
                      <td  style="text-align: center" >${repay.borrow.borrowNo}</td>

                  </tr>
                   <tr>
                       <td style="text-align: center;"class="repay_info_form_name">利息金额</td>
                       <td  style="text-align: center" >${repay.borrow.rateAmount}</td>
                       <td style="text-align: center;"class="repay_info_form_name">到账金额</td>
                       <td  style="text-align: center" >${repay.borrow.arrivalAmount}</td>
                   </tr>
                   <tr>
                       <td style="text-align: center;"class="repay_info_form_name">日利率</td>
                       <td  style="text-align: center" >${repay.borrow.overdueRate}</td>
                       <td style="text-align: center;"class="repay_info_form_name">到期应还金额</td>
                       <td  style="text-align: center" >${repay.borrow.amount + repay.borrow.rateAmount}</td>
                   </tr>
                   <tr>
                       <td style="text-align: center;"class="repay_info_form_name">当时到期实际应还金额</td>
                       <td  style="text-align: center" >${repay.borrow.amount + repay.borrow.rateAmount+repay.borrow.overdueAmount}</td>
                       <td style="text-align: center;"class="repay_info_form_name">总逾期费</td>
                       <td  style="text-align: center" >${repay.borrow.realOverdueAmount}</td>
                   </tr>
                   <tr>
                       <td style="text-align: center;"class="repay_info_form_name">
                           到账${cfg:getDictLabel('accountType',repay.borrow.accountType)}
                       </td>
                       <td  style="text-align: center" >${repay.borrow.accountName}</td>
                       <td style="text-align: center;"class="repay_info_form_name">
                           到账${cfg:getDictLabel('accountType',repay.borrow.accountType)}卡号</td>
                       <td  style="text-align: center" >${repay.borrow.accountNo}</td>
                   </tr>
                   <tr>
                       <td style="text-align: center;"class="repay_info_form_name">放款状态</td>
                       <td  style="text-align: center" >${cfg:getDictLabel('borrowState',repay.borrow.borrowState)}</td>
                       <td style="text-align: center;"class="repay_info_form_name">放款失败原因</td>
                       <td  style="text-align: center" >${repay.borrow.closeReason}</td>
                   </tr>
                   <tr>
                       <td style="text-align: center" class="repay_info_form_name">放款时间</td>
                       <td  style="text-align: center" ><fmt:formatDate value="${repay.borrow.gmtUpsReq}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                       <td style="text-align: center" class="repay_info_form_name">借款地点</td>
                       <td  style="text-align: center" >${repay.borrow.province}${repay.borrow.city}${repay.borrow.county}${repay.borrow.address}</td>
                   </tr>
                   <tr>
                       <td style="text-align: center" class="repay_info_form_name">第三方流水号</td>
                       <td  style="text-align: center" >${repay.borrow.upsOrderNo}</td>
                       <td style="text-align: center"  class="repay_info_form_name"></td>
                       <td  style="text-align: center" ></td>
                   </tr>
            </table>
            <%--<form:input path="menuName" htmlEscape="false" maxlength="50" class="input-xlarge"/>--%>
        </div>
     <div class="risk_info" style="margin:0 auto">
            <h4>还款情况</h4>
            <table class="dataintable">
                <tr>
                    <td style="text-align: center;"class="repay_info_form_name">用户还款账期</td>
                    <td  style="text-align: center" >${repay.repayAccountNper}</td>
                    <td style="text-align: center;"class="repay_info_form_name">实际还款账期</td>
                    <td  style="text-align: center" >${repay.realRepayAccountNper}</td>
                </tr>
                <tr>
                    <td style="text-align: center;"class="repay_info_form_name">实际还款金额</td>
                    <td  style="text-align: center" >${repay.repayAmount}</td>
                    <td style="text-align: center;"class="repay_info_form_name">还款结果</td>
                    <td  style="text-align: center" >${cfg:getDictLabel('repayState',repay.repayState)}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="repay_info_form_name">统一流水号</td>
                    <td  style="text-align: center" >${repay.upsOrderNo}</td>
                    <td style="text-align: center"  class="repay_info_form_name">余额抵扣</td>
                    <td  style="text-align: center" >${repay.rebateAmount}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="repay_info_form_name">优惠券抵扣</td>
                    <td  style="text-align: center" >${repay.couponAmount}</td>
                    <td style="text-align: center"  class="repay_info_form_name">实际支付</td>
                    <td  style="text-align: center" >${repay.realAmount}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="repay_info_form_name">还款提交时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${repay.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td style="text-align: center"  class="repay_info_form_name">还款方式</td>
                    <td  style="text-align: center" >${cfg:getDictLabel('repayType',repay.repayType)}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="repay_info_form_name">还款账号</td>
                    <td  style="text-align: center" >${repay.accountNo}</td>
                    <td style="text-align: center"  class="repay_info_form_name">失败原因</td>
                    <td  style="text-align: center" >${repay.failReason}</td>
                </tr>
            </table>
        </div>
         <c:if test="${repay.repayState == 1}">
          <div class="acccount_info" style="margin:0 auto">
              <h4>账单信息</h4>
              <table class="bill_table" style="width:96%;margin-left: 2%">
                  <thead>
                  <th style="text-align: center;"class="bill_list_th">拆分账期</th>
                  <th style="text-align: center;"class="bill_list_th">账单还款流水号</th>
                  <th style="text-align: center;"class="bill_list_th">拆分金额</th>
                  <th style="text-align: center;"class="bill_list_th">本期应还本息</th>
                  <th style="text-align: center;"class="bill_list_th">应还本金</th>
                  <th style="text-align: center;"class="bill_list_th">应还利息</th>
                  <th style="text-align: center;"class="bill_list_th">总逾期天数</th>
                  <th style="text-align: center;"class="bill_list_th">逾期费</th>
                  <th style="text-align: center;"class="bill_list_th">本期当时实际应还</th>
                  <th style="text-align: center;"class="bill_list_th">已还违约金</th>
                  <th style="text-align: center;"class="bill_list_th">已还利息</th>
                  <th style="text-align: center;"class="bill_list_th">已还本金</th>
                  <th style="text-align: center;"class="bill_list_th">减免金额</th>
                  <th style="text-align: center;"class="bill_list_th">剩余应还</th>
                  </thead>
                  <tbody>
                  <c:forEach items="${repay.billList}" var="billInfo">
                      <tr biilId ='${billInfo.id}'>
                          <td>${billInfo.billNper}期</td>
                          <td>${billInfo.billNo}</td>
                        <td>${billInfo.splitAmount}</td>
                          <td>${billInfo.billAmount}</td>
                          <td>${billInfo.billCapitalAmount}</td>
                          <td>${billInfo.billRateAmount}</td>
                          <td>${billInfo.overdueDays}</td>
                          <td>${billInfo.overdueAmount}</td>
                          <td>${billInfo.realRepayAmount}</td>
                          <td>${billInfo.sumRepayOverdueAmount}</td>
                          <td>${billInfo.sumRateAmount}</td>
                          <td>${billInfo.repayCapitalAmount}</td>
                          <td>${billInfo.derateOverdue}</td>
                          <td>${billInfo.remainderAmount}</td>
                      </tr>
                  </c:forEach>
                  </tbody>

              </table>
          </div>
      </c:if>
    </div>
    <div class="form-actions" style="text-align: center">

        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href = '/a/bpo/repay/list'"/>
    </div>
 <%--   </form:form>--%>


</div>
</body>
<script>
    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };


</script>
</html>