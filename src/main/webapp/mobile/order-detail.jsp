<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>

    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag() {
            dataLayer.push(arguments);
        }
        gtag('js', new Date());
        gtag('config', 'UA-122336495-1');
        gtag('config', 'UA-127715615-6');
    </script>

    <script type="text/javascript">
  		$(document).ready(function () {
  			//user_obj.user_order()
  		//渲染时间
  		var time = moment(Date.toLocale(${orderView.date }));
  			$("#user .order_detail .detail_box .txt:eq(1) .rows:eq(2) span").text(time.format('ll'));
  		});
  	</script>
</head>

<body>
	<%@include file="/mobile/template/header.jsp" %>
	<div id="user">
		<div class="crumb clean">
			<a href="/">
				<span class="icon_crumb_home"></span>
			</a>
			<em><i></i></em>
			<a href="/home/usr_UsrPurchase_userIndex"><!-- My Account --><s:text name="my_account" /></a>
			<em><i></i></em>
			<a href="/home/odr_OdrOrder_orders">
				<!-- My Orders -->
				<s:text name="my_orders" />

			</a>
		</div>
		<div class="divide_8px ui_border_b">
		</div>
		<div class="order_detail">
		<div class="detail_box ui_border_b">
			<div class="txt">
				<div class="blank10"></div>
				<div class="rows clean">
					<strong><!-- Shipped To --><s:text name="user.shippedTo" />:</strong>
                   	<p>${orderView.shipAddress.name }
                   	${orderView.shipAddress.surname }
                   	(${orderView.shipAddress.address }
                   	,${orderView.shipAddress.city }
                   	,${orderView.shipAddress.postalCode }
                   	-${orderView.shipAddress.region.name }
                   	,${orderView.shipAddress.country.name }
                   	)</p>
 				</div>
			</div>
		</div>
		<div class="divide_8px"></div>
		<div class="detail_box ui_border_b">
			<div class="title clean">
				<div class="ui_border_b"><a href="/home/odr_OdrOrder_orders">
				<s:text name="mobile.view_order" />
			</a></div>
			</div>
			<div class="txt">
				<div class="rows clean">
					<strong><!-- Status --><s:text name="mobile.order_status" />:</strong>
					<span><s:text name="orders.status.%{orderView.status}" /></span>
				</div>
				<div class="rows clean">
					<strong><!-- Number --><s:text name="mobile.ordernumber" />:</strong>
					<span>${orderView.number}</span>
				</div>
				<div class="rows clean">
					<strong><!-- Date --><s:text name="user.date" />:</strong>
					<span>${orderView.date}</span>
				</div>
				<s:if test="orderView.status==6">
				<div class="rows clean">
					<strong><!-- Cancel Reason --><s:text name="mobile.cancel_reason" />:</strong>
					<span>${orderView.cancelReason}</span>
				</div>
				</s:if>
				<div class="rows clean">
					<strong><!-- Payment --><s:text name="cart.payment" />:</strong>
					<span><s:text name="pay.mode.%{orderView.payType.mode}" /></span>
				</div>
			</div>
		</div>
		<div class="divide_8px"></div>
		<div class="detail_box ui_border_b">
			<div class="title clean">
				<div class="ui_border_b"><!-- Summary --><s:text name="cart.summary" /></div>
			</div>
			<div class="txt detail_prolist">
				<div class="item clean ui_border_b">
					<div style="padding:5px 0; float:none;">${orderView.shipName }</div>
					<div style="padding:5px 0; float:none"><!-- Shipping Method --><s:text name="shippingMethod" />：${orderView.deliveryType }</div>
					<div style="padding:5px 0; float:none"><!-- Status --><s:text name="cart.status" />：<s:text name="orders.status.%{orderView.status }" /></div>
					<s:if test="orderView.status == 4">
						<div style="padding:5px 0; float:none"><!-- Status --><s:text name="Package_Remarks" />：${orderView.expressNum}</div>
						<div style="padding:5px 0; float:none"><!-- Status --><s:text name="Order_Note_Content" />：${orderView.pagRemark}</div>
					</s:if>
				</div>
				<c:forEach items="${orderView.lines }" var="line" varStatus="index">
				<div class="item clean ui_border_b">
					<div class="img fl"><a href="/home/pdt_PdtProduct_gtProductsInfo?id=${line.spec.product }"><img src="${envConfig.imageBaseUrl}${line.spec.images }" alt="${line.spec.productName }"></a></div>
					<div class="info">
						<div class="name"><a href="/home/pdt_PdtProduct_gtProductsInfo?id=${line.spec.product }">${line.spec.productName }</a></div>
						<div class="number">${line.spec.productCode }</div><div class="attr clean"><!-- Size --><s:text name="product.size_attr" />: &nbsp;${line.spec.size }</div>
						<div class="attr clean"><!-- Color --><s:text name="spec.color" />: &nbsp;${line.spec.color }</div>
					</div>
					<div class="value">
						<div class="price">${line.subtotal }</div>
						<div class="qty">x${line.qty }</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="detail_summary">
				<div class="clean">
					<div class="key"><!-- Subtotal --><s:text name="cart.subtotal" />:</div>
					<div class="value">${orderView.subtotal }</div>
				</div>
				<div class="clean">
					<div class="key"><!-- Shipping&amp; Insurance --><s:text name="user.insurance" />:</div>
					<div class="value">${orderView.shippingChargesAndInsurance }</div>
				</div>
				<div class="clean">
					<div class="key"><!-- Total --><s:text name="user.orderTotal" />:</div>
					<div class="value total">${orderView.total }</div>
				</div>
			</div>
		</div>
		<div class="detail_button">
			<s:if test="orderView.status==0">
				<a href="/home/odr_OdrOrder_payOrder?orderNumber=${orderView.number }" class="btn_global btn_payment BuyNowBgColor"><s:text name="Complete_Your_Payment"></s:text></a>
			</s:if>
			<s:if test="orderView.status == 4">
				<a class="btn_global btn_payment BuyNowBgColor confirmReceiving_btn" style="color: #fff;font-size: .95rem;margin: .875rem;width: 92%;"><s:text name="user.receiving"></s:text></a>
				<script type="text/javascript">
	               $(".detail_button .confirmReceiving_btn").on("click", function() {
	            	   layer.open({
	            	          content: lang_obj.mobile.Confirm_receipt
	            	          ,btn: [lang_obj.global.confirm,lang_obj.global.cancel]
	            	          ,yes: function(index){
		            	        $.ajax({
	  								url:"/home/odr_OdrOrder_confirmReceiving",
	  								type:"post",
	  								data:{orderNumber:"${orderView.number}"},
	  								dataType:"json",
	  								success:function(response) {
		  								if(response.ret==1) {
		  	                      			layer.open({content: lang_obj.mobile.Successful_receipt,skin: 'msg'});
		  	                      			setTimeout(function () {location.reload();},2000);
		  								} else if(response.ret == -1) {
		  										location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/home/odr_OdrOrder_confirmReceiving?orderId=${orderView.number }";
		  								} else if(response.msg){
		  									layer.open({content: response.msg,skin: 'msg'});
		  								}
		  								layer.close(index);
	  								}
		  						})
	            	          }
	            	        });
				})
	                  		</script>
			</s:if>
