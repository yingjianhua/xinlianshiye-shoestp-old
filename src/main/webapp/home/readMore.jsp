<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"/>
<link rel="stylesheet" href="./static/css/readMore.css"/>
<body>
<jsp:include page="v3/nav.jsp"></jsp:include>
<div id="readMore" class="clearfix" v-cloak>
    <index-top></index-top>
    <div class="top-box">
        <!-- <a href="" target="_blank"> -->
        <div class="top-banner"></div>
        <!-- </a> -->
    </div>
    <div class="content-box">
        <!-- Product Information & Transport Information -->
        <div class="section1">
            <h1>Product Information & Transport Information</h1>
            <div class="section1-content">
                <ul>
                    <li class="flexSb">
                        <div class="section1-content-left flexSb">
                            <div class="title">Product Keywords:</div>
                            <div class="list clearfix">
                                {{companyInfo.title}}
                            </div>
                        </div>
                        <div class="section1-content-right">
                            <div class="flexSt">
                                <div class="title">Posted On:</div>
                                <div style="width: 155px;word-wrap: break-word;">{{companyInfo.time}}</div>
                            </div>
                        </div>
                    </li>
                    <li class="flexSb">
                            <div class="section1-content-left flexSb">
                                <div class="title">Quantity Required:</div>
                                <div class="list">{{companyInfo.quantitys}} pairs</div>
                            </div>
                            <div class="section1-content-right">
                                <div class="flexSt">
                                    <div class="title">Destination Port:</div>
                                    <div style="width: 155px;word-wrap: break-word;">{{companyInfo.rfqCountry}}</div>
                                </div>
                            </div>
                        
                    </li>
                    <li class="flexSt">
                        <div class="title">Product Details:</div>
                        <div style="text-decoration: underline;cursor: pointer;" v-if="!pdtDetails" @click="showpdtDetails">show</div>
                        <div style="width:720px;word-wrap:break-word;" v-if="pdtDetails">{{companyInfo.pdtDetails}}</div>
                    </li>
                    <li class="flexSt">
                            <div class="title">Product Image:</div>
                            <div class="img-list flexSt" style="width:440px;">
                                <div class="img-item" v-if="images.length == 0">
                                    <img src="./static/images/no-pdtImages.png" alt="">
                                </div>
                                <div class="img-item" v-for="item in images" v-else>
                                    <img :src="sysConfig.user?image(item):image(item)+ '?x-oss-process=image/resize,w_80/blur,r_8,s_8'" alt="">
                                </div>
                            </div>
                      
                    </li>
                </ul>
            </div>
        </div>
        <!-- Buyer Background Information -->
        <div class="section2">
            <h1>Buyer Information</h1>
            <div class="section2-content flexSb">
                <ul class="section2-ul1">
                    <li class="flexSt">
                        <div class="title">Purchaser:</div>
                        <div class="content">{{companyInfo.userName}}</div>
                    </li>
                    <li class="flexSt content">
                        <div class="title">Country / Region:</div>
                        <div class="flexSt">
                            <div>
                                {{companyInfo.userCountry}}
                            </div>
                            <div>
                                <img :src="image(companyInfo.userCountryImage)" alt=""
                                     style="margin-left:8px;width:26px;height:20px;">
                            </div>
                        </div>
                    </li>
                    <li class="flexSt">
                        <div class="title" style="width:260px; margin-left: -105px;">Preferred Payment Method:</div>
                        <div class="content">{{companyInfo.payMethod}}</div>
                    </li>
                </ul>
                <ul class="section2-ul2">
                    <p>To view this buyer's contact information please <span style="color: #dd2811;">Sign In Now</span>
                        ! Not a member yet? It takes less than a minute to Join <span
                                style="color: #dd2811;">FREE</span> now!</p>
                    <a href="javascript:void(0)" @click="quoteNow">Quote Now</a>
                </ul>
            </div>
        </div>
        <div class="banner-box">
            <el-carousel height="224px" indicator-position="outside">
                <el-carousel-item v-for="item in bannerList" :key="item.url">
                    <a :href="item.url" target="_blank">
                        <img :src="item.imgUrl" alt="">
                    </a>
                </el-carousel-item>
            </el-carousel>
        </div>
        <!-- 问题表单 -->
        <div class="quotation">
            <div class="quotation-title clearfix">
                <h3 class="fl">Request For Quotation</h3>
            </div>
            <el-form ref="form" :model="form" :rules="rules" label-width="275px" class="quotation-form">
                <el-form-item label="Product Name :" prop="title">
                    <el-input v-model.trim="form.title" placeholder="Please Enter The Product Name."></el-input>
                </el-form-item>
                
                <el-form-item label="Product Detailed Specification :" prop="descriotion">
                    <el-input class="textarea-placeholder" type="textarea" :rows="6" v-model.trim="form.descriotion"
                              placeholder="Please enter your Proct/Service Details"></el-input>
                </el-form-item>
                <el-form-item label="Estimates Order Quantity :" prop="quantity">
                    <el-row>
                        <el-col :span="7">
                            <el-input v-model.trim="form.quantity"></el-input>
                        </el-col>
                        <el-col :span="5" :offset="1">
                            <el-form-item prop="unit">
                                    <el-select v-model="form.unit" placeholder="Unit">
                                            <el-option
                                            v-for="item in options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                          </el-option>
                                    </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button :disabled="flag" type="primary" @click="submitForm('form')">Submit</el-button>
                    <a @click="ToRFQ" style="color: #9fafd7;font-size :14px;cursor: pointer;">Add more requirements</a>
                </el-form-item>
            </el-form>
        </div>
    </div>
    <index-bottom></index-bottom>

