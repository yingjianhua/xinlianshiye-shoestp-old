<%--全局登录弹框--%>
<jsp:include page="/home/v3/login-box.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- 顶部nav栏 -->
<div id="nav" style="height: auto;">
    <div id="new-top-nav" class="wide-wrap" v-cloak>
        <div class="wide" style="width: 1240px;min-width: 1240px;margin: 0 auto">
            <!-- 顶部左侧 - 4个下拉选 -->
            <el-menu :default-active="activeTopNavIndex" class="el-menu-demo" mode="horizontal"
                     @select="handleTopNavSelect">
                <el-menu-item index="11" class="link-item">
                    <a href="/home/usr_UsrPurchase" target="_blank">
                        <s:text name="Home"/>
                    </a>
                </el-menu-item>
                <el-menu-item index="2" class="link-item">
                    <a href="/home/pdt_PdtProduct" target="_blank">
                        <s:text name="Products"/>
                    </a>
                </el-menu-item>
                <el-menu-item index="3" class="link-item">
                    <a href="/home/pdt_PdtProduct_o2oList" target="_blank">
                        <s:text name="Romanian Office Exhibition"/>
                    </a>
                </el-menu-item>
                <el-menu-item index="4" class="link-item">
                    <a href="/html/SVS/svs.jsp" target="_blank">
                        <s:text name="SVS Suppliers"/>
                    </a>
                </el-menu-item>

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
                    <a @click="ToRFQ">
                        <s:text name="RFQ"/>
                    </a>
                </el-menu-item>
            </el-menu>
        </div>
    </div>
</div>
<%--<script src="/home/v2/static/lang/element/en.js"></script>--%>
<script>

    // ELEMENT.locale(ELEMENT.lang.en)
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
        data: {
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
            Vue.set(this.search, 'keyword', unescape(decodeURIComponent(getParams('Keyword', getParams('keyWord', getParams('keyword', ''))))))
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
            // 跳转RFQ
            ToRFQ(){
                let url = "/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1"+ "&backUrl=" + window.location.href
                util_function_obj.supplierCantEnter(this, url,"Please register or login your buyer account if you want public RFQ.");
             },
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

