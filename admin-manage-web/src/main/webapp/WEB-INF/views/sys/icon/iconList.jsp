<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>图标选择</title>
<sys:jscss jscss="jquery,webfont"/>
<link href="${ctxStatic}/webfont/font-awesome/css/site.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body{
	overflow-x:hidden;
}
.fontawesome-icon-list .fa-hover a.active {
    background-color: #1d9d74;
    color: #fff;
    text-decoration: none;
}
</style>
<script type="text/javascript">
$(function(){
	$("div.fa-hover > a").attr("href","javascript:void(0);"); 
	$("div.fa-hover > a").click(function(){
		$("div.fa-hover > a").removeClass("active");
		$(this).addClass("active");
		$("#icon").val($(this).children("i:first").attr('class'));
	});
	$("div.fa-hover > a").each(function(){
		if ($(this).children("i:first").attr('class')=="${value}"){
			$(this).click();
			return false;
		}
	});
	$("div.fa-hover > a").dblclick(function(){
		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
	});
});
</script>
</head>
<body>
<input type="hidden" id="icon" value="${value}" />
<div id="icons">
    <section id="new">
  <h2 class="page-header">30 New Icons in 4.6</h2>

  <div class="row fontawesome-icon-list">
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/american-sign-language-interpreting"><i class="fa fa-asl-interpreting" aria-hidden="true"></i>asl-interpreting</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/assistive-listening-systems"><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i>assistive-listening-systems</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/audio-description"><i class="fa fa-audio-description" aria-hidden="true"></i>audio-description</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/blind"><i class="fa fa-blind" aria-hidden="true"></i>blind</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/braille"><i class="fa fa-braille" aria-hidden="true"></i>braille</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-deaf" aria-hidden="true"></i>deaf</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-deafness" aria-hidden="true"></i>deafness</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/envira"><i class="fa fa-envira" aria-hidden="true"></i>envira</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/font-awesome"><i class="fa fa-fa" aria-hidden="true"></i>fa</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/first-order"><i class="fa fa-first-order" aria-hidden="true"></i>first-order</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/font-awesome"><i class="fa fa-font-awesome" aria-hidden="true"></i>font-awesome</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gitlab"><i class="fa fa-gitlab" aria-hidden="true"></i>gitlab</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/glide"><i class="fa fa-glide" aria-hidden="true"></i>glide</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/glide-g"><i class="fa fa-glide-g" aria-hidden="true"></i>glide-g</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-plus-official"><i class="fa fa-google-plus-circle" aria-hidden="true"></i>google-plus-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-plus-official"><i class="fa fa-google-plus-official" aria-hidden="true"></i>google-plus-official</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-hard-of-hearing" aria-hidden="true"></i>hard-of-hearing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/instagram"><i class="fa fa-instagram" aria-hidden="true"></i>instagram</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/low-vision"><i class="fa fa-low-vision" aria-hidden="true"></i>low-vision</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pied-piper"><i class="fa fa-pied-piper" aria-hidden="true"></i>pied-piper</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/question-circle-o"><i class="fa fa-question-circle-o" aria-hidden="true"></i>question-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-language"><i class="fa fa-sign-language" aria-hidden="true"></i>sign-language</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-language"><i class="fa fa-signing" aria-hidden="true"></i>signing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/snapchat"><i class="fa fa-snapchat" aria-hidden="true"></i>snapchat</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/snapchat-ghost"><i class="fa fa-snapchat-ghost" aria-hidden="true"></i>snapchat-ghost</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/snapchat-square"><i class="fa fa-snapchat-square" aria-hidden="true"></i>snapchat-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/themeisle"><i class="fa fa-themeisle" aria-hidden="true"></i>themeisle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/universal-access"><i class="fa fa-universal-access" aria-hidden="true"></i>universal-access</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/viadeo"><i class="fa fa-viadeo" aria-hidden="true"></i>viadeo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/viadeo-square"><i class="fa fa-viadeo-square" aria-hidden="true"></i>viadeo-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/volume-control-phone"><i class="fa fa-volume-control-phone" aria-hidden="true"></i>volume-control-phone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair-alt"><i class="fa fa-wheelchair-alt" aria-hidden="true"></i>wheelchair-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wpbeginner"><i class="fa fa-wpbeginner" aria-hidden="true"></i>wpbeginner</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wpforms"><i class="fa fa-wpforms" aria-hidden="true"></i>wpforms</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/yoast"><i class="fa fa-yoast" aria-hidden="true"></i>yoast</a></div>
    
  </div>

