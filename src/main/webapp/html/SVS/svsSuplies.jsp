<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-quiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <script src="./js/axios.min.js"></script>
    <script src="./js/vue.min.js"></script>
    <script src="./js/qs.js"></script>
    <link rel="stylesheet" href="css/reset.css">
    <%--<link rel="stylesheet" href="css/index.css">--%>
    <link rel="stylesheet" href="/html/SVS/css/index.css">
    <title>Online and Offline B2B Shoes Trading Platform—Shoestp.com Shoes Wholesale O2O New Mode</title>
    <jsp:include page="/home/v3/header.jsp"/>
</head>
<body>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
<div id="svsSuplies">
    <index-top></index-top>

    <!--页面左部分类导航-->
    <div class="w_1240">
        <!--分级导航 - 面包屑-->
        <div class="topNav">
            <div class="h1">
                <a href="/">Home</a><i class="el-icon-arrow-right"></i>
            </div>
            <div class="h1">
                <a href="javascript:;" class="now">Suppliers</a>
            </div>
        </div>

        <%--页面左部分类导航--%>
        <div id="left-nave-list" class="fl">
            <div class="leftNav">
                <h1>Related categories</h1>
                <div class="leftNav-box" v-for="(item,index) in classLists" for-key="index">
                    <p class="b" :class="[item.value==cated?'active':'']"
                       @click="categorySearch" :data-cated="item.value">
                        <img class="leftNav-icon1" src="/home/v3/static/images/ico/icon_nx.png" alt="{{item.label}}"
                             v-if="index==0"/>
                        <img class="leftNav-icon1" src="/home/v3/static/images/ico/icon_nvx.png" alt="{{item.label}}"
                             v-if="index==1"/>
                        <img class="leftNav-icon1" src="/home/v3/static/images/ico/icon_tx.png" alt="{{item.label}}"
                             v-if="index==2"/>
                        {{item.label}}
                    </p>
                    <div class="h2" v-for="(item2,indextwo) in item.children" for-key="indextwo">
                        <p :class="[item2.value==cated?'active':'']"><span @click="categorySearch" :data-cated="item2.value">{{item2.label}}</span><img
                                class="pl-icon2" :class="[item2.xiala==2?'':'pl-icon22']"
                                src="/home/v3/static/images/icon_down.png" alt="" @click="xiala" :data-index="index"
                                :data-indextwo="indextwo"/></p>
                        <el-collapse-transition>
                            <ul v-show="item2.xiala==2">
                                <li v-for="item3 in item2.children" :class="[item3.value==cated?'active':'']"
                                    @click="categorySearch" :data-cated="item3.value">
                                    <!-- <a :href="'home/pdt_PdtProduct?cated='+item3.value">{{item3.label}}</a> -->
                                    {{item3.label}}
                                </li>
                            </ul>
                        </el-collapse-transition>
                    </div>
                </div>
            </div>
        </div>
        <!--页面左部分类导航 end-->


        <div class="supplier-list-main fr">
            <%--顶部筛选--%>
            <div class="nav">
                <%--第一个筛选框 - 地区筛选--%>
                <div class="navitem" :class="{active: select1}"
                     @mouseenter="getCountryList">
                    <h3 @click="showcheck(1)">
                        Target Market
                        <img class="icon-down" src="images/svssupicondown.png" alt="">
                    </h3>
                    <div class="select" :class="select1 ?  'height' : ''" style="width: 990px;">
                        <el-input class="region-area-input" size="small"
                                  placeholder="Type to find a country or region"
                                  prefix-icon="el-icon-search"
                                  v-model.lazy.trim="filterAreaKeyword">
                        </el-input>
                        <%--<el-checkbox-group class="my-ele-checkbox-group" v-model="selectedMarketAreaList">--%>
                            <%--<template v-for="i in 3">--%>
                                <%--<el-checkbox--%>
                                        <%--v-for="item,index in marketAreaList"--%>
                                        <%--:key="item.value"--%>
                                        <%--:label="item.value">{{item.label}}--%>
                                <%--</el-checkbox>--%>
                            <%--</template>--%>
                        <%--</el-checkbox-group>--%>

                        <%--<div class="dividing-line"></div>--%>

                        <el-checkbox-group class="my-ele-checkbox-group"
                                           style="padding-bottom: 0;max-height: 400px;"
                                           v-model="selectedMarketCountryList">
                                <el-checkbox :class="{'double-length': util_function_obj.getByteLen(item.name)>=12}"
                                        v-for="item,index in marketCountryList"
                                        v-show="(!filterAreaKeyword) || (filterAreaKeyword && item.name.toLowerCase().indexOf(filterAreaKeyword.toLowerCase())!=-1 )"
                                        :key="item.id"
                                        :label="item.id">
                                    {{item.name}}
                                </el-checkbox>
                        </el-checkbox-group>
                    </div>
                </div>

                <%--第二个筛选框 - 材质筛选--%>
                <div class="navitem" :class="{active: select2}">
                    <h3 @click="showcheck(2)" style="min-width: 206px;">
                        {{selectedProcessTypeLabel?selectedProcessTypeLabel:"Shoe-making process"}}
                        <img class="icon-down" src="images/svssupicondown.png" alt="">
                    </h3>
                    <ul class="select shoes-make-wrap"  :class="select2 ?  'height' : ''"
                        style="min-width: 100%;">
                        <li class="shoes-make-item" :class="{active: processType.value == selectedProcessType}"
                            v-for="processType in processTypeList"
                            :data-type-value="processType.value"
                            :data-type-label="processType.label"
                            @click="selectProcessType">{{processType.label}}</li>
                    </ul>
                </div>
                <%--第三个筛选框 - 等级--%>
                <div class="navitem" :class="{active: select3}">
                    <h3 @click="showcheck(3)">
                        SVS Level<img class="icon-down" src="images/svssupicondown.png" alt="">
                    </h3>
                    <div class="select"  :class="select3 ?  'height' : ''"
                         style="padding-top: 0;padding-bottom: 0;">
                        <el-checkbox-group v-model="selectedLevelList"
                                           class="my-ele-checkbox-group checkbox-width-135">
                            <el-checkbox
                                    v-for="item,index in levelList"
                                    :key="item.value"
                                    :label="item.value">
                                <img :src="item.iconUrl" v-if="item.iconUrl">
                                {{item.label}}
                            </el-checkbox>
                        </el-checkbox-group>
                    </div>
                </div>
                <div class="grow"></div>

                <%--<el-button class="btn-search" style="margin-right: 20px;" size="small"--%>
                    <%--@click="search">search</el-button>--%>
            </div>

            <%--主体内容    --%>
            <div class="suplies" v-for="supplier,index in supplierList">
                <%--收藏行--%>
                <div class="likes">
                    <%--<img src="images/subliesiconsc.png" alt="" v-if="!supplier.isFavorite"  @click="cllection(index)">--%>
                    <%--<img src="images/subliesiconros.png" alt="" v-else  @click="cllection(index)">--%>
                    <%--<span @click="cllection(index)">Favorites </span>--%>
                </div>

                <div class="supliescon">
                    <%--左侧logo--%>
                    <div class="logo">
                        <img v-if="supplier.logo"
                            :src="util_function_obj.image(supplier.logo,160,130)" alt="">
                        <%--logo占位图--%>
                        <img v-else class="logo-placeholder"
                            src="/home/v3/static/images/supplier_no_img.png" alt="">
                        <div @click="toContactSupplier(supplier.id)">
                            <img src="images/subliesconxin.png" alt="">
                            <span>Contact Supplier</span>
                        </div>
                    </div>
                    <%--中间分数--%>
                    <div class="introduce">
                        <a class="company-name ellipsis_2" :href="'/home/usr_UsrSupplier_gtSupIndex?pkey=' + supplier.id" target="_blank">{{supplier.storeName}}</a>
                        <p class="main-product ellipsis_2"
                            v-if="supplier.categories && supplier.categories.length">
                            Main Products:
                            <span v-for="category in supplier.categories">
                                {{category.cateName}}
                            </span>
                        </p>
                        <div class="level">
                            <template v-if="supplier.svs && supplier.svs.status==1">
                                <img src="images/subliesiconcert.png" alt=""> Certificate | &nbsp;&nbsp;
                                <template v-if="supplier.svs && supplier.svs.grade">
                                    <img src="/home/static/images/ico/icon-svs-yp.png" alt="" v-if="supplier.svs.grade  == 1">
                                    <img src="/home/static/images/ico/icon-svs-jp.png" alt="" v-else-if="supplier.svs.grade  == 2">
                                    <img src="/home/static/images/ico/icon-svs-zs.png" alt="" v-else-if="supplier.svs.grade  == 3">
                                    SVS
                                </template>
                            </template>
                        </div>

                        <div class="score">
                            <span class="title">R&D: </span>
                            <span :title="(supplier.svs && supplier.svs.researchBase)?supplier.svs.researchBase:0">
                                <template v-if="supplier.svs && supplier.svs.researchBaseStar">
                                    <template v-for="i in 5">
                                        <img src="images/subliesiconros.png"
                                             v-if="i <= supplier.svs.researchBaseStar"
                                             class="star" alt="">
                                        <img src="images/subliesiconhalf.png"
                                             v-else-if="supplier.svs.researchBaseStar>(i-1) && supplier.svs.researchBaseStar<i"
                                             class="star" alt="">
                                        <img src="images/subliesiconsc.png" v-else
                                             class="star" alt="">
                                    </template>
                                </template>
                                <%--没有的话显示5个空星--%>
                                <template v-else>
                                    <img src="images/subliesiconsc.png" v-for="i in 5"
                                         class="star" alt="">
                                </template>
                            </span>
                        </div>
                        <div class="score">
                            <span class="title"> Output: </span>
                            <span :title="(supplier.svs && supplier.svs.capacityBase)?supplier.svs.capacityBase:0">
                                <template v-if="supplier.svs && supplier.svs.capacityBaseStar">
                                    <template v-for="i in 5">
                                        <img src="images/subliesiconros.png"
                                             v-if="i <= supplier.svs.capacityBaseStar"
                                             class="star" alt="">
                                        <img src="images/subliesiconhalf.png"
                                             v-else-if="supplier.svs.capacityBaseStar>(i-1) && supplier.svs.capacityBaseStar<i"
                                             class="star" alt="">
                                        <img src="images/subliesiconsc.png" v-else
                                             class="star" alt="">
                                    </template>
                                </template>
                                <%--没有的话显示5个空星--%>
                                <template v-else>
                                    <img src="images/subliesiconsc.png" v-for="i in 5"
                                         class="star" alt="">
                                </template>
                            </span>
                            <%--<template v-if="Math.floor(supplier.svs.capacityBaseStar) < 1">--%>
                                <%--<img src="images/subliesiconsc.png" class="star" alt="">--%>
                            <%--</template>--%>
                            <%--<template v-else>--%>
                                <%--<img src="images/subliesiconros.png" class="star" alt=""--%>
                                     <%--v-for="a in (Math.floor(supplier.svs.capacityBaseStar))">--%>
                                <%--<img src="images/subliesiconhalf.png" class="star" alt=""--%>
                                     <%--v-if="supplier.svs.capacityBaseStar%1">--%>
                            <%--</template>--%>
                        </div>
                        <div class="score">
                            <span class="title">Scale: </span>
                            <span :title="(supplier.svs && supplier.svs.factoryBase)?supplier.svs.factoryBase:0">
                                <template v-if="supplier.svs && supplier.svs.factoryBaseStar">
                                    <template v-for="i in 5">
                                        <img src="images/subliesiconros.png"
                                             v-if="i <= supplier.svs.factoryBaseStar"
                                             class="star" alt="">
                                        <img src="images/subliesiconhalf.png"
                                             v-else-if="supplier.svs.factoryBaseStar>(i-1) && supplier.svs.factoryBaseStar<i"
                                             class="star" alt="">
                                        <img src="images/subliesiconsc.png" v-else
                                             class="star" alt="">
                                    </template>
                                </template>
                                <%--没有的话显示5个空星--%>
                                <template v-else>
                                    <img src="images/subliesiconsc.png" v-for="i in 5"
                                         class="star" alt="">
                                </template>
                            </span>
                        </div>
                        <p class="ellipsis_2" v-if="supplier.address">{{supplier.address}}</p>
                    </div>
                    <%--右侧商品--%>
                    <div class="product">
                        <div class="product-item" v-for="(pro, pdtIndex) in supplier.products" v-if="pdtIndex<3">
                            <a :href="'/' + pro.link" target="_blank">
                                <img :src="util_function_obj.image(pro.pdtPictures.split(',')[0],144)" alt="">
                                <p class="goods-name ellipsis_2">{{pro.pdtName}}</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="pageBreak" v-if="totalCount > 0">
                <el-pagination
                        :current-page.sync="currentPage"
                        :page-size="limit"
                        layout="prev, pager, next"
                        prev-text="< Previous"
                        next-text="Next >"
                        :total="totalCount"
                        @current-change="changePage">
                </el-pagination>
            </div>
        </div>

        <div style="clear: both;"></div>

        <%--模态框 - 筛选下拉 点击隐藏--%>
        <div class="modal" @click="hiddenDropDown" v-show="select1 || select2 || select3"></div>

        <%--<div class="supplier_footer">--%>
            <%--<h2 class="supplier_footer_h2">Haven’t Find what you want?</h2>--%>
            <%--<div class="supplier_footer_content">--%>
                <%--<div class="content_item">--%>
                    <%--<h2 class="content_item_h2">RFQ</h2>--%>
                    <%--<p class="content_item_text">Post exact requests to find exact Chinese Suppliers </p>--%>
                    <%--<a @click="toRFQ" class="content_item_button btn_colour_1">Post Your Request</a>--%>
                <%--</div>--%>
                <%--<div class="content_item content_item_right">--%>
                    <%--<h2 class="content_item_h2">Supplier Search</h2>--%>
                    <%--<p class="content_item_text">Find More Supplier</p>--%>
                    <%--<a href="javascript:void(0)" class="content_item_button btn_colour_2" @click="noOpen">Search now</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
    <index-bottom></index-bottom>
