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
                'merchantName':$("#merchantName").val()

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/merchant/getMerchantData',
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
            <shiro:hasPermission name="merchant:list:edit">
              var state = 0;
              if( row.state == 0) state = 1;
              opStr+='<a class="si-option-a" href="${ctxA}/merchant/form?merchantNo='+row.merchantNo+'">修改</a>';
              opStr+="<a class='si-option-a' href='javascript:editState(\""+row.merchantNo+"\",\""+state+"\")'>"+(row.state==1?"禁用":"启用")+"</a>";
            </shiro:hasPermission>
            return opStr;
        }

        function stateformater(value,row,index) {
            if( value == 0){
                return '禁用'
            }else if( value == 1){
                return '启用'
            }
            return '未知';
        }

        function editState(merchantNo,state){
            var title = "确定启用用该商户吗";
            if(state == 0){
                title = "确定禁用该商户吗";
            }
            top.$.jBox.confirm(title,'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/merchant/editState", {'merchantNo':merchantNo,'state':state},
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
    <li class="active"><a href="javascript:void(0);">商户列表</a></li>
    <shiro:hasPermission name="merchant:list:edit">
        <li><a href="${ctxA}/merchant/form">创建商户</a></li>
    </shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
    <ul class="ul-form">
        <li>
            <label>商户名称：</label>
            <input id="merchantName"  class="input-large" type="text" value="" maxlength="50"/>
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
            <th data-options="field:'merchantNo',width:180,align:'center',halign:'center',fixed:true">商户编码</th>
            <th data-options="field:'merchantName',width:180,align:'center',halign:'center',fixed:true">商户名称</th>
            <th data-options="field:'addTime',width:160,align:'center',halign:'center',fixed:true">接入时间</th>
            <th data-options="field:'state',width:100,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>