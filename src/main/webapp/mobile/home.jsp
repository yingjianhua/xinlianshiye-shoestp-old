<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/swipe.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/index.js"></script>
    <%--<link href="/mobile/static/css/style(2).css" rel="stylesheet" type="text/css">--%>
      <style type="text/css">
          body, html {
              /* min-width: 320px;
              max-width: 640px; */
              height: 100%;
              /* margin: 0 auto; */
              font-family: Arial, Helvetica, sans-serif;
              color: #333;
              background-color: #fff;
          }

          .clear {
              clear: both;
          }

          .mobile_cate img {
              width: 100%;
              height: 100%;
              object-fit: cover;
          }

          .mobile_cate * {
              box-sizing: border-box;
              margin: 0;
              padding: 0;
          }

          .mobile_cate {
              width: 100%;
          }

          .mobile_cate .mobile_cate_item {
              width: 100%;
              font-size: 0;
              margin-top: 2%;
          }

          .mobile_cate .mobile_cate_item > div:nth-child(1) {
              width: 46%;
              height: 250px;
          }

          .mobile_cate .mobile_cate_item > div:nth-child(2) > .mobile_cate_shoes1 {
              height: 115px;
          }

          .mobile_cate .mobile_cate_item .mobile_cate_shoes {
              height: 135px;
              border: 1px solid #f1f1f1;
              border-top: none;
          }

          .mobile_cate .mobile_cate_item
          .mobile_cate_shoes > div:nth-child(1) {
              width: 50%;
              height: 134px;
              border-right: 1px solid #f1f1f1;
              position: relative;
          }

          .mobile_cate .mobile_cate_item
          .mobile_cate_shoes > div:nth-child(2) {
              width: 50%;
              height: 134px;
              position: relative;
          }

          .mobile_cate .mobile_cate_item .shoes_font {
              width: 100%;
              text-align: center;
              position: absolute;
              left: 0;
              top: 0;
              font-size: 12px;
              padding: 6%;
          }

          .mobile_cate
          .mobile_cate_item .shoes_font .shoes_font1 {
              line-height: 18px;
              overflow: hidden;
          }

          .mobile_cate .try-rfq > div:nth-child(1) {
              line-height: 3rem;
              display: flex;
              justify-content: flex-start;
              align-items: center;
              font-weight: bold;
          }

          .mobile_cate .try-rfq > div:nth-child(1) > div:nth-child(1) {
              padding: 0 5px;
              height: 30px;
              background-color: #689dff;
              text-align: center;
              margin-right: 2%;
              color: white;
              line-height: 30px;
              font-weight: bold;
              border-radius: 30px;
          }

          .mobile_cate .mobile-company-list {
              font-size: 0;
              display: flex;
              flex-wrap: wrap;
              justify-content: space-between;
          }

          .mobile_cate .mobile-company-list > li:nth-child(1), .mobile_cate
          .mobile-company-list > li:nth-child(2), .mobile_cate .mobile-company-list > li:nth-child(3) {
              border-right: 1px solid #f2f2f2;
              border-bottom: 1px solid #f2f2f2;
          }

          .mobile_cate
          .mobile-company-list > li:nth-child(5), .mobile_cate .mobile-company-list > li:nth-child(6), .mobile_cate
          .mobile-company-list > li:nth-child(7) {
              border-right: 1px solid #f2f2f2;
          }

          .mobile_cate .mobile-company-list > li:nth-child(4) {
              border-bottom: 1px solid #f2f2f2;
          }
          .spad div:first-child{
              float: left;
              width: 46%;
          }
          .spad div:nth-child(2){
              float: right;
              width: 52%;
          }
          @media screen and (min-width: 500px) {
              .mobile_cate .mobile_cate_item > div:nth-child(1) {
                  height: 420px;
              }

              .mobile_cate .mobile_cate_item > div:nth-child(2) > div:nth-child(1) {
                  height: 170px;
              }

              .mobile_cate .mobile_cate_item .mobile_cate_shoes {
                  height: 250px;
              }

              .mobile_cate .mobile_cate_item .mobile_cate_shoes > div:nth-child(1) {
                  height: 249px;
              }

              .mobile_cate .mobile_cate_item .mobile_cate_shoes > div:nth-child(2) {
                  height: 249px;
              }

              .mobile_cate .mobile_cate_item .shoes_font {
                  width: 100%;
                  text-align: center;
                  position: absolute;
                  left: 0;
                  top: 15%;
                  font-size: 16px;
                  padding: 6%;
              }

              .mobile_cate .mobile_cate_item .shoes_font .shoes_font1 {
                  line-height: 18px;
                  overflow: hidden;
              }
          }

          /* 商品图片 */
          .nspro_list .item .img{
            height: 10rem;
          }
          .nspro_list .item .img > a > img{
            width: 100%;
            height: 100%;
            object-fit: cover;
          }
          /* 商品左右间隔 */
          .nspro_list .item:nth-child(2n-1){
          	margin-right: 2%;
          }

          /*!* 轮播样式 - 图片高度不一致时 - 有空 隙 *!*/
          /*问题当高度一致时发现图片显示不完全*/
          .swipe-item{
            width: 100%;
            height: 8.5rem;
          }
          .swipe-item img{
            width: 100%;
            height: 100%;
            object-fit: cover;
          }
          .btn-more{
			display: inline-block;
			height: 1.8rem;
			line-height: 1.8rem;
			padding: 0 0.4rem;
			margin-right: 0.625rem;
			margin-top: 0.625rem;
			text-align: center;
			color: #fff;
			background: #043d81;
			float: right;
		}
      </style>
