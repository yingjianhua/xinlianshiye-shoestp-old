<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0024)https://www.shoestp.com/ -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/static/css/animate.min.css">
    <link rel="stylesheet" href="/home/static/css/swiper.min.css" type="text/css">
    <link href="/home/static/css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/static/css/xl-style.css">
    <link href="/home/static/css/stp.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/static/css/color.css" type="text/css">
    <script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/home/static/js/swiper.min.js"></script>
    <script type="text/javascript" src="/home/static/js/lazyload.min.js"></script>
</head>
<body class="lang_en w_1200">


<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/new-header.jsp" %>
<!-- .new_header -->
<style type="text/css">
    /* brand-line-height */
    .lh-brand {
        line-height: 56px;
    }

    /* 女鞋标题*/
    .toptic_title_0 {
        position: relative;
        display: inline-block;
        padding: 0 1em;
        border: 0;
        margin: 0.5em;
        background: transparent;
        color: #ffffff;
        /*text-transform: uppercase;*/
        font-weight: bold;
        font-size: 16px;
    }

    .toptic_title_0::before {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: -1;
        background: #FFA08A;
        transform: skew(-20deg);
        border-radius: 5px;
    }

    /*女鞋标题*/

    /* 男鞋标题*/

    .toptic_title_1 {
        position: relative;
        display: inline-block;
        padding: 0 1em;
        border: 0;
        margin: 0.5em;
        background: transparent;
        z-index: 91;
        color: #ffffff;
        /*text-transform: uppercase;*/
        text-decoration: none;
        font-weight: bold;
        font-size: 16px;
    }

    .toptic_title_1::before {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: -1;
        background: #7BB3EA;
        transform: skew(-20deg);
        border-radius: 5px;
    }

    /*男鞋标题*/

    /* 童鞋标题*/

    .toptic_title_2 {
        position: relative;
        display: inline-block;
        padding: 0 1em;
        border: 0;
        margin: 0.5em;
        background: transparent;
        color: #ffffff;
        /*text-transform: uppercase;*/
        font-weight: bold;
        font-size: 16px;

    }

    .toptic_title_2::before {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: -1;
        background: #9CDE75;
        transform: skew(-20deg);
        border-radius: 5px;
    }

    /*童鞋标题*/

    /* 新产品标题*/

    .NewProducts {
        position: relative;
        display: inline-block;
        padding: 0 1em;
        border: 0;
        margin: 0.5em;
        background: transparent;
        color: #ffffff;
        z-index: 91;
        /*text-transform: uppercase;*/
        font-weight: bold;
        font-size: 16px;
    }

    .NewProducts::before {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: -1;
        background: #76CFC1;
        transform: skew(-20deg);
        border-radius: 5px;
    }

    /*新产品标题*/

    /* 品牌展示标题*/

    .BrandShow {
        position: relative;
        display: inline-block;
        padding: 0 1em;
        border: 0;
        margin: 0.5em;
        background: transparent;
        color: #ffffff;
        z-index: 91;
        /*text-transform: uppercase;*/
        font-weight: bold;
        font-size: 16px;
    }

    .BrandShow::before {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: -1;
        background: #7BB3EA;
        transform: skew(-20deg);
        border-radius: 5px;
    }

    /*品牌展示标题*/

    .carousel {
        width: 800px;
        height: 400px;
        overflow: hidden;
        position: relative;
    }

    #swiperOne .swiper-pagination {
        width: 10px;
        height: 90px;
    }

    #swiperOne > .swiper-pagination-bullets .swiper-pagination-bullet {
        margin: 3px 0;
    }

    #swiperOne > .swiper-pagination-bullets,
    .swiper-pagination-custom,
    .swiper-pagination-fraction {
        left: 96% !important;
    }

    .swiper-pagination-bullet {
        width: 10px;
        height: 10px;
        border-radius: 6px;
        text-align: center;
        line-height: 5px;
        font-size: 12px;
        color: #000;
        opacity: 1;
        background: rgba(255, 255, 255, 0.7);
    }

    .swiper-pagination-bullet-active {
        background: #ffffff;
        color: white;
        opacity: 1;
    }
