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
          var typeList ='${channelType}';
          var channelType = eval("("+typeList+")");
          for( var i = 0;i<channelType.length;i++){
              $("#channelType").append("<option value='"+channelType[i].dicVal+"'>"+channelType[i].dicCode+"</option>");
          }
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
            url:'${ctxA}/channel/promotion/promotionChannelList',
            data:'channelName='+$('#channelName').val()+'&channelCode='+$('#channelCode').val()+'&page='+pageNum+'&pagesize='+pageSize,
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

      function channelTypeformater(value,row,index){
        if(value=='0'){
          return "渠道";
        }else if(value=='1'){
          return "应用市场";
        }else if(value=='2'){
          return "短信";
        }else if(value=='3'){
          return "其他";
        }
        return '未知';
      }

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="channel:appMarket:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/channel/market/form?id='+row.id+'">编辑</a>';
        opStr+='<a class="si-option-a" href="" onclick="del("+row.id+")">删除</a>';
        </shiro:hasPermission>
        return opStr;
      }
      function del(id){
          top.$.jBox.confirm("确定要删除该渠道吗",'系统提示',function(v,h,f){
              if(v=='ok'){
                  jQuery.post("${ctxA}/channel/market/delete", {'id':id},
                      function(data) {
                          if (data.code ==1) {
                              top.layer.alert("操作完成", {
                                  icon: 6,
                                  end: function(){
                                      pageNum = 1;
                                      etData(1,pageSize);
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
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">应用市场渠道列表</a></li>
	<shiro:hasPermission name="channel:market:edit">
		<li><a href="${ctxA}/channel/market/form">新增应用市场渠道</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>渠道名称：</label>
			<input id="channelName" placeholder="请输入渠道名称" class="input-large" type="text"  value=""
				   maxlength="32"/>
		</li>
		<li>
			<label>渠道编码：</label>
			<input id="channelCode" placeholder="请输入渠道编码" class="input-large" type="text"  value=""
				   maxlength="32"/>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询"/>
		</li>
	</ul>
</div>


<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'promotionChannelId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'id',width:138,align:'center',halign:'center',fixed:true">编号</th>
			<th data-options="field:'channelName',width:138,align:'center',halign:'center',fixed:true">投放渠道</th>
			<th data-options="field:'channelCode',width:138,align:'center',halign:'center',fixed:true">渠道编码</th>
			<th data-options="field:'channelType',width:138,align:'center',halign:'center',fixed:true,formatter:channelTypeformater">渠道类型</th>
			<th data-options="field:'companyName',width:138,align:'center',halign:'center',fixed:true">渠道公司</th>
			<th data-options="field:'companyName',width:138,align:'center',halign:'center',fixed:true">审核状态</th>
			<th data-options="field:'addTime',width:138,align:'center',halign:'center',fixed:true">创建时间</th>
			<th data-options="field:'approveStatus',width:138,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>