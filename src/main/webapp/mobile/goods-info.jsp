<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">

<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <link href="../home/static/themes/default/mobile/css/goods.css" rel="stylesheet" type="text/css">
    <style>
        /* 内容高度与宽度相等显示 */
        .h100p_box {
            position: relative;
            padding-top: 100%;
        }

        /* 轮播外框  - 高度与宽度相等 */
        .carousel-wrap {
            position: absolute;
            left: 0;
            top: 0;
            height: 100%;
            width: 100%;
        }

        /* 图片contain显示 */
        .img-contain {
            height: 100%;
            width: 100%;
            object-fit: contain;
        }

        .detail_desc table {
            border-collapse: collapse;
            width: 100%;
        }

        .detail_desc table td {
            border: 1px solid #ccc;
        }

        /* 轮播图片占满 */
        .el-carousel__item img {
            width: 100%;
            height: 100%;
            object-fit: contain;
        }

        /* element-ui样式更改 */
        .el-message {
            min-width: 80%;
            width: 80%;
        }

        .el-carousel__button {
            width: 0.6rem;
            height: 0.6rem;
            background-color: #000000;
            border-radius: 50%;
        }

        .btn-to-publish {
            display: inline-block;
            line-height: 1;
            white-space: nowrap;
            cursor: pointer;
            background: #fff;
            border: 1px solid #dcdfe6;
            border-color: #dcdfe6;
            color: #606266;
            -webkit-appearance: none;
            text-align: center;
            box-sizing: border-box;
            outline: none;
            margin: 0;
            transition: .1s;
            font-weight: 500;
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            font-size: 14px;
            border-radius: 4px;
            color: #fff;
            background-color: #67c23a;
            border-color: #67c23a;
            padding: 7px 15px;
        }

        .swiper-button-prev {
            width: 20px;
            height: 20px;
            border-top: 2px solid #999;
            border-left: 2px solid #999;
            transform: rotate(-45deg);
            background: none;
        }

        .swiper-button-next {
            width: 20px;
            height: 20px;
            border-top: 2px solid #999;
            border-right: 2px solid #999;
            transform: rotate(45deg);
            background: none;
        }

        .soldout {
            background-color: #D1D1D1 !important;
            border-color: #999 !important;
        }

        .mr10 {
            margin-right: 10px;
        }
    </style>
</head>

