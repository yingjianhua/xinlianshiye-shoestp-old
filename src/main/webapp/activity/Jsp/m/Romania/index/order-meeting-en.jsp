<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
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
    <!-- <link rel="stylesheet" href="./css/reset.css" media="all"> -->
    <link rel="stylesheet" href="/activity/Jsp/m/Romania/index/css/index.css" media="all">
    <link rel="stylesheet" href="/activity/Jsp/m/Romania/index/css/muse-ui.css" media="all">
    <link rel="stylesheet" href="/activity/Jsp/m/Romania/index/css/muse-ui-toast.all.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/material-design-icons/3.0.1/iconfont/material-icons.css">
    <script src="/activity/Jsp/m/Romania/index/js/jquery-1.7.2.min.js"></script>
    <script src="/activity/Jsp/m/Romania/index/js/vue.js"></script>
    <script src="/activity/Jsp/m/Romania/index/js/axios.min.js"></script>
    <script src="/activity/Jsp/m/Romania/index/js/qs.js"></script>
    <script src="/activity/Jsp/m/Romania/index/js/muse-ui.js"></script>
    <script src="/activity/Jsp/m/Romania/index/js/muse-ui-toast.js"></script>

    <link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
    <link href="/home/static/themes/default/mobile/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/themes/default/mobile/css/style.css" rel="stylesheet" type="text/css">
    <%-- <link href="/home/static/themes/default/mobile/css/style_new.css" rel="stylesheet" type="text/css"> --%>
    <link href="/home/static/themes/default/mobile/css/style(1).css" rel="stylesheet"
          type="text/css">
    <link href="/home/static/themes/default/mobile/css/user.css" rel="stylesheet" type="text/css">
    <%--<link href="/home/static/themes/default/mobile/css/mobile_time.min.css" rel="stylesheet" type="text/css">--%>
    <link href="/home/static/themes/default/mobile/css/layer-mobile.css" rel="stylesheet"
          type="text/css">
    <link href="/home/static/themes/default/mobile/css/default.css" rel="stylesheet"
          type="text/css">


    <script type="text/javascript"
            src="/home/static/themes/default/mobile/js/jquery-min.js"></script>
    <script type="text/javascript"
            src="/home/static/themes/default/mobile/js/layer-mobile.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/global.js"></script>
    <script type="text/javascript"
            src="/home/static/themes/default/mobile/js/global(1).js"></script>
    <script type="text/javascript"
            src="/home/static/themes/default/mobile/js/rye-touch.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/en.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/user.js"></script>
    <script type="text/javascript"
            src="/home/static/themes/default/mobile/js/mobile_time.min.js"></script>
    <%--<script type="text/javascript" src="/home/static/themes/default/mobile/js/saved_resource"></script>--%>
    <%--<script type="text/javascript" src="/home/static/themes/default/mobile/js/get_dynamic_config.js"></script>--%>

    <%--<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>--%>
    <%--<script src="https://unpkg.com/axios/dist/axios.min.js"></script>--%>
    <%--<script src="https://unpkg.com/element-ui/lib/index.js"></script>--%>
    <%--<script type="text/javascript" src="/home/static/themes/default/mobile/js/qs.js"></script>--%>
    <%-- <script src="https://cdn.bootcss.com/qs/6.5.2/qs.js"></script> --%>
    <style type="text/css">
        #livechat-compact-container {
            transform: translateY(-50%) !important;
            -webkit-transform: translateY(-50%) !important;
        }

        #header_fix {
            overflow: visible;
        }
    </style>
</head>

