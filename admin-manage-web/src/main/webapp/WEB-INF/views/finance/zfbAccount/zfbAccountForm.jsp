<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp" %>
<!doctype html>
<html>

<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
    <!--  -->
    <style type="text/css">
    </style>
    <script type="text/javascript">
      $(function () {
        $("#inputForm").validate({
          rules: {
            name: {
              required: true,
            },
            account: {
              required: true
            },
            qrcode: {
              required: true
            }
          },
          messages: {
            name: {
              required: "必填信息"
            },
            account: {
              required: "必填信息"
            },
            qrcode: {
              required: "必填信息"
            }
          },
          submitHandler: function (form) {
            if (!qrcode.value){
              top.layer.alert("请先选择二维码", {icon: 5});
              return;
            }
            if (!imgUrls.value){
              top.layer.alert("请先添加支付宝教程", {icon: 5});
              return;
            }
            loading('正在提交，请稍等...');
            form.submit();
          },
          errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio") || element.parent().is(
                ".input-append")) {
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
    .addPic span{
        width: 100px;
        height:120px ;
        background-image: url("/static/img/upload.png");
        background-size: cover;
        background-position-x: -10px;
        display:inline-block;
        background-repeat: no-repeat;
    }
    .update {
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

    .imgPic {
        width: 100px;
        height: 100px;
        display: block;
        float: left;
    }

    .thumbImgBox {
        position: relative;
        padding: 2px;
        border: 1px solid #E8E2D9;
        float: left;
        margin-right: 15px;
        line-height: 100px;
    }

    .img-div {
        border: 1px solid #ddd;
        float: left;
        overflow: hidden;
        height: 120px;
        width: 100px;
        position: relative;
        text-align: center;
    }

    .img-div img {
        width: 100px;
        height: 120px;
        background-size: 100%;
        background-repeat: no-repeat;
    }

    .img-div span {
        /*padding: 15px;*/
        display: inline-block;
        position: absolute;
        top: 0;
        right:0;
        width:20px;
        height:20px;
        background-image: url("/static/img/delete.png");
        background-size: 100%;
    }

    #xdaTanFileImg {
        height: 120px;
        width: 100px;
        position: absolute;
        left: 0;
        opacity: 0;
        top:0;
    }

    .addPic {
        height: 120px;
        width: 100px;
        float: left;
        position: relative;
        display: block;
        margin-left: 10px;
    }

    .delete {
    }
    ul,li{ padding:0;margin:0;list-style:none}
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/finance/zfbAccount/list?productId=${zfbAccountVo.productId}">支付宝账号列表</a></li>
    <li class="active">
        <shiro:hasPermission name="finance:zfbAccount:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}支付宝账号</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="finance:zfbAccount:edit">
            <a href="javascript:void(0);">查看支付宝账号</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="zfbAccountVo" action="${ctxA}/finance/zfbAccount/save"
               method="post" class="form-horizontal">
        <input type="hidden" id="id" name="id" value="${zfbAccountVo.id}">
        <input type="hidden" id="productId" name="productId" value="${zfbAccountVo.productId}">
        <div class="control-group">
            <label class="control-label">账号名称：</label>
            <div class="controls">
                <input type="text" id="name" name="name" maxlength="32" class="input-xlarge valid"  descripe="请输入账号名称"  value="${zfbAccountVo.name}">
                <span class="help-inline">
                        <font color="red">*</font>
                    </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">支付宝账号：</label>
            <div class="controls">
                <input type="text" id="account" name="account"  descripe="请输入支付宝账号"  maxlength="32" class="input-xlarge valid" value="${zfbAccountVo.account}">
                <span class="help-inline">
                        <font color="red">*</font>
                    </span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">二维码：</label>
            <div class="controls">
                <input type="hidden" id="qrcode" name="qrcode" value="${zfbAccountVo.qrcode}" class="valid" descripe="请上传二维码图片" >
                <div class="thumbImgBox">
                    <img id="imgPic" class="imgPic"
                         src="${not empty zfbAccountVo.qrcode?zfbAccountVo.qrcode:'/static/img/dftimage.png'}" >
                    <input type="file" style="margin-left:40px;" id="new_file" placeholder="请选择文件">
                    <div class="update" id="update" style="margin-right:40px;" type="primary">上传图片
                    </div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：200*200</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">支付宝教程：</label>
            <div class="controls">
                <input type="hidden" id="imgUrls" name="imgUrls" value="${zfbAccountVo.imgUrls}" class="valid" descripe="请上传支付宝教程图片">
                <div class="img-box" id="imgboxid">
                </div>
                <li class="addPic">
                    <span></span>
                    <input type="file" id="xdaTanFileImg" multiple="multiple"
                           onchange="xmTanUploadImg(this)"/>
                </li>
            </div>
        </div>


        <div class="form-actions">
            <shiro:hasPermission name="finance:zfbAccount:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="save()"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回"
                   onclick="window.location.href='${ctxA}/finance/zfbAccount/list?productId=${zfbAccountVo.productId}'"/>
        </div>
    </form:form>


</div>
</body>
<script>
  var picData = []
  let chooseData = $("#imgUrls").val();
  var imgLength = 0;
  if(chooseData){
      chooseData = chooseData.split(',');
      imgLength = chooseData.length
  }

  if ( imgLength > 0) {
    for (let i = 0; i < chooseData.length; i++) {
      let data = {
        url: chooseData[i]
      }
      picData.push(data)
    }
    proMiss(picData)
  }
  var enableFileTypes = 'png,jpg,jpeg';
  $('#update').click(() => {
    let oMyForm = new FormData();
    oMyForm.append("file", $('#new_file')[0].files[0]);
    let url = '${ctxA}/common/uploadImage?maxFileSize=100&enableFileTypes=' + enableFileTypes;
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
            $('#qrcode').val(newData.url);
          } else {
            top.$.jBox.tip(newData.msg);
          }
        },
        error: function (data) {
          window.alert(data.msg);
        }
      })
    }
  })

  function xmTanUploadImg(obj) {

    let url = '${ctxA}/common/uploadImage?maxFileSize=200&enableFileTypes=' + enableFileTypes;
    var fl = obj.files.length;
    if (imgLength > 15) {
      top.layer.alert("最多只能上传十五张图片", {icon: 5});
      $("#xdaTanFileImg").val("");
      return ;
    }
      imgLength = imgLength+fl
      for (var i = 0; i < fl; i++) {
      var file = obj.files[i];
      let data = new FormData();
      data.append("file", file);
      $.ajax({
        url: url,
        type: "POST",
        data: data,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: (data) => {
          let newData = JSON.parse(data)
          if (newData.success) {
            let chooseData = {
              url: newData.url
            }
            picData.push(chooseData)
          } else {
            top.$.jBox.tip(newData.msg);
          }
          if (i == fl-1){
            $("#xdaTanFileImg").val("");
          }
        },
        error: function (data) {
          window.alert(data.msg);
        }
      })
    }
    proMiss(picData)
    $('#imgUrls').val(getImgUrls(picData));
  }

  function deletePic(n) {
    console.log(n)
    picData.splice(n, 1)
    console.log(picData)
    // $('#imgUrls').val(JSON.stringify(picData));
    $('#imgUrls').val(getImgUrls(picData));
    var s = document.getElementById("imgboxid");
    s.removeChild(s.childNodes[n + 1]);
    imgLength = imgLength - 1
    $("#xdaTanFileImg").val("");

  }

  function getImgUrls(picData) {
    var imgUrls;
    for (let i = 0; i < picData.length; i++) {
      if (i == 0) {
        imgUrls = picData[i].url;
      } else {
        imgUrls = imgUrls + "," + picData[i].url;
      }
    }
    return imgUrls;
  }

  function proMiss(picData) {
    $("#imgboxid li").remove()
    for (let i = 0; i < picData.length; i++) {
      var oimgbox = document.getElementById("imgboxid");
      var imgstr = '<img  src="' + picData[i].url + '"/><span class="delete" onclick="deletePic('
          + i + ')"></span>';
      var ndiv = document.createElement("li");
      ndiv.innerHTML = imgstr;
      ndiv.className = "img-div";
      oimgbox.appendChild(ndiv);
    }
  }
  function save(){
      var bool = true;
      /*******  验证表单必填项目   ****************/
      $(".valid").each(function() {
          var descripe  = $(this).attr("descripe");
          if( $(this).val()=="" && descripe!=""){
              top.layer.alert(descripe, {icon: 5});
              bool = false;
              return false;
          }
      })
      if (imgLength > 5) {
          top.layer.alert("教程最多只能上传十五张图片", {icon: 5});
          return fal;
      }
      if( !bool ){
          return false;
      }
      $("#btnSubmit").attr("disabled",true);
      var form=$("#inputForm");
      var action = form[0].action;
      var data = form.serialize();
      jQuery.post(action,data, function(data) {
          $("#btnSubmit").attr("disabled",false);
          if (data.code ==1) {
              top.layer.alert("操作成功", {
                  icon: 6,
                  end: function(){
                      window.location.href="${ctxA}/finance/zfbAccount/list?productId="+$("#productId").val();
                  }
              });
          } else {
              top.layer.alert(data.msg, {icon: 5});
          }
      }, "json");
  }
</script>

</html>