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

</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li><a href="${ctxA}/order/refund/orderList">订单列表</a></li>
  <li class="active">
  	<a href="javascript:void(0);">订单详情</a>
  </li>
	
</ul>
<div class="si-warp">
	<br/>
	<div class="form-horizontal">
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
				${o.orderId}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				${orderState}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房费总额：</label>
			<div class="controls">
				${o.total/100.0 }元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">押金：</label>
			<div class="controls">
				${o.yajinPrice/100.0 }元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退款金额：</label>
			<div class="controls">
				${o.refundAmount/100.0 }元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房东姓名：</label>
			<div class="controls">
				${o.fdRealName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房东手机号：</label>
			<div class="controls">
				${o.fdMobile}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房客姓名：</label>
			<div class="controls">
				${o.fkNickname }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房客手机号：</label>
			<div class="controls">
				${o.fkMobile }
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">支付类型：</label>
			<div class="controls">
				${payType }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋名称：</label>
			<div class="controls">
				${o.houseTitle }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入住日期：</label>
			<div class="controls">
				${o.inDate }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退房日期：</label>
			<div class="controls">
				${o.outDate }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预订天数：</label>
			<div class="controls">
				${o.dayNum }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际入住日期：</label>
			<div class="controls">
				${o.realInDate }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际离开日期：</label>
			<div class="controls">
				${o.realOutDate }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际入住天数：</label>
			<div class="controls">
				${o.realDayNum } 天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入住人数：</label>
			<div class="controls">
				${o.rzrNum } 人
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预订房间数：</label>
			<div class="controls">
				${o.roomNum } 间
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入住人姓名：</label>
			<div class="controls">
				${o.rzrName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入住人身份证号码：</label>
			<div class="controls">
				${o.rzrIdcard }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入住人手机号：</label>
			<div class="controls">
				${o.rzrMobile }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房东确认时间：</label>
			<div class="controls">
				<fmt:formatDate value="${o.confirmTime }" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房客支付时间：</label>
			<div class="controls">
				<fmt:formatDate value="${o.payTime }" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">押金：</label>
			<div class="controls">
				${o.yajinPrice } 元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订金百分比：</label>
			<div class="controls">
				${o.dingjinDayBfb }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用明细：</label>
			<div class="controls">
				${o.dayInfo }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单时间：</label>
			<div class="controls">
				<fmt:formatDate value="${o.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">xxx：</label>
			<div class="controls">
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">xxx：</label>
			<div class="controls">
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">xxx：</label>
			<div class="controls">
				
			</div>
		</div>
	    <div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/order/orderList'"/>
		</div>
	</div>
</div>
</body>
</html>