</section>

    <section id="web-application">
  <h2 class="page-header">Web Application Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/adjust"><i class="fa fa-adjust" aria-hidden="true"></i>adjust</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/american-sign-language-interpreting"><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i>american-sign-language-interpreting</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/anchor"><i class="fa fa-anchor" aria-hidden="true"></i>anchor</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/archive"><i class="fa fa-archive" aria-hidden="true"></i>archive</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/area-chart"><i class="fa fa-area-chart" aria-hidden="true"></i>area-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows"><i class="fa fa-arrows" aria-hidden="true"></i>arrows</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows-h"><i class="fa fa-arrows-h" aria-hidden="true"></i>arrows-h</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows-v"><i class="fa fa-arrows-v" aria-hidden="true"></i>arrows-v</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/american-sign-language-interpreting"><i class="fa fa-asl-interpreting" aria-hidden="true"></i>asl-interpreting</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/assistive-listening-systems"><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i>assistive-listening-systems</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/asterisk"><i class="fa fa-asterisk" aria-hidden="true"></i>asterisk</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/at"><i class="fa fa-at" aria-hidden="true"></i>at</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/audio-description"><i class="fa fa-audio-description" aria-hidden="true"></i>audio-description</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/car"><i class="fa fa-automobile" aria-hidden="true"></i>automobile</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/balance-scale"><i class="fa fa-balance-scale" aria-hidden="true"></i>balance-scale</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ban"><i class="fa fa-ban" aria-hidden="true"></i>ban</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/university"><i class="fa fa-bank" aria-hidden="true"></i>bank</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bar-chart"><i class="fa fa-bar-chart" aria-hidden="true"></i>bar-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bar-chart"><i class="fa fa-bar-chart-o" aria-hidden="true"></i>bar-chart-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/barcode"><i class="fa fa-barcode" aria-hidden="true"></i>barcode</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bars"><i class="fa fa-bars" aria-hidden="true"></i>bars</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-empty"><i class="fa fa-battery-0" aria-hidden="true"></i>battery-0</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-quarter"><i class="fa fa-battery-1" aria-hidden="true"></i>battery-1</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-half"><i class="fa fa-battery-2" aria-hidden="true"></i>battery-2</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-three-quarters"><i class="fa fa-battery-3" aria-hidden="true"></i>battery-3</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-full"><i class="fa fa-battery-4" aria-hidden="true"></i>battery-4</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-empty"><i class="fa fa-battery-empty" aria-hidden="true"></i>battery-empty</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-full"><i class="fa fa-battery-full" aria-hidden="true"></i>battery-full</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-half"><i class="fa fa-battery-half" aria-hidden="true"></i>battery-half</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-quarter"><i class="fa fa-battery-quarter" aria-hidden="true"></i>battery-quarter</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/battery-three-quarters"><i class="fa fa-battery-three-quarters" aria-hidden="true"></i>battery-three-quarters</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bed"><i class="fa fa-bed" aria-hidden="true"></i>bed</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/beer"><i class="fa fa-beer" aria-hidden="true"></i>beer</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bell"><i class="fa fa-bell" aria-hidden="true"></i>bell</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bell-o"><i class="fa fa-bell-o" aria-hidden="true"></i>bell-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bell-slash"><i class="fa fa-bell-slash" aria-hidden="true"></i>bell-slash</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bell-slash-o"><i class="fa fa-bell-slash-o" aria-hidden="true"></i>bell-slash-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bicycle"><i class="fa fa-bicycle" aria-hidden="true"></i>bicycle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/binoculars"><i class="fa fa-binoculars" aria-hidden="true"></i>binoculars</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/birthday-cake"><i class="fa fa-birthday-cake" aria-hidden="true"></i>birthday-cake</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/blind"><i class="fa fa-blind" aria-hidden="true"></i>blind</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bluetooth"><i class="fa fa-bluetooth" aria-hidden="true"></i>bluetooth</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bluetooth-b"><i class="fa fa-bluetooth-b" aria-hidden="true"></i>bluetooth-b</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bolt"><i class="fa fa-bolt" aria-hidden="true"></i>bolt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bomb"><i class="fa fa-bomb" aria-hidden="true"></i>bomb</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/book"><i class="fa fa-book" aria-hidden="true"></i>book</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bookmark"><i class="fa fa-bookmark" aria-hidden="true"></i>bookmark</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bookmark-o"><i class="fa fa-bookmark-o" aria-hidden="true"></i>bookmark-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/braille"><i class="fa fa-braille" aria-hidden="true"></i>braille</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/briefcase"><i class="fa fa-briefcase" aria-hidden="true"></i>briefcase</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bug"><i class="fa fa-bug" aria-hidden="true"></i>bug</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/building"><i class="fa fa-building" aria-hidden="true"></i>building</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/building-o"><i class="fa fa-building-o" aria-hidden="true"></i>building-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bullhorn"><i class="fa fa-bullhorn" aria-hidden="true"></i>bullhorn</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bullseye"><i class="fa fa-bullseye" aria-hidden="true"></i>bullseye</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bus"><i class="fa fa-bus" aria-hidden="true"></i>bus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/taxi"><i class="fa fa-cab" aria-hidden="true"></i>cab</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calculator"><i class="fa fa-calculator" aria-hidden="true"></i>calculator</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calendar"><i class="fa fa-calendar" aria-hidden="true"></i>calendar</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calendar-check-o"><i class="fa fa-calendar-check-o" aria-hidden="true"></i>calendar-check-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calendar-minus-o"><i class="fa fa-calendar-minus-o" aria-hidden="true"></i>calendar-minus-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calendar-o"><i class="fa fa-calendar-o" aria-hidden="true"></i>calendar-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calendar-plus-o"><i class="fa fa-calendar-plus-o" aria-hidden="true"></i>calendar-plus-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/calendar-times-o"><i class="fa fa-calendar-times-o" aria-hidden="true"></i>calendar-times-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/camera"><i class="fa fa-camera" aria-hidden="true"></i>camera</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/camera-retro"><i class="fa fa-camera-retro" aria-hidden="true"></i>camera-retro</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/car"><i class="fa fa-car" aria-hidden="true"></i>car</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-down"><i class="fa fa-caret-square-o-down" aria-hidden="true"></i>caret-square-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-left"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>caret-square-o-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-right"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i>caret-square-o-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-up"><i class="fa fa-caret-square-o-up" aria-hidden="true"></i>caret-square-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cart-arrow-down"><i class="fa fa-cart-arrow-down" aria-hidden="true"></i>cart-arrow-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cart-plus"><i class="fa fa-cart-plus" aria-hidden="true"></i>cart-plus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc"><i class="fa fa-cc" aria-hidden="true"></i>cc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/certificate"><i class="fa fa-certificate" aria-hidden="true"></i>certificate</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check"><i class="fa fa-check" aria-hidden="true"></i>check</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check-circle"><i class="fa fa-check-circle" aria-hidden="true"></i>check-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check-circle-o"><i class="fa fa-check-circle-o" aria-hidden="true"></i>check-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check-square"><i class="fa fa-check-square" aria-hidden="true"></i>check-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check-square-o"><i class="fa fa-check-square-o" aria-hidden="true"></i>check-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/child"><i class="fa fa-child" aria-hidden="true"></i>child</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle"><i class="fa fa-circle" aria-hidden="true"></i>circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle-o"><i class="fa fa-circle-o" aria-hidden="true"></i>circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle-o-notch"><i class="fa fa-circle-o-notch" aria-hidden="true"></i>circle-o-notch</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle-thin"><i class="fa fa-circle-thin" aria-hidden="true"></i>circle-thin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/clock-o"><i class="fa fa-clock-o" aria-hidden="true"></i>clock-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/clone"><i class="fa fa-clone" aria-hidden="true"></i>clone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/times"><i class="fa fa-close" aria-hidden="true"></i>close</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cloud"><i class="fa fa-cloud" aria-hidden="true"></i>cloud</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cloud-download"><i class="fa fa-cloud-download" aria-hidden="true"></i>cloud-download</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cloud-upload"><i class="fa fa-cloud-upload" aria-hidden="true"></i>cloud-upload</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/code"><i class="fa fa-code" aria-hidden="true"></i>code</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/code-fork"><i class="fa fa-code-fork" aria-hidden="true"></i>code-fork</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/coffee"><i class="fa fa-coffee" aria-hidden="true"></i>coffee</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cog"><i class="fa fa-cog" aria-hidden="true"></i>cog</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cogs"><i class="fa fa-cogs" aria-hidden="true"></i>cogs</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/comment"><i class="fa fa-comment" aria-hidden="true"></i>comment</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/comment-o"><i class="fa fa-comment-o" aria-hidden="true"></i>comment-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/commenting"><i class="fa fa-commenting" aria-hidden="true"></i>commenting</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/commenting-o"><i class="fa fa-commenting-o" aria-hidden="true"></i>commenting-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/comments"><i class="fa fa-comments" aria-hidden="true"></i>comments</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/comments-o"><i class="fa fa-comments-o" aria-hidden="true"></i>comments-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/compass"><i class="fa fa-compass" aria-hidden="true"></i>compass</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/copyright"><i class="fa fa-copyright" aria-hidden="true"></i>copyright</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/creative-commons"><i class="fa fa-creative-commons" aria-hidden="true"></i>creative-commons</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/credit-card"><i class="fa fa-credit-card" aria-hidden="true"></i>credit-card</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/credit-card-alt"><i class="fa fa-credit-card-alt" aria-hidden="true"></i>credit-card-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/crop"><i class="fa fa-crop" aria-hidden="true"></i>crop</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/crosshairs"><i class="fa fa-crosshairs" aria-hidden="true"></i>crosshairs</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cube"><i class="fa fa-cube" aria-hidden="true"></i>cube</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cubes"><i class="fa fa-cubes" aria-hidden="true"></i>cubes</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cutlery"><i class="fa fa-cutlery" aria-hidden="true"></i>cutlery</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tachometer"><i class="fa fa-dashboard" aria-hidden="true"></i>dashboard</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/database"><i class="fa fa-database" aria-hidden="true"></i>database</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-deaf" aria-hidden="true"></i>deaf</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-deafness" aria-hidden="true"></i>deafness</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/desktop"><i class="fa fa-desktop" aria-hidden="true"></i>desktop</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/diamond"><i class="fa fa-diamond" aria-hidden="true"></i>diamond</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/dot-circle-o"><i class="fa fa-dot-circle-o" aria-hidden="true"></i>dot-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/download"><i class="fa fa-download" aria-hidden="true"></i>download</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pencil-square-o"><i class="fa fa-edit" aria-hidden="true"></i>edit</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ellipsis-h"><i class="fa fa-ellipsis-h" aria-hidden="true"></i>ellipsis-h</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ellipsis-v"><i class="fa fa-ellipsis-v" aria-hidden="true"></i>ellipsis-v</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/envelope"><i class="fa fa-envelope" aria-hidden="true"></i>envelope</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/envelope-o"><i class="fa fa-envelope-o" aria-hidden="true"></i>envelope-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/envelope-square"><i class="fa fa-envelope-square" aria-hidden="true"></i>envelope-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eraser"><i class="fa fa-eraser" aria-hidden="true"></i>eraser</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/exchange"><i class="fa fa-exchange" aria-hidden="true"></i>exchange</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/exclamation"><i class="fa fa-exclamation" aria-hidden="true"></i>exclamation</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/exclamation-circle"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>exclamation-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/exclamation-triangle"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>exclamation-triangle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/external-link"><i class="fa fa-external-link" aria-hidden="true"></i>external-link</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/external-link-square"><i class="fa fa-external-link-square" aria-hidden="true"></i>external-link-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eye"><i class="fa fa-eye" aria-hidden="true"></i>eye</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eye-slash"><i class="fa fa-eye-slash" aria-hidden="true"></i>eye-slash</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eyedropper"><i class="fa fa-eyedropper" aria-hidden="true"></i>eyedropper</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fax"><i class="fa fa-fax" aria-hidden="true"></i>fax</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rss"><i class="fa fa-feed" aria-hidden="true"></i>feed</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/female"><i class="fa fa-female" aria-hidden="true"></i>female</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fighter-jet"><i class="fa fa-fighter-jet" aria-hidden="true"></i>fighter-jet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-archive-o"><i class="fa fa-file-archive-o" aria-hidden="true"></i>file-archive-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-audio-o"><i class="fa fa-file-audio-o" aria-hidden="true"></i>file-audio-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-code-o"><i class="fa fa-file-code-o" aria-hidden="true"></i>file-code-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-excel-o"><i class="fa fa-file-excel-o" aria-hidden="true"></i>file-excel-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-image-o"><i class="fa fa-file-image-o" aria-hidden="true"></i>file-image-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-video-o"><i class="fa fa-file-movie-o" aria-hidden="true"></i>file-movie-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-pdf-o"><i class="fa fa-file-pdf-o" aria-hidden="true"></i>file-pdf-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-image-o"><i class="fa fa-file-photo-o" aria-hidden="true"></i>file-photo-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-image-o"><i class="fa fa-file-picture-o" aria-hidden="true"></i>file-picture-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-powerpoint-o"><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i>file-powerpoint-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-audio-o"><i class="fa fa-file-sound-o" aria-hidden="true"></i>file-sound-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-video-o"><i class="fa fa-file-video-o" aria-hidden="true"></i>file-video-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-word-o"><i class="fa fa-file-word-o" aria-hidden="true"></i>file-word-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-archive-o"><i class="fa fa-file-zip-o" aria-hidden="true"></i>file-zip-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/film"><i class="fa fa-film" aria-hidden="true"></i>film</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/filter"><i class="fa fa-filter" aria-hidden="true"></i>filter</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fire"><i class="fa fa-fire" aria-hidden="true"></i>fire</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fire-extinguisher"><i class="fa fa-fire-extinguisher" aria-hidden="true"></i>fire-extinguisher</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/flag"><i class="fa fa-flag" aria-hidden="true"></i>flag</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/flag-checkered"><i class="fa fa-flag-checkered" aria-hidden="true"></i>flag-checkered</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/flag-o"><i class="fa fa-flag-o" aria-hidden="true"></i>flag-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bolt"><i class="fa fa-flash" aria-hidden="true"></i>flash</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/flask"><i class="fa fa-flask" aria-hidden="true"></i>flask</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/folder"><i class="fa fa-folder" aria-hidden="true"></i>folder</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/folder-o"><i class="fa fa-folder-o" aria-hidden="true"></i>folder-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/folder-open"><i class="fa fa-folder-open" aria-hidden="true"></i>folder-open</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/folder-open-o"><i class="fa fa-folder-open-o" aria-hidden="true"></i>folder-open-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/frown-o"><i class="fa fa-frown-o" aria-hidden="true"></i>frown-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/futbol-o"><i class="fa fa-futbol-o" aria-hidden="true"></i>futbol-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gamepad"><i class="fa fa-gamepad" aria-hidden="true"></i>gamepad</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gavel"><i class="fa fa-gavel" aria-hidden="true"></i>gavel</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cog"><i class="fa fa-gear" aria-hidden="true"></i>gear</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cogs"><i class="fa fa-gears" aria-hidden="true"></i>gears</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gift"><i class="fa fa-gift" aria-hidden="true"></i>gift</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/glass"><i class="fa fa-glass" aria-hidden="true"></i>glass</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/globe"><i class="fa fa-globe" aria-hidden="true"></i>globe</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/graduation-cap"><i class="fa fa-graduation-cap" aria-hidden="true"></i>graduation-cap</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/users"><i class="fa fa-group" aria-hidden="true"></i>group</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-rock-o"><i class="fa fa-hand-grab-o" aria-hidden="true"></i>hand-grab-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-lizard-o"><i class="fa fa-hand-lizard-o" aria-hidden="true"></i>hand-lizard-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-paper-o"><i class="fa fa-hand-paper-o" aria-hidden="true"></i>hand-paper-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-peace-o"><i class="fa fa-hand-peace-o" aria-hidden="true"></i>hand-peace-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-pointer-o"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i>hand-pointer-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-rock-o"><i class="fa fa-hand-rock-o" aria-hidden="true"></i>hand-rock-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-scissors-o"><i class="fa fa-hand-scissors-o" aria-hidden="true"></i>hand-scissors-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-spock-o"><i class="fa fa-hand-spock-o" aria-hidden="true"></i>hand-spock-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-paper-o"><i class="fa fa-hand-stop-o" aria-hidden="true"></i>hand-stop-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-hard-of-hearing" aria-hidden="true"></i>hard-of-hearing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hashtag"><i class="fa fa-hashtag" aria-hidden="true"></i>hashtag</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hdd-o"><i class="fa fa-hdd-o" aria-hidden="true"></i>hdd-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/headphones"><i class="fa fa-headphones" aria-hidden="true"></i>headphones</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/heart"><i class="fa fa-heart" aria-hidden="true"></i>heart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/heart-o"><i class="fa fa-heart-o" aria-hidden="true"></i>heart-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/heartbeat"><i class="fa fa-heartbeat" aria-hidden="true"></i>heartbeat</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/history"><i class="fa fa-history" aria-hidden="true"></i>history</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/home"><i class="fa fa-home" aria-hidden="true"></i>home</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bed"><i class="fa fa-hotel" aria-hidden="true"></i>hotel</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass"><i class="fa fa-hourglass" aria-hidden="true"></i>hourglass</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-start"><i class="fa fa-hourglass-1" aria-hidden="true"></i>hourglass-1</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-half"><i class="fa fa-hourglass-2" aria-hidden="true"></i>hourglass-2</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-end"><i class="fa fa-hourglass-3" aria-hidden="true"></i>hourglass-3</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-end"><i class="fa fa-hourglass-end" aria-hidden="true"></i>hourglass-end</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-half"><i class="fa fa-hourglass-half" aria-hidden="true"></i>hourglass-half</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-o"><i class="fa fa-hourglass-o" aria-hidden="true"></i>hourglass-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hourglass-start"><i class="fa fa-hourglass-start" aria-hidden="true"></i>hourglass-start</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/i-cursor"><i class="fa fa-i-cursor" aria-hidden="true"></i>i-cursor</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/picture-o"><i class="fa fa-image" aria-hidden="true"></i>image</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/inbox"><i class="fa fa-inbox" aria-hidden="true"></i>inbox</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/industry"><i class="fa fa-industry" aria-hidden="true"></i>industry</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/info"><i class="fa fa-info" aria-hidden="true"></i>info</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/info-circle"><i class="fa fa-info-circle" aria-hidden="true"></i>info-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/university"><i class="fa fa-institution" aria-hidden="true"></i>institution</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/key"><i class="fa fa-key" aria-hidden="true"></i>key</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/keyboard-o"><i class="fa fa-keyboard-o" aria-hidden="true"></i>keyboard-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/language"><i class="fa fa-language" aria-hidden="true"></i>language</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/laptop"><i class="fa fa-laptop" aria-hidden="true"></i>laptop</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/leaf"><i class="fa fa-leaf" aria-hidden="true"></i>leaf</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gavel"><i class="fa fa-legal" aria-hidden="true"></i>legal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/lemon-o"><i class="fa fa-lemon-o" aria-hidden="true"></i>lemon-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/level-down"><i class="fa fa-level-down" aria-hidden="true"></i>level-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/level-up"><i class="fa fa-level-up" aria-hidden="true"></i>level-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/life-ring"><i class="fa fa-life-bouy" aria-hidden="true"></i>life-bouy</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/life-ring"><i class="fa fa-life-buoy" aria-hidden="true"></i>life-buoy</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/life-ring"><i class="fa fa-life-ring" aria-hidden="true"></i>life-ring</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/life-ring"><i class="fa fa-life-saver" aria-hidden="true"></i>life-saver</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/lightbulb-o"><i class="fa fa-lightbulb-o" aria-hidden="true"></i>lightbulb-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/line-chart"><i class="fa fa-line-chart" aria-hidden="true"></i>line-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/location-arrow"><i class="fa fa-location-arrow" aria-hidden="true"></i>location-arrow</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/lock"><i class="fa fa-lock" aria-hidden="true"></i>lock</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/low-vision"><i class="fa fa-low-vision" aria-hidden="true"></i>low-vision</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/magic"><i class="fa fa-magic" aria-hidden="true"></i>magic</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/magnet"><i class="fa fa-magnet" aria-hidden="true"></i>magnet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share"><i class="fa fa-mail-forward" aria-hidden="true"></i>mail-forward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reply"><i class="fa fa-mail-reply" aria-hidden="true"></i>mail-reply</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reply-all"><i class="fa fa-mail-reply-all" aria-hidden="true"></i>mail-reply-all</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/male"><i class="fa fa-male" aria-hidden="true"></i>male</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/map"><i class="fa fa-map" aria-hidden="true"></i>map</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/map-marker"><i class="fa fa-map-marker" aria-hidden="true"></i>map-marker</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/map-o"><i class="fa fa-map-o" aria-hidden="true"></i>map-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/map-pin"><i class="fa fa-map-pin" aria-hidden="true"></i>map-pin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/map-signs"><i class="fa fa-map-signs" aria-hidden="true"></i>map-signs</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/meh-o"><i class="fa fa-meh-o" aria-hidden="true"></i>meh-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/microphone"><i class="fa fa-microphone" aria-hidden="true"></i>microphone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/microphone-slash"><i class="fa fa-microphone-slash" aria-hidden="true"></i>microphone-slash</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/minus"><i class="fa fa-minus" aria-hidden="true"></i>minus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/minus-circle"><i class="fa fa-minus-circle" aria-hidden="true"></i>minus-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/minus-square"><i class="fa fa-minus-square" aria-hidden="true"></i>minus-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/minus-square-o"><i class="fa fa-minus-square-o" aria-hidden="true"></i>minus-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mobile"><i class="fa fa-mobile" aria-hidden="true"></i>mobile</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mobile"><i class="fa fa-mobile-phone" aria-hidden="true"></i>mobile-phone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/money"><i class="fa fa-money" aria-hidden="true"></i>money</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/moon-o"><i class="fa fa-moon-o" aria-hidden="true"></i>moon-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/graduation-cap"><i class="fa fa-mortar-board" aria-hidden="true"></i>mortar-board</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/motorcycle"><i class="fa fa-motorcycle" aria-hidden="true"></i>motorcycle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mouse-pointer"><i class="fa fa-mouse-pointer" aria-hidden="true"></i>mouse-pointer</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/music"><i class="fa fa-music" aria-hidden="true"></i>music</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bars"><i class="fa fa-navicon" aria-hidden="true"></i>navicon</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/newspaper-o"><i class="fa fa-newspaper-o" aria-hidden="true"></i>newspaper-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/object-group"><i class="fa fa-object-group" aria-hidden="true"></i>object-group</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/object-ungroup"><i class="fa fa-object-ungroup" aria-hidden="true"></i>object-ungroup</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paint-brush"><i class="fa fa-paint-brush" aria-hidden="true"></i>paint-brush</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paper-plane"><i class="fa fa-paper-plane" aria-hidden="true"></i>paper-plane</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paper-plane-o"><i class="fa fa-paper-plane-o" aria-hidden="true"></i>paper-plane-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paw"><i class="fa fa-paw" aria-hidden="true"></i>paw</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pencil"><i class="fa fa-pencil" aria-hidden="true"></i>pencil</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pencil-square"><i class="fa fa-pencil-square" aria-hidden="true"></i>pencil-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pencil-square-o"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>pencil-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/percent"><i class="fa fa-percent" aria-hidden="true"></i>percent</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/phone"><i class="fa fa-phone" aria-hidden="true"></i>phone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/phone-square"><i class="fa fa-phone-square" aria-hidden="true"></i>phone-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/picture-o"><i class="fa fa-photo" aria-hidden="true"></i>photo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/picture-o"><i class="fa fa-picture-o" aria-hidden="true"></i>picture-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pie-chart"><i class="fa fa-pie-chart" aria-hidden="true"></i>pie-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plane"><i class="fa fa-plane" aria-hidden="true"></i>plane</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plug"><i class="fa fa-plug" aria-hidden="true"></i>plug</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus"><i class="fa fa-plus" aria-hidden="true"></i>plus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus-circle"><i class="fa fa-plus-circle" aria-hidden="true"></i>plus-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus-square"><i class="fa fa-plus-square" aria-hidden="true"></i>plus-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus-square-o"><i class="fa fa-plus-square-o" aria-hidden="true"></i>plus-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/power-off"><i class="fa fa-power-off" aria-hidden="true"></i>power-off</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/print"><i class="fa fa-print" aria-hidden="true"></i>print</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/puzzle-piece"><i class="fa fa-puzzle-piece" aria-hidden="true"></i>puzzle-piece</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/qrcode"><i class="fa fa-qrcode" aria-hidden="true"></i>qrcode</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/question"><i class="fa fa-question" aria-hidden="true"></i>question</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/question-circle"><i class="fa fa-question-circle" aria-hidden="true"></i>question-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/question-circle-o"><i class="fa fa-question-circle-o" aria-hidden="true"></i>question-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/quote-left"><i class="fa fa-quote-left" aria-hidden="true"></i>quote-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/quote-right"><i class="fa fa-quote-right" aria-hidden="true"></i>quote-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/random"><i class="fa fa-random" aria-hidden="true"></i>random</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/recycle"><i class="fa fa-recycle" aria-hidden="true"></i>recycle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/refresh"><i class="fa fa-refresh" aria-hidden="true"></i>refresh</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/registered"><i class="fa fa-registered" aria-hidden="true"></i>registered</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/times"><i class="fa fa-remove" aria-hidden="true"></i>remove</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bars"><i class="fa fa-reorder" aria-hidden="true"></i>reorder</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reply"><i class="fa fa-reply" aria-hidden="true"></i>reply</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reply-all"><i class="fa fa-reply-all" aria-hidden="true"></i>reply-all</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/retweet"><i class="fa fa-retweet" aria-hidden="true"></i>retweet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/road"><i class="fa fa-road" aria-hidden="true"></i>road</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rocket"><i class="fa fa-rocket" aria-hidden="true"></i>rocket</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rss"><i class="fa fa-rss" aria-hidden="true"></i>rss</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rss-square"><i class="fa fa-rss-square" aria-hidden="true"></i>rss-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/search"><i class="fa fa-search" aria-hidden="true"></i>search</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/search-minus"><i class="fa fa-search-minus" aria-hidden="true"></i>search-minus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/search-plus"><i class="fa fa-search-plus" aria-hidden="true"></i>search-plus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paper-plane"><i class="fa fa-send" aria-hidden="true"></i>send</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paper-plane-o"><i class="fa fa-send-o" aria-hidden="true"></i>send-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/server"><i class="fa fa-server" aria-hidden="true"></i>server</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share"><i class="fa fa-share" aria-hidden="true"></i>share</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share-alt"><i class="fa fa-share-alt" aria-hidden="true"></i>share-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share-alt-square"><i class="fa fa-share-alt-square" aria-hidden="true"></i>share-alt-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share-square"><i class="fa fa-share-square" aria-hidden="true"></i>share-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share-square-o"><i class="fa fa-share-square-o" aria-hidden="true"></i>share-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/shield"><i class="fa fa-shield" aria-hidden="true"></i>shield</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ship"><i class="fa fa-ship" aria-hidden="true"></i>ship</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/shopping-bag"><i class="fa fa-shopping-bag" aria-hidden="true"></i>shopping-bag</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/shopping-basket"><i class="fa fa-shopping-basket" aria-hidden="true"></i>shopping-basket</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/shopping-cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>shopping-cart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-in"><i class="fa fa-sign-in" aria-hidden="true"></i>sign-in</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-language"><i class="fa fa-sign-language" aria-hidden="true"></i>sign-language</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-out"><i class="fa fa-sign-out" aria-hidden="true"></i>sign-out</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/signal"><i class="fa fa-signal" aria-hidden="true"></i>signal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-language"><i class="fa fa-signing" aria-hidden="true"></i>signing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sitemap"><i class="fa fa-sitemap" aria-hidden="true"></i>sitemap</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sliders"><i class="fa fa-sliders" aria-hidden="true"></i>sliders</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/smile-o"><i class="fa fa-smile-o" aria-hidden="true"></i>smile-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/futbol-o"><i class="fa fa-soccer-ball-o" aria-hidden="true"></i>soccer-ball-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort"><i class="fa fa-sort" aria-hidden="true"></i>sort</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-alpha-asc"><i class="fa fa-sort-alpha-asc" aria-hidden="true"></i>sort-alpha-asc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-alpha-desc"><i class="fa fa-sort-alpha-desc" aria-hidden="true"></i>sort-alpha-desc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-amount-asc"><i class="fa fa-sort-amount-asc" aria-hidden="true"></i>sort-amount-asc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-amount-desc"><i class="fa fa-sort-amount-desc" aria-hidden="true"></i>sort-amount-desc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-asc"><i class="fa fa-sort-asc" aria-hidden="true"></i>sort-asc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-desc"><i class="fa fa-sort-desc" aria-hidden="true"></i>sort-desc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-desc"><i class="fa fa-sort-down" aria-hidden="true"></i>sort-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-numeric-asc"><i class="fa fa-sort-numeric-asc" aria-hidden="true"></i>sort-numeric-asc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-numeric-desc"><i class="fa fa-sort-numeric-desc" aria-hidden="true"></i>sort-numeric-desc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort-asc"><i class="fa fa-sort-up" aria-hidden="true"></i>sort-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/space-shuttle"><i class="fa fa-space-shuttle" aria-hidden="true"></i>space-shuttle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/spinner"><i class="fa fa-spinner" aria-hidden="true"></i>spinner</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/spoon"><i class="fa fa-spoon" aria-hidden="true"></i>spoon</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/square"><i class="fa fa-square" aria-hidden="true"></i>square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/square-o"><i class="fa fa-square-o" aria-hidden="true"></i>square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/star"><i class="fa fa-star" aria-hidden="true"></i>star</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/star-half"><i class="fa fa-star-half" aria-hidden="true"></i>star-half</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/star-half-o"><i class="fa fa-star-half-empty" aria-hidden="true"></i>star-half-empty</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/star-half-o"><i class="fa fa-star-half-full" aria-hidden="true"></i>star-half-full</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/star-half-o"><i class="fa fa-star-half-o" aria-hidden="true"></i>star-half-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/star-o"><i class="fa fa-star-o" aria-hidden="true"></i>star-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sticky-note"><i class="fa fa-sticky-note" aria-hidden="true"></i>sticky-note</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sticky-note-o"><i class="fa fa-sticky-note-o" aria-hidden="true"></i>sticky-note-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/street-view"><i class="fa fa-street-view" aria-hidden="true"></i>street-view</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/suitcase"><i class="fa fa-suitcase" aria-hidden="true"></i>suitcase</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sun-o"><i class="fa fa-sun-o" aria-hidden="true"></i>sun-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/life-ring"><i class="fa fa-support" aria-hidden="true"></i>support</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tablet"><i class="fa fa-tablet" aria-hidden="true"></i>tablet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tachometer"><i class="fa fa-tachometer" aria-hidden="true"></i>tachometer</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tag"><i class="fa fa-tag" aria-hidden="true"></i>tag</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tags"><i class="fa fa-tags" aria-hidden="true"></i>tags</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tasks"><i class="fa fa-tasks" aria-hidden="true"></i>tasks</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/taxi"><i class="fa fa-taxi" aria-hidden="true"></i>taxi</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/television"><i class="fa fa-television" aria-hidden="true"></i>television</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/terminal"><i class="fa fa-terminal" aria-hidden="true"></i>terminal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumb-tack"><i class="fa fa-thumb-tack" aria-hidden="true"></i>thumb-tack</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-down"><i class="fa fa-thumbs-down" aria-hidden="true"></i>thumbs-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-o-down"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>thumbs-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-o-up"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>thumbs-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-up"><i class="fa fa-thumbs-up" aria-hidden="true"></i>thumbs-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ticket"><i class="fa fa-ticket" aria-hidden="true"></i>ticket</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/times"><i class="fa fa-times" aria-hidden="true"></i>times</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/times-circle"><i class="fa fa-times-circle" aria-hidden="true"></i>times-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/times-circle-o"><i class="fa fa-times-circle-o" aria-hidden="true"></i>times-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tint"><i class="fa fa-tint" aria-hidden="true"></i>tint</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-down"><i class="fa fa-toggle-down" aria-hidden="true"></i>toggle-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-left"><i class="fa fa-toggle-left" aria-hidden="true"></i>toggle-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/toggle-off"><i class="fa fa-toggle-off" aria-hidden="true"></i>toggle-off</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/toggle-on"><i class="fa fa-toggle-on" aria-hidden="true"></i>toggle-on</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-right"><i class="fa fa-toggle-right" aria-hidden="true"></i>toggle-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-up"><i class="fa fa-toggle-up" aria-hidden="true"></i>toggle-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/trademark"><i class="fa fa-trademark" aria-hidden="true"></i>trademark</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/trash"><i class="fa fa-trash" aria-hidden="true"></i>trash</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/trash-o"><i class="fa fa-trash-o" aria-hidden="true"></i>trash-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tree"><i class="fa fa-tree" aria-hidden="true"></i>tree</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/trophy"><i class="fa fa-trophy" aria-hidden="true"></i>trophy</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/truck"><i class="fa fa-truck" aria-hidden="true"></i>truck</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tty"><i class="fa fa-tty" aria-hidden="true"></i>tty</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/television"><i class="fa fa-tv" aria-hidden="true"></i>tv</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/umbrella"><i class="fa fa-umbrella" aria-hidden="true"></i>umbrella</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/universal-access"><i class="fa fa-universal-access" aria-hidden="true"></i>universal-access</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/university"><i class="fa fa-university" aria-hidden="true"></i>university</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/unlock"><i class="fa fa-unlock" aria-hidden="true"></i>unlock</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/unlock-alt"><i class="fa fa-unlock-alt" aria-hidden="true"></i>unlock-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sort"><i class="fa fa-unsorted" aria-hidden="true"></i>unsorted</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/upload"><i class="fa fa-upload" aria-hidden="true"></i>upload</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/user"><i class="fa fa-user" aria-hidden="true"></i>user</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/user-plus"><i class="fa fa-user-plus" aria-hidden="true"></i>user-plus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/user-secret"><i class="fa fa-user-secret" aria-hidden="true"></i>user-secret</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/user-times"><i class="fa fa-user-times" aria-hidden="true"></i>user-times</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/users"><i class="fa fa-users" aria-hidden="true"></i>users</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/video-camera"><i class="fa fa-video-camera" aria-hidden="true"></i>video-camera</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/volume-control-phone"><i class="fa fa-volume-control-phone" aria-hidden="true"></i>volume-control-phone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/volume-down"><i class="fa fa-volume-down" aria-hidden="true"></i>volume-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/volume-off"><i class="fa fa-volume-off" aria-hidden="true"></i>volume-off</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/volume-up"><i class="fa fa-volume-up" aria-hidden="true"></i>volume-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/exclamation-triangle"><i class="fa fa-warning" aria-hidden="true"></i>warning</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair"><i class="fa fa-wheelchair" aria-hidden="true"></i>wheelchair</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair-alt"><i class="fa fa-wheelchair-alt" aria-hidden="true"></i>wheelchair-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wifi"><i class="fa fa-wifi" aria-hidden="true"></i>wifi</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wrench"><i class="fa fa-wrench" aria-hidden="true"></i>wrench</a></div>
    
  </div>

