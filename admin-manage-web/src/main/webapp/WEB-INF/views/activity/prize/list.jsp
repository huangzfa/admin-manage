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
            var productList ='${productList}';
            list = eval("("+productList+")");
            for( var i = 0;i<list.length;i++){
                $("#productList").append("<option value='"+list[i].id+"'>"+list[i].productName+"</option>");
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
                'pagesize':pageSize

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/activity/prize/getPrizeData',
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
            <shiro:hasPermission name="activity:list:edit">
                opStr+='<a class="si-option-a" href="javascript:del('+row.prizeId+')">删除</a>';
                if( row.prizeType == 'hk' || row.prizeType == 'jk'){
                    opStr+='<a class="si-option-a" href="${ctxA}/activity/prize/form?prizeId='+row.prizeId+'">编辑</a>';
                }
            </shiro:hasPermission>
            return opStr;
        }

        function typeformater(value,row,index) {
            if( value == 'jk'){
                return "借款券";
            }else if( value == "hk"){
                return "还款券";
            }else if( value=="zyjp"){
                return "自由品类";
            }else if( value=="bzj"){
                return "谢谢参与";
            }else{
                return "未知";
            }
        }

        function stateformater(value,row,index){
            if( value == 1){
                return "有效";
            }else if( value == 0){
                return "无效";
            }
            return "未知";
        }

        function del(prizeId){

            top.$.jBox.confirm("确认删除此优惠券么？",'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/activity/prize/delete", {'prizeId':prizeId},
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
    <li class="active"><a href="javascript:void(0);">奖品列表</a></li>
    <shiro:hasPermission name="activity:list:edit">
        <li><a href="${ctxA}/activity/prize/form">添加奖品</a></li>
    </shiro:hasPermission>
    <li><a href="${ctxA}/activity/list">活动管理</a></li>
</ul>
<div class="si-warp" >
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'prizeName',width:180,align:'center',halign:'center',fixed:true">奖品名称</th>
            <th data-options="field:'imgUrl',width:180,align:'center',halign:'center',fixed:true">奖品图片</th>
            <th data-options="field:'prizeType',width:180,align:'center',halign:'center',fixed:true,formatter:typeformater">奖品类型</th>
            <th data-options="field:'link',width:200,align:'center',halign:'center',fixed:true">奖品链接</th>
            <th data-options="field:'state',width:160,align:'center',halign:'center',fixed:true,formatter:stateformater">是否有效</th>
            <th data-options="field:'addTime',width:200,align:'center',halign:'center',fixed:true">创建时间</th>
            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>