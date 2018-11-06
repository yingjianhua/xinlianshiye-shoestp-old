<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0107)https://www.shoestp.com/account/forgot.html?&email=MzMyOTkxMjM3QHFxLmNvbQ%3D%3D&expiry=MWEyMTkzMGQwMzgwNTMx -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="renderer" content="webkit">
    <script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }
        gtag('js', new Date());

        gtag('config', 'UA-122336495-1');
        
        
         var stpshop_config={
			"domain":"${envConfig.domain}",
			"lang":"${envConfig.lang}",
			"currency":"${envConfig.currency}",
			"currency_symbols":"${envConfig.currencySymbols}",
			"currency_rate":"${envConfig.currencyRate}",
			"userId":"${envConfig.userId}",
			"systemTime":${envConfig.systemTime},
			"timeDifference":new Date().getTime()-${envConfig.systemTime},
			"timezoneOffset":${envConfig.timezoneOffset},//服务器时间和UTC时间的时间差(分钟)
			"imageBaseUrl":"+${envConfig.imageBaseUrl}+"
		};
		var ueeshop_config = stpshop_config;
    </script>
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/global(1).js"></script>
        <script type="text/javascript" src="./static/js/user.js"></script>
	<script type="text/javascript" src="./static/js/global.js"></script>
<script type="text/javascript" src="./static/js/lang/${env.curLanguage }.js"></script>
    <script type="text/javascript">
   
        $(document).ready(function () {
            user_obj.sign_in_init();
            user_obj.forgot_init();
        });
    </script>
</head>

