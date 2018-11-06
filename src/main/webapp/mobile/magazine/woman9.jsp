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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a320e73ea8c49991b9955e7eadf81580.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f65822ce585bfc37f4d9f1c99f90dea6.jpg",
                    name: "Qililai Wedge Heels Women Shoes Leather With Bowknot",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-wedge-heels-women-shoes-leather-with-bowknot_p5979.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/63bad902b75abac32d8fb93e812d77e2.jpg",
                    name: "Qililai Fashion Slip On Comfortable Women Wedge Shoes Bowknot",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-fashion-slip-on-comfortable-women-wedge-shoes-bowknot_p5978.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fbff7d33f24d10acd956fc687caea7d5.jpg",
                    name: "Qililai Women Ladies Fancy Slip On Casual Shoes Women Peep Toe Casual Shoe",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-women-ladies-fancy-slip-on-casual-shoes-women-peep-toe-casual-shoe_p5977.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9fea338c36da6add1c0f89d131269bc.jpg",
                    name: "Qililai Fashion Slip On Comfortable Skid Gentil Casual Fashion Women Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-fashion-slip-on-comfortable-skid-gentil-casual-fashion-women-shoes_p5976.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7220ae81010ffedc695a696f088f20c9.jpg",
                    name: "Qililai Leather Women Footwear Lady Mesh Sigle Mother Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-leather-women-footwear-lady-mesh-sigle-mother-shoes_p5972.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5c731a0e928cdfae56658a7cc6fc85e0.jpg",
                    name: "Qililai Women Bowknot PU Wedge Elastic Style Casual Shoes Size 35 TO 40",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-women-bowknot-pu-wedge-elastic-style-casual-shoes-size-35-to-40_p5970.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1e5832b269a5576fe86b5022b75eec5f.jpg",
                    name: "Qililai China Women Shoes Fashion Lady Wedge Shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-china-women-shoes-fashion-lady-wedge-shoes_p5969.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dfc9b0df89a63b5b9cf028c48ff256c9.jpg",
                    name: "Qililai New Style Women's Peas Leather Slip-On Soft Shoes Non-slip Rubber Mom Footwear Fold Bowknot",
                    price: "15.00",
                    url: "https://www.shoestp.com/qililai-new-style-womens-peas-leather-slip-on-soft-shoes-non-slip-rubber-mom-footwear-fold-bowknot_p5967.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ba3e8f7e0e51f96704b9df727b5bf5be.jpg",
                    name: "Qililai Ladies Fancy Flat Shoes Women Peep Toe Casual Shoe",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-ladies-fancy-flat-shoes-women-peep-toe-casual-shoe_p5975.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5e96a9d1698eb8e0649bf0f81e1b456.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a66430a44a14360e5e3b32cafd0330e.jpg",
                    name: "Qililai Comfortable Genuine Leather Women Peep-toe Wedge shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/qililai-comfortable-genuine-leather-women-peep-toe-wedge-shoes_p5966.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/40e2e96f6532476bce3172b639d299f5.jpg",
                    name: "Qililai Casual Style Comfortable Ladies Hollow-out Wedge Shoes",
                    price: "15.00",
                    url: "https://www.shoestp.com/qililai-casual-style-comfortable-ladies-hollow-out-wedge-shoes_p5965.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fe37389eae5f83d944fe4ab19ada7b57.jpg",
                    name: "Qililai Hot Sales PU Leather Shoes Loafers Casual Shoes For Women",
                    price: "12.00",
                    url: "https://www.shoestp.com/qililai-hot-sales-pu-leather-shoes-loafers-casual-shoes-for-women_p5964.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e3317397a5ab6d27f27686b050ec9306.jpg",
                    name: "Qililai Women Shoes Comfortable Black Leather Pump Shoe",
                    price: "12.00",
                    url: "https://www.shoestp.com/qililai-women-shoes-comfortable-black-leather-pump-shoe_p5963.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ccc41ee7ba461168e6540554c2bf5e4d.jpg",
                    name: "Qililai Fashion Women Silp-on shoes Hollow-out shoes",
                    price: "13.00",
                    url: "https://www.shoestp.com/qililai-fashion-women-silp-on-shoes-hollow-out-shoes_p5961.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/31286afce82066df77d490be61ff0e07.jpg",
                    name: "Qililai Luxuriant In Design Womens' Shoes Leather Genuine With Bowknot",
                    price: "11.00",
                    url: "https://www.shoestp.com/qililai-luxuriant-in-design-womens-shoes-leather-genuine-with-bowknot_p5842.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e88d1e0e0638508601df23ea67d321f5.jpg",
                    name: "Women's Simple Loafers Metal Buckle Trim Slip-ons Spring and Autumn Wedge Casual Shoes",
                    price: "5.00",
                    url: "http://www.shoestp.com/womens-simple-loafers-metal-buckle-trim-slip-ons-spring-and-autumn-wedge-casual-shoes_p0364.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/73d4ef8691008c678dbfc7879efbceba.jpg",
                    name: "Women's Fashion Loafer Casual Simple Shoes for Spring and Autumn",
                    price: "5.00",
                    url: "http://www.shoestp.com/womens-fashion-loafer-casual-simple-shoes-for-spring-and-autumn_p0361.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e2ce9e5e2fb780914823e1cf22a9cf9a.jpg",
                    name: "Qililai Casual Style Comfortable Ladies Black Hollow-out Wedge Shoes",
                    price: "12.00",
                    url: "https://www.shoestp.com/qililai-casual-style-comfortable-ladies-black-hollow-out-wedge-shoes_p5962.html"
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
