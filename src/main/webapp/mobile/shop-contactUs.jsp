<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <div class="page page-enterprise-contact flexible flex-column fill">
            <!-- 顶上 - 固定的头部条 -->
             <%@include file="/mobile/template/header.jsp"%>
            <!-- 顶上 - 固定的头部条 -end -->
            <!-- 公司头部 - 含背景图 -->
            <div class="scroll flex-grow">
                <div class="enterprise-top-header flexible flex-c-center">
                    <!-- 公司logo -->
                    <div class="logo">
                        <img src="<c:if test='${not empty supView.logo}'>${envConfig.imageBaseUrl}${supView.logo}</c:if><c:if test='${empty supView.logo}'>/home/static/images/headimg.jpg</c:if>" alt="logo">
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
                            <!-- 自制年限图标 -->
                            <c:if test="${supView.isAuth == 1}">
                            <a href="#" target="_blank">
                                <img class="icon" src="/home/static/themes/default/mobile/images/defence-safe-o.png" alt="defence-safe">
                            </a>
                            <a href="#" target="_blank">
                                <img class="icon" src="/home/static/themes/default/mobile/images/certification-o.png" alt="certification">
                            </a>
                            </c:if>
                        </div>
                    </div>
                </div>
                <!-- 顶部导航条 -->
                <div class="nav-bar bg-white">
                    <ul class="flexible text-center">
                        <li class="">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supView.pkey}">
                                <!-- HOME -->
                                <s:text name="home" />
                            </a>
                        </li>
                        <li class="">
                            <a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supView.pkey}">
                                <!-- PRODUCTS -->
                                <s:text name="order_line.spec" />
                            </a>
                        </li>
                        <c:if test="${supView.prmAuthrity == true}">
	                        <li class="">
	                            <a href="/home/usr_UsrSupplier_gtSupGroup?pkey=${supView.pkey}">
	                                <!-- JOINT BUY -->
	                                <s:text name="group_order.activity" />
	                            </a>
	                        </li>
                        </c:if>
                        <li class="">
                            <a href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supView.pkey}">
                                <!-- PROFILE -->
                                <s:text name="freight_line.brief" />
                            </a>
                        </li>
                        <li class="active">
                            <a href="/home/usr_UsrSupplier_gtSupContact?pkey=${supView.pkey}">
                                <!-- CONTACT -->
                                <s:text name="contactmenu" />
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- 顶部导航条 - end -->
                <!-- 主内容 -->
                <div class="flex-grow">
                    <div class="main">
                        <!-- 块级信息1 - 联系信息 -->
                        <div class="info-group">
                            <div class="info-group-header">
                                <!-- Contact Information -->
                                <s:text name="Contact_Information" />
                            </div>
                            <div class="info-group-content bg-white">
                                <!-- 信息行 -->
                                <div class="info-item flexible">
                                    <div class="info-item-title"><!-- Name --><s:text name="review.name" /></div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.contacts}
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title">Department<s:text name="supplier.department" /></div>
                                    <div class="info-item-content flex-grow">${supView.department}
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title"><!-- Job Title --><s:text name="supplier.jobtitle" /></div>
                                    <div class="info-item-content flex-grow">${supView.jobTitle }
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title"><!-- Company Name --><s:text name="purchase.company" /></div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.name}
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title"><!-- Website --><s:text name="supplier.website" /></div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.website}
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title"><!-- Address --><s:text name="review.address" /></div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.companyAddr}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="symbolNoLogin" value="********"/>
                        <!-- 块级信息1 - 联系信息 - end -->
                        <!-- 块级信息2 - 联系我们 -->
                        <div class="info-group">
                            <div class="info-group-header">
                                <!-- Contact Us -->
                                <s:text name="contactUs" />
                            </div>
                            <div class="info-group-content bg-white">
                                <!-- 信息行 -->
                                <div class="info-item flexible">
                                    <div class="info-item-title">
                                        <!-- Telephone -->
                                        <s:text name="Telephone" />
                                    </div>
                                    <div class="info-item-content flex-grow">
                                        <c:if test="${env.login == null}">
						              		${symbolNoLogin}
						              	</c:if>
						              	<c:if test="${env.login != null}">
						              		<c:if test="${supView.telephone != 'null'}">${supView.telephone} </c:if>
						              	</c:if>
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title">
                                        <!-- Mobile phone -->
                                        <s:text name="purchase.telphone" />
                                    </div>
                                    <div class="info-item-content flex-grow">
                                        <c:if test="${env.login == null}">
						              		${symbolNoLogin}
						              	</c:if>
						              	<c:if test="${env.login != null}">
						              		<c:if test="${supView.phone != 'null'}">${supView.phone}</c:if>
						              	</c:if>
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title">
                                        <!-- Fax -->
                                        <s:text name="supplier.fax" />
                                    </div>
                                    <div class="info-item-content flex-grow">
                                       <c:if test="${env.login == null}">
						              		${symbolNoLogin}
						              	</c:if>
						              	<c:if test="${env.login != null}">
						              		<c:if test="${supView.fax != 'null'}">${supView.fax}</c:if>
						              	</c:if>
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title">
                                        <!-- Address -->
                                        <s:text name="review.address" />
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
                                        <!-- Country/Region -->
                                        <s:text name="mobile.country" />
                                    </div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.country}
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title">
                                        <!-- Province/state -->
                                        <s:text name="review.state" />
                                    </div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.province}
                                    </div>
                                </div>
                                <div class="info-item flexible">
                                    <div class="info-item-title">
                                        <!-- City -->
                                        <s:text name="user.city" />
                                    </div>
                                    <div class="info-item-content flex-grow">
                                        ${supView.city}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 块级信息2 - 联系我们 - end -->
                        <!-- 未登录时的提示信息 -->
                        <div class="notLogin-tip-info-group text-center">
                        	<c:if test="${empty env.login}">
	                            <p class="tip-text">
	                                See all the information after you
	                                <a href="/home/usr_UsrPurchase_userIndex">
	                                    <!-- log in -->
	                                    <s:text name="sign_in" />
	                                </a>
	                            </p>
                            </c:if>
                            <c:if test="${not empty env.login}">
	                            <a href="/home/pdt_PdtProduct">
	                                <img src="/home/static/themes/default/mobile/images/btn-begin-order.png" alt="">
	                            </a>
                            </c:if>
                        </div>
                        <!-- 未登录时的提示信息 - end -->
                    </div>
                    <!-- main - end -->
                </div>
                <!-- 主内容 - flex-grow - end -->
            </div>
            <!-- 公司头部 - 含背景图 - end -->
            <!-- 底部 -->
            	<%@ include file="/mobile/template/foot_menu.jsp" %>

                <!-- Google Tag Manager (noscript) -->
                <noscript>
                    &lt;iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KNPHSJ6"
                    height="0" width="0" style="display:none;visibility:hidden"&gt;&lt;/iframe&gt;
                </noscript>
                <!-- End Google Tag Manager (noscript) -->
            </div>
        </div>
    </body>
</html>