</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>

<script>
    new Vue({
        el: "#readMore",
        data(){
            return{
                flag : false, 
                companyInfo: [],
                images: [],
                pdtDetails: false, // 商品详情是否显示
                bannerList: [
                    {imgUrl: '/home/v3/static/images/ljxbanner1.jpg',url: "/country/Romania-Pantofi-en-gros/romania-index-ro.html"},
                    {imgUrl: '/home/v3/static/images/ljxbanner2.jpg', url: "/home/pdt_PdtProduct"},
                    {imgUrl: '/home/v3/static/images/ljxbanner3.jpg', url: "/home/usr_UsrConsult_publishView"},
                ],
                options: [{
                        value: "1",
                        label: "Pairs"
                    },
                        {
                            value: "2",
                            label: "Forty-Foot Container"
                        },
                        {
                            value: "3",
                            label: "Twenty-Foot Container"
                        },
                    ],
                form: {
                    title: '',
                    descriotion: '',
                    quantity: '',
                    unit: '1',
                    currency: 1,
                    pay_type: 1,
                    shipping_type: 1
                },
                rules: {  //表单验证
                    title: [
                        {required: true,message: 'Please fill in the title',trigger: 'blur'},
                        { max: 500, message: 'Enter up to 500 digits', trigger: 'blur' }
                    ],

                    descriotion: [{
                        required: true,
                        message: 'Please fill in the descriotion',
                        trigger: 'blur'
                    }],
                    quantity: [
                            {required: true,message: 'Please enter the quantity',trigger: 'blur'},
                            { pattern: util_regular_obj.register.positiveInteger, message: "Please enter the positive integer，and can\'t beyond 10 digits" }
                        ],
                    unit: [{
                        required: true,
                        message: 'Please select a unit',
                        trigger: 'change'
                    }],
                },
                }
            
        }, mounted() {
            var self = this;
            self.rfqPkey = self.getQueryString("rfqPkey");
            axios.get('/home/rfq_RFQConsult_getRFQDetails', {
                params: {
                    rfqPkey: self.rfqPkey
                }
            })
                .then(function (res) {
                    console.log(res);
                    if (res.data.ret == 1) {
                        self.companyInfo = res.data.result;
                        if(res.data.result.pdtImages){
                            self.images = res.data.result.pdtImages.split(",")
                        }
                    } else {
                        self.$message.error(res.data.msg || "Get information error, please refresh the page and try again");
                        return
                    }
                })
                .catch(function (error) {
                    self.$message.error("Network error, please refresh the page and try again");
                    console.log(error);
                });
        },
        methods: {
            // 跳转RFQ
            ToRFQ(){
                let url = "/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1" + "&backUrl=" + window.location.href
                util_function_obj.supplierCantEnter(this, url);
             },
            quoteNow(){
            //     // user_type  0普通用户  1卖家
               if(sysConfig.user){
                //   有登录
                console.log("登录")
                    if(sysConfig.user.user_type == 0){
                        // 普通用户
                        this.$alert('Please register or login your supplier account before you provide a quote for this RFQ', {
                        confirmButtonText: 'Sign In',
                        customClass: "my-custom-element-alert-class fs-content-18",
                        callback: action => {
                            console.log(action)
                            if(action == 'confirm'){
                                console.log("确定")
                                window.location.href =
                                '/home/usr_UsrPurchase_sign?jumpUrl=/home/rfq_RFQConsult_getRFQReadMore?rfqPkey=' + this.rfqPkey;
                            }else{
                                console.log("关闭")
                                return;
                            }
                        }
                    });
                    }else{
                        // 卖家
                         window.location.href =
                                '/newseller/#/RFQ/details?id=' + this.rfqPkey;
                    }
               }else{
                   console.log("没有登录")
                //    没有登录
                //  window.location.href =
                //             '/home/usr_UsrPurchase_sign?jumpUrl=/home/rfq_RFQConsult_getRFQReadMore?rfqPkey=' + this.rfqPkey;
                util_function_obj.alertWhenNoLogin(this);
                        return
               }
            },
            showpdtDetails() {  //点击显示 商品详情
                if (sysConfig.user) {
                    //  登录了
                    this.pdtDetails = true;
                } else {
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }
            },
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return "https://image.shoestp.com" + v + params
            },
            submitForm(formName) { //表单提交
                if(!sysConfig || !sysConfig.user){
                    util_function_obj.alertWhenNoLogin(this);
                        return
                    }else{
                        // 登录了
                        if(sysConfig.user.user_type == 1){
                            this.$alert("Sorry, the supplier cannot submit the form",{
                                confirmButtonText: 'Ok',
                                customClass: "my-custom-element-alert-class fs-content-18",
                                center: true,
                                callback: action =>{
                                    return
                                }
                            });
                            return
                        }else{
                            // 普通用户
                            this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.flag = true;
                        console.log(this.form)
                        console.log('submit!');
                        let data = JSON.stringify(this.form)
                        axios.post('/home/rfq_RFQConsult_putRFQInquiry', data,
                            {headers: {'Content-Type': 'application/json'}}
                        )
                            .then((res) => {
                                console.log(res)
                                // 提交成功时
                                if (res.data.ret == 1) {
                                    // 提示信息
                                    this.$message({
                                        showClose: true,
                                        message: 'Submitted successfully',
                                        type: 'success'
                                    });
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500)
                                    // 未登录时
                                }  else {
                                    this.flag = false;
                                    this.$alert(res.data.msg || "Failed to submit the form, please refresh the page and try again", {
                                        confirmButtonText: 'OK',
                                        customClass: "my-custom-element-alert-class fs-content-18",
                                    });
                                }

                            })
                            .catch((err) => {
                                this.flag = false;
                                this.$message.error("Network error, please refresh the page and try again");
                                console.log(err)
                            })
                    } else {
                        console.log('error submit!!');

                    }
                });
                        }
                    }
               
            },
            getQueryString: (name) => {
                let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                let reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
                let r = window.location.search.substr(1).match(reg);
                let q = window.location.pathname.substr(1).match(reg_rewrite);
                if (r != null) {
                    return unescape(r[2]);
                } else if (q != null) {
                    return unescape(q[2]);
                } else {
                    return null;
                }
            }
        }
    })
</script>
</body>

</html>
