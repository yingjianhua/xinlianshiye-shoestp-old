<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0,
maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="shoes purchasing, Men’s leather shoes, boy’s shoes, Men’s casual shoes,
		Men’s Sneakers, Children sandals, Men’s shoes, Women’s shoes, Women’s high heels, Women’s sandals, shoe companies,
		shoe suppliers, sneaker manufacturers, shoe factories, shoe manufacturing companies, sneaker manufacturer,
		china footwear manufacturer, sport shoes manufacturer, footwear factory.">
    <meta name="discription" content="We will have a showroom in Romanian offline shoes purchasing. During this show,
		you can experience hundreds of styles of shoes locally and get your choosing shoes with factory direct price.
		Also, you can receive professional advice from customization. In this purchasing, nearly 500 different styles of Men’s shoes,
		women’s shoes and kid’s shoes will be displayed. If you reserve your seat online, you will get a secret gift when you come.
		Several free samples of shoes will also be supplied in this show. ">
    <title>Romanian Offline Shoes Purchasing</title>
    <link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/reset.css" media="all">
    <link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/index.css" media="all">
    <link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/swiper.min.css" media="all">
    <link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/element-ui/element-ui.css">
    <script src="/activity/Jsp/Romania/Romaniaindex/js/jquery-1.7.2.min.js"></script>
    <script src="/activity/Jsp/Romania/Romaniaindex/js/swiper.min.js"></script>
    <script src="/activity/Jsp/Romania/Romaniaindex/js/vue.js"></script>
    <script src="/activity/Jsp/Romania/Romaniaindex/js/axios.min.js"></script>
    <script src="/activity/Jsp/Romania/Romaniaindex/js/qs.js"></script>
    <script src="/activity/Jsp/Romania/Romaniaindex/js/element-ui.js"></script>

    <link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/static/css/animate.min.css">
    <link rel="stylesheet" href="/home/static/css/swiper.min.css" type="text/css">
    <link href="/home/static/css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/static/css/xl-style.css">
    <link href="/home/static/css/stp.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/home/static/css/color.css" type="text/css">
    <script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/home/static/js/swiper.min.js"></script>
    <script type="text/javascript" src="/home/static/js/lazyload.min.js"></script>
</head>

