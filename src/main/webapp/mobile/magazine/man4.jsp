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
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9a2fbbb42c8c89438700470192cb3312.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fcc40be278600173df19768eb4d462b5.jpg",
                name: "Exquisite workmanship size 41-46 men leather dress shoes",
                price: "25.00",
                url: "https://www.shoestp.com/exquisite-workmanship-size-41-46-men-dress-shoes_p2804.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/734221c03dc3523327ca42f8f4351167.jpg",
                name: "Leather Wholesale Shoe Mens Oxford Brogue Design Ponit Toe Genuine Leather Dress Shoes Italian",
                price: "50.8",
                url: "https://www.shoestp.com/leather-wholesale-shoe-mens-oxford-brogue-design-ponit-toe-genuine-leather-dress-shoes-italian_p3645.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3237b422eb84e5f058df5a84d9180978.jpg",
                name: "Dark brown and wine color new Products Luxury Men genuine Leather high quality good year men dress Shoes",
                price: "90.00",
                url: "https://www.shoestp.com/dark-brown-and-wine-color-new-products-luxury-men-genuine-leather-high-quality-good-year-men-dress-shoes_p3648.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b0e97990eda015efc2b521d273ac872a.jpg",
                name: "Fashion Plaid Cloth&Leather Brogue Dress Shoes For Men",
                price: "50.8",
                url: "https://www.shoestp.com/fashion-plaid-clothampleather-brogue-dress-shoes-for-men_p3646.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3ff6cae755763500cb7963c6e79abfc5.jpg",
                name: "Latest Flat Sole Shoes Men Cow Leather&Upper Hot Sell Point Dress Male Shoes",
                price: "50.8",
                url: "https://www.shoestp.com/latest-flat-sole-shoes-men-cow-leatherampupper-hot-sell-point-dress-male-shoes_p3647.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/12392c9878ac730da1fae5df8d339f4a.jpg",
                name: "Supplier direct odm brand men leather dress shoes",
                price: "25.00",
                url: "https://www.shoestp.com/supplier-direct-odm-brand-men-dress-shoes_p2803.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/efd4b25360fdd0da0a703dfc90d03ae0.jpg",
                name: "2018 Black Fashion Point Toe Good Quality High Class Genuine Leather Wholesale Men Dress Shoes Italian",
                price: "9.50",
                url: "https://www.shoestp.com/2018-black-fashion-point-toe-good-quality-high-class-genuine-leather-wholesale-men-dress-shoes-italian_p3639.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5615dfac064575989c1ecb35ad9b3c2.jpg",
                name: "2018 China Black Point Toe Fashion Alibaba Wholesale Men Dress Shoes with Lace up Genuine Leather for Party",
                price: "9.50",
                url: "https://www.shoestp.com/2018-china-black-point-toe-fashion-alibaba-wholesale-men-dress-shoes-with-lace-up-genuine-leather-for-party_p3642.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c54f1badc6c2195df3e0f38f067e031a.jpg",
                name: "Wholesale Men Classic Lace Up Oxford Leather Shoes 7CM Height Increasing Dress Shoes In Black",
                price: "23.00",
                url: "https://www.shoestp.com/wholesale-men-classic-lace-up-oxford-leather-shoes-7cm-height-increasing-dress-shoes-in-black_p3643.html"
            },
        ]
    },
    {
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d1ae43085b311379abb20b49b7ce4975.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c3511d20c28dfa27f66a25e81fd39529.jpg",
                name: "New arrival 2017 shoes formal casual business shoes for men",
                price: "19.16",
                url: "https://www.shoestp.com/up-0523r-new-arrival-2017-shoes-formal-casual-business-shoes-for-men_p2857.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/19dbf06ce9d5d5733e3d582772b07132.jpg",
                name: "Men's Formal Shoes Fashionable Rivets All Seasons Lacing-up Business Leather Shoes",
                price: "20.00",
                url: "https://www.shoestp.com/mens-formal-shoes-fashionable-rivets-all-seasons-lacing-up-business-leather-shoes_p0441.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/800d0bb17616e9fc75134db142a1bb2c.jpg",
                name: "Men's Formal Shoes Fashionable Lace-up All Seasons Simple Business Leather Shoes",
                price: "22.00",
                url: "https://www.shoestp.com/mens-formal-shoes-fashionable-lace-up-all-seasons-simple-business-leather-shoes_p0442.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d4df394664238366d9a7e6f152cd6a4d.jpg",
                name: "Men's Formal Shoes Fashionable Rivets All Seasons Lacing-up Business Leather Shoes",
                price: "20.00",
                url: "https://www.shoestp.com/mens-formal-shoes-fashionable-rivets-all-seasons-lacing-up-business-leather-shoes_p0441.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/26e69107e53b62181bf78db630ec65ec.jpg",
                name: "Wholesale business shoes latest classic jing pin leather shoes man",
                price: "17.00",
                url: "https://www.shoestp.com/up-0628r-wholesale-business-shoes-latest-classic-jing-pin-leather-shoes-man_p2883.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e8aebddc26f33907c52bf6a9fa2bd900.jpg",
                name: "Wholesale Patent Leather Crocodile Shoes",
                price: "12.99",
                url: "https://www.shoestp.com/wholesale-patent-leather-crocodile-shoes_p2927.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/404b5a5f52493809f9458558b8e5a7b7.jpg",
                name: "Men leather large size shoes fancy dress shoes wholesale",
                price: "16.83",
                url: "https://www.shoestp.com/up-0797r-men-leather-large-size-shoes-fancy-dress-shoes-wholesale_p2900.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b217b62bb1a71db2a303cc2588357763.jpg",
                name: "New pattern men leather dress shoes fashion men footwear shoes",
                price: "15.66",
                url: "https://www.shoestp.com/up-0365j-new-pattern-men-leather-dress-shoes-fashion-men-footwear-shoes_p2907.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a8df6daefd85d5e049f0424ac18c05e1.jpg",
                name: "Luxury pu leather mens dress shoes formal business shoes",
                price: "17.50",
                url: "https://www.shoestp.com/up-0522r-luxury-pu-leather-mens-dress-shoes-formal-business-shoes_p2853.html"
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
