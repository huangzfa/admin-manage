<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<i id="${id}Icon" class="${not empty value?value:''}" style="font-size: 20px;"></i>&nbsp;&nbsp;<label id="${id}IconLabel">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/><a id="${id}Button" href="javascript:" class="btn">选择</a>&nbsp;&nbsp;
<script type="text/javascript">
	$("#${id}Button").click(function(){
		top.$.jBox.open("iframe:${ctxA}/sys/icon/data?value="+$("#${id}").val(), "选择图标", 800, $(top.document).height()-180, {
			top: '10%',buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                	var icon = h.find("iframe")[0].contentWindow.$("#icon").val();
                	$("#${id}Icon").attr("class", icon);
	                $("#${id}IconLabel").text(icon);
	                $("#${id}").val(icon);
                }else if (v=="clear"){
	                $("#${id}Icon").attr("class", "");
	                $("#${id}IconLabel").text("无");
	                $("#${id}").val("");
                }
            }, loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
            }
        });
	});
</script>