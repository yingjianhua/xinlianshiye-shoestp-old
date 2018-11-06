<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)https://www.shoestp.com/cart/checkout.html -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
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
    <link href="./static/css/page_cart1.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/global(1).js"></script>
</head>

<body class="lang_en w_1200">
    <%@ include file="/home/template/web-top.jsp" %>
	<%@ include file="/home/template/new-header.jsp" %>
	<div id="main" class="wide">
		<script type="text/javascript">
			$(document).ready(function () {
				cart_obj.cart_list()
			});
		</script>
		<script type="text/javascript">
		    $(window).resize(function () {
		        $(window).webDisplay(2);
		    });
		    $(window).webDisplay(2);
		    var stpshop_config = {
		        "domain": "https://www.shoestp.com",
		        "date": "2018/07/05 16:31:13",
		        "lang": "en",
		        "currency": "USD",
		        "currency_symbols": "$",
		        "currency_rate": "1.0000",
		        "FbAppId": "718848724966585",
		        "FbPixelOpen": "0",
		        "UserId": "0",
		        "TouristsShopping": "0",
		        "PaypalExcheckout": ""
		    };
		    var ueeshop_config = stpshop_config;
		</script>
		<div id="lib_cart">
			<div class="step">
				<div></div>
			</div>
			<div class="cartHeader">
				<a name="continue_shopping" class="textbtn fl" title="Continue Shopping">Continue Shopping</a>
				<a class="checkoutBtn fr" onclick="junpcart()">Proceed to Checkout</a>
			</div>
      <!-- 商品列表 之前是table -->
			<form name="shopping_cart">
        <!-- 单个商品列表 -->
        <div class="cart-list">
          
        </div>
    	</form>

      <!-- 模态框 -->
      <div class="modal"></div>
      <!-- 弹框 - 删除单个全部商品 -->
      <div class="pop-frame pop-frame-delete-product">
        <div class="pop-frame-content">
          <!-- 关闭按钮 -->
          <div class="pop-frame-close">&times;</div>
          Are you sure you
          <br>
          want to delete the product?
        </div>
        <div class="pop-frame-footer">
          <div class="btn-red btn-confirm"  onclick="delproduct()" >Sure</div>
          <div class="btn-red btn-cancel">Cancel</div>
        </div>
      </div>
      <!-- 弹框 - 删除单个全部商品 - end -->

      <!-- 弹框 - 删除单个商品的某个属性 -->
      <div class="pop-frame pop-frame-delete-style">
        <div class="pop-frame-content">
          <!-- 关闭按钮 -->
          <div class="pop-frame-close">&times;</div>
          Are you sure you
          <br>
          want to delete this style?
        </div>
        <div class="pop-frame-footer">
          <div class="btn-red btn-confirm" onclick="delspec()">Sure</div>
          <div class="btn-red btn-cancel">Cancel</div>
        </div>
      </div>
      <!-- 弹框 - 删除单个商品的某个属性 - end -->

      <!-- 弹框 - 添加商品的某个属性 -->
      <div class="pop-frame pop-frame-add-style">
        <div class="pop-frame-content flex-start">
          <!-- 关闭按钮 -->
          <div class="pop-frame-close">&times;</div>

          <!-- 类型选择框 -->
          <div class="style-selection-wrap">
            <div class="header-title">
              Style selevtion
            </div>
            <div class="style-selection-content">
              <div class="style-list style-list-left flex-center">
                <div class="style-item haveSelected">
                  <div class="style-pic">
                    <img src="./static/images/4fdc7f9940.jpg.500x500.jpg">
                  </div>
                  <div class="style-name">Orange</div>
                </div>
                <div class="style-item selected">
                  <div class="style-pic">
                    <img src="./static/images/4fdc7f9940.jpg.500x500.jpg">
                  </div>
                  <div class="style-name">Orange</div>
                </div>
                <div class="style-item">
                  <div class="style-pic">
                    <img src="./static/images/4fdc7f9940.jpg.500x500.jpg">
                  </div>
                  <div class="style-name">Orange</div>
                </div>
                <div class="style-item">
                  <div class="style-pic">
                    <img src="./static/images/4fdc7f9940.jpg.500x500.jpg">
                  </div>
                  <div class="style-name">Orange</div>
                </div>
              </div>
            </div>
          </div>

          <!-- 颜色选择框 -->
          <div class="style-selection-wrap">
            <div class="header-title">
              Size selevtion
            </div>
            <div class="style-selection-content">
              <div class="style-list style-list-right flex-center" style="height: 100px;">
                <div class="style-item style-item-small haveSelected">
                  <div class="style-name">28</div>
                </div>
                <div class="style-item style-item-small selected">
                  <div class="style-name">28</div>
                </div>
                <div class="style-item style-item-small">
                  <div class="style-name">28</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="pop-frame-footer">
          <div class="btn-red btn-confirm" onclick="addspecid()">Sure</div>
          <div class="btn-red btn-cancel">Cancel</div>
        </div>
      </div>
      <!-- 弹框 - 添加商品的某个属性 - end -->

			<div class="cartFooter">
				<a name="continue_shopping" class="textbtn fl" title="Continue Shopping">Continue Shopping</a>
				<a name="remove" class="textbtn fl" title="Remove">Remove</a>
				<a class="checkoutBtn fr">Proceed to Checkout</a>
				<div class="clear"></div>
			</div>
			 <!-- 没有商品时候  start-->
			 <div class="cartBox" id="cartboxdis" style="display: none;">
                <h2>Shopping Cart</h2>
                <div class="contents empty">
                    <h3>Your shopping cart is empty.</h3>
                    <div class="cartDraft">Looking for your items?
                        <br>Since you weren't able to complete your order, we've saved it as a draft order.
                        <a href="https://www.shoestp.com/account/">Click here</a> to go to My Orders where you can view and complete your draft order.</div>
                    <ul>
                        <li>
                            <span class="roundRedDot">•</span> You have the power to change this! Add all the
                            <a href="https://www.shoestp.com/c/mens-collections_0000">Mens Collections</a>,
                            <a href="https://www.shoestp.com/c/womens-collections_0000">Womens Collections</a>,
                            <a href="https://www.shoestp.com/c/kids-collections_0000">Kids Collections</a> you've been dreaming of!</li>
                        <li>
                            <span class="roundRedDot">•</span> Once you start filling your shopping cart, we'll show you all the special offers that
                            we have in store just for you!</li>
                    </ul>
                    <p>
                        <a name="continue_shopping" class="continueShoppingBtn" title="Continue Shopping">
                            <b>Continue Shopping</b>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 没有商品时候 end -->
			<div class="clear"></div>
