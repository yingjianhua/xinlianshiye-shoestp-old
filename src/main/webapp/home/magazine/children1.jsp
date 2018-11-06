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

<script>
    var data = [{
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e1fec08b249c763f015e89512860d5c3.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/54cb14eaca3d23233b1aef22143b6722.jpg",
                    name: "Best price latest designs fashion blue beautiful lazy children boy kids shoes",
                    price: "3.20",
                    url: "https://www.shoestp.com/best-price-latest-designs-fashion-blue-beautiful-lazy-children-boy-kids-shoes_p2467.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/972b67f90162574437c28aef76b95c73.jpg",
                    name: "2018 high quality color harmony china fashion style boys sandals",
                    price: "3.60",
                    url: "https://www.shoestp.com/2018-high-quality-color-harmony-china-fashion-style-boys-sandals_p2435.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/13ea8683e5b478265262f101654286b6.jpg",
                    name: "hot selling fashion style summer children shoes kids sandals cheap casual summer children shoes kids sandals",
                    price: "4.95",
                    url: "https://www.shoestp.com/hot-selling-fashion-style-summer-children-shoes-kids-sandals-cheap-casual-summer-children-shoes-kids-sandals-hw-1009_p1871.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cd4d0c070814c2d9ad57c95b4e9cff55.jpg",
                    name: "New blue pu leather wrapped toe casual boys sandals",
                    price: "4.45",
                    url: "https://www.shoestp.com/new-blue-pu-leather-wrapped-toe-casual-boys-sandals_p4205.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/683851e93daf05efe146058ec603702a.jpg",
                    name: "Cheap blue pu leather beach boys closed toe sandals",
                    price: "3.85",
                    url: "https://www.shoestp.com/cheap-blue-pu-leather-beach-boys-closed-toe-sandals_p4221.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a4b02536534088fc8a2a881b67bc5416.jpg",
                    name: "2018 dark blue gray pu leather wrapped toe beach kids sandals",
                    price: "4.85",
                    url: "https://www.shoestp.com/2018-dark-blue-gray-pu-leather-wrapped-toe-beach-kids-sandals_p4195.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/67de67f7b8b708ff18f025c70b24bf99.jpg",
                    name: "2018 summer new design thick sole top model sandals fashionable boys summer sandals",
                    price: "4.50",
                    url: "https://www.shoestp.com/2018-summer-new-design-thick-sole-top-model-sandals-fashionable-boys-summer-sandals_p1472.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/85af1f64e2aef75771116434d2b25e1d.jpg",
                    name: "2018 kids new arrival flat summer sandals fashion boys PU leather strap sandals",
                    price: "4.45",
                    url: "https://www.shoestp.com/2018-kids-new-arrival-flat-summer-sandals-fashion-boys-pu-leather-strap-sandals_p1421.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b2787a3fc1615b2ab2f961ebe957c349.jpg",
                    name: "Boys' Chic Velcro Sling-back Light Breathable Slip-resistant Beach Sandals",
                    price: "6.50",
                    url: "https://www.shoestp.com/boys-chic-velcro-sling-back-light-breathable-slip-resistant-beach-sandals_p0248.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7587b47237619da8dbb5460472551750.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/544e510c1ad06c0647482a8a80026cf8.jpg",
                    name: "Sport style blue boy leather orthopetic ankle boots",
                    price: "13.40",
                    url: "https://www.shoestp.com/sport-style-blue-boy-leather-orthopetic-ankle-boots_p2555.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/45d789e083a5823a760ad11ddeaf36a8.jpg",
                    name: "Hot sale lastest design kids pu leather boots custom fashion winter boots for boys",
                    price: "7.25",
                    url: "https://www.shoestp.com/hot-sale-lastest-design-kids-pu-leather-boots-custom-fashion-winter-boots-for-boys_p1637.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ff4a3f57738390992f5a1fa8e749a42c.jpg",
                    name: "Winter warm youth kids ankle boots boys lace up black boots",
                    price: "7.25",
                    url: "https://www.shoestp.com/winter-warm-youth-kids-ankle-boots-boys-lace-up-black-boots_p2049.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/96a89b1a6fde3adef642a3f17391bd2d.jpg",
                    name: "2017 new arrival custom kids boots thick sole boys winter boots with zipper",
                    price: "7.25",
                    url: "https://www.shoestp.com/2017-new-arrival-custom-kids-boots-thick-sole-boys-winter-boots-with-zipper_p1404.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6120750c0d2c6fc70c3fd97f973f5714.jpg",
                    name: "hot selling best price export fancy boots low cut boy martin boots",
                    price: "7.85",
                    url: "https://www.shoestp.com/hot-selling-best-price-export-fancy-boots-low-cut-boy-martin-boots_p1512.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/77ce70df264e8bff026386ebda4db85d.jpg",
                    name: "Sport style star decoration boy leather orthopetic boots",
                    price: "13.40",
                    url: "https://www.shoestp.com/sport-style-star-decoration-boy-leather-orthopetic-boots_p2547.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/81121ef6980cf29b344086c761e69bd1.jpg",
                    name: "High quality best price cute wholesale cool casual boy shoes",
                    price: "6.90",
                    url: "https://www.shoestp.com/high-quality-best-price-cute-wholesale-cool-casual-boy-shoes_p2494.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/29644f571f57ca5252e959e4baf0644.jpg",
                    name: "Comfortable navy lace-up boy anti-skiding casual shoes",
                    price: "12.49",
                    url: "https://www.shoestp.com/comfortable-navy-lace-up-boy-anti-skiding-casual-shoes_p2513.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1ecdcd54baf8e11efb460aa18cd2c4e5.jpg",
                    name: "2018 winter season fashion kids cheap boots boys custom warm boots for sale",
                    price: "6.85",
                    url: "https://www.shoestp.com/2018-winter-season-fashion-kids-cheap-boots-boys-custom-warm-boots-for-sale_p1372.html"
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
     <%@ include file="/home/magazine/template/new-foot.jsp" %>
</body>
</html>
