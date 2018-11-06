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
         bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/86c7a599c030698c3710f1d9f17b0fde.jpg",
         itemImg: [
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c8601485f010e3f960ccf886409889ab.jpg",
                 name: "Fashionable boys lace up high cut sneakers warm winter boys sport sneaker boots",
                 price: "6.95",
                 url: "https://www.shoestp.com/fashionable-boys-lace-up-high-cut-sneakers-warm-winter-boys-sport-sneaker-boots_p2067.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3fc02268b34b29675500063070bd4495.jpg",
                 name: "Kangfu Navy Pu Leather High Ankle Boy Winter Boots",
                 price: "10.50",
                 url: "https://www.shoestp.com/kangfu-navy-pu-leather-high-ankle-boy-winter-boots_p6316.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/321618e1753f83acb14dd0fa67cd27b9.jpg",
                 name: "Winter season kids fashion flexible ankle fur boots boys outdoor work boots",
                 price: "7.95",
                 url: "https://www.shoestp.com/winter-season-kids-fashion-flexible-ankle-fur-boots-boys-outdoor-work-boots_p2061.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/77e5bf78a03b7e0bcf0dcef7d39ef4f9.jpg",
                 name: "Kangfu Leather Boy Lace-Up  Work Shoes Pu Upper With Zipper",
                 price: "10.50",
                 url: "https://www.shoestp.com/kangfu-leather-boy-lace-up-work-shoes-pu-upper-with-zipper_p6314.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/952dbeb78094fd59568b5282d08e888.jpg",
                 name: "Kangfu Pu Leather High Ankle Boys Side Zipper Winter Boots",
                 price: "10.20",
                 url: "https://www.shoestp.com/kangfu-pu-leather-high-ankle-boys-side-zipper-winter-boots_p6311.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/eddd10bbb0c8183f2bc6b548fda1bc50.jpg",
                 name: "Kangfu Boy Winter High Ankle With Double Hook-Loop Boots",
                 price: "10.10",
                 url: "https://www.shoestp.com/kangfu-boy-winter-high-ankle-with-double-hook-loop-boots_p6308.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9048d4a3593fa026015c26c99c6f3a72.jpg",
                 name: "Kangfu Multi-funtion High toe Leather Steel Toe Cap Safety boy Shoes",
                 price: "10.80",
                 url: "https://www.shoestp.com/kangfu-multi-funtion-high-toe-leather-steel-toe-cap-safety-boy-shoes_p6306.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d8b4735437ea40396a2b54f6b805e788.jpg",
                 name: "Kangfu Durable Footwear Joggers With Leather Steel Toe Boy Safty Boots",
                 price: "10.80",
                 url: "https://www.shoestp.com/kangfu-durable-footwear-joggers-with-leather-steel-toe-boy-safty-boots_p6267.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/776e7604622823dc4351e5cd8b18e389.jpg",
                 name: "Breathable and warm kids ankle boots boys high top winter boots",
                 price: "6.99",
                 url: "https://www.shoestp.com/breathable-and-warm-kids-ankle-boots-boys-high-top-winter-boots_p1387.html"
             },
         ]
     },
     {
         bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/265162fcfc600fb3f3aafcb743a3d8d9.jpg",
         itemImg: [
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b6ad99560eab68306f4735849520174f.jpg",
                 name: "Fashionable kids black pu sneaker shoes boys high cut sneakers with zipper",
                 price: "6.95",
                 url: "https://www.shoestp.com/fashionable-kids-black-pu-sneaker-shoes-boys-high-cut-sneakers-with-zipper_p1303.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b13ba14ee1ad5b91dbe89f52019dad87.jpg",
                 name: "2017 new arrival boys fancy campus boots fashion simple hot design soft sole boys fancy campus boots",
                 price: "7.15",
                 url: "https://www.shoestp.com/2017-new-arrival-boys-fancy-campus-boots-fashion-simple-hot-design-soft-sole-boys-fancy-campus-boots_p1529.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d606ecdb166d6edd990c2c466febc584.jpg",
                 name: "Winter season girls pu leather boots nonslip kids girls boots with zipper",
                 price: "7.50",
                 url: "https://www.shoestp.com/winter-season-girls-pu-leather-boots-nonslip-kids-girls-boots-with-zipper_p1443.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/1dd9d5acf2acab4555fa5dbce106a445.jpg",
                 name: "2017 new model young fashion school boys boots fancy handsome hot design cheap young fashion school boys boots",
                 price: "6.55",
                 url: "https://www.shoestp.com/2017-new-model-young-fashion-school-boys-boots-fancy-handsome-hot-design-cheap-young-fashion-school-boys-boots_p1559.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/630700d7b972eb1cf2132011ada01f1e.jpg",
                 name: "New model baby girl ankle casual boots stylish fancy good quality soft sole baby girl ankle casual boots",
                 price: "6.55",
                 url: "https://www.shoestp.com/new-model-baby-girl-ankle-casual-boots-stylish-fancy-good-quality-soft-sole-baby-girl-ankle-casual-boots_p1784.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4e8cfcb517ed4a1e61bafc945da3122.jpg",
                 name: "New model custom combat kids ankle boots trendy toddler casual printed flower custom combat kids ankle boots",
                 price: "6.55",
                 url: "https://www.shoestp.com/new-model-custom-combat-kids-ankle-boots-trendy-toddler-casual-printed-flower-custom-combat-kids-ankle-boots_p1794.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/283a6e593b2b740508112084e0c0896e.jpg",
                 name: "2018 new design kids casual ankle boots fashion boys work boots",
                 price: "5.95",
                 url: "https://www.shoestp.com/2018-new-design-kids-casual-ankle-boots-fashion-boys-work-boots_p1401.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/41de22f63f56c12e8982fb9a7c5c8451.jpg",
                 name: "Fashionable and soft kids ankle boots boys black pu boots with magic tape",
                 price: "7.25",
                 url: "https://www.shoestp.com/fashionable-and-soft-kids-ankle-boots-boys-black-pu-boots-with-magic-tape_p1451.html"
             },
             {
                 img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e7c92e532ca6ba312d7a41032c9694bc.jpg",
                 name: "High quality wholesale children shoes boys kids sport for children",
                 price: "8.50",
                 url: "https://www.shoestp.com/high-quality-wholesale-children-shoes-boys-kids-sport-for-children_p2516.html"
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
