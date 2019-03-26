<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="foot_blank"></div>
<div id="foot_menu" class="clean">
    <div class="fl item">
        <div class="pic">
            <a href="/">
                <img src="/home/static/themes/default/mobile/images/ficon0.png">
            </a>
        </div>
        <div class="name ">
            <a href="/">
                <!-- home -->
                <s:text name="home"/>
            </a>
        </div>
    </div>
    <div class="fl item" id="ficon1">
        <div class="pic">
            <a href="/home/pdt_PdtCat_details">
                <img src="/home/static/themes/default/mobile/images/ficon1.png">
            </a>
        </div>
        <div class="name ">
            <a href="/home/pdt_PdtCat_details">
                <!-- category -->
                <s:text name="attr.category"/>
            </a>
        </div>
    </div>
    <div class="fl item" id="ficon2">
        <div class="pic">
            <a href="/home/pdt_PdtProduct">
                <img src="/home/static/themes/default/mobile/images/ficon2.png">
            </a>
        </div>
        <div class="name">
            <a href="/home/pdt_PdtProduct">
                <!-- discover -->
                <s:text name="discover"/>
            </a>
        </div>
    </div>
    <!--购物车 -->
    <div class="fl item " id="ficon4">
        <div class="pic">
            <a href="/home/usr_UsrCart_cartshopping">
                <img src="/home/static/themes/default/mobile/images/ficon4.png">
            </a>
        </div>
        <div class="name ">
            <a href="/home/usr_UsrCart_cartshopping">
                <!-- shopping_cart -->
                <s:text name="mobile.shopping_cart"/>
            </a>
        </div>
    </div>
    <%--<div class="fl item " id="ficon4">--%>
        <%--<div class="pic">--%>
            <%--<a href="/home/usr_UsrFavorites_myfavorite">--%>
                <%--<img src="/home/static/themes/default/mobile/images/love.png">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="name ">--%>
            <%--<a href="/home/usr_UsrFavorites_myfavorite">--%>
                <%--<!-- shopping_cart -->--%>
                <%--<s:text name="my_fav"/>--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="fl item " id="ficon3">
        <div class="pic">
            <a href="/home/usr_UsrPurchase_userIndex">
                <img src="/home/static/themes/default/mobile/images/ficon3.png">
            </a>
        </div>
        <div class="name ">
            <a href="/home/usr_UsrPurchase_userIndex">
                <!-- account -->
                <s:text name="account"/>
            </a>
        </div>
    </div>

</div>
<script type="text/javascript">
    var html = location.href;
    //$("div.fl.item img")
    //$("div.fl.item div.pic")
    if (html.indexOf("usr_UsrPurchase") != -1) {
        $("#foot_menu div div a img").eq(0).attr("src", "/home/static/themes/default/mobile/images/ficon0_on.png");
        $("div.fl.item div.name").eq(0).attr("class", "name on");
    }
    if (html.indexOf("pdt_PdtCat_details") != -1) {
        $("#foot_menu div div a img").eq(1).attr("src", "/home/static/themes/default/mobile/images/ficon1_on.png");
        $("div.fl.item div.name").eq(1).attr("class", "name on");
    }
    if (html.indexOf("pdt_PdtProduct") != -1) {
        $("#foot_menu div div a img").eq(2).attr("src", "/home/static/themes/default/mobile/images/ficon2_on.png");
        $("div.fl.item div.name").eq(2).attr("class", "name on");
    }
    if (html.indexOf("usr_UsrCart_cartshopping") != -1) {
        $("#foot_menu div div a img").eq(3).attr("src", "/home/static/themes/default/mobile/images/ficon4_on.png");
        $("div.fl.item div.name").eq(3).attr("class", "name on");
    }
    if (html.indexOf("usr_UsrPurchase_userIndex") != -1) {
        $("#foot_menu div div a img").eq(0).attr("src", "/home/static/themes/default/mobile/images/ficon0.png");
        $("#foot_menu div div a img").eq(4).attr("src", "/home/static/themes/default/mobile/images/ficon3_on.png");
        $("div.fl.item div.name").eq(4).attr("class", "name on");
    }
</script>
