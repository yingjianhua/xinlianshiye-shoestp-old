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
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link href="./static/css/index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <!-- 新引入的css -->
    <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
    <!-- 轮播插件 -->
    <script type="text/javascript" src="./static/js/jquery.SuperSlide.js"></script>
    <script type="text/javascript" src="./static/js/qs.js"></script>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="new-style-page lang_en w_1200">


<%@ include file="/home/template/web-top.jsp" %>


<div id="main">

    <div class="clean">
    </div>
    <index-top></index-top>
    <%@ include file="/home/template/shop-header.jsp" %>
    <div class="wide">
        <!-- 商家-信息1 -->
        <div class="enterprise-info-wrap">
            <h3 class="enterprise-info-title">
                <s:text name="CompanyInformation"/></h3>
            <div class="enterprise-info-content divide-harf">
                <div class="divide-harf-item">
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="purchase.company"/></h5>
                        <div class="text"><c:if test="${supView.name != 'null'}">${supView.showName}</c:if></div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.businesstyp"/></h5>
                        <div class="text">
                            <c:if test="${supView.businessTyp != 'null'}">${supView.businessTyp}</c:if></div>
                    </div>
                    <c:set var="symbolNoLogin" value="********"/>
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="Global.Address"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
                            </c:if>
                        </div>
                    </div>
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supt7"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.mainProd != 'null'}">${supView.mainProd}</c:if>
                            </c:if>

                        </div>
                    </div>
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supt8"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.prodPattern != 'null'}">${supView.prodPattern}</c:if>
                            </c:if>

                        </div>
                    </div>
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="developerNumber"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.developer != 'null'}">${supView.developer}</c:if>
                            </c:if>

                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.totalemployees"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.totalEmployees != 'null'}">${supView.totalEmployees}</c:if>
                            </c:if>

                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.annualsales"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.annualSales != 'null'}">${supView.annualSales}</c:if>
                            </c:if>

                        </div>
                    </div>
                </div>

                <div class="divide-harf-item">
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.companyestablishtime"/></h5>
                        <div class="text">
                            <c:if test="${supView.companyEstablishTime != 'null'}">${supView.companyEstablishTime}</c:if></div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.top3markets"/></h5>
                        <div class="text"><c:if test="${supView.top3Markets != 'null'}">${supView.top3Markets}</c:if>
                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="supplier.materials"/></h5>
                        <div class="text"><c:if test="${supView.materials != 'null'}">${supView.materials}</c:if>
                        </div>
                    </div>

                </div>
            </div>
            <!-- 商家-信息1 - end -->

            <!-- 商家-信息2 -->
            <div class="enterprise-info-wrap">
                <h3 class="enterprise-info-title">
                    <s:text name="authenticationInformation"/></h3>
                <div class="enterprise-info-content">
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="On-siteInspection"/></h5>
                        <div class="text">
                            <s:text name="On-siteInspection1"/>
                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="businessLicense"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <s:text name="RegistrationNo"/>. <c:if
                                    test="${supView.creditCode != 'null'}">${supView.creditCode}</c:if>
                                <br> <s:text name="purchase.company"/>: <c:if
                                    test="${supView.name != 'null'}">${supView.showName}</c:if>
                                <br> <s:text name="issue"/>: <c:if
                                    test="${supView.authTime != 'null'}">${supView.authTime}</c:if>
                                <br> <s:text name="supplier.registeredcapital"/>:<c:if
                                    test="${supView.registeredCapital != 'null'}">${supView.registeredCapital}</c:if>
                                <br> <s:text name="Global.Country"/>
                                <br> ${country }
                                <br> <s:text name="registeredAddress"/>
                                <br> <c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
                                <br> <s:text name="YearEstablished"/>
                                <br> <c:if
                                    test="${supView.companyEstablishTime != 'null'}">${supView.companyEstablishTime}</c:if>
                                <br> <s:text name="LegalRepresentative"/>
                                <br> <c:if test="${supView.entity != 'null'}">${supView.entity}</c:if>
                                <br> <s:text name="LegalForm"/>
                                <br> <c:if
                                    test="${supView.companyType != 'null'}">${supView.companyType}</c:if><!-- COMPANY_ADDR -->
                                <!-- <br> Issuing Authority
                                <br> tai'an City daiyue District Administration for Industry and Commerce -->
                            </c:if>
                        </div>
                    </div>

                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="OperationalAddress"/></h5>
                        <div class="text">
                            <c:if test="${env.login == null}">
                                ${symbolNoLogin}
                            </c:if>
                            <c:if test="${env.login != null}">
                                <c:if test="${supView.location != 'null'}">${supView.location }</c:if>
                            </c:if>

                        </div>
                    </div>
                    <a name="data"></a>
                    <!-- 供应商信息 -->
                    <div class="supplier-info-list">


                        <c:if test="${supView.isAuth == 1}">
                            <div class="supplier-info-item">
                                <h5 class="title">
                                    <i class="icon icon-defence-safe"></i>
                                    <span><s:text name="On-siteInspection"/></span>
                                </h5>
                                <div class="text">
                                    <s:text name="On-siteInspection2"/>
                                </div>
                            </div>
                            <div class="supplier-info-item">
                                <h5 class="title">
                                    <i class="icon icon-certification"></i>
                                    <span><s:text name="Certification"/></span>
                                </h5>
                                <div class="text">
                                    <s:text name="On-siteInspection3"/>
                                </div>
                            </div>
                        </c:if>

                        <!-- <div class="supplier-info-item">
                            <h5 class="title">
                                <i class="icon icon-defence-safe"></i>
                                <span>Onsite Check</span>
                            </h5>
                            <div class="text">
                                The supplier information has been checked and confirmed by SHOESTP.COM staff.
                            </div>
                        </div>
                        <div class="supplier-info-item">
                            <h5 class="title">
                                <i class="icon icon-certification "></i>
                                <span>Certification</span>
                            </h5>
                            <div class="text">
                                The supplier's company premises are inspected by the SHOESTP.COM staff and confirmed
                                that the scene is authentic.
                            </div>
                        </div> -->
                        <div class="supplier-info-item">
                            <h5 class="title">
                                <i class="icon icon-years icon-years-07"></i>
                                <span><s:text name="AgeLimit"/></span>
                            </h5>
                            <div class="text">
                                <s:text name="On-siteInspection4"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 商家-信息2 - end -->
            <!-- Contact supplier 表单 start -->
            <div class="contact-supplier">
                <h3 class="title">Contact supplier</h3>
                <div class="supplier-form">
                    <div class="flexSt">
                        <div class="company-logo">
                            <img src="${envConfig.imageBaseUrl}${supView.logo}?x-oss-process=image/resize,m_pad,h_130,w_165"
                                 alt="">
                        </div>
                        <p class="company-name"><c:if test="${supView.name != 'null'}">${supView.showName}</c:if></p>
                        <div>
                            <img src="./static/images/ico/icon-svs.png" alt="">
                            svs
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
                                <el-col :span="5">
                                    <div>on shoestp.com</div>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item label="Purchase  Quantity" prop="quantity">
                            <el-row :gutter="10">
                                <el-col :span="7">
                                    <el-input v-model.trim="form.quantity"></el-input>
                                </el-col>
                                <el-col :span="5">
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
                        <el-form-item label="Extra Request" prop="extra_request" class="request">
                            <el-checkbox-group v-model="form.extra_request">
                                <el-checkbox label="price" name="price">Price</el-checkbox>
                                <el-checkbox label="inspection certificate" name="inspection certificate">Inspection
                                    Certificate
                                </el-checkbox>
                                <el-checkbox label="product specifications" name="product specifications">Product
                                    Specifications
                                </el-checkbox>
                                <el-checkbox label="company profile" name="company profile">Company Profile
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                        <el-form-item label="Message:" prop="description">
                            <el-input
                                    placeholder="Enter product details such as color, size, materials etc. and other specification requirements to receive an accurate quote."
                                    type="textarea" v-model.trim="form.description"
                                    :autosize="{ minRows: 8, maxRows: 8}"></el-input>
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
                                * Recommend matching suppliers if this supplier doesn't contact me on Message Center
                                within 24 hours. RFQ
                            </div>
                        </el-form-item>
                        <el-form-item>
                            <el-button :disabled="flag" type="primary" @click="submitForm('form')">Send inquiry now</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <!-- Contact supplier 表单 end -->
        </div>

    </div>
    <index-bottom></index-bottom>

    <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
    <script type="text/javascript" src="./static/js/module.js"></script>
    <script type="text/javascript" src="./static/js/review.js"></script>
    <script type="text/javascript" src="./static/js/lightbox.min.js"></script>
    <!-- <script type="text/javascript" src="./static/js/addthis_widget.js"></script> -->

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
            el: "#main",
            data() {
                return {
                    flag : false, 
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
                        unit: '1',  // 单位
                        extra_request: [],  //  额外要求
                        description: '',  //描述
                        images: '',  // 图片
                        supplierId: null  // 供应商id
                    },

                    rules: {
                        title: [
                            {required: true, message: 'Please fill in the title'},
                            { max: 500, message: 'Enter up to 500 digits', trigger: 'blur' }
                        ],
                        quantity: [
                            {required: true,message: 'Please enter the quantity',trigger: 'blur'},
                            { max: 10, message: 'Enter up to 10 digits', trigger: 'blur' },
                            { pattern: /^\+?[1-9][0-9]*$/, message: "Please enter a number, can't start with 0, can't have decimal point" }
                        ],
                        description: [{
                            required: true,
                            message: 'Please fill in the message',
                            trigger: 'blur'
                        }]
                    },
                    pkey: null,
                    supData: [],
                }
            },
            mounted() {
                // 进来页面获取到供应商信息
                let self = this;
                // console.log(window.location.href)
                self.pkey = self.getQueryString("pkey");
                self.$set(self.form,"supplierId",self.pkey)
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
                    console.log(response)
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
                beforeUpload(file) {
                    console.log(file)
                    // if (!sysConfig || !sysConfig.user) {
                    //     this.$alert('Please login to operate', 'Please login to operate', {
                    //         confirmButtonText: 'Ok',
                    //         customClass: "my-custom-element-alert-class fs-content-18",
                    //         callback: action => {
                    //             window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrSupplier_gtSupInfo?pkey="+this.pkey
                    //         }
                    //     });
                    //     return
                    // }
                    let size = file.size / 1024;
                    if (size > 500) {
                        this.$message.error('Image size cannot exceed 500k');
                        return false;
                    }
                },
                submitForm(formName) { // 表单提交
                    let self = this;
                    if(!sysConfig || !sysConfig.user){
                        util_function_obj.alertWhenNoLogin(self);
                        return
                    }else{
                        // 登录了
                        if(sysConfig.user.user_type == 1){
                            self.$alert("Sorry, the supplier cannot submit the form",{
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
                        self.$refs[formName].validate((valid) => {
                         if (valid) {
                            self.flag = true;
                            console.log(self.form)
                            self.form.images = self.imgsToUpload.join(",");
                            console.log(self.imgsToUpload)
                            console.log('submit!');
                            let data = JSON.stringify(self.form)
                            axios.post("/home/rfq_RFQConsult_putSupplierInquiry", data, 
                                {headers: {'Content-Type': 'application/json'}}
                            )
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
                                            // gtag_report_conversion()
                                            // window.location.href =
                                            //     '/home/usr_UsrSupplier_gtSupInfo?pkey=' + self.pkey;
                                            window.location.reload();
                                        }, 1500)
                                        // 未登录时
                                    } else {
                                        self.flag = false;
                                        this.$alert(res.data.msg || "Failed to submit the form, please refresh the page and try again", {
                                            confirmButtonText: 'OK',
                                            customClass: "my-custom-element-alert-class fs-content-18",
                                        });
                                    }

                                })
                                .catch((err) => {
                                    self.flag = false;
                                    self.$message.error("Network error, please refresh the page and try again");
                                    console.log(err)
                                })
                                } else {
                                    console.log('error submit!!');
                                    // if (!self.form.quantity) {
                                    //     self.$message.error('Quantity cannot be empty');
                                    // } else if (!self.form.unit) {
                                    //     self.$message.error("Select unit");
                                    // } else if (!self.form.description) {
                                    //     self.$message.error('Please fill in the message');
                                    // }
                                    // return false;
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
