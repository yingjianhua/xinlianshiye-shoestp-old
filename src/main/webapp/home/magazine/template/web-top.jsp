<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
<link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
<link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/home/static/js/en.js"></script>
<script type="text/javascript" src="/home/static/js/lang/${env.curLanguage }.js"></script>
<script type="text/javascript" src="/home/static/js/global.js"></script>
<script type="text/javascript" src="/home/static/js/global(1).js"></script>
<script type="text/javascript" src="/home/static/js/user.js"></script>
<script type="text/javascript" src="/home/static/js/moment-with-locales.min.js"></script>

<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<!--引入vue-->
<script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body class="new-style-page w_1200">
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
                <el-submenu index="4" class="no-arrow">
                    <template slot="title">
                        <s:text name="CROWDFUNDING"/>
                    </template>
                    <el-menu-item index="4-1">
                        <a href="/home/Activity_Romania_classifyactivity?category=373">
                            Men's
                        </a>
                    </el-menu-item>
                    <el-menu-item index="4-2">
                        <a href="/home/Activity_Romania_classifyactivity?category=380">
                            Women's
                        </a>
                    </el-menu-item>
                    <el-menu-item index="4-3"><a
                            href="/home/Activity_Romania_classifyactivity?category=387">Children</a>
                    </el-menu-item>
                </el-submenu>

                <!-- 顶部右侧 - 收藏 -->
                <el-menu-item index="8" class="fr mr0">
                    <a href="/home/usr_UsrFavorites_myfavorite" target="_blank">
                        <img src="/home/v2/static/images/nav/icon-heart.png" alt="icon-heart" style="position: relative;top: -2px;">
                        <i class="fav_count imgnumber">${env.login==null?0:env.login.favoriteCount }</i>
                    </a>
                </el-menu-item>
                <!-- 顶部右侧 - 购物车 -->
                <el-menu-item index="9" class="fr">
                    <a href="/home/usr_UsrCart_cartshopping" target="_blank">
                        <img src="/home/v2/static/images/nav/icon_Shopping-Cart.png" alt="icon_Shopping-Cart" style="position: relative;top: -2px;">
                        <i class="cart_count imgnumber">${env.login==null?0:env.login.cartCount}</i>
                    </a>
                </el-menu-item>
                <!-- 顶部右侧 - 询盘 -->
                <el-menu-item index="7" class="fr">
                    <a href="/home/usr_UsrConsult_listView" target="_blank">
                        <s:text name="RFQ"/>
                        <i class="inq_count imgnumber">${env.login==null?0:env.login.inquiryCount }</i>
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
                    <s:if test="env.login==null">
                        <template slot="title">
                            <a href="/home/usr_UsrPurchase_sign" target="_blank">
                                <s:text name="Login"></s:text>
                            </a>
                        </template>
                    </s:if>
                    <s:else>
                        <%--<a href="/home/usr_UsrPurchase_userIndex">${env.login.loginName}</a>--%>
                        <template slot="title">
                            <a href="/home/usr_UsrPurchase_userIndex">${env.login.loginName}</a>
                        </template>
                        <el-menu-item index="5-1">
                            <a rel="nofollow" href="/home/usr_UsrPurchase_signOut">
                                <s:text name="sign_out" />
                            </a>
                        </el-menu-item>
                    </s:else>
                </el-submenu>
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
        activeTopNavIndex: '1', //默认选中的web-top澳航栏
      }
    },
    methods: {
      handleTopNavSelect(key, keyPath) {
        console.log(key, keyPath);
      },
    }
  })
  window.onscroll = function () {
    var sl = -Math.max(document.body.scrollLeft, document.documentElement.scrollLeft);
    document.getElementById('new-top-nav').style.left = sl + 'px';
    document.getElementById('new-top-search').style.left = sl + 'px';
  }
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