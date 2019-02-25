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
            url:'${ctxA}/ms/jc/jpushChannelList',
            data:'jpushChannelCode='+$('#jpushChannelCode').val()+'&jpushChannelName='+$('#jpushChannelName').val()+'&page='+pageNum+'&pagesize='+pageSize,
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

        $('#reset').click(function(){
          $("#jpushChannelCode").val(null);
          $("#jpushChannelName").val(null);
        });

      });

      function statusformater(value,row,index){
        if(value=='1'){
          return "启用";
        }else if(value=='2'){
          return "禁用";
        }
        return '未知';
      }

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="ms:jc:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/ms/jc/form?id='+row.id+'">编辑</a>';
        opStr+='<a class="si-option-a" href="${ctxA}/ms/jc/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该配置吗？\', this.href)">删除</a>';
        if (row.status == '2'){
          opStr+='<a class="si-option-a" href="${ctxA}/ms/jc/changeState?id='+row.id+'&status=1">开启</a>';
        }else if (row.status == '1') {
          opStr+='<a class="si-option-a" href="${ctxA}/ms/jc/changeState?id='+row.id+'&status=2">禁用</a>';
        }
        </shiro:hasPermission>
        return opStr;
      }

	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">推送渠道列表</a></li>
	<shiro:hasPermission name="ms:jc:edit">
		<li><a href="${ctxA}/ms/jc/form">新增推送渠道</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>渠道名称：</label>
			<input id="jpushChannelName" placeholder="请输入渠道名称" class="input-large" type="text"  value=""
				   maxlength="20"/>
		</li>
		<li>
			<label>渠道编码：</label>
			<input id="jpushChannelCode" placeholder="请输入渠道编码" class="input-large" type="text" value=""
				   maxlength="50" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
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
		   data-options="idField:'jpushChannelId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'platform',width:90,align:'center',halign:'center',fixed:true">平台编码</th>
			<th data-options="field:'jpushChannelCode',width:170,align:'center',halign:'center',fixed:true">渠道编码</th>
			<th data-options="field:'jpushChannelName',width:90,align:'center',halign:'center',fixed:true">渠道名称</th>
			<th data-options="field:'appkey',width:200,align:'center',halign:'center',fixed:true">账号</th>
			<th data-options="field:'secret',width:200,align:'center',halign:'center',fixed:true">密码</th>
			<th data-options="field:'status',width:75,align:'center',halign:'center',fixed:true,formatter:statusformater">渠道状态</th>
			<th data-options="field:'option',width:120,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>