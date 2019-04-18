<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title></title>
	<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
	<!--  -->
	<style type="text/css">
		.datagrid-btable{
			width:100% !important;
		}
		.datagrid-header-inner{
			width:100% !important;
		}
		.datagrid-htable{
			width:100% !important;
		}
	</style>
    <script type="text/javascript">
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
        var pageNum = 1;
        var pager;
        $(function(){
            $("#myModal").hide();
            var appLists ='${appLists}';
            var appList = eval("("+appLists+")");
            var appId = '${appId}';
            for( var i = 0;i<appList.length;i++){
                $("#appId").append("<option value='"+appList[i].id+"'>"+appList[i].appName+"</option>");
            }
            if( appId!=''){
                $("#appId").val(appId);
            }else{
                $("#appId").val(appList[0].id);
            }

            pager = $('#tt').datagrid('getPager');
            pager.pagination({

                onSelectPage:function(number, size){
                    pageSize=size;
                    pageNum = number;
                    getData();
                }
            });
            $('.datagrid-pager .pagination-num').hide();
            //加载第一页数据
            getData();


            $('#search').click(function(){
                getData();
            });

            $('#myModal').on('shown.bs.modal', function () {
/*
                var repaymentTbody = $("#repaymentTbody")
                repaymentTbody.html("");*/

            })

        });
        function getData(){
            $('#tt').datagrid('loading');
            hjnUtils.ajax({
                type:'post',
                url:'${ctxA}/channel/productApp/productAppChannelList',
                data:{'channelName':$('#channelName').val(),'channelCode':$('#channelCode').val(),'appId':$("#appId").val(),'page':pageNum,'pagesize':pageSize},
                dataType:'json',
                success:function(data){
                    $('#tt').datagrid('loaded');
                    pager.pagination({
                        pageSize: pageSize,//每页显示的记录条数，默认为10
                        pageList: pageList,//可以设置每页记录条数的列表
                        pageNumber:pageNum,
                        layout:['list','sep','first','prev','links','next','last','sep','manual'],
                        beforePageText: '',
                        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                        afterPageText: ' 共 {pages} 页'
                    })
                    if(data.code==1){
                        $('#tt').datagrid('loadData', data.list);
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
            <shiro:hasPermission name="channel:productApp:edit">
            opStr+='<a class="si-option-a" href="#"  data-toggle="modal" data-target="#myModal" onclick="setChannelId('+row.id+')">编辑</a>';
            </shiro:hasPermission>
            return opStr;
        }
        function setChannelId(channelId){
            $("#channelStyleId").html("")
            $("#isEnable").html("")
            $("#channelId").val(channelId);
            jQuery.post('${ctxA}/channel/productApp/form',{'channelId':$("#channelId").val(),"appId":$("#appId").val()},
                function(data) {
                    if (data.code ==1) {
                        var flag = true
                        if(data.config.id != null){
                            flag = false;
                            $("#productAppId").val(data.config.id);
                        }
                        if (flag){
                            $("#productAppId").val("");
                            $("#channelStyleId").append("<option value=''>请选择</option>");

                        }
                        for (var i = 0;i < data.styleList.length; i++){
                            $("#channelStyleId").append("<option value='"+data.styleList[i].id+"'>"+data.styleList[i].styleName+"</option>");
                        }
                        if (!flag){
                            $("#channelStyleId").val(data.config.channelStyleId);
                        }
                        for (var i = 0;i < data.isEnableList.length; i++){
                            $("#isEnable").append("<option value='"+data.isEnableList[i].dicVal+"'>"+data.isEnableList[i].dicCode+"</option>");
                        }
                        $("#isEnable").val(data.config.isEnable)
                    } else {
                        top.layer.alert("系统异常", {icon: 5});
                    }

                }, "json");
		}

        function save(){
            var channelStyleId = $("#channelStyleId").val()
            if (channelStyleId == ""){
                top.layer.alert("请选择样式", {icon: 5});
                return false;
			}
            jQuery.post('${ctxA}/channel/productApp/save', {'channelId':$("#channelId").val(),"appId":$("#appId").val(),"id":$("#productAppId").val(),"isEnable":$("#isEnable").val(),"channelStyleId":channelStyleId},
                function(data) {
                    if (data.code ==1) {
                        top.layer.alert("操作成功", {
                            icon: 6,
                            end: function(){
                                window.location.href="${ctxA}/channel/productApp/list?appId="+$("#appId").val();
                            }
                        });
                    } else {
                        top.layer.alert("系统异常", {icon: 5});
                    }

                }, "json");
        }
        function channelStateformatter(value,row,index){
            if(row.productAppChannel== null){
                return "未配置";
            }else if(row.productAppChannel.isEnable=='1'){
                return "启用";
            }else if(row.productAppChannel.isEnable=='0'){
                return "禁用"
			}
         	return "未知";
        }
		function  dateformatter(value,row,index){
            if(row.productAppChannel== null){
                return "";
            }else{
                return row.productAppChannel.addTime
            }
        }

        function channelUrlformatter(value,row,index){
           return '暂无';
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">H5注册页链接配置列表</a></li>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>选择应用：</label>
			<select id="appId" name="appId" class="selectpicker show-tick form-control" onchange="getData()">
			</select>
		</li>
		<li>
			<label>渠道名称：</label>
			<input id="channelName"  class="input-large" type="text"  value=""
				   maxlength="32"/>
		</li>
		<li>
			<label>渠道编码：</label>
			<input id="channelCode" class="input-large" type="text"  value=""
				   maxlength="32"/>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询"/>
		</li>
	</ul>
</div>


<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'productAppChannelId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'id',width:138,align:'center',halign:'center',fixed:true">ID</th>
			<th data-options="field:'channelName',width:138,align:'center',halign:'center',fixed:true">所属渠道</th>
			<th data-options="field:'channelCode',width:138,align:'center',halign:'center',fixed:true">渠道编码</th>
			<th data-options="field:'channelUrl',width:138,align:'center',halign:'center',fixed:true,formatter:channelUrlformatter">注册链接</th>
			<th data-options="field:'channelState',width:138,align:'center',halign:'center',fixed:true,formatter:channelStateformatter">状态</th>
			<th data-options="field:'addTime',width:138,align:'center',halign:'center',fixed:true,formatter:dateformatter">创建时间</th>
			<th data-options="field:'opertion',width:138,align:'center',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-body">
				<input type="hidden" id="channelId" value="">
				<input type="hidden" id="productAppId" value="">
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center">选择样式：</label>
					<div class="controls" style="display: inline-block">
						<select  name="channelStyleId" id="channelStyleId" class="selectpicker show-tick form-control" >
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="control-group" style="border-bottom: none">
					<label class="control-label" style="width: 30%;text-align: center;">状态：</label>
					<div class="controls" style="display: inline-block">
						<select  name="isEnable" id="isEnable" class="selectpicker show-tick form-control" >
						</select>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="save()" >提交更改</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
</html>