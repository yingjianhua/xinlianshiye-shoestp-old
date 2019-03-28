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
    <!-- 引入样式 -->
    <%--<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">--%>
    <!-- 引入组件库 -->
    <%--<script src="https://unpkg.com/element-ui/lib/index.js"></script>--%>
    <title></title>
    <script src="components/O2O-bottom.js"></script>
    <%--左侧分类列表--%>
</head>
<jsp:include page="/home/v3/header.jsp"/>
<body>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
<div id="svsSuplies">
    <index-top></index-top>

    <!--页面左部分类导航-->
    <div class="w_1240">
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
                <div class="navitem" :class="{active: select1}">
                    <h3 @click="showcheck(1)">Target Market<img src="images/svssupicondown.png" alt=""></h3>
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
{{selectedMarketCountryList}}---test
                        <el-checkbox-group class="my-ele-checkbox-group"
                                           style="padding-bottom: 0;max-height: 400px;"
                                           v-model="selectedMarketCountryList">
                            <template v-for="i in 5">
                                <el-checkbox
                                        v-for="item,index in marketCountryList"
                                        v-show="(!filterAreaKeyword) || (filterAreaKeyword && item.name.toLowerCase().indexOf(filterAreaKeyword.toLowerCase())!=-1 )"
                                        :key="item.id"
                                        :label="item.id">
                                    {{item.name}}
                                </el-checkbox>
                            </template>
                        </el-checkbox-group>
                    </div>
                </div>

                <%--第二个筛选框 - 材质筛选--%>
                <div class="navitem" :class="{active: select2}">
                    <h3 @click="showcheck(2)">Shoe-making process<img src="images/svssupicondown.png" alt=""></h3>
                    <ul class="select shoes-make-wrap"  :class="select2 ?  'height' : ''"
                        style="width: 100%;">
                        <li class="shoes-make-item">Vulcanization Shoes</li>
                        <li class="shoes-make-item">Injection Shoes</li>
                        <li class="shoes-make-item">Molded shoes</li>
                        <li class="shoes-make-item">Sewing shoes</li>
                        <li class="shoes-make-item">Adhesive shoes</li>
                    </ul>
                </div>
                <%--第三个筛选框 - 等级--%>
                <div class="navitem" :class="{active: select3}">
                    <h3 @click="showcheck(3)">SVS Level<img src="images/svssupicondown.png" alt=""></h3>
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
                <el-button style="margin-right: 20px;" size="small">search</el-button>
            </div>

            <%--主体内容    --%>
            <div class="suplies" v-for="item,index in supplierList">
                <div class="likes">
                    <img src="images/subliesiconsc.png" alt="" v-if="!item.isFavorite"  @click="cllection(index)">
                    <img src="images/subliesiconros.png" alt="" v-else  @click="cllection(index)">
                    <span @click="cllection(index)">Favorites </span>
                </div>
                <div class="supliescon">
                    <div class="logo">
                        <img :src="util_function_obj.image(item.logo,160,130)" alt="">
                        <div @click="ToContactSupplier(item.id)">
                            <img src="images/subliesconxin.png" alt="">
                            <span>Contact Supplier</span>
                        </div>
                    </div>
                    <div class="introduce" style="width:320px">
                        <a class="title ellipsis_2" :href="'/home/usr_UsrSupplier_gtSupIndex?pkey=' + item.id" target="_blank">{{item.showName}}</a>
                        <p class="p ellipsis_2">{{item.showName}}</p>
                        <div class="level">
                            <p><img src="images/subliesiconcert.png" alt=""> Certificate | &nbsp;&nbsp;<img src="images/subliesiconsvs1.png" alt="" v-if="item.isAuth  == 1"><img src="images/subliesiconsvs2.png" alt="" v-if="item.isAuth  == 2"><img src="images/subliesiconsvs3.png" alt="" v-if="item.isAuth  == 3"> SVS</p>
                        </div>
                        <p>
                            <span>R&D: </span><img src="images/subliesiconros.png" alt="" v-for="a in (Math.floor(item.rd))"><img src="images/subliesiconhalf.png" alt="" v-if="item.rd%1">
                        </p>
                        <p>
                            <span> Output: </span><img src="images/subliesiconros.png" alt="" v-for="a in (Math.floor(item.output))"><img src="images/subliesiconhalf.png" alt="" v-if="item.output%1">
                        </p>
                        <p>
                            <span>Scale: </span><img src="images/subliesiconros.png" alt="" v-for="a in (Math.floor(item.scale))"><img src="images/subliesiconhalf.png" alt="" v-if="item.scale%1">
                        </p>
                        <p class="ellipsis_2">{{item.mainSalesArea}}</p>
                    </div>
                    <div class="product">
                        <div v-for="(pro, pdtIndex) in item.products" v-if="pdtIndex<3">
                            <a :href="'/home/pdt_PdtProduct_gtProductsInfo?id=' + pro.id" target="_blank">
                                <img :src="util_function_obj.image(pro.picture.split(',')[0],144)" alt="">
                                <p>{{pro.name}}</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pageBreak">
                <el-pagination
                        :page-size="pagerSize"
                        :pager-count="pagerCount"
                        layout="prev, pager, next"
                        prev-text="< Previous"
                        next-text="Next >"
                        :total="pagerTotal"
                        @current-change="changeCurrent">
                </el-pagination>
            </div>
        </div>

        <div style="clear: both;"></div>

        <div class="supplier_footer">
            <h2 class="supplier_footer_h2">Haven’t Find what you want?</h2>
            <div class="supplier_footer_content">
                <div class="content_item">
                    <h2 class="content_item_h2">RFQ</h2>
                    <p class="content_item_text">Post exact requests to find exact Chinese Suppliers </p>
                    <a @click="ToRFQ" class="content_item_button btn_colour_1">Post Your Request</a>
                </div>
                <div class="content_item content_item_right">
                    <h2 class="content_item_h2">Supplier Search</h2>
                    <p class="content_item_text">Find More Supplier</p>
                    <a href="javascript:void(0)" class="content_item_button btn_colour_2" @click="noOpen">Search now</a>
                </div>
            </div>
        </div>
    </div>
    <index-bottom></index-bottom>
    <%--<o2o-bottom></o2o-bottom>--%>
