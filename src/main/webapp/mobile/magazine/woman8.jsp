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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/732fe2e5ead8f637015d68ecbfb25003.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5bec59d626c9e818550c905a48e76ef.jpg",
                    name: "2018 New Summer Style Women shoes sandals comfort Ladies Rhinestone fashion Classic non-slip high quality flat sandals",
                    price: "7.35",
                    url: "https://www.shoestp.com/2018-new-summer-style-women-shoes-sandals-comfort-ladies-rhinestone-fashion-classic-non-slip-high-quality-flat-sandals_p3578.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dbf3e635d3717736d5c5f4ad78b00b4b.jpg",
                    name: "Tracyee New Rope Flat Sandals Wholesale Latest Ladies Sandals Designs 2018 In China",
                    price: "30.99",
                    url: "https://www.shoestp.com/tracyee-new-rope-flat-sandals-wholesale-latest-ladies-sandals-designs-2018-in-china_p3415.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a6b11a9209618e50077b2dfd472a400.jpg",
                    name: "Beach New Design Fashion Girls Flat Shoes With Classic Diamond Flip Shoe Flop",
                    price: "17.99",
                    url: "https://www.shoestp.com/beach-new-design-fashion-girls-flat-shoes-with-classic-diamond-flip-shoe-flop_p3394.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/25bcfe78344333b01d22cf26211cc279.jpg",
                    name: "New Coming Small Diamond Pearls Little Girls Flat Sandal Flop Flip Shoe",
                    price: "17.99",
                    url: "https://www.shoestp.com/new-coming-small-diamond-pearls-little-girls-flat-sandal-flop-flip-shoe_p3393.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4bdf27439b5db9404b3b4ce554b977b1.jpg",
                    name: "Lady sandal wholesale 2018 latest low price flat ladies fancy woman sandal",
                    price: "3.00",
                    url: "https://www.shoestp.com/lady-sandal-wholesale-2018-latest-low-price-flat-ladies-fancy-woman-sandal_p1744.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9c597a14f95908e08810c2da2df02351.jpg",
                    name: "wholesale custom fit flip flop slipper, colorful women beach Rubber china",
                    price: "12.00",
                    url: "https://www.shoestp.com/wholesale-custom-fit-flip-flop-slipper-colorful-women-beach-rubber-china_p1204.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b3b84c2e63543d980527121aba6579f8.jpg",
                    name: "Hot selling new design low price summer lady fancy sandal pu woman sandal",
                    price: "3.00",
                    url: "https://www.shoestp.com/hot-selling-new-design-low-price-summer-lady-fancy-sandal-pu-woman-sandal_p1737.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e2b36cf45a6a07fd99ce0e298904954f.jpg",
                    name: "women shoes korean style flat comfortable women sandals",
                    price: "3.00",
                    url: "https://www.shoestp.com/women-shoes-korean-style-flat-comfortable-women-sandals_p1664.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/184cb6b99f145a3d6d44838a60852cc4.jpg",
                    name: "Cheap Wholesale Leather Flip Flop Ladies Fancy Flat Sandal Women",
                    price: "24.99",
                    url: "https://www.shoestp.com/cheap-wholesale-leather-flip-flop-ladies-fancy-flat-sandal-women_p3362.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/406b2485a499fb022522589897d93f6f.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1eaba652b21b2bdd877edb24676cff6a.jpg",
                    name: "Lady flat shoes 2018 new model beach women sandals",
                    price: "3.00",
                    url: "https://www.shoestp.com/lady-flat-shoes-2018-new-model-beach-women-sandals_p1707.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/593733c83ba53937acc3965034e89b48.jpg",
                    name: "2018 summer fashion design women sandals ladies flip flops",
                    price: "3.00",
                    url: "https://www.shoestp.com/2018-summer-fashion-design-women-sandals-ladies-flip-flops_p1659.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4ff72c03f8bcc21dc13a9f845ca31220.jpg",
                    name: "Women's Crystal Trim Sandals Wrinkled PU Summer Crucial Toe Slip-ons High Wedge Casual Loafer",
                    price: "5.00",
                    url: "https://www.shoestp.com/womens-crystal-trim-sandals-wrinkled-pu-summer-crucial-toe-slip-ons-high-wedge-casual-loafer_p0370.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/838c1b44a7a47078372318709d23af19.jpg",
                    name: "Women's Simple Hollow Loafer Summer Crucial Toe Wedge Slip-ons Casual Sandals",
                    price: "5.00",
                    url: "https://www.shoestp.com/womens-simple-hollow-loafer-summer-crucial-toe-wedge-slip-ons-casual-sandals_p0367.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d668a11ef6449db5c671209e1db28b03.jpg",
                    name: "Women's Fashionable Shoes Crystal Trim Peep Toe Slip-on Simple High-heeled Single Shoes",
                    price: "9.50",
                    url: "https://www.shoestp.com/womens-fashionable-shoes-crystal-trim-peep-toe-slip-on-simple-high-heeled-single-shoes_p0642.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/7f875b6690a571a381b87c48be1bc7f9.jpg",
                    name: "Women's Fashion Sandals Shining Leather Straps and Buckles Summer Chunky High Heeled Simple Shoes",
                    price: "49.00",
                    url: "https://www.shoestp.com/womens-fashion-sandals-shining-leather-straps-and-buckles-summer-chunky-high-heeled-simple-shoes_p0576.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f795483889828abfaa74389ce04a9f8e.jpg",
                    name: "Women's Pointed Toe Shoes Fashionable Slip-on Western Simple Summer New High Heel Sandals",
                    price: "8.80",
                    url: "https://www.shoestp.com/womens-pointed-toe-shoes-fashionable-slip-on-western-simple-summer-new-high-heel-sandals_p0695.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/416f7b89982ddbfe979b9e6dc46a27fd.jpg",
                    name: "Women's Simple Overlapping Straps Slippers Summer Thick Wedge Casual Slippers",
                    price: "6.90",
                    url: "https://www.shoestp.com/womens-simple-overlapping-straps-slippers-summer-thick-wedge-casual-slippers_p0081.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a4dda44f3a41cc186ee340797a15ae45.jpg",
                    name: "ladies fancy flat sandal",
                    price: "4.25",
                    url: "https://www.shoestp.com/ladies-fancy-flat-sandal_p1001.html"
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
