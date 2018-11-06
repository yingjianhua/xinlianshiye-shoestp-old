<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<style>
    .boo {
        font-weight: bold;
    }
</style>
<div id="lib_user_menu">
    <h3 class="title"><s:text name="Global.My_Account"/></h3>
    <ul id="navigationMenu">
        <li>
            <a href="/home/usr_UsrPurchase_userIndex"><s:text name="lib-user-menu.BasicInformation"/></a>
        </li>
        <li>
            <a href="/home/odr_OdrOrder_orders"><s:text name="Global.My_Order"/></a>
        </li>
        <LI>
            <A HREF="/home/pdt_PdtProduct_selectComment"><s:text name="Global.My_Comment"/></A>
        </LI>
        <li>
            <a href="/home/usr_UsrFavorites_myfavorite"><s:text name="Global.My_Favorites"/></a>
        </li><!--
		<li>
			<a href="/home/usr_UsrPurchase_coupon">My Coupons</a>
		</li> -->
        <li>
            <a href="/home/usr_UsrPurchaseLine_addmanagement"><s:text name="Global.My_Address_Book"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrConsult_listView"><s:text name=""/><s:text name="my-inquiry-publish.View_Inquiry"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrPurchase_usrSetting"><s:text name="Global.Account_Settings"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrMessages_inbox"><s:text name="Global.My_Inbox"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrMessages_systemMessage"><s:text name="Global.System_Information"/></a>
        </li>
        <li>
            <a href="/home/usr_UsrPurchase_signOut"><s:text name="Global.Drop_Out"/></a>
        </li>
    </ul>
</div>
<script type="text/javascript">
    mark();

    function mark() {
        $("#navigationMenu li a").each(function () {
            var url = $(this).attr("href")
            if (url == String(window.location.pathname)) {
                $(this).css("font-weight", "bold").css("color", "#f60");
            }
        });
    }
</script>