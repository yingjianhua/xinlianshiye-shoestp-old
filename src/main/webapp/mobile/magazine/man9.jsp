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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6375aebed23739206d2a7aaa7b4ab23d.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f5977d902fc8c9e01b25ff5e12db38e9.jpg",
                    name: "Luxin New Comfortable Imitate Leather Laced Up Shoe For Men",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-new-comfortable-imitate-leather-laced-up-shoe-for-men_p5734.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/57e709bb7d84aaa51e22550db9a10b44.jpg",
                    name: "Luxin 2018 Hot Sell Suede Leather Wear Resistant Lace-Up Outdoor Wear Shoes For Men's Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-2018-hot-sell-suede-leather-wear-resistant-lace-up-outdoor-wear-shoes-for-mens-shoes_p5733.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/217e8501293dd5ce663a9860482bebe9.jpg",
                    name: "Luxin High Top Casual British Style Martin Boots Fashionable Autumn And Winter Mens' Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-high-top-casual-british-style-martin-boots-fashionable-autumn-and-winter-mens-boots_p5732.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dc73c1a295b217b514940a7b9291118a.jpg",
                    name: "Luxin New Style Hot Sales Italian Men Ankle Boots Winter Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-new-style-hot-sales-italian-men-ankle-boots-winter-shoes_p5731.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/881478d73cc8e15df21ea44546984428.jpg",
                    name: "Luxin 2018 New High-Top British Style Martin Boots With Lace-Up",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-2018-new-high-top-british-style-martin-boots-with-lace-up_p5729.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/85f85f43815b65f2acd54e98d70b062e.jpg",
                    name: "Luxin Retro British Chelsea Men's Boots With Zipper High Top Men's Shoes Leather Men's Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-retro-british-chelsea-mens-boots-with-zipper-high-top-mens-shoes-leather-mens-boots_p5728.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ab8756da61f612d2d398a15eb436ea7e.jpg",
                    name: "Luxinl Leisure Leather Martin Boots Men's Workwear Short Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxinl-leisure-leather-martin-boots-mens-workwear-short-boots_p5727.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6d1dc19291bf2e583f003dd760b06375.jpg",
                    name: "Luxin Suede Leather Beige Color TPR Sole Chelsea Men Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-suede-leather-beige-color-tpr-sole-chelsea-men-boots_p5726.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/af7855dcde771325d2fadad724a7f5ad.jpg",
                    name: "Luxin Outdoor High-Top Boots With Fur To Keep Men Warm Warm Big Size Men's Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-outdoor-high-top-boots-with-fur-to-keep-men-warm-warm-big-size-mens-shoes_p5730.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/580a8d84c7f14220e28489e831d17d4f.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d725163471b57c13e7c8fbfa14bca908.jpg",
                    name: "Luxin Durable Fashion Rivet Martin Boots Ankle Leather Men Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-durable-fashion-rivet-martin-boots-ankle-leather-men-boots_p5725.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3386f5b44ff830b01e110ab6239718a3.jpg",
                    name: "Luxin New Arrival Outdoor Work Boots Fashion Winter Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-new-arrival-outdoor-work-boots-fashion-winter-boots_p5724.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7917119613b77b9885834f155e6d7fa6.jpg",
                    name: "Luxin 2018 New Style Fashion Breathable Martin Boots For Male",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-2018-new-style-fashion-breathable-martin-boots-for-male_p5723.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/543d7664cf418ee71abe71b09c825d99.jpg",
                    name: "Luxin 2018 New Arrivals Mens PU Leather Hiking Winter Boots For Men",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-2018-new-arrivals-mens-pu-leather-hiking-winter-boots-for-men_p5722.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/61ec2a391ddc8e8dc9169deba4adf060.jpg",
                    name: "Luxin Leisure Boots Thicken The Fashionable Boots Leather Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-leisure-boots-thicken-the-fashionable-boots-leather-shoes_p5720.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7558205b246618f91e22e683531eb3b1.jpg",
                    name: "Luxin 2018 The Best Price Casual Antiskid TPR Boots For Men",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-2018-the-best-price-casual-antiskid-tpr-boots-for-men_p5719.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1d6fb420960673d82d681f7a6c4db734.jpg",
                    name: "2018 Luxin Hot Sale Black Martin Genuine Leather Boots For Men",
                    price: "10.00",
                    url: "https://www.shoestp.com/2018-luxin-hot-sale-black-martin-genuine-leather-boots-for-men_p5717.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/712c89ea8ed717da4331a826d55685d2.jpg",
                    name: "Luxin Fashion Outdoor Black Color PU Boots Winter Shoes For Men",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-fashion-outdoor-black-color-pu-boots-winter-shoes-for-men_p4759.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b6f9085e08a0e62d306bfe78fbbefdd9.jpg",
                    name: "Luxin Outdoor Personal Equipment Climbing And Camping Safety Hikers Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/luxin-outdoor-personal-equipment-climbing-and-camping-safety-hikers-shoes_p5721.html"
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
