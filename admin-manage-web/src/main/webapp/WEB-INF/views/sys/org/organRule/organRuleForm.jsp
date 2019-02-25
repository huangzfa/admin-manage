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
	$("#inputForm").validate({
		rules: {
			supOrganTypeName:{
				required:true
			},
			subOrganTypeName:{
				required:true
			}
		},
		messages: {
			supOrganTypeName:{required : "必填信息"},
			subOrganTypeName:{required:"必填信息"}
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
	});
});
//选择组织类型
function selectOrganType(flag) {
	var organTypeId=flag=='sup'?$("#supOrganTypeId").val():$("#subOrganTypeId").val();
    top.$.jBox.open("iframe:${ctxA}/sys/organType/selectOrganType?organTypeId="+organTypeId, "选择组织类型", 350, 250, {
        buttons: {"确定": "ok", "关闭": true},
        submit: function (v, h, f) {
            var contentW = h.find("iframe")[0].contentWindow;
            if (v == "ok") {
                if (contentW.$("input[name='organTypeIdRadio']:checked").length > 0) {
                    if (contentW.$("input[name='organTypeIdRadio']:checked").length > 1) {
                        top.$.jBox.tip("只能选择一个组织类型！");
                        return false;
                    }
                    var selectOrganType=contentW.$("input[name='organTypeIdRadio']:checked:first");
                    $("#"+flag+"OrganTypeId").val(selectOrganType.val());
                    $("#"+flag+"OrganTypeName").val(selectOrganType.attr('organTypeName'));
                    return true;
                } else {
                    top.$.jBox.tip("请选择组织类型！");
                    return false;
                }
            }
        }, loaded: function (h) {
            $(".jbox-content", top.document).css("overflow-y", "hidden");
        }
    });
}
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li><a href="${ctxA}/sys/organRule/list">组织规则列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:organRule:edit">
  		<a href="javascript:void(0);">${not empty organRule.organRuleId?'修改':'添加'}组织规则</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:organRule:edit">
  		<a href="javascript:void(0);">查看组织规则</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="organRule" action="${ctxA}/sys/organRule/save" method="post" class="form-horizontal">
		<form:hidden path="organRuleId"/>
		<div class="control-group">
			<label class="control-label">上级组织类型：</label>
			<div class="controls">
				<div class="input-append" onclick="selectOrganType('sup');">
					<form:hidden path="supOrganTypeId"/>
					<form:input path="supOrganTypeName" htmlEscape="false" maxlength="50" readonly="true" cssStyle="width:230px;"/>
					<button class="btn" type="button"><i class="icon-search"></i></button>
				</div>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下级组织类型：</label>
			<div class="controls">
				<div class="input-append" onclick="selectOrganType('sub');">
					<form:hidden path="subOrganTypeId"/>
					<form:input path="subOrganTypeName" htmlEscape="false" maxlength="50" readonly="true" cssStyle="width:230px;"/>
					<button class="btn" type="button"><i class="icon-search"></i></button>
				</div>
				<span class="help-inline"><font color="red">*</font></span>
				<label id="organRule-error" class="error hide">分支组织只能直属在总组织或分支组织下</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
                <form:textarea path="remark" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:organRule:edit">
	    	<c:if test="${!organRule.isSystem}">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</c:if>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/sys/organRule/list'"/>
		</div>
	</form:form>
</div>
</body>
</html>