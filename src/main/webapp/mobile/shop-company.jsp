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
<div class="page page-enterprise-profile flexible flex-column fill">
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
        <%@ include file="template/shop-nav.jsp" %>
        <!-- 主内容 -->
        <c:set var="symbolNoLogin" value="********"/>
        <div class="flex-grow">
            <div class="main">
                <div class="clean">
                </div>
                <!-- 块级信息1 - 联系信息 -->
                <div class="info-group">
                    <div class="info-group-header">
                        <!--  Company information -->
                        <s:text name="Companys"/>
                    </div>
                    <div class="info-group-content bg-white">
                        <!-- 信息行 -->
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Company Name -->
                                <s:text name="purchase.company"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                ${supView.name}
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Business typ -->
                                <s:text name="category_business"/>
                            </div>
                            <div class="info-item-content flex-grow">${supView.businessTyp}
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Location -->
                                <s:text name="supplier.location"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                <c:if test="${env.login == null}">
                                    ${symbolNoLogin}
                                </c:if>
                                <c:if test="${env.login != null}">
                                    <c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
                                </c:if>
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Main products -->
                                <s:text name="supt7"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                <c:if test="${env.login == null}">
                                    ${symbolNoLogin}
                                </c:if>
                                <c:if test="${env.login != null}">
                                    <c:if test="${supView.mainProd != 'null'}">${supView.mainProd}</c:if>
                                </c:if>
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Developer Number -->
                                <s:text name="supplier.developer"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                <c:if test="${env.login == null}">
                                    ${symbolNoLogin}
                                </c:if>
                                <c:if test="${env.login != null}">
                                    <c:if test="${supView.developer != 'null'}">${supView.developer}</c:if>
                                </c:if>
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Total employees -->
                                <s:text name="supplier.totalemployees"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                <c:if test="${env.login == null}">
                                    ${symbolNoLogin}
                                </c:if>
                                <c:if test="${env.login != null}">
                                    <c:if test="${supView.totalEmployees != 'null'}">${supView.totalEmployees}</c:if>
                                </c:if>
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Established time -->
                                <s:text name="supplier.companyestablishtime"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                ${supView.companyEstablishTime}
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Top 3 Markets -->
                                <s:text name="supplier.top3markets"/>
                            </div>
                            <div class="info-item-content flex-grow">${supView.top3Markets}
                            </div>
                        </div>
                        <div class="info-item flexible">
                            <div class="info-item-title">
                                <!-- Materials selected -->
                                <s:text name="supplier.materials"/>
                            </div>
                            <div class="info-item-content flex-grow">${supView.materials}
                            </div>
                        </div>
                        <a name="data">
                        </a>
                        <!-- 认证内容 - 列表 -->
                        <ul class="authentication-list">
                            <c:if test="${supView.isAuth == 1}">
                                <li class="item">
                                    <h3 class="title">
                                        <img class="icon" src="/mobile/static/images/base-images/defence-safe.png"
                                             alt="">
                                        <span class="text">
                                               <!--  Onsite Check -->
                                                <s:text name="On-siteInspection"/>
                                            </span>
                                    </h3>
                                    <div class="content">
                                        <!-- The supplier company site has been checked by SHOETP.COM staff and confirmed
                                        its authenticity. -->
                                        <s:text name="On-siteInspection1"/>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${supView.isAuth == 1}">
                                <li class="item">
                                    <h3 class="title">
                                        <img class="icon" src="/mobile/static/images/base-images/certification.png"
                                             alt="">
                                        <span class="text">
                                                <!-- Certification -->
                                                <s:text name="Certification"/>
                                            </span>
                                    </h3>
                                    <div class="content">
                                        <!-- The supplier information has been checked and confirmed by SHOETP.COM
                                        staff. -->
                                        <s:text name="On-siteInspection2"/>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${supView.authAge != null && supView.authAge != 0 && supView.authAge != ''}">
                                <li class="item">
                                    <h3 class="title flexible">
                                        <!-- 自制年限图标 -->
                                        <div class="icon icon-years-limit-group icon-small">
                                            <img src="/mobile/static/images/base-images/doller_pic_o.png" alt="">
                                            <span class="years-num">
                                                    ${supView.authAge}
                                            </span>
                                            <span class="years-unit">
	                                                    <!-- YRS -->
	                                                    <s:text name="yes"/>
	                                                </span>
                                        </div>
                                        <span class="text">
	                                                <!-- Age limit -->
	                                                <s:text name="AgeLimit"/>
	                                            </span>
                                    </h3>
                                    <div class="content">
                                        <!-- The supplier is premium account. The premium account will show years and
                                        marks on the website and update every year. -->
                                        <s:text name="account_will"/>
                                    </div>
                                </li>
                            </c:if>

                        </ul>
                        <!-- 认证内容 - 列表 - end -->
                    </div>
                </div>
                <!-- 块级信息1 - 联系信息 - end -->
                <!-- 块级信息2 - 联系我们 -->
                <div class="info-group">
                    <div class="info-group-header">
                        <!-- Authentication information -->
                        <s:text name="authenticationInformation"/>
                    </div>
                    <div class="info-group-content bg-white">
                        <!-- 信息行 -->
                        <div class="info-item flexible">
                            <div class="info-item-title long-title">
                                <!-- On-site inspection -->
                                <s:text name="On-siteInspection"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                <!-- The supplier's company site has been checked by staff at shoestp com to
                                verify site operations -->
                                <s:text name="On-siteInspection1"/>
                            </div>
                        </div>
                        <!-- 信息行 -->
                        <div class="info-item flexible">
                            <div class="info-item-title long-title">
                                <!-- Address -->
                                <s:text name="review.address"/>
                            </div>
                            <div class="info-item-content flex-grow">
                                <c:if test="${env.login == null}">
                                    ${symbolNoLogin}
                                </c:if>
                                <c:if test="${env.login != null}">
                                    <c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
                                </c:if>
                                <!--  Rm 3009, 30/F, Jinying Mansion, No 316 Huanshi Middle Rd, Yuexiu Dist
                                 Guangzhou, Guangdong, China(Mainland) -->
                            </div>
                        </div>
                        <div class="cut-line">
                        </div>
                    </div>
                    <!-- license - 新增 -->
                    <div class="info-group-header license-header bg-white">
                        <!-- Business license -->
                        <s:text name="businessLicense"/>
                    </div>
                    <div class="info-group-content bg-white">
                        <c:if test="${env.login == null}">
                            ${symbolNoLogin}
                        </c:if>
                        <c:if test="${env.login != null}">
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!-- Registration NO -->
                                    <s:text name="RegistrationNo"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.creditCode}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!--  Company Name -->
                                    <s:text name="purchase.company"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.name}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!-- Date of issue -->
                                    <s:text name="issue"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.authTime}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!-- Registered Capital -->
                                    <s:text name="supplier.registeredcapital"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                    RMB ${supView.registeredCapital}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!-- Country/Territory -->
                                    <s:text name="mobile.country"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.country }
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!-- Year Established -->
                                    <s:text name="YearEstablished"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.companyEstablishTime}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    CEO

                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.entity}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!--  Registered address -->
                                    <s:text name="registeredAddress"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.companyAddr}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <div class="info-item flexible">
                                <div class="info-item-title long-title">
                                    <!-- Legal Form -->
                                    <s:text name="LegalForm"/>
                                </div>
                                <div class="info-item-content flex-grow">
                                        ${supView.companyType}
                                </div>
                            </div>
                            <!-- 信息行 -->
                            <!-- <div class="info-item flexible">
                            <div class="info-item-title long-title">
                            Issuing Authority
                            </div>
                            <div class="info-item-content flex-grow">
                            tai'an City Daiyue District Administration for Industry and commerce
                            </div> -->
                        </c:if>

                    </div>
                </div>
                <!-- license - 新增 - end -->
            </div>
            <!-- 块级信息2 - 联系我们 - end -->
            <!-- 块级信息3 - license -->
            <div class="info-group">
            </div>
            <!-- 块级信息3 - license - end -->
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

</div>
</body>
</html>
