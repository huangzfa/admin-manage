<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title></title>
	<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
	<!--  -->
	<style type="text/css">
		.datagrid-btable{
			width:100% !important;
		}
		.datagrid-header-inner{
			width:100% !important;
		}
		.datagrid-htable{
			width:100% !important;
		}
	</style>
	<script type="text/javascript">
      $(function(){
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pager=$('#tt').datagrid('getPager');
        pager.pagination({
          pageSize: pageSize,//每页显示的记录条数，默认为10
          pageList: pageList,//可以设置每页记录条数的列表
          layout:['list','sep','first','prev','links','next','last','sep','manual'],
          beforePageText: '',
          afterPageText: ' 共 {pages} 页',
          displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
          onSelectPage:function(pageNumber, pageSize){
            pageSize=pageSize;
            getData(pageNumber,pageSize);
          }
        });
        $('.datagrid-pager .pagination-num').hide();
        //加载第一页数据
        getData(1,pageSize);

        function getData(pageNum, pageSize){
          $('#tt').datagrid('loading');
          hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/cp/sm/channelStyleList',
            data:'name='+$('#name').val()+'&page='+pageNum+'&pagesize='+pageSize,
            dataType:'json',
            success:function(data){
              $('#tt').datagrid('loaded');
              if(data.code==1){
                $('#tt').datagrid('loadData', data.list);
              }else{
                top.$.jBox.tip(data.msg);
              }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
              $('#tt').datagrid('loaded');
            }
          });
        }

        $('#search').click(function(){
          getData(1,pageSize);
        });

      });

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="cp:sm:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/cp/sm/form?id='+row.id+'">编辑</a>';
        opStr+='<a class="si-option-a" href="${ctxA}/cp/sm/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该配置吗？\', this.href)">删除</a>';
        </shiro:hasPermission>
        return opStr;
      }

	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">样式管理</a></li>
	<shiro:hasPermission name="cp:sm:edit">
		<li><a href="${ctxA}/cp/sm/form">新增样式</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>样式名称：</label>
			<input id="name" placeholder="请输入样式名称" class="input-large" type="text" value=""
				   maxlength="20"/>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询"/>
		</li>
	</ul>
</div>


<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'channelStyleId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'name',width:618,align:'center',halign:'center',fixed:true">样式名称</th>
			<th data-options="field:'usedChannelCount',width:232,align:'center',halign:'center',fixed:true">使用渠道</th>
			<th data-options="field:'option',width:232,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>