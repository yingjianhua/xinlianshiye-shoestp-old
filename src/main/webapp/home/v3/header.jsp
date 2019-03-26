<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords"
          content="shoestp, shoes wholesale, shoe manufacturer, shoe supplier, shoe trade, shoes online, shoe O2O">
    <meta name="description"
          content="Shoestp offers popular shoes for individuals and wholesalers online and offline，offer a wide range of footwear and multilingual customer service.，work closely with hundreds of footwear manufacturers. ">
    <title>Online and Offline B2B Shoes Trading Platform—Shoestp.com Shoes Wholesale O2O New Mode</title>

    <script src='/home/v2/static/js/base/vue.min.js'></script>
    <script src='/home/v2/static/js/base/vue-i18n.js'></script>
    <script src='/home/v2/static/js/base/element-ui.js'></script>
    <script src='/home/v2/static/js/base/axios.min.js'></script>
    <script src="https://cdn.bootcss.com/babel-polyfill/7.2.5/polyfill.min.js"></script>
    <link rel="stylesheet" href="/home/v2/static/css/base/element-ui/element-ui.css"/>
    <link rel="stylesheet" href="/home/v2/static/css/base/foot.css"/>
    <script async src="https://www.googletagmanager.com/gtag/js?id=AW-783435725"></script>
    <script src="https://js.fundebug.cn/fundebug.1.5.1.min.js"
            apikey="afbc9f957e7689049c3282fe7696d30e7cb260e0ce11c148c0cf9e31d4e802f5"></script>
    <link rel="stylesheet" href="/home/v3/static/css/element-ui/element-ui.css"/>

    <script src="/home/v2/static/js/base/qs.js"></script>
    <link rel="stylesheet" href="/home/v3/static/css/reset.css"/>
    <link rel="stylesheet" href="/home/v3/static/css/index.css">
    <%--公共函数--%>
    <script src="/home/utils/util.js" type="text/javascript"></script>

    <!-- index为以上几个合并后的压缩文件 - 加前缀 -->
    <!-- <link rel="stylesheet" href="css/index.css" /> -->
    <link rel="stylesheet" href="/home/v3/static/css/swiper.min.css"/>
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
        var isLogin = ${env.login!=null};
        var user_obj = null;

        function getParams(name, defaultValue) {
            var url = window.location.href;
            var l = url.lastIndexOf(name)
            if (l != -1) {
                var ll = url.indexOf("&", l);
                if (ll == -1 || l > ll) {
                    ll = url.length
                }
                url = url.substring(l, ll);
                var result = url.split("=")
                if (result.length == 2) {
                    switch (typeof defaultValue) {
                        case "number":
                            return parseInt(result[1]);
                        case "boolean":
                            return Boolean(result[1])
                        default:
                            return result[1];
                    }
                }
            } else {
                if (defaultValue == 'NONE') {
                    return null;
                }
                if (defaultValue == null) {
                    return -1;
                }
                return defaultValue
            }
            return -1;
        }

        if (user_obj) {
            user_obj.sign_in_init()
        }
    </script>
