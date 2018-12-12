<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/home/static/js/layer.js"></script>
<link rel="stylesheet" href="/home/static/css/layer.css" type="text/css">
<script type="text/javascript" src="/home/static/js/en.js"></script>
<script type="text/javascript" src="/home/static/js/lang/${env.curLanguage }.js"></script>
<script type="text/javascript" src="/home/static/js/global.js"></script>
<script type="text/javascript" src="/home/static/js/global(1).js"></script>
<script type="text/javascript" src="/home/static/js/user.js"></script>
<script type="text/javascript" src="/home/static/js/moment-with-locales.min.js"></script>
<script type="text/javascript">
    $(window).resize(function () {
        $(window).webDisplay(2);
    });
    $(window).webDisplay(2);
    var stpshop_config = {
        "domain": "${envConfig.domain}",
        "lang": "${envConfig.lang}",
        "currency": "${envConfig.currency}",
        "currency_symbols": "${envConfig.currencySymbols}",
        "currency_rate": "${envConfig.currencyRate}",
        "userId": "${envConfig.userId}",
        "systemTime":${envConfig.systemTime},
        "timeDifference": new Date().getTime() -${envConfig.systemTime},
        "timezoneOffset":${envConfig.timezoneOffset},//服务器时间和UTC时间的时间差(分钟)
        "imageBaseUrl": "${envConfig.imageBaseUrl}"
    };
    var timezoneOffset = new Date().getTimezoneOffset();
    Date.toLocale = function (systemTime) {
        if (typeof(systemTime) == "string")
            systemTime = parseInt(systemTime)
        return new Date(systemTime + (timezoneOffset * 60 * 1000 + stpshop_config.timezoneOffset));
    }
    var ueeshop_config = stpshop_config;
    var isLogin = ${env.login!=null};

</script>
<div id="web_top">
    <div class="wide clean">
        <div class="top_lang fl">
            <dl>
                <dt class="">
						<span> <!--                -->
							<s:text name="Global.Language"/>:
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
                    <form action="" method="GET" hidden>
                        <input name="request_locale">
                    </form>
                </dd>
            </dl>
        </div>
        <!--        <div class="top_currency fl"></div>-->

        <div class="top_mem fr">
            <div class="mendiv">
                <s:if test="env.login==null">
                    <a rel="nofollow" href="javascript:;" class="SignInButton FontColor"><s:text
                            name="web-top.LogIn"/></a> <s:text name="web-top.Or"/>
                    <a rel="nofollow" href="/home/usr_UsrPurchase_sign" class="FontColor"><s:text
                            name="web-top.Registered"/></a>
                </s:if>
                <s:else>
                    <s:text name="web-top.Welcome"/>
                    <div class="user-drop-wrap">
                        <a href="/home/usr_UsrPurchase_userIndex">${env.login.loginName}</a>
                        <dl class="fl list">
                            <dd class="user">
                                <a rel="nofollow" href="/home/odr_OdrOrder_orders"><s:text name="Global.My_Order"/></a>
                                <a rel="nofollow" href="/home/usr_UsrFavorites_myfavorite"><s:text
                                        name="Global.My_Favorites"/></a>
                                    <%-- <a rel="nofollow" href="https://www.shoestp.com/account/coupon/"><s:text name="my_coupon" /></a> --%>
                                    <%-- <a rel="nofollow" href="https://www.shoestp.com/account/inbox/"><s:text name="my_inbox" /></a> --%>
                                <a rel="nofollow" href="/home/usr_UsrPurchase_signOut"><s:text
                                        name="Global.Drop_Out"/></a>
                            </dd>
                        </dl>
                    </div>
                </s:else>
            </div>
            <script type="text/javascript">
                $(document).ready(function () {
                    moment.locale('${env.curLanguage}');
                    user_obj.sign_in_init();
                    $("#web_top .language.lang a").on("click", function () {
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
        </div>
        <div class="fr top_cart">
            <div class="fl img">
                <i class="inq_count">${env.login==null?0:env.login.inquiryCount }</i>
            </div>
            <a href="/home/usr_UsrConsult_listView" class="fl"><s:text name="my-inquiry-publish.View_Inquiry"/></a>
        </div>
        <div class="fr top_my_fav">
            <div class="fl img">
                <i class="fav_count">${env.login==null?0:env.login.favoriteCount }</i>
            </div>
            <a href="/home/usr_UsrFavorites_myfavorite" class="fl"><s:text name="Global.My_Favorites"/></a>
        </div>
        <c:if test="${not empty env.login}">
            <c:if test="${env.login.cartCount>0}">
                <div class="fr top_cart top_cart0">
                    <div class="fl img">
                        <i class="cart_count">${env.login==null?0:env.login.cartCount}</i>
                    </div>
                    <a href="/home/usr_UsrCart_cartshopping" class="fl cart_inner"><s:text
                            name="Global.Shopping_Cart"/></a>
                </div>
            </c:if>
        </c:if>

    </div>
</div>
<c:if test="${empty supView.imList || fn:length(supView.imList) == 0}">
    <!--Start of Tawk.to Script-->
    <script type="text/javascript">
        var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
        (function () {
            var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
            s1.async = true;
            s1.src = 'https://embed.tawk.to/5b8fcb48f31d0f771d8476f6/default';
            s1.charset = 'UTF-8';
            s1.setAttribute('crossorigin', '*');
            s0.parentNode.insertBefore(s1, s0);
        })();
    </script>
    <!--End of Tawk.to Script-->
</c:if>
<c:if test="${not empty supView.imList && fn:length(supView.imList) > 0}">
    <c:set var="flag" value="true"/>
    <c:forEach items="${supView.imList}" var="im" varStatus="state">
        <c:if test="${flag == true}">
            <c:if test="${im.type == 1 || im.type == 2 }">
                ${im.demo}
                <c:set var="flag" value="false"/>
            </c:if>
            <c:if test="${im.type == 0}">
                <script type="text/javascript">
                    var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
                    (function () {
                        var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
                        s1.async = true;
                        s1.src = 'https://embed.tawk.to/5b8fcb48f31d0f771d8476f6/default';
                        s1.charset = 'UTF-8';
                        s1.setAttribute('crossorigin', '*');
                        s0.parentNode.insertBefore(s1, s0);
                    })();
                </script>
                <c:set var="flag" value="false"/>
            </c:if>
        </c:if>
    </c:forEach>
</c:if>
<script>

    function getMessage(str) {
        var sourceStr = str;
        if (str.indexOf("##") != -1) {
            str = str.split("##")[0];
        }
        var key = str.split("%")[0];
        var value = str.split("%")[1];
        var message = lang_obj[key][value];

        if (sourceStr.indexOf("##") != -1) {
            var arr = sourceStr.split("##");
            var arrs = new Array();
            for (var i = 1; i < arr.length; i++) {
                arrs[i - 1] = arr[i];
            }
            message = message.format(...arrs);
        }
        return message;
    }

    String.prototype.format = function () {
        if (arguments.length == 0)
            return this;
        for (var s = this, i = 0; i < arguments.length; i++)
            s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
        return s;
    };
</script>
