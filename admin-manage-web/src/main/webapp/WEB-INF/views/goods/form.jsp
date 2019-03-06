<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,select2"/>
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
        .thumbImgBox {
            position: relative;
            padding: 2px;
            border: 1px solid #E8E2D9;
            float: left;
            margin-right: 15px;
            height: 100px;
            line-height: 100px;
        }
        .thumbImgBox img{
            width: 100px;
            height: 100px;
            display: block;
            float: left;
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
        .delete {
        }
        ul,li{ padding:0;margin:0;list-style:none}
    </style>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/goods/list">产品列表</a></li>
    <li class="active">
        <shiro:hasPermission name="goods:list:edit">
            <a href="javascript:void(0);">${not empty goods.id?'修改':'添加'}商户</a>
        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="goodsForm" modelAttribute="goods"   action="${ctxA}/goods/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty goods.id?goods.id:''}">
        <input type="hidden" name="id" value="${not empty goods.goodsNo?goods.goodsNo:''}">
        <div class="control-group">
            <label class="control-label">商品名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写商品名称" type="text" name="goodsName" id="goodsName" maxlength="64" value="${product.goodsName}" style="width: 300px;"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品图标：</label>
            <div class="controls">
                <form:hidden path="goodsIcon" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic1" class="imgPic1"
                         src="${not empty goods.goodsIcon?goods.goodsIcon:'/static/img/dftimage.png'}" >
                    <input type="file" style="margin-left:40px;" id="new_file1" placeholder="请选择文件">
                    <div class="update" id="update1" sort="1" data-url="goodsIcon" filename ="new_file1" style="margin-right:40px;" type="primary">上传图片
                    </div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：100*100</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">缩略图：</label>
            <div class="controls">
                <form:hidden path="thumbnailIcon" htmlEscape="false"/>
                <div class="thumbImgBox">
                    <img id="imgPic2" class="imgPic2"
                         src="${not empty goods.thumbnailIcon?goods.thumbnailIcon:'/static/img/dftimage.png'}" >
                    <input type="file" style="margin-left:40px;" id="new_file2" placeholder="请选择文件">
                    <div class="update" id="update2" sort="2" data-url="thumbnailIcon" filename ="new_file2" style="margin-right:40px;" type="primary">上传图片
                    </div>
                </div>
                <div class="controls">
                    <ul style="margin-left: 6%;">
                        <small class="help-block owner_ID">建议尺寸：100*100</small>
                        <small class="help-block owner_ID">图片格式：PNG、JPG、JPEG</small>
                        <small class="help-block owner_ID">图片大小：100kb以内</small>
                    </ul>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品轮播图：</label>
            <div class="controls">
                <input type="hidden" id="bannerUrls" name="bannerUrls" value="${bannerUrls}">
                <div class="img-box" id="imgboxid1">
                </div>
                <li class="addPic">
                    <span></span>
                    <input type="file"  data-url="bannerUrls"  id="xdaTanFileImg" data-type="banner" multiple="multiple"
                           onchange="xmTanUploadImg(this)"/>
                </li>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">产品轮播图：</label>
            <div class="controls">
                <input type="hidden" id="detailUrls" name="bannerUrls" value="${detailUrls}">
                <div class="img-box" id="imgboxid2">
                </div>
                <li class="addPic">
                    <span></span>
                    <input type="file" data-url="detailUrls" id="xdaTanFileImg" data-type="detail" multiple="multiple"
                           onchange="xmTanUploadImg(this)"/>
                </li>
            </div>
        </div>
    </form:form>
</div>
</body>
<script>

    var enableFileTypes = 'png,jpg,jpeg';
    var bannerArr = [],detailArr = [];//分别定义详情图和轮播图数组
    let bannerData = $("#bannerUrls").val();
    if(bannerData){
        bannerData = bannerData.split(',');
    }
    var bannerLength = 0;
    if (bannerData.length > 0) {
        for (let i = 0; i < bannerData.length; i++) {
            let data = {
                url: bannerData[i]
            }
            bannerArr.push(data)
        }
        proMiss(bannerArr,'banner',1)
    }
    let detailData = $("#detailUrls").val();
    if(detailData){
        detailData = detailData.split(',')
    }
    var detailLength = 0
    if (detailData.length > 0) {
        for (let i = 0; i < detailData.length; i++) {
            let data = {
                url: detailData[i]
            }
            detailArr.push(data)
        }
        proMiss(detailArr,'detail','2')
    }
    $(function(){
        $(".update").each(function() {
            var self = $(this);
            var s = self.attr('sort');
            var filename = self.attr('filename');
            var dataUrl = self.attr('data-url');
            $('#update'+s).click(() => {
                let oMyForm = new FormData();
                oMyForm.append("file", $('#'+filename)[0].files[0]);
                let url = '${ctxA}/common/uploadImage?maxFileSize=100&enableFileTypes=' + enableFileTypes;
                if ($('#'+filename)[0].files[0]) {
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
                               $("#imgPic"+s).attr("src", newData.url);
                               $('#'+dataUrl).val(newData.url);
                            }else {
                               top.$.jBox.tip(newData.msg);
                            }
                        },
                        error: function (data) {
                            window.alert(data.msg);
                        }
                    })
                 }
             })
        })
    })

    function xmTanUploadImg(obj) {

        let url = '${ctxA}/common/uploadImage?maxFileSize=100&enableFileTypes=' + enableFileTypes;
        var fl = obj.files.length;
        var type = $(obj).attr("data-type");
        var dataUrl = $(obj).attr("data-url");
        if (detailLength > 11 || bannerLength > 11) {
            top.layer.alert("最多只能上传十一张图片", {icon: 5});
            $("."+dataUrl).val("");
            return ;
        }
        if( type == "banner"){
            bannerLength = bannerLength+fl;
        }else{
            detailLength = detailLength+fl;
        }
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
                            if( type == "banner"){
                                bannerArr.push(chooseData);
                            }else{
                                detailArr.push(chooseData);
                            }
                        } else {
                            top.$.jBox.tip(newData.msg);
                        }
                        if (i == fl-1){
                            $("."+dataUrl).val("");
                        }
                    },
                    error: function (data) {
                        window.alert(data.msg);
                    }
            });
        }
        if( type == "banner"){
            proMiss(bannerArr,type,"1");
            $('#bannerUrls').val(getImgUrls(bannerArr));
        }else{
            proMiss(detailArr,type,"2");
            $('#detailUrls').val(getImgUrls(detailArr));
        }

    }

    /**
     * 删除图片
     * @param n
     * @param type
     */
    function deletePic(n,type) {
        console.log(n)
        if( type == 'banner'){
            bannerArr.splice(n, 1)
            $('#bannerUrls').val(getImgUrls(bannerArr));
            var s = document.getElementById("imgboxid1");
            s.removeChild(s.childNodes[n + 1]);
            bannerLength = bannerLength - 1
            $(".bannerUrls").val("");
        }else{
            detailArr.splice(n, 1)
            $('#detailUrls').val(getImgUrls(detailArr));
            var s = document.getElementById("imgboxid2");
            s.removeChild(s.childNodes[n + 1]);
            detailLength = detailLength - 1
            $(".detailUrls").val("");
        }


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

    function proMiss(picData,type,sort) {
        $("#imgboxid"+sort+" li").remove()
        for (let i = 0; i < picData.length; i++) {
            var oimgbox = document.getElementById("imgboxid"+sort);
            var imgstr = "<img  src=" + picData[i].url + "/><span class='delete' onclick='deletePic("+i+",\""+type+"\")'></span>";
            var ndiv = document.createElement("li");
            ndiv.innerHTML = imgstr;
            ndiv.className = "img-div";
            oimgbox.appendChild(ndiv);
        }
    }

</script>
</html>
