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
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e180dfb541288e8a94ae33434afe2a30.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/4d97fbd35c37aa5535dd623741dcc610.jpg",
                name: "Follow me children's shoes toddler shoes summer new boy's cartoon sandals fashion baby leather sandals",
                price: "4.20",
                url: "https://www.shoestp.com/follow-me-childrens-shoes-toddler-shoes-summer-new-boys-cartoon-sandals-fashion-baby-leather-sandals_p6434.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a4131c52171bac7fc3033bb8b9ddb1df.jpg",
                name: "Follow me children's shoes 18 summer new Korean version of girl's shoes sandal skin baby little girl student princess middle child",
                price: "7.90",
                url: "https://www.shoestp.com/follow-me-childrens-shoes-18-summer-new-korean-version-of-girls-shoes-sandal-skin-baby-little-girl-student-princess-middle-child_p6432.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/13d8188ce827c6c220b48a643ddd218f.jpg",
                name: "Follow me girl's shoes 18 autumn new female baby shoes leather Korean version of children's shoes",
                price: "8.40",
                url: "https://www.shoestp.com/follow-me-girls-shoes-18-autumn-new-female-baby-shoes-leather-korean-version-of-childrens-shoes_p6445.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/e8abc0b98893ba66f63e4b8a714e7b68.jpg",
                name: "FOLLOW ME girls' genuine leather casual shoes with rabbit",
                price: "5.80",
                url: "https://www.shoestp.com/follow-me-girls-genuine-leather-casual-shoes-with-rabbit_p6446.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f939d613f7c79acbb12dac9ff4744315.jpg",
                name: "Follow me girl's shoes autumn new baby walker Korean version shoes soft - bottomed casual shoes",
                price: "5.60",
                url: "https://www.shoestp.com/follow-me-girls-shoes-autumn-new-baby-walker-korean-version-shoes-soft-bottomed-casual-shoes_p6459.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/6766c85a0c24a70850bb1eee51dea3.jpg",
                name: "Follow me girl's shoes princess shoes genuine leather soft sole 1-3 year old baby girl shoes spring 2018 new style",
                price: "8.40",
                url: "https://www.shoestp.com/follow-me-girls-shoes-princess-shoes-genuine-leather-soft-sole-1-3-year-old-baby-girl-shoes-spring-2018-new-style_p6460.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/35e98f9e9e7e59bdb0546afd56902.jpg",
                name: "FOLLOW ME baby's sandals summer new baby toddlers' shoes boy's shoes children's summer sandals",
                price: "5.80",
                url: "https://www.shoestp.com/follow-me-babys-sandals-summer-new-baby-toddlers-shoes-boys-shoes-childrens-summer-sandals_p6469.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/59ac27c01aba0cb5b37fbe4fe22bec6c.jpg",
                name: "Follow me children's shoes toddler shoes leather summer sandals new Korean version of rabbit soft cool shoes for girls",
                price: "7.90",
                url: "https://www.shoestp.com/follow-me-childrens-shoes-toddler-shoes-leather-summer-sandals-new-korean-version-of-rabbit-soft-cool-shoes-for-girls_p6462.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/5c94f25f481a1c04439d795beab3f34f.jpg",
                name: "Follow me children's shoes girls' shoes 2018 new autumn girl princess shoes Korean children's shoes",
                price: "8.40",
                url: "https://www.shoestp.com/follow-me-childrens-shoes-girls-shoes-2018-new-autumn-girl-princess-shoes-korean-childrens-shoes_p6449.html"
            },
        ]
    },
    {
        bgImg: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/976cc7497fcfbfa9073dacd78edc0e1a.jpg",
        itemImg: [
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/c30f285a093368444632b9bb2f05dfcd.jpg",
                name: "Follow me girl's shoes spring and autumn new princess shoes real leather single shoes lovely bowknot baby shoes",
                price: "6.20",
                url: "https://www.shoestp.com/follow-me-girls-shoes-spring-and-autumn-new-princess-shoes-real-leather-single-shoes-lovely-bowknot-baby-shoes_p6465.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/3d3489d1749774c4ecdb9dc1ea366daf.jpg",
                name: "Follow me girls' sandals 2018 summer new style children's shoes",
                price: "7.00",
                url: "https://www.shoestp.com/follow-me-girls-sandals-2018-summer-new-style-childrens-shoes_p6464.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/f1979b5d8db2c037d829edc959ac11b1.jpg",
                name: "Follow me children's shoes18 years of summer new style toddlers' shoes 1-3 years old children's shoes covered with real leather sandals",
                price: "6.40",
                url: "https://www.shoestp.com/follow-me-childrens-shoes18-years-of-summer-new-style-toddlers-shoes-1-3-years-old-childrens-shoes-covered-with-real-leather-sandals_p6463.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/870a8b419fdccd1d0508e313677a06f.jpg",
                name: "FOLLOW ME 2018 autumn new style children's shoes genuine leather white shoes",
                price: "8.40",
                url: "https://www.shoestp.com/follow-me-2018-autumn-new-style-childrens-shoes-genuine-leather-white-shoes_p6467.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/dea55e7c8f28bce7eec5ddd6a90d1a79.jpg",
                name: "FOLLOW ME children's sandals girl's shoes 2018 summer new cool and refreshing breathable girl princess shoes soft sole female sandals",
                price: "8.40",
                url: "https://www.shoestp.com/follow-me-childrens-sandals-girls-shoes-2018-summer-new-cool-and-refreshing-breathable-girl-princess-shoes-soft-sole-female-sandals_p6412.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/a5a0f3d6df9e6920764de36dd4cec648.jpg",
                name: "FOLLOW ME girl's summer sandals 2018 FOLLOW ME sandals girl's summer flat 2018 version of soft solenew woven princess children's leather sandals",
                price: "8.40",
                url: "https://www.shoestp.com/follow-me-girls-summer-sandals-2018-follow-me-sandals-girls-summer-flat-2018-version-of-soft-solenew-woven-princess-childrens-leather-sandals_p6417.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/bfd4810b12c18cff2c4aec3ca7b7817b.jpg",
                name: "FOLLOW ME girls' sandals 2018 summer princess shoes with sequin peep-toe",
                price: "11.30",
                url: "https://www.shoestp.com/follow-me-girls-sandals-2018-summer-princess-shoes-with-sequin-peep-toe_p6421.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/69d60074ead82626bc4a4afa6e15f01c.jpg",
                name: "Follow me children's shoes toddler shoes summer new boy's cartoon sandals fashion baby leather sandals",
                price: "4.20",
                url: "https://www.shoestp.com/follow-me-childrens-shoes-toddler-shoes-summer-new-boys-cartoon-sandals-fashion-baby-leather-sandals_p6434.html"
            },
            {
                img: "http://shoestest.oss-cn-hangzhou.aliyuncs.com/public/upload2/cnt/magazine/667749526524f048fa44e22a33be7573.jpg",
                name: "Follow me children's shoes girls' real leather single shoes spring and autumn new style toddlers' shoes Korean version of bow baby shoes",
                price: "7.00",
                url: "https://www.shoestp.com/follow-me-childrens-shoes-girls-real-leather-single-shoes-spring-and-autumn-new-style-toddlers-shoes-korean-version-of-bow-baby-shoes_p6438.html"
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
