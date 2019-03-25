<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->

    <script type="text/javascript" async="" src="./static/js/js.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/conversion_async.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/analytics.js">
    </script>
    <%-- <script type="text/javascript" async="" src="./static/js/tracking.js">
    </script> --%>
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market--Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <%--<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>--%>
    <script type="text/javascript" src="./static/js/lang/en.js">
    </script>
    <%--<script type="text/javascript" src="./static/js/main.js"></script>--%>
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
    <%--<link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">--%>
    <%--<link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">--%>
    <%--<script src="./static/js/swiper.min.js" type="text/javascript"></script>--%>
    <%--<script src="./static/js/lazyload.min.js" type="text/javascript"></script>--%>
    <!-- <script type="text/javascript">
        $(function () {
            products_list_obj.init();
        });
    </script> -->
    <%-- <script src="./static/js/saved_resource" type="text/javascript">
    </script> --%>
    <%--<script src="./static/js/qs.js" type="text/javascript"></script>--%>
    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">
    <link href="./static/css/inquiry-publish.css" rel="stylesheet" type="text/css">

    <%--<script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>--%>
    <%--<script src="https://unpkg.com/axios/dist/axios.min.js"></script>--%>
    <%--<link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.6/lib/theme-chalk/index.css">--%>
    <%--<script src="https://unpkg.com/element-ui@2.4.6/lib/index.js"></script>--%>
    <style media="screen">
        /* 上传图片 预览的样式调整 */
        .el-upload--picture-card {
            border-width: 0;
            width: 90px;
            height: 90px;
        }

        .el-upload-list--picture-card .el-upload-list__item {
            width: 90px;
            height: 90px;
            margin-bottom: 0;
        }

        .el-upload-list--picture-card .el-upload-list__item-thumbnail {
            object-fit: contain;
        }

        /* 为了与原先样式对其 */
        select[name="Country"] {
            width: 237px;
            border: 1px solid rgb(221, 221, 221);
        }

        .null {
            border: 1px solid red !important;
        }
        #o2otop .o2otopcon .topsearch > input{
            border: 1px solid #10389c;
        }

        /*左侧个人中心*/
        #left-user-menu .user-menu {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            width: 180px;
            height: 285px;
            flex: none;
            background-color: #ffffff;
            border: solid 1px #dedede;
            padding: 8px 24px;
        }

        #left-user-menu .user-menu .user-menu-title {
            height: 44px;
            line-height: 44px;
            font-weight: bold;
            font-size: 16px;
        }

        #left-user-menu .user-menu .user-menu-item {
            height: 44px;
            line-height: 44px;
            border-bottom: solid 1px #dddddd;
            font-size: 14px;
        }

        #left-user-menu .user-menu .user-menu-item:last-child {
            border: none;
        }

        #left-user-menu .user-menu .user-menu-item a {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            color: #232323;
            text-decoration: none;
        }

        .el-form-item__label{
            white-space: nowrap;
        }
    </style>

    <%--统计代码--%>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200" style="background: #fff;">