</div>
<%--<script src="components/index-top.js"></script>--%>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:'#svsSuplies',

        data:{
            baseurl:'',
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

            classLists: [], //左侧分类列表
            lose: 1,
            cated: -1,
            curr: 1,

            // 筛选框，下拉内容是否显示
            select1:false,
            select2:false,
            select3:false,

            page:1,
            pagerSize: 6, // 页面显示条数
            pagerCount: 6, //
            pagerTotal: 100, // 总条数

            supplierList:[], //显示的供应商列表信息
        },
        methods:{
            classList(e) { // 获取左边分类
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax', {
                    params: {
                        page: 1,
                        limit: 5
                    }
                })
                    .then((res) => {
                    this.classLists = res.data.result
                    for (var i in this.classLists) {
                    var children = this.classLists[i].children;
                    for (var j in children) {
                        this.$set(this.classLists[i].children[j], "xiala", 2)
                    }
                }
            })
            .catch((error) => {
                    console.log("err")
                });
            },
            // 获取筛选内容-国家列表
            getCountryList() {
                axios.get('/home/plt_PltCountry_list',)
                    .then((res) => {
                    console.log("获取国家列表 suc")
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

            // 下拉筛选1 - area
            getMarketAreaList() {
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax', {
                    params: {
                        page: 1,
                        limit: 5
                    }
                })
                    .then((res) => {
                    this.classLists = res.data.result
                    for (var i in this.classLists) {
                    var children = this.classLists[i].children;
                    for (var j in children) {
                        this.$set(this.classLists[i].children[j], "xiala", 2)
                    }
                }
            })
            .catch((error) => {
                    console.log("err")
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
            // 点击左侧分类时跳转
            categorySearch(e) {
                this.lose = 1
                this.cated = e.currentTarget.dataset.cated;
                console.log(this.cated)
                this.page = 0;
                this.curr = 1
                this.getSupplierList();
            },
            // 跳转RFQ
            ToRFQ(){
                let url = "/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1"+ "&backUrl=" + window.location.href
                util_function_obj.supplierCantEnter(this, url);
            },
            // 跳转供应商表单
            ToContactSupplier(supplierPkey){
                let url = "/home/usr_UsrSupplier_goContactSupplier?supplierPkey=" + supplierPkey+ "&backUrl=" + window.location.href;
                util_function_obj.supplierCantEnter(this, url);
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
            changeCurrent(res) {
                console.log(res)
                this.page = res;
                this.getSupplierList();
            },
            cllection:function (index) {
                // this.$set(this.supplierList[index],'isFavorite',!this.supplierList[index].isFavorite)
            },
            //获取供货商列表
            getSupplierList:function () {
                let self = this;
                axios.get('/home/usr_UsrSupplier_listSuppliers',{
                    params:{
                        start: (this.page -1) * self.pagerCount,
                        limit: self.pagerCount,
                        storeName: "",
                        targetMarket: "",
                        processType: "",
                        grade: "",
                        pdtCategory: "", //产品分类
                        checkType: 1, //检索类型 1为查询已认证SVS供应商列表,0为查询所有
                    }
                }).then(function (res) {
                    if(res.data.ret == 1){
                        let data = res.data.result;
                        console.log(data)
                        self.pagerTotal = data.totalCount
                        self.$set(self,'supplierList',data.items)
                        console.log(self.supplierList)
                    }
                })
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
            this.cated = getParams('cated', 0) //分类点击用的
            this.getCountryList();
            this.getSupplierList();
            this.classList();
        }
    })
</script>
</body>
</html>
