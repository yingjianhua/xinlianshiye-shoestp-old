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
                    <template slot="title">OEM{{activeTopNavIndex}}</template>
                    <el-menu-item index="1-1">OEM1</el-menu-item>
                    <el-menu-item index="1-2">OEM2</el-menu-item>
                    <el-menu-item index="1-3">OEM3</el-menu-item>
                </el-submenu>
                <el-submenu index="2" class="no-arrow">
                    <template slot="title">WholeSale</template>
                    <el-menu-item index="2-1">WholeSale1</el-menu-item>
                    <el-menu-item index="2-2">WholeSale2</el-menu-item>
                    <el-menu-item index="2-3">WholeSale3</el-menu-item>
                </el-submenu>
                <el-submenu index="3" class="no-arrow">
                    <template slot="title">Trade Show</template>
                    <el-menu-item index="3-1">Trade Show1</el-menu-item>
                    <el-menu-item index="3-2">Trade Show2</el-menu-item>
                    <el-menu-item index="3-3">Trade Show3</el-menu-item>
                </el-submenu>
                <el-submenu index="4" class="no-arrow">
                    <template slot="title">Group Funding</template>
                    <el-menu-item index="4-1">Group Funding1</el-menu-item>
                    <el-menu-item index="4-2">Group Funding2</el-menu-item>
                    <el-menu-item index="4-3">Group Funding3</el-menu-item>
                </el-submenu>

                <!-- 顶部右侧 - 收藏 -->
                <el-menu-item index="8" class="fr mr0">
                    <a href="" target="_blank">
                        <img src="/home/v2/static/images/nav/icon-heart.png" alt="icon-heart"
                             style="position: relative;top: -2px;">
                    </a>
                </el-menu-item>
                <!-- 顶部右侧 - 询盘 -->
                <el-menu-item index="7" class="fr"><a href="" target="_blank">RFQ</a></el-menu-item>
                <!-- 顶部右侧 - 注册 -->
                <el-submenu index="6" class="fr">
                    <template slot="title">Register</template>
                    <el-menu-item index="6-1">Buyer</el-menu-item>
                    <el-menu-item index="6-2">Supplier</el-menu-item>
                </el-submenu>
                <!-- 顶部右侧 - 登录 -->
                <el-menu-item index="5" class="fr"><a href="" target="_blank">Login</a></el-menu-item>
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
                Get Quotations
            </a>
            <!-- 多语言下拉选择 -->
            <div class="language-select">
                <img src="/v2/static/images/nav/icon-global.png" alt="">
                <br>
                {{_language}} <i class="el-icon-arrow-down el-icon--right"></i>
                <el-select v-model="language" placeholder="请选择" class="top-language-select-input"
                           popper-class="top-language-select-box">
                    <el-option v-for="language in languageList" :key="language.shortName"
                               :label="language.displayName"
                               :value="language.shortName">
                    </el-option>
                </el-select>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/home/static/js/lang/en.js"></script>
<script>
    var sysConfig = null
    var messages = {
        en: lang_obj
    }

    // Create VueI18n instance with options
    var i18n = new VueI18n({
        locale: 'en', // set locale
        messages, // set locale messages
    })
    // Create a Vue instance with `i18n` option

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
                    self.$data.languageList = res.data.result.languages
                    // Vue.set(self, "language", res.data.result.current_language)
                    // Vue.set(self, "languageList", res.data.result.languages)
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
</script>
