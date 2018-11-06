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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/222819bf78544867cdf64e20b8bb563c.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/96fc382039bf6af102cb9a7e1630d150.jpg",
                    name: "Aokang official women's shoes 2018 summer leisure new high thick heels ankle strap comfortable women's sandals",
                    price: "22.70",
                    url: "https://www.shoestp.com/aokang-official-womens-shoes-2018-summer-leisure-new-high-thick-heels-ankle-strap-comfortable-womens-sandals_p6218.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ce1ae2cb399644bd29a3ef8b9542ed7b.jpg",
                    name: "Summer Girls Lace Up Chunky Heel Peep Toe Women Fancy Shoes Manufacturers Ladies Leather Sandals",
                    price: "13.00",
                    url: "https://www.shoestp.com/summer-girls-lace-up-chunky-heel-peep-toe-women-fancy-shoes-manufacturers-ladies-leather-sandals_p4377.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e7a70ecc5fc8dd96fa5795ceed15c7ed.jpg",
                    name: "Tracyee suede Upper Material Ladies Popular sandals China Wholesale sandals",
                    price: "30.99",
                    url: "https://www.shoestp.com/tracyee-suede-upper-material-ladies-popular-sandals-china-wholesale-sandals_p3418.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/24f9dc567411e6ec42bfa4dcff7e47bc.jpg",
                    name: "Tracyee OEM 2017 simple style Ankle strap Chunky Heel Open Toe Women High Heel strap sandal",
                    price: "26.99",
                    url: "https://www.shoestp.com/tracyee-oem-2017-simple-style-ankle-strap-chunky-heel-open-toe-women-high-heel-strap-sandal_p3395.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e3124bbabe25c1af148042e41a70bdba.jpg",
                    name: "Fashion Women Summer High Heel Gold Lace Up Diamonte Heels sandal 2017 for Women Girls",
                    price: "17.45",
                    url: "https://www.shoestp.com/fashion-women-summer-high-heel-gold-lace-up-diamonte-heels-sandal-2017-for-women-girls_p3111.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d94ff82ea4d7a947347e582c8fdc35a0.jpg",
                    name: "Chunky Rubber Heels Latest Fashion Girls Sandals Women , High Heel Sandals",
                    price: "13.54",
                    url: "https://www.shoestp.com/chunky-rubber-heels-latest-fashion-girls-sandals-women-high-heel-sandals_p3101.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/11c964575f857cd66515d8fa03c2735a.jpg",
                    name: "Women 2018 green Denim Strap Block High heel sandals new design sandals",
                    price: "14.69",
                    url: "https://www.shoestp.com/women-2018-green-denim-strap-block-high-heel-sandals-new-design-sandals_p3073.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f3d375e85fd180ba3ecadffe3dc15b33.jpg",
                    name: "dance shoes women",
                    price: "6.95",
                    url: "https://www.shoestp.com/dance-shoes-women_p1155.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8384aaaab08c931780367f85d2eb9923.jpg",
                    name: "2018 fashion summer sandal shoe for woman China shoes manufacture",
                    price: "16.80`",
                    url: "https://www.shoestp.com/2018-fashion-summer-sandal-shoe-for-woman-china-shoes-manufacture_p4361.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cc391230b1492c808956eb364483214e.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/66093131af0346dc16faf4bdea134a68.jpg",
                    name: "Tracyee Latest Diamond Decoration Daily Dress Pumps Low Heel sandals High Quality Wrapped Heel shoes",
                    price: "28.99",
                    url: "https://www.shoestp.com/tracyee-latest-diamond-decoration-daily-dress-pumps-low-heel-sandals-high-quality-wrapped-heel-shoes_p3426.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/86e4af6ad2b0bd6f835ba769fe0e05a3.jpg",
                    name: "2016 new design custom oversea sexy night black ladies shoes latest sexy summer high heel sandals lace sandals",
                    price: "12.00",
                    url: "https://www.shoestp.com/2016-new-design-custom-oversea-sexy-night-black-ladies-shoes-latest-sexy-summer-high-heel-sandals-lace-sandals_p1149.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d7150fc05eeec0331c59dc2d07c2fc30.jpg",
                    name: "Women's T-shaped Shoes Fashionable Buckle Western Style Simple Chunky Heel Single Shoes",
                    price: "8.80",
                    url: "https://www.shoestp.com/womens-t-shaped-shoes-fashionable-buckle-western-style-simple-chunky-heel-single-shoes_p0693.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/176f29afa32d0f850c7522974208e20b.jpg",
                    name: "Women's Bowtie Sequins Shoes Spring and Autumn Slip-on Western Simple High Heels",
                    price: "9.00",
                    url: "https://www.shoestp.com/womens-bowtie-sequins-shoes-spring-and-autumn-slip-on-western-simple-high-heels_p0650.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e8af36d91e1d9cc32352c49d3325802d.jpg",
                    name: "Newest popular comfortable elegant good service top quality china wholesale cheap price high heel sandals",
                    price: "13.00",
                    url: "https://www.shoestp.com/newest-popular-comfortable-elegant-good-service-top-quality-china-wholesale-cheap-price-high-heel-sandals_p4395.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/75028f53811587bf4cb95d8767892604.jpg",
                    name: "Tracyee Strap Heels Sandal With Metal Buckle Platform Chunky Wedge Heel Gladiator Sandals",
                    price: "30.99",
                    url: "https://www.shoestp.com/tracyee-strap-heels-sandal-with-metal-buckle-platform-chunky-wedge-heel-gladiator-sandals_p3405.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4a02382ccb73bd53bc4d917d3a292d37.jpg",
                    name: "Sexy elegant women shoes peep toe new design patent leather upper average wholesale price shoes",
                    price: "24.50",
                    url: "https://www.shoestp.com/sexy-elegant-women-shoes-peep-toe-new-design-patent-leather-upper-average-wholesale-price-shoes_p4358.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c99638c03ddd0c326c6ac076cb77f6d3.jpg",
                    name: "Wholesale OEM Custom Latest Fashion Designs Summer Name Brand Ladies Fancy High Heel Leather Women Sandal",
                    price: "13.00",
                    url: "https://www.shoestp.com/wholesale-oem-custom-latest-fashion-designs-summer-name-brand-ladies-fancy-high-heel-leather-women-sandal_p4373.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f160503d68534a083dc031c0b7f0cfa5.jpg",
                    name: "Women's Fashionable Sandals Summer New Crystal Strap Buckles Western Casual Chunky Heels",
                    price: "8.20",
                    url: "https://www.shoestp.com/womens-fashionable-sandals-summer-new-crystal-strap-buckles-western-casual-chunky-heels_p0606.html"
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
