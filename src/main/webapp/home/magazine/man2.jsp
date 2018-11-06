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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ebfcbadd7a9778938ca642fda1471c6a.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8e2cb8eef01721997a713d4ab37cd767.jpg",
                    name: "New Design Comfortable Waterproof Original Cowhide Leather Loafer Shoes clack style",
                    price: "11.50",
                    url: "https://www.shoestp.com/new-design-comfortable-waterproof-original-cowhide-leather-loafer-shoes-clack-style_p2716.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c2d77025d69881be45131eb53b4fea94.jpg",
                    name: "New Design Comfortable Waterproof Soft Fashion Mens Driving Shoes clack style",
                    price: "11.30",
                    url: "https://www.shoestp.com/new-design-comfortable-waterproof-soft-fashion-mens-driving-shoes-clack-style_p2714.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5075af1efedc7daa9d7cb268665a4212.jpg",
                    name: "Customized shoes man leather shoe handmade casual men shoes 2018",
                    price: "50.00",
                    url: "https://www.shoestp.com/customized-shoes-man-leather-shoe-handmade-casual-men-shoes-2018_p4084.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1d1430281f62a6da5f93d457d45a6c2.jpg",
                    name: "Fance fashion crocodile grain casual shoes cow leather rubber outsole soft driving flat footwear clack style",
                    price: "16.50",
                    url: "https://www.shoestp.com/fance-fashion-crocodile-grain-casual-shoes-cow-leather-rubber-outsole-soft-driving-flat-footwear-clack-style_p2695.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6b8cf1bded4f5277f86c61c5b57e260b.jpg",
                    name: "Men's Driving Loafer Breathable Perforation Vent Slip-on Driving Shoes Clack Style",
                    price: "16.00",
                    url: "https://www.shoestp.com/mens-driving-loafer-breathable-perforation-vent-slip-on-driving-shoes-clack-style_p2641.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6ef9f0a3018d52d82160c2fd21d66df0.jpg",
                    name: "2018 Summer Italian Soft Cow Leather moccasin Shoes for Driving Men Clack Style",
                    price: "13.90",
                    url: "https://www.shoestp.com/2018-summer-italian-soft-cow-leather-moccasin-shoes-for-driving-men-clack-style_p2616.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2cece90e82aa2ec0e96adf05bac6b450.jpg",
                    name: "Men's Comfort Driving Car Soft Flats Loafers Casual Boat Shoes Factory clack style",
                    price: "16.90",
                    url: "https://www.shoestp.com/mens-comfort-driving-car-soft-flats-loafers-casual-boat-shoes-factory-clack-style_p2717.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c07600d0d424eb6b504e04f949d61f28.jpg",
                    name: "Kangrui New Arrivals Fashion Embroidery Men's Shoes Casual Flat Leather Shoes",
                    price: "14.10",
                    url: "https://www.shoestp.com/new-arrivals-fashion-embroidery-mens-shoes-casual-flat-shoes_p2205.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cf77d5ad2c9c6b88f9747771b08cd255.jpg",
                    name: "Men's Driving Loafer Slip-on Driving Shoes for Men clack style",
                    price: "16.00",
                    url: "https://www.shoestp.com/mens-driving-loafer-slip-on-driving-shoes-for-men-clack-style_p2706.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/847348d43b80f44f77257ab44f00f89e.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ee1ab5dac20c773182609a526f1b9091.jpg",
                    name: "High quality fashion bandage men business shoes",
                    price: "27.00",
                    url: "https://www.shoestp.com/high-quality-fashion-bandage-men-business-shoes_p2822.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5b7434111b070e9b50ddd794c7872476.jpg",
                    name: "Factory wholesale cheap price comfortable durable high state leather shoes",
                    price: "25.00",
                    url: "https://www.shoestp.com/factory-wholesale-cheap-price-comfortable-durable-high-state-leather-shoes_p2758.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7d85381989fa5532ba71864adb93c43.jpg",
                    name: "Fashion modern style men designer dress flat genuine leather shoes",
                    price: "27.00",
                    url: "https://www.shoestp.com/fashion-modern-style-men-designer-dress-flat-genuine-leather-shoes_p2782.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/afec504ded904be1a8c56ff4569fc188.jpg",
                    name: "Terse custom handmade oxfords men leather dress shoes for work",
                    price: "27.00",
                    url: "https://www.shoestp.com/terse-custom-handmade-oxfords-men-leather-dress-shoes-for-work_p2751.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bf164920442cae98b439e2b1773176da.jpg",
                    name: "Genuine leather men dress shoes made in china",
                    price: "25.00",
                    url: "https://www.shoestp.com/genuine-leather-men-dress-shoes-made-in-china_p2807.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f36d975e8e3034b467f7152674fdd50b.jpg",
                    name: "Hot sale fashion trendy pointed toe blue men lace-up leather shoes",
                    price: "25.00",
                    url: "https://www.shoestp.com/hot-sale-fashion-trendy-pointed-toe-blue-men-lace-up-leather-shoes_p2759.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6ee152360d9ad7d636b93cdc9456dd0f.jpg",
                    name: "Standard design classical casual men high-top dress shoes for office",
                    price: "27.00",
                    url: "https://www.shoestp.com/standard-design-classical-casual-men-high-top-dress-shoes-for-office_p2780.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b9c0010f499462721e5c5ed1ba72ac3b.jpg",
                    name: "High quality fashion high help men business shoes",
                    price: "27.00",
                    url: "https://www.shoestp.com/high-quality-fashion-high-help-men-business-shoes_p2821.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/adb6e2e69da403eeee71d5009f04c789.jpg",
                    name: "New style custom dress shoe genuine leather shoes manufacturers",
                    price: "27.00",
                    url: "https://www.shoestp.com/new-style-custom-dress-shoe-genuine-leather-shoes-manufacturers_p2778.html"
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
