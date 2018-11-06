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
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/19d881d537ce34a8cceaea0d3c0a8104.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/594b8eb728f9488cab4ea5198957057a.jpg",
                  name: "women casual leather sneaker shoe supplier",
                  price: "4.00",
                  url: "https://www.shoestp.com/women-casual-leather-sneaker-shoe-supplier_p0928.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/208a4233e98ec71f00b178e29186780a.jpg",
                  name: "Dabowen Women's Floral Print Loafers Fashionable Slip-ons Young Trend Spring and Autumn Casual Flat Sneakers",
                  price: "8.00",
                  url: "https://www.shoestp.com/womens-floral-print-loafers-fashionable-slip-ons-young-trend-spring-and-autumn-casual-flat-sneakers_p0455.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/234d664d6f6f7ff28f1a0da7bea1e96b.jpg",
                  name: "Dabowen Latest Ladies Fashion Color Matching White Shoes Lace-up Casual Flat Loafer for Spring and Autumn",
                  price: "7.00",
                  url: "https://www.shoestp.com/latest-ladies-fashion-color-matching-white-shoes-lace-up-casual-flat-loafer-for-spring-and-autumn_p0452.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/82ad23c0614a232b8bc5da7b51b9e79c.jpg",
                  name: "AIBIXI New Arrival Cheap African Pink Women Anti-Slip Casual Canvas Shoes",
                  price: "5.00",
                  url: "https://www.shoestp.com/aibixi-new-arrival-cheap-african-pink-women-anti-slip-casual-canvas-shoes_p3787.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fe406372b5ecea6e840894b3f282200d.jpg",
                  name: "2017 Fashion Vulcanized Shoes Women, Slip On Casual Shoes For Women, Women Canvas Shoes 2017",
                  price: "6.55",
                  url: "https://www.shoestp.com/2017-fashion-vulcanized-shoes-women-slip-on-casual-shoes-for-women-women-canvas-shoes-2017_p0934.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6c3dc92191e5d3a0890ed9ac582bf9e.jpg",
                  name: "New Spring and Summer Shoes Women Flat Leather Canvas Shoes Female Black PU Board Shoes Casual Shoes Female",
                  price: "4.30",
                  url: "https://www.shoestp.com/new-spring-and-summer-shoes-women-flat-leather-canvas-shoes-female-black-pu-board-shoes-casual-shoes-female_p3558.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a8c776a4def26b8b4988b32e936cdb6b.jpg",
                  name: "2018 Wholesale Ladies Black Canvas Lace up Shoes Girls women Casual Canvas Sneakers",
                  price: "3.50",
                  url: "https://www.shoestp.com/2018-wholesale-ladies-black-canvas-lace-up-shoes-girls-casual-canvas-sneakers-womens_p3784.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/672761d0b71336979a32ac478dfac307.jpg",
                  name: "Slip On PU Students Black Canvas Shoes Woman 2018 New Spring Casual Shoes Woman All-Match Zapatos Mujer Chaussure Femme",
                  price: "4.70",
                  url: "https://www.shoestp.com/slip-on-pu-students-black-canvas-shoes-woman-2018-new-spring-casual-shoes-woman-all-match-zapatos-mujer-chaussure-femme_p3525.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cfa950e83f7c20cd22db6f7cb12b07dc.jpg",
                  name: "Female-shoes women canvas shoes girls slipony flat shoes femal shoes loafers casual footwear",
                  price: "4.30",
                  url: "https://www.shoestp.com/female-shoes-women-canvas-shoes-girls-slipony-flat-shoes-femal-shoes-loafers-casual-footwear_p3582.html"
              },
          ]
      },
      {
          bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1b180a715ea0ee0f7751d9492ec4f167.jpg",
          itemImg: [
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6e8cb78e2d50836ee61323e88ca6035c.jpg",
                  name: "Ladies fancy high heels shoes ankle strappy pointed toe high quality patent leather comfort heel pumps for women",
                  price: "18.99",
                  url: "https://www.shoestp.com/ladies-fancy-high-heels-shoes-ankle-strappy-pointed-toe-high-quality-patent-leather-comfort-heel-pumps-for-women_p3744.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/90d26a96d0a23176d5e6c935a05f851c.jpg",
                  name: "Women's shoes cross-belt single shoes blue silk silk satin heels with high heels and European and American style",
                  price: "13.45",
                  url: "https://www.shoestp.com/womens-shoes-cross-belt-single-shoes-blue-silk-silk-satin-heels-with-high-heels-and-european-and-american-style_p2287.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/68b159cdc664ed3cbea9a87018bff82d.jpg",
                  name: "Personalized shallow mouth custom dark and light blue denim sandals pointed toe shoes womens high heels",
                  price: "29.91",
                  url: "https://www.shoestp.com/personalized-shallow-mouth-custom-dark-and-light-blue-denim-sandals-pointed-toe-shoes-womens-high-heels_p2284.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bde62ec7d1014a077bafc284079256b6.jpg",
                  name: "2018 hot sale new design fashion high heel mature sexy elegant royal blue women dress shoes for women",
                  price: "18.00",
                  url: "https://www.shoestp.com/2018-hot-sale-new-design-fashion-high-heel-mature-sexy-elegant-royal-blue-women-dress-shoes-for-women_p2246.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/26ae7d045f1454c1b1c770deb7d35568.jpg",
                  name: "Ladies Women High Heel China Wholesale Shoes Pointed Toe Leather Slip On Design Women Stiletto Dress Pump Shoes",
                  price: "29.99",
                  url: "https://www.shoestp.com/ladies-women-high-heel-china-wholesale-shoes-pointed-toe-leather-slip-on-design-women-stiletto-dress-pump-shoes_p3756.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5c6e0b9c8b4eecd7872393628e47f200.jpg",
                  name: "Latest Girls Shoes 2018 Lace Up Detail Pointed Toe Stilettos Heel Shinning High Quality Pump",
                  price: "30.99",
                  url: "https://www.shoestp.com/latest-girls-shoes-2018-lace-up-detail-pointed-toe-stilettos-heel-shinning-high-quality-pump_p3769.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8ced677e25ee5c46bb422a787143bbaa.jpg",
                  name: "Wholesale China Leather Shoes Factory Slip On Design Pointed Toe Radiant Dress Shoes Women Ladies Heel",
                  price: "25.99",
                  url: "https://www.shoestp.com/wholesale-china-leather-shoes-factory-slip-on-design-pointed-toe-radiant-dress-shoes-women-ladies-heel_p3749.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/75db8ad51661d49e84e507079bbc5a31.jpg",
                  name: "Gracozy Fashion Style Hot Lady Shoes Pointed Toe Dress Shoes High Heels Sexy Pumps",
                  price: "19.99",
                  url: "https://www.shoestp.com/gracozy-fashion-style-hot-lady-shoes-pointed-toe-dress-shoes-high-heels-sexy-pumps_p3767.html"
              },
              {
                  img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/83c84a5e62b06515794e407727e03134.jpg",
                  name: "China women lady casual shoe factory fashion pointed women shoes pumps",
                  price: "12.00",
                  url: "https://www.shoestp.com/china-women-lady-casual-shoe-factory-fashion-pointed-women-shoes-pumps_p2282.html"
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
