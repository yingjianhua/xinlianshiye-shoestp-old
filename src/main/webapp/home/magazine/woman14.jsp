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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7aa03f7547bd7a238443f723c4b14424.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/aed555f6a1fd7236eb2a0f2a78496f0c.jpg",
                    name: "Juheng Good Quality Popular Lace Up Casual PU Shoes For Women And Girl",
                    price: "2.80",
                    url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pu-shoes-for-women-and-girl_p6577.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/817685fbe1753d43db69265c61602fcc.jpg",
                    name: "Juheng Good Quality Popular Lace Up Casual PU Shoes For Women",
                    price: "3.50",
                    url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pu-shoes-for-women_p6576.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8ea404d2501bb12a4728bed7279d1e83.jpg",
                    name: "Juheng Good Quality Popular Lace Up Casual PU Shoes For Girl",
                    price: "2.90",
                    url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pu-shoes-for-girl-and-women_p6575.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/971bce4123e1304127865acbb730e92.jpg",
                    name: "Juheng Comfortable Slip-on Flat Sole Light Shoes for Women",
                    price: "2.70",
                    url: "https://www.shoestp.com/juheng-comfortable-slip-on-flat-sole-light-shoes-for-men-and-women_p6568.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4c27b56ade8d43998cb192d072c6db47.jpg",
                    name: "Juheng Popular Casual pvc Women Sport Shoes and Running Shoes",
                    price: "2.50",
                    url: "https://www.shoestp.com/juheng-popular-casual-pvc-women-sport-shoes-and-running-shoes_p6560.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/189863265a42fa9fabb56f3d47db5550.jpg",
                    name: "Juheng Popular Casual pvc Women Sport Shoes and Running Shoes",
                    price: "2.40",
                    url: "https://www.shoestp.com/juheng-popular-casual-pvc-women-sport-shoes-and-running-shoes_p6556.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e3ed7a6880a497a29e90e8bf19e3e28a.jpg",
                    name: "Nianyue fashion mens casual sneakers walking sport shoes running shoes",
                    price: "9.00",
                    url: "https://www.shoestp.com/nianyue-fashion-mens-casual-sneakers-walking-sport-shoes-running-shoes_p6370.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e6dfbd9c2e7d5bc6ce64366fa75eeeef.jpg",
                    name: "Nianyue popular womens casual sneakers walking sport shoes mens running shoes with mesh upper",
                    price: "10.50",
                    url: "https://www.shoestp.com/nianyue-popular-womens-casual-sneakers-walking-sport-shoes-mens-running-shoes-with-mesh-upper_p6366.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/68eaf8967dfddfffb8e3eccb1e7ef85d.jpg",
                    name: "Juheng Comfortable Slip-on Flat Sole Light Women Light Shoe",
                    price: "1.70",
                    url: "https://www.shoestp.com/juheng-comfortable-slip-on-flat-sole-light-women-light-shoe_p6567.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f46e39fe7b2c778391cfe420597adebd.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9a431e2c23589c6f3fcabbb68486750f.jpg",
                    name: "Nianyue popular womens casual sneakers walking sport shoes mens running shoes with mesh upper",
                    price: "10.50",
                    url: "https://www.shoestp.com/nianyue-popular-womens-casual-sneakers-walking-sport-shoes-mens-running-shoes-with-mesh-upper_p6366.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/477e8bd8f4a6e3ad68a1e969170976c4.jpg",
                    name: "Nianyue High Quality Womens Casual Walking Sport Shoes Running Shoes",
                    price: "8.50",
                    url: "https://www.shoestp.com/nianyue-high-quality-womens-casual-walking-sport-shoes-running-shoes_p6361.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9912bfaabab0b0fe9d76d66b8514698e.jpg",
                    name: "Nianyue flyknit New casual shoes women high running shoes",
                    price: "8.50",
                    url: "https://www.shoestp.com/nianyue-flyknit-new-casual-shoes-women-high-running-shoes_p6360.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1004baaf0692ba88662f66862b72dd3.jpg",
                    name: "Nianyue popular womens casual sneakers womens walking sport shoes running shoes with mesh upper",
                    price: "7.50",
                    url: "https://www.shoestp.com/nianyue-popular-womens-casual-sneakers-womens-walking-sport-shoes-running-shoes-with-mesh-upper_p6359.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f4c9a49d565d3a26d97518958de817a2.jpg",
                    name: "Nianyue flyknit New breathable casual shoes female women shoes running shoes",
                    price: "8.00",
                    url: "https://www.shoestp.com/nianyue-flyknit-new-breathable-casual-shoes-female-women-shoes-running-shoes_p6354.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2e1a82cd084e744875c93412e14d9607.jpg",
                    name: "Nianyue red knitted cotton upper Sneaker Sport lace up comfortable casual shoes for women",
                    price: "7.80",
                    url: "https://www.shoestp.com/nianyue-red-knitted-cotton-upper-sneaker-sport-lace-up-comfortable-casual-shoes-for-women_p6352.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/312554923353fa869ce54057de237397.jpg",
                    name: "Nianyue White Sneaker Sport lace up comfortable casual running shoes for women with flyknit upper",
                    price: "8.00",
                    url: "https://www.shoestp.com/nianyue-white-sneaker-sport-lace-up-comfortable-casual-running-shoes-for-women-with-flyknit-upper_p6350.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7afed145d1fd898f84f2471450936f3c.jpg",
                    name: "Nianyue fashion women casual mens walking sport shoes with black PU/Mesh running shoes",
                    price: "7.50",
                    url: "https://www.shoestp.com/nianyue-fashion-women-casual-mens-walking-sport-shoes-with-black-pu-mesh-running-shoes_p6349.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/58188cff30e7f6f0839c1f9d83891ef5.jpg",
                    name: "Nianyue classical black fashion women casual sneakers mens walking sport shoes running shoes",
                    price: "8.50",
                    url: "https://www.shoestp.com/nianyue-classical-black-fashion-women-casual-sneakers-mens-walking-sport-shoes-running-shoes_p6355.html"
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
