<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0032)https://www.shoestp.com/account/ -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<meta name="renderer" content="webkit">
	<script>
		window.dataLayer = window.dataLayer || [];

		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-122336495-1');
		    gtag('config', 'UA-127715615-6')

	</script>
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
					<li class="crumb2 root">
						<a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="my_account"/>
							<i></i>
						</a>
					</li>
				</ul>
			</div>
			<%@ include file="template/account/lib-user-menu.jsp" %>
			<div id="lib_user_main">
				<dl id="lib_user_welcome">
					<dt id="PdtLoginName"><s:text name="web-top.Welcome"/> </dt>
					<dd><s:text name="user.welcomeInfo"/></dd>
				</dl>
<!-- 				<ul id="lib_user_prompt"> -->
<!-- 					<li class="coupons"> -->
<!-- 						<b>0</b> You have new coupons!</li> -->
<!-- 				</ul> -->
<!-- 				<div class="rebate_apply"> -->
<!-- 					点击前是这句话  start -->
<!-- 						Click the <a href="javascript:void(0)" class="apply_btn" id="apply_btn">APPLY</a> button, through the audit can become a distribution member.     -->
<!-- 						点击前是变这句话  end -->
<!-- 					点击后变这句话  start -->
<!-- 					You have submitted a distribution application, please wait for the administrator to review.  -->
<!-- 					 点击后变这句话  end -->
<!-- 				</div> -->
				<div class="blank9"></div>
				<div class="index_ml index_boxes">
					<div class="index_item personal">
						<h4><s:text name="user.personal"/></h4>
						<ul>
							<li id="UsrEmail">
								<b><s:text name="consult.email"/>:</b></li>
<!-- 							<li> -->
<!-- 								<b>Approval Status:</b>Under review </li> -->
							<li>
								<b><s:text name="user.consumption"/>:</b>
								<a href="/"><s:text name="user.toGoShopping"/> ...</a>
							</li>
						</ul>
					</div>
					<div class="index_item address">
						<h4><s:text name="user.myShipping"/></h4>
						<ul id="UsrPurchaseLine">
						</ul>
						<div class="link">
							<a href="/home/usr_UsrPurchaseLine_addmanagement" ><s:text name="user.add"/></a>
							<a href="/home/usr_UsrPurchaseLine_addmanagement" id="UsrPurchaseLineMore"><s:text name="user.more"/></a>
						</div>
					</div>
				</div>
				<div class="index_mr index_boxes index_item orders">
					<h4><s:text name="my_orders"/></h4>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody id="Odrorder">
							<tr>
								<th width="18%"><s:text name="user.time"/></th>
								<th width="25%"><s:text name="user.orderNo"/></th>
								<th width="17%"><s:text name="user.price"/></th>
								<th width="20%"><s:text name="user.status"/></th>
							</tr>
<!-- 							<tr class="odd"> -->
<!-- 								<td>07/16/2018</td> -->
<!-- 								<td> -->
<!-- 									<a href="https://www.shoestp.com/account/orders/view18071610112026.html">18071610112026</a> -->
<!-- 								</td> -->
<!-- 								<td>$414.00</td> -->
<!-- 								<td>Cancelled</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="even"> -->
<!-- 								<td>07/13/2018</td> -->
<!-- 								<td> -->
<!-- 									<a href="https://www.shoestp.com/account/orders/view18071309175440.html">18071309175440</a> -->
<!-- 								</td> -->
<!-- 								<td>$9,214.00</td> -->
<!-- 								<td>Cancelled</td> -->
<!-- 							</tr> -->
						</tbody>
					</table>
					<div class="link">
						<a href="/home/odr_OdrOrder_orders"><s:text name="user.more"/></a>
					</div>
				</div>
				<div class="blank20"></div>
				<div class="index_ml index_boxes index_item service">
					<h4><s:text name="user.serviceHelp"/></h4>
					<ul>
					</ul>
				</div>
				<div class="index_mr index_boxes index_item favorites horizontal" style="height: 174px; width: 568px;">
					<h4><s:text name="user.favoriteTitle"/></h4>
					<div class="viewport">
						<ul class="list" data="{&quot;small&quot;:&quot;240x240&quot;,&quot;normal&quot;:&quot;500x500&quot;,&quot;large&quot;:&quot;v&quot;}"
						    style="width: 1620px;" id="UsrFavorites">

						</ul>
					</div>
					<div class="link">
						<a href="/home/usr_UsrFavorites_myfavorite"><s:text name="user.more"/></a>
					</div>
					<a href="javascript:void(0);" hidefocus="true" class="btn btn_l left prev"></a>
					<a href="javascript:void(0);" hidefocus="true" class="btn btn_r right next"></a>

				</div>
				<div class="blank20"></div>
				<%-- <div class="index_boxes index_item review" id="pdtComment">
					<h4>My Reviews</h4>
						<ul>
							<li class="clearfix">
								<dl class="fl">
									<dt>
										<a class="pic_box" href="https://www.shoestp.com/dabowen-canvas-shoes-2018-new-design-fashion-wholesale-lace-up-red-men-and-women-casual-rubber-vulcanized-shoe_p3779.html" title="Dabowen Canvas Shoes 2018 New Design Fashion Wholesale Lace up Red Men and Women Casual Rubber Vulcanized Shoe">
											<img src="./dddd_files/c4050b22bb.jpg.240x240.jpg" alt="Dabowen Canvas Shoes 2018 New Design Fashion Wholesale Lace up Red Men and Women Casual Rubber Vulcanized Shoe">
											<span></span>
										</a>
									</dt>
									<dd class="review">
										<div class="title"><span title="">..</span> <span class="star star_s5"></span><div class="time fr">Aug 06,2018 09:31:47</div></div>
										<div class="content">123213213213123123..</div>
									</dd>
								</dl>
							</li>
						</ul>
					<div class="link">
						<a href="https://www.shoestp.com/account/review/">More</a>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
	<%@ include file="/home/template/new-foot.jsp" %>
	<div id="hj_top" style="opacity: 0;">
		<img src="./static/images/hj_top.png">
	</div>
