<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0104)https://www.shoestp.com/se-black-women-new-style-ankle-boots-with-buckle-fashion-casual-boots_p6108.html -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit">
	<link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
	<meta name="keywords" content="SE BLACK Women New Style Ankle Boots With Buckle Fashion Casual Boots,Ankle Boots">
	<meta name="description" content="SE BLACK Women New Style Ankle Boots With Buckle Fashion Casual Boots,Ankle Boots,Womens Collections">
	<title> <c:if test="${supView.webSizeTitle !=''}">
   ${supView.webSizeTitle}
  </c:if>
    <c:if test="${supView.webSizeTitle ==''}">
	  An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
  </c:if></title>
	<link href="./static/css/global.css" rel="stylesheet" type="text/css">
	<link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
	<link href="./static/css/user.css" rel="stylesheet" type="text/css">
	<link href="./static/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="./static/css/color.css" type="text/css">
	<link rel="stylesheet" href="./static/css/animate.min.css">
	<link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
	<link href="./static/css/lightbox.min.css" rel="stylesheet" type="text/css">
	<link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./static/js/lazyload.min.js"></script>
	<style>
		img.lazy{background: url('./static/images/loading.gif') 50% no-repeat;}
	</style>
</head>
	<body class="lang_en w_1200">
		<%@ include file="/home/template/web-top.jsp" %>
		<%@ include file="/home/template/new-header.jsp" %>
		<%@ include file="/home/template/shop-header.jsp" %>
		<div id="main" class="wide">
			<link href="./static/css/module_4.css" rel="stylesheet" type="text/css">
			<div id="location"><s:text name="Global.Your_Location"/>:
				<a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supView.pkey}"><s:text name="Global.Home"/></a> &gt;

				<c:forEach begin="1" end="${fn:length(pdtCatList)}" varStatus="index">
					<c:if test="${!index.last}">
						<a href="">${pdtCatList[fn:length(pdtCatList) - index.index].name}</a> &gt;
					</c:if>
					<c:if test="${index.last}">
						<a href="">${pdtCatList[fn:length(pdtCatList) - index.index].name}</a>
					</c:if>
				</c:forEach>
			</div>
			<div id="prod_detail">
				<div itemscope="" itemtype="http://schema.org/Product" class="clearfix">
					<div class="detail_left fl prod_gallery_x" style="height: auto;">
						<div class="detail_pic">
							<div class="left">
								<div class="small_carousel vertical" style="height: 378px; width: 74px;">
									<div class="viewport">
											  			<ul class="list" style="width: 47px; height: 380px;" binding="${imgs.key.pkey}">
											  			<c:forTokens items="${product.picture}" delims="," var="pic">
											  				<li class="item FontBgColor" pos="${index.index}"><!-- 点击图片后添加样式 current-->
																<a href="javascript:;" class="pic_box FontBorderHoverColor thumbnail" alt="" title="" hidefocus="true">
																	<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${pic}" />
																	<span></span>
																</a>
																<em class="arrow FontPicArrowXColor" style="border-color: transparent transparent transparent rgb(102, 102, 102);"></em>
																<%-- <img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${pic}" style="display:none;">
																<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${pic}" style="display:none;"> --%>
															</li>
											  			</c:forTokens>
															</ul>
											  <c:forEach items="${colorToImg}" var="imgs">
												<ul class="list" style="width: 47px; height: 380px;" binding="${imgs.key.pkey}" style="display:none;">
															  		<c:forEach items="${imgs.value}" var="img" varStatus="index">
																		<c:if test="${not empty img}">
																	  		<li class="item FontBgColor" pos="${index.index}"><!-- 点击图片后添加样式 current-->
																				<a href="javascript:;" class="pic_box FontBorderHoverColor thumbnail" alt="" title="" hidefocus="true">
																					<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${img}" />
																					<span></span>
																				</a>
																				<em class="arrow FontPicArrowXColor" style="border-color: transparent transparent transparent rgb(102, 102, 102);"></em>
																				<%-- <img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${img}" style="display:none;">
																				<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${img}" style="display:none;"> --%>
																			</li>
																		</c:if>
															  		</c:forEach>
											  	</ul>
											</c:forEach>

									</div>
									<a href="javascript:void(0);" hidefocus="true" class="btn top prev"></a>
									<a href="javascript:void(0);" hidefocus="true" class="btn bottom next"></a>
								</div>
							</div>
							<!--  TODO   js更改图片 -->
							<div class="right pic_shell">
								<div class="big_box" style="width: 408px; height: 408px; left: 0px; top: 0px;">
									<div class="magnify" data="">
										<a class="big_pic" href="">
											<img class="normal" src="" alt="" style="">
										</a>
										<div class="detail_img_box" style="width: 453px; height: 453px; left: 426px; top: -9999px; display: none;">
											<img class="detail_img lazy" onerror="$.imgOnError(this)" src="./static/images/blank.gif" data-original="./static/images/189feda440.jpg" style="left: -292.127px; top: -347px; max-width: inherit; max-height: inherit;">
										</div>
										<div class="rect_mask" style="width: 231.03px; height: 231.03px; left: 148.985px; top: -9999px; display: none;"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="detail_right fr">
						<!-- 联合采购活动倒计时 S-->
						<div class="countDown_activities">
							<div class="activities_left">
								<div class="activities-icon">
									<img src="./static/images/ico/procurement_countDown.png" alt="">
								</div>
								<div style="font-size: 15px;font-weight: bold;margin: 4px 9px;">
									<c:if test="${groupPurchase.status == 1}">
										<s:text name="shoppingjl_start"/>
									</c:if>
									<c:if test="${groupPurchase.status == 2}">
										<s:text name="shoppingjl_end"/>
									</c:if>
									<c:if test="${groupPurchase.status == 3}">
										<s:text name="shoppingjl_end"/>
									</c:if>
									<c:if test="${groupPurchase.status == 4}">
										<s:text name="theEventIsOver"/>
									</c:if>





								</div>
								<div style="font-size: 20px;font-weight: bold;">
									<span id="days"></span>
									<i>
										<s:text name="groupPurchaseGoodsInfo.Only_Left_Day">
											<s:param><span></span></s:param>
										</s:text>
									</i>
									<span id="time"></span>
								</div>
							</div>
					<%--		<div class="activities_right" style="margin-bottom: 20px;">
								<div>
									${env.currency.symbols}${product.curPrice}
								</div>
								&lt;%&ndash; <del>
									${env.currency.symbols}${sourceProduct.curPrice}
								</del> &ndash;%&gt;
							</div>--%>
						</div>
						<!-- 商品编号 -->
						<div class="activities_goods_num">
							<s:text name="Global.Product_Number"/>: <span>${product.code}</span>
						</div>
						<!-- 联合采购活动倒计时 E-->
						<form class="prod_info_form" name="prod_info_form" id="goods_form" target="_blank">
							<ul class="widget attributes" default_selected="1" data-combination="0">
								<div class="attr_sure">
									<span class="attr_sure_choice">Please select the information you want</span>
									<span class="attr_sure_close">X</span>
								</div>

								<li class="attr_show" name="Color">
									<h5><s:text name="Global.Colour"/>:</h5>
									<c:forEach items="${colorShow}" var="color">
										<span class="imgView" class="GoodBorderColor GoodBorderHoverColor" title="${color.key.name}" binding="${color.key.pkey}">
										<em class="icon_selected"></em>
										<em class="icon_selected_bg GoodBorderBottomHoverColor"></em>
										<c:if test="${color.value !=  ''}">
											<a class="attr_pic">
												<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${color.value}" alt="${color.key.name}">
											</a>
										</c:if>
										<c:if test="${color.value == '' }">
											<a>
												${color.key.name}
											</a>
										</c:if>
										</span>
									</c:forEach>
								</li>
							<%--	<li style="font-size: 12px;">
									<span style="font-weight: bold;">MOQ:</span>
									<span style="margin-left: 11px;font-size: 14px;">${product.minOq}</span>
								</li>--%>
							</ul>
							<div class="size-main quantity_box" data="">
									<div  class="attr_show size-list">
											<h5><s:text name="Global.Size"/>:</h5>
												<ul>
													<c:set var="tally" value="-1"></c:set>
													<c:forEach items="${colorToSpec}" var="specs">
														<c:forEach items="${specs.value}" var="spec">
															<c:set var="tally" value="${tally+1}" />

															<li binding="${specs.key.pkey}" style="display:none;" class="spec">
																<input type="hidden" value="${supplier.pkey}" name="specList[${tally}].supplier"/>
																<input type="hidden" value="${spec.id}" name="specList[${tally}].spec"/>
																<span>${spec.size}</span>
															<%--	<span name="specList[${tally}].amt">${env.currency.symbols}${spec.price}</span>--%>
																<%-- <span><fmt:formatNumber type="number" maxFractionDigits="0" value="${product.minOq}" /> <s:text name="groupPurchaseGoodsInfo.Minimum_Order_Quantity"/></span> --%>
															<%--	<div class="column">
																		<a href="javascript:;" class="reduce">-</a><!-- disable 输入框中数量为1时添加样式disable -->
																		<input type="text" value="0" name="specList[${tally}].qty" class="total">
																		<a href="javascript:;" class="add" >+</a>
																</div>--%>
															</li>
														</c:forEach>
													</c:forEach>
												</ul>

										</div>
										<div style="cursor: pointer" class="click_list">
												<i class="arrow_down_black"></i>
										</div>
							</div>
							<!-- 展开尺寸表 和 闭合尺寸表 -->
							<script>

								</script>

									<!-- <div class="widget prod_info_moq">
											<label for="moq">MOQ: 2000</label>
										</div>
										<div class="widget prod_info_quantity">
											<label for="quantity">Quantity:</label>
											<div class="quantity_box" data="{&quot;min&quot;:2000,&quot;max&quot;:10000,&quot;count&quot;:2000}">
												<input id="quantity" class="qty_num" name="Qty" autocomplete="off" type="text" value="2000" stock="10000">
											</div> Units
											<span class="prod_info_inventory">
												<b id="inventory_number">10000</b> in Stock</span>
										</div> -->
							<div class="widget prod_info_actions">
								<div class="fl" style="position:relative;">
									<input type="hidden" value="${product.pkey}" id="productId"/>
									<input type="button" value="<s:text name='inq'/>"  style="display:inline-block; background:#333;" class="add_btn buynow"
									    id="add_to_inquiry">

							<input type="button" onclick="toAddCart(this)" data="${product.pkey}" value="<s:text name='Global.Add_To_Cart'/>" class="add_btn addtocart AddtoCartBgColor" id="addtocart" style="display: inline-block;">
									<a onclick="addThisToFavorites(${product.pkey})" id="addFavorite" class="<c:if test="${favorite == null || favorite == ''}">fav_new_btn</c:if><c:if test="${favorite != null}">fav_ed_btn</c:if>" >${product.favoriteCount}</a>
									<!-- <div style="position:absolute; top:-8px; right:-93px;">
										<a href="javascript:void(0);">
											<img src="./static/images/ico/norton.png">
										</a>
									</div> -->
								</div>
							</div>
							<!-- 购买成功 20天内发货 -->
							<hr style="width: 335px;height:1px;margin:20px 0;border: none;background-color: #e5e5e5;">
					<%--		<div style="line-height: 20px;">
									<s:text name="groupPurchaseGoodsInfo.Delivery_Information"/>
							</div>--%>
						</form>
					</div>
				</div>
				<div class="clearfix">

					<!-- 商品推荐 -->