</section>

    <section id="accessibility">
  <h2 class="page-header">Accessibility Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/american-sign-language-interpreting"><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i>american-sign-language-interpreting</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/american-sign-language-interpreting"><i class="fa fa-asl-interpreting" aria-hidden="true"></i>asl-interpreting</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/assistive-listening-systems"><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i>assistive-listening-systems</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/audio-description"><i class="fa fa-audio-description" aria-hidden="true"></i>audio-description</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/blind"><i class="fa fa-blind" aria-hidden="true"></i>blind</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/braille"><i class="fa fa-braille" aria-hidden="true"></i>braille</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc"><i class="fa fa-cc" aria-hidden="true"></i>cc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-deaf" aria-hidden="true"></i>deaf</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-deafness" aria-hidden="true"></i>deafness</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deaf"><i class="fa fa-hard-of-hearing" aria-hidden="true"></i>hard-of-hearing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/low-vision"><i class="fa fa-low-vision" aria-hidden="true"></i>low-vision</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/question-circle-o"><i class="fa fa-question-circle-o" aria-hidden="true"></i>question-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-language"><i class="fa fa-sign-language" aria-hidden="true"></i>sign-language</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sign-language"><i class="fa fa-signing" aria-hidden="true"></i>signing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tty"><i class="fa fa-tty" aria-hidden="true"></i>tty</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/universal-access"><i class="fa fa-universal-access" aria-hidden="true"></i>universal-access</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/volume-control-phone"><i class="fa fa-volume-control-phone" aria-hidden="true"></i>volume-control-phone</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair"><i class="fa fa-wheelchair" aria-hidden="true"></i>wheelchair</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair-alt"><i class="fa fa-wheelchair-alt" aria-hidden="true"></i>wheelchair-alt</a></div>
    
  </div>

