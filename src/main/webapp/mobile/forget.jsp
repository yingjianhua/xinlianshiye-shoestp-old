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

	<div id="user">
		<div class="crumb clean">
			<a href="/home/usr_UsrPurchase_userIndex"><span class="icon_crumb_home"></span></a><em><i></i></em><a href="https://www.shoestp.com/account/">My Account</a>
		</div>
		<form id="infoForm" class="form_forgot">
			<h3 class="title"><!-- Reset Your Password --><s:text name="user.resetPWD" /></h3>
			<div class="clear">
			</div>
			<div id="error_register_box" class="error_note_box">
			</div>
			<div class="clear">
			</div>
				<input type="hidden" value="${email}" name="email"/>

			<div class="row">
				<label for="Password"><!-- New Password --><s:text name="user.newPWD" /></label>
				<input name="password" id="Password" class="box_input" autocomplete="off" type="password" size="40" notnull="">
				<p class="on_error">
					 Enter Your Password
					
				</p>
			</div>
			<div class="row">
				<label for="Password2"><!-- Confirm Password --><s:text name="rePWD" /></label>
				<input name="password2" id="Password2" class="box_input" autocomplete="off" type="password" size="40" notnull="">
				<p class="on_error">
					<!-- Passwords do not match. Please try again. -->
					<s:text name="PWDBeen" />
				</p>
			</div>
			<dl class="intro">
				<dd><!-- To reset your password, please enter your new password below. -->
				<s:text name="reset_password" /></dd>
			</dl>
			<div class="row user_login_btn">
				<button class="btn_global btn btn_reset BuyNowBgColor" id="toUpdate" type="button">Submit</button>
			</div>
			<input type="hidden" name="email" value="MjgwNDgwMzc4NEBxcS5jb20=">
			<input type="hidden" name="expiry" value="NDU2YWIzZGM2ZGIyNTk2">
			<input type="hidden" name="do_action" value="user.reset_password">
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
	<span class="list_left">Sign In</span>
	<span class="list_right"><em><i></i></em></span>
	</a>
	</li>
</ul>
<nav></nav><section class="font_col border_col copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号</section></footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>
    <script type="text/javascript">
    	var email='';
    	$("#toUpdate").on("click",function(){
    		var password = $("#password").val();
    		var password2 = $("#password2").val();
    		if(password != password2){
          layer.open({
            content: '密码不一致,请重新输入'
            ,skin: 'msg'
            ,time: 2
          });
    			return;
    		}

    		 $.ajax({
				url:'/home/usr_UsrPurchase_uda',
				type:'post',
				data:$("#infoForm").serialize(),
				dataType:'json',
				success:function(data){
					if(typeof(data.msg) == "undefined"){
						window.location.href = "/";
					}else{
            layer.open({
              content: data.msg
              ,skin: 'msg'
              ,time: 2
            });
					}
				}
			});
    	});
    </script>
</body>
</html>
