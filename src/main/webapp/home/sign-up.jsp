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
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/user.js"></script>
    <script type="text/javascript" src="./static/js/global.js"></script>
    <script type="text/javascript" src="./static/js/global(1).js"></script>
    <script type="text/javascript" src="./static/js/lang/${env.curLanguage }.js"></script>
    <script type="text/javascript" src="./static/js/layer.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <script type="text/javascript">
        $(document).ready(function () {
            user_obj.sign_in_init(undefined, "<s:if test="jumpUrl==null">/home/usr_UsrPurchase_userIndex</s:if><s:else>${jumpUrl }</s:else>");
            user_obj.sign_up_init();
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
                <select name="bean.country" id="country">
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
                <dt><s:text name="sign-up.Said_That_I_Agree"/></dt>
                <dd></dd>
                <dd><s:text name="sign-up.May_Receive"/></dd>
                <dd><s:text name="sign-up.Promotional_Email"/></dd>
                <dd>
                    <a href="/home/cnt_CntSglPageCategory_gosglpageshoestp?pkey=9"><s:text name="TermsOfUse"/></a>
                </dd>
            </dl>
            <div class="row">
                <button class="signbtn signup form_button_bg" type="button" id="regButton"><s:text
                        name="createAccount"/></button>
            </div>
        </form>
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

<script>
    function gtag_report_conversion(url) {
        var callback = function () {
            if (typeof(url) != 'undefined') {
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
</script>

</body>

</html>
