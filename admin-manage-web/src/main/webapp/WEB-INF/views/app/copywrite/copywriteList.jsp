<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title></title>
	<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
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
            $("#myModal").hide();
            var productLists ='${productLists}';
            var productList = eval("("+productLists+")");
            var productId = '${productId}';
            for( var i = 0;i<productList.length;i++){
                $("#productId").append("<option value='"+productList[i].id+"'>"+productList[i].productName+"</option>");
            }
            if( productId!=''){
                $("#productId").val(productId);
            }else{
                $("#productId").val(productList[0].id);
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
                'productId':$('#productId').val(),
                'page':pageNum,
                'pagesize':pageSize,
                /*      'startDate':$('#startDate').val(),
                      'endDate':$('#endDate').val()*/

            }
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/app/copywrite/copywriteList',
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

        function optionformatter(value,row,index){

            var opStr='';
            <shiro:hasPermission name="app:copywrite:edit">
            opStr+='<a class="si-option-a" href="#"  data-toggle="modal" data-target="#myModal" onclick="getConfig('+row.id+')">编辑</a>';
            </shiro:hasPermission>
            return opStr;
        }

        function getConfig(id){
            $("#positionName").val("");
            $("#myModal").find("input").val("");
            $("#id").val(id);
            jQuery.post('${ctxA}/app/copywrite/form',{'id':$("#id").val()},
                function(data) {
                    if (data.code ==1) {
                        $("#positionName").html(data.copywriteConfig.positionName);
                        $("#copywriting1").val(data.copywriteConfig.copywriting1);
                        $("#copywriting2").val(data.copywriteConfig.copywriting2);
                        $("#copywriting3").val(data.copywriteConfig.copywriting3);

                    } else {
                        top.layer.alert("系统异常", {icon: 5});
                    }

                }, "json");
        }

        function update(){
            var id =  $("#id").val();
            var copywriting1 = $("#copywriting1").val();
            if (copywriting1 == "" || copywriting1 == null){
                top.layer.alert("文案不能为空", {icon: 5});
                return false;
            }
            var copywriting2 = $("#copywriting2").val();
            var copywriting3 = $("#copywriting3").val();
            jQuery.post('${ctxA}/app/copywrite/save', {'id':id,"copywriting1":copywriting1,"copywriting2":copywriting2,"copywriting3":copywriting3},
                function(data) {
                    if (data.code ==1) {
                        top.layer.alert("修改成功", {
                            icon: 6,
                            end: function(){
                                window.location.href="${ctxA}/app/copywrite/list?productId="+$("#productId").val();
                            }
                        });
                    } else {
                        top.layer.alert("系统异常", {icon: 5});
                    }

                }, "json");
        }


	</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">页面文案配置</a></li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form _clearfix">

		<li>
			<label>产品名称：</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control" onchange="getData()">
			</select>
		</li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th style="width:10%;" data-options="field:'positionName',width:80,align:'center',halign:'center',fixed:true">页面位置</th>
			<th style="width:30%;" data-options="field:'copywriting1',width:120,align:'center',halign:'center',fixed:true">文案1</th>
			<th style="width:30%;" data-options="field:'copywriting2',width:120,align:'center',halign:'center',fixed:true">文案2</th>
			<th style="width:30%;" data-options="field:'copywriting3',width:120,align:'center',halign:'center',fixed:true">文案3</th>
			<th style="width:10%;" data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformatter">操作</th>
		</tr>
		</thead>
	</table>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-body">
				<input type="hidden" id="id" value="">
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">页面位置：</label>
					<div class="controls" style="display: inline-block">
						<label class="control-label" style="text-align: center" id="positionName"></label>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">文案1(必填)：</label>
					<div class="controls" style="display: inline-block">
						<textarea class="control-label" id="copywriting1" maxlength="40"></textarea>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none;">
					<label class="control-label" style="width: 30%;text-align: center">文案2：</label>
					<div class="controls" style="display: inline-block">
						<textarea class="control-label" id="copywriting2" maxlength="40"></textarea>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">文案3：</label>
					<div class="controls" style="display: inline-block">
						<textarea class="control-label" id="copywriting3" maxlength="40"></textarea>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="update()" >保存</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
</html>