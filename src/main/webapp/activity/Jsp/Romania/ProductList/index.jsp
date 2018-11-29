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
    <div style="background: #090816;text-align: center;">
        <img class="maxW minW" src="/activity/Jsp/Romania/ProductList/images/top-bg.jpg" alt="">
    </div>
    <div style="background: #ffffff;text-align: center;">
        <!-- 内容容器 -->
        <div class="container-box maxW minW">
            <!-- 左边分类选择 -->
            <div class="classify-list">
                <div style="line-height:70px;text-align: left;">Rufine by</div>
                <el-tree :data="categoryListComputed" :props="defaultProps" @node-click="handleNodeClick"
                         highlight-current="true"></el-tree>
            </div>
            <!-- 右边列表 -->
            <div class="goods-list">
                <div class="goods-box">
                    <template v-for="(item,index) in goodsList.items">
                        <div class="goods-item">
                            <a :href="'/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+item.id" target="_blank">
                                <img :src="item.image" :alt="item.name" style="width:270px;height:270px">
                                <div class="goods-name">{{item.name}}</div>
                            </a>
                            <div class="flex" style="justify-content: space-between;">
                                <div class="inquiry-btn" @click="inquiry(item.productId)">INQUIRY</div>
                                <div style="cursor: pointer;">
                                    <img :src="item.ismyfavorite?'/activity/Jsp/Romania/ProductList/images/icon-like-on.png':'/activity/Jsp/Romania/ProductList/images/icon-like-off.png'"
                                         alt="" style="width:25px;height:21px;margin-right:8px;"
                                         :data-id="item.id" @click="addCollection(item.productId,index)">
                                    <img src="./images/iocn-search.png" alt="" style="width:22px;height:22px;">
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
</div>
<style>
    #web_top .top_mem {
        line-height: 25px;
    }
</style>
<script>
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
        },
        methods: {
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
            this.getGoodsList(this.page, this.limit, this.cated);
            this.getClassifyList(1, 5);
        },
        computed: {
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
                return categoryListComputed
            },
        }
    })
</script>
</body>

</html>
