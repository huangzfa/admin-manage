<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
    var pager;
    var bannerType = [];
    var pageSize=${cfg:getPageSize()};
    var pageList=[pageSize,30,50];
    var pageNum =1;
    $(function(){
        var typeList ='${bannerType}';
        bannerType = eval("("+typeList+")");
        for( var i = 0;i<bannerType.length;i++){
            $("#bannerType").append("<option value='"+bannerType[i].dicVal+"'>"+bannerType[i].dicCode+"</option>");
		}
        var appLists ='${appLists}';
        list = eval("("+appLists+")");
        var appId = '${appId}';
        for( var i = 0;i<list.length;i++){
            $("#appId").append("<option value='"+list[i].id+"'>"+list[i].appName+"</option>");
        }
        if( appId!=''){
            $("#appId").val(appId);
        }else{
            $("#appId").val(list[0].id);
        }

        pager=$('#tt').datagrid('getPager');
        pager.pagination({
            onSelectPage:function(number, size){
                pageSize = size;
                pageNum = number;
                getData();
            }
        });
        $('.datagrid-pager .pagination-num').hide();
        //加载第一页数据
        getData();
        $('#search').click(function(){
            pageNum = 1;
            getData();
        });

    });
    function getData(){
        var data = {
            'isEnable':$('#isEnable').val(),
			'bannerTitle':$('#bannerTitle').val(),
            'bannerType':$('#bannerType').val(),
			'appId':$("#appId").val(),
			'page':pageNum,
			'pagesize':pageSize,
      /*      'startDate':$('#startDate').val(),
            'endDate':$('#endDate').val()*/

		}
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/market/banner/bannerList',
            data:data,
            dataType:'json',
            success:function(data){
                if(data.code==1){
                    $('#menuOperation').attr("href","${ctxA}/market/banner/form?appId="+ $("#appId").val());
                    $('#tt').datagrid('loadData', data.list);
                    pager.pagination({
                        pageSize: pageSize,//每页显示的记录条数，默认为10
                        pageList: pageList,//可以设置每页记录条数的列表
                        pageNumber:pageNum,
                        layout:['list','sep','first','prev','links','next','last','sep','manual'],
                        beforePageText: '',
                        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                        afterPageText: ' 共 {pages} 页'
					})
                }else{
                    top.$.jBox.tip(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                $('#tt').datagrid('loaded');
            }
        });
    }
    function iconurlformater(value,row,index){
        if(value!="" && value!=null){

            return opStr='<img src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'

        }else{

            return '';
        }
    }
    function stateformater(value,row,index){
        if(value=='1'){

            return "启用";

        }else if(value=='0'){

            return "禁用";

        }
        return '未知';
    }

    function optionformater(value,row,index){

        var opStr='';
        <shiro:hasPermission name="market:banner:edit">
			opStr+='<a class="si-option-a" href="${ctxA}/market/banner/form?id='+row.id+'&appId='+ row.appId+'">修改</a>';
			opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
			var isEnable = 0;
			if( row.isEnable == 0) isEnable = 1;
			opStr+="<a class='si-option-a' href='javascript:editState(\""+row.id+"\",\""+isEnable+"\")'>"+(row.isEnable==0?"启用":"禁用")+"</a>";
        </shiro:hasPermission>
        return opStr;
    }

    function typeformater(value,row,index){
        var v = "未知"
		for(var i = 0;i<bannerType.length;i++){
		    if(bannerType[i].dicVal == value){
                v = bannerType[i].dicCode;
                break;
			}
		}
		return v;
	}
    function redirectformater(value,row,index) {
        if(value=='no'){

            return "无连接";

        }else if(value=='url'){

            return "h5链接";

        }
        return '未知';
    }
    function del(id){
        top.$.jBox.confirm("确定删除该轮播图吗吗",'系统提示',function(v,h,f){
            if(v=='ok'){
                jQuery.post("${ctxA}/market/banner/delete", {'id':id},
                    function(data) {
                        if (data.code ==1) {
                            top.layer.alert("操作完成", {
                                icon: 6,
                                end: function(){
                                    pageNum = 1;
                                    getData();
                                }
                            });
                        } else {
                            top.layer.alert(data.msg, {icon: 5});
                        }
                        return;
                    }, "json");
            }
        })
	}

    function editState(id,isEnable){
        var msg = "确定禁用该轮播图吗";
        if( isEnable == 1){
            msg = "确定启用该轮播图吗";
		}
        top.$.jBox.confirm(msg,'系统提示',function(v,h,f){
            if(v=='ok'){
                jQuery.post("${ctxA}/market/banner/editState", {'id':id,'isEnable':isEnable},
                    function(data) {
                        if (data.code ==1) {
                            top.layer.alert("操作完成", {
                                icon: 6,
                                end: function(){
                                    getData();
                                }
                            });
                        } else {
                            top.layer.alert(data.msg, {icon: 5});
                        }
                        return;
                    }, "json");
            }
        })
    }
   
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">轮播列表</a></li>
	<shiro:hasPermission name="market:banner:edit">
		<li><a id="menuOperation"  href="">添加轮播图</a></li>
	</shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">
		<li>
			<label>选择应用：</label>
			<select id="appId" name="appId" class="selectpicker show-tick form-control">
			</select>
		</li>
		<li>
			<label>轮播图名称：</label>
			<input id="bannerTitle" placeholder="请输入轮播图名称" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>轮播位置：</label>
			<select  name="bannerType" id="bannerType" class="selectpicker show-tick form-control" >
				<option value="">请选择轮播位置</option>
			</select>
		</li>
		<li>
			<label>状态：</label>
			<select  name="isEnable" id="isEnable" class="selectpicker show-tick form-control" >
					<option value="">状态</option>
					<option value="1">已启用</option>
					<option value="0">已禁用</option>
			</select>
		</li>
	<%--	<li>
		    <label>添加时间：</label>
			<input id="startDate" class="input-small" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			-
			<input id="endDate"  class="input-small" type="text" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
		</li>--%>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'id',width:80,align:'center',halign:'center',fixed:true">id</th>
			<th data-options="field:'imgUrl',width:120,align:'center',halign:'center',fixed:true,formatter:iconurlformater">图片</th>
			<th data-options="field:'bannerTitle',width:160,align:'center',halign:'center',fixed:true">轮播名称</th>
			<th data-options="field:'bannerType',width:160,align:'center',halign:'center',fixed:true,formatter:typeformater">所属位置</th>
			<th data-options="field:'redirectType',width:160,align:'center',halign:'center',fixed:true,formatter:redirectformater">类型</th>
			<th data-options="field:'redirectUrl',width:160,align:'center',halign:'center',fixed:true">链接</th>
			<th data-options="field:'remark',width:160,align:'center',halign:'center',fixed:true">备注说明</th>
			<th data-options="field:'isEnable',width:45,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
			<th data-options="field:'sort',width:160,align:'center',halign:'center',fixed:true">排序</th>
			<th data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">添加时间</th>
			<th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>