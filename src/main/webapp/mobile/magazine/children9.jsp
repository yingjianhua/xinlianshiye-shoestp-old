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
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ce139c21210e4f522933e7f3cb04179f.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/886affbc0c88c0e90566c2f4217f0c3f.jpg",
                  name: "Juyue Children Girls Shoes Cute Bowknot Canvas Shoes Candy Color Kids",
                  price: "3.00",
                  url: "https://www.shoestp.com/juyue-children-girls-shoes-cute-bowknot-canvas-shoes-candy-color-kids_p5814.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bcf093d667ff014264cb3a3c2f05668c.jpg",
                  name: "Juyue Hot Sell Boys Buckle Strap Style Casual Shoes",
                  price: "3.50",
                  url: "https://www.shoestp.com/juyue-hot-sell-boys-buckle-strap-style-casual-shoes_p5813.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6f78491dc6e1a570951708e49b2209a8.jpg",
                  name: "Juyue Girl's Shoes Breathable and Soft Upper Adjustable Straps Closed-Toe Style Canvas Shoes",
                  price: "3.50",
                  url: "https://www.shoestp.com/juyue-girls-shoes-breathable-and-soft-upper-adjustable-straps-closed-toe-style-canvas-shoes_p5812.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7302dbe1c95685cf489a3ed3c502875a.jpg",
                  name: "Children Shoes Girls Flat PU Casual Outdoor Shoes",
                  price: "4.00",
                  url: "https://www.shoestp.com/children-shoes-girls-flat-pu-casual-outdoor-shoes_p5809.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/44f9f3ee697d431ff1e8f45fe422b7cc.jpg",
                  name: "Juyue Fashion Magic Stickers Casual Flat Shoes",
                  price: "4.50",
                  url: "https://www.shoestp.com/juyue-fashion-magic-stickers-casual-flat-shoes_p5801.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/28e8da5432689a5a9d98ebc4966c68a9.jpg",
                  name: "Juyue Comfortable Canvas Girls Shoe Casual Shoes",
                  price: "3.50",
                  url: "https://www.shoestp.com/juyue-comfortable-canvas-girls-shoe-casual-shoes_p5773.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/82b31807af26d87f27706ca38583b73c.jpg",
                  name: "Juyue Graffiti Canvas Shoes Slip-on Boys' Shoes",
                  price: "3.80",
                  url: "https://www.shoestp.com/juyue-graffiti-canvas-shoes-slip-on-boys-shoes_p5772.htm"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1b3a81fcc825e4bd2de8127fbc295449.jpg",
                  name: "Juyue New Causal Canvas Girls' Shoes With Light Lace-up",
                  price: "4.00",
                  url: "https://www.shoestp.com/juyue-new-causal-canvas-girls-shoes-with-light-lace-up_p5770.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dcb316f32dc3a5d2dbfa53b606a3e89d.jpg",
                  name: "Juyue Kid Footwear Boy Casual Soft Shoe With Magic Strap",
                  price: "4.00",
                  url: "https://www.shoestp.com/juyue-kid-footwear-boy-casual-soft-shoe-with-magic-strap_p5804.html"
              },
          ]
      },
      {
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5f0ab822039632d32b91320423eadaa5.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7eb6cd5d235b88ad2646571fac3f4bd.jpg",
                  name: "Juyue Children Canvas Sneakers Casual Shoes in Buckle Strap For Girls",
                  price: "4.00",
                  url: "https://www.shoestp.com/juyue-children-canvas-sneakers-casual-shoes-in-buckle-strap-for-girls_p5769.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bb21050928775ceffaa7a926936f1110.jpg",
                  name: "Juyue Casual Slip On Girls' Shoes Flat Shoes",
                  price: "3.00",
                  url: "https://www.shoestp.com/juyue-casual-slip-on-girls-shoes-flat-shoes_p5768.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ca39569811905b3dfb6be5c46eb4b2d9.jpg",
                  name: "Juyue New Style Canvas Frenulum Fashion Girls' Canvas Shoes",
                  price: "3.00",
                  url: "https://www.shoestp.com/juyue-new-style-canvas-frenulum-fashion-girls-canvas-shoes_p5757.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/373d783a64cde3cf3bdd41b8fb7b04e1.jpg",
                  name: "Juyue 2018 Simple Casual Child Canvas Shoes",
                  price: "3.50",
                  url: "https://www.shoestp.com/juyue-2018-simple-casual-child-canvas-shoes_p5756.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/49ff318a3a2a473d1de6017912105885.jpg",
                  name: "Juyue 2018 Comfortable Casual Child Canvas Shoes",
                  price: "3.50",
                  url: "https://www.shoestp.com/juyue-2018-comfortable-casual-child-canvas-shoes_p5755.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6d3a6c04a9c44f665aaeb901fb55a.jpg",
                  name: "Juyue Cute Dinosaur Design Canvas Kids Shoes",
                  price: "3.50",
                  url: "https://www.shoestp.com/juyue-cute-dinosaur-design-canvas-kids-shoes_p5759.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1e75d0c92d605dfd102f9da2360f3b4d.jpg",
                  name: "Juyue New Causal Canvas Girls' Shoes With Light Lace-up",
                  price: "4.00",
                  url: "https://www.shoestp.com/juyue-new-causal-canvas-girls-shoes-with-light-lace-up_p5771.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f092306f64486eae47852193e0c1994f.jpg",
                  name: "Juyue Classic Fashion Kids Girls' Bowknot Princess Canvas Shoes",
                  price: "3.00",
                  url: "https://www.shoestp.com/juyue-classic-fashion-kids-girls-bowknot-princess-canvas-shoes_p5778.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a6d4a9cf76a45acb641dbe21e1efe5f2.jpg",
                  name: "Juyue Kids Glitter Upper Casual Shoes For Girls",
                  price: "4.20",
                  url: "https://www.shoestp.com/juyue-kids-glitter-upper-casual-shoes-for-girls_p5750.html"
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
