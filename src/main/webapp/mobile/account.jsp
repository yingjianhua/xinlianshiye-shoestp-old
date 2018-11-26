<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- saved from url=(0032)https://www.shoestp.com/account/ -->
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <script type="text/javascript">
            $(function() {
                user_obj.user_index();
            });
        </script>
    </head>

    <body>
        <%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
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
                        <s:text name="my_account" />
                    </a>
                </div>
                <!-- 原来位置 -->
                <!-- <div class="user_data clean">
                    <div class="name FontBgColor">
                        <strong>
                            ${usrPurchase.email}
                        </strong>
                    </div>
                    <div class="info ui_border_b">
                        <div class="address">
                            ${usrPurchase.address}
                        </div>
                    </div>
                </div> -->
                <div class="divide_8px">
                </div>
<%--                <div class="user_count clean ui_border_tb">
                    <a href="/home/usr_UsrCart_cartshopping" class="box cart">
                        <div class="num">
                             ${num}
                        </div>
                        <div class="link">
                            <s:text name="mobile.shopping_cart" />
                        </div>
                    </a>
                    <a href="/home/odr_OdrOrder_orders" class="box order">
                        <div class="num">
                             ${orders}
                        </div>
                        <div class="link">
                            <s:text name="all_order" />
                        </div>
                    </a>
                </div>--%>
                <div class="user_data clean">
                        <!-- <div class="name FontBgColor">
                            <strong>
                                ${usrPurchase.email}
                            </strong>
                        </div> -->
                        <div class="info ui_border_b">
                        <s:if test="usrPurchase.address == ''">
                           <div class="address">
                           	${purchaseLine.address}
                            </div>   
                        </s:if>
                        <s:if test="usrPurchase.address != ''">
                           <div class="address">
                           	${usrPurchase.address}
                            </div>   
                        </s:if>
                         
                        </div>
                    </div>
                <div class="divide_8px">
                </div>
                <div class="user_wallet">
                    <div class="rebate_apply" style="display:none">
                        <!-- Click the -->
                        <s:text name="Click_the"/>
                        <a href="javascript:void(0)" class="apply_btn" id="apply_btn">
                            <!-- APPLY -->
                            <s:text name="mobile.apply"/>
                        </a>
                        button, through the audit can become a distribution member.
                    </div>
                </div>
                <aside class="user_menu ui_border_b">
<%--                    <a href="/home/odr_OdrOrder_orders" class="orders">
                        <span class="ui_border_b">
                            <strong>
                                <s:text name="my_orders" />
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>--%>
                    <a href="/home/usr_UsrFavorites_myfavorite" class="favorite">
                        <span class="ui_border_b">
                            <strong>
                                <s:text name="my_fav" />
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
<!--                     <a href="#" class="coupon"> -->
<!--                         <span class="ui_border_b"> -->
<!--                             <strong> -->
<!--                                 My Coupons -->
<!--                             </strong> -->
<!--                             <em> -->
<!--                                 <i> -->
<!--                                 </i> -->
<!--                             </em> -->
<!--                         </span> -->
<!--                     </a> -->
                    <a href="/home/usr_UsrPurchaseLine_addmanagement" class="address">
                        <span class="ui_border_b">
                            <strong>
                                <s:text name="my_address_book" />
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
                    <a href="/home/usr_UsrConsult_listView" class="inquiry">
                        <span class="ui_border_b">
                            <strong>
                                <s:text name="my-inquiry-publish.View_Inquiry" />
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
                    <a href="/home/usr_UsrPurchase_usrSetting" class="setting">
                        <span class="ui_border_b">
                            <strong>
                                <s:text name="Global.Account_Settings" />
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
                                <s:text name="account_setting.Change_Password" />
                            </strong>
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
                </aside>
                <div class="user_button">
                    <a href="/home/usr_UsrPurchase_signOut" class="btn_global btn_sign_out FontColor FontBorderColor">
                        <s:text name="Global.Drop_Out" />
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
                    <a href="https://www.shoestp.com/account/" class="clean">
                        <span class="list_left">
                            <s:text name="sign_in" />
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
        <%-- <div align="center">

        </div> --%>
    </body>

</html>
