<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cfg" uri="/WEB-INF/tlds/cfg.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctxA" value="${pageContext.request.contextPath}${cfg:getAuthzPath()}" />
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
