<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <meta charset=UTF-8">
    <title>
        ${seoView.title}
    </title>
    <c:if test="${seoView.keyWord!=null}">
        <meta name="keyword" content="${seoView.keyWord}"/>
    </c:if>
    <c:if test="${seoView.description!=null}">
        <meta name="description" content="${seoView.description}"/>
    </c:if>
    <script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js"></script>
    <!--    <link rel="stylesheet" href="./css/productList.css"/>-->
    <link rel="stylesheet" href="/home/v3/static/css/productInfo/reset.css"/>
    <script src='/home/v2/static/js/base/vue.min.js'></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="/home/static/js/axios.min.js"></script>
    <script src="/home/static/js/qs.js"></script>
    <%--公共函数--%>
    <script src="/home/utils/util.js"></script>
    <link rel="stylesheet" href="/home/v2/static/css/base/element-ui/element-ui.css"/>

    <link href="/home/v3/static/css/productInfo/goodsInfoNew.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/v2/static/css/base/element-ui/element-ui.css"/>
    <link rel="stylesheet" href="/home/v2/static/css/base/foot.css"/>
    <link rel="stylesheet" href="/home/v3/static/css/index.css">
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
    </script>
    <style>

        .flexSb {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            justify-content: space-between;
            -webkit-justify-content: space-between;
            -moz-justify-content: space-between;
            -ms-justify-content: space-between;
            -o-justify-content: space-between;
            align-items: center;
            -webkit-align-items: center;
            -moz-align-items: center;
            -ms-align-items: center;
            -o-align-items: center;
        }

        .flexSt {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            justify-content: flex-start;
            -webkit-justify-content: flex-start;
            -moz-justify-content: flex-start;
            -ms-justify-content: flex-start;
            -o-justify-content: flex-start;
            align-items: center;
            -webkit-align-items: center;
            -moz-align-items: center;
            -ms-align-items: center;
            -o-align-items: center;
        }

        .flexCc {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            justify-content: center;
            -webkit-justify-content: center;
            -moz-justify-content: center;
              -ms-justify-content: center;
              -o-justify-content: center;
            align-items: center;
            -webkit-align-items: center;
            -moz-align-items: center;
            -ms-align-items: center;
            -o-align-items: center;
             
        }

        body {
            background: #f5f5f5;
            min-width: 1240px;
        }

        a, a:link, a:visited, a:hover, a:active {
            display: inline-block;
            color: inherit;
            text-decoration: none;
        }

        .w1240 {
            width: 1240px;
            margin: 0 auto;
        }

        .ww42 {
            display: inline-block;
            width: 42px;
        }

        .shareTb {
            float: left;
            background: url(/home/v3/static/images/productInfo/share_small.png) no-repeat;
            width: 16px;
            height: 16px;
            margin-top: 9px;
            margin-left: 5px;
            cursor: pointer;
        }

        .el-dropdown-menu__item .shareTb {
            margin-left: 0;
            margin-right: 5px;
        }

        .shareTbtb0 {
            background-position: -48px -32px;
        }

        .shareTbtb1 {
            background-position: 0 0;
        }

        .shareTbtb2 {
            background-position: -16px 0;
        }

        .shareTbtb3 {
            background-position: -48px 0;
        }

        .shareTbtb4 {
            background-position: 0 -16px;
        }

        .shareTbtb5 {
            background-position: -16px -16px;
        }

        .shareTbtb6 {
            background-position: -32px -16px;
        }

        .shareTbtb7 {
            background-position: -48px -16px;
        }

        .shareTbtb8 {
            background-position: 0 -32px;
        }

        .shareTbtb9 {
            background-position: -32px 0;
        }

        .shareTbtb10 {
            background-position: -32px -32px;
        }

        #productInfo .el-rate {
            display: inline-block;
            margin: 0 0 0 4px;
            line-height: 24px;
        }

        #productInfo .el-rate__item,
        #productInfo .el-rate__icon {
            margin: 0 !important;
            font-size: 16px;
        }

        #productInfo .el-pagination {
            text-align: center;
            margin-top: 30px;
        }

        #productInfo {
            font-size: 12px;
        }

        #productInfo .companyTop1 {
            background: #454545;
            box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.15);
            width: 100%;
            height: 36px;
            line-height: 36px;
            color: #fff;
            font-size: 12px;
        }

        #productInfo .companyTop1 .box {
            padding: 0 10px 0 70px;
        }

        #productInfo .companyTop1 .h2 img {
            margin: 0 6px 0 20px;
        }

        #productInfo .companyTop1 .h3,
        #productInfo .companyTop1 .h4 {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            background: #fff;
            text-align: center;
            margin-right: 12px;
        }

        #productInfo .companyTop2 {
            height: 130px;
            line-height: 130px;
            border-bottom: 1px solid #ddd;
            background: #fff;
            font-size: 16px;
            color: #454545;
            font-weight: bold;
        }

        #productInfo .companyTop2 .h1 {
            float: left;
            width: 86px;
            height: 86px;
            border-radius: 50%;
            border: 3px solid #e1e1e1;
            background: #f5f5f5;
            overflow: hidden;
            margin: 20px 22px 0 4px;
        }

        #productInfo .companyTop2 .h1 img {
            width: 100%;
        }

        #productInfo .companyTop3 {
            height: 40px;
            background: #fff;
            line-height: 40px;
            border-bottom: 1px solid #ddd;
            font-size: 12px;
            font-weight: bold;
        }

        #productInfo .companyTop3 li {
            height: 39px;
            padding: 0 4px;
            margin: 0 38px;
        }

        #productInfo .companyTop3 li:hover,
        #productInfo .companyTop3 li.sele {
            border-bottom: 2px solid #10389c;
            color: #10389c;
        }

        #productInfo .topNav {
            height: 52px;
            line-height: 52px;
            color: #7f7f7f;
            font-weight: bold;
        }

        #productInfo .topNav .h1 {
            /*padding-right:6px;*/
            display: inline-block;
        }

        #productInfo .topNav .h1 i {
            margin: 0 6px;
            display: inline-block;
        }

        #productInfo .topNav a.now {
            color: #10389c;
        }

        #productInfo .productInfo-com {
            width: 978px;
            background: #fff;
            border: 1px solid #ddd;
            padding: 40px;
            padding-bottom: 60px;
        }

        #productInfo .productInfo-com .flBox {
            width: 380px;
        }

        #productInfo .productInfo-com .flBox .hliketb {
            position: absolute;
            right: 0;
            top: 0;
            width: 24px;
            height: 24px;
            margin: 16px 24px;
            border-radius: 50%;
            border: 1px solid #ddd;
            z-index: 99;
            cursor: pointer;
        }

        #productInfo .productInfo-com .flBox .h1 {
            width: 380px;
            height: 380px;
            /*background:#999;*/
            overflow: hidden;
        }

        #productInfo .productInfo-com .flBox .h2 img,
        #productInfo .productInfo-com .flBox .h1 img {
            width: 100%;
        }

        #productInfo .productInfo-com .flBox .h2 ul {
            margin-top: 28px;
            padding: 0 15px;
        }

        #productInfo .productInfo-com .flBox .h2 ul li {
            width: 60px;
            height: 60px;
            border: 1px solid #ddd;
            cursor: pointer;
            float: left;
            margin: 0 5px;
        }

        #productInfo .productInfo-com .flBox .h2 ul li.sele {
            border: 2px solid #e54544;
        }

        #productInfo .productInfo-com .frBox {
            /*height:600px;*/
            width: 434px;
        }

        #productInfo .productInfo-com .frBox .h1 {
            font-size: 16px;
            font-weight: bold;
            margin-top: 10px;
        }

        #productInfo .productInfo-com .frBox .h1 .otowotb {
            float: left;
            width: 50px;
            height: 18px;
            line-height: 18px;
            font-size: 12px;
            border-radius: 18px;
            background: #f6f7f9;
            color: #b0b0b0;
            font-weight: initial;
            text-align: center;
            margin-right: 8px;
        }

        #productInfo .productInfo-com .frBox .h2 {
            height: 36px;
            line-height: 34px;
            border: 1px dashed #ddd;
            margin-top: 18px;
            border-left: 0;
            border-right: 0;
            color: #b0b0b0;
            font-size: 14px;
        }

        #productInfo .productInfo-com .frBox .h2 .sharebox {
            font-size: 12px;
            color: #000;
        }

        #productInfo .productInfo-com .frBox .h3 {
            height: 82px;
            line-height: 74px;
            padding-bottom: 8;
            font-size: 42px;
            font-weight: bold;
        }

        #productInfo .productInfo-com .frBox .h4 {
            border: 1px solid #ddd;
            height: 70px;
            margin-bottom: 18px;
        }

        #productInfo .productInfo-com .frBox .h4 .y1 {
            float: left;
            text-align: center;
            width: 25%;
            height: 68px;
            border-right: 1px solid #f1f1f1;
            padding-top: 20px;
            color: #7f7f7f;
            cursor: pointer;
        }

        #productInfo .productInfo-com .frBox .h4 .y1:hover {
            background: #f5f5f5;
        }

        #productInfo .productInfo-com .frBox .h4 .y1:last-of-type {
            border-right: 0;
        }

        #productInfo .productInfo-com .frBox .h4 .y1 .t1 {
            font-size: 18px;
            color: #000;
        }

        #productInfo .productInfo-com .frBox .h5 {
            padding-bottom: 5px;
            font-size: 14px;
            color: #7f7f7f;
        }

        #productInfo .productInfo-com .frBox .h5 .y1 {
            width: 72px;
            padding-top: 8px;
        }

        #productInfo .productInfo-com .frBox .h5 .y3,
        #productInfo .productInfo-com .frBox .h5 .y2 {
            width: 360px;
        }

        #productInfo .productInfo-com .frBox .h5 .y2 li {
            float: left;
            width: 40px;
            height: 40px;
            /*background:#f5f5f5;*/
            margin-right: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            cursor: pointer;
            overflow: hidden;
        }

        #productInfo .productInfo-com .frBox .h5 li .hPic {
            width: 100%;
        }

        #productInfo .productInfo-com .frBox .h5 .y3 li {
            position: relative;
            float: left;
            /*width:30px;*/
            padding: 0 5px;
            height: 30px;
            text-align: center;
            line-height: 28px;
            /*background:#f5f5f5;*/
            margin-right: 10px;
            margin-bottom: 10px;
            border: 2px solid transparent;
            cursor: pointer;
            overflow: hidden;
        }
        /*否则hover时宽度变粗，样式有问题*/
        #productInfo .productInfo-com .frBox .h5 .y3 li:before{
            content :"";
            display: inline-block;
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            border: 1px solid #ddd;
        }

        #productInfo .productInfo-com .frBox .h5 li {
            position: relative;
        }

        #productInfo .productInfo-com .frBox .h5 li .hitb {
            display: none;
            position: absolute;
            right: -1px;
            bottom: -1px;
        }

        #productInfo .productInfo-com .frBox .h5 .y3 li.sele,
        #productInfo .productInfo-com .frBox .h5 .y2 li.sele,
        #productInfo .productInfo-com .frBox .h5 .y3 li:hover,
        #productInfo .productInfo-com .frBox .h5 .y2 li:hover {
            border: 2px solid #e54544;
        }
        #productInfo .productInfo-com .frBox .h5 .y3 li:hover:before{
            border-color: transparent;
        }

        #productInfo .productInfo-com .frBox .h5 .y3 li.sele .hitb,
        #productInfo .productInfo-com .frBox .h5 .y2 li.sele .hitb {
            display: block;
        }

        #productInfo .productInfo-com .frBox .hBtn {
            width: 210px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            background: #10389c;
            color: #fff;
            border-radius: 3px;
            font-size: 18px;
            margin-top: 40px;
            cursor: pointer;
        }

        #productInfo .productInfo-com .frBox .hBtn:hover {
            opacity: 0.8;
        }

        #productInfo .productInfo-com2 {
            width: 978px;
            padding: 0 40px 40px 40px;
            border: 1px solid #ddd;
            background: #fff;
            margin: 30px 0;
        }

        #productInfo .productInfo-com2 .hTitle {
            height: 60px;
            line-height: 60px;
            font-size: 18px;
            /*font-weight:bold;*/
            margin-left: -18px;
        }

        #productInfo .productInfo-com2 .box table,
        #productInfo .productInfo-com2 .box img {
            width: 100% !important;
            margin-left: 0 !important;
            padding-left: 0 !important;
        }

        #productInfo .productInfo-com2 .box {
            padding-top: 34px;
        }

        #productInfo .productInfo-com2 .box2 {
            padding-bottom: 40px;
            border-bottom: 1px dashed #eee;
        }

        #productInfo .productInfo-com2 .h1 {
            padding: 30px 0 24px 0;
            font-size: 18px;
            font-weight: bold;
        }

        #productInfo .productInfo-com2 .h2 {
            display: inline-block;
            font-size: 12px;
            line-height: 26px;
            width: 114px;
        }

        #productInfo .productInfo-com2 .h3 {
            display: inline-block;
            font-size: 12px;
            line-height: 26px;
            color: #7f7f7f;
        }

        #productInfo .conpanyFrBox {
            width: 240px;
            /*height:384px;*/
            background: #fff;
            border: 1px solid #ddd;
            background-image: -moz-linear-gradient(-90deg, #f8f3ec 0%, #ffffff 30%);
            background-image: -webkit-linear-gradient(-90deg, #f8f3ec 0%, #ffffff 30%);
            background-image: -ms-linear-gradient(-90deg, #f8f3ec 0%, #ffffff 30%);
            padding: 15px 15px 0 15px;
        }

        #productInfo .conpanyFrBox .h1 {
            width: 54px;
            height: 54px;
            border-radius: 50%;
            border: 1px solid #ddd;
            overflow: hidden;
            background: #fff;
            margin-right: 12px;
        }

        #productInfo .conpanyFrBox .h1 img {
            width: 100%;
        }

        #productInfo .conpanyFrBox .h2 {
            width: 140px;
            font-size: 14px;
            font-weight: bold;
        }

        #productInfo .conpanyFrBox .txt2,
        #productInfo .conpanyFrBox .txt {
            border-bottom: 1px dashed #ddd;
            color: #b0b0b0;
            padding: 25px 3px 14px 6px;
        }

        #productInfo .conpanyFrBox .txt2 {
            border-bottom: none;
            padding-top: 0;
            padding-bottom: 0;
        }

        #productInfo .conpanyFrBox .h3 {
            color: #000;
            height: 16px;
            line-height: 16px;
        }

        #productInfo .conpanyFrBox .h3 img {
            width: 23px;
            height: 16px;
            margin-right: 5px;
        }

        #productInfo .conpanyFrBox .h4 {
            color: #000;
            padding: 18px 0 14px 0;
            font-size: 14px;
        }

        #productInfo .conpanyFrBox .h6,
        #productInfo .conpanyFrBox .h5 {
            line-height: 22px;
        }

        #productInfo .conpanyFrBox .mr4 {
            margin-right: 4px;
        }

        #productInfo .conpanyFrBox .i {
            margin: 0 10px;
            display: inline-block;
            width: 1px;
            height: 10px;
            background: #ddd;
        }

        #productInfo .conpanyFrBox .txt2 .h6 {
            line-height: 35px;
        }

        #productInfo .conpanyFrBox .hBtn {
            height: 30px;
            border: 1px solid #ddd;
            border-radius: 4px;
            text-align: center;
            line-height: 28px;
            font-size: 14px;
            cursor: pointer;
            background: #f6f7f9;
        }

        #productInfo .conpanyFrBox .hBtn img {
            margin: -4px 5px 0 0;
        }

        #productInfo .conpanyFrBox .hBtn:hover {
            background: #f2f2f2;
        }

        #productInfo .conpanyFrBox .h8 {
            display: block;
            text-align: center;
            color: #10389c;
            padding: 40px 0 10px 0;
        }

        #picBoxs {
            display: block;
            width: 380px;
            height: 380px;
            position: relative;
        }

        #float-box {
            display: none;
            width: 160px;
            height: 160px;
            position: absolute;
            background: #fff;
            border: 4px solid #ddd;
            filter: alpha(opacity=50);
            opacity: 0.5;
        }

        #big-box {
            display: none;
            position: absolute;
            top: 0;
            left: 400px;
            width: 430px;
            height: 430px;
            overflow: hidden;
            border: 4px solid #ddd;
            z-index: 101;
            -webkit-transition: all .4s;
            -moz-transition: all .4s;
            -o-transition: all .4s;
            transition: all .4s;
            background: #fff;
        }

        #big-box img {
            position: absolute;
            z-index: 5;
            width: 800px;
        }

        .location li {
            float: left;
            padding-left: 5px;
        }
    </style>
