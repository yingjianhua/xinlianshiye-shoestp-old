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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c657a0db70faa97bb05c1f888c2ba964.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/beb681955fad6b51f278b076b65a368f.jpg",
                    name: "Mesh and camo PU with buckle strap casual shoes sport kids shoes for boys",
                    price: "3.00",
                    url: "https://www.shoestp.com/mesh-and-camo-pu-with-buckle-strap-casual-shoes-sport-kids-shoes-for-boys_p2388.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/50f35ff2084e658316c22f73b448d70f.jpg",
                    name: "Fashion Cool Branded Design Mesh Upper Sports Shoes",
                    price: "5.50",
                    url: "https://www.shoestp.com/fashion-cool-branded-design-mesh-upper-sports-shoes_p2404.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/66975d0983f0770b4250e79c10f8b0f4.jpg",
                    name: "Light pattern PU with double magic tape sport shoes for boys from wenling",
                    price: "3.00",
                    url: "https://www.shoestp.com/light-pattern-pu-with-double-magic-tape-sport-shoes-for-boys-from-wenling_p2381.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/302fc486cfc0e1b4e7862958c966b3b.jpg",
                    name: "Sports style boy autumn mixture color magic type sneakers",
                    price: "15.00",
                    url: "https://www.shoestp.com/sports-style-boy-autumn-mixture-color-magic-type-sneakers_p2433.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ead01200c7c6846572a79453f3bf087e.jpg",
                    name: "Seal Heating PU Upper Elastic Strap Trainer shoes",
                    price: "5.00",
                    url: "https://www.shoestp.com/seal-heating-pu-upper-elastic-strap-trainer-shoes_p2397.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/33b3d59a4fef8b175534bf7571656791.jpg",
                    name: "Magic type camouflage children autumn sneakers",
                    price: "15.00",
                    url: "https://www.shoestp.com/magic-type-camouflage-children-autumn-sneakers_p2429.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/46717b38303120db27d3a7b1beb7a3e9.jpg",
                    name: "Suitable jean pattern girl walking shoes with zipper",
                    price: "10.40",
                    url: "https://www.shoestp.com/suitable-jean-pattern-girl-walking-shoes-with-zipper_p2493.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e96d1e3c154b71aee1ae79c4745e1181.jpg",
                    name: "boys sports shoes boys children running shoes",
                    price: "7.60",
                    url: "https://www.shoestp.com/boys-sports-shoes-boys-children-running-shoes_p1634.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9986da8c10fa8f5aac308b84f9071366.jpg",
                    name: "Flamingo Mixture color comfortable autumn magic type casual shoes",
                    price: "15.00",
                    url: "https://www.shoestp.com/flamingo-mixture-color-comfortable-autumn-magic-type-casual-shoes_p2460.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3c2c18440ba53a7ed7cf5c625f1e2d4.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ac5edb46c25100693e0e11dafb848c2c.jpg",
                    name: "Latest low price made in china manufacturer new fashionable kids import flat sandals shoes girls",
                    price: "3.60",
                    url: "https://www.shoestp.com/latest-low-price-made-in-china-manufacturer-new-fashionable-kids-import-flat-sandals-shoes-girls_p2453.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2871f27fe0559e5c1e123aa1ababa6f1.jpg",
                    name: "Cheap wholesale children fancy casual fashion sandals for kids girls",
                    price: "5.60",
                    url: "https://www.shoestp.com/cheap-wholesale-children-fancy-casual-fashion-sandals-for-kids-girls_p2478.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e27ac7477b75c898fc956987f6d57212.jpg",
                    name: "2018 color harmony summer korean style girls shoes sandals",
                    price: "6.60",
                    url: "https://www.shoestp.com/2018-color-harmony-summer-korean-style-girls-shoes-sandals_p2481.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/65a75d195aba71f0e52e56e5ce3e47c0.jpg",
                    name: "Factory outlets latest design party new girls sandals for kids children",
                    price: "7.80",
                    url: "https://www.shoestp.com/factory-outlets-latest-design-party-new-girls-sandals-for-kids-children_p2475.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f591fdb174f44d5962ed0ab813164a92.jpg",
                    name: "High grade attractive new style girls gold flat sandals design",
                    price: "5.50",
                    url: "https://www.shoestp.com/high-grade-attractive-new-style-girls-gold-flat-sandals-design_p2479.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/46fe87f2b667e076eea176a3dcd969e8.jpg",
                    name: "Factory supply cheap casual girls shoes fancy fashion sandals for kids",
                    price: "3.20",
                    url: "https://www.shoestp.com/factory-supply-cheap-casual-girls-shoes-fancy-fashion-sandals-for-kids_p2463.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6e379f5594e8b6d944e79b81b3f2470d.jpg",
                    name: "Girl's Canvas Sandals Leather Fashionable Bowknot Stripe Buckle Wear-resistant Thick-sole Wedge Summer Shoes",
                    price: "6.30",
                    url: "https://www.shoestp.com/girls-canvas-sandals-leather-fashionable-bowknot-stripe-buckle-wear-resistant-thick-sole-wedge-summer-shoes_p0052.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/96c874b794672485d9d3b82d03f265f2.jpg",
                    name: "Girl's Sling-back Sandals Lovely Butterfly Leather Buckle Wear-resistant Summer Sandals",
                    price: "6.90",
                    url: "https://www.shoestp.com/girls-sling-back-sandals-lovely-butterfly-leather-buckle-wear-resistant-summer-sandals_p0047.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1aad14b0c2871975823fc627c37af71f.jpg",
                    name: "Supplier direct pu new design boys child sandals 2018",
                    price: "3.20",
                    url: "https://www.shoestp.com/supplier-direct-pu-new-design-boys-child-sandals-2018_p2465.html"
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
