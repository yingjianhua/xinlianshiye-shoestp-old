<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="renderer" content="webkit">

    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link href="./static/css/cart.css" rel="stylesheet" type="text/css">
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->

    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>
    <script src="./static/js/swiper.min.js"></script>
    <script src="./static/js/lazyload.min.js"></script>
    <script type="text/javascript" src="./static/js/cart.js"></script>
    <script type="text/javascript" src="./static/js/tool_tips_web.js"></script>

    <style type="text/css">.project-box {
        background-color: #f9f9f9;
        width: 100%;
    }

    .project-title {
        height: 70px;
        line-height: 70px;
        font-size: 20px;
        width: 1200px;
        margin: 0 auto;
        padding-top: 20px;
    }

    .project-main {
        width: 1200px;
        margin: 0 auto;
    }

    a:hover {
        text-decoration: none;
    }

    .project-item {
        width: 230px;
        height: 321px;
        border-radius: 4px;
        background-color: #ffffff;
        float: left;
        margin: 0 10px 10px 0;
    }

    .project-info {
        padding: 10px;
        height: 122px;
    }

    .num {
        font-size: 14px
    }

    .project-item:hover .num {
        color: #4e84c4;
    }

    .project-item:hover {
        box-shadow: 0px 5px 15px 0px rgba(0, 0, 0, 0.2);
    }

    .project-content .project-none-item {
        margin-bottom: 70px;
    }

    .project-none-item {
        float: left;
    }


    </style>
</head>
<body>
<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/new-header.jsp" %>


<div class="project-box">
    <div class="project-title"><s:text name="magazine.Magazine_Ranking"/></div>

    <div class="project-main">
        <div class="project-content clearfix">
            <c:forEach items="${map['list']}" var="p">
                <div class="project-item">
                    <a href="/home/cnt_CntMagazine_content?contenturl=${p.contenturl}">
                        <div class="project-img">
                            <img src="${envConfig.imageBaseUrl}${p.specialPictures}" alt="">
                        </div>
                        <div class="project-info">
                            <div style="height: 66px;">
                                <div style="font-size:15px;color:#333">${p.name}</div>
                                <div style="font-size:12px;color:#999999">${p.content}</div>
                            </div>
                            <div style=" display: flex;justify-content: space-between;align-items: center;height: 34px;border-top: 1px solid #eeeeee;">
                                <div>
                                    <img src="/home/static/themes/default/mobile/images/off-label.png" alt=""
                                         class="icon-label">
                                    <span class="num">${p.cycle}</span>
                                </div>
                                <div>
                                    <img src="/home/static/themes/default/mobile/images/time.png" alt="">
                                    <span style="font-size: 14px;color:#999999;"><fmt:formatDate
                                            value="${p.time}"/></span>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="/home/template/new-foot.jsp" %>
<script>
    $('.project-content .project-item').hover(function () {
        $(this).find('.project-info').find('.icon-label').attr("src", "/home/static/themes/default/mobile/images/on-label.png")
    }, function () {
        $(this).find('.project-info').find('.icon-label').attr("src", "/home/static/themes/default/mobile/images/off-label.png")
    })
</script>
</body>


</html>
