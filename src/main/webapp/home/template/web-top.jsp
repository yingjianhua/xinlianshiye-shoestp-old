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
<script src='/home/v2/static/js/base/axios.min.js'></script>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<script src="/home/v2/static/js/base/vue.min.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
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