<body>
<jsp:include page="/mobile/template/header.jsp"></jsp:include>
<jsp:include page="/mobile/template/shop-nav.jsp"></jsp:include>
<div id="app" class="order-meeting-page">
    <mu-load-more :loading="loading" loading-text="loading" :loaded-all="isLoadAll" @load="loadMore" v-scroll="scroll">

        <!-- 顶部banner -->

        <div class="banner banner_ro_wrap" v-if="stpshop_config.lang=='ro'">
            <div class="banner_title_03" v-html="multiLanguage.banner_title_02"></div>
            <div class="banner_title_04" v-html="multiLanguage.banner_title_04"></div>
            <div class="banner_title_05" v-html="multiLanguage.banner_title_09"></div>
            <div class="banner_title_05" v-html="multiLanguage.banner_title_05"></div>
            <a class="btn-red" href="javascript:void(0);" @click="getGift" v-html="multiLanguage.get_gift"></a>
        </div>
        <div class="banner" v-else>
            <div class="banner_title_01">
                2018.12.4-2018.12.19
            </div>
            <div class="banner_title_02" v-html="multiLanguage.banner_title_02"></div>
            <div class="banner_title_03" v-html="multiLanguage.banner_title_03"></div>
            <div class="banner_title_04" v-html="multiLanguage.banner_title_04"></div>
            <div class="banner_title_05" v-html="multiLanguage.banner_title_05"></div>
            <a class="btn-red" href="javascript:void(0);" @click="getGift" v-html="multiLanguage.get_gift"></a>
        </div>
        <!-- 顶部banner - end -->
        <!-- 鞋款分类选择 -->
        <div class="category-select-placeholder">
            <ul class="category-select-list flexible flex-around" ref="navBar" :class="{isfixed: isNavBarFixed}">
                <li class="item" :class="{active: whichSecondClassIsSelected == index}"
                    v-for="(category, index) in categoryListComputed" :key="category.value">
                    <mu-menu @open="openSecondCate(index)" @close="closeSecondCate(index)" :open.sync="open[index]">
                        <div class="no-wrap">
                            {{category.title}} <i class="triangle"
                                                  :class="{rotate: whichSecondClassIsSelected==index && IsSecondClassShow}"></i>
                        </div>
                        <mu-list slot="content">
                            <mu-list-item button v-for="(subItem, subIndex) in category.sub"
                                          @click.native="selectCate(subIndex)"
                                          :class="{selected:selectedCate[0]==whichSecondClassIsSelected && selectedCate[1]==subIndex}">
                                <mu-list-item-title>{{subItem.title}}</mu-list-item-title>
                            </mu-list-item>
                        </mu-list>
                    </mu-menu>
                </li>
            </ul>
        </div>

        <!-- 产品列表 -->
        <ul class="goods-list">
            <li class="goods-item" v-for="(goods,index) in goodsList">
                <a class="goods-pic-wrap" :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id">
                    <img :src="image(goods.image.split(',')[0])" alt="goods pic" class="goods-pic">
                </a>
                <div class="goods-txt-box">
                    <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+goods.id">
                        <!-- <b>JIANSHA</b> Cool Boots comfortable men shoes cheap and quailty cheap la -->
                        <div class="goods-title ellipsis_2">{{goods.name}}</div>
                    </a>
                    <div class="flexible flex-between">
                        <div class="btn-contact" @click="inquiry(goods.productId)">
                            <img src="/activity/Jsp/m/Romania/index/images/icon/icon-letter.png" alt="icon-letter"
                                 class="icon-letter"> contact us
                        </div>
                        <div @click="collect(goods.productId,index)">
                            <img src="/activity/Jsp/m/Romania/index/images/icon/icon-heart_off.png" alt="icon-heart"
                                 class="icon-heart"
                                 v-if="!goods.ismyfavorite">
                            <img src="/activity/Jsp/m/Romania/index/images/icon/icon-heart_on.png" alt="icon-heart"
                                 class="icon-heart" v-else>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </mu-load-more>

    <div class="hoodle" v-show="hoodleShow">
        <div class="hoodle_modle" @click="close"></div>
        <div class="hoodle_form">
            <span class="close-btn" @click="close">+</span>
            <div class="hf-border">
                <h2 class="hf-title" v-html="multiLanguage.form_title">
                    <!-- 2018年 <br>罗马尼亚线下鞋类采购会 -->
                </h2>
                <div class="scroll-wrap">
                    <mu-container>
                        <mu-form ref="form" :model="hoodleForm" class="mu-demo-form">
                            <mu-form-item :label="multiLanguage.name" prop="name" :rules="multiLanguage.nameRules">
                                <mu-text-field v-model="hoodleForm.name" prop="name"></mu-text-field>
                            </mu-form-item>
                            <mu-form-item :label="multiLanguage.phone" prop="tel" :rules="multiLanguage.telRules">
                                <mu-text-field v-model="hoodleForm.tel" prop="tel"></mu-text-field>
                            </mu-form-item>
                            <mu-form-item :label="multiLanguage.email" prop="email" :rules="multiLanguage.emailRule">
                                <mu-text-field v-model="hoodleForm.email" prop="email"></mu-text-field>
                            </mu-form-item>
                            <mu-form-item prop="国家" :label="multiLanguage.country">
                                <mu-select v-model="hoodleForm.country">
                                    <mu-option v-for="(item,index) in countrys" :key="item.id" :label="item.name"
                                               :value="item.id"></mu-option>
                                </mu-select>
                            </mu-form-item>
                            <mu-form-item prop="需求" :label="multiLanguage.inquiry">
                                <mu-text-field class="text-field" v-model="hoodleForm.inquiry" multi-line :rows="3"
                                               full-width style="height: 4rem"></mu-text-field>
                            </mu-form-item>
                            <mu-form-item>
                                <mu-button color="primary" @click="onSubmit">{{multiLanguage.submit}}</mu-button>
                            </mu-form-item>
                        </mu-form>
                    </mu-container>
                    <div class="hf-input">
                        <p class="hf-text" v-html="multiLanguage.form_bottom_text">
                            <!-- 填写并发送表格，您将在罗马尼亚展览室获得一份神秘的礼物！<br>
        或者您可以在备注栏填写您的需求，我们的工作人员将在第一时间与您联系！<br>
        感谢您的光临，我们将为您 提供最好的服务！ -->
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/mobile/template/foot_menu.jsp"></jsp:include>
<script>

    var app = new Vue({
        el: '#app',
        data: {
            test: false,
            hoodleShow: false,	//弹窗是否显示
            hoodleForm: { 			//弹窗内容
                name: '',
                tel: '',
                email: '',
                country: '',
                inquiry: ''
            },
            countrys: [],
            stpshop_config: stpshop_config,
            open: [false, false, false], 		//3个二级分类列表 - 是否显示
            page: 1,
            limit: 12,
            category: -1, //选中的分类??

            selectedCate: [0, 0],  //选中的category
            whichSecondClassIsSelected: 0,  //哪一个二级分类正在显示
            IsSecondClassShow: false,  //二级分类是否正在显示
            isNavBarFixed: false,  //navbar是否固定在顶部
            loading: false,  //是否显示 正在加载
            isLoadAll: false, //是否已全部加载
            navBarOriginalPosition: 999, 	//navBar离顶部的距离
            // 分类列表
            categoryList: [],
            goodsList: [],
            // categoryList:[
            // 	{
            // 		title: "MEN`S ZONE",
            // 		sub:[
            // 			{title: "MEN`S 01"},
            // 			{title: "MEN`S 02"},
            // 			{title: "MEN`S 03"},
            // 			{title: "MEN`S 04"},
            // 			{title: "MEN`S 05"},
            // 		]
            // 	},
            // 	{
            // 		title: "WOMEN`S ZONE",
            // 		sub:[
            // 			{title: "WOMEN`S 01"},
            // 			{title: "WOMEN`S 02"},
            // 			{title: "WOMEN`S 03"},
            // 			{title: "WOMEN`S 04"},
            // 		]
            // 	},
            // 	{
            // 		title: "CHILDREN`S ZONE",
            // 		sub:[
            // 			{title: "CHILDREN`S 01"},
            // 			{title: "CHILDREN`S 02"},
            // 			{title: "CHILDREN`S 03"},
            // 		]
            // 	},
            // ]
        },
        mounted() {
            console.log("mounted")
            // console.log(this.$refs.navBar.getBoundingClientRect().top)
            console.log(this.$refs.navBar.offsetTop)
            this.navBarOriginalPosition = this.$refs.navBar.offsetTop;
            // 获取分类
            this.getCateList()
            // 获取鞋子列表
            this.getGoodsList()
            // 获取国家列表
            this.getCountryList()
        },
        methods: {
            // touchStart (e) {
            //   this.startY = e.targetTouches[0].pageY
            // },
            // touchMove(e){
            // 	this.scrollIsToTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop
            // 	console.log(this.scrollIsToTop)
            // 	if (e.targetTouches[0].pageY > this.startY) { // 下拉
            // 		this.isDropDown = true
            // 	}
            // },
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return stpshop_config.imageBaseUrl.replace(/\+/g, "") + v + params
            },
            scroll(e) {
                if (e.scrollY >= this.navBarOriginalPosition) {
                    if (!this.isNavBarFixed) {
                        this.isNavBarFixed = true
                    }
                } else {
                    if (this.isNavBarFixed) {
                        this.isNavBarFixed = false
                    }
                }
            },

            // 获取分类
            getCateList() {
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax?page=1&limit=5')
                    .then((res) => {
                        if (res.data.ret != 1) {
                            this.$toast.error('Please check the network');
                            return
                        }
                        ;
                        this.categoryList = res.data.result;
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },

            // 显示 相应的二级分类
            openSecondCate(n) {
                console.log("open", n)
                this.whichSecondClassIsSelected = n;
                setTimeout(() => {
                    this.IsSecondClassShow = true;
                }, 0)
            },
            // 关闭 相应的二级分类
            closeSecondCate() {
                this.IsSecondClassShow = false;
            },

            // 点击选中 二级分类
            selectCate(index) {
                this.open[this.whichSecondClassIsSelected] = false
                console.log(this.$refs['category' + this.whichSecondClassIsSelected])
                console.log("e", index)
                if (this.selectedCate[0] == this.whichSecondClassIsSelected && this.selectedCate[1] == index) return;
                this.selectedCate = [this.whichSecondClassIsSelected, index];
                this.category = this.categoryListComputed[this.whichSecondClassIsSelected].sub[index].value;
                this.page = 1;
                this.goodsList = [];
                this.isLoadAll = false;
                this.getGoodsList()
            },

            //获取国家列表
            getCountryList: function () {
                var self = this
                axios.get('/home/plt_PltCountry_list?lang=' + stpshop_config.lang + '&filter=romania').then(function (res) {
                    console.log("获取国家列表");
                    console.log(res.data);
                    self.countrys = res.data.result
                }).catch(function (err) {
                    console.log(err)
                })
            },
            // 点击出现弹窗
            getGift() {
                this.hoodleShow = true;
            },
            close: function () {
                this.hoodleShow = false;
                this.$refs.form.clear();
                this.hoodleForm = {
                    name: '',
                    tel: '',
                    email: '',
                    country: '',
                    inquiry: ''
                };
            },
            onSubmit: function () {
                console.log(this.hoodleForm)
                var self = this;
                self.$refs.form.validate().then((result) => {
                    console.log('form valid: ', result)
                    if (result) {
                        console.log('提交')
                        axios.post('/home/ActivitySignIn_ActivitySignIn_signIn',
                            Qs.stringify({
                                name: self.hoodleForm.name,
                                tel: self.hoodleForm.tel,
                                email: self.hoodleForm.email,
                                country: self.hoodleForm.country,
                                inquiry: self.hoodleForm.inquiry
                            })
                        ).then(function (res) {
                            if (res.data.ret != 1) {
                                if (res.data.msg) {
                                    self.$toast.error(res.data.msg);
                                } else {
                                    self.$toast.error('Please check if you have logged in');
                                }
                                return
                            }
                            self.$toast.success(self.multiLanguage.success_tip);
                            self.hoodleShow = false;
                            // self.gtag_report_conversion();
                            self.close();
                        }).catch(function (err) {
                            console.log(err);
                        })
                    }
                });
            },

            // 点击询盘
            inquiry(productId) {
                if (!sysConfig || !sysConfig.user) {
                    this.$toast.error(lang_obj.goods_info.Pleaselogin);
                    window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + window.location.href
                    return
                }
                axios.get('/home/pdt_PdtConsultPdtList_add', {
                    params: {
                        product: productId,
                    }
                })
                    .then((res) => {
                        console.log("询盘suc");
                        console.log(res);
                        if (res.data.ret != 1) {
                            this.$toast.error('Please check the network');
                            return
                        }

                        window.location.href = "/home/usr_UsrConsult_publishView?product_id=" + res.data.result.id;
                    })
                    .catch((error) => {
                        console.log("询盘fail");
                        console.log(error);
                    });
            },

            // 点击收藏
            collect(goodsId, goodsIndex) {
                if (!sysConfig || !sysConfig.user) {
                    this.$toast.error(lang_obj.goods_info.Pleaselogin);
                    window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + window.location.href
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
                                this.$toast.error(res.data.msg);
                            } else {
                                this.$toast.error('Please check if you have logged in');
                            }
                            return
                        }
                        let isFavorite = this.goodsList[goodsIndex].ismyfavorite;
                        this.goodsList[goodsIndex].ismyfavorite = !this.goodsList[goodsIndex].ismyfavorite
                    })
                    .catch((err) => {
                        this.$toast.error('Please check the network');
                        console.log("收藏err");
                        console.log(err);
                    });
            },

            // 获取对应分类的 鞋子列表
            getGoodsList() {
                axios.get('/home/prm_PrmGroupPurchase_getActProduct', {
                    params: {
                        id: 18,
                        page: this.page,
                        limit: this.limit,
                        category: this.category,
                        sort: -1,
                        type: -1,
                    }
                })
                    .then((res) => {
                        this.loading = false;
                        console.log("鞋子列表suc");
                        console.log(res);
                        if (res.data.items.length > 0) {
                            this.goodsList.push(...res.data.items);
                            console.log(this.goodsList);

                            if (this.page == res.data.pageAll) {
                                this.isLoadAll = true;
                            }
                            this.page++;
                        }
                        ;
                    })
                    .catch((error) => {
                        this.loading = false;
                        console.log("fail");
                        console.log(error);
                    });
            },

            // 上拉加载
            loadMore() {
                this.loading = true;
                this.getGoodsList();
                console.log("触底啦")
            },
        },

        computed: {
            categoryListComputed() {
                let categoryListComputed = [];
                if (!this.categoryList[0]) return;

                this.categoryList.forEach((valueF, indexF) => {
                    categoryListComputed.push({});
                    categoryListComputed[indexF].title = valueF.label;
                    categoryListComputed[indexF].value = valueF.value;

                    if (!valueF.children) return;

                    categoryListComputed[indexF].sub = [];
                    valueF.children.forEach((valueC, indexC) => {
                        if (valueC.value == 381 || valueC.value == 383 || valueC.value == 492
                            || valueC.value == 391 || valueC.value == 516
                            || valueC.value == 374 || valueC.value == 375 || valueC.value == 377 || valueC.value == 527) {
                            categoryListComputed[indexF].sub.push({
                                title: valueC.label,
                                value: valueC.value,
                            })
                        } else if (valueC.children && valueC.children.length > 0) {
                            valueC.children.forEach((valueCC, indexCC) => {
                                if (valueCC.value == 381 || valueCC.value == 383 || valueCC.value == 492
                                    || valueCC.value == 391 || valueCC.value == 516
                                    || valueCC.value == 374 || valueCC.value == 375 || valueCC.value == 377 || valueCC.value == 527) {
                                    categoryListComputed[indexF].sub.push({
                                        title: valueCC.label,
                                        value: valueCC.value,
                                    })
                                }
                            })
                        }
                    })
                })
                return categoryListComputed
            },
            // 监听多语言
            multiLanguage() {
                if (stpshop_config.lang == "ro") {
                    return {
                        banner_title_02: "ADUNARE DE APRECIERE A <br>MOSTRELOR DE PANTOFI <br> OFFLINE, ROMANIA",
                        // banner_title_03: "",
                        // banner_title_04: "Exhibition Hall Opens All Year Round",
                        banner_title_04: "Pavilionul Românesc Este Deschis Pe Tot Parcursul Anului",
                        // banner_title_05: "Magazinul Universal Dragonul Rosu Strada Drumul Garii 12，Bucuresti 077190",
                        banner_title_05: "Magazinul Universal Dragonul Roșu <br> Strada Drumul Garii 12,București 077190 <br> (primul etajul la partea dreapta.nr 3-4)",
                        banner_title_09: "Magazin online de încălțăminte <br> cu prețuri de fabrica",
                        get_gift: "Obțineți Mostre Gratuite Acum",
                        form_title: "2018 ADUNARE DE APRECIERE<br>A MOSTRELOR DE PANTOFI OFFLINE, ROMANIA",
                        form_bottom_text: "Spuneți-ne ce vreți, vă vom răspunde cât mai curând posibil!",
                        name: "Contact:",
                        phone: "telefon:",
                        email: "E-mail:",
                        country: "țară:",
                        inquiry: "Ancheta:",
                        submit: "prezenta",
                        success_tip: "Trimise cu succes",
                        // 表单验证规则rules
                        nameRules: [
                            {validate: (val) => !!val, message: "Numele de utilizator nu poate fi gol"},
                        ],
                        telRules: [
                            {validate: (val) => !!val, message: 'Numărul de telefon nu poate fi gol'},
                            {
                                validate: (val) => /^1[345789]\d{9}$/.test(val),
                                message: 'Eroare de format de număr de telefon'
                            }
                        ],
                        emailRule: [
                            {validate: (val) => !!val, message: 'Numărul căsuței poștale nu poate fi gol'},
                            {
                                validate: (val) => /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(val),
                                message: 'Format incorect de cutie poștală'
                            }
                        ],
                    }
                } else if (stpshop_config.lang == "zh_CN") {
                    return {
                        banner_title_02: "罗马尼亚",
                        banner_title_03: "线下鞋样品鉴会",
                        banner_title_04: "罗马尼当地展厅全年开放",
                        banner_title_05: "环球龙红街店<br>Drumul Garii 12布加勒斯特077190",
                        get_gift: "立即获得免费样品",
                        form_title: "2018年 <br>罗马尼亚线下鞋类采购会",
                        form_bottom_text: "告诉我们您的需求，我们会尽快回复您!",
                        name: "联系人:",
                        phone: "电话:",
                        email: "电子邮件:",
                        country: "国家:",
                        inquiry: "需求:",
                        submit: "提交",
                        success_tip: "提交成功",
                        // 表单验证规则rules
                        nameRules: [
                            {validate: (val) => !!val, message: "必须填写用户名"},
                        ],
                        telRules: [
                            {validate: (val) => !!val, message: '必须填写电话号码'},
                            {validate: (val) => /^1[345789]\d{9}$/.test(val), message: '电话号码格式错误'}
                        ],
                        emailRule: [
                            {validate: (val) => !!val, message: '必须填写邮箱'},
                            {
                                validate: (val) => /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(val),
                                message: '请输入正确邮箱'
                            }
                        ],
                    }
                } else {
                    return {
                        banner_title_02: "ROMANIAN",
                        banner_title_03: "OFFLINE SHOES PURCHASING",
                        banner_title_04: "Exhibition hall opens all year round",
                        banner_title_05: "Global Dragon Red Street Shop Drumul Garii 12 Bucharest 077190",
                        get_gift: "Get Free Sample Now",
                        form_title: "2018 ROMANIAN<br>OFFLINE SHOES PURCHASING",
                        form_bottom_text: "Tell us what you want, we'll reply you as soon as possible!",
                        name: "Contact:",
                        phone: "phone:",
                        email: "E-mail:",
                        country: "Country:",
                        inquiry: "inquiry:",
                        submit: "Submit",
                        success_tip: "Submitted successfully",
                        // 表单验证规则rules
                        nameRules: [
                            {validate: (val) => !!val, message: "Username can not be empty"},
                        ],
                        telRules: [
                            {validate: (val) => !!val, message: 'Phone number cannot be empty'},
                            {validate: (val) => /^1[345789]\d{9}$/.test(val), message: 'Phone number format error'}
                        ],
                        emailRule: [
                            {validate: (val) => !!val, message: 'Mailbox number cannot be empty'},
                            {
                                validate: (val) => /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(val),
                                message: 'Incorrect mailbox format'
                            }
                        ],
                    }
                }
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