<!-- 			<div class="cartBox"> -->
<!-- 				<h2>Your Recent History</h2> -->
<!-- 				<div class="contents products clearfix"> -->
<!-- 					<dl class="pro_item fl first"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper">2016 cheap wholesale fancy all kind of beach ..</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 					<dl class="pro_item fl"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p1148.html" title="woman sandals new design big size women lady big chunk heels sandals"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p1148.html" title="woman sandals new design big size women lady big chunk heels sandals">woman sandals new design big size women lady ..</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 					<dl class="pro_item fl"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper">2016 cheap wholesale fancy all kind of beach ..</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 					<dl class="pro_item fl"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p3413.html" title="Tracyee Flat Comfort Sandals Shoes Women 2018 Latest Ladies Designs Flat Jesus Sandals And Slippers"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p3413.html" title="Tracyee Flat Comfort Sandals Shoes Women 2018 Latest Ladies Designs Flat Jesus Sandals And Slippers">Tracyee Flat Comfort Sandals Shoes Women 2018..</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 					<dl class="pro_item fl"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p1143.html" title="fancy low price ladies sandals big sizes women flat roman sandals"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p1143.html" title="fancy low price ladies sandals big sizes women flat roman sandals">fancy low price ladies sandals big sizes wome..</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 					<dl class="pro_item fl"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p4619.html" title="Low Price Top Band Children\&#39;S Sandals"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p4619.html" title="Low Price Top Band Children\&#39;S Sandals">Low Price Top Band Children\'S Sandals</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 					<dl class="pro_item fl"> -->
<!-- 						<dt> -->
<!-- 							<a class="pic_box" href="https://www.shoestp.com/_p5733.html" title="Luxin 2018 Hot Sell Suede Leather Wear Resistant Lace-Up Outdoor Wear Shoes For Men\&#39;s Shoes"> -->
<!-- 								<img src=""> -->
<%-- 								<span></span> --%>
<!-- 							</a> -->
<!-- 						</dt> -->
<!-- 						<dd class="pro_name"> -->
<!-- 							<a href="https://www.shoestp.com/_p5733.html" title="Luxin 2018 Hot Sell Suede Leather Wear Resistant Lace-Up Outdoor Wear Shoes For Men\&#39;s Shoes">Luxin 2018 Hot Sell Suede Leather Wear Resist..</a> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
	</div>

	<%@ include file="/home/template/new-foot.jsp" %>
  <script>
      	var colorSpecs = {}; //
		var carts = {};		//查询当前采购商下的所有购物车
		var shops = {};		//所有产品下所对应的商家
		var products = {};	//购物车下的规格
		var specs = {};		//所有购物车下的所有规格
		var delspecid=0;
		var delproducts=0; 
		$(document).ready(function () {
		
			$.ajax({
	    		type:"post",
	    		url:"/home/usr_UsrCart_list",
	    		data:{"bean.cartType":0},
	    		dataType:'json',
	    		success:function(data){
	    			if(data.carts.length == 0){
	    				$("#cartboxdis").css("display", "block");
	    				$(".cartFooter").css("display", "none");
	    				$(".cartHeader").css("display", "none");
	    				return	
	    			} else{
	    				$("#cartboxdis").css("display", "none");
	    			};
	    			 $.each(data.carts, function(index, c) {
	    				carts[c.spec] = c
	    			})
	    			$.each(data.shops, function(index, c) {
	    				shops[c.id] = c
	    			})
	    			$.each(data.products, function(index, c) {
	    				products[c.id] = c
	    			})
	    			$.each(data.specs, function(index, c) {
						if(carts[c.id]) {
							carts[c.id].color = c.color;
							carts[c.id].size = c.size;
							carts[c.id].product = c.productId;
						}
	    				if(colorSpecs[c.productId] == undefined) {
	    					colorSpecs[c.productId] = {}
	    				}
	   					if(colorSpecs[c.productId][c.color] == undefined) {
	   						colorSpecs[c.productId][c.color] = []
	   					}
	   					colorSpecs[c.productId][c.color].push(c)
	    				specs[c.id] = c
	    			})
	    			show(data.carts) 
	    		}
	    	})
		});
		function show() {
			renderShop();
 			renderProduct();
 			renderCart();
 			renderSubCount();
 			renderCount();
		}
		function renderSubCount() {
			$.each(carts, function(index, cart) {
				
				var color = specs[cart.spec].color;
				var productId = specs[cart.spec].productId;
				var spec_dom = $("#spec"+productId+"_"+specs[cart.spec].colorId+"_"+cart.spec);
				spec_dom.find("input[name='cartid']").val(cart.id);
				spec_dom.next().next().find("input[name=qty]").val(cart.num);
				var price=parseInt(spec_dom.next("div[name=prPrice]").html());
				var num=parseInt(cart.num);
				var count=price*num;
				spec_dom.siblings(".total-price").children("span[name=amtTotal]").text(count);
			})
			
			
		}
		
		function renderCount(){
			var totals=0.0;
			$("#lib_cart .cart-list div[id^=product_]").each(function(i,val) {
				var nums=0;
				var id = $(this).attr("id").substring("product_".length);
				var total = 0;
				var num = 0;
				var product_dom = $(this);
				var speclist= $(this).find("[id^=spec"+id+"_]");
				$.each(speclist, function(){ 
					total += getAmtTotal($(this));
					num += getNum($(this));
				 }); 
				product_dom.children(".cart-list-item-base-info-wrap.flex-center").children(".goods-num.w_four.tc").text(num);
 				product_dom.children(".cart-list-item-base-info-wrap.flex-center").children(".total-price.w_five.tc").text(total);
			})
		}
		function getNum(spec_dom) {
			return parseInt(spec_dom.siblings(".goods-num.flex-center.w_four").children("input[name=qty]").val());
		}
		function getAmtTotal(spec_dom) {
			return parseInt(spec_dom.siblings(".total-price.w_five").children("span[name=amtTotal]").text());
		}
		
		function renderSpec(spec,index) {
			if(spec) {
				var s = $("[id^='spec"+spec.productId+"_"+spec.colorId+"_']:last");
				if(s.length == 0) {
					var str='';
					str=`
					<div class="cart-list-item-detail-info-item flex-start"  ">
                    <!-- 商品颜色图片 -->
                    <div class="goods-pic"><img src="${"${spec.img}"}"></div>
                    <!-- 同规格 - 商品数量 and 价格信息 -->
                    <div class="goods-spec-wrap">
                      <!-- 规格or颜色信息 -->
                      <!-- 同颜色下 不同型号的商品 item-list -->
                     
                      <div class="color-goods-list">
                      <div class="spec-or-color">Color:${"${spec.color}"}</div>
                      <div class="size-item"> 
                        <div class="color-goods-item flex-center">
                          <!-- 商品规格 -->
                          <div class="goods-spec" id="spec${"${spec.productId}"}_${"${spec.colorId}"}_${"${spec.id}"}" >
                            Number: ${"${spec.size}"}<input type="hidden" name="cartid"/>
                          </div>
                          <!-- 商品价格 -->
                          <div class="goods-price w_three" name="prPrice">
                          ${"${spec.price}"}
                          </div>
                          <!-- 商品数量 -->
                          <div class="goods-num flex-center w_four">
                            <span class="minus" name="reduce" onclick="reducenum(this)">-</span> <input type="text" class="qty" onKeyUp="keyup(this)" name="qty" value="0" maxlength="4"> <span class="plus" name="add" onclick="addnum(this)">+</span>
                          </div>
                          <!-- 单个商品总价 -->
                          <div class="total-price w_five">
                         	 $<span name="amtTotal">0.0</span>
                          </div>
                          <!-- 关闭 -->
                          <div class="close" onclick="closeSmallGoods(this)">&times;</div>
                        </div>
                        </div>
                      </div>
                    </div>
                  </div>
					`;
					
					$("#product_"+spec.productId+" .cart-list-item-detail-info-wrap").append(str);
				}else{
					var s2=$("[id^=spec"+spec.productId+"_"+spec.colorId+"_]:first");
					var str="";
					str=`
						 <div class="color-goods-item flex-center">
	                    <!-- 商品规格 -->
	                    <div class="goods-spec" id="spec${"${spec.productId}"}_${"${spec.colorId}"}_${"${spec.id}"}" >
	                      Number: ${"${spec.size}"}<input type="hidden" name="cartid"/>
	                    </div>
	                    <!-- 商品价格 -->
	                    <div class="goods-price w_three" name="prPrice">
	                    ${"${spec.price}"}
	                    </div>
	                    <!-- 商品数量 -->
	                    <div class="goods-num flex-center w_four">
	                      <span class="minus" name="reduce" onclick="reducenum(this)">-</span> <input type="text" class="qty" onKeyUp="keyup(this)" name="qty" value="0" maxlength="4"> <span class="plus" name="add" onclick="addnum(this)">+</span>
	                    </div>
	                    <!-- 单个商品总价 -->
	                    <div class="total-price w_five">
	                   	 <span name="amtTotal">0.0</span>
	                    </div>
	                    <!-- 关闭 -->
	                    <div class="close" onclick="closeSmallGoods(this)">&times;</div>
	                  </div>
					`;
					var list=  $("#product_"+spec.productId+" .cart-list-item-detail-info-wrap .spec-or-color");
					for (var k = 0; k <list.length; k++) {
						if($(list[k]).text().split(":")[1]==spec.color){
							$(list[k]).next(".size-item ").append(str);
						}
					}
				}
			}else{
				$.each(carts, function(index, c) {
					if($("[id^=spec"+c.product+"_"+c.colorId+"_]").length == 0) {
						renderColor(colorSpecs[c.product][c.color])
					}
				})
			}
		}	
		
		
				function CheckAll(btn){
					var checkbox = $(btn).parents(".cart-list-shop-item").find("input[type='checkbox']");
					if($(btn).attr("checked") == "checked"){
						for(var i in checkbox){
							checkbox[i].checked = true;
						}
					}else{
						for(var i in checkbox){
							checkbox[i].checked = false;
						}
					}
					
				}
				
		
		
		function renderShop() {
			var f = $("#lib_cart form .cart-list");
			f.empty();
			$.each(shops, function(index, d) {
				var str=`
				<div class="cart-list-shop-item" id="shop_${"${d.id}"}">
	            <div class="cart-list-shop-name flex-center">
	              <div class="checkbox w_one tc">
	                <input type="checkbox" onclick="CheckAll(this)">
	              </div>
	              <span class="shop-name">${"${d.name}"}</span>
	            </div>
	            <div class="shop-item-cart-list">
	              <!-- 表头 -->
	              <div class="cart-list-header flex-center">
	                <div class="checkbox w_one tc">
	                </div>
	                <div class="name w_two">Item</div>
	                <div class="price w_three tc">Price</div>
	                <div class="quantity w_four tc">Quantity</div>
	                <div class="total w_five tc">Total</div>
	              </div>

	              <!-- 仿tbody -->
	              <div class="cart-list-content"></div>
	              <!-- 仿tbody -->
	            </div>
	            
	          </div>
				`;
				f.append(str);
			})
		}
		
		function renderCart() {
			$.each(carts, function(index, c) {
				renderSpec(specs[c.spec],index);
			})
		}
		function renderProduct() {
			$.each(products, function(index, d) {
				var proId = d.id;
				var specId = new Array();
				var i = 0;
				var colorList = '';
				var listpruduct='';
				for (var color in colorSpecs[d.id]){
					colorList +=`
					<li>
	                    <span class="photo">
		                    <a href="/home/pdt_PdtProduct_gtProductsInfo?pkey=${"${d.id}"}" title="${"${d.name}"}"
		                        target="_blank">
		                      <img src="${"${colorSpecs[d.id][color][0].img}"}" alt="${"${d.name}"}">
		                    </a>
	                  </span>
              	  </li>`;
					specId[i] = colorSpecs[d.id][color][0].id;
					i++;
				};
				 listpruduct = `
				 <!-- 单个商品 - 里面有下拉项 -->
                <div class="cart-list-item" id="product_${"${d.id}"}">
                  <!-- 单个商品 基础信息 -->
                  <div class="cart-list-item-base-info-wrap flex-center">
                    <!-- 单个商品 选中状态 -->
                    <div class="checkbox w_one tc" >
                      <input type="checkbox"  name="select" value="${"${d.id}"}" >
                    </div>
                    <div class="w_two flex-start">
                      <!-- 商品图片 -->
                      <div class="goods-pic">
                        <img src="${"${d.img}"}">
                      </div>
                      <!-- 商品描述 -->
                      <div class="goods-descript">
                        <div class="goods-name">
                        ${"${d.name}"}
                        </div>
                        <div class="color-or-type">
                        ${"${d.code}"}
                        </div>

                    <!-- 自带轮播  -->
                        <div class="index_boxes index_item favorites horizontal">
                          <div class="viewport">
                            <ul class="list">
                            ${"${colorList}"}
                            </ul>
                          </div>
                          <a href="javascript:void(0);" hidefocus="true" class="btn btn_l left prev"></a>
                          <a href="javascript:void(0);" hidefocus="true" class="btn btn_r right next"></a>
                        </div>

                        <div class="bursting" id="${"${d.id}"}"><span class="f30">+</span> Bursting</div>

                    <!--  -->
                      </div>
                    </div>
                    <div class="goods-price w_three tc">
                    
                    </div>
                    <!-- 商品数量 -->
                    <div class="goods-num w_four tc">
                      1
                    </div>
                    <div class="total-price w_five tc">
                     0.0
                    </div>
                    <!-- 操作按钮 -->
                    <!-- 单个商品的 整体删除操作 -->
                    <div class="close" onclick="pruductdelshow(this)">&times;</div>
                    <div class="slide"></div>
                  </div>
                  <!-- 单个商品 基础信息 - end -->
                  <div class="cart-list-item-detail-info-wrap">
                  </div>
                  
                </div>
                
				`;
				
				$("#shop_"+d.shopId+" .cart-list-content").append(listpruduct);
			})
		// 原先轮播
			$(".index_boxes.favorites").slide({mainCell:".viewport ul",autoPage:true,effect:"left",vis:4,trigger:"click",effect:"leftLoop"});
			//点击下拉or收回
			  $(".cart-list-item-base-info-wrap").delegate(".slide", "click", function(e){
			    // 按钮转向
			    $(this).toggleClass("turn-around");
			    // 内容收拢
			    $(this).closest(".cart-list-item").find(".cart-list-item-detail-info-wrap").slideToggle();
			  })

// 			  // 删除单个全部商品时，提示
// 			  $(".cart-list-item-base-info-wrap").delegate(".close", "click", function(){
// 			    $(".modal,.pop-frame-delete-product").fadeIn();
// 			  })
			  // 添加商品，提示
			  $(".cart-list-item-base-info-wrap .goods-descript").delegate(".bursting", "click", function(){
				  $(".style-list.style-list-left.flex-center").empty();
				  $(".style-list.style-list-right.flex-center").empty();
				  var str='';
				  var  pid= $(this).parents(".cart-list-item").attr("id").substring("product_".length);
				  var numb=1;
				  for (var color in colorSpecs[pid]){
					  var strs='';
					  if($("#lib_cart .cart-list-item [id^='spec"+pid+"_"+colorSpecs[pid][color][0].colorId+"_']").length>0) {
								  if(numb==1)
								  {
											  str+=`<div class="style-item haveSelected "   data_id="${"${colorSpecs[pid][color][0].id}"}" onclick="change(this)"> <div class="style-pic"><img src="${"${colorSpecs[pid][color][0].img}"}"></div><div class="style-name">${"${color}"}</div></div>`;
 											 strs+=`<div  class="style-item-small" noclick="true"  style="" binding="${"${colorSpecs[pid][color][0].id}"}"  >`;
											$.each(colorSpecs[pid][color], function(index, spec) {
												if($("#spec"+pid+"_"+colorSpecs[pid][color][0].colorId+"_"+spec.id+"").length>0){
													strs+=`<div class="style-item haveSelected " noclick="true" id="${"${spec.id}"}"><div class="style-name">${"${spec.size}"}</div></div>`;
												}else{
													strs+=`<div class="style-item"   id="${"${spec.id}"}"><div class="style-name">${"${spec.size}"}</div></div>`;
												}
												
					  						})
											strs=strs+'</div>';
 											numb=0;
								  }else{
									  str+=`<div class="style-item haveSelected" data_id="${"${colorSpecs[pid][color][0].id}"}" onclick="change(this)" > <div class="style-pic"><img src="${"${colorSpecs[pid][color][0].img}"}"></div><div class="style-name">${"${color}"}</div></div>`;
										 strs+=`<div  class=" style-item-small style-list-right flex-center" noclick="true" style="display:none;" binding="${"${colorSpecs[pid][color][0].id}"}"  >`;
										$.each(colorSpecs[pid][color], function(index, spec) {
											if($("#spec"+pid+"_"+colorSpecs[pid][color][0].colorId+"_"+spec.id+"").length>0){
												strs+=`<div class="style-item haveSelected "  id="${"${spec.id}"}"><div class="style-name">${"${spec.size}"}</div></div>`;
											}else{
												strs+=`<div class="style-item "  id="${"${spec.id}"}"><div class="style-name">${"${spec.size}"}</div></div>`;
											}
											
				  						})
										strs=strs+`</div>`;
								  }
							 $(".style-list.style-list-right.flex-center").append(strs);
					  }else{
						  str+=`<div class="style-item" data_id="${"${colorSpecs[pid][color][0].id}"}" onclick="change(this)"  > <div class="style-pic"><img src="${"${colorSpecs[pid][color][0].img}"}"></div><div class="style-name">${"${color}"}</div></div>`;
							 strs+=`<div  class=" style-item-small style-list-right flex-center" style="display:none;" binding="${"${colorSpecs[pid][color][0].id}"}"  >`;
							$.each(colorSpecs[pid][color], function(index, spec) {
								if($("#spec"+pid+"_"+colorSpecs[pid][color][0].colorId+"_"+spec.id+"").length>0){
									strs+=`<div class="style-item  " id="${"${spec.id}"}"><div class="style-name">${"${spec.size}"}</div></div>`;
								}else{
									strs+=`<div class="style-item " id="${"${spec.id}"}"><div class="style-name">${"${spec.size}"}</div></div>`;
								}
								
	  						})
							strs=strs+`</div>`;
							 $(".style-list.style-list-right.flex-center").append(strs);
					  }
				  }
				  $(".style-list.style-list-left.flex-center").append(str);
			    $(".modal,.pop-frame-add-style").fadeIn();
			  })
			  // 取消弹框 and 模态框
			  $(".pop-frame .pop-frame-close,.pop-frame .btn-cancel").click(function(){
			    $(".modal,.pop-frame,.pop-frame-add-style").fadeOut();
			  })

			  // 新添商品时,颜色\尺码选中事件
			  $(".pop-frame-add-style .style-selection-wrap").delegate(".style-item", "click", function(){
				  if ($(this).attr("noclick")){
					  return;
				  }
			    $(this).addClass("selected").siblings(".style-item").removeClass("selected");
			  })
		}
		
		function change(bb){
			var binding = $(bb).attr("data_id");
			$(".style-item-small").hide()
			$("div[binding="+binding+"]").show();
		}

		function addnum(btn){
    		var input= $(btn).prev().val();
    		 var add=parseInt(input)+1;
    		 $(btn).prev(".qty").val(add);
    		var price=parseInt($(btn).parent().prev().html());
    		var count=add*price;
    		$(btn).parent().next().children("span").text(count);
    		renderCount();
    	}
    	function reducenum(btn){
    	 	var input= $(btn).next().val();
    		if(parseInt(input)>1){
    			var reducenum=parseInt(input)-1;
       		 	$(btn).next(".qty").val(reducenum);
       		 var price=parseInt($(btn).parent().prev().html());
     		var count=reducenum*price;
     		$(btn).parent().next().children("span").text(count);
     		renderCount();
    		}else{
    			alert("最少数量不能小于1");
    		}
    		
    	}
    	function closeSmallGoods(target){
			  delspecid=$(target).siblings(".goods-spec").children("input[name='cartid']").val();
		    $(".modal,.pop-frame-delete-style").fadeIn();
		  }
    	
    	function pruductdelshow(target){
    		 delproducts=$(target).parents(".cart-list-item").attr("id").substring("product_".length);
    		 $(".modal,.pop-frame-delete-product").fadeIn();
    	}
    	
		function delspec(){
			console.log(delspecid);
			if(delspecid>0){
				$.ajax({
					type:'post',
					url:'/home/usr_UsrCart_del',
					data:{"bean.pkey":delspecid},
					dataType:'JSON',
					success:function(){
						location.reload();
					}
				})
			}
		}
    	
		 function keyup(btn){
         	var input= $(btn).val();
         	if( input==null||input==""){
         		$(btn).val(1);
         	}
    			var add=parseInt(input);
    			 var price=parseInt($(btn).parent().prev().html());
    			var count=add*price;
    			$(btn).parent().next().children("span").text(count);
					renderCount();
		};
		
		function delproduct()
    	{
			if(delproducts>0){
				var  productid = delproducts;
				$("#lib_cart [id^=spec"+productid+"_]").each(function(){
					var  cartnum=$(this).find("input[name='cartid']").val();
					$.ajax({
    	    			type:'post',
    	    			url:'/home/usr_UsrCart_del?bean.pkey='+cartnum,
    	    			dataType : "json",
    	    			success : function(data){
    	    			}
    	    		})
				})
				location.reload(); 
			}
    			
    				
    	} 
		//此处提交事件写入封装JS里头
