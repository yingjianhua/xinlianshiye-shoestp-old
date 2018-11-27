<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en" data-dpr="1">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <link href="/home/static/themes/default/mobile/css/style_new.css" rel="stylesheet" type="text/css">
    <%-- <script src="/home/static/themes/default/mobile/js/jquery.SuperSlide.js"></script> --%>
    <script src="/home/static/themes/default/mobile/js/jquery.TouchSlide.1.1.js"></script>
    <style>
        /* 轮播插件的样式 */
        .slideBox {
            width: 96%;
            height: 13.5rem;
            margin: 0 auto;
            overflow: hidden;
            position: relative;
            border: 1px solid #ddd;
        }

        .slideBox .hd {
            height: 15px;
            overflow: hidden;
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            bottom: 12px;
            z-index: 1;
        }

        .slideBox .hd ul {
            overflow: hidden;
            zoom: 1;
            float: left;
        }

        .slideBox .hd ul li {
            float: left;
            margin-right: 12px;
            width: 30px;
            height: 6px;
            line-height: 6px;
            font-size: 0;
            text-align: center;
            background: #ddd;
            cursor: pointer;
            border-radius: 6px;
        }

        .slideBox .hd ul li.on {
            background: #fff;
            color: #fff;
        }

        .slideBox .bd {
            position: relative;
            height: 100%;
            z-index: 0;
        }

        .slideBox .bd li {
            zoom: 1;
            vertical-align: middle;
        }

        .slideBox .bd img {
            width: 100%;
            height: 13.5rem;
            display: block;
            object-fit: cover;
        }
    </style>
</head>

