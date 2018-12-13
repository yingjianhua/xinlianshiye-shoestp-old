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
    <meta name="keywords" content="罗马尼亚订购会">
    <meta name="description" content="罗马尼亚订购会">
    <title>罗马尼亚订购会</title>
    <link rel="stylesheet" href="/activity/Jsp/Romania/ProductList/css/index.css"/>
    <script src='/activity/Jsp/Romania/ProductList/js/vue.min.js'></script>
    <!-- <link rel="stylesheet" href="./css/element-ui.min.css">
    <script src="./js/element-ui.min.js"></script> -->
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="/activity/Jsp/Romania/ProductList/js/axios.min.js"></script>
    <script src="/activity/Jsp/Romania/ProductList/js/qs.js"></script>
    <style>

        .formBtn {
            background-color: #be1a39;
            color: #ffffff;
            position: absolute;
            right: 7%;
            bottom: 15%;
            cursor: pointer;
            font-size: 20px;
            padding: 0 10px;
            line-height: 35px;
            border-radius: 5px;
        }

        .el-tree-node__content {
            height: 63px;
            border-bottom: 1px solid #e8e8e8;
        }

        .el-tree-node__expand-icon.is-leaf {
            display: none;
        }

        .el-tree-node__expand-icon {
            position: absolute;
            right: 0;
            font-size: 30px;
        }

        .el-tree-node__label {
            font-size: 16px;
            color: #222222;
        }

        .el-tree-node__children .el-tree-node__content .el-tree-node__label {
            font-size: 14px !important;
        }

        .el-pagination {
            margin: 50px 0;
        }

        @media screen and (max-width: 1600px) and (min-width: 1400px) {
            #app .container-box .classify-list {
                width: 14%;
            }
        }

        @media screen and (max-width: 1400px) and (min-width: 1200px) {
            #app .container-box .classify-list {
                width: 13%;
                padding-left: 10px;
            }

            .el-tree-node__content > .el-tree-node__expand-icon {
                padding: 0;
                font-size: 28px;
            }
        }

        @media screen and (max-width: 1200px) and (min-width: 980px) {
            .formBtn {
                right: 6%;
                font-size: 18px;
                line-height: 30px;
            }

            .el-tree-node__label {
                font-size: 12px;
            }

            .el-tree-node__children .el-tree-node__content .el-tree-node__label {
                font-size: 12px !important;
            }

            .el-tree-node__content > .el-tree-node__expand-icon {
                padding: 0;
                font-size: 24px;
            }

            #app .container-box .classify-list {
                width: 13%;
                padding-left: 10px;
            }

            #app .container-box .goods-list {
                padding-left: 20px;
            }
        }

        @media screen and (max-width: 980px) {
            .formBtn {
                right: 6%;
                font-size: 16px;
                line-height: 26px;
            }

            .el-tree-node__content > .el-tree-node__expand-icon {
                padding: 0;
                font-size: 20px;
            }

            .el-tree-node__label {
                font-size: 12px;
            }

            .el-tree-node__children .el-tree-node__content .el-tree-node__label {
                font-size: 12px !important;
            }

            #app .container-box .classify-list {
                width: 13%;
                padding-left: 10px;
            }

            #app .container-box .goods-list {
                padding-left: 20px;
            }
        }

        .modal-wrap {
            position: fixed;
            top: 0;
            left: 0;
            z-index: 999;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.3);
        }

        .modal-wrap .modal-inner-box {
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            width: 380px;
            padding: 30px 30px 20px;
            border-radius: 6px;
            background: #eee;
        }

        .modal-wrap .btn-close {
            position: absolute;
            right: 0;
            top: 0;
            -webkit-transform: translate3d(50%, -50%, 0);
            transform: translate3d(50%, -50%, 0);
            width: 34px;
            cursor: pointer;
        }

        .modal-wrap .form-title {
            line-height: 26px;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
        }

        .modal-wrap .el-form-item--small.el-form-item {
            margin-bottom: 0;
        }

        .modal-wrap .el-select {
            width: 100%;
        }

        .modal-wrap .el-form-item--small .el-form-item__label {
            line-height: 30px;
            padding-top: 10px;
            padding-bottom: 0;
            color: #525252;
            font-size: 18px;
        }

        .modal-wrap .required-label .el-form-item__label:after {
            content: '*';
            position: relative;
            top: 4px;
            color: #f56c6c;
            margin-left: 4px;
        }

        .modal-wrap .el-input__inner, .modal-wrap .el-input__inner:hover, .modal-wrap .el-select:hover .el-input__inner, .modal-wrap .el-textarea__inner, .modal-wrap .el-textarea__inner:hover {
            border: 1px solid #f19149;
        }

        .modal-wrap .el-button.form-button {
            line-height: 44px;
            padding: 0 60px;
            margin-top: 25px;
            margin-bottom: 15px;
            background-color: #e73147;
            border-color: #e73147;
            border-radius: 10px;
            font-size: 20px;
        }

        .modal-wrap .tips {
            text-align: center;
            font-size: 16px;
        }

        .modal-wrap .model-confirm {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 9;
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            min-width: 300px;
            overflow: hidden;
            border-radius: 6px;
            color: #b4b4b4;
            background: #fff;
        }

        .modal-wrap .model-confirm .close-wrap {
            padding: 10px 10px 0 0;
            text-align: right;
            cursor: pointer;
        }

        .modal-wrap .model-confirm .model-confirm-content {
            padding: 15px 40px 40px;
            font-size: 20px;
        }

        .modal-wrap .model-confirm .model-confirm-content .icon {
            width: 60px;
            height: 60px;
            margin-right: 12px;
            border-radius: 50%;
        }

        .modal-wrap .model-confirm .model-confirm-footer {
            height: 50px;
            line-height: 50px;
            font-size: 24px;
            text-align: center;
            cursor: pointer;
            color: #fff;
            background: #51ca98;
        }

    </style>
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
    <div style="text-align: center;margin: 0 auto;position: relative" class="maxW">
        <img class="minW" :src="_banner" :alt="_alt">
        <div class="formBtn" @click="getGift">
            <template v-if="lang == 'ro'">
                Obțineți Mostre Gratuite Acum
            </template>
            <template v-else-if="lang == 'zh_CN'">
                立即获得免费样品
            </template>
            <template v-else>
                Request Now
            </template>
        </div>
    </div>
    <div style="background: #ffffff;text-align: center;">
        <!-- 内容容器 -->
        <div class="container-box maxW minW">
            <!-- 左边分类选择 -->
            <div class="classify-list">
                <div style="line-height:70px;text-align: left;" @click="getAllCatGoodsList">{{_title}}</div>
                <el-tree :data="categoryListComputed" :props="defaultProps" @node-click="handleNodeClick"
                         highlight-current="true"></el-tree>
            </div>
            <!-- 右边列表 -->
            <div class="goods-list">
                <div class="goods-box">
                    <template v-for="(item,index) in goodsList.items">
                        <div class="goods-item">
                            <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+item.id" target="_blank">
                                <img :src="item.image+'?x-oss-process=image/resize,m_fixed,h_270,w_270'" :alt="item.name" style="width:270px;height:270px">
                                <div class="goods-name">{{item.name}}</div>
                            </a>
                            <div class="flex" style="justify-content: space-between;">
                                <div class="inquiry-btn" @click="inquiry(item.productId)">INQUIRY</div>
                                <div style="cursor: pointer;">
                                    <img :src="item.ismyfavorite?'/activity/Jsp/Romania/ProductList/images/icon-like-on.png':'/activity/Jsp/Romania/ProductList/images/icon-like-off.png'"
                                         alt="" style="width:25px;height:21px;margin-right:8px;"
                                         :data-id="item.id" @click="addCollection(item.productId,index)">
                                </div>
                            </div>
                        </div>
                    </template>
                </div>
                <!-- {{goodsList.items}} -->
                <!-- 分页 -->
                <el-pagination background layout="prev, pager, next" :page-count="goodsList.pageAll"
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange">
                </el-pagination>
            </div>
        </div>
    </div>


    <!-- form弹框 -->
    <transition name="el-fade-in-linear" v-cloak>
        <div class="modal-wrap" v-show="isFormShow || isTipDialogShow" @mousewheel.prevent>
            <transition name="el-fade-in-linear">
                <div class="modal-inner-box" v-show="isFormShow">
                    <img src="/activity/Jsp/Romania/ProductList/images/close-black.png" alt="button to close the form"
                         class="btn-close"
                         @click="isFormShow = false">
                    <div class="form-title" v-if="lang == 'ro'">
                        2018 MOSTRELOR DE
                        <br>
                        PANTOFI OFFLINE,ROMANIA
                    </div>
                    <div class="form-title" v-else-if="lang == 'zh_CN'">
                        2018年
                        <br>
                        罗马尼亚线下鞋类采购会
                    </div>
                    <div class="form-title" v-else>
                        2018 ROMANIAN
                        <br>
                        OFFLINE SHOES PURCHASING
                    </div>
                    <el-form ref="form" :model="form" label-width="80px" label-position="top" size="small">
                        <el-form-item label="A lua legatura:" class="required-label" v-if="lang == 'ro'">
                            <el-input v-model="form.name" placeholder="lasă - ţi numele"></el-input>
                        </el-form-item>
                        <el-form-item label="联系人:" class="required-label" v-else-if="lang == 'zh_CN'">
                            <el-input v-model="form.name" placeholder="名字"></el-input>
                        </el-form-item>
                        <el-form-item label="Contact:" class="required-label" v-else>
                            <el-input v-model="form.name" placeholder="Please input your name"></el-input>
                        </el-form-item>


                        <el-form-item label="Statul" class="required-label" v-if="lang == 'ro'">
                            <el-select v-model="form.country">
                                <el-option value="-1" label="Te rog alege ţara ta"></el-option>
                                <el-option v-for="country in countryList" :key="country.id" :label="country.name"
                                           :value="country.id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="国家" class="required-label" v-else-if="lang == 'zh_CN'">
                            <el-select v-model="form.country">
                                <el-option value="-1" label="请选择您所在的国家"></el-option>
                                <el-option v-for="country in countryList" :key="country.id" :label="country.name"
                                           :value="country.id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="Country" class="required-label" v-else>
                            <el-select v-model="form.country">
                                <el-option value="-1" label="Please select your country"></el-option>
                                <el-option v-for="country in countryList" :key="country.id" :label="country.name"
                                           :value="country.id"></el-option>
                            </el-select>
                        </el-form-item>


                        <el-form-item label="Telefon:" class="required-label" v-if="lang == 'ro'">
                            <el-input v-model="form.tel"
                                      placeholder="vă rog introduceţi numărul de telefon."></el-input>
                        </el-form-item>
                        <el-form-item label="电话:" class="required-label" v-else-if="lang == 'zh_CN'">
                            <el-input v-model="form.tel" placeholder="请输入您的电话号码"></el-input>
                        </el-form-item>
                        <el-form-item label="Telephone:" class="required-label" v-else>
                            <el-input v-model="form.tel" placeholder="Please input your telephone number."></el-input>
                        </el-form-item>


                        <el-form-item label="Email:" class="required-label" v-if="lang == 'ro'">
                            <el-input v-model="form.email" placeholder="te rog intră în cutia ta poştală"></el-input>
                        </el-form-item>
                        <el-form-item label="电子邮件:" class="required-label" v-else-if="lang == 'zh_CN'">
                            <el-input v-model="form.email" placeholder="请输入您的邮箱"></el-input>
                        </el-form-item>
                        <el-form-item label="Email:" class="required-label" v-else>
                            <el-input v-model="form.email" placeholder="Please input your E-mail."></el-input>
                        </el-form-item>

                        <el-form-item label="Anchetă:" v-if="lang == 'ro'">
                            <el-input type="textarea" v-model="form.inquiry"
                                      placeholder="vă rog să introduceţi informaţii"></el-input>
                        </el-form-item>
                        <el-form-item label="需求:" v-else-if="lang == 'zh_CN'">
                            <el-input type="textarea" v-model="form.inquiry" placeholder="请输入您的需求信息"></el-input>
                        </el-form-item>
                        <el-form-item label="Inquiry:" v-else>
                            <el-input type="textarea" v-model="form.inquiry"
                                      placeholder="Please input enquiry information."></el-input>
                        </el-form-item>


                        <div class="tc">
                            <el-button type="danger form-button" @click="formSubmit">
                                <template v-if="lang == 'ro'">A depune</template>
                                <template v-else-if="lang == 'zh_CN'">提交</template>
                                <template v-else>Submit</template>
                            </el-button>
                        </div>

                        <div class="tips" v-if="lang == 'ro'">
                            Spuneți-ne ce vreți, vă vom răspunde cât mai curând posibil!
                        </div>
                        <div class="tips" v-else-if="lang == 'zh_CN'">
                            填写并发送表格，您将在
                            <br>
                            罗马尼亚展览室获得一份神秘的礼物！
                            <br>
                            或者您可以在备注栏填写您的需求，
                            <br>
                            我们的工作人员将在第一时间与您联系！
                            <br>
                            感谢您的光临，我们将为您
                            <br>
                            提供最好的服务！
                        </div>
                        <div class="tips" v-else>
                            Tell us what you want,
                            <br/>we'll reply you as soon as possible!
                        </div>
                    </el-form>
                </div>
            </transition>

            <!-- 提示弹窗 -->
            <transition name="el-zoom-in-center">
                <div class="model-confirm" v-show="isTipDialogShow">
                    <div class="close-wrap">
                        <img src="/activity/Jsp/Romania/ProductList/images/close-red.png" alt="close" class="close"
                             @click="isTipDialogShow=false"
                             style="width:auto">
                    </div>
                    <div class="model-confirm-content flex"
                         style="-webkit-box-pack: center;-ms-flex-pack: center;justify-content: center;">
                        <img src="/activity/Jsp/Romania/ProductList/images/right-red.png" alt="right" class="icon">
                        <div>
                            <template v-if="lang == 'ro'">A depune<br>Cu succes！</template>
                            <template v-else-if="lang == 'zh_CN'">提交成功</template>
                            <template v-else>Submit <br>Successfully！</template>
                        </div>
                    </div>
                    <div class="model-confirm-footer" @click="isTipDialogShow = false">
                        <template v-if="lang == 'ro'">A determina</template>
                        <template v-else-if="lang == 'zh_CN'">确定</template>
                        <template v-else>Determine</template>
                    </div>
                </div>
            </transition>
        </div>
    </transition>

