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
        var productList = [];
        $(function(){
            var productLists ='${productList}';
            productList = eval("("+productLists+")");
            for( var i = 0;i<productList.length;i++){
                $("#productId").append("<option value='"+productList[i].id+"'>"+productList[i].productName+"</option>");
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
                getData();
            });

        });
        function getData(){
            var data = {
                'page':pageNum,
                'pagesize':pageSize,
                'name':$("#name").val(),
                'productId':$("#productId").val()
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/resource/getResourceData',
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
            <shiro:hasPermission name="market:banner:edit">
                opStr+='<a class="si-option-a" href="${ctxA}/resource/form?id='+row.id+'">修改</a>';
            opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
            </shiro:hasPermission>
            return opStr;
        }

        function del(id){
            top.$.jBox.confirm("确定删除该配置吗",'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/resource/delete", {'id':id},
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

        function productFormater(value,row,index){
            var productName = "未知";
            for(var i = 0 ;i<productList.length;i++){
                if( productList[i].id == value){
                    productName = productList[i].productName;
                    break;
                }
            }
            return productName;
        }

        function add() {
            var productId = $("#productId").val();
            window.location.href="${ctxA}/resource/form?productId="+productId;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="javascript:void(0);">资源列表</a></li>
    <shiro:hasPermission name="resource:list:edit">
        <li><a href="javascript:add()">添加资源</a></li>
    </shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form _clearfix">
        <li>
            <label>选择产品：</label>
            <select id="productId" name="productId" class="selectpicker show-tick form-control" onchange="getData()">
            </select>
        </li>
        <li>
            <label>资源名称：</label>
            <input id="name" placeholder="请输入资源名称" class="input-large" type="text" value="" maxlength="50" />
        </li>
        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询" />
        </li>
    </ul>
</div>
<div class="si-warp"  style="top:95px;">
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'id',width:80,align:'center',halign:'center',fixed:true">id</th>
            <th data-options="field:'name',width:120,align:'center',halign:'center',fixed:true">资源名称</th>
            <th data-options="field:'productId',width:120,align:'center',halign:'center',fixed:true,formatter:productFormater">所属产品</th>
            <th data-options="field:'type',width:140,align:'center',halign:'center',fixed:true">资源类型</th>
            <th data-options="field:'secType',width:140,align:'center',halign:'center',fixed:true">附类型</th>
            <th data-options="field:'intValue',width:100,align:'left',halign:'center',fixed:true">数值1配置</th>
            <th data-options="field:'longValue',width:100,align:'left',halign:'center',fixed:true">数值2配置</th>
            <th data-options="field:'stringValue',width:160,align:'left',halign:'center',fixed:true">文本配置</th>
            <th data-options="field:'stringValue1',width:160,align:'left',halign:'center',fixed:true">文本配置1</th>
            <th data-options="field:'stringValue2',width:160,align:'left',halign:'center',fixed:true">文本配置2</th>
            <th data-options="field:'decimalValue',width:100,align:'left',halign:'center',fixed:true">小数配置</th>
            <th data-options="field:'des',width:160,align:'left',halign:'center',fixed:true">描述</th>
            <th data-options="field:'modifyTime',width:160,align:'left',halign:'center',fixed:true">修改时间</th>
            <th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>