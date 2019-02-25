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
            name:{
              required:true,
            },
            buttonTemplate:{
              required:true
            },
            buttonText:{
              required:true
            },
            buttonBackground:{
              required:true
            },
            imageUrl:{
              required:true
            }
          },
          messages: {
            name:{required : "必填信息"},
            buttonTemplate:{required : "必填信息"},
            buttonText:{required : "必填信息"},
            buttonBackground:{required : "必填信息"},
            imageUrl:{required : "必填信息"}
          },
          submitHandler: function(form){
            if (!imageUrl.value){
              top.layer.alert("请先选择图片", {icon: 5});
              return;
            }
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
    </script>
</head>
<style>
    .update{
        padding: 9px 15px;
        font-size: 12px;
        border-radius: 3px;
        display: inline-block;
        line-height: 1;
        white-space: nowrap;
        cursor: pointer;
        background: #006dcc;
        border: 1px solid #dcdfe6;
        color: #fff;
        -webkit-appearance: none;
        text-align: center;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        outline: 0;
        margin: 0;
        -webkit-transition: .1s;
        transition: .1s;
        font-weight: 500;
        padding: 9px 10px;
        font-size: 10px;
        border-radius: 4px;
    }

    .imgPic{
        width:75px;
        height:133px;
        display:block;
        float:left;
    }
    .thumbImgBox{
        position:relative;
        padding: 2px;
        border: 1px solid #E8E2D9;
        float: left;
        margin-right: 15px;
        line-height:133px;
    }
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/cp/sm/list?id=${object.id}">样式列表</a></li>
    <li class="active">
        <shiro:hasPermission name="cp:sm:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}样式</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="cp:sm:edit">
            <a href="javascript:void(0);">查看样式</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="channelStyle" action="${ctxA}/cp/sm/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <div class="control-group">
            <label class="control-label">样式名称：</label>
            <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label">按钮模板：</label>
            <div class="controls">
                <form:select path="buttonTemplate" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('channelStylebtTempt')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">按钮文案：</label>
            <div class="controls">
                <form:input path="buttonText" htmlEscape="false" maxlength="10" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">按钮背景：</label>
            <div class="controls">
                <form:input path="buttonBackground" htmlEscape="false" maxlength="10" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">背景图：</label>
            <div class="controls">
                <form:hidden path="imageUrl" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic" class="imgPic" src="${not empty channelStyle.imageUrl?channelStyle.imageUrl:'/static/img/dftimage.png'}"/>
                    <input type="file" style="margin-left:40px;" id="new_file" placeholder="请选择文件">
                    <div class="update" id="update" style="margin-right:40px;" type="primary">上传图片</div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：750*1334</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>


        <div class="form-actions">
            <shiro:hasPermission name="cp:sm:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/cp/sm/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
<script>
  var enableFileTypes ='png,jpg';
  $('#update').click(() => {
    let oMyForm = new FormData();
    oMyForm.append("file", $('#new_file')[0].files[0]);
    console.log($('#new_file')[0].files[0]);
    let url = '${ctxA}/common/uploadImage?maxFileSize=100&enableFileTypes='+enableFileTypes;
    if ($('#new_file')[0].files[0]) {
      $.ajax({
        url: url,
        type: "POST",
        data: oMyForm,
        cache: false,
        contentType: false,
        processData: false,
        success: (data) => {
          let newData = JSON.parse(data)
          if (newData.success) {
            $("#imgPic").attr("src", newData.url);
            $('#imageUrl').val(newData.url);
          } else {
            top.$.jBox.tip(newData.msg);
          }
        },
        error: function (data) {
          window.alert(data.msg);
        }
      })
    }else {
      top.layer.alert("请先选择图片", {icon: 5});
    }
  })

</script>
</html>