<body>
<jsp:include page="../../../../home/template/web-top.jsp"></jsp:include>
<div id="app">
    <template v-if="img_confing.lang=='ro'">
        <div class="banner_01 banner-wrap banner_01_ro">
            <div class="w1200 txt-box por">
                <div class="h4">2018.12.4-2018.12.19</div>
                <div class="h3">
                    ADUNARE DE APRECIERE
                    <br>
                    A MOSTRELOR DE
                    <br>
                    PANTOFI OFFLINE,
                    <br>
                    ROMANIA
                </div>
                <div class="h5">
                    Exhibition Hall Opens All Year Round
                    <br>
                    Magazinul Universal Dragonul Rosu Strada Drumul Garii 12，Bucuresti 077190
                </div>
            </div>
        </div>
    </template>

    <template v-else-if="img_confing.lang=='zh_CN'">
        <div class="banner_01 banner-wrap banner_01_zh">
            <div class="w1200 txt-box por">
                <div class="h4">2018.12.1-2018.12.19</div>
                <div class="h3">罗马尼亚线下鞋样品鉴会</div>
                <div class="h4 btn-blue-yellow">罗马尼当地展厅全年开放</div>
                <div class="h5">
                    环球龙红街店Drumul Garii 12布加勒斯特077190
                </div>
            </div>
        </div>
    </template>

    <template v-else>
        <div class="banner_01 banner-wrap">
            <div class="w1200 tc txt-box por">
                <div class="h4">2018.12.1-2018.12.19</div>
                <div class="h1">ROMANIAN</div>
                <div class="h3">OFFLINE SHOES PURCHASING</div>
                <div class="h5">
                    Exhibition Hall Opens All Year Round
                    <br>
                    Magazinul Universal Dragonul Rosu Strada Drumul Garii 12，Bucuresti 077190
                </div>
                <img src="./images/flag.png" alt="" class="flag-pic">
            </div>
        </div>
    </template>

    <!-- 4张分类 -->
    <div class="banner_02 w1200 banner-wrap">
        <ul class="clearfix">
            <li class="cat-item fl">
                <img src="/activity/Jsp/Romania/Romaniaindex/images/category_01.png" alt=""
                     class="img-cover cat-pic scale">
                <div class="hover-box">
                    <div class="bar"></div>
                    <div class="title h5">{{text_content.menShoes}}</div>
                    <a href="/home/Activity_Romania_classifyactivity?category=373" class="btn-more-simple fs_26">{{text_content.viewMore}}
                        > </a>
                </div>
            </li>
            <li class="cat-item fl">
                <img src="/activity/Jsp/Romania/Romaniaindex/images/category_02.png" alt="img-cover"
                     class="img-cover cat-pic scale">
                <div class="hover-box">
                    <div class="bar"></div>
                    <div class="title h5">{{text_content.womenShoes}}</div>
                    <a href="/home/Activity_Romania_classifyactivity?category=380" class="btn-more-simple fs_26">{{text_content.viewMore}}
                        > </a>
                </div>
            </li>
            <li class="cat-item fl">
                <img src="/activity/Jsp/Romania/Romaniaindex/images/category_03.png" alt="img-cover"
                     class="img-cover cat-pic scale">
                <div class="hover-box">
                    <div class="bar"></div>
                    <div class="title h5">{{text_content.childrenShoes}}</div>
                    <a href="/home/Activity_Romania_classifyactivity?category=387" class="btn-more-simple fs_26">{{text_content.viewMore}}
                        > </a>
                </div>
            </li>
            <li class="cat-item fl flexible flex-center">
                <img src="/activity/Jsp/Romania/Romaniaindex/images/category_04.png" alt="img-cover"
                     class="img-cover cat-pic">
                <div class="txt_04 fs_60" style="width: 60%;">
                    <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html"
                       v-if="img_confing.lang=='ro'">
                        <img src="/activity/Jsp/Romania/Romaniaindex/images/category_04-tetx.png" alt="scale's text"
                             class="scale" style="width: 100%;">
                    </a>
                    <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-zh.html"
                       v-else-if="img_confing.lang=='zh_CN'">
                        <img src="/activity/Jsp/Romania/Romaniaindex/images/category_04-tetx.png" alt="scale's text"
                             class="scale" style="width: 100%;">
                    </a>
                    <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-en.html" v-else>
                        <img src="/activity/Jsp/Romania/Romaniaindex/images/category_04-tetx.png" alt="scale's text"
                             class="scale" style="width: 100%;">
                    </a>
                </div>
            </li>
        </ul>
    </div>

    <!-- 轮播 -->
    <div class="banner_03 banner-wrap">
        <div class="title tc bold fs_60">{{text_content.recommendedProducts}}</div>

        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" v-for="carousel in carouselList">
                    <div class="img-wrap">
                        <a :href="carousel.link" target="_blank">
                            <img :src="carousel.url" alt="show-pic" class="show-pic">
                            <img :src="carousel.hoverUrl" alt="hover-show-pic" class="hover-show-pic">
                        </a>
                    </div>
                </div>
                <!-- <div class="swiper-slide">
                    <div class="img-wrap">
                        <a href="">
                            <img src="./images/carousel_03.png" alt="" class="show-pic">
                            <img src="./images/carousel_02.png" alt="" class="hover-show-pic">
                        </a>
                    </div>
                </div> -->
            </div>

            <!-- 如果需要导航按钮 -->
            <div class="swiper-btn-prev">← {{text_content.precedente}}</div>
            <div class="swiper-btn-next">{{text_content.successivo}} →</div>
        </div>
    </div>

    <!-- 男鞋区 -->
    <div class="banner_04 banner-wrap">
        <div class="w1200 por">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_04_bg-loop.png" alt="bg-loop" class="bg-loop">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_04_bg-shoes.png" alt="bg-shoes" class="bg-shoes">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_04_bg-men.png" alt="bg-men" class="bg-men">

            <!-- 内容区域 -->
            <div class="content-wrap">
                <div class="title-box tr">
                    <h2 class="zone-title">
                        <!-- MEN'S ZONE -->
                        {{text_content.menShoes}}
                    </h2>
                    <a class="btn-more" href="/home/Activity_Romania_classifyactivity?category=373">
                        <!-- view more -->
                        {{text_content.viewMore}}
                    </a>
                </div>

                <!-- 产品展示列表 -->
                <ul class="goods-list clearfix">
                    <li class="goods-item" v-for="(shoes,index) in manShoesList">
                        <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+shoes.id" target="_blank">
                            <img :src="image(shoes.img)" alt="goods-pic" class="goods-pic">
                            <h5 class="goods-title fs_32">
                                <!-- <b>Jiansha</b> Gentleman Kid Suede Genuine Leather Lace Up Casul Dress Shoes Shoes -->
                                {{shoes.name}}
                            </h5>
                        </a>
                        <div class="tools-box tr">
                            <a class="icon-letter-wrap inline-block pointer"
                               :href="'https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html?shoesId='+shoes.id">
                                <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-letter.png"
                                     alt="icon-letter" class="icon-letter pointer">
                                <span class="tips fs_22">{{text_content.inquiry}}</span>
                            </a>
                            <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_off.png"
                                 alt="icon-heart" class="icon-heart pointer"
                                 v-if="!shoes.isFavorite" @click="collect(shoes.productid,'manShoesList',index)">
                            <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_on.png" alt="icon-heart"
                                 class="icon-heart pointer"
                                 v-else @click="collect(shoes.productid,'manShoesList',index)">
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 女鞋区 -->
    <div class="banner_05 banner-wrap">
        <div class="w1200 por">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_05_bg-loop.png" alt="bg-loop" class="bg-loop">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_05_bg-shoes.png" alt="bg-shoes" class="bg-shoes">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_05_bg-women.png" alt="bg-men" class="bg-men">

            <!-- 内容区域 -->
            <div class="content-wrap">
                <div class="title-box">
                    <h2 class="zone-title">
                        <!-- WOMEN'S ZONE -->
                        {{text_content.womenShoes}}
                    </h2>
                    <a class="btn-more" href="/home/Activity_Romania_classifyactivity?category=380">
                        <!-- view more -->
                        {{text_content.viewMore}}
                    </a>
                </div>

                <!-- 产品展示列表 -->
                <ul class="goods-list clearfix">
                    <li class="goods-item" v-for="(shoes,index) in womanShoesList">
                        <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+shoes.id" target="_blank">
                            <img :src="image(shoes.img)" alt="goods-pic" class="goods-pic">
                            <h5 class="goods-title fs_32">
                                {{shoes.name}}
                            </h5>
                        </a>
                        <div class="tools-box tr">
                            <a class="icon-letter-wrap inline-block pointer"
                               :href="'https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html?shoesId='+shoes.id">
                                <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-letter.png"
                                     alt="icon-letter" class="icon-letter pointer">
                                <span class="tips fs_22">{{text_content.inquiry}}</span>
                            </a>
                            <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_off.png"
                                 alt="icon-heart" class="icon-heart pointer"
                                 v-if="!shoes.isFavorite" @click="collect(shoes.id,'womanShoesList',index)">
                            <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_on.png" alt="icon-heart"
                                 class="icon-heart pointer"
                                 v-else @click="collect(shoes.id,'womanShoesList',index)">
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 童鞋区 -->
    <div class="banner_06 banner-wrap">
        <div class="w1200 por">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_06_bg-loop.png" alt="bg-loop" class="bg-loop">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_06_bg-shoes.png" alt="bg-shoes" class="bg-shoes">
            <img src="/activity/Jsp/Romania/Romaniaindex/images/banner_06_bg-child.png" alt="bg-child" class="bg-men">

            <!-- 内容区域 -->
            <div class="content-wrap">
                <div class="title-box tr">
                    <h2 class="zone-title">
                        <!-- CHILDREN'S ZONE -->
                        {{text_content.childrenShoes}}
                    </h2>
                    <a class="btn-more" href="/home/Activity_Romania_classifyactivity?category=387">
                        <!-- view more -->
                        {{text_content.viewMore}}
                    </a>
                </div>

                <!-- 产品展示列表 -->
                <ul class="goods-list clearfix">
                    <li class="goods-item" v-for="(shoes,index) in childrenShoesList">
                        <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+shoes.id" target="_blank">
                            <img :src="image(shoes.img)" alt="goods-pic" class="goods-pic">
                            <h5 class="goods-title fs_32">
                                {{shoes.name}}

                            </h5>
                        </a>
                        <div class="tools-box tr">
                            <a class="icon-letter-wrap inline-block pointer"
                               :href="'https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html?shoesId='+shoes.id">
                                <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-letter.png"
                                     alt="icon-letter" class="icon-letter pointer">
                                <span class="tips fs_22">{{text_content.inquiry}}</span>
                            </a>
                            <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_off.png"
                                 alt="icon-heart" class="icon-heart pointer"
                                 v-if="!shoes.isFavorite" @click="collect(shoes.productid,'childrenShoesList',index)">
                            <img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_on.png" alt="icon-heart"
                                 class="icon-heart pointer"
                                 v-else @click="collect(shoes.productid,'childrenShoesList',index)">
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script>
    const img_confing = stpshop_config

    text_content = {
        "menShoes": "MEN'S SHOES",
        "womenShoes": "WOMEN'S SHOES",
        "childrenShoes": "CHILDREN'S SHOES",
        "viewMore": "VIEW MORE",
        "recommendedProducts": "Recommended Products", //轮播header
        "precedente": "precedente",
        "successivo": "successivo",
        "inquiry": "INQUIRY",
    }

    new Vue({
        el: '#app',
        data: {
            img_confing: img_confing,
            text_content: text_content,
            text_content_en: text_content,
            text_content_zh: {
                "menShoes": "男鞋",
                "womenShoes": "女鞋",
                "childrenShoes": "童鞋",
                "viewMore": "查看更多",
                "recommendedProducts": "产品推荐", //轮播header
                "precedente": "上一个",
                "successivo": "下一个",
                "inquiry": "询盘",
            },
            text_content_ro: {
                "menShoes": "Pantofi pentru bărbați",
                "womenShoes": "Pantofi pentru femei",
                "childrenShoes": "Pantofi pentru copii",
                "viewMore": "Vezi mai mult",
                "recommendedProducts": "Pantofi recomandați", //轮播header
                "precedente": "anterior",
                "successivo": "următorul",
                "inquiry": "Anchetă",
            },
            manShoesList: [],
            womanShoesList: [],
            childrenShoesList: [],
            carouselList: [
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_01.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_01_on.png",
                    link: "https://www.shoestp.com/word-buckle-sandals-female-summer-2018-new-thick-with-open-toe-simple-wild-korean-version-of-the-fairy-rome-with-women's-shoes-_p8741.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_02.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_02_on.png",
                    link: "https://www.shoestp.com/2018-winter-new-men's-casual-men's-shoes-brock-shoes-british-trend-shoes-korean-version-of-the-tide-shoes-wild-_p8733.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_03.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_03_on.png",
                    link: "https://www.shoestp.com/high-heels-female-autumn-2018-new-rivet-pointed-12-stiletto-single-shoes-wild-baotou-roman-sandals-summer-_p8747.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_04.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_04_on.png",
                    link: "https://www.shoestp.com/old-shoes-female-korean-version-of-ulzzang-harajuku-wild-ins-sneakers-flame-2018-new-net-red-women's-shoes-summer_p8742.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_05.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_05_on.png",
                    link: "https://www.shoestp.com/boys-shoes-2018-autumn-children's-shoes-new-children's-fashion-small-white-shoes-plus-velvet-big-children-breathable-casual-shoes-_p8736.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_06.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_06_on.png",
                    link: "https://www.shoestp.com/girls-roman-sandals-2018-new-summer-children-soft-bottom-fashion-little-princess-shoes-girls-flowers-primary-school-shoes-_p8738.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_07.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_07_on.png",
                    link: "https://www.shoestp.com/european-and-american-style-sexy-nightclubs-thin-women's-shoes-stiletto-high-heeled-shallow-mouth-pointed-side-hollow-sequined-single-shoes_p8721.html"
                },
                {
                    url: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_08.png",
                    hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel/carousel_08_on.png",
                    link: "https://www.shoestp.com/sandals-female-summer-flat-2018-new-korean-version-of-the-wild-roman-shoes-flat-with-non-slip-holiday-seaside-beach-shoes-women's-shoes-_p8744.html"
                },
            ]
        },
        mounted() {
            console.log("mounted")
            // 判断语种,显示相应文字
            if (img_confing.lang == "zh_CN") {
                this.text_content = this.text_content_zh;
            } else if (img_confing.lang == "ro") {
                this.text_content = this.text_content_ro;
            } else {
                this.text_content = this.text_content_en;
            }
            this.initCarousel();
            this.getThreeShoesCateList();
        },
        methods: {
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return img_confing.imageBaseUrl + v + params
            },

            // 轮播
            initCarousel() {
                var mySwiper = new Swiper('.swiper-container', {
                    // 如果需要前进后退按钮
                    navigation: {
                        nextEl: '.swiper-btn-next',
                        prevEl: '.swiper-btn-prev',
                    },

                    loop: true, // 循环模式选项
                    autoplay: {
                        delay: 5000
                    },
                    slidesPerView: 3,
                    spaceBetween: 200,
                    // on:{
                    // 	transitionStart: function(e){
                    // 		console.log("11:")
                    // 		console.log(this)
                    // 	},
                    // },
                })
            },
            // 获取男、女、童鞋子列表
            getThreeShoesCateList() {
                axios.get('/home/prm_PrmGroupPurchase_groupshoplist?lang=en')
                    .then((res) => {
                        console.log("鞋子列表suc");
                        console.log(res);
                        if (res.data.ret != 1) {
                            this.$alert('Product acquisition failed, please check the network')
                            return
                        }
                        ;
                        this.manShoesList = res.data.result.manshoes;
                        this.womanShoesList = res.data.result.womanshoes;
                        this.childrenShoesList = res.data.result.childrenshoes;
                    })
                    .catch((error) => {
                        console.log("fail");
                        console.log(error);
                    });
            },
            // 收藏
            collect(goodsId, whichShoesList, shoesIndex) {
                debugger
                if (!isLogin) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                }
                axios.post('/home/usr_UsrFavorites_addFavorite', Qs.stringify({
                    pdtPkey: goodsId,
                }, {allowDots: true}))
                    .then((res) => {
                        console.log("收藏suc");
                        console.log(res);
                        if (res.data.ret != 1) {
                            if (res.data.msg) {
                                this.$message.error(res.data.msg);
                            } else {
                                this.$message.error('Collection failed, please check if you have logged in');
                            }
                            return
                        }
                        let isFavorite = this[whichShoesList][shoesIndex].isFavorite;
                        this[whichShoesList][shoesIndex].isFavorite = !isFavorite;
                    })
                    .catch((err) => {
                        this.$message.error('Request failed, please check the network')
                    });

            },
        }
    })
</script>

<!-- 轮播 -->
<script>

</script>

<!-- 代码追踪 -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-113892728-1"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'UA-113892728-1');
</script>


<!--Start of Tawk.to Script-->
<script type="text/javascript">
    var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
    (function () {
        var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
        s1.async = true;
        s1.src = 'https://embed.tawk.to/5b8fcb48f31d0f771d8476f6/default';
        s1.charset = 'UTF-8';
        s1.setAttribute('crossorigin', '*');
        s0.parentNode.insertBefore(s1, s0);
    })();
</script>
<!--End of Tawk.to Script-->
</body>

</html>