</head>

<body>
<jsp:include page="template/header.jsp"></jsp:include>
<div class="spsearchm">
    <div class="w94ps">
        <div class="spsearch">
            <form action="/home/pdt_PdtProduct" method="get" class="form">
                <input type="submit" value="" class="fl btn">
                <input class="text fr" value="" placeholder="" name="Keyword" type="search">
            </form>
        </div>
        <!-- .spsearch -->
    </div>
    <!-- .w94ps -->
</div>
<div class="wrapper spmain">
    <div class="w94ps">
        <div class="sfico clean">
            <div class="im fl">
                <a href="/home/pdt_PdtProduct?cated=373">
                    <img alt="Men" src="/mobile/static/images/939dad0217.png">
                </a>
                <div class="name">
                    <!-- Men -->
                    <s:text name="Mr" />
                </div>
            </div>
            <div class="im fl">
                <a href="/home/pdt_PdtProduct?cated=380">
                    <img alt="Women" src="/mobile/static/images/e0775fa677.png">
                </a>
                <div class="name">
                    <!-- Women -->
                    <s:text name="Ms" />
                </div>
            </div>
            <div class="im fl">
                <a href="/home/pdt_PdtProduct?cated=387">
                    <img alt="Chidren" src="/mobile/static/images/3e99d7f200.png">
                </a>
                <div class="name">
                    <!-- Chidren -->
                    <s:text name="kidsCollections" />
                </div>
            </div>
            <div class="im fl">
                <a href="/usr_UsrConsult_productPublishView">
                    <img alt="RFQ" src="/mobile/static/images/dbf04e16c0.png">
                </a>
                <div class="name">
                    <!-- RFQ -->
                    <s:text name="consult_relation.consult" />
                </div>
            </div>
            <div class="im fl">
                <!-- <a href="https://www.shoestp.com/inquiry.html?gt=list"> -->
                <a href="/home/usr_UsrConsult_publicListView">
                    <img alt="RFQ list" src="/mobile/static/images/01cee4a428.png">
                </a>
                <div class="name">
                    <!-- RFQ list -->
                    <s:text name="inq_list" />
                </div>
            </div>
        </div>
        <div class="sunbanner" id="banner_box">
            <ul>
                <c:forEach items="${purchaseIndexView.headedSwiperAds}" var="d">
                    <li class="swipe-item">
                        <a href="${d.url}">
                            <img src="${envConfig.imageBaseUrl}${d.image}" alt="${d.name}">
                        </a>
                    </li>
                </c:forEach>
            </ul>
            <div class="btn">
                <c:forEach items="${purchaseIndexView.headedSwiperAds}" var="d">
                  <span class="">
                        </span>
                </c:forEach>
            </div>
        </div>
        <div class="spad clean">
            <c:forEach var="d" items="${purchaseIndexView.middleAds}">
                <div class="y">
                    <a href="${d.url}">
                        <img src="${envConfig.imageBaseUrl}${d.image}" alt="">
                    </a>
                </div>
            </c:forEach>
        </div>
        <div class="index_ad_box">

            <div>
                <div class="mobile_cate">
                    <c:forEach items="${indexAdView4Mobile.productCategoryAd}" var="ad">
                        <div class="mobile_cate_item">
                            <div class="fl">
                                <a href="${ad.items[0].url}">
                                    <img src="${envConfig.imageBaseUrl}${ad.items[0].image}" alt="">
                                </a>
                            </div>
                            <div class="fr" style="width:52%">
                                <div class="mobile_cate_shoes1">
                                    <a href="${ad.items[1].url}">
                                        <img alt="" src="${envConfig.imageBaseUrl}${ad.items[1].image}">
                                    </a>
                                </div>
                                <div class="mobile_cate_shoes">
                                    <div class="fl mobile_cate_shoes2">
                                        <a href="${ad.items[2].url}">
                                            <img alt="" src="${envConfig.imageBaseUrl}${ad.items[2].image}">
                                        </a>
                                    </div>
                                    <div class="fl mobile_cate_shoes2">
                                        <a href="${ad.items[3].url}">
                                            <img alt="" src="${envConfig.imageBaseUrl}${ad.items[3].image}">
                                        </a>
                                    </div>

                                    <div class="clear"></div>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </c:forEach>

                    <div class="try-rfq">
                        <div>
                            <!-- RFQæ é¢1-->
                            <div>
                                <!-- RFQ -->
                                <s:text name="inq" />
                            </div>
                            <div>
                                <!-- REQUEST FOR QUOTATION -->
                                <s:text name="consult_message.relation" />
                            </div>
                        </div>
                        <div style="font-size:0;">
                            <!-- ææºä¸ä¾§1å¾-->
                            <a href="/home/usr_UsrConsult_listView">
                                <img alt="" src="/mobile/static/images/45309e7ce3.png">
                            </a>
                        </div>
                    </div>
                    <div class="mobile-company">
                        <div style="line-height: 3rem;font-weight:bold;">
                            <!-- æ é¢2-->
                            <!-- RECOMMENDED SUPPLIERS -->
                            <s:text name="supplier_roleact.role" />
                            <a href="/home/usr_UsrSupplier" class="btn-more"><s:text name="read_more" /></a>
                        </div>

                        <div>
                            <ul style="border: 1px solid #f2f2f2;" class="mobile-company-list">
                                <c:forEach items="${purchaseIndexView.supplier}" var="d">
                                    <li style="width:24.9%">
                                        <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}">
                                            <c:forTokens items="${d.logo}" delims="," var="photo" end="0">
                                                <img src="${envConfig.imageBaseUrl}${photo}" alt="${d.name}">
                                            </c:forTokens>
                                        </a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div>
                            <ul class="mobile-company-list" style="border: 1px solid #f2f2f2;">
                            </ul>
                        </div>
                        <div style="margin-top:2%;">