<body>
<%@include file="/mobile/template/header.jsp" %>
<div class="wrapper" id="goods_main">
    <div class="txt" style="background-color:#0c1B8E;display: flex;align-items: center;padding: 10px 15px;">
        <img alt="" :src="'${envConfig.imageBaseUrl}'+result.logo" width="65" height="50" class="mr10">
        <span class="mr10" style="color:white">{{result.supName}}</span>
        <div v-if="result.authTime==1" style="display: flex;align-items: center;">
            <a :href="'/home/usr_UsrSupplier_gtSupInfo?pkey='+result.supId" class="mr10">
                <img class="icon" src="./static/images/certification-o.png" alt="">
            </a>
            <a :href="'/home/usr_UsrSupplier_gtSupInfo?pkey='+result.supId" class="mr10">
                <img class="icon" src="./static/images/defence-safe-o.png" alt="">
            </a>
        </div>
    </div>
    <div class="crumb clean">
        <a href="/">
                    <span class="icon_crumb_home">
                    </span>
        </a>
        <a :href="'/home/pdt_PdtProduct?cated='+k.pkey" v-for="(k,index) in result.breadcrumbNav">
            {{k.name}}
            <em>
                <i>
                </i>
            </em>
        </a>
    </div>

    <div class="detail_pic clean ui_border_b">
        <div class="h100p_box">
            <el-carousel :interval="3000" arrow="always" ref="image" height="100%" class="carousel-wrap">
                <el-carousel-item v-for="(item, index) in _img" :key="item.id" :name="item.name" v-if="index < 6">
                    <img :src="'${envConfig.imageBaseUrl}'+item.src" class="img-contain"/>
                </el-carousel-item>
            </el-carousel>
        </div>
        <!-- Swiper -->
        <%-- <div class="swiper-container goods-info-swiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide" v-for="item in _img" :key="item.id" :name="item.name">
                        <img :src="'${envConfig.imageBaseUrl}'+item.src"/>
                </div>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
            <!-- Add Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>
        --%>
        <!-- 抛物线的div -->
    </div>
    <div class="goods_info clean">
        <div class="prod_info_name">
            {{result.pdtName}}
        </div>
        <div class="prod_info_brief">
            <!-- Item Code -->
            <div><s:text name="Global.Product_Number"/>: {{_itemCode}}
                <div style="display: inline;border: 1px solid black;text-align: center;margin-left: 10px;"><a
                        :href="linkToShop" style="font-weight: bold;">{{result.supName}}</a></div>
            </div>
        </div>
        <div class="clean prod_info_price">
            <div class="box_price clean price_1 last_price">
                <div class="fl title">
                    <!-- Reference Price -->
                    <s:text name="price"/>:
                </div>
                <div class="fl">
                    <div class="price cur_price">
                        <span>
                                {{result.currency_symbol}}
                        </span>
                        {{_price}}
                    </div>
                </div>
            </div>
        </div>
        <div class="clean prod_info_line ui_border_t">
            <div class="clean rows attr_show none" name="Color">
                <div class="title">
                    <!-- Color -->
                    <s:text name="color"/>:
                </div>
                <div class="txt">
                                <span :title="v" v-for="(k,v) in result.spec" @click.prevent="select(v)"
                                      :class="{'soldout':!k.colorFlag, selected:status.color==v}">
                                    {{v}}<em>X</em>
                                </span>
                </div>
            </div>
            <div class="clean rows attr_show none" name="Size" v-show="status.color!=null">
                <div class="title">
                    <!-- Size -->
                    <s:text name="product.size_attr"/>:
                </div>
                <div class="txt" v-for="(k,v) in result.spec">
                                <span class="" :title="d.size" :disable="d.sizeFlag" v-for="d in k"
                                      v-on:click="select(d)"
                                      :class="{'soldout':d.sizeFlag,selected:status.size==d &&status.color==d.color}"
                                      v-show="status.color==d.color">
                                        {{d.size}}
                                    <em>
                                        X
                                    </em>
                                </span>
                </div>
            </div>

            <div class="clean rows">
                <div class="title">
                    <!-- MOQ -->
                    <s:text name="products.moq"/>:
                </div>
                <div class="txt">
                    {{result.min_oq}}
                </div>
            </div>
            <div class="clean rows prod_info_qty">
                <div class="title">
                    <!-- QTY -->
                    <s:text name="order_line.qty"/>:
                </div>
                <div class="txt" v-if="!status.isSoleOut">
                    <div class="cut" v-on:click="subStock">
                        -
                    </div>
                    <div class="qty">
                        <input name="Qty" v-model="status.qty" v-on:blur="checkQty">
                    </div>
                    <div class=" add" v-on:click="addStock">
                        +
                    </div>
                    <%-- <div class="stock">
                        <b id="inventory_number">
                            {{_stock}}
                        </b>
                        <!-- in Stock -->
                        <s:text name="product.stock"/>
                    </div> --%>
                </div>
                <div class="txt" v-if="status.isSoleOut">
                    <div class="stock">
                        <span>Sole Out</span>
                    </div>
                </div>
                <a href="javascript:;" class="add_favorite fl" v-on:click="add_favorite"
                   style="line-height:2.1875rem; height:2.1875rem; font-size:1rem; color:#666; margin-left:6%;"
                >
                    <img :src="_favorite" style="vertical-align:middle;">
                    <%--{{result.favorite_count}}--%>
                </a>
            </div>
        </div>
        <div class="widget prod_info_actions clean">
            <div class="btn_add" style="width:100%;" id="addCart">
                <input type="button" value="<s:text name="mobile.add_to_cart" />"
                       class="btn_global add_btn addtocart AddtoCartBgColor"
                       v-on:click="addCart"
                >
            </div>
            <div class="blank15">
            </div>
            <a href="javascript:void(0)" target="_blank" class="btn_global add_btn BuyNowBgColor inquiry_btn"
               style="background:#333; border-color:#333;" v-on:click="inquiry">
                <!-- Inquiry/RFQ -->
                <s:text name="Add_inquiry"/>
            </a>
            <div class="clear">
            </div>
        </div>
    </div>
    <div class="detail_list">
    </div>
    <div class="prod_info_divide">
    </div>
    <div class="prod_info_detail ui_border_b">
        <section class="detail_desc"
                 :class="{detail_close:status.showIndex!=1}"
                 v-on:click="ChangeTab()"
        >
            <div class="t">
                <!-- Specifications -->
                <s:text name="prodDetail"/>
                <em>
                    <i>
                    </i>
                </em>
            </div>
            <div class="text ui_border_t" v-show="status.showIndex==1">
                <div class="specifics_text" v-for="(k,v) in result.specifications">
                    <strong>
                        {{v}}
                    </strong>
                    <span v-for="d in k">{{d}}</span>
                </div>
            </div>
        </section>
        <section class="detail_desc " :class="{detail_close:status.showIndex!=2}" v-on:click="ChangeTab()">
            <div class="t ui_border_t">
                <!-- Products Details -->
                <s:text name="mobile.pro_detail"/>

                <em>
                    <i>
                    </i>
                </em>
            </div>
            <div class="text editor_txt ui_border_t" v-show="status.showIndex==2" v-html="result.description">

            </div>
        </section>
        <%--<section class="detail_desc" :class="{detail_close:status.showIndex!=3}" v-on:click="ChangeTab(3)">--%>
        <%--<div class="t ui_border_t">--%>
        <%--Company Profile--%>
        <%--<em>--%>
        <%--<i>--%>
        <%--</i>--%>
        <%--</em>--%>
        <%--</div>--%>
        <%--<div class="text editor_txt ui_border_t" v-show="status.showIndex==3">--%>
        <%--&nbsp;&nbsp;--%>
        <%--<strong>--%>
        <%--Company basic information:--%>
        <%--</strong>--%>
        <%--公司基本信息--%>
        <%--{{result.tab1}}--%>
        <%--</div>--%>
        <%--</section>--%>
        <%--<section class="detail_desc" :class="{detail_close:status.showIndex!=4}" v-on:click="ChangeTab(4)">--%>
        <%--<div class="t ui_border_t">--%>
        <%--FAQ--%>
        <%--<em>--%>
        <%--<i>--%>
        <%--</i>--%>
        <%--</em>--%>
        <%--</div>--%>
        <%--<div class="text editor_txt ui_border_t" v-show="status.showIndex==4">--%>
        <%--{{result.tab2}}--%>
        <%--</div>--%>
        <%--</section>--%>
    </div>
