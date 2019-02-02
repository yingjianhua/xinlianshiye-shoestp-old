<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/home/v2/template/header.jsp"></jsp:include>
 <link rel="stylesheet" href="css/index.css">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="./js/config.js"></script>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>

<div id="lhl-highClass">
    <o2o-top :show="1"></o2o-top>
    <!---->
    <div class="wrap main-head">
        <img src="images/banner-hc-text.png" alt="" class="head-textImg">
    </div>
    <!---->
    <div class="wrap">
        <div class="main-operate">
            <div class="main-operate-title">
                <h2 class="title-h2">How To Operate</h2>
                <p class="title-span">Customer could better know the qualitu of a shoe</p>
                <p class="title-wire"></p>
            </div>
            <div class="main-operate-content">
                <div class="operate-content">
                    <ul>
                        <li :class="overLi == 1 ? 'color-red' : ''" @mouseenter="enterOperate(1)">
                            <div class="rank">1</div>
                            <p class="content-text">We will advertise on social media six months in advance and
                                collection messages to see the footwear requirements of customers required.</p>
                        </li>
                        <li :class="overLi == 2 ? 'color-red' : ''" @mouseenter="enterOperate(2)">
                            <div class="rank">2</div>
                            <p class="content-text">Then we will prepare the next season's
                                shoes according to thoes required.</p>
                        </li>
                        <li :class="overLi == 3 ? 'color-red' : ''" @mouseenter="enterOperate(3)">
                            <div class="rank">3</div>
                            <p class="content-text">In order to make sure the interests of merchants and buyers, we will
                                verify the wholesalers who sign up for the new product show in advance.</p>
                        </li>
                        <li :class="overLi == 4 ? 'color-red' : ''" @mouseenter="enterOperate(4)">
                            <div class="rank">4</div>
                            <p class="content-text">Only those who have received the invitation letter from us will be
                                able to participate in the show. </p>
                        </li>
                    </ul>
                    <p class="content-text content-text01">If a guest sees the favorite style at the exhibition, he (or
                        she) can get the contact information of the corresponding factory directly. </p>
                </div>
                <div class="operate-image">
                    <el-carousel trigger="click" height="361px" :autoplay="false" arrow="never" indicator-position="none" ref="carousel">
                        <el-carousel-item>
                            <img src="images/operate_img01.png" alt="">
                        </el-carousel-item>
                        <el-carousel-item>
                            <img src="images/operate_img01.png" alt="">
                        </el-carousel-item>
                        <el-carousel-item>
                            <img src="images/operate_img01.png" alt="">
                        </el-carousel-item>
                        <el-carousel-item>
                            <img src="images/operate_img01.png" alt="">
                        </el-carousel-item>
                    </el-carousel>
                </div>
            </div>
        </div>
    </div>
    <!---->
    <div class="wrap main-sample">
        <div class="main-sample-content">
            <div class="sample-left">
                <img src="images/highClass-sam_tx_bg.png" alt="">
                <p class="sample-left-text">We will advertise on social media six months in advance and collection
                    messages to see the footwear require-ments of customers required.</p>
                <p class="sample-wire"></p>
            </div>
            <div class="sample-right">
                <div class="sample-item" v-for="item in sampleDetail">
                    <img :src="baseimgurl + item.image+'?x-oss-process=image/resize,w_154,h_154/blur,r_5,s_20'" alt="">
                    <div class="sample-item-text">
                        <p class="sample-text show-line">{{item.title}}</p>
                        <p class="sample-state">US  <span class="text-red">{{sysConfig.currency_symbol}} {{item.price}}</span>
                        <p class="sample-text">Min.Order:{{item.min_order}} pairs</p>
                    </div>
                    <div class="sample-item-model">
                        <a href="javascript: void(0);" class="sample-button" @click="gotoinquery(item.id)">inquery</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--How To Sign Up-->
    <div class="wrap main-sign">
        <h2 class="main-sign-h2">How To Sign Up</h2>
        <p class="main-sign-text">Please fill out the registration form in our O2O webpage and send the registration
            form to the official email address of Shoestp.com. We will send you an invitation card one month before the
            start of the new product show.</p>
        <a href="O2Oinputform.jsp" class="button">On line</a>
    </div>
    <!--Time and location-->
    <div class="wrap main-location">
        <!--谷歌地图-->
        <iframe
            src="http://www.google.cn/maps/embed?pb=!1m18!1m12!1m3!1d5860766.60791944!2d18.330279800725073!3d44.07126408586853!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1f9ac606033e3%3A0x7d391e85ce7a445b!2z57qi6b6Z5Lit5Zu95aSn5biC5Zy6!5e0!3m2!1szh-CN!2scn!4v1548317126897"
            width="100%" height="540" frameborder="0" style="border:0;position: relative;z-index:99;"
            allowfullscreen></iframe>
        <!---->
        <div class="location-content">
            <div class="location-content-tier">
                <div class="location-introduce">
                    <h2 class="introduce-h2">Time and Location</h2>
                    <p class="introduce-text">Could Better Know The Quality Of A Shoes</p>
                    <p class="introduce-wire"></p>
                    <p class="introduce-text">We will publish the location and time of the new product show meeting at
                        the Shoestp.com three months in advance. The show will generally last for 7 days.</p>
                </div>
            </div>
        </div>
    </div>
    <o2o-bottom></o2o-bottom>
</div>
<script src="components/O2O-top.js"></script>
<script src="components/O2O-bottom.js"></script>
<script>
    new Vue({
        el: '#lhl-highClass',
        data: {
            baseimgurl:'https://image.shoestp.com',
            overLi: 1,
            sampleDetail: [
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1a.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1a.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1a.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1a.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1.png'
                },
                {
                    text: 'Autumn and winter retro outdoor men\'s shoes  ...',
                    require: 'Min.Order:600 pairs',
                    state: 'US',
                    price: 7.9,
                    image: 'images/highClass-pro_1.png'
                }
            ]
        },
        mounted() {
            this.getporduct();
        },
        methods: {
            enterOperate(num) {
                this.overLi = num;
                this.$refs.carousel.setActiveItem(num-1);
            },
            getporduct:function () {
                let self = this;
                axios.get('/home/o2o_O2oRegistration_o2oPrivateList', {
                    params: {
                        start:0,
                        limit:7
                    }
                }).then(function (res) {
                    let data = res.data.result;
                    if(res.data.ret == 1){
                        self.$set(self,'sampleDetail',data)
                    }
                })
            },
            gotoinquery:function (id) {
                window.location = '/home/usr_UsrConsult_productPublishView?product_id=' +  id;
            }
        }
    })
</script>
</body>
</html>
