<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date" />
    <!--  -->
    <style type="text/css">
        .datagrid-pager,.pagination{
            width:100% !important;
            position: absolute !important;
            bottom:0 !important;
        }
        .datagrid-wrap{
            /*height:calc(100% - 50px)*/
            height: 500px;
        }
    </style>
    <script type="text/javascript">

        $(function(){
            var pageSize=${cfg:getPageSize()};
            var pageList=[pageSize,30,50];
            var pager=$('#tt').datagrid('getPager');
            pager.pagination({
                pageSize: pageSize,//每页显示的记录条数，默认为10
                pageList: pageList,//可以设置每页记录条数的列表
                layout:['list','sep','first','prev','links','next','last','sep','manual'],
                beforePageText: '',
                afterPageText: ' 共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                onSelectPage:function(pageNumber, pageSize){
                    pageSize=pageSize;
                    //alert("pageNumber:"+pageNumber+";pageSize:"+pageSize);
                    getData(pageNumber,pageSize);
                }
            });
            $('.datagrid-pager .pagination-num').hide();

            //加载第一页数据
            getData(1,pageSize);

            $('#search').click(function(){
                getData(1,pageSize);
            });
        });
        function getData(pageNum, pageSize){
            $('#tt').datagrid('loading');
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/oc/resource/bizResourceList',
                data:{'name':$('#name').val(),'page':pageNum,'pageSize':pageSize},
                dataType:'json',
                success:function(data){
                    $('#tt').datagrid('loaded');
                    if (data.code == 1) {
                        $('#tt').datagrid('loadData', data.list);
                    }else{
                        top.layer.alert("系统异常",{icon: 5})
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    top.layer.alert("系统异常",{icon: 5})
                    $('#tt').datagrid('loaded');
                }
            });
        }
        function deleteData(id) {
            top.$.jBox.confirm("确定要删？",'系统提示',function(v,h,f){
                if(v=='ok'){
                    $.ajax({
                        type: "POST",
                        url: '${ctxA}/oc/resource/edtiStatus',
                        contentType: "application/x-www-form-urlencoded;charset=utf-8",
                        data: {'id':id},
                        dataType: "json",
                        success: function(data){

                            if (data.code == 1){
                                top.layer.alert("删除成功",{icon: 6});
                                var pageSize=${cfg:getPageSize()};
                                var grid = $('#tt');
                                var options = grid.datagrid('getPager').data("pagination").options;
                                var curr = options.pageNumber;
                                getData(curr,pageSize);

                            }else{
                                top.layer.alert(data.msg,{icon: 5});
                            }

                        },
                        error: function(msg){
                            top.layer.alert("设置失败",{icon: 5});
                        }
                    });
                }
            })
        }


        function optionformater(value,row,index){
            var opStr='';
            <shiro:hasPermission name="oc:resource:view">
            opStr+='<a class="si-option-a" href="${ctxA}/oc/resource/form?id='+row.id+'">编辑</a>';
            </shiro:hasPermission>
            opStr+='<a class="si-option-a" href="#" onclick="deleteData('+row.id+')">删除</a>';
            return opStr;
        }


    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="javascript:void(0);">运营资源列表</a></li>
    <shiro:hasPermission name="product:authConfig:edit">
        <li><a href="${ctxA}/oc/resource/form">添加资源</a></li>
    </shiro:hasPermission>
</ul>
<div class="breadcrumb form-search">
    <ul class="ul-form">
        <li>
            <label>配置名：</label>
            <input id="name"  class="input-medium" type="text" value="" maxlength="50" />
        </li>
        <li class="btns">
            <input id="search" class="btn btn-primary" type="submit" value="查询" />
        </li>
        <li class="clearfix"></li>
    </ul>
</div>
<div class="si-warp" style="top:95px;">
    <sys:message content="${message}" isShowBox="false" />
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true" >
        <thead>
        <tr>
            <th style="width: 5%" data-options="field:'id',width:30,align:'center',halign:'center',fixed:true">配置名id</th>
            <th style="width: 15%" data-options="field:'name',width:45,align:'center',halign:'center',fixed:true">配置名称</th>
            <th style="width: 15%" data-options="field:'type',width:40,align:'center',halign:'center',fixed:true">主类型</th>
            <th style="width: 15%" data-options="field:'secType',width:30,align:'center',halign:'center',fixed:true">附类型</th>
            <th style="width: 8%" data-options="field:'addOperatorName',width:30,align:'center',halign:'center',fixed:true">创建人</th>
            <th style="width: 12%" data-options="field:'addTime',width:30,align:'center',halign:'center',fixed:true">创建时间</th>
            <th style="width: 8%" data-options="field:'modifyOperatorName',width:30,align:'center',halign:'center',fixed:true">最后修改人</th>
            <th style="width: 12%" data-options="field:'modifyTime',width:30,align:'center',halign:'center',fixed:true">最后修改时间</th>
            <th style="width: 10%" data-options="field:'option',width:60,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>

</div>
</body>
</html>