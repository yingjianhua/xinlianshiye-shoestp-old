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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f95e5c124ba2c3be0588a7dc2de13f4d.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2b73a6b18e4736d2db797ac0ba381d3f.jpg",
                    name: "Boy's Color-matching High-cut Shoes Trendy Casual Winter Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boys-color-matching-high-cut-shoes-trendy-casual-winter-flat-sneakers_p0689.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/48f1e958b04ad1899126b52dfb96c09f.jpg",
                    name: "Boy and Girl's Canvas Shoes Fashionable High-cut Elastic Spring and Autumn Young Casual Embroidery Sneakers",
                    price: "16.00",
                    url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-high-cut-elastic-spring-and-autumn-young-casual-embroidery-sneakers_p0556.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/92e7130b1367f068f180726b3aec8ee4.jpg",
                    name: "Boy's High-cut Shoes Velcro Trendy Casual Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boys-high-cut-shoes-velcro-trendy-casual-flat-sneakers_p0688.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b54f49c36b1fdb9f0864ca9a261ae635.jpg",
                    name: "Boy and Girl's Canvas Shoes Fashionable Camouflage Slip-ons Spring and Autumn Young Casual Sports Sneakers",
                    price: "15.00",
                    url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-camouflage-slip-ons-spring-and-autumn-young-casual-sports-sneakers_p0559.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d9b103562b59995c677bea4131f316a2.jpg",
                    name: "Boy and Girl's Canvas Shoes Fashionable Slip-ons Spring and Autumn Casual Cloth Sports Shoes",
                    price: "15.00",
                    url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-slip-ons-spring-and-autumn-casual-cloth-sports-shoes_p0552.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f4ed85b743c6bd50354db5ebbeecaa79.jpg",
                    name: "Boy's Color-matching Shoes Velcro Casual Trendy Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boys-color-matching-shoes-velcro-casual-trendy-flat-sneakers_p0687.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a0314a821966d6e8fa82f98dd8b944c1.jpg",
                    name: "Boy and Girl's Fashionable Shoes Color-matching Trendy Casual Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boy-and-girls-fashionable-shoes-color-matching-trendy-casual-flat-sneakers_p0672.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e552bf3e6c9ad50e0b6b854a0720b5c6.jpg",
                    name: "Boy and Girl's High-cut Shoes Metal Zipper Trim Velcro Western Style Casual Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boy-and-girls-high-cut-shoes-metal-zipper-trim-velcro-western-style-casual-flat-sneakers_p0686.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e795f8d869ae469f9482d39ea6562ce7.jpg",
                    name: "Boy and Girl's Fashionable Sneakers Colour-matching Velcro Spring and Autumn Young Casual Sports Shoes",
                    price: "16.00",
                    url: "https://www.shoestp.com/boy-and-girls-fashionable-sneakers-colour-matching-velcro-spring-and-autumn-young-casual-sports-shoes_p0558.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a2b5c1016b5cfc4df4dc3b794ffdc954.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f08652256252bc34facc55d6fb228e7c.jpg",
                    name: "Girl's Princess Shoes Fashionable Pearl Flowers Velcro Trendy Casual Baby Shoes",
                    price: "12.00",
                    url: "https://www.shoestp.com/girls-princess-shoes-fashionable-pearl-flowers-velcro-trendy-casual-baby-shoes_p0669.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6c379dcc7049b5177bd5d492e42b5b1d.jpg",
                    name: "Boy and Girl's High-cut Shoes Metal Zipper Trim Velcro Western Style Casual Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boy-and-girls-high-cut-shoes-metal-zipper-trim-velcro-western-style-casual-flat-sneakers_p0686.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2e823058769e69a83eacbed5986bc10d.jpg",
                    name: "Girl's Flat Sneakers Fashionable Velcro Elastic Trendy Casual Sports Children Shoes",
                    price: "13.00",
                    url: "https://www.shoestp.com/girls-flat-sneakers-fashionable-velcro-elastic-trendy-casual-sports-children-shoes_p0679.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7d38870d2fb765da33f6f9946dbcaf8a.jpg",
                    name: "Boy and Girl's Fashionable White Shoes Color-matching Velcro Trendy Casual Sports Flat Shoes",
                    price: "13.00",
                    url: "https://www.shoestp.com/boy-and-girls-fashionable-white-shoes-color-matching-velcro-trendy-casual-sports-flat-shoes_p0675.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/11423fa911a384fcffd41dd76b5051ee.jpg",
                    name: "Boy and Girl's Fashionable Shoes Color-matching Trendy Casual Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boy-and-girls-fashionable-shoes-color-matching-trendy-casual-flat-sneakers_p0672.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/44d674ab07f118f4a67039ada3ff4f5e.jpg",
                    name: "Girl's Fashionable Shoes Bowtie Wrinkled Edge Velcro Trendy Casual Leather Shoes",
                    price: "14.00",
                    url: "https://www.shoestp.com/girls-fashionable-shoes-bowtie-wrinkled-edge-velcro-trendy-casual-leather-shoes_p0667.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/168f7b03847b369510613ef90296478d.jpg",
                    name: "Girl's Princess Shoes Fashionable Flowers Velcro Trendy Casual Children Shoes",
                    price: "12.00",
                    url: "https://www.shoestp.com/girls-princess-shoes-fashionable-flowers-velcro-trendy-casual-children-shoes_p0668.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/62fbc8462b538da4c4a33dd4b62c438c.jpg",
                    name: "Boy's High-cut Shoes Fashionable Velcro Casual Winter New Flat Baby Shoes",
                    price: "13.00",
                    url: "https://www.shoestp.com/boys-high-cut-shoes-fashionable-velcro-casual-winter-new-flat-baby-shoes_p0691.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9fbe998f7feb20e153c374f7987e7a06.jpg",
                    name: "Girl's Shell Toe Sneakers Fashionable Velcro Trendy Casual Sports Flat Children Shoes",
                    price: "13.00",
                    url: "https://www.shoestp.com/girls-shell-toe-sneakers-fashionable-velcro-trendy-casual-sports-flat-children-shoes_p0678.html"
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
