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
            var productList ='${productList}';
            list = eval("("+productList+")");
            for( var i = 0;i<list.length;i++){
                $("#productId").append("<option value='"+list[i].id+"'>"+list[i].productName+"</option>");
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
                'productId':$("#productId").val(),

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/activity/getActivityData',
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
            var productId = $("#productId:selected").val(),
            <shiro:hasPermission name="activity:list:edit">
            opStr = '<a class="si-option-a" href="${ctxA}/activity/form?code='+row.code+'&productId='+productId+'">修改</a>';
            </shiro:hasPermission>
            return opStr;
        }

        function typeformater(value,row,index) {
            if( value == 'hongbao'){
                return "红包模板";
            }else if( value == "zhuanpan"){
                return "转盘模板";
            }else if( value=="static"){
                return "静态模板";
            }else if( value=="exchange"){
                return "兑换模板";
            }else{
                return "未知";
            }
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="javascript:void(0);">活动列表</a></li>
    <shiro:hasPermission name="product:list:edit">
        <li><a href="${ctxA}/product/mpForm">添加活动</a></li>
    </shiro:hasPermission>
    <li><a href="${ctxA}/activity/prize/list">奖品池管理</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form">
        <li>
            <label>选择产品：</label>
            <select id="productId" name="productId" class="selectpicker show-tick form-control">
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
            <th data-options="field:'actName',width:180,align:'center',halign:'center',fixed:true">活动名称</th>
            <th data-options="field:'atCode',width:180,align:'center',halign:'center',fixed:true,formatter:typeformater">活动模板</th>
            <th data-options="field:'createBy',width:180,align:'center',halign:'center',fixed:true">创建人</th>
            <th data-options="field:'createTiime',width:200,align:'center',halign:'center',fixed:true">创建时间</th>
            <th data-options="field:'updateBy',width:160,align:'center',halign:'center',fixed:true">修改人</th>
            <th data-options="field:'updateTime',width:200,align:'center',halign:'center',fixed:true">修改时间</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>