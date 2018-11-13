<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<script src="/home/static/js/jquery-1.7.2.min.js"></script>
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

    .goods-box {
        max-width: 1200px;
        min-width: 375px;
        margin: 0 auto;
        height: 100%;
        width: 100%;
    }

    .goods-bgImg {
        font-size: 0;
    }

    .goods-box .goods-list:nth-of-type(4) {
        margin-bottom: 5%;
    }

    .goods-box .goods-bgImg:nth-of-type(3) {
        margin-top: 20px;
    }

    .goods-item {
        width: 24.3%;
        float: left;
        position: relative;
        font-size: 0;
        margin: 0.9% 0.9% 0 0;
        overflow: hidden;
    }

    .goods-item-info {
        box-sizing: border-box;
        padding: 20px;
        width: 100%;
        height: 34.5%;
        background: #313131;
        position: absolute;
        left: 0;
        bottom: 0;
        text-align: left;
        color: #ffffff;
        transform: translateY(220px);
        -webkit-transform: translateY(220px);
        -moz-transform: translateY(220px);
        transition: all 0.7s;
        -webkit-transition: all 0.7s;
        -moz-transition: all 0.7s;
        opacity: 0.8;
    }

    .goods-item-info p {
        margin: 0;
        font-size: 14px;
        line-height: 18px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        -ms-text-overflow: ellipsis;
        text-overflow: ellipsis;
        white-space: normal;
        word-wrap: break-word;
        word-break: break-all;
        word-wrap: break-word;
        position: relative;
        max-height: 35px;
    }

    .goods-item-info h3 {
        margin: 10px 0 0 0;
        font-size: 16px;
    }

    .goods-item:hover {
        box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
        -moz-box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
        -webkit-box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
    }

    .goods-item:hover .goods-item-info {
        transform: translateY(0px);
        -webkit-transform: translateY(0px);
        -moz-transform: translateY(0px);
    }

    @media screen and (min-width: 1025px) {
        .goods-box .goods-list > a:last-child .goods-item {
            display: none;
        }

        .goods-list a:nth-child(4n+4) .goods-item {
            margin-right: 0;
        }
    }

    @media screen and (min-width: 768px) and (max-width: 1024px) {
        .goods-item {
            width: 32.66%;
        }

        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }

        .goods-item-info {
            display: none;
        }
    }

    @media screen and (min-width: 415px) and (max-width: 768px) {
        .goods-item {
            width: 32.66%;
        }

        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }

        .goods-item-info {
            display: none;
        }
    }

    @media screen and (max-width: 415px) {
        .goods-item {
            width: 32.66%;
        }

        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }

        .goods-item-info {
            display: none;
        }
    }
</style>


<%@ include file="/home/magazine/template/web-top.jsp" %>
<%@ include file="/home/magazine/template/new-header.jsp" %>

<div class="goods-box clearfix">

</div>
<%@ include file="/home/magazine/template/new-foot.jsp" %>

