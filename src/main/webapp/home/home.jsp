<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v2/template/header.jsp"/>
<link rel="stylesheet" href="/home/v2/static/css/base/reset.css"/>
<link rel="stylesheet" href="/home/v2/static/css/base/index.css"/>
<script src='/home/v2/static/js/base/qs.js'></script>
<script src='/home/v2/static/js/base/jquery-1.7.2.min.js'></script>
<script src='/home/v2/static/js/base/swiper.min.js'></script>
<link rel="stylesheet" href="/home/v2/static/css/base/swiper.min.css"/>
<jsp:include page="v2/template/nav.jsp"/>
<div id="mix">
    <!-- 林嘉祥 - 顶部 -->
    <div id="ljx_top">
        <div class="ljx">
            <div class="top-bg-container">
                <el-carousel height="225px" arrow="always" :interval="6000" indicator-position="outside">
                    <el-carousel-item>
                        <a href="/country/Romania-Pantofi-en-gros/romania-index-ro.html"
                           target="_blank"><img src="/home/v2/static/images/home/ljxbanner1.jpg" class="carousel-pic"
                                                alt=""></a>
                    </el-carousel-item>
                    <el-carousel-item>
                        <a href="/home/pdt_PdtProduct" target="_blank"><img
                                src="/home/v2/static/images/home/ljxbanner2.jpg" class="carousel-pic" alt=""></a>
                    </el-carousel-item>
                    <el-carousel-item>
                        <a href="/home/usr_UsrConsult_publicListView" target="_blank"><img
                                src="/home/v2/static/images/home/ljxbanner3.jpg" class="carousel-pic" alt=""></a>
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="ljxcon-1">
                <div class="ljxcon">
                    <div class="ljxshow">
                        <span>His</span>
                        <img src="/home/v2/static/images/home/his.png" alt="">
                    </div>
                    <div class="ljxhidden" style="padding-top: 68px;">
                        <!-- <h4>His</h4> -->
                        <ul>
                            <li>Mens' leather shoes</li>
                            <li>Mens' sneakers</li>
                            <li>Mens' sneakers</li>
                        </ul>
                        <a href="/home/pdt_PdtProduct?cated=373"><span>Learn More</span></a>
                    </div>
                </div>
                <div class="ljxcon">
                    <div class="ljxshow">
                        <span>Her</span>
                        <img src="/home/v2/static/images/home/her.png" alt="">
                    </div>
                    <div class="ljxhidden" style="padding-top: 68px;">
                        <!-- <h4>Her</h4> -->
                        <ul>
                            <li>Womens' high heels</li>
                            <li>Womens' sneakers</li>
                            <li>Womens' sandals</li>
                        </ul>
                        <a href="/home/pdt_PdtProduct?cated=380"><span>Learn More</span></a>
                    </div>
                </div>
                <div class="ljxcon">
                    <div class="ljxshow">
                        <span>Kids</span>
                        <img src="/home/v2/static/images/home/kids.png" alt="">
                    </div>
                    <div class="ljxhidden" style="padding-top: 68px;">
                        <!-- <h4>Kids</h4> -->
                        <ul>
                            <li>Boys' shoes</li>
                            <li>Girls' shoes</li>
                            <li>Childrens' sandals</li>
                        </ul>
                        <a href="/home/pdt_PdtProduct?cated=387"><span>Learn More</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 徐世奇 -->
    <div id="xsq">
        <div class="wide">
            <!-- 鞋款分类wrap -->
            <div class="goods-category-wrap">
                <div class="goods-category-item stop-swiping">
                    <div class="flex-left-right">
                        <div class="descript-box">
                            <div class="big-title">O2O Show Room</div>
                            <div class="content-text">
                                From our exhibitors,
                                <br>
                                selected by analysts
                            </div>
                            <a href="/home/Activity_Romania" target="_blank" class="btn-see-all">See
                                All </a>
                        </div>

                        <div class="right-goods-out-wrap swiper-container swiper-category-list">
                            <div class="slide-title" v-if="$t">
                                {{_o2o_slide_title}}
                            </div>
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <ul class="right-goods-wrap">
                                        <li class="item" v-for="(goods, index) in manShoesList" v-if="index < 4">
                                            <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id"
                                               target="_blank">
                                                <img :src="image(goods.image)" alt="man's shoes" class="goods-pic">
                                                <div class="goods-name">
                                                    <div class="ellipsis_2">
                                                        {{goods.title}}
                                                    </div>
                                                </div>
                                            </a>

                                            <div class="hover-show">
                                                <div class="hover-text-wrap">
                                                    <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id"
                                                       target="_blank">
                                                        <div class="goods-name2 ellipsis_3">
                                                            {{goods.title}}
                                                        </div>
                                                    </a>
                                                    <div class="price" v-if="goods.price > 0">
                                                        Price: {{sysConfig.currency_symbol}}{{goods.price}}
                                                    </div>
                                                    <div class="tips" v-if="goods.min_order > 0">Min.order:
                                                        {{goods.min_order}} pairs
                                                    </div>
                                                </div>

                                                <div class="btn-group">
                                                    <div class="btn-inquiry btn-blue" @click="inquiry(goods.id)">
                                                        <div class="icon icon-inquiry"></div>
                                                        Inquiry
                                                    </div>
                                                    <div class="btn-collect btn-gray" favorite-type="man"
                                                         @click="addCollection($event,goods.productid,index)">
                                                        <div class="icon icon-collect"
                                                             :class="{on:goods.favorite}"></div>
                                                        Save
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                                <div class="swiper-slide">
                                    <!-- <div class="slide-title">Women's shoes</div> -->
                                    <ul class="right-goods-wrap">
                                        <li class="item" v-for="(goods, index) in womanShoesList" v-if="index < 4">
                                            <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id"
                                               target="_blank">
                                                <img :src="image(goods.image)" alt="woman's shoes" class="goods-pic">
                                                <div class="goods-name ">
                                                    <div class="ellipsis_2">
                                                        {{goods.title}}
                                                    </div>
                                                </div>
                                            </a>

                                            <div class="hover-show">
                                                <div class="hover-text-wrap">
                                                    <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id"
                                                       target="_blank">
                                                        <div class="goods-name2 ellipsis_3">
                                                            {{goods.title}}
                                                        </div>
                                                    </a>
                                                    <div class="price" v-if="goods.price > 0">
                                                        Price:{{sysConfig.currency_symbol}} {{goods.price}}
                                                    </div>
                                                    <div class="tips" v-if="goods.min_order > 0">Min.order:
                                                        {{goods.min_order}} pairs
                                                    </div>
                                                </div>

                                                <div class="btn-group">
                                                    <div class="btn-inquiry btn-blue" @click="inquiry(goods.id)">
                                                        <div class="icon icon-inquiry"></div>
                                                        Inquiry
                                                    </div>
                                                    <div class="btn-collect btn-gray" favorite-type="woman"
                                                         @click="addCollection($event,goods.productid,index)">
                                                        <div class="icon icon-collect"
                                                             :class="{on:goods.favorite}"></div>
                                                        Save
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                                <div class="swiper-slide">
                                    <!-- <div class="slide-title">Children's shoes</div> -->
                                    <ul class="right-goods-wrap ">
                                        <li class="item" v-for="(goods, index) in childrenShoesList" v-if="index < 4">
                                            <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id"
                                               target="_blank">
                                                <img :src="image(goods.image)" alt="children's shoes" class="goods-pic">
                                                <div class="goods-name ">
                                                    <div class="ellipsis_2">
                                                        {{goods.title}}
                                                    </div>
                                                </div>
                                            </a>

                                            <div class="hover-show">
                                                <div class="hover-text-wrap">
                                                    <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id"
                                                       target="_blank">
                                                        <div class="goods-name2 ellipsis_3">
                                                            {{goods.title}}
                                                        </div>
                                                    </a>
                                                    <div class="price" v-if="goods.price > 0">
                                                        Price:{{sysConfig.currency_symbol}} {{goods.price}}
                                                    </div>
                                                    <div class="tips" v-if="goods.min_order > 0">Min.order:
                                                        {{goods.min_order}} pairs
                                                    </div>
                                                </div>

                                                <div class="btn-group">
                                                    <div class="btn-inquiry btn-blue" @click="inquiry(goods.id)">
                                                        <div class="icon icon-inquiry"></div>
                                                        Inquiry
                                                    </div>
                                                    <div class="btn-collect btn-gray" favorite-type="children"
                                                         @click="addCollection($event,goods.productid,index)">
                                                        <div class="icon icon-collect"
                                                             :class="{on:goods.favorite}"></div>
                                                        Save
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 男鞋、女、童的nav wrap -->
            <ul class="goods-category-nav-wrap">
                <li class="goods-category-nav-item" :class="{active: selectedCategory==1}"
                    @click="clickToSelectedCategory(1)">
                    <img src="/home/v2/static/images/home/icon-man-shoes_off.png" alt="" class="icon icon-off icon-man">
                    <img src="/home/v2/static/images/home/icon-man-shoes_on.png" alt="" class="icon icon-on icon-man">
                    <span>His</span>
                </li>
                <li class="goods-category-nav-item" :class="{active: selectedCategory==2}"
                    @click="clickToSelectedCategory(2)">
                    <img src="/home/v2/static/images/home/icon-woman-shoes_off.png" alt=""
                         class="icon icon-off icon-woman">
                    <img src="/home/v2/static/images/home/icon-woman-shoes_on.png" alt=""
                         class="icon icon-on icon-wpman">
                    <span>Her</span>
                </li>
                <li class="goods-category-nav-item" :class="{active: selectedCategory==3}"
                    @click="clickToSelectedCategory(3)">
                    <img src="/home/v2/static/images/home/icon-child-shoes_off.png" alt=""
                         class="icon icon-off icon-child">
                    <img src="/home/v2/static/images/home/icon-child-shoes_on.png" alt=""
                         class="icon icon-on icon-child">
                    <span>Kids</span>
                </li>
            </ul>

        </div>
    </div>

    <!-- 林铁原 -->
    <div id="lty">
        <div class="hotCouny">
            <h2 style="font-size: 40px;height: 110px;line-height: 140px;font-weight: 500;border-bottom: 1px solid #ccc;margin-bottom: 20px;">
                Trade Shows </h2>
            <ul class="top">
                <!-- <li class="on"><img src="images/couny1.jpg" alt="" />Romania<i></i></li> -->
                <li><img src="/home/v2/static/images/home/couny1.jpg" alt=""/>Romania<i></i></li>
                <li><img src="/home/v2/static/images/home/couny2.jpg" alt=""/>Brazil<i></i></li>
                <li><img src="/home/v2/static/images/home/couny3.jpg" alt=""/>Russia<i></i></li>
                <li><img src="/home/v2/static/images/home/couny4.jpg" alt=""/>Italy<i></i></li>
            </ul>
            <div class="com">
                <div class="left fl">
                    <div class="left-title">
                        500-800<br/>pairs of
                    </div>
                    <div class="left-title_2">
                        premium shoes
                    </div>
                    <dl>
                        <!-- <dd>500-800 pairs of premium shoes</dd> -->
                        <dd><i></i>More than 500 shoe companies were selected</dd>
                        <!-- <dd><i></i>500-800 pairs of premium shoes</dd> -->
                        <dd><i></i>24 hours customer service</dd>
                    </dl>
                    <a class="btn" href="/country/Romania-Pantofi-en-gros/romania-index-ro.html"
                       style="display: block;"><p>Register Now</p></a>
                </div>
                <ul class="fr">
                    <li>
                        <div class="pic1">
                            <img src="/home/v2/static/images/home/counyCom0.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="/home/v2/static/images/home/counyicon2.png" alt="" class="pic2"/>
                            <p>About Show</p>
                        </div>
                    </li>
                    <li>
                        <div class="pic1">
                            <img src="/home/v2/static/images/home/counyCom1.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="/home/v2/static/images/home/counyicon1.png" alt="" class="pic2"/>
                            <p>
                                Who Should
                                <br>
                                Visit
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="pic1">
                            <img src="/home/v2/static/images/home/counyCom2.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="/home/v2/static/images/home/counyicon3.png" alt="" class="pic2"/>
                            <p>Exhibitor List </p>
                        </div>
                    </li>
                    <li>
                        <div class="pic1">
                            <img src="/home/v2/static/images/home/counyCom3.jpg" alt=""/>
                        </div>
                        <div class="fr tite">
                            <img src="/home/v2/static/images/home/counyicon1.png" alt="" class="pic2"/>
                            <p>
                                Who Should
                                <br>
                                Exhibit
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 夏超 -->
    <div id="xc">
        <!-- New Products -->
        <div class="new-products-container">
            <div class="title" style="margin-top: 21px;">New Products</div>
            <div class="tip clearfix">
                <span class="tip-title fl">Free Alerts on newly added products! </span>
                <input type="email" v-model="form.email" class="tip-input fl" placeholder="Example:jsun@company.com"/>
                <!-- {{bean.email}} -->
                <div class="tip-btn fl" @click="subscribe">Subscribe</div>
            </div>
            <!-- Swiper -->
            <div style=" width:1240px;height:388px;position:relative;left:0;" class="new-swiper">
                <div class="swiper-container swiper-no-swiping" id="new-products-swiper" style="width:1240px;">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide" v-cloak v-for="(item, index) in newProductsList" :key="index">
                            <a :href="'/'+item.rewrite" target="_blank">
                                <img :src="image(item.image)" alt=""  style="width:100%;height:217px;object-fit: contain;">
                            </a>
                            <div class="goods-info">
                                <a :href="'/'+item.rewrite" target="_blank">
                                    <div>
                                        {{item.title}}
                                    </div>
                                    <div>
                                        <div style="color: #2435a1;">
                                            Price:{{sysConfig.currency_symbol}}{{item.price}}
                                        </div>
                                        <div>
                                            Min.Order:{{item.min_order}} pairs
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="new-slide-show transformY">
                                <a :href="'/'+item.rewrite" target="_blank">
                                    <div class="new-slide-show-name">
                                        {{item.title}}

                                    </div>
                                </a>
                                <div class="btn-list clearfix">
                                    <div class="inquiry-btn fl btn" @click="inquiry(item.id)"><img
                                            src="/home/v2/static/images/base/icon-inquiry.png" alt="">
                                        Inquiry
                                    </div>
                                    <div class="favorite-btn fr btn" favorite-type="1"
                                         @click="addCollection($event,item.id,index)">
                                        <template v-if="item.favorite">
                                            <img src="/home/v2/static/images/base/icon-favorite-on.png" alt=""
                                                 style="width:18px;height:16px;"> Saveed
                                        </template>
                                        <template v-else>
                                            <img src="/home/v2/static/images/base/icon-favorite-off.png" alt=""
                                                 style="width:19px;height:16px;"> Save
                                        </template>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!--  @mouseover="pageOver($event)" -->
                    <div class="swiper-pagination" id="newPage"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>
            <div class="clearfix">
                <a href="/home/pdt_PdtProduct?orderfld=New" target="_blank"
                   class="blueFontColor fr">See
                    All Products ></a>
            </div>
        </div>
    </div>

    <!-- 林华力 -->
    <div id="home">
        <div class="top10" style="width: 1240px;margin: 0 auto;">
            <h2 style="font-size: 40px;height: 100px;line-height: 126px;font-weight: 500;border-bottom: 1px solid #c4c4c4">
                Top10 Reliable Suppliers</h2>
            <p style="font-size: 18px;color: #000;line-height: 50px;height: 40px;">Free Alerts on newly added
                products! </p>
            <div class="top10-03" style="width: 100%;position: relative">
                <img src="/home/v2/static/images/home/top10-01-1.png" alt="">
                <!-- 六边形logo定位 -->
                <div class="hexagon-list-wrap">
                    <a class="hexagon-wrap"
                       :href="'/home/usr_UsrSupplier_gtSupIndex?pkey=' + company.pkey"
                       target="_blank"
                       v-for="company in companys">
                        <div class="hexagon-center hexagon-common"></div>
                        <div class="hexagon-left hexagon-common"></div>
                        <div class="hexagon-right hexagon-common"></div>
                        <img :src="company.imgUrl" alt="logo" class="hexagon-inner">
                    </a>
                </div>

            </div>
            <h2 style="font-size: 40px;height: 80px;line-height: 90px;font-weight: 500;border-bottom: 1px solid #c4c4c4">
                Request
                For Quotation</h2>
            <p style="font-size: 18px;color: #000;height: 60px;line-height: 50px">Customization Service</p>
            <a href="/home/usr_UsrConsult_listView">
                <div class="top10-01"
                     style="display: inline-block;width: 787px;height: 377px;background: url('/home/v2/static/images/home/top10-02.png') center no-repeat;background-size: cover;padding-left: 40px;padding-top: 25px;vertical-align: top">
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
                     style="display: inline-block;width: 435px;height: 377px;border: 7px solid #c4c4c4;vertical-align: top;margin-left: 17px;padding: 20px">

                    <h2 style="font-size: 40px;line-height: 50px;">One Request, Multiple Quotes</h2>
                    <form action="">
                        <input type="text" placeholder="what are you looking for..."
                               style="width: 100%;height: 40px;border: 1px solid #c4c4c4;margin: 10px 0;text-indent: 10px">
                        <input type="text" placeholder="Quantity"
                               style="width: 60%;height: 40px;border: 1px solid #c4c4c4;margin: 10px 0;text-indent: 10px;vertical-align: top">
                        <span style="display:inline-block;width: 39%;height: 44px;border: 1px solid #c4c4c4;margin: 10px 0;text-indent: 10px;margin-left:-5px;font-size: 16px;line-height: 42px;color: #a1a1a1;">Bag/Bags</span>
                        <p style="font-size: 16px;line-height: 42px;color: #a1a1a1;">Select template type :</p>
                        <a href="javascript:void(0);"
                           style="display: inline-block;width: 80%;height: 40px;font-size: 18px;line-height: 40px;color: #fff;background-color: #10389c;text-align: center;border-radius: 10px">Request
                            For Quotation</a>
                    </form>
                </div>
            </a>
        </div>
    </div>

    <!-- 夏超 -->
    <div id="xc">
        <!-- BRAND ZONE  -->
        <div class="brand-zone-container">
            <div class="title" style="margin-top: 20px;">Brand Zone</div>
            <div style="margin:34px 0 20px 0;" class="brand-zone-title">
                <div class="name">
                    <div>Buy Fashion Shoes Instantly</div>
                    <a href="/home/pdt_PdtProduct" target="_blank">Order Now</a>
                </div>
                <div class="clearfix list">
                    <div class="fl">
                        <img src="/home/v2/static/images/home/icon-gouwuche.png" alt="">
                        <div>Speedy <br>
                            Checkout
                        </div>
                    </div>
                    <div class="fl">
                        <img src="/home/v2/static/images/home/icon-wuliu.png" alt="">
                        <div>Price Includes <br>
                            Shipping
                        </div>
                    </div>
                    <div class="fl">
                        <img src="/home/v2/static/images/home/icon-zhiliang.png" alt="">
                        <div> Secure <br>
                            Transactions
                        </div>
                    </div>
                </div>
            </div>
            <div style=" width:1240px;height:271px;position:relative;left:0;">
                <div class="swiper-container swiper-no-swiping" id="brand-zone-swiper" style="width:1240px;">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide" v-cloak
                             v-for="(item,index) in RandomPdtList" :key="index">
                            <a :href="'/'+item.rewrite" target="_blank">
                                <img :src="image(item.image)" alt=""
                                     style="width: 100%;height: 162px;object-fit: contain;">
                                <div class="goods-info">
                                    <div>
                                        {{item.title}}
                                    </div>
                                </div>
                            </a>
                            <div class="brand-slide-show transformY flexSb">
                                <a :href="'/'+item.rewrite" target="_blank">
                                    <div class="brand-slide-show-name ellipsis_2">{{item.title}}</div>
                                    <div class="brand-price">
                                        <div>Price:{{sysConfig.currency_symbol}}{{item.price}}</div>
                                        <div>Min.Order:{{item.min_order}} pairs</div>
                                    </div>
                                </a>
                                <div class="btn-list flexSb">
                                    <div class="inquiry-btn btn" @click="inquiry(item.id)"><img
                                            src="/home/v2/static/images/base/icon-inquiry.png" alt=""
                                            style="width:13px;height:11px;">
                                        Inquiry
                                    </div>
                                    <div class="favorite-btn btn" :favorite-type="2"
                                         @click="addCollection($event,item.id,index)">
                                        <template v-if="item.favorite">
                                            <img src="/home/v2/static/images/base/icon-favorite-on.png" alt=""
                                                 style="width:15px;height:14px;"> Saveed
                                        </template>
                                        <template v-else>
                                            <img src="/home/v2/static/images/base/icon-favorite-off.png" alt=""
                                                 style="width:15px;height:14px;"> Save
                                        </template>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- <div class="swiper-pagination"></div> -->
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>

            <div class="clearfix">
                <a href="/home/pdt_PdtProduct" target="_blank" class="blueFontColor fr">See All
                    Products ></a>
            </div>
        </div>
        <!-- CROWDFUNDING -->
        <div class="crowdfunding-container" style="margin-bottom:15px;">
            <div class="clearfix" style="border-bottom: 1px solid #cccccc;">
                <div class="title fl" style="border:none;line-height: normal;margin-bottom:25px;">Crowdfunding</div>
                <div class="tip clearfix fl" style="margin:12px 0 0 15px;">
                    <ul class="tip-list clearfix">
                        <li class="fl">Lower price in small number</li>
                        <li class="fl" style="margin-left:40px;">Fashion Design will be published</li>
                    </ul>
                </div>
            </div>
            <div style=" width:1280px;height:462px;position:relative;left:-40px;margin-top:35px;">
                <div class="swiper-container swiper-no-swiping" id="crowdfunding-swiper" style="width:1200px;">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=977"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods1.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=977"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                Leather shoes male Korean version of the trend of leather shoes men's
                                                casual shoes autumn men's shoes tide
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
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
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=870"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods2.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=870"
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
                                    <div class="goods-price clearfix">
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
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1145"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods3.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1145"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                Students wild winter hip hop Gaobang men's shoes high-top shoes men's
                                                Korean version of the tide shoes
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
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
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=883"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods4.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=883"
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
                                    <div class="goods-price clearfix">
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
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=845"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods5.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=845"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                Wholesale Summer New Design Beaches sandals Men's sandals
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">500</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">83%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1073"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods6.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1073"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                Ins tide shoes men's winter old shoes Korean version of ulzzang new
                                                color matching white shoes street
                                                shooting men's shoes leather shoes
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">300</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">60%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1143"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods7.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1143"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                High heels female autumn 2018 new rivet pointed 12 stiletto single shoes
                                                wild Baotou Roman sandals summer
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">650</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">81%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1026"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods8.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1026"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                New Style Ballet Shoes Wholesale Women Shoes Leather Flat Shoes
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">200</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">40%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1094"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods9.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1094"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                2019 summer soft composite bottom bow toe sandals Velcro
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">600</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">75%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1133"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods10.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1133"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                2018 new round head British wind small shoes low heel tassel fashion
                                                shoes patent leather wild children's
                                                shoes
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">400</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">66%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=822"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods12.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=822"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                Women Slippers Shoes Summer Female Fashion Shoes beach Shoes Slippers
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">550</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">110%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="crowdfunding-item">
                                <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1054"
                                   target="_blank">
                                    <img src="/home/v2/static/images/home/temp/goods11.jpg" alt=""
                                         style="width:269.5px;height:271px;">
                                </a>
                                <div class="goods-detail">
                                    <a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1054"
                                       target="_blank">
                                        <div class="goods-name">
                                            FUNDING
                                        </div>
                                        <div class="goods-title">
                                            <div class="ellipsis_2">
                                                Classic Fashion Cheap Flat Shoes Men Casual
                                            </div>
                                        </div>
                                    </a>
                                    <div class="goods-price clearfix">
                                        <div class="fl" style="line-height:26px;">
                                            <span style="color: #2a2a2a;font-weight: bold;font-size: 18px;">550</span>
                                            <span>Sales volume</span>
                                        </div>
                                        <div class="fr">69%</div>
                                    </div>
                                    <div>
                                        <el-progress :percentage="83" color="#35ca97" :show-text="false"></el-progress>
                                    </div>
                                    <div class="goods-time">
                                        <img src="/home/v2/static/images/home/icon-time.png" alt="">
                                        <span style="color:#808080;font-size: 14px;">1 days left</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- <div class="swiper-pagination"></div> -->
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>
            <div class="clearfix">
                <a href="/home/Activity_Romania_classifyactivity" target="_blank"
                   class="blueFontColor fr">See
                    All Products ></a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="v2/template/foot.jsp"></jsp:include>
