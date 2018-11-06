<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
        <script src="/home/static/js/jquery-1.7.2.min.js"></script>
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

    img {
        width: 100%;
        height: 100%;
    }

    .goods-box {
        max-width: 1200px;
        min-width: 375px;
        margin: 0 auto;
        height: 100%;
        width: 100%;
    }

    .goods-bgImg {
        font-size: 0;
    }

    .goods-box .goods-list:nth-of-type(4) {
        margin-bottom: 5%;
    }

    .goods-box .goods-bgImg:nth-of-type(3) {
        margin-top: 20px;
    }

    .goods-item {
        width: 24.3%;
        float: left;
        position: relative;
        font-size: 0;
        margin: 0.9% 0.9% 0 0;
        overflow: hidden;
    }

    .goods-item-info {
        box-sizing: border-box;
        padding: 20px;
        width: 100%;
        height: 34.5%;
        background: #313131;
        position: absolute;
        left: 0;
        bottom: 0;
        text-align: left;
        color: #ffffff;
        transform: translateY(220px);
        -webkit-transform: translateY(220px);
        -moz-transform: translateY(220px);
        transition: all 0.7s;
        -webkit-transition: all 0.7s;
        -moz-transition: all 0.7s;
        opacity: 0.8;
    }

    .goods-item-info p {
        margin: 0;
        font-size: 14px;
        line-height: 18px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        -ms-text-overflow: ellipsis;
        text-overflow: ellipsis;
        white-space: normal;
        word-wrap: break-word;
        word-break: break-all;
        word-wrap: break-word;
        position: relative;
        max-height: 35px;
    }

    .goods-item-info h3 {
        margin: 10px 0 0 0;
        font-size: 16px;
    }

    .goods-item:hover {
        box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
        -moz-box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
        -webkit-box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
    }

    .goods-item:hover .goods-item-info {
        transform: translateY(0px);
        -webkit-transform: translateY(0px);
        -moz-transform: translateY(0px);
    }

    @media screen and (min-width: 1025px) {
        .goods-box .goods-list>a:last-child .goods-item {
            display: none;
        }
        .goods-list a:nth-child(4n+4) .goods-item {
            margin-right: 0;
        }
    }

    @media screen and (min-width: 768px) and (max-width: 1024px) {
        .goods-item {
            width: 32.66%;
        }
        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }
        .goods-item-info {
            display: none;
        }
    }

    @media screen and (min-width: 415px) and (max-width: 768px) {
        .goods-item {
            width: 32.66%;
        }
        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }
        .goods-item-info {
            display: none;
        }
    }

    @media screen and (max-width: 415px) {
        .goods-item {
            width: 32.66%;
        }
        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }
        .goods-item-info {
            display: none;
        }
    }
</style>
<%@ include file="/home/magazine/template/web-top.jsp" %>
    <%@ include file="/home/magazine/template/new-header.jsp" %>

    <div class="goods-box clearfix">

    </div>
         <%@ include file="/home/magazine/template/new-foot.jsp" %>
