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
            var appQuery = $('#appQuery').val();
          $('#tt').datagrid('loading');
          hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/app/pageConfig/appPageConfigList',
            data:{'page':pageNum,'pagesize':pageSize,'appId':appQuery},
            dataType:'json',
            success:function(data){
              $('#tt').datagrid('loaded');
              if(data.code==1){

            /*    if(datalist.total > 4){
                  $('.chooseTwo').css('display','block')
                  $('.chooseTwo a').css('color','#ccc')*/
				  	var nowAppId = -1;
                  $('#menuOperation').attr("href","#");
                    $('#appQuery').html("")
                    for (var i = 0; i < data.appList.length; i++) {
                        var option = ""
                        if (appQuery == data.appList[i].id) {
                            option = "<option value= '" + data.appList[i].id + "'selected = 'selected'>" + data.appList[i].appName + "</option>";
                            nowAppId = data.appList[i].id;
                        } else {
                            option = "<option value= '" + data.appList[i].id + "'>" + data.appList[i].appName + "</option>";
                        }
                        $(option).appendTo($("#appQuery"));
                    }
                    if (nowAppId == -1){
                            nowAppId = data.appList[0].id;
                    }
                  $('#menuOperation').attr("href","${ctxA}/app/pageConfig/form?appId="+nowAppId);
				  $('#appId').val(nowAppId);
            /*    }else{
                  $('.chooseOne').css('display','block')
                }
*/
                // console.log(datalist.total+'22222222')
                  $('#tt').datagrid('loadData', data.list);
                  datalist=data.list;
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

        opStr+='<a class="si-option-a" href="${ctxA}/app/pageConfig/form?id='+row.id+'&appId='+ $('#appId').val()+'">编辑</a>';
        if(row.isEnable == 1){
            opStr+='<a class="si-option-a" href="${ctxA}/app/pageConfig/updateStatus?id='+row.id+'&appId='+ $('#appId').val()+'&isEnable='+0+'">禁用</a>';
        }else{
            opStr+='<a class="si-option-a" href="${ctxA}/app/pageConfig/updateStatus?id='+row.id+'&appId='+ $('#appId').val()+'&isEnable='+1+'">启用</a>';
        }

        /*if (row.menuName != '借钱' && row.menuName != '我的'){
          opStr+='<a class="si-option-a" href="${ctxA}/app/pageConfig/delete?id='+row.id+'" onclick="return confirmx(\'确定要删除该应用页面吗？\', this.href)">删除</a>';
        }*/
        return opStr;
      }
	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">应用页面配置</a></li>
	<shiro:hasPermission name="app:pageConfig:edit">
			<%--<li class="chooseTwo" style="display:none"><a href="${ctxA}/app/pageConfig/form" onclick="return false;" style="cursor: default;" >新增菜单</a></li>--%>
			<li class="chooseOne" <%--style="display:none"--%>><a id="menuOperation" href="#" >新增配置</a></li>
	</shiro:hasPermission>
</ul>
<input type="hidden" name="" id="appId">
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>选择应用：</label>
			<select id="appQuery">
			</select>
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
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'menuName',width:185,align:'center',align:'center',fixed:true">菜单名称</th>
			<th data-options="field:'menuSort',width:154,align:'center',align:'center',fixed:true">排序</th>
			<th data-options="field:'iconUrl',width:185,align:'center',align:'center',fixed:true,formatter:iconurlformater">icon图片</th>
			<th data-options="field:'selectIconUrl',width:185,align:'center',align:'center',fixed:true,formatter:iconclickurlformater">icon点击效果</th>
			<th data-options="field:'option',width:185,align:'center',align:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>