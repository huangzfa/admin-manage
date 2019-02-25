<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
});
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="active">
  	<a href="javascript:void(0);">业务参数</a>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<div class="form-horizontal">
		<div class="control-group">
			<label class="control-label">平台默认手续费比例：</label>
			<div class="controls">
				${cf.poundageBfb}%
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规则时间点(HH:mm)：</label>
			<div class="controls">
				${cf.ruleTime}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最少房屋照片数量：</label>
			<div class="controls">
				${cf.houseImgCount} 张
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日历显示月数：</label>
			<div class="controls">
				${cf.showMonthCount} 个月
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最低房价：</label>
			<div class="controls">
				${cf.minDayPrice} 元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退款有效期：</label>
			<div class="controls">
				${cf.refundExpiryMonthCount} 个月
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">默认地图房源搜索范围：</label>
			<div class="controls">
				${cf.positionDistance} 米
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房东订单确认时间限制：</label>
			<div class="controls">
				${cf.landlordConfirmTimeLimit} 分钟
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房客订单支付时间限制：</label>
			<div class="controls">
				${cf.tenantPayTimeLimit} 分钟
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房东订单退款时间限制：</label>
			<div class="controls">
				${cf.landlordRefundTimeLimit} 分钟
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户token有效期：</label>
			<div class="controls">
				${cf.userTokenValidTime} 天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客服电话：</label>
			<div class="controls">
				${cf.servicePhone}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">app分享url：</label>
			<div class="controls">
				${cf.appShareUrl}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房源分享url：</label>
			<div class="controls">
				${cf.houseShareUrl}
			</div>
		</div>
	</div>
</div>
</body>
</html>