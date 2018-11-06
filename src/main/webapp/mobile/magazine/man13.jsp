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
         bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/419a48174031f022be513c8e8937b3f4.jpg",
         itemImg: [
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6581409e565f4bfbcbd97faea103c190.jpg",
                 name: "SE New Arrive Men Casual Shoes",
                 price: "6.80",
                 url: "https://www.shoestp.com/se-new-arrive-men-casual-shoes_p6065.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5308f416ebdccdf3e11b21777a9cfeaf.jpg",
                 name: "Joyou Mesh Men's Sports Running Shoes Men Casual Shoes Comfortable Breathable Shoes",
                 price: "4.50",
                 url: "https://www.shoestp.com/joyou-mesh-mens-sports-running-shoes-men-casual-shoes-comfortable-breathable-shoes_p5753.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/402e6660cb7fabf2ea8e816e012c94d1.jpg",
                 name: "Joyou 2018 Light Fashion Men's Casual Shoes Breathable PU Sports Shoes",
                 price: "4.00",
                 url: "https://www.shoestp.com/joyou-2018-light-fashion-mens-casual-shoes-breathable-pu-sports-shoes_p5785.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/945d3c35def2c49d42ad2f80431dac65.jpg",
                 name: "SE New Arrive Men Casual PU Shoes",
                 price: "7.00",
                 url: "https://www.shoestp.com/se-new-arrive-men-casual-pu-shoes_p6071.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5621effb8a3715b2482a2c6400605da9.jpg",
                 name: "Jiansha Brown Genuine Leather Dress Shoes For Men",
                 price: "20.50",
                 url: "https://www.shoestp.com/jiansha-brown-genuine-leather-dress-shoes-for-men_p6146.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ead9529623a80515e19339b2cc3fa023.jpg",
                 name: "Jiansha Gentleman Kid Suede Genuine Leather Lace Up Casul Dress Shoes",
                 price: "20.50",
                 url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-genuine-leather-lace-up-casul-dress-shoes_p6153.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d28bea89fdd55e030c5a4316818886e4.jpg",
                 name: "Jiansha Gentleman Kid Suede Leather Lace Up Brown Casul Dress Shoes",
                 price: "20.50",
                 url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-leather-lace-up-brown-casul-dress-shoes_p6158.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e911825536bb95193496acfb6771b4d2.jpg",
                 name: "Jiansha Gentleman Kid Suede Leather White Outsole Casul Dress Shoes",
                 price: "23.00",
                 url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-leather-white-outsole-casul-dress-shoes_p6160.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c43f6d5de9d83c79f4dd83e7c159e90.jpg",
                 name: "Jiansha Genuine Leather Lace Up Casual Business Shoes For Men",
                 price: "21.00",
                 url: "https://www.shoestp.com/jiansha-genuine-leather-lace-up-casual-business-shoes-for-men_p6134.html"
             },
         ]
     },
     {
         bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/631764487d9bb2a7bdc35613104d33a6.jpg",
         itemImg: [
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e955adc71aeb04437eea248fc2915c04.jpg",
                 name: "Jiansha Gentleman Kid Suede Leather Lace Up Brown Casul Dress Shoes",
                 price: "20.50",
                 url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-leather-lace-up-brown-casul-dress-shoes_p6158.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e999304a2dbc3051a6a410a4084a20cc.jpg",
                 name: "Jiansha Popular Genuine Leather Lace Up White Outsole Casual Shoes",
                 price: "22.00",
                 url: "https://www.shoestp.com/jiansha-popular-genuine-leather-lace-up-white-outsole-casual-shoes_p6114.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bf864dc95a6f4876c18d9a37d760d3f3.jpg",
                 name: "Kangfu Summer Breathable Leisure Hollow Out Shoes Leather Fashion Shoes",
                 price: "13.50",
                 url: "https://www.shoestp.com/kangfu-summer-breathable-leisure-hollow-out-shoes-leather-fashion-shoes_p6233.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5698b6ecf11ed498cddb7ca5aa3940c5.jpg",
                 name: "Kangfu Men Fashion Design Soft Men Leather Sandals",
                 price: "22.38",
                 url: "https://www.shoestp.com/kangfu-men-fashion-design-soft-men-leather-sandals_p6300.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/951232a6b94d8149413aef9de4faf482.jpg",
                 name: "Kangfu white slip-on Hollow Out casual Pu shoes for mens",
                 price: "11.30",
                 url: "https://www.shoestp.com/kangfu-white-slip-on-hollow-out-casual-pu-shoes-for-mens_p6315.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e65f28ebdce8567bd87a6014753a458f.jpg",
                 name: "Kangfu Mens Hollow Out Casual Dress Shoes With Lace-Up",
                 price: "12.20",
                 url: "https://www.shoestp.com/kangfu-mens-hollow-out-casual-dress-shoes-with-lace-up_p6320.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2a0fb486804bf1d37edd6cc1f504facf.jpg",
                 name: "SE 2018Popular Men Shoes with White Outsole PU Casual Shoes",
                 price: "7.10",
                 url: "https://www.shoestp.com/se-2018popular-men-shoes-with-white-outsole-pu-casual-shoes_p6091.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1935839b3944d000733a199763be48fb.jpg",
                 name: "Jiansha Popular Genuine Leather Lace Up Casual Shoes",
                 price: "22.00",
                 url: "https://www.shoestp.com/jiansha-popular-genuine-leather-lace-up-casual-shoes_p6116.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/118d111fc8c5d59a624b9f616f52db4c.jpg",
                 name: "Kangfu new fashion White Anti-slip men shoes flat sole summer shoes",
                 price: "11.60",
                 url: "https://www.shoestp.com/kangfu-new-fashion-white-anti-slip-men-shoes-flat-sole-summer-shoes_p6313.html"
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
