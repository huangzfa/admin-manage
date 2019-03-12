<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date"/>
<!--  -->
<style type="text/css">
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
            'borrowNo':$('#borrowNo').val(),
			'mobile':$("#mobile").val(),
			'productId':$("#productId").val(),
			'page':pageNum,
			'pagesize':pageSize,
      /*      'startDate':$('#startDate').val(),
            'endDate':$('#endDate').val()*/

		}
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/order/product/borrowCashList',
            data:data,
            dataType:'json',
            success:function(data){
                if(data.code==1){
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
    function stateformater(value,row,index){
        if(value=='1'){

            return "启用";

        }else if(value=='0'){

            return "禁用";

        }
        return '未知';
    }

    function optionformater(value,row,index){

        var opStr='';
        <shiro:hasPermission name="order:borrow:view">
			opStr+='<a class="si-option-a" href="${ctxA}/order/borrow/form?id='+row.id+'&productId='+ row.productId+'">查看详情</a>';
        </shiro:hasPermission>
        return opStr;
    }

    function redirectformater(value,row,index) {
        if(value=='no'){

            return "无连接";

        }else if(value=='url'){

            return "h5链接";

        }
        return '未知';
    }

    function editState(id,isEnable){
        var msg = "确定禁用该轮播图吗";
        if( isEnable == 1){
            msg = "确定启用该轮播图吗";
		}
        top.$.jBox.confirm(msg,'系统提示',function(v,h,f){
            if(v=='ok'){
                jQuery.post("${ctxA}/market/banner/editState", {'id':id,'isEnable':isEnable},
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
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">借款订单</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">

		<li>
			<label>服务订单号：</label>
			<input id="borrowNo" lass="input-large" type="text" value=""  />
		</li>

		<li>
			<label>注册手机号：</label>
			<input id="mobile" class="input-large" type="text" value="" />
		</li>

		<li>
			<label>产品名称：</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control">
			</select>
		</li>


		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
		<%--	<th data-options="field:'id',width:80,align:'center',halign:'center',fixed:true">id</th>
			<th data-options="field:'imgUrl',width:120,align:'center',halign:'center',fixed:true,formatter:iconurlformater">图片</th>
			<th data-options="field:'bannerTitle',width:160,align:'center',halign:'center',fixed:true">轮播名称</th>
			<th data-options="field:'bannerType',width:160,align:'center',halign:'center',fixed:true,formatter:typeformater">所属位置</th>
			<th data-options="field:'redirectType',width:160,align:'center',halign:'center',fixed:true,formatter:redirectformater">类型</th>
			<th data-options="field:'redirectUrl',width:160,align:'center',halign:'center',fixed:true">链接</th>
			<th data-options="field:'remark',width:160,align:'center',halign:'center',fixed:true">备注说明</th>
			<th data-options="field:'isEnable',width:45,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
			<th data-options="field:'sort',width:160,align:'center',halign:'center',fixed:true">排序</th>
			<th data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">添加时间</th>
			<th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>--%>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>