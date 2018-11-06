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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d05ac2f89f2a5d41c1ab673822a88ff3.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/92d6b6b5be2baf93ac066fd392ea05a8.jpg",
                    name: "Brand Handmade Genuine Leather Mens Blue Casual Shoes loafers clark style",
                    price: "17.90",
                    url: "https://www.shoestp.com/brand-handmade-genuine-leather-mens-blue-casual-shoes_p2173.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4514e134014933abaf4360215e1305b8.jpg",
                    name: "Vintage British Shoes, High Quality Flat Hard Sole Man Shoes, New Design Fashion Men Blue Casual Shoes",
                    price: "29.80",
                    url: "https://www.shoestp.com/vintage-british-shoes-high-quality-flat-hard-sole-man-shoes-new-design-fashion-men-blue-casual-shoes_p2176.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/43902e1c7a8ab1397874055a7bc8721d.jpg",
                    name: "2017 wholesale china cheap price leather dress shoes men blue",
                    price: "13.99",
                    url: "https://www.shoestp.com/hfrta119-2017-wholesale-china-cheap-price-leather-dress-shoes-men-blue_p2166.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b6a682a25076d051a07db1095f6fc7a0.jpg",
                    name: "2018 Hot Sale men Navy Blue Suede loafers Shoes clark styles",
                    price: "15.50",
                    url: "https://www.shoestp.com/2017-hot-sale-men-navy-blue-suede-loafers-shoes_p2169.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/30df8adffda82cadcb6b90e3483c3c1b.jpg",
                    name: "Italian Leather Boots Men Fur Boots",
                    price: "12.80",
                    url: "https://www.shoestp.com/italian-leather-boots-men-fur-boots_p2178.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f240e0b8efe9b50db2dbb86107e6335c.jpg",
                    name: "Vintage British Shoes, High Quality Flat Hard Sole Man Shoes, New Design Fashion Men Blue Casual Shoes",
                    price: "12.00",  
                    url: "https://www.shoestp.com/vintage-british-shoes-high-quality-flat-hard-sole-man-shoes-new-design-fashion-men-blue-casual-shoes_p2175.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/39ba7b9edfbfeb01f2c0c331feaa1614.jpg",
                    name: "blue men rhinestone leather casual shoes Dress Shoes Clark Style",
                    price: "50.00",
                    url: "https://www.shoestp.com/blue-men-rhinestone-leather-casual-shoes_p2170.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/94c89b6f1626a5331b773c4e8f053687.jpg",
                    name: "Chaussures Mocassin Hommes Blue Suede Leather Men Driving Shoes",
                    price: "23.20",
                    url: "https://www.shoestp.com/chaussures-mocassin-hommes-blue-suede-leather-men-driving-shoes_p2172.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6fd44a3d886ebc6ea42eed93c3aa2a1b.jpg",
                    name: "double monk strap shoe man blue genuine leather dress shoes handmade shoes for men Clark Style",
                    price: "180.00",
                    url: "https://www.shoestp.com/double-monk-strap-shoe-man-blue-genuine-leather-dress-shoes-handmade-shoes-for-men_p2167.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2950772d4cd031eabff608b49c88d5.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4c81599656972b86a832c7cb3b55e0ee.jpg",
                    name: "Cheap price popular oxford style men dress shoes for genuine leather",
                    price: "30.00",
                    url: "https://www.shoestp.com/cheap-price-popular-oxford-style-men-dress-shoes-for-genuine-leather_p2201.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/32dbb66b7baa40160d3fdf6fd6a92c0f.jpg",
                    name: "Mens round head breathable business shoes Dress Shoes Clark Style",
                    price: "27.50",
                    url: "https://www.shoestp.com/mens-round-head-breathable-business-shoes_p2203.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c705c9dd53e71f8fc8f4e0d39c5a7da0.jpg",
                    name: "ODM design formal shoes for men genuine leather",
                    price: "30.00",
                    url: "https://www.shoestp.com/odm-design-formal-shoes-for-men-genuine-leather_p2197.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/11d98a75e7ae037d7c0029d056aa263.jpg",
                    name: "Sell well classic oxfords genuine leather shoes men dress shoes Clark Style",
                    price: "28.00",
                    url: "https://www.shoestp.com/sell-well-classic-oxfords-genuine-leather-shoes-men-dress-shoes_p2194.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f92d3d3c6aaab839e3f48843dd849d05.jpg",
                    name: "High shiny fashion casual men trendy genuine leather dress shoes",
                    price: "30.00",
                    url: "https://www.shoestp.com/high-shiny-fashion-casual-men-trendy-genuine-leather-dress-shoes_p2198.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/223cd1b021d61c0ccd0b453671899e12.jpg",
                    name: "Fashion reliable quality genuine leather suede leather men dress shoes",
                    price: "28.00",
                    url: "https://www.shoestp.com/fashion-reliable-quality-genuine-leather-suede-leather-men-dress-shoes_p2193.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b5a188f47b628d37306255cc61361980.jpg",
                    name: "Fashion High Quality Blue Genuine Leather Men Dress Shoes, Designer Men Leather Shoes Clark Style",
                    price: "28.00",
                    url: "https://www.shoestp.com/fashion-high-quality-blue-genuine-leather-men-dress-shoes-designer-men-leather-shoes_p2191.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8946e4d1e05ddc50291eeb6493d53b59.jpg",
                    name: "Sell well classic oxfords genuine leather shoes men dress shoes Clark Style",
                    price: "28.00",
                    url: "https://www.shoestp.com/sell-well-classic-oxfords-genuine-leather-shoes-men-dress-shoes_p2195.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2f320d2bcfc105fa38c8ed41a10c3476.jpg",
                    name: "Custom size high-end men breathable casual leather shoes",
                    price: "28.00",
                    url: "https://www.shoestp.com/custom-size-high-end-men-breathable-casual-leather-shoes_p2200.html"
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
