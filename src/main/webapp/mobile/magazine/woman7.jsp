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
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/43f3fde455c3ff51a0b5a0997b06791d.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/d69e21a7c4df4fb175df0504395a1231.jpg",
                    name: "big size Spring Autumn American and European pointed stilettos shoes women high heels dress sexy girls party shoes",
                    price: "8.50",
                    url: "https://www.shoestp.com/big-size-spring-autumn-american-and-european-pointed-stilettos-shoes-women-high-heels-dress-sexy-girls-party-shoes_p1340.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6374bbd754b9a9d606b1bd99f3b6d616.jpg",    
                    name: "Chengdu women's shoes factory 2018 ladies leather dress shoe leopard print women high heels shoes",
                    price: "16.00",
                    url: "https://www.shoestp.com/chengdu-womens-shoes-factory-2018-ladies-leather-dress-shoe-leopard-print-women-high-heels-shoes_p2307.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b080cc93e8ba06f86d7dd0948f507c54.jpg",
                    name: "2018 Alibaba Elegant PVC Insole Material Women Sexy Blue High Heel Shoes Office Shoes",
                    price: "8.50",
                    url: "https://www.shoestp.com/2018-alibaba-elegant-pvc-insole-material-women-sexy-blue-high-heel-shoes-office-shoes_p2251.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/9836aa5d3feca8b66897e5f1e85b84b1.jpg",
                    name: "Original 2018 lady pointy pencil high heel suede upper material navy blue dress shoes womens casual pure solid colour custom shoes",
                    price: "22.00",
                    url: "https://www.shoestp.com/original-2018-lady-pointy-pencil-high-heel-pu-upper-material-navy-blue-dress-shoes-womens-casual-pure-solid-colour-custom-shoes_p2285.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/b78b283c89c098c622b756a7950b83aa.jpg",
                    name: "Women's shoes cross-belt single shoes blue silk silk satin heels with high heels and European and American style",
                    price: "13.45",
                    url: "https://www.shoestp.com/womens-shoes-cross-belt-single-shoes-blue-silk-silk-satin-heels-with-high-heels-and-european-and-american-style_p2287.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f10acaa3599fdc98027baf3580ea3091.jpg",
                    name: "2018 latest design chengdu manufacturer low price wine red ankle strap high heel shoe women high heel shoes ODM OEM",
                    price: "16.00",
                    url: "https://www.shoestp.com/2018-latest-design-chengdu-manufacturer-low-price-wine-red-ankle-strap-high-heel-shoe-women-high-heel-shoes-odm-oem_p2265.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/25f78ff6a3f4cc9180c9fae3e03b1096.jpg",
                    name: "China women lady casual shoe factory fashion pointed women shoes pumps",
                    price: "12.00",
                    url: "https://www.shoestp.com/china-women-lady-casual-shoe-factory-fashion-pointed-women-shoes-pumps_p2282.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/41794ee8858db547e519cf38fcefcda5.jpg",
                    name: "Women's shoes blue fashion and simple silk satin pointed toes high heels for women with single shoes",
                    price: "19.00",
                    url: "https://www.shoestp.com/womens-shoes-blue-fashion-and-simple-silk-satin-pointed-toes-high-heels-for-women-with-single-shoes_p2279.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f4725d88010b8cd0fb8a1d6d3b049971.jpg",
                    name: "Blue sexy satin fabric material bridal wedding shoes high heels women",
                    price: "26.80",
                    url: "https://www.shoestp.com/zx1130-blue-sexy-satin-fabric-material-bridal-wedding-shoes-high-heels-women_p2230.html"
                },
            ]
        },
        {
            bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f3d061b32931a90d9a8d2be1fcb82b8d.jpg",
            itemImg: [
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/55874c42459e581573eb50fc3ab117f2.jpg",
                    name: "consumers'satisfied feedback wine and royal blue womens dress shoes 2018 heels with platform",
                    price: "18.00",
                    url: "https://www.shoestp.com/ljj041_p2278.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/312593fd355b6c5942121fddcf5f107f.jpg",
                    name: "2018 New fashion and Sexy High Heel Steel and Peep-toe Women Pumps Shoes",
                    price: "26.00",
                    url: "https://www.shoestp.com/ljj051_p2289.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/822c9c9be4672058abe4db26145561e8.jpg",
                    name: "2017 China factory initial payment women shoes 2018 in casual shoes",
                    price: "10.00",
                    url: "https://www.shoestp.com/ljj052_p2290.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3f89297def8b9e59cd27dc72caae05b8.jpg",
                    name: "Stellito Heel Dark Royal Blue Women More Shoes",
                    price: "14.00",
                    url: "https://www.shoestp.com/ljj043_p2280.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/541de261d61b6580acb7d11e85e7732f.jpg",
                    name: "2018 Women'S Comfort Slip-On Loafer Driving Shoes",
                    price: "7.10",
                    url: "https://www.shoestp.com/ljj050_p2288.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/8e2566ea4a4edf4c7f4c7ed2a1e04de9.jpg",
                    name: "China women lady casual shoe factory fashion pointed women shoes pumps",
                    price: "12.00",
                    url: "https://www.shoestp.com/ljj045_p2282.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/361c29dff22ba90eb26b56e3fbca8bcb.jpg",
                    name: "Leather Suede royal blue color block patchwork 2018 New arrival Women Formal Pump ladies pointed toe Chunky High heel shoes",
                    price: "33.00",
                    url: "https://www.shoestp.com/ljj045_p2282.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/261b2a8a58fbb0eeb95fc767895cb469.jpg",
                    name: "Women's shoes blue fashion and simple silk satin pointed toes high heels for women with single shoes",
                    price: "19.00",
                    url: "https://www.shoestp.com/ljj042_p2279.html"
                },
                {
                    img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/708a99eb3ee8f31e50dad49c63cc9824.jpg",
                    name: "nature walk leather oxford shoes for women,royal blue womens dress shoes",
                    price: "15.00",
                    url: "https://www.shoestp.com/ljj044_p2281.html"
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
