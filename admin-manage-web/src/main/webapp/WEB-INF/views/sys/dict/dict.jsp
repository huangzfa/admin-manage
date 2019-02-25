<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,layer"/>
<!--  -->
<style type="text/css">
.groupSort{
	width: 40px;
}
.typeSort{
	width: 40px;
}
.ml0{
	margin-left: 0 !important;
}
</style>
<script type="text/javascript">
$(function(){
	var pageSize=50;
	var pageList=[50];
	$('#group').datagrid({onClickRow:function(index, row){
		getTypeData(row.id);
	}});
	//加载第一页数据
	getGroupData();
	
	function getGroupData(){
		var data={};
		$('#group').datagrid('loading');
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/dict/dictTypetData',  
	        data:data,
	        dataType:'json',  
	        success:function(data){
	        	$('#group').datagrid('loaded');
	        	if(data.code==1){
	        		$('#group').datagrid('loadData', data.list); 
	        		$('#group').datagrid('selectRow',0);
	        		var firstRow=$('#group').datagrid('getSelected');
	        		getTypeData(firstRow.id);
	        	}else{
	        		top.$.jBox.tip(data.msg);
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	$('#group').datagrid('loaded');
	        }
	    });
	}
	
	function getTypeData(pId){
		var data={};
		data.pId=pId;
		$('#type').datagrid('loading');
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/dict/dictItemData',  
	        data:data,
	        dataType:'json',  
	        success:function(data){
	        	$('#type').datagrid('loaded');
	        	if(data.code==1){
	        		$('#type').datagrid('loadData', data.list); 
	        	}else{
	        		top.$.jBox.tip(data.msg);
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	$('#type').datagrid('loaded');
	        }
	    });
	}
	
	$('#updateHouseTypeSort').click(function(){
		confirmx('确定要修改顺序吗?',function(){
			var sorts=[];
			$("input[class='houseTypeSort']").each(function(){
				sorts.push({houseTypeId:$(this).attr('houseTypeId'),sort:$(this).val()});
			});
			hjnUtils.ajax({  
		        type:'post',      
		        url:'${ctxA}/portal/menu/updateMenuSort',  
		        dataType:'json',  
		        data:{menuSorts:JSON.stringify(sorts)},
		        success:function(data){
		        	top.$.jBox.tip(data.msg);
		        	if(data.code){
		        		window.location.href="${ctxA}/portal/menu/list";
		        	}
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        }
		    });
		});
	});
});
function groupSortformater(value,row,index){
	return '<input class="groupSort" type="text" facilityGroupId="'+row.facilityGroupId+'" value="'+value+'"/>';
}
function groupOptionformater(value,row,index){
	var opStr='';
	return opStr;
		<shiro:hasPermission name="house:config:edit">
		opStr+='<a class="si-option-a" href="${ctxA}/house/config/houseTypeForm?houseTypeId='+row.facilityGroupId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/house/config/houseTypedelete?houseTypeId='+row.facilityGroupId+'" onclick="return confirmx(\'确定要删除该房屋类型吗？\', this.href)">删除</a>';
		</shiro:hasPermission>
	return opStr;
}
function typeSortformater(value,row,index){
	return '<input class="typeSort" type="text" facilityTypeId="'+row.facilityTypeId+'" value="'+value+'"/>';
}
function typeOptionformater(value,row,index){
	var opStr='';
	return opStr;
		<shiro:hasPermission name="house:config:edit">
		opStr+='<a class="si-option-a" href="${ctxA}/house/config/houseTypeForm?houseTypeId='+row.facilityTypeId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/house/config/houseTypedelete?houseTypeId='+row.facilityTypeId+'" onclick="return confirmx(\'确定要删除该房屋类型吗？\', this.href)">删除</a>';
		</shiro:hasPermission>
	return opStr;
}

</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
  <li class="active"><a href="javascript:void(0);">数据字典列表</a></li>
  <!-- 
  <shiro:hasPermission name="portal:menu:edit">
  	<li><a href="${ctxA}/portal/menu/form">添加配套设施组</a></li>
  	<li><a href="${ctxA}/portal/menu/form">添加配套设施类型</a></li>
  </shiro:hasPermission>
   -->
</ul>
<!-- 
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li class="btns">
			<shiro:hasPermission name="portal:menu:edit">
			<input id="updateHouseTypeSort" class="btn btn-primary" type="submit" value="保存顺序"/>
			</shiro:hasPermission>
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
 -->
<div class="si-warp" style="top:45px;">
	<sys:message content="${message}" isShowBox="false"/>
	
	<div style="float: left;width: 35%;height: 100%;">
		<table id="group" class="easyui-datagrid"
			data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true">
		    <thead>   
		        <tr>   
		            <th data-options="field:'dicCode',width:130,halign:'center',align:'center',fixed:true">字典类型</th>
		            <th data-options="field:'dicVal',width:130,halign:'center',align:'center',fixed:true">字典类型值</th>
		            <th data-options="field:'option',width:120,align:'left',halign:'center',fixed:true,formatter:groupOptionformater">操作</th>
		        </tr>   
		    </thead>   
		</table>  
	</div>
	<div style="float: right;width: 64%;height: 100%;">
		<table id="type" class="easyui-datagrid"
			data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true">
		    <thead>   
		        <tr>   
		            <th data-options="field:'dicCode',width:200,halign:'center',align:'center',fixed:true">字典类型</th>
		            <th data-options="field:'dicVal',width:130,halign:'center',align:'center',fixed:true">字典类型值</th>
		            <th data-options="field:'option',width:120,align:'left',halign:'center',fixed:true,formatter:typeOptionformater">操作</th>
		        </tr>   
		    </thead>   
		</table>  
	</div>
	
</div>
</body>
</html>