</section>

    <section id="hand">
  <h2 class="page-header">Hand Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-rock-o"><i class="fa fa-hand-grab-o" aria-hidden="true"></i>hand-grab-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-lizard-o"><i class="fa fa-hand-lizard-o" aria-hidden="true"></i>hand-lizard-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-down"><i class="fa fa-hand-o-down" aria-hidden="true"></i>hand-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-left"><i class="fa fa-hand-o-left" aria-hidden="true"></i>hand-o-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-right"><i class="fa fa-hand-o-right" aria-hidden="true"></i>hand-o-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-up"><i class="fa fa-hand-o-up" aria-hidden="true"></i>hand-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-paper-o"><i class="fa fa-hand-paper-o" aria-hidden="true"></i>hand-paper-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-peace-o"><i class="fa fa-hand-peace-o" aria-hidden="true"></i>hand-peace-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-pointer-o"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i>hand-pointer-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-rock-o"><i class="fa fa-hand-rock-o" aria-hidden="true"></i>hand-rock-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-scissors-o"><i class="fa fa-hand-scissors-o" aria-hidden="true"></i>hand-scissors-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-spock-o"><i class="fa fa-hand-spock-o" aria-hidden="true"></i>hand-spock-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-paper-o"><i class="fa fa-hand-stop-o" aria-hidden="true"></i>hand-stop-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-down"><i class="fa fa-thumbs-down" aria-hidden="true"></i>thumbs-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-o-down"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>thumbs-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-o-up"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>thumbs-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/thumbs-up"><i class="fa fa-thumbs-up" aria-hidden="true"></i>thumbs-up</a></div>
    
  </div>

