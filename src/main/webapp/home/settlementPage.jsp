<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script type="text/javascript" src="./static/js/global.js"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>

    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->

    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js"></script>
    <script src="./static/js/lazyload.min.js"></script>
    <link href="./static/css/cart.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/cart.js"></script>
    <script type="text/javascript" src="./static/js/tool_tips_web.js"></script>

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
</head>

<body class="lang_en w_1200">
    <%@ include file="/home/template/web-top.jsp" %>
    <%@ include file="/home/template/new-header.jsp" %>

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

    <div id="main" class="wide">
        <div id="lib_cart">
            <div class="step">
              <div class="step">
                <div class="step-content">
                  1. <s:text name="Shopping_Cart"/>
                </div>
                <div class="step-content active">
                  2. <s:text name="Checkout"/>
                </div>
                <div class="step-content">
                  3. <s:text name="Complete"/>
                </div>
              </div>
            </div>
            <div class="cartBox" id="addressObj">
                <h2><s:text name="Global.Delivery_Address"/></h2>
                <div class="contents address">
                    <ul id="lib_address" style="float:left;">
                    	<!-- 显示收获地址集合 -->
                    	<c:forEach items="${map.addressList}" var="address" varStatus="idx">
	                        <li class="" style="display: list-item;">
	                             <input type="radio" name="addressRadio" <c:if test="${address.isdefault == 1}">checked</c:if> value="${address.pkey}">
	                            <label for="address_465">
	                                <strong>${address.name} ${address.surname}</strong>
	                                (<data>${address.address} ${address.city}
	                                <c:forEach items="${map.provinces}" var="province">
	                                	<c:if test="${province.pkey == address.region}">
	                                		${province.name}
	                                	</c:if>
	                                </c:forEach>
	                                 <c:forEach items="${map.countries}" var="country">
	                                	<c:if test="${country.pkey == address.country}">
	                                		${country.name}
	                                	</c:if>
	                                </c:forEach>
	                               </data>)
	                            </label>
	                            <a href="javascript:;" class="edit_address_info" style="display:inline" id="${address.pkey}"><s:text name="Global.Edit"/></a>
	                        </li>
                    	</c:forEach>



                        <li style="display: list-item;">
                            <a id="addAddress" href="javascript:;" class="textbtn"><s:text name="Global.Add_New_Shipping_Address"/></a>
                        </li>
                        <li id="addressInfo" style=""></li>
                        <li id="addressForm" style="display: none;">
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    user_obj.user_address()
                                });
                            </script>
                            <div class="editAddr">
                                <form id="setAddressId" method="post" action="">
                                    <input id="addressId" type="hidden" name="edit_address_id" value="465">
                                    <a id="cancelAddr" class="cancel" href="javascript:;" style="display:;"><s:text name="Global.Cancel"/></a>
                                    <p>
                                        <span class="required">*</span>&nbsp;
                                        <span class="indicates"><s:text name="Global.Indicates_Required_Fields"/></span>
                                    </p>
                                    <table class="tb-shippingAddr">
                                        <tbody>
                                            <tr>
                                                <th></th>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <label><s:text name="Global.Name"/>:</label>
                                                </th>
                                                <td class="recipient">
                                                    <div>
                                                        <input type="text" name="bean.name" maxlength="32" class="elmbBlur">
                                                        <p>
                                                            <span class="required">*</span>&nbsp;<s:text name="Global.First_Name"/></p>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <div>
                                                        <input type="text" name="bean.surname" maxlength="32" class="elmbBlur">
                                                        <p>
                                                            <span class="required">*</span>&nbsp;<s:text name="Global.Surname"/></p>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label><s:text name="Global.Postal_Code"/>:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.emailcode" maxlength="10" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                       <tr>
											<th>
												<span class="required">*</span><label><s:text name="Global.Country"/>:</label>
											</th>
											<td>
												<input id="sldCountryHiden" type="hidden" name="bean.country"/>
												<div id="country_chzn" class="chzn-container chzn-container-single" style="width:310px">
													<a class="chzn-single" tabindex="0">
														<span id="sldProvince"></span>
														<div><b></b></div>
													</a>
													<div class="chzn-drop" style="left: -9000px; width: 308px;">
														<div class="chzn-search clearfix" style="height: 20px;">
															<input type="text" autocomplete="off" class="">
														</div>
														<ul class="chzn-results">
															<c:forEach items="${map.countries}" var="country">
																<li class="group-option active-result" value="${country.pkey}" onclick="selectThisCountry(this)">${country.name}</li>
															</c:forEach>
														</ul>
													</div>
												</div>
												<p class="errorInfo">
												</p>
											</td>
						             </tr>
                                        <tr id="zoneId">
												<th>
													<span class="required">*</span><label><s:text name="Global.Province"/>:</label>
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
																<c:forEach items="${map.countries}" var="country">
																	<ul class="chzn-results" id="getProvinces${country.pkey}" style="display:none;">
																		<c:forEach items="${map.provinces}" var="province">
																			<c:if test="${province.main == country.pkey}">
																				<li class="group-option active-result" value="${province.pkey}" onclick="chooseThisProvice(this)">${province.name}</li>
																			</c:if>
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
                                                    <label><s:text name="Global.City"/>：</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.city" maxlength="30" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label><s:text name="Global.Detailed_Address"/>:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.address" maxlength="100" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label><s:text name="Global.Telephone_Number"/>:</label>
                                                </th>
                                                <td>
                                                    <input style="width:48px;" id="countryCode" class="left countryCode" name="CountryCode" type="text" value="+0000" readonly="">
                                                    <input id="addressId" type="hidden" name="bean.pkey"/>
                                                    <div class="left editableSelect hasLayout">
                                                        <input type="text" name="bean.phonenumber" class="phoneNum elmbBlur" maxlength="15" autocomplete="off">
                                                        <ul id="otherPhones"></ul>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <a href="javascript:void(0);" class="lightGray askTips" title="We ask for your phone number just in case we need to reach you regarding your order."><s:text name="user.whyAsk"/></a>
                                                    <p id="phoneSample" class="lightGray clearfix"><s:text name="Global.For_Example"/>: +
                                                        <span>376</span> 9549031647-535</p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>
                                                    <button id="useAddress" type="button" class="textbtn"><s:text name="Global.Use_This_Shipping_Address"/></button>
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
					    <p style="font-size: 17px;padding: 8px 8px;font-family: fantasy;"><s:text name="cartcheckout.billing_Address"/></p>
					    	<c:set var="billAddress" value="${map.billAddress}" />
					    	<c:if test="${billAddress != null}">
						    	<div style="width: 100%;padding: 10px;">
							        <span style="font-family: Verdana;"><s:text name="Global.Name"/>:</span>
							        <span style="font-family: Verdana;">${billAddress.name} ${billAddress.surname}</span>
						    	</div>
								<div style="width: 100%;padding: 10px;">
							        <span style="font-family: Verdana;"><s:text name="Global.Postal_Code"/>:</span>
							        <span style="font-family: Verdana;">${billAddress.emailcode}</span>
						    	</div>
								<div style="width: 100%;padding: 10px;">
							        <span style="font-family: Verdana;"><s:text name="Global.Phone"/>:</span>
							        <span style="font-family: Verdana;">${billAddress.phonenumber }</span>
							    </div>
								<div style="width: 100%;padding: 10px;">
							        <span style="font-family: Verdana;"><s:text name="Global.Address"/>:</span>
							        <span style="font-family: Verdana;">${billAddress.address} ${billAddress.city}
							        	<c:forEach items="${map.countries}" var="country">
							        		<c:if test="${country.pkey == billAddress.country}">
							        			${country.name}
							        		</c:if>
							        	</c:forEach>
							        	<c:forEach items="${map.provinces}" var="region">
							        		<c:if test="${region.pkey == billAddress.region}">
							        			${region.name}
							        		</c:if>
							        	</c:forEach>
							        </span>
							    </div>
						    </c:if>
						    <c:if test="${billAddress == null}">
						    	<a class="toSetBillAddress" style="padding-left: 9px;cursor:pointer;">
						    		<s:text name="GoSet"/>
						    	</a>
					    	</c:if>
					</div>
                    <div class="clear"></div>
                </div>
            </div>
            <form id="PlaceOrderFrom" method="post" action="" amountprice="" userprice="0" userratio="">
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
											<c:set value="0" var="numbers"/>
                                        	<c:forEach items="${map.suppliers}" var="supplier" varStatus="index">
                                        		<!-- 国家采购点 -->
	                                            <div class="cart-purchasing-station flex-center">
	                                                    <div class="checkbox w_one tc"><s:text name="Shop"/>:</div>
	                                                	${supplier.name}
	                                                	<input class="supplier" type="hidden" name="list[${index.index}].supplierid" value="${supplier.id}"/>
	                                            </div>
	                                            <div class="cart-container">
	                                                <!-- 表头 -->
	                                          <div class="cart-list-header flex-center">
	                                                <div class="checkbox w_one tc">
	                                                </div>
	                                                <div class="name w_two"><s:text name="Global.Product"/></div>
	                                                <div class="price w_three tc"><s:text name="Global.Price"/></div>
	                                                <div class="quantity w_four tc"><s:text name="Global.Quantity"/></div>
	                                                <div class="total w_five tc"><s:text name="Global.Total_Price"/></div>
	                                              </div>

	                                              <!-- 仿tbody -->
	                                              <div class="cart-list-content">
	                                                <!-- 单个商品 - 里面有下拉项 -->
	                                                <c:forEach items="${map.products}" var="product">
	                                                	<c:if test="${product.supId == supplier.id}">
		                                                	<div class="cart-list-item">
		                                                		<div class="cart-list-item-base-info-wrap flex-center">
			                                                    <!-- 单个商品 选中状态 -->
			                                                    <div class="checkbox w_one tc">
			                                                    </div>
			                                                    <div class="w_two flex-start">
			                                                      <!-- 商品图片 -->
			                                                      <div class="goods-pic">
			                                                        <img src="${envConfig.imageBaseUrl}${product.img}">
			                                                      </div>
			                                                      <!-- 商品描述 -->
			                                                      <div class="goods-descript">
			                                                        <div class="goods-name">
			                                                          <a target="_blank" href="<c:if test="${product.type == 1}">/home/prm_PrmGroupPurchase_getGroupPdt?pkey=${product.line}</c:if><c:if test="${product.type == 0}">/home/pdt_PdtProduct_gtProductsInfo?id=${product.id}</c:if>">
			                                                                ${product.name}
			                                                          </a>
			                                                        </div>
			                                                        <div class="color-or-type">
			                                                          ${product.sku}
			                                                        </div>
			                                                      </div>
			                                                    </div>
			                                                    <div class="goods-price w_three tc">
			                                                      ${env.currency.symbols} ${product.price}
			                                                    </div>
			                                                    <!-- 商品数量 -->
			                                                    <div class="goods-num w_four tc">
			                                                      	${product.qty}
			                                                    </div>
			                                                    <div class="total-price w_five tc">
			                                                      ${env.currency.symbols} ${product.totalAmt}
			                                                    </div>
			                                                  </div>
			                                                  <div class="cart-list-item-detail-info-wrap">
																  <c:set value="0" var="specNums"/>
		                                                    	<c:forEach items="${map.colors}" var="color">
		                                                    		<c:if test="${color.proId == product.id}">
					                                                    <div class="cart-list-item-detail-info-item flex-start">
							                                                    <div class="goods-pic">
							                                                    	<c:if test="${not empty color.img}">
							                                                    		<img src="${envConfig.imageBaseUrl}${color.img}">
							                                                    	</c:if>
							                                                    	<c:if test="${empty color.img}">
							                                                    		<img src="./static/images/noImage.png">
							                                                    	</c:if>
							                                                      </div>
							                                                       <!-- 同规格 - 商品数量 and 价格信息 -->
								                                                      <div class="goods-spec-wrap">
								                                                        <!-- 规格or颜色信息 -->
								                                                        <div class="spec-or-color"><s:text name="Global.Colour"/>:${color.color}</div>
								                                                        <!-- 同颜色下 不同型号的商品 item-list -->
								                                                        <div class="color-goods-list">

								                                                        	<c:forEach items="${map.specs}" var="spec" varStatus="specIndex">
								                                                        		<c:if test="${spec.colorId == color.id && spec.productId == product.id}">
																									<%--<input type="hidden" value="${supplier.id}" name="list2[${numbers}].supplierId"/>--%>
																									<input type="hidden" value="${spec.id}" name="list[${index.index}].list[${specNums}].id"/>
									                                                        		<div class="color-goods-item flex-center">
											                                                            <!-- 商品规格 -->

											                                                            <div class="goods-spec">
											                                                              <s:text name="Global.Size"/>: ${spec.size}
											                                                            </div>
											                                                            <!-- 商品价格 -->
											                                                            <div class="goods-price w_three">
											                                                              ${env.currency.symbols} ${spec.price}
											                                                            </div>
											                                                            <!-- 商品数量 -->
											                                                            <div class="goods-num flex-center w_four">
																											<input type="hidden" value="${spec.qty}" name="list[${index.index}].list[${specNums}].num"/>
											                                                              ${spec.qty}
											                                                            </div>
											                                                            <!-- 单个商品总价 -->
											                                                            <div class="total-price w_five">
											                                                              ${env.currency.symbols} <fmt:formatNumber type="number" maxFractionDigits="2" value="${spec.qty * spec.price}" /></p>
											                                                            </div>
											                                                        </div>
																									<c:set value="${numbers+1}" var="numbers"/>
																									<c:set value="${specNums+1}" var="specNums"/>
								                                                        		</c:if>
								                                                        	</c:forEach>
								                                                        </div>
								                                                  </div>
					                                                      <!-- 同规格 - 商品数量 and 价格信息 - end -->
					                                                    </div>





		                                                    	</c:if>
		                                                    	</c:forEach>

		                                                  </div>
		                                                	</div>
	                                                	</c:if>
                                                </c:forEach>
                                              </div>
                                              <!-- 仿tbody -->

                                                <div>
                                                 <!-- 留言 bengin -->
	                                              	<div class="remark" style="float:left;">
	                                                    <%--  Remark：
	                                                     <span>Material description or special requirements</span> --%>
	                                                     <span style="float: left;"><s:text name="Global.Remarks"/>:</span>
														    <textarea name="list[${index.index}].remarks" id="supRemark_${supplier.id}" data="${supplier.id}" type="text" placeholder="<s:text name='Global.Remarks'/>" style="padding:0 10px;width: 27em;height: 5em;float: left;margin-left: 13px;"></textarea>
														<div style="clear:both;"></div>
	                                                 </div>
	                                                  <!-- 留言 end -->
                                                 <!-- 单个采购点总价格 begin -->
	                                                 <div class="item-total" style="float:right;">
	                                                     <p><s:text name="user.orderTotal"/>(
	                                                         <span>${supplier.qty}</span><s:text name="Global.Double"/> ): ${env.currency.symbols}
	                                                         <span>${supplier.totalPrice}</span>
	                                                     </p>
	                                                 </div>

	                                                 <div style="clear:both;"></div>
                                                 </div>
	                                             <%--    <div class="cartBox" id="paymentObj">
									                    <h2><s:text name="Global.Payment_Method"/></h2>
									                    <div class="contents payment">
									                        <h3><s:text name="Global.Please_Select_Mode_Of_Payment"/>:</h3>
									                        <select name="odrView[${index.index}].payMethod" id="payMethod_${supplier.id}" data="${supplier.id}">
									                            <option value="-1">
									                            	<c:if test="${fn:length(map.payMethods[supplier.id]) == 0}">
								                            			<s:text name="The_Merchant_Has_Not_Set_A_Payment_Method"/>
									                            	</c:if>
									                            	<c:if test="${fn:length(map.payMethods[supplier.id]) > 0}">
									                            		<s:text name="Global.Please_Select_Mode_Of_Payment"/>
									                            	</c:if>
									                            </option>

									                            <c:forEach items="${map.payMethods[supplier.id]}" var="payMethod">
										                            <option value="${payMethod.id}" style="display: block;"><s:text name="pay.mode.%{#attr.payMethod.mode}" /></option>
									                            </c:forEach>
									                        </select>
									                        <div class="blank12"></div>
									                    </div>
									                    </div>--%>
									                    <!-- 快递公司选择BEGIN -->
										<%--                <div class="cartBox" id="express">
										                    <h2><s:text name="Global.Delivery_Method"/></h2>
										                    <div class="contents payment">
										                        <h3 style="font-size: 14px;font-weight: bold;line-height: 30px;display: block;"><s:text name="Global.Delivery_Method"/>:</h3>
										                        <select name="odrView[${index.index}].express" id="express_${supplier.id}" data="${supplier.id}">
										                            <option value="-1">
										                            	<c:if test="${fn:length(map.freights[supplier.id]) == 0}">
									                            			<s:text name="The_Merchant_Has_Not_Set_The_Courier_Mode"/>
										                            	</c:if>
										                            	<c:if test="${fn:length(map.freights[supplier.id]) > 0}">
										                            		 <s:text name="Global.Please_Select_Your_Shipping_Method"/>
										                            	</c:if>
										                           </option>
										                            <c:forEach items="${map.freights[supplier.id]}" var="express">
										                            	<option value="${express.id}" style="display: block;">${express.company}</option>
										                            </c:forEach>
										                        </select>
										                        <div class="blank6"></div>
										                    </div>
										                </div>--%>
										                <!-- 快递公司选择END -->

                                              <%--			<div>
											                <div class="NoteBox" style="float: left;margin-top: 0;">
											                    <h2 style="float: left;display: block;height: 38px;font: 400 16px/38px georgia;color: #990000;zoom: 1;text-indent: 12px;margin: 0 13px 0 0;"><s:text name="Order_Note_Content"/></h2>
											                    <div class="notes">
											                        <textarea plecaholder="<s:text name="Order_Note_Content"/>" id="odrRemarks_${supplier.id}" name="odrView[${index.index}].odrRemarks"></textarea>
											                    </div>
											                </div>

			                                               <table style="width: 36%;float: right;">
															    <tbody style="width: 100%;">
															        <tr style="width: 100%;border-top: 1px dotted #ccc;">
															            <td style="width: 86.5%;text-align: right;padding: 10px;">
															                <input value="Free Shipping" name="shippingMent" style="position: relative;top: 2px;left: -6px;" type="radio" checked="checked"><s:text name="product.is_free_shipping"/>:
															            </td>
															            <td style="color: #c80201;font-size: 16px;padding: 20px 0 20px 10px;">
															                <span><s:text name="FOB"/></span>
															            </td>
															        </tr>
															    </tbody>
															</table>
															<div style="clear:both;"></div>
														</div>--%>
                                                 <!-- 单个采购点总价格 end -->
                                            </div>

                                        	</c:forEach>

                                        </div>
                <div class="CartAmountSum">
