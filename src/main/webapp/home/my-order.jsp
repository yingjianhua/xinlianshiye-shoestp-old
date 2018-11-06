<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
       /*  $(function () {
            products_list_obj.init();
        }); */
    </script>
    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">
</head>

<body class="lang_en w_1200">
    <%@ include file="/home/template/web-top.jsp" %>
    <%@ include file="/home/template/new-header.jsp" %>

	<div id="main" class="wide">
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
					<li class="crumb2 root">
						<a href="/home/odr_OdrOrder_orders" title="My Orders"><s:text name="my_orders"/>
							<i></i>
						</a>
					</li>
				</ul>
			</div>

			<%@ include file="/home/template/account/lib-user-menu.jsp" %>

			<div id="lib_user_main">
				<h1 class="lib_user_title"><s:text name="my_orders"/></h1>
				<dl class="orders_status_list">
					<dd>
						<a href="javascript:void(0)" status="-1" class="current"><s:text name="all"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="0"><s:text name="user.1"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="1"><s:text name="user.2"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="2"><s:text name="user.3"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="3"><s:text name="user.4"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="4"><s:text name="user.5"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="5"><s:text name="user.6"/></a>
					</dd>
					<dt></dt>
					<dd>
						<a href="javascript:void(0)" status="6"><s:text name="user.7"/></a>
					</dd>
				</dl>
				<table id="lib_user_order" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<th width="110"><s:text name="orderDate"/></th>
							<th width="110"><s:text name="orderNumber"/></th>
							<th width="90"><s:text name="user.orderTotal"/></th>
							<th width="150"><s:text name="user.orderStatus"/></th>
							<th width="180"><s:text name="user.action"/></th>
						</tr>
						<c:if test="${orderViews.items==null}">
						<tr>
						<td> </td>
						<td> </td>
						<td><s:text name="mobile.no_data"/></td>
						<td></td>
						</tr>
						</c:if>
						<c:if test="${orderViews.items!=null}">
						<s:iterator value="orderViews.items" var="order" status="status">
						<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>" >
							<td>${order.date}</td>
							<td>
								<a class="order_info" href="/home/odr_OdrOrder_detail?orderId=${order.number}" title="${order.number}">${order.number}</a>
							</td>
							<td>${order.total}</td>
							<td><strong><s:text name="orders.status.%{#order.status}" /></strong></td>
							<td>
								<a class="order_info" href="/home/odr_OdrOrder_detail?orderId=${order.number}"><s:text name="user.viewDetails"/></a>&nbsp;&nbsp;
								<%-- <a class="order_info" href="/home/odr_OdrOrder_print?orderId=${order.number}" target="_blank"><s:text name="user.printOrder"/></a> --%>
								<a class="order_info" href="/home/odr_OdrOrder_junpOrderPrint?orderId=${order.number}" target="_blank"><s:text name="user.printOrder"/></a>
							</td>
						</tr>
						</s:iterator>
						</c:if>
					</tbody>
				</table>
				<div class="blank20"></div>
				<div id="turn_page">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./static/js/page.js"></script>
	<script>
		$(document).ready(function(){
			$("#lib_user_order tbody tr").each(function(index, order) {
				if(index == 0) return true;
				var d = $(this).find("td:first");
				//d.text(moment(Date.toLocale(d.text())).format("ll"))
			})
			$("#turn_page .prev").on("click", function() {
				order.prevPage();
			})
			$("#turn_page .next").on("click", function() {
				order.nextPage();
			})
			$("#lib_user_main .orders_status_list dd a").on("click", function() {
				order.selectStatus($(this).attr("status"))
			})
			var order = {
				totalCount:${orderViews.totalCount},
				totalPage:${orderViews.totalPage},
				currentPage:${orderViews.currentPage},
				start:${orderViews.start},
				limit:${orderViews.limit},
				items:[],
				prev_btn : $("#turn_page .prev"),
				next_btn : $("#turn_page .next"),
				tbody : $("#lib_user_order tbody"),
				selectStatus : function(status) {
					this.ajaxOrder(0, this.limit, status)
				},
				nextPage : function(page) {
					if(page.currentPage < page.totalPage)
						page.ajaxOrder(page.start+page.limit, page.limit, page.getCurrentStatus())
				},
				prevPage : function(page) {
					if(page.currentPage > 1)
						page.ajaxOrder(page.start-page.limit, page.limit, page.getCurrentStatus())
				},
				selectPage : function(page, selected) {
					page.ajaxOrder((selected-1)*page.limit, page.limit, page.getCurrentStatus())
				},
				getCurrentStatus : function() {
					return $("#lib_user_main .orders_status_list dd a.current").attr("status");
				},
				render : function(status) {
					this.renderList();
					pageRender(this, $("#turn_page"), this.prevPage, this.nextPage, this.selectPage);
					this.renderStatus(status);
				},
				renderList : function() {
					var me = this;
					me.tbody.children("tr").each(function(index, orderline) {
						if(index == 0) return true;
						me.tbody[0].removeChild(orderline);
						//orderline.remove(); ie内核不兼容，改成上面这种方式
					})
					$.each(me.items, function(index, order) {
						var tr = '<tr class="'+((index%2==0)?'even':'odd')+'" >'
								+'<td>'+moment(Date.toLocale(order.date)).format('ll')+'</td>'
								+'<td>'
								+'<a class="order_info" href="/home/odr_OdrOrder_detail?orderId='+order.number+'" title="'+order.number+'">'+order.number+'</a>'
								+'</td>'
								+'<td>'+order.total+'</td>'
								+'<td>'
								+'<strong>'+lang_obj.orders.status[order.status]+'</strong>'
								+'</td>'
								+'<td>'
								+'<a class="order_info" href="/home/odr_OdrOrder_detail?orderId='+order.number+'"><s:text name="user.viewDetails"/></a>&nbsp;&nbsp;'
								+'<a class="order_info" href="/home/order-print.html?orderId='+order.number+'" target="_blank"><s:text name="user.printOrder"/></a>'
								+'</td>'
							+'</tr>';
						me.tbody.append(tr);
					})
				},
				renderStatus : function(status) {
					var me = this;
					$("#lib_user_main .orders_status_list dd a").each(function(index, a) {
						if($(this).attr("status") == status)  {
							$(this).addClass("current");
						} else {
							$(this).removeClass("current");
						}
					})
				},
				ajaxOrder : function(start, limit, status) {
					var me = this;
					$.ajax({
						type:'post',
						url:'/home/odr_OdrOrder_list',
						data:{"start": start, "limit":limit, "status":status},
						dataType: 'json',
						success:function(data){
							if(data.ret != 1) return;
							me.totalCount = data.result.totalCount;
							me.totalPage = data.result.totalPage;
							me.currentPage = data.result.currentPage;
							me.start = data.result.start;
							me.limit = data.result.limit;
							me.items = data.result.items;
							me.render(status)
						}
					});
				}
			}
			pageRender(order, $("#turn_page"), order.prevPage, order.nextPage, order.selectPage);
		});
	</script>

	<%@ include file="/home/template/new-foot.jsp" %>
	<div id="hj_top" style="opacity: 1;">
		<img src="/home/static/images/hj_top.png"/>
	</div>
</body>

</html>
