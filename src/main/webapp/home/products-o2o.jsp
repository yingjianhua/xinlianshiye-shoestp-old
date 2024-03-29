<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="v3/header.jsp"/>
<link rel="stylesheet" href="/home/v3/static/css/productList.css">
<style>
    #o2otop {
        position: inherit;
    }

    #o2otop .o2otopcon a:nth-of-type(4) .smallspan {
        color: white;
        background: linear-gradient(to right, rgb(113, 139, 223), rgb(159, 87, 254));
        display: inline-block;
        width: 207px;
        height: 54px;
        line-height: 54px;
        font-size: 22px;
        border-radius: 27px;
        cursor: pointer;
        text-align: center;
    }
    #o2obottom .o2obottomcopyright{
        border-top: 1px solid #575f75 !important;
    }
    #o2obottom .o2obottomlinks ul .ullink a{
        color: white !important;
    }
    #o2obottom {
        background-color: #424d65 !important;
    }
</style>
<body>
<jsp:include page="v3/nav.jsp"></jsp:include>
<div id="new_navs">
    <o2o-top></o2o-top>
</div>

<script src="/home/v3/static/js/index-top.js">
</script>
<script src="/html/o2o/js/config.js"></script>
<link rel="stylesheet" href="/html/o2o/css/index.css">
<script src="/home/components/O2O-top.js"></script>
<!-- <script src="/home/components/O2O-bottom.js"></script> -->
<script src="/home/v3/static/js/index-bottom.js"></script>

