<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link rel="stylesheet" href="./static/css/combo.select.css">

    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/user.js"></script>
    <script type="text/javascript" src="./static/js/global.js"></script>
    <script type="text/javascript" src="./static/js/global(1).js"></script>
    <script type="text/javascript" src="./static/js/lang/${env.curLanguage }.js"></script>
    <script type="text/javascript" src="./static/js/layer.js"></script>
    <script type="text/javascript" src="./static/js/jquery.combo.select.js"></script>

    <script src="./static/js/hellojs/demos/client_ids.js"></script>
    <script type="text/javascript" src="./static/js/hellojs/dist/hello.all.js"></script>

    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <script type="text/javascript">
        $(document).ready(function () {
            user_obj.sign_in_init(undefined, "<s:if test="jumpUrl==null">/home/usr_UsrPurchase_userIndex</s:if><s:else>${jumpUrl }</s:else><s:if test="backUrl!=null">&backUrl=${backUrl }</s:if>");
            user_obj.sign_up_init();
        });
    </script>

    <%--google支持--%>
    <meta name="google-signin-client_id" content="1094073562755-0sf2r98c8q0ik850jafbe7o5qoclsi63.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <%--<script>--%>
        <%--// facebook异步调用支持--%>
        <%--window.fbAsyncInit = function() {--%>
            <%--FB.init({--%>
                <%--appId      : '801883060180295',--%>
                <%--cookie     : true,  // enable cookies to allow the server to access--%>
                                    <%--// the session--%>
                <%--xfbml      : true,  // parse social plugins on this page--%>
                <%--version    : 'v3.2' // The Graph API version to use for the call--%>
            <%--});--%>

            <%--FB.getLoginStatus(function(response) {--%>
                <%--statusChangeCallback(response);--%>
            <%--});--%>

        <%--};--%>
        <%--(function(d, s, id) {--%>
            <%--var js, fjs = d.getElementsByTagName(s)[0];--%>
            <%--if (d.getElementById(id)) return;--%>
            <%--js = d.createElement(s); js.id = id;--%>
            <%--js.src = "https://connect.facebook.net/en_US/sdk.js";--%>
            <%--fjs.parentNode.insertBefore(js, fjs);--%>
        <%--}(document, 'script', 'facebook-jssdk'));--%>
    <%--</script>--%>



</head>

