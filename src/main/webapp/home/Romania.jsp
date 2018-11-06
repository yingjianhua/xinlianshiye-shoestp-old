<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
	<link href="./static/css/global.css" rel="stylesheet" type="text/css">
	<link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
	<link href="./static/css/user.css" rel="stylesheet" type="text/css">
	<link href="./static/css/style.css" rel="stylesheet" type="text/css">
	<!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
	<link rel="stylesheet" href="./static/css/animate.min.css">
	<link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
  <link href="./static/css/index.css" rel="stylesheet" type="text/css">
  <!-- 新引入的css -->
  <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./static/js/en.js"></script>
	<script type="text/javascript" src="./static/js/global.js"></script>
	<script type="text/javascript" src="./static/js/global(1).js"></script>
	<script type="text/javascript" src="./static/js/user.js"></script>
	<script type="text/javascript" src="./static/js/main.js"></script>
	<script src="./static/js/swiper.min.js"></script>
	<script src="./static/js/lazyload.min.js"></script>
	<!-- 轮播插件 -->
	<script type="text/javascript" src="./static/js/jquery.SuperSlide.2.1.1.js"></script>
	<%-- <script src="./static/saved_resource"></script> --%>

	<style type="text/css">
		.min-w1200 {
			min-width: 1200px;
		}
		.enterprise-header .enterprise-logo {
			width: 108px;
			height: 68px;
		}
		area {
			outline: none;
		}
		/* 轮播样式 */
		.carousel-wrap {
			width: 680px;
			height: 350px;
		}
		/* 轮播右边的三个小图样式 */
		.category-group {
			width: 500px;
			height: 350px;
		}
		.category-group .category-item {
			width: 100%;
			height: 110px;
			margin-bottom: 10px;
		}
		.category-group .category-item>div {
			margin-right: 40px;
		}
		.category-group .category-item>div span {
			margin-top: 22px;
			float: right;
			display: block;
			font-size: 18px;
			color: #fff;
		}
		.category-group .category-item>div a {
			margin-top: 12px;
			clear: right;
			float: right;
			width: 95px;
			height: 29px;
			text-align: center;
			line-height: 29px;
			display: block;
			font-size: 10px;
			color: #fff;
			background: #f8280c;
			border-radius: 29px;
		}
		.category-group .category-item img {
			width: 100%;
			height: 100%;
			border-radius: 10px;
		}
		.category-group .category-item:last-child {
			margin-bottom: 0;
		}
		.pic-1300 {
			width: 1300px;
			margin-left: -50px;
			max-width: 1300px;
		}
		/* 顶部width占满的图片上的超链接 */
		.top-big-goodsList-pic {
			position: relative;
		}
		.top-big-goodsList-pic img {
			width: 100%;
		}
		.top-big-goodsList-pic a {
			position: absolute;
			z-index: 2;
			width: 120px;
			height: 100px;
			background: rgba(255, 255, 255, 0);
		}
		@media screen and (max-width: 1300px) {
			.wide {
				overflow: hidden
			}
		}
		@media screen and (max-width: 1200px) {
			.top-big-goodsList-pic .top-pic-goods-01 {
				top: 108px;
				left: 4px;
			}
			.top-big-goodsList-pic .top-pic-goods-02 {
				top: 170px;
				left: 114px;
			}
			.top-big-goodsList-pic .top-pic-goods-03 {
				top: 80px;
				left: 190px;
			}
			.top-big-goodsList-pic .top-pic-goods-04 {
				top: 60px;
				left: 314px;
			}
			.top-big-goodsList-pic .top-pic-goods-05 {
				top: 170px;
				left: 430px;
			}
			.top-big-goodsList-pic .top-pic-goods-06 {
				top: 110px;
				left: 580px;
			}
			.top-big-goodsList-pic .top-pic-goods-07 {
				top: 180px;
				left: 660px;
			}
			.top-big-goodsList-pic .top-pic-goods-08 {
				top: 40px;
				left: 760px;
			}
			.top-big-goodsList-pic .top-pic-goods-09 {
				top: 190px;
				left: 810px;
			}
			.top-big-goodsList-pic .top-pic-goods-10 {
				top: 110px;
				left: 880px;
			}
			.top-big-goodsList-pic .top-pic-goods-11 {
				top: 170px;
				left: 1000px;
			}
			.top-big-goodsList-pic .top-pic-goods-12 {
				top: 70px;
				left: 1080px;
			}
		}
		@media screen and (min-width: 1200px) {
			.top-big-goodsList-pic .top-pic-goods-01 {
				top: 33%;
				left: 0;
			}
			.top-big-goodsList-pic .top-pic-goods-02 {
				top: 50%;
				left: 9%;
			}
			.top-big-goodsList-pic .top-pic-goods-03 {
				top: 24%;
				left: 16%;
			}
			.top-big-goodsList-pic .top-pic-goods-04 {
				top: 18%;
				left: 26%;
			}
			.top-big-goodsList-pic .top-pic-goods-05 {
				top: 41%;
				left: 36%;
			}
			.top-big-goodsList-pic .top-pic-goods-06 {
				top: 38%;
				left: 48%;
			}
			.top-big-goodsList-pic .top-pic-goods-07 {
				top: 45%;
				left: 55%;
			}
			.top-big-goodsList-pic .top-pic-goods-08 {
				top: 10%;
				left: 61%;
			}
			.top-big-goodsList-pic .top-pic-goods-09 {
				top: 53%;
				left: 68%;
			}
			.top-big-goodsList-pic .top-pic-goods-10 {
				top: 30%;
				left: 74%;
			}
			.top-big-goodsList-pic .top-pic-goods-11 {
				top: 40%;
				left: 82%;
			}
			.top-big-goodsList-pic .top-pic-goods-12 {
				top: 22%;
				left: 90%;
			}
		}
	</style>
