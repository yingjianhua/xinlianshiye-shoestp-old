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
      bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3000485d86aa3c8dde1777a9bd1c80fe.jpg",
      itemImg: [{
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b309a817363ac3500849549af3981afa.jpg",
              name: "Lightweight Runing Breathable Slip-On Mesh action sports running sneakers shoes for men women",
              price: "6.00",
              url: "https://www.shoestp.com/lightweight-runing-breathable-slip-on-mesh-action-sports-running-sneakers-shoes-for-men-women_p3003.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3d6dc546551d6d77e5fae71a3e11a759.jpg",
              name: "upper fashion women sports casual running shoes in stock",
              price: "10.00",
              url: "https://www.shoestp.com/pu-upper-fashion-women-sports-casual-running-shoes-in-stock_p3007.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/981cbbddeffb96b5df55c8956f55e0e5.jpg",
              name: "Breathable Mesh Sports Training Sneakers Running Shoes for Men and Women",
              price: "8.00",
              url: "https://www.shoestp.com/breathable-mesh-sports-training-sneakers-running-shoes-for-men-and-women_p3000.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d75c770f359fee183b006d42b5427943.jpg",
              name: "2018 New look lady comfortable high quality women sport air cushione shoes",
              price: "8.00",
              url: "https://www.shoestp.com/2018-new-look-lady-comfortable-high-quality-women-sport-air-cushione-shoes_p2963.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/11040a3b3c8045c4aa6fbd3800b50d27.jpg",
              name: "Factory product women sport dress shoes and bags to match",
              price: "7.90",
              url: "https://www.shoestp.com/hfx0113-factory-product-women-sport-dress-shoes-and-bags-to-match_p2961.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/50ee70985f35906cce7277536cb233e8.jpg",
              name: "flymesh fashion cool woman footwear sneakers woman sport running shoe with knit",
              price: "8.00",
              url: "https://www.shoestp.com/flymesh-fashion-cool-woman-footwear-sneakers-woman-sport-running-shoe-with-knit_p3001.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8553556089fc9ac30e5c1d504f42194a.jpg",
              name: "women fashion sports shoes cheap prices",
              price: "6.80",
              url: "https://www.shoestp.com/women-fashion-sports-shoes-cheap-prices_p0999.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d69e0cfbb0eed757dedd41201183e867.jpg",
              name: "Wholesale non slip outdoor sports waterproof hiking shoes women",
              price: "5.90",
              url: "https://www.shoestp.com/hf-x046-wholesale-non-slip-outdoor-sports-waterproof-hiking-shoes-women_p2962.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2921ce3cd40bc137521690581e9f5231.jpg",
              name: "2018 fashion lady sport shoe",
              price: "11.00",
              url: "https://www.shoestp.com/2018-fashion-lady-sport-shoe_p2992.html"
          },
      ]
  },
  {
      bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b048549b36c44b916fb301191024fb00.jpg",
      itemImg: [{
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6079accad912c0665791f848d818369e.jpg",
              name: "New style custom fashion sport women casual shoes China shoe factory wholesale cheap shoes and sneakers",
              price: "9.60",
              url: "https://www.shoestp.com/new-style-custom-fashion-sport-women-casual-shoes-china-shoe-factory-wholesale-cheap-shoes-and-sneakers_p2970.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4f16dfb68ef784df3b02a5bccd9deeee.jpg",
              name: "women sports running shoes girls school sports shoes",
              price: "6.90",
              url: "https://www.shoestp.com/women-sports-running-shoes-girls-school-sports-shoes_p0977.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5363106d2d4e1ae63a396c1ea8001f61.jpg",
              name: "High quality wholesale fashion running sport casual shoes for women",
              price: "7.00",
              url: "https://www.shoestp.com/high-quality-wholesale-fashion-running-sport-casual-shoes-for-women_p3002.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3eefca39a4279fa53cf4a51c08fb33e9.jpg",
              name: "New design fashion white air mesh sport shoes",
              price: "11.00",
              url: "https://www.shoestp.com/new-design-fashion-white-air-mesh-sport-shoes_p3041.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/14957fc9e602fe66924537f9379b19b6.jpg",
              name: "Lightweight Runing Breathable Slip-On Mesh action sports running sneakers shoes for men women",
              price: "6.00",
              url: "https://www.shoestp.com/lightweight-runing-breathable-slip-on-mesh-action-sports-running-sneakers-shoes-for-men-women_p3003.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d8bf2d7e017e3419456560cedf823c3c.jpg",
              name: "lightweight men casual sport shoes women sneaker sale online",
              price: "10.00",
              url: "https://www.shoestp.com/lightweight-men-casual-sport-shoes-women-sneaker-sale-online_p3020.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/63652748325c520628bfeb972c84da30.jpg",
              name: "China Cheap price Air cushion running sports shoes for women",
              price: "1.00",
              url: "https://www.shoestp.com/hfrta153-china-cheap-price-air-cushion-running-sports-shoes-for-women_p2956.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c00200bf7c9e6edb6aef4ef841490788.jpg",
              name: "fashion latest model white sport shoes lady in 2018",
              price: "9.00",
              url: "https://www.shoestp.com/fashion-latest-model-white-sport-shoes-lady-in-2018_p2993.html"
          },
          {
              img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ac296aca0ca599a5cf8d508524a20f82.jpg",
              name: "New arrived fashion look women sport fly walking shoes",
              price: "9.20",
              url: "https://www.shoestp.com/new-arrived-fashion-look-women-sport-fly-walking-shoes_p2978.html"
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
