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
       bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/24954b276a715f0a8971172ff617bf7e.jpg",
       itemImg: [
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f0986c719584e4a4de7b981a0c39cf28.jpg",
               name: "Boy's Black and Red Sandals Color-matching Velcro Summer TPR sole Slip-resistant Breathable Sandals",
               price: "6.50",
               url: "https://www.shoestp.com/boys-black-and-red-sandals-color-matching-velcro-summer-tpr-sole-slip-resistant-breathable-sandals_p0238.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5651c2d918c518f68c6e9f39fefbed47.jpg",
               name: "Boy's PU Sandals Fashionable Velcro Light Summer Breathable Slip-resistant Beach Sandals",
               price: "4.30",
               url: "https://www.shoestp.com/boys-pu-sandals-fashionable-velcro-light-summer-breathable-slip-resistant-beach-sandals_p0237.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b7f89e41a812c8d54562e7e98d37d083.jpg",
               name: "2018 kids new arrival flat summer sandals fashion boys PU leather strap sandals",
               price: "4.45",
               url: "https://www.shoestp.com/2018-kids-new-arrival-flat-summer-sandals-fashion-boys-pu-leather-strap-sandals_p1421.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f25591b5596798820898b941307cf9ea.jpg",
               name: "Boys' Fashion Sling-back Light Breathable Velcro Slip-resistant Sandals",
               price: "7.60",
               url: "https://www.shoestp.com/boys-fashion-sling-back-light-breathable-velcro-slip-resistant-sandals_p0252.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f0c50cb8c93338e8a558d7b93e678c03.jpg",
               name: "Boy's PU Sandals Black and Red Color-matching Classical Velcro Light Breathable Summer Slip-resisting Beach Sandals",
               price: "4.30",
               url: "https://www.shoestp.com/boys-pu-sandals-black-and-red-color-matching-classical-velcro-light-breathable-summer-slip-resisting-beach-sandals_p0244.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d7467ae8d1954338dcdf7a1765254efa.jpg",
               name: "Boy's Camouflage Sandals Fashionable Velcro Strap Light Breathable Summer Slip-resistant Beach Sandals",
               price: "4.30",
               url: "https://www.shoestp.com/boys-camouflage-sandals-fashionable-velcro-strap-light-breathable-summer-slip-resistant-beach-sandals_p0240.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ab628fdbaf19aa4db4afaf6d2ef078f3.jpg",
               name: "Boys' Chic Velcro Sling-back Light Breathable Slip-resistant Beach Sandals",
               price: "6.50",
               url: "https://www.shoestp.com/boys-chic-velcro-sling-back-light-breathable-slip-resistant-beach-sandals_p0248.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d11b8c46ce8a8b513903a1dc8b5b5f00.jpg",
               name: "Boy's PU Sandals Fashionable Color-matching Summer Velcro Light Breathable Slip-resisting Beach Sandals",
               price: "7.60",
               url: "https://www.shoestp.com/boys-pu-sandals-fashionable-color-matching-summer-velcro-light-breathable-slip-resisting-beach-sandals_p0245.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f778118340eb0a4217372e9d85861738.jpg",
               name: "Boy's Printing Non-woven Sandals Fashionable Velcro Sling-back Light Summer Breathable Beach Flat Sandals",
               price: "4.30",
               url: "https://www.shoestp.com/boys-printing-non-woven-sandals-fashionable-velcro-sling-back-light-summer-breathable-beach-flat-sandals_p0236.html"
           },
       ]
   },
   {
       bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/eb7f4ccff23b4477f9fd263369fb1d94.jpg",
       itemImg: [
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b5d3484c370929c1613b1b4ff5c67d7c.jpg",
               name: "Girl's Cartoon Sneakers Flower Print Embroidery Velcro Trendy Casual Sports Flat Shoes",
               price: "13.00",
               url: "https://www.shoestp.com/girls-cartoon-sneakers-flower-print-embroidery-velcro-trendy-casual-sports-flat-shoes_p0677.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f57417e93b77fb8f29d3e875d53d73a7.jpg",
               name: "Boy and Girl's Fashionable Sneakers Shell Toe Shiny Color-matching Velcro Trendy Casual Sports Shoes",
               price: "13.00",
               url: "https://www.shoestp.com/boy-and-girls-fashionable-sneakers-shell-toe-shiny-color-matching-velcro-trendy-casual-sports-shoes_p0676.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e2f384d6a7cefc52a4b9d07104e81b73.jpg",
               name: "Girl's Shell Toe Sneakers Fashionable Velcro Trendy Casual Sports Flat Children Shoes",
               price: "13.00",
               url: "https://www.shoestp.com/girls-shell-toe-sneakers-fashionable-velcro-trendy-casual-sports-flat-children-shoes_p0678.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2f55f96d5e62e5a197a9b22a764c5d7d.jpg",
               name: "Girl's Shell Toe Shoes Fashionable Cartoon Print Velcro Casual Sports Children Sneakers",
               price: "13.00",
               url: "https://www.shoestp.com/girls-shell-toe-shoes-fashionable-cartoon-print-velcro-casual-sports-children-sneakers_p0680.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/79d2ba61b5e4fc111d146325e9117a8e.jpg",
               name: "Girl's Cartoon Shoes Fashionable Velcro Trendy Casual Baby Shoes",
               price: "13.00",
               url: "https://www.shoestp.com/girls-cartoon-shoes-fashionable-velcro-trendy-casual-baby-shoes_p0674.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a85a20f14fff98ae0ed9c23c94157f84.jpg",
               name: "Boys and Girls' Canvas Shoes Fashionable Elastic Slip-ons Spring and Autumn New Casual Sports Shoes",
               price: "16.00",
               url: "https://www.shoestp.com/boys-and-girls-canvas-shoes-fashionable-elastic-slip-ons-spring-and-autumn-new-casual-sports-shoes_p0551.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/44fc8a08de4ceb5df4a0758fb2706271.jpg",
               name: "Boy and Girl's Fashionable White Shoes Color-matching Velcro Trendy Casual Sports Flat Shoes",
               price: "13.00",
               url: "https://www.shoestp.com/boy-and-girls-fashionable-white-shoes-color-matching-velcro-trendy-casual-sports-flat-shoes_p0675.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9b11d73e1e298d25cfd17abf20a05f25.jpg",
               name: "Girl's Plush Collar Shoes Fashionable Color-matching Velcro Casual Flat Baby Shoes",
               price: "12.00",
               url: "https://www.shoestp.com/girls-plush-collar-shoes-fashionable-color-matching-velcro-casual-flat-baby-shoes_p0682.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9b81eb75d05e2b93f0778b6a56d964a9.jpg",
               name: "Girl's Flat Sneakers Fashionable Velcro Elastic Trendy Casual Sports Children Shoes",
               price: "13.00",
               url: "https://www.shoestp.com/girls-flat-sneakers-fashionable-velcro-elastic-trendy-casual-sports-children-shoes_p0679.html"
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