</head>
<body id="goodsInfo" class="lang_en w_1200">
<div id="nav" style="height: auto;">
    <div id="new-top-nav" class="wide-wrap">
        <div class="wide" style="width: 1240px;min-width: 1240px;margin: 0 auto">
            <!-- 顶部左侧 - 4个下拉选 -->
            <el-menu :default-active="activeTopNavIndex" class="el-menu-demo" mode="horizontal"
                     @select="handleTopNavSelect">
                <el-submenu index="8" class="fr no-arrow" v-if="languageList.length>0">
                    <template slot="title">
                        Language
                    </template>
                    <el-menu-item index="7-1" v-for="language in languageList">
                        <a rel="nofollow" @click="changeLang(language.shortName)">
                            {{language.displayName}}
                        </a>
                    </el-menu-item>
                </el-submenu>

                </el-menu-item>
                <el-menu-item index="7" class="fr">
                    <a href="/home/usr_UsrConsult_listView" target="_blank">
                        <s:text name="RFQ"/>
                    </a>
                </el-menu-item>
            </el-menu>
        </div>
    </div>
</div>
<script src="/home/v2/static/lang/element/en.js"></script>
<script>

    ELEMENT.locale(ELEMENT.lang.en)
    var sysConfig = {
        baseImageUrl: "https://image.shoestp.com",
        currency_symbol: "$",
        current_language: "en",
    }
    var messages = {
        shoestp: null
    }
    var nav = new Vue({
        el: "#nav",
        data() {
            return {
                activeTopNavIndex: 1, //默认选中的web-top澳航栏
                topSearchBarCategory: 0, //搜索 分类前的下拉选
                language: "en",
                languageList: [],
                sysConfig: {
                    baseImageUrl: "https://image.shoestp.com",
                    currency_symbol: "$",
                    current_language: "en",
                },
                search: {
                    keyword: "",
                    typeList: [
                        {
                            label: "Product",
                            value: 0
                        },
                        {
                            label: "Suppiler",
                            value: 1
                        }
                    ]
                }
            }
        }, computed: {
            _language: function () {
                for (var key in this.$data.languageList) {
                    if (this.$data.languageList[key]["shortName"] == this.$data.language) {
                        return this.$data.languageList[key]["displayName"]
                    }
                }
                return "-1"
            },
            _favorite_count: function () {
                if (this.sysConfig.user) {
                    return this.sysConfig.user.favorite_count
                }
                return 0
            },
            _inquiry_count: function () {
                if (this.sysConfig.user) {
                    return this.sysConfig.user.inquiry_count
                }
                return 0
            },
            _shopping_cart_count: function () {
                if (this.sysConfig.user) {
                    return this.sysConfig.user.shopping_cart_count
                }
                return 0
            }

        }, mounted() {
            var self = this
            axios({
                url: "/home/plt_PltConfig_getSysConfig"
            }).then(function (res) {
                if (res.data.ret && res.data.ret == 1) {
                    sysConfig = res.data.result
                    Vue.set(self, "language", res.data.result.current_language)
                    Vue.set(self, "sysConfig", res.data.result)
                    Vue.set(self, "languageList", res.data.result.languages)
                } else {
                    console.error("ERR::FLAG")
                }
            }).catch(function (err) {
                console.error(err)
                console.error("ERR::FLAG")
            })
        },
        methods: {
            searchClick() {
                window.location.href = "/home/pdt_PdtProduct?Keyword=" + this.search.keyword
                    + "&v=2&searchtype=" + this.topSearchBarCategory
            },
            handleTopNavSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            changeLang(lang) {
                var self = this
                axios({
                    url: "/home/plt_PltConfig_changeLanguage",
                    method: "get",
                    params: {
                        request_locale: lang
                    }
                }).then(function (res) {
                    if (res.data.ret && res.data.ret == 1) {
                        location.reload();
                    } else {
                        console.error("ERR::FLAG")
                    }
                })

            },
            handleLanguageSelect(e) {
                console.log("click")
                console.log(e.target.dataset.language)
                console.log(e.currentTarget)
                if (e.target && e.target.dataset && e.target.dataset.language) {
                    this.language = e.target.dataset.language;
                }
            }
        }
    })
