<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="jscss" type="java.lang.String" required="true" 
description="js和css文件标记:jquery,webfont,bootstrap,jeesite,common,siMenu,siLayout,easyui,select2,layer,jbox,zTree,validation,hjn"%>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:if test="${not empty jscss}">
<script src="${ctxStatic}/js/base.js?mod=${jscss}" type="text/javascript"></script>
</c:if>