<script>     new Vue({
    el: "#new_navs"
})</script>
<div id="productList" class="clearfix w1240">
    <!--分级导航-->
    <div class="topNav">
        <div class="h1"><a href="/">Home</a><i class="el-icon-arrow-right"></i></div>
        <div class="h1"><a :class="{'now':(!breadcrumbnav || breadcrumbnav.length<=0)}" href="/home/pdt_PdtProduct_o2oList"> All product</a><i v-if="breadcrumbnav && breadcrumbnav.length>0" class="el-icon-arrow-right"></i></div>
        <blcok v-if="breadcrumbnav && breadcrumbnav.length>0">
            <div class="h1" v-for="(item,index) in breadcrumbnav" for-key="index">
                <a @click="categorySearch" :data-cated="item.pkey" href="javascript:;"
                   :class="breadcrumbnav.length-1==index?'now':''">{{item.name}}</a>
                <i class="el-icon-arrow-right" v-show="breadcrumbnav.length-1!=index"></i>
            </div>
        </blcok>
    </div>
    <!--分级导航 end-->

    <!--页面左部分类导航-->
    <div class="leftNav fl">
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
                           v-if="item2&&item2.children"
                        src="/home/v3/static/images/icon_up.png" alt="" @click="xiala" :data-index="index"
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
    <!--页面左部分类导航 end-->


    <!--页面右部列表-->
    <div class="rightList fr">
        <div class="top">
                <div class="top-box">
                        <p>Export Market<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt=""/></p>
                        <div class="i1"></div>
                        <ul style="width:990px;height:auto;min-height: 215px;">
                                <el-input class="region-area-input" size="small"
                                          placeholder="Type to find a country or region"
                                          prefix-icon="el-icon-search"
                                          v-model.lazy.trim="filterAreaKeyword">
                                </el-input>
                                <el-checkbox-group class="my-ele-checkbox-group"
                                                   style="padding-bottom: 0;max-height: 400px;"
                                                   v-model="selectedMarketCountryList">
                                        <el-checkbox :class="{'double-length': util_function_obj.getByteLen(item.name)>=12}"
                                                v-for="item,index in countryList"
                                                v-show="(!filterAreaKeyword) || (filterAreaKeyword && item.name.toLowerCase().indexOf(filterAreaKeyword.toLowerCase())!=-1 )"
                                                :key="item.id"
                                                :label="item.id"
                                                @change="changeCountry"
                                                >
                                            {{item.name}}
                                        </el-checkbox>
                                </el-checkbox-group>
                        </ul>
                    </div>
                    <div class="top-box">
                        <p>SVS Level<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt=""/></p>
                        <div class="i1"></div>
                        <ul style="height:auto;">
                            <el-checkbox-group v-model="grade" class="my-ele-checkbox-group1">
                                <el-checkbox label="3" name="3" @change="changeSVSLevel"> <img src="/home/v3/static/images/supplier-level3.png" alt="" style="margin-right:8px;">Diamond</el-checkbox>
                                <el-checkbox label="2" name="2" @change="changeSVSLevel"><img src="/home/v3/static/images/supplier-level2.png" alt="" style="margin-right:8px;">Gold</el-checkbox>
                                <el-checkbox label="1" name="1" @change="changeSVSLevel"><img src="/home/v3/static/images/supplier-level1.png" alt="" style="margin-right:8px;">Silver</el-checkbox>
                            </el-checkbox-group>
                        </ul>
                    </div>
                    <div class="top-box">
                        <p style="min-width: 120px;text-align: center;white-space: nowrap;">
                        {{(sort || sort===0)? priceSortList[sort].name: "Price"}}
                        <img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt=""/></p>
                        <div class="i1"></div>
                        <ul style="padding:10px 0;width:168px;height:auto;text-align: center;">
                            <div class="price-sort" :class="sort == item.rule?'price-sort-active' :''" v-for="(item, index) in priceSortList" :key="index" @click="priceBtn(item.rule)">{{item.name}}</div>
                        </ul>
                    </div>

            <%-- <div class="top-box">
                <p>Export Countries<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt="" /></p><div class="i1"></div>
                <ul>
                    <li v-for="(item,index) in 16" :key="index" :data-selecount="index+1" @click="seleCount">
                        <div class="s" :class="[selecount==index+1?'sele':'']"></div>Safety Shoes{{index}}
                    </li>
                </ul>
            </div>

            <div class="top-box">
                <p>Offline store<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt="" /></p><div class="i1"></div>
                <ul>
                    <li v-for="(item,index) in 16" :key="index" :data-selestore="index+1" @click="seleStore">
                        <div class="s" :class="[selestore==index+1?'sele':'']"></div>Safety Shoes{{index}}
                    </li>
                </ul>
            </div> --%>

            <div class="top-box2" style="margin-left: 20px;">Min Order :
                <input class="w63" type="text"
                       @keyup.enter="search"
                       v-model.trim="lessthan"
                       placeholder="less than" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>
            </div>
            <div class="i0"></div>
            <div class="top-box2">Price :
                <input class="w63" type="text"
                       @keyup.enter="search"
                       v-model.trim.number="min" placeholder="min."
                       onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/> -
                <input class="w63" type="text"
                       @keyup.enter="search"
                       v-model.trim.number="max" placeholder="max."
                       onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>
            </div>

            <div class="btn-search" @click="search">search</div>
        </div>


        <div class="common">
            <!--产品-->
            <div class="common-box ripple slideInRight" v-for="(item,index) in productLists" :key="index"
                 :style="{'animation-delay': index/10 + 's'}">
                <div class="common-boxslide">
                    <div class="likebai" @click="shoucang" :data-num="index" :data-id="item.pdtId">
                        <img :src="item.eshrine ?'/home/v3/static/images/ico/likesele.png' : '/home/v3/static/images/ico/likebai.png'"/>
                    </div>
                    <div class="block">
                        <el-carousel :arrow="item.picture.split(',').length > 1 ? 'hover':'never'" height="197px"
                                     indicator-position="none" :autoplay="false">
                            <el-carousel-item v-for="item2 in item.picture.split(',')" :key="item">
                                <div class="h3" @mouseenter="bigPicBoxopen" @mouseleave="bigPicBoxclose"
                                     :data-pic="item2">
                                    <a :href="'/'+item.rewrite" target="_blank"><img class="fl goods-img"
                                                                                     :src="util_function_obj.image(item2,195)"/></a>
                                </div>
                            </el-carousel-item>
                        </el-carousel>
                    </div>
                </div>
                <div class="common-boxtitle">
                        <a :href="'/'+item.rewrite" target="_blank" style="width:100%;">
                            <h1>
                                <div class="ootit" v-show="item.pdtType"><img class="mtf4"
                                                                              src="/home/v3/static/images/ico/icon_o2o.png"
                                                                              alt="O2O"/>O2O
                                </div>
                                {{item.pdtName}}
                            </h1>
                            <div class="clearfix" style="position:relative;">
                                <div class="fl">
                                    <h2><span>{{sysConfig.currency_symbol}}{{ item.price }}</span></h2>
                                    <div class="h3">Min.Order: {{item.minOrder}} pairs</div>
                                </div>
                                <div class="fr" style="width: 196px;">
                                    <div class="">
                                        <div class="h3">Inner Material:</div>
                                        {{item.inner?item.inner:'No data'}}
                                    </div>
                                    <div class="">
                                        <div class="h3">Sole Material:</div>
                                        {{item.sole?item.sole:'No data'}}
                                    </div>
                                    <div class="">
                                        <div class="h3">Upper Material:</div>
                                        {{item.upper?item.upper:'No data'}}
                                    </div>
                                    <div class="">
                                        <div class="h3">Appropriate Season:</div>
                                        {{item.season?item.season:'No data'}}
                                    </div>
                                    <div class="">
                                        <div class="h3">Closed Way:</div>
                                        {{item.closed?item.closed:'No data'}}
                                    </div>
                                </div>
                                </div>
                            </a>
                            <div class="product-inquiry-btn" @click.stop="ToProductInquiry(item.pdtId)">Product Inquiry</div>
                    </div>
                <div class="common-boxspan fr">
                    <a class="h1" :href="'/home/usr_UsrSupplier_gtSupIndex?pkey='+item.supId"
                       target="_blank"><%--<div class="year">{{item.enter}}YRS</div>--%>{{item.supName}}</a>
                    <div>
                        <img class="mr6 icon01" src="/home/v3/static/images/ico/icon_cert.png" alt="Certificate"/>
                        Certificate
                        <template  v-if="item.svsInfo && item.svsInfo.grade != 0 && item.svsInfo.status == 1">
                            <div class="i"></div>
                            <img v-if="item.svsInfo.grade == 1" src="/home/static/images/ico/icon-svs-yp.png" alt="SVS"/>
                            <img v-if="item.svsInfo.grade == 2" src="/home/static/images/ico/icon-svs-jp.png" alt="SVS"/>
                            <img v-if="item.svsInfo.grade == 3" src="/home/static/images/ico/icon-svs-zs.png" alt="SVS"/>
                            SVS
                        </template>
                    </div>
                    <div>
                        <div class="ww42">R&D：</div>
                        <a v-if="item.svsInfo" class="common-a" href="javascript:void(0);" :title="(item.svsInfo.researchBase + 12) > 20 ? 20 : (item.svsInfo.researchBase + 12)">
                            <el-rate v-model="/[\.]/.test(item.svsInfo.researchBaseStar)?Math.floor(item.svsInfo.researchBaseStar) + 0.5 + 3: item.svsInfo.researchBaseStar + 3" disabled></el-rate>
                        </a>
                        <a v-else class="common-a" href="javascript:void(0);" title="0">
                                <el-rate value="0" disabled></el-rate>
                            </a>
                    </div>
                    <div>
                        <div class="ww42">Output：</div>
                        <a v-if="item.svsInfo" class="common-a" href="javascript:void(0);" :title="(item.svsInfo.factoryBase + 12) > 20 ? 20 : (item.svsInfo.factoryBase + 12)">
                            <el-rate v-model="/[\.]/.test(item.svsInfo.factoryBaseStar)?Math.floor(item.svsInfo.factoryBaseStar) + 0.5 + 3: item.svsInfo.factoryBaseStar + 3" disabled></el-rate>
                        </a>
                        <a v-else class="common-a" href="javascript:void(0);" title="0">
                                <el-rate value="0" disabled></el-rate>
                            </a>
                    </div>
                    <div>
                        <div class="ww42">Scale：</div>
                        <a v-if="item.svsInfo" class="common-a" href="javascript:void(0);" :title="(item.svsInfo.capacityBase + 12) > 20 ? 20 : (item.svsInfo.capacityBase + 12)">
                            <el-rate v-model="/[\.]/.test(item.svsInfo.capacityBaseStar)?Math.floor(item.svsInfo.capacityBaseStar) + 0.5 + 3: item.svsInfo.capacityBaseStar + 3" disabled></el-rate>
                        </a>
                        <a v-else class="common-a" href="javascript:void(0);" title="0">
                                <el-rate value="0" disabled></el-rate>
                            </a>
                    </div>
                    <div>
                        {{item.originCountry}} ( {{item.originProvince}} )
                    </div>
                        <a class="btn" @click="ToContactSupplier(item.supId)">
                                Contact Supplier
                            </a>
                </div>
            </div>
            <!--产品 end-->

        </div>

        <block v-show="productLists.length>0">
            <el-pagination
                    layout="prev, pager, next"
                    :total="allpage"
                    prev-text="Previous"
                    next-text="Next"
                    @current-change="current_change">
            </el-pagination>
        </block>

        <div class="noData" v-show="noData">
            <h1>NO RESULT</h1>
            <p>There is no relevant content for the time being.</p>
        </div>

    </div>
    <!--页面右部列表  end-->

    <div class="bigPicBox" v-show="bigPicBox">
        <img :src="util_function_obj.image(bigPicBoxpic,446)" alt=""/>
    </div>