<%--                    <table id="subTotal" cellpadding="0" cellspacing="0" border="0" width="100%">
                        <!-- Grand Total -->
                       <tfoot>
                            <tr id="cartAmount" style="display: table-row;">
                                <th width="100%"><s:text name="Global.Total_Price"/>:
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_total">${map.allAmt }</strong>
                                </td>
                            </tr>
                        </tfoot>
                        <tbody>

                            <tr style="display: table-row;">
                                <!-- subtotal -->
                                <th><s:text name="Global.Total_Price"/>( ${map.allQty } <s:text name="Global.Double"/> ):
                                    <em>${env.currency.symbols}</em>
                                </th>
                                <td>
                                    <strong id="ot_subtotal">${map.allAmt }</strong>
                                </td>
                            </tr>
                        </tbody>
                    </table>--%>
                    <fieldset id="submitCart" class="clearfix" style="display: block;">
                            <button type="button" id="orderSubmit" class="fr litb-btn placeOrderBtn"><s:text name="Place_Your_Order"/></button>
                        <p class="clearfix"><s:text name="Global.Every_Order_You_Place_With_Us_Is_Safe_And_Reliable"/></p>
                    </fieldset>
                </div>
                <input type="hidden" name="purchaseLine" value=""/>
               	<%-- <input type="hidden" name="carts" value="${cartPkeys}"/> --%>
               	<input type="hidden" name="currency" value="${env.currency.id}"/>
               	<input type="hidden" name="jsonCarts" value='${jsonCarts}' />
               	<input type="hidden" name="enterType" value='${enterType}' />
               	<%-- <input  type="hidden" name="buyNow" value="${dataMap.state}"/> --%>
               	<%-- <input type="hidden" name="specAndQty" value='${dataMap.qtyAndSpec}'/> --%>
            </form>



            <form action="/home/usr_UsrPurchaseLine_addmanagement" method="post" id="toSetBillAddress">
            	<input type="hidden" value='${jsonCarts}' name="jsonCarts"/>
            </form>
        </div>
    </div>

    <%@ include file="/home/template/new-foot.jsp" %>
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
	$("#sldProvince").text("")
	$("#setAddressId input[name='bean.region']").val("")
	var country = $(btn).attr("value");
	$("ul[id^=getProvinces]").hide();
	$("#getProvinces"+country).show();
	$("#setAddressId input[name='bean.country']").val(country);
	var countryName = $(btn).text();
	$("#country_chzn .chzn-single").text("");
	$("#country_chzn .chzn-single").append(countryName).append("<div><b></b></div>");
	//$("#sldCountry").append(countryName).append("<div><b></b></div>");
}

