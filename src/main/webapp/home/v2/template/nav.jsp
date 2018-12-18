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
                    <el-menu-item index="3-1"><a
                            href="/country/Romania-Pantofi-en-gros/romania-index-en.html">Romania</a></el-menu-item>
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
                <el-menu-item index="7" class="fr">
                    <a href="/home/usr_UsrConsult_listView" target="_blank">RFQ</a>
                </el-menu-item>
                <!-- 顶部右侧 - 注册 -->
                <el-submenu index="6" class="fr">
                    <template slot="title">Register</template>
                    <el-menu-item index="6-1"><a href="/home/usr_UsrPurchase_sign">Buyer</a></el-menu-item>
                    <el-menu-item index="6-2"><a href="/home/usr_UsrSupplier_supplierEntry">Supplier</a></el-menu-item>
                </el-submenu>
                <!-- 顶部右侧 - 登录 -->
                <el-menu-item index="5" class="fr"><a href="/home/usr_UsrPurchase_sign" target="_blank">Login</a>
                </el-menu-item>
            </el-menu>
        </div>
    </div>

    <!-- 顶部搜索栏 -->
    <div id="new-top-search">
        <div class="wide new-top-search">
            <!-- 左侧 - logo -->
            <div class="logo-box">
                <a href="/">
                    <img src="/home/v2/static/images/nav/logo.png" alt="logo">
                </a>

            </div>

            <!-- 搜索条 -->
            <div class="top-input-bar">

                <!-- 类型 下拉选择 -->
                <%--<div class="cotegory-select-box">--%>
                <%--<el-select v-model="topSearchBarCategory" placeholder="请选择"--%>
                <%--popper-class="top-search-bar-cotegory-select-dropdown">--%>
                <%--<el-option--%>
                <%--v-for="item in 6"--%>
                <%--:key="item"--%>
                <%--:label="item"--%>
                <%--:value="item">--%>
                <%--</el-option>--%>
                <%--</el-select>--%>
                <%--</div>--%>

                <!-- 输入框 -->
                <div class="input-box">
                    <input type="text" @keyup.enter="searchClick" v-model="search.keyword">
                </div>
                <!-- 搜索按钮 -->
                <div class="btn-search" @click="searchClick">
                    <i class="el-icon-search"></i>
                </div>
            </div>

            <a class="btn-get-quotations">
                Get Quotations
            </a>
            <!-- 多语言下拉选择 -->
            <div class="language-select">
                <img src="./images/icon-global.png" alt="">
                <br>
                {{language}} <i class="el-icon-arrow-down el-icon--right"></i>
                <el-select v-model="language" placeholder=""
                           class="top-language-select-input"
                           popper-class="top-language-select-box"
                           @change="changeLang"
                >
                    <el-option
                            v-for="language in languageList"
                            :key="language.languageName"
                            :label="name"
                            :value="shortName">
                    </el-option>
                </el-select>
            </div>
        </div>
    </div>
</div>

<script>
    var nav = new Vue({
        el: "#nav",
        data() {
            return {
                activeTopNavIndex: 1, //默认选中的web-top澳航栏
                topSearchBarCategory: "2", //搜索 分类前的下拉选
                language: <s:iterator value="env.languages" var="language">
                    <s:if test="shortName==env.curLanguage">
                    <s:if test='#language.shortName=="zh_TW"'>"繁体中文"</s:if>
                <s:elseif test='#language.shortName=="de"'>"Deutsch"</s:elseif>
                    <s:elseif test='#language.shortName=="en"'>"English"</s:elseif>
                <s:elseif test='#language.shortName=="es"'>"Español"</s:elseif>
                    <s:elseif test='#language.shortName=="fr"'>"Français"</s:elseif>
                <s:elseif test='#language.shortName=="ja"'>"日本語"</s:elseif>
                    <s:elseif test='#language.shortName=="pt"'>"Português"</s:elseif>
                <s:elseif test='#language.shortName=="ro"'>"românesc"</s:elseif>
                    <s:elseif test='#language.shortName=="ru"'>"русский"</s:elseif>
                <s:elseif test='#language.shortName=="zh_CN"'>"简体中文"</s:elseif>
                    <s:elseif test='#language.shortName=="hu"'>"magyar"</s:elseif>
                </s:if>
                </s:iterator>, //当前选中的语种
                languageList: [
                    <c:forEach items="${env.languages}" var="language">
                        <c:if test='language.shortName=="zh_TW"'>{name: "繁体中文", value: "zh"}, </c:if>
                        <c:if test='l anguage.shortName=="de"'>{name: "Deutsch", value: "de"}, </c:if>
                        <c:if test='language.shortName=="en"'>{name: "English", value: "en"}, </c:if>
                        <c:if test='language.shortName=="es"'>{name: "Español", value: "es"} </c:if>
                        <c:if test='language.shortName=="fr"'>{name: "Français", value: "fr"}, </c:if>
                        <c:if test='language.shortName=="ja"'>{name: "日本語", value: "ja"}, </c:if>
                        <c:if test='language.shortName=="pt"'>{name: "Português", value: "pt"}, </c:if>
                        <c:if test='language.shortName=="ro"'>{name: "românesc", value: "ro"}, </c:if>
                        <c:if test='language.shortName=="ru"'>{name: "русский", value: "ru"}, </c:if>
                        <c:if test='language.shortName=="zh_CN"'>{name: "简体中文", value: "zh_CN"}, </c:if>
                    <c:if test='language.shortName=="hu"'> {name: "magyar", value: "hu"}, </c:if>
                    </c:forEach>
                ],
                search: {
                    keyword: ""
                }
            }
        },
        methods: {
            searchClick() {
                window.location.href = "/home/pdt_PdtProduct?Keyword=" + this.search.keyword
            },
            handleTopNavSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            changeLang() {

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

    console.log(this.language)
    },
    handleLanguageSelect(e)
    {
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
