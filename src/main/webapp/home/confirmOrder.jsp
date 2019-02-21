<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)https://www.shoestp.com/cart/checkout.html -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="renderer" content="webkit">
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/confirmUser.js"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>

    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->

    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js"></script>
    <script src="./static/js/lazyload.min.js"></script>
    <link href="./static/css/cart.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/confirmCart.js"></script>
    <script type="text/javascript" src="./static/js/tool_tips_web.js"></script>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">

    <style type="text/css">
        .FontColor,
        a.FontColor,
        a.FontColor:hover,
        a:hover {
            color: #333333;
        }

        .FontBgColor {
            background-color: #333333;
        }

        .FontBorderColor {
            border-color: #333333;
        }

        .FontBorderHoverColor:hover,
        a.FontBorderHoverColor:hover {
            border-color: #333333;
        }

        .NavBgColor {
            background-color: #333333;
        }

        .NavHoverBgColor:hover {
            background-color: #424242;
        }

        .NavBorderColor1 {
            border-color: #333333;
        }

        .NavBorderColor2 {
            border-color: #333333;
        }

        .CategoryBgColor {
            background-color: #424242;
        }

        .PriceColor {
            color: #101010;
        }

        .AddtoCartBgColor {
            background-color: #D80000;
        }

        .BuyNowBgColor {
            background-color: #FF9900;
        }

        .ReviewBgColor {
            background-color: #FF9900;
        }

        .DiscountBgColor {
            background-color: #D80000;
        }

        .DiscountBorderColor {
            border-color: #D80000;
        }

        .ProListBgColor {
            background-color: #FF9900;
        }

        .ProListHoverBgColor:hover {
            background-color: #FF9900;
        }

        .GoodBorderColor {
            border-color: #626262;
        }

        .GoodBorderHoverColor:hover {
            border-color: #D80000;
        }

        .GoodBorderColor.selected {
            border-color: #D80000;
        }

        .GoodBorderBottomHoverColor {
            border-bottom-color: #D80000;
        }
    </style>
    <script type="text/javascript">
        $(window).resize(function () {
            $(window).webDisplay(2);
        });
        $(window).webDisplay(2);
        var stpshop_config = {
            "domain": "https://www.shoestp.com",
            "date": "2018/07/16 11:11:32",
            "lang": "en",
            "currency": "USD",
            "currency_symbols": "$",
            "currency_rate": "1.0000",
            "FbAppId": "718848724966585",
            "FbPixelOpen": "0",
            "UserId": "224",
            "TouristsShopping": "0",
            "PaypalExcheckout": ""
        };
        var ueeshop_config = stpshop_config;
    </script>
  	<%@ include file="/home/template/web-top.jsp" %>
    <div id="main">
        <index-top></index-top>
        <div class="wide">
            <script type="text/javascript">
                var address_count = 1;
                var address_perfect = 0;
                $(document).ready(function () {
                    cart_obj.checkout_init()
                });
                $(this).loginOrVisitors('/cart/checkout.html', 0, function () {
                    ueeshop_config['_login'] = 1;
                    return false;
                });
            </script>
            <style>
                .tdshipping .td_title {
                    text-align: right;
                }

                .tdshipping li {
                    height: 25px;
                    line-height: 25px;
                    cursor: default;
                }

                .tdshipping li input {
                    vertical-align: middle;
                }

                .tdshipping li span {
                    color: #c80201;
                    font-size: 14px;
                }
            </style>
            <div id="lib_cart">
                <div class="step">
                    <div class="step_1"></div>
                </div>
                <div class="cartBox" id="addressObj">
                    <h2>Shipping Address</h2>
                    <div class="contents address">
                        <ul id="lib_address" style="float:left;">

                            <c:forEach items="${confirmView.addressList}" var="address">
                                <li class="<c:if test="${address.isdefault==1}">cur</c:if>" style="display: list-item;">
                                    <input type="radio" name="addressRadio"  <c:if test="${address.isdefault==1}">checked="checked"</c:if> value="${address.id}">
                                    <label for="address_465">
                                        <strong>${address.name} ${address.surname}</strong>
                                        (<data>${address.address} ${address.city} ${address.region} ${address.country}</data>)
                                    </label>
                                    <a href="javascript:;" class="edit_address_info" data="${address.id}" style="display:inline">Edit</a>
                                </li>
                            </c:forEach>
                            <li style="display: list-item;">
                                <a id="addAddress" href="javascript:;" class="textbtn">Add a New Shipping Address</a>
                            </li>
                            <li id="addressInfo" style=""></li>
                            <li id="addressForm" style="display: none;">

                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        user_obj.user_address()
                                    });
                                </script>
                                <div class="editAddr">
                                    <form id="setAddressId" >
                                        <a id="cancelAddr" class="cancel" href="javascript:;" style="display:;">Cancel</a>
                                        <p>
                                            <span class="required">*</span>&nbsp;
                                            <span class="indicates">Indicates required fields.</span>
                                        </p>
                                        <table class="tb-shippingAddr">
                                            <tbody>
                                            <tr>
                                                <th></th>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <label>Your Name:</label>
                                                </th>
                                                <td class="recipient">
                                                    <div>
                                                        <input type="text" name="bean.name" maxlength="32" class="elmbBlur">
                                                        <p>
                                                            <span class="required">*</span>&nbsp;First Name</p>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <div>
                                                        <input type="text" name="bean.surname" maxlength="32" class="elmbBlur">
                                                        <p>
                                                            <span class="required">*</span>&nbsp;Last Name</p>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>ZIP / Postal Code:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.emailcode" maxlength="10" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span><label>Destination Country/Region:</label>
                                                </th>
                                                <td>

                                                    <div id="country_chzn" class="chzn-container chzn-container-single" style="width:310px">
                                                        <input id="sldCountryHiden" type="hidden" name="bean.country"/>
                                                        <a href="javascript:void(0)"  class="chzn-single">
                                                            <span id="sldCountry" onchange="changeCountry()"></span>
                                                            <div>
                                                                <b></b>
                                                            </div>
                                                        </a>
                                                        <div class="chzn-drop" style="left: -9000px; width: 308px;">
                                                            <div class="chzn-search clearfix" style="height: 20px;">
                                                                <input type="text" autocomplete="off" class="">
                                                            </div>
                                                            <ul class="chzn-results">
                                                                <c:forEach items="${confirmView.countryList}" var="country">
                                                                    <li class="group-option active-result" value="${country.pkey}" onclick="selectThisCountry(this)">${country.name}</li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <p class="errorInfo">
                                                    </p>
                                                </td>
                                            </tr>
                                            <!--  <tr id="taxCode" style="display: none;">
                                                 <th>
                                                     <span class="required">*</span>
                                                     <label>CPF or CNPJ code:</label>
                                                 </th>
                                                 <td>
                                                     <select name="tax_code_type" class="taxCodeOption" id="taxCodeOption" disabled="disabled">
                                                         <option value="1" selected="selected">CPF (personal order)</option>
                                                         <option value="2">CNPJ (company order)</option>
                                                     </select>
                                                     <input type="text" name="tax_code_value" id="taxCodeValue" maxlength="11" class="taxCodeValue elmbBlur" disabled="disabled">
                                                     <p class="lightGray">Please only input numbers, no dots, dashes or other characters.</p>
                                                     <p class="errorInfo"></p>
                                                 </td>
                                             </tr>
                                             <tr id="tariffCode" style="display: none;">
                                                 <th>
                                                     <span class="required">*</span>
                                                     <label>Personal or VAT ID</label>
                                                 </th>
                                                 <td>
                                                     <select name="tax_code_type" class="tariffCodeOption" id="tariffCodeOption" disabled="disabled">
                                                         <option value="3" selected="selected">My personal details</option>
                                                         <option value="4">VAT ID number (company order)</option>
                                                     </select>
                                                     <input type="text" name="tax_code_value" id="tariffCodeValue" maxlength="11" class="tariffCodeValue elmbBlur" disabled="disabled">
                                                     <a href="javascript:void(0);" class="lightGray askTipsTariff" title="Your Personal ID/VAT ID number is required to ensure successful delivery of your order.">Why ask?</a>
                                                     <p class="lightGray">Please only input numbers, no dots, dashes or other characters.</p>
                                                     <p class="errorInfo"></p>
                                                 </td>
                                             </tr> -->
                                            <tr id="zoneId">
                                                <th>
                                                    <span class="required">*</span><label>State / Province / Region:</label>
                                                </th>
                                                <td>

                                                    <input id="sldProvinceHiden" type="hidden" name="bean.region"/>
                                                    <div id="province_chzn" class="chzn-container chzn-container-single" style="width:310px">
                                                        <a href="javascript:void(0)" class="chzn-single" tabindex="0" >
                                                            <span id="sldProvince" ></span>
                                                            <div>
                                                                <b></b>
                                                            </div>
                                                        </a>
                                                        <div class="chzn-drop" style="left: -9000px; width: 308px;">
                                                            <div class="chzn-search clearfix" style="height: 20px;">
                                                                <input type="text" autocomplete="off" tabindex="-1" class="">
                                                            </div>
                                                            <c:forEach items="${confirmView.provinceMap}" var="provinceMap">
                                                                <ul class="chzn-results" id="getProvinces${provinceMap.key }" style="display:none;">
                                                                    <c:forEach items="${provinceMap.value}" var="province">
                                                                        <li class="group-option active-result" value="${province.pkey}" onclick="chooseThisProvice(this)">${province.name}</li>
                                                                    </c:forEach>
                                                                </ul>
                                                            </c:forEach>
                                                            <!-- <ul class="chzn-results" id="getProvinces">
                                                            </ul> -->

                                                        </div>
                                                    </div>
                                                    <p class="errorInfo">
                                                    </p>
                                                </td>
                                            </tr>

                                            <th>
                                                <span class="required">*</span>
                                                <label>City:</label>
                                            </th>
                                            <td>
                                                <input type="text" name="bean.city" maxlength="30" class="elmbBlur">
                                                <p class="errorInfo"></p>
                                            </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Address Line 1:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.address" maxlength="100" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <!-- <tr>
                                                <th>
                                                    <label>Address Line 2:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="AddressLine2" maxlength="100" class="elmbBlur">
                                                    <p class="lightGray">Example: apartment, suite, unit, building, floor</p>
                                                </td>
                                            </tr> -->
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Phone Number:</label>
                                                </th>
                                                <td>
                                                    <input style="width:48px;" id="countryCode" class="left countryCode" name="CountryCode" type="text" value="+0000" readonly="">
                                                    <input id="addressId" type="hidden" name="bean.pkey"/>
                                                    <div class="left editableSelect hasLayout">
                                                        <input type="text" name="bean.phonenumber" class="phoneNum elmbBlur" maxlength="15" autocomplete="off">
                                                        <ul id="otherPhones"></ul>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <a href="javascript:void(0);" class="lightGray askTips" title="We ask for your phone number just in case we need to reach you regarding your order.">Why ask?</a>
                                                    <p id="phoneSample" class="lightGray clearfix">Example: +
                                                        <span>376</span> 9549031647-535</p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>
                                                    <button id="useAddress" type="button" class="textbtn">Use This Shipping Address</button>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <input type="reset" id="resetAddr" style="display:none;">
                                        <input type="hidden" name="typeAddr" value="0">
                                    </form>
                                </div>
                            </li>
                        </ul>
                        <div style="width: 30%;float: right;border-left: 1px dotted #ccc;margin-right: 39px;">
                            <p style="font-size: 17px;padding: 8px 8px;font-family: fantasy;">账单地址</p>
                            <c:if test="${confirmView.billAddress != null}">
                                <div style="width: 100%;padding: 10px;">
                                    <span style="font-family: Verdana;">name:</span>
                                    <span style="font-family: Verdana;">${confirmView.billAddress.name} ${confirmView.billAddress.surname}</span>
                                </div>
                                <div style="width: 100%;padding: 10px;">
                                    <span style="font-family: Verdana;">code</span>
                                    <span style="font-family: Verdana;">${confirmView.billAddress.emailCode}</span>
                                </div>
                                <div style="width: 100%;padding: 10px;">
                                    <span style="font-family: Verdana;">telephone</span>
                                    <span style="font-family: Verdana;">${confirmView.billAddress.phoneNumber }</span>
                                </div>
                                <div style="width: 100%;padding: 10px;">
                                    <span style="font-family: Verdana;">address</span>
                                    <span style="font-family: Verdana;">${confirmView.billAddress.address} ${confirmView.billAddress.city}
							        			${confirmView.billAddress.country}
							        			${confirmView.billAddress.region}
							        </span>
                                </div>
                            </c:if>
                            <c:if test="${confirmView.billAddress == null}">
                                <a class="toSetBillAddress" style="padding-left: 9px;cursor:pointer;">
                                    未设置账单地址,去设置
                                </a>
                            </c:if>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>


                <form id="PlaceOrderFrom" method="post" action="" amountprice="14000.00" userprice="0" userratio="100">
                    <!-- 新换样式 -->
                    <style>


                        .flex-start {
                            display: -webkit-box;
                            display: -ms-flexbox;
                            display: flex;
                        }
                        .flex-center {
                            display: -webkit-box;
                            display: -ms-flexbox;
                            display: flex;
                            -webkit-box-align: center;
                            -ms-flex-align: center;
                            align-items: center;
                        }
                        .tc{
                            text-align: center;
                        }
                        /* 自定义 表头 仿table */
                        #lib_cart .cart-list-header{
                            height: 40px;
                            font: 400 14px/29px georgia;
                            color: #990000;
                            border-bottom: solid 1px #e5e5e5;
                            box-shadow: inset 0px 2px 0px 0px 	rgba(255, 255, 255, 0.6);
                            background-image: linear-gradient(0deg, #f2f2f2 0%,	#f7f7f7 50%,	#fbfbfb 100%);
                        }
                        /* 123宽度定义 */
                        #lib_cart .w_one{
                            width: 40px;
                        }
                        #lib_cart .w_two{
                            width: 540px;
                        }
                        #lib_cart .w_three{
                            width: 180px;
                        }
                        #lib_cart .w_four{
                            width: 180px;
                        }
                        #lib_cart .w_five{
                            width: 180px;
                        }

                        .cart-list-item-base-info-wrap{
                            position: relative;
                            padding: 10px 0;
                            line-height: 24px;
                            font-size: 12px;
                            color: #000;
                            border-bottom: 1px dotted #e5e5e5;
                        }
                        /* 单个商品 基础信息 */
                        .cart-list-item-base-info-wrap .goods-pic{
                            width: 60px;
                            height: 60px;
                            margin-right: 8px;
                            border: 1px solid #e5e5e5;
                            box-sizing: border-box;
                        }
                        .cart-list-item-base-info-wrap .goods-descript{
                            width: 500px;  /* 待定 */
                        }
                        .cart-list-item-base-info-wrap .goods-descript .goods-name a{
                            color: #0050a8;
                        }
                        .cart-list-item-base-info-wrap .goods-descript .color-or-type{

                        }
                        .cart-list-item-base-info-wrap .goods-descript .goods-price{

                        }


                        /* 以下为单个商品里 不同规格的信息显示 */
                        .cart-list-item-detail-info-wrap{
                            padding-bottom: 10px;
                        }
                        .cart-list-item-detail-info-wrap .cart-list-item-detail-info-item{
                            padding: 10px;
                        }
                        .cart-list-item-detail-info-wrap .goods-pic{
                            width: 60px;
                            height: 60px;
                            margin-left: 30px;
                            margin-right: 8px;
                            border: 1px solid #e5e5e5;
                            box-sizing: border-box;
                            flex-shrink: 0;
                        }
                        /* 虚线外框 */
                        .cart-list-item-detail-info-wrap .goods-spec-wrap{
                            position: relative;
                            flex-grow: 1;
                            margin-right: 30px;
                            padding: 16px 20px;
                            border: 1px dotted #7ecef4;
                        }
                        /* 商品颜色 */
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .spec-or-color{
                            position: absolute;
                            left: 20px;
                            top: 20px;
                        }
                        /* 同颜色下 不同型号的商品 item-list */
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .color-goods-list{
                            margin-left: 173px;
                        }
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .color-goods-item{
                            margin-bottom: 10px;
                        }
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .color-goods-item:last-child{
                            margin-bottom: 0;
                        }
                        /* 商品规格 */
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .goods-spec{
                            width: 280px;
                        }
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .goods-price{
                            text-align: center;
                        }
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .goods-num{
                            -webkit-box-pack: center;
                            -ms-flex-pack: center;
                            justify-content: center;
                            text-align: center;
                            line-height: 24px;
                        }
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .goods-num input{
                            width: 82px;
                            margin-left: 5px;
                            margin-right: 5px;
                            text-align: center;
                        }
                        .cart-list-item-detail-info-wrap .goods-spec-wrap .total-price{
                            text-align: center;
                        }
                        .cart-list .cart-purchasing-station {
                            margin: 12px 0;
                            line-height: 24px;
                            color: #820000;
                        }
                        .cart-list .cart-container {
                            border: 1px solid #e5e5e5;
                            margin-bottom: 30px;
                        }
                        .cart-list .remark {
                            margin: 12px 0 12px 50px;
                        }

                        .cart-list .item-total {
                            text-align: right;
                            margin: 12px 50px 12px 0;
                        }
                    </style>
                    <!-- 商品列表 之前是table -->
                    <!-- 单个商品列表 -->
                    <div class="cart-list">
                        <!-- 国家采购点 -->
                        <div class="cart-purchasing-station flex-center">
                            <div class="checkbox w_one tc">
                            </div>
                            ${confirmView.supplier.name}
                        </div>
                        <div class="cart-container">
                            <!-- 表头 -->
                            <div class="cart-list-header flex-center">
                                <div class="checkbox w_one tc">
                                </div>
                                <div class="name w_two">Item</div>
                                <div class="price w_three tc">Price</div>
                                <div class="quantity w_four tc">Quantity</div>
                                <div class="total w_five tc">Total</div>
                            </div>

                            <!-- 仿tbody -->
                            <div class="cart-list-content">
                                <!-- 单个商品 - 里面有下拉项 -->
                                <div class="cart-list-item">
                                    <!-- 单个商品 基础信息 -->
                                    <div class="cart-list-item-base-info-wrap flex-center">
                                        <!-- 单个商品 选中状态 -->
                                        <div class="checkbox w_one tc">

                                        </div>
                                        <div class="w_two flex-start">
                                            <!-- 商品图片 -->
                                            <div class="goods-pic">
                                                <c:forTokens items="${confirmView.product.picture}" end="0" delims="," var="pic">
                                                    <img src="${envConfig.imageBaseUrl}${pic}">
                                                </c:forTokens>
                                            </div>
                                            <!-- 商品描述 -->
                                            <div class="goods-descript">
                                                <div class="goods-name">
                                                    <a href="#">
                                                        ${confirmView.product.name}
                                                    </a>
                                                </div>
                                                <div class="color-or-type">
                                                    ${confirmView.product.sku}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="goods-price w_three tc">

                                            ${env.currency.symbols}<fmt:formatNumber type="number" value="${confirmView.product.wsPrice}" pattern="0.00" maxFractionDigits="2"/>
                                        </div>
                                        <!-- 商品数量 -->
                                        <div class="goods-num w_four tc">
                                            ${confirmView.sta.qty}
                                        </div>
                                        <div class="total-price w_five tc">
                                            ${env.currency.symbols}<fmt:formatNumber type="number" value="${confirmView.sta.totalPrice}" pattern="0.00" maxFractionDigits="2"/>
                                        </div>
                                    </div>
                                    <!-- 单个商品 基础信息 - end -->

                                    <!-- 单个商品 详细信息 -->
                                    <div class="cart-list-item-detail-info-wrap">
                                        <c:set value="0" var="index" />
                                        <c:forEach items="${confirmView.color}" var="color" varStatus="step1">
                                            <div class="cart-list-item-detail-info-item flex-start">
                                                <div class="goods-pic">
                                                    <img src="${envConfig.imageBaseUrl}${color.img}">
                                                </div>
                                                <div class="goods-spec-wrap">
                                                    <!-- 规格or颜色信息 -->
                                                    <div class="spec-or-color">Color:${color.color}</div>
                                                    <!-- 同颜色下 不同型号的商品 item-list -->
                                                    <div class="color-goods-list">
                                                        <c:forEach items="${confirmView.staView[color.color]}" var="spec" varStatus="step2">
                                                            <div class="color-goods-item flex-center">
                                                                <input type="hidden" value="${spec.specId}" name="specList[${index}].spec"/>
                                                                <input type="hidden" value="${spec.price}" name="specList[${index}].subtotal"/>
                                                                <input type="hidden" value="${spec.qty}" name="specList[${index}].qty"/>
                                                                <!-- 商品规格 -->
                                                                <div class="goods-spec">
                                                                    Number: ${spec.size}
                                                                </div>
                                                                <!-- 商品价格 -->
                                                                <div class="goods-price w_three">
                                                                        ${env.currency.symbols} <fmt:formatNumber type="number" value="${spec.price}" pattern="0.00" maxFractionDigits="2"/>

                                                                </div>
                                                                <!-- 商品数量 -->
                                                                <div class="goods-num flex-center w_four">
                                                                        ${spec.qty}
                                                                </div>
                                                                <!-- 单个商品总价 -->
                                                                <div class="total-price w_five">
                                                                        ${env.currency.symbols} <fmt:formatNumber type="number" value="${spec.totalPrice}" pattern="0.00" maxFractionDigits="2"/>
                                                                </div>
                                                            </div>
                                                            <c:set value="${index+1}" var="index"/>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>

                                    </div>
                                    <table style="width: 100%;">
                                        <tbody style="width: 100%;">
                                        <tr style="width: 100%;border-top: 1px dotted #ccc;">
                                            <td style="width: 86.5%;text-align: right;padding: 10px;">
                                                <input value="Free Shipping" name="shippingMent" style="position: relative;top: 2px;left: -6px;" type="radio" checked="checked">Free mail:
                                            </td>
                                            <td style="color: #c80201;font-size: 16px;padding: 20px 0 20px 10px;">
                                                <span>FOB</span>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <!-- 单个商品 详细信息 - end -->
                                </div>
                            </div>



                            <!-- 仿tbody -->
                            <!-- 留言 bengin -->
                            <div class="remark">
                                <%--  Remark：
                               <span>Material description or special requirements</span> --%>
                                <span style="float: left;">Remark:</span>
                                <textarea name="bean.pagRemarks" id="pagRemarks" type="text" placeholder="Material description or special requirements" style="padding:0 10px;width: 27em;height: 5em;float: left;margin-left: 13px;"></textarea>
                                <div style="clear:both;"></div>
                            </div>
                            <!-- 留言 end -->
                            <!-- 单个采购点总价格 begin -->
                            <div class="item-total">
                                <p>Subtotal(
                                    <span>${confirmView.sta.qty}</span> items ): ${env.currency.symbols}
                                    <span>${confirmView.sta.totalPrice}</span>
                                </p>
                            </div>
                            <!-- 单个采购点总价格 end -->
                        </div>
                    </div>
                    <!-- <input type="hidden" name="bean.delivery" value="待补充"/> -->
                    <input type="hidden" name="bean.freightPrice" value="0"/>
                    <input type="hidden" name="bean.insurancePrice" value="0"/>
                    <input type="hidden" name="bean.prodPrice" value="${confirmView.sta.totalPrice}"/>
                    <input type="hidden" name="bean.priceTotal" value="${confirmView.sta.totalPrice}"/>
                    <input type="hidden" name="id" value="${confirmView.groupLine.pkey}"/>
                    <input type="hidden" name="currency" value="${env.currency.id}"/>
                    <input type="hidden" name="purchaseLine" value=""/>
                    <div class="cartBox" id="paymentObj">
                        <h2>Payment Method</h2>
                        <div class="contents payment">
                            <h3>Choose payment method:</h3>
                            <select name="bean.payType">
                                <option value="-1">
                                    <c:if test="${fn:length(confirmView.payList) == 0}">
                                        该商家未设置支付方式
                                    </c:if>
                                    <c:if test="${fn:length(confirmView.payList) > 0}">
                                        <s:text name="Global.Please_Select_Mode_Of_Payment"/>
                                    </c:if>
                                </option>
                                <c:forEach items="${confirmView.payList}" var="pay">
                                    <option value="${pay.pay.pkey}" min="0" max="0" style="display: block;">${pay.name}</option>
                                </c:forEach>
                                <!--  <option value="0" min="0" max="0" style="display: block;">T/T</option>
                                 <option value="1" min="0" max="0" style="display: block;">Alipay</option>
                                 <option value="2" min="0" max="0" style="display: block;">WeChat</option> -->
                            </select>
                            <div class="blank12"></div>
                            <ul>
                                <li style="display:block;">Please select your payment method</li>
                                <li class="editor_txt" id="payment_contents_10" fee="0.00" affix="0.00">
                                    <p>INTERMEDIARY BANK SWIFT： ICBKUS33
                                        <br> INDUSTRIAL AND COMMERCIAL BANK OF CHINA(NEW YORK)
                                        <br> BENEFICIARY BANK&nbsp; &nbsp;SWIFT： OHBKCNSH
                                        <br> ZHEJIANG WENZHOU OUHAI RURAL COMMERCIAL BANK COMPANY LIMITED
                                        <br> BENEFICIARY BANK ADD ：FUSEN BUILDING FLOOR 1-3 STATION ROAD，WENZHOU，ZHEJIANG,CHINA
                                        <br> ZIP CODE:325000
                                        <br>
                                        <br> BENEFICIARY ：WENZHOU NEW-UNION INDUSTRIAL CO LTD&nbsp;
                                        <br> BENEFICAIRY ADD&nbsp; : &nbsp;NO.51 FUHAO RD,QUXI ST,WENZHOU,ZHEJIANG,CHINA.325000&nbsp;
                                        <br> BENEFICIARY A/C NO. ：201000169314392 &nbsp;</p>
                                </li>
                            </ul>
                            <!-- <div class="new-coupon">
                                <p id="new-coupon-valid">
                                    <span class="valid" style="display:none;">The coupon code "
                                        <strong></strong>" is valid! Your discount is
                                        <span class="red"></span> (expiration date:
                                        <strong></strong>)
                                        <br>
                                    </span>
                                </p>
                                <p>
                                    <a href="javascript:;" class="u" id="removeCoupon">Remove</a>
                                </p>
                                <p id="new-cp">
                                    <a href="javascript:;" id="to-use-coupon">Apply Coupon Code
                                        <i> </i>
                                    </a>
                                </p>
                                <p id="link-error">
                                    <span class="netError red" style="display: none;"></span>
                                </p>
                            </div>
                            <div class="blank6"></div>
                            <div class="clean selcoupon">
                                <select name="SelCId" class="select">
                                    <option value="">Please select</option>
                                </select>
                            </div> -->

                            <script>
                                $(function () {
                                    $('.selcoupon .select').change(function () {
                                        var val = $(this).val();
                                        if (val != '') {
                                            cart_obj.cart_init.ajax_get_coupon_info(val);
                                        }
                                    });
                                });
                            </script>
                        </div>
                    </div>
                    <!-- 快递公司选择BEGIN -->
                    <div class="cartBox" id="express">
                        <h2>Delivery Method</h2>
                        <div class="contents payment">
                            <h3 style="font-size: 14px;font-weight: bold;line-height: 30px;display: block;">Choose delivery method:</h3>
                            <select name="bean.delivery">
                                <option value="-1">
                                    <c:if test="${fn:length(confirmView.delivery) == 0}">
                                        该商家未设置快递方式
                                    </c:if>
                                    <c:if test="${fn:length(confirmView.delivery) > 0}">
                                        <s:text name="Global.Please_Select_Your_Shipping_Method"/>
                                    </c:if>
                                </option>
                                <c:forEach items="${confirmView.delivery}" var="express">
                                    <option value="${express.company}" style="display: block;">${express.company}</option>
                                </c:forEach>
                            </select>
                            <div class="blank12"></div>
                            <div class="new-coupon">
                            </div>
                            <div class="blank6"></div>
                        </div>
                    </div>
                    <!-- 快递公司选择END -->
                    <div class="NoteBox">
                        <h2>Note: If you choose Paypal as payment method, please leave us your Paypal email address right here for
                            payment confirmation on our end.</h2>
                        <div class="notes">
                            <textarea name="bean.odrRemarks"></textarea>
                        </div>
                    </div>
                    <div class="CartAmountSum">
                        <table id="subTotal" cellpadding="0" cellspacing="0" border="0" width="100%">
                            <!-- Grand Total -->
                            <tfoot>
                            <tr id="cartAmount" style="display: table-row;">
                                <th width="100%">Grand Total:
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong>${confirmView.sta.totalPrice}</strong>
                                </td>
                            </tr>
                            </tfoot>
                            <tbody>

                            <tr style="display: table-row;">
                                <!-- subtotal -->
                                <th>Subtotal( ${confirmView.sta.qty} items ):
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_subtotal">${confirmView.sta.totalPrice}</strong>
                                </td>
                            </tr>


                            <tr id="shippingCharges" style="">
                                <!-- Shipping Charges-->
                                <th>(+) Shipping Charges:
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_shipping">0.00</strong>
                                </td>
                            </tr>

                            <tr id="shippingInsuranceCombine" style="display: none;">
                                <!-- Shipping Insurance combine-->
                                <th>(+) Shipping Charges &amp; Insurance:
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_combine_shippnig_insurance">0.00</strong>
                                </td>
                            </tr>

                            <tr id="couponSavings" style="display: none;">
                                <!-- Coupon Savings -->
                                <th>(-) Coupon Savings:
                                    <em>- ${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_coupon">0.00</strong>
                                </td>
                            </tr>

                            <tr id="serviceCharge" style="display: none;">
                                <!-- Service Charge -->
                                <th>(+) Service Charge:
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_fee">0.00</strong>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <fieldset id="submitCart" class="clearfix" style="display: block;">
                            <a href="javascript:;" class="fr">
                                <input type="button" id="orderFormSubmit" class="litb-btn placeOrderBtn">
                            </a>
                            <p class="clearfix">Every order you place with us is safe and secure.</p>
                        </fieldset>
                    </div>
                </form>
            </div>
        </div>
        <index-bottom></index-bottom>
    </div>

     <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
    <div id="hj_top" style="opacity: 0;">
        <img src="./static/images/hj_top.png">
    </div>
</body>

<script type="text/javascript">
	window.onload = function(){
		if($("#PlaceOrderFrom input[name=purchaseLine]").val() == ""){
			var checked = $("#lib_address li input[name=addressRadio]:checked");
			var purchaseLine = $(checked[0]).val();
			$("#PlaceOrderFrom input[name=purchaseLine]").val(purchaseLine);
		}
	}
	function selectThisCountry(btn){
		var country = $(btn).attr("value");
		$("ul[id^=getProvinces]").hide();
		$("#getProvinces"+country).show();
		$("#setAddressId input[name='bean.country']").val(country);
		var countryName = $(btn).text();
		$("#sldCountry").text(countryName);
	}

	function chooseThisProvice(btn){
		var province = $(btn).attr("value");
		var provinceName = $(btn).text();
		$("#sldProvince").text(provinceName);
		$("#setAddressId input[name='bean.region']").val(province)
	}

	$("#lib_address input[type=radio][name=shipping_address_id]").on("change",function(){
		var purchaseLine = $(this).val();
		console.log(purchaseLine);
		$("#PlaceOrderFrom input[name=purchaseLine]").val(purchaseLine);
	})
	$(".toSetBillAddress").on("click",function(){
	window.location.href = "/home/usr_UsrPurchaseLine_addmanagement?jumpUrl="+window.location.pathname+window.location.search;
})

</script>
<script>
    new Vue({
        el:"#main"
    })
</script>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
</html>
