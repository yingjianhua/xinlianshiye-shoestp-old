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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a7a487c2bba0f9ab769bfad7da4f4d0e.jpg",
            itemImg: [{
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/aebe4ca7b1d39792be53915df1cd25dc.jpg",
                    name: "fashionable sexy synthetic sheepskin women blue pure color high heel pumps shoes",
                    price: "12.50",
                    url: "https://www.shoestp.com/ljj025_p2252.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ff08fcbcefbf6c38ff58f5a313b24f26.jpg",
                    name: "2018 blue velvet high heel alibaba china women shoes",
                    price: "22.00",
                    url: "https://www.shoestp.com/ljj014_p2236.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/448f15f7896badf2098a58311c001357.jpg",
                    name: "2018 hot sale new design fashion high heel mature sexy elegant royal blue women dress shoes for women",
                    price: "18.00",
                    url: "https://www.shoestp.com/ljj021_p2246.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/faebe74a5ffa0f1c51b2b35896105fac.jpg",
                    name: " Blue sexy satin fabric material bridal wedding shoes high heels women",
                    price: "26.80",
                    url: "https://www.shoestp.com/ljj011_p2230.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/48b55227b4f494c080d7f436521019e2.jpg",
                    name: "luxury fancy deep blue soft touch low heel dress shoes for women",
                    price: "16.00",
                    url: "https://www.shoestp.com/ljj012_p2232.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/721e5e8a67e83a6bb540e1c589a02b6e.jpg",
                    name: "WOMENS SHOES 2017 BLUE CLOSED FISH TOE PLATFORM PUMPS LUXURY HIGH HEELS LADY SHOES",
                    price: "12.50",
                    url: "https://www.shoestp.com/ljj017_p2239.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/994633b18ddc5554d51f31001ece218e.jpg",
                    name: "2018 new style size 35 - 43 women light blue high heel shoes Made in China wholesale",
                    price: "12.60",
                    url: "https://www.shoestp.com/ljj010_p2229.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/647a0d1a400c6a663606cce79fe3a78d.jpg",
                    name: "shoes women crocodile grain blue women high heel shoes fashion new style high heel shoes",
                    price: "22.77",
                    url: "https://www.shoestp.com/ljj015_p2237.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2dfa8de24730be2eeda4baa3cd3eb6fb.jpg",
                    name: "Wholesale China Shoes Women Fashionable Lady Casual Shoes",
                    price: "12.12",
                    url: "https://www.shoestp.com/ljj020_p2245.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6f5af08446963be576d6af4683098b2.jpg",
            itemImg: [{
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1ca4ec34468baca7f948c4393e0d6e7.jpg",
                    name: "Women's Elastic Ankle Boots Fashionable Color-matching Autumn and Winter Slip-on Flat Casual Boots",
                    price: "6.90",
                    url: "https://www.shoestp.com/womens-elastic-ankle-boots-fashionable-color-matching-autumn-and-winter-slip-on-flat-casual-boots_p0353.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a1938b8187fcc31ec0b23804c4b5b314.jpg",
                    name: "ladies ankle sexy chunk boots women maroon winter sexy suede boots shoes",
                    price: "8.50",
                    url: "https://www.shoestp.com/ladies-ankle-sexy-chunk-boots-women-maroon-winter-sexy-suede-boots-shoes_p1088.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3bb050af0362dee37c1f7276e2a9c455.jpg",
                    name: "Women's Microfiber Ankle Boots with Rivets Side-opening Western Style Spring and Winter Flat Casual Boots",
                    price: "9.50",
                    url: "https://www.shoestp.com/womens-microfiber-ankle-boots-with-rivets-side-opening-western-style-spring-and-winter-flat-casual-boots_p0294.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4540420befb474d2406fe4558e8f0595.jpg",
                    name: "Women's New Shic Buckle Side-zipper Vintage Casual Ankle Boots for Autumn and Winter",
                    price: "10.80",
                    url: "https://www.shoestp.com/womens-new-shic-buckle-side-zipper-vintage-casual-ankle-boots-for-autumn-and-winter_p0086.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b794a011c59986c1dd85ac6dcd708a3f.jpg",
                    name: "Women's Tan Microfiber PU Ankle Boots High Heel Pointed Toe Side-zipper Western Style Metal Buckle Seasons Boots",
                    price: "8.50",
                    url: "https://www.shoestp.com/womens-tan-microfiber-pu-ankle-boots-high-heel-pointed-toe-side-zipper-western-style-metal-buckle-seasons-boots_p0282.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e78ed8d5438fea52372e786bfd2d29b2.jpg",
                    name: "large size ladies ankle boots winter women boots",
                    price: "11.80",
                    url: "https://www.shoestp.com/35-43-large-size-ladies-ankle-boots-winter-women-boots_p1076.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2c912a9dc09de1a79447dbe5df4335cb.jpg",
                    name: "ladies ankle suede boots women sex high heel chunk boots winter shoes",
                    price: "8.80",
                    url: "https://www.shoestp.com/ladies-ankle-suede-boots-women-sex-high-heel-chunk-boots-winter-shoes_p1063.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7f93969473158851e1d9f1cb17dbf27e.jpg",
                    name: "genuine leather china shoes winter boots stiletto high heels for women 2017",
                    price: "45.00",
                    url: "https://www.shoestp.com/8656-genuine-leather-china-shoes-winter-boots-stiletto-high-heels-for-women-2017_p0711.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/19d2b2d814539536c8eddd49eacb1908.jpg",
                    name: "Women's Peep-toe Shoes Fashion Spring and Autumn High Wedge U-cut Shoes",
                    price: "9.80",
                    url: "https://www.shoestp.com/womens-peep-toe-shoes-fashion-spring-and-autumn-high-wedge-u-cut-shoes_p0085.html"
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
