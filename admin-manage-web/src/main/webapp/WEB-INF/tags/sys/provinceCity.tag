<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="province_id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="province_name" type="java.lang.String" required="true" description="name"%>
<%@ attribute name="province_defaultValue" type="java.lang.String" required="false" description="默认值"%>
<%@ attribute name="city_id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="city_name" type="java.lang.String" required="true" description="name"%>
<%@ attribute name="city_defaultValue" type="java.lang.String" required="false" description="默认值"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
省：
<select _id="_province" id="${province_id}" name="${province_name}" class="${cssClass}" style="${cssStyle}" defaultValue="${province_defaultValue}">
	<option value="">请选择</option>
	<c:forEach items="${cfg:getProvinces()}" var="o">
		<option value="${o.id}">${o.shortName}</option>
	</c:forEach>
</select>
市：
<select _id="_city" id="${city_id}" name="${city_name}" class="${cssClass}" style="${cssStyle}" defaultValue="${city_defaultValue}">
</select>
<script type="text/javascript">
	(function(){
		var $province=$("select[_id='_province']");
		var $city=$("select[_id='_city']");
		
		$province.change(function(){
			$city.html('');
			if($.trim($(this).val())!=''){
				var that=$(this);
				hjnUtils.ajax({  
			        type:'post',      
			        url:'${ctxA}/sys/area/area',  
			        data:'id='+that.val(),
			        dataType:'json',  
			        success:function(data){
			        	if(data.code==1){
			        		var optHtml='';
			        		var firstVal='';
			        		$.each(data.list, function(i, a) {
			        			optHtml+='<option value="'+a.id+'">'+a.shortName+'</option>';
			        			if(i==0){
			        				firstVal=a.id;
			        			}
			        		});
			        		optHtml='<option value="">请选择</option>'+optHtml;
			        		$city.html(optHtml);
			        		$city.select2().val(firstVal);
			        		if($city.attr('defaultValue')){
			        			$city.val($city.attr('defaultValue')).trigger("change"); 
			        		}
			        	}else{
			        		top.$.jBox.tip(data.msg);
			        	}
			        },
			        error:function(XMLHttpRequest, textStatus, errorThrown) {
			        }
			    });
			}
		});
		if($province.attr('defaultValue')){
			$province.val($province.attr('defaultValue')).trigger("change"); 
			$province.change();
		}
	})();
</script>