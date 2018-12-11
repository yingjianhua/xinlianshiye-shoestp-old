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
        bgImg: "/public/upload/cnt/ad/1d8c6e6c0ad9060d75e275b33c80d373.jpg",
        itemImg: [
            {
                img: "/public/upload/cnt/ad/789eb05bebe5759ec37d02c22b0a817f.jpg",
                name: "Fashion Slide Women Party Sandal Custom Logo Made In China",
                price: "12.99",
                url: "https://www.shoestp.com/europe-and-the-united-states-luxury-metal-wings-open-toe-stiletto-sexy-super-high-heeled-nightclub-models-foreign-trade-high-heeled-sandals_p8857.html"
            },
            {
                img: "/public/upload/cnt/ad/c0f3220fd38321ef13c3d7e6f25caef1.jpg",
                name: "Tracyee Sexy Stiletto High Heels Elegant Ladies Ankle Sandals For Women",
                price: "34.99",
                url: "https://www.shoestp.com/tracyee-sexy-stiletto-high-heels-elegant-ladies-ankle-sandals-for-women_p8714.html"
            },
            {
                img: "/public/upload/cnt/ad/7ac865eceb50780b5e66a18453dbb862.jpg",
                name: "Tracy Best Selling Soft Leather Shoes Woman Elegant Attractive Dress Sandals 2018",
                price: "19.99",
                url: "https://www.shoestp.com/summer-new-korean-fashion-show-stage-sexy-stiletto-super-high-heels-open-toe-cool_p8860.html"
            },
            {
                img: "/public/upload/cnt/ad/e1a3c94a23433ba307e7206281f64f3c.jpg",
                name: "Fashion New Arrival PVC Buckle Ladies Sandals Stilleto Heel Women Summer Shoes",
                price: "13.99",
                url: "https://www.shoestp.com/fashion-new-arrival-pvc-buckle-ladies-sandals-stilleto-heel-women-summer-shoes_p8861.html"
            },
            {
                img: "/public/upload/cnt/ad/15562c51b7572bbab107a15763755c1f.jpg",
                name: "Tracyee 2018 Hot Selling Women Fancy Peep Toe Black Shoes Thin High Heel Sexy Ankle Wrap Pump Sandals",
                price: "28.99",
                url: "https://www.shoestp.com/tracyee-2018-hot-selling-women-fancy-peep-toe-black-shoes-thin-high-heel-sexy-ankle-wrap-pump-sandals_p8718.html"
            },
            {
                img: "/public/upload/cnt/ad/8a992aecabc6980193202704813feaa9.jpg",
                name: "Tracyee sexy stiletto High Heel Woman sandals Lace Up Footwear sandals Woman",
                price: "34.99",
                url: "https://www.shoestp.com/tracyee-sexy-stiletto-high-heel-woman-sandals-lace-up-footwear-sandals-woman_p8719.html"
            },
            {
                img: "/public/upload/cnt/ad/a90901c19580ad6cd66acc87de5b30bc.jpg",
                name: "Tracyee Women Popular sexy Ankle strappy shoes slip On stiletto Heel Ladies shoes In Dubai",
                price: "28.99",
                url: "https://www.shoestp.com/tracyee-women-popular-sexy-ankle-strappy-shoes-slip-on-stiletto-heel-ladies-shoes-in-dubai_p8856.html"
            },
            {
                img: "/public/upload/cnt/ad/c804b51ed140b0d094910126dcdfdfe3.jpg",
                name: "Europe Style Girls Latest Pencil High Heels Dress Shoes Fancy Sandals For Women",
                price: "29.99",
                url: "https://www.shoestp.com/europe-style-girls-latest-pencil-high-heels-dress-shoes-fancy-sandals-for-women_p8863.html"
            },

            {
                img: "/public/upload/cnt/ad/b3efed97aa5a2317cc9391110f8f072d.jpg",
                name: "2018 Fashionable China Wholesale Stud Strap Peep Toe Pumps Sandals Chunky Heel Women Sandals Shoes",
                price: "15.99",
                url: "https://www.shoestp.com/2018-fashionable-china-wholesale-stud-strap-peep-toe-pumps-sandals-chunky-heel-women-sandals-shoes_p8863.html"
            },
        ]
    },
        {
            bgImg: "/public/upload/cnt/ad/e60d28df59639b928eadbe913ac2c76f.jpg",
            itemImg: [
                {
                    img: "/public/upload/cnt/ad/3142a8202b084df02e95966c7c09af98.jpg",
                    name: "689-8 Latest Newest Design PU Women Sandals High Heels Girls Sandals Simple Designs",
                    price: "18.99",
                    url: "https://www.shoestp.com/689-8-latest-newest-design-pu-women-sandals-high-heels-girls-sandals-simple-designs_p8865.html"
                },
                {
                    img: "/public/upload/cnt/ad/ac9120f6eeb9705417bc375ac566c8df.jpg",
                    name: "Lace Up Summer Ladies Sandals For Women 2018 New Trend High Heels Summer Shoes",
                    price: "18.99",
                    url: "https://www.shoestp.com/lace-up-summer-ladies-sandals-for-women-2018-new-trend-high-heels-summer-shoes_p8858.html"
                },
                {
                    img: "/public/upload/cnt/ad/48f2a93f13d66b00f78b77e35702be2d.jpg",
                    name: "Fancy Simple Summer Beautiful Girls PU Sandal Shoes",
                    price: "19.99",
                    url: "https://www.shoestp.com/fancy-simple-summer-beautiful-girls-pu-sandal-shoes_p8856.html"
                },
                {
                    img: "/public/upload/cnt/ad/112b289ad9d4a4718bc84e63a51f3a34.jpg",
                    name: "High Quality Denim Fabric Ladies High Heels Sandals Fashion Dress Shoes For Women",
                    price: "24.99",
                    url: "https://www.shoestp.com/one-word-buckle-with-sandals-female-summer-2018-new-high-heel-women's-shoes-stiletto-sexy-open-toe-wild_p8864.html"
                },
                {
                    img: "/public/upload/cnt/ad/6d525a15441d2796e0f90c896060745c.jpg",
                    name: "China Wholesale New Design Leather Sexy High Heel Shoes Women Sandals",
                    price: "24.99",
                    url: "https://www.shoestp.com/china-wholesale-new-design-leather-sexy-high-heel-shoes-women-sandals_p8720.html"
                },
                {
                    img: "/public/upload/cnt/ad/1ff05afcae2d146d3d3bcad4a7397d95.jpg",
                    name: "Ladies High Heels Sandals Shoes Women Lace Up Stiletto Dress Shoes",
                    price: "24.99",
                    url: "https://www.shoestp.com/ladies-high-heels-sandals-shoes-women-lace-up-stiletto-dress-shoes_p8715.html"
                },

                {
                    img: "/public/upload/cnt/ad/e068d9670aedfe4327defecb268f3d3f.jpg",
                    name: "Blue Color Suede Ladies Fancy Nude Sandal High Heels Back Zipper",
                    price: "19.99",
                    url: "https://www.shoestp.com/factory-price-pvc-ladies-fashion-stiletto-high-heel-model-sandals-with-rivets_p8717.html"
                },
                {
                    img: "/public/upload/cnt/ad/122adfbe5b8d416aec83cdac35c03ca4.jpg",
                    name: "New Model Trendy Fashion Design Women Sexy Dress Shoes High Heel Sandlas",
                    price: "24.99",
                    url: "https://www.shoestp.com/new-model-trendy-fashion-design-women-sexy-dress-shoes-high-heel-sandlas_p8862.html"
                },
                {
                    img: "/public/upload/cnt/ad/eab7708cd35f38a0d7d9c2099cd42c16.jpg",
                    name: "Factory Price PVC Ladies Fashion Stiletto High Heel Model Sandals With Rivets",
                    price: "10.99",
                    url: "https://www.shoestp.com/factory-price-pvc-ladies-fashion-stiletto-high-heel-model-sandals-with-rivets_p8717.html"
                },
            ]
        }
    ]
    var imageBaseUrl = "https://image.shoestp.com"
    var item = ''
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
