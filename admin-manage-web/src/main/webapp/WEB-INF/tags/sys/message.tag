<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="isShowBox" type="java.lang.Boolean" required="false" description="是否显示信息框"%>
<%@ attribute name="timeout" type="java.lang.String" required="false" description="消息显示时间，毫秒"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、error、loading"%>
<script type="text/javascript">top.$.jBox.closeTip();</script>
<c:if test="${not empty content}">
	<c:if test="${not empty type}">
		<c:set var="ctype" value="${type}"/>
	</c:if>
	<c:if test="${empty type}">
		<c:set var="ctype" value="${fn:indexOf(content,'失败') eq -1?'success':'error'}"/>
	</c:if>
	<c:if test="${empty timeout}">
		<c:set var="timeout" value="0"/>
	</c:if>
	<c:if test="${empty isShowBox}">
		<c:set var="isShowBox" value="true"/>
	</c:if>
	<%--错误提示重复--%>
	<%--<div id="messageBox" class="alert alert-${ctype} hide" style="margin-bottom: 5px;">--%>
		<%--<button data-dismiss="alert" class="close">×</button>${content}--%>
	<%--</div> --%>
	<script type="text/javascript">
		(function(){
			if(!top.$.jBox.tip.mess){
				top.$.jBox.tip.mess=1;
				top.$.jBox.tip("${content}","${ctype}",{persistent:true,opacity:0});
				if("${isShowBox}"=='true'){
					$("#messageBox").show();
					try {
						var timeout=${timeout};
						if(timeout>0){
							var t=setTimeout(function(){
								$("#messageBox").fadeOut("slow");
								clearTimeout(t);
							},timeout);
						}
					} catch (e) {
					}
				}
				
			}
		})();
	</script>
</c:if>