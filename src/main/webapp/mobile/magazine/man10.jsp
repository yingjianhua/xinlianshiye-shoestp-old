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
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/956cc95b45eb97b48c7d04a615e7f790.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/63fd20effd5b31063638b277249ae7aa.jpg",
                name: "china shoe manufacturer mens casual shoes suede tassel loafers men",
                price: "15.13",
                url: "https://www.shoestp.com/china-shoe-manufacturer-mens-casual-shoes-suede-tassel-loafers-men_p2619.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/65b923bfce102dccc1b7a0b70bc8f3c8.jpg",
                name: "New design rubber outsole comfort leather Flat men loafer shoes",
                price: "14.91",
                url: "https://www.shoestp.com/new-design-rubber-outsole-comfort-leather-flat-men-loafer-shoes_p2617.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2bb4042ae7dadd31a9624982314a5922.jpg",
                name: "china shoe manufacturer new comfortable casual flat Genuine Leather stylish penny loafers men",
                price: "17.95",
                url: "https://www.shoestp.com/china-shoe-manufacturer-new-comfortable-casual-flat-genuine-leather-stylish-penny-loafers-men_p2602.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/55331acb5e2cfcf689c4640c0d4d2c54.jpg",
                name: "Men Loafers Slip On Suede Leather Shoes zapatos For Men",
                price: "14.04",
                url: "https://www.shoestp.com/men-loafers-slip-on-suede-leather-shoes-zapatos-for-men_p2597.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/586cbefc45faceed6fa67114653ac895.jpg",
                name: "England style man cow leather rubber outsole fashion soft comfortable leisure loafers casual flat footwear clack style",
                price: "15.90",
                url: "https://www.shoestp.com/england-style-man-cow-leather-rubber-outsole-fashion-soft-comfortable-leisure-loafers-casual-flat-footwear-clack-style_p2700.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6cc2d32d785085086cae8b368949d9e1.jpg",
                name: "Fance fashion crocodile grain casual shoes cow leather rubber outsole soft driving flat footwear clack style",
                price: "16.50",
                url: "https://www.shoestp.com/fance-fashion-crocodile-grain-casual-shoes-cow-leather-rubber-outsole-soft-driving-flat-footwear-clack-style_p2695.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d23c25c24dcec3596399881363ef0008.jpg",
                name: "2017 Winter man warm driving shoes warm floss lazyman casual flat footwear clack style",
                price: "15.90",
                url: "https://www.shoestp.com/2017-winter-man-warm-driving-shoes-warm-floss-lazyman-casual-flat-footwear-clack-style_p2698.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ce476dac18855dca5470b884f634292b.jpg",
                name: "Summer breathable home soft cow suede casual moccasin footwear slippers shoes clack style",
                price: "14.00",
                url: "https://www.shoestp.com/summer-breathable-home-soft-cow-suede-casual-moccasin-footwear-slippers-shoes-clack-style_p2690.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/adf4ab707ff3e231ea8153c852bb98bd.jpg",
                name: "Men's Comfort Driving Car Soft Flats Loafers Casual Boat Shoes Factory clack style",
                price: "16.90",
                url: "https://www.shoestp.com/mens-comfort-driving-car-soft-flats-loafers-casual-boat-shoes-factory-clack-style_p2717.html"
            },
        ]
    },
    {
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c30657aaf46afbd5036901f55095284b.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/989f49b3d72192432390699e66616773.jpg",
                name: "Summer casual Genuine cow leather breathable slipper man shoes clack style",
                price: "13.90",
                url: "https://www.shoestp.com/china-shoe-manufacturer-mens-casual-shoes-suede-tassel-loafers-men_p2619.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f96a8f9311e2208be36bbaff85b60303.jpg",
                name: "2017 China professional factory cow leather casual shoes man moccasin casual shoes lazyman moccasin shoes Clack Style",
                price: "15.90",
                url: "https://www.shoestp.com/new-design-rubber-outsole-comfort-leather-flat-men-loafer-shoes_p2617.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/39c51485ffa9708496f99b40a68dad4.jpg",
                name: "Summer breathable loafer soft shoes man lazyman driving casual flat footwear clack style",
                price: "15.90",
                url: "https://www.shoestp.com/china-shoe-manufacturer-new-comfortable-casual-flat-genuine-leather-stylish-penny-loafers-men_p2602.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/60e38383ffe6330a9209350edc381030.jpg",
                name: "wholesale breathable womens spring colorful slip on casual leather suede driving shoes with flat outsole Clack Style",
                price: "11.50",
                url: "https://www.shoestp.com/men-loafers-slip-on-suede-leather-shoes-zapatos-for-men_p2597.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/68696466989679206b8ab5da556e166a.jpg",
                name: "2017 Winter autumn man warm driving shoes warm floss casual flat lazyman footwear clack style",
                price: "15.90",
                url: "https://www.shoestp.com/england-style-man-cow-leather-rubber-outsole-fashion-soft-comfortable-leisure-loafers-casual-flat-footwear-clack-style_p2700.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/de7d6f25edb055bc447ad4e5fbc2a28d.jpg",
                name: "Kangrui Men's Fashionable Leather Shoes Embossed Spring and Autumn Slip-ons Casual Flat Soft Loafers clark style",
                price: "13.80",
                url: "https://www.shoestp.com/fance-fashion-crocodile-grain-casual-shoes-cow-leather-rubber-outsole-soft-driving-flat-footwear-clack-style_p2695.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bd155ed0ab1e90b3bd013cc4296e83db.jpg",
                name: "Kangrui Men's Fashionable Shoes Square Toe Spring and Autumn Sneakers Casual Thick Sole loafers Leather Shoes clark style",
                price: "14.40",
                url: "https://www.shoestp.com/2017-winter-man-warm-driving-shoes-warm-floss-lazyman-casual-flat-footwear-clack-style_p2698.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/206f25a3ef182bf34ad56355195369a.jpg",
                name: "SE New Arrive Men Casual Shoes",
                price: "6.80",
                url: "https://www.shoestp.com/summer-breathable-home-soft-cow-suede-casual-moccasin-footwear-slippers-shoes-clack-style_p2690.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/641d853ca0b766a2d78062a6da644043.jpg",
                name: "2018 Hot Sale men Navy Blue Suede loafers Shoes Clack Style",
                price: "14.50",
                url: "https://www.shoestp.com/mens-comfort-driving-car-soft-flats-loafers-casual-boat-shoes-factory-clack-style_p2717.html"
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