<!--                             <a href="#"> -->
                                <img  src="/mobile/static/images/5ee8d5b808.png">
<!--                             </a> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="spblue_t spgray_t">
           <!--  Products -->
           <s:text name="order_line.spec" />
        </div>
        <div class="nspro_list clean" id="nspro_list_js">
            <c:forEach var="d" items="${purchaseIndexView.newProducts}">
                <div class="item fl">
                    <div class="img pic_box">
                        <a href="/${d.rewrite}"
                           title="${d.name}">
                            <img src="${envConfig.imageBaseUrl}${d.image}?x-oss-process=image/resize,m_pad,h_170,w_170">">
                        </a>
                        <span>
                            </span>
                        <div class="ico">
                            <div class="fl">
                                <img src="/mobile/static/images/xin_27.png">
                            </div>
                            <div class="fr">
                                <img src="/mobile/static/images/huo_23.png">
                            </div>
                        </div>
                    </div>
                    <div class="name">
                        <a href="./pdt_PdtProduct_gtProductsInfo?id=${d.id}"
                           title="${d.name}">
                                ${d.name}
                        </a>
                    </div>
                    <div class="price">
                            ${d.price}
                    </div>
                </div>
            </c:forEach>
            <%-- <div class="clear"></div> --%>
        </div>
        <div class="click_more">
                    <span page="2">
                        <!-- View More -->
                        <s:text name="read_more" />
                    </span>
        </div>
    </div>
    <!-- .w94ps -->