<script>
    var data = [{
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b7822663782c76808a7e196e9dd6a98e.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dfede5650342aba2a427688f1143d692.jpg",
                    name: "Outdoor high quality genuine leather summer sandals men",
                    price: "14.91",
                    url: "https://www.shoestp.com/outdoor-high-quality-genuine-leather-summer-sandals-men_p2070.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4602d9c251e37d104704c8b858860b3d.jpg",
                    name: "low prices men beach nude sandals big size men beach sandals",
                    price: "6.90",
                    url: "https://www.shoestp.com/low-prices-men-beach-nude-sandals-big-size-men-beach-sandals_p1094.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/60b2a80adb60e5500ce9239a8b811004.jpg",
                    name: "2018 New product summer breathable men beach sandals",
                    price: "12.50",
                    url: "https://www.shoestp.com/2018-new-product-summer-breathable-men-beach-sandals_p2069.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a6770475f38e2ffb727a5e4553f4d778.jpg",
                    name: "Latest good quality men flat leather summer men sandals",
                    price: "11.40",
                    url: "https://www.shoestp.com/latest-good-quality-men-flat-leather-summer-men-sandals_p2064.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8dba5c2fb45bd42855708806c489ed92.jpg",
                    name: "men nude beach sandals low prices big size men sandals",
                    price: "5.80",
                    url: "https://www.shoestp.com/men-nude-beach-sandals-low-prices-big-size-men-sandals_p1012.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/649f9866ff8f5968e4e8c9d1a7a31440.jpg",
                    name: "china wholesale customized mens summer fashion leather sport slippers sandals 2017",
                    price: "9.36",
                    url: "https://www.shoestp.com/china-wholesale-customized-mens-summer-fashion-leather-sport-slippers-sandals-2017_p2080.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dff814f0d796d53888624eaefb898d3a.jpg",
                    name: "boys beach sandals slippers children summer low prices children slippers",
                    price: "4.90",
                    url: "https://www.shoestp.com/boys-beach-sandals-slippers-children-summer-low-prices-children-slippers_p1509.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f38ed09db299ff05948f10ff99c58672.jpg",
                    name: "men nude beach sandals low prices big size men sandals",
                    price: "5.80",
                    url: "https://www.shoestp.com/men-nude-beach-sandals-low-prices-big-size-men-sandals_p1084.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3e2c1d01016a182e6e6aaf52b40e7811.jpg",
                    name: "men nude beach sandals low prices big size men sandals",
                    price: "5.80",
                    url: "https://www.shoestp.com/men-nude-beach-sandals-low-prices-big-size-men-sandals_p1012.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/10bf7b42cd67c20fe279ea5ca654221b.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/27a194f78c7121245774a714e72f88c3.jpg",
                    name: "2018 high quality italian mens brogue bullock shoes vintage style winter handmade men leather boots",
                    price: "136.00",
                    url: "https://www.shoestp.com/2018-high-quality-italian-mens-brogue-bullock-shoes-vintage-style-winter-handmade-men-leather-boots_p2163.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3aa067975b5c796c4246ecef86b966e0.jpg",
                    name: "Wholesalers china genuine burnished cow leather mens shoes boots high demand products",
                    price: "40.00",
                    url: "https://www.shoestp.com/wholesalers-china-genuine-burnished-cow-leather-mens-shoes-boots-high-demand-products_p2162.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fd79691488f428f917523ac67307cea9.jpg",
                    name: "2018 New Design Winter Classic Men Dress Chelsea Luxury Boots for online sale",
                    price: "18.90",
                    url: "https://www.shoestp.com/2018-new-design-winter-classic-men-dress-chelsea-luxury-boots-for-online-sale_p2157.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c05bf20102231fee411c4a5f68a27d3.jpg",
                    name: "Shoes Hot Sell Style Flat Heel Brown PU Winter Shoes Boots Men Casual",
                    price: "25.00",
                    url: "https://www.shoestp.com/alibaba-shoes-hot-sell-style-flat-heel-brown-pu-winter-shoes-boots-men-casual_p2151.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bcb525e06a1c14c668c376813575ab45.jpg",
                    name: "2017 High Quality Good Design Mens Yellow Casual Hiking Shoes Boots For Sale",
                    price: "42.50",
                    url: "https://www.shoestp.com/2017-high-quality-good-design-mens-yellow-casual-hiking-shoes-boots-for-sale_p2155.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/44a25be43a3745b75a39a900392ac185.jpg",
                    name: "2018 New Design Winter Classic Men Dress Chelsea Luxury Boots for online sale",
                    price: "27.50",
                    url: "https://www.shoestp.com/2018-new-design-winter-classic-men-dress-chelsea-luxury-boots-for-online-sale_p2156.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3fece4f89f8d679069e0f25b2c59e914.jpg",
                    name: "Top fashion high quality winter shoes men lace leather boots",
                    price: "36.80",
                    url: "https://www.shoestp.com/top-fashion-high-quality-winter-shoes-men-lace-leather-boots_p2161.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/88235f908d198c936bf39bd4fbfe693b.jpg",
                    name: "2017 High Quality Good Design Mens Yellow Casual Hiking Shoes Boots For Sale",
                    price: "20.50",
                    url: "https://www.shoestp.com/2017-high-quality-good-design-mens-yellow-casual-hiking-shoes-boots-for-sale_p2154.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a3206dce313f2a29ff0e164535add672.jpg",
                    name: "2017 High Quality Good Design Mens Yellow Casual Hiking Shoes Boots For Sale",
                    price: "42.50",
                    url: "https://www.shoestp.com/2017-high-quality-good-design-mens-yellow-casual-hiking-shoes-boots-for-sale_p2155.html"
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
