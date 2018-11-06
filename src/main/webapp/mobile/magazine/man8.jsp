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
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fecc5398ead6286606627be0f90fdd5d.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2b019031fe86603906046975746db490.jpg",
                  name: "white canvas men's flat casual shoes 2018 men fashion slip-on wholesale",
                  price: "6.70",
                  url: "https://www.shoestp.com/white-canvas-mens-flat-casual-shoes-2018-men-fashion-slip-on-wholesale_p2038.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/db701e393ecbe7ffc9a6d459ae70f9ec.jpg",
                  name: "new model casual man cheap shoes for men",
                  price: "6.00",
                  url: "https://www.shoestp.com/new-model-casual-man-cheap-shoes-for-men_p2034.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/64f6c79f8fd726280bf7ef973a6da94e.jpg",
                  name: "China manufacture professional mens casual sneaker shoes loafer clark style",
                  price: "3.75",
                  url: "https://www.shoestp.com/china-manufacture-professional-mens-casual-sneaker-shoes-loafer-clark-style_p1899.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3aa72f207a65bc9639fa6d216d0fa314.jpg",
                  name: "Men's Fashionable Shoes Flower Print Metal Buckle Slip-on Spring and Autumn Embossed Casual Canvas Shoes",
                  price: "13.53",
                  url: "https://www.shoestp.com/mens-fashionable-shoes-flower-print-metal-buckle-slip-on-spring-and-autumn-embossed-casual-canvas-shoes_p1384.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4b7ba2523d2b6dc080827ea8aed365cd.jpg",
                  name: "New design fashion Cloth+EVA black casual men shoes,eva casual shoes lace up for men summer 2017",
                  price: "8.74",
                  url: "https://www.shoestp.com/new-design-fashion-clotheva-black-casual-men-shoeseva-casual-shoes-lace-up-for-men-summer-2017_p0714.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/97aeff8be85a221635bc7cb380520ea0.jpg",
                  name: "Men's Grey Canvas Shoes Lacing up Spring and Autumn Breathable Flat Single Shoes",
                  price: "5.50",
                  url: "https://www.shoestp.com/mens-grey-canvas-shoes-lacing-up-spring-and-autumn-breathable-flat-single-shoes_p0107.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/99b0a9320d64bd083ba2b593a0e09fce.jpg",
                  name: "ODM Most Popular Men Shoes , Casual Shoes For Men ,Men Canvas Shoes",
                  price: "7.90",
                  url: "https://www.shoestp.com/odm-most-popular-men-shoes-casual-shoes-for-men-men-canvas-shoes_p0844.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/437a9888f4d057e80df740187ec7f16e.jpg",
                  name: "hot sale good quality lace-up men Shoes",
                  price: "4.50",
                  url: "https://www.shoestp.com/hot-sale-good-quality-lace-up-men-shoes_p0850.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/13981743eda53dd3547542e30994828e.jpg",
                  name: "Trend Personality Men Canvas Shoes Colourful Casual Shoes Wholesale In China",
                  price: "6.50",
                  url: "https://www.shoestp.com/trend-personality-men-canvas-shoes-colourful-casual-shoes-wholesale-in-china_p3507.html"
              },
          ]
      },
      {
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/eff2f45d5a1170b48d02481184c39d54.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a75f1aae184a5e53a59b965a633f2979.jpg",
                  name: "Wholesale Canvas Upper Men Casual Shoes Hot Sale Men Footwear Casual Sneakers",
                  price: "6.35",
                  url: "https://www.shoestp.com/wholesale-canvas-upper-men-casual-shoes-hot-sale-men-footwear-casual-sneakers_p3493.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/51012375de080d24274d0109226f486c.jpg",
                  name: "2018 Customized Man Shoe Casual Canvas Shoes Men March Expo Shoes",
                  price: "7.50",
                  url: "https://www.shoestp.com/2018-customized-man-shoe-casual-canvas-shoes-men-march-expo-shoe_p3487.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/820efaf584a621a581ae3ec779006203.jpg",
                  name: "New style Mens low Top Colorful printing Vulcanized shoes",
                  price: "4.30",
                  url: "https://www.shoestp.com/new-style-mens-low-top-colorful-printing-vulcanized-shoes_p0863.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ed6cc3f79029ac851a9a8fa6086d1eb5.jpg",
                  name: "Sport casual style men rubber mesh safety water shoes in Wenzhou",
                  price: "7.60",
                  url: "https://www.shoestp.com/sport-casual-style-men-rubber-mesh-safety-water-shoes-in-wenzhou_p0806.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5d478b104e62fb48984f7b7650cf5d41.jpg",
                  name: "shoes men casual shoes and sneakers boys school slip on casual shoes",
                  price: "3.45",
                  url: "https://www.shoestp.com/shoes-men-casual-shoes-and-sneakers-boys-school-slip-on-casual-shoes_p0787.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5c8bd24ae8a3ce7c7e3c50bc53e5de0a.jpg",
                  name: "Stylish Jeans Upper Canvas Shoes Men Casual Men Shoes Men Sneakers",
                  price: "6.50",
                  url: "https://www.shoestp.com/stylish-jeans-upper-canvas-shoes-men-casual-men-shoes-men-sneakers_p3484.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3032c4c9d7f24b0f3d8add7a108a43ea.jpg",
                  name: "Hot Sale Customized Man Shoes and Sneakers Men Footwear 100 Pairs MOQ Accept",
                  price: "7.50",
                  url: "https://www.shoestp.com/hot-sale-customized-man-shoes-and-sneakers-men-footwear-100-pairs-moq-accept_p3488.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2e103e4d582a39d1ffc24adf308c29d1.jpg",
                  name: "Men Casual Shoes Fashion Men Boat Shoes Slip On Canvas Shoes Sneakers",
                  price: "6.50",
                  url: "https://www.shoestp.com/oem-men-casual-shoes-fashion-men-boat-shoes-slip-on-canvas-shoes-sneakers_p3477.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b3c41edab94f449e4004eb32493d9042.jpg",
                  name: "Casual Men Shoes Hemp Canvas Shoes Comfortable and Breathable Footwear",
                  price: "4.30",
                  url: "https://www.shoestp.com/casual-men-shoes-hemp-canvas-shoes-comfortable-and-breathable-footwear_p0779.html"
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
