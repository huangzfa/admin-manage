<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="province_id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="province_name" type="java.lang.String" required="true" description="name"%>
<%@ attribute name="province_defaultValue" type="java.lang.String" required="false" description="默认值"%>
<%@ attribute name="city_id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="city_name" type="java.lang.String" required="true" description="name"%>
<%@ attribute name="city_defaultValue" type="java.lang.String" required="false" description="默认值"%>
<%@ attribute name="district_id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="district_name" type="java.lang.String" required="true" description="name"%>
<%@ attribute name="district_defaultValue" type="java.lang.String" required="false" description="默认值"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="noSelectText" type="java.lang.String" required="false" description="不选择时的文本"%>
省：
<select _id="_province" id="${province_id}" name="${province_name}" class="select2 ${cssClass}" style="${cssStyle}" defaultValue="${province_defaultValue}" noSelectText="${noSelectText}">
	<option value="">
		<c:choose>  
		   <c:when test="${empty noSelectText}">
		   	请选择
		   </c:when>  
		   <c:otherwise>
		   ${noSelectText}
		   </c:otherwise>  
		</c:choose>  
	</option>
	
	<c:forEach items="${cfg:getProvinces()}" var="o">
		<option value="${o.id}">${o.shortName}</option>
	</c:forEach>
</select>
市：
<select _id="_city" id="${city_id}" name="${city_name}" class="select2 ${cssClass}" style="${cssStyle}" defaultValue="${city_defaultValue}">
</select>
区：
<select _id="_district" id="${district_id}" name="${district_name}" class="select2 ${cssClass}" style="${cssStyle}" defaultValue="${district_defaultValue}">
</select>
<script type="text/javascript">
	(function(){
		var $province=$("select[_id='_province']");
		var $city=$("select[_id='_city']");
		var $district=$("select[_id='_district']");
		
		var noSelectText=$province.attr('noSelectText');
		
		var pdv=$province.attr('defaultValue'),
		cdv=$city.attr('defaultValue'),
		ddv=$district.attr('defaultValue'),
		pdvFlag=false,
		cdvFlag=false,
		ddvFlag=false;
		
		$province.change(function(){
			$city.html('');
			$district.html('');
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
				        	optHtml='<option value="">'+(noSelectText==''?'请选择':noSelectText)+'</option>'+optHtml;
			        		$city.html(optHtml);
			        		if(cdv&&!cdvFlag){
			        			$city.select2().val(cdv);
			        			cdvFlag=true;
			        		}else{
			        			$city.select2().val(firstVal);
			        		}
			        		$city.change();
			        	}else{
			        		top.$.jBox.tip(data.msg);
			        	}
			        },
			        error:function(XMLHttpRequest, textStatus, errorThrown) {
			        }
			    });
			}
		});
		$city.change(function(){
			$district.html('');
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
			        		optHtml='<option value="">'+(noSelectText==''?'请选择':noSelectText)+'</option>'+optHtml;
			        		$district.html(optHtml);
			        		if(ddv&&!ddvFlag){
			        			$district.select2().val(ddv);
			        			ddvFlag=true;
			        		}else{
			        			$district.select2().val(firstVal);
			        		}
			        		$district.change();
			        	}else{
			        		top.$.jBox.tip(data.msg);
			        	}
			        },
			        error:function(XMLHttpRequest, textStatus, errorThrown) {
			        }
			    });
			}
		});
		//初始化
		if(pdv&&!pdvFlag){
			$province.select2().val(pdv);
			$province.change();
			pdvFlag=true;
		}
	})();
</script>