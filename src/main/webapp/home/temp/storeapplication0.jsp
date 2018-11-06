
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0053)https://www.shoestp.com/art/privacy-policy_a0065.html -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<link rel="shortcut icon"href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
<meta name="keywords" content="Privacy Policy">
<meta name="description" content="Privacy Policy">
<title>资质审核中</title>
<link href="./static/css/global.css" rel="stylesheet"type="text/css">
<link href="./static/css/global(1).css" rel="stylesheet"type="text/css">
<link href="./static/css/user.css" rel="stylesheet"type="text/css">
<link href="./static/css/style.css" rel="stylesheet"type="text/css">
<link rel="stylesheet" href="./static/css/color.css" type="text/css">
<script type="text/javascript"src="./static/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="./static/animate.min.css">
<link rel="stylesheet" href="./static/swiper.min.css"type="text/css">
<link rel="stylesheet"href="./static/css/storeapplication.css" type="text/css">
<script type="text/javascript" src="./static/js/layer.js"></script>
<link rel="stylesheet" href="./static/css/layer.css" type="text/css">
</head>
<body class="lang_en w_1200">

	<div class="xmg-tc" style="display: none;">
		<div class="xmg-tcbg"></div>
		<div class="xmg-tcbox">
			<div class="xmg-tcclose">×</div>
			<p>
				<i class="dagou"></i> 入驻申请已提交！
			</p>
			<div class="xmg-bottom">
				<a href="/home/storeapplication4.jsp">确定</a>
			</div>
		</div>
	</div>

	<%@ include file="/home/template/web-top.jsp" %>
    <%@ include file="/home/template/new-header.jsp" %>
	

	<form action="" id="commit">
		<div id="step1">
			<!-- ******************************************************************申请开店 -->
			<div class="w1200">
				<div id="lib_user" class="clearfix">
					<div id="lib_user_crumb" class="widget">
						<ul class="crumb_box clearfix">
							<li class="home"><a href="/" title="Home">首页<i></i></a></li>
							<li class="crumb2 root"><a href="/account/"
								title="My Account">申请开店<i></i></a></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="xmg-storeapplication000">
				<h2>
					您还未拥有开店权限，请先完成<a class="step1">申请开店</a>！
				</h2>
			</div>
		</div>
		<div id="step2" style="display: none;">
			<!-- ******************************************************************填写公司信息 -->
			<div class="w880 xmg-storeapplication111">
				<div class="xmg-top"
					style="background: url(./static/images/xmg-storeapplication111.jpg) no-repeat 0 0;"></div>
				<div class="xmg-common">
					<div class="xmg-t">
						<h4>填写公司信息</h4>
					</div>

					<h5>公司信息</h5>
					<ul style="border-top: 0;">

						<li>
							<p>
								<i>*</i> 公司名称：
							</p> <input type="" name="bean.name" id="name" value="" />
						</li>
						<li>
							<p>公司性质：</p> <input type="" name="bean.companyNature"
							id="companyNature" value="" class="w130" placeholder="个人独立企业" />
						</li>
						<li>
							<p>
								<i>*</i> 供应商分类：
							</p><select name='bean.category' id="usrSupplierCategory" class="w110">
								<option>请选择</option>
								<c:forEach items="${usrSupplierCategory}" var="category">
									<option class="category" id="category" name="bean.category" value="${category.pkey}">${category.name}</option>
								</c:forEach>
						</select> 
						</li>
						<li>
							<p>公司官网地址：</p> <input type="" name="bean.website" id="website"
							value="" />
						</li>
						<li>
							<p>
								<i>*</i> 公司所在地：
							</p> <select name='bean.country' id="country" class="w110">
								<option>请选择</option>
								<c:forEach items="${pltCountry}" var="state">
									<option class="state" id="state" name="state" value="${state.pkey}">${state.name}</option>
								</c:forEach>
						</select> 
						<select name='bean.province' id="province" class="w110">
								<option>请选择</option>
						</select>
							<!--  <select name='bean.city' id="city" class="w110">
							 	
						</select> -->
						</li>
						<li>
							<p>
								<i>*</i>城市：
							</p>
							<input type="text" id="bankCity" name="bankCity" value="" style="width: 136px"/>
						</li>
						<li>
							<p>
								<i>*</i> 公司详细地址：
							</p> <input type="" name="bean.companyAddr" id="companyAddr" value=""
							class="w362" />
						</li>
						<li>
							<p>固定电话：</p> <input type="" name="bean.telephone" id="telephone"
							value="" />
						</li>
						<li>
							<p>
								<i>*</i> 电子邮箱：
							</p> <input type="" name="bean.email" id="email" value="" />
						</li>
						<li>
							<p>传真：</p> <input type="" name="bean.fax" id="fax" value="" />
						</li>
					</ul>

					<ul>
						<li>
							<p>注册资金：</p> <input type="" name="bean.registeredCapital"
							id="registeredCapital" value="" /><span>万元（人民币）</span>
						</li>
						<li>
							<p>
								<i>*</i> 统一社会信用代码：
							</p> <input type="" name="bean.creditCode" id="creditCode" value="" /><span>请输入18位数字或字母组成的统一社会信用代码</span>
						</li>
						<li>
							<p>
								<i>*</i> 法定代表人姓名：
							</p> <input type="" name="bean.entity" id="entity" value="" />
						</li>
						<li>
							<p>
								<i>*</i> 营业执照有效期：
							</p> <input type="" name="bean.businessLicenseBeginTime" id="businessLicenseBeginTime" value="" class="w130" /><b>-</b> 
							<input type="" name="bean.businessLicenseEndTime" id="businessLicenseEndTime" value="" class="w130" />
							<span><input type="checkbox" name="bean.businessLicenseIsSecular"
								id="businessLicenseIsSecular" value="0"> 长期</span>
						</li>
						<li>
							<p>纳税人类型：</p> <input type="" name="bean.taxpayerType"
							id="taxpayerType" value="" class="w130" placeholder="一般纳税人" />
						</li>

					</ul>
				</div>
				<div class="xmg-bottom">
					<a class="step2">下一步，填写运营信息</a>
				</div>
			</div>
		</div>
		<div id="step3" style="display: none;">
			<!-- ******************************************************************填写运营信息 -->
			<div class="w880 xmg-storeapplication222">
				<div class="xmg-top"
					style="background: url(./static/images/xmg-storeapplication222.jpg) no-repeat 0 0;"></div>
				<div class="xmg-common">
					<div class="xmg-t">
						<h4>填写运营信息</h4>
					</div>

					<h5>运营信息</h5>
					<ul style="border-top: 0;">

						<li>
							<p>
								<i>*</i> 联系人：
							</p>
							<input type="" name="bean.contacts" id="contacts" value="" />
						</li>
						<li>
							<p>
								<i>*</i> 手机号码：
							</p>
							<input type="" name="bean.phone" id="phone" value="" />
						</li>
						<li>
							<p>
								<i>*</i> 结算开户行：
							</p>
							<input type="" name="bean.settlementBank" id="settlementBank"
							value="" />
						</li>
						<li>
							<p>
								<i>*</i> 银行账号：
							</p>
							<input type="" name="bean.bankAccount" id="bankAccount" value="" />
						</li>
						<li>
							<p>
								<i>*</i> 开户银行支行名称：
							</p>
							<input type="" name="bean.bankBranch" id="bankBranch" value="" />
						</li>
						<li>
							<p>
								<i>*</i> 开户银行支行所在地：
							</p> <select name='bean.bankCountry' id="bankCountry" class="w110">
							<option>请选择</option>
							<c:forEach items="${pltCountry}" var="bankState">
									<option class="bankState" id="bankState" name="bankState" value="${bankState.pkey}">${bankState.name}</option>
								</c:forEach>
							</select>
							<select name='bean.bankProvince' id="bankProvince" class="w110">
								<option>请选择</option>
							</select>
						</li>
					</ul>

				</div>
				<div class="xmg-bottom">
					<a class="step02">上一步</a> <a class="step3">下一步，上传公司资质</a>
				</div>
			</div>
		</div>
		<div id="step4" style="display: none;">
			<!-- ******************************************************************上传公司资质 -->
			<div class="w880 xmg-storeapplication333">
				<div class="xmg-top"
					style="background: url(/home/static/images/xmg-storeapplication333.jpg) no-repeat 0 0;"></div>
				<div class="xmg-common">
					<div class="xmg-t">
						<h4>上传资质照片</h4>
					</div>

					<h5>资质上传</h5>
					<ul style="border-top: 0;">
						<h3>
							<i class="dengpao"></i>
							所有资质必须清晰可辩并加盖贵司红章/彩章（即在资质复印件上加盖贵司自己的红章，再扫描或拍照上传）
						</h3>
						<h3>
							<i class="dengpao"></i>
							以下所需要上传电子版资质仅支持JPG、JPEG、GIF、PNG格式的图片，大小不超过2M，且必须加盖企业彩色公章。
						</h3>
						<h2>基本资质</h2>
						<li>
							<p>
								<i>*</i> 企业凭证（需加盖红章）<a href="">查看范本 </a> <input type="file"
									name="bean.certPhoto" id="certPhoto" value="" />
							</p>
						</li>
						<li>
							<p>
								<i>*</i> 法人身份证正反面复印件或护照（需加盖红章）<a href="">查看范本 </a> 身份证/护照号码：<input
									type="" name="bean.idCard" id="idCard" value="" /> <input
									type="file" name="bean.idCardFrontPhoto" id="idCardFrontPhoto"
									value="" /><input type="file" name="" id="idCardBackPhoto"
									value="idCardBackPhoto" />
							</p>
						</li>
						<li>
							<p>
								<i>*</i> 运营负责人身份证正反面复印件（需加盖红章）<a href="">查看范本 </a> 身份证号码： <input
									type="" name="bean.operateIdCard" id="operateIdCard" value="" />
								<input type="file" name="bean.contactsIdCardFrontPhoto"
									id="contactsIdCardFrontPhoto" value="" /><input type="file"
									name="contactsIdCardBackPhoto" id="contactsIdCardBackPhoto"
									value="" />
							</p>
						</li>

					</ul>

				</div>
				<div class="xmg-bottom">
					<a class="step03">上一步</a> <a class="next">保存资料</a>
				</div>
			</div>
		</div>
	</form>
	<div id="step5" style="display: none;">
		<!-- ******************************************************************资质审核中 -->
		<div class="w880 xmg-storeapplication444">
			<div class="xmg-top"
				style="background: url(./static/images/xmg-storeapplication444.jpg) no-repeat 0 0;"></div>
			<div class="xmg-common">
				<div class="xmg-t">
					<h4>资质审核中</h4>
				</div>

				<ul style="border-top: 0;">

					<li>
						<h2>您的入驻申请正在审核中，请耐心等待！</h2>
						<h3>
							如有疑问，请联系客服：****-******
							</h2>
					</li>
				</ul>

			</div>
			<div class="xmg-bottom">
				<a href="/home/">返回</a>
				<!-- *****************************************************返回sign in**************************************************** -->
			</div>
		</div>
	</div>

