<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/layer.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
    <style type="text/css">
        .fl tbody tr{
        	display: block;
        	margin: 0 0 10px 0;
        }
        .order_view{
        	position: absolute;
        	top:0px;
        	left: 69%
        }
        .order_base .order_view .order_btn{
        	display: table;
        }
    </style>
	<%@ include file="/home/template/web-top.jsp" %>
    <div id="main">
        <index-top></index-top>
        <div class="wide">
        <div id="lib_user" class="clearfix">
            <div id="lib_user_crumb" class="widget">
                <ul class="crumb_box clearfix">
                    <li class="home">
                        <a href="/" title="Home"><s:text name="home"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb1">
                        <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="my_account"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb2">
                        <a href="/home/odr_OdrOrder_orders" title="My Orders"><s:text name="my_orders"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="root">
                        <a href="/home/odr_OdrOrder_detail?orderId=${orderView.number }" title="${orderView.number }">${orderView.number }
                            <i></i>
                        </a>
                    </li>
                </ul>
            </div>
            <%@ include file="/home/template/account/lib-user-menu.jsp" %>
            <div id="lib_user_main">
                <script type="text/javascript">
                    $(document).ready(function () {
                        user_obj.order_init();
                        //渲染时间
                        var time = moment(Date.toLocale(${orderView.date }));
                        $("#lib_user_main .order_body .order_base table tr:eq(1) td").text(time.format('ll'));
                        $("#lib_user_main .order_body .order_menu.order_status tbody tr").each(function(){
                        	var date_td = $(this).find("td:eq(1)");
                        	var time_td = $(this).find("td:eq(2)");
                        	var time = moment(Date.toLocale(parseInt(date_td.text())))
                        	date_td.text(time.format("ll"))
                        	time_td.text(time.format("LT"))
                        })
                    });
                </script>
                <!--startprint-->
                <div class="order_body">
                    <div class="order_base">
                        <table class="fl" cellpadding="3" >
                            <tbody>
                                <tr>
                                    <th><s:text name="user.orderNumber"/>:</th>
                                    <td>${orderView.number }</td>
                                </tr>
                                <tr>
                                    <th><s:text name="user.orderDate"/>:</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><s:text name="user.orderStatus"/>:</th>
                                    <td><s:text name="orders.status.%{orderView.status }" /></td>
                                </tr>
                                <s:if test="orderView.cancelReason">
                                <tr>
                                    <th><s:text name="user.cancelReason"/></th>
                                    <td>
                                        <strong>:${orderView.cancelReason }</strong>
                                    </td>
                                </tr>
                                </s:if>
                                <tr>
                                    <th><s:text name="user.shippedTo"/>:</th>
                                    <td>
                                    	${orderView.shipAddress.name }
                                    	${orderView.shipAddress.surname }
                                    	(${orderView.shipAddress.address }
                                    	,${orderView.shipAddress.city }
                                    	,${orderView.shipAddress.postalCode }
                                    	-${orderView.shipAddress.region.name }
                                    	,${orderView.shipAddress.country.name }
                                    	)<br/>
                                    	<s:text name="Global.Telephone_Number"/>:
                                    	${orderView.shipAddress.phone }
                                    </td>
                                </tr>
                                <tr>
                                    <th><s:text name="Billing_Address"/>:</th>
                                    <td>
                                    	${orderView.billAddress.name }
                                    	${orderView.billAddress.surname }
                                    	(${orderView.billAddress.address }
                                    	,${orderView.billAddress.city }
                                    	,${orderView.billAddress.postalCode }
                                    	-${orderView.billAddress.region.name }
                                    	,${orderView.billAddress.country.name }
                                    	)<br/>
                                    	<s:text name="Global.Telephone_Number"/>:
                                    	${orderView.billAddress.phone }
                                    </td>
                                </tr>
                                <tr>
                                    <th><s:text name="Telephone"/>：</th>
                                    <td>${orderView.contactNum }</td>
                                </tr>
                                <tr>
                                    <th><s:text name="paymentMethod"/>:</th>
                                    <td><s:text name="pay.mode.%{orderView.payType.mode}" /></td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="order_view fr">
                            <s:if test="orderView.status==0">
	                            <a class="order_btn payment2btn" href="/home/odr_OdrOrder_payOrder?orderNumber=${orderView.number }" target="_blank" title="Go to T/T and complete your payment."><s:text name="Complete_Your_Payment"/></a>
                            </s:if>
                            <s:if test="orderView.status!=6&&orderView.status!=7">
                            	<a class="order_btn" href="/home/odr_OdrOrder_junpOrderPrint?orderId=${orderView.number }" target="_blank"><s:text name="user.printOrder"/></a>
                            </s:if>
                            <s:if test="orderView.status!=0&&orderView.status!=7">
                            	<div class="payment_info">
	                                <h3><s:text name="user.paymentInfo"/>:</h3>
	                                <s:iterator value="orderView.payContent" var="line">
		                                <div class="rows">
		                                    <strong><s:property value="key"/></strong>
		                                    <span><s:property value="value"/></span>
		                                </div>
	                                </s:iterator>
	                            </div>
                            </s:if>
                            <input id="panInfo" type="hidden" value="${orderView.payContent }" />
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="order_menu order_status">
                        <h3><s:text name="user.orderStatus"/></h3>
                        <table cellpadding="0" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th width="25">&nbsp;</th>
                                    <th width="200"><s:text name="user.date"/></th>
                                    <th width="100"><s:text name="user.time"/></th>
                                    <th><s:text name="user.status"/></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<s:iterator value="orderView.historys" var="history">
                            	 <tr>
                                    <td>&nbsp;</td>
                                    <td>${history.date}</td>
                                    <td>${history.date}</td>
                                    <td>
                                    	<s:text name="orders.status.%{#history.status }" />
                                    	<s:if test="orderView.status==4&&#history.status==4">
                                    		&nbsp;<a class=" order_btn confirmReceiving_btn" href="javascript:;" style="text-decoration:underline"><s:text name="user.receiving"/></a>
                                    		<script type="text/javascript">
	                                    		$("#lib_user_main .confirmReceiving_btn").on("click", function() {
                                                    layer.confirm(lang_obj.mobile.Confirm_receipt,{btn:[lang_obj.global.confirm,lang_obj.global.cancel], icon: 3} ,function(){
                                                        $.ajax({
		        											url:"/home/odr_OdrOrder_confirmReceiving",
		        											type:"post",
		        											data:{orderNumber:"${orderView.number }"},
		        											dataType:"json",
		        											success:function(response) {
		        												if(response.ret==1) {
				                                    				layer.msg(lang_obj.mobile.Successful_receipt, {icon: 1,time:1000},function(){
                                                                        location.href = location.href;
                                                                    });
		        												} else if(response.ret == -1) {
		        													location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/home/odr_OdrOrder_confirmReceiving?orderId=${orderView.number }";
		        												} else if(response.msg){
		        													layer.msg(response.msg, {icon: 2});
		        												}
		        											}
		        										})
                                                    }, function(){
                                                    })
	        									})
                                    		</script>
                                    	</s:if>
                                    </td>
                                </tr>
                            	</s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="order_menu order_summary">
                        <h3><s:text name="cart.summary"/></h3>
                        <table cellpadding="0" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th><s:text name="comment.product"/></th>
                                    <th><s:text name="Global.Proposed_Price"/></th>
                                    <th><s:text name="Global.Quantity"/></th>
                                    <th><s:text name="Global.Total_Price"/></th>
                                </tr>
                            </thead>
							<tbody>
                                <tr>
                                    <td>
                                        <div style="padding:5px 0;"></div>
                                        <div style="padding:5px 0;"><s:text name="Global.Payment_Method"/>：${orderView.deliveryType }</div>
                                        <div style="padding:5px 0;"><s:text name="Global.Freight"/>：<span id="freightPrice">${orderView.shippingCharges }</span></div>
                                        <div style="padding:5px 0;"><s:text name="user.orderStatus"/>：
                                        	<s:text name="orders.status.%{orderView.status }" />
                                        </div>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>

                                <c:forEach items="${orderView.lines }" var="line"  varStatus="index">
	                                <tr>
	                                    <td class="pro_list">
	                                        <dl class="clearfix">
	                                            <dt>
	                                                <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${line.spec.product }"
	                                                    title="" target="_blank">
	                                                    <img src="${envConfig.imageBaseUrl}${line.spec.images }" title=""
	                                                        alt="" />
	                                                </a>
	                                            </dt>
	                                            <dd>
	                                                <h4>
	                                                    <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${line.spec.product }"
	                                                        title="" target="_blank">
	                                                    	${line.spec.productName }
	                                                    </a>
	                                                </h4>
	                                                <p class="pro_attr">${line.spec.productCode } </p>
	                                                <p class="pro_attr"><s:text name="spec.size"/>:${line.spec.size } </p>
	                                                <p class="pro_attr"><s:text name="spec.color"/>:${line.spec.color } </p>
	                                            </dd>
	                                        </dl>
	                                    </td>
	                                    <td class="pro_price">
	                                        <span>${line.spec.price }</span>
	                                    </td>
	                                    <td class="pro_qty">${line.qty }</td>
	                                    <td class="pro_amount">
	                                        <span class="subtotal-price">${line.subtotal}</span>
	                                    </td>
	                                </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="grand_total">
                        <table cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <th><s:text name="user.orderTotal"/> (${orderView.itemsCount }<s:text name="Global.Double"/>):
                                        <em>${orderView.currency }</em>
                                    </th>
                                    <td>${orderView.subtotal }</td>
                                </tr>
                                <tr>
                                    <th><s:text name="shipInsurance"/>:
                                        <em>${orderView.currency }</em>
                                    </th>
                                    <td>${orderView.shippingChargesAndInsurance }</td>
                                </tr>
                                <tr>
                                    <td colspan="2"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th width="100%" class="totalprod"><s:text name="Global.Total_Price"/>:
                                        <em>${orderView.currency }</em>
                                    </th>
                                    <td id="total" class="totalPrice">${orderView.total }</td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            </div>
                	<!--endprint-->
        </div>
        <index-bottom></index-bottom>
    </div>
    <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>
    <script>
        new Vue({
            el:"#main"
        })
    </script>
</body>
</html>
