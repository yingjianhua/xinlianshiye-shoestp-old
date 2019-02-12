<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<a href="/home/usr_UsrPurchase_userIndex"><span class="icon_crumb_home"></span></a><em><i></i></em><a href="/home/usr_UsrPurchase_userIndex"><s:text name="Global.My_Account"/></a>
		</div>
		<form id="infoForm" class="form_forgot">
			<h3 class="title"><s:text name="user.resetPWD" /></h3>
			<div class="clear">
			</div>
			<div id="error_register_box" class="error_note_box">
			</div>
			<div class="clear">
			</div>
			              <input type="hidden" value="${purchasepkey}" name="purchasepkey"/>
                <input type="hidden" value="${randomNum}" name="randomNum"/>

			<div class="row">
				<label for="Password"><s:text name="Global.New_Password"/></label>
				<input name="password" id="Password" class="box_input" autocomplete="off" type="password" size="40" notnull="">
				<p class="on_error">
					<s:text name="Enter_Your_Password"/>
				</p>
			</div>
			<div class="row">
				<label for="Password2"><s:text name="Global.Confirm_Password"/></label>
				<input name="password2" id="Password2" class="box_input" autocomplete="off" type="password" size="40" notnull="">
				<!-- <p class="on_error">
					Passwords do not match. Please try again.
				</p> -->
			</div>
			<dl class="intro">
				<dd><s:text name="forgetPWD.fill_In_Completely"/></dd>
			</dl>
			<div class="row user_login_btn">
				<button class="btn_global btn btn_reset BuyNowBgColor" id="toUpdate" type="button"><s:text name="Global.Submit"/></button>
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
	<span class="list_left"><s:text name="web-top.LogIn"/></span>
	<span class="list_right"><em><i></i></em></span>
	</a>
	</li>
</ul>
<nav></nav><section class="font_col border_col copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
	<%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
</section></footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>
    <script type="text/javascript">
       window.onload = function() {
			var result= '${returnresult}';
			console.log(result);	
			if(result!=null && result != ""){
				window.location.href="/home/usr_UsrPurchase_sendEmail"; 
			}
	
		};
    	var email='';
    	$("#toUpdate").on("click",function(){
    		var password = $("#Password").val();
    		var password2 = $("#Password2").val();
    		if(password != password2){
          layer.open({
            content: "<s:text name='Global.Inconsistent'/>"
            ,skin: 'msg'
            ,time: 2
          });
    			return;
    		}else if(password==''||password==null ||password2==''||password2==null){
    			 layer.open({
    		            content: "<s:text name='NotNullTip_PWD'/>"
    		            ,skin: 'msg'
    		            ,time: 2
    		          });
    			return;
    		}else if(password.length>20||password.length<6||password2.length>20||password2.length<6){
    			layer.open({
    	            content: "<s:text name='Length_Limit'/>"
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
              content: getMessage(data.msg)
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
