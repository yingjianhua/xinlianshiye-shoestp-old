<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title></title>

    <script src='./js/swiper.min.js'></script>
    <script src='./js/vue.min.js'></script>
    <script src='./js/element-ui.js'></script>
    <script src='./js/axios.min.js'></script>
    <script src='./js/qs.js'></script>
    <link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/index-xsq.css"/>
    <link rel="stylesheet" href="css/index-ljx.css"/>
    <link rel="stylesheet" href="css/index-lhl.css"/>
    <link rel="stylesheet" href="css/index-lty.css"/>
    <link rel="stylesheet" href="css/index-xc.css"/>
    <link rel="stylesheet" href="css/element-ui/element-ui.css"/>
    <link rel="stylesheet" href="css/swiper.min.css"/>
</head>
<body>
<div id="mix">
    <!-- 林嘉祥 - 顶部 -->
    <div id="ljx_top">
        <div class="ljx">
            <div class="logocon">
                <img src="images/ljxlogo.png" alt="">
            </div>
            <el-carousel trigger="click" height="460px" arrow="always" :interval="8000">
                <el-carousel-item>
                    <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html"><img
                            src="images/ljxbanner1.jpg" alt=""></a>
                </el-carousel-item>
                <el-carousel-item>
                    <a href="https://www.shoestp.com/home/pdt_PdtProduct"><img src="images/ljxbanner2.jpg" alt=""></a>
                </el-carousel-item>
                <el-carousel-item>
                    <a href="https://www.shoestp.com/home/usr_UsrConsult_publicListView"><img
                            src="images/ljxbanner3.jpg" alt=""></a>
                </el-carousel-item>
            </el-carousel>
            <div class="ljxcon-1">
                <div class="ljxcon">
                    <div class="ljxshow">
                        <!--<span>His</span>-->
                        <img src="images/his.jpg" alt="">
                    </div>
                    <div class="ljxhidden">
                        <h4>His</h4>
                        <ul>
                            <li>Men's leather shoes</li>
                            <li>Men's sneakers</li>
                            <li>Men's sneakers</li>
                        </ul>
                        <a href="https://www.shoestp.com/home/pdt_PdtProduct?cated=373"><span>Learn More</span></a>
                    </div>
                </div>
                <div class="ljxcon">
                    <div class="ljxshow">
                        <!--<span>Her</span>-->
                        <img src="images/her.jpg" alt="">
                    </div>
                    <div class="ljxhidden">
                        <h4>Her</h4>
                        <ul>
                            <li>Women's high heels</li>
                            <li>Women's sneakers</li>
                            <li>Women's sandals</li>
                        </ul>
                        <a href="https://www.shoestp.com/home/pdt_PdtProduct?cated=380"><span>Learn More</span></a>
                    </div>
                </div>
                <div class="ljxcon">
                    <div class="ljxshow">
                        <!--<span>Kids</span>-->
                        <img src="images/kids.jpg" alt="">
                    </div>
                    <div class="ljxhidden">
                        <h4>Kids</h4>
                        <ul>
                            <li>Boy's shoes</li>
                            <li>Girls shoes</li>
                            <li>Children's sandals</li>
                        </ul>
                        <a href="https://www.shoestp.com/home/pdt_PdtProduct?cated=387"><span>Learn More</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 徐世奇 -->
    <div id="xsq">
        <div class="w1200">
            <!-- 鞋款分类wrap -->
            <div class="swiper-container goods-category-wrap swiper-category-list ">
                <div class="swiper-wrapper">
                    <div class="goods-category-item swiper-slide stop-swiping">
                        <div class="big-title">O2O SHOW ROOM</div>
                        <div class="flex-left-right">
                            <!-- 左侧描述性文字 -->
                            <div class="descript-box">
                                <div class="content-text">
                                    From our exhibitors,
                                    <br>
                                    selected by analysts
                                </div>
                                <a href="https://www.shoestp.com/home/Activity_Romania" target="_blank"
                                   class="btn-see-all">See All </a>
                            </div>

                            <div class="right-goods-out-wrap">
                                <!-- 右侧goods列表 -->

                                <ul class="right-goods-wrap">
                                    <li class="item" v-for="(goods, index) in manShoesList" v-if="index < 4">
                                        <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id">
                                            <img :src="image(goods.img)" alt="man's shoes" class="goods-pic">
                                            <div class="goods-name ">
                                                <div class="ellipsis_2">
                                                    {{goods.name}}
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>


                    <div class="goods-category-item swiper-slide stop-swiping">
                        <div class="big-title">O2O SHOW ROOM</div>
                        <div class="flex-left-right">
                            <!-- 左侧描述性文字 -->
                            <div class="descript-box">
                                <div class="content-text">
                                    From our exhibitors,
                                    <br>
                                    selected by analysts
                                </div>
                                <a href="https://www.shoestp.com/home/Activity_Romania" target="_blank"
                                   class="btn-see-all">See All </a>
                            </div>

                            <div class="right-goods-out-wrap">
                                <!-- 右侧goods列表 -->

                                <ul class="right-goods-wrap">
                                    <li class="item" v-for="(goods, index) in womanShoesList" v-if="index < 4">
                                        <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id">
                                            <img :src="image(goods.img)" alt="man's shoes" class="goods-pic">
                                            <div class="goods-name ">
                                                <div class="ellipsis_2">
                                                    {{goods.name}}
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>


                    <div class="goods-category-item swiper-slide stop-swiping">
                        <div class="big-title">O2O SHOW ROOM</div>
                        <div class="flex-left-right">
                            <!-- 左侧描述性文字 -->
                            <div class="descript-box">
                                <div class="content-text">
                                    From our exhibitors,
                                    <br>
                                    selected by analysts
                                </div>
                                <a href="https://www.shoestp.com/home/Activity_Romania" target="_blank"
                                   class="btn-see-all">See All </a>
                            </div>

                            <div class="right-goods-out-wrap">
                                <!-- 右侧goods列表 -->

                                <ul class="right-goods-wrap">
                                    <li class="item" v-for="(goods, index) in childrenShoesList" v-if="index < 4">
                                        <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id">
                                            <img :src="image(goods.img)" alt="man's shoes" class="goods-pic">
                                            <div class="goods-name ">
                                                <div class="ellipsis_2">
                                                    {{goods.name}}
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>


                </div>
            </div>

            <!-- 男鞋、女、童的nav wrap -->
            <ul class="goods-category-nav-wrap">
                <li class="goods-category-nav-item" :class="{active: selectedCategory==1}"
                    @click="clickToSelectedCategory(1)">
                    <img src="./images/icon-man-shoes_off.png" alt="" class="icon icon-off icon-man">
                    <img src="./images/icon-man-shoes_on.png" alt="" class="icon icon-on icon-man">
                    <span>His</span>
                </li>
                <li class="goods-category-nav-item" :class="{active: selectedCategory==2}"
                    @click="clickToSelectedCategory(2)">
                    <img src="./images/icon-woman-shoes_off.png" alt="" class="icon icon-off icon-woman">
                    <img src="./images/icon-woman-shoes_on.png" alt="" class="icon icon-on icon-wpman">
                    <span>Her</span>
                </li>
                <li class="goods-category-nav-item" :class="{active: selectedCategory==3}"
                    @click="clickToSelectedCategory(3)">
                    <img src="./images/icon-child-shoes_off.png" alt="" class="icon icon-off icon-child">
                    <img src="./images/icon-child-shoes_on.png" alt="" class="icon icon-on icon-child">
                    <span>Kids</span>
                </li>
            </ul>

        </div>
    </div>

    <!-- 林铁原 -->
    <div id="lty">
        <div class="hotCouny">
            <h2 style="font-size: 40px;height: 100px;line-height: 120px;font-weight: 500;border-bottom: 1px solid #ccc;margin-bottom: 20px;">
                Trade Shows </h2>
            <ul class="top">
                <!-- <li class="on"><img src="images/couny1.jpg" alt="" />Romania<i></i></li> -->
                <li><img src="images/couny1.jpg" alt=""/>Romania<i></i></li>
                <li><img src="images/couny2.jpg" alt=""/>Brazil<i></i></li>
                <li><img src="images/couny3.jpg" alt=""/>Russia<i></i></li>
                <li><img src="images/couny4.jpg" alt=""/>Italy<i></i></li>
            </ul>
            <div class="com">
                <div class="left fl">
                    <dl>
                        <dd>500-800 pairs of premium shoes</dd>
                        <dd><i></i>More than 500 shoe companies were selected</dd>
                        <dd><i></i>500-800 pairs of premium shoes</dd>
                        <dd><i></i>24 hours customer service</dd>
                    </dl>
                    <a class="btn" href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html"
                       style="display: block;"><p>Register Now</p></a>
                </div>
                <ul class="fr">
                    <li>
                        <div class="pic1">
                            <img src="images/counyCom0.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="images/counyicon2.png" alt="" class="pic2"/>
                            <p>About Show</p>
                        </div>
                    </li>
                    <li>
                        <div class="pic1">
                            <img src="images/counyCom1.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="images/counyicon1.png" alt="" class="pic2"/>
                            <p>
                                Who Should
                                <br>
                                Visit
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="pic1">
                            <img src="images/counyCom2.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="images/counyicon3.png" alt="" class="pic2"/>
                            <p>Exhibitor List </p>
                        </div>
                    </li>
                    <li>
                        <div class="pic1">
                            <img src="images/counyCom3.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="images/counyicon1.png" alt="" class="pic2"/>
                            <p>
                                Who Should
                                <br>
                                Visit
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 夏超 -->
    <div id="xc" style="margin-top: 20px;">
        <!-- New Products -->
        <div class="new-products-container">
            <div class="title">New Products</div>
            <div class="tip clearfix">
                <span class="tip-title fl">Free Alerts on newly added products! </span>
                <input type="email" class="tip-input fl" v-model="form.email" placeholder="Example:jsun@company.com"/>
                <!-- {{bean.email}} -->
                <div class="tip-btn fl" @click="subscribe">Subscribe</div>
            </div>
            <!-- Swiper -->
            <div class="swiper-container" id="new-products-swiper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" v-for="(item, index) in newProductsList">
                        <a :href="'https://www.shoestp.com/'+item.rewrite" target="_blank">
                            <img :src="image(item.image,'?x-oss-process=image/resize,m_fixed,h_280,w_280')" alt="" style="width:280px;height:280px;" >
                            <div class=" goods-info">
                                <div>
                                    {{item.name}}
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>

            </div>
            <div class="clearfix">
                <a href="https://www.shoestp.com/home/pdt_PdtProduct?orderfld=New" target="_blank"
                   class="blueFontColor fr">See All Products ></a>
            </div>
        </div>
    </div>

    <!-- 林华力 -->
    <div id="home">
        <div class="top10" style="width: 1200px;margin: 0 auto;">
            <h2 style="font-size: 40px;line-height: 100px;font-weight: 500;border-bottom: 1px solid #c4c4c4">Top10
                Reliable Suppliers</h2>
            <p style="font-size: 18px;color: #c3c3c3;line-height: 50px">Free Alerts on newly added products! </p>
            <div class="top10-03" style="width: 100%;position: relative">
                <img src="images/top10-01-1.png" alt="">
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=274">
                        <img src="images/lhl1.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=293">
                        <img src="images/lhl2.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=17">
                        <img src="images/lhl3.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=28">
                        <img src="images/lhl4.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=297">
                        <img src="images/lhl5.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=274">
                        <img src="images/lhl1.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=293">
                        <img src="images/lhl2.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=17">
                        <img src="images/lhl3.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=28">
                        <img src="images/lhl4.png" alt="" style="">
                    </a>
                </div>
                <div class="hexagon">
                    <a href="https://www.shoestp.com/home/usr_UsrSupplier_gtSupIndex?pkey=297">
                        <img src="images/lhl5.png" alt="" style="">
                    </a>
                </div>
            </div>
            <a href="https://www.shoestp.com/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_listView">
                <div class="top10-01"
                     style="display: inline-block;width: 762px;height: 377px;background: url('images/top10-02.png') center no-repeat;background-size: 100% 100%;padding-left: 40px;padding-top: 25px;vertical-align: top">
                    <p style="font-size: 18px;color: #c3c3c3;line-height: 50px">SHOES</p>
                    <p style="font-size: 24px;color: #ffffff;line-height: 25px;width: 250px">AN EASY WAY TO SEND BUYING
                        REQUESTS TO SUPPLIERS & GET QUOTES QUICKLY.</p>
                    <ul style="margin-top: 40px">
                        <li style="font-size: 14px;color: white"><span>● </span>Get quotes for your custom request</li>
                        <li style="font-size: 14px;color: white"><span>● </span>Lte the right suppliers find you</li>
                        <li style="font-size: 14px;color: white"><span>● </span>Close deals with one click</li>
                    </ul>
                </div>
                <div class="top10-02"
                     style="display: inline-block;width: 420px;height: 377px;border: 7px solid #c4c4c4;vertical-align: top;margin-left: 12px;padding: 20px">
                    <h2 style="font-size: 40px;line-height: 50px;">One Request, Multiple Quotes</h2>
                    <form action="">
                        <input type="text" placeholder="what are you looking for..."
                               style="width: 100%;height: 40px;border: 1px solid #c4c4c4;margin: 10px 0;text-indent: 10px">
                        <input type="text" placeholder="Quantity"
                               style="width: 60%;height: 40px;border: 1px solid #c4c4c4;margin: 10px 0;text-indent: 10px;vertical-align: top">
                        <span style="display:inline-block;width: 39%;height: 44px;border: 1px solid #c4c4c4;margin: 10px 0;text-indent: 10px;margin-left:-5px;font-size: 16px;line-height: 42px;color: #a1a1a1;">Bag/Bags</span>
                        <p style="font-size: 16px;line-height: 42px;color: #a1a1a1;">Select template type :</p>
                        <a href="javascript:void(0);"
                           style="display: inline-block;width: 80%;height: 40px;font-size: 18px;line-height: 40px;color: #c2c2c2;background-color: #10389c;text-align: center;border-radius: 10px">Request
                            For Quotation</a>
                    </form>
                </div>
            </a>
        </div>
    </div>

    <!-- 夏超 -->
    <div id="xc" style="margin-bottom: 40px;">
        <!-- BRAND ZONE  -->
        <div class="brand-zone-container">
            <div class="title">BRAND ZONE</div>
            <div style="margin:40px 0;" class="brand-zone-title">
                <div class="name">
                    <div>Buy Fashion Shoes Instantly</div>
                    <a href="https://www.shoestp.com/home/pdt_PdtProduct" target="_blank">Order Now</a>
                </div>
                <div class="clearfix list">
                    <div class="fl">
                        <img src="./images/icon-gouwuche.png" alt="">
                        <div>Speedy <br>
                            Checkout
                        </div>
                    </div>
                    <div class="fl">
                        <img src="./images/icon-wuliu.png" alt="">
                        <div>Price Includes <br>
                            Shipping
                        </div>
                    </div>
                    <div class="fl">
                        <img src="./images/icon-zhiliang.png" alt="">
                        <div> Secure <br>
                            Transactions
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper-container" id="brand-zone-swiper">
                <div class="swiper-wrapper">

                    <div class="swiper-slide" v-for="(item,index) in RandomPdtList" ::key="index">
                        <a :href="'https://www.shoestp.com/'+item.rewrite" target="_blank">
                            <img :src="image(item.image,'?x-oss-process=image/resize,m_fixed,h_162,w_177')" alt="" style="width: 177px;height: 162px;">
                            <div class="goods-info">
                                <div>
                                    {{item.title}}
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
            <div class="clearfix">
                <a href="https://www.shoestp.com/home/pdt_PdtProduct" target="_blank" class="blueFontColor fr">See All
                    Products ></a>
            </div>
        </div>
        <!-- CROWDFUNDING -->
        <div class="crowdfunding-container">
            <div class="title">CROWDFUNDING</div>
            <div class="tip clearfix">
                <ul class="tip-list clearfix">
                    <li class="fl">Lower price in small number</li>
                    <li class="fl" style="margin-left:50px;">Fashion Design will be published as soon as possible</li>
                </ul>
            </div>
            <div class="crowdfunding-list clearfix">
                <div class="crowdfunding-item">
                    <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=977" target="_blank">
                        <img src="./images/goods1.jpg" alt="" style="width:271px;height:271px;">
                    </a>
                    <div class="goods-detail">
                        <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=977"
                           target="_blank">
                            <div class="goods-name">
                                FUNDING
                            </div>
                            <div class="goods-title">
                                <div class="ellipsis_2">
                                    Leather shoes male Korean version of the trend of leather shoes men's casual shoes
                                    autumn men's shoes tide
                                </div>
                            </div>
                        </a>
                        <div class="goods-price clearfix" style="font-size: 14px;color:#808080;">
                            <div class="fl" style="line-height:26px;">
                                <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">600</span>
                                <span>Sales volume</span>
                            </div>
                            <div class="fr">120%</div>
                        </div>
                        <div>
                            <el-progress :percentage="120" color="#35ca97" :show-text="false"></el-progress>
                        </div>
                        <div class="goods-time">
                            <img src="./images/icon-time.png" alt="">
                            <span style="color:#808080;font-size: 14px;">3 days left</span>
                        </div>
                    </div>
                </div>
                <div class="crowdfunding-item">
                    <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=870" target="_blank">
                        <img src="./images/goods2.jpg" alt="" style="width:271px;height:271px;">
                    </a>
                    <div class="goods-detail">
                        <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=870"
                           target="_blank">
                            <div class="goods-name">
                                FUNDING
                            </div>
                            <div class="goods-title">
                                <div class="ellipsis_2">
                                    Dingtai Boots Nice High Heel Shoes Womens Winter Boots
                                </div>
                            </div>
                        </a>
                        <div class="goods-price clearfix" style="font-size: 14px;color:#808080;">
                            <div class="fl" style="line-height:26px;">
                                <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">420</span>
                                <span>Sales volume</span>
                            </div>
                            <div class="fr">140%</div>
                        </div>
                        <div>
                            <el-progress :percentage="140" color="#35ca97" :show-text="false"></el-progress>
                        </div>
                        <div class="goods-time">
                            <img src="./images/icon-time.png" alt="">
                            <span style="color:#808080;font-size: 14px;">3 days left</span>
                        </div>
                    </div>
                </div>
                <div class="crowdfunding-item">
                    <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1145" target="_blank">
                        <img src="./images/goods3.jpg" alt="" style="width:271px;height:271px;">
                    </a>
                    <div class="goods-detail">
                        <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1145"
                           target="_blank">
                            <div class="goods-name">
                                FUNDING
                            </div>
                            <div class="goods-title">
                                <div class="ellipsis_2">
                                    Students wild winter hip hop Gaobang men's shoes high-top shoes men's Korean version
                                    of the tide shoes
                                </div>
                            </div>
                        </a>
                        <div class="goods-price clearfix" style="font-size: 14px;color:#808080;">
                            <div class="fl" style="line-height:26px;">
                                <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">50</span>
                                <span>Sales volume</span>
                            </div>
                            <div class="fr">10%</div>
                        </div>
                        <div>
                            <el-progress :percentage="10" color="#35ca97" :show-text="false"></el-progress>
                        </div>
                        <div class="goods-time">
                            <img src="./images/icon-time.png" alt="">
                            <span style="color:#808080;font-size: 14px;">3 days left</span>
                        </div>
                    </div>
                </div>
                <div class="crowdfunding-item">
                    <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=883" target="_blank">
                        <img src="./images/goods4.jpg" alt="" style="width:271px;height:271px;">
                    </a>
                    <div class="goods-detail">
                        <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=883"
                           target="_blank">
                            <div class="goods-name">
                                FUNDING
                            </div>
                            <div class="goods-title">
                                <div class="ellipsis_2">
                                    Romania Wholesale Shoes comfortable casual canvas shoes
                                </div>
                            </div>
                        </a>
                        <div class="goods-price clearfix" style="font-size: 14px;color:#808080;">
                            <div class="fl" style="line-height:26px;">
                                <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">300</span>
                                <span>Sales volume</span>
                            </div>
                            <div class="fr">60%</div>
                        </div>
                        <div>
                            <el-progress :percentage="60" color="#35ca97" :show-text="false"></el-progress>
                        </div>
                        <div class="goods-time">
                            <img src="./images/icon-time.png" alt="">
                            <span style="color:#808080;font-size: 14px;">3 days left</span>
                        </div>
                    </div>
                </div>

            </div>
            <div class="clearfix">
                <a href="https://www.shoestp.com/home/Activity_Romania_classifyactivity" target="_blank"
                   class="blueFontColor fr">See All Products ></a>
            </div>
        </div>
    </div>

    <!-- 林嘉祥 -->
    <div class="ljx">
        <div class="footer-1">
            <div>
                <div class="right">
                    <h3>Subscribe Now！</h3>
                    <p>Receive The Latest Offers and Promotions Free your Email Subscribe</p>
                    <input type="text" v-model="form.email" placeholder="Your Email"><span
                        @click="subscribe">Subscribe</span>
                </div>
                <div class="left">
                    <h3>Follow Us</h3>
                    <div class="logos">

                        <a href="http://www.facebook.com/share.php?src=bm&v=4&u=https%3A%2F%2Fwww.shoestp.com%2Fhome%2Fusr_UsrPurchase&t=facebook"><img
                                src="images/ljxlogo1.png" alt=""></a>
                        <a href="https://twitter.com/intent/tweet?status=twitter:+https%3A%2F%2Fwww.shoestp.com%2Fhome%2Fusr_UsrPurchase"><img
                                src="images/ljxlogo2.png" alt=""></a>
                        <a href="https://www.baidu.com/link?url=U7A1o2bi8aWiIxRt-ojE-oGQL6-OE9SPkWyhfUBtayRPB4d2lKrLs21wYbw_m0Qx&wd=&eqid=ad5b5a8400044197000000045c138692"><img
                                src="images/ljxlogo3.png" alt=""></a>
                        <a href="http://www.linkedin.com/cws/share?url=https%3A%2F%2Fwww.shoestp.com%2Fhome%2Fusr_UsrPurchase&title=linkedin"><img
                                src="images/ljxlogo4.png" alt=""></a>
                        <a href="javascript: void(0);"><img src="images/ljxlogo5.png" alt=""></a>
                        <a href="javascript: void(0);"><img src="images/ljxlogo6.png" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-2">
            <div>
                <ul class="licon">
                    <li class="lititle"><a href="javascript: void(0);">Customer Services</a></li>
                    <li class="libody"><a href="javascript: void(0);">About Us</a></li>
                    <li class="libody"><a href="javascript: void(0);">Wholesaler Market</a></li>
                </ul>
                <ul class="licon">
                    <li class="lititle"><a href="javascript: void(0);">Contact Us</a></li>
                    <!-- <li class="libody">Sitemap</li> -->
                    <li class="libody"><a href="javascript: void(0);">Sitemap</a></li>
                    <li class="libody"><a href="javascript: void(0);">Buy on shoestp.com</a></li>
                    <li class="libody"><a href="javascript: void(0);">Request for Quotation</a></li>
                    <li class="libody"><a href="javascript: void(0);">Wholesaler Market</a></li>
                    <li class="libody"><a href="javascript: void(0);">Sell on shoestp.com</a></li>
                    <li class="libody"><a href="javascript: void(0);">Supplier Memberships</a></li>
                </ul>
                <ul class="licon">
                    <li class="lititle"><a href="javascript: void(0);">Policies & Rules</a></li>
                    <li class="libody"><a href="javascript: void(0);">Buy on shoestp.com</a></li>
                    <li class="libody"><a href="javascript: void(0);">FAQ</a></li>
                </ul>
                <ul class="licon">
                    <li class="lititle"><a href="javascript: void(0);">About shoestp.com</a></li>
                    <li class="libody"><a href="javascript: void(0);">Request for Quotation</a></li>
                    <li class="libody"><a href="javascript: void(0);">FAQ</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-3">
            <h3>版权所有©2016-2017温州新联实业股份有限公司。版权所有。浙ICP备16034166号-1浙公网安备33030402000493号 </h3>
        </div>
    </div>