</style>
<div class="supbanner">
    <div id="xl-topic">
        <div id="xl-banner">
            <div id="xl-banner_left">
                <div class="carousel">
                    <div class="swiper-container swiper-container-horizontal swiper-container-wp8-horizontal"
                         id="swiperOne">
                        <div class="swiper-wrapper"
                             style="transform: translate3d(-3200px, 0px, 0px); transition-duration: 0ms;">
                            <c:forEach var="d" items="${purchaseIndexView.headedSwiperAds}">
                                <div class="swiper-slide" style="width: 800px;">
                                    <a href="${d.url}" target="_blank">
                                        <img src="${envConfig.imageBaseUrl}${d.image}" alt="${d.name}">
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-pagination swiper-pagination-bullets">
                            <%--                             <c:forEach items="${purchaseIndexView.headedSwiperAds}"> --%>
                            <span class="swiper-pagination-bullet"></span>
                            <%--                             </c:forEach> --%>
                        </div>
                    </div>
                </div>
            </div>
            <div id="xl-banner_right">
                <!--Banner右侧1图-->
                <c:forEach var="d" items="${purchaseIndexView.headedRightTopAds}">
                    <h1>
                        <a target="_blank" href="${d.url}">
                            <img src="${envConfig.imageBaseUrl}${d.image}" width="380" height="212"
                                 alt="${d.name}"> </a>
                    </h1>
                </c:forEach>
                <h1>
                    <!--banner右侧2图-->
                    <c:forEach var="d" items="${purchaseIndexView.headedBottomTopAds}">
                        <a href="${d.url}" target="_blank">
                            <img src="${envConfig.imageBaseUrl}${d.image}" alt=""
                                 width="${380/fn:length(purchaseIndexView.headedBottomTopAds)}"
                                 height="170" border="0"
                            >
                        </a>
                        <%--<map name="Map">--%>
                        <%--<area shape="rect" coords="33,91,162,139" href="https://www.shoestp.com/inquiry.html"--%>
                        <%--target="_blank">--%>
                        <%--<area shape="rect" coords="222,93,357,139"--%>
                        <%--href="https://www.shoestp.com/inquiry.html?gt=list"--%>
                        <%--target="_blank">--%>
                        <%--</map>--%>
                    </c:forEach>
                </h1>
            </div>
        </div>
    </div>

</div>

