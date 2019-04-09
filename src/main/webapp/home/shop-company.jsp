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
    <meta name="keywords" content="${supView.seoKeyword}">
    <meta name="description" content="${supView.seoContent}">
    <title>
        <c:if test="${supView.webSizeTitle !=''}">
            ${supView.webSizeTitle}
        </c:if>
        <c:if test="${supView.webSizeTitle ==''}">
            An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
        </c:if>
    </title>
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
    <style>
        .enterprise-info-content .info-item .title {
            flex: 0 0 250px;
        }

        .enterprise-info-content .info-item .text {
            word-break: break-all;
        }
    </style>
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

            <div class="enterprise-info-content horizontal-arrange">

                <div class="grow-more">
                    <!--公司名称-->
                    <div class="info-item">
                        <h5 class="title">
                            <s:text name="purchase.company"/>
                        </h5>
                        <div class="text">
                            <c:if test="${not empty supView.showName and !(supView.showName eq 'null') and !(supView.showName eq null)}">
                                ${supView.showName}</c:if></div>
                    </div>

                    <c:set var="symbolNoLogin" value="********"/>
                    <div class="info-item">
                        <%--公司地址--%>
                        <h5 class="title">
                            <s:text name="review.address"/>
                        </h5>

                        <div class="text">
                            <c:if test="${not empty supView.companyAddr and !(supView.companyAddr eq 'null') and !(supView.showName eq null)}">
                                ${supView.companyAddr}</c:if>
                        </div>
                    </div>
                    <div class="info-item">
                        <%--目标市场--%>
                        <h5 class="title">
                            <s:text name="TargetedMarkets"/>
                        </h5>
                        <div class="text">
                            <c:if test="${not empty supView.targetedMarkets and !(supView.targetedMarkets eq 'null') and !(supView.showName eq null)}">
                                ${supView.targetedMarkets}</c:if>
                        </div>
                    </div>
                    <div class="info-item">
                        <%--生产模式--%>
                        <h5 class="title">
                            <s:text name="shop-company.Production_Mode"/>
                        </h5>
                        <div class="text">
                            <c:if test="${not empty supView.prodPattern and !(supView.prodPattern eq 'null') and !(supView.showName eq null)}">
                                ${supView.prodPattern}</c:if>
                        </div>
                    </div>
                    <div class="info-item">
                        <%--年产量--%>
                        <h5 class="title">
                            <s:text name="AnnualOutput"/>
                        </h5>
                        <div class="text">
                            <c:if test="${not empty supView.annualOutput and !(supView.annualOutput eq 'null') and !(supView.showName eq null)}">
                                ${supView.annualOutput}</c:if>
                        </div>
                    </div>
                </div>

            </div>

            <!-- 商家-信息1 - end -->

            <!-- svs认证信息 -->
            <h3 class="enterprise-info-title">
                <s:text name="SupplierVerifiedInformation"/>
            </h3>
            <c:if test="${supView.statusAuth == 1}">
                <div class="enterprise-info-content divide-harf">
                    <div class="divide-harf-item">
                        <div class="info-item">
                                <%--独立研发团队--%>
                            <h5 class="title">
                                <s:text name="rDepartment"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.rddepartment and !(supView.rddepartment eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.rddepartment == 0}">
                                        <s:text name="yes"/>
                                    </c:if>
                                    <c:if test="${supView.rddepartment == 1}">
                                        <s:text name="no"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <c:set var="symbolNoLogin" value="********"/>
                        <div class="info-item">
                                <%--年开发鞋款数量--%>
                            <h5 class="title">
                                <s:text name="AnnualNumberOfNewShoes"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.annualNumberOfNewShoes and !(supView.annualNumberOfNewShoes eq 'null') and !(supView.showName eq null)}">
                                    ${supView.annualNumberOfNewShoes}
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--生产线数量--%>
                            <h5 class="title">
                                <s:text name="NumberOfProductionLines"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.numberOfProductionLines and !(supView.numberOfProductionLines eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.numberOfProductionLines < 50}">
                                        <s:text name="needleCartNumOptions1"/>
                                    </c:if>
                                    <c:if test="${supView.numberOfProductionLines > 50 and supView.numberOfProductionLines < 70}">
                                        <s:text name="needleCartNumOptions2"/>
                                    </c:if>
                                    <c:if test="${supView.numberOfProductionLines > 70}">
                                        <s:text name="needleCartNumOptions3"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--针车数量--%>
                            <h5 class="title">
                                <s:text name="NumberOfSewingMachines"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.numberOfSewingMachines and !(supView.numberOfSewingMachines eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.numberOfSewingMachines < 3}">
                                        ${supView.numberOfSewingMachines}
                                    </c:if>
                                    <c:if test="${supView.numberOfSewingMachines > 2}">
                                        <s:text name="productionLineQuantityOptions3"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--年出口额--%>
                            <h5 class="title">
                                <s:text name="AnnualExportValue"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.annualExportValue and !(supView.annualExportValue eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.annualExportValue < 200}">
                                        <s:text name="exportVolumeOptions1"/>
                                    </c:if>
                                    <c:if test="${supView.annualExportValue > 200 and supView.annualExportValue < 500}">
                                        <s:text name="exportVolumeOptions2"/>
                                    </c:if>
                                    <c:if test="${supView.annualExportValue > 500}">
                                        <s:text name="exportVolumeOptions3"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                    </div>

                    <div class="divide-harf-item">
                        <div class="info-item">
                                <%--员工人数--%>
                            <h5 class="title">
                                <s:text name="NumberOfEmployees"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.numberOfEmployees and !(supView.numberOfEmployees eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.numberOfEmployees < 200}">
                                        <s:text name="exportVolumeOptions1"/>
                                    </c:if>
                                    <c:if test="${supView.numberOfEmployees > 200 and supView.numberOfEmployees < 300}">
                                        <s:text name="exportVolumeOptions2"/>
                                    </c:if>
                                    <c:if test="${supView.numberOfEmployees > 300}">
                                        <s:text name="exportVolumeOptions3"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--出口许可证--%>
                            <h5 class="title">
                                <s:text name="ExportLicense"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.exportLicense and !(supView.exportLicense eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.exportLicense == ''}">
                                        <s:text name="yes"/>
                                    </c:if>
                                    <c:if test="${supView.exportLicense != ''}">
                                        <s:text name="no"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--测试设备--%>
                            <h5 class="title">
                                <s:text name="TestEquipmentAndFacilities"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.testEquipmentAndFacilities and !(supView.testEquipmentAndFacilities eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.testEquipmentAndFacilities == 0}">
                                        <s:text name="yes"/>
                                    </c:if>
                                    <c:if test="${supView.testEquipmentAndFacilities == 1}">
                                        <s:text name="no"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--外贸团队人数--%>
                            <h5 class="title">
                                <s:text name="NumberOfForeignTradeTeams"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.numberOfForeignTradeTeams and !(supView.numberOfForeignTradeTeams eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.numberOfForeignTradeTeams < 10}">
                                        <s:text name="employeesNumOptions1"/>
                                    </c:if>
                                    <c:if test="${supView.numberOfForeignTradeTeams > 10 and supView.numberOfForeignTradeTeams < 15}">
                                        <s:text name="employeesNumOptions2"/>
                                    </c:if>
                                    <c:if test="${supView.numberOfForeignTradeTeams > 15}">
                                        <s:text name="employeesNumOptions3"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="info-item">
                                <%--外贸经验--%>
                            <h5 class="title">
                                <s:text name="YearsOfForeignTradeExperience"/>
                            </h5>
                            <div class="text">
                                <c:if test="${not empty supView.yearsOfForeignTradeExperience and !(supView.yearsOfForeignTradeExperience eq 'null') and !(supView.showName eq null)}">
                                    <c:if test="${supView.yearsOfForeignTradeExperience < 5}">
                                        <s:text name="experienceOptions1"/>
                                    </c:if>
                                    <c:if test="${supView.yearsOfForeignTradeExperience > 5 and supView.yearsOfForeignTradeExperience < 10}">
                                        <s:text name="experienceOptions2"/>
                                    </c:if>
                                    <c:if test="${supView.yearsOfForeignTradeExperience > 10}">
                                        <s:text name="experienceOptions3"/>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                    </div>
                </div>
            </c:if>
            <c:if test="${supView.statusAuth == 0}">
                <div class="enterprise-info-content horizontal-arrange">
                    <div style="margin: 0 auto;">
                        <p style="text-align: center;font-size: 16px;color: #999;">
                            <span style="display: inline-block;width: 15px;height: 15px;line-height: 15px;border: 1px solid;border-radius: 50%;font-size: 12px;">!</span>
                                <%--No Verified Information</p>--%>
                            <s:text name="NoVerifiedInformation"/></p>
                    </div>
                </div>
            </c:if>
            <!-- svs认证信息 - end-->

            <!-- 联系信息 -->
            <div class="enterprise-info-wrap">
                <h3 class="enterprise-info-title">
                    <s:text name="contactInformation"/></h3>
                <div class="enterprise-info-content horizontal-arrange">

                    <div class="grow-more">
                        <div class="info-item">
                            <h5 class="title">
                                <s:text name="supplier.department"/>
                            </h5><!-- 联系人部门 -->
                            <div class="text">
                                <c:if test="${not empty supView.department and !(supView.department eq 'null') and !(supView.showName eq null)}">
                                    ${supView.department}</c:if>
                            </div>
                        </div>

                        <div class="info-item">
                            <h5 class="title">
                                <s:text name="position"/>
                            </h5><!-- 联系人职位 -->
                            <div class="text">
                                <c:if test="${not empty supView.jobTitle and !(supView.jobTitle eq 'null') and !(supView.showName eq null)}">
                                    ${supView.jobTitle }</c:if>
                            </div>
                        </div>

                        <div class="info-item">
                            <h5 class="title">
                                <s:text name="review.name"/>
                            </h5><!-- 联系人 -->
                            <div class="text">
                                <c:if test="${not empty supView.contactsName and !(supView.contactsName eq 'null') and !(supView.showName eq null)}">
                                    ${supView.contactsName}
                                </c:if>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            <!--联系信息 - end-->

            <!-- Contact supplier 表单 start -->
            <div class="contact-supplier">
                <h3 class="title">Contact supplier</h3>
                <div class="supplier-form">
                    <div class="flexSt">
                        <div class="company-logo">
                            <img src="${envConfig.imageBaseUrl}${supView.logo}?x-oss-process=image/resize,m_pad,h_130,w_165"
                                 alt="">
                        </div>
                        <p class="company-name">
                            <c:if test="${not empty supView.name and !(supView.name eq 'null') and !(supView.showName eq null)}">
                                ${supView.showName}
                            </c:if>
                        </p>
                        <div>

                            <c:if test="${not empty supView.grade || !(supView.grade eq 'null') and !(supView.showName eq null)}">
                                <c:if test="${supView.grade == 1}">
                                    <img src="./static/images/ico/icon-svs-yp.png" alt="">
                                </c:if>
                                <c:if test="${supView.grade == 2}">
                                    <img src="./static/images/ico/icon-svs-jp.png" alt="">
                                </c:if>
                                <c:if test="${supView.grade == 3}">
                                    <img src="./static/images/ico/icon-svs-zs.png" alt="">
                                </c:if>
                                svs
                            </c:if>
                        </div>
                    </div>
                    <el-form :model="form" :rules="rules" ref="form" label-width="155px" style="margin-top: 30px;">
                        <el-form-item label="Name of inquiry:" prop="title">
                            <el-row :gutter="15">
                                <el-col :span="4">
                                    <div>I am looking for</div>
                                </el-col>
                                <el-col :span="8">
                                    <el-input v-model="form.title" @blur="paxTrimBlur"></el-input>
                                </el-col>
                                <el-col :span="5">
                                    <div>on shoestp.com</div>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item label="Purchase  Quantity:" prop="quantity">
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
                        <el-form-item label="Extra Request:" prop="extra_request">
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
                                    type="textarea" v-model="form.description"
                                    :autosize="{ minRows: 8, maxRows: 8}" @blur="paxTrimBlur"></el-input>
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
                            <el-button :disabled="flag" type="primary" @click="submitForm('form')">Send inquiry now
                            </el-button>
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
                    flag: false,
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
                            {max: 500, message: 'Enter up to 500 digits', trigger: 'blur'}
                        ],
                        quantity: [
                            {required: true, message: 'Please enter the quantity', trigger: 'blur'},
                            {
                                pattern: util_regular_obj.register.positiveInteger,
                                message: "Please enter the positive integer，and can\'t beyond 10 digits"
                            }
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
                self.$set(self.form, "supplierId", self.pkey)
            },
            methods: {
                paxTrimBlur() {
                    this.form.description = this.form.description.trim();
                    this.form.title = this.form.title.trim();
                },
                // elementui 上传功能 *2 - 删除操作
                handleRemove(file, fileList) {
                    // 清空imgs数组
                    this.imgsToUpload = [];
                    // 删除图片后，将授予的图片地址放入数组
                    $.each(fileList, (i, v) => {
                        console.log(v)
                        this.imgsToUpload.push(v.response.result.url)
                    }
                )

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
                    // if (!sysConfig && !sysConfig.user) {
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
                    if (!sysConfig || !sysConfig.user) {
                        util_function_obj.alertWhenNoLogin(self);
                        return
                    } else {
                        // 登录了
                        if (sysConfig.user.user_type == 1) {
                            self.$alert("Sorry, the supplier cannot submit the form", {
                                confirmButtonText: 'Ok',
                                customClass: "my-custom-element-alert-class fs-content-18",
                                center: true,
                                callback: action => {
                                return
                            }
                        })
                            ;
                            return
                        } else {
                            // 普通用户
                            self.$refs[formName].validate((valid) => {
                                if(valid) {
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
                                        if(res.data.ret == 1
                                )
                                    {
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
                                    }
                                else
                                    {
                                        self.flag = false;
                                        this.$alert(res.data.msg || "Failed to submit the form, please refresh the page and try again", {
                                            confirmButtonText: 'OK',
                                            customClass: "my-custom-element-alert-class fs-content-18",
                                        });
                                    }

                                })
                                .
                                    catch((err) => {
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
                        })
                            ;
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
