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
                                <div>{{companyInfo.time}}</div>
                            </div>
                            <div class="flexSt">
                                <div class="title">Destination Port:</div>
                                <div>{{companyInfo.rfqCountry}}</div>
                            </div>
                        </div>
                    </li>
                    <li class="flexSt">
                        <div class="title">Product Image:</div>
                        <div class="img-list flexSt">
                            <div v-if="images" class="img-item" v-for="item in images">
                                <img :src="images?isLogin?image(item):image(item)+ '?x-oss-process=image/resize,w_80/blur,r_8,s_8':'./static/images/no-pdtImages.png'"
                                     alt="">
                            </div>
                        </div>
                    </li>
                    <li class="flexSt">
                        <div class="title">Product Details:</div>
                        <div style="text-decoration: underline;cursor: pointer;" @click="showpdtDetails">show</div>
                        <div style="width:720px;" v-if="pdtDetails">{{companyInfo.pdtDetails}}</div>
                    </li>
                    <li class="flexSt">
                        <div class="title">Quantity Required:</div>
                        <div>{{companyInfo.quantitys}}pairs</div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Buyer Background Information -->
        <div class="section2">
            <h1>Buyer Background Information</h1>
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
                    <a href="" target="_blank">Quote Now</a>
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
                <h3 class="fl">Request for Quotation</h3>
            </div>
            <el-form ref="form" :model="form" :rules="rules" label-width="220px" class="quotation-form">
                <el-form-item label="Product Name :" prop="title">
                    <el-input v-model="form.title" placeholder="Please Enter The Product Name."></el-input>
                </el-form-item>
                <el-form-item label="Product Detailed Specification :" prop="descriotion">
                    <el-input type="textarea" :rows="6" v-model="form.descriotion"
                              placeholder="Please enter your Proct/Service Details"></el-input>
                </el-form-item>
                <el-form-item label="Estimates Order Quantity :" prop="quantity">
                    <el-row>
                        <el-col :span="7">
                            <el-input v-model="form.quantity"></el-input>
                        </el-col>
                        <el-col :span="5" :offset="1">
                            <el-form-item prop="unit">
                                <el-select v-model="form.unit" placeholder="Pairs">
                                    <el-option label="Pairs" value="1"></el-option>
                                    <el-option label="Forty-Foot Container" value="2"></el-option>
                                    <el-option label="Twenty-Foot Container" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('form')">Submit</el-button>
                    <a href="/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1"
                       style="color: #9fafd7;font: size 12px;" target="_blank">Add more requirements</a>
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
        data: {
            companyInfo: [],
            images: [],
            pdtDetails: false,
            bannerList: [
                {
                    imgUrl: '/home/v3/static/images/ljxbanner1.jpg',
                    url: "/country/Romania-Pantofi-en-gros/romania-index-ro.html"
                },
                {imgUrl: '/home/v3/static/images/ljxbanner2.jpg', url: "/home/pdt_PdtProduct"},
                {imgUrl: '/home/v3/static/images/ljxbanner3.jpg', url: "/home/usr_UsrConsult_publishView"},
            ],
            form: {
                title: '',
                descriotion: '',
                quantity: '',
                unit: '',
                currency: 1,
                pay_type: 1,
                shipping_type: 1
            },
            rules: {  //表单验证
                title: [{
                    required: true,
                    message: '请输入标题',
                    trigger: 'blur'
                },],

                descriotion: [{
                    required: true,
                    message: '请输入内容',
                    trigger: 'blur'
                }],
                quantity: [{
                    required: true,
                    message: '请输入数量',
                    trigger: 'blur'
                }],
                unit: [{
                    required: true,
                    message: '请选择单位',
                    trigger: 'change'
                }],
            },
        },
        mounted() {
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
                        self.images = res.data.result.pdtImages.split(",")
                    } else {
                        self.$message.error(res.data.msg);
                        return
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        methods: {
            showpdtDetails() {  //点击显示 商品详情
                if (isLogin) {
                    //  登录了
                    this.pdtDetails = true;
                } else {
                    //  没有登录 提醒去登录
                    this.$confirm('登录后可以查看完整信息!是否登录?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        window.location.href =
                            '/home/usr_UsrPurchase_sign?jumpUrl=/home/rfq_RFQConsult_getRFQReadMore';
                    }).catch(() => {

                    });

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
                this.$refs[formName].validate((valid) => {
                    if (valid) {
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
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                    // setTimeout(function () {
                                    //   gtag_report_conversion()
                                    //   window.location.href =
                                    //     '/home/usr_UsrConsult_listView';
                                    // }, 2000)
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 2000)
                                    // 未登录时
                                } else if (res.data.ret == -1) {
                                    window.location.href =
                                        '/home/usr_UsrPurchase_sign?jumpUrl=/home/rfq_RFQConsult_getRFQReadMore';
                                    // 提交失败时
                                } else {
                                    this.$alert(res.data.msg, {
                                        confirmButtonText: 'OK'
                                    });
                                }

                            })
                            .catch((err) => {
                                console.log(err)
                            })
                    } else {
                        console.log('error submit!!');

                    }
                });
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
