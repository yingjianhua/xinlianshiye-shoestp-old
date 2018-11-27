<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en" data-dpr="1">
<head>
		<%@include file="/mobile/template/style_import.jsp" %>
		<link href="/home/static/themes/default/mobile/css/style_new.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			.clearfloat:after {
				display: block;
				clear: both;
				content: "";
				visibility: hidden;
				height: 0
			}

			.clearfloat {
				zoom: 1
			}

			.page-enterprise-product .main {
				padding: 0;
			}

			.flex-grow .main000 {
				width: 100%;
				padding: 0 2%;
				height: 3.75rem;
				line-height: 3.75rem;
				background: #f4f4f4;
			}

			.flex-grow .main000 h2,
			.flex-grow .main000 h3 {
				font-size: 0.88rem;
				color: #5668a6;
				float: left;
				margin-right: 0.75rem;
			}

			.flex-grow .main000 h3 {
				font-weight: bold;
				color: #8ac762;
			}

			.flex-grow .main000 .xmg-xiala {
				margin-top: 0.75rem;
				position: relative;
			}
			
			.flex-grow .main000 .xmg-xiala .xmg-top {
				width: 7.5rem;
				height: 2.25rem;
				line-height: 2.25rem;
				text-align: center;
				background: #5668a6;
				border-radius: 0.4rem;
				font-size: 0.82rem;
			}

			.flex-grow .main000 .xmg-xiala.show .xmg-top {
				border-bottom-left-radius: 0;
				border-bottom-right-radius: 0;
			}

			.flex-grow .main000 .xmg-xiala .xmg-top b,
			.flex-grow .main000 .xmg-xiala .xmg-top span {
				color: #fff;
				-webkit-transition: all .4s;
				-moz-transition: all .4s;
				-o-transition: all .4s;
				transition: all .4s;
				display: inline-block;
			}

			.flex-grow .main000 .xmg-xiala .xmg-bottom {
				position: absolute;
				display: none;
				top: 3em;
				right: 0;
				width: 7.5rem;
				line-height: 2.25rem;
				text-align: center;
				background: #5668a6;
				border-radius: 0.15625rem;
				font-size: 0.75rem;
				padding-bottom: 0.15625rem;
				border-radius: 0.4rem;
				border-top-left-radius: 0;
				border-top-right-radius: 0;
				z-index: 68;
			}

			.flex-grow .main000 .xmg-xiala.show .xmg-top b {
				transform: rotateZ(180deg);
			}

			.flex-grow .main000 .xmg-xiala.show .xmg-bottom {
				display: block;
			}
			.flex-grow .main000 .xmg-xiala.show .xmg-bottom li{
				color: #fff;
			}

			.page-enterprise-product .tool-group {
				padding: 0 2%;
			}

			.tool-group .tool-item {
				width: 50%;
				margin-right: 0;
				font-size: 0.3125rem;
			}
		</style>
		<style>
			.xmg-jiontbuy-goods {
				padding-bottom: 1.2rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox {
				float: left;
				width: 46%;
				margin: 0 2% 1rem 2%;
				font-size: 0.375rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item {
				width: 94%;
				border-bottom: 0.0375rem solid #e5e5e5;
				padding-bottom: 1.0625rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-top {
				height: 2.1875rem;
				overflow: hidden;
				line-height:1.1rem;
				margin-top: 0.3125rem;
				font-size: 0.82rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common {
				height: 2.1875rem;
				margin-top: 0.3125rem;
				font-size: 1rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-l {
				float: left;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-l .xmg-t {
				font-weight: bold;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-l .xmg-t span {
				color: #002172;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-l .xmg-b {
				color: #d6d6d6;
				margin-top: 0.3125rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-l .xmg-b span {
				font-size: 0.3125rem;
				color: #d6d6d6;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-r {
				float: right;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-r .xmg-t {
				color: #999;
				text-decoration: line-through;
				font-size: 0.75rem;
				text-align: right;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-item .xmg-common .xmg-r .xmg-b {
				color: #ff6420;
				font-size: 0.95rem;
				margin-top: 0.4rem
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-bottom .addcart,
			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-bottom .favorite {
				float: left;
				width: 38%;
				text-align: center;
				height: 1.77rem;
				line-height: 1.77rem;
				border-radius: 0.18rem;
				background-image: -moz-linear-gradient( 0deg, rgb(211, 169, 97) 0%, rgb(227, 193, 134) 45%, rgb(243, 216, 170) 100%);
				background-image: -webkit-linear-gradient( 0deg, rgb(211, 169, 97) 0%, rgb(227, 193, 134) 45%, rgb(243, 216, 170) 100%);
				background-image: -ms-linear-gradient( 0deg, rgb(211, 169, 97) 0%, rgb(227, 193, 134) 45%, rgb(243, 216, 170) 100%);
				margin-right: 6%;
				color: #fff;
				margin-top: 0.4375rem;
				font-size: 0.1rem;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-bottom .addcart {
				background-image: -moz-linear-gradient( 0deg, rgb(243, 216, 170) 0%, rgb(227, 193, 134) 55%, rgb(211, 169, 97) 100%);
				background-image: -webkit-linear-gradient( 0deg, rgb(243, 216, 170) 0%, rgb(227, 193, 134) 55%, rgb(211, 169, 97) 100%);
				background-image: -ms-linear-gradient( 0deg, rgb(243, 216, 170) 0%, rgb(227, 193, 134) 55%, rgb(211, 169, 97) 100%);
				width: 50%;
			}

			.xmg-jiontbuy-goods .xmg-goodsbox .xmg-bottom .addcart img {
				display: inline-block;
				width: 0.4rem;
				height: 0.4rem;
			}
		</style>
</head>

<body>
	<div class="page page-enterprise-product flexible flex-column fill">
		<!-- 顶上 - 固定的头部条 -->
		<%@ include file="/mobile/template/header.jsp" %>
			<!-- 顶上 - 固定的头部条 -end -->
			<!-- 公司头部 - 含背景图 -->
			<div class="scroll flex-grow">
				<div class="enterprise-top-header flexible flex-c-center">
					<!-- 公司logo -->
					<div class="logo">
						<img src="<c:if test='${not empty supView.logo}'>${envConfig.imageBaseUrl}${supView.logo}</c:if><c:if test='${empty supView.logo}'>/home/static/images/headimg.jpg</c:if>" alt="logo">
					</div>
					<!-- 公司简介信息 -->
					<div class="enterprise-profile">
						<div class="text">
							${supView.showName}
						</div>
						<!-- 图标组 -->
						<div class="icon-group flexible flex-c-center">
							<c:if test="${not empty supView.authAge && supView.authAge > 0}">
		                    <!-- 自制年限图标 -->
		                    <div class="icon icon-years-limit-group">
		                		<a href="/m/spain/company.html#data" target="_blank">
		                    		<img src="/home/static/themes/default/mobile/images/doller_pic_o.png" alt="">
		                    		<span class="years-num">${supView.authAge}</span>
		                    		<span class="years-unit"><!-- YRS --><s:text name="global.n_y.1" /></span>
		                		</a>
		              		</div>
		              		</c:if>
							<c:if test="${supView.isAuth == 1}">
								<a href="#" target="_blank">
									<img class="icon" src="/home/static/themes/default/mobile/images/defence-safe-o.png" alt="defence-safe">
								</a>
								<a href="#" target="_blank">
									<img class="icon" src="/home/static/themes/default/mobile/images/certification-o.png" alt="certification">
								</a>
							</c:if>
						</div>
					</div>
				</div>
				<!-- 顶部导航条 -->
				<div class="nav-bar bg-white">
					<ul class="flexible text-center">
						<li class="">
							<a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supView.pkey}">
								<!-- HOME -->
								<s:text name="home" />
							</a>
						</li>
						<li>
							<a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supView.pkey}">
								<!-- PRODUCTS -->
								<s:text name="order_line.spec" />
							</a>
						</li>
						<c:if test="${supView.prmAuthrity == true}">
							<li class="active">
								<a href="/home/usr_UsrSupplier_gtSupGroup?pkey=${supView.pkey}">
									<!-- JOINT BUY -->
									<s:text name="group_order.activity" />
								</a>
							</li>
						</c:if>
						<li class="">
							<a href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supView.pkey}">
								<!-- PROFILE -->
								<s:text name="freight_line.brief" />
							</a>
						</li>
						<li class="">
							<a href="/home/usr_UsrSupplier_gtSupContact?pkey=${supView.pkey}">
								<!-- CONTACT -->
								<s:text name="contactmenu" />
							</a>
						</li>
					</ul>
				</div>
				<!-- 顶部导航条 - end -->
				<!-- 主内容 -->
				<c:if test="${not empty unionList && fn:length(unionList)>0}">
				<div class="flex-grow">
					<div class="main000">
						<h2>｛${prm.title}｝</h2>
						<h3>
							<c:choose>
								<c:when test="${prm.status == 1}">
									<li style="color: #11d702;">即将开始</li>
								</c:when>
								<c:when test="${prm.status == 2}">
									<li style="color: #11d702;">进行中</li>
								</c:when>
								<c:when test="${prm.status == 3}">
									<li style="color: #11d702;">即将结束</li>
								</c:when>
								<c:when test="${prm.status == 4}">
									<li style="color: #11d702;">已结束</li>
								</c:when>
							</c:choose>
						</h3>
						<div class="xmg-xiala fr">
							<div class="xmg-top">
								<span>
									<!-- 已选择的内容 -->
									${prm.title}
								</span>
								<b>▼</b>
							</div>
							<div class="xmg-bottom">

								<ul id="activityList">
									<c:forEach items="${unionList}" var="union">
										<li value="${union.pkey}" state="${union.status}" data='{"title":"${union.title}","state":"${union.status}","pkey":"${union.pkey}"}'>
												${union.title}
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>


					<div class="main">
						<div class="clean">
						</div>
						<!--- 工具栏 -->
						<ul class="tool-group">

							<li class="tool-item" id="cat">
								<span class="tool-name">
									<!-- Product -->
									<s:text name="products" />
								</span>
								<div class="tool-content tool-drop-down-wrap">
									<div class="tool-drop-down-btn">
										<span class="select-text">
											<!-- All product -->
											<s:text name="all_products" />
										</span>
										<i class="icon icon-arrow-down">
										</i>
									</div>
									<div class="tool-drop-down-content">
										<ul class="classify">
											<li onclick='chooseThisCat(-1,"<s:text name="All_Categories"/>")'><s:text name="All_Categories"/></li>
											<c:forEach items="${catList}" var="cat" end="10">
												<li onclick='chooseThisCat(${cat.id},"${cat.name}")'>${cat.name}</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</li>
							<li class="tool-item">
								<span class="tool-name">
									<!-- Sort by -->
									<s:text name="sort_by" />
								</span>
								<div class="tool-content tool-drop-down-wrap">
									<div class="tool-drop-down-btn">
										<span class="select-text">
											<!-- 已选择的内容 -->
											<!-- Most popular -->
											<s:text name="most_popular" />
										</span>
										<i class="icon icon-arrow-down">
										</i>
									</div>
									<div class="tool-drop-down-content">
										<ul>
											<li>
												<a onclick="chooseThisSort(3)">
													<!-- Most Popular -->
													<s:text name="most_popular" />
												</a>
											</li>
											<li>
												<a onclick="chooseThisSort(2)">
													<!-- Client evaluation -->
													<s:text name="customer_review" />
												</a>
											</li>
											<li>
												<a onclick="chooseThisSort(1)">
													<!-- Price -->
													<s:text name="cart.price" />
												</a>
											</li>

										</ul>
									</div>
								</div>
							</li>
						</ul>
						<!-- 工具栏 - end -->

						<!-- 商品栏 -->
						<div class="xmg-jiontbuy-goods clearfloat" id="proList">
						</div>
						<!-- 商品栏 - end -->
						<div class="click_more" page="2" style="width:30em;">
		                    <span id="viewMore" style="display: inline-block;">
		                        <!-- View More -->
		                        View More
		                    </span>
				        </div>
					</div>
					<!-- main - end -->
				</div>
				<!-- 主内容 - flex-grow - end -->
				</c:if>
				<c:if test="${empty unionList || fn:length(unionList) <= 0}">
					<div style="border-top: 1px dotted rgb(204, 204, 204); text-align: center;" id="noActivity">
				    	<p style="padding: 215px 0;text-align: center;display:  inline-block;">
				    		<em style="width: 20px;height: 1px;background-color: #000;display: block;float: left;margin-top: 8px;margin-right: 20px;"></em>
								暂无活动
				    		<em style="width: 20px;height: 1px;background-color: #000;display: block;float: right;margin-top: 8px;margin-left: 20px;"></em>
						</p>
					</div>
				</c:if>
				
			</div>
			<!-- 公司头部 - 含背景图 - end -->
			<!-- 底部 -->
			<%@ include file="/mobile/template/foot_menu.jsp" %>

	</div>
	<%-- <script src="/home/static/themes/default/mobile/js/zepto.js"></script> --%>
	<script type="text/javascript" src="/home/static/themes/default/mobile/js/base.js"></script>
	<script type="text/javascript">
	var pkey;
	var page = 1;
	var category = -1;
	var sort = -1;
	var type = 1;
	var allPage = 0;
	var _self = this;
	
	window.onload = function(){
		var opt = $("#activityList li[state=2]");
		if(opt.length == 0){
			opt = $("#activityList [value]:eq(0)");
		}
		getActInfo(opt)
	}
	
	$("#activityList").on("click","li",function(){
		getActInfo($(this));
	})
	
	$(".click_more").on("click",function(){
		self.page = ++self.page;
  		if(self.page > self.allPage){
  			self.page = --self.page;
  			layer.open({content: "已加载所有商品",skin: 'msg',time:2});
  			return;
  		}
  		getData();
	})
	
	function chooseThisCat(id,name){
		_self.category = id;
		_self.page = 1;
		$("#cat .select-text").text(name);
		$(".tool-drop-down-content").slideUp();
		$(".tool-drop-down-btn").removeClass("active");
		$("#proList").html('');
		getData();
	}
	
	function chooseThisSort(sort){
		switch(_self.type){
	  	case 1:
	  		_self.type = 0;
	  		break;
	  	case 0:
	  		_self.type = 1;
	  		break;
	  }
	  $("#proList").html('');
	  $(".tool-drop-down-content").slideUp();
	  $(".tool-drop-down-btn").removeClass("active");
	  _self.page = 1;
	  _self.sort = sort;
	  getData();
	}
	
	function getActInfo(opt){
		console.log($(opt).attr("data"))
		var data = JSON.parse($(opt).attr("data"));
		
		$(".xmg-top span").text(data.title)
		$(".main000 h2").text("{"+data.title+"}");
		switch(Number(data.state)){
		case 1:
			$(".main000 h3").html('<s:text name="About_To_Begin"/>');
			break;
		case 2:
			$(".main000 h3").html('<s:text name="groupPurchase.Processing"/>');
			break;
		case 3:
			$(".main000 h3").html('<s:text name="Coming_To_An_End"/>');
			break;
		case 4:
			$(".main000 h3").html('<s:text name="Ended"/>');
			break;
		}
		_self.pkey = Number(data.pkey)
		$("#proList").html('');
		getData();
	}
	
	function getData(){
		var param = {"id":_self.pkey,"page":_self.page,"limit":8,"category":_self.category,"sort":_self.sort,"type":_self.type};
		$.ajax({
			url:'/home/prm_PrmGroupPurchase_getActProduct',
			type:'post',
			data:param,
			dataType:'JSON',
			success:function(data){
				if(data.items.length == 0){
					$("#noGoods").show();
					$(".procurement-goodsList").hide();
					return;
				}else{
					$("#noGoods").hide();
					$(".procurement-goodsList").show();
				}
				renderProduct(data.items);
				_self.allPage = data.pageAll;
			}
		})
	}
	
	function renderProduct(items){
	 	$(".procurement-item").remove();
	 	var div = '';
		$.each(items, function (i, val) {
			div ='<div class="xmg-goodsbox">' +
				'<div class="xmg-pic">' +
				'<a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+val.id+'">'+
				'<img src="${envConfig.imageBaseUrl}' + getProPic(val.image) + '" />' +
				'</a>'+
				'</div>' +
				'<div class="xmg-item">' +
				'<div class="xmg-top">' + val.name + '</div>' +
				'<div class="xmg-common">' +
				/*'<div class="xmg-l">' +
				'<div class="xmg-t" style="font-size:12px;">MOQ:<span>'+val.count+'</span></div>' +
				'<div class="xmg-b">' + judgeReview(val.reviewRating) + '<span>（' + val.reviewCount +
				'）</span> </div>' +
				'</div>' +
				'<div class="xmg-r">' +
				/!* '<div class="xmg-t">${env.currency.symbols}' + val.sourcePrice + '</div>' + *!/
				'<div class="xmg-b">${env.currency.symbols}' + val.curPrice + '</div>' +
				'</div>' +*/
				'</div>' +
				'</div>' +
				'<div class="xmg-bottom">' +
				'<div class="favorite" onclick="addThisToFavorite(' + val.productId + ')">Favorite</div>' +
		/*		'<div class="addcart" onclick="addThisToCart(' + val.productId +
				');"><img src="/home/static/themes/default/mobile/images/myfavorite-carttb.png"/>  Add to Cart</div>' +*/
				'</div>' +
				'</div>';
			$("#proList").append(div);
		});
  }
	
	
	
		/* var category = '${category}';
		var sort = '${sort}';
		var pkey = '${prm.pkey}';
		var prmPkey = '${prmPkey}'; */

		/* $(function () {
			$.ajax({
				url: '/home/pdt_PdtCat_selBsicCat',
				type: 'POST',
				dataType: 'JSON',
				success: function (data) {
					var span = "<li><a href='/home/usr_UsrSupplier_gtSupGroup?pkey=${supplier.pkey}&prmPkey=" + prmPkey + "&category=&sort=" + sort + "'>Most Popular</a></li>";
					$.each(data.items, function (i, val) {
						if (typeof (val.categoryUp) == "undefined") {
							if (val.name == '${category}') {
								span = span + "<li><a href='/home/usr_UsrSupplier_gtSupGroup?pkey=${supplier.pkey}&prmPkey=" + prmPkey + "&category=" + val.pkey + "&sort=" + sort + "'>" + val.name + "</a></li>";
							} else {
								span = span + "<li><a href='/home/usr_UsrSupplier_gtSupGroup?pkey=${supplier.pkey}&prmPkey=" + prmPkey + "&category=" + val.pkey + "&sort=" + sort + "'>" + val.name + "</a></li>";
							}
						}
					});
					$(".classify").html(span);
				}
			});
		}); */

		/* var type = '${type}'; */
		/* function chooseThisCat(cat){
		window.location.href = "/home/usr_UsrSupplier_gtSupGroup?pkey="+${supplier.pkey}+"&prmPkey=" + prmPkey + "&category=" + cat + "&sort=" + sort + "&type=" + type;
		}
		function chooseThisSort(sort1){
			window.location.href = "/home/usr_UsrSupplier_gtSupGroup?pkey="+${supplier.pkey}+"&prmPkey=" + prmPkey + "&category=" + category + "&sort=" + sort1 + "&type=" + type;
		}

		function changeGroup(btn){
			window.location.href = "/home/usr_UsrSupplier_gtSupGroup?pkey="+${supplier.pkey}+"&prmPkey=" + $(btn).val();
		}
 */
		/* $(function () {
			var dataParam = {};
			dataParam.unionPkey = pkey;
			if (category != null && category != "" && typeof (category) != "undefined") {
				dataParam['category'] = category;
			}
			if(category != null && category != "" && typeof(category) != "undefined"){
				dataParam.category = category;
			}
			if(sort != null && sort != "" && typeof(sort) != "undefined"){
				if(type == 0 || type == undefined || type == null){
					type = 1
				}else{
					type = 0
				}
				dataParam.sort = sort;
				dataParam.type = type;
			}


			$.ajax({
				url: '/home/prm_PrmGroupPurchase_getLineData',
				type: 'post',
				data: dataParam,
				dataType: 'json',
				success: function (data) {
					var div = '';
					$.each(data.items, function (i, val) {
						div = div + '<div class="xmg-goodsbox">' +
							'<div class="xmg-pic">' +
							'<a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey='+val.pkey+'">'+
							'<img src="${envConfig.imageBaseUrl}' + getProPic(val.pro.picture) + '" />' +
							'</a>'+
							'</div>' +
							'<div class="xmg-item">' +
							'<div class="xmg-top">' + val.pro.name + '</div>' +
							'<div class="xmg-common">' +
							'<div class="xmg-l">' +
							'<div class="xmg-t"><span>'+val.boughtCount+'</span> Sold</div>' +
							'<div class="xmg-b">' + judgeReview(val.pro.defaultReviewRating) + '<span>（' + val.pro.defaultReviewCount +
							'）</span> </div>' +
							'</div>' +
							'<div class="xmg-r">' +
							'<div class="xmg-t">${env.currency.symbols}' + val.source.curPrice + '</div>' +
							'<div class="xmg-b">${env.currency.symbols}' + val.pro.curPrice + '</div>' +
							'</div>' +
							'</div>' +
							'</div>' +
							'<div class="xmg-bottom">' +
							'<div class="favorite" onclick="addThisToFavorite(' + val.pro.pkey + ')">Favorite</div>' +
							'<div class="addcart" onclick="addThisToCart(' + val.pro.pkey +
							');"><img src="/home/static/themes/default/mobile/images/myfavorite-carttb.png"/>  Add to Cart</div>' +
							'</div>' +
							'</div>';
					});
					$("#proList").prepend(div);
				}
			})
		}); */

		/*评分判断*/
		function judgeReview(score) {
			score = Math.ceil(score);
			if (score == 0) {
				return "☆☆☆☆☆";
			}
			if (score == 1) {
				return "★☆☆☆☆";
			}
			if (score == 2) {
				return "★★☆☆☆";
			}
			if (score == 3) {
				return "★★★☆☆";
			}
			if (score == 4) {
				return "★★★★☆";
			}
			if (score == 5) {
				return "★★★★★";
			}
		}

		/*获取产品图片*/
		function getProPic(str) {
			return str.split(",")[0];
		}

		function addThisToCart(data) {
			$.ajax({
				url: '/home/usr_UsrCart_boughtPro',
				type: 'post',
				data: {
					"pdtPkey": data
				},
				dataType: 'json',
				success: function (data) {
					if (data.success == true) {
						layer.open({
							content: "添加成功"
							,skin: 'msg'
							,time: 2
						});
					} else if(data.ret == -1){
						window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl="+window.location.pathname+window.location.href.substring(window.location.href.indexOf("?"));
					}else{
						layer.open({
							content: data.msg
							,skin: 'msg'
							,time: 2
						});
					}
				}
			})
		}

		function addThisToFavorite(data) {
			$.ajax({
				url: '/home/usr_UsrFavorites_addFavorite',
				type: 'post',
				data: {
					"pdtPkey": data,
					"addType": 1
				},
				dataType: 'json',
				success: function (data) {
					if (data.success == true || data.ret == 1) {
						layer.open({
							content: "收藏成功"
							,skin: 'msg'
							,time: 2
						});
					} else if(data.ret == -1){
						window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl="+window.location.pathname+window.location.href.substring(window.location.href.indexOf("?"));
					} else {
						layer.open({
							content: data.msg
							,skin: 'msg'
							,time: 2
						});
					}
				}
			})
		}
		
		$('.flex-grow .main000 .xmg-xiala').click(function(){
    		$(this).toggleClass('show');
    	})
	</script>
</body>
</html>