</div>
<footer>
    <div id="prolist_mask_footer">
    </div>
    <div class="footer_top clean">
    </div>
    <ul class="footer_list ui_border_b clean" style="display:none;">
        <li class="fl" style="border-right:1px solid #ddd;">
            <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                        <span class="list_left">
                            <!-- Sign In -->
                            <s:text name="sign_in" />
                        </span>
                <span class="list_right">
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
        </li>
    </ul>
    <nav>
    </nav>
    <section class="font_col border_col copyright">
        Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
        <%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
    </section>
</footer>
<jsp:include page="./template/foot_menu.jsp"></jsp:include>

<script type="text/javascript">
    $('.click_more span').click(function () {
        var limit = 6
        var self = this;
        var page = parseInt($(self).attr('page'));
        $(self).fadeOut();
        $.get('/home/pdt_PdtProduct_gtNewProducts', {
                limit: limit,
                page: page
            },
            function (data) {
                if (data) {
                    $.each(data.items, function (i, d) {
                        $('#nspro_list_js').append("     <div class=\"item fl\">\n" +
                            "                    <div class=\"img pic_box\">\n" +
                            "                        <a href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + d.id + "\"\n" +
                            "                           title=\"" + d.name + "\">\n" +
                            "                            <img src=\"${envConfig.imageBaseUrl}" + d.image + "\">\n" +
                            "                        </a>\n" +
                            "                        <span>\n" +
                            "                            </span>\n" +
                            "                        <div class=\"ico\">\n" +
                            "                            <div class=\"fl\">\n" +
                            "                                <img src=\"/mobile/static/images/xin_27.png\">\n" +
                            "                            </div>\n" +
                            "                            <div class=\"fr\">\n" +
                            "                                <img src=\"/mobile/static/images/huo_23.png\">\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"name\">\n" +
                            "                        <a href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + d.id + "\"\n" +
                            "                           title=\"" + d.name + "\">\n" +
                            "                                " + d.name + "\n" +
                            "                        </a>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"price\">\n" +
                            "                            " + d.price + "\n" +
                            "                    </div>\n" +
                            "                </div>");

                    })
                }
                if ((limit * page) < data.total) {
                    $(self).fadeIn();
                    $(self).attr('page', ++page)
                }
            }, "json");
    });
</script>
<%-- <script src="/mobile/static/js/get_static_config.0.378.2.2.1342.14.2.22.9.8.2.6.285.js"></script>
<script src="/mobile/static/js/localization.en.0.043117e7a56a2e3ea008a802da2a0076_9f9568ee0491374470a5f78170eb7aed.js"></script> --%>

<div id="lc_invite_layer" style="text-align: left; display: none; z-index: 16777261;">
</div>
<div id="lc_overlay_layer"
     style="background-color: rgb(0, 0, 0); position: fixed; left: 0px; top: 0px; z-index: 16777260; display: none; width: 100%; height: 100%;">
</div>

</body>
</html>
