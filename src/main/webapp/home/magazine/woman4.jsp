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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a579f2a561b5c9119cc21cbf90046af2.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f31b9d5433ceb2e23a3c9624b69e61bb.jpg",
                    name: "Europe big size lady high slim heel sandals Roman women sandals",
                    price: "20.00",
                    url: "https://www.shoestp.com/europe-big-size-lady-high-slim-heel-sandals-roman-women-sandals_p1048.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5d98c5a035de8b5f0610198fa230d81.jpg",
                    name: "Ladies Party Wear Black Suede Diamonte Stiletto Heels Latest Sandal Shoes Women 2017",
                    price: "18.18",
                    url: "https://www.shoestp.com/ladies-party-wear-black-suede-diamonte-stiletto-heels-latest-sandal-shoes-women-2017_p3077.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/32806d5a2d9bc0fe351b388ddfa01f5a.jpg",
                    name: "Women's Fashionable Crystal Shoes Peep Toe Back Zipper Summer New Style Western Simple High-heeled Sandals",
                    price: "10.00",
                    url: "https://www.shoestp.com/womens-fashionable-crystal-shoes-peep-toe-back-zipper-summer-new-style-western-simple-high-heeled-sandals_p0637.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d58847cd6d8624361ef17e7c16c378d4.jpg",
                    name: "lady high heel bridal fancy sandal Rose Gold Gem Clear Heels",
                    price: "18.00",
                    url: "https://www.shoestp.com/lady-high-heel-bridal-fancy-sandal-rose-gold-gem-clear-heels_p3096.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/96f07580b24d3910f9c9d85f6de5ee36.jpg",
                    name: "Women's Peep Toe Sandals Fashionable Summer Zipper Thick Sole Casual Campagus",
                    price: "20.00",
                    url: "https://www.shoestp.com/womens-peep-toe-sandals-fashionable-summer-zipper-thick-sole-casual-campagus_p0420.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f8a0882f5f4f294409595f54f1eba1ea.jpg",
                    name: "Fashion Women Summer High Heel Gold Lace Up Diamonte Heels sandal 2017 for Women Girls",
                    price: "17.45",
                    url: "https://www.shoestp.com/fashion-women-summer-high-heel-gold-lace-up-diamonte-heels-sandal-2017-for-women-girls_p3111.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2826752b221efb294755f72bd946410.jpg",
                    name: "Women's Leather Patchwork Shoes Summer New Buckles Western Simple Pointed High-heeled Sandals",
                    price: "9.00",
                    url: "https://www.shoestp.com/womens-leather-patchwork-shoes-summer-new-buckles-western-simple-pointed-high-heeled-sandals_p0654.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5408765d35547e37a41f404cbee0f3ef.jpg",
                    name: "Fancy girls women Black Snake Lace Up high heels shoes sandals",
                    price: "16.90",
                    url: "https://www.shoestp.com/fancy-girls-women-black-snake-lace-up-high-heels-shoes-sandals_p3045.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f0ff42f3121d721c913b141624d25df2.jpg",
                    name: "ladies heels sandals women suede sexy bands sandals",
                    price: "8.50",
                    url: "https://www.shoestp.com/ladies-heels-sandals-women-suede-sexy-bands-sandals_p1187.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/25cc4e3c04d2497aaacbc3d719a059e4.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1b7ff133e2da5af964d54bbb0f3bab02.jpg",
                    name: "New Fashion Sex Wine Red Ladies Chunky Heels Dress Shoes Women Pvc High Heels Shoes",
                    price: "17.00",
                    url: "https://www.shoestp.com/ljj034_p2267.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/557d6d6e698380160a561a442796a590.jpg",
                    name: "New design faux suede pumps lady chunky heel shoes with back zipper",
                    price: "19.90",
                    url: "https://www.shoestp.com/ljj037_p2270.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dd6b82f7b17d1bf4f0f9f42645c96df6.jpg",
                    name: "2018 winter wine red women ladies leather boots shoes",
                    price: "37.00",
                    url: "https://www.shoestp.com/ljj028_p2259.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ea750637fb33ba82539aa35b817198fd.jpg",
                    name: "Latest Design China ChengDu factory wholesale Wine-Red women Super high-heel pumps shoes",
                    price: "29.00",
                    url: "https://www.shoestp.com/ljj035_p2268.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c9cb42e18dca23910c420130d372c10d.jpg",
                    name: "genuine leather italian shoes black wine red leather women shoes",
                    price: "17.05",
                    url: "https://www.shoestp.com/ljj029_p2260.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d87d0b88c4bbb54e6a13b3c0d05ec03a.jpg",
                    name: "Fashion Latest Design Cow Skin Women High Heel Leather Shoe",
                    price: "36.00",
                    url: "https://www.shoestp.com/ljj034_p2274.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5bd1a42aa54147bc2bb40063657aab6e.jpg",
                    name: "Best Price Quality Fashion Girls Loafers Slip-on Round-toe Wine Red Velvet Women Casual Shoes",
                    price: "3.00",
                    url: "https://www.shoestp.com/ljj033_p2266.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7365b997b420ba42f4bfb3ec084c562d.jpg",
                    name: "ladies high heel women ankle boots shoes black leather booties",
                    price: "29.70",
                    url: "https://www.shoestp.com/ljj038_p2272.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5a4801831b000b7c065cd58dd7a0c4.jpg",
                    name: "2018 Fashion New Design Platform Women High Heel Shoes",
                    price: "9.10",
                    url: "https://www.shoestp.com/ljj036_p2269.html"
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