</section>

    <section id="transportation">
  <h2 class="page-header">Transportation Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ambulance"><i class="fa fa-ambulance" aria-hidden="true"></i>ambulance</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/car"><i class="fa fa-automobile" aria-hidden="true"></i>automobile</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bicycle"><i class="fa fa-bicycle" aria-hidden="true"></i>bicycle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bus"><i class="fa fa-bus" aria-hidden="true"></i>bus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/taxi"><i class="fa fa-cab" aria-hidden="true"></i>cab</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/car"><i class="fa fa-car" aria-hidden="true"></i>car</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fighter-jet"><i class="fa fa-fighter-jet" aria-hidden="true"></i>fighter-jet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/motorcycle"><i class="fa fa-motorcycle" aria-hidden="true"></i>motorcycle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plane"><i class="fa fa-plane" aria-hidden="true"></i>plane</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rocket"><i class="fa fa-rocket" aria-hidden="true"></i>rocket</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ship"><i class="fa fa-ship" aria-hidden="true"></i>ship</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/space-shuttle"><i class="fa fa-space-shuttle" aria-hidden="true"></i>space-shuttle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/subway"><i class="fa fa-subway" aria-hidden="true"></i>subway</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/taxi"><i class="fa fa-taxi" aria-hidden="true"></i>taxi</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/train"><i class="fa fa-train" aria-hidden="true"></i>train</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/truck"><i class="fa fa-truck" aria-hidden="true"></i>truck</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair"><i class="fa fa-wheelchair" aria-hidden="true"></i>wheelchair</a></div>
    
  </div>

</section>

    <section id="gender">
  <h2 class="page-header">Gender Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/genderless"><i class="fa fa-genderless" aria-hidden="true"></i>genderless</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/transgender"><i class="fa fa-intersex" aria-hidden="true"></i>intersex</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mars"><i class="fa fa-mars" aria-hidden="true"></i>mars</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mars-double"><i class="fa fa-mars-double" aria-hidden="true"></i>mars-double</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mars-stroke"><i class="fa fa-mars-stroke" aria-hidden="true"></i>mars-stroke</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mars-stroke-h"><i class="fa fa-mars-stroke-h" aria-hidden="true"></i>mars-stroke-h</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mars-stroke-v"><i class="fa fa-mars-stroke-v" aria-hidden="true"></i>mars-stroke-v</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mercury"><i class="fa fa-mercury" aria-hidden="true"></i>mercury</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/neuter"><i class="fa fa-neuter" aria-hidden="true"></i>neuter</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/transgender"><i class="fa fa-transgender" aria-hidden="true"></i>transgender</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/transgender-alt"><i class="fa fa-transgender-alt" aria-hidden="true"></i>transgender-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/venus"><i class="fa fa-venus" aria-hidden="true"></i>venus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/venus-double"><i class="fa fa-venus-double" aria-hidden="true"></i>venus-double</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/venus-mars"><i class="fa fa-venus-mars" aria-hidden="true"></i>venus-mars</a></div>
    
  </div>

</section>

    <section id="file-type">
  <h2 class="page-header">File Type Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file"><i class="fa fa-file" aria-hidden="true"></i>file</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-archive-o"><i class="fa fa-file-archive-o" aria-hidden="true"></i>file-archive-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-audio-o"><i class="fa fa-file-audio-o" aria-hidden="true"></i>file-audio-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-code-o"><i class="fa fa-file-code-o" aria-hidden="true"></i>file-code-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-excel-o"><i class="fa fa-file-excel-o" aria-hidden="true"></i>file-excel-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-image-o"><i class="fa fa-file-image-o" aria-hidden="true"></i>file-image-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-video-o"><i class="fa fa-file-movie-o" aria-hidden="true"></i>file-movie-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-o"><i class="fa fa-file-o" aria-hidden="true"></i>file-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-pdf-o"><i class="fa fa-file-pdf-o" aria-hidden="true"></i>file-pdf-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-image-o"><i class="fa fa-file-photo-o" aria-hidden="true"></i>file-photo-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-image-o"><i class="fa fa-file-picture-o" aria-hidden="true"></i>file-picture-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-powerpoint-o"><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i>file-powerpoint-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-audio-o"><i class="fa fa-file-sound-o" aria-hidden="true"></i>file-sound-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-text"><i class="fa fa-file-text" aria-hidden="true"></i>file-text</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-text-o"><i class="fa fa-file-text-o" aria-hidden="true"></i>file-text-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-video-o"><i class="fa fa-file-video-o" aria-hidden="true"></i>file-video-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-word-o"><i class="fa fa-file-word-o" aria-hidden="true"></i>file-word-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-archive-o"><i class="fa fa-file-zip-o" aria-hidden="true"></i>file-zip-o</a></div>
    
  </div>

</section>

    <section id="spinner">
  <h2 class="page-header">Spinner Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle-o-notch"><i class="fa fa-circle-o-notch" aria-hidden="true"></i>circle-o-notch</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cog"><i class="fa fa-cog" aria-hidden="true"></i>cog</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cog"><i class="fa fa-gear" aria-hidden="true"></i>gear</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/refresh"><i class="fa fa-refresh" aria-hidden="true"></i>refresh</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/spinner"><i class="fa fa-spinner" aria-hidden="true"></i>spinner</a></div>
    
  </div>
</section>

    <section id="form-control">
  <h2 class="page-header">Form Control Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check-square"><i class="fa fa-check-square" aria-hidden="true"></i>check-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/check-square-o"><i class="fa fa-check-square-o" aria-hidden="true"></i>check-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle"><i class="fa fa-circle" aria-hidden="true"></i>circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/circle-o"><i class="fa fa-circle-o" aria-hidden="true"></i>circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/dot-circle-o"><i class="fa fa-dot-circle-o" aria-hidden="true"></i>dot-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/minus-square"><i class="fa fa-minus-square" aria-hidden="true"></i>minus-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/minus-square-o"><i class="fa fa-minus-square-o" aria-hidden="true"></i>minus-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus-square"><i class="fa fa-plus-square" aria-hidden="true"></i>plus-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus-square-o"><i class="fa fa-plus-square-o" aria-hidden="true"></i>plus-square-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/square"><i class="fa fa-square" aria-hidden="true"></i>square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/square-o"><i class="fa fa-square-o" aria-hidden="true"></i>square-o</a></div>
    
  </div>
</section>

    <section id="payment">
  <h2 class="page-header">Payment Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-amex"><i class="fa fa-cc-amex" aria-hidden="true"></i>cc-amex</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-diners-club"><i class="fa fa-cc-diners-club" aria-hidden="true"></i>cc-diners-club</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-discover"><i class="fa fa-cc-discover" aria-hidden="true"></i>cc-discover</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-jcb"><i class="fa fa-cc-jcb" aria-hidden="true"></i>cc-jcb</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-mastercard"><i class="fa fa-cc-mastercard" aria-hidden="true"></i>cc-mastercard</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-paypal"><i class="fa fa-cc-paypal" aria-hidden="true"></i>cc-paypal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-stripe"><i class="fa fa-cc-stripe" aria-hidden="true"></i>cc-stripe</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-visa"><i class="fa fa-cc-visa" aria-hidden="true"></i>cc-visa</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/credit-card"><i class="fa fa-credit-card" aria-hidden="true"></i>credit-card</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/credit-card-alt"><i class="fa fa-credit-card-alt" aria-hidden="true"></i>credit-card-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-wallet"><i class="fa fa-google-wallet" aria-hidden="true"></i>google-wallet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paypal"><i class="fa fa-paypal" aria-hidden="true"></i>paypal</a></div>
    
  </div>

</section>

    <section id="chart">
  <h2 class="page-header">Chart Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/area-chart"><i class="fa fa-area-chart" aria-hidden="true"></i>area-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bar-chart"><i class="fa fa-bar-chart" aria-hidden="true"></i>bar-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bar-chart"><i class="fa fa-bar-chart-o" aria-hidden="true"></i>bar-chart-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/line-chart"><i class="fa fa-line-chart" aria-hidden="true"></i>line-chart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pie-chart"><i class="fa fa-pie-chart" aria-hidden="true"></i>pie-chart</a></div>
    
  </div>

