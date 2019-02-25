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
    .borrow_info_form_name{
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
    <li><a href="${ctxA}/bpo/stageBorrow/list">借款订单列表</a></li>
</ul>
<div class="si-warp">
    <br/>
   <%-- <sys:message content="${message}"/>--%>
  <%--  <form:form id="inputForm" modelAttribute="stageBorrow" action="${ctxA}/ac/apppage/save" method="post" class="form-horizontal">
        <form:hidden path="id" />--%>
     <div class="control-group">
         <div class="borrow_info" style="margin:0 auto">
          <h4>基本信息</h4>
                <table class="dataintable">
                    <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">用户姓名</td>
                        <td  style="text-align: center" >${stageBorrow.realName}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">用户账号</td>
                        <td  style="text-align: center" >${stageBorrow.userName}</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">借款时间</td>
                        <td  style="text-align: center" ><fmt:formatDate value="${stageBorrow.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td style="text-align: center;"class="borrow_info_form_name">金融产品名称</td>
                        <td  style="text-align: center" >${stageBorrow.productName}</td>
                    </tr>
                   <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">借款本金</td>
                        <td  style="text-align: center" >${stageBorrow.amount}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">借款周期</td>
                        <td  style="text-align: center" >
                            <c:if test="${stageBorrow.borrowNperUnit eq '1'} '">
                                ${stageBorrow.borrowNperInterval}天
                            </c:if>
                            <c:if test="${stageBorrow.borrowNperUnit eq '2'}">
                                ${stageBorrow.nper}期
                            </c:if></td>
                    </tr>
                     <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">借款流水号</td>
                        <td  style="text-align: center" >${stageBorrow.borrowNo}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">利息金额</td>
                        <td  style="text-align: center" >${stageBorrow.rateAmount}</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">到账金额</td>
                        <td  style="text-align: center" >${stageBorrow.arrivalAmount}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">借款券</td>
                        <td  style="text-align: center" >${stageBorrow.couponAmount}</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">日利率</td>
                        <td  style="text-align: center" >${stageBorrow.borrowRate}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">到期应还金额</td>
                        <td  style="text-align: center" >${stageBorrow.amount + stageBorrow.rateAmount}</td>
                    </tr>
                  <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">总违约金</td>
                        <td  style="text-align: center" >${stageBorrow.overdueAmount}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">实际应还金额</td>
                        <td  style="text-align: center" >${stageBorrow.amount + stageBorrow.rateAmount+stageBorrow.overdueAmount}</td>
                    </tr>
                      <tr>
                         <td style="text-align: center;"class="borrow_info_form_name">
                             到账${cfg:getDictLabel('accountType',stageBorrow.accountType)}
                             </td>
                         <td  style="text-align: center" >${stageBorrow.accountName}</td>
                         <td style="text-align: center;"class="borrow_info_form_name">
                             到账${cfg:getDictLabel('accountType',stageBorrow.accountType)}卡号</td>
                         <td  style="text-align: center" >${stageBorrow.accountNo}</td>
                     </tr>
                     <tr>
                         <td style="text-align: center;"class="borrow_info_form_name">放款状态</td>
                         <td  style="text-align: center" >${cfg:getDictLabel('borrowState',stageBorrow.borrowState)}</td>
                         <td style="text-align: center;"class="borrow_info_form_name">放款失败原因</td>
                         <td  style="text-align: center" >${stageBorrow.closeReason}</td>
                     </tr>
                     <tr>
                         <td style="text-align: center" class="borrow_info_form_name">放款时间</td>
                         <td  style="text-align: center" ><fmt:formatDate value="${stageBorrow.gmtUpsReq}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                         <td style="text-align: center" class="borrow_info_form_name">借款地点</td>
                         <td  style="text-align: center" >${stageBorrow.province}${stageBorrow.city}${stageBorrow.county}${stageBorrow.address}</td>
                     </tr>
                     <tr>
                         <td style="text-align: center" class="borrow_info_form_name">第三方流水号</td>
                         <td  style="text-align: center" >${stageBorrow.upsOrderNo}</td>
                         <td style="text-align: center"  class="borrow_info_form_name"></td>
                         <td  style="text-align: center" ></td>
                     </tr>
                </table>
                <%--<form:input path="menuName" htmlEscape="false" maxlength="50" class="input-xlarge"/>--%>
            </div>
            <div class="risk_info" style="margin:0 auto">
            <h4>风控&人审信息</h4>
                <table class="dataintable">
                    <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">风控结果</td>
                        <td  style="text-align: center" >${cfg:getDictLabel('reviewState',stageBorrow.reviewState)}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">结果返回时间</td>
                        <td  style="text-align: center" ><fmt:formatDate value="${stageBorrow.gmtRiskFinish}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: center;"class="borrow_info_form_name">人审结果</td>
                        <td  style="text-align: center" >${cfg:getDictLabel('reviewBorrow',stageBorrow.reviewBorrow)}</td>
                        <td style="text-align: center;"class="borrow_info_form_name">人审订单号</td>
                        <td  style="text-align: center" >${stageBorrow.riskOrderNo}</td>
                    </tr>
                    <tr>
                        <td style="text-align: center" class="borrow_info_form_name">人审返回时间</td>
                        <td  style="text-align: center" ><fmt:formatDate value="${stageBorrow.gmtRiskReviewFinish}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td style="text-align: center"  class="borrow_info_form_name"></td>
                        <td  style="text-align: center" ></td>
                    </tr>
                </table>
            </div>
         <div class="acccount_info" style="margin:0 auto">
             <h4>账单信息</h4>
             <table class="bill_table" style="width:96%;margin-left: 2%">
                 <thead>
                     <th style="text-align: center;"class="bill_list_th">应还日期</th>
                     <th style="text-align: center;"class="bill_list_th">应还本息</th>
                     <th style="text-align: center;"class="bill_list_th">应还本金</th>
                     <th style="text-align: center;"class="bill_list_th">应还利息</th>
                     <th style="text-align: center;"class="bill_list_th">总逾期天</th>
                     <th style="text-align: center;"class="bill_list_th">逾期违约</th>
                     <th style="text-align: center;"class="bill_list_th">实际应还</th>
                     <th style="text-align: center;"class="bill_list_th">已还违约</th>
                     <th style="text-align: center;"class="bill_list_th">已还利息</th>
                     <th style="text-align: center;"class="bill_list_th">已还本金</th>
                     <th style="text-align: center;"class="bill_list_th">减免金额</th>
                     <th style="text-align: center;"class="bill_list_th">剩余应还</th>
                     <th style="text-align: center;"class="bill_list_th">操作</th>
                 </thead>
                 <tbody>
                 <c:forEach items="${stageBorrow.billList}" var="billInfo">
                         <tr biilId ='${billInfo.id}'>
                             <td><fmt:formatDate value="${billInfo.gmtExpire}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                             <td>${billInfo.billAmount}</td>
                             <td>${billInfo.billCapitalAmount}</td>
                             <td>${billInfo.billRateAmount}</td>
                             <td>${billInfo.overdueDays}</td>
                             <td>${billInfo.overdueAmount}</td>
                             <td>${billInfo.billAmount+billInfo.overdueAmount}</td>
                             <td>${billInfo.sumRepayOverdueAmount}</td>
                             <td>${billInfo.sumRateAmount}</td>
                             <td>${billInfo.sumRepayAmount - billInfo.sumRepayOverdueAmount - billInfo.sumRateAmount}</td>
                             <td>${billInfo.derateOverdue}</td>
                             <td>${billInfo.billAmount + billInfo.overdueAmount  - billInfo.sumRepayAmount - billInfo.derateOverdue}</td>
                             <td><a href="#" onclick="getRepaymentLog(${billInfo.id})">查看还款记录</a></td>
                     </tr>
                 </c:forEach>
                 </tbody>

             </table>
         </div>
        </div>
        <div id="dlg" class="easyui-dialog" title="还款记录" data-options="iconCls:'icon-save',closed:true,modal:true" style="width:80%;padding:10px"  >
             <table class="bill_table" style="width:96%;margin-left: 2%">
                 <thead>
                 <th style="text-align: center;"class="bill_list_th">还款时间</th>
                 <th style="text-align: center;"class="bill_list_th">统一还款流水</th>
                 <th style="text-align: center;"class="bill_list_th">账单还款流水</th>
                 <th style="text-align: center;"class="bill_list_th">还款方式</th>
                 <th style="text-align: center;"class="bill_list_th">还款账号</th>
                 <th style="text-align: center;"class="bill_list_th">还款状态</th>
                 <th style="text-align: center;"class="bill_list_th">还款总额</th>
               <%--  <th style="text-align: center;"class="bill_list_th">优惠券抵扣</th>--%>
                 <th style="text-align: center;"class="bill_list_th">余额支付</th>
                 <th style="text-align: center;"class="bill_list_th">实际支付</th>
                 <th style="text-align: center;"class="bill_list_th">失败原因</th>
                 </thead>
                 <tbody id = "repaymentTbody">
                 </tbody>

             </table>
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
    function getRepaymentLog(id) {
        $('#tt').datagrid('loading');
        var repaymentTbody = $("#repaymentTbody")
        repaymentTbody.html("");
        jQuery.post('${ctxA}/bpo/stageBorrow/billList',{'id':id},
            function(data) {
                $('#tt').datagrid('loaded');
                $("#dlg").dialog("open")
                for (var i = 0 ;i<data.length;i++){
                    var tr = "<tr>";
                    tr += "<td>" + formatDateTime(data[i].addTime) +"</td>";
                    if (data[i].upsOrderNo != null) {
                        tr += "<td>" + data[i].upsOrderNo + "</td>"
                    }else{
                        tr +="<td></td>"
                    }
                    tr +="<td>" +data[i].repayNo+"</td>"
                    tr += "<td>" + data[i].repayTypeName+"</td>"
                    if (data[i].accountNo != null){
                        tr += "<td>" +data[i].accountNo+"</td>"
                    } else{
                        tr +="<td></td>"
                    }

                    tr += "<td>" + data[i].repayStateName+"</td>"
                    tr += "<td>" +data[i].repayAmount+"</td>"
          /*          tr += "<td>" +data[i].couponAmount+"</td>"*/
                    tr += "<td>" +data[i].rebateAmount+"</td>"
                    tr +="<td>" +data[i].realAmount+"</td>"
                    if(data[i].failReason != null){
                        tr +="<td>" +data[i].failReason+"</td>"
                    }else{
                        tr +="<td></td>"
                    }

                    tr += "</tr>"
                    $(tr).appendTo(repaymentTbody)
                }
            }, "json");
    }


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