<div id="main">
    <index-top></index-top>
    <div class="wide">
        <div id="lib_user" class="clearfix">
            <%--左侧个人中心列表--%>
            <s:if test="env.login!=null">
                <div id="left-user-menu" class="fl">
                    <div class="user-menu">
                        <div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account</div>
                        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
                        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
                        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
                        <div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favourites <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
                        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
                    </div>
                </div>
            </s:if>

            <%--中间具体内容   --%>
            <div id="lib_user_main">
                <div id="lib_user_msg" class="clearfix">

                    <div class="main_left">
                        <div>
                            <h1 class="header-title1">Tell us what do you want</h1>
                            <p class="font-bold" style="margin-bottom:10px;font-size: 22px;">
                                Complete Your RFQ
                            </p>
                            <p style="font-size:14px;color: #666;">
                                The more specific your information, the more accurately we can match your request to
                                the target supplier.
                            </p>
                        </div>
                        <el-form size="small" label-width="160px" ref="form" :rules="rules" :model="form" id="inquiry_form">
                            <div class="input-wrap required">
                                <el-form-item label-width="0" prop="title">
                                    <el-input placeholder="Key words of products you are looking for" v-model.trim="form.title"></el-input>
                                </el-form-item>
                            </div>


                            <div class="input-wrap required form-item-label">
                                Product Descriotion:
                            </div>
                            <div class="form_main">
                                <el-form-item label-width="0" prop="descriotion">
                                    <el-input type="textarea" :autosize="{ minRows: 14, maxRows: 14}" placeholder="Please let us know your requirements as detail as possible. You may include: color, size, material, gradw/standard, etc."
                                              v-model.trim="form.descriotion">
                                    </el-input>
                                </el-form-item>
                            </div>

                            <div class="form_main marginBottom">
                                <!--  添加 勾选商品后的图片显示 -->
                                <div class="chooseImgWrap clearfix">
                                    <div class="chooseImg fl">
                                        <el-upload action="/home/usr_UsrConsult_upload" list-type="picture-card"
                                                   :on-success="handlePictureCardPreview" :limit="5"
                                                   :before-upload="beforeUpload"
                                                   :on-remove="handleRemove">
                                            <img src="./static/images/upImg.png"
                                                 style="width: 100%;height: 100%;object-fit: contain;vertical-align: baseline;">
                                        </el-upload>
                                    </div>
                                </div>
                            </div>

                            <div class="inquiry-goods-info">
                                <el-form-item label="Purchase Quantity：" required>
                                    <el-col :span="15">
                                        <el-form-item label-width="0" prop="quantity">
                                            <el-input v-model.trim="form.quantity"
                                                      onkeyup="this.value=this.value.replace(/[^0-9]+/g,'')"></el-input>
                                        </el-form-item>
                                    </el-col>

                                    <el-col class="input-unit" :span="6" :offset="1">
                                        <!-- Pairs -->
                                        <el-select placeholder="" v-model.trim="form.unit">
                                            <el-option label="Pairs" :value="1"></el-option>
                                            <el-option label="Forty-Foot Container" :value="2"></el-option>
                                            <el-option label="Twenty-Foot Container" :value="3"></el-option>
                                            <!-- <el-option label="Forty-Foot Container" :value="2"></el-option>
                                  <el-option label="Twenty-Foot Container" :value="3"></el-option> -->
                                        </el-select>
                                    </el-col>
                                </el-form-item>

                                <el-form-item label="Price：">
                                    <el-col :span="7">
                                        <el-form-item prop="min_price">
                                            <el-input v-model.trim="form.min_price"
                                                      onkeyup="this.value=this.value.replace(/[^0-9.]+/g,'')"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="1">
                                        <div class="tc">-</div>
                                    </el-col>
                                    <el-col :span="7">
                                        <el-form-item prop="max_price">
                                            <el-input v-model.trim="form.max_price"
                                                      onkeyup="this.value=this.value.replace(/[^0-9.]+/g,'')"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6" :offset="1">
                                        <el-select placeholder="" v-model="form.currency">
                                            <el-option v-for="item in currencyList" :label="item.curname"
                                                       :value="item.id"></el-option>
                                        </el-select>
                                    </el-col>
                                </el-form-item>

                                <el-form-item label="Valid to：" hidden>
                                    <el-col :span="11">
                                        <el-date-picker type="date" readonly placeholder="Selection date"
                                                        v-model="form.valid_date"
                                                        style="width: 100%;"></el-date-picker>
                                    </el-col>
                                </el-form-item>
                            </div>

                            <el-collapse>
                                <el-collapse-item title="Fitter suppliers and set trading terms to get better quotes." name="1">
                                    <el-card class="box-card">
                                        <div slot="header" class="clearfix" style="font-size: 14px;">
                                            Requirements for Trading Terms
                                        </div>

                                        <div class="transport-box">
                                            <!-- 运送方式 -->
                                            <el-form-item label-width="0">
                                                <el-select placeholder="" v-model="form.shipping_type">
                                                    <el-option label="FOB" :value="1"></el-option>
                                                    <el-option label="CIF" :value="2"></el-option>
                                                    <el-option label="CNF" :value="3"></el-option>
                                                    <el-option label="CRF" :value="4"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <!-- 目的地 -->
                                            <el-form-item label-width="0">
                                                <el-input v-model.trim="form.destination" placeholder="Destination Port"></el-input>
                                            </el-form-item>
                                            <!-- 支付方式 -->
                                            <el-form-item label-width="0">
                                                <el-select placeholder="" v-model="form.pay_type">
                                                    <el-option label="T/T" :value="1"></el-option>
                                                    <el-option label="L/C" :value="2"></el-option>
                                                    <el-option label="D/P" :value="3"></el-option>
                                                    <el-option label="Western Union" :value="4"></el-option>
                                                    <el-option label="Money Gram" :value="5"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </div>
                                    </el-card>
                                </el-collapse-item>
                            </el-collapse>

                            <input type="button" class="submit" :disabled="flag" value="Submit" @click="submit">
                        </el-form>

                    </div>


                    <div class="main_right">
                        <header>
                            <div style="margin-right: 6px;">
                                <img src="./static/images/RFQ.png" alt="">
                            </div>
                            <div>
                                <h2 class="header-title2 noWrap">Request a Quote</h2>
                                <p style="font-size:14px;color: #666;">One Request Multiple Quotes</p>
                            </div>
                        </header>

                        <div class="main_rightT">
                            <h3 class="header-title2" style="margin-top:0">Intro to our suppliers and our RFQ system:</h3>
                            We've got hundreds of suppliers in the Shoestp,and all of them are selected excellent
                            original entrusted manufacturers. As long as we receive your request, we will search the
                            suitable supplier and reply to you in the first time.
                        </div>
                        <div class="main_rightB">
                            <h3 class="header-title2">Contact Us</h3>
                            <p>TEL：+86-577-85887589</p>
                            <%--<p>Fax：+86-577-89711316</p>--%>
                            <p>Mail：marketing@shoestp.com</p>
                            <p style="margin-top: 6px;font-size: 12px;color: #777;line-height: 1.4;">Thank you for your
                                interest in our website Any further suggestions are sincere Welcome from
                                marketing@shoestp.com</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <index-bottom></index-bottom>