</section>

    <section id="currency">
  <h2 class="page-header">Currency Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/btc"><i class="fa fa-bitcoin" aria-hidden="true"></i>bitcoin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/btc"><i class="fa fa-btc" aria-hidden="true"></i>btc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/jpy"><i class="fa fa-cny" aria-hidden="true"></i>cny</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/usd"><i class="fa fa-dollar" aria-hidden="true"></i>dollar</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eur"><i class="fa fa-eur" aria-hidden="true"></i>eur</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eur"><i class="fa fa-euro" aria-hidden="true"></i>euro</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gbp"><i class="fa fa-gbp" aria-hidden="true"></i>gbp</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gg"><i class="fa fa-gg" aria-hidden="true"></i>gg</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gg-circle"><i class="fa fa-gg-circle" aria-hidden="true"></i>gg-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ils"><i class="fa fa-ils" aria-hidden="true"></i>ils</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/inr"><i class="fa fa-inr" aria-hidden="true"></i>inr</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/jpy"><i class="fa fa-jpy" aria-hidden="true"></i>jpy</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/krw"><i class="fa fa-krw" aria-hidden="true"></i>krw</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/money"><i class="fa fa-money" aria-hidden="true"></i>money</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/jpy"><i class="fa fa-rmb" aria-hidden="true"></i>rmb</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rub"><i class="fa fa-rouble" aria-hidden="true"></i>rouble</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rub"><i class="fa fa-rub" aria-hidden="true"></i>rub</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rub"><i class="fa fa-ruble" aria-hidden="true"></i>ruble</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/inr"><i class="fa fa-rupee" aria-hidden="true"></i>rupee</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ils"><i class="fa fa-shekel" aria-hidden="true"></i>shekel</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ils"><i class="fa fa-sheqel" aria-hidden="true"></i>sheqel</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/try"><i class="fa fa-try" aria-hidden="true"></i>try</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/try"><i class="fa fa-turkish-lira" aria-hidden="true"></i>turkish-lira</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/usd"><i class="fa fa-usd" aria-hidden="true"></i>usd</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/krw"><i class="fa fa-won" aria-hidden="true"></i>won</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/jpy"><i class="fa fa-yen" aria-hidden="true"></i>yen</a></div>
    
  </div>

</section>

    <section id="text-editor">
  <h2 class="page-header">Text Editor Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/align-center"><i class="fa fa-align-center" aria-hidden="true"></i>align-center</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/align-justify"><i class="fa fa-align-justify" aria-hidden="true"></i>align-justify</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/align-left"><i class="fa fa-align-left" aria-hidden="true"></i>align-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/align-right"><i class="fa fa-align-right" aria-hidden="true"></i>align-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bold"><i class="fa fa-bold" aria-hidden="true"></i>bold</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/link"><i class="fa fa-chain" aria-hidden="true"></i>chain</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chain-broken"><i class="fa fa-chain-broken" aria-hidden="true"></i>chain-broken</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/clipboard"><i class="fa fa-clipboard" aria-hidden="true"></i>clipboard</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/columns"><i class="fa fa-columns" aria-hidden="true"></i>columns</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/files-o"><i class="fa fa-copy" aria-hidden="true"></i>copy</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/scissors"><i class="fa fa-cut" aria-hidden="true"></i>cut</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/outdent"><i class="fa fa-dedent" aria-hidden="true"></i>dedent</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eraser"><i class="fa fa-eraser" aria-hidden="true"></i>eraser</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file"><i class="fa fa-file" aria-hidden="true"></i>file</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-o"><i class="fa fa-file-o" aria-hidden="true"></i>file-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-text"><i class="fa fa-file-text" aria-hidden="true"></i>file-text</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/file-text-o"><i class="fa fa-file-text-o" aria-hidden="true"></i>file-text-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/files-o"><i class="fa fa-files-o" aria-hidden="true"></i>files-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/floppy-o"><i class="fa fa-floppy-o" aria-hidden="true"></i>floppy-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/font"><i class="fa fa-font" aria-hidden="true"></i>font</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/header"><i class="fa fa-header" aria-hidden="true"></i>header</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/indent"><i class="fa fa-indent" aria-hidden="true"></i>indent</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/italic"><i class="fa fa-italic" aria-hidden="true"></i>italic</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/link"><i class="fa fa-link" aria-hidden="true"></i>link</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/list"><i class="fa fa-list" aria-hidden="true"></i>list</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/list-alt"><i class="fa fa-list-alt" aria-hidden="true"></i>list-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/list-ol"><i class="fa fa-list-ol" aria-hidden="true"></i>list-ol</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/list-ul"><i class="fa fa-list-ul" aria-hidden="true"></i>list-ul</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/outdent"><i class="fa fa-outdent" aria-hidden="true"></i>outdent</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paperclip"><i class="fa fa-paperclip" aria-hidden="true"></i>paperclip</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paragraph"><i class="fa fa-paragraph" aria-hidden="true"></i>paragraph</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/clipboard"><i class="fa fa-paste" aria-hidden="true"></i>paste</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/repeat"><i class="fa fa-repeat" aria-hidden="true"></i>repeat</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/undo"><i class="fa fa-rotate-left" aria-hidden="true"></i>rotate-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/repeat"><i class="fa fa-rotate-right" aria-hidden="true"></i>rotate-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/floppy-o"><i class="fa fa-save" aria-hidden="true"></i>save</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/scissors"><i class="fa fa-scissors" aria-hidden="true"></i>scissors</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/strikethrough"><i class="fa fa-strikethrough" aria-hidden="true"></i>strikethrough</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/subscript"><i class="fa fa-subscript" aria-hidden="true"></i>subscript</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/superscript"><i class="fa fa-superscript" aria-hidden="true"></i>superscript</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/table"><i class="fa fa-table" aria-hidden="true"></i>table</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/text-height"><i class="fa fa-text-height" aria-hidden="true"></i>text-height</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/text-width"><i class="fa fa-text-width" aria-hidden="true"></i>text-width</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/th"><i class="fa fa-th" aria-hidden="true"></i>th</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/th-large"><i class="fa fa-th-large" aria-hidden="true"></i>th-large</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/th-list"><i class="fa fa-th-list" aria-hidden="true"></i>th-list</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/underline"><i class="fa fa-underline" aria-hidden="true"></i>underline</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/undo"><i class="fa fa-undo" aria-hidden="true"></i>undo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chain-broken"><i class="fa fa-unlink" aria-hidden="true"></i>unlink</a></div>
    
  </div>

</section>

    <section id="directional">
  <h2 class="page-header">Directional Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-double-down"><i class="fa fa-angle-double-down" aria-hidden="true"></i>angle-double-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-double-left"><i class="fa fa-angle-double-left" aria-hidden="true"></i>angle-double-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-double-right"><i class="fa fa-angle-double-right" aria-hidden="true"></i>angle-double-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-double-up"><i class="fa fa-angle-double-up" aria-hidden="true"></i>angle-double-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-down"><i class="fa fa-angle-down" aria-hidden="true"></i>angle-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-left"><i class="fa fa-angle-left" aria-hidden="true"></i>angle-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-right"><i class="fa fa-angle-right" aria-hidden="true"></i>angle-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angle-up"><i class="fa fa-angle-up" aria-hidden="true"></i>angle-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-down"><i class="fa fa-arrow-circle-down" aria-hidden="true"></i>arrow-circle-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-left"><i class="fa fa-arrow-circle-left" aria-hidden="true"></i>arrow-circle-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-o-down"><i class="fa fa-arrow-circle-o-down" aria-hidden="true"></i>arrow-circle-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-o-left"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i>arrow-circle-o-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-o-right"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>arrow-circle-o-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-o-up"><i class="fa fa-arrow-circle-o-up" aria-hidden="true"></i>arrow-circle-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-right"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i>arrow-circle-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-circle-up"><i class="fa fa-arrow-circle-up" aria-hidden="true"></i>arrow-circle-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-down"><i class="fa fa-arrow-down" aria-hidden="true"></i>arrow-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-left"><i class="fa fa-arrow-left" aria-hidden="true"></i>arrow-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-right"><i class="fa fa-arrow-right" aria-hidden="true"></i>arrow-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrow-up"><i class="fa fa-arrow-up" aria-hidden="true"></i>arrow-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows"><i class="fa fa-arrows" aria-hidden="true"></i>arrows</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows-alt"><i class="fa fa-arrows-alt" aria-hidden="true"></i>arrows-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows-h"><i class="fa fa-arrows-h" aria-hidden="true"></i>arrows-h</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows-v"><i class="fa fa-arrows-v" aria-hidden="true"></i>arrows-v</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-down"><i class="fa fa-caret-down" aria-hidden="true"></i>caret-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-left"><i class="fa fa-caret-left" aria-hidden="true"></i>caret-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-right"><i class="fa fa-caret-right" aria-hidden="true"></i>caret-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-down"><i class="fa fa-caret-square-o-down" aria-hidden="true"></i>caret-square-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-left"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>caret-square-o-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-right"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i>caret-square-o-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-up"><i class="fa fa-caret-square-o-up" aria-hidden="true"></i>caret-square-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-up"><i class="fa fa-caret-up" aria-hidden="true"></i>caret-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-circle-down"><i class="fa fa-chevron-circle-down" aria-hidden="true"></i>chevron-circle-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-circle-left"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i>chevron-circle-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-circle-right"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i>chevron-circle-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-circle-up"><i class="fa fa-chevron-circle-up" aria-hidden="true"></i>chevron-circle-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-down"><i class="fa fa-chevron-down" aria-hidden="true"></i>chevron-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-left"><i class="fa fa-chevron-left" aria-hidden="true"></i>chevron-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-right"><i class="fa fa-chevron-right" aria-hidden="true"></i>chevron-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chevron-up"><i class="fa fa-chevron-up" aria-hidden="true"></i>chevron-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/exchange"><i class="fa fa-exchange" aria-hidden="true"></i>exchange</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-down"><i class="fa fa-hand-o-down" aria-hidden="true"></i>hand-o-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-left"><i class="fa fa-hand-o-left" aria-hidden="true"></i>hand-o-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-right"><i class="fa fa-hand-o-right" aria-hidden="true"></i>hand-o-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hand-o-up"><i class="fa fa-hand-o-up" aria-hidden="true"></i>hand-o-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/long-arrow-down"><i class="fa fa-long-arrow-down" aria-hidden="true"></i>long-arrow-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/long-arrow-left"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>long-arrow-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/long-arrow-right"><i class="fa fa-long-arrow-right" aria-hidden="true"></i>long-arrow-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/long-arrow-up"><i class="fa fa-long-arrow-up" aria-hidden="true"></i>long-arrow-up</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-down"><i class="fa fa-toggle-down" aria-hidden="true"></i>toggle-down</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-left"><i class="fa fa-toggle-left" aria-hidden="true"></i>toggle-left</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-right"><i class="fa fa-toggle-right" aria-hidden="true"></i>toggle-right</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/caret-square-o-up"><i class="fa fa-toggle-up" aria-hidden="true"></i>toggle-up</a></div>
    
  </div>