<script>
  var data = [{
    bgImg: "/public/upload/cnt/ad/5fbab122762e682c96ecdcad8cc715e0.jpg",
    itemImg: [
      {
        img: "/public/upload/cnt/ad/50a3bb02593b296444d18a8b2de57ec4.jpg",
        name: "Aokang official men's shoes 2018 spring new 6cm hidden heel business casual shoes new invisible heel fashionable men's shoes",
        price: "31.30",
        url: "https://www.shoestp.com/aokang-official-mens-shoes-2018-spring-new-6cm-hidden-heel-business-casual-shoes-new-invisible-heel-fashionable-mens-shoes_p6207.html"
      },
      {
        img: "/public/upload/cnt/ad/b211fbce9a5506f66a747138422bcc07.jpg",
        name: "Ailaifa new style men leather dress shoes stylish casual big size shoes",
        price: "8.50",
        url: "https://www.shoestp.com/ailaifa-new-style-men-leather-dress-shoes-stylish-casual-big-size-shoes_p6614.html"
      },
      {
        img: "/public/upload/cnt/ad/8736ac306f99bf9a19f435f1831a565e.jpg",
        name: "Ailaifa Casual Mens Shoes With Comfortable Fashion Sneaker",
        price: "9.70",
        url: "https://www.shoestp.com/ailaifa-casual-mens-shoes-with-comfortable-fashion-sneaker_p6612.html"
      },
      {
        img: "/public/upload/cnt/ad/1275756c9a422af74c90c73e5608f28c.jpg",
        name: "Ailaifa casual shoes good for walking men shoes",
        price: "8.40",
        url: "https://www.shoestp.com/ailaifa-casual-shoes-good-for-walking-men-shoes_p6627.html"
      },
      {
        img: "/public/upload/cnt/ad/4e04f16d786365e9b08c98ca40bad27e.jpg",
        name: "Ailaifa Most Popular Soft Leather Comfortable Black Sneakers For Mens",
        price: "10.80",
        url: "https://www.shoestp.com/ailaifa-most-popular-soft-leather-comfortable-black-sneakers-for-mens_p6605.html"
      },
      {
        img: "/public/upload/cnt/ad/b6e7ea492dd5cf2e520597fc207ba2b5.jpg",
        name: "Ailaifa Men's casual shoes with durable men breathable",
        price: "10.50",
        url: "https://www.shoestp.com/ailaifa-mens-casual-shoes-with-durable-men-breathable_p6607.html"
      },
      {
        img: "/public/upload/cnt/ad/762bf06d11a9e1462a38cb705f7b74f3.jpg",
        name: "Ailaifa Lace-up Black Mens Casual Sneaker Shoes",
        price: "9.50",
        url: "https://www.shoestp.com/ailaifa-lace-up-black-mens-casual-sneaker-shoes_p6588.html"
      },
      {
        img: "/public/upload/cnt/ad/8412259258a9d78f4de2676a845f0ca4.jpg",
        name: "Ailaifa Latest design comfortable casual men fashion shoes",
        price: "9.80",
        url: "https://www.shoestp.com/ailaifa-latest-design-comfortable-casual-men-fashion-shoes_p6601.html"
      },
      {
        img: "/public/upload/cnt/ad/618ffaa1f47f50b16bdd28f04b1ff16b.jpg",
        name: "Ailaifa New Arrival Fashion new fancy men casual leather shoes",
        price: "9.60",
        url: "https://www.shoestp.com/ailaifa-new-arrival-fashion-new-fancy-men-casual-leather-shoes_p6630.html"
      },
    ]
  },
    {
      bgImg: "/public/upload/cnt/ad/86455b1ceba01f654331dae2a4a3f6e6.jpg",
      itemImg: [
        {
          img: "/public/upload/cnt/ad/3bbd0535be8f59841af5df4896845bf4.jpg",
          name: "Ailaifa Trendy custom men's casual shoes with lace-up",
          price: "10.00",
          url: "https://www.shoestp.com/ailaifa-trendy-custom-mens-casual-shoes-with-lace-up_p6602.html"
        },
        {
          img: "/public/upload/cnt/ad/95ae758e37e94be909ca67f8429e3c94.jpg",
          name: "Nianyue classical black fashion mens casual sneakers mens walking sport shoes running shoes with microfiber and suede leather",
          price: "9.98",
          url: "https://www.shoestp.com/nianyue-classical-black-fashion-mens-casual-sneakers-mens-walking-sport-shoes-running-shoes-with-microfiber-and-suede-leather_p6358.html"
        },
        {
          img: "/public/upload/cnt/ad/6cb275da293f9713496b2953a8cff5c0.jpg",
          name: "Aokang official men's shoes spring and summer men's leisure breathable mesh hollow shoes men's sports shoes breathable",
          price: "24.10",
          url: "https://www.shoestp.com/aokang-official-mens-shoes-spring-and-summer-mens-leisure-breathable-mesh-hollow-shoes-mens-sports-shoes-breathable_p6197.html"
        },
        {
          img: "/public/upload/cnt/ad/1b4fb0fa237fd9b9f553606ad6ffc7f.jpg",
          name: "Ailaifa slip-on casual leather sneaker shoes",
          price: "9.50",
          url: "https://www.shoestp.com/ailaifa-slip-on-casual-leather-sneaker-shoes_p6587.html"
        },
        {
          img: "/public/upload/cnt/ad/533a70dd080e695a9e6884f68107c788.jpg",
          name: "Juheng Popular Lace Up Casual Injection Mens Shoes Running Shoes",
          price: "3.60",
          url: "https://www.shoestp.com/juheng-popular-lace-up-casual-injection-mens-shoes-running-shoes_p6534.html"
        },
        {
          img: "/public/upload/cnt/ad/eef89d927a9db54892e86d506ecd0ec3.jpg",
          name: "Juheng Good Quality Popular Lace Up Casual pvc Shoes Running Shoes For Men",
          price: "2.90",
          url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pvc-shoes-running-shoes-for-men_p6532.html"
        },
        {
          img: "/public/upload/cnt/ad/7ac4edb211c0435cbf45b0bff2ae91fd.jpg",
          name: "Wish Classics style colors footwear slip-on casual mens shoes canvas",
          price: "5.90",
          url: "https://www.shoestp.com/wish-classics-style-colors-footwear-slip-on-casual-mens-shoes-canvas_p6742.html"
        },
        {
          img: "/public/upload/cnt/ad/18fec0610b51d39e00e4dcde3f0c7363.jpg",
          name: "Nianyue popular mens casual sneakers walking sport shoes mens running shoes with mesh upper",
          price: "9.98",
          url: "https://www.shoestp.com/nianyue-popular-mens-casual-sneakers-walking-sport-shoes-mens-running-shoes-with-mesh-upper_p6364.html"
        },
        {
          img: "/public/upload/cnt/ad/4c9958f0272b4fb1ea9c459c5ff4adf0.jpg",
          name: "Ailaifa new style fashion casual shoes men black shoes",
          price: "9.50",
          url: "https://www.shoestp.com/ailaifa-new-style-fashion-casual-shoes-men-black-shoes_p6586.html"
        },
      ]
    }
  ]
  var imageBaseUrl = "https://image.shoestp.com"
  var item = ''
  $.each(data, function (i, ele) {
    item +=
        '<div class="goods-bgImg">' +
        '<img src="' + imageBaseUrl + ele.bgImg + '" alt="">' +
        '</div>' +
        '<div class="goods-list clearfix">';
    $.each(ele.itemImg, function (i, e) {
      item +=
          '<a href="' + e.url + '" target="_blank">' +
          '<div class="goods-item">' +
          '<img src="' + imageBaseUrl + e.img + '" alt="">' +
          '<div class="goods-item-info">' +
          '<p>' +
          e.name +
          '</p>' +
          '<h3>' +
          '$' + e.price +
          '</h3>' +
          '</div>' +
          '</div>' +
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
