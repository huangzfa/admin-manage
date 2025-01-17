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
                'state':$('#state').val(),
                'couponName':$('#couponName').val(),
                'orderNo':$('#orderNo').val(),
                'phone':$('#phone').val(),
                'receiveStartTime':$('#receiveStartTime').val(),
                'receiveEndTime':$('#receiveEndTime').val(),
                'useStartTime':$('#useStartTime').val(),
                'useEndTime':$('#useEndTime').val(),
                'state':$('#state').val(),
                'page':pageNum,
                'pagesize':pageSize
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/market/couponUser/getCouponData',
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
            <shiro:hasPermission name="order:borrow:view">
              if(row.borrowId!=null){
                  opStr+='<a class="si-option-a" href="${ctxA}/order/borrow/form?id='+row.borrowId+'&productId='+ row.productId+'">查看详情</a>';
              }
            </shiro:hasPermission>
            return opStr;
        }

        /**
         * 返回状态
         * @param value
         * @param row
         * @param index
         * @returns {*}
         */
        function stateformater(value,row,index){
            if( value ==0 ){
                return '未使用';
            }else if ( value == 1){
                return '已使用';
            }else if( value == 2){
                return '冻结'
            }else if( value == 3){
                return '过期'
            }

            return '未知';

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
    <li class="active"><a href="">优惠券列表</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form" style="margin-right: 20px">
        <li>
            <label>产品：</label>
            <select  name="productId" id="productId" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">

            </select>
        </li>
        <li>
            <label>用户账号：</label>
            <input id="phone"  class="input-large" type="text" value="" maxlength="11" />
        </li>
        <li>
            <label>订单编号：</label>
            <input id="orderNo"  class="input-large" type="text" value="" maxlength="30" />
        </li>
        <li>
            <label>优惠券名称：</label>
            <input id="couponName" placeholder="请输入优惠券名称" class="input-large" type="text" value="" maxlength="50" />
        </li>
        <li>
            <label>领取时间：</label>
            <input id="receiveStartTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
            -
            <input id="receiveEndTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
        </li>
        <li>
            <label>使用时间：</label>
            <input id="useStartTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
            -
            <input id="useEndTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
        </li>
        <li>
            <label>使用状态：</label>
            <select  name="state" id="state" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">
                <option value="">全部</option>
                <option value="0">未使用</option>
                <option value="1">已使用</option>
                <option value="3">过期</option>
            </select>
        </li>

        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询" />
        </li>
        <li class="clearfix"></li>
    </ul>
</div>
<div class="si-warp" style="top:126px;">
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'userNameEncrypt',width:140,align:'center',halign:'center',fixed:true">用户编号</th>
            <th data-options="field:'productId',width:140,align:'center',halign:'center',fixed:true,formatter:productFormater">所属产品</th>
            <th data-options="field:'addTime',width:100,align:'center',halign:'center',fixed:true">领取时间</th>
            <th data-options="field:'couponName',width:100,align:'center',halign:'center',fixed:true">优惠券名称</th>
            <th data-options="field:'amount',width:100,align:'center',halign:'center',fixed:true">面值</th>
            <th data-options="field:'orderNo',width:180,align:'center',halign:'center',fixed:true">订单号</th>
            <th data-options="field:'usedTime',width:180,align:'center',halign:'center',fixed:true">使用时间</th>
            <th data-options="field:'state',width:100,align:'center',halign:'center',fixed:true,formatter:stateformater">使用状态</th>
            <th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>

        </tr>
        </thead>
    </table>
</div>
</body>
</html>