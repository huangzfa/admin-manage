<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui"/>


</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctxA}/product/list">消费贷产品配置</a></li>
</ul>
${show=='true'?'':'请关联消费贷业务'}
<div class="si-warp" style="display: ${show=='true'?'block':'none'}">
    <ul class="nav nav-tabs bar_tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#authConfig" id="tab1" role="tab" data-toggle="tab" aria-expanded="true">基础借款配置</a>
        </li>
        <li>
            <a href="#loanConfig" id="tab2" role="tab" data-toggle="tab" aria-expanded="true">消费贷相关配置</a>
        </li>
        <li>
            <a href="#rateDayConfig" id="tab3" role="tab" data-toggle="tab" aria-expanded="true">期限利率配置</a>
        </li>
    </ul>
    <div class="tab-content" style="height:100%">
        <div role="tabpanel" class="tab-pane fade active in" id="authConfig" style="height: 100%">
            <object style="border:0px" type="text/x-scriptlet" data="${ctxA}/product/config/auth?productCode=${productCode}" width="100%" height="100%"></object>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="loanConfig" style="height: 100%">
            <object style="border:0px" type="text/x-scriptlet" data="${ctxA}/product/config/laon?productCode=${productCode}" width="100%" height="100%"></object>
        </div>
        <div role="tabpanel" class="tab-pane fad" id="rateDayConfig" style="height: 100%">
            <object style="border:0px" type="text/x-scriptlet" data="${ctxA}/product/config/rateDay?productCode=${productCode}" width="100%" height="100%"></object>
        </div>
    </div>
</div>