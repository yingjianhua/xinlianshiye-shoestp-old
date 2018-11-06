<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
 <head>
      <%@include file="/mobile/template/style_import.jsp" %>

      <style>
        .psdModal{
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            z-index: 999;
            width: 70%;
           	height:1.5rem;
           	line-height:1.5rem;
           	opacity:0.8;
           	padding:1rem;
           	text-align:center;
            margin: 0 auto;
            transform: translate(-50%, -50%);
            font-size: 0.8125rem;
            border-radius: 0.1875rem;
            background: #666;
            color:#FFF;
        }
 		 </style>
  </head>

  <body>
		<%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
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
                   <!--  My Account -->
                    <s:text name="my_account" />
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="javascript:;">
                   <!--  Password -->
                    <s:text name="account.password" />
                </a>
            </div>
            <div id="user" class="user_login">
                <div class="blank10">
                </div>
                <form  method="post" class="user_login_form" id="updPwd">
                    <div class="rows">
                        <label class="field">
                            <!-- Current Password -->
                            <s:text name="user.curPWD" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="password" class="box_input" name="oldPwd" id="oldPwd"   autocomplete="off"
                            placeholder="<s:text name='Current_Password' />" data-field="Current Password" notnull="">
                            <p class="error">
                            <input type="hidden"  name="bean.password"   id="pwd"  />
                            </p>
                        </div>
                    </div>
                    <div class="rows">
                        <label class="field">
                            <!-- New Password -->
                            <s:text name="user.newPWD" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="password" class="box_input" name="newPwd" id="newPwd" autocomplete="off"
                            placeholder="<s:text name='New_Password' />" data-field=" New Password" notnull="">
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    <div class="rows">
                        <label class="field">
                            <!-- Confirm Password -->
                            <s:text name="user.ConfirmPWD" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="password" class="box_input"  id="comfirmPwd" autocomplete="off"
                            placeholder="<s:text name='Confirm_Password' />" data-field="Confirm Password" notnull="">
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    <div class="user_login_btn">
                        <div class="btn_global btn_sign_up btn_submit BuyNowBgColor" onclick="updPwd()">
                            <!-- Update Password -->
                            <s:text name="user.updatePWD" />
                        </div>
                        <a href="javascript:history.go(-1);" class="btn_global btn btn_back" id="btn_back">
                            <!-- Back -->
                            <s:text name="user.back" />
                        </a>
                        
                    </div>
                    <div class="blank25">
                    </div>
                    <input type="hidden" name="ajax_submit" value="1">
                    <input type="hidden" name="do_action" value="user.mod_password">
                </form>
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
                            <!-- Sign In -->
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
        <div align="center">
            <!-- Google Tag Manager (noscript) -->
            <noscript>
                &lt;iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KNPHSJ6"
                height="0" width="0" style="display:none;visibility:hidden"&gt;&lt;/iframe&gt;
            </noscript>
            <!-- End Google Tag Manager (noscript) -->
        </div>

      <!--   <iframe src="./minIndex/pixel.html" style="display: none;">
        </iframe> -->
        <div class="psdModal" id="tip">
   		</div>
        <script type="text/javascript">
      function updPwd(){
    	  if($("#oldPwd").val()==''){
    		$("#tip").html(lang_obj.signIn.Old_Password_Empty);
			$('.psdModal').show().delay(1500).fadeOut();
    		return false;
    	  }
    	  if($("#newPwd").val()==''){
    		$("#tip").html(lang_obj.signIn.New_Password_Empty);
			$('.psdModal').show().delay(1500).fadeOut();
    		return false;
    	  }
    	  if(checkLength($("#newPwd").val())){
  			$("#tip").html('<s:text name="pwd_length"/>');
			$('.psdModal').show().delay(1500).fadeOut();
  			return false;
  			}
    	  if(!isNaN($("#newPwd").val())){
     		 $("#tip").html('<s:text name="pwd_length"/>');
   			$('.psdModal').show().delay(1500).fadeOut();
     			return false;
     		}
    	  if($("#comfirmPwd").val()==''){
      		$("#tip").html(lang_obj.signIn.Confirm_Password_Empty);
			$('.psdModal').show().delay(1500).fadeOut();
      		return false;
      	  }
    	  if($("#newPwd").val()!=$("#comfirmPwd").val()){
      		$("#tip").html(lang_obj.manage.account.password_tips);
			$('.psdModal').show().delay(1500).fadeOut();
      		return false;
      	  }
        $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/usr_UsrPurchase_updPwd',
			dataType:'json',
			data:$("#updPwd").serialize(),
			success:function(data){
				if(data.ret==1){
				$("#oldPwd").val("");
				$("#newPwd").val("");
				$("#comfirmPwd").val("");
				$("#tip").html(lang_obj.addressfrom.Successfully_modified);
				$('.psdModal').show().delay(1500).fadeOut();
				setTimeout(function () {window.location="/home/usr_UsrPurchase_userIndex"; },1000);    		
			  
				}else{
				$("#oldPwd").val("");
				$("#newPwd").val("");
				$("#comfirmPwd").val("");
				$("#tip").html(getMessage(data.msg));
				$('.psdModal').show().delay(1500).fadeOut();
				}
			  }
           })
        }
      function checkLength(str){
  		if(str.length<8||str.length>15){
  			return true;
  		}else{
  			return false;
  		}
  		}
        </script>
    </body>
</html>
