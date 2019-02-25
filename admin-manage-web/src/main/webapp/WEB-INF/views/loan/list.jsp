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
        var loanType = [];
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        $(function(){
            var loanTypes ='${loanType}';
            loanType = eval("("+loanTypes+")");
            pager=$('#tt').datagrid('getPager');
            pager.pagination({
                onSelectPage:function(pageNumber, pageSize){
                    pageSize=pageSize;
                    getData(pageNumber,pageSize);
                }
            });
            $('.datagrid-pager .pagination-num').hide();
            //加载第一页数据
            getData(1,pageSize);

        });
        function getData(pageNum, pageSize){
            var data = {
                'page':pageNum,
                'pagesize':pageSize
            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/loan/getLoanData',
                data:data,
                dataType:'json',
                success:function(data){
                    if(data.code==1){
                        $('#tt').datagrid('loadData', data.list);
                        pager.pagination({
                            pageSize: pageSize,//每页显示的记录条数，默认为10
                            pageList: pageList,//可以设置每页记录条数的列表
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
            <shiro:hasPermission name="market:banner:edit">
                opStr+='<a class="si-option-a" href="${ctxA}/loan/form?id='+row.id+'">修改</a>';
                opStr+="<a class='si-option-a' href='javascript:openOperat(\""+row.id+"\",\""+row.name+"\")'>操作记录</a>";
            </shiro:hasPermission>
            return opStr;
        }

        function typeformater(value,row,index){
            var v = "未知"
            for(var i = 0;i<loanType.length;i++){
                if(loanType[i].dicVal == value){
                    v = loanType[i].dicCode;
                    break;
                }
            }
            return v;
        }

        function openOperat(pageId,name){
            loanOperateModal.open({
                'pageId':pageId,
                'type':name,
                callback:function(data){

                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/include/loanOperatDialog.jsp"/>
<div class="si-warp" >
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'id',width:80,align:'center',halign:'center',fixed:true">id</th>
            <th data-options="field:'type',width:160,align:'center',halign:'center',fixed:true,formatter:typeformater">所属位置</th>
            <th data-options="field:'value',width:400,align:'center',halign:'center',fixed:true">URL</th>
            <th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>