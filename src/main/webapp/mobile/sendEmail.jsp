<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>

    </head>

    <body>
		    <%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <script type="text/javascript">
                $(function() {
                    user_obj.forgot_init()
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
                <form class="form_forgot">
                    <h3 class="title">
                        <!-- Reset Your Password -->
                        <s:text name="user.resetPWD" />
                    </h3>
                    <div class="clear">
                    </div>
                    <div id="error_register_box" class="error_note_box">
                    </div>
                    <div class="clear">
                    </div>
                    <div class="row">
                        <label for="Email">
                            <!-- Enter your email address -->
                            <s:text name="user.enterEmail" />
                        </label>
                        <input name="Email" id="Email" class="box_input" type="text" autocomplete="off"
                        size="40" maxlength="100" format="Email" notnull="">
                        <p class="on_error">
                            <!-- The Email address you entered is incorrect. -->
                            <s:text name="EmailEntered" />
                        </p>
                    </div>
                    <dl class="intro">
                        <dd>
                            <!-- Before we can reset your password, we require that you enter your email
                            address below. You will then receive an email with instructions to reset
                            your password. -->
                            <s:text name="user.sentReset" />
                        </dd>
                        <dd>
                            <!-- If you can't remember which email address you registered with or still
                            have problems signing in to your account please contact our Customer Services. -->
                            <s:text name="user.contactServices" />
                        </dd>
                    </dl>
                    <div class="row user_login_btn">
                        <button class="btn_global btn btn_fotgot BuyNowBgColor" onclick="junpforget()" type="button">
                            <!-- Send Email -->
                            <s:text name="user.sendEmail" />
                        </button>
                    </div>
                    <input type="hidden" name="do_action" value="user.forgot">
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
                        <span class="list_left">
                           <!--  Sign In -->
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

	    <script type="text/javascript">
	   
	    function junpforget(){
	    var status='';
		   			 if(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test($('#Email').val())==false){
						$('#Email').next().show();
					}else{
						$('#Email').next().hide();
						$.ajax({
							type:'POST',
							url:'/home/usr_UsrPurchase_useremail',
							data:{"useremailpwd":$('#Email').val()},
							dataType:'JSON',
							success:function(data){
								if(data.ret != 0){
									window.location.href = "/home/usr_UsrPurchase_sendemail?purchasepkey="+data.purchase+"&email="+$('#Email').val();
								}else{
									layer.open({content:getMessage(data.msg),skin: 'msg',time: 2});
								}
							}
						})
						
						
					}
	 	}
	    </script>
</body>
</html>
