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
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/befe8f994747396e52f8d4b203662550.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1213765bf22aa695d09032dcca5458cf.jpg",
                name: "Fashion style leather with butterfly customer made shoes for girls 2018",
                price: "14.00",
                url: "https://www.shoestp.com/fashion-style-leather-with-butterfly-customer-made-shoes-for-girls-2018_p4690.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7395c2a6d79293ea3e23ccbf90d4f3df.jpg",
                name: "Special embroidered design elastic and decoration rubber sole cat girls dress shoes",
                price: "14.00",
                url: "https://www.shoestp.com/special-embroidered-design-elastic-and-decoration-rubber-sole-cat-girls-dress-shoes_p4692.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5ce8cb0daf60c657e76120a8caa4314.jpg",
                name: "2018 Hot Sale Style Patent Pu With StrapFlower Decoration Girls Dress Shoes",
                price: "10.00",
                url: "https://www.shoestp.com/2018-hot-sale-style-patent-pu-with-strapflower-decoration-girls-dress-shoes_p4677.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dd5973de95716e275070c6e95a4a0b00.jpg",
                name: "lovely style leather flower decoration strap baby shoes",
                price: "12.00",
                url: "https://www.shoestp.com/lovely-style-leather-flower-decoration-strap-baby-shoes_p4657.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/67caa0382d1679d3e2c6555ed06d5ab0.jpg",
                name: "Wholesale PU upper pink flat dress with pearls and tassels shoes for kids girls",
                price: "13.00",
                url: "https://www.shoestp.com/wholesale-pu-upper-pink-flat-dress-with-pearls-and-tassels-shoes-for-kids-girls_p4773.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7ce03704cbb1276f44378ab92e787411.jpg",
                name: "Cheap price genuine leather model sole kid shoes 2018",
                price: "6.00",
                url: "https://www.shoestp.com/cheap-price-genuine-leather-model-sole-kid-shoes-2018_p4763.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/921947241d8468223678b811d51fb397.jpg",
                name: "Wholesale factory low price leather with flower and buckle decoration girls dress shoes",
                price: "14.00",
                url: "https://www.shoestp.com/wholesale-factory-low-price-leather-with-flower-and-buckle-decoration-girls-dress-shoes_p4699.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/76afe0f61a1f944f84668a85229763cf.jpg",
                name: "New good quality Baby girl Shoes Summer girls Lace Up kids shoes children's shoes factory",
                price: "6.80",
                url: "https://www.shoestp.com/new-good-quality-baby-girl-shoes-summer-girls-lace-up-kids-shoes-childrens-shoes-factory_p4293.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/84add92eb89fd6d44b3edd3c4d6b6e62.jpg",
                name: "Lovely style genuine leather with flower decoration baby girls shoes",
                price: "14.00",
                url: "https://www.shoestp.com/lovely-style-genuine-leather-with-flower-decoration-baby-girls-shoes_p4701.html"
            },
        ]
    },
    {
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7df6afb54c47e2bd50ed2b7e0169e5a4.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/603965899c6ea5df5d5b6a7fa7d99cda.jpg",
                name: "gugutree baby infants children sports shoes school soft sole running shoes high quality with light",
                price: "9.50",
                url: "https://www.shoestp.com/gugutree-baby-infants-children-sports-shoes-school-soft-sole-running-shoes-high-quality-with-light_p1246.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7cab0163345b0232fad9be7b31fa152.jpg",
                name: "walker sports shoes infants child baby casual running shoes soft soles cheap prices",
                price: "4.80",
                url: "https://www.shoestp.com/walker-sports-shoes-infants-child-baby-casual-running-shoes-soft-soles-cheap-prices_p1230.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4d1ceb6b89d35c3d3a8fd172d5bc6f5b.jpg",
                name: "gugutree inftants soft sole girls boys sports shoes",
                price: "6.50",
                url: "https://www.shoestp.com/gugutree-inftants-soft-sole-girls-boys-sports-shoes_p1207.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b014809965095cd47f82f1b250b2d04e.jpg",
                name: "2018 kids denim cloth fashion sneaker shoes canvas sneakers",
                price: "6.45",
                url: "https://www.shoestp.com/2018-kids-denim-cloth-fashion-sneaker-shoes-canvas-sneakers_p1320.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6faf9d8221fa4c2ac3f9fb7f21116a6.jpg",
                name: "gugutree teenager school children sports running shoes girls white casual shoes",
                price: "8.00",
                url: "https://www.shoestp.com/gugutree-teenager-school-children-sports-running-shoes-girls-white-casual-shoes_p1332.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7a4f579b98d127821c6c656855b71be5.jpg",
                name: "Children's sports shoes 2017 autumn girls casual running shoes",
                price: "4.80",
                url: "https://www.shoestp.com/childrens-sports-shoes-2017-autumn-girls-casual-running-shoes_p1308.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7e0b08cd7b23f3226e1894c4e8a1271f.jpg",
                name: "Fashionable kids black pu sneaker shoes boys high cut sneakers with zipper",
                price: "6.95",
                url: "https://www.shoestp.com/fashionable-kids-black-pu-sneaker-shoes-boys-high-cut-sneakers-with-zipper_p1303.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/84763c436d0a850332d1079e0bee8836.jpg",
                name: "wholesale factory low price laser upper leather with butterfly kid shoes",
                price: "6.20",
                url: "https://www.shoestp.com/wholesale-factory-low-price-laser-upper-leather-with-butterfly-kid-shoes_p4698.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1e7e4efa3be1235ac7b8667d870842e.jpg",
                name: "kids sport shoes casual PU school skate shoes",
                price: "3.35",
                url: "https://www.shoestp.com/kids-sport-shoes-casual-pu-school-skate-shoes_p1293.html"
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
