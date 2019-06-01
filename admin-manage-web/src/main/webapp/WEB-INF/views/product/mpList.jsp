<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui"/>
    <!--  -->
    <style type="text/css">
    </style>
    <script type="text/javascript">
        var pager;
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum =1;
        $(function(){
            var merchantList ='${merchantList}';
            list = eval("("+merchantList+")");
            for( var i = 0;i<list.length;i++){
                $("#merchantId").append("<option value='"+list[i].id+"'>"+list[i].merchantName+"</option>");
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
                'page':pageNum,
                'pagesize':pageSize,
                'merchantId':$("#merchantId").val(),
                'productName':$("#productName").val()

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/product/getProductData',
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
        function optionformater(value,row,index){
            var opStr='';
            <shiro:hasPermission name="product:list:edit">
               opStr+='<a class="si-option-a" href="${ctxA}/product/mpForm?productCode='+row.productCode+'">修改</a>';
            </shiro:hasPermission>
            return opStr;
        }


    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="javascript:void(0);">产品列表</a></li>
    <shiro:hasPermission name="product:list:edit">
        <li><a href="${ctxA}/product/mpForm">添加产品</a></li>
    </shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form">
        <li>
            <label>产品名称：</label>
            <input id="productName"  class="input-large" type="text" value="" maxlength="50"/>
        </li>
        <li>
            <label>商户名称：</label>
            <select id="merchantId" name="merchantId" class="selectpicker show-tick form-control">
                <option value="">全部</option>
            </select>
        </li>
        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询"/>
        </li>
    </ul>
</div>
<div class="si-warp" style="top:95px;">
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'productCode',width:180,align:'center',halign:'center',fixed:true">产品编码</th>
            <th data-options="field:'productName',width:180,align:'center',halign:'center',fixed:true">产品名称</th>
            <th data-options="field:'merchantName',width:180,align:'center',halign:'center',fixed:true">所属商户</th>
            <th data-options="field:'bizName',width:180,align:'center',halign:'center',fixed:true">业务类型</th>
            <th data-options="field:'serviceName',width:200,align:'center',halign:'center',fixed:true">接入服务</th>
            <th data-options="field:'modifyTime',width:160,align:'center',halign:'center',fixed:true">更新时间</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>