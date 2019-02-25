<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
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
    <c:if test="${pageSource} == 1">
        <li><a href="${ctxA}/finance/offline/repayment/list">线下平账列表</a></li>
    </c:if>
    <c:if test="${pageSource} == 2">
        <li><a href="${ctxA}/finance/offline/repayment/ronglist">融360线下平账列表</a></li>
    </c:if>
</ul>
<div class="si-warp">
    <br/>
    <div class="control-group">
        <div class="borrow_info" style="margin:0 auto">
            <h4>基本信息</h4>
            <table class="dataintable">
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">用户姓名</td>
                    <td  style="text-align: center" >${borrow.realName}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">用户账号</td>
                    <td  style="text-align: center" >${borrow.userName}</td>
                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">用户来源</td>
                    <td  style="text-align: center" >
                        ${userInfo.channelName}
                    </td>
                    <td style="text-align: center;"class="borrow_info_form_name">借款时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${borrow.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">金融产品名称</td>
                    <td  style="text-align: center" >${borrow.productName}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">借款本金</td>
                    <td  style="text-align: center" >${borrow.amount}</td>

                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">借款周期</td>
                    <td  style="text-align: center" >
                        <c:if test="${borrow.borrowNperUnit eq 1}">
                            ${borrow.borrowNperInterval}天
                        </c:if>
                        <c:if test="${borrow.borrowNperUnit eq 2}">
                            ${borrow.nper}期
                        </c:if></td>
                    <td style="text-align: center;"class="borrow_info_form_name">借款流水号</td>
                    <td  style="text-align: center" >${borrow.borrowNo}</td>

                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">利息金额</td>
                    <td  style="text-align: center" >${borrow.rateAmount}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">到账金额</td>
                    <td  style="text-align: center" >${borrow.arrivalAmount}</td>

                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">日利率</td>
                    <td  style="text-align: center" >${borrow.borrowRate}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">到期应还金额</td>
                    <td  style="text-align: center" >${borrow.amount + borrow.rateAmount}</td>
                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">当期到期实际应还金额</td>
                    <td  style="text-align: center" >${borrow.amount + borrow.rateAmount + borrow.overdueAmount}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">总违约金</td>
                    <td  style="text-align: center" >${borrow.realOverdueAmount}</td>

                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">
                        到账${cfg:getDictLabel('accountType',borrow.accountType)}
                    </td>
                    <td  style="text-align: center" >${borrow.accountName}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">
                        到账${cfg:getDictLabel('accountType',borrow.accountType)}卡号</td>
                    <td  style="text-align: center" >${borrow.accountNo}</td>
                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">放款状态</td>
                    <td  style="text-align: center" >${cfg:getDictLabel('borrowState',borrow.borrowState)}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">放款失败原因</td>
                    <td  style="text-align: center" >${borrow.closeReason}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="borrow_info_form_name">放款时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${borrow.gmtUpsReq}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td style="text-align: center" class="borrow_info_form_name">借款地点</td>
                    <td  style="text-align: center" >${borrow.province}${borrow.city}${borrow.county}${borrow.address}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="borrow_info_form_name">第三方流水号</td>
                    <td  style="text-align: center" >${borrow.upsOrderNo}</td>
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
                    <td  style="text-align: center" >${cfg:getDictLabel('reviewState',borrow.reviewState)}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">结果返回时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${borrow.gmtRiskFinish}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">人审结果</td>
                    <td  style="text-align: center" >${cfg:getDictLabel('reviewBorrow',borrow.reviewBorrow)}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">人审订单号</td>
                    <td  style="text-align: center" >${borrow.riskOrderNo}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="borrow_info_form_name">人审返回时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${borrow.gmtRiskReviewFinish}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td style="text-align: center"  class="borrow_info_form_name"></td>
                    <td  style="text-align: center" ></td>
                </tr>
            </table>
        </div>

        <div class="risk_info" style="margin:0 auto">
            <h4>还款信息</h4>
            <table class="dataintable">
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">本次还款金额</td>
                    <td  style="text-align: center" >${offline.repayAmount}</td>
                    <td style="text-align: center;"class="borrow_info_form_name">统一还款流水号</td>
                    <td  style="text-align: center" >${offline.orderNo}</td>
                </tr>
                <tr>
                    <td style="text-align: center;"class="borrow_info_form_name">还款提交时间</td>
                    <td  style="text-align: center" ><fmt:formatDate value="${offline.submitterTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td style="text-align: center;"class="borrow_info_form_name">来源</td>
                    <td  style="text-align: center" >
                        <c:forEach items="${offlineTypes}" var="data">
                            <c:if test="${offline.submitterType == data.dicVal}" >${data.dicCode}</c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center" class="borrow_info_form_name">提交人员</td>
                    <td  style="text-align: center" >${offline.submitterName}</td>
                    <td style="text-align: center"  class="borrow_info_form_name">平账人员</td>
                    <td  style="text-align: center" >${offline.settleOperatorName}</td>
                </tr>
                <tr>
                    <td style="text-align: center" class="borrow_info_form_name">平账方式</td>
                    <td  style="text-align: center" >
                        <c:if  test="${offline.settleType eq 1}" >批量平账</c:if>
                        <c:if  test="${offline.settleType eq 2}" >人工平账</c:if>
                    </td>
                    <td style="text-align: center"  class="borrow_info_form_name">平账状态</td>
                    <td  style="text-align: center" >
                        <c:if  test="${offline.repayState eq -1}" >平账失败</c:if>
                        <c:if  test="${offline.repayState eq 0}" >待平账</c:if>
                        <c:if  test="${offline.repayState eq 1}" >已平账</c:if>
                    </td>
                </tr>
            </table>
        </div>


        <div class="acccount_info" style="margin:0 auto">
            <h4>还款拆分信息</h4>
            <table class="bill_table" style="width:96%;margin-left: 2%">
                <thead>
                <th style="text-align: center;"class="bill_list_th">分期账单号</th>
                <th style="text-align: center;"class="bill_list_th">期限</th>
                <th style="text-align: center;"class="bill_list_th">待还金额</th>
                <th style="text-align: center;"class="bill_list_th">已还金额</th>
                <th style="text-align: center;"class="bill_list_th">逾期费</th>
                <th style="text-align: center;"class="bill_list_th">本次平账金额</th>
                <th style="text-align: center;"class="bill_list_th">逾期费减免</th>
                <th style="text-align: center;"class="bill_list_th">减免费用</th>
                <th style="text-align: center;"class="bill_list_th">计划还款时间</th>
                <th style="text-align: center;"class="bill_list_th">实际还款时间</th>
                <th style="text-align: center;"class="bill_list_th">状态</th>
                <th style="text-align: center;"class="bill_list_th">平账时间</th>
                <th style="text-align: center;"class="bill_list_th">减免金额</th>
                <th style="text-align: center;"class="bill_list_th">平账人员</th>
                </thead>
                <tbody>
                <c:forEach items="${billRepayments}" var="data">
                    <tr biilId ='${data.id}'>
                        <td>${data.billNo}</td>
                        <td>${data.billNper}/${borrow.nper}</td>
                        <td>${data.remainderAmount}</td>
                        <td>${data.sumRepayAmount}</td>
                        <td>${data.overdueAmount}</td>
                        <td>${data.billCurrentRepayAmount}</td>
                        <td>${data.derateOverdue}</td>
                        <td>${data.derateOverdue}</td>
                        <td><fmt:formatDate value="${data.gmtExpire}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${data.gmtRealRepay}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <c:choose>
                                <c:when  test="${data.billState == 1}">
                                    已平账
                                </c:when>
                                <c:otherwise>
                                    部分平账
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${data.dealBillRepayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${data.derateOverdue}</td>
                        <td>${offline.settleOperatorName}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

    <div class="form-actions" style="text-align: center">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:history.back(-1)"/>
    </div>

</div>
</body>
</html>