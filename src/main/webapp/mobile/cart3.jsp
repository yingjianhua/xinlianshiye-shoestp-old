<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- saved from url=(0057)https://www.shoestp.com/cart/complete/18071614040668.html
-->
<html lang="us">

    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <script type="text/javascript" src="/home/static/themes/default/mobile/js/cart.js"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'UA-122336495-1');

            $(function() {
                cart_obj.complete()
            });
        </script>
        <style type="text/css">
            .cart_step>div.current{background-color:#333333;} .cart_step>div.current>i{border-color:transparent
            transparent transparent #333333;}
            .whalf{
            	width: 48%;
            }
        </style>
    </head>

    <body>
		<%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div id="cart">

                <div class="cart_step clean">
                    <div class="step_0">
                        <!-- Place Order -->
                        <s:text name="mobile.place_order" />
                        <i>
                        </i>
                    </div>
                    <div class="step_1 <c:if test="${orderView.status == 0}">current</c:if>">
                        <em>
                        </em>
                        <!-- Pay -->
                        <s:text name="mobile.pay_method" />
                        <i>
                        </i>
                    </div>
                    <div class="step_2 <c:if test="${orderView.status == 1}">current</c:if>" >
                        <em>
                        </em>
                        <!-- Completed -->
                        <s:text name="mobile.com_your_pay" />
                    </div>
                </div>
                <div class="complete_box">
                	<c:if test="${orderView.status == 1}">
	                    <div class="complete_tips">
	                        <!-- Thank you for shopping with us! Your order has been received! -->
	                        <s:text name="cart.thanks" />
	                    </div>
                    </c:if>

                    <div class="pay_info">

                        <div class="title">
                            <!-- Order Summary -->
                            <s:text name="Global.Order_Details" />
                        </div>
                        <div class="rows">
                            <strong>
                                <!-- Order Number -->
                                <s:text name="user.orderNumber" />:
                            </strong>
                            <span>
                                ${orderView.number}
                            </span>
                        </div>
                        <div class="rows">
                            <strong>
                                <!-- Total Amount -->
                                <s:text name="user.amount" />:
                            </strong>
                            <span>
                                ${orderView.total}
                            </span>
                        </div>
                        <div class="rows">
                            <strong>
                                <!-- Number of Item -->
                                <s:text name="order_line.qty" />:
                            </strong>
                            <span>
                            	${orderView.itemsCount }
                            </span>
                        </div>
                        <div class="rows">
                            <strong>
                                <!-- Order Status -->
                                <s:text name="mobile.order_status" />:
                            </strong>
                            <span>
                            		<s:text name="orders.status.%{orderView.status}" />
                            </span>
                        </div>
                        <div class="rows">
                            <strong>
                                <!-- Payment Method -->
                                <s:text name="mobile.pay_method" />:
                            </strong>
                            <span>
								<s:text name="pay.mode.%{orderView.payType.mode}" />
                            </span>
                        </div>
                    </div>
                    <form method="post" action="" class="pay_form" id="PaymentForm">
                    	<c:if test="${orderView.status == 0}">
                        <div class="rows">
                            <div class="field">
								<!-- Sender's Name -->
                                <s:text name="review.f_name" />:
                            </div>
                            <div class="input clean">
                                <span class="input_span fl whalf">
                                    <input type="text" class="box_input" placeholder="First Name" name="FirstName" >
                                </span>
                                <span class="input_span fr whalf">
                                    <input type="text" class="box_input" placeholder="Last Name" name="LastName" >
                                </span>
                            </div>
                        </div>
                        <div class="rows">
                            <div class="field">
                                <!-- Currency -->
                                <s:text name="currency" />:
                            </div>
                            <div class="input clean">
                                <div class="box_select">
                                    <select class="addr_select" name="currency" notnull="">
                                    	<c:forEach items="${currencys}" var="c" varStatus="vs">
	                                        <option value="${c.id}" <c:if test="${vs.first }" >selected="selected"</c:if>>
	                                            ${c.shortName}
	                                        </option>
                                    	</c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="rows">
                            <div class="field">
                                <!-- Sent Money -->
                                <s:text name="cart3.Remittance" />:
                            </div>
                            <div class="input clean">
                                <span class="input_span">
                                    <input type="text" class="box_input" name="SentMoney" maxlength="8" notnull="">
                                </span>
                            </div>
                        </div>
                        <div class="rows">
                            <div class="field">
                                <!-- Reference Number -->
                                <s:text name="cart.mtcn_num" />:
                            </div>
                            <div class="input clean">
                                <span class="input_span">
                                    <input type="text" class="box_input" name="MTCNNumber" placeholder="8 Digits"
                                    maxlength="8" format="Length|8" notnull="">
                                </span>
                            </div>
                        </div>
                        <div class="rows">
                            <div class="field">
                                <!-- Contents -->
                                <s:text name="Global.Remarks" />:
                            </div>
                            <div class="input clean">
                                <span class="input_span">
                                    <textarea name="Contents" class="box_input box_textarea">
                                    </textarea>
                                </span>
                            </div>
                        </div>
                        </c:if>
                        <div class="pay_button">
                        	<c:if test="${orderView.status == 0}">
                            <span class="btn_global btn btn_pay BuyNowBgColor" id="paybtn">
                                <!-- Submit -->
                                <s:text name="mobile.submit" />
                            </span>
                            </c:if>
                            <c:if test="${orderView.status == 0||orderView.status == 1}">
                            <a class="btn_global btn btn_view_order" href="/home/odr_OdrOrder_orders">
                                <!-- View Order -->
                                <s:text name="mobile.view_order" />
                            </a>
							</c:if>
                        </div>
                        <!-- 待灵活字段 -->
                        <input type="hidden" name="bean.state" value="1">
                    </form>
                    <div class="blank15"></div>
                </div>
            </div>
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
        <div class="foot_blank">
        </div>
        <%@ include file="/mobile/template/foot_menu.jsp" %>
        <div align="center">
        </div>
    </body>
	<script type="text/javascript">
		$("#paybtn").on("click",function(){
			var firstName=$("#PaymentForm input[name='FirstName']").val()
			var lastName=$("#PaymentForm input[name='LastName']").val()
			var Name=firstName+" "+lastName
			var SentMoney=$("#PaymentForm input[name='SentMoney']").val()
		    var MTCNNumber=$("#PaymentForm input[name='MTCNNumber']").val()
			var Contents=$("#PaymentForm textarea[name='Contents']").val().trim()
			if(Name == undefined || Name == ""){
				$('html').tips_box(lang_obj.Name_cannot, 'error');
				return;
			}
			if(SentMoney == undefined || SentMoney == ""){
				$('html').tips_box(lang_obj.The_amount_cannot, 'error');
				return;
			}

			var payContent='{"Name":"'+Name+'","SentMoney":"'+SentMoney+'","MTCNNumber":"'+MTCNNumber+'"}';

			var param = {};
			param['orderNumber'] = getParam("orderNumber");
			param['currency'] = $("#PaymentForm select[name='currency']").val();
			param['payContent'] = payContent;
		    $.post('/home/odr_OdrOrder_pay',param, function(data){
				if(data.ret==1){
					window.top.location.reload();
				}else{
					$('html').tips_box(data.msg, 'error');
				}
			}, 'json');
		})

		function getParam(str){
				var url = window.location.href;
				var param = {};
				var paramStr = url.substring(url.indexOf("?")+1);
				var paramArr = paramStr.split("&");
				for(var i=0;i<paramArr.length;i++){
					var params = paramArr[i].split("=");
					param[params[0]] = params[1];
				}
				return param[str];
			}
	</script>
</html>
