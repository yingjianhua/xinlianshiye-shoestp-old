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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fa306d091d4995bc672a8eb2c3d1bbe8.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6fed0980e68501df35cf2e3618ae6ae2.jpg",
                    name: "Soft and breathable girls lace up chelsea boots fashionable girls black pu ankle boots",
                    price: "7.60",
                    url: "https://www.shoestp.com/soft-and-breathable-girls-lace-up-chelsea-boots-fashionable-girls-black-pu-ankle-boots_p1441.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/eb7ea619db515ff6bd231a91863214c9.jpg",
                    name: "Manufacturers selling high grade sand color cute fancy girls shoes",
                    price: "6.60",
                    url: "https://www.shoestp.com/manufacturers-selling-high-grade-sand-color-cute-fancy-girls-shoes_p2520.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/82b1e3b6b8401627fe892f74e06d5bc.jpg",
                    name: "Import china wholesale fashion children kids girls shoes supplier",
                    price: "7.30",
                    url: "https://www.shoestp.com/import-china-wholesale-fashion-children-kids-girls-shoes-supplier_p2507.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dd5883a86ab7e868a5363f1231cbae54.jpg",
                    name: "Shining black women style name brand kids casual boots",
                    price: "12.50",
                    url: "https://www.shoestp.com/shining-black-women-style-name-brand-kids-casual-boots_p2509.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/552b524363a21d82fe1310510ee82ccd.jpg",
                    name: "2018 new model high quality girls shoes for kids children",
                    price: "6.60",
                    url: "https://www.shoestp.com/2018-new-model-high-quality-girls-shoes-for-kids-children_p2500.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/560c53cabf27427fdf287a62ed339e87.jpg",
                    name: "New design kids comfortable navy snow boots boys fur lining winter boots",
                    price: "12.55",
                    url: "https://www.shoestp.com/new-design-kids-comfortable-navy-snow-boots-boys-fur-lining-winter-boots_p2089.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4c01e974cbb7baabd4df7aec4011f110.jpg",
                    name: "Fashionable and comfortable kids winter booties boys pu navy ankle boots",
                    price: "7.25",
                    url: "https://www.shoestp.com/fashionable-and-comfortable-kids-winter-booties-boys-pu-navy-ankle-boots_p2052.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a45425f3033209bcea39fc2ea6c83bc1.jpg",
                    name: "2018 cool design kids soft sole snake grain pu leather boots girls casual martin boots",
                    price: "6.95",
                    url: "https://www.shoestp.com/2018-cool-design-kids-soft-sole-snake-grain-pu-leather-boots-girls-casual-martin-boots_p2027.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6b71c0d3b426118579b71704ff9900d9.jpg",
                    name: "2018 beautiful children kids shoes manufacturer for girls",
                    price: "8.60",
                    url: "https://www.shoestp.com/2018-beautiful-children-kids-shoes-manufacturer-for-girls_p2519.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dac388084e33b9025fa66c8fd5d85eb2.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/87db43d676314b7a78c61d348f07bcab.jpg",
                    name: "Winter warm youth kids ankle boots boys lace up black boots",
                    price: "7.25",
                    url: "https://www.shoestp.com/winter-warm-youth-kids-ankle-boots-boys-lace-up-black-boots_p2049.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/46a03f48a2f7ce5c82adcb458f642e4.jpg",
                    name: "New design winter season kids ankle boots warm boys boots sale",
                    price: "7.25",
                    url: "https://www.shoestp.com/new-design-winter-season-kids-ankle-boots-warm-boys-boots-sale_p1644.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3e93bc70943552298019b32742047131.jpg",
                    name: "2018 comfortable and nonslip kids warm winter boots custom boys black boots",
                    price: "7.50",
                    url: "https://www.shoestp.com/2018-comfortable-and-nonslip-kids-warm-winter-boots-custom-boys-black-boots_p1606.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ac2d024286fec421f5b57fb7b36b8785.jpg",
                    name: "New arrival free sample kids lace up dress boots boys winter work boots wholesale",
                    price: "7.25",
                    url: "https://www.shoestp.com/new-arrival-free-sample-kids-lace-up-dress-boots-boys-winter-work-boots-wholesale_p1345.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e586520c9465a99052fdef4d133b3201.jpg",
                    name: "2017 new arrival custom kids boots thick sole boys winter boots with zipper",
                    price: "7.25",
                    url: "https://www.shoestp.com/2017-new-arrival-custom-kids-boots-thick-sole-boys-winter-boots-with-zipper_p1404.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8a590b0a6d96182f2985751af61cbca5.jpg",
                    name: "2018 Winter season flexible kids lace up ankle boots boys work boots",
                    price: "7.35",
                    url: "https://www.shoestp.com/2018-winter-season-flexible-kids-lace-up-ankle-boots-boys-work-boots_p1430.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6f5ee78752d75549313ae624064ce7c3.jpg",
                    name: "hot selling best price export fancy boots low cut boy martin boots",
                    price: "7.85",
                    url: "https://www.shoestp.com/hot-selling-best-price-export-fancy-boots-low-cut-boy-martin-boots_p1512.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d390949a55ef2780713138dd8a4d2571.jpg",
                    name: "New arrival kids outdoor work boots fashion ankle boos boys winter boots",
                    price: "7.25",
                    url: "https://www.shoestp.com/new-arrival-kids-outdoor-work-boots-fashion-ankle-boos-boys-winter-boots_p1648.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ffad403795f6fa478ef7c1cdc02b538.jpg",
                    name: "Warm and soft pu high cut sneakers boys lace up pull on winter sneakers footwear",
                    price: "6.95",
                    url: "https://www.shoestp.com/warm-and-soft-pu-high-cut-sneakers-boys-lace-up-pull-on-winter-sneakers-footwear_p1367.html"
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
