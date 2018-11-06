<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="keywords" content="Privacy Policy">
<meta name="description" content="Privacy Policy">
<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
<link href="./static/css/global.css" rel="stylesheet"type="text/css">
<link href="./static/css/global(1).css" rel="stylesheet"type="text/css">
<link href="./static/css/user.css" rel="stylesheet"type="text/css">
<link href="./static/css/style.css" rel="stylesheet"type="text/css">
<link rel="stylesheet" href="./static/css/color.css" type="text/css">
<script type="text/javascript"src="./static/js/jquery-1.7.2.min.js"></script>
<%-- <link rel="stylesheet" href="./static/animate.min.css"> --%>
<%-- <link rel="stylesheet" href="./static/swiper.min.css"type="text/css"> --%>
<link rel="stylesheet"href="./static/css/storeapplication.css" type="text/css">

<script src="./static/js/vue.min.js" type="text/javascript"></script>
<script src="./static/js/axios.min.js" type="text/javascript"></script>
<script src="./static/js/qs.js" type="text/javascript"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script type="text/javascript" src="./static/js/layer.js"></script>
<link rel="stylesheet" href="./static/css/layer.css" type="text/css">
</head>
<style>
	.el-icon-document{
		height:auto!important;
	}
	.tc{
		text-align: center;
	}
	/* 按钮与输入框都贴在一起了 */
	.el-upload{
		margin-bottom: 10px;
	}

	/* 步骤图转文字 */
	.step{
		padding-left: 12px;
	}
  .step .step-content{
	  display: inline-block;
	  position: relative;
		min-width: 145px;
	  height: 34px;
	  line-height: 34px;
	  margin: 0 15px 5px 4px;
	  padding: 0 24px;
	  font-weight: bold;
	  font-size: 13px;
	  color: #444;
		text-align: center;
	  background: #ddd;
	}
  .step .step-content:before{
	  position: absolute;
	  content: "";
	  line-height: 0;
	  top: 0;
	  left: -17px;
	  display: block;
	  border: 17px solid #ddd;
	  border-right-width: 0;
	  border-left-color: transparent;
	}
  .step .step-content:after{
	  position: absolute;
	  content: "";
	  line-height: 0;
	  top: 0;
	  right: -17px;
	  display: block;
	  border: 17px solid #fff;
	  border-right-width: 0;
	  border-left-color: #ddd;
	}

  .step .step-content.active{
	  color: #fff;
	  background: #666;
	}
  .step .step-content.active:before{
	  border: 17px solid #666;
	  border-left-color: transparent;
	}
  .step .step-content.active:after{
	  border-left-color: #666;
	}

	/* 按钮背景色 红转蓝 */
	.xmg-storeapplication111 .xmg-bottom a{
		background: #0050a8;
	}
	/* 上一步按钮为灰色 */
	.xmg-storeapplication111 .xmg-bottom a.previous{
		padding: 0 30px;
    color: #666;
    background: #e5e5e5;
	}
	.xmg-storeapplication333 .xmg-common ul li .el-button span{
		margin-left: 0;
	}
	::-webkit-input-placeholder { /* WebKit, Blink, Edge */    color:   #C4C4C4;}
	:-moz-placeholder { /* Mozilla Firefox 4 to 18 */   color:    #C4C4C4;  }
	::-moz-placeholder { /* Mozilla Firefox 19+ */   color:   #C4C4C4;}
	:-ms-input-placeholder { /* Internet Explorer 10-11 */   color:  #C4C4C4;}
	::-ms-input-placeholder { /* Microsoft Edge */   color: #C4C4C4;

</style>


<body class="lang_en w_1200">
	<%@ include file="/home/template/web-top.jsp" %>
    <%@ include file="/home/template/new-header.jsp" %>
    <div id="app">
		<div class="w1200">
			<div id="lib_user" class="clearfix">
				<div id="lib_user_crumb" class="widget">
					<ul class="crumb_box clearfix">
						<li class="home"><a href="/" title="Home"><s:text name="home"/><i></i></a></li>
						<li class="crumb2 root"><a href="/home/usr_UsrSupplier_supplierEntry" title="supplier entry"><s:text name="ApplyShop"/><i></i></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="tc">
			<div v-show="step==0" class="xmg-storeapplication000" style="display: none;">
				<h2><s:text name="Jurisdiction"/><a @click='toStep1'>&nbsp<s:text name="ApplyShop"/></a>！</h2>
			</div>
		</div>
		<div v-show="step==1" class="w880 xmg-storeapplication111" style="display: none;">
			<%-- <div class="xmg-top" style="background:url(./static/images/xmg-storeapplication111.jpg) no-repeat 0 0;"></div> --%>
			<div class="step">
        <div class="step-content active">
          1. <s:text name="step-content1"/>
        </div>
        <div class="step-content">
          2. <s:text name="step-content2"/>
        </div>
        <div class="step-content">
          3. <s:text name="step-content3"/>
        </div>
        <div class="step-content">
          4. <s:text name="step-content4"/>
        </div>
			</div>
			<div class="xmg-common">
				<div class="xmg-t"><h4><s:text name="FillCompanyInformation"/></h4></div>
				<h5><s:text name="CompanyInformation"/></h5>
				<ul style="border-top:0;">
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="purchase.company"/>：</p><input v-model="bean.name">
					</li>
					<li class="clearfloat">
						<p><s:text name="supplier.companytype"/>：</p><input v-model="bean.companyType" class="" placeholder="<s:text name='supplier.placeholder.company'/>">
					</li>
					<li class="clearfloat">
						<p><s:text name="supplier.companynature"/>：</p><input v-model="bean.companyNature" class="" placeholder="<s:text name="supplier.placeholder.nature"/>">
					</li>
					<li class="clearfloat">
						<p>
							<i>*</i> <s:text name="supplier.category"/>：
						</p>
						<select v-model='bean.category' class="w110">
							<option style="display:none;" disabled selected value="-1"><s:text name="CategorySelect"/></option>
							<option v-for="category in categorys" :key="category.id" :value="category.id">{{category.name}}</option>
                        </select>
					</li>
					<li class="clearfloat">
						<p><s:text name="CompanyOfficialWebsiteAddress"/>：</p><input v-model="bean.website">
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="CompanyAddress"/>：</p>
						<select v-model="bean.country" class="w110">
							<option style="display:none;" disabled selected value="-1"><s:text name="CountrySelect"/></option>
							<option v-for="(country,id) in countrys" :key="id" :value="id">{{country.name}}</option>
						</select>
						<select v-model="bean.province" class="w110">
							<option style="display:none;" disabled selected value="-1"><s:text name="ProvinceSelect"/></option>
							<template v-if="bean.country!=-1">
							<option v-for="(province,id) in countrys[bean.country].provinces" :key="id" :value="id">{{province.name}}</option>
							</template>
						</select>
					</li>
					<li class="clearfloat">
						<p>
							<i>*</i><s:text name="supplier.city"/>：
						</p>
						<input v-model="bean.city" style="width: 136px"/>
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="CompanyDetailedAddress"/>：</p><input v-model="bean.companyAddr" class="w362"/>
					</li>
					<li class="clearfloat">
						<p><s:text name="Telephone"/>：</p><input v-model="bean.telephone"/>
					</li>
					<li class="clearfloat">
						<p><i>*</i> <s:text name="consult.email"/>：</p><input v-model="bean.email">
					</li>
					<li class="clearfloat">
						<p><s:text name="Fax"/>：</p><input v-model="bean.fax">
					</li>
				</ul>

				<ul>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="supplier.registeredcapital"/>：</p><input v-model="bean.registeredCapital"><span><s:text name="supplier-entry.Ten_Thousand_Yuan"/></span>
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="supplier-entry.Unified_Social_Credit_Code"/>：</p><input v-model="bean.creditCode"><span style="width:44.5%;display:inline-block;float:left;"><s:text name="PleaseCreditCode"/></span>
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="LegalRepresentative"/>：</p><input v-model="bean.entity">
					</li>
					<li class="clearfloat">
						<p><i>*</i>   <s:text name="ValidityPeriodOfBusinessLicense"/>：</p>
						<input v-model="bean.businessLicenseBeginTime" class="w130" placeholder="2018/01/01"><b>-</b>
						<input v-model="bean.businessLicenseEndTime" class="w130" placeholder="2018/01/01">
						<span><input style="position: relative;top:2px;" type="checkbox" v-model="bean.businessLicenseIsSecular"> <s:text name="Long_term"/></span>
					</li>
					<li class="clearfloat">
						<p><s:text name="supplier.taxpayertype"/>：</p><input v-model="bean.taxpayerType" class="" placeholder="<s:text name='supplier.placeholder.Taxpayer'/>">
					</li>
				</ul>
			</div>
			<div class="xmg-bottom tc">
				<a @click="toStep2"><s:text name="supplier-entry.Next1"/></a>
			</div>
		</div>
		<div v-show="step==2" class="w880 xmg-storeapplication111" style="display: none;">
			<%-- <div class="xmg-top" style="background:url(./static/images/xmg-storeapplication222.jpg) no-repeat 0 0;"></div> --%>
			<div class="step">
        <div class="step-content">
          1. <s:text name="step-content1"/>
        </div>
        <div class="step-content active">
          2. <s:text name="step-content2"/>
        </div>
        <div class="step-content">
          3. <s:text name="step-content3"/>
        </div>
        <div class="step-content">
          4. <s:text name="step-content4"/>
        </div>
			</div>
			<div class="xmg-common">
				<div class="xmg-t"><h4><s:text name="FillInOperationInformation"/></h4></div>

				<h5><s:text name="OperationInformation"/></h5>
				<ul style="border-top:0;">

					<li class="clearfloat">
						<p><i>*</i>  <s:text name="supplier.contacts"/>：</p><input v-model="bean.contacts">
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="Global.Telephone_Number"/>：</p><input v-model="bean.phone">
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="Settlement_Account"/>：</p><input v-model="bean.settlementBank">
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="Bank_Account"/>：</p><input v-model="bean.bankAccount">
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="Bank_Branch_Name"/>：</p><input v-model="bean.bankBranch">
					</li>
					<li class="clearfloat">
						<p><i>*</i>  <s:text name="Bank_Branch_Branch_Location"/>：</p>
						<select v-model="bean.bankCountry" class="w110">
							<option style="display:none;" disabled selected value="-1"><s:text name="countryOfBank"/></option>
							<option v-for="(country,id) in countrys" :key="id" :value="id">{{country.name}}</option>
						</select>
						<select v-model="bean.bankProvince" class="w110">
							<option style="display:none;" disabled selected value="-1"><s:text name="provinceOfBank"/></option>
							<template v-if="bean.bankCountry!=-1">
							<option v-for="(province,id) in countrys[bean.bankCountry].provinces" :key="id" :value="id">{{province.name}}</option>
							</template>
						</select>
					</li>
				</ul>

			</div>
			<div class="xmg-bottom tc">
				<a @click="step=1" class="previous"><s:text name="Step"/></a>
				<a @click="toStep3" class="next"><s:text name="nextCompanyQualification"/></a>
			</div>
		</div>
		<div v-show="step==3" class="w880 xmg-storeapplication333" style="display: none;">
			<%-- <div class="xmg-top" style="background:url(./static/images/xmg-storeapplication333.jpg) no-repeat 0 0;"></div> --%>
			<div class="step">
        <div class="step-content">
          1. <s:text name="step-content1"/>
        </div>
        <div class="step-content">
          2. <s:text name="step-content2"/>
        </div>
        <div class="step-content active">
          3. <s:text name="step-content3"/>
        </div>
        <div class="step-content">
          4. <s:text name="step-content4"/>
        </div>
			</div>
			<div class="xmg-common">
				<div class="xmg-t"><h4><s:text name="UploadQualificationPhotos"/></h4></div>

				<h5><s:text name="AptitudeUpload"/></h5>
				<ul style="border-top:0;">
					<h3><i class="dengpao"></i><s:text name="AptitudeUpload0"/></h3>
					<h3><i class="dengpao"></i><s:text name="AptitudeUpload1"/></h3>
					<h2><s:text name="BasicQualifications"/></h2>
					<li class="clearfloat">
						<p>
							<i>*</i><s:text name="EnterpriseVoucher"/><a href="javascript:void(0)"><s:text name="LookAtTheModel"/></a>
							<el-upload accept="image/jpeg,image/gif,image/png" :before-upload="onBeforeUpload" action="/home/usr_UsrSupplier_upload" :on-success="uploadCertPhoto" :on-remove="removeCertPhoto" :limit="1">
							  <el-button v-show="typeof(certPhoto.name)=='undefined'" size="small" type="primary"><s:text name="ClickUpload"/></el-button>
							</el-upload>
						</p>
					</li>
					<li class="clearfloat">
						<p>
							<i>*</i><s:text name="IDCardPhoto0"/><a href="javascript:void(0)"><s:text name="LookAtTheModel"/></a>
							<s:text name="IDCard"/>：<input v-model="bean.idCard"/>
						</p>
						<p>
							<s:text name="supplier.idcardfrontphoto"/>：
							<el-upload accept="image/jpeg,image/gif,image/png" :before-upload="onBeforeUpload" action="/home/usr_UsrSupplier_upload" :on-success="uploadIdCardFrontPhoto" :on-remove="removeIdCardFrontPhoto" :limit="1">
							  <el-button v-show="typeof(idCardFrontPhoto.name)=='undefined'" size="small" type="primary"><s:text name="ClickUpload"/></el-button>
							</el-upload>
	                    </p>
	                    <p>
							<s:text name="supplier.idcardbackphoto"/>：
							<el-upload action="/home/usr_UsrSupplier_upload" :before-upload="onBeforeUpload" :on-success="uploadIdCardBackPhoto" :on-remove="removeIdCardBackPhoto" :limit="1">
							  <el-button v-show="typeof(idCardBackPhoto.name)=='undefined'" size="small" type="primary"><s:text name="ClickUpload"/></el-button>
							</el-upload>
	                    </p>
					</li>
					<li class="clearfloat">
						<p>
							<s:text name="IDCardPhoto1"/><a href="javascript:void(0)"><s:text name="LookAtTheModel"/></a>
							<s:text name="IDCard"/>：<input v-model="bean.operateIdCard"/>
						</p>
						<p>
							<s:text name="supplier.idcardfrontphoto"/>：
							<el-upload action="/home/usr_UsrSupplier_upload" :before-upload="onBeforeUpload"  :on-success="uploadContactsIdCardFrontPhoto" :on-remove="removeContactsIdCardFrontPhoto" :limit="1">
							  <el-button v-show="typeof(contactsIdCardFrontPhoto.name)=='undefined'" size="small" type="primary"><s:text name="ClickUpload"/></el-button>
							</el-upload>
	                    </p>
	                    <p>
							<s:text name="supplier.idcardbackphoto"/>：
							<el-upload action="/home/usr_UsrSupplier_upload" :before-upload="onBeforeUpload" :on-success="uploadContactsIdCardBackPhoto" :on-remove="removeContactsIdCardBackPhoto" :limit="1">
							  <el-button v-show="typeof(contactsIdCardBackPhoto.name)=='undefined'" size="small" type="primary"><s:text name="ClickUpload"/></el-button>
							</el-upload>
	                    </p>

					</li>

				</ul>

			</div>
			<div class="xmg-bottom tc">
				<a @click="step=2" class="previous"><s:text name="Step"/></a>
				<a @click="submit" class="next"><s:text name="PreservationInformation"/></a>
			</div>
		</div>
		<div>
		</div>

		<!-- 提心入驻申请已提交 -->
		<div v-show="submitsuccess" class="xmg-tc" style="display: none;">
			<div class="xmg-tcbg"></div>
			<div class="xmg-tcbox">
				<div @click="step=4;submitsuccess=false;" class="xmg-tcclose">×</div>
				<p><i class="dagou"></i><s:text name="Submission"/></p>
				<div class="xmg-bottom tc">
					<a @click="step=4;submitsuccess=false;"><s:text name="cpop_t4"/></a>
				</div>
			</div>
		</div>

		<div v-show="step==4" class="w880 xmg-storeapplication111" style="display: none;">
			<%-- <div class="xmg-top" style="background:url(./static/images/xmg-storeapplication444.jpg) no-repeat 0 0;"></div> --%>
			<div class="step">
        <div class="step-content">
          1. <s:text name="step-content1"/>
        </div>
        <div class="step-content">
          2. <s:text name="step-content2"/>
        </div>
        <div class="step-content">
          3. <s:text name="step-content3"/>
        </div>
        <div class="step-content active">
          4. <s:text name="step-content4"/>
        </div>
			</div>
			<div class="xmg-common">
				<div class="xmg-t"><h4><s:text name="QualificationExamination"/></h4></div>

				<ul style="border-top:0;">

					<li class="clearfloat">
						<h2><s:text name="UnderReview"/></h2>
						<h3><s:text name="questions"/>：0577-85887575</h3>
					</li>
				</ul>

			</div>
			<div class="xmg-bottom tc">
				<a @click="goBack"><s:text name="goBack"/></a>
			</div>
		</div>
	</div>

	<%@ include file="/home/template/new-foot.jsp" %>
	<div id="hj_top" style="opacity: 0; bottom: 10%;">
		<img src="./static/images/hj_top.png">
	</div>	﻿
</body>
<script>
	var baseUrl = "";
	var bean = {
			country:-1,
			province:-1,
			category:-1,
			bankCountry:-1,
			bankProvince:-1,
			businessLicenseIsSecular:false
		};
	var vm = new Vue({
		el:"#app",
		data : {
			step : ${entryStep},
			submitsuccess : false,
			bean : bean,
			countrys :{},
			categorys:{},
			certPhoto:{},
			idCardFrontPhoto:{},
			idCardBackPhoto:{},
			contactsIdCardFrontPhoto:{},
			contactsIdCardBackPhoto:{}
		},
		methods:{
			uploadCertPhoto : function(response, file, fileList) {
				file.url=response.result.url;
				file.name=response.result.originalName
				this.certPhoto=file;
			},
			onBeforeUpload :function(file){
				if (file.type !== 'image/jpeg'&&file.type !== 'image/gif'&&file.type !== 'image/png') { 
				layer.msg('<s:text name="img_upload"/>', {icon: 2});
				 return false;	
				}
				return true;
				}
			,
			removeCertPhoto : function(file, fileList) {
				this.certPhoto={};
			},
			uploadIdCardFrontPhoto : function(response, file, fileList) {
				file.url=response.result.url;
				file.name=response.result.originalName
				this.idCardFrontPhoto=file;
			},
			removeIdCardFrontPhoto : function(file, fileList) {
				this.idCardFrontPhoto={};
			},
			uploadIdCardBackPhoto : function(response, file, fileList) {
				file.url=response.result.url;
				file.name=response.result.originalName
				this.idCardBackPhoto=file;
			},
			removeIdCardBackPhoto : function(file, fileList) {
				this.idCardBackPhoto={};
			},
			uploadContactsIdCardFrontPhoto : function(response, file, fileList) {
				file.url=response.result.url;
				file.name=response.result.originalName
				this.contactsIdCardFrontPhoto=file;
			},
			removeContactsIdCardFrontPhoto : function(file, fileList) {
				this.contactsIdCardFrontPhoto={};
			},
			uploadContactsIdCardBackPhoto : function(response, file, fileList) {
				file.url=response.result.url;
				file.name=response.result.originalName
				this.contactsIdCardBackPhoto=file;
			},
			removeContactsIdCardBackPhoto : function(file, fileList) {
				this.contactsIdCardBackPhoto={};
			},
			toStep1:function() {
				this.step=1
			},
			toStep2:function() {
				console.log("toStep2")
				if (this.empty(this.bean.name))
					return this.error("<s:text name='NotNullTip-Company'/>");
				if (this.bean.category==-1)
					return this.error("<s:text name='NotNullTip-Supplier'/>")
				if (this.bean.country==-1)
					return this.error("<s:text name='NotNullTip-ComAddress'/>");
				if (this.bean.province==-1)
					return this.error("<s:text name='NotNullTip-ComAddress'/>");
				if (this.empty(this.bean.city))
					return this.error("<s:text name='NotNullTip-City'/>");
				if (this.empty(this.bean.companyAddr))
					return this.error("<s:text name='NotNullTip-deAdr'/>");
				if (this.empty(this.bean.email))
					return this.error("<s:text name='NotNullTip-mail'/>");
				if (!this.email(this.bean.email))
					return this.error("<s:text name='format-mail'/>");
				if (this.empty(this.bean.registeredCapital))
					return this.error("<s:text name='NotNullTip-reMoney'/>");
				if (this.empty(this.bean.creditCode))
					return this.error("<s:text name='NotNullTip-soCode'/>");
				if (this.empty(this.bean.entity))
					return this.error("<s:text name='NotNullTip-lePerson'/>");
				if (this.empty(this.bean.businessLicenseBeginTime))
					return this.error("<s:text name='NotNullTip-busVal'/>");
				if (!this.date(this.bean.businessLicenseBeginTime))
					return this.error("<s:text name='format-license'/>");
				if (this.bean.businessLicenseIsSecular==false){
					if (this.empty(this.bean.businessLicenseEndTime))
						return this.error("<s:text name='NotNullTip-busVal'/>");
					if (!this.date(this.bean.businessLicenseEndTime))
						return this.error("<s:text name='NotNullTip-format-license'/>");
				}
				this.step=2;
			},
			toStep3:function() {
				console.log("toStep3")
				if (this.empty(this.bean.contacts))
					return this.error("<s:text name='NotNullTip-contacts'/>");
				if (this.empty(this.bean.phone))
					return this.error("<s:text name='NotNullTip-phoneNumber'/>");
				if (this.empty(this.bean.settlementBank))
					return this.error("<s:text name='NotNullTip-bank'/>");
				if (this.empty(this.bean.bankAccount))
					return this.error("<s:text name='NotNullTip-accountNum'/>");
				if (this.empty(this.bean.bankBranch))
					return this.error("<s:text name='NotNullTip-bankName'/>");
				if (this.bean.bankCountry==-1)
					return this.error("<s:text name='NotNullTip-bankLocation'/>");
				if (this.bean.bankProvince==-1)
					return this.error("<s:text name='NotNullTip-bankLocation'/>");
				this.step=3;
			},
			submit:function() {
				console.log("submit")
 				if (this.empty(this.bean.idCard))
					return this.error("<s:text name='NotNullTip-CorporateId'/>");
				/* if (this.empty(this.bean.operateIdCard))
					return this.error("<s:text name='NotNullTip-operationId'/>"); */
				if (this.empty(this.certPhoto.url))
					return this.error("<s:text name='NotNullTip-uplbusCredentials'/>");
				if (this.empty(this.idCardFrontPhoto.url))
					return this.error("<s:text name='NotNullTip-NotNullTip-upllegalId'/>");
				if (this.empty(this.idCardBackPhoto.url))
					return this.error("<s:text name='NotNullTip-upllegalIdB'/>");
				/* if (this.empty(this.contactsIdCardFrontPhoto.url))
					return this.error("<s:text name='NotNullTip-uploperaID'/>");
				if (this.empty(this.contactsIdCardBackPhoto.url))
					return this.error("<s:text name='NotNullTip-uploperaIDB'/>"); */

				this.bean.certPhoto = this.certPhoto.url;
				this.bean.idCardFrontPhoto = this.idCardFrontPhoto.url;
				this.bean.idCardBackPhoto = this.idCardBackPhoto.url;
				this.bean.contactsIdCardFrontPhoto = this.contactsIdCardFrontPhoto.url;
				this.bean.contactsIdCardBackPhoto = this.contactsIdCardBackPhoto.url;
				var data = {view:this.bean}
				axios.post(baseUrl+"/home/usr_UsrSupplier_applyEntry",Qs.stringify(data, {allowDots: true}))
					.then((response)=>{
						if(response.data.ret==1) {
							console.log("show tips")
							this.submitsuccess=true;
						} else if(response.data.msg){
							layer.msg(getMessage(response.data.msg), {icon: 2});
						}
					})
			},
			goBack:function() {
				//返回用户中心还是商城中心
				console.log("go back");
				location.href = "/home/usr_UsrPurchase_userIndex";
			},
			loadProvince:function(val) {
				for(var key in this.countrys[val].provinces) {
					return;
				}
				axios.post(baseUrl+"/home/plt_PltProvince_list",Qs.stringify({countryId:val}))
					.then((response)=>{
						var provinces = {};
	                	if(response.data.ret == 1) {
	                		response.data.result.forEach(function(province) {
	                			provinces[province.id]={};
	                			provinces[province.id].name=province.name;
	                			provinces[province.id].shortName=province.shortName;
	                		})
	                		this.countrys[val].provinces = provinces;
						}
					})
			},
			empty:function(str) {
				if (typeof(str)=="undefined" || str.trim() == "" || str == null)
					return true;
				else
					return false;
			},
			email:function(str) {
				//验证邮箱
				return new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$").test(str);
			},
			date:function(str) {
				//验证营业执照有效期
				var reg = new RegExp("^(?:(?:([0-9]{4}/(?:(?:0?[1,3-9]|1[0-2])/(?:29|30)|((?:0?[13578]|1[02])/31)))|([0-9]{4}/(?:0?[1-9]|1[0-2])/(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))/0?2/29))))$");
				return reg.test(str);
			},
			error:function(str) {
				layer.msg(str, {icon: 2});
			}
		},
		computed:{
		},
		watch:{
			"bean.country":function(val) {
				this.loadProvince(val);
				this.bean.province = -1;
			},
			"bean.bankCountry":function(val) {
				this.loadProvince(val);
			},
			"bean.businessLicenseBeginTime":function(val) {
				if(val!="")
					this.bean.businessLicenseIsSecular=false;
			},
			"bean.businessLicenseEndTime":function(val) {
				if(val!=""&&val!="长期")
					this.bean.businessLicenseIsSecular=false;
			},
			"bean.businessLicenseIsSecular":function(val) {
				if(val)
					this.bean.businessLicenseEndTime="长期";
				else
					this.bean.businessLicenseEndTime="";
			}
		},
		created:function(){
			axios.post(baseUrl+"/home/plt_PltCountry_list",{})
				.then((response)=>{
					var countrys = {};
                	if(response.data.ret == 1) {
                		response.data.result.forEach(function(country) {
                			countrys[country.id]={};
                			countrys[country.id].name=country.name;
                			countrys[country.id].shortName=country.shortName;
                			countrys[country.id].provinces = {};
                		})
                		this.countrys = countrys;
					}
                })
			axios.post(baseUrl+"/home/usr_UsrSupplierCategory_listTop",{})
				.then((response)=>{
                	if(response.data.ret == 1) {
                		this.categorys = response.data.result;
					}
                })
		}
	})
</script>
</html>
