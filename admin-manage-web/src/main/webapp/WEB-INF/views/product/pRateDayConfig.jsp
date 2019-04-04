<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui"/>
    <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.min.js"></script>
    <!--  -->
</head>
<body>
<div ng-app="rateFormApp" ng-controller="rateFormCtrl">
    <form  class="form-horizontal" id="rateForm" >
        <input type="hidden" value="loan.id">
        <div class="control-group">
            <label class="control-label"><h5>借款利率对应天数配置</h5></label>
        </div>

    </form>
</div>
</body>
</html>