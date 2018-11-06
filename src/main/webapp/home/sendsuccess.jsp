<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><!-- saved from url=(0060)https://www.shoestp.com/account/forgot.html?forgot_success=1 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<!-- Global site tag (gtag.js) - Google Analytics -->
<meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
<meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
<link href="./static/css/global.css" rel="stylesheet" type="text/css">
<link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
<link href="./static/css/user.css" rel="stylesheet" type="text/css">
<link href="./static/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./static/animate.min.css">
<link rel="stylesheet" href="./static/swiper.min.css" type="text/css">
<link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/user.js"></script>
	<script type="text/javascript" src="./static/js/global.js"></script>
	<script type="text/javascript" src="./static/js/global(1).js"></script>
<script type="text/javascript" src="./static/js/lang/${env.curLanguage }.js"></script>
<script type="text/javascript" src="./static/js/moment-with-locales.min.js"></script>

</head>
<body>
<div id="customer">
	<style>
body{ background-image:none;}
	</style>
	<div class="header" style="overflow:visible;">
		<div class="logo fl pic_box">
			<a href="/"><img src="./static/images/85bb6f7a6c.png"><span></span></a>
		</div>
		<a href="/" class="home fr"><s:text name="user.returnHome"/></a>
		<div class="fr account_lang">
				<dl>
					<dt class="">
						<span> <!--                -->
							<s:text name="Global.Language" />:
							<s:iterator value="env.languages" var="language">
		                    	<s:if test="shortName==env.curLanguage">
									<s:if test='#language.shortName=="zh_TW"'>繁体中文</s:if>
		                    		<s:elseif test='#language.shortName=="de"'>Deutsch</s:elseif>
		                    		<s:elseif test='#language.shortName=="en"'>English</s:elseif>
		                    		<s:elseif test='#language.shortName=="es"'>Español</s:elseif>
		                    		<s:elseif test='#language.shortName=="fr"'>Français</s:elseif>
		                    		<s:elseif test='#language.shortName=="ja"'>日本語</s:elseif>
		                    		<s:elseif test='#language.shortName=="pt"'>Português</s:elseif>
		                    		<s:elseif test='#language.shortName=="ro"'>românesc</s:elseif>
		                    		<s:elseif test='#language.shortName=="ru"'>русский</s:elseif>
		                    		<s:elseif test='#language.shortName=="zh_CN"'>简体中文</s:elseif>
		                    		<s:elseif test='#language.shortName=="hu"'>magyar</s:elseif>
		                    	</s:if>
							</s:iterator>
						</span>
					</dt>
					<dd class="language lang">
						<s:iterator value="env.languages" var="language">
	                    	<s:if test="isEnabled">
		                    	<a rel="nofollow" href="javascript:void(0);" lang="${language.shortName}">
		                    		<s:if test='#language.shortName=="zh_TW"'>繁体中文</s:if>
		                    		<s:elseif test='#language.shortName=="de"'>Deutsch</s:elseif>
		                    		<s:elseif test='#language.shortName=="en"'>English</s:elseif>
		                    		<s:elseif test='#language.shortName=="es"'>Español</s:elseif>
		                    		<s:elseif test='#language.shortName=="fr"'>Français</s:elseif>
		                    		<s:elseif test='#language.shortName=="ja"'>日本語</s:elseif>
		                    		<s:elseif test='#language.shortName=="pt"'>Português</s:elseif>
		                    		<s:elseif test='#language.shortName=="ro"'>românesc</s:elseif>
		                    		<s:elseif test='#language.shortName=="ru"'>русский</s:elseif>
		                    		<s:elseif test='#language.shortName=="zh_CN"'>简体中文</s:elseif>
		                    		<s:elseif test='#language.shortName=="hu"'>magyar</s:elseif>
		                    	</a>
	                    	</s:if>
						</s:iterator>
						<form action="" method="get" hidden>
							<input name="request_locale">
						</form>
					</dd>
				</dl>
				<script type="text/javascript">
                    $(document).ready(function () {
                    	moment.locale('${env.curLanguage}');
   						user_obj.sign_in_init();
                    	$(".header .language.lang a").on("click", function() {
                    		$.ajax({
                    			url:"/home/plt_PltConfig_changeLanguage",
                    			type:"GET",
                    			dataType:"json",
                    			data:"request_locale="+$(this).attr("lang"),
                    			success:function(data) {
                    				if(data.ret==1)
                    					location.reload();
                    			}
                    		})
                    	})
                    	
                    });
                </script>
			<%-- <div id="pop_lang_currency" class="hide">
				<button class="shopbox_close"><span>Ã</span></button>
				<div class="shopbox_wrap">
					<div class="shopbox_skin pop_skin">
						<h4>Language</h4>
						<ul class="lang_item">
							<li data-lang="en" class="current">English</li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
							<li data-lang=""></li>
						</ul>
						<div class="blank15">
						</div>
					</div>
					<div class="shopbox_bot">
						<div class="pop_currency">
							<span class="pop_currency_title">Choose Currency:</span>
							<a rel="nofollow" class="btn_currency" href="javascript:;" data-currency="USD"><img src="./static/images/53092c531f.jpg" alt="USD">USD<em></em></a>
							<ul class="pop_currency_menu">
								<li><a rel="nofollow" href="javascript:;" data="USD"><img src="./static/images/53092c531f.jpg" alt="USD">USD</a></li>
							</ul>
						</div>
						<a class="btn btn_success btn_save FontBgColor">Save</a>
						<a class="btn btn_cancel">Cancel</a>
					</div>
				</div>
			</div> --%>
		</div>
	</div>
	<div id="signup">
		<form class="register fl">
			<h3 class="title"><s:text name="user.resetPWD"/></h3>
			<div class="clear">
			</div>
			<div id="error_register_box" class="error_note_box">
			</div>
			<div class="clear">
			</div>
			<dl class="intro">
				<dd><s:text name="user.sentEmail"/></dd>
			</dl>
			<dl class="intro">
				<dt><s:text name="user.receivedEmail"/></dt>
				<dd><s:text name="user.checkEmail"/></dd>
			</dl>
			<a href="/" class="signbtn signup NavBgColor"><s:text name="user.continueShopping"/></a>
		</form>
		<div class="info fr">
			<div class="box member">
				<p>
					<s:text name="user.already"/>
				</p>
				<div class="sign_btn">
					<a href="javascript:;" class="SignInButton signinbtn"><s:text name="user.signInNow"/></a>
				</div>
				<p class="forgot">
					<a href="/home/usr_UsrPurchase_sendEmail" class="FontColor"><s:text name="user.forgotPWD"/></a>
				</p>
				<script type="text/javascript" src="./static/js/facebook.js"></script>
				<div id="fb_button" scope="public_profile, email" onclick="checkLoginState();" class="fb_button" appid="718848724966585">
					<i></i><span><s:text name="log_facebook"/></span><em></em>
				</div>
				<script type="text/javascript" src="./static/js/twitter.js"></script>
				<span class="twitter_button" id="twitter_btn" key="vbjGm4vKnKi4qo6VssublM6Fh5vKzqmMpg==" secret="qYmsiJ6SqpOdmr2szIqlqZOyzcanm5LOzs/JyrS7sMiwqYfLrbSwjs6pqM/PtLW7vJE=" callback="">
				<span class="icon"></span>
				<span class="text"><s:text name="log_twitter"/></span>
				</span>
				<script type="text/javascript" src="./static/js/google.js"></script>
				<div id="google_btn" class="google_button" clientid="158102854367-ml2isqm92pp0hbtf2kplip0he9h64ebk.apps.googleusercontent.com">
					<span class="icon"></span><span class="button_text"><s:text name="log_google"/></span>
				</div>
			</div>
		</div>
		<div class="blank20">
		</div>
	</div>
	<div class="footer">
		<div class="img">
			<a href="https://www.shoeslogo.com/" title="shoeslogo.com" target="_blank" style="margin:0 5px;"><img src="./static/images/854464db07.png" alt="shoeslogo.com"></a><a href="http://www.shoesmat.com/" title="shoesmat.com" target="_blank" style="margin:0 5px;"><img src="./static/images/0f12682298.png" alt="shoesmat.com"></a><a href="http://www.shoesrd.com/" title="shoesrd.com" target="_blank" style="margin:0 5px;"><img src="./static/images/3c27f32d85.png" alt="shoesrd.com"></a><a href="http://www.wzsomt.com/" title="wzsomt.com" target="_blank" style="margin:0 5px;"><img src="./static/images/d30276cc0d.png" alt="wzsomt.com"></a>
		</div>
		<div class="copyright">
			Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号 &nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	</body>
	</html>