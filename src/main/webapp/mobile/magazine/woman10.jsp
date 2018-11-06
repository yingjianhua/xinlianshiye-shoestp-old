<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@include file="/mobile/template/style_import.jsp" %>
</head>
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
    .goods-item {
        width: 24.3%;
        float: left;
        position: relative;
        font-size: 0;
        margin: 0.9% 0.9% 0 0;
        overflow: hidden;
    }
        .goods-item {
            width: 32.66%;
        }
        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }
        .goods-item-info {
            display: none;
        }
        .goods-box .goods-bgImg:nth-of-type(3) {
		    margin-top: 0.9%;
		}
</style>
	<jsp:include page="/mobile/magazine/template/header.jsp"></jsp:include>

    <div class="goods-box clearfix">

    </div>
    <%@ include file="/mobile/magazine/template/foot_menu.jsp" %>
<script>
        var data = [{
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/682cb471af8d0e08703b8bcf09547725.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f795e1c5605316fd25596ac4869a3d62.jpg",
                    name: "Debang New Style Fashionable Black Lady Boot The Double Side Zipper Woman Shoes Winter",
                    price: "15.00",
                    url: "https://www.shoestp.com/debang-new-style-fashionable-black-lady-boot-the-double-side-zipper-woman-shoes-winter_p6049.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/86346abc02b8b0afa97b5d9c9e33c1d9.jpg",
                    name: "Debang Women Fashion Over The Knee Boots Flat Sexy Thigh High Boots Ladies Winter Velvet Boots",
                    price: "17.00",
                    url: "https://www.shoestp.com/debang-women-fashion-over-the-knee-boots-flat-sexy-thigh-high-boots-ladies-winter-velvet-boots_p6048.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a21220c83331079fce816879d37defc.jpg",
                    name: "Debang Winter Wedge Black Fancy Overknee Boots Women",
                    price: "14.00",
                    url: "https://www.shoestp.com/debang-winter-wedge-black-fancy-overknee-boots-women_p6047.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d1cdd7d7967077450046223e3f3b95ee.jpg",
                    name: "Debang Winter Boots Knee-high Shoes Flat Lady Fashion Women Wedge Boots",
                    price: "12.00",
                    url: "https://www.shoestp.com/debang-winter-boots-knee-high-shoes-flat-lady-fashion-women-wedge-boots_p6046.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/21c83460dba0210159365771b0a1c39c.jpg",
                    name: "Debang Women Wide Calf Leather Big Size Knee High Boots",
                    price: "16.00",
                    url: "https://www.shoestp.com/debang-women-wide-calf-leather-big-size-knee-high-boots_p6044.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/201c93623d724ba011a587c04a9a4a1f.jpg",
                    name: "Debang Designs Shoes Zipper Side Leather Boots Wedges Boots Woman",
                    price: "13.00",
                    url: "https://www.shoestp.com/debang-designs-shoes-zipper-side-leather-boots-wedges-boots-woman_p6043.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e9fa042144016947863b6643a3ffc1b2.jpg",
                    name: "Debang Casual Winter Boots With Fur Calf Boots Calf Boot Knee High Boot With Zipper",
                    price: "13.00",
                    url: "https://www.shoestp.com/debang-casual-winter-boots-with-fur-calf-boots-calf-boot-knee-high-boot-with-zipper_p6042.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a7c971aba98fc3f3a1caa97a6860cec.jpg",
                    name: "Debang Block Heel Puleather Knee High Winter Warm Boots For Women",
                    price: "14.00",
                    url: "https://www.shoestp.com/debang-block-heel-puleather-knee-high-winter-warm-boots-for-women_p6041.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/64adc7ccab30a652f11e14ba8d7aefbb.jpg",
                    name: "Debang Fashion Keep Warm Suede Long Boots Flat Casual Boots With Tassel",
                    price: "15.00",
                    url: "https://www.shoestp.com/debang-fashion-keep-warm-suede-long-boots-flat-casual-boots-with-tassel_p6045.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/84227480565392ecf09139a86a798d28.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8f5d4214a737fab553cd60aaac576e3f.jpg",
                    name: "Debang Winter Women Boots PU Leather Round Toe Heel Mid Boots",
                    price: "16.00",
                    url: "https://www.shoestp.com/debang-winter-women-boots-pu-leather-round-toe-heel-mid-boots_p6040.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/50d874964f0fe9983c7e44f622a5e7f1.jpg",
                    name: "Debang Women Boots Flat Heel Leather Uppers Round Toe Long Boots Shoes For Ladies",
                    price: "12.00",
                    url: "https://www.shoestp.com/debang-women-boots-flat-heel-leather-uppers-round-toe-long-boots-shoes-for-ladies_p6039.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f9bb03740a7278063e948ea1c72ec310.jpg",
                    name: "Debang Women Boot Zip-up Boots Fashion Boot Cheap Boot",
                    price: "14.00",
                    url: "https://www.shoestp.com/debang-women-boot-zip-up-boots-fashion-boot-cheap-boot_p6038.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2ecd3f4bdb13279ff3980098e06efe91.jpg",
                    name: "2017 Honglu New Style Knee-High Women Boots,Fashion Zipper type Ladies Snow Boots",
                    price: "13.20",
                    url: "https://www.shoestp.com/2017-honglu-new-style-knee-high-women-bootsfashion-zipper-type-ladies-snow-boots_p1154.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ac84508e5997c65f47f51802b3154402.jpg",
                    name: "Xiawei 2018 Classic PU Womens High Boots Zipper Inside With Belt Round Toe Boots Chunky Heels",
                    price: "10.50",
                    url: "https://www.shoestp.com/xiawei-2018-classic-pu-womens-high-boots-zipper-inside-with-belt-round-toe-boots-chunky-heels_p5568.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ee3ca3f7f396e6888e30b9abd900e226.jpg",
                    name: "Xiawei 2018 Classic Snake PU Womens High Boots With Rivet And Zipper Inside Chunky Heels",
                    price: "10.50",
                    url: "https://www.shoestp.com/xiawei-2018-classic-snake-pu-womens-high-boots-with-rivet-and-zipper-inside-chunky-heels_p5567.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ab3134aceabfde31fa84b64edeb0375e.jpg",
                    name: "EUROPE PROFESSIONAL RIDING BOOTS PROTECTING FEET AND LEG SKID RESISTANCE BREATHING BOOTS",
                    price: "20.00",
                    url: "https://www.shoestp.com/europe-professional-riding-boots-protecting-feet-and-leg-skid-resistance-breathing-boots_p3536.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/494645b085b2f9ded65ed43b521be507.jpg",
                    name: "EUROPE PROFESSIONAL RIDING BOOTS PROTECTING FEET AND LEG SKID RESISTANCE BREATHING BOOTS",
                    price: "23.00",
                    url: "https://www.shoestp.com/europe-professional-riding-boots-protecting-feet-and-leg-skid-resistance-breathing-boots_p3537.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/195dd26445f59fc70cc76d1593009c31.jpg",
                    name: "EUROPE WINTER BOOTS PROFESSIONAL RIDING BOOTS PROTECTING FEET AND LEG SKID RESISTANCE WARM BREATHING BOOTS",
                    price: "20.00",
                    url: "https://www.shoestp.com/europe-winter-boots-professional-riding-boots-protecting-feet-and-leg-skid-resistance-warm-breathing-boots_p3513.html"
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
