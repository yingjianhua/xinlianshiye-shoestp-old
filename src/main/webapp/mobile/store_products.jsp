<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<!-- saved from url=(0032)https://www.shoestp.com/m/luxin/ -->
<html lang="en" data-dpr="1">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <link href="/home/static/themes/default/mobile/css/style_new.css" rel="stylesheet" type="text/css">

</head>

<body style="font-size: 12px;">
<div class="page page-enterprise-product flexible flex-column fill">
    <!-- 顶上 - 固定的头部条 -->
    <%@include file="/mobile/template/header.jsp" %>
    <!-- 顶上 - 固定的头部条 -end -->
    <!-- 公司头部 - 含背景图 -->
    <div class="scroll flex-grow">
        <div class="enterprise-top-header flexible flex-c-center">
            <!-- 公司logo -->
            <div class="logo">
                <img src="/home/static/themes/default/mobile/images/4df46e0c5e.jpg" alt="logo">
            </div>
            <!-- 公司简介信息 -->
            <div class="enterprise-profile">
                <div class="text">
                    WENZHOU LUXIN SHOES CO.,LTD

                </div>
                <!-- 图标组 -->
                <div class="icon-group flexible flex-c-center">
                    <!-- 自制年限图标 -->
                    <a href="#" target="_blank">
                        <img class="icon" src="/home/static/themes/default/mobile/images/defence-safe-o.png"
                             alt="defence-safe">
                    </a>
                    <a href="#" target="_blank">
                        <img class="icon" src="/home/static/themes/default/mobile/images/certification-o.png"
                             alt="certification">
                    </a>
                </div>
            </div>
        </div>
        <!-- 顶部导航条 -->
        <div class="nav-bar bg-white">
            <ul class="flexible text-center">
                <li class="">
                    <a href="/">
                        <!-- HOME -->
                        <s:text name="home"/>
                    </a>
                </li>
                <li class="active">
                    <a href="/home/pdt_PdtProduct">
                        <!-- PRODUCTS -->
                        <s:text name="order_line.spec"/>
                    </a>
                </li>
                <li class="">
                    <a href="https://www.shoestp.com/m/luxin/company.html">
                        <!-- PROFILE -->
                        <s:text name="freight_line.brief"/>
                    </a>
                </li>
                <li class="">
                    <a href="https://www.shoestp.com/m/luxin/contact.html">
                        <!-- CONTACT -->
                        <s:text name="contactmenu"/>
                    </a>
                </li>
            </ul>
        </div>
        <!-- 顶部导航条 - end -->
        <!-- 主内容 -->
        <div class="flex-grow">
            <div class="main">
                <div class="clean">
                </div>
                <!--- 工具栏 -->
                <ul class="tool-group">
                    <li class="tool-item">
                                <span class="tool-name">
                                    <!-- Sort by -->
                                    <s:text name="sort_by"/>
                                </span>
                        <div class="tool-content tool-drop-down-wrap">
                            <div class="tool-drop-down-btn">
                                        <span class="select-text">
                                            <!-- 已选择的内容 -->
                                            <!-- Sort by -->
                                            <s:text name="sort_by"/>
                                        </span>
                                <i class="icon icon-arrow-down">
                                </i>
                            </div>
                            <div class="tool-drop-down-content">
                                <ul>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;Sort=1d">
                                            <!-- Most Popular -->
                                            <s:text name="most_popular"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;Sort=2d">
                                            <!-- Sales -->
                                            <s:text name="products.sales"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;Sort=3d">
                                            <!-- Favorites -->
                                            <s:text name="Favorites"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;Sort=4d">
                                            <!-- New -->
                                            <s:text name="new"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;Sort=5a">
                                            <!-- Price -->
                                            <s:text name="cart.price"/>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="tool-item">
                                <span class="tool-name">
                                    <!-- Classify -->
                                    <s:text name="article.article_category"/>
                                </span>
                        <div class="tool-content tool-drop-down-wrap">
                            <div class="tool-drop-down-btn">
                                        <span class="select-text">
                                            <!-- All Categories -->
                                            <s:text name="All_Categories"/>
                                        </span>
                                <i class="icon icon-arrow-down">
                                </i>
                            </div>
                            <div class="tool-drop-down-content">
                                <ul>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?">
                                            <!-- All Categories -->
                                            <s:text name="All_Categories"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;CateIdBusiness=36">
                                            <!-- MENS SHOES -->
                                            <s:text name="womensShoes"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.shoestp.com/m/luxin/product/?&amp;CateIdBusiness=37">
                                            <!-- MENS BOOTS -->
                                            <s:text name="womensBoots"/>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
                <!-- 工具栏 - end -->
                <!-- 商品栏 -->
                <div class="goods-show-wrap goods-show-col-2 col-two flexible">

                    <c:forEach items="${list}" varStatus="status">
                        <div class="goods-item-box">
                            <div class="goods-item">
                                <div class="goods-pic">
                                    <a href="/home/usr_UsrSupplier_goProduct?id='+${list[status.index].pkey}+'"
                                       title="${list[status.index].name}"
                                       target="_blank">
                                        <img src="${envConfig.imageBaseUrl}${list[status.index].picture}">
                                    </a>
                                    <span>
                                        </span>
                                    <i class="badge badge-hot">
                                    </i>
                                </div>
                                <div class="goods-info-wrap">
                                    <div class="descript">
                                        <a href="/home/usr_UsrSupplier_ShopListPage?id='+${list[status.index].pkey}+'"
                                           title="${list[status.index].name}"
                                           target="_blank">
                                            Luxin 2018 Hot Sell Suede Leather Wear Resistant Lace-Up Outdoor Wear
                                            Shoes For Men's Shoes
                                        </a>
                                    </div>
                                    <div class="price">
                                        $${list[status.index].wsPrice}
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </div>
                <!-- 商品栏 - end -->
            </div>
            <!-- main - end -->
        </div>
        <!-- 主内容 - flex-grow - end -->
    </div>
    <!-- 公司头部 - 含背景图 - end -->
    <!-- 底部 -->
    <%@ include file="/mobile/template/foot_menu.jsp" %>
    <!-- 底部 - end -->
    <div align="center">
        <script type="text/javascript" src="/home/static/themes/default/mobile/js/analytics(1).js">
        </script>
    </div>
</div>
<script src="/home/static/themes/default/mobile/js/zepto.js">
</script>
<script type="text/javascript" src="/home/static/themes/default/mobile/js/base.js">
</script>
</body>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127715615-6"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());
    gtag('config', 'UA-127715615-6');
</script>


</html>
