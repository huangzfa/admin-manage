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
        var pager;
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum =1;
        $(function(){
            var productLists ='${productLists}';
            var productList = eval("("+productLists+")");
            var productId = '${productId}';
            for( var i = 0;i<productList.length;i++){
                $("#productId").append("<option value='"+productList[i].id+"'>"+productList[i].productName+"</option>");
            }
            if( productId!=''){
                $("#productId").val(productId);
            }else{
                $("#productId").val(productList[0].id);
            }
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
                'productId':$("#productId").val(),
                'page':pageNum,
                'pagesize':pageSize,
                /*      'startDate':$('#startDate').val(),
                      'endDate':$('#endDate').val()*/

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/finance/zfbAccount/zfbAccountList',
                data:data,
                dataType:'json',
                success:function(data){
                    if(data.code==1){
                        $('#menuOperation').attr("href","${ctxA}/finance/zfbAccount/form?productId="+ $("#productId").val());
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
        <shiro:hasPermission name="finance:zfbAccount:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/finance/zfbAccount/form?id='+row.id+'&appId='+ row.appId+'">编辑</a>';
        opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
          var isEnable = 0;
          if( row.isEnable == 0) isEnable = 1;
          opStr+="<a class='si-option-a' href='javascript:editState(\""+row.id+"\",\""+isEnable+"\")'>"+(row.isEnable==0?"启用":"禁用")+"</a>";
        </shiro:hasPermission>
        return opStr;
      }

        function del(id){
            top.$.jBox.confirm("确定删除该账号配置吗？",'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/finance/zfbAccount/delete", {'id':id,'productId':$("#productId").val()},
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

        function editState(id,isEnable){
            var msg = "确定禁用该支付宝账号吗";
            if( isEnable == 1){
                msg = "确定启用该支付宝账号吗";
            }
            top.$.jBox.confirm(msg,'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/finance/zfbAccount/updateState", {'id':id,'isEnable':isEnable,'productId':$("#productId").val()},
                        function(data) {
                            if (data.code ==1) {
                                top.layer.alert("操作完成", {
                                    icon: 6,
                                    end: function(){
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
	<li class="active"><a href="javascript:void(0);">支付宝账号列表</a></li>
	<shiro:hasPermission name="finance:zfbAccount:edit">
		<li><a id="menuOperation"  href="">添加支付宝账号</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">
		<li>
			<label>产品名称：</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control" onchange="getData()">
			</select>
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'name',width:618,align:'center',halign:'center',fixed:true">账号名称</th>
			<th data-options="field:'isEnable',width:232,align:'center',halign:'center',fixed:true,formatter:accountStateformater">状态</th>
			<th data-options="field:'option',width:232,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>