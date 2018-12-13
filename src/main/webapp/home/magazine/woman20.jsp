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
        .goods-box .goods-list > a:last-child .goods-item {
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
        bgImg: "/public/upload/cnt/ad/caf762a6cfd14f5490f44aba937fc421.jpg",
        itemImg: [
            {
                img: "/public/upload/cnt/ad/7b8e40213d59c779992ecd6162216819.jpg",
                name: "DABOWEN LADIES FASHION MID-HIGH RIVET SHOES LACE-UP YOUTH TREND CASUAL FLAT LOAFER SHOES WOMEN CANVAS SHOES SPRING&AUTUMN NEW ARRIVAL",
                price: "6.5",
                url: "https://www.shoestp.com/dabowen-ladies-fashion-mid-high-rivet-shoes-lace-up-youth-trend-casual-flat-loafer-shoes-women-canvas-shoes-spring&autumn-new-arrival_p2148.html"
            },
            {
                img: "/public/upload/cnt/ad/18c002e89fe82f601ac0b346da5d29a.jpg",
                name: "Fengsheng fashion round head with zipper ankle boots casual shoes",
                price: "9.8",
                url: "https://www.shoestp.com/fengsheng-fashion-round-head-with-zipper-ankle-boots-casual-shoes_p6765.html"
            },
            {
                img: "/public/upload/cnt/ad/8a297953bbc58e53dbc20e7e625282c7.jpg",
                name: "Women's Casual Simple Slip-ons Fashionable All Match Chunky Heeled Ankle Shoes",
                price: "7.8",
                url: "https://www.shoestp.com/women's-casual-simple-slip-ons-fashionable-all-match-chunky-heeled-ankle-shoes_p354.html"
            },
            {
                img: "/public/upload/cnt/ad/e2be0a5783c8ff360f26158dd0e200c9.jpg",
                name: "Women's Ankle Boots Simple Metal Buckle Side Zipper British Style Spring and Autumn Fahionable Chunky Heel Short Boots",
                price: "18",
                url: "https://www.shoestp.com/women's-ankle-boots-simple-metal-buckle-side-zipper-british-style-spring-and-autumn-fahionable-chunky-heel-short-boots_p402.html"
            },
            {
                img: "/public/upload/cnt/ad/1021ec1cf41a6b7bc25ea3d3f8faed58.jpg",
                name: "Women's Casual Fashion Black Leather Boots Side Zipper Middle Heeled Ankle Shoes for All Seasons",
                price: "8.9",
                url: "https://www.shoestp.com/women's-casual-fashion-black-leather-boots-side-zipper-middle-heeled-ankle-shoes-for-all-seasons_p267.html"
            },
            {
                img: "/public/upload/cnt/ad/cca20a36901d242e0bbdbdc8b9c551c5.jpg",
                name: "Women's Chic Metal Buckle Leather Boots Side Zipper Western Style Casual Ankle Shoes for All Seasons",
                price: "9.5",
                url: "https://www.shoestp.com/women's-chic-metal-buckle-leather-boots-side-zipper-western-style-casual-ankle-shoes-for-all-seasons_p266.html"
            },
            {
                img: "/public/upload/cnt/ad/c237b278e70a5dc013ccd0d3360d3a16.jpg",
                name: "Women's PU Ankle Boots Fashionable Chunky Heels Western Spring and Autumn Pointed Toe Side-Zipper Simple Boots",
                price: "9.9",
                url: "https://www.shoestp.com/women's-pu-ankle-boots-fashionable-chunky-heels-western-spring-and-autumn-pointed-toe-side-zipper-simple-boots_p271.html"
            },
            {
                img: "/public/upload/cnt/ad/629a3d06c5800cc0453e335a28a6b99.jpg",
                name: "Women's Black PU Boots Letter Straps Pattern Pointed Toe Back Zipper Chunky Heel Seasons Western Boots",
                price: "9.9",
                url: "https://www.shoestp.com/women's-black-pu-boots-letter-straps-pattern-pointed-toe-back-zipper-chunky-heel-seasons-western-boots_p279.html"
            },
            {
                img: "/public/upload/cnt/ad/e784c7954b77db90f2edc06658b7fcb1.jpg",
                name: "Women's Simple Ankle Boots Winter All-match Perfect Side Zipper Chunky Heel Short Boots",
                price: "22",
                url: "https://www.shoestp.com/women's-simple-ankle-boots-winter-all-match-perfect-side-zipper-chunky-heel-short-boots_p403.html"
            },
        ]
    },
        {
            bgImg: "/public/upload/cnt/ad/d5f6821d1b71a99101e232c153b39eca.jpg",
            itemImg: [
                {
                    img: "/public/upload/cnt/ad/797f2fdde8a54ef8b05938ef76ededbe.jpg",
                    name: "Women's Velvet Boots with Flower Embroidery Heels Pointed Toe Side-opening Ankle Boots",
                    price: "8.8",
                    url: "https://www.shoestp.com/women's-velvet-boots-with-flower-embroidery--heels-pointed-toe-side-opening-ankle-boots_p295.html"
                },
                {
                    img: "/public/upload/cnt/ad/887028332e4d6be9c4663582ae022d61.jpg",
                    name: "Ladies' Pointed Simple Leather Boots Black Autumn and Winter Zipper Waterproof High Heel with Metal Panel Warm Boots",
                    price: "9.78",
                    url: "https://www.shoestp.com/ladies'-pointed-simple-leather-boots-black-autumn-and-winter-zipper-waterproof-high-heel-with-metal-panel-warm-boots_p177.html"
                },
                {
                    img: "/public/upload/cnt/ad/2f695f663e1240d9cd64b35dedecf973.jpg",
                    name: "Ladies' Microfiber Simple Shoes Fashionable Pointed Toe Autumn and Winter Zipper Western High Heel Warm Boots",
                    price: "8.89",
                    url: "https://www.shoestp.com/ladies'-microfiber-simple-shoes-fashionable-pointed-toe-autumn-and-winter-zipper-western-high-heel-warm-boots_p178.html"
                },
                {
                    img: "/public/upload/cnt/ad/b8ae33d5aad009887bc08847537155f8.jpg",
                    name: "Jielida women short boots suede rough heels ankle boots",
                    price: "10.5",
                    url: "https://www.shoestp.com/jielida-women-short-boots-suede-rough-heels-ankle-boots_p6816.html"
                },
                {
                    img: "/public/upload/cnt/ad/8f6e3d02d2137dda9f48562feb8d22ac.jpg",
                    name: "Women's Simple Boots Zipper Western Spring and Autumn Casual Chunky Heel Short Boots",
                    price: "11.35",
                    url: "https://www.shoestp.com/women's-simple-boots-zipper-western-spring-and-autumn-casual-chunky-heel-short-boots_p488.html"
                },
                {
                    img: "/public/upload/cnt/ad/e62e099bd88f40d1fb4c4d12c56be954.jpg",
                    name: "Women's Boots with Fashionable Weave Strap Metal Buckle Side Zipper Western Simple Chunky Heel Short Boots for Spring and Autumn",
                    price: "11.75",
                    url: "https://www.shoestp.com/women's-boots-with-fashionable-weave-strap-metal-buckle-side-zipper-western-simple-chunky-heel-short-boots-for-spring-and-autumn_p485.html"
                },
                {
                    img: "/public/upload/cnt/ad/8995402b0877f0eabe344e4a3ee2c9aa.jpg",
                    name: "Women's Fashion Shoes Metal Buckle Side Zipper Western Style Spring and Autumn Casual Chunky Heel Short Boots",
                    price: "9.8",
                    url: "https://www.shoestp.com/women's-fashion-shoes-metal-buckle-side-zipper-western-style-spring-and-autumn-casual-chunky-heel-short-boots_p592.html"
                },
                {
                    img: "/public/upload/cnt/ad/eebef9d1697b30108818ec63a967b032.jpg",
                    name: "Women's Fashionable Boots Metal Rivets Buckle Side Zipper British Punk Style Casual Flats Short Boots",
                    price: "10.5",
                    url: "https://www.shoestp.com/women's-fashionable-boots-metal-rivets-buckle-side-zipper-british-punk-style-casual-flats-short-boots_p593.html"
                },
                {
                    img: "/public/upload/cnt/ad/985d6ac04b8125db8049c427739b8b65.jpg",
                    name: "Jielida winter comfortable flat style suede ankle boots",
                    price: "11.6",
                    url: "https://www.shoestp.com/jielida-winter-comfortable-flat-style-suede-ankle-boots_p6804.html"
                },
            ]
        }
    ]
    var item = ''
    var imageBaseUrl = 'https://image.shoestp.com'
    $.each(data, function (i, ele) {
        item +=
            '<div class="goods-bgImg">' +
            '<img src="' + imageBaseUrl + ele.bgImg + '" alt="">' +
            '</div>' +
            '<div class="goods-list clearfix">';
        $.each(ele.itemImg, function (i, e) {
            item +=
                '<a href="' + e.url + '" target="_blank">' +
                '<div class="goods-item">' +
                '<img src="' + imageBaseUrl + e.img + '" alt="">' +
                '<div class="goods-item-info">' +
                '<p>' +
                e.name +
                '</p>' +
                '<h3>' +
                '$' + e.price +
                '</h3>' +
                '</div>' +
                '</div>' +
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
