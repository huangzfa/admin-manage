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
            appId:{
              required:true,
            },
            versionNumber:{
              required:true
            },
            versionName:{
              required:true
            },
            apkUrl:{
              required:true
            },
            upgradeRange:{
              required:true
            },
            appSystemType:{
              required:true
            },
            isForce:{
              required:true
            },
            versionDesc:{
              required:true
            },
            state:{
              required:true
            }
          },
          messages: {
            appId:{required : "必填信息"},
            versionNumber:{required : "必填信息"},
            versionName:{required : "必填信息"},
            apkUrl:{required : "必填信息"},
            upgradeRange:{required : "必填信息"},
            appSystemType:{required : "必填信息"},
            isForce:{required : "必填信息"},
            versionDesc:{required : "必填信息"},
            state:{required : "必填信息"}
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


      function onlyNumber(obj){
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d\.]/g,'');
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g,'');
      }

    </script>
</head>
<style>
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/operat/appupgrade/list?id=${object.id}">升级配置列表</a></li>
    <li class="active">
        <shiro:hasPermission name="operat:appupgrade:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}升级配置</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="operat:appupgrade:edit">
            <a href="javascript:void(0);">查看升级配置</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="appUpgradeVo" action="${ctxA}/operat/appupgrade/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <div class="control-group">
            <label class="control-label">应用：</label>
            <div class="controls">
                <form:select path="appId" cssStyle="width:285px;">
                    <c:forEach items="${appUpgradeVo.appList}" var="o">
                        <form:option value="${o.id}">${o.appName}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本号：</label>
            <div class="controls">
                <form:input path="versionNumber" htmlEscape="false" maxlength="50" type="number" class="input-xlarge" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                <span class="help-inline">请填写无小数点的版本号</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本名：</label>
            <div class="controls">
                <form:input path="versionName" htmlEscape="false" maxlength="20" class="input-xlarge"  onkeyup="onlyNumber(this)" onblur="onlyNumber(this)"/>
                <span class="help-inline">请填写带小数点的版本名称，客户端显示</span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">状态：</label>
            <div class="controls">
                <form:select path="state" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('appUpgradeState')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">版本描述：</label>
            <div class="controls">
                <form:textarea path="versionDesc" htmlEscape="false" maxlength="50" class="input-xxlarge"/>
                <span class="help-inline">请填写版本描述客户端可见</span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">下载地址：</label>
            <div class="controls">
                <form:input path="apkUrl" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline">下载地址,必填</span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">升级范围：</label>
            <div class="controls">
                <form:select path="upgradeRange" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('upgradeRangeState')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">类型：</label>
            <div class="controls">
                <form:select path="appSystemType" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('appSystemType')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">是否强制升级：</label>
            <div class="controls">
                <form:select path="isForce" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('appUpgradeIsForce')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="operat:appupgrade:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/operat/appupgrade/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
</html>