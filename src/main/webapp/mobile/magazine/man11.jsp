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
           bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c458d74083e3005e21a251b5eab0ab1f.jpg",
           itemImg: [
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dc0ae505a99cb86587c807ef072f59f.jpg",
                   name: "Jiansha Fashion Genuine Leather Lace Up Dress Shoes For Men",
                   price: "20.50",
                   url: "https://www.shoestp.com/jiansha-fashion-genuine-leather-lace-up-dress-shoes-for-men_p6159.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/14d236e00b0180e3a65ff0c956ed8547.jpg",
                   name: "Jiansha Gentleman Kid Suede Leather Lace Up Brown Casul Dress Shoes",
                   price: "20.50",
                   url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-leather-lace-up-brown-casul-dress-shoes_p6158.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/18a6671bec4c55e547888b928c97cfd2.jpg",
                   name: "Jiansha Popular Genuine Leather Lace Up Dress Shoes For Men",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-popular-genuine-leather-lace-up-dress-shoes-for-men_p6155.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/aa379fd90605db8e94601cba9d6ed075.jpg",
                   name: "Jiansha Gentlemen Burgundy Genuine Leather Dress Shoes",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-gentlemen-burgundy-genuine-leather-dress-shoes_p6152.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/510d4c091cf0fcc84d8641933d32349f.jpg",
                   name: "Jiansha Brown Genuine Leather Dress Shoes For Men",
                   price: "22.00",
                   url: "https://www.shoestp.com/jiansha-brown-genuine-leather-dress-shoes-for-men_p6149.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/acdb94c8c61eab2a7ee6b11efd89b9e7.jpg",
                   name: "Jiansha Fashion Genuine Leather Strap With Buckle Dress Shoes For Men",
                   price: "20.50",
                   url: "https://www.shoestp.com/jiansha-fashion-genuine-leather-strap-with-buckle-dress-shoes-for-men_p6148.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/95f80c9187d0bef0d3f6c5e90ffb986b.jpg",
                   name: "Jiansha Brown Genuine Leather Dress Shoes For Men",
                   price: "20.50",
                   url: "https://www.shoestp.com/jiansha-brown-genuine-leather-dress-shoes-for-men_p6146.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2467419654e203645847043847cab74d.jpg",
                   name: "Jiansha Popular Genuine Leather Strap With Buckle Dress Shoes For Men",
                   price: "20.50",
                   url: "https://www.shoestp.com/jiansha-popular-genuine-leather-strap-with-buckle-dress-shoes-for-men_p6145.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c1f90e0bec2d4d4cd30d04f4c218278e.jpg",
                   name: "Jiansha Gentlemen Genuine Leather Punching Casual Dress Shoes",
                   price: "23.00",
                   url: "https://www.shoestp.com/jiansha-gentlemen-genuine-leather-punching-casual-dress-shoes_p6151.html"
               },
           ]
       },
       {
           bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4b4463c10483ef052136cfd1a5946bb6.jpg",
           itemImg: [
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2f3f64e3140551c8000cb47468d86ce1.jpg",
                   name: "Jiansha Popular Genuine Leather Casual Dress Shoes For Men",
                   price: "23.00",
                   url: "https://www.shoestp.com/jiansha-popular-genuine-leather-casual-dress-shoes-for-men_p6142.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5d98af2e18b08802fbc546e6cf3cdfa3.jpg",
                   name: "Jiansha 2018 Genleman Genuine Leather Casual Dress Shoes",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-2018-genleman-genuine-leather-casual-dress-shoes_p6141.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/84553b158221aa54678cf858658bfd0a.jpg",
                   name: "Jiansha Gentleman Genuine Leather Punching Casual Dress Shoes",
                   price: "22.30",
                   url: "https://www.shoestp.com/jiansha-gentleman-genuine-leather-punching-casual-dress-shoes_p6139.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ef230c24a71326f9c93a7224769ffbc9.jpg",
                   name: "Jiansha Popular Gentleman Genuine Leather Blue Dress Shoes",
                   price: "20.50",
                   url: "https://www.shoestp.com/jiansha-popular-gentleman-genuine-leather-blue-dress-shoes_p6137.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f93aeed846af9d690d71229f3689230b.jpg",
                   name: "Jiansha Popular Genuine Leather Lace Up Business Shoes For Men",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-popular-genuine-leather-lace-up-business-shoes-for-men_p6127.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/539c7618e7ed18d3c6b4a3b4d19e8766.jpg",
                   name: "Jiansha Fashion Gentleman Genuine Leather Punching Brown Dress Shoes",
                   price: "22.00",
                   url: "https://www.shoestp.com/jiansha-fashion-gentleman-genuine-leather-punching-brown-dress-shoes_p6119.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b965e42fa9dfa2ddd1009f951463336a.jpg",
                   name: "Jiansha Lace Up Genuine Leather Dress Dress Shoes For Men",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-lace-up-genuine-leather-dress-dress-shoes-for-men_p6115.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/735138bcece7368c4dac9b5f1e071ca4.jpg",
                   name: "Jiansha Durable Soft Genuine Leather Dress Shoes For Men",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-durable-soft-genuine-leather-dress-shoes-for-men_p6112.html"
               },
               {
                   img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/10b0da99a9413a62fea8817112e25cd1.jpg",
                   name: "Jiansha Genuine Leather Lace Up Casual Business Shoes For Men",
                   price: "21.00",
                   url: "https://www.shoestp.com/jiansha-genuine-leather-lace-up-casual-business-shoes-for-men_p6134.html"
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