<body style="font-size: 12px;">
<div class="page page-enterprise-home flexible flex-column fill">
    <!-- 顶上 - 固定的头部条 -->
    <%@ include file="/mobile/template/header.jsp" %>
    <!-- 顶上 - 固定的头部条 -end -->
    <!-- 公司头部 - 含背景图 -->
    <div class="scroll flex-grow">

        <div class="enterprise-top-header flexible flex-c-center">
            <!-- 公司logo -->
            <div class="logo">
                <img src="<c:if test='${not empty supView.logo}'>${envConfig.imageBaseUrl}${supView.logo}</c:if><c:if test='${empty supView.logo}'>/home/static/images/headimg.jpg</c:if>"
                     alt="logo">
            </div>
            <!-- 公司简介信息 -->
            <div class="enterprise-profile">
                <div class="text">
                    ${supView.showName}
                </div>
                <!-- 图标组 -->

                <div class="icon-group flexible flex-c-center">
                    <c:if test="${not empty supView.authAge && supView.authAge > 0}">
                        <!-- 自制年限图标 -->
                        <div class="icon icon-years-limit-group">
                            <a href="/m/spain/company.html#data" target="_blank">
                                <img src="/home/static/themes/default/mobile/images/doller_pic_o.png" alt="">
                                <span class="years-num">${supView.authAge}</span>
                                <span class="years-unit">YRS</span>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${supView.isAuth == 1}">
                        <!-- 自制年限图标 -->
                        <a href="#" target="_blank">
                            <img class="icon" src="/home/static/themes/default/mobile/images/defence-safe-o.png"
                                 alt="defence-safe">
                        </a>
                        <a href="#" target="_blank">
                            <img class="icon" src="/home/static/themes/default/mobile/images/certification-o.png"
                                 alt="certification">
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- 顶部导航条 -->
        <%@ include file="template/shop-nav.jsp" %>
        <!-- 顶部导航条 - end -->
        <!-- 主内容 -->
        <div class="flex-grow">
            <div class="main">
                <!-- é¡¶é¨ - å¬å¸ä¸»å¾ç -->
                <div class="enterprise-index-pic">

                    <c:set var="adPhotoLink" value="''"/>
                    <c:forTokens items="${supView.adPhotoMobile}" var="photo" delims="," end="0" varStatus="index">
                        <%-- <c:if test="${not empty supView.adPhotoLink}">
                              <c:forTokens items="${supView.adPhotoLink}" delims="," var="adPhoto" varStatus="index2">
                                  <c:if test="${index.index == index2.index}">
                                      <c:set var="adPhotoLink" value="${adPhoto}"/>
                                  </c:if>
                              </c:forTokens>
                          </c:if> --%>
                        <c:if test="${supView.pkey == 262}">
                            <c:set var="adPhotoLink" value="/home/usr_UsrSupplier_gtSupGroup?pkey=262"/>
                            <a href="${adPhotoLink}">
                                <img src="${envConfig.imageBaseUrl}${photo}">
                            </a>
                        </c:if>
                        <c:if test="${supView.pkey != 262}">
                            <img src="${envConfig.imageBaseUrl}${photo}">
                        </c:if>
                    </c:forTokens>
                </div>
                <!-- homeé¡µä¸­çå¬å¸ç®ä» -->
                <div class="enterprise-home-profile text-center bg-white">
                    <div class="profile-title">
                        ${supView.showName}
                    </div>
                    <div class="profile-content">
                        <p>
                            <!-- Company Type -->
                            <s:text name="supplier.companytype"/>: ${supView.companyType}
                        </p>
                        <p>
                            <!-- Sales area -->
                            <s:text name="supplier.mainsalesarea"/>: ${supView.mainSalesArea}
                        </p>
                    </div>
                    <a class="m-btn" href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supView.pkey}">
                        <!-- View More -->
                        <s:text name="read_more"/>
                    </a>
                </div>


                <!-- 轮播图 -->
                <div id="slide" class="slideBox">
                    <!-- 轮播中导航用的点点点 -->
                    <div class="hd">
                        <ul></ul>
                    </div>
                    <!-- 轮播图片 -->
                    <div class="bd">
                        <ul>
                            <%-- <li><img src="/home/static/themes/default/mobile/images/0af52b7816.jpg" /></li>
                                      <li><img src="/home/static/themes/default/mobile/images/3.png" /></li> --%>

                            <c:set var="companyPhotoLink" value="''"/>
                            <c:forTokens items="${supView.companyPhoto}" delims="," var="companyPhoto"
                                         varStatus="index">
                                <c:if test="${not empty supView.companyPhotoLink}">
                                    <c:forTokens items="${supView.companyPhotoLink}" delims="," var="companyLink"
                                                 varStatus="index2">
                                        <c:if test="${index.index == index2.index}">
                                            <c:set var="companyPhotoLink" value="${companyLink}"/>
                                        </c:if>
                                    </c:forTokens>
                                </c:if>

                                <li>
                                    <a href="${companyPhotoLink}" target="_blank">
                                        <img src="${envConfig.imageBaseUrl}${companyPhoto}"/>
                                    </a>
                                </li>
                            </c:forTokens>


                        </ul>
                    </div>
                </div>

                <!-- 轮播图 - end -->
                <%--<div class="clean">--%>
                    <%-- <c:if test="${supView.homePageDiyMobile != null && supView.homePageDiyMobile != ''}">
                        ${supView.homePageDiyMobile}
                    </c:if> --%>
                <%--</div>--%>
                <!-- 商品栏 - 标题 -->
                <div class="info-group-header">
                    <!-- PRODUCTS -->
                    <s:text name="mobile.product"/>
                </div>
                <!-- 商品栏 -->
                <div class="goods-show-wrap goods-show-col-2 col-two flexible">
                    <c:forEach items="${supView.productList}" var="pro" varStatus="index">
                        <div class="goods-item-box">
                            <div class="goods-item">
                                <div class="goods-pic">
                                    <a href="/${pro.rewrite}" title="${pro.pdt.name}" target="_blank">
                                        <c:forTokens items="${pro.pdt.picture}" delims="," var="pic" end="0">
                                            <img src="${envConfig.imageBaseUrl}${pic}">
                                        </c:forTokens>
                                    </a>
                                    <i class="badge badge-hot">
                                    </i>
                                </div>
                                <div class="goods-info-wrap">
                                    <div class="descript">
                                        <a href="/${pro.rewrite}" title="${pro.pdt.name}" target="_blank">
                                                ${pro.pdt.name}
                                        </a>
                                    </div>
                                    <div class="price">
                                            <%--${pro.pdt.curPrice}--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- 商品栏 - end -->
                <!-- 加载更多 - 按钮 -->
                <div class="loading-btn-wrap text-center">
                    <a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supView.pkey}" class="m-btn round">
                        <!--  View More -->
                        <s:text name="read_more"/>
                    </a>
                </div>
            </div>
            <!-- main - end -->
        </div>
        <!-- 主内容 - flex-grow - end -->
    </div>
    <!-- 公司头部 - 含背景图 - end -->
    <%@ include file="/mobile/template/foot_menu.jsp" %>

</div>

<script type="text/javascript">
    $(function () {
        // 启用轮播1
        TouchSlide({
            slideCell: "#slide",
            titCell: ".hd ul",
            mainCell: ".bd ul",
            autoPlay: true,
            autoPage: true,
            interTime: 5000,
            effect: "leftLoop"
        });
    });
</script>
</body>
</html>
