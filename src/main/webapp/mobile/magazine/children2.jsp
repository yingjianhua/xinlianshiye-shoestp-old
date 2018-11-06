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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9e64203766ebbe379961485469e60749.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1550cf910442cb7ff083206862ab22fa.jpg",
                    name: "New design childrens navy pu boots youth girls winter half boots with zipper",
                    price: "7.50",
                    url: "https://www.shoestp.com/new-design-childrens-navy-pu-boots-youth-girls-winter-half-boots-with-zipper_p2099.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ddc761b687e225992f61c19dd1c238d8.jpg",
                    name: "Soft patent leather flexible girls lace up black boots winter season girls martin boots",
                    price: "7.50",
                    url: "https://www.shoestp.com/soft-patent-leather-flexible-girls-lace-up-black-boots-winter-season-girls-martin-boots_p1414.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f24c196536361449bc9644a19f5d7c7a.jpg",
                    name: "Flower printing leather beautiful girl half boots",
                    price: "21.68",
                    url: "https://www.shoestp.com/flower-printing-leather-beautiful-girl-half-boots_p2561.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e3668e62a1493dc2ae52510d41cb2f78.jpg",
                    name: "Girl's Brown Fur Collar Shoes Fashionable National Round-toe Zipper Thick-sole Warm Winter Tall Boots",
                    price: "8.60",
                    url: "https://www.shoestp.com/girls-brown-fur-collar-shoes-fashionable-national-round-toe-zipper-thick-sole-warm-winter-tall-boots_p0065.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ee1862718371dd17496a1bfe28356a82.jpg",
                    name: "Hot selling black beauty girls school shoes factory supply",
                    price: "7.70",
                    url: "https://www.shoestp.com/hot-selling-black-beauty-girls-school-shoes-factory-supply_p2498.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c340e31594d266d9c8b211fc96eeadc.jpg",
                    name: "Supplier direct sell black teenage children kid girls shoes for winter season",
                    price: "7.50",
                    url: "https://www.shoestp.com/supplier-direct-sell-black-teenage-children-kid-girls-shoes-for-winter-season_p2527.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b40329a579010ee330d75e0da2f58af0.jpg",
                    name: "Professional manufacture high quality attractive appearance girls boots",
                    price: "6.90",
                    url: "https://www.shoestp.com/professional-manufacture-high-quality-attractive-appearance-girls-boots_p2403.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/50f279a48a56bb9f297b5a38b8af6fd6.jpg",
                    name: "Girl's Quilted Warm Shoes National Round-toe Zipper Thick-sole Winter Tall Boots",
                    price: "10.00",
                    url: "https://www.shoestp.com/girls-quilted-warm-shoes-national-round-toe-zipper-thick-sole-winter-tall-boots_p0067.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c3fae20a4ce02eccc29dbba3e478c33a.jpg",
                    name: "Women's Cute Vintage Dot Ankle Boots Side Zipper Buckle Shoes Riding Shoes",
                    price: "7.00",
                    url: "https://www.shoestp.com/womens-cute-vintage-dot-ankle-boots-side-zipper-buckle-shoes-riding-shoes_p0103.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/26aca7706b626369de4becdac06e9686.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/236d88b4afea867b4ad2f19afe392d57.jpg",
                    name: "low prices wholesale boys plain white casual shoes children sports running school black shoes",
                    price: "4.50",
                    url: "https://www.shoestp.com/low-prices-wholesale-boys-plain-white-casual-shoes-children-sports-running-school-black-shoes_p1219.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4288de80a04c84bc03efa59d0722eda9.jpg",
                    name: "Boy and Girl's Canvas Shoes Fashionable Camouflage Slip-ons Spring and Autumn Young Casual Sports Sneakers",
                    price: "15.00",
                    url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-camouflage-slip-ons-spring-and-autumn-young-casual-sports-sneakers_p0559.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/734249a32dc0bf53c4a5250d19f2c1bc.jpg",
                    name: "2018 airy cute casual shoes spring kids shoes hottest sneakers boys",
                    price: "6.05",
                    url: "https://www.shoestp.com/2018-airy-cute-casual-shoes-spring-kids-shoes-hottest-sneakers-boys_p1353.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5be5c7dc83584256c1f870c6e012962d.jpg",
                    name: "Navy PU Strap Mesh Trainer Shoes For Boys",
                    price: "4.50",
                    url: "https://www.shoestp.com/navy-pu-strap-mesh-trainer-shoes-for-boys_p2380.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/254b0edcc73584459bfdc901ebafbf70.jpg",
                    name: "2018 slip on lovely washed canvas casual kids shoes",
                    price: "2.80",
                    url: "https://www.shoestp.com/2018-slip-on-lovely-washed-canvas-casual-kids-shoes_p4253.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/83bd4b9411313af0de1f38cab58b0b11.jpg",
                    name: "Boy's Color-matching High-cut Shoes Trendy Casual Winter Flat Sneakers",
                    price: "13.00",
                    url: "https://www.shoestp.com/boys-color-matching-high-cut-shoes-trendy-casual-winter-flat-sneakers_p0689.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b68f4d910c209522e8876f36a4b273dc.jpg",
                    name: "2018 new spring fashion kid canvas shoes children shoes",
                    price: "3.50",
                    url: "https://www.shoestp.com/2018-new-spring-fashion-kid-canvas-shoes-children-shoes_p4240.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ea7eebd7b8cd5f32cd75a1a9920740e5.jpg",
                    name: "2018 new arrival kids breathable sneakers boys casual sneakers",
                    price: "6.05",
                    url: "https://www.shoestp.com/2018-new-arrival-kids-breathable-sneakers-boys-casual-sneakers_p1378.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/45bb73df4c37705a80c8d47d433c6272.jpg",
                    name: "Newest design grey boy anti-skid sports magic type casual shoes",
                    price: "13.00",
                    url: "https://www.shoestp.com/newest-design-grey-boy-anti-skid-sports-magic-type-casual-shoes_p2501.html"
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