</head>
<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/new-header.jsp" %>
<body class="lang_en w_1200">

				<!-- 内容 start -->
				<!-- 商家-头部 -->
				<!-- 大轮播 图 top -->
				<div class="top-big-goodsList-pic">
					<img style="width: 100%;margin-bottom: 45px;min-width: 1200px;" src="https://image.shoestp.com/static/themes/t050/images/purchase-center/romania/big-pic-goodsList-01.jpg" alt="">
					<!-- <a class='top-pic-goods-01' href="https://image.shoestp.com/2018-new-men-fashion-business-shoes_p5612.html" target="_blank"></a>
					<a class='top-pic-goods-02' href="https://image.shoestp.com/womens-fashionable-boots-spring-and-autumn-back-zipper-pointed-simple-stiletto-short-boots_p5609.html"
						target="_blank"></a>
					<a class='top-pic-goods-03' href="https://image.shoestp.com/fashion-tassel-decorated-pointed-toe-8cm-chunky-heel-ankle-high-women-boots_p5605.html"
						target="_blank"></a>
					<a class='top-pic-goods-04' href="https://image.shoestp.com/winter-warm-youth-kids-ankle-boots-boys-lace-up-black-boots_p5637.html"
						target="_blank"></a>
					<a class='top-pic-goods-05' href="https://image.shoestp.com/mens-round-head-breathable-business-shoes-dress-shoes-clark-style_p5651.html"
						target="_blank"></a>
					<a class='top-pic-goods-06' href="https://image.shoestp.com/fashionable-and-comfortable-kids-winter-booties-boys-pu-navy-ankle-boots_p5633.html"
						target="_blank"></a>
					<a class='top-pic-goods-07' href="https://image.shoestp.com/new-fashion-design-soft-rubber-sole-leather-men-dress-shoes_p5606.html"
						target="_blank"></a>
					<a class='top-pic-goods-08' href="https://image.shoestp.com/2018-winter-season-fashion-kids-cheap-boots-boys-custom-warm-boots-for-sale_p5610.html"
						target="_blank"></a>
					<a class='top-pic-goods-09' href="https://image.shoestp.com/womens-pu-ankle-boots-chunky-metal-heel-side-opening-western-style-all-seasons-boots_p5614.html"
						target="_blank"></a>
					<a class='top-pic-goods-10' href="https://image.shoestp.com/reliable-quality-luxury-brands-designer-genuine-leather-dress-shoe_p5615.html"
						target="_blank"></a>
					<a class='top-pic-goods-11' href="https://image.shoestp.com/cool-design-girls-pu-leather-lace-up-boots-winter-season-girls-silver-ankle-boots-with-rivets_p5607.html"
						target="_blank"></a>
					<a class='top-pic-goods-12' href="https://image.shoestp.com/womens-ankle-boots-simple-metal-buckle-side-zipper-british-style-spring-and-autumn-fahionable-chunky-heel-short-boots_p5613.html"
						target="_blank"></a> -->
				</div>

				<div class="wide clearfix">
					<ul class="category-group fr">
						<li class="category-item" style="background: url(https://image.shoestp.com/static/themes/t050/images/purchase-center/xyl-lllbanner1.jpg) no-repeat 0 0;">
							<div class="fr">
								<span>Férfi cipő terület</span>
								<a href="#" target="_blank">SHOP NOW</a>
							</div>
						</li>
						<li class="category-item" style="background: url(https://image.shoestp.com/static/themes/t050/images/purchase-center/xyl-lllbanner2.jpg) no-repeat 0 0;">
							<div class="fr">
								<span>Női cipő</span>
								<a href="#" target="_blank">SHOP NOW</a>
							</div>
						</li>
						<li class="category-item" style="background: url(https://image.shoestp.com/static/themes/t050/images/purchase-center/xyl-lllbanner3.jpg) no-repeat 0 0;">
							<div class="fr">
								<span>Gyermek cipő terület</span>
								<a href="#" target="_blank">SHOP NOW</a>
							</div>
						</li>
					</ul>

					<div class="carousel-wrap">
						<div>
							<div>
								<div>
									<ul>
										<li>
											<img src="https://image.shoestp.com/static/themes/t050/images/purchase-center/romania/banner.jpg">
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="wide">
					<img class="pic-1300" src="https://image.shoestp.com/static/themes/t050/images/purchase-center/romania/big-pic-goodsList-02.jpg" alt="" border="0" usemap="#Map">
					<map name="Map">
						<area shape="rect" coords="51,141,327,270" href="#"
							target="_blank">
						<area shape="rect" coords="358,154,633,270" href=""
							target="_blank">
						<area shape="rect" coords="662,143,947,268" href=""
							target="_blank">
						<area shape="rect" coords="970,143,1262,269" href=""
							target="_blank">
						<area shape="rect" coords="49,325,328,440" href=""
							target="_blank">
						<area shape="rect" coords="357,324,642,441" href=""
							target="_blank">
						<area shape="rect" coords="665,324,951,441" href=""
							target="_blank">
						<area shape="rect" coords="972,323,1261,439" href=""
							target="_blank">
						<area shape="rect" coords="38,491,337,615" href=""
							target="_blank">
						<area shape="rect" coords="352,489,640,614" href=""
							target="_blank">
						<area shape="rect" coords="667,491,947,613" href=""
							target="_blank">
						<area shape="rect" coords="970,492,1254,617" href=""
							target="_blank">
						<area shape="rect" coords="49,705,1250,833" href="" target="_blank">>
					</map>

					<img class="pic-1300" src="https://image.shoestp.com/static/themes/t050/images/purchase-center/romania/big-pic-goodsList-03.jpg" alt="" border="0" usemap="#Map2">
					<map name="Map2">
						<area shape="rect" coords="40,122,334,253" href=""
							target="_blank">
						<area shape="rect" coords="352,119,645,252" href=""
							target="_blank">
						<area shape="rect" coords="664,116,950,252" href=""
							target="_blank">
						<area shape="rect" coords="965,112,1260,251" href=""
							target="_blank">
						<area shape="rect" coords="40,263,333,426" href="" target="_blank">
						<area shape="rect" coords="352,268,643,424" href=""
							target="_blank">
						<area shape="rect" coords="659,267,950,422" href=""
							target="_blank">
						<area shape="rect" coords="968,268,1260,425" href=""
							target="_blank">
						<area shape="rect" coords="40,449,330,597" href=""
							target="_blank">
						<area shape="rect" coords="356,452,641,597" href=""
							target="_blank">
						<area shape="rect" coords="660,451,952,595" href=""
							target="_blank">
						<area shape="rect" coords="973,451,1256,599" href=""
							target="_blank">
					</map>
				</div>

				<!-- 内容 end -->
			<%@ include file="/home/template/new-foot.jsp" %>

      <!-- Google Tag Manager -->
      <script type="text/javascript" src="./static/js/tracking.js"></script>
      <script type="text/javascript" src="./static/js/js.js"></script>
      <script type="text/javascript" src="./static/js/conversion_async.js"></script>
      <script type="text/javascript" src="./static/js/analytics.js"></script>
      <script src="./static/js/gtm.js"></script>
      <script>
        (function (w, d, s, l, i) {
          w[l] = w[l] || [];
          w[l].push({
            'gtm.start': new Date().getTime(),
            event: 'gtm.js'
          });
          var f = d.getElementsByTagName(s)[0],
            j = d.createElement(s),
            dl = l != 'dataLayer' ? '&l=' + l : '';
          j.async = true;
          j.src =
            'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
          f.parentNode.insertBefore(j, f);
        })(window, document, 'script', 'dataLayer', 'GTM-KNPHSJ6');
      </script>
      <!-- End Google Tag Manager -->
</body>
</html>