</section>

    <section id="video-player">
  <h2 class="page-header">Video Player Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/arrows-alt"><i class="fa fa-arrows-alt" aria-hidden="true"></i>arrows-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/backward"><i class="fa fa-backward" aria-hidden="true"></i>backward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/compress"><i class="fa fa-compress" aria-hidden="true"></i>compress</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/eject"><i class="fa fa-eject" aria-hidden="true"></i>eject</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/expand"><i class="fa fa-expand" aria-hidden="true"></i>expand</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fast-backward"><i class="fa fa-fast-backward" aria-hidden="true"></i>fast-backward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fast-forward"><i class="fa fa-fast-forward" aria-hidden="true"></i>fast-forward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/forward"><i class="fa fa-forward" aria-hidden="true"></i>forward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pause"><i class="fa fa-pause" aria-hidden="true"></i>pause</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pause-circle"><i class="fa fa-pause-circle" aria-hidden="true"></i>pause-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pause-circle-o"><i class="fa fa-pause-circle-o" aria-hidden="true"></i>pause-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/play"><i class="fa fa-play" aria-hidden="true"></i>play</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/play-circle"><i class="fa fa-play-circle" aria-hidden="true"></i>play-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/play-circle-o"><i class="fa fa-play-circle-o" aria-hidden="true"></i>play-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/random"><i class="fa fa-random" aria-hidden="true"></i>random</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/step-backward"><i class="fa fa-step-backward" aria-hidden="true"></i>step-backward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/step-forward"><i class="fa fa-step-forward" aria-hidden="true"></i>step-forward</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stop"><i class="fa fa-stop" aria-hidden="true"></i>stop</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stop-circle"><i class="fa fa-stop-circle" aria-hidden="true"></i>stop-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stop-circle-o"><i class="fa fa-stop-circle-o" aria-hidden="true"></i>stop-circle-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/youtube-play"><i class="fa fa-youtube-play" aria-hidden="true"></i>youtube-play</a></div>
    
  </div>

