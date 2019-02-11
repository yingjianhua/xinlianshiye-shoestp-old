<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>
        <c:if test="${supView.webSizeTitle !=''}">
            ${supView.webSizeTitle}
        </c:if>
        <c:if test="${supView.webSizeTitle ==''}">
            An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
        </c:if>
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link href="./static/css/index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <!-- 新引入的css -->
    <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
    <!-- 轮播插件 -->
    <script type="text/javascript" src="./static/js/jquery.SuperSlide.js"></script>
    <style>
        .por {
            position: relative;
        }

        /* 轮播背景色 */
        .carousel-wrap {
            background: #fff;
        }

        /* 固定顶部轮播图的height */
        .slideBox, .slideBox li, .slideBox ul img {
            height: 480px;
            display: block;
        }

        /* banner图片width特大时，居中显示 */
        .slideBox .slide-pic-group li img {
            position: absolute;
            top: 0;
            object-fit: cover;
            /*       left: 50%; */
            /*       transform: translateX(-50%); */

            left: 0;
            height: 100%;
            width: 100%;
        }
    </style>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">

<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/shop-header.jsp" %>

<div id="main">
    <index-top></index-top>
    <div class="wide">
        <c:if test="${supView.homePageOn==1}">
            <div class="clean">
                <c:if test="${supView.homePageDiy != null && supView.homePageDiy != ''}">
                    ${supView.homePageDiy}
                </c:if>
            </div>
        </c:if>
        <!-- 大轮播 图 top -->

        <c:if test="${supView.homePageOn!=1}">
            <div class="carousel-wrap">
                <div id="slideBox" class="slideBox">
                    <div class="slide-pic-group">
                            <%-- <div class="tempWrap" style="overflow:hidden; position:relative; width:2541px"> --%>
                            <%-- <ul style="width: 12705px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: -7623px;"> --%>
                        <ul>
                            <c:set var="adPhotoLink" value="''"/>
                            <c:forTokens items="${supView.adPhoto}" delims="," var="photo"
                                         varStatus="index">
                                <li class="por">

                                    <c:if test="${not empty supView.adPhotoLink}">
                                        <c:forTokens items="${supView.adPhotoLink}" delims="," var="adPhoto"
                                                     varStatus="index2">
                                            <c:if test="${index.index == index2.index}">
                                                <c:set var="adPhotoLink" value="${adPhoto}"/>
                                            </c:if>
                                        </c:forTokens>
                                    </c:if>

                                    <a href="${adPhotoLink}" target="_self">
                                        <img src="${envConfig.imageBaseUrl}${photo}"/>
                                    </a>
                                </li>
                            </c:forTokens>
                        </ul>
                            <%-- </div> --%>
                    </div>

                    <c:if test="${not empty supView}">
                        <!-- 下面是前/后按钮代码 -->
                        <a class="prev" href="javascript:void(0)"></a>
                        <a class="next" href="javascript:void(0)"></a>
                    </c:if>
                </div>
            </div>
            <!-- 公司介绍 - wrap -->
            <div class="bg-gray">
                <div class="wide">
                    <div class="enterprise-info-wrap">
                        <h3 class="enterprise-info-title">
                            <s:text name="CompanyInformation"/></h3>
                        <!-- 公司介绍 -->
                        <div class="company-introduction-wrap">

                            <!-- 小轮播 图 middle  -->
                            <div id="slideBoxM" class="slideBoxM poster-pic-slide">
                                <div class="slide-nav">
                                    <ul></ul>
                                </div>
                                <div class="slide-pic-group">
                                    <div class="tempWrap"
                                         style="overflow:hidden; position:relative; width:780px">
                                        <ul style="width: 4680px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: -2340px;">
                                            <c:set var="companyPhotoLink" value="''"/>
                                            <c:forTokens items="${supView.companyPhoto}" delims=","
                                                         var="photo"
                                                         varStatus="index">
                                                <c:if test="${not empty supView.companyPhotoLink}">
                                                    <c:forTokens items="${supView.companyPhotoLink}"
                                                                 delims=","
                                                                 var="companyLink" varStatus="index2">
                                                        <c:if test="${index.index == index2.index}">
                                                            <c:set var="companyPhotoLink"
                                                                   value="${companyLink}"/>
                                                        </c:if>
                                                    </c:forTokens>
                                                </c:if>


                                                <li class="clone" style="float: left; width: 780px;">
                                                    <a href="${companyPhotoLink}" target="_blank">
                                                        <img src="${envConfig.imageBaseUrl}${photo}"/>
                                                    </a>
                                                </li>
                                            </c:forTokens>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- <div class="poster-pic-slide">
                              <img src="/static/themes/t050/images/four_pic/slide-img/poster_pic_01.jpg" style="width: 100%;"/>
                            </div> -->
                            <div class="summary">
                                <div class="enterprise-info-title" style="overflow:hidden;">
                                        ${fn:toLowerCase(supView.showName)} </div>
                                <div class="summary-text">
                                    <p><s:text name="LimitedLiabilityCompany"/></p><!-- 企业类型 -->
                                    <p><s:text
                                            name="supplier.mainsalesarea"/>: ${supView.mainSalesArea} </p>
                                    <p><s:text name="supplier.companytype"/>: ${supView.companyType}</p>
                                    <!-- MATERIALS -->
                                </div>
                                <a class="btn btn-mist"
                                   href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supView.pkey}">
                                    <s:text name="read_more"/> </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${supView.isPro==1}">
            <div class="clean">
                <div class="wide">
                    <div class="enterprise-info-wrap">
                        <h3 class="enterprise-info-title">
                            <s:text name="hottestItems"/>
                            <!-- 查看更多-按钮 -->
                            <div class="tip-info fr">
                                <a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supView.pkey}">
                                    <span><s:text name="read_more"/></span>
                                    <i class="icon icon-plus"></i>
                                </a>
                            </div>
                        </h3>
                        <!-- 商品展示 -->
                        <div class="goods-wrap">
                            <c:forEach items="${supView.productList}" var="pro" varStatus="index">
                                <div class="goods-box" style="width: 284px;">
                                    <div class="goods-item">
                                        <div class="goods-pic pic_box">
                                            <a href="/${pro.rewrite}" title="${pro.pdt.name}"
                                               target="_blank">
                                                <c:forTokens items="${pro.pdt.picture}" var="pic" delims=","
                                                             end="0">
                                                    <img src="${envConfig.imageBaseUrl}${pic}?x-oss-process=image/resize,m_pad,h_256,w_256">
                                                </c:forTokens>
                                            </a>
                                            <span></span>
                                        </div>
                                        <h5 class="goods-title">
                                            <a href="/${pro.rewrite}" title="${pro.pdt.name}"
                                               target="_blank">${pro.pdt.name}</a>
                                        </h5>
                                        <div class="goods-price">
                                                ${env.currency.symbols}${pro.pdt.curPrice}
                                        </div>
                                        <div class="btn btn-enter">
                                            <a href="/${pro.rewrite}" class="btn btn-enter" target="_blank"><s:text
                                                    name="show_now"/></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <%@ include file="/home/template/new-foot.jsp" %>
        ${supView.traceCode}
    </div>
</div>
<script type="text/javascript">
    $(function () {
        // 启用轮播1
        jQuery(".slideBox").slide({
            mainCell: ".slide-pic-group ul",
            autoPlay: true,
            interTime: 5000,
            effect: "leftLoop"
        });
        // 启用轮播2
        jQuery(".slideBoxM").slide({
            mainCell: ".slide-pic-group ul",
            titCell: ".slide-nav li",
            trigger: "click",
            autoPlay: true,
            interTime: 5000,
            effect: "leftLoop"
        });
    })
</script>

<div align="center">
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script>
    new Vue({
        el: "#main"
    })
</script>
</body>

</html>