<!-- 内容部分 -->
<div id="xl-main">
    <c:if test="${indexAdView4PC.middleAd !=null}">
        <div id="xl-middle clearfloat" style="width:1200px;margin:0 auto">
            <c:forEach var="d" items="${indexAdView4PC.middleAd[0].items}">
                <!--Banner下方-->
                <a href="${d.url}" target="_blank">
                    <img class="lazy" src="${envConfig.imageBaseUrl}${d.image}"
                         data-original="${envConfig.imageBaseUrl}${d.image}"
                         width="390" height="156"
                         alt="">
                </a>
            </c:forEach>
        </div>
    </c:if>

    <%--  <c:forEach items="${purchaseIndexView.pdtCategoryAds}" var="d" varStatus="status">
          <div class="xl-banner" id="aaaaaaaa">
              <span class="toptic_title">
                  <a class="toptic_title_${status.index}" href="/home/pdt_PdtProduct?cated=${d.pkey}"
                     target="_blank">
                          ${d.name}
                  </a>
              </span>
              <ul class="women_ul clearfloat">
                  <li>
                      <a href="/home/pdt_PdtProduct?cated=${d.pkey}" target="_blank">Global.More+</a>
                  </li>
                  <c:forEach var="dd" items="${d.categorys}" step="1">
                      <li>
                          <a href="/home/pdt_PdtProduct?cated=${dd.pkey}" target="_blank">${dd.name}</a>
                      </li>
                  </c:forEach>
              </ul>

              <div class="toys">
                  <div class="${(status.index+1)%2!=0?"toys_right":"toys_left"} left800">
                      <div class="cata-box">
                          <ul class="toys_ul">
                              <c:forEach var="dd" items="${d.ads.items}">
                                  <li>
                                      <a target="_blank" href="${dd.url}">
                                          <img class="lazy" src="${dd.image}" data-original="${dd.pkey}" width="260" height="260"alt="${dd.name}">
                                          <div class="cover">
                                              <p>${dd.name}</p>
                                              <h3></h3>
                                          </div>
                                      </a>
                                  </li>
                              </c:forEach>

                                  <li>
                                      <a target="_blank" href="#">
                                          <img class="lazy" src="/home/static/images/user/aCasual.jpg" width="260" height="260"alt="Casual Men's PU Lace-up Shoes">
                                          <div class="cover">
                                              <p>Casual Men's PU Lace-up Shoes</p>
                                              <h3></h3>
                                          </div>
                                      </a>
                                  </li>


                          </ul>

                      </div>
                  </div>
                  <div class="${(status.index+1)%2==0?"toys_right":"toys_left"} left380">
                      <a href="${d.ads.main.url}" target="_blank">
                          <img class="lazy" src="${d.ads.main.image}"
                               data-original="${d.ads.main.pkey}" width="380" height="530"
                               alt="${d.ads.main.name}">
                      </a>
                  </div>
              </div>

          </div>

      </c:forEach> --%>

    <div class="xl-banner" id="aaaaaaaa">
			<span class="toptic_title">
				<a class="toptic_title_0" href="/home/pdt_PdtProduct?cated=380" target="_blank"><s:text
                        name="home.Womens"/></a>
			</span>
        <ul class="women_ul clearfloat">
            <li>
                <a href="/home/pdt_PdtProduct?cated=380" target="_blank"><s:text name="Global.More"/>+</a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=490" target="_blank"><s:text name="home.Womens_Shoes"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=385" target="_blank"><s:text name="home.Womens_Boots"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=386" target="_blank"><s:text name="home.Womens_Sandals"/></a>
            </li>
        </ul>
        <div class="toys">
            <div class="toys_right left800">
                <div class="cata-box">
                    <ul class="toys_ul">
                        <c:forEach items="${indexAdView4PC.productCategoryAd }" var="ad">
                            <c:if test="${ad.category==380 }">
                                <c:forEach items="${ad.items }" var="line">
                                    <c:if test="${!line.isPrimary() }">
                                        <li>
                                            <a target="_blank" href="${line.url}">
                                                <img class="lazy" src=""
                                                     data-original="${envConfig.imageBaseUrl}${line.image}" width="260"
                                                     height="260" alt="${line.summary }">
                                                <div class="cover">
                                                    <p>${line.summary }</p>
                                                    <h3></h3>
                                                </div>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="toys_left left380">
                <c:forEach items="${indexAdView4PC.productCategoryAd }" var="ad">
                    <c:if test="${ad.category==380 }">
                        <c:forEach items="${ad.items }" var="line">
                            <c:if test="${line.isPrimary() }">
                                <a href="${line.url}" target="_blank">
                                    <img class="lazy" src="" data-original="${envConfig.imageBaseUrl}${line.image}"
                                         alt="${line.summary }">
                                </a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="xl-banner" id="aaaaaaaa">
			<span class="toptic_title">
				<a class="toptic_title_1" href="/home/pdt_PdtProduct?cated=373" target="_blank"><s:text
                        name="home.Mens"/></a>
			</span>
        <ul class="women_ul clearfloat">
            <li>
                <a href="/home/pdt_PdtProduct?cated=373" target="_blank"><s:text name="Global.More"/>+</a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=484" target="_blank"><s:text name="home.Mens_Shoes"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=378" target="_blank"><s:text name="home.Mens_Boots"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=375" target="_blank"><s:text name="home.Mens_Sandals"/></a>
            </li>
        </ul>
        <div class="toys">
            <div class="toys_left left800">
                <div class="cata-box">
                    <ul class="toys_ul">
                        <c:forEach items="${indexAdView4PC.productCategoryAd }" var="ad">
                            <c:if test="${ad.category==373 }">
                                <c:forEach items="${ad.items }" var="line">
                                    <c:if test="${!line.isPrimary() }">
                                        <li>
                                            <a target="_blank" href="${line.url}">
                                                <img class="lazy" src=""
                                                     data-original="${envConfig.imageBaseUrl}${line.image}" width="260"
                                                     height="260" alt="${line.summary }">
                                                <div class="cover">
                                                    <p>${line.summary }</p>
                                                    <h3></h3>
                                                </div>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="toys_right left380">
                <c:forEach items="${indexAdView4PC.productCategoryAd }" var="ad">
                    <c:if test="${ad.category==373 }">
                        <c:forEach items="${ad.items }" var="line">
                            <c:if test="${line.isPrimary() }">
                                <a href="${line.url}" target="_blank">
                                    <img class="lazy" src="" data-original="${envConfig.imageBaseUrl}${line.image}"
                                         alt="${line.summary }">
                                </a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </div>
        </div>

    </div>
    <div class="xl-banner" id="aaaaaaaa">
			<span class="toptic_title">
				<a class="toptic_title_2" href="/home/pdt_PdtProduct?cated=387" target="_blank"><s:text
                        name="home.Childrens"/></a>
			</span>
        <ul class="women_ul clearfloat">
            <li>
                <a href="/home/pdt_PdtProduct?cated=387" target="_blank"><s:text name="Global.More"/>+</a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=508" target="_blank"><s:text name="home.Boy_Collection"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=509" target="_blank"><s:text name="home.Girl_Collection"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=507" target="_blank"><s:text name="home.Baby_Collection"/></a>
            </li>
            <li>
                <a href="/home/pdt_PdtProduct?cated=519" target="_blank"><s:text
                        name="home.Waterproof_Boots_And_Rain_Boots"/></a>
            </li>
        </ul>
        <div class="toys">
            <div class="toys_right left800">
                <div class="cata-box">
                    <ul class="toys_ul">
                        <c:forEach items="${indexAdView4PC.productCategoryAd }" var="ad">
                            <c:if test="${ad.category==387 }">
                                <c:forEach items="${ad.items }" var="line">
                                    <c:if test="${!line.isPrimary() }">
                                        <li>
                                            <a target="_blank" href="${line.url}">
                                                <img class="lazy" src=""
                                                     data-original="${envConfig.imageBaseUrl}${line.image}" width="260"
                                                     height="260" alt="${line.summary }">
                                                <div class="cover">
                                                    <p>${line.summary }</p>
                                                    <h3></h3>
                                                </div>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="toys_left left380">
                <c:forEach items="${indexAdView4PC.productCategoryAd }" var="ad">
                    <c:if test="${ad.category==387 }">
                        <c:forEach items="${ad.items }" var="line">
                            <c:if test="${line.isPrimary() }">
                                <a href="${line.url}" target="_blank">
                                    <img class="lazy" src="" data-original="${envConfig.imageBaseUrl}${line.image}"
                                         alt="${line.summary }">
                                </a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="xl-products_content">
        <div id="xl-banner_four">
            <!-- 新品start -->
            <div class="new_product">
                <div class="clearfloat" style="padding:0 15px 0 0;">
						<span class="toptic_title">
							<!--                                新品标题-->
							<a class="NewProducts" target="_blank" href="/home/pdt_PdtProduct?orderfld=New">
								<s:text name="home.New_Products"/>
							</a>
						</span>
                    <span class="more lh-brand">
							<!--                                更多按钮-->
							<a href="/home/pdt_PdtProduct?orderfld=New" target="_blank"><s:text
                                    name="Global.More"/>+</a>
						</span>
                </div>
                <div class="product_left">
                    <div class="cata-box">
                        <ul class="toys_ul product_ul_list clearfloat">
                            <c:forEach items="${purchaseIndexView.newProducts}" var="d" step="1">
                                <li>
                                    <a href="/${d.rewrite}"
                                       title="${d.name}"
                                       target="_blank">
                                        <img class="lazy" src=""
                                             data-original="${envConfig.imageBaseUrl}${d.image}?x-oss-process=image/resize,m_fixed,h_200,w_200"
                                             width="230"
                                             height="230" alt="">
                                        <p class="newP">${d.name}</p>
                                            <%--<h3>${env.currency.symbols}${d.price}</h3>--%>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- 新品end -->
            <div class="brand">
                <div class="clearfloat" style="padding:0 15px 0 0;">
						<span class="toptic_title">
							<!--                                品牌展示-->
							<a class="BrandShow" target="_blank" href="/home/usr_UsrSupplier"><s:text
                                    name="home.Brand_Show"/></a>
						</span>
                    <span class="more lh-brand">
							<!--                                更多按钮-->
							<a href="/home/usr_UsrSupplier" target="_blank"><s:text name="Global.More"/>+</a>
						</span>
                </div>
                <div class="brand-right">
                    <div class="xl-box">
                        <div class="box_body_list clearfloat">
                            <!-- line1 -->
                            <c:forEach var="d" items="${purchaseIndexView.supplier}" step="1">
                                <span href="javascript:void(0)" class="item">
                                        <img class="lazy" src=""
                                             data-original="${envConfig.imageBaseUrl}${d.logo}"
                                             style="display: inline;">
                                        <span class="_hover">
                                               <a target="_blank"
                                                  href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}">
                                                <span class="_name"> ${d.name}</span>
                                                <span class="_enterBtn"><!-- Enter --><s:text
                                                        name="my-order.See_Details"/></span>
                                            </a>
                                        </span>
                                </span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="template/new-foot.jsp"></jsp:include>
<div id="hj_top" style="opacity: 0; bottom: 10%;">
    <img src="./static/images/hj_top.png">
</div>
<script>
    $(document).ready(function () {
        // 图片懒加载
        $("img.lazy").lazyload({
            effect: "show",
            threshold: 150,
            event: 'scroll',
            failure_limit: 6
        });
        // 轮播
        var swiperOne = new Swiper('#swiperOne', {
            pagination: {
                el: '.swiper-pagination',
            },
            autoplay: {
                delay: 2500,
                disableOnInteraction: false,
            },
        });
        $("#swiperOne").mouseover(function () {
            swiperOne.autoplay.stop();
        }).mouseleave(function () {
            swiperOne.autoplay.start();
        });

        $('#swiperOne .swiper-pagination').on('mouseenter', 'span', function () {
            var index = $(this).index();
            swiperOne.slideTo(index, 1000, false);
        })
    });
</script>
</body>
</html>
