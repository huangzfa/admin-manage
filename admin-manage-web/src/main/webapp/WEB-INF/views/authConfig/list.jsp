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
        getData();
    });
    function getData(){
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/authConfig/getAuthConfigData',
            data:'&page='+pageNum+'&pagesize='+pageSize,
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
    //状态格式化
    function stateformater(value,row,index){
        if(value=='1'){
            return "可用";
        }else if(value=='0'){
            return "不可用";
        }
        return '未知';
    }
    //跳转类型格式化
    function pageformater(value,row,index){
        if(value=='1'){
            return "原生";
        }else if(value=='2'){
            return "H5";
        }
        return '未知';
    }
    //认证类型格式化
    function typeformater(value,row,index){
        if(value=='1'){
            return "基础认证";
        }else if(value=='2'){
            return "补充认证";
        }
        return '未知';
    }
    /**
	 * 图片格式化
     */
    function iconurlformater(value,row,index){
        if(value!="" && value!=null){
            return opStr='<img src="' + value + '" "style=width:33px; height:30px;margin-left:3px;" />'
        }else{
            return '';
        }
    }
    //有效期格式化
    function validValformater(value,row,index){
        if(value <= 0){
            return "不限制";
        }else{
            if( row.validUnit == 1){
                return value + "天";
			}else if(row.validUnit == 2){
                return value + "小时";
			}else{
                return '未知';
			}
        }
    }
    //操作
    function optionformater(value,row,index){
        var opStr='';
        <shiro:hasPermission name="authConfig:list:edit">
		    var isEnable = 0;
		    if( row.isEnable == 0) isEnable = 1;
			opStr+="<a class='si-option-a' href='javascript:editState(\""+row.authCode+"\",\""+isEnable+"\")'>"+(row.isEnable==1?"禁用":"启用")+"</a>";
			opStr+="<a class='si-option-a' href='${ctxA}/authConfig/form?authCode="+row.authCode+"'>编辑</a>";
        </shiro:hasPermission>
        return opStr;
    }
    function editState(authCode,authState){
        var title = "确定启用该认证项吗";
        if(authState == 0){
            title = "确定禁用该认证项吗";
		}
        top.$.jBox.confirm(title,'系统提示',function(v,h,f){
            if(v=='ok'){
                jQuery.post("${ctxA}/authConfig/editState", {'authCode':authCode,'authState':authState},
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
<div class="si-warp">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'authCode',width:100,align:'center',halign:'center',fixed:true">认证编码</th>
			<th data-options="field:'authType',width:120,align:'center',halign:'center',fixed:true,formatter:typeformater">认证类型</th>
			<th data-options="field:'authName',width:120,align:'center',halign:'center',fixed:true">认证项名称</th>
			<th data-options="field:'unImg',width:100,align:'center',halign:'center',fixed:true,formatter:iconurlformater">未认证图标</th>
			<th data-options="field:'successImg',width:100,align:'center',halign:'center',fixed:true,formatter:iconurlformater">认证完成图标</th>
			<th data-options="field:'ingImg',width:100,align:'center',halign:'center',fixed:true,formatter:iconurlformater">认证中图标</th>
			<th data-options="field:'failImg',width:100,align:'center',halign:'center',fixed:true,formatter:iconurlformater">认证失败图标</th>
			<th width="10%" data-options="field:'isEnable',width:160,align:'center',halign:'center',fixed:true,formatter:stateformater">是否可用</th>
			<th width="10%" data-options="field:'pageType',width:160,align:'center',halign:'center',fixed:true,formatter:pageformater">跳转类型</th>
			<th width="20%" data-options="field:'option',width:120,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
		</tr>
		</thead>
	</table>
</div>
</body>
</html>