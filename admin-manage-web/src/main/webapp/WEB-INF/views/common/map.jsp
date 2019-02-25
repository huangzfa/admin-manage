<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery"/>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7G9EEVrZaWxNLzuh3MEKiZ5dDcmYP3IY"></script>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
	}
	.baiduMap{
		width: 100%;
		height: 100%;
	}
</style>
<script type="text/javascript">
$(function(){
	$('#baiduMap').height($(window).height());
	//百度地图===============================================================
	var baiduMap = new BMap.Map("baiduMap"); // 创建地图实例
	var baiduMapMarkerIcon = new BMap.Icon("/static/img/Marker.png", new BMap.Size(40,40), {
		anchor: new BMap.Size(20, 40),
		imageSize:new BMap.Size(40,40)
	});
	baiduMap.enableScrollWheelZoom();
	baiduMap.addControl(new BMap.NavigationControl({ type: BMAP_NAVIGATION_CONTROL_LARGE, anchor: BMAP_ANCHOR_TOP_RIGHT }));
	var point = new BMap.Point(${lon}, ${lat}); // 创建点坐标  
	baiduMap.centerAndZoom(point, 15);
	var baiduMapMarker = new BMap.Marker(point, { icon: baiduMapMarkerIcon }); // 创建标注    
	baiduMap.addOverlay(baiduMapMarker);
	//百度地图===============================================================
	
});
</script>
</head>
<body>
	<div id="baiduMap" class="baiduMap"></div>
</body>
</html>