</section>

    <section id="brand">
  <h2 class="page-header">Brand Icons</h2>

  <div class="row fontawesome-icon-list margin-bottom-lg">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/500px"><i class="fa fa-500px" aria-hidden="true"></i>500px</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/adn"><i class="fa fa-adn" aria-hidden="true"></i>adn</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/amazon"><i class="fa fa-amazon" aria-hidden="true"></i>amazon</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/android"><i class="fa fa-android" aria-hidden="true"></i>android</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/angellist"><i class="fa fa-angellist" aria-hidden="true"></i>angellist</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/apple"><i class="fa fa-apple" aria-hidden="true"></i>apple</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/behance"><i class="fa fa-behance" aria-hidden="true"></i>behance</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/behance-square"><i class="fa fa-behance-square" aria-hidden="true"></i>behance-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bitbucket"><i class="fa fa-bitbucket" aria-hidden="true"></i>bitbucket</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bitbucket-square"><i class="fa fa-bitbucket-square" aria-hidden="true"></i>bitbucket-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/btc"><i class="fa fa-bitcoin" aria-hidden="true"></i>bitcoin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/black-tie"><i class="fa fa-black-tie" aria-hidden="true"></i>black-tie</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bluetooth"><i class="fa fa-bluetooth" aria-hidden="true"></i>bluetooth</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/bluetooth-b"><i class="fa fa-bluetooth-b" aria-hidden="true"></i>bluetooth-b</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/btc"><i class="fa fa-btc" aria-hidden="true"></i>btc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/buysellads"><i class="fa fa-buysellads" aria-hidden="true"></i>buysellads</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-amex"><i class="fa fa-cc-amex" aria-hidden="true"></i>cc-amex</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-diners-club"><i class="fa fa-cc-diners-club" aria-hidden="true"></i>cc-diners-club</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-discover"><i class="fa fa-cc-discover" aria-hidden="true"></i>cc-discover</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-jcb"><i class="fa fa-cc-jcb" aria-hidden="true"></i>cc-jcb</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-mastercard"><i class="fa fa-cc-mastercard" aria-hidden="true"></i>cc-mastercard</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-paypal"><i class="fa fa-cc-paypal" aria-hidden="true"></i>cc-paypal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-stripe"><i class="fa fa-cc-stripe" aria-hidden="true"></i>cc-stripe</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/cc-visa"><i class="fa fa-cc-visa" aria-hidden="true"></i>cc-visa</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/chrome"><i class="fa fa-chrome" aria-hidden="true"></i>chrome</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/codepen"><i class="fa fa-codepen" aria-hidden="true"></i>codepen</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/codiepie"><i class="fa fa-codiepie" aria-hidden="true"></i>codiepie</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/connectdevelop"><i class="fa fa-connectdevelop" aria-hidden="true"></i>connectdevelop</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/contao"><i class="fa fa-contao" aria-hidden="true"></i>contao</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/css3"><i class="fa fa-css3" aria-hidden="true"></i>css3</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/dashcube"><i class="fa fa-dashcube" aria-hidden="true"></i>dashcube</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/delicious"><i class="fa fa-delicious" aria-hidden="true"></i>delicious</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/deviantart"><i class="fa fa-deviantart" aria-hidden="true"></i>deviantart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/digg"><i class="fa fa-digg" aria-hidden="true"></i>digg</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/dribbble"><i class="fa fa-dribbble" aria-hidden="true"></i>dribbble</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/dropbox"><i class="fa fa-dropbox" aria-hidden="true"></i>dropbox</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/drupal"><i class="fa fa-drupal" aria-hidden="true"></i>drupal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/edge"><i class="fa fa-edge" aria-hidden="true"></i>edge</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/empire"><i class="fa fa-empire" aria-hidden="true"></i>empire</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/envira"><i class="fa fa-envira" aria-hidden="true"></i>envira</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/expeditedssl"><i class="fa fa-expeditedssl" aria-hidden="true"></i>expeditedssl</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/font-awesome"><i class="fa fa-fa" aria-hidden="true"></i>fa</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/facebook"><i class="fa fa-facebook" aria-hidden="true"></i>facebook</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/facebook"><i class="fa fa-facebook-f" aria-hidden="true"></i>facebook-f</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/facebook-official"><i class="fa fa-facebook-official" aria-hidden="true"></i>facebook-official</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/facebook-square"><i class="fa fa-facebook-square" aria-hidden="true"></i>facebook-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/firefox"><i class="fa fa-firefox" aria-hidden="true"></i>firefox</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/first-order"><i class="fa fa-first-order" aria-hidden="true"></i>first-order</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/flickr"><i class="fa fa-flickr" aria-hidden="true"></i>flickr</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/font-awesome"><i class="fa fa-font-awesome" aria-hidden="true"></i>font-awesome</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fonticons"><i class="fa fa-fonticons" aria-hidden="true"></i>fonticons</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/fort-awesome"><i class="fa fa-fort-awesome" aria-hidden="true"></i>fort-awesome</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/forumbee"><i class="fa fa-forumbee" aria-hidden="true"></i>forumbee</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/foursquare"><i class="fa fa-foursquare" aria-hidden="true"></i>foursquare</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/empire"><i class="fa fa-ge" aria-hidden="true"></i>ge</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/get-pocket"><i class="fa fa-get-pocket" aria-hidden="true"></i>get-pocket</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gg"><i class="fa fa-gg" aria-hidden="true"></i>gg</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gg-circle"><i class="fa fa-gg-circle" aria-hidden="true"></i>gg-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/git"><i class="fa fa-git" aria-hidden="true"></i>git</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/git-square"><i class="fa fa-git-square" aria-hidden="true"></i>git-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/github"><i class="fa fa-github" aria-hidden="true"></i>github</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/github-alt"><i class="fa fa-github-alt" aria-hidden="true"></i>github-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/github-square"><i class="fa fa-github-square" aria-hidden="true"></i>github-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gitlab"><i class="fa fa-gitlab" aria-hidden="true"></i>gitlab</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gratipay"><i class="fa fa-gittip" aria-hidden="true"></i>gittip</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/glide"><i class="fa fa-glide" aria-hidden="true"></i>glide</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/glide-g"><i class="fa fa-glide-g" aria-hidden="true"></i>glide-g</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google"><i class="fa fa-google" aria-hidden="true"></i>google</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-plus"><i class="fa fa-google-plus" aria-hidden="true"></i>google-plus</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-plus-official"><i class="fa fa-google-plus-circle" aria-hidden="true"></i>google-plus-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-plus-official"><i class="fa fa-google-plus-official" aria-hidden="true"></i>google-plus-official</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-plus-square"><i class="fa fa-google-plus-square" aria-hidden="true"></i>google-plus-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/google-wallet"><i class="fa fa-google-wallet" aria-hidden="true"></i>google-wallet</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/gratipay"><i class="fa fa-gratipay" aria-hidden="true"></i>gratipay</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hacker-news"><i class="fa fa-hacker-news" aria-hidden="true"></i>hacker-news</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/houzz"><i class="fa fa-houzz" aria-hidden="true"></i>houzz</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/html5"><i class="fa fa-html5" aria-hidden="true"></i>html5</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/instagram"><i class="fa fa-instagram" aria-hidden="true"></i>instagram</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/internet-explorer"><i class="fa fa-internet-explorer" aria-hidden="true"></i>internet-explorer</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ioxhost"><i class="fa fa-ioxhost" aria-hidden="true"></i>ioxhost</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/joomla"><i class="fa fa-joomla" aria-hidden="true"></i>joomla</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/jsfiddle"><i class="fa fa-jsfiddle" aria-hidden="true"></i>jsfiddle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/lastfm"><i class="fa fa-lastfm" aria-hidden="true"></i>lastfm</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/lastfm-square"><i class="fa fa-lastfm-square" aria-hidden="true"></i>lastfm-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/leanpub"><i class="fa fa-leanpub" aria-hidden="true"></i>leanpub</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/linkedin"><i class="fa fa-linkedin" aria-hidden="true"></i>linkedin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/linkedin-square"><i class="fa fa-linkedin-square" aria-hidden="true"></i>linkedin-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/linux"><i class="fa fa-linux" aria-hidden="true"></i>linux</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/maxcdn"><i class="fa fa-maxcdn" aria-hidden="true"></i>maxcdn</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/meanpath"><i class="fa fa-meanpath" aria-hidden="true"></i>meanpath</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/medium"><i class="fa fa-medium" aria-hidden="true"></i>medium</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/mixcloud"><i class="fa fa-mixcloud" aria-hidden="true"></i>mixcloud</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/modx"><i class="fa fa-modx" aria-hidden="true"></i>modx</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/odnoklassniki"><i class="fa fa-odnoklassniki" aria-hidden="true"></i>odnoklassniki</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/odnoklassniki-square"><i class="fa fa-odnoklassniki-square" aria-hidden="true"></i>odnoklassniki-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/opencart"><i class="fa fa-opencart" aria-hidden="true"></i>opencart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/openid"><i class="fa fa-openid" aria-hidden="true"></i>openid</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/opera"><i class="fa fa-opera" aria-hidden="true"></i>opera</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/optin-monster"><i class="fa fa-optin-monster" aria-hidden="true"></i>optin-monster</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pagelines"><i class="fa fa-pagelines" aria-hidden="true"></i>pagelines</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/paypal"><i class="fa fa-paypal" aria-hidden="true"></i>paypal</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pied-piper"><i class="fa fa-pied-piper" aria-hidden="true"></i>pied-piper</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pied-piper-alt"><i class="fa fa-pied-piper-alt" aria-hidden="true"></i>pied-piper-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pied-piper-pp"><i class="fa fa-pied-piper-pp" aria-hidden="true"></i>pied-piper-pp</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i>pinterest</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pinterest-p"><i class="fa fa-pinterest-p" aria-hidden="true"></i>pinterest-p</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/pinterest-square"><i class="fa fa-pinterest-square" aria-hidden="true"></i>pinterest-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/product-hunt"><i class="fa fa-product-hunt" aria-hidden="true"></i>product-hunt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/qq"><i class="fa fa-qq" aria-hidden="true"></i>qq</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rebel"><i class="fa fa-ra" aria-hidden="true"></i>ra</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rebel"><i class="fa fa-rebel" aria-hidden="true"></i>rebel</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reddit"><i class="fa fa-reddit" aria-hidden="true"></i>reddit</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reddit-alien"><i class="fa fa-reddit-alien" aria-hidden="true"></i>reddit-alien</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/reddit-square"><i class="fa fa-reddit-square" aria-hidden="true"></i>reddit-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/renren"><i class="fa fa-renren" aria-hidden="true"></i>renren</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/rebel"><i class="fa fa-resistance" aria-hidden="true"></i>resistance</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/safari"><i class="fa fa-safari" aria-hidden="true"></i>safari</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/scribd"><i class="fa fa-scribd" aria-hidden="true"></i>scribd</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/sellsy"><i class="fa fa-sellsy" aria-hidden="true"></i>sellsy</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share-alt"><i class="fa fa-share-alt" aria-hidden="true"></i>share-alt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/share-alt-square"><i class="fa fa-share-alt-square" aria-hidden="true"></i>share-alt-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/shirtsinbulk"><i class="fa fa-shirtsinbulk" aria-hidden="true"></i>shirtsinbulk</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/simplybuilt"><i class="fa fa-simplybuilt" aria-hidden="true"></i>simplybuilt</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/skyatlas"><i class="fa fa-skyatlas" aria-hidden="true"></i>skyatlas</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/skype"><i class="fa fa-skype" aria-hidden="true"></i>skype</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/slack"><i class="fa fa-slack" aria-hidden="true"></i>slack</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/slideshare"><i class="fa fa-slideshare" aria-hidden="true"></i>slideshare</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/snapchat"><i class="fa fa-snapchat" aria-hidden="true"></i>snapchat</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/snapchat-ghost"><i class="fa fa-snapchat-ghost" aria-hidden="true"></i>snapchat-ghost</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/snapchat-square"><i class="fa fa-snapchat-square" aria-hidden="true"></i>snapchat-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/soundcloud"><i class="fa fa-soundcloud" aria-hidden="true"></i>soundcloud</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/spotify"><i class="fa fa-spotify" aria-hidden="true"></i>spotify</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stack-exchange"><i class="fa fa-stack-exchange" aria-hidden="true"></i>stack-exchange</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stack-overflow"><i class="fa fa-stack-overflow" aria-hidden="true"></i>stack-overflow</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/steam"><i class="fa fa-steam" aria-hidden="true"></i>steam</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/steam-square"><i class="fa fa-steam-square" aria-hidden="true"></i>steam-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stumbleupon"><i class="fa fa-stumbleupon" aria-hidden="true"></i>stumbleupon</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stumbleupon-circle"><i class="fa fa-stumbleupon-circle" aria-hidden="true"></i>stumbleupon-circle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tencent-weibo"><i class="fa fa-tencent-weibo" aria-hidden="true"></i>tencent-weibo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/themeisle"><i class="fa fa-themeisle" aria-hidden="true"></i>themeisle</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/trello"><i class="fa fa-trello" aria-hidden="true"></i>trello</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tripadvisor"><i class="fa fa-tripadvisor" aria-hidden="true"></i>tripadvisor</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tumblr"><i class="fa fa-tumblr" aria-hidden="true"></i>tumblr</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/tumblr-square"><i class="fa fa-tumblr-square" aria-hidden="true"></i>tumblr-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/twitch"><i class="fa fa-twitch" aria-hidden="true"></i>twitch</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/twitter"><i class="fa fa-twitter" aria-hidden="true"></i>twitter</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/twitter-square"><i class="fa fa-twitter-square" aria-hidden="true"></i>twitter-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/usb"><i class="fa fa-usb" aria-hidden="true"></i>usb</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/viacoin"><i class="fa fa-viacoin" aria-hidden="true"></i>viacoin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/viadeo"><i class="fa fa-viadeo" aria-hidden="true"></i>viadeo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/viadeo-square"><i class="fa fa-viadeo-square" aria-hidden="true"></i>viadeo-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/vimeo"><i class="fa fa-vimeo" aria-hidden="true"></i>vimeo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/vimeo-square"><i class="fa fa-vimeo-square" aria-hidden="true"></i>vimeo-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/vine"><i class="fa fa-vine" aria-hidden="true"></i>vine</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/vk"><i class="fa fa-vk" aria-hidden="true"></i>vk</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/weixin"><i class="fa fa-wechat" aria-hidden="true"></i>wechat</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/weibo"><i class="fa fa-weibo" aria-hidden="true"></i>weibo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/weixin"><i class="fa fa-weixin" aria-hidden="true"></i>weixin</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/whatsapp"><i class="fa fa-whatsapp" aria-hidden="true"></i>whatsapp</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wikipedia-w"><i class="fa fa-wikipedia-w" aria-hidden="true"></i>wikipedia-w</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/windows"><i class="fa fa-windows" aria-hidden="true"></i>windows</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wordpress"><i class="fa fa-wordpress" aria-hidden="true"></i>wordpress</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wpbeginner"><i class="fa fa-wpbeginner" aria-hidden="true"></i>wpbeginner</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wpforms"><i class="fa fa-wpforms" aria-hidden="true"></i>wpforms</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/xing"><i class="fa fa-xing" aria-hidden="true"></i>xing</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/xing-square"><i class="fa fa-xing-square" aria-hidden="true"></i>xing-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/y-combinator"><i class="fa fa-y-combinator" aria-hidden="true"></i>y-combinator</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hacker-news"><i class="fa fa-y-combinator-square" aria-hidden="true"></i>y-combinator-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/yahoo"><i class="fa fa-yahoo" aria-hidden="true"></i>yahoo</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/y-combinator"><i class="fa fa-yc" aria-hidden="true"></i>yc</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hacker-news"><i class="fa fa-yc-square" aria-hidden="true"></i>yc-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/yelp"><i class="fa fa-yelp" aria-hidden="true"></i>yelp</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/yoast"><i class="fa fa-yoast" aria-hidden="true"></i>yoast</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/youtube"><i class="fa fa-youtube" aria-hidden="true"></i>youtube</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/youtube-play"><i class="fa fa-youtube-play" aria-hidden="true"></i>youtube-play</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/youtube-square"><i class="fa fa-youtube-square" aria-hidden="true"></i>youtube-square</a></div>
    
  </div>

</section>

    <section id="medical">
  <h2 class="page-header">Medical Icons</h2>

  <div class="row fontawesome-icon-list">
    

    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/ambulance"><i class="fa fa-ambulance" aria-hidden="true"></i>ambulance</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/h-square"><i class="fa fa-h-square" aria-hidden="true"></i>h-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/heart"><i class="fa fa-heart" aria-hidden="true"></i>heart</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/heart-o"><i class="fa fa-heart-o" aria-hidden="true"></i>heart-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/heartbeat"><i class="fa fa-heartbeat" aria-hidden="true"></i>heartbeat</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/hospital-o"><i class="fa fa-hospital-o" aria-hidden="true"></i>hospital-o</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/medkit"><i class="fa fa-medkit" aria-hidden="true"></i>medkit</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/plus-square"><i class="fa fa-plus-square" aria-hidden="true"></i>plus-square</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/stethoscope"><i class="fa fa-stethoscope" aria-hidden="true"></i>stethoscope</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/user-md"><i class="fa fa-user-md" aria-hidden="true"></i>user-md</a></div>
    
      <div class="fa-hover col-md-3 col-sm-4"><a href="../icon/wheelchair"><i class="fa fa-wheelchair" aria-hidden="true"></i>wheelchair</a></div>
    
  </div>

</section>

  </div>
</body>
</html>