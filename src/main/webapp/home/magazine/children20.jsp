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
        bgImg: "/public/upload/cnt/ad/cf88db23568af174e27bc484cb6f1756.jpg",
        itemImg: [
            {
                img: "/public/upload/cnt/ad/46c00479f529628d4cb36b0b5227414.jpg",
                name: "Piaodu Fashion Children Ankle Boots Black PU Good Qaulity Children Shoes",
                price: "10.8",
                url: "https://www.shoestp.com/piaodu-fashion-children-ankle-boots-black-pu-good-qaulity-children-shoes_p6504.html"
            },
            {
                img: "/public/upload/cnt/ad/798c4262dae313114c755917d3054326.jpg",
                name: "Piaodu Popular Children Boots Low Heel Children Casual PU Shoes",
                price: "10.1",
                url: "https://www.shoestp.com/piaodu-popular-children-boots-low-heel-children-casual-pu-shoes_p6513.html"
            },
            {
                img: "/public/upload/cnt/ad/fe11425bd156db7768e860eca1cd48c9.jpg",
                name: "Girls' Fashion Bowknot Side-zipper Low-heeled Ankle Shoes for Autumn and Winter",
                price: "6.05",
                url: "https://www.shoestp.com/girls'-fashion-bowknot-side-zipper-low-heeled-ankle-shoes-for-autumn-and-winter_p377.html"
            },
            {
                img: "/public/upload/cnt/ad/39ef9e62a86820171af482a23d838ef4.jpg",
                name: "Haiyue Fashion Children Boots Zebra/PU shoes with Outside Zipper",
                price: "9.8",
                url: "https://www.shoestp.com/haiyue-fashion-children-boots-zebrapu-shoes-with-outside-zipper_p6407.html"
            },
            {
                img: "/public/upload/cnt/ad/87d1163e3b084042915075034f36d224.jpg",
                name: "Kangfu Pu Leather High Ankle Boys Side Zipper Winter Boots",
                price: "10.2",
                url: "https://www.shoestp.com/kangfu-pu-leather-high-ankle-boys-side-zipper-winter-boots_p6311.html"
            },
            {
                img: "/public/upload/cnt/ad/bb09a3540b505bcd98c78d0294fdf62d.jpg",
                name: "Girl's Lacing up Ankle Shoes Round-toe Wear-resisting Thick-sole Wedge Warm Winter Casual Boots",
                price: "8.5",
                url: "https://www.shoestp.com/girl's-lacing-up-ankle-shoes-round-toe-wear-resisting-thick-sole-wedge-warm-winter-casual-boots_p61.html"
            },
            {
                img: "/public/upload/cnt/ad/6922e6651e22abcc3e6d9b280f304720.jpg",
                name: "Women's Vintage Ankle Boots Side Zipper Rding Shoes Buckle Shoes",
                price: "10",
                url: "https://www.shoestp.com/women's-vintage-ankle-boots-side-zipper-rding-shoes-buckle-shoes_p104.html"
            },
            {
                img: "/public/upload/cnt/ad/619decfa75861af47b9d9e9dd7c46485.jpg",
                name: "Boy and Girl's Color-matching Shoes Velcro Trendy Casual Flat Sneakers",
                price: "13",
                url: "https://www.shoestp.com/boy-and-girl's-color-matching-shoes-velcro-trendy-casual-flat-sneakers_p685.html"
            },
            {
                img: "/public/upload/cnt/ad/838c9807df48834eb9225f1509271935.jpg",
                name: "Kangfu Boy Winter High Ankle With Double Hook-Loop Boots",
                price: "10.1",
                url: "https://www.shoestp.com/kangfu-boy-winter-high-ankle-with-double-hook-loop-boots_p6308.html"
            },
        ]
    },
        {
            bgImg: "/public/upload/cnt/ad/3138d60e9a93ea649dff82e40a3acdcd.jpg",
            itemImg: [
                {
                    img: "/public/upload/cnt/ad/fcc4e208ce11ddd76e379f29630d6f81.jpg",
                    name: "Boy and Girl's High-cut Shoes Metal Zipper Trim Velcro Western Style Casual Flat Sneakers",
                    price: "13",
                    url: "https://www.shoestp.com/boy-and-girl's-high-cut-shoes-metal-zipper-trim-velcro-western-style-casual-flat-sneakers_p686.html"
                },
                {
                    img: "/public/upload/cnt/ad/eea88ea98e814b212b5f254d6d2c6768.jpg",
                    name: "Boy's Lacing up Ankle Shoes Round-toe Wedge Thick-sole D Eyelets Winter Warm Boots",
                    price: "9.8",
                    url: "https://www.shoestp.com/boy's-lacing-up-ankle-shoes-round-toe-wedge-thick-sole-d-eyelets-winter-warm-boots_p62.html"
                },
                {
                    img: "/public/upload/cnt/ad/1ec38b1defd37794c2411370ca299661.jpg",
                    name: "Joyou Kids Glitter High Upper Casual Shoes For Boys",
                    price: "4.5",
                    url: "https://www.shoestp.com/joyou-kids-glitter-high-upper-casual-shoes-for-boys_p5781.html"
                },
                {
                    img: "/public/upload/cnt/ad/504ed8a063ee6c891fd400cfea320dcd.jpg",
                    name: "Joyou New Model In Autumn & Winter PU Upper Casual Boys' Shoes",
                    price: "5",
                    url: "https://www.shoestp.com/joyou-new-model-in-autumn-&-winter-pu-upper-casual-boys'-shoes_p5751.html"
                },
                {
                    img: "/public/upload/cnt/ad/23cc8370da4b7eb7d5ce7829f26a0fc9.jpg",
                    name: "Boy's Camel Ankle Shoes Fashionable for Spring and Autumn Lacing-up Casual Shoes",
                    price: "6.4",
                    url: "https://www.shoestp.com/boy's-camel-ankle-shoes-fashionable-for-spring-and-autumn-lacing-up-casual-shoes_p72.html"
                },
                {
                    img: "/public/upload/cnt/ad/6670cc5f84c3d435c6e4a2243fac5552.jpg",
                    name: "Girl's Leisure Ankle Shoes Fashionable Side-zipper for Spring and Autumn Lacing-up Casual Shoes",
                    price: "7.2",
                    url: "https://www.shoestp.com/girl's-leisure-ankle-shoes-fashionable-side-zipper-for-spring-and-autumn-lacing-up-casual-shoes_p74.html"
                },
                {
                    img: "/public/upload/cnt/ad/af04f4066516aae9f5af0f64dfce72a0.jpg",
                    name: "Girl's Western Water-proof Side-zipper Ankle Boots for Autumn and Winter",
                    price: "8.7",
                    url: "https://www.shoestp.com/girl's-western-water-proof-side-zipper-ankle-boots-for-autumn-and-winter_p36.html"
                },
                {
                    img: "/public/upload/cnt/ad/d685bf868a29d5df687112fe39c333a1.jpg",
                    name: "Boy's Wear-resistant Western Style Lace-up PU Ankle Boots for Autumn and Winter",
                    price: "8.3",
                    url: "https://www.shoestp.com/boy's-wear-resistant-western-style-lace-up-pu-ankle-boots-for-autumn-and-winter_p37.html"
                },
                {
                    img: "/public/upload/cnt/ad/aaf164e468712dd180750dedce059ab0.jpg",
                    name: "Girl's Pink Leisure Shoes Fashionable for Spring and Autumn Casual Riding Boots",
                    price: "5.6",
                    url: "https://www.shoestp.com/girl's-pink-leisure-shoes-fashionable-for-spring-and-autumn-casual-riding-boots_p73.html"
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
