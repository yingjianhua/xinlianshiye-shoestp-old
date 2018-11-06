<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@include file="/mobile/template/style_import.jsp" %>
</head>
        <body>
<style>
      .clearfix:after {
        content: "";
        height: 0;
        line-height: 0;
        display: block;
        visibility: hidden;
        clear: both;
    }

    .clearfix {
        zoom: 1;
    }
    .goods-item {
        width: 24.3%;
        float: left;
        position: relative;
        font-size: 0;
        margin: 0.9% 0.9% 0 0;
        overflow: hidden;
    }
        .goods-item {
            width: 32.66%;
        }
        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }
        .goods-item-info {
            display: none;
        }
        .goods-box .goods-bgImg:nth-of-type(3) {
		    margin-top: 0.9%;
		}
</style>
	<jsp:include page="/mobile/magazine/template/header.jsp"></jsp:include>

    <div class="goods-box clearfix">

    </div>
    <%@ include file="/mobile/magazine/template/foot_menu.jsp" %>
<script>
         var data = [{
             bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5e24f48b944906f6be9c8c828ffb790a.jpg",
             itemImg: [
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/53a3ad22cc7d4de2f4f9c780bfc947e0.jpg",
                     name: "DABOWEN LADIES FASHION LOAFERS LACE-UP CASUAL FLAT WOMEN PU CANVAS SHOES SPRING&AUTUMN NEW ARRIVAL",
                     price: "5.50",
                     url: "https://www.shoestp.com/dabowen-ladies-fashion-loafers-lace-up-casual-flat-women-pu-canvas-shoes-springampautumn-new-arrival_p6280.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b3d619716d45be2d0dd79b0349d4d355.jpg",
                     name: "Dabowen Women's Canvas Shoes Fashionable Lace-up Spring and Autumn Casual Flat Sneakers",
                     price: "7.00",
                     url: "https://www.shoestp.com/dabowen-womens-canvas-shoes-fashionable-lace-up-spring-and-autumn-casual-flat-sneakers_p6278.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/995fb15de223a0e9305d2f5a10bf56df.jpg",
                     name: "Dabowen Women's Fashionable White PU Canvas Shoes Breathable Lace-up Spring and Autumn Thick Sole Casual Flat Loafers",
                     price: "7.00",
                     url: "https://www.shoestp.com/dabowen-womens-fashionable-white-pu-canvas-shoes-breathable-lace-up-spring-and-autumn-thick-sole-casual-flat-loafers_p6275.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/17f358b2475f6d4d4f08e712db89a345.jpg",
                     name: "DABOWEN LADIES FASHION MULTI-COLOR CANVAS WOMEN SHOES SLIP-ON YOUTH TREND CASUAL FLAT BLOG SHOES SPRING&AUTUMN NEW ARRIVAL",
                     price: "6.50",
                     url: "https://www.shoestp.com/dabowen-ladies-fashion-multi-color-canvas-women-shoes-slip-on-youth-trend-casual-flat-blog-shoes-springampautumn-new-arrival_p6290.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/72fd2bede52d98062da5d43fd01ca9f6.jpg",
                     name: "Dabowen Women's Canvas Shoes Spring and Autumn Elastic Slip-on Trendy Casual Flat Sneakers",
                     price: "7.00",
                     url: "https://www.shoestp.com/dabowen-womens-canvas-shoes-spring-and-autumn-elastic-slip-on-trendy-casual-flat-sneakers_p6276.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ea3d932682bf46a7761d3096f4e6175b.jpg",
                     name: "Dabowen Women's Fashionable Loafers Color-matching Korean Velcro Spring and Autumn Casual PU Canvas Shoes Flat Sneakers",
                     price: "8.00",
                     url: "https://www.shoestp.com/dabowen-womens-fashionable-loafers-color-matching-korean-velcro-spring-and-autumn-casual-pu-canvas-shoes-flat-sneakers_p6277.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/32484b4be32aa71434ced9b9ad35896e.jpg",
                     name: "Dabowen Women's Flat Sneakers Fashionable Shell Toe Lace-up British Style Spring and Autumn New Casual Canvas Sports Shoes",
                     price: "7.00",
                     url: "https://www.shoestp.com/dabowen-womens-flat-sneakers-fashionable-shell-toe-lace-up-british-style-spring-and-autumn-new-casual-canvas-sports-shoes_p6273.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/74ec6ee3a971dc25be8ac71dfcef9be0.jpg",
                     name: "hot sale casual lace-up sport shoes women canvas shoes",
                     price: "5.00",
                     url: "https://www.shoestp.com/hot-sale-casual-lace-up-sport-shoes-women-canvas-shoes_p0912.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/370eb732ba8d598410428c1378b9f9b7.jpg",
                     name: "DABOWEN LADIES FASHION EMBROIDERY SHOES WOMEN CANVAS SHOES VELCRO YOUTH TREND CASUAL FLAT LOAFER SHOES SPRING&AUTUMN NEW ARRIVAL",
                     price: "6.50",
                     url: "https://www.shoestp.com/dabowen-ladies-fashion-embroidery-shoes-women-canvas-shoes-velcro-youth-trend-casual-flat-loafer-shoes-springampautumn-new-arrival_p2149.html"
                 },
             ]
         },
         {
             bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fc50baa04d8cae8cd6ebadf52820329d.jpg",
             itemImg: [
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/428ccdd85489cbaad8bf7a05c2425cca.jpg",
                     name: "Dabowen Women's Canvas Shoes Fashionable Lace-up Korean Casual Flat Breathable Sneakers Spring and Autumn Sports Shoes",
                     price: "6.00",
                     url: "https://www.shoestp.com/dabowen-womens-canvas-shoes-fashionable-lace-up-korean-casual-flat-breathable-sneakers-spring-and-autumn-sports-shoes_p0453.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3e346b319f109953bf459b7a99dbed4b.jpg",
                     name: "Dabowen Ladies Fashion Shell Head Women Canvas Shoes Lace-up Casual Flat Breathable Sneakers for Spring and Autumn",
                     price: "8.00",
                     url: "https://www.shoestp.com/dabowen-ladies-fashion-shell-head-women-canvas-shoes-lace-up-casual-flat-breathable-sneakers-for-spring-and-autumn_p6257.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/76f1515efa2ab03e5a9581bd58d5a05d.jpg",
                     name: "Dabowen Women's Canvas Shoes Spring and Autumn Lace-up Trendy Young Style Casual Flat Sneakers",
                     price: "6.00",
                     url: "https://www.shoestp.com/dabowen-womens-canvas-shoes-spring-and-autumn-lace-up-trendy-young-style-casual-flat-sneakers_p6264.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c40cf69a705833492b0fb44a40bad946.jpg",
                     name: "Dabowen Women's Canvas Shoes Fashionable Floral Print Velcro Campus Spring and Autumn Casual Flat Sneakers",
                     price: "7.00",
                     url: "https://www.shoestp.com/dabowen-womens-canvas-shoes-fashionable-floral-print-velcro-campus-spring-and-autumn-casual-flat-sneakers_p0473.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a2489e8a6613a9d29d27b6c6bdfc0cb1.jpg",
                     name: "DABOWEN LADIES FASHION WHITE SHOES LACE-UP CASUAL FLAT LOAFER WOMEN CANVAS SHOES SPRING&AUTUMN NEW ARRIVAL",
                     price: "6.50",
                     url: "https://www.shoestp.com/dabowen-ladies-fashion-white-shoes-lace-up-casual-flat-loafer-women-canvas-shoes-springampautumn-new-arrival_p2047.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9616fb982d15f54264500dc5636d3d58.jpg",
                     name: "Dabowen Women's Fashionable White PU Canvas Shoes Colour-matching Lace-up Spring and Autumn Trend Style Casual Flat Sneaker Shoes",
                     price: "6.00",
                     url: "https://www.shoestp.com/dabowen-womens-fashionable-white-pu-canvas-shoes-colour-matching-lace-up-spring-and-autumn-trend-style-casual-flat-sneaker-shoes_p0449.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a865d989ce077504bd70ccc232b72ef7.jpg",
                     name: "Dabowen Women's White Shoes Fashionable Color-matching Lace-up Korean Spring and Autumn Casual PU Canvas Flat Breathable Sneakers",
                     price: "6.00",
                     url: "https://www.shoestp.com/dabowen-womens-white-shoes-fashionable-color-matching-lace-up-korean-spring-and-autumn-casual-pu-canvas-flat-breathable-sneakers_p6269.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/27524ff5504ffa367f38239bae9c2e76.jpg",
                     name: "Dabowen Women's Canvas Shoes Spring and Autumn Lace-up Trendy Young Style Campus Style Casual Flat Sneakers",
                     price: "7.00",
                     url: "https://www.shoestp.com/dabowen-womens-canvas-shoes-spring-and-autumn-lace-up-trendy-young-style-campus-style-casual-flat-sneakers_p0645.html"
                 },
                 {
                     img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/21219c8366aebf30aa65de99d786fa90.jpg",
                     name: "DABOWEN LADIES FASHION MULTI-COLOR CANVAS WOMEN SHOES LACE-UP CASUAL FLAT SHOES SPRING&AUTUMN NEW ARRIVAL",
                     price: "5.00",
                     url: "https://www.shoestp.com/dabowen-ladies-fashion-multi-color-canvas-women-shoes-lace-up-casual-flat-shoes-springampautumn-new-arrival_p2143.html"
                 },
             ]
         }
     ]
    var item = ''
    $.each(data, function (i, ele) {
        item +=
            '<div class="goods-bgImg">'+
                '<img src="'+ele.bgImg+'" alt="">'+
            '</div>'+
            '<div class="goods-list clearfix">';
        $.each(ele.itemImg, function (i, e) {
            item +=
            '<a href="'+e.url+'" target="_blank">'+
                '<div class="goods-item">'+
                    '<img src="'+e.img+'" alt="">'+
                    '<div class="goods-item-info">'+
                    '<p>'+
                      e.name+
                    '</p>'+
                    '<h3>'+
                        '$'+e.price+
                    '</h3>'+
               '</div>'+
                '</div>'+
            '</a>';        
        })
        item += '</div>';

    })
    $(".goods-box").html(item)

    function autoWidth() {
        var $width = window.screen.width;
        if ($width <= 450) {
            $(".goods-box a").attr('target', '');
        } else if ($width > 450) {
            $(".goods-box a").attr('target', '_blank');
        }
    }
    autoWidth();
    $(window).resize(function () {
        autoWidth();
    })
</script>
</body>
</html>
