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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1b212c3300bc98fadd5a000744ec50f9.jpg",
            itemImg: [{
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/29e8c65b9dfcf3b4ec7d789b6740e7d.jpg",
                    name: "Original 2018 lady pointy pencil high heel suede upper material navy blue dress shoes womens casual pure solid colour custom shoes",
                    price: "22.00",
                    url: "https://www.shoestp.com/original-2018-lady-pointy-pencil-high-heel-pu-upper-material-navy-blue-dress-shoes-womens-casual-pure-solid-colour-custom-shoes_p2285.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c749a2e1aa329e7944720fd0060a7088.jpg",
                    name: "Women's Ctystal Trim Shoes Spring and Autumn Slip-on Western Simple Chunky Single Shoes",
                    price: "9.50",
                    url: "https://www.shoestp.com/womens-ctystal-trim-shoes-spring-and-autumn-slip-on-western-simple-chunky-single-shoes_p0656.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4ee3d64e4771ce7eae7ef5df40fe32c1.jpg",
                    name: "ladies high heel shoes made in china women pumps fancy sexy dress shoes",
                    price: "10.40",
                    url: "https://www.shoestp.com/ladies-high-heel-shoes-made-in-china-women-pumps-fancy-sexy-dress-shoes_p1264.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/622725a7cac2a773090121bdc52adef2.jpg",
                    name: "2018 New look lady comfortable high quality women sport air cushione shoes",
                    price: "5.30",
                    url: "https://www.shoestp.com/ladies-chunky-heels-ladies-high-heels-dress-shoes-low-prices-women-work-shoes_p1289.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/88c9094b541116b4f3d5cad46b284e91.jpg",
                    name: "attractive design bow knot performance shoes super high heel rivets strap black women platform pumps shoes",
                    price: "13.90",
                    url: "https://www.shoestp.com/2018-hot-selling-wholesale-pointy-toe-rivets-studded-women-shoes-high-heels-fetish-pumps_p3048.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/acc05f836597788e03a4c5a586172eb3.jpg",
                    name: "2018 Hot Selling Wholesale Pointy Toe Rivets Studded Women Shoes High Heels Fetish Pumps",
                    price: "22.00",
                    url: "https://www.shoestp.com/2018-hot-selling-wholesale-pointy-toe-rivets-studded-women-shoes-high-heels-fetish-pumps_p3048.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7a19998ac08bc2229d8e285f8bb9ec9.jpg",
                    name: "ladies high heel shoes made in china big size high heel sandals",
                    price: "15.00",
                    url: "https://www.shoestp.com/ladies-high-heel-shoes-made-in-china-big-size-high-heel-sandals_p1286.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/991d1bf98e6ed8ece562e381298a2534.jpg",
                    name: "Women's High Heel Shoes Fashionable Crystal Slip-ons Spring and Autumn Business Shallow Single Shoes",
                    price: "15.30",
                    url: "https://www.shoestp.com/womens-high-heel-shoes-fashionable-crystal-slip-ons-spring-and-autumn-business-shallow-single-shoes_p0416.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/106312b08bfc9ffb63730d3e7fef14ba.jpg",
                    name: "latest high heel ladies shoes women high heels dress shoes",
                    price: "5.50",
                    url: "https://www.shoestp.com/latest-high-heel-ladies-shoes-women-high-heels-dress-shoes_p1194.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/34b60780d67c76096109094ca2f82658.jpg",
            itemImg: [{
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7421c35768be2d1a267de77a5c1a7950.jpg",
                    name: "lady suede leather shoes flat guangzhou factory oem",
                    price: "9.60",
                    url: "https://www.shoestp.com/lady-suede-leather-shoes-flat-guangzhou-factory-oem_p2255.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2c999ef937c1ec7ce873895f07345bd9.jpg",
                    name: "2018 Wholesale custom made casual designer stylish beautiful ladies flat shoes for women suede loafers",
                    price: "12.00",
                    url: "https://www.shoestp.com/2018-wholesale-custom-made-casual-designer-stylish-beautiful-ladies-flat-shoes-for-women-suede-loafers_p2244.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8c6b4375ff584e1ff7d48ad157274b75.jpg",
                    name: "2018 fashion New design fashion ladies shoes flat custom loafers women casual shoes",
                    price: "39.90",
                    url: "https://www.shoestp.com/2018-fashion-new-design-fashion-ladies-shoes-flat-custom-loafers-women-casual-shoes_p2231.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b24c78a90a09d48d1950652d0daedc10.jpg",
                    name: "Womens Fashion Suede Footwear Metal Spike Flat Heels Loafers Tassel Slip-on Ladies Shoes",
                    price: "30.00",
                    url: "https://www.shoestp.com/womens-fashion-suede-footwear-metal-spike-flat-heels-loafers-tassel-slip-on-ladies-shoes_p2220.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ba31d61664b543f43a02013c49a8f472.jpg",
                    name: "Women black round toe dress shoes with ant slip rubber outsole",
                    price: "17.94",
                    url: "https://www.shoestp.com/women-black-round-toe-dress-shoes-with-ant-slip-rubber-outsole_p4301.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/43a3f2023481bfa0de99b5f5b526c522.jpg",
                    name: "Loafer shoes for women black cow leather mental ornament loafer shoes",
                    price: "25.00",
                    url: "https://www.shoestp.com/yh1122-loafer-shoes-for-women-black-cow-leather-mental-ornament-loafer-shoes_p2223.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3401cf39b5cbd0500225eb86a2278695.jpg",
                    name: "Trendy light pink ladies low heel party dress shoes",
                    price: "18.94",
                    url: "https://www.shoestp.com/trendy-light-pink-ladies-low-heel-party-dress-shoes_p4310.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/956511d04347d50972b84e548bb3dd87.jpg",
                    name: "2018 Women'S Comfort Slip-On Loafer Driving Shoes",
                    price: "7.10",
                    url: "https://www.shoestp.com/2018-womens-comfort-slip-on-loafer-driving-shoes_p2288.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1cb7e1a6c054aa50d1e2e5e6c683c402.jpg",
                    name: "China manufacture wholesale comfortable black and white genuine leather flat loafers casual shoes for women",
                    price: "32.99",
                    url: "https://www.shoestp.com/china-manufacture-wholesale-comfortable-black-and-white-genuine-leather-flat-loafers-casual-shoes-for-women_p2233.html"
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
