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
            url:'${ctxA}/fm/zfbm/zfbAccountList',
            data:'page='+pageNum+'&pagesize='+pageSize,
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
      });

      function accountStateformater(value,row,index){
        if(value=='1'){
          return "启用";
        }else if (value=='0') {
          return "禁用";
        }
        return '未知';
      }

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="fm:zfbm:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/fm/zfbm/form?id='+row.id+'">编辑</a>';
        opStr+='<a class="si-option-a" href="${ctxA}/fm/zfbm/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该配置吗？\', this.href)">删除</a>';
        if (row.accountState == '0'){
          opStr+='<a class="si-option-a" href="${ctxA}/fm/zfbm/changeState?id='+row.id+'&accountState=1 " onclick="return confirmx(\'确定要启用该配置吗？\', this.href)">启用</a>';
        }else if (row.accountState == '1') {
          opStr+='<a class="si-option-a" href="${ctxA}/fm/zfbm/changeState?id='+row.id+'&accountState=0 " onclick="return confirmx(\'确定要禁用该配置吗？\', this.href)">禁用</a>';
        }
        </shiro:hasPermission>
        return opStr;
      }

	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">支付宝账号列表</a></li>
	<shiro:hasPermission name="fm:zfbm:edit">
		<li><a href="${ctxA}/fm/zfbm/form">添加账号</a></li>
	</shiro:hasPermission>
</ul>


<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'zfbAccountId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'name',width:618,align:'center',halign:'center',fixed:true">账号名称</th>
			<th data-options="field:'accountState',width:232,align:'center',halign:'center',fixed:true,formatter:accountStateformater">状态</th>
			<th data-options="field:'option',width:232,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>