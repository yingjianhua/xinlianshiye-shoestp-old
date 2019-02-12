<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	<!DOCTYPE html>
	<html lang="us">
	<head>
		<%@include file="/mobile/template/style_import.jsp" %>
		<style type="text/css">
			.prod_list .prod_box {
				display: none;
			}

			.prod_list .prod_box:nth-of-type(1),
			.prod_list .prod_box:nth-of-type(2),
			.prod_list .prod_box:nth-of-type(3) {
				display: block;
			}

			.prod_list222 .prod_box {
				display: block;
			}
		</style>
	</head>

	<body>
		<%@ include file="/mobile/template/header.jsp" %>
			<div class="wrapper">

				<div id="user">
					<div class="crumb clean">
						<a href="/">
							<span class="icon_crumb_home"></span>
						</a>
						<em>
							<i></i>
						</em>
						<a href="/home/usr_UsrPurchase_userIndex"><!-- My Account --><s:text name="my_account" /></a>
						<em>
							<i></i>
						</em>
						<a href="/home/odr_OdrOrder_orders"><!-- My Orders --><s:text name="my_orders" /></a>
					</div>
					<div id="orderlist" class="user_order" data-number="1" data-page="1" data-total="1">
						<div class="pro_list">
						</div>
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
						<a href="/home/usr_UsrPurchase_userIndex" class="clean">
							<span class="list_left"><s:text name="sign_in" /></span>
							<span class="list_right">
								<em>
									<i></i>
								</em>
							</span>
						</a>
					</li>
				</ul>
				<nav></nav>
				<section class="font_col border_col copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
					<%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
				</section>
			</footer>
			<%@ include file="/mobile/template/foot_menu.jsp" %>

	<script type="text/javascript">
		$.ajax({
			type: 'post',
			async: false,
			url: '/home/odr_OdrOrder_list',
			dataType: 'json',
			success: function (response) {
				if(response.ret == 1) {
					$.each(response.result.items, function (key, order) {
						var html = "";
						html+=
							'<a href="/home/odr_OdrOrder_detail?orderId='+order.number+'"><div class="item clean" >'
							+	'<div class="title clean ui_border_b">'
							+		'<span class="oid">'+lang_obj.mobile.Order_Number + order.number + '</span>'
							+		'<em><i></i></em>'
							+		'<span class="status"  >' + lang_obj.orders.status[order.status]+ '</span>'
							+	'</div>'
							+	'<div class="prod_list" id="odrOrder_' + order.number + '">';
						$.each(order.lines, function (index, line) {
							if(index==3)
								html+='<div class="prod_tr" style="display: none;">';
							html+=
								 '<div class="prod_box clean">'
								+	'<div class="img fl">'
								+		'<img src="${envConfig.imageBaseUrl}' + line.spec.images + '" alt="' + line.spec.productName + '">'
								+	'</div>'
								+	'<div class="info">'
								+		'<div class="name">'
								+			'<a href="/home/odr_OdrOrder_detail?orderId='+order.number+'">' + line.spec.productName + '</a>'
								+		'</div>'
								+		'<div class="number">' + line.spec.productCode + '</div>'
								+		'<div class="attr clean">'+lang_obj.mobile.Color+'&nbsp;' +line.spec.color + '</div>'
								+		'<div class="attr clean">'+lang_obj.mobile.Size+'&nbsp;' + line.spec.size + '</div>'
								+	'</div>'
								+	'<div class="value">'
								+		'<div class="price">' + line.spec.price + '</div>'
								+		'<div class="qty">x' + line.qty + '</div>'
								+	'</div>'
								+'</div>';
						});
						if(order.lines.length>3)
							html+='</div></a>';
						html+=
								'</div>'
							+	'<div class="total ui_border_tb">';
						if(order.lines.length>3) {
							html+='<a href="javascript:;" class="btn_global btn_more ui_border_radius">More<em><i></i></em></a>';
						}
						html+=
								order.itemsCount+lang_obj.mobile.Double+'&nbsp;&nbsp;&nbsp;'+lang_obj.mobile.Total_Price+order.currency+ '&nbsp;' +order.total+'</div>'
							+'</div>'
							+'<div class="divide_8px"></div>';

						$("#orderlist").append(html);
					})
				}
			}
		})
	</script>
	<script type="text/javascript">
		$('#orderlist .btn_more').off().on('tap', function(){
			if($(this).hasClass('current')){
				$(this).removeClass('current');
				$(this).parents('.item').find('.prod_tr').slideUp();
			}else{
				$(this).addClass('current');
				$(this).parents('.item').find('.prod_tr').slideDown();
			}
			return false;
		});
	</script>
	</body>
	</html>
