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
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5641e1f8b6da9384ad7b919bee6bc574.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/187911472ac14030539ed44ed90cfa78.jpg",
                  name: "Kangyida Comfortable Lace Up Men Shoes Black Casua Cow Leather Shoes",
                  price: "14.00",
                  url: "https://www.shoestp.com/kangyida-comfortable-lace-up-men-shoes-black-casua-cow-leather-shoes_p6546.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3d8c1371fe7882b562cf143435aa3419.jpg",
                  name: "Kangyida Popular Lace Up Men Shoes Brown Casual Cow Leather Shoes",
                  price: "13.00",
                  url: "https://www.shoestp.com/kangyida-popular-lace-up-men-shoes-brown-casual-cow-leather-shoes_p6543.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1b9e795536bf019d0ffbb203a8d23e03.jpg",
                  name: "Kangyida Comfortable Lace Up Cow Leather men shoes soft casual daily walking shoes",
                  price: "26.00",
                  url: "https://www.shoestp.com/kangyida-comfortable-lace-up-cow-leather-men-shoes-soft-casual-daily-walking-shoes_p6540.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/60966bdfb6e35ad481000a60c7203d0b.jpg",
                  name: "Kangyida Popular Lace Up Men Shoes Casual And Sport Cow Leather Shoes",
                  price: "9.98",
                  url: "https://www.shoestp.com/kangyida-popular-lace-up-men-shoes-casual-and-sport-cow-leather-shoes_p6528.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cf518b96121ac4016807c95647d0b449.jpg",
                  name: "Kangyida Good Quality Men Shoes Hook&Loop Black Casual Cow Leather Shoes",
                  price: "21.18",
                  url: "https://www.shoestp.com/kangyida-good-quality-men-shoes-hookamploop-black-casual-cow-leather-shoes_p6525.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6c01679eb61a0e492ce9c478e6a6710f.jpg",
                  name: "Kangyida Good Quality Men Shoes Brown Casual And Business Cow Leather Shoes",
                  price: "7.50",
                  url: "https://www.shoestp.com/kangyida-good-quality-men-shoes-brown-casual-and-business-cow-leather-shoes_p6566.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ad10ae4c87a3392d30f2e4ddb2b90638.jpg",
                  name: "Kangyida Popular Lace Up Men Shoes Casual And Sport Cow Leather Shoes",
                  price: "13.00",
                  url: "https://www.shoestp.com/kangyida-popular-lace-up-men-shoes-casual-and-sport-cow-leather-shoes_p6565.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6d8493b90c195ee2cd1bfedb128e82e8.jpg",
                  name: "Kangyida New Lace Up Men Shoes Brown Casual And Sport Cow Leather Shoes",
                  price: "7.50",
                  url: "https://www.shoestp.com/kangyida-new-lace-up-men-shoes-brown-casual-and-sport-cow-leather-shoes_p6563.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6676f49eb350092d99cbfbfc32f22402.jpg",
                  name: "Kangyida Good Quality Popular Lace Up Men Shoes Brown Thick Stitching Casual Cow Leather Shoes",
                  price: "21.18",
                  url: "https://www.shoestp.com/kangyida-good-quality-popular-lace-up-men-shoes-brown-thick-stitching-casual-cow-leather-shoes_p6526.html"
              },
          ]
      },
      {
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5f228136a1996c7a8c4b046afc208f9.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bd7c8dae8ab54f52921e66e049e1bc9d.jpg",
                  name: "Kangyida 2018New Design Men Shoes Strap Brown Casual And Business Cow Leather Shoes",
                  price: "10.00",
                  url: "https://www.shoestp.com/kangyida-2018new-design-men-shoes-strap-brown-casual-and-business-cow-leather-shoes_p6562.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1c0e674e1fc792b951d9374a5ca4bc4d.jpg",
                  name: "Kangyida Comfortable Lace Up Men Shoes Casual And Sport Cow Leather Shoes",
                  price: "14.00",
                  url: "https://www.shoestp.com/kangyida-comfortable-lace-up-men-shoes-casual-and-sport-cow-leather-shoes_p6559.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/98592594c89d3446baa41bc884916bbc.jpg",
                  name: "Kangyida New Lace Up Men Shoes Brown Casual And Sport Cow Leather Shoes",
                  price: "9.50",
                  url: "https://www.shoestp.com/kangyida-new-lace-up-men-shoes-brown-casual-and-sport-cow-leather-shoes_p6561.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/60bc79e5193cde9d2908e40c2235cd74.jpg",
                  name: "Kangyida New Lace Up Men Shoes Black Casual And Sport Cow Leather Shoes",
                  price: "19.00",
                  url: "https://www.shoestp.com/kangyida-new-lace-up-men-shoes-black-casual-and-sport-cow-leather-shoes_p6558.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7317bf5abdfc1f19dfe58298e4733cd2.jpg",
                  name: "Kangyida Good Quality Popular Lace Up Men Shoes Black Casual Cow Leather Shoes",
                  price: "26.00",
                  url: "https://www.shoestp.com/kangyida-good-quality-popular-lace-up-men-shoes-black-casual-cow-leather-shoes_p6554.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/aac5a66fe5b1c60cccc538ede02a8345.jpg",
                  name: "Kangyida Comfortable Men Shoes Brown Casual Cow Leather Shoes",
                  price: "13.00",
                  url: "https://www.shoestp.com/kangyida-comfortable-men-shoes-brown-casual-cow-leather-shoes_p6551.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d5a91dccdc392147b690f1456a7134ba.jpg",
                  name: "Kangyida Popular Lace Up Men Shoes Black Casual Cow Leather Daily Walking Shoes",
                  price: "26.00",
                  url: "https://www.shoestp.com/kangyida-comfortable-men-shoes-brown-casual-cow-leather-shoes_p6551.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d8f6cd20feae42429f683e0e0477e3f7.jpg",
                  name: "Kangyida Comfortable Men Shoes Black Casual Cow Leather Walking Shoes",
                  price: "26.00",
                  url: "https://www.shoestp.com/kangyida-comfortable-men-shoes-black-casual-cow-leather-walking-shoes_p6547.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6e3a5bb5b297fb917312020a2b28c845.jpg",
                  name: "Kangyida Popular Men Shoes Brown Casual And Business Cow Leather Shoes",
                  price: "14.00",
                  url: "https://www.shoestp.com/kangyida-popular-men-shoes-brown-casual-and-business-cow-leather-shoes_p6557.html"
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