<%@ include file="/home/template/new-foot.jsp" %>
	﻿
	
	<div id="hj_top" style="opacity: 0; bottom: 10%;">
		<img src="./static/images/hj_top.png">
	</div>
	﻿


</body>
<script type="text/javascript">
	$(function() {
		$("#country option:first").attr("selected","selected");
		// 初始化省市区
		initAddress();
		// 更改省份后的操作
		$("select[name='province']").change(function() {
			var provCode = $("select[name='province']").val();
			getCity(provCode);
		});

		// 更改城市后的操作
		$("select[name='city']").change(function() {
			var cityCode = $("select[name='city']").val();
			getArea(cityCode);
		});
	});

	function initAddress() {
		var firstProvCode;
		// ajax请求所有省份
		$.get("/airticleMgr/address", {
			method : "initProvince"
		}, function(data) {
			$.each(data, function(i, d) {
				$("select[name='province']").append(
						"<option value='"+d.provinceCode+"'>" + d.provinceName
								+ "</option>");
			});
			// 获取第一个省份的code
			firstProvCode = data[0].provinceCode;
			// 根据第一个省份code获取对应城市列表
			getCity(firstProvCode);
		}, 'json');
	}

	//获取对应城市列表（里面包括获取第一个城市的区县列表）
	function getCity(provCode) {
		var firstCityCode;
		// ajax请求所有市级单位
		$.get("/airticleMgr/address", {
			method : "getCity",
			provCode : provCode
		}, function(data) {
			// 先清空城市下拉框
			$("select[name='city']").empty();
			$.each(data, function(i, d) {
				$("select[name='city']").append(
						"<option value='"+d.cityCode+"'>" + d.cityName
								+ "</option>");
			});
			// 获取第一个城市的code
			firstCityCode = data[0].cityCode;
			// 根据第一个城市code获取对应区县列表
			getArea(firstCityCode);
		}, 'json');
	}

	function getArea(cityCode) {
		// ajax请求所有区县单位
		$.get("/airticleMgr/address", {
			method : "getArea",
			cityCode : cityCode
		}, function(data) {
			// 先清空区县下拉框
			$("select[name='area']").empty();
			$.each(data, function(i, d) {
				$("select[name='area']").append(
						"<option value='"+d.areaCode+"'>" + d.areaName
								+ "</option>");
			});
		}, 'json');
	}
	$(".next").on("click", function() {
		var certPhoto = $("#certPhoto").val();//企业凭证
		var idCard = $("#idCard").val();//法人身份证号码
		var idCardFrontPhoto = $("#idCardFrontPhoto").val();//法人身份证正面
		var idCardBackPhoto = $("#idCardBackPhoto").val();//法人身份证反面
		var operateIdCard = $("#operateIdCard").val();//运营负责人身份证号码
		var contactsIdCardFrontPhoto = $("#contactsIdCardFrontPhoto").val();//运营负责人身份证正面
		var contactsIdCardBackPhoto = $("#contactsIdCardBackPhoto").val();//运营负责人身份证反面
		if (isBlank(certPhoto)) {
			layer.msg('企业凭证不能为空!', {icon: 2});
		} else if (isBlank(idCard)) {
			layer.msg('法人身份证号码不能为空!', {icon: 2});
		} else if (isBlank(idCardFrontPhoto)) {
			layer.msg('法人身份证正面不能为空!', {icon: 2});
		} else if (isBlank(operateIdCard)) {
			layer.msg('运营身份证号码不能为空!', {icon: 2});
		} else if (isBlank(contactsIdCardFrontPhoto)) {
			layer.msg('运营负责人身份证正反面复印件为空!', {icon: 2});
		} else if (isBlank(contactsIdCardBackPhoto)) {
			layer.msg('运营负责人身份证正反面复印件不能为空!', {icon: 2});
		} else {
			console.log($("#commit").serialize());
			$('.xmg-tc').show();
			$.ajax({
				url : "/home/usr_UsrSupplier_enterSup",
				data : $("#commit").serialize(),
				type : "post",
				dataType : "josn",
			})
		}
	})
	$('.xmg-tc .xmg-tcbox .xmg-tcclose').click(function() {
		$('.xmg-tc').hide();
	})

	$(".step1").on("click", function() {
		$("#step1").hide();
		$("#step2").show();
	})
	$(".step2")
			.on(
					"click",
					function() {
						var name = $("#name").val();//公司名称
						var category= $("#category").val();//下拉选供应商分类
						var country = $("#country").val();//公司下拉国家
						var Province = $("#Province").val();//公司下拉选省份
						var bankCity = $("#bankCity").val();//城市填写
						var companyAddr = $("#companyAddr").val();//公司详细地址
						var email = $("#email").val();//邮箱
						var creditCode = $("#creditCode").val();//社会信用代码
						var entity = $("#entity").val();//法定人姓名
						var businessLicenseBeginTime = $(
								"#businessLicenseBeginTime").val();//营业执照有效期开始
						var businessLicenseEndTime = $(
								"#businessLicenseEndTime").val();//营业执照有效期结束
						var remail = new RegExp(
								"^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");//验证邮箱
						var rbusinessLicenseBeginTime = new RegExp(
								"^(?:(?:([0-9]{4}/(?:(?:0?[1,3-9]|1[0-2])/(?:29|30)|((?:0?[13578]|1[02])/31)))|([0-9]{4}/(?:0?[1-9]|1[0-2])/(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))/0?2/29))))$");//验证营业执照有效期

						if (isBlank(name)) {
							layer.msg('公司名称不能为空!', {icon: 2});
						}else if(isBlank(category)){
							layer.msg('供应商不能为空!', {icon: 2});
						}else if (isBlank(country)) {
							layer.msg('公司所在地不能为空!', {icon: 2});
						} else if (isBlank(Province)) {
							layer.msg('公司所在地不能为空!', {icon: 2});
						} else if (isBlank(bankCity)) {
							layer.msg('城市不能为空!', {icon: 2});
						} else if (isBlank(companyAddr)) {
							layer.msg('公司详细地址不能为空!', {icon: 2});
						} else if (isBlank(email)) {
							layer.msg('电子邮箱不能为空!', {icon: 2});
						} else if (!remail.test(email)) {
							layer.msg('非法电子邮箱格式!', {icon: 2});
						} else if (isBlank(creditCode)) {
							layer.msg('社会信用代码不能为空!', {icon: 2});
						} else if (isBlank(entity)) {
							layer.msg('法人姓名不能为空!', {icon: 2});
						}else if(!$("#businessLicenseIsSecular").is(":checked")){
							if (isBlank(businessLicenseBeginTime)
									|| isBlank(businessLicenseEndTime)) {
								layer.msg('营业执照有效期不能为空!', {icon: 2});
							} else if (!rbusinessLicenseBeginTime
									.test(businessLicenseBeginTime)) {
								layer.msg('营业执照有效期格式不正确,正确格式为：2010/01/01', {icon: 2});
							} else if (!rbusinessLicenseBeginTime
									.test(businessLicenseBeginTime)) {
								layer.msg('营业执照有效期格式不正确,正确格式为：2010/01/01', {icon: 2});
							}
						}else {
							$("#businessLicenseBeginTime").val("");
							$("#businessLicenseEndTime").val("");
							$("#step2").hide();
							$("#step3").show();
						}
					})
	$(".step3").on("click", function() {
		var contacts = $("#contacts").val();//联系人
		var phone = $("#phone").val();//电话
		var settlementBank = $("#settlementBank").val();//结算开户行
		var bankAccount = $("#bankAccount").val();//银行账号
		var bankBranch = $("#bankBranch").val();//开户银行支行名称
		var bankCountry = $("#bankCountry").val();//开户银行支行所在地国家
		var BankProvince = $("#BankProvince").val();//开户银行支行所在地省份

		if (isBlank(contacts)) {
			layer.msg('联系人不能为空!', {icon: 2});
		} else if (isBlank(phone)) {
			layer.msg('手机号码不能为空!', {icon: 2});
		} else if (isBlank(settlementBank)) {
			layer.msg('结算开户行不能为空!', {icon: 2});
		} else if (isBlank(bankAccount)) {
			layer.msg('银行账号不能为空!', {icon: 2});
		} else if (isBlank(bankBranch)) {
			layer.msg('开户银行支行名称不能为空!', {icon: 2});
		} else if (isBlank(bankCountry)) {
			layer.msg('开户银行支行所在地不能为空!', {icon: 2});
		} else if (isBlank(BankProvince)) {
			layer.msg('开户银行支行所在地不能为空!', {icon: 2});
		} else {
			$("#step3").hide();
			$("#step4").show();
		}
	})
	$(".step4").on("click", function() {
		$("#step4").hide();
		$("#step5").show();
	})
	$(".step5").on("click", function() {
		$("#step5").hide();
	})
	$(".step02").on("click", function() {
		$("#step3").hide();
		$("#step2").show();
	})
	$(".step03").on("click", function() {
		$("#step4").hide();
		$("#step3").show();
	})

	//验证必填项
	function isBlank(str) {
		console.log(str);
		if (str.trim() == "" || str == null || str == "请选") {
			return true;
		} else {
			return false;
		}
	}
	/* $(".state").on(
			"click",
			function() {
				$.ajax({
					url : "/home/usr_UsrSupplier_PltProvince",
					data : "id=" + $("#state").val(),
					type : "post",
					dataType : "json",
					success : function(data) {
						console.log(data);
						var option = '';
						$.each(data.items, function(i, val) {
							option += '<option value="'+val.pkey+'">'
									+ val.name + '</option>';
						})
						$("#province option:first").after(option);
					}
				})
			}) */
	$("#country").on("change",function(){
		$.ajax({
			url:"/home/usr_UsrSupplier_pltprovince",
			data:"id="+$("#state").val(),
			type:"post",
			dataType:"json",
			success:function(data){
				var option = '';
				$.each(data.items, function(i, val) {
					option += '<option value="'+val.pkey+'" id="Province">'
							+ val.name + '</option>';
				})
				$("#province option:first").after(option);
			}
		})
	})
	$("#bankCountry").on("change",function(){
		$.ajax({
			url:"/home/usr_UsrSupplier_pltprovince",
			data:"id="+$("#bankState").val(),
			type:"post",
			dataType:"json",
			success:function(data){
				var option = '';
				$.each(data.items, function(i, val) {
					option += '<option value="'+val.pkey+'" id="BankProvince" >'+ val.name + '</option>';
				})
				$("#bankProvince option:first").after(option);
			}
		})
	})
	$("#businessLicenseIsSecular").on("change",function(){
		if($("#businessLicenseIsSecular").is(":checked")){
			$("#businessLicenseIsSecular").val(1);
			$("#businessLicenseBeginTime").val("");
			$("#businessLicenseEndTime").val("");
			$("#businessLicenseEndTime").attr("readonly","readonly");
			$("#businessLicenseBeginTime").attr("readonly","readonly");
		}else{
			$("#businessLicenseEndTime").removeAttr("readonly");
			$("#businessLicenseBeginTime").removeAttr("readonly");
			$("#businessLicenseIsSecular").val(0);
		}
	})
</script>
</html>