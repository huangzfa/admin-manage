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
        var loanType = [];
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        $(function(){
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
                url:'${ctxA}/ac/copyWriting/getData',
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
            <shiro:hasPermission name="copyWriting:list:edit">
            opStr+="<a class='si-option-a' href='javascript:edit(\""+row.id+"\")'>编辑</a>";
            </shiro:hasPermission>
            return opStr;
        }
        function textformater(value,row,index){
            if (row.type == 'OFFLINE_REPAY_SUBMIT_SUCCESS' ||
                row.type == 'OFFLINE_REPAY_SUBMIT_FAIL' ||
                row.type == 'BANK_REPAY_FEEDBACK') {

                return row.copywriting1 + ";" + row.copywriting2;
            }
            return value;
        }
        var close = true;
        function edit(id){
            top.layer.open({
                type: 2,
                title: '修改文案',
                shadeClose: true,
                shade: 0.8,
                area: ['680px', '50%'],
                btn: ['确定', '取消'],
                content: '${ctxA}/ac/copyWriting/form?id='+id,
                yes: function(index,layero) {
                    var form = $(layero).find("iframe")[0].contentWindow.document.getElementById("resourceForm");
                    var bool = true;
                    /*******  验证表单必填项目   ****************/
                    $(form).find(".valid").each(function() {
                        var descripe  = $(this).attr("descripe");
                        if( $(this).val()=="" && descripe!=""){
                            top.layer.alert(descripe, {icon: 5});
                            bool = false;
                            return false;
                        }
                    })
                    if( !bool ){
                        return false;
                    }
                    $(form).find("#btnSubmit").attr("disabled",true);
                    var data = {
                        'id':$(form).find("#id").val(),
                        'type':$(form).find("#type").val(),
                        'copywriting1':$(form).find("#copywriting1").val(),
                        'copywriting2':$(form).find("#copywriting2").val(),
                        'color':$(form).find("#color").val()
                    }
                    jQuery.post("${ctxA}/ac/copyWriting/update",data, function(data) {
                        $("#btnSubmit").attr("disabled",false);
                        if (data.code ==1) {
                            top.layer.alert("操作成功", {
                                icon: 6,
                                end: function(){
                                    top.layer.close(index);
                                    window.location.reload();
                                }
                            });
                        } else {
                            top.layer.alert(data.msg, {icon: 5});
                        }
                    }, "json");
                },
                no:function () {
                    
                }
            });
        }
    </script>
</head>
<body>
<ul class="ul-form _clearfix" style="display: none">
    <li>
        <label>轮播图名称：</label>
        <input id="titleName" placeholder="请输入轮播图名称" class="input-large" type="text" value="" maxlength="50" />
    </li>
    <li>
        <label>轮播位置：</label>
        <select  name="type" id="type" class="selectpicker show-tick form-control" >
            <option value="">请选择轮播位置</option>
        </select>
    </li>
    <li class="btns">
        <input id="search" class="btn btn-primary" type="submit" value="查询" />
    </li>
</ul>
<div class="si-warp" >
    <table id="tt" class="easyui-datagrid"
           data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'name',width:200,align:'center',halign:'center',fixed:true">页面位置</th>
            <th data-options="field:'copywriting1',width:400,align:'center',halign:'center',fixed:true,formatter:textformater">文案</th>
            <th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>