<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
</head>
<body class="new-style-page w_1200 gjhMain">
<!-- 顶部nav栏 -->
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

    <!-- 顶部搜索栏 -->
    <div id="new-top-search" class="wide-wrap">
        <div class="wide new-top-search">
            <!-- 左侧 - logo -->
            <div class="logo-box">
                <a href="/"><img src="/home/v2/static/images/nav/logo.png" alt="logo"></a>
            </div>

            <!-- 搜索条 -->
            <div class="top-input-bar">
                <!-- 类型 下拉选择 -->
                <div class="cotegory-select-box">
                    <el-select v-model="topSearchBarCategory"
                               placeholder="<s:text name="plesaeSelect"/>"
                               popper-class="top-search-bar-cotegory-select-dropdown">
                        <el-option v-for="item in search.typeList" :key="item.value"
                                   :label="item.label"
                                   :value="item.value">
                        </el-option>
                    </el-select>
                </div>
                <div class="input-box">
                    <input type="text" @keyup.enter="searchClick" v-model="search.keyword">
                </div>
                <div class="btn-search" @click="searchClick">
                    <i class="el-icon-search"></i>
                </div>
            </div>

            <a class="btn-get-quotations" href="/home/usr_UsrConsult_listView">
                <s:text name="Get_Quotations"/>
            </a>
            <div class="language-select" v-if="languageList.length>0">
                <img src="/home/v2/static/images/nav/icon-global.png" alt="">
                <br>
                {{_language}} <i class="el-icon-arrow-down el-icon--right"></i>
                <el-select v-model="language" placeholder="<s:text name="plesaeSelect"/>"
                           class="top-language-select-input"
                           popper-class="top-language-select-box"
                           @change="changeLang"
                >
                    <el-option v-for="language in languageList" :key="language.shortName"
                               :label="language.displayName"
                               :value="language.shortName">
                    </el-option>
                </el-select>
            </div>
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
<div style="height: 114px;">
</div>

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
