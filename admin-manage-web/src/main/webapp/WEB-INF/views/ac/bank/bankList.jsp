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
		.imageSmall{
			width: 60px;
			height: 60px;
		}

		.imageBig{
			width: 234px;
			height: 60px;
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
            //alert("pageNumber:"+pageNumber+";pageSize:"+pageSize);
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
            url:'${ctxA}/ac/bank/bankList',
            data:'bankName='+$('#bankName').val()+'&page='+pageNum+'&pagesize='+pageSize,
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

      function bankIconformater(value,row,index){
        var opStr='';
        opStr='<img class="imageSmall" src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'
        return opStr;
      }

      function bankIconGreyformater(value,row,index){
        var opStr='';
        opStr='<img class="imageSmall" src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'
        return opStr;
      }

      function bankIconBackformater(value,row,index){
        var opStr='';
        opStr='<img class="imageBig" src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'
        return opStr;
      }

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="ac:bank:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/ac/bank/form?id='+row.id+'">编辑</a>';
        </shiro:hasPermission>
        return opStr;
      }
	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">银行图标管理</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>银行名称：</label>
			<input id="bankName" placeholder="请输入银行名称" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>

<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'bankId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'bankName',width:200,align:'center',halign:'center',fixed:true">银行名称</th>
			<th data-options="field:'bankIcon',width:200,align:'center',halign:'center',fixed:true,formatter:bankIconformater">高亮图标</th>
			<th data-options="field:'bankIconGrey',width:200,align:'center',halign:'center',fixed:true,formatter:bankIconGreyformater">置灰图标</th>
			<th data-options="field:'bankIconBack',width:264,align:'center',halign:'center',fixed:true,formatter:bankIconBackformater">背景颜色</th>
			<th data-options="field:'option',width:216,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>