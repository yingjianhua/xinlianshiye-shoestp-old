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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8f65afd3d4966af9a0d9ef2dc2d2dbd8.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/77c8c083991363eab039336922e228bb.jpg",
                    name: "High quality durable casual men safety shoes",
                    price: "14.60",
                    url: "https://www.shoestp.com/high-quality-durable-casual-men-safety-shoes_p1916.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c0e00545eb2b16c968b04350cf32f911.jpg",
                    name: "cheapest mining industrial safety shoe anti lsip function middle safety boot",
                    price: "7.00",
                    url: "https://www.shoestp.com/cheapest-mining-industrial-safety-shoe-anti-lsip-function-middle-safety-boot_p1849.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f4ca23c9af7a210dee3e3fde27f99dfc.jpg",
                    name: "High quality Leather Safety Shoes S1P Men Work Boot with Steel Toe low price",
                    price: "9.90",
                    url: "https://www.shoestp.com/high-quality-leather-safety-shoes-s1p-men-work-boot-with-steel-toe-low-price_p1797.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e519e09a3cbaf43152fbb4bcc811992f.jpg",
                    name: "Fashionable unique durable composite toe slip-resistant work safety shoe",
                    price: "13.00",
                    url: "https://www.shoestp.com/fashionable-unique-durable-composite-toe-slip-resistant-work-safety-shoe_p1954.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6be98ec7dc29d533173c89a11177e0c.jpg",
                    name: "per Pair Cheap Wholesale Microfiber Leather Rubber Outsole Anti-skid Working Safety Shoes with Steel Toe Cap High Ankle",
                    price: "4.90",
                    url: "https://www.shoestp.com/usd39-per-pair-cheap-wholesale-microfiber-leather-rubber-outsole-anti-skid-working-safety-shoes-with-steel-toe-cap-high-ankle_p1971.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d14c93a9626e4777830ff17b30016274.jpg",
                    name: "New Design durable high top protective toe non-metallic work safety shoes",
                    price: "30.00",
                    url: "http://www.shoestp.com/new-design-durable-high-top-protective-toe-non-metallic-work-safety-shoes_p1959.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7425fc4a6b2de0b7d59728f38e36f74b.jpg",
                    name: "high quality breathable cowhide leather steel toe cap working lightweight safety shoes low ankle sandal for summer",
                    price: "9.00",
                    url: "https://www.shoestp.com/high-quality-breathable-cowhide-leather-steel-toe-cap-working-lightweight-safety-shoes-low-ankle-sandal-for-summer_p2021.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ff73c09c0f727f0a6cf5af634fddef3a.jpg",
                    name: "high quality cowhide leather steel toe cap safety men shoes working boot high ankle with warm plush",
                    price: "8.50",
                    url: "http://www.shoestp.com/high-quality-cowhide-leather-steel-toe-cap-safety-men-shoes-working-boot-high-ankle-with-warm-plush_p1944.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/65326df3104375fcacef2b2f820c739f.jpg",
                    name: "china wholesale cheap price men safety shoes",
                    price: "6.55",
                    url: "https://www.shoestp.com/china-wholesale-cheap-price-men-safety-shoes_p1093.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/17a31e7b4a86511ccec573847b243278.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cf301344631aa23cea34fb2823b5816.jpg",
                    name: "low prices men beach nude sandals big size men beach sandals",
                    price: "6.90",
                    url: "https://www.shoestp.com/low-prices-men-beach-nude-sandals-big-size-men-beach-sandals_p1094.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f9a07d4b6c1e04c96eeff42c765bf5c6.jpg",
                    name: "Factory Price Smoothy 2016 New Men Sandals",
                    price: "7.35",
                    url: "https://www.shoestp.com/factory-price-smoothy-2016-new-men-sandals_p3789.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/553df7a8ad07fe352156c980aa543c80.jpg",
                    name: "new arrival rubber sole fashion hiking leather sandal shoes man stock in china factory 2018",
                    price: "4.40",
                    url: "https://www.shoestp.com/new-arrival-rubber-sole-fashion-hiking-leather-sandal-shoes-man-stock-in-china-factory-2018_p2073.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b2220ecb4c29335c9f6b2d6c7d35400d.jpg",
                    name: "Wholesale mens classic genuine rubber leather sports sandals shoes",
                    price: "8.99",
                    url: "https://www.shoestp.com/wholesale-mens-classic-genuine-rubber-leather-sports-sandals-shoes_p1453.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e0e5ac1535ea8af261370a329635b218.jpg",
                    name: "Hot Sell Athletic beach Mens Flip Flop Sandals",
                    price: "7.35",
                    url: "https://www.shoestp.com/hot-sell-athletic-beach-mens-flip-flop-sandals_p3781.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a6a02160b98f2e309703b584990e1abf.jpg",
                    name: "chinese suppliers rubber sole outdoor comfortable summer men hiking sandals 2018",
                    price: "7.76",
                    url: "https://www.shoestp.com/chinese-suppliers-rubber-sole-outdoor-comfortable-summer-men-hiking-sandals-2018_p2071.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e1d304936800b02c2b88c9939b421a22.jpg",
                    name: "Outdoor high quality genuine leather summer sandals men",
                    price: "14.91",
                    url: "https://www.shoestp.com/outdoor-high-quality-genuine-leather-summer-sandals-men_p2070.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/35411c1c192c0fe16e57008d8ddbdaf6.jpg",
                    name: "Hot sale fashionable men webbing sandals latest design factory made",
                    price: "9.99",
                    url: "https://www.shoestp.com/hot-sale-fashionable-men-webbing-sandals-latest-design-factory-made_p1601.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e29c19108865c658c245abcb4108c5b2.jpg",
                    name: "wholesale customzed simple new design sandals for men",
                    price: "5.40",
                    url: "https://www.shoestp.com/wholesale-customzed-simple-new-design-sandals-for-men_p4466.html"
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
