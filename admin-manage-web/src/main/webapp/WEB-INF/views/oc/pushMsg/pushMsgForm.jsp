<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ajaxfileupload"/>
    <!--  -->
    <style type="text/css">
        label{
            width: 10%;
        }
    </style>

</head>
<style>
</style>
<script>
    $(function(){

        $(".pushTimeConfigInput").bind("click",function(){
            if ($(this).val() == 1) {
                $("#setTimeDiv").show();
            }else{
                $("#setTiming").val(null);
                $("#setTimeDiv").hide();
            }
        })
        $(".platformDiv button").bind("click",function(){
            if($(this).hasClass("btn-primary")){
                $(this).removeClass("btn-primary");
                $(this).removeClass("plateform");
            }else{
                $(this).addClass("btn-primary");
                $(this).addClass("plateform");
            }

        })
        $(".pushUserDiv button").bind("click",function(){
            $(".pushUserDiv button").removeClass("btn-primary");
            $(".pushUserDiv button").attr("id",null);
            $(this).addClass("btn-primary");
            $(this).attr("id","pushUser");
            if ($(this).val() == 1) {
                $("#user_name").val(null);
                $("#file").val(null);
                $("#uploadFile").hide();
            }else{
                $("#uploadFile").show();
            }
        })
    })
    function sendMessage() {
        top.$.jBox.confirm("确认推送？",'消息推送',function(v,h,f){
            if(v=='ok'){
            var src = "${ctxA}/oc/pushMsg/sendMessage";
            var txt = "pushType=" + $('#pushType').val();
            txt = txt + "&title=" + $('#title').val();
            //推送内容
            var content = $('#content').val();
            if(content == null || content == ''){
                top.layer.alert("内容不能为空",{icon: 5})
                return false;
            }
            txt = txt + "&content=" + content;
            //推送平台
            var plateform = new Array();
            $(".plateform").each(function(key,value){
                plateform[key] = $(this).val();
            });
            if(plateform.length <1){
                top.layer.alert("请选择推送平台",{icon: 5})
                return false;
            }
            txt = txt + "&plateformData=" + plateform;
            //推送人员
            var pushUser = $("#pushUser").val();
            if (pushUser == 1) {

            }else if (pushUser == 2){
                var userNames = $("#user_name").val();
                if (userNames == '' || userNames == null) {
                    top.layer.alert("请导入名单",{icon: 5})
                    return false;
                }
                txt = txt + "&userNames=" + userNames;
            }else{
                top.layer.alert("请选择推送人员",{icon: 5})
                return false;
            }
            txt = txt + "&pushUser=" + pushUser;

            txt = txt + "&offLineSaveTime=" + $("#offLineSaveTime").val();

            var pushTimeConfig = $("input[name='pushTimeConfig']:checked").val()
            if (pushTimeConfig == 1) {
                var setTiming = $("#setTiming").val();
                if (setTiming>0 && setTiming<=1400) {
                    txt = txt + "&setTiming=" + setTiming;
                }else{
                    top.layer.alert("定速时间范围为(0,1400]",{icon: 5})
                    return false;
                }
            }
            txt = txt +"&pushTimeConfig="+pushTimeConfig;


            jQuery.post(src, txt,
                function(data) {

                    var result = eval("(" + data + ")");
                    var returnJson = eval("(" + result + ")");
                    if (returnJson.code == 1) {
                        top.layer.alert(returnJson.msg,{icon: 6,end :function(){
                                if (returnJson.url != "" && returnJson.url != null) {
                                    window.location.href = returnJson.url;
                                }
                            }
                        })

                    } else {
                        top.layer.alert(returnJson.msg,{icon: 5})
                    }
                    return;
                },
                "html");

        }
    });
    }

    function formatRepo(repo) {
        var markup = "<div>"+repo.userName+'('+repo.realName+')'+"</div>";
        return markup;
    }

    function formatRepoDisplay(repo) {
        return repo.userName+'('+repo.realName+')';
    }


    function upload(){
        var src = "/a/oc/pushMsg/upload";
        jQuery.ajaxFileUpload({
            url:src, //需要链接到服务器地址
            secureuri:false,
            fileElementId:"file", //文件选择框的id属性
            dataType: 'json',
            //服务器返回的格式类型
            success: function (result){
                var data = eval("("+result+")");
                if (data.code == 1){
                    $("#user_name").val(data.successPhone);
                    var failRowNum = data.failRowNum;
                    if(failRowNum == undefined){
                        top.layer.alert("导入成功，无导入失败的手机号码！",{icon: 6});
                    }else{
                        top.layer.alert("导入成功，导入失败的手机号码行号："+failRowNum,{icon: 7});
                    }
                }else{
                    top.layer.alert(data.msg,{icon: 5})
                }
            }
        })
    }
