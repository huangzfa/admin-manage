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
        var productList =[];
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum =1;
        $(function(){
            var productLists ='${productList}';
            productList = eval("("+productLists+")");
            for( var i = 0;i<productList.length;i++){
                $("#productId").append("<option value='"+productList[i].id+"'>"+productList[i].productName+"</option>");
            }
            var pageSize=${cfg:getPageSize()};
            var pageList=[pageSize,30,50];
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
                'productId':$('#productId').val(),
                'couponName':$('#couponName').val(),
                'state':$('#state').val(),
                'page':pageNum,
                'pagesize':pageSize
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/market/coupon/getRecordData',
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
            <shiro:hasPermission name="market:coupon:edit">
            opStr+='<a class="si-option-a" href="${ctxA}/market/coupon/form?id='+row.id+'&productId='+ row.productId+'">修改</a>';
            opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
            </shiro:hasPermission>
            return opStr;
        }


        function countFormater(value,row,index){
            return row.successCount + row.failCount;
        }

        function typeFormater(value,row,index){
            if( value == 1){
                return "单个";
            }else if( value ==2){
                return "批量";
            }
            return "未知";
        }

        function stateFormater(value,row,index){
            if( value == 0){
                return "待发送";
            }else if( value ==1){
                return "已发送";
            }
            return "未知";
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
    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="">发送记录</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form" style="margin-right: 20px">
        <li>
            <label>产品：</label>
            <select  name="productId" id="productId" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">

            </select>
        </li>
        <li>
            <label>优惠券名称：</label>
            <input id="couponName" placeholder="请输入优惠券名称" class="input-large" type="text" value="" maxlength="50" />
        </li>
        <li>
            <label>使用状态：</label>
            <select  name="state" id="state" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">
                <option value="">全部</option>
                <option value="0">待发送</option>
                <option value="1">已发送</option>
            </select>
        </li>
        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询" />
        </li>
        <li class="clearfix"></li>
    </ul>
</div>
<div class="si-warp" style="top:95px;">
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'couponName',width:140,align:'center',halign:'center',fixed:true">优惠券名称</th>
            <th data-options="field:'productId',width:140,align:'center',halign:'center',fixed:true,formatter:productFormater">所属产品</th>
            <th data-options="field:'sendType',width:140,align:'center',halign:'center',fixed:true,formatter:typeFormater">发送类型</th>
            <th data-options="field:'addTime',width:280,align:'center',halign:'center',fixed:true">发送时间</th>
            <th data-options="field:'state',width:100,align:'center',halign:'center',fixed:true,formatter:stateFormater">发送状态</th>
            <th data-options="field:'id',width:100,align:'center',halign:'center',fixed:true,formatter:countFormater">发送数量</th>
            <th data-options="field:'successCount',width:100,align:'center',halign:'center',fixed:true">成功数量</th>
            <th data-options="field:'failCount',width:100,align:'center',halign:'center',fixed:true">失败数量</th>

        </tr>
        </thead>
    </table>
</div>
</body>
</html>