</div>
<%--<script src="components/index-top.js"></script>--%>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:'#svsSuplies',

        data:{
            // 筛选框，下拉内容是否显示
            select1:false,
            select2:false,
            select3:false,
            filterAreaKeyword:"", //筛选1中的 地区筛选输入内容
            processList:[],
            // 选中的区域
            selectedMarketAreaList:[],
            // 筛选区域列表
            marketAreaList:[{
                value: 1, label: "浙江"
            },{
                value: 2, label: "福建"
            },{
                value: 3, label: "江西"
            },{
                value: 4, label: "四川"
            },{
                value: 5, label: "青海"
            },{
                value: 6, label: "重庆"
            },{
                value: 7, label: "江苏"
            },{
                value: 8, label: "河北"
            },],
            // 选中的国家
            selectedMarketCountryList:[],
            // 筛选的国家列表
            marketCountryList:[],
            // 选中的制鞋工艺-value值
            selectedProcessType: null,
            // 选中的制鞋工艺-名称
            selectedProcessTypeLabel: null,
            // 制鞋工艺
            processTypeList:[
                {label: "All",value: null},
                {label: "Vulcanization Shoes",value: 41},
                {label: "Injection Shoes",value: 42},
                {label: "Molded Shoes",value: 40},
                {label: "Sewing Shoes",value: 43},
                {label: "Adhesive Shoes",value: 37},
            ],

            // 选中的等级
            selectedLevelList:[],
            // 筛选的等级列表
            levelList:[{
                value: "3",
                label: "Diamond",
                iconUrl: "/home/v3/static/images/supplier-level3.png"
            },{
                value: "2",
                label: "Gold",
                iconUrl: "/home/v3/static/images/supplier-level2.png"
            },{
                value: "1",
                label: "Silver",
                iconUrl: "/home/v3/static/images/supplier-level1.png"
            },],

            classLists: [], //左侧分类列表
            lose: 1,
            cated: null,
            currentPage: 1,
            page:1, //当前页数
            limit: 6, //每页页数
            totalCount: 100, // 总条数

            supplierList:[], //显示的供应商列表信息
        },
        methods:{
            // 获取左边分类
            getClassList(e) {
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax', {
                    params: {
                        page: 1,
                        limit: 5
                    }
                })
                    .then((res) => {
                    if(res.data.ret != 1){
                    this.$message.error(res.data.msg || "Get category list error, please try again later");
                    return
                }

                    this.classLists = res.data.result
                    for (var i in this.classLists) {
                    var children = this.classLists[i].children;
                    for (var j in children) {
                        this.$set(this.classLists[i].children[j], "xiala", 2)
                    }
                }
            })
            .catch((error) => {
                    this.$message.error(error || 'Network error,please try again later');
                });
            },

            // 左边列表下拉功能
            xiala(e) {
                var i = e.currentTarget.dataset.index;
                var j = e.currentTarget.dataset.indextwo;
                if (this.classLists[i].children[j].xiala == 2) {
                    this.$set(this.classLists[i].children[j], "xiala", 1)
                } else {
                    this.$set(this.classLists[i].children[j], "xiala", 2)
                }
            },

            // 点击左侧分类时search
            categorySearch(e) {
                this.cated = e.currentTarget.dataset.cated;
                this.search();
            },

            // 顶部筛选，点击下拉，并隐藏其余几个
            showcheck:function (id) {
                for (let i=1;i<4;i++){
                    if (i == id) {
                        this.$set(this,'select'+i,!this['select'+i])
                    }else {
                        this.$set(this,'select'+i,false)
                    }
                }
            },

            // 点击modal隐藏筛选下拉框
            hiddenDropDown(){
                this.select1 = false;
                this.select2 = false;
                this.select3 = false;
            },

            // 获取筛选内容-国家列表
            getCountryList() {
                // 第一次移入时发请求
                if(this.marketCountryList.length > 0) return;

                axios.get('/home/plt_PltCountry_list')
                    .then((res) => {
                    if (res.data.ret != 1) {
                        this.$message.error(res.data.msg || "Get country list error,please try again later");
                        return
                    }
                    ;
                    this.marketCountryList = res.data.result;
                })
                .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                });
            },

            //选择制鞋工艺
            selectProcessType(e){
                this.selectedProcessType = e.currentTarget.dataset.typeValue;
                this.selectedProcessTypeLabel = e.currentTarget.dataset.typeLabel;
                this.hiddenDropDown();
                this.search();
            },

            // 改变分页
            changePage(page) {
                this.page = page;
                this.getSupplierList();
            },

            // 收藏事件
            cllection:function (index) {
                // this.$set(this.supplierList[index],'isFavorite',!this.supplierList[index].isFavorite)
            },

            //获取供货商列表
            getSupplierList:function () {
                console.log("getList")
                // 筛选地区+国家 - 暂时没有,直传了国家列表 - 之后改回来时用这个值就好
                // let  selectedTargetMarketArr = this.marketAreaList.push(...this.selectedMarketCountryList);
                axios.get('/home/usr_UsrSupplier_supplierList',{
                    params:{
                        start: (this.page -1) * this.limit,
                        limit: this.limit,
                        // storeName: "", //店铺名称
                        targetMarket: this.selectedMarketCountryList.length>0 ? this.selectedMarketCountryList.join(",") : null, //暂时写国家 - 未含地区
                        processType: this.selectedProcessType, //工艺类型 （注塑鞋，硫化鞋）
                        grade: this.selectedLevelList.length>0 ? this.selectedLevelList.join(",") : null,  //SVS等级 1为银，2为金，3为钻石，0为暂无等级
                        pdtCategory: this.cated>0 ? this.cated : null, //产品分类 - 左侧列表选中项
                        checkType: 1, //检索类型 1为查询已认证SVS供应商列表,0为查询所有 - 此处固定为1
                    }
                }).then( (res) => {
                    if(res.data.ret != 1){
                        this.$message.error(res.data.msg || "Get supplier list error, please try again later");
                        return
                    }

                    // 分页用
                    this.totalCount = res.data.result.totalCount
                    this.supplierList = res.data.result.items;
                })
            },

            // 重置搜索条件 - 从第一页开始
            resetSearchParams(){
                this.page = 1;
                this.currentPage = 1;
            },
            // 点击搜索
            search(){
                // 隐藏下拉框
                // this.hiddenDropDown();
                this.resetSearchParams();
                // util_function_obj._throttle(this.getSupplierList)()
                this.getSupplierList();
            },

            // 跳转RFQ
            toRFQ(){
                let url = "/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1"+ "&backUrl=" + window.location.href
                util_function_obj.supplierCantEnter(this, url);
            },
            // 跳转供应商表单
            toContactSupplier(supplierPkey){
                let url = "/home/usr_UsrSupplier_goContactSupplier?supplierPkey=" + supplierPkey+ "&backUrl=" + window.location.href;
                util_function_obj.supplierCantEnter(this, url);
            },
            // 点击 搜索供应商 (未开放先提示)
            noOpen(){
                this.$alert("Sorry, this feature is not open yet.", {
                    confirmButtonText: 'OK',
                    customClass: "my-custom-element-alert-class fs-content-18",
                });
            }
        },
        mounted(){
            // this.cated = getParams('cated', "NONE") //分类点击用的
            this.getClassList();
            this.getSupplierList();
        },
        watch:{
            selectedLevelList: function (val) {
                this.search();
            },
            selectedMarketCountryList: function (val) {
                this.search();
            },
        }
    })
</script>
</body>
</html>
