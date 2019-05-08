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
            var loanTypes ='${loanType}';
            pager=$('#tt').datagrid('getPager');
            pager.pagination({
                onSelectPage:function(number, size){
                    pageSize = size;
                    pageNum = number;
                    getData();
                }
            });
            $('.datagrid-pager .pagination-num').hide();
            getData();
            $('#search').click(function(){
                getData();
            });

        });
        function getData(){
            var data = {
                'page':pageNum,
                'pagesize':pageSize,
                'resType':$("#resType").val()
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/resource/log/getResourceLogData',
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
    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="javascript:void(0);">资源列表</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form _clearfix">
        <li>
            <label>资源类型：</label>
            <input id="resType" placeholder="请输入资源类型" class="input-large" type="text" value="" maxlength="50" />
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
            <th data-options="field:'resType',width:140,align:'center',halign:'center',fixed:true">资源类型</th>
            <th data-options="field:'oldJson',width:300,align:'left',halign:'center',fixed:true">修改前配置</th>
            <th data-options="field:'modifyJson',width:300,align:'left',halign:'center',fixed:true">修改后配置</th>
            <th data-options="field:'addOperatorId',width:80,align:'left',halign:'center',fixed:true">操作人</th>
            <th data-options="field:'addTime',width:160,align:'left',halign:'center',fixed:true">操作时间</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>