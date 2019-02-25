<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>运维平台</title>
<sys:jscss jscss="css,jquery,si,highcharts"/>
<!--  -->
<style type="text/css">
.totalItem{
	width: 20%;
	border: 1px solid #e0e0e0;
	height: 70px;
	line-height: 70px;
	padding: 0;
	margin: 0;
	margin-left: 3%;
}
.totalItem .icon{
	width: 70px;
	height: 70px;
	line-height: 70px;
}
.totalItem .icon.houseCheckIcon{
	background: url(/static/img/houseCheckIcon.png) no-repeat center center;
	background-size: 70%;
	background-color: #F7274C;
}
.totalItem .icon.order{
	background: url(/static/img/order.png) no-repeat center center;
	background-size: 50%;
	background-color: #FFC456;
}
.totalItem .icon.user{
	background: url(/static/img/userIcon.png) no-repeat center center;
	background-size: 60%;
	background-color: #50D1DE;
}
.totalItem .icon.house{
	background: url(/static/img/houseIcon.png) no-repeat center center;
	background-size: 60%;
	background-color: #E85FAB;
}


.totalItem .item{
	text-align: center;
	margin-left: 70px;
}
.totalItem .item .num{
	font-size: 25px;
	font-weight: 600;
	line-height: 40px;
}
.totalItem .item .tip{
	font-size: 13px;
	line-height: 20px;
}

.charWarp{
	position: relative;
	padding: 0;
	width: 95%;
}
.font15{
	font-size: 15px;
}
</style>
<script type="text/javascript">
$(function(){
	hjnUtils.ajax({  
        type:'post',      
        url:'${ctxA}/sys/sys/dataCenterData',  
        dataType:'json',  
        success:function(data){
        	if(data.code==1){
        		(function(){
        			var days=[];
            		var userArr=[];
            		var houseArr=[];
            		var orderArr=[];
            		$.each(data.days,function(i,d){
            			//parseInt(Math.random()*100)
            			days.push(parseInt(d.ymd/100%100)+'月'+d.ymd%100+'日');
            			userArr.push(d.userNum);
            			houseArr.push(d.houseNum);
            			orderArr.push(d.orderNum);
            		});
            		$('#dayReport').highcharts({
            	        chart: {
            	            type: 'line'
            	        },
            	        credits: {
            	            enabled: false
            	        },
            	        title: {
            	            text: '近30天每日统计'
            	        },
            	        colors: ['#50D1DE', '#E85FAB', '#FFC456'],
            	        xAxis: {
            	            categories: days
            	        },
            	        yAxis: {
            	            title: {
            	                text: ' '
            	            }
            	        },
            	        plotOptions: {
            	            line: {
            	                dataLabels: {
            	                    enabled: false          // 开启数据标签
            	                }
            	            }
            	        },
            	        series: [{
            	            name: '新增用户',
            	            data: userArr
            	        }, {
            	            name: '新增房源',
            	            data: houseArr
            	        }, {
            	            name: '新增订单',
            	            data: orderArr
            	        }]
            	    });
        		})();
        		(function(){
        			var months=[];
            		var userArr=[];
            		var houseArr=[];
            		var orderArr=[];
            		$.each(data.months,function(i,m){
            			months.push(parseInt(m.ym/100)+'年'+m.ym%100+'月');
            			userArr.push(m.userNum);
            			houseArr.push(m.houseNum);
            			orderArr.push(m.orderNum);
            		});
            		$('#monthReport').highcharts({
            	        chart: {
            	            type: 'line'
            	        },
            	        credits: {
            	            enabled: false
            	        },
            	        title: {
            	            text: '近12月每月统计'
            	        },
            	        colors: ['#50D1DE', '#E85FAB', '#FFC456'],
            	        xAxis: {
            	            categories: months
            	        },
            	        yAxis: {
            	            title: {
            	                text: ' '
            	            }
            	        },
            	        plotOptions: {
            	            line: {
            	                dataLabels: {
            	                    enabled: true          // 开启数据标签
            	                }
            	            }
            	        },
            	        series: [{
            	            name: '新增用户',
            	            data: userArr
            	        }, {
            	            name: '新增房源',
            	            data: houseArr
            	        }, {
            	            name: '新增订单',
            	            data: orderArr
            	        }]
            	    });
        		})();
        	}else{
        		top.layer.msg(data.msg);
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
	
});

</script>
</head>
<body>
	<div class="_clearfix" style="padding: 10px 0 20px 0;">
		<div class="totalItem _clearfix fl">
			<div class="icon houseCheckIcon fl"></div>
			<div class="item">
				<div class="num">${checkHouseTotal }</div>
				<div class="tip">待审核房源数</div>
			</div>
		</div>
		<div class="totalItem _clearfix fl">
			<div class="icon order fl"></div>
			<div class="item">
				<div class="num">${orderTotal }</div>
				<div class="tip">订单总数</div>
			</div>
		</div>
		<div class="totalItem _clearfix fl">
			<div class="icon user fl"></div>
			<div class="item">
				<div class="num">${userTotal }</div>
				<div class="tip">用户总数</div>
			</div>
		</div>
		<div class="totalItem _clearfix fl">
			<div class="icon house fl"></div>
			<div class="item">
				<div class="num">${houseTotal }</div>
				<div class="tip">房源总数</div>
			</div>
		</div>
	</div>
	<div id="dayReport" class="charWarp">
	
	</div>
	<div id="monthReport" class="charWarp">
	
	</div>
</body>
</html>