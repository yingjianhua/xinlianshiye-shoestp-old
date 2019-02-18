<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <index-top></index-top>

</div>
<script src="/home/v3/static/js/index-top.js"></script>

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
        data:{
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
                    keyword: '',
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
            Vue.set(this.search,'keyword',unescape(decodeURIComponent(getParams('Keyword',getParams('keyWord',getParams('keyword',''))))))
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
                }).catch(reason => {
                    console.error(reason)
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
