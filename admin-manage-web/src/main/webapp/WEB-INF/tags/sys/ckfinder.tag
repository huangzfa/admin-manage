<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="true" description="输入框" %>
<%@ attribute name="type" type="java.lang.String" required="true" description="files、images、flash、thumb" %>
<%@ attribute name="uploadPath" type="java.lang.String" required="true" description="打开文件管理的上传路径" %>
<%@ attribute name="selectMultiple" type="java.lang.Boolean" required="false" description="是否允许多选" %>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false" description="是否查看模式" %>
<%@ attribute name="maxWidth" type="java.lang.String" required="false" description="最大宽度" %>
<%@ attribute name="maxHeight" type="java.lang.String" required="false" description="最大高度" %>
<span id="${input}Preview"></span>
<c:if test="${!readonly}"><a href="javascript:" onclick="${input}FinderOpen();"
                             class="btn">${selectMultiple?'添加':'选择'}</a>&nbsp;<a href="javascript:" onclick="${input}DelAll();" class="btn">清除</a></c:if>
<script type="text/javascript">
    function ${input}FinderOpen() {//<c:if test="${type eq 'thumb'}"><c:set var="ctype" value="images"/></c:if><c:if test="${type ne 'thumb'}"><c:set var="ctype" value="${type}"/></c:if>
        var date = new Date(), year = date.getFullYear(), month = (date.getMonth() + 1) > 9 ? date.getMonth() + 1 : "0" + (date.getMonth() + 1);
        var url = "${ctxStatic}/plugin/ckfinder/ckfinder.html?type=${ctype}&action=js&func=${input}SelectAction&thumbFunc=${input}ThumbSelectAction&cb=${input}Callback&dts=${type eq 'thumb'?'1':'0'}&sm=${selectMultiple?1:0}";
        windowOpen(url, "文件管理", 800, 600);
        //top.$.jBox("iframe:"+url+"&pwMf=1", {title: "文件管理", width: 800, height: 600, buttons:{'关闭': true}});
    }

    function ${input}SelectAction(fileUrl, data, allFiles) {
        var url = "", files = ckfinderAPI.getSelectedFiles();
        for (var i = 0; i < files.length; i++) {//<c:if test="${type eq 'thumb'}">
            url += files[i].getThumbnailUrl();//</c:if><c:if test="${type ne 'thumb'}">
            url += files[i].getUrl();//</c:if>
            url = url.replace("${ctx}"+"/userfiles/", "/userfiles/");
            if (i < files.length - 1) url += "|";
        }//<c:if test="${selectMultiple}">
        $("#${input}").val($("#${input}").val() + ($("#${input}").val(url) == "" ? url : "|" + url));//</c:if><c:if test="${!selectMultiple}">
        $("#${input}").val(url);//</c:if>
        ${input}Preview();
        //top.$.jBox.close();
    }
    function ${input}ThumbSelectAction(fileUrl, data, allFiles) {
        var url = "", files = ckfinderAPI.getSelectedFiles();
        for (var i = 0; i < files.length; i++) {
            url += files[i].getThumbnailUrl();
            url = url.replace("${ctx}"+"/userfiles/", "/userfiles/");
            if (i < files.length - 1) url += "|";
        }//<c:if test="${selectMultiple}">
        $("#${input}").val($("#${input}").val() + ($("#${input}").val(url) == "" ? url : "|" + url));//</c:if><c:if test="${!selectMultiple}">
        $("#${input}").val(url);//</c:if>
        ${input}Preview();
        //top.$.jBox.close();
    }
    function ${input}Callback(api) {
        ckfinderAPI = api;
    }
    function ${input}Del(obj) {
        var url = $(obj).prev().attr("url");
        $("#${input}").val($("#${input}").val().replace("|" + url, "", "").replace(url + "|", "", "").replace(url, "", ""));
        ${input}Preview();
    }
    function ${input}DelAll() {
        $("#${input}").val("");
        ${input}Preview();
    }
    function ${input}Preview() {
        var li, urls = $("#${input}").val().split("|");
        $("#${input}Preview").text("");
        var oUrl;
        for (var i = 0; i < urls.length; i++) {
            if (urls[i] != "") {
                oUrl = urls[i];
                urls[i] = "${ctx}" + urls[i];
                li = "<img src=\"" + urls[i] + "\" url=\"" + oUrl + "\" width=\"60\" height=\"60\" style=\"max-width:200px;max-height:200px;border:0;padding:3px;\">";
                li += "<a href=\"" + urls[i] + "\" url=\"" + urls[i] + "\" target=\"_blank\">" + decodeURIComponent(urls[i].substring(urls[i].lastIndexOf("/") + 1)) + "</a>";
                li += "&nbsp;&nbsp;<c:if test="${!readonly}"><a href=\"javascript:\" onclick=\"${input}Del(this);\">×</a></c:if>";
                $("#${input}Preview").append(li);
            }
        }
        if ($("#${input}Preview").text() == "") {
            $("#${input}Preview").text("无");
        }
    }
    ${input}Preview();

</script>