</div>
<style>
    #web_top .top_mem {
        line-height: 25px;
    }
</style>

<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'AW-783435725');
    gtag('config', 'UA-127715615-6')

</script>
<script>
    function gtag_report_conversion(url) {
        var callback = function () {
            if (typeof(url) != 'undefined') {
                window.location = url;
            }
        };
        gtag('event', 'conversion', {
            'send_to': 'AW-783435725/2sU4CMb_io8BEM2PyfUC',
            'event_callback': callback
        });
        return false;
    }
</script>
<script>
    function gtag_report_conversionGoogle(url) {
        var callback = function () {
            if (typeof(url) != 'undefined') {
                window.location = url;
            }
        };
        gtag('event', 'conversion', {
            'send_to': 'AW-783435725/gYQ7CJq0upABEM2PyfUC',
            'event_callback': callback
        });
        return false;
    }
</script>
<script>
    function getParams(name, defaultValue) {
        var url = window.location.href;
        var l = url.lastIndexOf(name)
        if (l != -1) {
            var ll = url.indexOf("&");
            if (ll == -1 || l > ll) {
                ll = url.length
            }
            url = url.substring(l, ll);
            var result = url.split("=")
            if (result.length == 2) {
                switch (typeof defaultValue) {
                    case "number":
                        return parseInt(result[1]);
                    case "boolean":
                        return Boolean(result[1])
                    default:
                        return result[1];
                }
            }
        } else {
            if (defaultValue == 'NONE') {
                return null;
            }
            if (defaultValue == null) {
                return -1;
            }
            return defaultValue
        }
        return -1;
    }

    function carWindow(data, msg) {
        $('#addtocart_button').attr('disabled', false);
        var excheckout_html = '<div id="shipping_cost_choose">';
        excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
        excheckout_html += '<div id="choose_content">';
        excheckout_html += '<div class="cart_view">' +
            '<p>' + msg + '</p>' +
            '</div>';
        excheckout_html += '<p class="footRegion">';
        if (msg.indexOf(lang_obj.global.inqadd) != -1) {
            excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">'
                + lang_obj.global.continues + '</a><a href="/home/usr_UsrConsult_publishView?product_id='
                + data.result.id + '" class="btn btn-success" id="excheckout_button">'
                + lang_obj.global.inquery + '</a>';
        } else {
            excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">'
                + lang_obj.cart.return_shopping
                + '</a><a href="/home/usr_UsrCart_cartshopping" class="btn btn-success" id="excheckout_button">'
                + lang_obj.cart.proceed_checkout + '</a>';
        }

        excheckout_html += '</p>';
        excheckout_html += '</div>';
        excheckout_html += '</div>';

        $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
        $('body').prepend(excheckout_html);
        $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});
        global_obj.div_mask();

    }

    $('html').on('click', '#choose_close, #div_mask, #exback_button', function () {
        if ($('#shipping_cost_choose').length) {
            $('#shipping_cost_choose').remove();
            global_obj.div_mask(1);
            $('#shipping_cost_button').removeAttr('disabled');
        }
    });


    new Vue({
        el: '#app',
        data: {
            defaultProps: {
                children: 'children',
                label: 'label'
            },
            cated: 373, // 分类id
            page: 1, // 当前页
            limit: 24, // 每页显示数量
            goodsList: [], // 商品列表
            categoryList: [], // 分类列表
            productId: null, //获取到的询盘id
            bannerType: 1, // 显示对应广告图  1男士 2女士 3儿童
            lang: stpshop_config.lang, // 给对应语言赋值
            bannerImage: {
                "en": {
                    "1": "/activity/Jsp/Romania/ProductList/images/enMenBanner.jpg",
                    "2": "/activity/Jsp/Romania/ProductList/images/enWomenBanner.png",
                    "3": "/activity/Jsp/Romania/ProductList/images/enChildrenBanner.png"
                }, "ro": {
                    "1": "/activity/Jsp/Romania/ProductList/images/roMenBanner.png",
                    "2": "/activity/Jsp/Romania/ProductList/images/roWomenBanner.png",
                    "3": "/activity/Jsp/Romania/ProductList/images/roChildrenBanner.png"
                }, "zh_CN": {
                    "1": "/activity/Jsp/Romania/ProductList/images/zhMenBanner.png",
                    "2": "/activity/Jsp/Romania/ProductList/images/zhWomenBanner.png",
                    "3": "/activity/Jsp/Romania/ProductList/images/zhChildrenBanner.png"
                }
            }, bannerAlt: {
                "en": "ROMANIAN OFFLINE SHOES PURCHASING",
                "ro": "ADUNARE DE APRECIERE A MOSTRELORDE PANTOFI OFFLINE,ROMANIA",
                "zh_CN": "罗马尼亚线下订购会"
            }, isFormShow: false, //是否显示弹窗
            isTipDialogShow: false, //是否显示提示弹窗
            countryList: [], //国家列表
            form: {
                country: "-1"
            }, //form提交的内容
        },
        methods: {
// 点击获取样品 - 弹出form框
            getGift() {
                gtag_report_conversionGoogle()
                this.isFormShow = true;
            },

            formSubmit() {
                if (!this.form.name || this.form.name == "") {
                    if (this.lang == 'ro') {
                        this.$message.error('Introduceți numele dvs.');
                    } else if (this.lang == 'zh_CN') {
                        this.$message.error('姓名不可为空，请重新填写');
                    } else {
                        this.$message.error('Names must not be empty.');
                    }
                    return;
                } else if (!this.form.country || this.form.country == -1) {
                    if (this.lang == 'ro') {
                        this.$message.error('Selectați țara dvs.');
                    } else if (this.lang == 'zh_CN') {
                        this.$message.error('暂未选择国家，请选择');
                    } else {
                        this.$message.error('Please choose your country');
                    }
                    return;
                } else if (!this.form.tel || this.form.tel == "") {
                    if (this.lang == 'ro') {
                        this.$message.error('Introduceți numărul dvs. de telefon');
                    } else if (this.lang == 'zh_CN') {
                        this.$message.error('电话号码不可为空，请重新填写');
                    } else {
                        this.$message.error('Please input your telephone number.');
                    }
                    return;
                } else if (!(/^\d{1,}$/.test(this.form.tel))) {
                    if (this.lang == 'ro') {
                        this.$message.error('Eroare de format de număr de telefon');
                    } else if (this.lang == 'zh_CN') {
                        this.$message.error('电话号码格式错误，请重新填写');
                    } else {
                        this.$message.error('Wrong telephone number format');
                    }
                    return;
                } else if (!this.form.email || this.form.email == "") {
                    if (this.lang == 'ro') {
                        this.$message.error('Introduceți adresa dvs. de e-mail');
                    } else if (this.lang == 'zh_CN') {
                        this.$message.error('邮箱不可为空，请重新填写');
                    } else {
                        this.$message.error('Please input your mailbox.');
                    }
                    return;
                } else if (!(/[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/.test(this.form.email))) {
                    if (this.lang == 'ro') {
                        this.$message.error('Format incorect de cutie poștală');
                    } else if (this.lang == 'zh_CN') {
                        this.$message.error('邮箱格式错误，请重新填写');
                    } else {
                        this.$message.error('Error in mailbox format');
                    }
                    return;
                }
                axios.post('/home/ActivitySignIn_ActivitySignIn_signIn', Qs.stringify({
                    name: this.form.name,
                    country: this.form.country,
                    tel: this.form.tel,
                    email: this.form.email,
                    inquiry: this.form.inquiry
                }, {
                    allowDots: true
                }))
                    .then((res) => {
                        gtag_report_conversion()
                        if (res.data.ret == 1) {
                            this.form = {};
                            this.isFormShow = false;
                            this.isTipDialogShow = true;
                        }
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            },
            getClassifyList(page, limit, category) { // 获取左边分类
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax', {
                    params: {
                        page,
                        limit,
                    }
                })
                    .then((res) => {
                        this.categoryList = res.data.result
                    })
                    .catch((error) => {
                        this.$message.error(error);
                    });
            },
            getGoodsList(page, limit, category) { // 获取商品列表
                axios.get('/home/prm_PrmGroupPurchase_getActProduct', {
                    params: {
                        page,
                        limit,
                        category,
                        sort: -1,
                        type: 1,
                        id: 18
                    }
                })
                    .then((res) => {
                        this.goodsList = res.data;
                        for (let i = 0; i < this.goodsList.items.length; i++) {
                            var img = this.goodsList.items[i].image.split(',')[0]
                            this.$set(this.goodsList.items[i], "image", stpshop_config.imageBaseUrl + img)
                        }
                    })
                    .catch((error) => {
                        this.$message.error(error);
                    });
            },
            getAllCatGoodsList() {
                this.getGoodsList(this.page, this.limit, -1)
            },
            inquiry(id) { // 点击 询盘
                console.log(id)
                if (!isLogin) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                } else {
                    // console.log("已登录")
                    axios.post('/home/pdt_PdtConsultPdtList_add', Qs.stringify({ //请求参数
                        product: id,
                    }))
                        .then((data) => {
                            if (data.data) {
                                if (data.data.ret && data.data.ret === 1) {
                                    carWindow(data.data, lang_obj.global.inqadd)
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
                        });

                }
            },
            handleNodeClick(e) { // 左边分类
                this.cated = e.id
                this.page = 1;
                this.getGoodsList(this.page, this.limit, this.cated);
                // if (stpshop_config.lang === "en" || stpshop_config.lang == 'ro' || stpshop_config.lang === 'zh_CN') {
                if (e.id == 373 || e.id == 374 || e.id == 375 || e.id == 377 || e.id == 527) {
                    this.bannerType = 1;
                } else if (e.id == 380 || e.id == 381 || e.id == 383 || e.id == 492) {
                    this.bannerType = 2;
                } else if (e.id == 387 || e.id == 391 || e.id == 516) {
                    this.bannerType = 3;
                }
                // }
            },
            // 分页方法
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getGoodsList(this.page, this.limit, this.cated);
            },
            addCollection(id, index) { // 添加收藏
                if (!isLogin) {
                    // user_obj.set_form_sign_in('', window.location.href, 1);
                    // return
                    user_obj.set_form_sign_in('', window.location.href, 1);
                } else {
                    axios.post('/home/usr_UsrFavorites_addFavorite', Qs.stringify({ //请求参数
                        pdtPkey: id,
                    }))
                        .then((res) => {
                            this.goodsList.items[index].ismyfavorite = !this.goodsList.items[index].ismyfavorite
                        })
                        .catch((error) => {
                            this.$message.error(error);
                        });
                }
            }
        },
        mounted() {
            this.cated = getParams("category", 373)
            this.getGoodsList(this.page, this.limit, this.cated);
            this.getClassifyList(1, 5);
            axios.get('/home/plt_PltCountry_list?filter=romania') // 获取国家信息
                .then((res) => {
                    if (res.data.ret == 1) {
                        this.countryList = res.data.result
                    }
                })
                .catch(function (err) {
                    console.log("err");
                });
        },
        computed: {
            _title() {
                switch (stpshop_config.lang) {

                    case "ro":
                        return "Toate Categoriile"
                    case "en":
                        return "All Categories"
                    case "zh_CN":
                        return "全部分类"
                    default:
                        return "Rufine by"

                }

            },
            categoryListComputed() {
                let categoryListComputed = [];
                if (!this.categoryList[0]) return;

                this.categoryList.forEach((valueF, indexF) => {
                    categoryListComputed.push({});
                    categoryListComputed[indexF].label = valueF.label;
                    categoryListComputed[indexF].id = valueF.value;

                    if (!valueF.children) return;

                    categoryListComputed[indexF].children = [];
                    valueF.children.forEach((valueC, indexC) => {
                        if (valueC.value == 381 || valueC.value == 383 || valueC.value == 492 ||
                            valueC.value == 391 || valueC.value == 516 ||
                            valueC.value == 374 || valueC.value == 375 || valueC.value == 377 || valueC.value == 527) {
                            categoryListComputed[indexF].children.push({
                                label: valueC.label,
                                id: valueC.value,
                            })
                        } else if (valueC.children && valueC.children.length > 0) {
                            valueC.children.forEach((valueCC, indexCC) => {
                                if (valueCC.value == 381 || valueCC.value == 383 || valueCC.value == 492 ||
                                    valueCC.value == 391 || valueCC.value == 516 ||
                                    valueCC.value == 374 || valueCC.value == 375 || valueCC.value == 377 || valueCC.value == 527) {
                                    categoryListComputed[indexF].children.push({
                                        label: valueCC.label,
                                        id: valueCC.value,
                                    })
                                }
                            })

                        }
                    })

                    // childVal = childVal.filter((v)=>{
                    // 	return v
                    // })

                    // if( childVal.length > 0 ){
                    // 	categoryListComputed.push({
                    // 		label: valueF.label,
                    // 		value: valueF.value,
                    // 		children: childVal
                    // 	})
                    // }
                })
                var label = {
                    "en": "Other",
                    "ro": "Alte",
                    "zh_CN": "其他"
                }
                label = label[stpshop_config.lang]
                if (!label) {
                    label = "Other"
                }
                for (var x in categoryListComputed) {
                    categoryListComputed[x].children.push({
                        label: label,
                        id: -2 + x * -1
                    })
                }
                return categoryListComputed
            },
            _banner: function () {
                switch (this.lang) {
                    case "zh_CN": {
                        return this.bannerImage["zh_CN"][this.bannerType]
                    }
                    case "ro": {
                        return this.bannerImage["ro"][this.bannerType]
                    }
                    default :
                        return this.bannerImage["en"][this.bannerType]
                }
            }, _alt: function () {
                switch (this.lang) {
                    case "zh_CN": {
                        return this.bannerAlt["zh_CN"]
                    }
                    case "ro": {
                        return this.bannerAlt["ro"]
                    }
                    default :
                        return this.bannerAlt["en"]
                }
            }
        },

    })
</script>
</body>

</html>