<body>
<div id="customer">
    <style>
        body {
            background-image: none;
        }
    </style>
    <script type="text/javascript">
        var ueeshop_config = {
            "date": "2018/07/18 09:34:10",
            "lang": "_en",
            "currency": "USD",
            "currency_symbols": "$"
        }
    </script>
    <div class="header" style="overflow:visible;">
        <div class="logo fl pic_box">
            <a href="/">
                <img src="./static/images/85bb6f7a6c.png">
                <span></span>
            </a>
        </div>
        <a href="/" class="home fr"><s:text name="user.retuSignrnHome"/></a>
        <div class="fr account_lang">
            <%-- <dl>
                <dt class="">
                    <span>
                        <!--                -->
                        <!--: -->Language: English </span>
                </dt>
                <dd class="language lang">
                </dd>
            </dl> --%>
            <dl>
                <dt class="">
						<span> <!--                -->
							<s:text name="language"/>:
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
                    $(".language.lang a").on("click", function () {
                        $.ajax({
                            url: "/home/plt_PltConfig_changeLanguage",
                            type: "GET",
                            dataType: "json",
                            data: "request_locale=" + $(this).attr("lang"),
                            success: function (data) {
                                if (data.ret == 1)
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
        <link href="./static/css/daterangepicker.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="./static/js/moment.min.js"></script>
        <script type="text/javascript" src="./static/js/daterangepicker.js"></script>
        <form class="register fl" id="infoForm">
            <h3 class="title"><s:text name="register"/></h3>
            <div class="clear"></div>
            <div id="error_register_box" class="error_note_box"></div>
            <div class="clear"></div>
            <div class="row">
                <span class="fc_red">*</span>
                <label for="Email"><s:text name="consult.email"/>
                </label>
                <input name="bean.email" id="Email" class="lib_txt" type="text" size="40" maxlength="100" format="Email"
                       notnull="">
                <p class="on_error"><s:text name="EmailEntered"/></p>
            </div>
            <div class="row">
                <span class="fc_red">*</span>
                <label for="Password"><s:text name="createPWD"/>
                </label>
                <input name="password" id="Password" class="lib_txt" type="password" size="40" notnull=""
                       placeholder="<s:text name="pwd_length"/>">
            </div>
            <div class="row">
                <span class="fc_red">*</span>
                <label for="Password2"><s:text name="confirm_password"/>
                </label>
                <input name="password2" id="Password2" class="lib_txt" type="password" size="40" notnull=""
                       placeholder="<s:text name="pwd_length"/>">
                <p class="on_error"><s:text name="user.matchPWD"/></p>
            </div>
            <div class="row">
                <span class="fc_red">*</span>
                <label for="Country"><s:text name="Global.Country"/>
                </label>


                <!-- 带搜索功能的下拉框 -->
                <select name="bean.country"  id="select">
                    <c:forEach items="${countrys}" var="country">
                        <c:if test="${country.isDefault == true}">
                            <option value="${country.id}" selected="selected">${country.name}</option>
                        </c:if>
                        <c:if test="${country.isDefault == false}">
                            <option value="${country.id}">${country.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="clear"></div>
            <div class="row">

                <span class="fc_red">*</span>
                <label for="Security Code"><s:text name="Global.Verification_Code"/>

                </label>
                <input id="checkCode" name="checkCode" class="lib_txt fl" type="text" size="10" maxlength="4"
                       notnull="">&nbsp;&nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/servlet/verify.img"
                     style="vertical-align:top;width:53px;height:28px;line-height:21px;" id="verify"/></div>
            <dl class="intro">

                <dt><input type="checkbox" id="isdisenabild"
                           style="float: left;margin-top: 2px;margin-right: 5px;"/><s:text
                        name="sign-up.Said_That_I_Agree"/></dt>
                <dd></dd>
                <dd><s:text name="sign-up.May_Receive"/></dd>
                <dd><s:text name="sign-up.Promotional_Email"/></dd>
                <dd>
                    <a href="/home/cnt_CntSglPageCategory_gosglpageshoestp?pkey=9"><s:text name="TermsOfUse"/></a>
                </dd>
            </dl>
            <div class="row">

                <button class="signbtn signup form_button_bg" type="button" id="regButton" style="background-color: #DDDDDD;border-color: #DDDDDD " disabled><s:text
                        name="createAccount"/></button>
            </div>
        </form>

        <!-- facebook登陆 -->
        <%--<fb:login-button--%>
                <%--scope="public_profile,email"--%>
                <%--onlogin="checkLoginState();">--%>
        <%--</fb:login-button>--%>
        <%--google登陆--%>
        <%--<div class="g-signin2" data-onsuccess="onSignIn"></div>--%>

        <%--<div id="fb-root"></div>--%>
        <%--<script async defer src="https://connect.facebook.net/zh_CN/sdk.js#xfbml=1&version=v3.2&appId=801883060180295&autoLogAppEvents=1"></script>--%>
        <%--<div onlogin="checkLoginState();" class="fb-login-button" data-size="large" data-button-type="continue_with" data-auto-logout-link="true" data-use-continue-as="true"></div>--%>
        <%--<button onclick="ajaxforid()">登陆</button>--%>

        <%--<button onclick="linkedin()">linkedin</button>--%>
        <button id='profile' onclick="login('facebook');">facebook</button>

        <%--<button id='login' onclick="login('linkedin')">LinkedIn</button>--%>



        <div class="info fr">
            <div class="box member">
                <p><s:text name="Global.Already_Have_An_Account"/></p>
                <div class="sign_btn">
                    <a href="javascript:;" class="SignInButton signinbtn"><s:text name="Global.Log_In_Now"/></a>
                </div>
                <p class="forgot">
                    <a href="/home/usr_UsrPurchase_sendEmail" class="FontColor"><s:text name="user.forgotPWD"/></a>
                </p>
                <%-- <script type="text/javascript" src="./static/js/facebook.js"></script>
                <div id="fb_button" scope="public_profile, email" onclick="checkLoginState();" class="fb_button" appid="718848724966585">
                    <i></i>
                    <span><s:text name="log_facebook"/></span>
                    <em></em>
                </div>
                <script type="text/javascript" src="./static/js/twitter.js"></script>
                <span class="twitter_button" id="twitter_btn" key="vbjGm4vKnKi4qo6VssublM6Fh5vKzqmMpg==" secret="qYmsiJ6SqpOdmr2szIqlqZOyzcanm5LOzs/JyrS7sMiwqYfLrbSwjs6pqM/PtLW7vJE="
                    callback="#">
                    <span class="icon"></span>
                    <span class="text"><s:text name="log_twitter"/></span>
                </span>
                <script type="text/javascript" src="./static/js/google.js"></script>
                <div id="google_btn" class="google_button" clientid="158102854367-ml2isqm92pp0hbtf2kplip0he9h64ebk.apps.googleusercontent.com">
                    <span class="icon"></span>
                    <span class="button_text"><s:text name="log_google"/></span>
                </div> --%>
            </div>
        </div>
        <div class="blank20"></div>
    </div>
    <div class="footer">
        <div class="copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
            <%--浙ICP备16034166号-1 浙公网安备--%>
            <%--33030402000493号--%>
            &nbsp;&nbsp;&nbsp;&nbsp;
        </div>
    </div>
</div>
<script async src="https://www.googletagmanager.com/gtag/js?id=AW-783435725"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'AW-783435725');
    gtag('config', 'UA-127715615-6')
</script>

<script class="pre">
    function login(network) {
        var facebook = hello(network);
        facebook.login().then(function() {
            // get user profile data
            return facebook.api('me');
        }).then(function(p) {
            console.log("p====>",p)
            document.getElementById('profile').innerHTML = "<img src='"+ p.thumbnail + "' width=24/>Connected to "+ network +" as " + p.name;
            console.log(p.id)
        })
            .catch(function(err) {
                console.log(err);
            });
    }

</script>
<script class="pre">
    hello.init({
        facebook: ''
        // 'linkedin' : '81xpp0e4b5z1fh',
    }, {
        // scope : ['friends','email'],
        redirect_uri: 'https://5c937c6f.ngrok.io/home/usr_UsrPurchase_sign',
        // oauth_proxy: OAUTH_PROXY_URL
    });
</script>

<%--<script>--%>
    <%--var a = "";--%>
    <%--function linkedin() {--%>
        <%--window.location.href = "https://www.linkedin.com/uas/oauth2/authorization?state=987654321&" +--%>
                <%--"response_type=code&" +--%>
                <%--"client_id=81xpp0e4b5z1fh&" +--%>
                <%--"redirect_uri=http://localhost:8080/home/usr_UsrPurchase_sign&" +--%>
                <%--"scope=r_basicprofile";--%>
        <%--console.log(getParam("code"))--%>
        <%--$.ajax({--%>
            <%--url: '/home/usr_UsrPurchase_test',--%>
            <%--type: 'post',--%>
            <%--data: {--%>
                <%--'code':getParam("code")--%>
            <%--},--%>
            <%--dataType: 'json',--%>
            <%--success: function (data) {--%>
                <%--console.log("data==>",data)--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    <%--function getParam(paramName) {--%>
        <%--paramValue = "", isFound = !1;--%>
        <%--if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {--%>
            <%--arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;--%>
            <%--while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++--%>
        <%--}--%>
        <%--return paramValue == "" && (paramValue = null), paramValue--%>
    <%--}--%>
<%--</script>--%>

<%--google登陆--%>
<script>
    function onSignIn(googleUser) {
        console.log("ssss")
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        $.ajax({
            url: '/home/usr_UsrPurchase_googleNewlogin',
            type: 'post',
            data: {
                'googleID':profile.getId().toString()
            },
            dataType: 'json',
            success: function (data) {
                console.log("data==>",data)
                if(data.ret == 1){
                    window.location.href = '/home/usr_UsrPurchase_userIndex'
                }else{
                    // window.location.href = '/home/usr_UsrPurchase'
                }
            }
        });
    }

</script>

<%--facebook登陆--%>
<script>
    // facebook登陆回调函数
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        if (response.status === 'connected') {
            testAPI(response);
        } else {
            // document.getElementById('status').innerHTML = 'Please log ' +
            //     'into this app.';
        }
    }

    // 登陆时调用此函数
    function checkLoginState() {
        //获取登陆状态
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }

    //登陆成功后调用的方法
    function testAPI(response) {
        console.log('Welcome!  Fetching your information.... ');
        console.log(response);
        FB.api('/me', function(response) {
            console.log('Successful login for: ',response.id);
            $.ajax({
                url: '/home/usr_UsrPurchase_faceBookNewlogin',
                type: 'post',
                data: {
                    'facebookID':response.id.toString()
                },
                dataType: 'json',
                success: function (data) {
                    console.log("data==>",data)
                    if(data.ret == 1){
                        window.location.href = '/home/usr_UsrPurchase_userIndex'
                    }else{
                        // window.location.href = '/home/usr_UsrPurchase'
                    }
                }
            });
        });
    }

    function ajaxforid() {
        console.log("ssss")
        var id = 119600319125978;
        // var id = 0;
        $.ajax({
            url: '/home/usr_UsrPurchase_faceBookNewlogin',
            type: 'post',
            data: {
                'facebookID':id
            },
            dataType: 'json',
            success: function (data) {
                console.log("data==>",data)
                if(data.ret == 1){
                    window.location.href = '/home/usr_UsrPurchase_userIndex'
                }else{
                    window.location.href = '/home/usr_UsrPurchase_sign'
                }
            }
        });
    }
</script>


<script>
    function gtag_report_conversion(url) {
        var callback = function () {
            if (typeof (url) != 'undefined') {
                window.location = url;
            }
        };
        gtag('event', 'conversion', {
            'send_to': 'AW-783435725/kZX7CNPfnZABEM2PyfUC',
            'event_callback': callback
        });
        return false;
    }
</script>
<script type="text/javascript">

    $("#isdisenabild").on("click", function () {
        var booleans= document.getElementById("isdisenabild")
        if(booleans.checked){
            $("#regButton").attr("disabled",false);
            $("#regButton").css("background-color","#005AB0")
            $("#regButton").css(" border-color","#005AB0")


        }else{
            $("#regButton").attr("disabled",true);
            $("#regButton").css(" border-color","#DDDDDD")
            $("#regButton").css("background-color","#DDDDDD")
        }
    })
    $("#verify").on("click", function () {
        refreshVerify();
    })

    $("#regButton").on("click", function () {
        var email = $("#Email").val();
        var password = $("#Password").val();
        var password2 = $("#Password2").val();
        var checkCode = $("#checkCode").val();

        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");

        if (isBlank(email)) {
            refreshVerify();
            layer.msg('<s:text name="NotNullTip-mail"/>', {icon: 2});
            return;
        }
        if (!reg.test(email)) {
            refreshVerify();
            layer.msg('<s:text name="format-mail"/>', {icon: 2});
            return;
        }
        if (checkLength(password)) {
            refreshVerify();
            layer.msg('<s:text name="pwd_length"/>', {icon: 2});
            return;
        }
        if (!isNaN(password)) {
            refreshVerify();
            layer.msg('<s:text name="pwd_length"/>', {icon: 2});
            return;
        }
        if (isBlank(password)) {
            refreshVerify();
            layer.msg('<s:text name="NotNullTip-password"/>', {icon: 2});
            return;
        }
        if (isBlank(password2)) {
            refreshVerify();
            layer.msg('<s:text name="NotNullTip-cfmPassword"/>', {icon: 2});
            return;
        }
        if (password != password2) {
            refreshVerify();
            layer.msg('<s:text name="Global.Inconsistent"/>', {icon: 2});
            return;
        }
        if (isBlank(checkCode)) {
            refreshVerify();
            layer.msg('<s:text name="NotNullTip-verification"/>', {icon: 2});
            return;
        }
        $.ajax({
            url: '/home/usr_UsrPurchase_pcRegister',
            type: 'post',
            data: $("#infoForm").serialize(),
            dataType: 'json',
            success: function (data) {

                if (data.ret == 1) {
                    gtag_report_conversion();
                    layer.msg("<s:text name='signup-success'/>", {icon: 1, time: 3000}, function () {
                        window.location.href = "/";
                    });
                } else {
                    console.log(data.msg);
                    layer.msg(getMessage(data.msg), {icon: 2});
                    var time = new Date().getTime();
                    $("#verify").attr("src", "/servlet/verify.img?d=" + time);
                }
            }
        });
    });

    function getMessage(str) {
        if (str.indexOf("##") != -1) {
            str = str.split("##")[0];
        }
        var key = str.split("%")[0];
        var value = str.split("%")[1];
        var message = lang_obj[key][value];
        return message;
    }

    function refreshVerify() {
        var time = new Date().getTime();
        $("#verify").attr("src", "/servlet/verify.img?d=" + time);
    }


    function isBlank(str) {
        if (str.trim() == "" || str == null) {
            return true;
        } else {
            return false;
        }
    }

    function checkLength(str) {
        if (str.length < 8 || str.length > 15) {
            return true;
        } else {
            return false;
        }
    }
    $("#select").comboSelect();
</script>

</body>

</html>
