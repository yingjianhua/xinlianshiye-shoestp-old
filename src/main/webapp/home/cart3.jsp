<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link href="./static/css/cart.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>
    <script src="./static/js/swiper.min.js"></script>
    <script src="./static/js/lazyload.min.js"></script>
    <script type="text/javascript" src="./static/js/cart.js"></script>
    <script type="text/javascript" src="./static/js/tool_tips_web.js"></script>

</head>

<body class="lang_en w_1200">

	<%@ include file="/home/template/web-top.jsp" %>
    <div id="main">
        <index-top></index-top>
        <div class="wide">

        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                cart_obj.complete_init()
            });
        </script>
        <div id="lib_cart">
            <div class="position">
                <a href="/"><s:text name="Global.Home"/></a> &gt;
                <a href="/home/usr_UsrCart_cartshopping"><s:text name="Global.Shopping_Cart"/></a> &gt;
                <strong><s:text name="cart3.Payment"/></strong>
            </div>
            <div class="blank12"></div>
            <div class="complete">
                <div class="tips fl">
                    <h3><s:text name="cart3.Thank_Shopping"/></h3>
                    <div class="payment_info">
						<c:forEach items="${orderView.payType.setting }" var="ps">
						<label id="${ps.key}"></label>: ${ps.value}<br>
						<script>
							$("#${ps.key}").html(lang_obj.home.${ps.key});
						</script>
						</c:forEach>
						<br>
						<br> <s:text name="cart3.Payee"/> ：${orderView.supplier.name}&nbsp;
						<br> <s:text name="cart3.Payee_Address"/>&nbsp; : &nbsp;${orderView.supplier.companyAddr}&nbsp;
                    </div>
                    <div class="editAddr pay_form">
                        <form method="post" id="PaymentForm">
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
                                            <span class="required">*</span>
                                            <label><s:text name="cart3.Sender_Name"/>:</label>
                                        </th>
                                        <td class="recipient">
                                            <div>
                                                <input type="text" name="FirstName" maxlength="32" notnull="">
                                                <p>
                                                    <span class="required">*</span>&nbsp;<s:text name="Global.First_Name"/></p>
                                            </div>
                                            <div>
                                                <input type="text" name="LastName" maxlength="32" notnull="">
                                                <p>
                                                    <span class="required">*</span>&nbsp;<s:text name="Global.Surname"/></p>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <span class="required">*</span>
                                            <label><s:text name="cart3.Currency"/>:</label>
                                        </th>
                                        <td>
                                            <select name="currency">
                                            	<c:forEach items="${currencys}" var="c" varStatus="vs">
	                                                <option value="${c.id}" <c:if test="${vs.first }">selected="selected"</c:if>>${c.shortName}</option>
                                            	</c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <span class="required">*</span>
                                            <label><s:text name="cart3.Remittance"/>:</label>
                                        </th>
                                        <td>
                                            <input type="text" name="SentMoney" maxlength="8" value="" notnull="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <span class="required">*</span>
                                            <label><s:text name="cart3.Reference_Number"/>:</label>
                                        </th>
                                        <td>
                                            <input type="text" name="MTCNNumber" maxlength="8" value="" placeholder="<s:text name="Order-MtNumber"/>"
                                                title="<s:text name="cart3.8Digits"/>" notnull="" format="Length|8">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <label><s:text name="Global.Remarks"/>:</label>
                                        </th>
                                        <td>
                                            <textarea name="Contents" rows="4" cols="49"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td>
                                            <button id="paySubmit" class="textbtn"><s:text name="Global.Submit"/></button>
                                            <a href="javascript:void(0);" id="Cancel" class="textbtn"><s:text name="Global.Cancel"/></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" name="bean.orderNum" value="${orderView.number}">
                        </form>
                    </div>
                </div>
                <div class="orders_info fl" id="doubleorder">
                    <h3><s:text name="Global.Order_Details"/></h3>
                    <div class="rows" >
                        <label><s:text name="Global.Order_Number"/>: </label>
                        <span class="red">
                        	<img src="<c:if test='${orderView.orderType == 1}'>./static/images/jpcartjptb222.png</c:if><c:if test='${orderView.orderType == 0}'>./static/images/jpcartnptb222.png</c:if>" alt="" />${orderView.number }
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label><s:text name="cart3.Total_Amount"/>:</label>
                        <span class="red">${orderView.total }</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label><s:text name="cart3.Product_Quantity"/>:</label>
                        <span>${orderView.itemsCount}</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label><s:text name="Global.Order_Status"/>:</label>
                        <span><s:text name="orders.status.%{orderView.status }" /></span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label><s:text name="Global.Payment_Method"/>:</label>
                        <span><s:text name="pay.mode.%{orderView.payType.mode}" /></span>
                        <div class="clear"></div>
                    </div>
                    <c:if test="${orderView.status==0}">
                    <a class="textbtn payButton"><s:text name="cart3.Immediate_Payment"/></a>
                    </c:if>
                    <c:if test="${orderView.status==1}">
					<a class="textbtn payStateButton" href="/home/odr_OdrOrder_orders"><s:text name="cart3.Waiting_For_Confirmation"/></a>
                    </c:if>
                </div>
                <div class="blank12"></div>
                <div class="product_list"></div>
            </div>
            <div class="blank12"></div>
        </div>
        <index-bottom></index-bottom>
    </div>

     <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
    <div id="hj_top" style="opacity: 0;">
        <img src="/home/static/images/hj_top.png">
    </div>

    <!-- Google Tag Manager -->
    <script type="text/javascript" async="" src="./static/js/js.js"></script>
    <script type="text/javascript" async="" src="./static/js/tracking.js"></script>
    <script type="text/javascript" async="" src="./static/js/analytics.js"></script>
    <script async="" src="./static/js/gtm.js"></script>
    <script>
        new Vue({
            el:"#main"
        })
    </script>
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>
</body>

</html>
