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
    <li><a href="${ctxA}/order/renewal/list?productId=${userInfo.product.id}">续借列表</a></li>
</ul>
<div class="si-warp">
    <br/>
   <%-- <sys:message content="${message}"/>--%>
  <%--  <form:form id="inputForm" modelAttribute="stageBorrow" action="${ctxA}/ac/apppage/save" method="post" class="form-horizontal">
        <form:hidden path="id" />--%>
     <div class="control-group">
         <div class="user_info" style="margin:0 auto">
          <h4>续期人信息</h4>
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
            <h4>续期信息</h4>
                <table class="dataintable">
                    <thead class="info_form_name">
                    <th>服务订单号</th>
                    <th>续期流水号</th>
                    <th>续期状态</th>
                    <th>续期时间</th>
                    <th>还上期本金</th>
                    <th>本期手续费</th>
                    <th>逾期费</th>
                    <th>实际支付</th>
                    <th>续期账号</th>
                    <th>续期失败原因</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${renewal.borrowCash.borrowNo}</td>
                        <td>${renewal.renewalNo}</td>
                        <td>${cfg:getDictLabel('renewalState',renewal.state)}</td>
                        <td><fmt:formatDate value="${renewal.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${renewal.capitalAmount}</td>
                        <td>${renewal.renewalPoundage}</td>
                        <td>${renewal.renewalOverdueAmount}</td>
                        <td>${renewal.actualAmount}</td>
                        <td>${renewal.accountName}${renewal.accountNo}</td>
                        <td>${renewal.failReason}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>


        <div class="form-actions" style="text-align: center">

            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/order/renewal/list?productId=${userInfo.product.id}'"/>
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