<script>
    var app = new Vue({
        el: "#mix", i18n,
        data() {
            return {
                manShoesList: [],
                womanShoesList: [],
                childrenShoesList: [],
                selectedCategory: 1, //选中的男、女、童分类
                goodsCategorySwiper: null, //轮播命名变量 - 自定义切换 使用
                form: { 	//提交 订阅 功能用
                    email: ""
                },
                newProductsList: [], //新品
                RandomPdtList: [], // 随机商品
                companys: [
                    {pkey: 281, imgUrl: "/home/v2/static/images/home/company-logo_01.png"},
                    {pkey: 30, imgUrl: "/home/v2/static/images/home/company-logo_02.png"},
                    {pkey: 298, imgUrl: "/home/v2/static/images/home/company-logo_03.png"},
                    {pkey: 283, imgUrl: "/home/v2/static/images/home/company-logo_04.png"},
                    {pkey: 12, imgUrl: "/home/v2/static/images/home/company-logo_05.png"},
                    {pkey: 16, imgUrl: "/home/v2/static/images/home/company-logo_06.png"},
                    {pkey: 282, imgUrl: "/home/v2/static/images/home/company-logo_07.png"},
                    {pkey: 317, imgUrl: "/home/v2/static/images/home/company-logo_08.png"},
                    {pkey: 295, imgUrl: "/home/v2/static/images/home/company-logo_09.png"},
                    {pkey: 23, imgUrl: "/home/v2/static/images/home/company-logo_10.png"},

                ]
            }
        },
        computed: {
            _o2o_slide_title: function () {
                switch (this.$data.selectedCategory) {
                    case 1:
                        return "Men's Shoes";
                    case 2:
                        return "Women's Shoes";
                    case 3:
                        return "Children's Shoes"
                }

            }
        },
        mounted() {
            this.getThreeShoesCateList();
            this.goodsCategorySwiper = new Swiper('#xsq .swiper-category-list', {
                loop: true,
                noSwiping: true,
                noSwipingClass: 'stop-swiping',
            });
            this.gtNewProducts();
            this.getRandomPdt();
            var crowdfundingSwiper = new Swiper('#crowdfunding-swiper', {
                slidesPerView: 4,
                spaceBetween: 35,
                slidesPerGroup: 4,
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
            });
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
                axios.get('/home/prm_PrmGroupPurchase_groupshoplist?v=2')
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

            gtNewProducts() { // 获取最新商品
                let _self = this;
                axios.post('/home/pdt_PdtProduct_gtNewProducts', Qs.stringify({ //请求参数
                    page: 1,
                    limit: 12,
                    v: 2,
                }))
                    .then(function (res) {
                        console.log(res)
                        if (res.data.ret == 1) {
                            _self.newProductsList = res.data.result;
                            _self.$nextTick(() => {
                                setTimeout(() => {
                                    var newProductsSwiper = new Swiper('#new-products-swiper', {
                                        slidesPerView: 4,
                                        spaceBetween: 25,
                                        slidesPerGroup: 4,
                                        pagination: {
                                            el: '.swiper-pagination',
                                        },
                                        navigation: {
                                            nextEl: '.swiper-button-next',
                                            prevEl: '.swiper-button-prev',
                                        },
                                    });

                                    $('#new-products-swiper .swiper-pagination').on('mouseenter', '.swiper-pagination-bullet', function () {
                                        var index = parseInt($(this).index());
                                        if (index == 0) {
                                            newProductsSwiper.slideTo(index * 4);
                                        } else {
                                            newProductsSwiper.slideTo(index * 4 + 1);
                                        }
                                    })
                                }, 50)


                            })
                        } else {
                            console.error("WARRING::INVALIDVALUE")
                        }
                    })
                    .catch(function (error) {
                        console.error("ERR::FLAG")
                        console.log(error);
                    });
            },
            getRandomPdt() { //获取随机商品
                let _self = this;
                axios.get('/home/pdt_PdtProduct_getRandomPdt?v=2&page=1&limit=17')
                    .then(function (res) {
                        console.log(res)
                        if (res.data.ret == 1) {
                            _self.RandomPdtList = res.data.result;
                            _self.$nextTick(() => {
                                var brandZoneSwiper = new Swiper('#brand-zone-swiper', {
                                    slidesPerView: 6,
                                    spaceBetween: 12,
                                    slidesPerGroup: 6,
                                    pagination: {
                                        el: '.swiper-pagination',
                                    },
                                    navigation: {
                                        nextEl: '.swiper-button-next',
                                        prevEl: '.swiper-button-prev',
                                    },
                                });
                            })
                        } else {
                            console.error("WARRING::INVALIDVALUE")
                        }
                    })
                    .catch(function (error) {
                        console.error("ERR::FLAG")
                    });
            },
            inquiry(id) { // 点击 询盘
                if (!isLogin) {
                    this.$alert('Please login to operate', 'Please login to operate', {
                        confirmButtonText: 'Ok',
                        callback: action => {
                            window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/"
                        }
                    });
                    return
                } else {
                    // console.log("已登录")
                    axios.post('/home/pdt_PdtConsultPdtList_add', Qs.stringify({ //请求参数
                        product: id,
                    }))
                        .then((data) => {
                            if (data.data) {
                                if (data.data.ret && data.data.ret != 1) {
                                    console.error("ERR::FLAG")
                                }
                            }
                            // this.productId = res.data.result.id
                            // this.$confirm('成功添加到您的询盘！', { // 是否跳转到询盘页面
                            //     confirmButtonText: '现在询价',
                            //     cancelButtonText: '继续逛逛',
                            //     type: 'success'
                            // }).then(() => {
                            //     window.location.href = "/home/usr_UsrConsult_publishView?product_id=" + this.productId
                            // }).catch((error) => {
                            //     // this.$message.error(error);
                            // });
                        })
                        .catch((error) => {
                            this.$message.error(error);
                            console.error("ERR::FLAG")
                        });
                }
            },
            addCollection(event, id, index) { // 新商品添加收藏
                let favoriteType = event.currentTarget.getAttribute('favorite-type');
                console.log("addCollection")
                console.log(favoriteType)
                if (!isLogin) {
                    // user_obj.set_form_sign_in('', window.location.href, 1);
                    // return
                    this.$alert('Please login to operate', 'Please login to operate', {
                        confirmButtonText: 'Ok',
                        callback: action => {
                            window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/"
                        }
                    });

                } else {
                    axios.post('/home/usr_UsrFavorites_addFavorite', Qs.stringify({ //请求参数
                        pdtPkey: id,
                    }))
                        .then((res) => {
                            if (res.data.ret == 1) {
                                if (favoriteType == 1) {
                                    this.newProductsList[index].favorite = !this.newProductsList[index].favorite
                                } else if (favoriteType == 2) {
                                    this.RandomPdtList[index].favorite = !this.RandomPdtList[index].favorite
                                } else if (favoriteType == "man") {
                                    this.manShoesList[index].favorite = !this.manShoesList[index].favorite
                                } else if (favoriteType == "woman") {
                                    this.womanShoesList[index].favorite = !this.womanShoesList[index].favorite
                                } else if (favoriteType == "children") {
                                    this.childrenShoesList[index].favorite = !this.childrenShoesList[index].favorite
                                }
                            }
                        })
                        .catch((error) => {
                            this.$message.error(error);
                        });
                }
            },
        }
    })


</script>
</body>
</html>
