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
      var pageSize=${cfg:getPageSize()};
      var pageList=[pageSize,30,50];
      var pageNum = 1;
      var pager;
      $(function(){

          var typeList ='${channelType}';
          var channelType = eval("("+typeList+")");
          for( var i = 0;i<channelType.length;i++){
              $("#channelType").append("<option value='"+channelType[i].dicVal+"'>"+channelType[i].dicCode+"</option>");
          }
          pager = $('#tt').datagrid('getPager');
        pager.pagination({
			onSelectPage:function(number, size){
            pageSize=size;
            pageNum = number;
            getData();
          }
        });
        $('.datagrid-pager .pagination-num').hide();
        //加载第一页数据
        getData();



          $('#search').click(function(){
              getData();
          });

      });
      function getData(){
          $('#tt').datagrid('loading');
          hjnUtils.ajax({
              type:'post',
              url:'${ctxA}/channel/promotion/promotionChannelList',
              data:{'nameAndCompany':$('#nameAndCompany').val(),'channelType':$('#channelType').val(),'page':pageNum,'pagesize':pageSize},
              dataType:'json',
              success:function(data){
                  $('#tt').datagrid('loaded');
                  pager.pagination({
                      pageSize: pageSize,//每页显示的记录条数，默认为10
                      pageList: pageList,//可以设置每页记录条数的列表
                      pageNumber:pageNum,
                      layout:['list','sep','first','prev','links','next','last','sep','manual'],
                      beforePageText: '',
                      displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                      afterPageText: ' 共 {pages} 页'
                  })

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

      function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="channel:promotion:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/channel/promotion/form?id='+row.id+'">编辑</a>';
        opStr+=' <a class="si-option-a"  onclick="del('+row.id+')">删除</a>';
        </shiro:hasPermission>
        return opStr;
      }
      function del(id){
          top.$.jBox.confirm("确定要删除该渠道吗",'系统提示',function(v,h,f){
              if(v=='ok'){
                  jQuery.post("${ctxA}/channel/promotion/delete", {'id':id},
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
	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">推广渠道列表</a></li>
	<shiro:hasPermission name="channel:promotion:edit">
		<li><a href="${ctxA}/channel/promotion/form">新增推广渠道</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>样式名称：</label>
			<input id="styleName" class="input-large" type="text"  value=""
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
		   data-options="idField:'promotionChannelId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'styleName',width:138,align:'center',halign:'center',fixed:true">样式名称</th>
			<th data-options="field:'channelCode',width:138,align:'center',halign:'center',fixed:true">使用渠道</th>
			<th data-options="field:'opertion',width:138,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>