</script>
<body>
<ul class="nav nav-tabs">
    <li><a>推送页面</a></li>
</ul>
<div class="si-warp">
    <br/>
    <form:form    method="post" class="form-horizontal">
  <div  style="margin-left:  2%">
       <div class="control-group">
        <h3 ><strong>基础设置</strong></h3>
        </div>
    <input type="hidden" id="user_name" value="" />
        <div class="control-group">
            <label class="control-label">推送类型：</label>
            <div class="controls">
                <select id="pushType" class="selectpicker show-tick form-control">
                    <c:forEach items="${pushType}" var="o">
                        <option value="${o.dicVal}"
                                <c:if test="${o.dicSort == 1}">
                                    selected
                                </c:if>
                        >${o.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">推送标题：</label>
            <div class="controls">
                <input type="text" class="form-control"  type="text"id="title" maxlength="15" placeholder="请输入推送标题（非必填，最多输入15个字）"/>
            </div>
        </div>
    <div class="control-group">
        <label class="control-label">发送内容：</label>
        <div class="controls">
            <textarea type="text" class="form-control" id="content" placeholder="推送内容（必填，最多输入60个字）" maxlength="60"></textarea>
        </div>
    </div>
        <div class="control-group">
            <label class="control-label">推送平台：</label>
            <div class="controls">
                <div class="platformDiv" id = "platform">
                    <c:forEach items="${pushPlateforms}" var="o">
                        <div class="btn-group" style="width: 132px">
                            <button type="button" class="btn btn-default" value="${o.dicVal}" style="width: 100%" >${o.dicCode}</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    <div class="control-group">
        <label class="control-label" >推送人员：</label>
        <div class="controls">
            <div class="pushUserDiv">
                <div class="btn-group" style="padding-right: 20px;">
                    <button type="button" class="btn btn-default" value="2" style="width: 100%" >用户id</button>
                </div>
          <%--      <c:forEach items="${pushPersonnel}" var="o">
                    <div class="btn-group" style="padding-right: 20px;">
                        <button type="button" class="btn btn-default" value="${o.dicVal}" style="width: 100%" >${o.dicCode}</button>
                    </div>
                </c:forEach>--%>
            </div>
        </div>
    </div>
    <div class="control-group" id="uploadFile" style="display: none">
        <label class="control-label">导入名单：</label>
        <div class="controls" >
            <table>
                <tr>
                    <td>
                        <input name="file" id="file" type="file" />
                    </td>
                    <td>
                        <input id="submitButton" class="btn btn-primary" type="button"
                               value="导入手机号" onclick="upload()" />
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </div>
    <div  style="margin-left:  2%">
        <h3 ><strong>其他设置</strong></h3>
        <div class="control-group">
            <label class="control-label">离线消息保存时长：</label>
            <div class="controls">
                <select id="offLineSaveTime" class="selectpicker show-tick form-control">
                    <c:forEach items="${offLineSaveTime}" var="o">
                        <option value="${o.dicVal}"
                                <c:if test="${o.dicSort == 5}">
                                    selected
                                </c:if>
                        >${o.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">定速推送：</label>
            <div class="controls " >
                <c:forEach items="${setTimeSwitch}" var="o">
                    <label>
                        <input type="radio"  name="pushTimeConfig" class="pushTimeConfigInput" value="${o.dicVal}"
                        <c:if test="${o.dicSort == 1}">
                               checked
                        </c:if>
                               > ${o.dicCode}
                    </label>
                </c:forEach>
            </div>
        </div>
        <div class="control-group" id="setTimeDiv" style="display: none">
            <label class="control-label">定速设置：</label>
            <div class="controls" style="margin-top:4px" >
                推送将再<input type="number"  id="setTiming" style="width: 100px;margin: 0px 5px" min="0" max="1400" onkeyup="this.value=this.value.replace(/\D/g,'')">分钟内完成
            </div>
        </div>
    </div>
    <div class="form-actions">
        <input class="btn btn-primary" onClick="sendMessage()" value="发 送"></input>

    </div>
</div>
</form:form>
</body>
</html>