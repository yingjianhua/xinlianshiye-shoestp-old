<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<script src="/home/static/js/jquery-1.7.2.min.js"></script>

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

    .goods-box {
        max-width: 1200px;
        min-width: 375px;
        margin: 0 auto;
        height: 100%;
        width: 100%;
    }

    .goods-bgImg {
        font-size: 0;
    }

    .goods-box .goods-list:nth-of-type(4) {
        margin-bottom: 5%;
    }

    .goods-box .goods-bgImg:nth-of-type(3) {
        margin-top: 20px;
    }

    .goods-item {
        width: 24.3%;
        float: left;
        position: relative;
        font-size: 0;
        margin: 0.9% 0.9% 0 0;
        overflow: hidden;
    }

    .goods-item-info {
        box-sizing: border-box;
        padding: 20px;
        width: 100%;
        height: 34.5%;
        background: #313131;
        position: absolute;
        left: 0;
        bottom: 0;
        text-align: left;
        color: #ffffff;
        transform: translateY(220px);
        -webkit-transform: translateY(220px);
        -moz-transform: translateY(220px);
        transition: all 0.7s;
        -webkit-transition: all 0.7s;
        -moz-transition: all 0.7s;
        opacity: 0.8;
    }

    .goods-item-info p {
        margin: 0;
        font-size: 14px;
        line-height: 18px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        -ms-text-overflow: ellipsis;
        text-overflow: ellipsis;
        white-space: normal;
        word-wrap: break-word;
        word-break: break-all;
        word-wrap: break-word;
        position: relative;
        max-height: 35px;
    }

    .goods-item-info h3 {
        margin: 10px 0 0 0;
        font-size: 16px;
    }

    .goods-item:hover {
        box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
        -moz-box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
        -webkit-box-shadow: 0px 5px 10px 0px rgba(51, 51, 51, 0.35);
    }

    .goods-item:hover .goods-item-info {
        transform: translateY(0px);
        -webkit-transform: translateY(0px);
        -moz-transform: translateY(0px);
    }

    @media screen and (min-width: 1025px) {
        .goods-box .goods-list > a:last-child .goods-item {
            display: none;
        }

        .goods-list a:nth-child(4n+4) .goods-item {
            margin-right: 0;
        }
    }

    @media screen and (min-width: 768px) and (max-width: 1024px) {
        .goods-item {
            width: 32.66%;
        }

        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }

        .goods-item-info {
            display: none;
        }
    }

    @media screen and (min-width: 415px) and (max-width: 768px) {
        .goods-item {
            width: 32.66%;
        }

        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }

        .goods-item-info {
            display: none;
        }
    }

    @media screen and (max-width: 415px) {
        .goods-item {
            width: 32.66%;
        }

        .goods-list a:nth-child(3n+3) .goods-item {
            margin-right: 0;
        }

        .goods-item-info {
            display: none;
        }
    }
</style>

<%@ include file="/home/magazine/template/web-top.jsp" %>
<%@ include file="/home/magazine/template/new-header.jsp" %>


<div class="goods-box clearfix">

