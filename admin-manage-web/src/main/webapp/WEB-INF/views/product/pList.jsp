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
                'productCode':$("#productCode").val(),
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
        function stateformater(value,row,index){
            if(value=='1'){
                return "启用";
            }else if(value=='0'){
                return "停用";
            }
            return '未知';
        }
        function optionformater(value,row,index){
            var opStr='';
            <shiro:hasPermission name="product:list:edit">
            var productState = 0;
            if( row.state == 0) productState = 1;
            opStr+='<a class="si-option-a" href="${ctxA}/product/pForm?productCode='+row.productCode+'">修改</a>';
            opStr+="<a class='si-option-a' href='javascript:editState(\""+row.productCode+"\",\""+productState+"\")'>"+(row.state==1?"禁用":"启用")+"</a>";
            opStr+='<a class="si-option-a" href="${ctxA}/product/pConfig?productCode='+row.productCode+'">配置产品</a>';
            </shiro:hasPermission>
            return opStr;
        }

        function editState(productCode,productState){
            var title = "确定启用用该产品吗";
            if(productState == 0){
                title = "确定禁用该产品吗";
            }
            top.$.jBox.confirm(title,'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/product/editState", {'productCode':productCode,'productState':productState},
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
    <li class="active"><a href="javascript:void(0);">产品列表</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form">
        <li>
            <label>产品编号：</label>
            <input id="productCode"  class="input-large" type="text" value="" maxlength="50"/>
        </li>
        <li>
            <label>产品名称：</label>
            <input id="productName"  class="input-large" type="text" value="" maxlength="50"/>
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
            <th data-options="field:'state',width:200,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
            <th data-options="field:'modifyTime',width:160,align:'center',halign:'center',fixed:true">更新时间</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>