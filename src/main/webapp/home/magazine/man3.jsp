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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b6f126dd4eb075c0744a4c215214b472.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/43d20fd8df88001a807222640d1716ac.jpg",
                    name: "Zapatillas men running mesh casual shoes",
                    price: "12.50",
                    url: "https://www.shoestp.com/zapatillas-men-running_p4017.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e4b6a3d42577cc6c903a099c1456f18.jpg",
                    name: "Fashion phylon cross casual sport trainer cheap men shoes",
                    price: "13.60",
                    url: "https://www.shoestp.com/fashion-phylon-cross-sport-trainer-cheap_p4009.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/828b9c645ea5558a3b3dcda5d155d2ad.jpg",
                    name: "Los hombres zapatos de deporte mesh casual men shoes",
                    price: "13.60",
                    url: "https://www.shoestp.com/los-hombres-zapatos-de-deporte_p4015.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b6297ca0e9c7be947def0c2d7cbdda69.jpg",
                    name: "China mesh causal sport shoes maker",
                    price: "15.50",
                    url: "https://www.shoestp.com/china-causal-sport-shoes-maker_p4008.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/391607afdff6947025636c0b09608e1b.jpg",
                    name: "New arrival fashon perfect fitness step casual men sports shoes",
                    price: "13.20",
                    url: "https://www.shoestp.com/new-arrival-fashon-perfect-fitness-step-shoes_p4011.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b12e9d055a9ead3a6aa67d856a28a2db.jpg",
                    name: "New model pu/mesh comfortable air sports shoes for men",
                    price: "13.60",
                    url: "https://www.shoestp.com/new-model-comfortable-air-sports-shoes-for-men_p3938.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/27be3b8e472bd9432ac67ce8912dbdb8.jpg",
                    name: "China supplier custom logo multiple sizes sport men shoes and sneakers",
                    price: "12.60",
                    url: "https://www.shoestp.com/china-supplier-custom-logo-multiple-sizes-sport-shoes-and-sneakers_p3993.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/658461f746a4fb7a4a08d2e003e3e3f5.jpg",
                    name: "Cheap air sneakers shoes athletic sport shoes china for wholesale",
                    price: "12.60",
                    url: "https://www.shoestp.com/cheap-air-sneakers-shoes-athletic-sport-shoes-china-for-wholesale_p3984.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/94fd44a6a3eaaf0a8f514e6ebce665e6.jpg",
                    name: "Lmportadora de zapatos chinos Pu casual men shoes",
                    price: "15.20",
                    url: "https://www.shoestp.com/lmportadora-de-zapatos-chinos_p4016.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a1775d74c3968ad36c07d660c50013a8.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6a681248c232892d64ef157ac63be961.jpg",
                    name: "2018 New Hot selling classy Oxford genuine leather formal dress shoes for men with cheap price",
                    price: "17.50",
                    url: "https://www.shoestp.com/2018-new-hot-selling-classy-oxford-genuine-leather-formal-dress-shoes-for-men-with-cheap-price_p3624.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d20966ae9c87944d36de6d602b2c6f0d.jpg",
                    name: "2018 Wholesale european trendy white and black USA genuine leather luxury men dress shoes",
                    price: "50.8",
                    url: "https://www.shoestp.com/2018-wholesale-european-trendy-white-and-black-usa-genuine-leather-luxury-men-dress-shoes_p3637.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f3b83c610829e8e1876691da0fded7de.jpg",
                    name: "2018 New classy black leather light up men dress shoes",
                    price: "17.50",
                    url: "https://www.shoestp.com/2018-new-classy-black-leather-light-up-men-dress-shoes_p3620.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1b15031d682647650d58a0bc8b0192a4.jpg",
                    name: "2018 Stitching upper stylish cow Leather durable oxford shoes men UK Market",
                    price: "17.50",
                    url: "https://www.shoestp.com/2018-stitching-upper-stylish-cow-leather-durable-oxford-shoes-men-uk-market_p3622.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c556ac8517f9a78ec335c96025811f61.jpg",
                    name: "New arrival Goodyear welt wholesale man made luxury laces men shiny leather wholesale loafer design fashion shoes",
                    price: "50.8",
                    url: "https://www.shoestp.com/new-arrival-goodyear-welt-wholesale-man-made-luxury-laces-men-shiny-leather-wholesale-loafer-design-fashion-shoes_p3635.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fd297100dd157abd21bfde8b88f6b316.jpg",
                    name: "New Arrival Genuine Leather Upper Comfortable Insole 7cm Up Elevator Dress Shoes Men",
                    price: "23.00",
                    url: "https://www.shoestp.com/new-arrival-genuine-leather-upper-comfortable-insole-7cm-up-elevator-dress-shoes-men_p3619.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/efcf4399c10a6334bdaee2592399a0cc.jpg",
                    name: "2018 Brown Italian leather comfort formal boxing istanbul shoes men Wholesale",
                    price: "17.50",
                    url: "https://www.shoestp.com/2018-brown-italian-leather-comfort-formal-boxing-istanbul-shoes-men-wholesale_p3631.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2406303126d92e0678d73c6ac6bcd751.jpg",
                    name: "Dress Shoes Collection Goodyear welt Lace-up army green mens leather sole oxford shoes",
                    price: "50.8",
                    url: "https://www.shoestp.com/dress-shoes-collection-goodyear-welt-lace-up-army-green-mens-leather-sole-oxford-shoes_p3636.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6f53b466dda90a5c5d086986ce541a01.jpg",
                    name: "luxurious Brand Designer Classic Blue Goodyear welt sole Lace-up handmade impored leather Oxford Dress Shoes Men",
                    price: "50.8",
                    url: "https://www.shoestp.com/luxurious-brand-designer-classic-blue-goodyear-welt-sole-lace-up-handmade-impored-leather-oxford-dress-shoes-men_p3638.html"
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