</div>

<div id="foot">
        <index-bottom></index-bottom>
</div>


<script>
    function getParams(name, defaultValue) {
        var url = window.location.href;
        var l = url.lastIndexOf(name)
        if (l != -1) {
            var ll = url.indexOf("&", l);
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


    new Vue(
        {
            el: "#foot"
        }
    )
    new Vue({
        el: '#productList',
        data: {
            selelv: "",
            selecount: "",
            selestore: "",
            cated: '', // 分类
            lose: '',
            currentPage: 1,
            allpage: '',
            noData: false,
            productLists: [],
            breadcrumbnav: [],
            page: 0,   // 第几个商品开始
            limit: 8,   // 每页请求数量
            curr: 1,    // 当前页数
            classLists: [],
            bigPicBox: false,
            bigPicBoxpic: '',
            countryList:[], // 国家列表
            pName:'', // 搜索值
            lessthan:'', // 最小起订量
            sort:null, // 参数格式为 : [{name:"curPrice",sort:1,rule:1}] name为排序的字段  rule 1 为倒序,0为正序
            grade:[], // SVS等级 0-无等级商家 1-银 2 -金 3-钻石
            min:'', // 最小价格
            max:'', //最大价格
            filterAreaKeyword:'', // 过滤 搜索国家
            selectedMarketCountryList:[], // 国家主键
            priceSortList:[
                {name:"From low to high",rule:0,},
                {name:"From high to low",rule:1},
            ],
            pricerRuleActive:null,
        },
        methods: {
            changeCountry(){
                this.page = 0
                this.curr = 1
                this.productList();
            },
            changeSVSLevel(){
                this.page = 0
                this.curr = 1
                this.productList();
            },
            priceBtn(rule){  // 价格排序 选择按钮
                if(this.pricerRuleActive == rule){
                    return;
                }else{
                    this.sort =  rule
                    this.page = 0
                    this.curr = 1
                    this.productList();
                }
                this.pricerRuleActive = rule
            },
            getCountry(){  // 获取 国家列表
                let self = this;
                axios.get('/home/plt_PltCountry_list').then(function (res) {
                    self.countryList = res.data.result
                })
            },
             // 跳转供应商表单
             ToContactSupplier(supplierPkey){
                let url = "/home/usr_UsrSupplier_goContactSupplier?supplierPkey=" + supplierPkey+ "&backUrl=" + window.location.href;
                util_function_obj.supplierCantEnter(this, url,"Please register or login your buyer account if you want making enquiries.");
            },
            // 跳转商品询盘表单
            ToProductInquiry(pdtId){
                let url = '/home/usr_UsrConsult_productPublishView?product_id=' + pdtId+ "&backUrl=" + window.location.href;
                util_function_obj.supplierCantEnter(this, url,"Please register or login your buyer account if you want making enquiries.");
            },
            // 读取链接带参
            GetQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(decodeURIComponent(r[2]));
                return null;
            },

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
            // 获取产品列表
            productList(e) {
                // if (this.pName === null) {
                //     this.pName = ''
                // }
                // axios.get('/home/pdt_PdtProduct_gtProductsIndexListAjax?v=3', {
                //     params: {
                //         start: this.page,
                //         limit: this.limit,
                //         cate: this.cated,
                //         min: this.min,
                //         max: this.max,
                //         mOrder: this.lessthan,
                //         pName: unescape(decodeURIComponent(this.pName)),
                //         level: this.selelv,
                //         export: this.selecount,
                //         o2oAddress: this.selestore,
                //         orderfld: getParams("orderfld", "NONE"),
                //         lose: this.lose,
                //         IsO2o: 1,
                //     }
                // })
                var params = {
                        start:this.page,
                        limit:this.limit,
                        // "search.supplier":8,
                        "search.export": this.selectedMarketCountryList.toString(),
                        "search.minOq": this.lessthan,
                        "search.minCurPrice": this.min,
                        "search.maxCurPrice": this.max,
                        "search.keywords": this.pName,
                        "search.category": this.cated,
                        "newSort[0].name":"curPrice",
                        "newSort[0].rule":this.sort,
                        "search.grade": this.grade.toString(),
                        "search.isO2o": 1,
                    };
                    var url = '/home/pdt_PdtProduct_gtProductsIndexListAjax?v=4'
                    var numbers = 0
                    for(var i in params){
                        if(i != Object.keys(params).length - 1 && params[i] != null){
                            url += "&"
                        }
                        if(params[i] != null){
                            url += i+"="+params[i]
                        }
                        numbers++
                    }
                axios.get(encodeURI(url))
                    .then((res) => {
                        this.productLists = res.data.result.items
                        this.allpage = res.data.result.totalCount
                        this.breadcrumbnav = res.data.result.breadcrumbnav
                        if (res.data.result.items.length <= 0) {
                            this.noData = true
                        } else {
                            this.noData = false
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
            // 添加收藏和取消收藏
            shoucang: function (e) {
                var index = e.currentTarget.dataset.num;
                if(!sysConfig || !sysConfig.user){
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }else {
                    if(sysConfig.user.user_type == 1){
                        this.$alert("If you want to get this supplier or shoes in your wish list,please register or login your buyer account.",{
                            confirmButtonText: 'Ok',
                            customClass: "my-custom-element-alert-class fs-content-18",
                            center: true,
                            callback: action =>{
                                return
                            }
                        });
                        return
                    }
                    axios.get('/home/usr_UsrFavorites_addFavorite', {
                        params: {
                            pdtPkey: e.currentTarget.dataset.id
                        }
                    })
                        .then((res) => {
                            if (res.data.ret == 1) {
                                this.$set(this.productLists[index], "eshrine", !this.productLists[index].eshrine)
                            } else {
                                this.$message.error(res.data.msg || "The operation failed, please try again later");
                            }
                        })
                        .catch((error) => {

                        });
                }
            },

            //   页数加载
            current_change: function (currentPage) {
                this.page = (currentPage - 1) * this.limit;
                this.productList();
                document.body.scrollTop = 0
                document.documentElement.scrollTop = 0
            },

            //   最小购买量失去焦点时触发
            lessthan222: function () {
                // if(this.lessthan!=''){
                // 	this.productList();
                // }
            },
            //   最小金额最大金额 失去焦点时触发
            min222: function () {
                // if(this.min>=0 && this.max>0){
                // 	if(this.max>this.min){
                // 		this.productList();
                // 	}else{
                // 		this.$message({
                //       message: 'Max must be greater than Min',
                //       type: 'warning'
                //     });
                // 	}
                // }
            },
            //   商家等级筛选
            seleLevel: function (e) {
                if (this.selelv == e.currentTarget.dataset.selelv) {
                    this.selelv = ""
                } else {
                    this.selelv = e.currentTarget.dataset.selelv;
                }
                this.productList();
            },
            //   出口国筛选
            seleCount: function (e) {
                if (this.selecount == e.currentTarget.dataset.selecount) {
                    this.selecount = ""
                } else {
                    this.selecount = e.currentTarget.dataset.selecount;
                }
                this.productList();
            },
            //   展会在哪国筛选
            seleStore: function (e) {
                if (this.selestore == e.currentTarget.dataset.selestore) {
                    this.selestore = ""
                } else {
                    this.selestore = e.currentTarget.dataset.selestore;
                }
                this.productList();
            },
            // 点击后才实现搜索
            search() {
                // min order正整数判断
                if( this.lessthan && !util_regular_obj.register.positiveInteger.test(this.lessthan) ){
                    this.$message({
                        message: 'Min order should be positive integer number',
                        type: 'warning'
                    });
                    return;
                }else if( this.min && !util_regular_obj.register.priceDecimal.test(this.min) ){
                    this.$message({
                        message: 'Min price can\'t greater than 6 digit integer and 2 decimal places',
                        type: 'warning'
                    });
                    return;
                }else if( this.max && !util_regular_obj.register.priceDecimal.test(this.max) ){
                    this.$message({
                        message: 'Max price can\'t greater than 6 digit integer and 2 decimal places',
                        type: 'warning'
                    });
                    return
                }else if (this.min >= 0 && this.max > 0 && this.max < this.min) {
                    this.$message({
                        message: 'Max must be greater than Min',
                        type: 'warning'
                    });
                    return
                } else {
                    this.limit = 8
                    this.page = 0
                    this.curr = 1
                    this.productList();
                }
            },
            // 点击左侧分类时跳转
            categorySearch(e) {
                document.body.scrollTop = 0
                document.documentElement.scrollTop = 0
                this.lose = 1
                this.curr = 1;
                this.cated = e.currentTarget.dataset.cated;
                this.page = 0;
                this.productList();
            },
            //   添加到询盘
            addRFQ(e) {
                if (!sysConfig.user) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                }
                axios({
                    url: "/home/pdt_PdtConsultPdtList_add",
                    method: "post",
                    data: Qs.stringify({
                        product: e.currentTarget.dataset.id
                    })
                }).then(function (data) {
                    if (data.data) {
                        if (data.data.ret && data.data.ret === 1) {
                            carWindow(data.data, lang_obj.global.inqadd)
                        }
                    }
                })
            },

            //	  打开放大图片窗口
            bigPicBoxopen(e) {
                this.bigPicBoxpic = e.currentTarget.dataset.pic;
                this.bigPicBox = true
            },
            //	  关闭放大图片窗口
            bigPicBoxclose(e) {
                this.bigPicBox = false
            },

        },
        mounted() {
            this.pName = this.GetQueryString("Keyword")
            this.cated = getParams('cated','')
            // if(this.GetQueryString("cated").length>0){
            // 	this.lose=1
            // }
            this.classList();
            this.productList();
            this.getCountry();

        },
        watch: {
            // 输入监听
            lessthan: function (val) {
                this.lessthan = val;
            },
            min: function (val) {
                this.min = val;
            },
            max: function (val) {
                this.max = val;
            },
        }

    })
</script>

</body>

</html>