</script>
<div style="height: 33px;">
</div>
<jsp:include page="/home/template/web-top.jsp"></jsp:include>
<%--全局登录弹框--%>
<jsp:include page="/home/v3/login-box.jsp"></jsp:include>
<div id="productInfo">
    <index-top></index-top>

    <!-- <div class="companyTop1">
        <div class="box w1240 flexSb">
            <div class="flexCc">
                <div class="h1">{{productinfocom.supName}}</div>
                <div class="h2"><img src="images/icon-love.png"/> Favorite Supplier</div>
            </div>
            <div class="flexCc">
                <div class="h3 flexCc" v-if="productinfocom.type==4"><img src="images/icon-o2ot.png"/></div>
                <div class="h4 flexCc"><img src="images/icon-jinpai.png"/></div>
                <img class="h5" src="images/icon-renzheng.png" alt="" />
            </div>
        </div>

    </div>
    <div class="companyTop2">
        <div class="w1240">
            <div class="h1 flexCc">
                <img :src="image(productinfocom.logo)" alt="" />
            </div>
            <div class="h2">{{productinfocom.supName}}</div>
        </div>
    </div>
    <div class="companyTop3">
        <ul class="w1240 flexCc">
            <li class="sele"><a href="">Company   Home</a></li>
            <li><a href="">Product   Center </a></li>
            <li><a href="">Company   Profile</a></li>
            <li><a href="">Contact   Us</a></li>
        </ul>
    </div>-->


    <div class="w1240">
        <!--分级导航-->
        <div class="topNav">
            <!-- <div class="h1"><a href="/">Home</a><i class="el-icon-arrow-right"></i></div>

            <div class="h1"><a class="now" href="/"> All product</a></div> -->
            <ul class="location">
                <li><s:text name="Global.Your_Location"/>:</li>
                <li><a href="/"><s:text name="Global.Home"/></a>&nbsp;></li>

                <li v-for="(v,index) in productinfocom.breadcrumbNav" v-if="productinfocom.breadcrumbNav"
                    :key="v.pkey"
                    :class="(index===productinfocom.breadcrumbNav.length-1)?'xmgLast':''">
                    <a :href="'/home/pdt_PdtProduct?cated='+v.pkey">{{v.name}}</a>
                    <span v-if="index != productinfocom.breadcrumbNav.length-1">></span>
                </li>
            </ul>
        </div>
        <!--分级导航 end-->


        <div class="clearfix">
            <div class="fl">
                <div class="productInfo-com clearfix">
                    <div class="flBox fl">
                        <div style="position:relative;height:0;">
                            <div class="hliketb flexCc" @click="addfav">
                                <img src="/home/v3/static/images/productInfo/hliketb1.png" alt=""
                                     v-show="!productinfocom.favorite"/>
                                <img src="/home/v3/static/images/productInfo/hliketb2.png" alt=""
                                     v-show="productinfocom.favorite"/>
                            </div>
                        </div>
                        <div class="wrap" id="picBoxs">
                            <div class="h1 flexCc" id="small-box">
                                <img :src="image(selePic)" alt="">
                                <div id="float-box"></div>
                            </div>
                            <div id="big-box">
                                <img :src="image(selePic)" alt="">
                            </div>
                        </div>

                        <div class="h2">
                            <ul class="clearfix">
                                <li class="flexCc" :class="selePicIndex==index?'sele':''"
                                    v-for="item,index in productinfocom.pdtImg"
                                    :data-lipic="item"
                                    :data-index="index"
                                    v-if="index<5"
                                    @mouseenter="selePicli">
                                    <img :src="image(item)" alt=""/>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <div class="frBox fr">
                        <div class="h1">
                            <div class="otowotb" v-if="productinfocom.type==4">
                                <img src="/home/v3/static/images/productInfo/icon-jinpai.png" alt=""/>
                                O2O
                            </div>
                            {{productinfocom.pdtName}}
                        </div>

                        <div class="h2">
                            <div class="fl">ID: {{productinfocom.pdtId}}</div>
                            <div class="sharebox fr">
                                <div class="fl"><s:text name="products.share"/> :</div>
                                <a href="javascript:;" class="shareTb shareTbtb1" @click="shareThis('facebook')"></a>
                                <a href="javascript:;" class="shareTb shareTbtb2" @click="shareThis('twitter')"></a>
                                <a href="javascript:;" class="shareTb shareTbtb3" @click="shareThis('googleplus')"></a>
                                <a href="javascript:;" class="shareTb shareTbtb4" @click="shareThis('pinterest')"></a>

                                <el-dropdown>
								  <span class="el-dropdown-link">
								   	<div class="shareTb shareTbtb0"></div>
								  </span>
                                    <el-dropdown-menu slot="dropdown">
                                        <div @click="shareThis('delicious')">
                                            <el-dropdown-item><a href="javascript:;" class="shareTb shareTbtb5"></a>delicious
                                            </el-dropdown-item>
                                        </div>
                                        <div @click="shareThis('digg')">
                                            <el-dropdown-item><a href="javascript:;" class="shareTb shareTbtb6"></a>digg
                                            </el-dropdown-item>
                                        </div>
                                        <div @click="shareThis('reddit')">
                                            <el-dropdown-item><a href="javascript:;" class="shareTb shareTbtb7"></a>reddit
                                            </el-dropdown-item>
                                        </div>
                                        <div @click="shareThis('stumbleupon')">
                                            <el-dropdown-item><a href="javascript:;" class="shareTb shareTbtb8"></a>stumbleupon
                                            </el-dropdown-item>
                                        </div>
                                        <div @click="shareThis('linkedin')">
                                            <el-dropdown-item><a href="javascript:;" class="shareTb shareTbtb9"></a>linkedin
                                            </el-dropdown-item>
                                        </div>
                                        <div @click="shareThis('vk')">
                                            <el-dropdown-item><a href="javascript:;" class="shareTb shareTbtb10"></a>vk
                                            </el-dropdown-item>
                                        </div>
                                    </el-dropdown-menu>
                                </el-dropdown>
                            </div>
                        </div>

                        <div class="h3">{{productinfocom.currency_unit}} {{productinfocom.currency_symbol}}{{price}}
                        </div>

                        <div class="h4" v-if="tpView">
                            <div class="y1" v-for="item,index in tpView">
                                <div class="t1">{{productinfocom.currency_symbol}}{{item.price}}</div>
                                <div>＞＝{{item.count}}</div>
                            </div>
                        </div>


                        <div class="h5 clearfix">
                            <div class="y1 fl"><s:text name="Global.Colour"/>:</div>
                            <ul class="y2 fr clearfix">
                                <li class="flexCc" :class="seleColorIndex==index?'sele':''"
                                    v-for="item,index in productinfocom.spec"
                                    @click="seleColorli" :data-index="index"
                                    :data-lipic="item[0].img">
                                    <img class="hPic" :src="image(item[0].img)" :title="item[0].color"/>
                                    <img class="hitb" src="/home/v3/static/images/productInfo/icon-sele01.png" alt=""/>
                                </li>
                            </ul>
                        </div>

                        <div class="h5 clearfix">
                            <div class="y1 fl">
                                <span v-if="sizeList[0] && sizeList[0].sizeType == 1">USA</span>
                                <span v-if="sizeList[0] && sizeList[0].sizeType == 2">EUR</span>
                                <s:text name="Global.Size"/>:
                            </div>
                            <ul class="y3 fr clearfix">
                                <li :class="seleSizeIndex==index?'sele':''" v-for="item,index in sizeList"
                                    @click="seleSizeli" :data-index="index" :data-price="item.price">
                                    {{item.size}}
                                    <img class="hitb" src="/home/v3/static/images/productInfo/icon-sele01.png" alt=""/>
                                </li>
                            </ul>
                        </div>

                        <div class="hBtn" @click="addRFQ"><s:text name="inquiry"/></div>

                    </div>
                </div>


                <div class="productInfo-com2">
                    <div class="hTitle"><s:text name="Global.Product_Details"/></div>
                    <div class="box2">
                        <div class="h1"><s:text name="Global.Product_Specifications"/></div>
                        <div class="clearfix">
                            <div class="fl" style="width:50%;" v-for="item,index in productinfocom.specifications">
                                <div class="h2" style="font-size:16px;font-weight:bold;width:auto;padding-bottom:3px;">
                                    {{index}} :
                                </div>&nbsp;&nbsp;
                                <div class="h3" v-for="v in item" style="font-size:16px;">{{v}}</div>
                            </div>
                        </div>
                    </div>
                    <div class="box">
                        <div style="background-color: #dee2eb; width: 100%; height: 50px;"
                             v-if="productinfocom.description != null && productinfocom.desModule.length>0">
                            <p style="line-height: 45px; font-size: 26px;">&nbsp;Product description</p>
                        </div>
                        <br/>
                        <div v-html="productinfocom.description" style="width:100%;"></div>
                        <div v-html="productinfocom.desModule[0]" style="width:100%;"></div>
                        <div v-html="productinfocom.desModule[1]" style="width:100%;"></div>
                        <div v-html="productinfocom.desModule[2]" style="width:100%;"></div>
                        <!--{{productinfocom.description}}-->
                    </div>

                </div>


            </div>


            <div class="fr">
                <div class="conpanyFrBox">
                    <div class="flexSt">
                        <div class="h1 flexCc">
                            <img :src="image(productinfocom.logo)" alt=""/>
                        </div>
                        <div class="h2">
                            <a :href="'/home/usr_UsrSupplier_gtSupIndex?pkey='+productinfocom.supId">{{productinfocom.supName}}</a>
                        </div>
                    </div>

                    <div class="txt">
                        <div class="h3"><img src="/home/v3/static/images/productInfo/cn.png" alt=""/>CN</div>
                        <div class="h4">Shose</div>
                        <div class="h5">
                            <img class="mr4" src="/home/v3/static/images/productInfo/icon-renzheng.png" alt=""/>Certificate
                            <div class="i"></div>
                            <img class="mr4" src="/home/v3/static/images/productInfo/icon-jinpai.png" alt=""/>SVS
                        </div>
                        <div class="h6">
                            <div>
                                <div class="ww42">R&D：</div>
                                <el-rate v-model="5.0" disabled></el-rate>
                            </div>
                            <div>
                                <div class="ww42">Output：</div>
                                <el-rate v-model="5.0" disabled></el-rate>
                            </div>
                            <div>
                                <div class="ww42">Scale：</div>
                                <el-rate v-model="5.0" disabled></el-rate>
                            </div>
                        </div>
                    </div>

                    <div class="txt2">
                        <div class="h6">China</div>
                    </div>

                    <div class="hBtn" @click="contactSupplier"><a
                            href="javascript: void(0);"><img
                            src="/home/v3/static/images/productInfo/icon-youjian.png" alt=""/>Contact Supplier</a></div>

                    <a :href="'/home/usr_UsrSupplier_gtSupInfo?pkey='+productinfocom.supId" class="h8">View Company
                        Profile</a>

                </div>
            </div>

        </div>
    </div>
    <index-bottom></index-bottom>
