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
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8c2cfda45c460f0076c379110a05ce08.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/42be4749b8d9c67f55a19b1a62530d7.jpg",
                name: "Boy's High-cut Shoes Fashionable Velcro Casual Winter New Flat Baby Shoes",
                price: "13.00",
                url: "https://www.shoestp.com/boys-high-cut-shoes-fashionable-velcro-casual-winter-new-flat-baby-shoes_p0691.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e41356df5047885138548db325d7c1ed.jpg",
                name: "Boy's Color-matching High-cut Shoes Trendy Casual Winter Flat Sneakers",
                price: "13.00",
                url: "https://www.shoestp.com/boys-color-matching-high-cut-shoes-trendy-casual-winter-flat-sneakers_p0689.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/15bb5816079f961c1b0c97e13ed169f1.jpg",
                name: "Boy's High-cut Shoes Velcro Trendy Casual Flat Sneakers",
                price: "13.00",
                url: "https://www.shoestp.com/boys-high-cut-shoes-velcro-trendy-casual-flat-sneakers_p0688.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e05264756286375fc1c6e56a19abe1a8.jpg",
                name: "Boy's Color-matching Shoes Velcro Casual Trendy Flat Sneakers",
                price: "13.00",
                url: "https://www.shoestp.com/boys-color-matching-shoes-velcro-casual-trendy-flat-sneakers_p0687.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cc81c2fd6636062938047dfee1fad9d6.jpg",
                name: "Boy and Girl's Canvas Shoes Fashionable High-cut Elastic Spring and Autumn Young Casual Embroidery Sneakers",
                price: "16.00",
                url: "https://www.shoestp.com/boy-and-girls-canvas-shoes-fashionable-high-cut-elastic-spring-and-autumn-young-casual-embroidery-sneakers_p0556.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/87f610ff6723038ec42e31160ee482b2.jpg",
                name: "Juyue Fashion Glitter PU Sneaker Shoes With Lace-up Casual Boys' Shoes",
                price: "4.50",
                url: "https://www.shoestp.com/juyue-fashion-glitter-pu-sneaker-shoes-with-lace-up-casual-boys-shoes_p5799.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cc21beb5871d105748d56d98f711bb75.jpg",
                name: "Boy and Girl's Fashionable Shoes High-cut Young Velcro Elastic Autumn and Winter Casual Sports Sneakers",
                price: "16.00",
                url: "https://www.shoestp.com/boy-and-girls-fashionable-shoes-high-cut-young-velcro-elastic-autumn-and-winter-casual-sports-sneakers_p0561.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/ea10ea7dcf9ef31f8814cd98724fd52d.jpg",
                name: "Juyue Fashion High Upper Boys' Casual Shoes",
                price: "4.00",
                url: "https://www.shoestp.com/juyue-fashion-high-upper-boys-casual-shoes_p5786.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/af3fd3580ff9de017e5e1db6f3c1d636.jpg",
                name: "Boy and Girl's Shoes Floral Embroidery High-cut Velcro Elastic Casual Flat Children Shoes",
                price: "12.00",
                url: "https://www.shoestp.com/boy-and-girls-shoes-floral-embroidery-high-cut-velcro-elastic-casual-flat-children-shoes_p0684.html"
            },
        ]
    },
    {
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3400ef31444f76ea2aa86554de7c1992.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7f08cda8bd0cfb37f2ae973f1e4ba58b.jpg",
                name: "Juyue New Model In Autumn & Winter PU Upper Casual Boys' Shoes",
                price: "5.00",
                url: "https://www.shoestp.com/juyue-new-model-in-autumn-amp-winter-pu-upper-casual-boys-shoes_p5751.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e4eda82a23bfff4c4e03736318750ae9.jpg",
                name: "Juyue High Ankle Design Fashion Casual Boys' Canvas Shoes",
                price: "5.00",
                url: "https://www.shoestp.com/juyue-high-ankle-design-fashion-casual-boys-canvas-shoes_p5765.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a473f13fdcfc175b97effd9e72c1b8.jpg",
                name: "Children's Woven Oxford Ankle Shoes Fashionable Side Zipper Seasons Lacing-up Flat Casual Shoes",
                price: "2.90",
                url: "https://www.shoestp.com/childrens-woven-oxford-ankle-shoes-fashionable-side-zipper-seasons-lacing-up-flat-casual-shoes_p0338.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5573a9a555f2bdb309eded1d161ecf40.jpg",
                name: "Juyue Comfort Lace Up Boys Side Zipper High-upper Shoes",
                price: "4.50",
                url: "https://www.shoestp.com/juyue-comfort-lace-up-boys-side-zipper-high-upper-shoes_p5802.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cbef42a291f89fad73b691cee1f15dd3.jpg",
                name: "Flexible and soft kids custom pu sneakers boys lace up high top sneaker shoes",
                price: "6.15",
                url: "https://www.shoestp.com/flexible-and-soft-kids-custom-pu-sneakers-boys-lace-up-high-top-sneaker-shoes_p1299.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1f98c4b12371dee31526a2c2c48733de.jpg",
                name: "Fashionable and soft unisex kids casual sneakers kids custom lace up high top pu sneakers",
                price: "6.15",
                url: "https://www.shoestp.com/fashionable-and-soft-unisex-kids-casual-sneakers-kids-custom-lace-up-high-top-pu-sneakers_p1287.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/cf73a7a6489f419bf8f47e2283e5c87b.jpg",
                name: "Juyue High Upper Girls' Shoes Pink PU Casual Shoes",
                price: "4.50",
                url: "https://www.shoestp.com/juyue-high-upper-girls-shoes-pink-pu-casual-shoes_p5762.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8a3425ac9ac4ce9622bc01088b83f43c.jpg",
                name: "Boy and Girl's High-cut Shoes Metal Zipper Trim Velcro Western Style Casual Flat Sneakers",
                price: "13.00",
                url: "https://www.shoestp.com/boy-and-girls-high-cut-shoes-metal-zipper-trim-velcro-western-style-casual-flat-sneakers_p0686.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6963492d736fbe9642f731ea055c69f6.jpg",
                name: "Juyue Children Boys Shoes Knee-high PU Casual Shoes",
                price: "4.50",
                url: "https://www.shoestp.com/juyue-children-boys-shoes-knee-high-pu-casual-shoes_p5806.html"
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
