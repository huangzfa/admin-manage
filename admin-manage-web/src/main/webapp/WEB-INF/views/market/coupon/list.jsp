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
    var productList =[];
    var pageSize=${cfg:getPageSize()};
    var pageList=[pageSize,30,50];
    var pageNum =1;
    $(function(){
        var typeList ='${couponType}';
        couponType = eval("("+typeList+")");
        for( var i = 0;i<couponType.length;i++){
            $("#couponType").append("<option value='"+couponType[i].dicVal+"'>"+couponType[i].dicCode+"</option>");
		}
        var productLists ='${productList}';
        productList = eval("("+productLists+")");
        for( var i = 0;i<productList.length;i++){
            $("#productId").append("<option value='"+productList[i].id+"'>"+productList[i].productName+"</option>");
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
        var data = {
            'productId':$('#productId').val(),
			'couponType':$('#couponType').val(),
            'couponName':$('#couponName').val(),
            'state':$('#state').val(),
			'page':pageNum,
			'pagesize':pageSize
		}
        hjnUtils.ajax({
            type:'post',
            url:'${ctxA}/market/coupon/getCouponData',
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
        <shiro:hasPermission name="market:coupon:edit">
        opStr+='<a class="si-option-a" href="${ctxA}/market/coupon/form?id='+row.id+'&productId='+ row.productId+'">修改</a>';
        opStr+="<a class='si-option-a' href='javascript:del(\""+row.id+"\")'>删除</a>";
        </shiro:hasPermission>
        return opStr;
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
            var gmtStart = new Date(row.gmtStart.replace(/\-/g, "\/"));
            var gmtEnd = new Date(row.gmtEnd.replace(/\-/g, "\/"));
            if(gmtStart > new Date()){
                return '未开始';
			}
			else if(gmtEnd < new Date()){
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
    function typeFormater(value,row,index){
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
    function countFormater(value,row,index){
        if( value == -1){
            return "无限制";
		}
		return value;
	}

    function validDaysFormater(value,row,index){
		if( row.expiryType == 1){
           return row.gmtStart+"至"+row.gmtEnd;
		}
		return row.validDays;
	}

	function limitAmountFormater(value,row,index){
        if( value == 0){
            return "无限制";
        }
        return value;
	}

	function productFormater(value,row,index){
        var productName = "未知";
        for(var i = 0 ;i<productList.length;i++){
            if( productList[i].id == value){
                productName = productList[i].productName;
                break;
			}
		}
		return productName;
	}

	function add() {
        var productId = $("#productId").val();
		window.location.href="${ctxA}/market/coupon/form?productId="+productId;
    }
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
	<li class="active"><a href="">优惠券列表</a></li>
	<shiro:hasPermission name="market:coupon:edit">
		<li><a href="javascript:add();">添加优惠券</a></li>
	</shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form" style="margin-right: 20px">
		<li>
			<label>产品：</label>
			<select  name="productId" id="productId" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">

			</select>
		</li>
		<li>
			<label>优惠券名称：</label>
			<input id="couponName" placeholder="请输入优惠券名称" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>使用状态：</label>
			<select  name="state" id="state" class="selectpicker show-tick form-control" onchange="getData()" style="width: 152px">
				<option value="">全部</option>
				<option value="1">未开始</option>
				<option value="2">进行中</option>
				<option value="3">已结束</option>
			</select>
		</li>
		<li>
			<label>优惠券类型：</label>
			<select  name="couponType" id="couponType" class="selectpicker show-tick form-control" style="width: 152px" onchange="getData()">
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
			<th data-options="field:'couponName',width:140,align:'center',halign:'center',fixed:true">优惠券名称</th>
			<th data-options="field:'productId',width:140,align:'center',halign:'center',fixed:true,formatter:productFormater">所属产品</th>
			<th data-options="field:'couponType',width:100,align:'center',halign:'center',fixed:true,formatter:typeFormater">优惠券类型</th>
			<th data-options="field:'amount',width:100,align:'center',halign:'center',fixed:true">面值</th>
			<th data-options="field:'personLimitCount',width:100,align:'center',halign:'center',fixed:true,formatter:countFormater">领取限制</th>
			<th data-options="field:'limitAmount',width:100,align:'center',halign:'center',fixed:true,formatter:limitAmountFormater">金额限制</th>
			<th data-options="field:'validDays',width:280,align:'center',halign:'center',fixed:true,formatter:validDaysFormater">有效期(天)</th>
			<th data-options="field:'quota',width:100,align:'center',halign:'center',fixed:true">发行量</th>
			<th data-options="field:'expiryType',width:100,align:'center',halign:'center',fixed:true,formatter:stateformater">使用状态</th>
			<th data-options="field:'option',width:160,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>

		</tr>
		</thead>
	</table>
</div>
</body>
</html>