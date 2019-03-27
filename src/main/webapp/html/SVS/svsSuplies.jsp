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
	<link rel="stylesheet" href="css/index.css">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<title></title>
    <link rel="stylesheet" href="css/reset.css">
    <script src="components/O2O-bottom.js"></script>
</head>
<jsp:include page="/home/v3/header.jsp"/>
<body>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
	<div id="svsSuplies">
        <index-top></index-top>
        <div class="nav">
            <div class="navitem" @click="showcheck(1)">
                <h3>Target Market<img src="images/svssupicondown.png" alt=""></h3>
                <div class="select" :class="select1 ?  'height' : ''">
                    <el-checkbox-group v-model="marketingList">
                        <el-checkbox
                            v-for="item,index in marketingList"
                            :key="item.value"
                            :label="item.value">{{item.label}}
                        </el-checkbox>
                    </el-checkbox-group>
                </div>
            </div>
            <div class="navitem" @click="showcheck(2)">
                <h3>Shoe-making process<img src="images/svssupicondown.png" alt=""></h3>
                <div class="select"  :class="select2 ?  'height' : ''">
                    <el-checkbox-group v-model="processList">
                        <el-checkbox
                            v-for="item,index in processList"
                            :key="item.value"
                            :label="item.value">{{item.label}}
                        </el-checkbox>
                    </el-checkbox-group>
                </div>
            </div>
            <div class="navitem" @click="showcheck(3)">
                <h3>SVS Level<img src="images/svssupicondown.png" alt=""></h3>
                <div class="select"  :class="select3 ?  'height' : ''">
                    <el-checkbox-group v-model="LevelList">
                        <el-checkbox
                            v-for="item,index in LevelList"
                            :key="item.value"
                            :label="item.value">{{item.label}}
                        </el-checkbox>
                    </el-checkbox-group>
                </div>
            </div>
        </div>
        <div class="suplies" v-for="item,index in sublies">
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
                    <a class="title" :href="'/home/usr_UsrSupplier_gtSupIndex?pkey=' + item.id" target="_blank">{{item.showName}}</a>
                    <p class="p">{{item.showName}}</p>
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
                    <p>{{item.mainSalesArea}}</p>
                </div>
                <div class="product">
                    <div v-for="pro in item.products">
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
                    <a href="javascript:void(0)" class="content_item_button btn_colour_2">Search now</a>
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
                LevelList:[],
                processList:[],
                marketingList:[],

                select1:false,
                select2:false,
                select3:false,

                page:1,
                pagerSize: 6, // 页面显示条数
                pagerCount: 6, //
                pagerTotal: 100, // 总条数

                marketingoptions: [],

                sublies:[],
			},
			methods:{
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
                    this.getmsg();
                },
                cllection:function (index) {
                    // this.$set(this.sublies[index],'isFavorite',!this.sublies[index].isFavorite)
                },
                //获取供货商列表
                getmsg:function () {
                    let self = this;
                    axios.get('/home/usr_UsrSupplier_listSuppliers',{
                        params:{
                            start: (this.page -1) * self.pagerCount,
                            limit: self.pagerCount
                        }
                    }).then(function (res) {
                        if(res.data.ret == 1){
                            let data = res.data.result;
                            console.log(data)
                            self.pagerTotal = data.totalCount
                            self.$set(self,'sublies',data.items)
                            console.log(self.sublies)
                        }
                    })
                }
			},
			mounted(){
                this.getmsg();
			}
		})
	</script>
</body>
</html>
