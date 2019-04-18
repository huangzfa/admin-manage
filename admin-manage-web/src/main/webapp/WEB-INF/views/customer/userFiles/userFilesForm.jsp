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
    div.div_back_color{
        background-color: rgba(242, 242, 242, 1);
        width:94%;
        margin-left: 2%;
    }
    table.user_info{
        background-color: rgba(242, 242, 242, 1);
        margin-top:15px;
        border-collapse:collapse;
        width: 96%;
        margin-left:2%;
        font-size: 16px;
    }
    table.user_info td{
        padding-top: 4%;
        width: 25%;
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
    table.bank_info {
        background-color: rgba(242, 242, 242, 1);
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width: 100%;
        margin-left:2%;

    }
    table.bank_info th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.bank_info td {
        text-align: center;
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        background-color: white;
        width: 5%;
    }
    table.borrow_info {
        background-color: rgba(242, 242, 242, 1);
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width: 100%;
        margin-left:2%;

    }
    table.borrow_info th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.borrow_info td {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        background-color: white;
        width: 5%;
    }

    table.borrow_info {
        background-color: rgba(242, 242, 242, 1);
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width: 100%;
        margin-left:2%;

    }
    table.borrow_info th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.borrow_info td {
        text-align: center;
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        background-color: white;
        width: 5%;
    }
    table.user_auth_info {
        background-color: rgba(242, 242, 242, 1);
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width: 100%;
        margin-left:2%;

    }
    table.user_auth_info th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.user_auth_info td {
        text-align: center;
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        background-color: white;
        width: 5%;
    }

    table.user_coupon_info {
        background-color: rgba(242, 242, 242, 1);
        margin-top:15px;
        border-collapse:collapse;
        border:1px solid #aaa;
        width: 100%;
        margin-left:2%;

    }
    table.user_coupon_info th {
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
    }
    table.user_coupon_info td {
        text-align: center;
        vertical-align:text-top;
        padding:6px 15px 6px 6px;
        border:1px solid #aaa;
        background-color: white;
        width: 5%;
    }
    .list_th{
        background-color:#e5e5e5;
        vertical-align:text-top;
        border:1px solid #aaa;
        width: 0%;
    }
    div.query_group{

    }

    .couponStateQuery{
        background-color: white;
        border: 1px solid black;
        cursor:pointer;
        width:3.2rem;
        height:1rem;
        line-height:1rem;
        text-algin:center;
    }
    .stageBorrowQuery{
        background-color: white;
        border: 1px solid black;
        cursor:pointer;
    }
    .active{
        background-color:rgba(22, 155, 213, 1);
        color: white;
        border: 1px solid rgba(22, 155, 213, 1)
    }
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/customer/userFiles/list">用户档案</a></li>
</ul>
<div class="si-warp">
    <input type="hidden" id="userId" value="${user.id}">
    <input type="hidden" id="userName" value="${user.userName}">
    <input type="hidden" id="realName" value="${user.realName}">
    <br/>
   <%-- <sys:message content="${message}"/>--%>
  <%--  <form:form id="inputForm" modelAttribute="repay" action="${ctxA}/ac/apppage/save" method="post" class="form-horizontal">
        <form:hidden path="id" />--%>
    <div class="control-group">
        <div class="user_info" style="margin-top:1%;">
            <h4>基础信息</h4>
            <shiro:hasPermission name="customer:userFiles:loginLog:view">
            <a href="#" style="margin-left: 92%" onclick="getLoginLog()">登录记录</a>
            </shiro:hasPermission>
            <div class="div_back_color">
                <table class="user_info">
                    <tr>
                        <td style="text-align: left"class="">用户账号:${user.userName}</td>
                        <td style="text-align: left;"class="">用户ID:${user.id}</td>
                        <td style="text-align: left;"class="">用户姓名:${user.realName}<%--<span id="userName"></span> <a href="#">修改</a>--%></td>
                        <td style="text-align: left;"class="">注册时间:<fmt:formatDate value="${user.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left"class="">注册渠道:${user.promotionChannel.channelName}</td>
                        <td style="text-align: left"class="">注册产品:${user.app.appName}</td>
                        <td style="text-align: left;"class="">最近登录时间:<fmt:formatDate value="${user.lastLogVo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td style="text-align: left;"class="">收货地址:
                            <select id="address" name="address" class="selectpicker show-tick form-control" >
                                <c:forEach items="${addressList}" var="address">
                                    <c:if test="${address.isDefault == 1}">
                                        <option value="${address.id}" selected>${address.province}${address.city}${address.region}${address.street}${address.detailAddress}（默认）</option>
                                    </c:if>
                                    <c:if test="${address.isDefault == 0}">
                                        <option value="${address.id}">${address.province}${address.city}${address.region}${address.street}${address.detailAddress}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr><td></Td></tr>
                </table>
                </div>
            <%--<form:input path="menuName" htmlEscape="false" maxlength="50" class="input-xlarge"/>--%>
        </div>
        <div id="login" class="easyui-dialog" title="最近5次登录时间" data-options="closed:true,modal:true" style="width:60%;padding:10px"  >
            <table class="bill_table" style="width:96%;margin-left: 2%">
                <thead>
                <th style="text-align: center;"class="bill_list_th">时间</th>
                <th style="text-align: center;"class="bill_list_th">客户端版本</th>
                <th style="text-align: center;"class="bill_list_th">登录APP</th>
                <th style="text-align: center;"class="bill_list_th">手机系统</th>
                <th style="text-align: center;"class="bill_list_th">手机型号</th>
                <th style="text-align: center;"class="bill_list_th">IP地址</th>
                </thead>
                <tbody id = "loginTable">
                </tbody>

            </table>
        </div>
    <div class="bank_info" style="margin-top:3%">
        <h4>银行卡信息</h4>
        <br>
        <div class="div_back_color">
            <br>
        <table class="bank_info" style="width:96%;margin-left: 2%">
            <thead>
            <th style="text-align: center;"class="list_th">银行</th>
            <th style="text-align: center;"class="list_th">卡号</th>
            <th style="text-align: center;"class="list_th">主副卡</th>
            <th style="text-align: center;"class="list_th">操作</th>
            </thead>
            <tbody id="bankTable">
            <c:forEach items="${bankList}" var="bankInfo">
                <h bankId ='${bankInfo.id}'>
                    <td>${bankInfo.bankName}</td>
                    <td>${bankInfo.cardNo}</td>
                    <td>${cfg:getDictLabel('bankIsMain',bankInfo.isMain)}</td>
                    <shiro:hasPermission name="customer:userFiles:setMainBank:edit">
                    <c:choose>
                        <c:when test="${bankInfo.isMain == '1'}">
                            <td>——</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="#" onclick="setMainBank(${bankInfo.id})">设为主卡</a></td>
                        </c:otherwise>
                    </c:choose>
                    </shiro:hasPermission>
                </tr>
            </c:forEach>
            </tbody></table>
            <br>
        </div>
    </div>

    <div class="borrow_info" style="margin-top:3%">
        <h4>借款信息</h4>
        <br>
        <div class="div_back_color">
            <br>
            <div style="text-align: center" class="query_group">
                <table style="margin-left: 47%">
                <tr>
                    <c:forEach items="${customer.stageBorrowQuery}" var="stageQuery">
                        <td class="stageBorrowQuery
                        <c:if test="${stageQuery.des == '1'}">
                        active
                        </c:if>
                        " stageQueryState="${stageQuery.dicVal}">${stageQuery.dicCode}</td>
                    </c:forEach>
                </tr>
                </table>
            </div>

            <table class="borrow_info" style="width:98%;margin-left: 1%">
                <thead>
                <th style="text-align: center;"class="list_th">ID</th>
                <th style="text-align: center;"class="list_th">借款编号</th>
                <th style="text-align: center;"class="list_th">借款金额（元）</th>
                <th style="text-align: center;"class="list_th">天数</th>
                <th style="text-align: center;"class="list_th">服务费（元）</th>
                <th style="text-align: center;"class="list_th">优惠金额（元）</th>
                <th style="text-align: center;"class="list_th">到帐金额（元）</th>
                <th style="text-align: center;"class="list_th">借款状态</th>
                <th style="text-align: center;"class="list_th">风控状态</th>
                <th style="text-align: center;"class="list_th">申请时间</th>
                <th style="text-align: center;"class="list_th">预计还款日</th>
                <th style="text-align: center;"class="list_th">续借次数</th>
                <th style="text-align: center;"class="list_th">未还逾期费（元）</th>
                <th style="text-align: center;"class="list_th">剩余待还金额（元）</th>
                <th style="text-align: center;"class="list_th">操作</th>
                </thead>
                <tbody id="borrowInfoTable">
                <c:forEach items="${stageBorrowVoList}" var="borrowInfo">
                    <tr borrowId ='${borrowInfo.id}'>
                        <td>${borrowInfo.id}</td>
                        <td>${borrowInfo.borrowNo}</td>
                        <td>${cfg:amountLongToBigDecimal(borrowInfo.amount)}</td>
                        <td>${customer.borrowDays}</td>
                        <td>${cfg:amountLongToBigDecimal(borrowInfo.poundage)}</td>
                        <td>${cfg:amountLongToBigDecimal(borrowInfo.couponAmount)}</td>
                        <td>${cfg:amountLongToBigDecimal(borrowInfo.arrivalAmount)}</td>
                        <td>${cfg:getDictLabel('borrowState',borrowInfo.borrowState)}</td>
                        <td>${cfg:getDictLabel('riskState',borrowInfo.riskState)}</td>
                        <td><fmt:formatDate value="${borrowInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${borrowInfo.gmtPlanRepayment}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${borrowInfo.renewalNum}</td>
                        <td>${cfg:amountLongToBigDecimal(borrowInfo.overdueAmount-borrowInfo.derateOverdue-borrowInfo.sumOverdueAmount)}</td>
                        <td>${cfg:amountLongToBigDecimal(borrowInfo.amount+borrowInfo.overdueAmount+borrowInfo.poundage+borrowInfo.poundage-borrowInfo.consumeAmount - borrowInfo.repayAmount - borrowInfo.derateOverdue)}</td>
                        <td><a class="si-option-a" href="${ctxA}/order/borrow/form?userId=${user.id}&id=${borrowInfo.id}">查看</a>
                            <c:if test="${borrowInfo.borrowState == 2}">
                            <a href="#">重新回调</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
         </div>
        </div>

        <div class="auth_info" style="margin-top:3%">
            <h4>认证信息</h4>
            <br>
            <div class="div_back_color">
                <br>
                <table class="user_auth_info" style="width:98%;margin-left: 1%">
                    <thead>
                    <th style="text-align: center;"class="list_th">认证项</th>
                    <th style="text-align: center;"class="list_th">认证状态</th>
                    <th style="text-align: center;"class="list_th">认证时间</th>
                    <th style="text-align: center;"class="list_th">操作</th>
                    </thead>
                    <tbody id="userAuth">
                    <c:forEach items="${userAuthList}" var="userAuthInfo">
                        <tr userAuthId ='${userAuthInfo.code}'>
                            <td>${userAuthInfo.authName}</td>
                            <td>${userAuthInfo.authState}</td>
                            <td><fmt:formatDate value="${userAuthInfo.authTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <shiro:hasPermission name="customer:userFiles:auth:edit">
                            <c:choose>
                                <c:when test="${userAuthInfo.operState == 'reset'}">
                                    <td><a href="#" onclick="resetAuthState('${userAuthInfo.code}')">重置</a></td>

                                </c:when>
                                <c:otherwise>
                                    <td>——</td>
                                </c:otherwise>
                            </c:choose>
                            </shiro:hasPermission>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br>
            </div>
        </div>
        <div class="borrow_info" style="margin-top:3%">
            <h4>优惠券信息</h4>
            <br>
            <div class="div_back_color">
                <br>
                <div style="text-align: center" class="query_group">
                    <table style="margin-left: 47%">
                        <tr>
                            <c:forEach items="${userCouponStateList}" var="couponStateQuery">
                                <td couponState ="${couponStateQuery.dicVal}"   class="couponStateQuery <c:if test="${couponStateQuery.des == '1'}">active</c:if>" >${couponStateQuery.dicCode}</td>
                            </c:forEach>
                        </tr>
                    </table>
                </div>

                <table class="user_coupon_info" style="width:98%;margin-left: 1%">
                    <thead>
                    <th style="text-align: center;"class="list_th">优惠券名称</th>
                    <th style="text-align: center;"class="list_th">优惠券类型</th>
                    <th style="text-align: center;"class="list_th">面值（元）</th>
                    <th style="text-align: center;"class="list_th">有效期</th>
                    </thead>
                    <tbody id ="userCouponTable">
                    <c:forEach items="${userCouponList}" var="userCouponInfo">
                        <tr userCouponId ='${userCouponInfo.id}'>
                            <td>${userCouponInfo.couponName}</td>
                            <td>${userCouponInfo.couponTypeName}</td>
                            <td>${cfg:amountLongToBigDecimal(userCouponInfo.amount)}</td>
                            <td><fmt:formatDate value="${userCouponInfo.validEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br>
            </div>
        </div>
    </div>
</div>

    </div>
<%--    <div class="form-actions" style="text-align: center">

        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:history.back(-1)"/>
    </div>--%>
 <%--   </form:form>--%>


</div>
</body>
<script>
    $(function () {
        $("#login").dialog("close")
    })

    function getLoginLog() {
        $('#tt').datagrid('loading');
        var loginTable = $("#loginTable")
        loginTable.html("");
        var id = $("#userId").val()
        $.ajax({
            type: "POST",
            url: '${ctxA}/customer/userFiles/loginList',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data:{"id":id},
            dataType: "json",
            success: function(data){
                $('#tt').datagrid('loaded');
                $("#login").dialog("open")
                for (var i = 0 ;i<data.length;i++){
                    var tr = "<tr>";
                    tr += "<td>" + formatDateTime(data[i].addTime) +"</td>";
                    tr +="<td>" +data[i].appVersion+"</td>"
                    tr +="<td>" +data[i].appName+"</td>"
                    tr +="<td>" +data[i].osType+"</td>"
                    tr += "<td>" + data[i].phoneType+"</td>"
                    tr += "<td>" +data[i].loginIp+"</td>"
                    tr += "</tr>"
                    $(tr).appendTo(loginTable)
                }
            },
            error: function(msg){
                $('#tt').datagrid('loaded');
                top.layer.alert("查询登录记录失败",{icon: 5});
            }
        });
    }

    function setMainBank(id){
        top.$.jBox.confirm("确认设置此卡为用户主卡？",'主卡设置',function(v,h,f){
            if(v=='ok'){
                $('#tt').datagrid('loading');
            $.ajax({
                type: "POST",
                url: '${ctxA}/customer/userFiles/setMainBank',
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data: {'id':id,'userId':$("#userId").val()},
                dataType: "json",
                success: function(data){
                    $('#tt').datagrid('loaded');
                    top.layer.alert("设置成功",{icon: 6,end:function(){
                            var bankTable = $("#bankTable");
                            bankTable.html("");
                            for (var i = 0 ;i<data.length;i++){
                                var tr = "<tr bankId ='"+data[i].id+"'>";
                                tr +="<td>" +data[i].bankName+"</td>"
                                tr +="<td>" +data[i].cardNo+"</td>"
                                if (data[i].isMain){
                                    tr +="<td>主卡</td>"
                                }else{
                                    tr +="<td>副卡</td>"
                                }
                                if (data[i].isMain){
                                    tr += "<td>——</td>"
                                }else{
                                    tr += "<td><a href='#' onclick='setMainBank("+data[i].id+")'>设为主卡</a></td>"
                                }

                                tr += "</tr>"
                                $(tr).appendTo(bankTable)
                            }
                        }});

                },
                error: function(msg){
                    $('#tt').datagrid('loaded');
                    top.layer.alert("修改主卡失败",{icon: 5});
                }
            });
      }
    })
    }
    function resetAuthState(authCode) {
            top.$.jBox.confirm("确认重置用户认证状态？",'系统提示',function(v,h,f){
                if(v=='ok'){
                    $('#tt').datagrid('loading');
                $.ajax({
                    type: "POST",
                    url: '${ctxA}/customer/userFiles/resetAuthState',
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    data: {'authCode':authCode,'userId':$("#userId").val()},
                    dataType: "json",
                    success: function(data){
                        $('#tt').datagrid('loaded');
                        top.layer.alert("重置成功",{icon: 6,end:function(){
                                var authTable = $("#userAuth");
                                authTable.html("");
                                for (var i = 0 ;i<data.length;i++){
                                    var tr = "<tr userAuthId ='"+data[i].id+"'>";
                                    tr +="<td>" +data[i].authName+"</td>"
                                    tr +="<td>" +data[i].authState+"</td>"
                                    if (data[i].authTime != null) {
                                        tr += "<td>" + formatDateTime(data[i].authTime) + "</td>";
                                    }else{
                                        tr += "<td></td>";
                                    }
                                    if (data[i].operState != 'reset'){
                                        tr += "<td>——</td>"
                                    }else{
                                        tr += "<td><a href='#' onclick='resetAuthState(\""+data[i].code+"\")'>重置</a></td>"
                                    }

                                    tr += "</tr>"
                                    $(tr).appendTo(authTable)
                                }
                            }});
                    },
                    error: function(msg){
                        $('#tt').datagrid('loaded');
                        top.layer.alert("重置失败",{icon: 5});
                    }
                });
            }
        })
    }
    $(".stageBorrowQuery").click(function(){
        var obj = this;
        var borrowInfoTable = $("#borrowInfoTable")
        $('#tt').datagrid('loading');
        var userId = $("#userId").val();
        $.ajax({
            type: "POST",
            url: '${ctxA}/customer/userFiles/stageBorrowListQuery',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {'state':$(this).attr('stageQueryState'),"userId":userId},
            dataType: "json",
            success: function(data){
                $('#tt').datagrid('loaded');
                borrowInfoTable.html("");
                for (var i = 0; i < data.length; i++) {
                    var tr = "<tr borrowId ='"+data[i].id+"'>";
                    tr += "<td>" + data[i].id + "</td>"
                    tr += "<td>" + data[i].borrowNo + "</td>"
                    tr += "<td>" + data[i].productName + "</td>"
                    tr += "<td>" +$("#realName").val() + "</td>"
                    tr += "<td>" +$("#userName").val()+ "</td>"
                    tr += "<td>" + data[i].amount + "</td>"
                    tr += "<td>" + data[i].rateAmount + "</td>"
                    tr += "<td>" + data[i].nperName + "</td>"
                    tr += "<td>" + data[i].couponAmount + "</td>"
                    tr += "<td>" + data[i].arrivalAmount + "</td>"
                    tr += "<td>" + data[i].reviewStateName + "</td>"
                    tr += "<td>" + data[i].reviewBorrowName + "</td>"
                    tr += "<td>" + data[i].borrowStateName + "</td>"
                    if (data[i].addTime != null ) {
                        tr += "<td>" + formatDateTime(data[i].addTime) + "</td>"
                    }else{
                        tr += "<td></td>"
                    }
                    tr += "<td><a class='si-option-a' href='${ctxA}/order/borrow/form?id=" + data[i].id + "'>查看</a> "
                    if(data[i].borrowState == '2'){
                        tr += "<a href='#'>重新回调</a></td>"
                    }

                    tr += "</tr>"
                    $(tr).appendTo(borrowInfoTable)
                }
                $(".stageBorrowQuery").removeClass('active')
                $(obj).addClass('active')
            },
            error: function(msg){
                $('#tt').datagrid('loaded');
                top.layer.alert("查询借款信息失败",{icon: 5});
            }
        });
    })

    function getProductAuthConfig(id) {
        $('#tt').datagrid('loading');
        var productAuthConfigTable1 = $("#productAuthConfigTable1")
        productAuthConfigTable1.html("");

        var productAuthConfigTable2 = $("#productAuthConfigTable2")
        productAuthConfigTable2.html("");
        $.ajax({
            type: "POST",
            url: '${ctxA}/csc/customer/productAuthConfig',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {'id':id},
            dataType: "json",
            success: function(data){
                $('#tt').datagrid('loaded');
                    $("#productAuthConfig").dialog("open")
                    for (var i = 0; i < data.basicList.length; i++) {
                        var tr = "<tr>";
                        tr += "<td>" + data.basicList[i].authName + "</td>"

                        if (data.basicList[i].authState == 1) {
                            tr += "<td>可用</td>"
                        } else if(data.basicList[i].authState == 0) {
                            tr += "<td>不可用</td>"
                        }
                        if (data.basicList[i].isRequired == 1) {
                            tr += "<td>必填 </td>"
                        } else if (data.basicList[i].isRequired == 0) {
                            tr += "<td>不必填</td>"
                        }

                        tr += "<td>" + data.basicList[i].authSort + "</td>"
                        tr += "</tr>"
                        $(tr).appendTo(productAuthConfigTable1)
                    }
                    for (var i = 0; i < data.supplyList.length; i++) {
                        var tr = "<tr>";
                        tr += "<td>" + data.supplyList[i].authName + "</td>"

                        if (data.supplyList[i].authState == 1) {
                            tr += "<td>可用</td>"
                        } else if(data.supplyList[i].authState == 0) {
                            tr += "<td>不可用</td>"
                        }
                        if (data.supplyList[i].isRequired == 1) {
                            tr += "<td>必填 </td>"
                        } else if (data.supplyList[i].isRequired == 0) {
                            tr += "<td>不必填</td>"
                        }

                        +"</td>"
                        tr += "<td>" + data.supplyList[i].authSort + "</td>"
                        tr += "</tr>"
                        $(tr).appendTo(productAuthConfigTable2)
                    }
            },
            error: function(msg){
                $('#tt').datagrid('loaded');
                top.layer.alert("查询认证项失败",{icon: 5});
            }
        });
    }

    $(".couponStateQuery").click(function(){
        $('#tt').datagrid('loading');
        var obj = this;
        var userCouponTable = $("#userCouponTable")
        $.ajax({
            type: "POST",
            url: '${ctxA}/csc/customer/userCouponListQuery',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {'state':$(this).attr('couponState'),"userId":$("#userId").val()},
            dataType: "json",
            success: function(data){
                $('#tt').datagrid('loaded');
                userCouponTable.html("");
                for (var i = 0; i < data.length; i++) {
                    var tr = "<tr userCouponId ='"+data[i].id+"'>";
                    tr += "<td>" + data[i].couponName + "</td>"
                    tr += "<td>" + data[i].couponTypeName + "</td>"
                    tr += "<td>" + data[i].amount + "</td>"
                    if (data[i].validEndTime != null) {
                        tr += "<td>" + formatDateTime(data[i].validEndTime) + "</td>"
                    }else{
                        tr += "<td></td>"
                    }
                    tr += "</tr>"
                    $(tr).appendTo(userCouponTable)
                }
                $(".couponStateQuery").removeClass('active')
                $(obj).addClass('active')
            },
            error: function(msg){
                $('#tt').datagrid('loaded');
                top.layer.alert("查询优惠券信息失败",{icon: 5});
            }
        });
    })


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