<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%--<meta name="google-signin-scope" content="profile email">
       <meta name="google-signin-client_id" content="1033833261193-aqk9ri39kpavtic5if3kosv8uuusrlc4.apps.googleusercontent.com">
       <script src="https://apis.google.com/js/platform.js" async defer></script> --%>
    <meta name="google-signin-client_id"
          content="1033833261193-aqk9ri39kpavtic5if3kosv8uuusrlc4.apps.googleusercontent.com">
    <%@include file="/mobile/template/style_import.jsp" %>
    <%--  <script type="text/javascript" src="/home/static/themes/default/mobile/js/facebook.js"> </script> --%>
    <script src="https://connect.facebook.net/en_UsugbS/sdk.js" defer async="true"></script>
    <script type="text/javascript">
        $(function () {
            user_obj.user_login();

            var curr = new Date().getFullYear(),
                opt = {};
            opt.date = {
                preset: 'date'
            };
            opt.datetime = {
                preset: 'datetime',
                minDate: new Date(2012, 3, 10, 9, 22),
                maxDate: new Date(2014, 7, 30, 15, 44),
                stepMinute: 5
            };
            opt.time = {
                preset: 'time'
            };
            opt.tree_list = {
                preset: 'list',
                labels: ['Region', 'Country', 'City']
            };
            opt.image_text = {
                preset: 'list',
                labels: ['Cars']
            };
            opt.select = {
                preset: 'select'
            };
            $('input[name=Birthday]').val('').scroller('destroy').scroller($.extend(opt['date'], {
                theme: 'sense-ui',
                mode: 'scroller',
                display: 'modal',
                lang: ''
            }));
        });
    </script>
    <%--<script type="text/javascript" src="./static/js/jquery-min.js"></script>--%>
    <style>
        .psdModal {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            z-index: 999;
            width: 70%;
            height: 1.5rem;
            line-height: 1.5rem;
            opacity: 0.8;
            padding: 1rem;
            text-align: center;
            margin: 0 auto;
            transform: translate(-50%, -50%);
            font-size: 0.8125rem;
            border-radius: 0.1875rem;
            background: #666;
            color: #FFF;
        }

        .abcRioButtonBlue {
            background-color: #fff !important;
            border-radius: 50% !important;
            border: 1px solid #4285f4 !important;
        }

        .abcRioButtonIcon {
            padding: 0 !important;
            width: 62px;
            height: 62px;
        }

        .abcRioButtonSvgImageWithFallback {
            width: auto !important;
            height: auto !important;
            position: relative;
            left: 50%;
            transform: translate(-50%);
            top: 20%;
        }

        #google_btn .abcRioButtonSvg {
            display: inline-block;
            width: 35px;
            height: 35px;
        }
    </style>

</head>

