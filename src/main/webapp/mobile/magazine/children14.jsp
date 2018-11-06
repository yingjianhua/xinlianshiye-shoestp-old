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
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/53518493d2aef6f73337bfe722e3110d.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9308ea4a53c21c2e601d9c71f9151bb1.jpg",
                name: "Joyou Kids Glitter Upper Casual Shoes For Girls",
                price: "4.20",
                url: "https://www.shoestp.com/joyou-kids-glitter-upper-casual-shoes-for-girls_p5750.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5b39f3658f6eb760f0ccccb12a425b23.jpg",
                name: "Juheng Good Quality Popular Lace Up Casual PU Shoes For Girl",
                price: "2.90",
                url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pu-shoes-for-girl_p6573.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1bdbde9a2d1b493468dd8649ccfa78ea.jpg",
                name: "Juheng Popular casual pvc children boy sport shoes",
                price: "2.70",
                url: "https://www.shoestp.com/juheng-popular-casual-pvc-children-boy-sport-shoes_p6552.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/177cb6eb7cf4bad8cc39a8ba5da1bf30.jpg",
                name: "Juheng Casual Shoes Lace Up Children Canvas Shoes",
                price: "2.10",
                url: "https://www.shoestp.com/juheng-casual-shoes-lace-up-children-canvas-shoes_p6548.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/52d475c74b83af8057462431b5fe98b.jpg",
                name: "Juheng Fashion Canvas Shoes Slip-on Lazy Boys' Shoes Children Casual Shoes",
                price: "1.80",
                url: "https://www.shoestp.com/juheng-fashion-canvas-shoes-slip-on-lazy-boys-shoes-children-casual-shoes_p6539.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c3a592390022fca7486688fcf44e21af.jpg",
                name: "Juheng Popular Canvas Shoes Lace Up Children Casual Shoes",
                price: "2.00",
                url: "https://www.shoestp.com/juheng-popular-canvas-shoes-lace-up-children-casual-shoes_p6542.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dd548fabbcad7bc50acef248f05956aa.jpg",
                name: "Juheng Popular casual Injection children sport shoes",
                price: "2.20",
                url: "https://www.shoestp.com/juheng-popular-casual-injection-children-sport-shoes_p6538.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a67268e83ee8e76ec26e32d92c152b45.jpg",
                name: "Juheng Popular casual pvc children boy sport shoes",
                price: "2.60",
                url: "https://www.shoestp.com/juheng-popular-casual-pvc-children-boy-sport-shoes_p6536.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ac242dfbab22dbd69ae0105b9b37def6.jpg",
                name: "Juheng Soft Comfortable Kids Canvas Toddler Shoes With Stripe Bowknot",
                price: "1.90",
                url: "https://www.shoestp.com/juheng-soft-comfortable-kids-canvas-toddler-shoes-with-stripe-bowknot_p6545.html"
            },
        ]
    },
    {
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/712b1b8fe7546be2bfbc6077cf817755.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/93d0fd225cc9ac6f5758f9f03989493a.jpg",
                name: "Juheng Good Quality casual PVC children boy sport shoes",
                price: "2.90",
                url: "https://www.shoestp.com/juheng-good-quality-casual-pvc-children-boy-sport-shoes_p6531.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/eadeeb40a566d9a2b21ffabcc51af5b6.jpg",
                name: "Juheng Children Injection Shoes Casual Children Shoes",
                price: "2.20",
                url: "https://www.shoestp.com/juheng-children-injection-shoes-casual-children-shoes_p6530.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/698c2cffc63ee55c8954f6a18681a96.jpg",
                name: "Juheng Children Shoes Casual Children Shoes",
                price: "2.20",
                url: "https://www.shoestp.com/juheng-children-shoes-casual-children-shoes_p6529.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a34e6a9a39c2ff003b7ac37d5a03538d.jpg",
                name: "Juheng Good Quality Popular Lace Up Casual PU Shoes For Girl",
                price: "2.10",
                url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pu-shoes-for-girl_p6578.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/900e316db36b4bba01cc09acaf4aa7a6.jpg",
                name: "Joyou Slip On Girls' Flat Casual Canvas Leisure Shoes",
                price: "4.00",
                url: "https://www.shoestp.com/joyou-slip-on-girls-flat-casual-canvas-leisure-shoes_p5763.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/628ef77185e2f61537cdb05995a6faa9.jpg",
                name: "Children Shoes Girls Flat PU Casual Outdoor Shoes",
                price: "4.00",
                url: "https://www.shoestp.com/children-shoes-girls-flat-pu-casual-outdoor-shoes_p5809.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8522e266a15e92e4d5bda0be7bd3bc57.jpg",
                name: "Juheng Good Quality Popular Lace Up Casual PU Shoes For Girl And Women",
                price: "2.50",
                url: "https://www.shoestp.com/juheng-good-quality-popular-lace-up-casual-pu-shoes-for-girl-and-women_p6574.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4cd84ba7431a0c26f4c6d10591d53d9a.jpg",
                name: "Joyou Custom Printed Comfortable Children Wearing PU Sneakers Shoes For Girls",
                price: "4.00",
                url: "https://www.shoestp.com/joyou-custom-printed-comfortable-children-wearing-pu-sneakers-shoes-for-girls_p5782.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f83ec4f763aafad834ff831d65454a09.jpg",
                name: "Joyou 2018 Simple Casual Child Canvas Shoes",
                price: "3.50",
                url: "https://www.shoestp.com/joyou-2018-simple-casual-child-canvas-shoes_p5756.html"
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
