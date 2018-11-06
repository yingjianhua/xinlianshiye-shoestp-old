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

    </div>     <%@ include file="/home/magazine/template/new-foot.jsp" %>
<script>
    var data = [{
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/da5e56525d8b4a8b125c24408f31f8cc.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/33247d37b5efe48b583d01253409dc77.jpg",
                    name: "Kids fashion light-weighted flynit shoes sport flexible boys running shoes",
                    price: "4.65",
                    url: "https://www.shoestp.com/kids-fashion-light-weighted-flynit-shoes-sport-flexible-boys-running-shoes_p1385.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bbcfe69b291f85d1ab8bb019e58de430.jpg",
                    name: "Mesh cover PU ornament with buckle strap cemented TPR outsole sport and casual shoes for boys",
                    price: "3.00",
                    url: "https://www.shoestp.com/mesh-cover-pu-ornament-with-buckle-strap-cemented-tpr-outsole-sport-and-casual-shoes-for-boys_p2386.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3f1a35bdf7a514b32b6a3b86ab7b0bfb.jpg",
                    name: "Lycra fabric with embossing panel and plastic strap cute slip on casual kids shoes for boys",
                    price: "3.00",
                    url: "https://www.shoestp.com/lycra-fabric-with-embossing-panel-and-plastic-strap-cute-slip-on-casual-kids-shoes-for-boys_p2390.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7c88db17d7d959931de52bb1bfcf38b7.jpg",
                    name: "Seal Heating PU Upper Elastic Strap Trainer shoes",
                    price: "5.00",
                    url: "https://www.shoestp.com/seal-heating-pu-upper-elastic-strap-trainer-shoes_p2397.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8608639195358745136def1f9be56df2.jpg",
                    name: "Mesh and camo PU with buckle strap casual shoes sport kids shoes for boys",
                    price: "3.00",
                    url: "https://www.shoestp.com/mesh-and-camo-pu-with-buckle-strap-casual-shoes-sport-kids-shoes-for-boys_p2388.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d6c2032eff2c2e4e545bc3e187f81eec.jpg",
                    name: "children sports shoes boys kids sports running shoes",
                    price: "8.20",
                    url: "https://www.shoestp.com/children-sports-shoes-boys-kids-sports-running-shoes_p1216.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8e3df7a70993f055b7b081027c747f19.jpg",
                    name: "Sports style boy autumn mixture color magic type sneakers",
                    price: "15.00",
                    url: "https://www.shoestp.com/sports-style-boy-autumn-mixture-color-magic-type-sneakers_p2433.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2bbbf762d407838d2e70162ae815b7c5.jpg",
                    name: "Children's boys sports shoes 2017 spring and autumn breathable shoes casual",
                    price: "6.00",
                    url: "https://www.shoestp.com/childrens-boys-sports-shoes-2017-spring-and-autumn-breathable-shoes-casual_p1355.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7abe05bbb9d54ce6de8e5ff08f14a31e.jpg",
                    name: "High Quality Buckle Strap boys trainer Kids Shoes",
                    price: "4.00",
                    url: "https://www.shoestp.com/high-quality-buckle-strap-boys-trainer-kids-shoes_p2402.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d76eb910f96550accbc8dd29c7e22b5d.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/469183f65d1139447edf72a71436dc65.jpg",
                    name: "Wholesale soft sole genuine leather prewalker infant baby shoes",
                    price: "3.39",
                    url: "https://www.shoestp.com/wholesale-soft-sole-genuine-leather-prewalker-infant-baby-shoes_p3189.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4273c3513a0b9c9da6792355c0a5030f.jpg",
                    name: "Wholesale Infant Shoes Leather Soft Soled Shoe Baby First Walking Shoes",
                    price: "4.19",
                    url: "https://www.shoestp.com/wholesale-infant-shoes-leather-soft-soled-shoe-baby-first-walking-shoes_p3169.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3e666f5cc190e174a3c93d6ad9e6e58d.jpg",
                    name: "Wholesale Baby Boots Leather Baby Boy Girl Snow Boots",
                    price: "4.08",
                    url: "https://www.shoestp.com/wholesale-baby-boots-leather-baby-boy-girl-snow-boots_p3173.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cf8b0a71e1c298edc26da736848997b0.jpg",
                    name: "2018 Wholesale Sandals Leather Baby Girls Sandals Summer Baby Sandals",
                    price: "2.29",
                    url: "https://www.shoestp.com/2018-wholesale-sandals-leather-baby-girls-sandals-summer-baby-sandals_p3182.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/df54672bcfc853c772bf002e60f4c6d0.jpg",
                    name: "Wholesale Hard Sole Kids Shoes Genuine Leather Baby Shoes",
                    price: "3.29",
                    url: "https://www.shoestp.com/wholesale-hard-sole-kids-shoes-genuine-leather-baby-shoes_p3188.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/59703f1c873741bdeb297f177c8b58bf.jpg",
                    name: "Wholesale Kids Shoes Leather Soft Soled Shoe Baby First Walking Shoes",
                    price: "4.19",
                    url: "https://www.shoestp.com/wholesale-kids-shoes-leather-soft-soled-shoe-baby-first-walking-shoes_p3164.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/56b1c868be491b4f969cebe8aa7561b3.jpg",
                    name: "4 Sizes 6 colors Unisex Non-slip Soft Sole Leather Tassel Toddler Prewalker Baby Shoes",
                    price: "6.90",
                    url: "https://www.shoestp.com/4-sizes-6-colors-unisex-non-slip-soft-sole-leather-tassel-toddler-prewalker-baby-shoes_p3161.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/491cb927d5da7abd99fe4366445a3348.jpg",
                    name: "Wholesale Baby Shoes Leather Prewalker Shoes Oxford Shoes Baby",
                    price: "2.29",
                    url: "https://www.shoestp.com/wholesale-baby-shoes-leather-prewalker-shoes-oxford-shoes-baby_p3185.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/30127dce7dd8e4b203eb8b2d42a3bf45.jpg",
                    name: "Wholesale Toddler Boots Soft Sole Toddler Shoes Pre-walkers Soft Baby Shoes",
                    price: "4.19",
                    url: "https://www.shoestp.com/wholesale-toddler-boots-soft-sole-toddler-shoes-pre-walkers-soft-baby-shoes_p3186.html"
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
