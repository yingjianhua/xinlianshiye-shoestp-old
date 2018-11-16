<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>

    <link href="/home/static/themes/default/mobile/css/xunpanMC.css" rel="stylesheet"
          type="text/css">
    <script type="text/javascript"
            src="/home/static/themes/default/mobile/js/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/en(1).js"></script>

    <style type="text/css">
        .layui-m-layermain div {
            color: inherit;
        }

        .imgbox {
            float: left;
            position: relative;
            width: 20%;
            padding-top: 20%;
            margin: 2rem 3% 0.5rem 2%;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

        .imgbox.imgbox-add {
            border-width: 0;
        }

        .imgbox.imgbox-add .imgnum {
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
        }

        .imgbox .imgnum {
            position: absolute;
            left: 5%;
            top: 5%;
            width: 90%;
            height: 90%;
        }

        /* .imgbox .imgnum .btn-add-img{
            transform: rotate(45deg);
        } */
        .imgbox .imgnum .img-show {

        }

        .imgbox .imgnum input {
            position: absolute;
            z-index: 9;
            width: 100%;
            height: 100%;
            opacity: 0;
        }

        .imgbox .imgnum img {
            width: 100%;
            height: 100%;
            object-fit: contain;
            border-radius: 3px;
        }

        .imgbox .close {
            color: #fff;
            position: absolute;
            right: -5%;
            left: -5%;
            top: -12%;
            padding: 0.3rem;
            border: 1px solid #ddd;
            border-radius: 2px;
            text-align: center;
            background: #f30;
            transform: translateY(-100%);
        }
    </style>

</head>

<body>
<%@ include file="/mobile/template/header.jsp" %>
<main id="app">

    <div class="box">
        <div class="main">
            <div class="goods-box">
                <ul>
                </ul>

            </div>
            <div class="main_left">
                <header>
                    <div>
                        <img src="/home/static/themes/default/mobile/images/RFQ.png" alt="">
                    </div>
                    <div>
                        <h2 style="font-size:18px">
                            <!-- Request for Quotation -->
                            <s:text name="inq"/>
                        </h2>
                        <p class="color666">
                            <!-- One Request，Multiple Quotes -->
                            <s:text name="Global.One_Request"/>
                        </p>
                    </div>
                </header>
                <div>
                    <h1>
                        <!-- Tell suppliers what you need -->
                        <s:text name="tellSuppliers"/>
                    </h1>
                    <p class="font-bold" style="margin-bottom:10px">
                        <!-- Complete Your RFQ -->
                        <s:text name="CompleteRFQ"/>
                    </p>
                    <p class="color666">
                        <!-- The more specific your information, the more accurately we can match your
                        request to the right suppliers -->
                        <s:text name="CorrectMatching"/>
                    </p>
                </div>
                <%-- action="https://www.shoestp.com/inquiry.html"  enctype="multipart/form-data" --%>
                <form method="post"
                      name="inquiry" id="inquiry_form">
                    <!-- 询盘中 新添选择产品进行询盘 -->
                    <div class="form_header marginBottom">
                        <input id="title" class="selected-product-name inputHeight marginRight"
                               notnull="" placeholder="<s:text name="title" />"
                               v-model.lazy.trim="submitObj.v.title"/>
                        <div class="btn-select-product" @click="toSelectPdt"><!-- Select Product --><s:text
                                name="selectProduct"/></div>
                    </div>

                    <!-- 选择商品外框 -->
                    <div class="modal-wrap choose-goods-wrap">
                        <div class="modal-header clearfix">
                            <span class="fr close">&times;</span>
                        </div>
                        <div class="modal-content">
                            <div class="choose-goods-list">

                                <div class="goods-item clean"
                                     :class="[goods.id==param_product_id?'selected':'']"
                                     :data-goods-id="goods.id"
                                     :data-product-id="goods.productId"
                                     :data-goods-index="index"
                                     v-for="(goods,index) in goodsList"
                                     :key="goods.id"
                                     @click="clickPdt">
                                    <!-- 是否选中的图标标识 -->
                                    <i class="icon-is-selected"></i>
                                    <!-- 商品图片 -->
                                    <div class="goods-pic fl">
                                        <img :src="'${envConfig.imageBaseUrl}'+goods.imgs.split(',')[0] || '/home/static/themes/default/mobile/images/1ed13be5ce.jpg.500x500.jpg'"
                                             alt="">
                                    </div>
                                    <!-- 商品描述 -->
                                    <div class="goods-descript">
                                        <div class="goods-name">{{goods.name}}</div>
                                        <div class="goods-spec">
                                            <div class="goods-spec-name">
                                                {{goods.code}}
                                            </div>
                                            <div class="close" :data-goods-id="goods.id">&times;
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div v-show="goodsList.length==0"
                                 style="text-align: center;padding: 15px;">
                                <!-- There is no goods to be selected. -->
                                <s:text name="beselected"/>
                            </div>
                        </div>
                        <div class="modal-footer flex">
                            <label for="use-title" class="flex-grow">
                                <input type="checkbox" id="use-title">
                                <span style="color: #777;"><!-- Use products title as RFQ title --><s:text
                                        name="RFQ_title"/></span>
                            </label>
                            <%-- <input class="btn fr" type="button" value="Submit" @click="submitSelectedGoods">
                            <input class="btn fr btn-cancel-chooseGoods" type="reset" value="Cancel" @click="cancelSelectedGoods"> --%>
                            <div>
                                <input class="btn" type="button" value="Submit"
                                       @click="submitSelectedGoods">
                                <input class="btn btn-cancel-chooseGoods" type="reset"
                                       value="Cancel" @click="cancelSelectedGoods">
                            </div>
                        </div>
                    </div>
                    <div class="modal"></div>

                    <div class="form_header marginBottom">
                        <input name="LastName" value="" type="text"
                               placeholder=" <s:text name="user.name" />"
                               class="inputHeight marginRight"
                               style="width:40%" notnull="" v-model.lazy.trim="submitObj.v.name">
                        <input name="Email" value="" type="text"
                               placeholder=" <s:text name="user.email" />" class="inputHeight"
                               style="width:60%" notnull="" v-model.lazy.trim="submitObj.v.email">
                    </div>
                    <div class="form_header marginBottom">
                        <select name="Country" notnull="" class="inputHeight" style="width: 100%;"
                                v-model="submitObj.v.country">
                            <option value="undefined">
                                ---<!-- Please Select Your Country --><s:text
                                    name="mobile.plz_sel"/>---
                            </option>
                            <option :value="country.id" v-for="country in countryList">
                                {{country.name}}
                            </option>
                        </select>
                    </div>
                    <div class="form_main marginBottom">
                        <!-- Product Detailed Specifications. Describe about your perferred unit price, destination port and the payment methods. -->
                        <textarea name="Message" id="" class="form_textarea"
                                  placeholder=" <s:text name="Product_detailed" />"

                                  notnull="" v-model.lazy.trim="submitObj.v.content">
                                </textarea>
                        <div class="clean">

                            <%-- 勾选商品后显示的imgs --%>
                            <div class="imgbox" v-if="selectGoodsIndex != -1">
                                <div class="imgnum">
                                    <img :src="'${envConfig.imageBaseUrl}'+goodsList[selectGoodsIndex].imgs.split(',')[0]"
                                         class="img-show"/>
                                </div>
                            </div>

                            <%-- 添加后显示的imgs --%>
                            <div class="imgbox" v-if="imgsToUpload.length > 0"
                                 v-for="(imgUrl, index) in imgsToUpload">
                                <div class="imgnum">
                                    <span class="close" :data-index="index"
                                          @click="deleteImg"><s:text name="user.delete"/></span>
                                    <img :src='"${envConfig.imageBaseUrl}"+imgUrl'
                                         class="img-show"/>
                                </div>
                            </div>

                            <%-- 添加图片的按钮 --%>
                            <%-- 选中了goods时，图片最多只能传4张 --%>

                            <div class="imgbox imgbox-add"
                                 v-show="(selectGoodsIndex >= 0 && imgsToUpload.length < 4) || (selectGoodsIndex == -1 && imgsToUpload.length <= 4)">
                                <div class="imgnum">
                                    <input type="file" class="filepath" id="input-img"
                                           accept="image/png,image/gif,image/jpeg"
                                           @change="changeImg"/>
                                    <img src="/home/static/themes/default/mobile/images/icon-add-pic.png"
                                         class="btn-add-img"/>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="form_footer marginBottom">
                        <input name="Subject" type="text"
                               placeholder=" <s:text name="enterQuantity" />" class="inputHeight"
                               notnull=""
                               style="width:30%;margin-right:0.5rem;"
                               v-model.lazy.trim.number="submitObj.v.quantity">
                        <input name="VCode" type="text"
                               placeholder=" <s:text name="review.vcode" />:" class="inputHeight"
                               notnull=""
                               style="margin-right:0.3rem;width:36%"
                               v-model.lazy.trim="submitObj.vCode">
                        <div class="form_code" style="width:32%">
                            <img id="imgVcode" src="/servlet/verify.img"
                                 onclick="this.src=&quot;/servlet/verify.img?r=&quot;+Math.random();"
                                 style="cursor:pointer; width: 100%; height: 100%; object-fit: contain;">
                        </div>
                    </div>
                    <div>
                        <div>
                            <input name="Submit" type="button" class="submit" id="sub_btn000"
                                   value="<s:text name="submit" />" @click="submit">
                        </div>
                    </div>
                    <input name="Site" value="en" type="hidden" class="inputHeight marginRight">
                    <input type="hidden" name="do_action" value="action.submit_inquiry">
                </form>
                <div class="footer">
                    <div>
                        <h2>
                            <!-- How to Get Quotes quickly? -->
                            <s:text name="quotesQuickly"/>
                        </h2>
                        <div class="color666">
                                    <span>
                                        <!-- Submit RFQ -->
                                        <s:text name="submit"/> RFQ
                                    </span>
                            →
                            <span>
                                        <!-- Compare Quotes -->
                                        <s:text name="compareQuotes"/>
                                    </span>
                            →
                            <span>
                                        <!-- Contact Supplier -->
                                        <s:text name="Contact_supplier"/>
                                    </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main_right">
                <div class="main_rightT color666">
                    <h2 style="margin-top:0;color:black">
                        <!-- About Posting Buy Offer -->
                        <s:text name="AIPP"/>
                    </h2>
                    <p>
                        <!-- Here, you can ask the price and delivery -->
                        <s:text name="AIPPDetails"/>
                    </p>
                    <!-- <p>
                        deadline of products in our website.Besides,
                    </p>
                    <p>
                        you can post buy offers or customize the products.
                    </p>
                    <p>
                        After receiving your requirements,
                    </p>
                    <p>
                        we will match the suitable shoes factories for you.
                    </p> -->
                </div>
                <div class="main_rightB color666">
                    <h2 style="color:black">
                        <!-- Contact us -->
                        <s:text name="contactUs"/>
                    </h2>
                    <p>
                        <!-- TEL --><s:text name="Telephone"/>：+86-577-85887585 <!-- Fax --><s:text
                            name="fax"/>：+86-577-89711316
                    </p>
                    <p>
                        <!-- Mail --><s:text name="review.email"/>：marketing@shoestp.com
                    </p>
                    <p>
                        <!-- Thanks for your interest in our website -->
                        <s:text name="ThanksAttention"/>
                    </p>
                    <!-- <p>
                        Any further suggestions are sincerely
                    </p>
                    <p>
                        welcomed by us xiuchuan@shoestp.cn
                    </p> -->
                </div>
            </div>
        </div>
    </div>
</main>

<footer>
    <div id="prolist_mask_footer">
    </div>
    <div class="footer_top clean">
    </div>
    <ul class="footer_list ui_border_b clean" style="display:none;">
        <li class="fl" style="border-right:1px solid #ddd;">
            <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                        <span class="list_left">
                            <!-- Sign In -->
                            <s:text name="sign_in"></s:text>
                        </span>
                <span class="list_right">
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
            </a>
        </li>
    </ul>
    <nav>
    </nav>
    <section class="font_col border_col copyright">
        Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1
        浙公网安备 33030402000493号
    </section>
</footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>

<%-- start 用 vue --%>
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

    var vm = new Vue({
        el: "#app",
        data: {
            imgsToUpload: []       // 需要upload的img - 显示在页面上
            , rq_mark: true        // 开关
            , goodsList: []        //供选择的商品列表
            , selectGoodsIndex: -1       //勾选的商品下标
            , countryList: []        //城市列表
            , param_product_id: -1 //传过来的id(询盘商品列表对应的id)
            , submitObj: {          // 需要上传给后台的对象
                vCode: "",
                v: {
                    title: "",
                    image: "",
                    product: undefined,
                    name: "",
                    email: "",
                    country: undefined,
                    quantity: undefined,
                    content: "",
                    supplierId: -1  //商店进来时，携带 商店参数
                }
            }
        },
        methods: {
            // 点击选择商品 - 远程获取 goodsList
            toSelectPdt() {
                // 清空之前选中的，如果之前选中并提交过，则选中该goods
                $(".choose-goods-wrap .goods-item").removeClass("selected")
                if (this.selectGoodsIndex != -1) {
                    $(".choose-goods-wrap .goods-item").eq(this.selectGoodsIndex).addClass("selected");
                }

                $(".modal").fadeIn();
                $(".modal-wrap.choose-goods-wrap").fadeIn();
            },

            // 点击商品 显示选中 or 取消选中
            clickPdt(e) {
                let currentTarget = $(e.currentTarget);
                // 点击的是删除
                if ($(e.target).hasClass("close")) {
                    layer.open({
                        content: lang_obj.my_inquiry_publish.deletethisgoods
                        , btn: ['yes', 'no']
                        , yes: (index) => {
                            layer.close(index);
                            axios.post('/home/pdt_PdtConsultPdtList_deletes',
                                Qs.stringify({ids: currentTarget.data("goodsId")}))
                                .then((res) => {

                                    // 提交成功时
                                    if (res.data.ret == 1) {
                                        currentTarget[0].style.display = "none";
                                        // 如果删除的是已选中的商品，清空显示在页面上的商品信息
                                        if (this.goodsList[this.selectGoodsIndex].id == e.target.dataset.goodsId) {
                                            this.selectGoodsIndex = -1;
                                            this.submitObj.v.product = "";
                                        }
                                        // this.goodsList.splice( currentTarget.data("goodsIndex"), 1 );
                                        // 提示信息
                                        layer.open({
                                            content: 'delete success!'
                                            , style: 'bottom: auto;'
                                            , skin: 'msg'
                                            , time: 2 //2秒后自动关闭
                                        });
                                        // 提交失败时

                                    } else {
                                        layer.open({
                                            content: res.data.msg
                                            , btn: 'ok'
                                        });
                                    }
                                })

                        }
                    });
                    // 否则是选中 事件
                } else {
                    $(e.currentTarget).toggleClass("selected").siblings(".goods-item").removeClass(
                        "selected");
                }
            },

            // 取消弹框选择
            cancelSelectedGoods() {
                $(".modal").fadeOut();
                $(".modal-wrap.choose-goods-wrap").fadeOut();
            },

            // 选好商品后提交商品 （确认弹框）
            submitSelectedGoods() {
                // 如果选中商品，复制图片地址 及 goodsId
                let target = $(".choose-goods-wrap .goods-item.selected");
                if (target.length > 0) {
                    this.selectGoodsIndex = target.data("goodsIndex");
                    this.submitObj.v.product = target.data("productId");

                    // 如果勾选了"use products title"
                    if ($("#use-title").prop("checked")) {
                        this.submitObj.v.title = target.find(".goods-name").text();
                    }
                    // 否则清空上传的img地址 及 goodsId
                } else {
                    this.selectGoodsIndex = -1;
                    this.submitObj.v.product = "";
                    //layer.open({content:'No product was selected.',btn: 'ok'});
                }

                // 模态框隐藏
                $(".modal").fadeOut();
                $(".modal-wrap.choose-goods-wrap").fadeOut();
            },
            // 整个form提交
            submit() {
                if (!this.isFormatRight()) return;
                // 拼接多图 地址
                // this.submitObj.v.image =   this.imgsToUpload.join(",");
                let goodsIndex = $(".choose-goods-list .goods-item.selected").data("goodsIndex"); //获取选中的商品所在list的下标
                // 提交时，如果勾选了商品，则上传的图片第一张为该商品图，后面4张为上传的图片
                if ($(".choose-goods-list .goods-item.selected").length > 0) {
                    if (this.imgsToUpload.length > 4) {
                        this.submitObj.v.image = this.goodsList[goodsIndex].imgs.split(',')[0] + ","
                            + this.imgsToUpload.slice(0, 4).join(",");
                    } else if (this.imgsToUpload.length == 0) {
                        this.submitObj.v.image = this.goodsList[goodsIndex].imgs.split(',')[0]
                    } else {
                        this.submitObj.v.image = this.goodsList[goodsIndex].imgs.split(',')[0] + ","
                            + this.imgsToUpload.join(",");
                    }
                } else {
                    this.submitObj.v.image = this.imgsToUpload.join(",");
                }

                // 如果没有选择goods，or没有上传图片
                if (this.submitObj.v.image.length == 0) {
                    layer.open({
                        content: lang_obj.my_inquiry_publish.Pleaseselect_a_goods
                        , style: 'bottom: auto;'
                        , skin: 'msg'
                        , time: 3 //2秒后自动关闭
                    });
                    return
                }

                // axios({
                //     method: 'post',
                //     url: '/home/usr_UsrConsult_publish',
                //     headers: {
                //         'Content-type': 'application/x-www-form-urlencoded'
                //     },
                //     params: {
                //       'vCode':this.submitObj.vCode,
                //       'v.title':this.submitObj.v.title,
                //       "vCode":this.submitObj.vCode,
                //       "v.title":this.submitObj.v.title,
                //       "v.image":this.submitObj.v.image,
                //       "v.product":this.submitObj.v.product,
                //       "v.name":this.submitObj.v.name,
                //       "v.email":this.submitObj.v.email,
                //       "v.country":this.submitObj.v.country,
                //       "v.quantity":this.submitObj.v.quantity,
                //       "v.content":this.submitObj.v.content
                //     }
                // })
                //     .then((response) => {
                //         console.log(response);
                //     })
                //     .catch((error) => {
                //         console.log(error);
                //     });

                if (this.rq_mark) {
                    this.rq_mark = false;

                    axios.post('/home/usr_UsrConsult_publish',
                        Qs.stringify(this.submitObj, {allowDots: true}))
                        .then((res) => {
                            this.rq_mark = true;

                            // 提交成功时
                            if (res.data.ret == 1) {
                                // 提示信息
                                layer.open({
                                    content: lang_obj.my_inquiry_publish.submitsuccess
                                    , style: 'bottom: auto;'
                                    , skin: 'msg'
                                    , time: 2 //2秒后自动关闭
                                });
                                setTimeout(function () {
                                    gtag_report_conversion()

                                    window.location.href = '/home/usr_UsrConsult_listView';
                                }, 2000)

                                // 未登录时
                            } else if (res.data.ret == -1) {
                                if (this.param_product_id != -1) {
                                    window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView?product_id='
                                        + this.param_product_id;
                                } else {
                                    window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
                                }
                                // 提交失败时
                            } else {
                                layer.open({
                                    content: res.data.msg
                                    , btn: 'ok'
                                });
                                $("#imgVcode").attr("src", "/servlet/verify.img?r=" + Math.random())
                            }

                        })
                        .catch((err) => {
                            this.rq_mark = true;
                        })
                }

            },

            // 判断form内容是否符合格式
            isFormatRight() {
                let inquiry_form = $('#inquiry_form');
                let notnull = $('*[notnull]', inquiry_form);
                notnull.removeClass('null');

                var status = 0;
                // 判断是否为空
                notnull.each(function (index, element) {
                    if ($(element).val() == '' || $(element).val() == "undefined") {
                        $(element).addClass('null').focus();
                        status = 1;
                        // 提示信息
                        layer.open({
                            content: lang_obj.my_inquiry_publish.completeinformation
                            , style: 'bottom: auto;'
                            , skin: 'msg'
                            , time: 2 //2秒后自动关闭
                        });
                    } else {
                        $(element).removeClass('null');
                    }
                });

                // 标题长度限制
                if ($('#title').val().trim().length > 200) {
                    $('#title').addClass('null').focus();
                    status = 1;
                    // 提示信息
                    layer.open({
                        content: lang_obj.my_inquiry_publish.thecompatibletitle
                        , style: 'bottom: auto;'
                        , skin: 'msg'
                        , time: 2 //2秒后自动关闭
                    });
                }
                var Email = $('input[name=Email]', inquiry_form);
                if (/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(
                    Email.val()) == false) {
                    Email.addClass('null').focus();
                    status = 1;
                    // 提示信息
                    layer.open({
                        content: lang_obj.my_inquiry_publish.thecorrectEmail
                        , style: 'bottom: auto;'
                        , skin: 'msg'
                        , time: 2 //2秒后自动关闭
                    });
                }
                var quantity = $('input[name=Subject]', inquiry_form);
                if (/^[0-9]*$/.test(quantity.val()) == false || quantity.val() > 2147483647) {
                    quantity.addClass('null').focus();
                    status = 1;
                    // 提示信息
                    layer.open({
                        content: lang_obj.my_inquiry_publish.thecorrectquantity
                        , style: 'bottom: auto;'
                        , skin: 'msg'
                        , time: 2 //2秒后自动关闭
                    });
                }

                if (status) {
                    return false;
                }
                return true;
            },

            // 上传图片更改
            changeImg(e) {
                let file = e.target.files[0];
                let formImgData = new FormData()  // 创建form对象
                formImgData.append('file', file, file.name)  // 通过append向form对象添加数据
                axios.post('/home/usr_UsrConsult_upload', formImgData, {
                    headers: {'Content-Type': 'multipart/form-data'}
                })
                    .then(response => {
                        if (response.data.ret == 1) {
                            // 添加图片后，在前面显示 img
                            this.imgsToUpload.push(response.data.result.url);
                        }
                    })
            },
            // 点击删除 upload的img
            deleteImg(e) {
                let index = $(e.target).data("index");
                this.imgsToUpload.splice(index, 1);
            }

        },

        mounted() {
            // 获取国家列表
            axios.get('/home/plt_PltCountry_list')
                .then((res) => {
                    this.countryList = res.data.result;
                })
                .catch((err) => {
                    console.log(lang_obj.my_inquiry_publish.goodsListerr);
                });

            // 获取商品列表
            this.submitObj.v.supplierId = getQueryString("supplierId") ? getQueryString("supplierId")
                : -1;
            axios.get('/home/pdt_PdtConsultPdtList_list',
                {params: {supplierId: this.submitObj.v.supplierId}})
                .then((res) => {

                    if (res.data.ret == 1) {
                        this.goodsList = res.data.result;

                        // 如果是在商品页 添加了inquiry商品后 直接点进来的，则默认勾选该商品
                        if (getQueryString("product_id")) {
                            this.param_product_id = getQueryString("product_id");
                            $.each(res.data.result, (index, value) => {
                                if (value.id == this.param_product_id) {
                                    $("#use-title").prop("checked", true);
                                    this.selectGoodsIndex = index;
                                    this.submitObj.v.product = value.productId;
                                    this.submitObj.v.title = value.name;
                                }
                            })
                        }
                    } else if (res.data.ret == -1) {
                        if (this.param_product_id != -1) {
                            window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView?product_id='
                                + this.param_product_id;
                        } else {
                            window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
                        }
                    }
                })
                .catch((err) => {
                    console.log(lang_obj.my_inquiry_publish.goodsListerr);
                });
        }
    })

    // 点击删除弹出框
    $(".modal-wrap .modal-header .close,.modal-wrap .btn-cancel-chooseGoods").click(function () {
        $(".modal").fadeOut();
        $(".modal-wrap.choose-goods-wrap").fadeOut();
        $("#use-title").prop("checked", false);
    })
</script>

</body>
</html>
