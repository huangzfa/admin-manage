<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/plugin/layer/mobile/need/layer.css">
    <script type="text/javascript" src="/static/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/static/plugin/layer/layer.js"></script>

</head>

<script>
    $(function(){
        var date = getNowFormatDate();
        top.layer.alert("您的账号与${tickTime}在其他设备登录，如非本人操作，短信验证码可能泄露", {
            icon: 6,
            end: function(){
                parent.window.location.href="/";
            }
        });
    })
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }
</script>
</html>