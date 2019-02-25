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
            bankName:{
              required:true,
            },
            bankIcon:{
              required:true
            },
            bankIconGrey:{
              required:true
            },
            bankIconBack:{
              required:true
            }
          },
          messages: {
            bankName:{required : "必填信息"},
            bankIcon:{required : "必填信息"},
            bankIconGrey:{required : "必填信息"},
            bankIconBack:{required : "必填信息"}
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

    .imgPicSmall{
        width:60px;
        height:60px;
        display:block;
        float:left;
    }
    .imgPicBig{
        width:234px;
        height:60px;
        display:block;
        float:left;
    }
    .thumbImgBox{
        position:relative;
        padding: 2px;
        border: 1px solid #E8E2D9;
        float: left;
        margin-right: 15px;
        line-height:60px;
    }
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/ac/bank/list?id=${object.id}">银行图标管理</a></li>
    <li class="active">
        <shiro:hasPermission name="ac:bank:edit">
            <a href="javascript:void(0);">修改银行图标</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="ac:bank:edit">
            <a href="javascript:void(0);">查看银行图标</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="bank" action="${ctxA}/ac/bank/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <form:hidden path="bankCode" />
        <div class="control-group">
            <label class="control-label">银行名称：</label>
            <div class="controls">
                <form:input path="bankName" htmlEscape="false" maxlength="50" readonly="true" class="input-xlarge"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">高亮图标：</label>
            <div class="controls">
                <form:hidden path="bankIcon" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic" class="imgPicSmall" src="${not empty bank.bankIcon?bank.bankIcon:'/static/img/dftimage.png'}"/>
                    <input type="file" style="margin-left:40px;" id="new_file" placeholder="请选择文件">
                    <div class="update" id="update" style="margin-right:40px;" type="primary">上传图片</div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：60*60</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">置灰图标：</label>
            <div class="controls">
                <form:hidden path="bankIconGrey" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic1" class="imgPicSmall" src="${not empty bank.bankIconGrey?bank.bankIconGrey:'/static/img/dftimage.png'}"/>
                    <input type="file" style="margin-left:40px;" id="new_file1" placeholder="请选择文件">
                    <div class="update" id="update1" style="margin-right:40px;" type="primary">上传图片</div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：60*60</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">背景图片：</label>
            <div class="controls">
                <form:hidden path="bankIconBack" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic2" class="imgPicBig" src="${not empty bank.bankIconBack?bank.bankIconBack:'/static/img/dftimage.png'}"/>
                    <input type="file" style="margin-left:40px;" id="new_file2" placeholder="请选择文件">
                    <div class="update" id="update2" style="margin-right:40px;" type="primary">上传图片</div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：700*180</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>


        <div class="form-actions">
            <shiro:hasPermission name="ac:bank:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/ac/bank/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
<script>
    var enableFileTypes ='png,jpg,jpeg';
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
            $('#bankIcon').val(newData.url);
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
            $('#bankIconGrey').val(newData.url);
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

  $('#update2').click(() => {
    let oMyForm = new FormData();
    oMyForm.append("file", $('#new_file2')[0].files[0]);
    console.log($('#new_file2')[0].files[0]);
    let url = '${ctxA}/common/uploadImage?maxFileSize=100&enableFileTypes='+enableFileTypes;
    if ($('#new_file2')[0].files[0]) {
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
            $("#imgPic2").attr("src", newData.url);
            $('#bankIconBack').val(newData.url);
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