<body>
    <div id="customer">
        <style>
            body {
                background-image: none;
            }
        </style>
        <div class="header" style="overflow:visible;">
            <div class="logo fl pic_box">
                <a href="/">
                    <img src="./static/images/85bb6f7a6c.png">
                    <span></span>
                </a>
            </div>
            <a href="/" class="home fr"><s:text name="Global.Back_To_Home"/></a>
            <div class="fr account_lang">
                <!-- <dl>
                    <dt class="">
                        <span>
                                          
                            :Language: English </span>
                    </dt>
                    <dd class="language lang">
                    </dd>
                </dl> -->
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
					<dd class="language lang" style="min-width:83%;">
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
   						user_obj.sign_in_init();
                    	$(".language.lang a").on("click", function() {
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
                    <button class="shopbox_close">
                        <span>Ã</span>
                    </button>
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
                            <div class="blank15"></div>
                        </div>
                        <div class="shopbox_bot">
                            <div class="pop_currency">
                                <span class="pop_currency_title">Choose Currency:</span>
                                <a rel="nofollow" class="btn_currency" href="javascript:;" data-currency="USD">
                                    <img src="./static/images/53092c531f.jpg" alt="USD">USD
                                    <em></em>
                                </a>
                                <ul class="pop_currency_menu">
                                    <li>
                                        <a rel="nofollow" href="javascript:;" data="USD">
                                            <img src="./static/images/53092c531f.jpg" alt="USD">USD</a>
                                    </li>
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
            <form class="register fl" id="infoForm">
                <h3 class="title"><s:text name="Global.Reset_Password"/></h3>
                <div class="clear"></div>
                <div id="error_register_box" class="error_note_box"></div>
                <div class="clear"></div>
                <input type="hidden" value="${purchasepkey}" name="purchasepkey"/>
                <input type="hidden" value="${randomNum}" name="randomNum"/>
                
                <div class="row">
                    <label for="Password"><s:text name="Global.New_Password"/></label>
                    <input name="password" id="Password" class="lib_txt" autocomplete="off" type="password" size="40" notnull="">
                </div>
                <div class="row">
                    <label for="Password2"><s:text name="Global.Confirm_Password"/></label>
                    <input name="password2" id="Password2" class="lib_txt" autocomplete="off" type="password" size="40" notnull="">
                    <p class="on_error"></p>
                </div>
                <dl class="intro">
                    <dd><s:text name="forgetPWD.fill_In_Completely"/></dd>
                </dl>
                <div class="row">
                    <button class="signbtn signup form_button_bg NavBgColor resetbtn" type="button" id="toUpdate"><s:text name="Global.Submit"/></button>
                </div>
            </form>
            <div class="info fr">
                <div class="box member">
                    <p><s:text name="Global.Already_Have_An_Account"/></p>
                    <div class="sign_btn">
                        <a href="javascript:;" class="SignInButton signinbtn"><s:text name="Global.Log_In_Now"/></a>
                    </div>
                    <p class="forgot">
                        <a href="/home/usr_UsrPurchase_sendEmail" class="FontColor"><s:text name="Global.Forget_Password"/></a>
                    </p>
                    <%-- <script type="text/javascript" src="./static/js/facebook.js"></script> --%>
                    <div id="fb_button" scope="public_profile, email" onclick="checkLoginState();" class="fb_button" appid="718848724966585">
                        <i></i>
                        <span><s:text name="Global.Sign_In_With_Facebook"/></span>
                        <em></em>
                    </div>
                   <%--  <script type="text/javascript" src="./static/js/twitter.js"></script> --%>
                    <span class="twitter_button" id="twitter_btn" key="vbjGm4vKnKi4qo6VssublM6Fh5vKzqmMpg==" secret="qYmsiJ6SqpOdmr2szIqlqZOyzcanm5LOzs/JyrS7sMiwqYfLrbSwjs6pqM/PtLW7vJE="
                        callback="">
                        <span class="icon"></span>
                        <span class="text"><s:text name="Global.Sign_In_With_Twitter"/></span>
                    </span>
                   <%--  <script type="text/javascript" src="./static/js/google.js"></script> --%>
                    <div id="google_btn" class="google_button" clientid="158102854367-ml2isqm92pp0hbtf2kplip0he9h64ebk.apps.googleusercontent.com">
                        <span class="icon"></span>
                        <span class="button_text"><s:text name="Global.Sign_In_With_Google"/></span>
                    </div>
                </div>
            </div>
            <div class="blank20"></div>
        </div>
        <div class="footer">
<!--             <div class="img"> -->
<!--                 <a href="https://www.shoeslogo.com/" title="shoeslogo.com" target="_blank" style="margin:0 5px;"> -->
<!--                     <img src="./static/images/854464db07.png" alt="shoeslogo.com"> -->
<!--                 </a> -->
<!--                 <a href="http://www.shoesmat.com/" title="shoesmat.com" target="_blank" style="margin:0 5px;"> -->
<!--                     <img src="./static/images/0f12682298.png" alt="shoesmat.com"> -->
<!--                 </a> -->
<!--                 <a href="http://www.shoesrd.com/" title="shoesrd.com" target="_blank" style="margin:0 5px;"> -->
<!--                     <img src="./static/images/3c27f32d85.png" alt="shoesrd.com"> -->
<!--                 </a> -->
<!--                 <a href="http://www.wzsomt.com/" title="wzsomt.com" target="_blank" style="margin:0 5px;"> -->
<!--                     <img src="./static/images/d30276cc0d.png" alt="wzsomt.com"> -->
<!--                 </a> -->
<!--             </div> -->
            <div class="copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号 &nbsp;&nbsp;&nbsp;&nbsp;
                </div>
        </div>
        <div align="center">
            <script type="text/javascript" src="./static/js/analytics(1).js"></script>
        </div>
    </div>
    <script type="text/javascript" src="/home/static/js/layer.js"></script>
	<link rel="stylesheet" href="/home/static/css/layer.css" type="text/css">
    <script type="text/javascript">

    function getMessage(str){
    	var sourceStr = str;
    	if(str.indexOf("##") != -1){
    		str = str.split("##")[0];
    	}
    	var key = str.split("%")[0];
    	var value = str.split("%")[1];
    	var message = lang_obj[key][value];
    	
    	if(sourceStr.indexOf("##") != -1){
    		var arr = sourceStr.split("##");
    		var arrs = new Array();
    		for(var i = 1;i<arr.length;i++){
    			arrs[i-1] = arr[i];
    		}
    		message = message.format(...arrs);
    	}
    	console.log(message)
    	return message;
    }

    String.prototype.format = function() {
    	if (arguments.length == 0)
    		return this;
    	console.log(arguments.length)
    	for (var s = this, i = 0; i < arguments.length; i++)
    		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
    	return s;
    };
    	
	    window.onload = function() {
			var result= '${returnresult}';
			if(result!=null && result != ""){
				window.location.href="/home/usr_UsrPurchase_sendEmail"; 
			}
	
		};
    	var email='';
    	$("#toUpdate").on("click",function(){
    		var password = $("#Password").val();
    		var password2 = $("#Password2").val();
    		if(password != password2){
    			layer.msg("<s:text name='Global.Inconsistent'/>",{icon:2,time:2000});
    			return;
    		}else if(password==''||password==null ||password2==''||password2==null){
    			layer.msg("<s:text name='NotNullTip_PWD'/>",{icon:2,time:2000});
    			return;
    		}else if(password.length>20||password.length<6||password2.length>20||password2.length<6){
    			layer.msg("<s:text name='Length_Limit'/>",{icon:2,time:2000});
    			return;
    		}
    		
    		 $.ajax({
				url:'/home/usr_UsrPurchase_uda',
				type:'post',
				data:$("#infoForm").serialize(),
				dataType:'json',
				success:function(data){
					if(typeof(data.msg) == "undefined"){
						layer.msg(lang_obj.signIn.password+" "+lang_obj.addressfrom.Successfully_modified,{icon:1,time:2000},function(){
							window.location.href="/"; 
						});
					}else{
						layer.msg(getMessage(data.msg),{icon:2,time:2000});
					}
				}
			}); 
    	});
    </script>

</body>

</html>