function chooseThisProvice(btn){
	var province = $(btn).attr("value");
	var provinceName = $(btn).text();
	$("#sldProvince").text(provinceName);
	$("#setAddressId input[name='bean.region']").val(province)
}

$("#lib_address input[type=radio][name=addressRadio]").on("change click",function(){
	var purchaseLine = $(this).val();
	$("#PlaceOrderFrom input[name=purchaseLine]").val(purchaseLine);
})

$(".toSetBillAddress").on("click",function(){
	$("#toSetBillAddress").submit();
})

$("#orderSubmit").on("click",function(){
	<%--var supplier = $(".supplier");--%>
	<%--for(var i=0;i<supplier.length;i++){--%>
		<%--var supId = $(supplier[i]).val();--%>
		<%--var payMethod = $("select[id=payMethod_"+supId+"]").val();--%>
		<%--var express = $("select[id=express_"+supId+"]").val();--%>
		 <%--if(payMethod == -1){--%>
			<%--layer.msg("<s:text name='Global.Please_Select_Mode_Of_Payment'/>",{icon:2,time:3000});--%>
			<%--return;--%>
		<%--}--%>
		<%--if(express == -1){--%>
			<%--layer.msg("<s:text name='Global.Please_Select_Your_Shipping_Method'/>",{icon:2,time:3000});--%>
			<%--return;--%>
		<%--}--%>
	<%--}--%>
	var address = $("#PlaceOrderFrom input[name=purchaseLine]").val();
	if(address == undefined || address == ""){
		layer.msg(lang_obj.addressfrom.Please_Select_The_Shipping_Address,{icon:2,time:2000});
		return;
	}
	$.ajax({
		url:'/home/eo_EasyOdr_generateOrder',
		type:'post',
		data:$("#PlaceOrderFrom").serialize(),
		dataType:'json',
		success:function(data){
			if(data.ret == 1){
				var orderNumbers = data.orderNumbers;
	/*			layer.msg(lang_obj.addressfrom.Submit_Order_Successfully,{icon:1,time:2000},function(){
					if(orderNumbers.indexOf(",") != -1){
						window.location.href = "/home/odr_OdrOrder_orders";
					}else{
						window.location.href = "/home/odr_OdrOrder_payOrder?orderNumber="+orderNumbers;
					}
				})*/
			}else{
				layer.msg(getMessage(data.msg));
			}
		}
	})
})

</script>
</html>
