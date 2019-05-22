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
                'pagesize':pageSize
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/quartz/getData',
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
            <shiro:hasPermission name="quartz:list:edit">
              opStr+='<a class="si-option-a" href="${ctxA}/quartz/form?code='+row.code+'">修改</a>';
              opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
            </shiro:hasPermission>
            return opStr;
        }

        function stateformater(value,row,index) {
            if(value == 1){
                return "启用";
            }else if(value == 0){
                return "禁用";
            }
            return "未知";
        }

        function del(id){
            top.$.jBox.confirm("确定删除此任务吗",'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/push/sys/delete", {'id':id},
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
    <li class="active"><a href="javascript:void(0);">任务列表</a></li>
    <shiro:hasPermission name="quartz:list:edit">
        <li><a href="${ctxA}/quartz/form">创建任务</a></li>
    </shiro:hasPermission>
</ul>
<div class="si-warp" >
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'code',width:180,align:'center',halign:'center',fixed:true">任务编号</th>
            <th data-options="field:'name',width:180,align:'center',halign:'center',fixed:true">任务名称</th>
            <th data-options="field:'cycle',width:180,align:'center',halign:'center',fixed:true">执行周期</th>
            <th data-options="field:'succeed',width:200,align:'center',halign:'center',fixed:true">成功次数</th>
            <th data-options="field:'fail',width:160,align:'center',halign:'center',fixed:true">失败次数</th>
            <th data-options="field:'state',width:160,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>