</div>
<%@ include file="/home/magazine/template/new-foot.jsp" %>
<script>
    var data = [{
        bgImg: "/public/upload/cnt/ad/fe2020f8ad81d5fd4288b3410fd8d14b.jpg",
        itemImg: [
            {
                img: "/public/upload/cnt/ad/b9e613f65a7b2de031425f29f001710e.jpg",
                name: "Kangrui men comfortable leather leisure shoes and driver shoes",
                price: "13.94",
                url: "https://www.shoestp.com/kangrui-men-comfortable-leather-leisure-shoes-and-driver-shoes_p6821.html"
            },
            {
                img: "/public/upload/cnt/ad/9c9bcf1325fa22106c2f7bfa606c213e.jpg",
                name: "Kangrui mens leather formal slip-on loafer shoes",
                price: "13.94",
                url: "https://www.shoestp.com/kangrui-mens-leather-formal-slip-on-loafer-shoes_p6828.html"
            },
            {
                img: "/public/upload/cnt/ad/bbc17af9d42fa2e2ad51bc9dea4cf56a.jpg",
                name: "Kangrui men's slip-on pointed toe shoes leeather shoes",
                price: "14.19",
                url: "https://www.shoestp.com/kangrui--men's-slip-on-pointed-toe-shoes-leeather-shoes_p6832.html"
            },
            {
                img: "/public/upload/cnt/ad/6eae727330d53669ee7ec2415a39f51d.jpg",
                name: "Aokang Men's Rivets Shoes Fashionable Spring and Autumn Lace-up Casual Flat Comfortable Damping Shoes",
                price: "19",
                url: "https://www.shoestp.com/aokang-men's-rivets-shoes-fashionable-spring-and-autumn-lace-up-casual-flat-comfortable-damping-shoes_p571.html"
            },
            {
                img: "/public/upload/cnt/ad/d3822d11e0da5953ff87edf5768f0b36.jpg",
                name: "Aokang Men's Fashionable Shoes Metal Rivets Spring and Autumn Lace-up Casual Sports Flat Shoes",
                price: "19",
                url: "https://www.shoestp.com/aokang-men's-fashionable-shoes-metal-rivets-spring-and-autumn-lace-up-casual-sports-flat-shoes_p574.html"
            },
            {
                img: "/public/upload/cnt/ad/fa2c03989af4253e070c24a32b599aba.jpg",
                name: "Men's Autumn and Winter New Fashion High Cut Boots Lace-up Round Head British Style Casual Flat Boots",
                price: "18",
                url: "https://www.shoestp.com/men's-autumn-and-winter-new-fashion-high-cut-boots-lace-up-round-head-british-style-casual-flat-boots_p324.html"
            },
            {
                img: "/public/upload/cnt/ad/e682f32b09f412baeca1e391907c32e1.jpg",
                name: "Men's Autumn and Winter New Fashion Ankle Shoes Lace-up Round Head British Style Casual Flat Middle Shoes",
                price: "14",
                url: "https://www.shoestp.com/men's-autumn-and-winter-new-fashion-ankle-shoes-lace-up-round-head-british-style-casual-flat-middle-shoes_p327.html"
            },
            {
                img: "/public/upload/cnt/ad/2f847015c7b883e46f357ca7e770fa94.jpg",
                name: "Luxin Suede Leather Beige Color TPR Sole Chelsea Men Boots",
                price: "10",
                url: "https://www.shoestp.com/luxin-suede-leather-beige-color-tpr--sole-chelsea-men-boots_p5726.html"
            },
            {
                img: "/public/upload/cnt/ad/761b6b1297dbebd1d3a705c176e12bee.jpg",
                name: "Aokang Men's Embroidery Shoes Fashionable Spring and Autumn Lace-up Casual Flat Sports Shoes",
                price: "19",
                url: "https://www.shoestp.com/aokang-men's-embroidery-shoes-fashionable-spring-and-autumn-lace-up-casual-flat-sports-shoes_p573.html"
            },
        ]
    },
        {
            bgImg: "/public/upload/cnt/ad/851d261e3d754656f40c7fb2b52952c1.jpg",
            itemImg: [
                {
                    img: "/public/upload/cnt/ad/db1e826d0d728eb8c21a80d4d0ceba8a.jpg",
                    name: "Luxin New Comfortable Imitate Leather Laced Up Shoe For Men",
                    price: "10",
                    url: "https://www.shoestp.com/luxin-new-comfortable-imitate-leather-laced-up-shoe-for-men_p5734.html"
                },
                {
                    img: "/public/upload/cnt/ad/24021567eb49ce27df23e1d2131dc4cb.jpg",
                    name: "Kangrui Men's Fashionable Shoes Crocodile Print Embossed Spring and Autumn Slip-on Elastic Casual Business Shoes Clark Style",
                    price: "14.52",
                    url: "https://www.shoestp.com/kangrui-men's-fashionable-shoes-crocodile-print-embossed-spring-and-autumn-slip-on-elastic-casual-business-shoes-clark-style_p580.html"
                },
                {
                    img: "/public/upload/cnt/ad/ab029741b4a385567ab1eaeb8fc80f35.jpg",
                    name: "Luxin 2018 The Best Price Casual Antiskid TPR Boots For Men",
                    price: "10",
                    url: "https://www.shoestp.com/luxin-2018-the-best-price-casual--antiskid-tpr--boots-for-men_p5719.html"
                },
                {
                    img: "/public/upload/cnt/ad/827560abedac237e16e168e7de4f978a.jpg",
                    name: "Jiansha Gentleman Kid Suede Genuine Leather Casul Dress Shoes",
                    price: "20.6",
                    url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-genuine-leather-casul-dress-shoes_p6128.html"
                },
                {
                    img: "/public/upload/cnt/ad/382d4778e43678cb9239b3bc863e6815.jpg",
                    name: "Jiansha Popular Genuine Leather Casual Dress Shoes For Men",
                    price: "23",
                    url: "https://www.shoestp.com/jiansha-popular-genuine-leather-casual-dress-shoes-for-men_p6142.html"
                },
                {
                    img: "/public/upload/cnt/ad/608c53d8ecd1cbc6044e9e3b2f828b72.jpg",
                    name: "Jiansha Gentleman Kid Suede Leather White Outsole Casul Dress Shoes",
                    price: "23",
                    url: "https://www.shoestp.com/jiansha-gentleman-kid-suede-leather-white-outsole-casul-dress-shoes_p6160.html"
                },
                {
                    img: "/public/upload/cnt/ad/cfe699eae693f0eb8db053e8e40dbc6b.jpg",
                    name: "Jiansha Popular Genuine Leather Strap With Buckle Dress Shoes For Men",
                    price: "20.5",
                    url: "https://www.shoestp.com/jiansha-popular-genuine-leather-strap-with-buckle-dress-shoes-for-men_p6145.html"
                },
                {
                    img: "/public/upload/cnt/ad/a9522d8c47efaf202c240bd90aee7fc8.jpg",
                    name: "Jiansha Black Genuine Leather Casual Dress Shoes For Men",
                    price: "22",
                    url: "https://www.shoestp.com/jiansha-black-genuine-leather-casual-dress-shoes-for-men_p6147.html"
                },
                {
                    img: "/public/upload/cnt/ad/cf642b3e033da897cb976167ab63b627.jpg",
                    name: "Jiansha Gentleman Genuine Leather Casul Dress Shoes",
                    price: "20.5",
                    url: "https://www.shoestp.com/jiansha-gentleman-genuine-leather-casul-dress-shoes_p6132.html"
                },
            ]
        }
    ]
    var item = ''
    var imageBaseUrl = 'https://image.shoestp.com'
    $.each(data, function (i, ele) {
        item +=
            '<div class="goods-bgImg">' +
            '<img src="' + imageBaseUrl + ele.bgImg + '" alt="">' +
            '</div>' +
            '<div class="goods-list clearfix">';
        $.each(ele.itemImg, function (i, e) {
            item +=
                '<a href="' + e.url + '" target="_blank">' +
                '<div class="goods-item">' +
                '<img src="' + imageBaseUrl + e.img + '" alt="">' +
                '<div class="goods-item-info">' +
                '<p>' +
                e.name +
                '</p>' +
                '<h3>' +
                '$' + e.price +
                '</h3>' +
                '</div>' +
                '</div>' +
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
