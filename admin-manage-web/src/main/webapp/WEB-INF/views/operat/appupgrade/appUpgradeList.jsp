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
      var flag = true
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
            url:'${ctxA}/operat/appupgrade/appUpgradeList',
            data:'versionNumber='+$('#versionNumber').val()+'&appId='+$('#appSelect').val()+'&page='+pageNum+'&pagesize='+pageSize,
            dataType:'json',
            success:function(data){
              $('#tt').datagrid('loaded');
              if(data.code==1){
                $('#tt').datagrid('loadData', data.list);
                if(flag == true){
                  $.each(data.appList, function (i, item) {
                    jQuery("#appSelect").append("<option value="+item.id+">"+item.appName+"</option>");
                  });
                  flag = false
                }
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

      function stateformater(value,row,index){
        if(value=='1'){
          return "开启状态";
        }else if(value=='-1'){
          return "关闭状态";
        }else if (value=='0') {
          return "新建状态";
        }
        return '未知';
      }

      function upgradeRangeformater(value,row,index){
        if(value=='0'){
          return "所有版本";
        }else if(value=='1'){
          return "部分版本";
        }
        return '未知';
      }

      function isForceformater(value,row,index){
        if(value=='1'){
          return "是";
        }else if(value=='0'){
          return "否";
        }
        return '未知';
      }

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="operat:appupgrade:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/operat/appupgrade/form?id='+row.id+'">编辑</a>';
        opStr+='<a class="si-option-a" href="${ctxA}/operat/appupgrade/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该配置吗？\', this.href)">删除</a>';
        if (row.state == '0' || row.state == '-1' ){
          opStr+='<a class="si-option-a" href="${ctxA}/operat/appupgrade/changeState?id='+row.id+'&state=1">开启</a>';
        }else if (row.state == '1') {
          opStr+='<a class="si-option-a" href="${ctxA}/operat/appupgrade/changeState?id='+row.id+'&state=-1">关闭</a>';
        }
        </shiro:hasPermission>
        return opStr;
      }

	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">升级配置列表</a></li>
	<shiro:hasPermission name="operat:appupgrade:edit">
		<li><a href="${ctxA}/operat/appupgrade/form">新增升级配置</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>版本：</label>
			<input id="versionNumber" placeholder="请输入版本号" class="input-large" type="text" value=""
				   maxlength="50"/>
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
	</ul>
</div>


<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'appUpgradeId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'id',width:57,align:'center',halign:'center',fixed:true">配置id</th>
			<th data-options="field:'appName',width:120,align:'center',halign:'center',fixed:true">应用</th>
			<th data-options="field:'versionNumber',width:57,align:'center',halign:'center',fixed:true">版本号</th>
			<th data-options="field:'versionName',width:120,align:'center',halign:'center',fixed:true">版本名</th>
			<th data-options="field:'state',width:90,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
			<th data-options="field:'versionDesc',width:196,align:'center',halign:'center',fixed:true">版本描述</th>
			<th data-options="field:'upgradeRange',width:90,align:'center',halign:'center',fixed:true,formatter:upgradeRangeformater">升级范围</th>
			<th data-options="field:'isForce',width:90,align:'center',halign:'center',fixed:true,formatter:isForceformater">是否强制升级</th>
			<th data-options="field:'option',width:170,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>