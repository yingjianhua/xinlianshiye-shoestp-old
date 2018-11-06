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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/68d40eb79cde76a2037691734b2fd585.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/69a8253d41428c1875ee40c9d37fcfcd.jpg",
                    name: "SE Women New Style Ankle Boots With Buckle Fashion Casual Boots",
                    price: "9.50",
                    url: "https://www.shoestp.com/se-women-new-style-ankle-boots-with-buckle-fashion-casual-boots_p6110.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e1a2938aba1f782c1f68d4ee8bf6c2ed.jpg",
                    name: "SE Women Ankle Boots With Chain Fashion Casual Boots",
                    price: "8.60",
                    url: "https://www.shoestp.com/se-women-ankle-boots-with-chain-fashion-casual-boots_p6106.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/30392374f707a94254241114063d543b.jpg",
                    name: "SE Women New Style Ankle Boots With Buckle Casual Boots",
                    price: "9.20",
                    url: "https://www.shoestp.com/se-women-new-style-ankle-boots-with-buckle-casual-boots_p6105.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e6250577f7c24535d1c23a910ce0588d.jpg",
                    name: "SE New Styles Flat Ankle Boots",
                    price: "8.80",
                    url: "https://www.shoestp.com/se-new-styles-flat-ankle-boots_p6098.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/237aa87bd37ac42b0dda418c8bee3624.jpg",
                    name: "SE Women Fashion Flat Ankle Boots Winter Boots",
                    price: "8.50",
                    url: "https://www.shoestp.com/se-women-fashion-flat-ankle-boots-winter-boots_p6096.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e74a403766ec347e362241cc24a15eea.jpg",
                    name: "SE Popular Casual Zipper Women's lace up Ankle Boots PU Boots",
                    price: "8.20",
                    url: "https://www.shoestp.com/se-popular-casual-zipper-womens-lace-up-ankle-boots-pu-boots_p6069.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3546194c38e4b45893e7e43bcbe905bf.jpg",
                    name: "SE Popular Casual Women's lace up Ankle Boots PU Boots",
                    price: "8.30",
                    url: "https://www.shoestp.com/se-popular-casual-womens-lace-up-ankle-boots-pu-boots_p6068.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b9893405ee6ff2046702e126ee762da5.jpg",
                    name: "SE Women's lace up Ankle Boots Emboridery PU Boots",
                    price: "9.60",
                    url: "https://www.shoestp.com/se-womens-lace-up-ankle-boots-emboridery-pu-boots_p6060.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4d77d5e8d45afa7482009e133bd3b271.jpg",
                    name: "SE Women Fashion Flat Ankle Boots New styles Boots",
                    price: "8.80",
                    url: "https://www.shoestp.com/se-women-fashion-flat-ankle-boots-new-styles-boots_p6097.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/708e039c7f46752ddf3b3784def0b6a3.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f757963329b40ff0b98105c732cd8d78.jpg",
                    name: "Debang 2018 Fashion Classic Black Ankle Martin Boots For Women",
                    price: "10.00",
                    url: "https://www.shoestp.com/debang-2018-fashion-classic-black-ankle-martin-boots-for-women_p6053.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cfa55c7e6cf74a2d2ed5cd1a43dccfc.jpg",
                    name: "Dingtai Ladies Black Comfortable Ankle Buckle Strap With Inner Zip Boots On Rubber Sole",
                    price: "8.00",
                    url: "https://www.shoestp.com/dingtai-ladies-black-comfortable-ankle-buckle-strap-with-inner-zip-boots-on-rubber-sole_p6031.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/81ea9fa1fd26e1bd24f4d5c101f7ce9e.jpg",
                    name: "SE Women Fashion Flat Ankle Boots Winter Boots",
                    price: "8.80",
                    url: "https://www.shoestp.com/se-women-fashion-flat-ankle-boots-winter-boots_p6078.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1f43e37cfdf95b701566aa53687ba940.jpg",
                    name: "Debang Popular Design Classic Crochet Ladies Half Boot Shoes",
                    price: "9.00",
                    url: "https://www.shoestp.com/debang-popular-design-classic-crochet-ladies-half-boot-shoes_p6056.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/642f41d7c83dd21477f3d8fe60e5c006.jpg",
                    name: "SE New Styles Flat Ankle Boots",
                    price: "8.50",
                    url: "https://www.shoestp.com/se-new-styles-flat-ankle-boots_p6104.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9bd55fa100ec8b65605182c80c9c6f6e.jpg",
                    name: "SE Women Ankle Boots Safety Boots",
                    price: "8.20",
                    url: "https://www.shoestp.com/se-women-ankle-boots-safety-boots_p6103.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b0311f532ee738ac8dd2ba7c301250b.jpg",
                    name: "SE Women Flat Ankle Boots Winter Camouflage Materials Boots",
                    price: "8.80",
                    url: "https://www.shoestp.com/se-women-flat-ankle-boots-winter-camouflage-materials-boots_p6087.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5e39ef5b313acee7990dfad409f98f7.jpg",
                    name: "Dingtai Hiking Boots For Kids Casual Shoes Comfort And Durability Flat Ankle Ladies Boots",
                    price: "7.50",
                    url: "https://www.shoestp.com/dingtai-hiking-boots-for-kids-casual-shoes-comfort-and-durability-flat-ankle-ladies-boots_p6034.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/59ec3dd5acfe28f02f5a6651cf06148b.jpg",
                    name: "SE Women's lace up Ankle Boots PU Boots",
                    price: "8.60",
                    url: "https://www.shoestp.com/se-womens-lace-up-ankle-boots-pu-boots_p6099.html"
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
