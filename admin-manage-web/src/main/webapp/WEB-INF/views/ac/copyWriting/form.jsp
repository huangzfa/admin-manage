<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ocupload"/>

</head>
<body>
    <div class="si-warp">
        <form id="resourceForm" action="" method="post" class="form-horizontal">
            <input type="hidden" name="id" id="id" value="${not empty resource.id?resource.id:''}">
            <input type="hidden" name="type" id="type" value="${resource.type}">
            <div class="control-group">
                <label class="control-label">页面位置：</label>
                <div class="controls">
                    <label class="control-label" style="text-align:left">${resource.name}</label>
                </div>
            </div>
            <c:if test="${twoCopy == false}">
                <div class="control-group">
                    <label class="control-label">文案内容：</label>
                    <div class="controls">
                        <input type="text" class="form-control valid" descripe="请填写文案内容"  type="text" id="copywriting1" name="copywriting1"  maxlength="40" value="${resource.copywriting1}" style="width: 300px;"></input>
                    </div>
                </div>
            </c:if>
            <c:if test="${twoCopy ==true}">
                <div class="control-group">
                    <label class="control-label">文案内容1</label>
                    <div class="controls">
                        <input type="text" class="form-control valid" descripe="请填写文案内容" type="text" id="copywriting1" name="copywriting1" maxlength="40" value="${resource.copywriting1}" style="width: 300px;"></input>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">文案内容2</label>
                    <div class="controls">
                        <input type="text" class="form-control valid" descripe="请填写文案内容" type="text" id="copywriting2" name="copywriting2"  maxlength="40" value="${resource.copywriting2}" style="width: 300px;"></input>
                    </div>
                </div>
            </c:if>
            <div class="control-group">
                <label class="control-label">颜色值</label>
                <div class="controls">
                    <input type="text" class="form-control"  type="text" name="color" id="color" maxlength="20" value="${resource.color}" style="width: 300px;"></input>
                </div>
            </div>
        </form>
    </div>
<script>
</script>
</body>
</html>