// 		function junpcart(){
// 			var cartsnext =  new Array();	
// 			var checkz = $(".cartFrom").find("[id^=product] input[type=checkbox]:checked");
//     		var baba = $(checkz[0]).parents(".cart-list-item");
//     		var blean=0;
//     		for(var i =0;i<checkz.length;i++){
//     			if(!$.contains(baba[0],checkz[i])){
//     				blean=1;
//     			}
//     			var proid = $(checkz[i]).val();//产品ID
//     			var spec = $("tr[id^=spec"+proid+"]");
//     			for(var j = 0;j <spec.length;j++){
//     				var map={};
//     				var cartid = $(spec[j]).find("input[name=cartid]").val();
//     				var count = $(spec[j]).find(".qty").val();
//     				map['id']=cartid;
//     				map['num']=count;
//     				cartsnext.push(map);
    				
//     			}
//     		}
//     		console.log(cartsnext);
//     		if(blean==0)
//     		{
//     			console.log(cartsnext);
//     		//	location.href="/home/cart2.jsp";
//     		}else{
//     			alert("一次只能购买一家店铺的商品");
//     		}
//     	} 	
		function addspecid(){
			 var cheval;
			var divs = $("div.style-item-small:visible");
			
			cheval=divs.children(".style-item.selected").attr("id")
				if(typeof cheval=='undefined'){
					alert("请选中商品规格！！");
				}else{
				var postData={};
				postData[cheval]=1;
					$.ajax({
						type:'post',
						url:'/home/usr_UsrCart_InsCart',
						data:{carts:JSON.stringify(postData)},
						dataType:'JSON',
						success:function(){
							location.reload(); 
						}
					})
				}
					
		}
  </script>
  <script>

  </script>
  
</body>

</html>