</div>
<footer>
    <section class="font_col border_col copyright">
        Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1
        浙公网安备 33030402000493号
    </section>
</footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>
<div id="tips_cart">
    <p>
        <!--  Adding to cart succeed! -->
        <s:text name="addToCartTo"/>

    </p>
    <p>
                <span class="tips_cart_count">
                </span>
        <!-- items in cart. -->
        <s:text name="mobile.shopping_cart"/>
    </p>
    <p>
        <!-- Subtotal -->
        <s:text name="mobile.subtotal"/>:
        <span class="tips_cart_total">
                </span>
    </p>
    <div class="blank5">
    </div>
    <a href="/home/usr_UsrCart_cartshopping" class="btn_global btn_check">
        <!-- Proceed to Checkout -->
        <s:text name="cart.nextPay"/>
    </a>
    <a href="javascript:;" class="btn_global btn_return">
        <!-- Return to Shopping -->
        <s:text name="mobile.re_to_shop"/>
    </a>
</div>


<script type="text/javascript">

    function getParms(name, defaultValue) {
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


    var app = new Vue({
        el: '#goods_main',
        data() {
            return {
                status: {
                    size: null,
                    color: null,
                    stock: 0,
                    qty: 0,
                    itemCode: null,
                    showIndex: 1,
                    isSoleOut: false
                },
                linkToShop: '',
                "result": {
                    "pdtName": "aaaa",
                    "logo": "",
                    "pdtId": 0,
                    "itemCode": "0",
                    "pdtImg": "",
                    "price": -1,
                    "spec": {},
                    "description": "",
                    "min_oq": 0,
                    "max_oq": 0,
                    "specifications": {},
                    "breadcrumbNav": [],
                    "favorite_count": 0,
                    "currency_symbol": "",
                    "supName": "",
                    "authTime": "",
                    "supId": "",
                }
            }
        }, computed: {
            _price: function () {
                if (this.status.size == null || this.status.color == null)
                    return this.result.price;
                return this.status.price;
            }
            , _stock: function () {
                return this.status.stock;
            }, _itemCode: function () {
                if (this.status.itemCode == null)
                    return this.result.itemCode;
                return this.status.itemCode;
            }, _img: function () {
                var self = this;
                var result = [];
                for (var p = 0; p < this.result.pdtImg.length; p++) {
                    if (this.result.pdtImg[p])
                        result.push({
                            name: "item_" + self.result.pdtId,
                            src: this.result.pdtImg[p] + "?x-oss-process=image/resize,m_pad,h_500,w_500"
                        })
                }
                for (var key in this.result.spec) {
                    this.result.spec[key].forEach(function (k, v) {
                        if (k.imgList != null) {
                            k.imgList.forEach(function (dddd, s) {
                                if (dddd)
                                    result.push({
                                        name: "item_" + k.id,
                                        src: dddd + "?x-oss-process=image/resize,m_pad,h_500,w_500"
                                    })
                            })
                        }
                    })
                }
                return result;
            }, _favorite: function () {
                return this.result.favorite ? "/mobile/static/images/liked.png" : "/mobile/static/images/like.png";
            }
        },
        methods: {
            goLoginPage: function () {
                this.$message.error(lang_obj.goods_info.Pleaselogin);
                window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + window.location.href
            },
            ChangeTab: function () {
                if (this.status.showIndex == 1) {
                    this.status.showIndex = 2;
                } else if (this.status.showIndex == 2) {
                    this.status.showIndex = 1;
                }

            }, inquiry: function () {
                var self = this;
                self.post("/home/pdt_PdtConsultPdtList_add", {
                    product: self.result.pdtId
                }, function (res) {
                    if (res.status == 200) {
                        if (res.data.ret == 1) {
                            self.$message({
                                duration: 4000,
                                type: 'success',
                                showClose: true,
                                dangerouslyUseHTMLString: true,
                                message: lang_obj.global.inqadd + '<br><br><a class="btn-to-publish" href="/home/usr_UsrConsult_publishView?supplierId='+self.result.supId+'&product_id=' + res.data.result.id + '">' + lang_obj.global.inquery + '</a>'
                            })
                        } else if (res.data.ret == -1) {
                            self.goLoginPage();
                        }
                    }

                }, function (error) {
                    console.log(error)
                })
            },
            add_favorite: function () {
                var self = this
                self.post(
                    "/home/usr_UsrFavorites_addFavorite",
                    {pdtPkey: self.result.pdtId},
                    function (res) {
                        if (res.data.ret == -1) {
                            self.goLoginPage();
                        } else {
                            if (res.data.type != "1") {
                                self.$message({
                                    type: 'success',
                                    message: lang_obj.goods_info.Successfulinclusion
                                });
                            }
                            self.$set(self.result, "favorite_count", res.data.number)
                            self.$set(self.result, "favorite", !self.result.favorite)
                        }
                    },
                    function (error) {
                        console.log("error");
                        console.log(error)
                    }
                )
            }, addCart: function () {
                var self = this;
                if (this.status.size == null || this.status.color == null) {
                    this.$message(lang_obj.cart.checked_error)
                    return;
                }
                if (this.status.isSoleOut) {
                    this.$message.error(lang_obj.goods_info.product_out);
                    return;
                }
                if (self.status.qty == 0) {
                    this.$message(lang_obj.goods_info.Please_purchased)
                    return;
                }
                var postData = {}
                postData["specList[0].spec"] = self.status.size.id
                postData["specList[0].qty"] = self.status.qty
                self.post(
                    "/home/usr_UsrCart_boughtPro",
                    postData,
                    function (res) {
                        if (res.data.ret == -1) {
                            self.goLoginPage();
                        } else {
                            console.log(res.data)
                            self.$message({
                                type: 'success',
                                message: lang_obj.goods_info.Added_successfully
                            });
                        }
                    }, function (error) {
                        console.log("error");
                    }
                )
            },
            select: function (v) {
                var self = this;
                if (v.sizeFlag == null) {
                    if (!self.result.spec[v].colorFlag) {
                        this.$message.error(lang_obj.goods_info.product_out);
                        return;
                    }
                } else {
                    if (v.sizeFlag) {
                        this.$message.error(lang_obj.goods_info.product_out);
                        return;
                    }
                }

                if (v.size != null) {
                    if (this.status.size == v) {
                        this.status.size = null;
                    } else {
                        this.status.size = v;
                        self.status.price = this.status.size.price
                        self.status.stock = this.status.size.qty === -1 ? this.result.stock : this.status.size.qty;
                        self.status.itemCode = this.status.size.itemCode
                        self.status.isSoleOut = this.result.min_oq > this.status.stock && this.status.stock !== -1
                        self.$refs.image.setActiveItem("item_" + v.id);
                    }
                } else {
                    if (this.status.color == v) {
                        this.status.color = null;
                    } else {
                        this.status.color = v;
                    }
                }
            },
            checkQty:     // 购买数量更改时
                function () {
                    // 不是全为数量时，显示为最小值
                    if (!/^\d*$/.test(this.status.qty)) {
                        this.$set(this.status, "qty", this.result.min_oq)
                    }
                    //根据库存与购买量作对比.取最小值 作为基准值
                    var maxOq = -1
                    if (this.result.max_oq === 0) {
                        maxOq = this.status.stock;
                    } else {
                        maxOq = Math.min(this.result.max_oq, this.status.stock);
                    }
                    if (this.status.qty > maxOq) {
                        this.$set(this.status, "qty", maxOq)
                    }
                },
            addStock: function () {
                var maxOq = -1
                if (this.result.max_oq === 0) {
                    maxOq = this.status.stock;
                } else {
                    maxOq = Math.min(this.result.max_oq, this.status.stock);
                }
                if (this.status.qty < maxOq) {
                    this.status.qty++;
                }
                this.checkQty()
            }, subStock: function () {
                if (this.status.qty > 0)
                    this.status.qty--;
                this.checkQty()

            }
        }, beforeCreate: function () {
            this.post = function (url, data, then, error) {
                axios({
                    method: "POST",
                    url: url,
                    data: Qs.stringify(data),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(then).catch(error);
            }
            this.get = function (url, parm, then, error) {
                axios.get(url, {
                    params: parm
                }).then(then).catch(error)
            }
            var self = this;
            this.get("/home/pdt_PdtProduct_gtProductsInfoAjax", {id:${id}}, function (res) {
                if (res.data.ret == 1) {
                    self.$set(self, "result", res.data.result)
                    var specs = self.result.spec;
                    /*   if(res.data.result.stock==0){
                       self.result.stockFlag=true;
                       $("#addCart").html(" <input type='button' value="+lang_obj.goods_info.product_out+" class='btn_global add_btn addtocart AddtoCartBgColor'>");
                      } */
                    var sumNum = 0;
                    for (var i in specs) {
                        var colorFlag = false;
                        for (var j in specs[i]) {
                            var sizeFlag = false;
                            if (specs[i][j].qty != 0) {
                                colorFlag = true;
                            }
                            if (specs[i][j].qty == 0) {
                                sizeFlag = true;
                            }
                            sumNum += specs[i][j].qty;
                            self.result.spec[i][j].sizeFlag = sizeFlag;
                        }
                        self.result.spec[i].colorFlag = colorFlag;

                    }
                    if (sumNum == 0) {
                        $("#addCart").html(" <input type='button' style='background-color:#989898'  value=" + lang_obj.goods_info.product_out + " class='btn_global add_btn addtocart AddtoCartBgColor'>");
                    }
                    self.$set(self.status, "stock", res.data.result.stock)
                    self.$set(self, "linkToShop", "/home/usr_UsrSupplier_gtSupIndex?pkey=" + res.data.result.supId);
                }
            }, function (err) {
                console.log(err)
            })
        },
        //watch 这里我移除了,因为V-Model 双向绑定的问题...可能手机没问题,但是电脑模拟时会出现无法修改的问题,所以移到了  blur事件里..   @author lijie@shoestp.cn

    })
    /*  var swiper = new Swiper('.goods-info-swiper', {
       loop: true,
       pagination: {
         el: '.swiper-pagination',
         clickable: true,
       },
       navigation: {
         nextEl: '.swiper-button-next',
         prevEl: '.swiper-button-prev',
       },
       observer:true,//修改swiper自己或子元素时，自动初始化swiper
       observeParents:true,//修改swiper的父元素时，自动初始化swiper
     }); */
</script>
</body>
</html>
