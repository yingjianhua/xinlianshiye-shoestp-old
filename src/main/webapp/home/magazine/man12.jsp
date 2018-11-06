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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fcc209db450926191c14f17f2a94b96b.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4e6feecfddfc04856d917a0956ac5a4f.jpg",
                    name: "SE Popular Men Shoes With Lace Up White Outsole PU Casual Shoes",
                    price: "7.00",
                    url: "https://www.shoestp.com/se-popular-men-shoes-with-lace-up-white-outsole-pu-casual-shoes_p6093.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f8c152cb6dcec26ca6d3d1207f9baba0.jpg",
                    name: "SE 2018Popular Men Shoes With Lace Up PU Casual Shoes",
                    price: "7.00",
                    url: "https://www.shoestp.com/se-2018popular-men-shoes-with-lace-up-pu-casual-shoes_p6092.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b0afd2a767330f6872a013375cd0dfe4.jpg",
                    name: "SE New Style Men's Casual PU Shoes Lace up Shoes",
                    price: "7.00",
                    url: "https://www.shoestp.com/se-new-style-mens-casual-pu-shoes-lace-up-shoes_p6088.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/61d9708d78895a587d68b7402bdbec5a.jpg",
                    name: "SE Popular Men Shoes Casual Men Shoes with Lace Up",
                    price: "7.50",
                    url: "https://www.shoestp.com/se-popular-men-shoes-casual-men-shoes-with-lace-up_p6086.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ecfdd773883e79c47b7bcf5082bfc43a.jpg",
                    name: "SE New Style Men's Casual PU Shoes White Outsole Lace up Shoes",
                    price: "7.50",
                    url: "https://www.shoestp.com/se-new-style-mens-casual-pu-shoes-white-outsole-lace-up-shoes_p6084.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c406451790296946fb113502e2cfd40.jpg",
                    name: "SE New Style Burgundy Men's Casual PU Shoes Lace up Shoes",
                    price: "7.50",
                    url: "https://www.shoestp.com/se-new-style-burgundy-mens-casual-pu-shoes-lace-up-shoes_p6082.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a4c8dcd73f6b84890437aa37fabc973b.jpg",
                    name: "SE New Style Men's Casual PU Shoes Lace up Shoes",
                    price: "7.30",
                    url: "https://www.shoestp.com/se-new-style-mens-casual-pu-shoes-lace-up-shoes_p6080.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3b464849300e847a77ce09f92e1be1e8.jpg",
                    name: "SE Men's Casual PU Shoes Lace up Shoes",
                    price: "7.30",
                    url: "https://www.shoestp.com/se-mens-casual-pu-shoes-lace-up-shoes_p6079.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8ad9c11ab35db34454d0631596c76d37.jpg",
                    name: "SE New Arrive Men Casual PU Shoes Lace up Shoes",
                    price: "7.30",
                    url: "https://www.shoestp.com/se-new-arrive-men-casual-pu-shoes-lace-up-shoes_p6076.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/afc741ac3a1e7e663d9dcde1278bfbd9.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/78fdb46c50a42ae41a15f3d53fe7f00b.jpg",
                    name: "SE New Arrive Black Men Casual PU Shoes Lace up Business Shoes",
                    price: "7.30",
                    url: "https://www.shoestp.com/se-new-arrive-black-men-casual-pu-shoes-lace-up-business-shoes_p6075.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/427bea8b097fee5a51c647e882fe446d.jpg",
                    name: "Aokang official men's leather shoes new genuine leather business casual shoes with low upper leather dress shoes",
                    price: "19.90",
                    url: "https://www.shoestp.com/aokang-official-mens-leather-shoes-new-genuine-leather-business-casual-shoes-with-low-upper-leather-dress-shoes_p6171.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/be546cfcf77f0a2b40abaede19acc58c.jpg",
                    name: "SE New Arrive Men's Casual PU Shoes Lace up Shoes",
                    price: "7.30",
                    url: "https://www.shoestp.com/se-new-arrive-mens-casual-pu-shoes-lace-up-shoes_p6077.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ca27f1b545d036ce45576afa2081ba41.jpg",
                    name: "SE New Arrive Black Men Casual PU Shoes",
                    price: "7.20",
                    url: "https://www.shoestp.com/se-new-arrive-black-men-casual-pu-shoes_p6072.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6e240521f780569c009f8e871ede8555.jpg",
                    name: "Kangfu leather men shoes soft casual daily walking shoes",
                    price: "21.88",
                    url: "https://www.shoestp.com/kangfu-leather-men-shoes-soft-casual-daily-walking-shoes_p6323.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4eb9dc05bd9f691095e651b5b1bc39bb.jpg",
                    name: "Kangfu Lace-Up Hollow Out Casual Pu Men Shoes",
                    price: "12.10",
                    url: "https://www.shoestp.com/kangfu-leather-men-shoes-soft-casual-daily-walking-shoes_p6323.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5333a2b8d25608af94adb56a33733d02.jpg",
                    name: "Kangfu Men Casual Soft Leather Sole Mens Dress Shoes Loafer Shoes",
                    price: "12.70",
                    url: "https://www.shoestp.com/kangfu-men-casual-soft-leather-sole-mens-dress-shoes-loafer-shoes_p6285.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dbd910fc6a6d710b4b69dbcaf92ba701.jpg",
                    name: "Kangfu Fashion Wholesale Hot Sale Slip On Flat Soft Men Casual Shoes",
                    price: "11.20",
                    url: "https://www.shoestp.com/kangfu-fashion-wholesale-hot-sale-slip-on-flat-soft-men-casual-shoes_p6344.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/39f770442d62689c5d1e8c0188cf6759.jpg",
                    name: "Fashion PU Men Casual Shoes,Hot sale new men's casual shoes",
                    price: "6.80",
                    url: "https://www.shoestp.com/fashion-pu-men-casual-shoeshot-sale-new-mens-casual-shoes_p0743.html"
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