<%-- 		<s:if test="orderView.status==0||orderView.status==5||orderView.status==6"> --%>
<%-- 			<a href="javascript:void(0)" class="btn_global btn_delete"><s:text name="Delete_order"></s:text></a> --%>
<!-- 			<br/> -->
<%-- 		</s:if> --%>
			<div class="blank30"></div>
		</div>
	</div>
</div>
<footer>
    <div id="prolist_mask_footer"></div>
    <div class="footer_top clean"></div>
    <ul class="footer_list ui_border_b clean" style="display:none;">
        <li class="fl" style="border-right:1px solid #ddd;">
            <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                <span class="list_left">
                    <!-- Sign In -->
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
    <nav></nav>
    <section class="font_col border_col copyright"></section>
</footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>

<%-- <script type="text/javascript">
//   $("#user .btn_delete").on("click", function() {
//     $.ajax({
//       url:"/home/odr_OdrOrder_delete",
//         type:"post",
//         data:{orderNumber:"${orderView.number }"},
//         dataType:"json",
//         success:function(response) {
//           if(response.ret==1) {
//             layer.open({
//               content: "删除成功,返回我的订单"
//               ,skin: 'msg'
//               ,time: 2
//             });
//             setTimeout(function(){
//               location.href = "/home/odr_OdrOrder_orders";
//             },2000)
//           } else if(response.ret == -1) {
//             location.href = "/home/usr_UsrPurchase_sign?jumpUrl=/home/odr_OdrOrder_detail?orderId=${orderView.number }";
//           } else if(response.msg){
//             layer.open({
//               content: response.msg
//               ,skin: 'msg'
//               ,time: 2
//             });
//           }
//         }
//     })
//   })
</script> --%>
</body>
</html>