</body>

<script type="text/javascript">
	$(function(){
		$.ajax({
			type : 'post',
			async:false,
			url:'/home/usr_UsrPurchase_detailById',
			dataType:'json',
			success:function(data){
				if(data.ret != 1)
					return false;
				var orders = data.result.orders;
				var favorites = data.result.favorites;
				var defaultAddress = data.result.defaultAddress;
				$("#PdtLoginName").append(data.result.loginName);
				$("#UsrEmail").append(data.result.email);
				$.each(orders,function(index,order){
					$("#Odrorder").append(
					'	<tr class="odd">'+
					'		<td>'+moment(Date.toLocale(order.date)).format("ll")+'</td>'+
					'		<td>'+
					'			<a href="/home/odr_OdrOrder_detail?orderId='+order.number+'">'+order.number+'</a>'+
					'		</td>'+
					'		<td>'+order.total+'</td>'+
					'		<td>'+lang_obj.orders.status[order.status]+'</td>'+
					'	</tr>'
					);
				})
				if(defaultAddress)
					$("#UsrPurchaseLine").append(
							'<li>'+defaultAddress.surname+' '+defaultAddress.name+'</li>'+
							'<li>'+defaultAddress.address+'</li>'+
							'<li>'+defaultAddress.city+' , '+defaultAddress.region.name+' , '+defaultAddress.postalCode+' , '+defaultAddress.country.name+' </li>'+
							'<li>'+defaultAddress.phone+'</li>'
					);

				$.each(favorites,function(index,favorite){
					$("#UsrFavorites").append(
					'	<li>'+
					'		<span class="photo">'+
					'			<a href="/home/pdt_PdtProduct_gtProductsInfo?id='+favorite.productId+'"'+
					'			    title="'+favorite.name+'" target="_blank">'+
					'				<img src="${envConfig.imageBaseUrl}'+favorite.image+'" alt="'+favorite.name+'">'+
					'			</a>'+
					'		</span>'+
					'		<a href="/home/pdt_PdtProduct_gtProductsInfo?id='+favorite.productId+'" class="name" title="'+favorite.name+'" target="_blank">'+cutstr(favorite.name,15)+'</a>'+
					'	</li>'
					);
				})
				if(data.result.pdtComment)
					$.each(data.result.pdtComment,function(key,commentValue){//'+commentValue.name+'
							$("#pdtComment").append(
							'	<ul>'+
							'		<li class="clearfix">'+
							'			<dl class="fl">'+
							'				<dt>'+
							'					<a class="pic_box" href="" title="'+commentValue.name+'">'+
							'						<img src="${envConfig.imageBaseUrl}'+commentValue.picture+'" alt="'+commentValue.name+'">'+
							'						<span></span>'+
							'					</a>'+
							'				</dt>'+
							'				<dd class="review">'+
							'					<div class="title"><span title="'+commentValue.surname+'">'+commentValue.surname+'</span> '+
							'						<span class="star star_s'+commentValue.productSatisfaction+'"></span>'+
							'						<div class="time fr">'+commentValue.commentTime+'</div>'+
							'					</div>'+
							'					<div class="content">'+commentValue.comment+'</div>'+
							'				</dd>'+
							'			</dl>'+
							'		</li>'+
							'	</ul>'
							)
						})
				}
			})
			user_obj.user_index_init();
		})

	function cutstr(str, len) {
		if(str!=null && len!=null){
	        var str_length = 0;
	        var str_len = 0;
	        str_cut = new String();
	        str_len = str.length;
	        for (var i = 0; i < str_len; i++) {
	            a = str.charAt(i);
	            str_length++;
	            if (escape(a).length > 4) {
	                //中文字符的长度经编码之后大于4
	                str_length++;
	            }
	            str_cut = str_cut.concat(a);
	            if (str_length >= len) {
	                str_cut = str_cut.concat("...");
	                return str_cut;
	            }
	        }
	        //如果给定字符串小于指定长度，则返回源字符串；
	        if (str_length < len) {
	            return str;
	        }
		}
    }

</script>
</html>
