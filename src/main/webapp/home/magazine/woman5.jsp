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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/176dcae7052394b65a98d60f47273008.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e49a307cfac1009b8e23e339f25358c7.jpg",
                    name: "Latest Women Pumps Wine Red Color Suede Uppers Pointed Toe Chunky High Heel Women Pump Shoes",
                    price: "15.99",
                    url: "https://www.shoestp.com/latest-women-pumps-wine-red-color-suede-uppers-pointed-toe-chunky-high-heel-women-pump-shoes_p2263.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/423d1ab18d571937d5ac4ccabe84fe65.jpg",
                    name: "Pointed shoes ankle strap shallow mouth unique heel custom women shoes",
                    price: "19.30",
                    url: "https://www.shoestp.com/pointed-shoes-ankle-strap-shallow-mouth-unique-heel-custom-women-shoes_p2341.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d4d1eb431281f56e2fb8c79382980410.jpg",
                    name: "ladies sexy chunky heels women bridal dress shoes",
                    price: "11.50",
                    url: "https://www.shoestp.com/ladies-sexy-chunky-heels-women-bridal-dress-shoes_p1100.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/784e81d2bda88baabfda783b595fb820.jpg",
                    name: "ladies bridal dress shoes chunky heels",
                    price: "6.90",
                    url: "https://www.shoestp.com/ladies-bridal-dress-shoes-chunky-heels_p1268.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/86f84a96d1e0983cd894db719f3ef50c.jpg",
                    name: "China supplier cheap black ribbon lace-up sexy girls wearing high heels shoes",
                    price: "11.96",
                    url: "https://www.shoestp.com/china-supplier-cheap-black-ribbon-lace-up-sexy-girls-wearing-high-heels-shoes_p4351.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c02efeaaecb6354eac0b3d7048a45052.jpg",
                    name: "wholesale dark blue fashion shoes women leather pumps hand made simple design shoes without lace",
                    price: "11.90",
                    url: "https://www.shoestp.com/wholesale-dark-blue-fashion-shoes-women-leather-pumps-hand-made-simple-design-shoes-without-lace_p2227.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6c0ca93cfd152e77080b76541c3cc57.jpg",
                    name: "New design faux suede pumps lady chunky heel shoes with back zipper",
                    price: "19.90",
                    url: "https://www.shoestp.com/new-design-faux-suede-pumps-lady-chunky-heel-shoes-with-back-zipper_p2270.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/54e2c981d4db80b00d0e9ba13eaf99d9.jpg",
                    name: "Fashion Women’s High Heels Color Matching Chunky Heels Pointed Toe Shoes",
                    price: "10.30",
                    url: "https://www.shoestp.com/fashion-womens-high-heels-color-matching-chunky-heels-pointed-toe-shoes_p2405.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1a67caa3fe1408839b3d81e0f3fd6fbd.jpg",
                    name: "Latest elegant and comfortable ladies pointy high heel shoes",
                    price: "8.00",
                    url: "https://www.shoestp.com/latest-elegant-and-comfortable-ladies-pointy-high-heel-shoes_p4346.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1afae10b5761fcfd06c8e25e707642a7.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/768c330d83d7ba897e77e11f1670b6d4.jpg",
                    name: "Women Cushion Air Balance Shoes",
                    price: "9.00",
                    url: "https://www.shoestp.com/women-cushion-air-balance-shoes_p3093.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b96e3c3de49d37131bb4707b7efa3f02.jpg",
                    name: "Waterproof Outdoor Sports Trekking Hiking Shoes For Women",
                    price: "11.00",
                    url: "https://www.shoestp.com/waterproof-outdoor-sports-trekking-hiking-shoes-for-women_p3028.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a576aa62d4d0256cb69b29a68deca089.jpg",
                    name: "Hot Sale Flat Online Sports Shoes",
                    price: "9.00",
                    url: "https://www.shoestp.com/hot-sale-flat-online-sports-shoes_p3095.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/676bb04974a5747a5ee6cca9d6d758af.jpg",
                    name: "Hot Selling Student Sport Outdoor Snekers Training Running Sneakers Shoes For Reseller",
                    price: "13.00",
                    url: "https://www.shoestp.com/hot-selling-student-sport-outdoor-snekers-training-running-sneakers-shoes-for-reseller_p3039.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c1132ceadf578c69150d6fcf0fd5d84c.jpg",
                    name: "Hiking Shoes For Women Breathable Freely Outdoor Trekking Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/hiking-shoes-for-women-breathable-freely-outdoor-trekking-shoes_p3062.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dfbebf8c2442341998932b74e04cc600.jpg",
                    name: "New Style Comfortable Durable Brand Sport Shoes",
                    price: "9.00",
                    url: "https://www.shoestp.com/new-style-comfortable-durable-brand-sport-shoes_p3088.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/13623039f1c6dd1a15505f058eccd6b6.jpg",
                    name: "Comfortable Breathable Air Fashion Sport Shoes",
                    price: "11.00",
                    url: "https://www.shoestp.com/comfortable-breathable-air-fashion-sport-shoes_p3089.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8b00f6604c4c31de974380c2da4f265b.jpg",
                    name: "Latest Design Lightweight Best Womens Running Shoes",
                    price: "9.00",
                    url: "https://www.shoestp.com/latest-design-lightweight-best-womens-running-shoes_p3078.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7ec3f295d67548ae37909958a65dacc4.jpg",
                    name: "Air Free Sport Shoe Running Shoes Women",
                    price: "11.00",
                    url: "https://www.shoestp.com/air-free-sport-shoe-running-shoes-women_p3036.html"
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
