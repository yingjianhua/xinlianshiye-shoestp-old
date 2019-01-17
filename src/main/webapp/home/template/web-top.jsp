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
<script src="https://js.fundebug.cn/fundebug.1.5.1.min.js"
        apikey="afbc9f957e7689049c3282fe7696d30e7cb260e0ce11c148c0cf9e31d4e802f5"></script>


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
<div id="nav">
    <div id="new-top-nav" class="wide-wrap">
        <div class="wide">
            <!-- 顶部左侧 - 4个下拉选 -->
            <el-menu :default-active="activeTopNavIndex" class="el-menu-demo" mode="horizontal"
                     @select="handleTopNavSelect">
                <el-submenu index="1" class="no-arrow new-top-nav-item">
                    <template slot="title">
                        <s:text name="OEM"/>
                    </template>
                    <el-menu-item index="1-1">
                        <a href="/home/pdt_PdtProduct?cated=373">
                            <s:text name="Men"/>
                        </a>

                    </el-menu-item>
                    <el-menu-item index="1-2">
                        <a href="/home/pdt_PdtProduct?cated=380">
                            <s:text name="WoMen"/>
                        </a>
                    </el-menu-item>
                    <el-menu-item index="1-3">
                        <a href="/home/pdt_PdtProduct?cated=387">
                            <s:text name="Children"/>
                        </a>
                    </el-menu-item>
                </el-submenu>
                <el-submenu index="2" class="no-arrow">
                    <template slot="title">WholeSale</template>
                    <el-menu-item index="2-1">
                        <a href="/home/pdt_PdtProduct?cated=373">
                            <s:text name="Men"/>
                        </a>
                    </el-menu-item>
                    <el-menu-item index="2-2">
                        <a href="/home/pdt_PdtProduct?cated=380">
                            <s:text name="WoMen"/>
                        </a>
                    </el-menu-item>
                    <el-menu-item index="2-3">
                        <a href="/home/pdt_PdtProduct?cated=387">
                            <s:text name="Children"/>
                        </a>
                    </el-menu-item>
                </el-submenu>
                <el-submenu index="3" class="no-arrow">
                    <template slot="title">
                        <s:text name="Trade_Show"/>
                    </template>
                    <el-menu-item index="3-1"><a
                            href="/country/Romania-Pantofi-en-gros/romania-index-ro.html">
                        <s:text name="Romania"/>
                    </a></el-menu-item>
                </el-submenu>
                <%--<el-submenu index="4" class="no-arrow">--%>
                <%--<template slot="title">--%>
                <%--<s:text name="CROWDFUNDING"/>--%>
                <%--</template>--%>
                <%--<el-menu-item index="4-1">--%>
                <%--<a href="/home/Activity_Romania_classifyactivity?category=373">--%>
                <%--Men's--%>
                <%--</a>--%>
                <%--</el-menu-item>--%>
                <%--<el-menu-item index="4-2">--%>
                <%--<a href="/home/Activity_Romania_classifyactivity?category=380">--%>
                <%--Women's--%>
                <%--</a>--%>
                <%--</el-menu-item>--%>
                <%--<el-menu-item index="4-3"><a--%>
                <%--href="/home/Activity_Romania_classifyactivity?category=387">Children</a>--%>
                <%--</el-menu-item>--%>
                <%--</el-submenu>--%>

                <!-- 顶部右侧 - 收藏 -->
                <el-menu-item index="8" class="fr mr0">
                    <a href="/home/usr_UsrFavorites_myfavorite" target="_blank">
                        <img src="/home/v2/static/images/nav/icon-heart.png" alt="icon-heart"
                             style="position: relative;top: -2px;">
                        <i class="fav_count imgnumber">
                            {{_favorite_count}}
                        </i>
                    </a>
                </el-menu-item>
                <!-- 顶部右侧 - 购物车 -->
                <el-menu-item index="9" class="fr">
                    <a href="/home/usr_UsrCart_cartshopping" target="_blank">
                        <img src="/home/v2/static/images/nav/icon_Shopping-Cart.png" alt="icon_Shopping-Cart"
                             style="position: relative;top: -2px;">
                        <i class="cart_count imgnumber">
                            {{_shopping_cart_count}}
                        </i>
                    </a>
                </el-menu-item>
                <!-- 顶部右侧 - 询盘 -->
                <el-menu-item index="7" class="fr">
                    <a href="/home/usr_UsrConsult_listView" target="_blank">
                        <s:text name="RFQ"/>
                        <i class="inq_count imgnumber">
                            {{_inquiry_count}}
                        </i>
                    </a>
                </el-menu-item>

                <!-- 顶部右侧 - 注册 -->
                <el-submenu index="6" class="fr">
                    <template slot="title"><s:text name="Register"/></template>
                    <el-menu-item index="6-1">
                        <a href="/home/usr_UsrPurchase_sign" target="_blank">
                            <s:text name="Buyer"/>
                        </a>
                    </el-menu-item>
                    <el-menu-item index="6-2">
                        <a href="/home/usr_UsrSupplier_supplierEntry">
                            <s:text name="Supplier"/>
                        </a>
                    </el-menu-item>
                </el-submenu>

                <!-- 顶部右侧 - 登录 -->
                <el-submenu index="5" class="fr no-arrow">
                    <template slot="title" v-if="!sysConfig.user">
                        <a href="/home/usr_UsrPurchase_sign" target="_blank">
                            <s:text name="Login"></s:text>
                        </a>
                    </template>
                    <template slot="title" v-if="sysConfig.user">
                        <a href="/home/usr_UsrPurchase_userIndex">{{sysConfig.user.name}}</a>
                    </template>
                    <el-menu-item index="5-1" v-if="sysConfig.user">
                        <a rel="nofollow" href="/home/usr_UsrPurchase_signOut">
                            <s:text name="sign_out"/>
                        </a>
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </div>
    </div>

</div>

<script>
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
            changeLang() {
                var self = this
                axios({
                    url: "/home/plt_PltConfig_changeLanguage",
                    method: "get",
                    params: {
                        request_locale: this.$data.language
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
    window.onscroll = function () {
        var sl = -Math.max(document.body.scrollLeft, document.documentElement.scrollLeft);
        document.getElementById('new-top-nav').style.left = sl + 'px';
        document.getElementById('new-top-search').style.left = sl + 'px';
    }
</script>
<script type="text/javascript">
    $(document).ready(function () {
        moment.locale('${env.curLanguage}');
        user_obj.sign_in_init();
    });
</script>

<style scope>
    .el-menu-item .imgnumber {
        height: 12px;
        min-width: 12px;
        background: #ff3c3c;
        border-radius: 8px;
        right: -6px;
        top: 4px;
        line-height: 13px;
        text-align: center;
        color: #fff;
        position: absolute;
    }

</style>
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