</div>

<%--<%@ include file="/home/template/new-foot.jsp" %>--%>

<div id="hj_top" style="opacity: 0;">
    <img src="/home/static/images/hj_top.png">
</div>

<script>
    function gtag_report_conversion(url) {
        var callback = function () {
            if (typeof (url) != 'undefined') {
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
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script type="text/javascript">
    // 最低价格
    const validateMinPrice = (rule, value, callback) => {
        if (!value) {
            callback();
            return
            // 小数点验证
        }else if(parseFloat(value) != value){
            callback(new Error('Please enter a right number'));
            return;
        } else if( !/^([1-9]{1}\d{0,5}|([0]{1}))(\.(\d){1,2})?$/.test(value) ){
            callback(new Error('Price can\'t greater than 6 digit integer and 2 decimal places'));
        } else {
            callback();
        }
    };
    // 最高价格 - 不鞥低于最低价
    const validateMaxPrice = (rule, value, callback) => {
        // let pattrn = /^((?!0)\d+(\.\d+)?)$/g
        if (!value) {
            callback();
            return
        // 小数点验证
        }else if(parseFloat(value) != value){
            callback(new Error('Please enter a right number'));
            return;
        } else
        // if (!pattrn.test(value)) {
        //     callback(new Error('Please enter a number type'));
        // }else
            if( !/^([1-9]{1}\d{0,5}|([0]{1}))(\.(\d){1,2})?$/.test(value) ){
            callback(new Error('Price can\'t greater than 6 digit integer and 2 decimal places'));
        } else if (value < (vm.form.min_price ? vm.form.min_price : 0)) {
            callback(new Error('The maximum must be greater than the minimum!'));
        } else {
            callback();
        }
    };

    var vm = new Vue({
        el: "#main",
        data: {
            imgsToUpload: [] // 需要upload的img - 显示在页面上
            ,
            rq_mark: true // 开关
            ,
            flag: false,
            form: { // 需要上传给后台的对象
                title: "",
                image: "",
                descriotion: "",
                quantity: "",
                unit: 1, //单位 - 双
                min_price: null,
                max_price: null,
                currency: 1, //货币单位
                valid_date: null,
                shipping_type: 1, //运送方式
                destination: "", //目的地
                pay_type: 1, //支付方式
            },

            // 货币单位列表
            currencyList: [{
                curname: "USD",
                enabled: true,
                id: 1,
                logo: "/u_file/1501/photo/53092c531f.jpg",
                nowrate: 1,
                rate: 1,
                sitecur: true,
                supcur: true,
                symbol: "$",
            }, {
                    curname: "GBP",
                    enabled: false,
                    id: 3,
            }, {
                curname: "EUR",
                enabled: false,
                id: 2,
            }
                , {
                    curname: "AUD",
                    enabled: false,
                    id: 5,
                }
                , {
                    curname: "CAD",
                    enabled: false,
                    id: 4,
                }
                , {
                    curname: "CHF",
                    enabled: false,
                    id: 6,
                }
                , {
                    curname: "JPY",
                    enabled: false,
                    id: 8,
                }
                , {
                    curname: "HKD",
                    enabled: false,
                    id: 7,
                }
                , {
                    curname: "BRL",
                    enabled: false,
                    id: 10,
                }, {
                    curname: "RUB",
                    enabled: false,
                    id: 9,
                }, {
                    curname: "CLP",
                    enabled: false,
                    id: 11,
                }, {
                    curname: "NOK",
                    enabled: false,
                    id: 12,
                }, {
                    curname: "DKK",
                    enabled: false,
                    id: 13,
                }, {
                    curname: "SEK",
                    enabled: false,
                    id: 14,
                }, {
                    curname: "KRW",
                    enabled: false,
                    id: 15,
                }, {
                    curname: "ILS",
                    enabled: false,
                    id: 16,
                }, {
                    curname: "MXN",
                    enabled: false,
                    id: 17,
                }],

            rules: {
                title: [{
                    required: true,
                    message: 'Please enter key words for inquiry',
                    trigger: 'blur'
                },
                    {
                        min: 1,
                        max: 500,
                        message: 'Content cannot exceed 500 characters',
                        trigger: 'blur'
                    }
                ],
                descriotion: [{
                    required: true,
                    message: 'Please enter description information',
                    trigger: 'blur'
                }],
                quantity: [{
                    required: true,
                    message: 'Please enter quantity',
                    trigger: 'blur'
                },
                    {
                        pattern: util_regular_obj.register.positiveInteger,
                        message: 'Please enter the positive integer，and can\'t beyond 10 digits',
                        trigger: 'blur'
                    }
                ],
                min_price: [{
                    validator: validateMinPrice,
                    trigger: 'blur',
                    type: "number"
                }],
                max_price: [{
                    validator: validateMaxPrice,
                    trigger: 'blur',
                    type: "number"
                }],
            }
        },
        methods: {
            // 获取地址里的参数
            GetQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(decodeURIComponent(r[2]));
                return null;
            },
            //完成后跳转回哪个页面
            // - 参数为手输的默认地址
            // - 地址中有值时用地址中的值，否则用调用时的参数地址，也没填写时跳转回首页
            // elementui 上传功能 *2 - 删除操作
            handleRemove(file, fileList) {
                // 清空imgs数组
                this.imgsToUpload = [];
                // 删除图片后，将授予的图片地址放入数组
                $.each(fileList, (i, v) => {
                    this.imgsToUpload.push(v.response.result.url)
                })

                if (!(this.imgsToUpload.length >= 5)) {
                    $(".chooseImg .el-upload.el-upload--picture-card").show();
                }
            },
            beforeUpload(file) {
                if (!sysConfig || !sysConfig.user) {
                    sessionStorage['Temp_publish_form'] = JSON.stringify(this.form)
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }
                const isLt2M = file.size / 1024 < 500;

                if (!isLt2M) {
                    this.$message.error('Picture size should not exceed 500k!');
                }
                return isLt2M;
            },
            // elementui 上传功能 *2 - 上传成功操作
            handlePictureCardPreview(file, p2, p3) {
                if (file.ret != 1) {
                    p3.splice(p3.length - 1, 1);
                    return;
                }
                // 添加图片后，在前面显示 img
                this.imgsToUpload.push(file.result.url);

                // 勾选商品后 - 上传的图片4张时，隐藏添加按钮
                if (this.imgsToUpload.length >= 5) {
                    $(".chooseImg .el-upload.el-upload--picture-card").hide();
                }
            },

            // 整个form提交
            submit() {
                // if (!sysConfig || !sysConfig.user) {
                //     sessionStorage['Temp_publish_form'] = JSON.stringify(this.form)
                //     this.$alert('Please login to operate', {
                //         confirmButtonText: 'Ok',
                //         customClass: "my-custom-element-alert-class fs-content-18",
                //         center: true,
                //         callback: action => {
                //             if(action=="confirm"){
                //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView"
                //             }
                //         }
                //     });
                //     return
                // }

                if (!sysConfig || !sysConfig.user) {
                    sessionStorage['Temp_publish_form'] = JSON.stringify(this.form);
                    util_function_obj.alertWhenNoLogin(this);
                    return;
                // 供应商不能提交
                }else if(sysConfig && sysConfig.user && sysConfig.user.user_type == 1){
                    this.$alert("Sorry, the supplier cannot submit the form",{
                        confirmButtonText: 'Ok',
                        customClass: "my-custom-element-alert-class fs-content-18",
                        center: true,
                        callback: action =>{
                            return
                        }
                    });
                    return
                }

                this.$refs.form.validate((valid) => {
                    if (valid) {
                        console.log('submit validate suc');
                        this.flag = true;

                        // this.submitObj.v.image =   this.imgsToUpload.join(",");
                        var submitObj = Object.assign({}, this.form);
                        submitObj.images = this.imgsToUpload.join(",");
                        submitObj.min_price = this.form.min_price || 0;
                        submitObj.max_price = this.form.max_price || 0;
                        var submitData = JSON.stringify(submitObj);

                        // 如果没有上传图片
                        // if (this.imgsToUpload.length == 0) {
                        //     this.flag = false;
                        <%--//     if(${env.login==null}){--%>
                        //         this.$message({
                        //             showClose: true,
                        //             message: 'Pleaselogin',
                        //             type: 'warning'
                        //         });
                        //
                        //         sessionStorage["submitObj"]=submitData;
                        //         window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
                        //     }
                        //     this.$message.error('my-inquiry-publish.Some_Pictures');
                        //     return
                        // }

                        if (this.rq_mark) {
                            this.rq_mark = false;

                            axios.post('/home/rfq_RFQConsult_putRFQInquiry', submitObj)
                                .then((res) => {
                                    this.rq_mark = true;
                                    // 提交成功时
                                    if (res.data.ret == 1) {
                                        console.log('提交成功1');
                                        // 提示信息
                                        this.$message({
                                            showClose: true,
                                            message: '<s:text name="my-inquiry-publish.Successfully_Released"/>',
                                            type: 'success'
                                        });
                                        setTimeout( () => {
                                            gtag_report_conversion()
                                            sessionStorage['Temp_publish_form'] = { // 需要上传给后台的对象
                                                title: "",
                                                image: "",
                                                descriotion: "",
                                                quantity: "",
                                                unit: 1, //单位 - 双
                                                min_price: null,
                                                max_price: null,
                                                currency: 1, //货币单位
                                                valid_date: null,
                                                shipping_type: 1, //运送方式
                                                destination: "", //目的地
                                                pay_type: 1, //支付方式
                                            }
                                            // 有传回跳地址时直接回跳，否则跳首页
                                            let url = window.location.href;
                                            if(url.indexOf("backUrl=")!=-1){
                                                let backUrl = url.substr(url.indexOf("backUrl")+8);
                                                window.location.href = backUrl;
                                            }else{
                                                window.location.href = "/";
                                            }

                                        }, 2000)
                                        // 未登录时
                                    } else if (res.data.ret == -1) {
                                        sessionStorage['Temp_publish_form'] = JSON.stringify(this.form)
                                        util_function_obj.alertWhenNoLogin(this);
                                        return
                                    } else {
                                        console.log('提交失败else');
                                        this.flag = false;
                                        this.$alert(res.data.msg || "Submit error,please try again later", {
                                            confirmButtonText: '<s:text name="my-inquiry-publish.Ok"/>'
                                        });
                                    }

                                })
                                .catch((err) => {
                                    this.flag = false;
                                    this.rq_mark = true;
                                    this.$message.error(err || 'Network error,please try again later');
                                })
                        }
                    } else {
                        console.log('submit validate error!!');
                    }
                });
            },

        },
        mounted() {
            if (sessionStorage['Temp_publish_form']) {
                this.form = JSON.parse(sessionStorage['Temp_publish_form'])
                // Vue.$set()
            }
            // 其他页面带参数跳转过来时，将参数赋值显示
            if (this.GetQueryString("title")) {
                this.form.title = this.GetQueryString("title");
            }
            if( this.GetQueryString("quantity") ){
                var quantity = Number(this.GetQueryString("quantity"));
                if( isNaN(quantity) ) return;
                this.form.quantity = quantity;
            }
              if( this.GetQueryString("chooesValue") ){
                var chooesValue = Number(this.GetQueryString("chooesValue"));
                if( isNaN(chooesValue) ) return;
                this.form.unit = chooesValue;
            }



            // 全局获取货币单位
            // this.currencyList = [];
            // if(sysConfig){
            //   sysConfig.currencyList.forEach((val,index)=>{
            //     if( val.enabled ){
            //       this.currencyList.push(val)
            //     }
            //   })
            // }
        }
    })
</script>
</body>

</html>
