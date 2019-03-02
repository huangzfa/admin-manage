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
            menuName:{
              required:true
            },
            pageTemplet:{
              required:true
            },
            menuSort:{
              required:true,
              digits:true
            },
            iconUrl:{
              required:true
            },
            menuVal:{
              required:true
            },
            selectIconUrl:{
              required:true
            }
          },
          messages: {
            menuName:{required : "必填信息"},
            pageTemplet:{required : "必填信息"},
            menuSort:{required:"必填信息",digits:"只能输入整数"},
            iconUrl:{required : "必填信息"},
            menuVal:{required : "必填信息"},
            selectIconUrl:{required : "必填信息"}
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


      function sortFormate(){
        var inputdata = $('#menuSort').val().replace(/\D/g, '');
        console.log(inputdata)
        if (inputdata != '' && inputdata > 5) {
          inputdata = 5;
        }else if(inputdata != '' && inputdata <= 5 && inputdata > 0){
          // alert(inputdata
          inputdata = parseInt(inputdata)
        }else {
          inputdata = 1;
        }
        $('#menuSort').val(inputdata);
      }
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
        width:48px;
        height:48px;
        display:block;
        float:left;
    }
    .thumbImgBox{
        position:relative;
        padding: 2px;
        border: 1px solid #E8E2D9;
        float: left;
        margin-right: 15px;
        line-height:48px;
    }
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/app/pageConfig/list?">应用页面配置</a></li>
    <li class="active">
        <shiro:hasPermission name="app:pageConfig:edit">
            <a href="javascript:void(0);">${not empty appPageConfig.id?'修改':'添加'}配置</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="app:pageConfig:edit">
            <a href="javascript:void(0);">查看菜单</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="appPageConfig" action="${ctxA}/app/pageConfig/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <form:hidden path="appId" />
        <div class="control-group">
            <label class="control-label">菜单名称：</label>
            <div class="controls">
                <form:input path="menuName" htmlEscape="false" maxlength="4" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
     <%--   <div class="control-group">
            <label class="control-label">选择模板：</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${appPageConfig.pageTemplet != null}">
                        <form:input path="pageTemplet" htmlEscape="false"  maxlength="50" class="input-xlarge" readonly="true" />
                    </c:when>
                    <c:otherwise>
                        <form:input path="pageTemplet" htmlEscape="false" value="认证页模板" maxlength="50" class="input-xlarge" readonly="true" />
                    </c:otherwise>
                </c:choose>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>--%>
        <div class="control-group">
            <label class="control-label">排序：</label>
            <div class="controls">
                <form:input path="menuSort" id="menuSort" htmlEscape="false" maxlength="10" type="number" class="input-xlarge" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" onchange="sortFormate()"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label">icon：</label>
            <div class="controls">
                <form:hidden path="iconUrl" htmlEscape="false"/>
                <div class="thumbImgBox">
                        <img id="imgPic" class="imgPic" src="${not empty appPageConfig.iconUrl?appPageConfig.iconUrl:'/static/img/dftimage.png'}" />
                        <input type="file" style="margin-left:40px;" id="new_file" placeholder="请选择文件">
                        <div class="update" id="update" style="margin-right:40px;" type="primary">上传图片</div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：10kb以内</small>
                        <small class="help-block owner_ID">建议尺寸：48*48</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">icon点击效果：</label>
            <div class="controls">
                <form:hidden path="selectIconUrl" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic1" class="imgPic" src="${not empty appPageConfig.selectIconUrl?appPageConfig.selectIconUrl:'/static/img/dftimage.png'}" />
                    <input type="file" style="margin-left:40px;" id="new_file1" placeholder="请选择文件">
                    <div class="update" id="update1" style="margin-right:40px;" type="primary">上传图片</div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG、GIF</small>
                        <small class="help-block owner_ID">图片大小：10kb以内</small>
                        <small class="help-block owner_ID">建议尺寸：48*48</small>
                    </ul>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">模板链接：</label>
            <div class="controls">
                <form:input path="menuVal" htmlEscape="false" maxlength="200" class="input-xlarge"/>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="app:pageConfig:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/app/pageConfig/list?appId=${appPageConfig.appId}'"/>
        </div>
    </form:form>


</div>
</body>
<script>
    var enableFileTypes='png,jpg,jpeg,gif';
  $('#update').click(() => {
    let oMyForm = new FormData();
    oMyForm.append("file", $('#new_file')[0].files[0]);

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
            $('#iconUrl').val(newData.url);
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


  $('#update1').click(() => {
    let oMyForm = new FormData();
    oMyForm.append("file", $('#new_file1')[0].files[0]);
    console.log($('#new_file1')[0].files[0]);
    let url = '${ctxA}/common/uploadImage?maxFileSize=100&enableFileTypes='+enableFileTypes;
    if ($('#new_file1')[0].files[0]) {
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
            $("#imgPic1").attr("src", newData.url);
            $('#selectIconUrl').val(newData.url);
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