<body>
<%@ include file="/mobile/template/header.jsp" %>
<div class="wrapper">

    <div class="wrapper">
        <div class="user_login_tab clean">
            <div class="on fl">
                <!-- Sign In -->
                <s:text name="sign_in"/>
            </div>
            <div class="fl">
                <!-- Join Free -->
                <s:text name="join_free"/>
            </div>
        </div>
        <div class="user_login">
            <form method="post" class="user_login_form" id="login_form">
                <div class="user_login_box">
                    <div class="user_input user_email">
                        <div class="ui_border_b">
                            <input type="email" name="email" placeholder="<s:text name='Enter_Your_Email'/>" notnull="">
                        </div>
                    </div>
                    <div class="user_input user_password">
                        <div class="ui_border_b">
                            <input type="password" name="password" autocomplete="off"
                                   placeholder="<s:text name='Enter_Your_Password'/>"
                                   notnull="">
                        </div>
                    </div>
                    <div class="user_input user_password" id="w_login_vcode" style="display:none;">
                        <div class="ui_border_b clean">
                            <input type="text" name="checkCode" autocomplete="off" class="fl" maxlength="4"
                                   style="width:50%; text-transform:uppercase;" placeholder="Security Code">
                            <div class="fl" style="height:2.1875rem; line-height:2.1875rem; width:45%; margin-left:1%;">
                                <img src="/servlet/verify.img"
                                     onclick="this.src=&quot;/servlet/verify.img?r=&quot;+Math.random();"
                                     style="cursor:pointer;">
                            </div>
                        </div>
                    </div>
                    <div class="user_login_btn">
                        <div class="btn_global btn_submit BuyNowBgColor">
                            <!-- Sign In -->
                            <s:text name="sign_in"/>
                        </div>
                    </div>
                    <div class="user_forgot">
                        <a href="/home/usr_UsrPurchase_sendEmail" rel="nofollow" class="forget_btn">
                            <!-- Forgot your password? -->
                            <s:text name="user.forgotPWD"/>
                        </a>
                    </div>
                    <div class="oauth_title ui_border_b">
                        <div class="float">
                            <strong>
                                <!-- Or Join with -->
                                <s:text name="mobile.or_join_with"/>
                            </strong>
                        </div>
                    </div>
                    <!--  <div class="oauth_body">
                         <div id="fb_button" scope="public_profile, email" onclick="checkLoginState();"
                         appid="311019632992273" class="login_ex clean">
                             <a href="javascript:;">
                                 Sign up with Facebook
                             </a>
                         </div> -->
                    <div style="display:none">
                        <div class="oauth_body" id="fblogin"><!-- FACEBOOK登录 -->
                            <%-- <s:text name="facebookStr" /> --%>
                            <div id="fb_button" class="login_ex clean">
                                <a href="javascript:;">
                                    Sign up with Facebook
                                </a>
                            </div>
                            <div id="google_btn" class="login_ex clean">
                                <a href="javascript:;">
                                    Sign up with Google+
                                </a>
                            </div>
                        </div>
                    </div>
                    <%--<script type="text/javascript" src="/home/static/themes/default/mobile/js/google.js">
                      </script> --%>
                    <%--  <script type="text/javascript" src="https://apis.google.com/js/api:client.js">
                     </script> --%>
                    <!--  <div  id="googleLogin"
                      class="login_ex clean" onclick="onSignIn()">
                          <a href="javascript:;">
                              Sign up with Google+
                          </a>
                      </div>  -->
                    <div id="googleLogin"></div>
                    <!--    <div class="g-signin2" data-onsuccess="" data-theme="dark" class="login_ex clean"></div>-->
                </div>
                <div class="blank15">
                </div>
                <!-- </div> -->
                <input type="hidden" name="jumpUrl"
                       value="<s:if test="jumpUrl==null">/</s:if><s:else>${jumpUrl }</s:else>">
                <input type="hidden" name="do_action" value="user.login">
                <input type="hidden" name="vcodeNum" id="w_vcode_num" value="1">
            </form>
        </div>
        <div class="user_login" style="display:none;">
            <form
                    class="user_login_form" id="reg_form">
                <div class="rows">
                    <label class="field">
                        <!-- Email -->
                        <s:text name="purchase.email"/>
                        <span class="fc_red">
                                *
                            </span>
                    </label>
                    <div class="input clean">
                        <input type="email" class="box_input" name="Email" autocomplete="off"
                               placeholder="you@domain.com" data-field="<s:text name='consult.email'/>" notnull="">
                        <p class="error">
                        </p>
                    </div>
                </div>
                <div class="rows">
                    <label class="field">
                        <!-- Password -->
                        <s:text name="supplier.password"/>
                        <span class="fc_red">
                                *
                            </span>
                    </label>
                    <div class="input clean">
                        <input type="password" class="box_input" name="Password" autocomplete="off"
                               placeholder="<s:text name='Purchase_sign_up_pwd'/>"
                               data-field="<s:text name='account.password'/>" notnull="">
                        <p class="error">
                        </p>
                    </div>
                </div>
                <div class="rows">
                    <label class="field">
                        <!-- Confirm -->
                        <s:text name="Global.Confirm_Password"/>
                        <span class="fc_red">
                                *
                            </span>
                    </label>
                    <div class="input clean">
                        <input type="password" class="box_input" name="Password2" autocomplete="off"
                               placeholder="<s:text name="Global.Confirm_Password" />"
                               data-field="<s:text name='Global.Confirm_Password'/>" notnull="">
                        <p class="error">
                        </p>
                    </div>
                </div>
                <div class="rows">
                    <label class="field">
                        <!-- Country -->
                        <s:text name="purchase.country"/>
                    </label>
                    <div class="input clean">
                        <div class="box_select">
                            <select name="country_id" autocomplete="off">
                                <s:iterator value="countrys" var="line">
                                    <option value="${line.id}"
                                            <s:if test="#line.isDefault==1">selected="selected"</s:if>>${line.name }</option>
                                </s:iterator>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="rows">
                    <div class="form_name form_code clean">
                        <div class="box" id="demo_default">
                            <label class="field">
                                <!-- Security Code -->
                                <s:text name="SecurityCode"/>
                                <span class="fc_red">
                                        *
                                    </span>
                            </label>
                            <input name="checkCode" class="box_input" type="text" size="10" maxlength="4"
                                   data-field="<s:text name='Global.Verification_Code'/>" notnull="">
                            <p class="error">
                            </p>
                        </div>
                        <div class="box">
                            <label class="field">
                                &nbsp;
                            </label>
                            <img src="/servlet/verify.img"
                                 onclick="this.src=&quot;/servlet/verify.img?r=&quot;+Math.random();"
                                 style="cursor:pointer;">
                        </div>
                    </div>
                </div>
                <div class="user_login_btn">
                    <input type="hidden" name="jumpUrl" value="/home/usr_UsrPurchase">
                    <input type="hidden" name="do_action" value="user.register">
                    <div class="btn_global btn_sign_up btn_submit BuyNowBgColor">
                        <!-- Sign Up -->
                        <s:text name="mobile.sign_up"/>
                    </div>
                </div>
                <div class="blank25">
                </div>
            </form>
        </div>
    </div>
    <div class="psdModal" id="tip">
        <input type="text" id="login_name" value="">
        <input type="hidden" id="facbooktoken">
        <input type="hidden" id="googletoken">
        <input type="button" value="submit" style="display:none" id="faceBtn" onclick="facebookLogin()">
        <input type="button" value="submit" style="display:none" id="googleBtn" onclick="googleLogin()">
    </div>

    <!-- end of .wrapper -->
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
                        <s:text name="sign_in"/>
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
    $(function () {
        var em = sessionStorage.getItem("em");
        sessionStorage.setItem("em", "");
        if (em != null && em != "") {
            $("#login_form").find("input[name=email]").val(em);
        }
    })
    window.fbAsyncInit = function () {
        FB.init({
            appId: '718848724966585',
            cookie: true,
            xfbml: true,
            version: 'v2.8'
        });
    };
    $("#fb_button").click(function () {
        FB.login(function (response) {
            statusChangeCallback(response);  //登录回调函数
        }, {scope: 'email'});  //需要获取的信息scope
    });

    function statusChangeCallback(response) {
        //可用于后台验证，但是前台调用SDK则会自动验证
        var accessToken = response.authResponse.accessToken;
        console.log(response.authResponse.accessToken);
        $("#facbooktoken").val(accessToken);
        if (response.status === 'connected') {//sdk会自动保留accessToken，并且验证该请求是否来自我的应用
            FB.api('/me?fields=name,first_name,last_name,email', function (response) {
                var agoUrl = '';
                if (document.referrer == "") {
                    agoUrl = "/home/usr_UsrPurchase_userIndex";
                } else {
                    agoUrl = document.referrer;
                }
                //将用户信息传回服务端
                //window.location.href="http://gntina.iok.la/userInfo?userInfo="+JSON.stringify(response);
                $.ajax({
                    url: "/home/usr_UsrPurchase_faceBookLogin?faceBookToken=" + accessToken + "&login_name=" + $("#login_name").val() + "&jumpUrl=" + agoUrl,
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        if (data.ret == '-1') {
                            $("#faceBtn").show();
                            $('.psdModal').show().delay(20000).fadeOut();
                        }
                        if (data.ret == '1') {
                            window.location.href = data.msg;
                        }
                    }
                });
            });

        } else {

        }
    }

    function facebookLogin() {
        if ($("#login_name").val() == null || '' == $("#login_name").val()) {
            return;
        }
        var agoUrl = '';
        if (document.referrer == "") {
            agoUrl = "/home/usr_UsrPurchase_userIndex";
        } else {
            agoUrl = document.referrer;
        }
        var accessToken = $("#facbooktoken").val();
        $.ajax({
            url: "/home/usr_UsrPurchase_faceBookLogin?faceBookToken=" + accessToken + "&login_name=" + $("#login_name").val() + "&jumpUrl=" + agoUrl,
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.ret == '-2') {
                    window.location.href = "/home/usr_UsrPurchase_sign";
                }
                if (data.ret == '1') {
                    window.location.href = data.msg;
                }
            }
        });
    }

    function googleLogin() {
        if ($("#login_name").val() == null || '' == $("#login_name").val()) {
            return;
        }
        var agoUrl = '';
        if (document.referrer == "") {
            agoUrl = "/home/usr_UsrPurchase_userIndex";
        } else {
            agoUrl = document.referrer;
        }
        var accessToken = $("#googletoken").val();
        $.ajax({
            url: "/home/usr_UsrPurchase_googleLogin?googleToken=" + accessToken + "&login_name=" + $("#login_name").val() + "&jumpUrl=" + agoUrl,
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.ret == '-2') {
                    window.location.href = "/home/usr_UsrPurchase_sign";
                }
                if (data.ret == '1') {
                    window.location.href = data.msg;
                }
            }
        });
    }

    var i = 0;

    function onSuccess(googleUser) {
        var agoUrl = '';
        if (document.referrer == "") {
            agoUrl = "/home/usr_UsrPurchase_userIndex";
        } else {
            agoUrl = document.referrer;
        }
        var id_token = googleUser.getAuthResponse().id_token;
        if (i > 0) {
            $("#googletoken").val(id_token);
            $.ajax({
                url: "/home/usr_UsrPurchase_googleLogin?googleToken=" + id_token + "&login_name=" + $("#login_name").val() + "&jumpUrl=" + agoUrl,
                dataType: "json",
                async: false,
                success: function (data) {
                    console.log(data);
                    if (data.ret == '-1') {
                        $("#googleBtn").show();
                        $('.psdModal').show().delay(20000).fadeOut();
                    }
                    if (data.ret == '1') {
                        window.location.href = data.msg;
                    }
                }
            });
        }
        i++;
    }

    function onFailure(error) {
        console.log(error);
    }

    function renderButton() {
        gapi.signin2.render('google_btn', {
            'scope': 'profile email',
            'width': 64,
            'height': 64,
            'longtitle': true,
            'theme': 'dark',
            'onsuccess': onSuccess,
            'onfailure': onFailure
        });
    }
</script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
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
</body>
</html>