</div>

<script>
    new Vue({
        el: "#mix",
        data() {
            return {
                manShoesList: [],
                womanShoesList: [],
                childrenShoesList: [],

                selectedCategory: 1, //选中的男、女、童分类
                goodsCategorySwiper: null, //轮播命名变量

                form: {
                    email: ""
                },

                newProductsList: [], //新品 - x夏超
                RandomPdtList: [], // 随机商品 - x夏超
            }
        },
        mounted() {
            console.log("mounted");
            // 获取男、女、童鞋子列表
            this.getThreeShoesCateList();

            this.goodsCategorySwiper = new Swiper('#xsq .swiper-category-list', {
                loop: true,
                noSwiping: true,
                noSwipingClass: 'stop-swiping',
            });

            this.gtNewProducts();
            this.getRandomPdt();
        },
        methods: {
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return "https://image.shoestp.com" + v + params
            },
            clickToSelectedCategory(index) {
                console.log("index")
                console.log(index)
                this.selectedCategory = index;

                this.goodsCategorySwiper.slideTo(index)
            },

            // 获取男、女、童鞋子列表
            getThreeShoesCateList() {
                axios.get('/home/prm_PrmGroupPurchase_groupshoplist')
                    .then((res) => {
                        console.log("鞋子列表suc");
                        console.log(res);
                        if (res.data.ret != 1) {
                            // this.$alert('Product acquisition failed, please check the network')
                            this.$message.error(res.data.msg);
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
            // 订购
            subscribe() {
                console.log("subscribe")

                if (!this.form.email || this.form.email == "") {
                    this.$message.error("Email cannot be empty, please re-fill");
                    return;
                } else if (!(/[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/.test(this.form.email))) {
                    this.$message.error("The mailbox format is incorrect, please re-fill");
                    return;
                }
                axios.post('/home/usr_UsrSubscribe_ins', Qs.stringify({
                    "bean.email": this.form.email,
                }, {allowDots: true}))
                    .then((res) => {
                        console.log(res)
                        // gtag_report_conversion()
                        // if (res.data.ret == 1) {
                        if (res.data.success) {
                            this.form = {};
                            this.$message.success("Submitted successfully");
                        }
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            },

            gtNewProducts() {
                let _self = this;
                axios.post('/home/pdt_PdtProduct_gtNewProducts', Qs.stringify({ //请求参数
                    page: 1,
                    limit: 8
                }))
                    .then(function (res) {
                        _self.newProductsList = res.data.items;
                        _self.$nextTick(() => {
                            var newProductsSwiper = new Swiper('#new-products-swiper', {
                                slidesPerView: 4,
                                spaceBetween: 30,
                                loop: true,
                                autoplay: {
                                    delay: 2500,
                                    disableOnInteraction: false,
                                },
                                navigation: {
                                    nextEl: '.swiper-button-next',
                                    prevEl: '.swiper-button-prev',
                                },
                            });
                        })
                    })
                    .catch(function (error) {
                        console.error("ERR::FLAG")
                        console.log(error);
                    });
            },
            getRandomPdt() {

                let _self = this;
                axios.get('/home/pdt_PdtProduct_getRandomPdt')
                    .then(function (res) {
                        if (res.data.ret == 1) {
                            _self.RandomPdtList = res.data.result;
                            _self.$nextTick(() => {
                                var brandZoneSwiper = new Swiper('#brand-zone-swiper', {
                                    slidesPerView: 6,
                                    spaceBetween: 12,
                                    loop: true,
                                    autoplay: {
                                        delay: 22500,
                                        disableOnInteraction: false,
                                    },
                                    navigation: {
                                        nextEl: '.swiper-button-next',
                                        prevEl: '.swiper-button-prev',
                                    },
                                });
                            })
                        }
                    })
                    .catch(function (error) {
                        console.error("ERR::FLAG")
                        console.log(error);
                    });
            },
            // Subscribe(){
            // 	let _self = this;
            // 	axios.post('/home/usr_UsrSubscribe_ins', Qs.stringify({ //请求参数
            //
            // 		}))
            // 		.then(function (res) {
            // 			console.log(res)
            // 		})
            // 		.catch(function (error) {
            // 			console.log(error);
            // 		});
            // }
        }
    })


</script>
</body>
</html>
