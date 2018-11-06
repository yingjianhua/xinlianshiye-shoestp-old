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
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/eb73ed7ae7e7269d4eee3cfdf50b99d8.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/48442b4357fdd8458cbca25ca955431e.jpg",
                  name: "men ankle casual shoes latest men low prices sports running shoes",
                  price: "7.10",
                  url: "https://www.shoestp.com/men-ankle-casual-shoes-latest-men-low-prices-sports-running-shoes_p0868.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dc20bc872fe8e45dfeed46cf9f2dfb46.jpg",
                  name: "New Fashion Sport Shoes SuperStar Shoes Sneakers For men and Women",
                  price: "10.00",
                  url: "https://www.shoestp.com/new-fashion-sport-shoes-superstar-shoes-sneakers-for-men-and-women_p3012.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1e8a111fc09326a0c508d8871e538c1a.jpg",
                  name: "Breathable Mesh Sports Training Sneakers Running Shoes for Men and Women",
                  price: "8.00",
                  url: "https://www.shoestp.com/breathable-mesh-sports-training-sneakers-running-shoes-for-men-and-women_p3000.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2d1fe0b2337623e0d940f82e4bf167b6.jpg",
                  name: "wholesale basketball shoes for men",
                  price: "12.00",
                  url: "https://www.shoestp.com/wholesale-basketball-shoes-for-men_p3055.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3d2d49e24c989f4645ea3ea85e7e067a.jpg",
                  name: "colorful Factory Direct Sales Knitting fabric lace-up man sport shoe shoes",
                  price: "12.10",
                  url: "https://www.shoestp.com/colorful-factory-direct-sales-knitting-fabric-lace-up-man-sport-shoe-shoes_p1897.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e982cf636a3adc948560eacaae0c32f1.jpg",
                  name: "Custom Sneaker Manufacturer Sneaker Men Running Sport Shoe",
                  price: "12.00",
                  url: "https://www.shoestp.com/custom-sneaker-manufacturer-sneaker-men-running-sport-shoe_p3051.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d979d6506373a2768259edd819ddf1d.jpg",
                  name: "China Factory Waterproof Men Basketball Shoes",
                  price: "10.00",
                  url: "https://www.shoestp.com/china-factory-waterproof-men-basketball-shoes_p3066.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b8a1afa0f3d1f9da5e40120686d58865.jpg",
                  name: "2018 best-selling wholesale manufacturer casual sport women shoes",
                  price: "15.00",
                  url: "https://www.shoestp.com/2018-best-selling-wholesale-manufacturer-casual-sport-women-shoes_p3038.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f92fa372fbb91c841fc2e7d5fe7ce55b.jpg",
                  name: "Custom Sneaker Manufacturer Sneaker Men Running Sport Shoe",
                  price: "12.00",
                  url: "https://www.shoestp.com/custom-sneaker-manufacturer-sneaker-men-running-sport-shoe_p3051.html"
              },
          ]
      },
      {
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/77f083568e4191c5f87f2e11dfd78594.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/37925d668e4170a5a06e11e943a4e10f.jpg",
                  name: "men sports shoes running men sports shoes",
                  price: "8.28",
                  url: "https://www.shoestp.com/men-sports-shoes-running-men-sports-shoes_p0749.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/86c20b7b56d464eba0f92f5824191d3.jpg",
                  name: "wholesale basketball shoes for men",
                  price: "12.00",
                  url: "https://www.shoestp.com/wholesale-basketball-shoes-for-men_p3055.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6edac4e85683b7f5883f8db93208c2f7.jpg",
                  name: "2018 Hot sale factory direct Sales lace-up man sport shoe",
                  price: "9.20",
                  url: "https://www.shoestp.com/2018-hot-sale-factory-direct-sales-lace-up-man-sport-shoe_p1931.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cdadb718618f8f19d99b0a7ed39d39d7.jpg",
                  name: "Men's PU and Microfiber Sneaker Shoes Spring and Autumn Lacing-up Thick Sole Flat Casual Sports Shoes",
                  price: "12.50",
                  url: "https://www.shoestp.com/mens-pu-and-microfiber-sneaker-shoes-spring-and-autumn-lacing-up-thick-sole-flat-casual-sports-shoes_p0299.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6becab10271e779d01210a1ba4fcbd68.jpg",
                  name: "Leisure style white basketball exercise men new sport shoes for teenager",
                  price: "11.50",
                  url: "https://www.shoestp.com/_p3281.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/987ec9d4f60a7fa97389dffa80880325.jpg",
                  name: "Custom Sneaker Manufacturer Sneaker Men Running Sport Shoe",
                  price: "12.00",
                  url: "https://www.shoestp.com/custom-sneaker-manufacturer-sneaker-men-running-sport-shoe_p3051.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bfd87ae28cc5795e0fb03d664dcf35cf.jpg",
                  name: "latest men's fashion air balance shoes",
                  price: "9.00",
                  url: "https://www.shoestp.com/latest-mens-fashion-air-balance-shoes_p3059.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bb43d1dd487e579543a26e5038ba19c3.jpg",
                  name: "good quality fashion breathable used tennis shoes",
                  price: "100.00",
                  url: "https://www.shoestp.com/_p3283.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/52c476fb6fced4a564b1badfd463d0d5.jpg",
                  name: "Fashion Sneakers Men Casual Shoes 2018 Spring New Design lightweight Breathable Mesh Canvas shoes Men shoes Zapatillas Hombre",
                  price: "3.30",
                  url: "https://www.shoestp.com/fashion-sneakers-men-casual-shoes-2018-spring-new-design-lightweight-breathable-mesh-canvas-shoes-men-shoes-zapatillas-hombre_p0836.html"
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