<%--					<div class="maylike_new">
						<h3 class="b_title"><s:text name="Global.You_May_Like"/></h3>
						<div class="may_prolist clean">
							<c:forEach items="${recommendationPdt}" var="pro">
								<dl class="pro_item fl">
									<dt>
										<a class="pic_box" href="${pro.rewrite}" target="_blank">
											<c:forTokens items="${pro.pdt.picture}" delims="," var="proPic" end="0">
												<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${proPic}" title="${pro.pdt.name}" alt="${pro.pdt.name}">
											</c:forTokens>
											<span></span>
										</a>
									</dt>
									<dd class="pro_info">
										<div class="pro_name">
											<a href="${pro.rewrite}" title="${pro.pdt.name}"target="_blank">${pro.pdt.name}</a>
										</div>
										<div class="pro_price">
											<em class="currency_data PriceColor">${env.currency.symbols}</em>
											<span class="price_data PriceColor">${pro.pdt.curPrice}</span>
										</div>
									</dd>
								</dl>
							</c:forEach>

						</div>
					</div>--%>
					<div class="prod_desc_left fl" style="width:100%;">
						<div class="prod_description">
							<ul class="pd_title">
								<li class="current">
									<span><s:text name="Global.Product_Details"/></span>
								</li>
								<!-- <li>
									<span data="0">Company Profile</span>
								</li>
								<li>
									<span data="2">FAQ</span>
								</li> -->
							</ul>
							<div class="pd_content editor_txt">
								<div class="desc">


									<!-- 商品属性 -->
									<div class="item_specifics">
										<div class="title"><s:text name="Global.Product_Specifications"/></div>
										<div class="clean">
											<c:forEach items="${proAttribute}" var="attr">
												<div class="fl" style="width:50%;">
													<span>
														<strong>${attr.key.name}:</strong>
														${attr.value.name}</span>
												</div>
											</c:forEach>

										</div>
									</div>
									<div class="clean"></div>


									<!-- 商品详情 -->

									${product.description}
								</div>


								<!-- <div class="desc hide">&nbsp;&nbsp;

									供应商基本信息
									<strong>Company basic information:</strong>
									<table border="1" cellpadding="0" cellspacing="0" style="width: 100%;">
										<tbody>
											<tr>
												<td>
													<strong>Business Type</strong>
												</td>
												<td>Limited Liability Company</td>
												<td>Verified</td>
											</tr>
											<tr>
												<td>
													<strong>Main product</strong>
												</td>
												<td>Women Boots,Ladies Shoes,Men Causal Shoes.........</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<strong>Trademarks</strong>
												</td>
												<td>
													<font face="arial, sans-serif">SIEN</font>
												</td>
												<td>Verified</td>
											</tr>
											<tr>
												<td>
													<strong>Locations</strong>
												</td>
												<td>Wenzhou, Zhejiang, China (Mainland)</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<strong>Total Employees</strong>
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<strong>Total Annual Revenue</strong>
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<strong>Year Established</strong>
												</td>
												<td>2015-9-16</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<strong>Business Scope</strong>
												</td>
												<td>&nbsp;</td>
												<td>Verified</td>
											</tr>
										</tbody>
									</table>
									<br>
									<br>
									<br> &nbsp;
								</div> -->
								<!-- <div class="desc hide">Q1: Will the sample be charged? How many days will the sample be ready?
									<br> A1: The sample with one piece and one color will be ready within 7 days. Sample fee and sending cost will be charged,
									but would be returned once place the bulk order.
									<br> &nbsp;
									<br> Q2: What payment do you we support?
									<br> A2: T/T &amp; L/C.
									<br>
									<br> Q3: What's the production delivery time?
									<br> A3: Normally 45-60 days once the contract down.
									<br>
									<br> Q4: Do we support OEM/ODM?
									<br> A4: Yes. We can develop the styles as customers' requirements.
									<br>
									<br> Q5: Do we have stock?
									<br> A5: We have no stock. Only customers give the new order, then we start the production.
									<br>
									<br> Q6: Can we offer Certification of Original?
									<br> A6: Yes, we can.
									<br>
									<br> Q7: How to visit our company?
									<br> A7: We are located in Wenzhou Ouhai with convenient transportation of 40 minutes to airport, train station and
									five star hotels. If needed, we can help to arrange the pick up and the hotel booking.
									<br> &nbsp;
									<br> Q8:What is your terms of payment?
									<br> A8: T/T 30% as deposit, and 70% before delivery. We'll show you the photos of the products and packages before
									you pay the balance.
									<br> &nbsp;
									<br> Q9: What is your terms of delivery?
									<br> A9: FOB.
									<br> &nbsp;
									<br> Q10: Are you a factory or company?
									<br> A10: Factory. We have been engaged in product shoes for 5 years, so competitive price can be kept our customers.
									And the quality for products and our after service are good.
									<br> &nbsp;
									<br> Q11:Is sample free?
									<br> A11:Yes, we can make it free for you, and you just pay us shipping cost.
									<br> &nbsp;
									<br> Q12: How can you control the quality for us?
									<br> A12:Our professional QC will take the sample for checking materials, last, outsole, craft and all the packing details
									to make sure everything as your requirement.
									<br> &nbsp;
									<br> Q13. How can the products be send to us.
									<br> A13: We use DHL, UPS, EMS, FEDEX. Basically, the transportation depends on client’s choice.
									<br> &nbsp;
									<br> Q14.What’s your best price?
									<br> A14: Our shoes have a quite wide price range for different types and different quality requirement. Please contact
									us freely, we will be happy to give you a quotation upon receipt of your detailed requirements.
									<br> &nbsp;
									<br> Q15:What is your main shipping method?
									<br> A15:By sea.
									<br> &nbsp;
								</div> -->
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$(function () {
						$('.pic_list>a').lightBox();
					});
				</script>
				<%-- <div id="review_box">
					<div class="widget prod_write_review">
						<div class="review_title">
							<span><s:text name="Global.Product_Evaluation"/></span>
						</div>
						<div class="average_rating">
							<h6><s:text name="Global.Average_Evaluation"/>:</h6>
							<p>
								<c:set value="${product.defaultReviewRating}" var="review"/>
								<c:choose>
									<c:when test="${review==0}">
										<span class="star star_b0"></span>
									</c:when>
									<c:when test="${review==1}">
										<span class="star star_b1"></span>
									</c:when>
									<c:when test="${review==2}">
										<span class="star star_b2"></span>
									</c:when>
									<c:when test="${review==3}">
										<span class="star star_b3"></span>
									</c:when>
									<c:when test="${review==4}">
										<span class="star star_b4"></span>
									</c:when>
									<c:when test="${review>=5}">
										<span class="star star_b5"></span>
									</c:when>
								</c:choose>
							</p>
							<div>
								<a href="/home/pdt_PdtProduct_writeComment?id=${product.pkey}" class="write_review_btn ReviewBgColor" rel="nofollow"><s:text name="user.writeReview"/></a>
							</div>
						</div>
					</div>
					<c:if test="${env.login == null}">
						<div class="prod_review_view">
							<div class="review_sign"><s:text name="groupPurchaseGoodsInfo.After_Login">
														<s:param>javascript:;</s:param>
													</s:text>
								<!-- <a class="SignInButton">click here</a> to login</div> -->
							<div class="blank12"></div>

							<div id="turn_page"></div>
							<div class="blank12"></div>
						</div>
					</c:if>
				</div> --%>
				<div class="blank12"></div>
			</div>
			<div class="blank12"></div>
			<!-- <iframe name="export_pdf" id="export_pdf" class="export_pdf" src="./static/saved_resource.html" style="width:0px; height:0px;"></iframe> -->
		</div>
		</div>
		<%@ include file="/home/template/new-foot.jsp" %>


		<script type="text/javascript" src="./static/js/module.js"></script>
		<script type="text/javascript" src="./static/js/review.js"></script>
		<script type="text/javascript" src="./static/js/lightbox.min.js"></script>
		<%-- <script type="text/javascript" src="./static/js/addthis_widget.js"></script> --%>

	</body>

	<script tyle="text/javascript">
		var time = '${groupPurchase.endTime}';
		var startTime = '${groupPurchase.startTime}';
		var actState = '${groupPurchase.status}';
		var state = 0;
		window.onload=function(){
			// 图片懒加载
			$("img.lazy").lazyload({　　　　　　　　　　
				effect: "show",
　　　　　　　　  threshold: 150,
　　　　　　　　　　event: 'scroll',
　　　　　　　　　　failure_limit: 5　　　　　　　　
			});

			var img = $(".list li:first .thumbnail img").attr("data-original");
			$(".list li:first").addClass("current");
			$(".big_pic").attr("href",img);
			$(".big_pic").find(".normal").attr("src",img);
			//$(".imgView:first").click();
			$(".imgView:first").addClass("selected");
			var bingData = $(".imgView:first").attr("binding");
			$("li[binding="+bingData+"]").show();

			var endTime = new Date(time);
			var startTime2 = new Date(startTime);
			var nowTime = new Date();
			//时间戳相减除以1000 = 秒
			var differenceTime;
			if(actState == 1){
				differenceTime = startTime2 - nowTime;
			}else{
				differenceTime = endTime - nowTime;
			}
			if(differenceTime <= 0){
				$("#days").parent().hide();
				state = 1;
			}else{
				var day = Math.floor(differenceTime/86400000);
				var hour = Math.floor(differenceTime%86400000/3600000);
				var minute = Math.floor(differenceTime%86400000%3600000/60000);
				var second = Math.floor(differenceTime%86400000%3600000%60000%60);
				countDown(day,hour,minute,second);
			}
			var liHeight = $('.size-list ul li').outerHeight();
			var li_len = $('.size-list ul li').length;
			var visible_li_len = $('.size-list ul li:visible').length
			var listHeight = $('.size-list').height();
			$('.click_list').toggle(function(){
				$('.size-list').animate({
					height: liHeight * visible_li_len + 'px'
				}, 500,function(){
					$('.click_list').find('i').removeClass('arrow_down_black').addClass('arrow_up_black')
				});
				},function(){
				$('.size-list').animate({
					height:listHeight + 'px'
				}, 500,function(){
					$('.click_list').find('i').removeClass('arrow_up_black').addClass('arrow_down_black')
				});
				}
			)

		}

		function countDown(day,hour,minute,second){
			setInterval(function(){
				second--;
				if(Number(day) == 0 && Number(minute) == 0 && Number(hour) == 0 && Number(second) == 0){
					window.location.reload();
				}
				if(second < 0){
					second = 59;
					minute--;
					if(minute < 0 ){
						minute = 59;
						hour--;
						if(hour <=0 ){
							day--;
						}
					}
				}
				$("#days").next().find("span").text(day);
				$("#time").text(getZero(hour)+":"+getZero(minute)+":"+getZero(second));
			},1000);
		}

		function getZero(str){
			if(str.toString().length == 1){
				return "0" + str;
			}else{
				return str;
			}
		}

		$(".thumbnail").on("click",function(){
			var img = $(this).children("img").attr("src");
			$(".big_pic").attr("href",img);
			$(".big_pic").children("img").attr("src",img);
			$(".detail_img").attr("src",img);
		})

		$(".imgView").on("click",function(){
			var binding = $(this).attr("binding");
			$(".spec").hide();
			$(this).siblings(".imgView").removeClass("selected");
			$(this).addClass("selected");
			$("li[binding="+binding+"]").show();
			var imgs = $("ul[binding="+binding+"] li");
			if(imgs.length == 0){
				return;
			}
			$(".list").hide();
			$("ul[binding="+binding+"]").show();
			var img = $(this).find("img").attr("data-original");
			$(".normal").attr("src",img);

		})


		$(".reduce").on("click",function(){
			var countInput = $(this).siblings("input");
			var count = $(this).siblings("input").val();
			count--;
			if(count < 0 ){
				$(this).addClass("disable");
				return;
			}
			$(countInput).val(count);
		})


		$(".add").on("click",function(){
			var countInput = $(this).siblings("input");
			var count = $(this).siblings("input").val();
			count++;
			$(countInput).val(count);
		})

		$(".total").on("keyup",function(){
			var price = $(this).parent().siblings("span[name$=amt]").text();
			var count = $(this).val();
			var totalAmt = Number(price).toFixed(2) * parseInt(Number(count));
			$(this).parent().siblings("input[name$=amtTotal]").val(totalAmt);
		});

		function toAddCart(btn){
			if(state == 1){
				layer.msg("<s:text name='product.sold_time_e'/>",{icon:2,time:1500});
				return;
			}
			var qty = $("input[name^=specList]input[name$=qty]");
			var flag = false;
			for(var i=0;i<qty.length;i++){
				if($(qty[i]).val() != 0){
					flag = true;
					break;
				}
			}
			var pdtParam ;
			if(flag){
				pdtParam = $("#goods_form").serialize()
			}else{
				var pdt = $(btn).attr("data");
				pdtParam = {"pdtPkey":pdt};
			}
			   $.ajax({
				url:'/home/usr_UsrCart_boughtPro',
				type:'post',
				data:pdtParam,
				dataType:'json',
				success:function(data){
					if(data.ret == 1 || data.success == true){
						var _self = $(btn)[0];
						cartFly(data, _self, lang_obj.cart.additem_0);
					}else if(data.ret == -1){
						$(".SignInButton").click();
					}else{
						layer.msg(getMessage(data.msg),{time:3000});
					}
				}
			})
		}

		function addThisToFavorites(data){
	  		$.ajax({
	  			url:'/home/usr_UsrFavorites_addFavorite',
	  			type:'post',
	  			data:{"pdtPkey":data},
	  			dataType:'json',
	  			success:function(data){
	  				if(data.success == true || data.ret == 1){

	  					var count = $("#addFavorite").text();
	  					if(count < data.number){
	  						$("#addFavorite").addClass("fav_ed_btn");
		  					$("#addFavorite").removeClass("fav_new_btn")
	  					}else{
	  						$("#addFavorite").addClass("fav_new_btn")
		  					$("#addFavorite").removeClass("fav_ed_btn");
	  					}
	  					$("#addFavorite").text(data.number);
	  				}else if(data.ret == -1){
	  					$(".SignInButton").click();
	  				}else{
	  					layer.msg(data.msg,{time:3000});
	  				}
	  			}
	  		})
	  	}



	</script>
</html>
