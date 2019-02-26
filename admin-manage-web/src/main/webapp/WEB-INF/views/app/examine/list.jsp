<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
<script type="text/javascript">
	var pager;
	var pageSize=${cfg:getPageSize()};
	var pageList=[pageSize,30,50];
	var pageNum =1;
	$(function(){
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
		$('#reset').click(function(){
			$("#channelName").val(null);
			$("#versionNumber").val("");
			$("#appSelect").val(null);
		});
	});
	function getData(){
		  hjnUtils.ajax({
			  type:'post',
			  url:'${ctxA}/app/examine/getPageList',
			  dataType:'json',
			  success:function(data){
				  if(data.code==1){
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
	function appSystemTypeformater(value,row,index){
            if(value=='1'){
                return "IOS";
            }else if(value=='2'){
                return "安卓";
            }else if (value=='0') {
                return "不限制";
            }
            return '未知';
	}
	function optionformater(value,row,index){
            var opStr='';
            <shiro:hasPermission name="operat:appexam:edit">
            opStr+='<a class="si-option-a" href="${ctxA}/operat/appexam/form?id='+row.id+'">编辑</a>';
            opStr+='<a class="si-option-a" href="${ctxA}/operat/appexam/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该应用页面吗？\', this.href)">删除</a>';
            </shiro:hasPermission>
            return opStr;
	}
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">APP审核管理</a></li>
	<shiro:hasPermission name="operat:appexam:edit">
		<li><a href="${ctxA}/operat/appexam/form">新增升级配置</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>渠道：</label>
			<input id="channelName" placeholder="请输入渠道名称" class="input-large" type="text" value="" maxlength="50"/>
		</li>
		<li>
			<label>版本：</label>
			<input id="versionNumber" placeholder="请输入版本号" class="input-large" type="text" value="" maxlength="50"/>
		</li>
		<li>
			<label>应用：</label>
			<select id="appSelect" name="appSelect" class="selectpicker show-tick form-control">
				<option value="">全部</option>
			</select>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询"/>
		</li>
		<li class="clearfix">
			<input id="reset" class="btn btn-primary" type="submit" value="重置"/>
		</li>
	</ul>
</div>

<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'appExamId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'appName',width:216,align:'center',halign:'center',fixed:true">应用</th>
			<th data-options="field:'channelName',width:216,align:'center',halign:'center',fixed:true">渠道</th>
			<th data-options="field:'appSystemType',width:216,align:'center',halign:'center',fixed:true,formatter:appSystemTypeformater">系统</th>
			<th data-options="field:'versionNumber',width:216,align:'center',halign:'center',fixed:true">版本号</th>
			<th data-options="field:'option',width:216,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>