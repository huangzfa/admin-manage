<%@ page language="java" pageEncoding="utf-8"%>
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
        var pager;
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum =1;
        $(function(){
            var appLists ='${appLists}';
            list = eval("("+appLists+")");
            var appId = '${appId}';
            for( var i = 0;i<list.length;i++){
                $("#appId").append("<option value='"+list[i].id+"'>"+list[i].appName+"</option>");
            }
            if( appId!=''){
                $("#appId").val(appId);
            }else{
                $("#appId").val(list[0].id);
            }
            $('#menuOperation').attr("href","${ctxA}/app/pageConfig/form?appId="+ $("#appId").val());
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

        });
        function getData(){
            var data = {
                'pageNum':pageNum,
                'pageSize':pageSize,
                'appId':$("#appId").val()

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/app/pageConfig/appPageConfigList',
                data:data,
                dataType:'json',
                success:function(data){
                    if(data.code==1){
                        $('#menuOperation').attr("href","${ctxA}/app/pageConfig/form?appId="+ $("#appId").val());
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

        function iconurlformater(value,row,index){
           var opStr='<img src="' + value + '" "style=width:48px; height:48px;margin-left:3px;" />'
           return opStr;
        }

        function iconclickurlformater(value,row,index){
           var opStr='<img src="' + value + '" "style=width:48px; height:48px;margin-left:3px;" />'
           return opStr;
        }

        function optionformater(value,row,index){
           var opStr='<a class="si-option-a" href="${ctxA}/app/pageConfig/form?id='+row.id+'&appId='+ $('#appId').val()+'">编辑</a>';
           if(row.isEnable == 1){
              opStr+='<a class="si-option-a" href="${ctxA}/app/pageConfig/updateStatus?id='+row.id+'&appId='+ $('#appId').val()+'&isEnable='+0+'">禁用</a>';
           }else{
              opStr+='<a class="si-option-a" href="${ctxA}/app/pageConfig/updateStatus?id='+row.id+'&appId='+ $('#appId').val()+'&isEnable='+1+'">启用</a>';
          }

          return opStr;
        }
	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">应用页面配置</a></li>
	<shiro:hasPermission name="app:pageConfig:edit">
			<li class="chooseOne"><a id="menuOperation" href="#" >新增配置</a></li>
	</shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>选择应用：</label>
			<select id="appId" name="appId" class="selectpicker show-tick form-control">
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