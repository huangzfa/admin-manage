<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
<script type="text/javascript">
	var pager;
	var pageSize=${cfg:getPageSize()};
	var pageList=[pageSize,30,50];
	var pageNum =1;
	$(function(){
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
		$('#reset').click(function(){
			$("#channelName").val(null);
			$("#versionNumber").val("");
			$("#appId").val(null);
		});
	});
	function getData(){
	    var appId = $("#appId").val()
        var data = {
            'productId':$('#productId').val(),
            'appId':appId,
            'versionNumber':$('#versionNumber').val(),
            'channelName':$("#channelName").val(),
            'page':pageNum,
            'pagesize':pageSize,
            /*      'startDate':$('#startDate').val(),
                  'endDate':$('#endDate').val()*/

        }
		  hjnUtils.ajax({
			  type:'post',
			  url:'${ctxA}/app/upgrade/upgradeList',
			  dataType:'json',
			  data:data,
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

                      $("#appId").html("");
                      $("#appId").append("<option value=''>全部</option>");
					  var appList = data.appList;
                      for( var i = 0;i<appList.length;i++){
                          $("#appId").append("<option value='"+appList[i].id+"'>"+appList[i].appName+"</option>");
                      }
                      if( appId!=''){
                          $("#appId").val(appId);
                      }

				  }else{
					  top.$.jBox.tip(data.msg);
				  }
			  },
			  error:function(XMLHttpRequest, textStatus, errorThrown) {
				  $('#tt').datagrid('loaded');
			  }
		  });
	}
	function appSystemTypeformatter(value,row,index){
            if(value == 'ios'){
                return "IOS";
            }else if(value == 'android'){
                return "安卓";
            }else if (value == '') {
                return "不限制";
            }
            return '未知';
	}
    function stateformatter(value,row,index){
 		if (value == '0'){
 		    return '关闭状态'
		}else if (value == '1'){
 		    return '开启状态'
		}
		return '未知';
    }
    function upgradeRangeformatter(value,row,index){
        if (value == '0'){
            return '所有版本'
        }else if (value == '1'){
            return row.minVersionNumber+"~"+row.maxVersionNumber
        }
        return '未知';
    }
    function isForceformatter(value,row,index){
        if (value == '0'){
            return '否'
        }else if (value == '1'){
            return '是'
        }
        return '未知';
    }
    function isSilenceformatter(value,row,index){
        if (value == '0'){
            return '否'
        }else if (value == '1'){
            return '是'
        }
        return '未知';
    }

    function optionformatter(value,row,index){
            var opStr='';
            <shiro:hasPermission name="app:upgrade:edit">
            opStr+='<a class="si-option-a" href="${ctxA}/app/upgrade/form?id='+row.id+'">编辑</a>';
       		 opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
			var isEnable = 0;
        	if( row.state == 0) {isEnable = 1;}

        opStr+="<a class='si-option-a' href='javascript:editState(\""+isEnable+"\" ,\""+row.id+"\"   )'>"+(row.state==0?"禁用":"启用")+"</a>";
            </shiro:hasPermission>
            return opStr;
	}


    function del(id){
        top.$.jBox.confirm("确定删除该审核吗？",'系统提示',function(v,h,f){
            if(v=='ok'){
                jQuery.post("${ctxA}/app/upgrade/delete", {'id':id},
                    function(data) {
                        if (data.code ==1) {
                            top.layer.alert("操作完成", {
                                icon: 6,
                                end: function(){
                                    pageNum = 1;
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

    function editState(state,id) {
        $('#tt').datagrid('loading');
        var title = "确定启用？";
        if(status == 1){
            title = "确定禁用？";
        }
        top.$.jBox.confirm(title,'系统提示',function(v,h,f){
            $('#tt').datagrid('loaded');
            if(v=='ok'){
                jQuery.post('${ctxA}/app/upgrade/editState',{'state':state,'id':id},
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
    function flush() {
        window.location.href="${ctxA}/app/upgrade/list?productId="+$("#productId").val();
    }
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
	<li class="active"><a href="javascript:void(0);">APP升级管理</a></li>
	<shiro:hasPermission name="app:upgrade:edit">
		<li><a href="${ctxA}/app/upgrade/form">新增APP升级配置</a></li>
	</shiro:hasPermission>
</ul>

<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>选择产品：</label>
			<select id="productId" name="productId" class="selectpicker show-tick form-control " onchange="flush()">
			</select>
		</li>
		<li>
			<label>应用：</label>
			<select id="appId" name="appId" class="selectpicker show-tick form-control">
			</select>
		</li>

		<li>
			<label>版本号：</label>
			<input id="versionNumber"  class="input-large" type="text" value="" maxlength="50"/>
		</li>

		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询"/>
		</li>
		<li class="clearfix">
			<input id="reset" class="btn btn-primary" type="submit" value="重置"/>
		</li>
	</ul>
</div>

<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'appExamId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th style="width: 10%" data-options="field:'appName',width:216,align:'center',halign:'center',fixed:true">应用</th>
			<th style="width: 10%" data-options="field:'versionNumber',width:216,align:'center',halign:'center',fixed:true">版本号</th>
			<th style="width: 10%" data-options="field:'versionName',width:216,align:'center',halign:'center',fixed:true">版本名</th>
			<th style="width: 5%" data-options="field:'state',width:216,align:'center',halign:'center',fixed:true,formatter:stateformatter">系统</th>
			<th style="width: 30%" data-options="field:'versionRemark',width:216,align:'center',halign:'center',fixed:true">版本描述</th>
			<th style="width: 5%" data-options="field:'upgradeRange',width:216,align:'center',halign:'center',fixed:true,formatter:upgradeRangeformatter">升级范围</th>
			<th style="width: 5%" data-options="field:'isForce',width:216,align:'center',halign:'center',fixed:true,formatter:isForceformatter">升级范围</th>
			<th style="width: 5%" data-options="field:'isSilence',width:216,align:'center',halign:'center',fixed:true,formatter:isSilenceformatter">升级范围</th>
			<th style="width: 20%" data-options="field:'option',width:216,align:'center',halign:'center',fixed:true,formatter:optionformatter">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>