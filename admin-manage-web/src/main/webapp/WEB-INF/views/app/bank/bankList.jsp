<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title></title>
	<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,97Date"/>
	<!--  -->
	<style type="text/css">
		.imageSmall{
			width: 60px;
			height: 60px;
		}

		.imageBig{
			width: 234px;
			height: 60px;
		}
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
                'bankName':$('#bankName').val(),
                'page':pageNum,
                'pagesize':pageSize,
                /*      'startDate':$('#startDate').val(),
                      'endDate':$('#endDate').val()*/

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/app/bank/bankList',
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


        function bankIconBackformatter(value,row,index){
            var opStr='';
            opStr='<img class="imageBig" src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'
            var isEnable = 0;
            if( row.isEnable == 0) isEnable = 1;
            opStr+="<a class='si-option-a' href='javascript:editState(\""+row.id+"\",\""+isEnable+"\")'>"+(row.isEnable==0?"启用":"禁用")+"</a>";
            return opStr;
        }
        function editState(id,isEnable){
            var msg = "确定禁用该银行吗";
            if( isEnable == 1){
                msg = "确定启用该银行吗";
            }
            top.$.jBox.confirm(msg,'系统提示',function(v,h,f){
                if(v=='ok'){
                    jQuery.post("${ctxA}/app/bank/editState", {'id':id,'isEnable':isEnable},
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

        function stateformatter(value,row,index){
            if(value=='1'){

                return "有效";

            }else if(value=='0'){

                return "无效";

            }
            return '未知';
        }

        function optionformatter(value,row,index){

            var opStr='';
            <shiro:hasPermission name="app:bank:edit">
            opStr+='<a class="si-option-a" href="${ctxA}/app/bank/form?id='+row.id+'">编辑</a>';
            var isEnable = 0;
            if( row.isEnable == 0) isEnable = 1;
            opStr+="<a class='si-option-a' href='javascript:editState(\""+row.id+"\",\""+isEnable+"\")'>"+(row.isEnable==0?"启用":"禁用")+"</a>";
            </shiro:hasPermission>
            return opStr;
        }

        function bankIconformatter(value,row,index){
            var opStr='';
            opStr='<img class="imageSmall" src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'
            return opStr;
        }

	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">银行卡列表</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">

		<li>
			<label>银行名称：</label>
			<input id="bankName" lass="input-large" type="text" value=""  />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th style="width:5%;" data-options="field:'bankName',width:80,align:'center',halign:'center',fixed:true">银行名称</th>
			<th style="width:15%;" data-options="field:'bankIcon',width:120,align:'center',halign:'center',fixed:true,formatter:bankIconformatter">银行图标1</th>
			<th style="width:10%;" data-options="field:'bankIconGrey',width:160,align:'center',halign:'center',fixed:true,formatter:bankIconformatter">银行灰度图标</th>
			<th style="width:5%;" data-options="field:'bankIconBack',width:160,align:'center',halign:'center',fixed:true,formatter:bankIconBackformatter">背景图片/th>
			<th style="width:15%;" data-options="field:'isEnable',width:160,align:'center',halign:'center',fixed:true,formatter:stateformatter">是否有效</th>
			<th style="width:10%;" data-options="field:'skRemark',width:160,align:'center',halign:'center',fixed:true">收款文案</th>
			<th style="width:10%;" data-options="field:'kkRemark',width:160,align:'center',halign:'center',fixed:true">扣款文案</th>
			<th style="width:10%;" data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformatter">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>