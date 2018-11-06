<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <script type="text/javascript">
            $(function() {
                user_obj.user_address()
            });
        </script>
    </head>

    <body>
		    <%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div id="user">
                <div class="address_box">
                    <div class="title ui_border_b">
                        Your Billing Address
                    </div>
                    <div class="txt" id="address_list">
                        <div class="address_row" data-aid="467" data-cid="222">
                            <div class="info" data-url="/account/address/?Form=1&amp;AId=467">
                                <a href="javascript:;" class="btn_edit edit_address_info">
                                    <em>
                                    </em>
                                </a>
                                <c:forEach items="${usrPurchaseLine}" varStatus="status">
                               	<c:if test="${usrPurchaseLine[status.index].addrsstype == 1}">


		                                <strong>
		                                    ${usrPurchaseLine[status.index].name} ${usrPurchaseLine[status.index].surname}<!--姓名 姓氏  -->
		                                </strong>
		                                <p>
		                                    ${usrPurchaseLine[status.index].address}  ${usrPurchaseLine[status.index].city} ${usrPurchaseLine[status.index].region} ${usrPurchaseLine[status.index].emailcode} ${usrPurchaseLine[status.index].country}<!--详细地址 城市 省 邮编 国家 -->
		                                </p>

		                                <p>
		                                    ${usrPurchaseLine[status.index].phonenumber}<!--电话  -->
		                                </p>

	                                </c:if>
	                             </c:forEach>
                            </div>
                        </div>
                        <div class="divide_20px">
                        </div>
                    </div>
                </div>
                <div class="address_box">
                    <div class="title ui_border_b">
                        Your Shipping Address
                    </div>
                    <div class="txt" id="address_list">
                        <div class="address_row" data-aid="466" data-cid="226">
                            <div class="info" data-url="/account/address/?Form=1&amp;AId=466">
                                <a href="javascript:;" class="btn_edit edit_address_info">
                                    <em>
                                    </em>
                                </a>

                              <c:forEach items="${usrPurchaseLine}" varStatus="status">
                               	<c:if test="${usrPurchaseLine[status.index].addrsstype == 0}">

		                                <strong>
		                                    ${usrPurchaseLine[status.index].name} ${usrPurchaseLine[status.index].surname}<!--姓名 姓氏  -->
		                                </strong>
		                                <p>
		                                    ${usrPurchaseLine[status.index].address}  ${usrPurchaseLine[status.index].city} ${usrPurchaseLine[status.index].region} ${usrPurchaseLine[status.index].emailcode} ${usrPurchaseLine[status.index].country}<!--详细地址 城市 省 邮编 国家 -->
		                                </p>

		                                <p>
		                                    ${usrPurchaseLine[status.index].phonenumber}<!--电话  -->
		                                </p>

	                                </c:if>
	                             </c:forEach>

                            </div>
                            <div class="button ui_border_tb">
                                <div class="fl selected">
                                    <a href="javascript:;" class="FontColor" data-aid="466">
                                        <i class="FontBgColor">
                                        </i>
                                        Selected
                                    </a>
                                </div>
                                <div class="fr menu">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="blank50">
                </div>
                <div id="address_btn" class="clean">
                    <a href="/home/usr_UsrPurchase_userIndex" class="btn btn_back">
                        <em>
                            <i>
                            </i>
                        </em>
                    </a>
                    <a href="https://www.shoestp.com/account/address/?Form=1" class="btn add_address FontBgColor">
                        <em>
                            +
                        </em>
                        Add a New Shipping Address
                    </a>
                </div>
            </div>
        </div>
        <footer>
            <div id="prolist_mask_footer">
            </div>
            <div class="footer_top clean">
            </div>
            <ul class="footer_list ui_border_b clean" style="display:none;">
                <li class="fl" style="border-right:1px solid #ddd;">
                    <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                        <span class="list_left">
                            Sign In
                        </span>
                        <span class="list_right">
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
                </li>
            </ul>
            <nav>
            </nav>
            <section class="font_col border_col copyright">
                Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1
                浙公网安备 33030402000493号
            </section>
        </footer>
        <%@ include file="/mobile/template/foot_menu.jsp" %>
        <div align="center">
            <script type="text/javascript" src="/home/static/themes/default/mobile/js/analytics(1).js">
            </script>
            <!-- Google Tag Manager (noscript) -->
            <noscript>
                &lt;iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KNPHSJ6"
                height="0" width="0" style="display:none;visibility:hidden"&gt;&lt;/iframe&gt;
            </noscript>
            <!-- End Google Tag Manager (noscript) -->
        </div>
        <script src="/home/static/themes/default/mobile/js/get_static_config.0.382.2.2.1344.14.2.22.9.8.2.6.285.js">
        </script>
        <script src="/home/static/themes/default/mobile/js/localization.en.0.043117e7a56a2e3ea008a802da2a0076_9f9568ee0491374470a5f78170eb7aed.js">
        </script>
        <div id="lc_invite_layer" style="text-align: left; display: none; z-index: 16777261;">
        </div>
        <div id="lc_overlay_layer" style="background-color: rgb(0, 0, 0); position: fixed; left: 0px; top: 0px; z-index: 16777260; display: none; width: 100%; height: 100%;">
        </div>
    </body>
</html>
