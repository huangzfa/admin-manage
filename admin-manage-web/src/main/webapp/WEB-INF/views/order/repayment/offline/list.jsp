<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,ajaxfileupload"/>
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
            getData();
            $('.datagrid-pager .pagination-num').hide();
            //加载第一页数据
            $('#search').click(function(){
                pageNum = 1;
                getData();
            });

        });
        function getData(){
            var data = {
                'productId':$('#productId').val(),
                'state':$('#state').val(),
                'userId':$("#userId").val(),
                'page':pageNum,
                'pagesize':pageSize
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/order/repayment/offline/getData',
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
            <shiro:hasPermission name="repayment:offline:edit">
              if( row.state == '0'){
                  opStr+='<a class="si-option-a" href="${ctxA}/order/repayment/offline/form?id='+row.id+'">还款审核</a>';
              }else{
                  opStr+='<a class="si-option-a" href="${ctxA}/order/repayment/offline/form?id='+row.id+'">查看详情</a>';
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

            if( value =='0' ){
                return '待平账';
            }else if( value == '1'){
                return '平账成功';
            }else if( value == '3'){
                return '平账失败';
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

        function uploadExcel(){
            top.layer.load();
            $.ajaxFileUpload({
                url: "${ctxA}/order/repayment/offline/uploadOfflineExcel",
                secureuri: false,
                fileElementId: "importexcel",
                dataType: "json",
                success: function(f) {
                    top.layer.closeAll('loading');//关闭loading
                    var data = eval("("+f+")");
                    if(data.code == 1){
                        var msg = "操作完成！平账成功 "+data.map.successeCount+" 条,失败 "+data.map.failCount+"条";
                        if( data.map.failCount>0){
                            msg=msg+" "+ data.map.failMap;
                        }
                        top.layer.alert(msg, {
                            icon: 6,
                            end: function(){
                            }
                        });
                    }else{
                        top.layer.alert(data.msg,{icon: 5});
                    }
                },
                error: function(g, f, h) {
                    top.layer.closeAll('loading');//关闭loading
                    top.layer.alert(h,{icon: 5});
                }
            });
        }
    </script>
</head>
<body>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form" style="margin-right: 20px">
        <li>
            <label>产品：</label>
            <select  name="productId" id="productId" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">
                <option value="">全部</option>
            </select>
        </li>
        <li>
            <label>用户id：</label>
            <input id="userId" placeholder="请输入用户id" class="input-large" type="text" value="" maxlength="50" />
        </li>
        <li>
            <label>使用状态：</label>
            <select  name="state" id="state" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">
                <option value="">全部</option>
                <option value="0">待平账</option>
                <option value="1">已平账</option>
                <option value="2">平账失败</option>
            </select>
        </li>

        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询" />
        </li>
        <shiro:hasPermission name="repayment:offline:edit">
            <li>
                <div class="uploadExcle">
                    <input type="file"  id="importexcel"    name="importexcel" />
                </div>
            </li>
            <li>
                <input class="btn btn-primary" type="button" onclick="uploadExcel()" value="批量平账" />
            </li>
        </shiro:hasPermission>
        <li class="clearfix"></li>
    </ul>
</div>
<div class="si-warp" style="top:95px;">
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'tradeNo',width:140,align:'center',halign:'center',fixed:true">订单号</th>
            <th data-options="field:'productId',width:140,align:'center',halign:'center',fixed:true,formatter:productFormater">所属产品</th>
            <th data-options="field:'userId',width:100,align:'center',halign:'center',fixed:true">user-id</th>
            <th data-options="field:'submitterName',width:100,align:'center',halign:'center',fixed:true">姓名</th>
            <th data-options="field:'phone',width:100,align:'center',halign:'center',fixed:true">注册手机号</th>
            <th data-options="field:'amount',width:100,align:'center',halign:'center',fixed:true">借款金额</th>
            <th data-options="field:'repaymentAmount',width:100,align:'center',halign:'center',fixed:true">待还金额</th>
            <th data-options="field:'realOverdueAmount',width:100,align:'center',halign:'center',fixed:true">逾期费</th>
            <th data-options="field:'repayAmount',width:100,align:'center',halign:'center',fixed:true">已还金额</th>
            <th data-options="field:'borrowTime',width:100,align:'center',halign:'center',fixed:true">借款时间</th>
            <th data-options="field:'submitterTime',width:100,align:'center',halign:'center',fixed:true">提交时间</th>
            <th data-options="field:'renewalNum',width:100,align:'center',halign:'center',fixed:true">续借次数</th>
            <th data-options="field:'addTime',width:100,align:'center',halign:'center',fixed:true">平账时间</th>
            <th data-options="field:'modifyOperatorName',width:100,align:'center',halign:'center',fixed:true">平账人员</th>
            <th data-options="field:'state',width:100,align:'center',halign:'center',fixed:true,formatter:stateformater">平账状态</th>
            <th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>