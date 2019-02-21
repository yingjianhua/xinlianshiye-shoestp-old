<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0052)https://www.shoestp.com//sell/nr/001/images/drob.png -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" async="" src="./static/js/tracking.js"></script>
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>

    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->

    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js"></script>
    <script src="./static/js/lazyload.min.js"></script>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<div id="app">
    <index-top></index-top>
</div>
<div id="error_page">
    <div id="main">
        <div class="wide">
            <div class="error_logo sw"></div>
            <div class="error_warning sw"><s:text name="404.Sorry"/></div>
            <div class="error_nav sw">
                <a href="javascript:;" onclick="history.back();"><s:text name="Global.Return"/></a>|
                <a href="/"><s:text name="Global.Home"/></a>|
                <a href="/home/cnt_CntSglPageCategory_gosglpage?pkey=8"><s:text name="Global.Contact_Us"/></a>
            </div>
        </div>
    </div>
    <index-bottom></index-bottom>
</div>
<%--<%@ include file="/home/template/new-foot.jsp" %>--%>
<div id="hj_top" style="opacity: 0;">
    <img src="./static/images/hj_top.png">
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:"#app"
    })
</script>
</body>
</html>