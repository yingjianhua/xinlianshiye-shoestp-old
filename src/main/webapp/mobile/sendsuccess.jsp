<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html><!-- saved from url=(0060)https://www.shoestp.com/account/forgot.html?forgot_success=1 -->
<html lang="us">
<head>
		<%@include file="/mobile/template/style_import.jsp" %>
		<script type="text/javascript">$(function(){user_obj.forgot_init()});</script>
</head>

<body>
<%@include file="/mobile/template/header.jsp" %>
<div class="wrapper">

	<div id="user">
		<div class="crumb clean">
			<a href="/"><span class="icon_crumb_home"></span></a><em><i></i></em><a href="/home/usr_UsrPurchase_userIndex">My Account</a>
		</div>
		<form class="form_forgot">
			<h3 class="title"><!-- Reset Your Password --><s:text name="user.resetPWD" /></h3>
			<div class="clear">
			</div>
			<div id="error_register_box" class="error_note_box">
			</div>
			<div class="clear">
			</div>
			<dl class="intro">
				<dd><!-- We have sent an email to the address you have on file with us. Please follow the instructions in this email to reset your password. -->
				<s:text name="user.sentEmail" /></dd>
			</dl>
			<dl class="intro">
				<dt><!-- Haven't received the email? --><s:text name="user.receivedEmail" /></dt>
				<dd><!-- Check your bulk and junk email folders. If you still can't find the password reset email, please call our Customer Care Team. Thank you! --><s:text name="user.checkEmail" /></dd>
			</dl>
			<div class="row user_login_btn">
				<a href="/" class="btn_global btn BuyNowBgColor"><!-- Continue Shopping --><s:text name="mobile.continue_shop" /></a>
			</div>
		</form>
		<div class="blank20">
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
	<span class="list_left"><s:text name="sign_in" /></span>
	<span class="list_right"><em><i></i></em></span>
	</a>
	</li>
</ul>
<nav></nav><section class="font_col border_col copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
	<%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
</section></footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>
</body>
</html>