</div>


<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>


<script type="text/javascript">
    $('html').on('click', '#signin_close', function () {
        $('#signin_module').remove();
        global_obj.div_mask(1);

    })
</script>

<script>

    new Vue({
        el: '#productInfo',
        data: {
            selePic: '',
            selePicIndex: 0,
            seleColorIndex: 0,
            seleSizeIndex: -1,
            productinfocom:${goodsInfo},
            sizeList: [],
            price: null,
            tpView: [],
        },
        mounted() {
            var that = this
            //this.productinfo();
            that.selePic = that.productinfocom.pdtImg[0];

            var firstKey = Object.keys(that.productinfocom.spec)[0];
            that.seleColorIndex = firstKey;
            that.sizeList = that.productinfocom.spec[firstKey];
            console.log(that.sizeList[0])

            if (that.productinfocom.tpView) {
                that.tpView = that.productinfocom.tpView
                var arr = new Array;
                for (var i = 0; i < that.tpView.length; i++) {
                    arr.push(that.tpView[i].price);
                }
                arr.sort(function (a, b) {
                    return b - a;
                })
                if(that.productinfocom.tpView.length <= 1){
                	that.price = that.productinfocom.price;
                }else{
                	that.price = arr[arr.length - 1] + '-' + arr[0];
                }
            } else {
                that.price = that.productinfocom.price;
                that.tpView = false
            }
        },
        methods: {
            contactSupplier(){
                var jumpUrl = "/home/usr_UsrSupplier_goContactSupplier?supplierPkey=" + this.productinfocom.supId + '&backUrl=' + window.location.href;;
                util_function_obj.supplierCantEnter(this, jumpUrl);
            },
            // 选择放大镜的图片
            selePicli: function (e) {
                this.selePicIndex = e.currentTarget.dataset.index;
                this.selePic = e.currentTarget.dataset.lipic;
            },
            // 选择颜色
            seleColorli: function (e) {
                var index = e.currentTarget.dataset.index;
                this.selePic = e.currentTarget.dataset.lipic;
                var firstKey = index
                this.seleColorIndex = index;
                this.sizeList = this.productinfocom.spec[firstKey]

            },
            // 选择尺寸
            seleSizeli: function (e) {
                this.seleSizeIndex = e.currentTarget.dataset.index;
                //this.price = e.currentTarget.dataset.price;
            },


            addfav: function () {
                if (!sysConfig || !sysConfig.user) {
                    // user_obj.set_form_sign_in('', window.location.href, 1);
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }
                var self = this
                axios({
                    url: "/home/usr_UsrFavorites_addFavorite",
                    data: Qs.stringify({
                        pdtPkey: self.productinfocom.pdtId
                    }), method: "post"
                }).then(function (data) {
                    if (data.data) {
                        if (data.data.ret && data.data.ret == 1) {
                            self.productinfocom.favorite = data.data.type !== 1
                            self.productinfocom.favorite_count = data.data.number
                        }else{
                            self.$message.error(data.data.msg || "Add to favorite error, please try again later");
                        }
                    }
                }).catch((error)=>{
                    self.$message.error(error || 'Network error,please try again later');
                })
            },
            /* productinfo:function(){
                var that = this
                    axios.get('/home/pdt_PdtProduct_gtProductsInfo1', {
                          params: {
                              id:9226
                          }
                        }).then(function(res){
                          if (res.data.ret == 1) {
                            that.productinfocom=res.data.result;
                            that.selePic = that.productinfocom.pdtImg[0];

                           var firstKey = Object.keys(res.data.result.spec)[0];
                           that.seleColorIndex=firstKey;
                           that.sizeList=that.productinfocom.spec[firstKey];
                           if(res.data.result.tpView){
                               var tpViewl = res.data.result.tpView
                               that.tpView=tpViewl;
                               that.price= tpViewl[tpViewl.length-1].price + '-' + tpViewl[0].price;
                           }else{
                               that.price=res.data.result.price;
                               that.tpView=false
                           }

                          }
                        }).catch(function(err){
                          console.log(err)
                        })
            }, */
            image: function (v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return "https://image.shoestp.com" + v + params
            },
            shareThis: function (type) {
                var image = back_url = encode_url = "";
                url = window.location.href;
                if (url.indexOf("#") > 0) {
                    url = url.substring(0, url.indexOf("#"));
                }
                image = this.productinfocom.pdtImg[0]
                var e_url = encodeURIComponent(url);
                var title = encodeURIComponent(this.productinfocom.pdtName);
                switch (type) {
                    case "delicious":
                        back_url = "https://delicious.com/post?title=" + title + "&url=" + e_url;
                        break;
                    case "digg":
                        back_url = "http://digg.com/submit?phase=2&url=" + e_url + "&title=" + title
                            + "&bodytext=&topic=tech_deals";
                        break;
                    case "reddit":
                        back_url = "http://reddit.com/submit?url=" + e_url + "&title=" + title;
                        break;
                    case "furl":
                        back_url = "http://www.furl.net/savedialog.jsp?t=" + title + "&u=" + e_url;
                        break;
                    case "rawsugar":
                        back_url = "http://www.rawsugar.com/home/extensiontagit/?turl=" + e_url + "&tttl="
                            + title;
                        break;
                    case "stumbleupon":
                        back_url = "http://www.stumbleupon.com/submit?url=" + e_url + "&title=" + title;
                        break;
                    case "blogmarks":
                        break;
                    case "facebook":
                        back_url = "http://www.facebook.com/share.php?src=bm&v=4&u=" + e_url + "&t=" + title;
                        break;
                    case "technorati":
                        back_url = "http://technorati.com/faves?sub=favthis&add=" + e_url;
                        break;
                    case "spurl":
                        back_url = "http://www.spurl.net/spurl.php?v=3&title=" + title + "&url=" + e_url;
                        break;
                    case "simpy":
                        back_url = "http://www.simpy.com/simpy/LinkAdd.do?title=" + title + "&href=" + e_url;
                        break;
                    case "ask":
                        break;
                    case "google":
                        back_url = "http://www.google.com/bookmarks/mark?op=edit&output=popup&bkmk=" + e_url
                            + "&title=" + title;
                        break;
                    case "netscape":
                        back_url = "http://www.netscape.com/submit/?U=" + e_url + "&T=" + title + "&C=";
                        break;
                    case "slashdot":
                        back_url = "http://slashdot.org/bookmark.pl?url=" + url + "&title=" + title;
                        break;
                    case "backflip":
                        back_url = "http://www.backflip.com/add_page_pop.ihtml?title=" + title + "&url="
                            + e_url;
                        break;
                    case "bluedot":
                        back_url = "http://bluedot.us/Authoring.aspx?u=" + e_url + "&t=" + title;
                        break;
                    case "kaboodle":
                        back_url = "http://www.kaboodle.com/za/selectpage?p_pop=false&pa=url&u=" + e_url;
                        break;
                    case "squidoo":
                        back_url = "http://www.squidoo.com/lensmaster/bookmark?" + e_url;
                        break;
                    case "twitter":
                        back_url = "https://twitter.com/intent/tweet?status=" + title + ":+" + e_url;
                        break;
                    case "pinterest":
                        back_url = "http://pinterest.com/pin/create/button/?url=" + e_url + "&media=" + image
                            + "&description=" + title;
                        break;
                    case "vk":
                        back_url = "http://vk.com/share.php?url=" + url;
                        break;
                    case "bluedot":
                        back_url = "http://blinkbits.com/bookmarklets/save.php?v=1&source_url=" + e_url
                            + "&title=" + title;
                        break;
                    case "blinkList":
                        back_url = "http://blinkbits.com/bookmarklets/save.php?v=1&source_url=" + e_url
                            + "&title=" + title;
                        break;
                    case "linkedin":
                        back_url = "http://www.linkedin.com/cws/share?url=" + e_url + "&title=" + title;
                        break;
                    case "googleplus":
                        back_url = "https://plus.google.com/share?url=" + e_url;
                        break;
                }
                window.open(back_url, "bookmarkWindow");
            },
            addRFQ: function () {
                if (!sysConfig || !sysConfig.user) {
                    // user_obj.set_form_sign_in('', window.location.href, 1);
                    util_function_obj.alertWhenNoLogin(this,"/home/usr_UsrConsult_productPublishView?product_id=" + this.productinfocom.pdtId+ '&backUrl=' + window.location.href);
                    return
                }
                window.location = '/home/usr_UsrConsult_productPublishView?product_id=' + this.productinfocom.pdtId + '&backUrl=' + window.location.href
            },
        },

    })
