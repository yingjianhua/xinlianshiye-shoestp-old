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
       bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e02617325341bd9979b347869e04fda2.jpg",
       itemImg: [
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d28e997833151c15b088323d99d06f72.jpg",
               name: "Cheap running sneakers mesh sports shoes for children with custom logo",
               price: "11.80",
               url: "https://www.shoestp.com/cheap-running-sneakers-mesh-sports-shoes-for-children-with-custom-logo_p3886.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/77469044188d9726ce4f926d17f2de81.jpg",
               name: "Hot sale high quality pretty casual sport shoes new design top fashion cartoon kid air sneaker manufacturers",
               price: "12.60",
               url: "https://www.shoestp.com/hot-sale-high-quality-pretty-casual-sport-shoes-new-design-top-fashion-cartoon-kid-air-sneaker-manufacturers_p3954.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/2ddc67e9de3db680fac52e8fd1d502af.jpg",
               name: "Wholesale high quality lightweight sneaker sports training running air shoes for kids",
               price: "12.60",
               url: "https://www.shoestp.com/wholesale-high-quality-lightweight-sneaker-sports-training-running-air-shoes-for-kids_p3949.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d9732b5c3e55740d3e4fd4428d99ea59.jpg",
               name: "Wholesale casual fitness gym walking shoes breathable mesh children shoes",
               price: "8.50",
               url: "https://www.shoestp.com/wholesale-casual-fitness-gym-walking-shoes-breathable-mesh-children-shoes_p3959.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5fd80bf6bc5151b023b1e391bf3ba5d.jpg",
               name: "Low MOQ high quality soccer boots TF students outdoor football shoes for kids",
               price: "12.36",
               url: "https://www.shoestp.com/low-moq-high-quality-soccer-boots-tf-students-outdoor-football-shoes-for-kids_p4074.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/11a14e581efd3d653ecc43f2c40fad36.jpg",
               name: "new style kids led flashing funny night casual shoes,wholesale breathable kids running shoes",
               price: "15.90",
               url: "https://www.shoestp.com/new-style-kids-led-flashing-funny-night-casual-shoeswholesale-breathable-kids-running-shoes_p4108.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/33d33ee77ccb3d7e020079dfb8c586cb.jpg",
               name: "Sports style boy autumn mixture color magic type sneakers",
               price: "15.00",
               url: "https://www.shoestp.com/sports-style-boy-autumn-mixture-color-magic-type-sneakers_p2433.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c68b7e703929a76241e9d2d4255d0070.jpg",
               name: "New design Soft Gorgeous Color Tennis light up led shoes for kid",
               price: "14.30",
               url: "https://www.shoestp.com/new-design-soft-gorgeous-color-tennis-light-up-led-shoes-for-kid_p4117.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/fbecedc575ea71a20d37710672d5fc25.jpg",
               name: "Kids kinderen schoenen loopschoenen",
               price: "12.80",
               url: "https://www.shoestp.com/kids-kinderen-schoenen-loopschoenen_p3937.html"
           },
       ]
   },
   {
       bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/df8a5693f994bbedae2c588d0e9031a1.jpg",
       itemImg: [
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bbe75016980259b9482091dea8d0460b.jpg",
               name: "Supplier direct selling new design children kids shoes for boy",
               price: "8.90",
               url: "https://www.shoestp.com/supplier-direct-selling-new-design-children-kids-shoes-for-boy_p2454.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c6f339a2620f10af80f05073ad3697a9.jpg",
               name: "2018 Beautiful appearance wear resisting winter boys shoes kids casual shoes",
               price: "9.00",
               url: "https://www.shoestp.com/2018-beautiful-appearance-wear-resisting-winter-boys-shoes-kids-casual-shoes_p2472.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/26c324dec25f2f08706fb5ea05685597.jpg",
               name: "low prices wholesale boys plain white casual shoes children sports running school black shoes",
               price: "4.50",
               url: "https://www.shoestp.com/low-prices-wholesale-boys-plain-white-casual-shoes-children-sports-running-school-black-shoes_p1219.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/77aa12250a1e73eb6f4abd5183eb0595.jpg",
               name: "Fashion Stylish Kids Canvas Shoes for Boys Plain Casual Shoes and Sneakers",
               price: "5.50",
               url: "https://www.shoestp.com/fashion-stylish-kids-canvas-shoes-for-boys-plain-casual-shoes-and-sneakers_p1185.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/67010b2daaa048a930a47da013c46887.jpg",
               name: "Newest design grey boy anti-skid sports magic type casual shoes",
               price: "13.00",
               url: "https://www.shoestp.com/newest-design-grey-boy-anti-skid-sports-magic-type-casual-shoes_p2501.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/19aec467400a2ca75c6a5bf921f3d279.jpg",
               name: "Children's Woven Oxford Ankle Shoes Fashionable Side Zipper Seasons Lacing-up Flat Casual Shoes",
               price: "2.90",
               url: "https://www.shoestp.com/childrens-woven-oxford-ankle-shoes-fashionable-side-zipper-seasons-lacing-up-flat-casual-shoes_p0338.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e41befe7a5208220dbe399758a7bc391.jpg",
               name: "New style oem winter PU kid school shoes boys casual shoes",
               price: "8.00",
               url: "https://www.shoestp.com/new-style-oem-winter-pu-kid-school-shoes-boys-casual-shoes_p2476.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c65dba7c591318f377780a1da31f0d66.jpg",
               name: "New Fashion Children's Sport Shoes Kid's Runing Shoes For Boys in Stock",
               price: "5.50",
               url: "https://www.shoestp.com/new-fashion-childrens-sport-shoes-kids-runing-shoes-for-boys-in-stock_p1196.html"
           },
           {
               img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/41a4cc9d851adf2dde999da8a236a508.jpg",
               name: "Latest model high grade winter boys dress shoes casual shoes",
               price: "9.00",
               url: "https://www.shoestp.com/latest-model-high-grade-winter-boys-dress-shoes-casual-shoes_p2482.html"
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
