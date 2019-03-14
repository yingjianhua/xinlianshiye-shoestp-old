<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0039)https://www.shoestp.com/m/sien/product/ -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title><c:if test="${supView.webSizeTitle !=''}">
        ${supView.webSizeTitle}
    </c:if>
        <c:if test="${supView.webSizeTitle ==''}">
            An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
        </c:if></title>
        <!-- <link href="./static/css/global.css" rel="stylesheet" type="text/css"> -->
        <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
        <!-- <link href="./static/css/user.css" rel="stylesheet" type="text/css">
        <link href="./static/css/style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="./static/css/animate.min.css">
        <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
        <link rel="stylesheet" href="./static/css/color.css" type="text/css">
        <link href="./static/css/index.css" rel="stylesheet" type="text/css"> -->
        <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <!-- 新引入的css -->
    <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
    <!-- 轮播插件 -->
    <!-- <script type="text/javascript" src="./static/js/qs.js"></script> -->
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="new-style-page lang_en w_1200">


<%@ include file="/home/template/web-top.jsp" %>



<div id="main">
    <div class="clean">
    </div>
    <index-top></index-top>
    <div class="wide">
         <!-- Contact supplier 表单 start -->
            <div class="contact-supplier">
                <h3 class="title">Contact Supplier</h3>
                <div class="supplier-form">
                    <div class="flexSt">
                        <div class="company-logo">
                            <img  :src="image(companyInfo.logo)" alt="">
                        </div>
                        <p class="company-name">
                            {{companyInfo.companyName}}
                        </p>
                        <div v-if="companyInfo.svsgrade == 0" style="color:#7f7f7f">
                            暂无等级
                        </div>
                        <div v-if="companyInfo.svsgrade == 1" style="color:#7f7f7f">
                            银牌级卖家
                        </div>
                        <div v-if="companyInfo.svsgrade == 2" style="color:#7f7f7f">
                            金牌级卖家
                        </div>
                        <div v-if="companyInfo.svsgrade == 3" style="color:#7f7f7f">
                            钻石级卖家
                        </div>
                    </div>
                    <el-form :model="form" :rules="rules" ref="form" label-width="155px">
                        <el-form-item label="Name of inquiry" prop="title">
                            <el-row :gutter="15">
                                <el-col :span="4">
                                    <div>I am looking for</div>
                                </el-col>
                                <el-col :span="8">
                                    <el-input v-model.trim="form.title"></el-input>
                                </el-col>
                                <el-col :span="5" >
                                    <div>on shoestp.com</div>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item label="Purchase  Quantity" prop="quantity" class="name">
                            <el-row :gutter="10">
                                <el-col :span="7">
                                    <el-input v-model.trim="form.quantity"></el-input>
                                </el-col>
                                <el-col :span="5">
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
                        <el-form-item label="Extra Request" prop="extraRequest" class="request">
                            <el-checkbox-group v-model="form.extraRequest">
                                <el-checkbox label="price" name="price">Price</el-checkbox>
                                <el-checkbox label="inspection certificate" name="inspection certificate">Inspection Certificate</el-checkbox>
                                <el-checkbox label="product specifications" name="product specifications">Product Specifications</el-checkbox>
                                <el-checkbox label="company profile" name="company profile">Company Profile</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                        <el-form-item label="Message:" prop="descriotion">
                            <el-input placeholder="Enter product details such as color, size, materials etc. and other specification requirements to receive an accurate quote." type="textarea" v-model.trim="form.descriotion":autosize="{ minRows: 8, maxRows: 8}"></el-input>
                        </el-form-item>
                        <el-form-item label="" prop="images">
                            <div class="upImg flexSt">
                                <el-upload action="/home/usr_UsrConsult_upload" list-type="picture-card"
                                           :on-success="handlePictureCardPreview" :limit="5" :on-remove="handleRemove"
                                           :before-upload="beforeUpload"
                                           accept=".jpg,.jpeg,.png"
                                >
                                    <img src="./static/images/ico/add-photos.png">
                                </el-upload>
                                <span class="add-photos-font" style="margin-left:10px;">add photos</span>
                            </div>
                        </el-form-item>
                        <el-form-item>
                            <div style="color: #e54544;">
                                * Recommend matching suppliers if this supplier doesn't contact me on Message Center within 24 hours. RFQ
                            </div>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('form')">Send inquiry now</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <!-- Contact supplier 表单 end -->
        </div>
    </div>
    <div id="bottom">
        <index-bottom></index-bottom>
    </div>

    <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
    <script type="text/javascript">
        $(function () {
            var type = sessionStorage.getItem("type");
            if (type == "1") {
                sessionStorage.setItem("type", "");
                window.scrollTo(0, 840)
            }
        }) 

    </script>
    ${supView.traceCode}
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>
    <script>    
         new Vue({
            el:"#bottom",
        })
    </script>
    <script>
        new Vue({
            el:"#main",
            data() {
                 var validateQuantity = (rule, value, callback) => {
                    let re = /^[1-9]\d*$/;
                    if(value){
                        if(parseInt(value)!=value){
                            callback(new Error('Please enter an integer'));
                        }
                        if( !re.test(value)){
                            callback(new Error('The number cannot be 0'));
                        }
                    }
                    callback();
                };
                return {
                    companyInfo:[], // 供应商信息
                    imgsToUpload: [], // 需要upload的img - 显示在页面上
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
                        title: '', // 标题 名字
                        quantity: '',  // 数量
                        unit: 'Pairs',  // 单位
                        extraRequest:[],  //  额外要求
                        descriotion: '',  //描述
                        images: '',  // 图片
                    },

                    rules: {
                        title:[
                            { required: true,message: 'Please fill in the title'}
                        ],
                        quantity: [
                            { validator: validateQuantity, trigger: 'blur' }
                        ],
                        descriotion: [{
                            required: true,
                            message: 'Please fill in the message',
                            trigger: 'blur'
                        }]
                    },
                    supplierPkey:null,
                    supData:[],
                }
            },
            mounted() {
                // 进来页面获取到供应商信息
                let self = this;
                self.supplierPkey = self.getQueryString("supplierPkey");
                axios.get('/home/usr_UsrSupplier_getSupplierDetail', {
                // axios.get('http://192.168.1.48:889/mock/5c6a1556af4d250024d48c6d/home/home/usr_UsrSupplier_getSupplierDetail', {
                params: {
                    supplierPkey: self.supplierPkey
                }
            })
                .then(function (res) {
                    console.log(res);
                    if (res.data.ret == 1) {
                        self.companyInfo = res.data.result;
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
                // elementui 上传功能 *2 - 删除操作
                handleRemove(file, fileList) {
                    // 清空imgs数组
                    this.imgsToUpload = [];
                    // 删除图片后，将授予的图片地址放入数组
                    $.each(fileList, (i, v) => {
                        console.log(v)
                        this.imgsToUpload.push(v.response.result.url)
                    })

                    if ((this.imgsToUpload.length <= 4)) {
                        $(".upImg .el-upload.el-upload--picture-card,.upImg .add-photos-font").show();
                    }
                },
                // elementui 上传功能 *2 - 上传成功操作
                handlePictureCardPreview(response, file, fileList) {
                    console.log("上传成功response" + response)
                    console.log(file)
                    console.log(fileList)
                    if (response.ret != 1) return;
                    // 添加图片后，在前面显示 img
                    this.imgsToUpload.push(file.response.result.url);
                    if (this.imgsToUpload.length >= 5) {
                        $(".upImg .el-upload.el-upload--picture-card,.upImg .add-photos-font").hide();
                    }
                    console.log(this.imgsToUpload)

                },
                // 上传图片文件之前
                beforeUpload(file){
                    console.log(file)
                    let size = file.size / 1024;
                    if(size > 500 ){
                        this.$message.error('Image size cannot exceed 500k');
                        return false;
                    }
                },
                submitForm(formName) { // 表单提交
                    let self = this;
                    self.$refs[formName].validate((valid) => {
                        if (valid) {
                            console.log(self.form)
                            self.form.images = self.imgsToUpload.join(",");
                            // self.form.pdtId = self.id;
                            console.log(self.imgsToUpload)
                            // if (self.form.images.length == 0) {
                            //     // if(${env.login==null}){
                            //     //     self.$message({
                            //     //         showClose: true,
                            //     //         message: 'Pleaselogin',
                            //     //         type: 'warning'
                            //     //     });

                            //     //     window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
                            //     // }
                            //     self.$message.error('Please upload an image');
                            //     return
                            // }
                            console.log('submit!');
                            let data = JSON.stringify(self.form)
                            axios.post("/home/rfq_RFQConsult_putRFQInquiry",data)
                                .then((res) => {
                                console.log(res)
                                // 提交成功时
                                if (res.data.ret == 1) {
                                // 提示信息
                                self.$message({
                                    showClose: true,
                                    message: 'Submitted successfully',
                                    type: 'success'
                                });
                                setTimeout(function () {
                                    gtag_report_conversion()
                                    window.location.href =
                                        '/home/usr_UsrSupplier_goContactSupplier?supplierPkey=' + self.supplierPkey;
                                }, 2000)
                                // 未登录时
                            } else if (res.data.ret == -1) {
                                window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrSupplier_goContactSupplier?supplierPkey=' + self.supplierPkey;
                                // 提交失败时
                            } else {
                                self.$alert(res.data.msg, {
                                    confirmButtonText: 'OK'
                                });
                            }

                        })
                        .catch((err) => {
                            console.log(err)
                        })
                        } else {
                            console.log('error submit!!');
                    // if (!self.form.quantity) {
                    //     self.$message.error('Quantity cannot be empty');
                    // } else if (!self.form.unit) {
                    //     self.$message.error("Select unit");
                    // } else if (!self.form.descriotion) {
                    //     self.$message.error('Please fill in the message');
                    // }
                    // return false;
                }
                });
                },
                getQueryString: (name) => {
                    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                    let reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
                    let r = window.location.search.substr(1).match(reg);
                    let q = window.location.pathname.substr(1).match(reg_rewrite);
                    if(r != null){
                        return unescape(r[2]);
                    }else if(q != null){
                        return unescape(q[2]);
                    }else{
                        return null;
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
            }
        })
    </script>
</body>

</html>
