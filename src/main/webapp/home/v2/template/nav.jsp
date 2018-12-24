<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
</head>
<body class="new-style-page w_1200">
<!-- 顶部nav栏 -->
<div id="nav">
    <div id="new-top-nav">
        <div class="wide">
            <!-- 顶部左侧 - 4个下拉选 -->
            <el-menu :default-active="activeTopNavIndex" class="el-menu-demo" mode="horizontal"
                     @select="handleTopNavSelect">
                <el-submenu index="1" class="no-arrow new-top-nav-item">
                    <template slot="title"><s:text name="OEM"/>{{activeTopNavIndex}}</template>
                    <el-menu-item index="1-1">{{$t("Man")}}</el-menu-item>
                    <el-menu-item index="1-2">{{$t("WoMan")}}</el-menu-item>
                    <el-menu-item index="1-3">{{$t("Children")}}</el-menu-item>
                </el-submenu>
                <el-submenu index="2" class="no-arrow">
                    <template slot="title"><s:text name="WholeSale"/></template>
                    <el-menu-item index="2-1">{{$t("Man")}}</el-menu-item>
                    <el-menu-item index="2-2">{{$t("WoMan")}}</el-menu-item>
                    <el-menu-item index="2-3">{{$t("Children")}}</el-menu-item>
                </el-submenu>
                <el-submenu index="3" class="no-arrow">
                    <template slot="title"><s:text name="Trade_Show"/></template>
                    <el-menu-item index="3-1"><a
                            href="/country/Romania-Pantofi-en-gros/romania-index-ro.html"><s:text name="Romania"/></a></el-menu-item>
                </el-submenu>
                <el-submenu index="4" class="no-arrow">
                    <template slot="title"><s:text name="CROWDFUNDING"/></template>
                    <el-menu-item index="4-1"><a href="/home/Activity_Romania"><s:text name="Romania"/></a></el-menu-item>
                </el-submenu>

                <!-- 顶部右侧 - 收藏 -->
                <el-menu-item index="8" class="fr mr0">
                    <a href="/home/usr_UsrFavorites_myfavorite" target="_blank">
                        <img src="/home/v2/static/images/nav/icon-heart.png" alt="icon-heart"
                             style="position: relative;top: -2px;">
                    </a>
                </el-menu-item>
                <!-- 顶部右侧 - 询盘 -->
                <el-menu-item index="7" class="fr"><a href="/home/usr_UsrConsult_listView" target="_blank"><s:text name="inq"/></a>
                </el-menu-item>
                <!-- 顶部右侧 - 注册 -->
                <el-submenu index="6" class="fr">
                    <template slot="title"><s:text name="Register"/></template>
                    <el-menu-item index="6-1"><a href="/home/usr_UsrPurchase_sign" target="_blank"><s:text name="Buyer"/></a>
                    </el-menu-item>
                    <el-menu-item index="6-2"><a href="/home/usr_UsrSupplier_supplierEntry">{{$t("Supplier")}}</a>
                    </el-menu-item>
                </el-submenu>
                <!-- 顶部右侧 - 登录 -->
                <el-menu-item index="5" class="fr"><a href="/home/usr_UsrPurchase_sign" target="_blank"><s:text name="Login"/></a>
                </el-menu-item>
            </el-menu>
        </div>
    </div>

    <!-- 顶部搜索栏 -->
    <div id="new-top-search">
        <div class="wide new-top-search">
            <!-- 左侧 - logo -->
            <div class="logo-box">
                <a href="/"><img src="/home/v2/static/images/nav/logo.png" alt="logo"></a>
            </div>

            <!-- 搜索条 -->
            <div class="top-input-bar">
                <!-- 类型 下拉选择 -->
                <%--<div class="cotegory-select-box">--%>
                <%--<el-select v-model="topSearchBarCategory" placeholder="请选择"--%>
                <%--popper-class="top-search-bar-cotegory-select-dropdown">--%>
                <%--<el-option v-for="item in 6" :key="item" :label="item" :value="item">--%>
                <%--</el-option>--%>
                <%--</el-select>--%>
                <%--</div>--%>

                <!-- 输入框 -->
                <div class="input-box">
                    <input type="text" @keyup.enter="searchClick" v-model="search.keyword">
                </div>
                <!-- 搜索按钮 -->
                <div class="btn-search">
                    <i class="el-icon-search"></i>
                </div>
            </div>

            <a class="btn-get-quotations">
                <s:text name="Get_Quotations"/>
            </a>
            <!-- 多语言下拉选择 -->
            <div class="language-select" v-if="languageList.length>0">
                <img src="/home/v2/static/images/nav/icon-global.png" alt="">
                <br>
                {{_language}} <i class="el-icon-arrow-down el-icon--right"></i>
                <el-select v-model="language" placeholder="请选择" class="top-language-select-input"
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
<script>
    var sysConfig = null
    var messages = {
        shoestp: null
    }
    var i18n = new VueI18n({
        locale: 'shoestp', // set locale
        messages, // set locale messages
    })
    axios({
        url: "/home/v2/static/lang/${envConfig.lang}.json"
    }).then(function (res) {
        messages.shoestp = res.data
    })
    var nav = new Vue({
        el: "#nav", i18n,
        data() {
            return {
                activeTopNavIndex: 1, //默认选中的web-top澳航栏
                topSearchBarCategory: "2", //搜索 分类前的下拉选
                language: "en",
                languageList: [],
                search: {
                    keyword: ""
                }
            }
        }, computed: {
            _language: function () {
                for (var key in this.$data.languageList) {
                    if (this.$data.languageList[key]["shortName"] == this.$data.language) {
                        console.log(this.$data.languageList[key]["displayName"])
                        return this.$data.languageList[key]["displayName"]
                    }
                }
                return "-1"
            }
        }, mounted() {
            var self = this
            axios({
                url: "/home/plt_PltConfig_getSysConfig"
            }).then(function (res) {
                if (res.data.ret && res.data.ret == 1) {
                    sysConfig = res.data.result
                    Vue.set(self, "language", res.data.result.current_language)
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
                        // location.reload();
                        axios({
                            url: "/home/v2/static/lang/" + self.$data.language + ".json"
                        }).then(res => {
                            messages["shoestp"] = res.data
                        }).catch(function (err) {
                            console.log(err)
                            console.error("ERR::FLAG")

                        })
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
