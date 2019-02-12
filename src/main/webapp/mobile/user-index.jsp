<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
</head>

<body>
<%@include file="/mobile/template/header.jsp" %>
<div class="wrapper">
    <script type="text/javascript">
        $(function () {
            user_obj.user_index();
        });
    </script>
    <div id="user">
        <div class="crumb clean">
            <a href="/">
                        <span class="icon_crumb_home">
                        </span>
            </a>
            <em>
                <i>
                </i>
            </em>
            <a href="/home/usr_UsrPurchase_userIndex">
                <!-- My Account -->
                <s:text name="my_account" />
                
            </a>
        </div>
        <div class="user_data clean">
            <div class="name FontBgColor">
                <strong>
                    ${accountInfo.email}
                </strong>
            </div>
            <div class="info ui_border_b">
                <div class="address">
                    ${accountInfo.address}
                </div>
            </div>
        </div>
        <div class="divide_8px">
        </div>
        <div class="user_count clean ui_border_tb">
            <a href="/home/usr_UsrCart_cart1" class="box cart">
                <div class="num">
                    ${accountInfo.catCount}
                </div>
                <div class="link">
                    <!-- Shopping Cart -->
                    <s:text name="mobile.shopping_cart"/>
                </div>
            </a>
            <a href="/home/odr_OdrOrder_orders" class="box order">
                <div class="num">
                    ${accountInfo.orderCount}
                </div>
                <div class="link">
                    <!-- All Orders -->
                    <s:text name="all_order"/>
                </div>
            </a>
        </div>
        <div class="divide_8px">
        </div>
        <div class="user_wallet">
            <div class="rebate_apply" style="display: none">
                <!-- Click the -->
                <s:text name="Click_the"/>
                <a href="javascript:void(0)" class="apply_btn" id="apply_btn">
                   <!--  APPLY -->
                    <s:text name="mobile.apply"/>
                </a>
                button, through the audit can become a distribution member.
            </div>
        </div>
        <aside class="user_menu ui_border_b">
            <a href="/home/usr_" class="orders">
                        <span class="ui_border_b">
                            <strong>
                                <!-- My Orders -->
                                <s:text name="my_orders"/>
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
            <a href="/home/usr_UsrFavorites_myfavorite" class="favorite">
                        <span class="ui_border_b">
                            <strong>
                                <!-- My Favorites -->
                                <s:text name="my_fav"/>
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
            <a href="/home/usr_UsrPurchaseLine_addmanagement" class="address">
                        <span class="ui_border_b">
                            <strong>
                                <!-- My Address Book -->
                                <s:text name="my_address_book"/>
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
            <a href="/home/usr_UsrPurchase_userIndex" class="setting">
                        <span class="ui_border_b">
                            <strong>
                               <!--  Account Settings -->
                                <s:text name="mySettings"/>
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
            <a href="/home/usr_UsrPurchase_toPassword" class="password">
                        <span class="ui_border_b">
                            <strong>
                                <!-- Password -->
                                <s:text name="account.password"/>
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
        </aside>
        <div class="user_button">
            <a href="/home/usr_UsrPurchase_signOut"
               class="btn_global btn_sign_out FontColor FontBorderColor">
                <!-- Sign Out -->
                <s:text name="sign_out"/>
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
            <a href="/home/usr_UsrPurchase_sign" class="clean">
                        <span class="list_left">
                            <!-- Sign In -->
                            <s:text name="sign_in"/>
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
        Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
        <%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
    </section>
</footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>
<script src="/mobile/static/js/get_static_config.0.379.2.2.1342.14.2.22.9.8.2.6.285.js">
</script>
<script src="/mobile/static/js/localization.en.0.043117e7a56a2e3ea008a802da2a0076_9f9568ee0491374470a5f78170eb7aed.js">
</script>
<div id="lc_invite_layer" style="text-align: left; display: none; z-index: 16777261;">
</div>
<div id="lc_overlay_layer"
     style="background-color: rgb(0, 0, 0); position: fixed; left: 0px; top: 0px; z-index: 16777260; display: none; width: 100%; height: 100%;">
</div>
</body>
</html>
