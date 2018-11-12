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
    bgImg: "/public/upload/cnt/ad/8fb85f0a8b8d0c9de482007be1d04dc6.jpg",
    itemImg: [
      {
        img: "/public/upload/cnt/ad/96887f98be9c1ced79c223f44addc1fa.jpg",
        name: "Boy and Girl's Canvas Shoes Fashionable Colour-matching Velcro Spring and Autumn Casual Sports Sneakers",
        price: "16.00",
        url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-colour-matching-velcro-spring-and-autumn-casual-sports-sneakers_p0554.html"
      },
      {
        img: "/public/upload/cnt/ad/18baff617b6bf01a3ddd359ac78874ad.jpg",
        name: "Kangfu Boy Uniforms Shoes Back To School Shoes Cheap Slip On Casual Shoes",
        price: "10.20",
        url: "https://www.shoestp.com/kangfu-boy-uniforms-shoes-back-to-school-shoes-cheap-slip-on-casual-shoes_p6341.html"
      },
      {
        img: "/public/upload/cnt/ad/6357c62b78060511013d2f8c9939adde.jpg",
        name: "Joyou Children Boys Shoes Knee-high PU Casual Shoes",
        price: "4.50",
        url: "https://www.shoestp.com/joyou-children-boys-shoes-knee-high-pu-casual-shoes_p5806.html"
      },
      {
        img: "/public/upload/cnt/ad/cc72214dac0f0ce4d05fbeac9732b742.jpg",
        name: "Kangfu Boy Casual Footwear Outdoor Or Walking Genuine Pu Loafer Shoes",
        price: "9.10",
        url: "https://www.shoestp.com/kangfu-boy-casual-footwear-outdoor-or-walking-genuine-pu-loafer-shoes_p6330.html"
      },
      {
        img: "/public/upload/cnt/ad/4db2185163232152a4d4a7f1f46bb2e5.jpg",
        name: "Joyou Children Canvas Shoes Magic Stickers For Boys",
        price: "4.00",
        url: "https://www.shoestp.com/joyou-children-canvas-shoes-magic-stickers-for-boys_p5760.html"
      },
      {
        img: "/public/upload/cnt/ad/bfe074c7024fce02cc3927fc13f210b.jpg",
        name: "Joyou Fashion Canvas Shoes Slip-on Lazy Boys' Shoes",
        price: "4.00",
        url: "https://www.shoestp.com/joyou-fashion-canvas-shoes-slip-on-lazy-boys-shoes_p5774.html"
      },
      {
        img: "/public/upload/cnt/ad/41860b9b70a3103f6a45a584e75ccd46.jpg",
        name: "Joyou Graffiti Canvas Shoes Slip-on Boys' Shoes",
        price: "3.80",
        url: "https://www.shoestp.com/joyou-graffiti-canvas-shoes-slip-on-boys-shoes_p5772.html"
      },
      {
        img: "/public/upload/cnt/ad/70f411d3a62a776684342fdf7917d358.jpg",
        name: "Joyou Light Young Kids Casual Shoes PU Sport Boys Shoes",
        price: "4.20",
        url: "https://www.shoestp.com/joyou-light-young-kids-casual-shoes-pu-sport-boys-shoes_p5792.html"
      },
      {
        img: "/public/upload/cnt/ad/1d3e177f352321c15cde4c4edf07ef1f.jpg",
        name: "Joyou 2018 Hot Sale Durable Boys Buckle Strap Casual Canvas Shoes",
        price: "4.00",
        url: "https://www.shoestp.com/joyou-2018-hot-sale-durable-boys-buckle-strap-casual-canvas-shoes_p5783.html"
      },
    ]
  },
    {
      bgImg: "/public/upload/cnt/ad/228d5f60ac76d10afcb6232eb608fa96.jpg",
      itemImg: [
        {
          img: "/public/upload/cnt/ad/20c0ab6bc166561955f84114b354e108.jpg",
          name: "Best price fashion style winter PU red-brown girls dress shoes",
          price: "6.70",
          url: "https://www.shoestp.com/best-price-fashion-style-winter-pu-red-brown-girls-dress-shoes_p2488.html"
        },
        {
          img: "/public/upload/cnt/ad/6846760f3832453fa068feb57931f80d.jpg",
          name: "Boy and Girl's Sports Shoes Fashionable Sneakers Velcro Trendy Casual Flat Children Shoes",
          price: "13.00",
          url: "https://www.shoestp.com/boy-and-girls-sports-shoes-fashionable-sneakers-velcro-trendy-casual-flat-children-shoes_p0673.html"
        },
        {
          img: "/public/upload/cnt/ad/db6fc5f8d9d72549c81f80c7c4211e26.jpg",
          name: "Boy and Girl's Fashionable White Shoes Color-matching Velcro Trendy Casual Sports Flat Shoes",
          price: "13.00",
          url: "https://www.shoestp.com/boy-and-girls-fashionable-white-shoes-color-matching-velcro-trendy-casual-sports-flat-shoes_p0675.html"
        },
        {
          img: "/public/upload/cnt/ad/550d8779ed2806d9ee139b14eceaeee4.jpg",
          name: "Comfortable jean pattern boy walking shoes with zipper",
          price: "4.56",
          url: "https://www.shoestp.com/comfortable-jean-pattern-boy-walking-shoes-with-zipper_p2474.html"
        },
        {
          img: "/public/upload/cnt/ad/e58302e259037a9ef045821d9e59de92.jpg",
          name: "latest custom classic kids canvas shoes",
          price: "4.00",
          url: "https://www.shoestp.com/latest-custom-classic-kids-canvas-shoes_p1583.html"
        },
        {
          img: "/public/upload/cnt/ad/92edfc6631ae63d45f10bc090dfb8114.jpg",
          name: "High quality color harmony cheap casual girls shoes for children",
          price: "6.10",
          url: "https://www.shoestp.com/high-quality-color-harmony-cheap-casual-girls-shoes-for-children_p2420.html"
        },
        {
          img: "/public/upload/cnt/ad/4d2fd67f78421c162ac00205e8561aff.jpg",
          name: "Latest fashion girls lace up low top casual shoes pink glitter sneakers for girls",
          price: "6.25",
          url: "https://www.shoestp.com/latest-fashion-girls-lace-up-low-top-casual-shoes-pink-glitter-sneakers-for-girls_p1250.html"
        },
        {
          img: "/public/upload/cnt/ad/f1f9d0f20180640dd89fa0ab03661be3.jpg",
          name: "Boy and Girl's Canvas Shoes Fashionable Camouflage Slip-ons Spring and Autumn Young Casual Sports Sneakers",
          price: "15.00",
          url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-camouflage-slip-ons-spring-and-autumn-young-casual-sports-sneakers_p0559.html"
        },
        {
          img: "/public/upload/cnt/ad/c07c37796a54898fdc5d9f015d0cf70e.jpg",
          name: "Boy and Girl's Color-matching Shoes Velcro Trendy Casual Flat Sneakers",
          price: "13.00",
          url: "https://www.shoestp.com/boy-and-girls-color-matching-shoes-velcro-trendy-casual-flat-sneakers_p0685.html"
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
