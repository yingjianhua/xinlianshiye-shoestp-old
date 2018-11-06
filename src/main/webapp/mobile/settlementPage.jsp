<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <script type="text/javascript" src="/home/static/themes/default/mobile/js/cart.js"></script>
        <script type="text/javascript">
            $(function() {
                cart_obj.cart_checkout();
            });
        </script>
        <style type="text/css">
            .cart_step>div.current{background-color:#333333;} .cart_step>div.current>i{border-color:transparent
            transparent transparent #333333;}
        </style>
    </head>

    <body>
        <%@ include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div id="cart">
                <div class="cart_step clean">
                    <div class="step_0 current">
                        <!-- Place Order -->
                        <s:text name="mobile.place_order" />
                        <i>
                        </i>
                    </div>
                    <div class="step_1">
                        <em>
                        </em>
                        <!-- Pay -->
                        <s:text name="mobile.com_your_pay" />
                        <i>
                        </i>
                    </div>
                    <div class="step_2">
                        <em>
                        </em>
                        <!-- Completed -->
                        <s:text name="orders.status.5" />
                    </div>
                </div>
                <div class="cart_checkout">
                <form id="PlaceOrderFrom">
                	<%-- <input type="hidden" name="carts" value="${cartPkeys}"/> --%>
                    <div class="checkout_box">
                        <div class="title ui_border_b">
                         <!-- Shipping Address -->
                         <s:text name="purchase_line.addrsstype.0" />
                        </div>
                        <div class="txt" id="address_list">
                        	<div class="address_row">
                                <strong>
                                	<c:set var="defaultAddress" value="${map.defaultAddress}"/>
                                    ${defaultAddress.name} ${defaultAddress.surname} 
                                    <c:forEach items="${map.countries}" var="country">
	                                	<c:if test="${country.pkey == defaultAddress.country}">
	                                		${country.name}
	                                	</c:if>
	                                </c:forEach>
                                </strong>
                                <p>
                                	<c:forEach items="${map.provinces}" var="province">
	                                	<c:if test="${province.pkey == defaultAddress.region}">
	                                		${country.name}
	                                	</c:if>
	                                </c:forEach>
                                    , ${defaultAddress.city}, ${defaultAddress.address} , ${defaultAddress.emailcode}
                                </p>
                                <p>
                                    ${defaultAddress.phonenumber }
                                </p>
                                <input type="hidden" value="${defaultAddress.pkey}" name="purchaseLine"/>
                                <input type="hidden" value="${env.currency.id}" name="currency"/>
                                <input type="hidden" name="jsonCarts" value='${jsonCarts}' />
                                <input type="hidden" name="enterType" value='${enterType}' />
                                <!-- <input type="hidden" value="" id="cartPkeys" name="carts" /> -->
                            </div>
                            <div class="address_btn">
                                <a onclick="toChangeAddress()" class="btn_global btn change">
                                    <!-- Change Shipping Address -->
                                    <s:text name="mobile.change_ship" />
                                </a>
                                <a onclick="toAddNewAddress()" class="btn_global btn add_address FontBgColor FontBorderColor">
                                    <em>
                                        +
                                    </em>
                                    <!-- Add a New Shipping Address -->
                                    <s:text name="mobile.add_new_addr" />
                                </a>
                            </div>
                        </div>
                    </div>

                        <div class="checkout_divide">
                        </div>
                        <div class="checkout_box">
                        	<div class="checkout_box">
	                            <div class="title ui_border_b">
	                               <!--  Order Summary -->
	                                <s:text name="mobile.order_summary" />
	                               
	                            </div>
	                            <div class="txt">
	                                <div class="cart_item_list">
	                                
	                                
	                                	<c:forEach items="${map.products}" var="product">
	                                	<c:forEach items="${map.colors}" var="proColor">
	                                		<c:if test="${proColor.proId == product.id}">
		                                		<div class="item clean ui_border_b">
		                                        <div class="img fl">
		                                            <a href="">
		                                                <img src="${envConfig.imageBaseUrl}${proColor.img}" alt="${proColor.name}">
		                                            </a>
		                                        </div>
		                                        <div class="info">
		                                            <div class="name">
		                                                <a href="">
		                                                    ${proColor.name}
		                                                </a>
		                                            </div>
		                                            <div class="rows clean">
		                                                <!-- Color -->
		                                                <s:text name="color" />: &nbsp;${proColor.color}
		                                            </div>
		                                            <div class="rows clean">
		                                                <!-- Size -->
		                                                <s:text name="product.size_attr" />: &nbsp;
		                                                <c:forEach items="${map.specs}" var="spec">
		                                                	<c:if test="${spec.colorId == proColor.id && spec.productId == product.id}">
			                                                		${spec.size}/
		                                                	</c:if>
		                                                </c:forEach>
		                                            </div>
		                                            <div class="rows clean">
		                                               <!--  Quantity -->
		                                                <s:text name="consult.quantity" />: &nbsp;${proColor.qty}
		                                            </div>
		                                            <div class="price">
		                                                ${env.currency.symbols}${proColor.totalPrice}
		                                            </div>
		                                        </div>
		                                    	</div>
	                                    	</c:if>
	                                	</c:forEach>
	                                	</c:forEach>
	                                	
	                                	
	                                </div>
	                            </div>
                        	</div>
                            <div class="title ui_border_b">
                                <s:text name="shippingMethod" /><!-- Shipping --> &amp; <!-- Delivery --><s:text name="cart.delivery" />
                                
                            </div>
                            <div class="txt">
                                    <dl>
                                    	<c:forEach items="${map.suppliers}" var="supplier" varStatus="index">
                                    		<div class="checkout_divide">
						                        </div>
                                    		<dt>
	                                            ${supplier.name}
	                                            <input type="hidden" class="supplier" name="odrView[${index.index}].supplier" value="${supplier.id}"/>
	                                        </dt>
                                       <dd>
                                       		<div class="checkout_box" id="paymentObj">
					                            <div class="title ui_border_b">
					                                <!-- Payment Method -->
					                                <s:text name="cart.paymethod" />
					                            </div>
					                            <div class="txt">
					                                <div class="choose_pay">
					                                    <!-- Choose payment method -->
					                                    <s:text name="cart.choosepay" />:
					                                </div>
					                                <div class="box_select">
					                                    <select name="odrView[${index.index}].payMethod" id="payMethod_${supplier.id}" class="payment_method">
					                                        <option value="-1">
					                                            <!-- Please select your payment method -->
					                                            <s:text name="cart.selectpay" />
					                                        </option>
								                             <c:forEach items="${map.payMethods[supplier.id]}" var="payMethod">
								                            	<option value="${payMethod.id}" style="display: block;"><s:text name="pay.mode.%{#attr.payMethod.mode}" /></option>
								                            </c:forEach>
					                                    </select>
					                                </div>
						                               <!--  <ul class="payment_list">
						                                    <li id="payment_txt">
						                                        &nbsp;
						                                    </li>
						                                    <li id="payment_contents_10" fee="0.00" affix="0.00" style="display:none;">
						                                        <p>
						                                            INTERMEDIARY BANK SWIFT： ICBKUS33
						                                            <br>
						                                            INDUSTRIAL AND COMMERCIAL BANK OF CHINA(NEW YORK)
						                                            <br>
						                                            BENEFICIARY BANK&nbsp; &nbsp;SWIFT： OHBKCNSH
						                                            <br>
						                                            ZHEJIANG WENZHOU OUHAI RURAL COMMERCIAL BANK COMPANY LIMITED
						                                            <br>
						                                            BENEFICIARY BANK ADD ：FUSEN BUILDING FLOOR 1-3 STATION ROAD，WENZHOU，ZHEJIANG,CHINA
						                                            <br>
						                                            ZIP CODE:325000
						                                            <br>
						                                            <br>
						                                            BENEFICIARY ：WENZHOU NEW-UNION INDUSTRIAL CO LTD&nbsp;
						                                            <br>
						                                            BENEFICAIRY ADD&nbsp; : &nbsp;NO.51 FUHAO RD,QUXI ST,WENZHOU,ZHEJIANG,CHINA.325000&nbsp;
						                                            <br>
						                                            BENEFICIARY A/C NO. ：201000169314392 &nbsp;
						                                        </p>
						                                    </li>
						                                </ul> -->
					                            </div>
					                        </div>
					                        <!-- <div class="checkout_divide">
						                        </div> -->
						                        <div class="checkout_box" id="paymentObj">
						                            <div class="title ui_border_b">
						                                <!-- Delivery Method -->
						                                <s:text name="order.delivery" />
						                            </div>
						                            <div class="txt">
						                                <div class="choose_pay">
						                                    <!-- Choose delivery method -->
						                                    <s:text name="cart.shipmethod" />:
						                                </div>
						                                <div class="box_select">
						                                    <select name="odrView[${index.index}].express" id="express_${supplier.id}" class="payment_method">
						                                        <option value="-1">
						                                            <!-- Please select your delivery method -->
						                                            <s:text name="cart.shipmethod" />
						                                        </option>
									                             <c:forEach items="${map.freights[supplier.id]}" var="express">
										                            	<option value="${express.id}" style="display: block;">${express.company}</option>
									                            </c:forEach>
						                                    </select>
						                                </div>
						                            </div>
						                        </div>
                                           <%--  <input type="radio" name="delivery" value="Free Shipping" checked="checked">
                                            <!-- Free mail -->
                                            <s:text name="products.freeShipping" />
                                            <span>
                                                (<!-- Free Shipping --><s:text name="free_shipping" />)
                                            </span> --%>
                                        </dd>
                                    	</c:forEach>
                                        
                                    </dl>
                                    <!-- <dl>
                                        <dt>
                                            ZHEJIANG JIANSHA SHOES CO.,LTD
                                        </dt>
                                        <dd>
                                            <input type="radio" name="_shipping_method[279]" value="260" price="0"
                                            insurance="0" shippingtype="" cid="220" checked="checked">
                                            Free mail
                                            <span>
                                                (Free Shipping)
                                            </span>
                                        </dd>
                                    </dl> -->
                                </div>
                        </div>
                        <div class="checkout_divide">
                        </div>
                        
                        <div class="checkout_divide">
                        </div>
                        
                        <div class="checkout_summary ui_border_tb">
                            <div class="clean">
                                <!-- subtotal -->
                                <div class="key">
                                    <!-- Subtotal --> 
                                    <s:text name="cart.subtotal" />(${map.allQty} <s:text name="items" />):
                                </div>
                                <div class="value">
                                    ${env.currency.symbols}
                                    <span id="ot_subtotal">
                                        ${map.allAmt}
                                    </span>
                                </div>
                            </div>
                            <div class="clean" >
                                <!-- Total -->
                                <div class="key">
                                    <!-- Grand Total -->
                                    <s:text name="mobile.grand_total" />:
                                </div>
                                <div class="value">
                                    ${env.currency.symbols}
                                    <span>
                                        ${map.allAmt}
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="checkout_button">
                            <div class="btn_global btn BuyNowBgColor" id="cartCheckout">
                                <!-- Place Your Order -->
                                <s:text name="mobile.place_order" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <script type="text/javascript">
                var address_count = 19;
                var address_perfect = 0;
            </script>
        </div>
        <footer>
            <div id="prolist_mask_footer">
            </div>
            <div class="footer_top clean">
            </div>
            <ul class="footer_list ui_border_b clean" style="display:none;">
                <li class="fl" style="border-right:1px solid #ddd;">
                    <a href="" class="clean">
                        <span class="list_left">
                            <s:text name="sign_in" />
                        </span>
                        <span class="list_right">
                            <em>
                                <i>
                                </i>
                            </em>
                        </span>
                    </a>
                </li>
            </ul>
            <nav>
            </nav>
            <section class="font_col border_col copyright">
                Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1
                浙公网安备 33030402000493号
            </section>
        </footer>
        <div class="pop_up language_side">
            <div class="pop_up_container clean">
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <div class="menu_list">
                </div>
            </div>
        </div>
        <div class="pop_up currency_side">
            <div class="pop_up_container clean">
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <div class="menu_list">
                    <div class="item">
                        <a href="javascript:;" data="USD">
                            <img src="/home/static/themes/default/mobile/images/53092c531f.jpg" alt="USD">
                            ${env.currency.shortName}
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/mobile/template/foot_menu.jsp" %>
        <div align="center">
            <!-- Google Tag Manager (noscript) -->
            <noscript>
                &lt;iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KNPHSJ6"
                height="0" width="0" style="display:none;visibility:hidden"&gt;&lt;/iframe&gt;
            </noscript>
            <!-- End Google Tag Manager (noscript) -->
        </div>
	<form action="/home/usr_UsrPurchaseLine_addmanagement" method="post" id="toChangeAddress">
     	<input type="hidden" value='${jsonCarts}' name="jsonCarts"/>
     </form>
     <form action="/home/usr_UsrPurchaseLine_addressform" method="post" id="toAddNewAddress">
      	<input type="hidden" value='${jsonCarts}' name="jsonCarts"/>
      </form>
    <script type="text/javascript">
    	window.onload = function(){
    		var cartPkeys = getParam().cartPkeys;
    		$("#PlaceOrderFrom input[name=carts]").val(cartPkeys);
    	}
    	
    	$("#cartCheckout").on("click",function(){
    		//console.log($("#PlaceOrderFrom").serialize());
    		var supplier = $(".supplier");
			for(var i=0;i<supplier.length;i++){
				var supId = $(supplier[i]).val();
				var payMethod = $("select[id=payMethod_"+supId+"]").val();
				var express = $("select[id=express_"+supId+"]").val();
				 if(payMethod == -1){
					 $('html').tips_box(lang_obj.cart.payment_error, 'error');
					return;
				}
				if(express == -1){
					 $('html').tips_box(lang_obj.cart.shipping_error, 'error');
					return;
				} 
			}
    		$.ajax({
    			url:'/home/odr_OdrOrder_generateOrder',
    			type:'post',
    			data:$("#PlaceOrderFrom").serialize(),
    			dataType:'json',
    			success:function(data){
    				if(data.ret == 1){
    					var orderNumbers = data.orderNumbers;
    					 $('html').tips_box(lang_obj.addressfrom.Submit_Order_Successfully, 'error');
    					 setTimeout(function(){
	    					if(orderNumbers.indexOf(",") != "-1"){
	    						window.location.href = "/home/odr_OdrOrder_orders";
	    					}else{
	    						window.location.href = "/home/odr_OdrOrder_payOrder?orderNumber="+orderNumbers;
	    					}
    					 },2000)
    				}else{
    					 $('html').tips_box(getMessage(data.msg), 'error');
    				}
    			}
    		})
    	})

    	function getParam(){
    		var str = window.location.href.substring(window.location.href.indexOf("?")+1);
    		var strArr = str.split("&");
    		var param = {};
    		for(var i=0;i<strArr.length;i++){
    			param[strArr[i].split("=")[0]] = strArr[i].split("=")[1];
    		}
    		return param;
    	}

    	function toChangeAddress(){
    		$("#toChangeAddress").submit();
    		//window.location.href = "/home/usr_UsrPurchaseLine_addmanagement?jumpUrl="+window.location.pathname + window.location.href.substring(window.location.href.indexOf("?"));
    	}

    	function toAddNewAddress(){
    		$("#toAddNewAddress").submit();
    		//window.location.href = "/home/usr_UsrPurchaseLine_addressform?jumpUrl="+window.location.pathname + window.location.href.substring(window.location.href.indexOf("?"));
    	}
    </script>
  </body>
</html>