</script>


<script type="text/javascript">
    //		放大镜
    var oWrap = document.getElementById('picBoxs');
    var smallDiv = document.getElementById('small-box');
    var move = document.getElementById('float-box');
    var bigDiv = document.getElementById('big-box');

    //2.
    smallDiv.onmousemove = function (ev) {
        var oEvent = ev || event;
        var iScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        var iScrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
        var disX = oEvent.clientX + iScrollLeft - move.offsetWidth / 2 - oWrap.offsetLeft;
        var disY = oEvent.clientY + iScrollTop - move.offsetHeight / 2 - oWrap.offsetTop;
        //  console.log(oWrap.offsetLeft)
        if (disX < 0) {
            disX = 0;
        } else if (disX > smallDiv.offsetWidth - move.offsetWidth) {
            disX = smallDiv.offsetWidth - move.offsetWidth;
        }
        ;
        if (disY < 0) {
            disY = 0;
        } else if (disY > smallDiv.offsetHeight - move.offsetHeight) {
            disY = smallDiv.offsetHeight - move.offsetHeight;
        }
        ;
        move.style.left = disX + 'px';
        move.style.top = disY + 'px';
        /*算出move块在X轴的移动距离与总的移动距离的比例*/
        var _pageX = disX / (smallDiv.offsetWidth - move.offsetWidth);
//	        console.log(_pageX)   /*这是一个0~1之间的数*/
        /*算出move块在Y轴的移动距离与总的移动距离的比例*/
        var _pageY = disY / (smallDiv.offsetHeight - move.offsetHeight);
        /*求出大图片在X轴的移动距离*/
        var mX = _pageX * (bigDiv.children[0].offsetWidth - bigDiv.offsetWidth);
        /*求出大图片在Y轴的移动距离*/
        var mY = _pageY * (bigDiv.children[0].offsetHeight - bigDiv.offsetHeight);
        bigDiv.children[0].style.left = -mX + 'px';
        bigDiv.children[0].style.top = -mY + 'px';
    };

    //3.
    smallDiv.onmouseover = function (ev) {
        bigDiv.style.display = 'block';
        move.style.display = 'block';
        smallDiv.onmousemove(); //兼容IE
    };
    smallDiv.onmouseout = function () {
        bigDiv.style.display = 'none';
        move.style.display = 'none';
    };
</script>


</body>
