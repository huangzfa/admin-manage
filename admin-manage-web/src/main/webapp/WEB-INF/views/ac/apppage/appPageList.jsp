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
		img {
			width: 48px;
			height: 48px;
		}
	</style>
	<script type="text/javascript">
		var datalist;
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
            url:'${ctxA}/ac/apppage/apppageList',
            data:'page='+pageNum+'&pagesize='+pageSize,
            dataType:'json',
            success:function(data){
              $('#tt').datagrid('loaded');
              if(data.code==1){
                $('#tt').datagrid('loadData', data.list);
                datalist=data.list;
                console.log(datalist.total)
                if(datalist.total > 4){
                  $('.chooseTwo').css('display','block')
                  $('.chooseTwo a').css('color','#ccc')
                }else{
                  $('.chooseOne').css('display','block')
                }
                // console.log(datalist.total+'22222222')

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

      function iconurlformater(value,row,index){
        var opStr='';
        opStr='<img src="' + value + '" "style=width:48px; height:48px;margin-left:3px;" />'
        return opStr;
      }

      function iconclickurlformater(value,row,index){
        var opStr='';
        opStr='<img src="' + value + '" "style=width:48px; height:48px;margin-left:3px;" />'
        return opStr;
      }

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="ac:apppage:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/ac/apppage/form?id='+row.id+'">编辑</a>';
        if (row.menuName != '借钱' && row.menuName != '我的'){
          opStr+='<a class="si-option-a" href="${ctxA}/ac/apppage/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该应用页面吗？\', this.href)">删除</a>';
        }
        </shiro:hasPermission>
        return opStr;
      }
	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">应用页面配置</a></li>
	<shiro:hasPermission name="ac:apppage:edit">
			<li class="chooseTwo" style="display:none"><a href="${ctxA}/ac/apppage/form" onclick="return false;" style="cursor: default;" >新增菜单</a></li>
			<li class="chooseOne" style="display:none"><a href="${ctxA}/ac/apppage/form" >新增菜单</a></li>
	</shiro:hasPermission>
</ul>
<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'apppageId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'menuName',width:185,align:'center',halign:'center',fixed:true">菜单名称</th>
			<th data-options="field:'pageTemplet',width:185,align:'center',halign:'center',fixed:true">页面模板</th>
			<th data-options="field:'menuSort',width:154,align:'center',halign:'center',fixed:true">排序</th>
			<th data-options="field:'iconUrl',width:185,align:'center',halign:'center',fixed:true,formatter:iconurlformater">icon图片</th>
			<th data-options="field:'selectIconUrl',width:185,align:'center',halign:'center',fixed:true,formatter:iconclickurlformater">icon点击效果</th>
			<th data-options="field:'option',width:185,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>