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
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum =1;
        $(function(){
            var productLists ='${productList}';
            var productList = eval("("+productLists+")");
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
                pageNum = 1;
                getData();
            });

        });
        function getData(){
            var data = {
                'page':pageNum,
                'pagesize':pageSize,
                'pushType':1,
                'productId':$("#productId").val(),
                'addOperatorName':$("#addOperatorName").val(),
                'pushStartTime':$("#pushStartTime").val(),
                'pushEndTime':$("#pushEndTime").val()

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/push/sys/getSysData',
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
        function typeformater(value,row,index) {
            if(value == 1){
                return "通知";
            }else if( value ==2){
                return "自定义消息";
            }

        }

        function countformater(value,row,index) {
            if( row.state ==1){
                return row.successCount;
            }
        }

        function optionformater(value,row,index){
            var opStr='';
            <shiro:hasPermission name="push:sys:edit">
                if( row.state == 0){
                    opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
                }else if(row.state == 1){
                    opStr+="<a class='si-option-a' href='javascript:exportFial(\""+row.id+"\")'>导出失败文件</a>";
                }else if(row.state == 2){
                    opStr+="<a class='si-option-a' >推送失败("+row.remark+")</a>";
                }
            </shiro:hasPermission>
            return opStr;
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

        function add() {
            window.location.href="${ctxA}/push/sys/form?productId="+$("#productId").val()
        }

        function exportFial(id) {
            window.location.href="${ctxA}/push/export?pushId="+id;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
    <li class="active"><a href="javascript:void(0);">推送列表</a></li>
    <shiro:hasPermission name="push:sys:edit">
        <li><a href="javascript:add()">新增推送</a></li>
    </shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form">
        <li>
            <label>产品：</label>
            <select  name="productId" id="productId" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">

            </select>
        </li>
        <li>
            <label>操作人员：</label>
            <input id="addOperatorName"  class="input-large" type="text" value="" maxlength="50"/>
        </li>
        <li>
            <label>启动时间：</label>
            <input id="pushStartTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
            -
            <input id="pushEndTime"  class="input-small" type="text" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
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
            <th data-options="field:'id',width:100,align:'center',halign:'center',fixed:true">推送id</th>
            <th data-options="field:'pushTime',width:180,align:'center',halign:'center',fixed:true">启动时间</th>
            <th data-options="field:'noticeType',width:100,align:'center',halign:'center',fixed:true,formatter:typeformater">通知类型</th>
            <th data-options="field:'productName',width:100,align:'center',halign:'center',fixed:true">推送产品</th>
            <th data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">推送标题</th>
            <th data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">推送内容</th>
            <th data-options="field:'pushCount',width:80,align:'center',halign:'center',fixed:true">推送数量</th>
            <th data-options="field:'successCount',width:80,align:'center',halign:'center',fixed:true,formatter:countformater">成功数量</th>
            <th data-options="field:'addOperatorName',width:80,align:'center',halign:'center',fixed:true">操作人员</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>