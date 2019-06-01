<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,97Date,ajaxfileupload"/>
    <style type="text/css">
       .control-group li {float:left;position:relative;margin-left:2%;margin-top:0px;list-style:none;}
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/push/sms/list">返回列表</a></li>
    <li class="active">
        <shiro:hasPermission name="push:sys:edit">
            <a href="javascript:void(0);">${not empty push.id?'修改':'添加'}推送</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="pushForm" modelAttribute="push"   action="${ctxA}/push/sys/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty push.id?push.id:''}">
        <input type="hidden" name="productId" id="productId" value="${productId}">
        <input type="hidden" name="pushType" value="2">
        <input type="hidden" name="platform" id="platform" value="android,ios">
        <div class="control-group">
            <label class="control-label">选择应用：</label>
            <div class="controls">
                <select  id="appId" name="appId" class="selectpicker show-tick form-control" descripe="请选择应用">
                    <c:forEach items="${appList}" var="app">
                        <option value="${app.id}" >${app.appName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">短信内容：</label>
            <div class="controls">
                <textarea  class="form-control valid" descripe="请填写短信内容" type="text" name="pushContent" id="pushContent" maxlength="32" >${push.pushContent}</textarea>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">发送名单：</label>
            <div class="controls ">
                <li class='sub_file_li' >
                    <a  class='btn  btn-primary' >user-id</a>
                </li>
            </div>
        </div>
        <div class="control-group import" >
            <label class="control-label">文件导入：</label>
            <div class="controls">
                <div class="uploadExcle">
                    <input type="file"  id="importexcel"    name="importexcel" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">推送时间：</label>
            <div class="controls">
                <label class="radio-inline">
                    <input type="radio"  value="1" name="pushWay" ${empty push || push.pushWay==1?"checked":''} ><span>立即推送</span>
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="2" name="pushWay" ${push.pushWay==2?"checked":''}><span>定时推送</span>
                </label>
            </div>
        </div>
        <div class="control-group pushWay"  style="display:none">
            <label class="control-label"></label>
            <div class="controls">
                <input id="pushTime" name="pushTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="push:sys:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/push/sms/list'"/>
        </div>
    </form:form>


</div>
</body>
<script>

    $("input[type='radio']").bind("click",function(){
        if( $(this).val() == "1"){
            $(".pushWay").css("display","none");
        }else{
            $(".pushWay").css("display","block");
        }
    })

    function save() {
        var bool = true;
        /*******  验证表单必填项目   ****************/
        $(".valid").each(function () {
            var descripe = $(this).attr("descripe");
            if ($(this).val() == "" && descripe != "") {
                top.layer.alert(descripe, {icon: 5});
                bool = false;
                return false;
            }
        })
        if (!bool) {
            return false;
        }
        uploadExcel()
    }
    function uploadExcel(){
        top.layer.load();
        $("#btnSubmit").attr("disabled",true);
        var form=$("#pushForm");
        var data = form.serialize();
        $.ajaxFileUpload({
            url: "${ctxA}/push/import?"+data,
            secureuri: false,
            fileElementId: "importexcel",
            dataType: "json",
            success: function(f) {
                top.layer.closeAll('loading');//关闭loading
                $("#btnSubmit").attr("disabled",false);
                var data = eval("("+f+")");
                if(data.code == 1){
                    window.location.href="${ctxA}/push/sms/list";
                }else{
                    top.layer.alert(data.msg,{icon: 5});
                }
            },
            error: function(g, f, h) {
                $("#btnSubmit").attr("disabled",false);
                top.layer.alert(h,{icon: 5});
            }
        });
    }
</script>
</html>