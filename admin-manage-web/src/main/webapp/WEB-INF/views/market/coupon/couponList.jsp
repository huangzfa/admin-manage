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
    var couponType = [];
    var pageSize=${cfg:getPageSize()};
    var pageList=[pageSize,30,50];
    var pageNum =1;
    $(function(){
        var typeList ='${couponType}';
        couponType = eval("("+typeList+")");
        for( var i = 0;i<couponType.length;i++){
            $("#couponType").append("<option value='"+couponType[i].dicVal+"'>"+couponType[i].dicCode+"</option>");
		}
        var pageSize=${cfg:getPageSize()};
        var pageList=[pageSize,30,50];
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
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/market/coupon/getCouponData',
            data:'couponType='+$('#couponType').val()+'&couponName='+$('#couponName').val()+'&page='+pageNum+'&pagesize='+pageSize,
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
    /**
	 * 返回状态
     * @param value
     * @param row
     * @param index
     * @returns {*}
     */
    function stateformater(value,row,index){

        var expirytype = row.expiryType;
        if( expirytype ==1 ){
            var validStartTime = new Date(row.validStartTime.replace(/\-/g, "\/"));
            var validEndTime = new Date(row.validEndTime.replace(/\-/g, "\/"));
            if(validStartTime > new Date()){
                return '未开始';
			}
			else if(validEndTime < new Date()){
                return '已结束';
            }
		}
        return '进行中';

	}

    /**
	 * 返回优惠券类型
     * @param value
     * @param row
     * @param index
     * @returns {string}
     */
    function typeformater(value,row,index){
        var v = "未知"
		for(var i = 0;i<couponType.length;i++){
		    if(couponType[i].dicVal == value){
                v = couponType[i].dicCode;
                break;
			}
		}
		return v;
	}

    /**
	 * 返回领取限制
     * @param value
     * @param row
     * @param index
     * @returns {*}
     */
    function countformater(value,row,index){
        if( value == -1){
            return "无限制";
		}
		return value;
	}

    function validDaysFormater(value,row,index){
		if( value == -1){
           return row.validStartTime+"至"+row.validEndTime;
		}
		return value;
	}
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="javascript:void(0);">优惠券列表</a></li>
	<shiro:hasPermission name="market:coupon:edit">
		<li><a href="${ctxA}/market/coupon/form">添加优惠券</a></li>
	</shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form" style="margin-right: 20px">
		<li>
			<label>优惠券名称：</label>
			<input id="couponName" placeholder="请输入优惠券名称" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>优惠券类型：</label>
			<select  name="couponType" id="couponType" class="selectpicker show-tick form-control" >
				<option value="">全部</option>
			</select>
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<table id="tt" class="easyui-datagrid"
		   data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
		<tr>
			<th data-options="field:'couponName',width:180,align:'center',halign:'center',fixed:true">优惠券名称</th>
			<th data-options="field:'couponType',width:100,align:'center',halign:'center',fixed:true,formatter:typeformater">优惠券类型</th>
			<th data-options="field:'amount',width:100,align:'center',halign:'center',fixed:true">面值</th>
			<th data-options="field:'limitCount',width:160,align:'center',halign:'center',fixed:true,formatter:countformater">领取限制</th>
			<th data-options="field:'validDays',width:280,align:'center',halign:'center',fixed:true,formatter:validDaysFormater">有效期(天)</th>
			<th data-options="field:'quota',width:100,align:'center',halign:'center',fixed:true">发行量</th>
			<th data-options="field:'quotaSend',width:100,align:'center',halign:'center',fixed:true">已经使用</th>
			<th data-options="field:'option',width:100,align:'center',halign:'center',fixed:true,formatter:stateformater">使用状态</th>

		</tr>
		</thead>
	</table>
</div>
</body>
</html>