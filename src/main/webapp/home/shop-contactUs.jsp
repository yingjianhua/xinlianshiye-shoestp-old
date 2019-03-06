<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0039)https://www.shoestp.com/m/sien/product/ -->
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
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link href="./static/css/index.css" rel="stylesheet" type="text/css">
    <!-- 新引入的css -->
    <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
    <!-- 轮播插件 -->
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/jquery.SuperSlide.js"></script>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<%@ include file="/home/template/web-top.jsp" %>
<!-- 以下div为防止底部公用部分超出1200px后隐藏 -->
<c:if test="${supView.contactPageOn==1}">
    <div>${supView.contactPageDiy}</div>
</c:if>
<c:if test="${supView.contactPageOn!=1}">
<div id="main">
    <index-top></index-top>
        <%@ include file="/home/template/shop-header.jsp" %>
        <c:set var="symbolNoLogin" value="********"/>
    <div class="wide">
        <!-- 联系-信息1 -->
        <div class="enterprise-info-wrap">
            <h3 class="enterprise-info-title">
                <s:text name="contactInformation"/></h3>
            <div class="enterprise-info-content horizontal-arrange">
                <div class="department-logo-wrap">
                    <div class="department-logo pic_box">
                        <img src="${envConfig.imageBaseUrl}${supView.headPic}"><!-- 头像 -->
                        <span></span>
                    </div>
                    <div class="department-maneger"><c:if
                            test="${supView.contacts != 'null'}">${supView.contacts}</c:if></div><!-- 联系人名称 -->
                </div>
                <div class="grow-more">
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.department"/></h5><!-- 联系人部门 -->
                        <div class="text"><c:if test="${supView.department != 'null'}">${supView.department}</c:if>
                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.jobtitle"/></h5><!-- 联系人职位 -->
                        <div class="text"><c:if test="${supView.jobTitle != 'null'}">${supView.jobTitle }</c:if>
                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="purchase.company"/></h5><!-- 公司名称 -->
                        <div class="text">
                            <c:if test="${supView.name != 'null'}">${supView.showName}</c:if></div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.website"/></h5><!-- 公司网站标题 -->
                        <div class="text">
                            <c:if test="${supView.webSizeTitle != 'null'}">${supView.webSizeTitle}</c:if></div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="aboutShoestp"/><!-- 公司网址 -->
                        </h5>
                        <div class="text">
                            <c:if test="${supView.webSite != 'null'}">${supView.webSite}</c:if></div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="OperationalAddress"/></h5><!-- 公司地址Location -->
                        <div class="text">
                            <c:if test="${supView.location != 'null'}">${supView.location}</c:if></div>
                    </div>
                </div>

            </div>
        </div>
        <!-- 联系-信息1 - end -->

        <!-- 联系-信息2 -->
        <div class="enterprise-info-wrap mb60">
            <h3 class="enterprise-info-title clean">
                <s:text name="contactUs"/>
                <c:if test="${env.login == null}">
                    <div class="tip-info fr">
                        <s:text name="ViewAfterLogin"/>
                        <a class="SignInButton"><s:text name="sign_in"/></a>
                    </div>
                </c:if>
            </h3>
            <div class="enterprise-info-content divide-harf">
                <div class="divide-harf-item">
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="Telephone"/></h5><!-- telephone -->
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.telephone != 'null'}">${supView.telephone} </c:if>
                            </c:if>

                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.phone"/></h5><!-- phone -->
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.phone != 'null'}">${supView.phone}</c:if>
                            </c:if>

                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title"><s:text name="Fax"/></h5><!-- fax -->
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.fax != 'null'}">${supView.fax}</c:if>
                            </c:if>

                        </div>
                    </div>
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="address"/></h5><!-- companyAddr -->
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
                            </c:if>

                        </div>
                    </div>
                </div>

                <div class="divide-harf-item">
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="Global.Country"/></h5><!-- 公司所在国家 -->
                        <div class="text">
                                ${supView.countryName}</div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="Global.Province"/></h5><!-- 公司所在省份 -->
                        <div class="text">
                                ${supView.provinceName}</div>
                    </div>

                    <div class="info-item">
                        <h5 class="title"><s:text name="user.city"/></h5><!-- 公司所在城市 -->
                        <div class="text">
                            <c:if test="${supView.city != 'null'}">${supView.city}</c:if></div>
                    </div>

                </div>
            </div>
            <!-- 联系-信息2 - end -->
        </div>
        <index-bottom></index-bottom>
    </div>
    </c:if>
    <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
    ${supView.traceCode}
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>
    <script>
        new Vue({
            el:"#main"
        })
    </script>
</body>

</html>
