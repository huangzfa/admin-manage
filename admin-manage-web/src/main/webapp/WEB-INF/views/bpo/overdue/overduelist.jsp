<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>逾期记录列表</title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,ajaxfileupload"/>
</head>
<body>

<div class="breadcrumb form-search" style="height: 10%">
    <form id="searchOfflineForm">
        <ul class="ul-form">
            <li>
                <label>用户账号：</label>
                <input id="userName" name="userName" class="input-large" value="" type="text" value="" maxlength="50"/>
            </li>
            <li>
                <label>借款流水号：</label>
                <input id="borrowNo" name="borrowNo" class="input-large" value="" type="text" value="" maxlength="50"/>
            </li>
            <li>
                <label>产品名称</label>
                <input id="productName" name="productName" class="input-large" type="text"  maxlength="32" >
               <%-- <select id="productId" name="productId" class="selectpicker show-tick form-control">
                    <option value="">---请选择---</option>
                    <c:forEach var="item" items="${products}" >
                        <option value="${item.id}">${item.productName}</option>
                    </c:forEach>
                </select>--%>
            </li>
            <li>
                &nbsp;&nbsp;&nbsp;&nbsp; <input id="search" class="btn btn-primary" type="button" value="查询"/>
            </li>
        </ul>
    </form>
</div>
<div class="si-warp" style="position: inherit;height: 85%">
    <sys:message content="${message}" isShowBox="false"/>
    <table id="overdueRecordList" title="Overdue Record List" class="easyui-datagrid"
           data-options="singleSelect:true,rownumbers:true,toolbar:'toolbar',singleSelect:true,striped:true,fit:true,pagination:true">
        <thead>
            <tr>
                <th data-options="field:'id',width:150,align:'center',halign:'center'">id</th>
                <th data-options="field:'borrowNo',width:100,align:'center',halign:'center'">借款流水号</th>
                <th data-options="field:'productName',width:100,align:'center',halign:'center'">产品名称</th>
                <th data-options="field:'userName',width:100,align:'center',halign:'center'">用户账号</th>
                <th data-options="field:'addTime',width:100,align:'center',halign:'center',fixed:true">创建时间</th>
                <th data-options="field:'billOverdueAmount',width:150,align:'center',halign:'center'">逾期费用</th>
                <%--<th data-options="field:'billOverdueAmount',width:100,align:'center',halign:'center'">实际逾期费用</th>--%>
            </tr>
        </thead>
    </table>
</div>

</body>
<script type="text/javascript">
    var pageSize = 10;
    $(function () {
        pageSize = ${cfg:getPageSize()};
        var pageList = [pageSize, 30, 50];
        var pager = $('#overdueRecordList').datagrid('getPager');
        pager.pagination({
            pageSize: pageSize,//每页显示的记录条数，默认为10
            pageList: pageList,//可以设置每页记录条数的列表
            layout: ['list', 'sep', 'first', 'prev', 'links', 'next', 'last', 'sep', 'manual'],
            beforePageText: '',
            afterPageText: ' 共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            onSelectPage: function (pageNumber, pageSize) {
                pageSize = pageSize;
                getData(pageNumber, pageSize);
            }
        });
        $('.datagrid-pager.pagination-num').hide();

        //加载第一页数据
        getData(1, pageSize);
    });

    function getData(pageNum, pageSize) {
        $('#overdueRecordList').datagrid('loading');
        hjnUtils.ajax({
            type: 'post',
            url: '${ctxA}/bpo/stageBorrow/overdue/overdueRecordlist/getData',
            data: $("#searchOfflineForm").serialize() + '&page=' + pageNum + '&pagesize=' + pageSize ,
            dataType: 'json',
            success: function (data) {
                $('#overdueRecordList').datagrid('loaded');
                if (data.code == 1) {

                    $('#overdueRecordList').datagrid('loadData', data.list);
                } else {
                    top.$.jBox.tip(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('#overdueRecordList').datagrid('loaded');
            }
        });
    }

    $('#search').click(function () {
        getData(1, pageSize);
    });



</script>


</html>
