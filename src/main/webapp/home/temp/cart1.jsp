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
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
</head>

<body class="lang_en w_1200">
	<%@ include file="/home/template/web-top.jsp" %>
	<%@ include file="/home/template/new-header.jsp" %>

    <div id="main" class="wide">
        <div id="lib_cart">
            <div class="step">
                <div></div>
            </div>
            <!-- 有商品时候 start -->
            <div class="cartHeader" >
                <a name="continue_shopping" class="textbtn fl" title="Continue Shopping">Continue Shopping</a>
                <a class="checkoutBtn fr" onclick="jumpcart()">Proceed to Checkout</a>
            </div>
            <form name="shopping_cart">
                <div class="cartFrom" shop="1">
                </div>
            </form>
            <div class="cartFooter">
                <a name="continue_shopping" class="textbtn fl" title="Continue Shopping">Continue Shopping</a>
                <a name="remove" class="textbtn fl" title="Remove">Remove</a>
                <a class="checkoutBtn fr"  onclick="jumpcart()">Proceed to Checkout</a>
                <div class="clear"></div>
            </div>
            <!-- 有商品时候 end -->
            <!-- 没有商品时候  start-->
            <div class="cartBox" style="display:none;">
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
            <div class="cartBox">
                <h2>Your Recent History</h2>
                <div class="contents products clearfix">
                    <dl class="pro_item fl first">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p1143.html" title="fancy low price ladies sandals big sizes women flat roman sandals">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p1143.html" title="fancy low price ladies sandals big sizes women flat roman sandals">fancy low price ladies sandals big sizes wome..</a>
                        </dd>
                    </dl>
                    <dl class="pro_item fl">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p3413.html" title="Tracyee Flat Comfort Sandals Shoes Women 2018 Latest Ladies Designs Flat Jesus Sandals And Slippers">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p3413.html" title="Tracyee Flat Comfort Sandals Shoes Women 2018 Latest Ladies Designs Flat Jesus Sandals And Slippers">Tracyee Flat Comfort Sandals Shoes Women 2018..</a>
                        </dd>
                    </dl>
                    <dl class="pro_item fl">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper">2016 cheap wholesale fancy all kind of beach ..</a>
                        </dd>
                    </dl>
                    <dl class="pro_item fl">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p1148.html" title="woman sandals new design big size women lady big chunk heels sandals">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p1148.html" title="woman sandals new design big size women lady big chunk heels sandals">woman sandals new design big size women lady ..</a>
                        </dd>
                    </dl>
                    <dl class="pro_item fl">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p1199.html" title="2016 cheap wholesale fancy all kind of beach flat pu slipper emoji slipper ladies color picture lady fashion woman slipper">2016 cheap wholesale fancy all kind of beach ..</a>
                        </dd>
                    </dl>
                    <dl class="pro_item fl">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p3413.html" title="Tracyee Flat Comfort Sandals Shoes Women 2018 Latest Ladies Designs Flat Jesus Sandals And Slippers">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p3413.html" title="Tracyee Flat Comfort Sandals Shoes Women 2018 Latest Ladies Designs Flat Jesus Sandals And Slippers">Tracyee Flat Comfort Sandals Shoes Women 2018..</a>
                        </dd>
                    </dl>
                    <dl class="pro_item fl">
                        <dt>
                            <a class="pic_box" href="https://www.shoestp.com/_p1143.html" title="fancy low price ladies sandals big sizes women flat roman sandals">
                                <img src="">
                                <span></span>
                            </a>
                        </dt>
                        <dd class="pro_name">
                            <a href="https://www.shoestp.com/_p1143.html" title="fancy low price ladies sandals big sizes women flat roman sandals">fancy low price ladies sandals big sizes wome..</a>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>

    <div id="new_foot">
        <div class="wide">
            <div class="foot_list fl">
                <div class="title">Help</div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/about-payment_a0061.html" title="About Payment" target="_blank">About Payment</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/faq_a0060.html" title="FAQ" target="_blank">FAQ</a>
                </div>
            </div>
            <div class="foot_list fl">
                <div class="title">Terms &amp; conditions</div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/privacy-policy_a0065.html" title="Privacy Policy" target="_blank">Privacy Policy</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/terms-amp-conditions_a0064.html" title="Terms &amp; conditions" target="_blank">Terms &amp; conditions</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/return-policy_a0063.html" title="Return Policy" target="_blank">Return Policy</a>
                </div>
            </div>
            <div class="foot_list fl">
                <div class="title">ABOUT US</div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/about-shoestpcom_a0067.html" title="About shoestp.com" target="_blank">About shoestp.com</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/shoestp-cooperation-program_a0066.html" title="ShoeSTP Cooperation Program" target="_blank">ShoeSTP Cooperation Program</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/contact-us_a0062.html" title="Contact Us" target="_blank">Contact Us</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/manage/" title="供应商注册登陆" target="_blank">供应商注册登陆</a>
                </div>
            </div>
            <div class="foot_letter fr">
                <div class="t">Subscribe to Our Newsletter</div>
                <div class="t0">Get information of our latest products and promotions</div>
                <div class="letter_form">
                    <form id="newsletter_form">
                        <input type="text" value="" name="Email" class="text" notnull="" format="Email">
                        <input type="submit" value="Subscribe" class="btn button">
                    </form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="folink">
            <div class="wide clean">
                <div class="img fl pic_box">
                    <a href="https://www.shoeslogo.com/" target="_blank">
                        <img src="" alt="shoeslogo.com">
                    </a>
                    <span></span>
                </div>
                <div class="img fl pic_box">
                    <a href="http://www.shoesmat.com/" target="_blank">
                        <img src="" alt="shoesmat.com">
                    </a>
                    <span></span>
                </div>
                <div class="img fl pic_box">
                    <a href="http://www.shoesrd.com/" target="_blank">
                        <img src="" alt="shoesrd.com">
                    </a>
                    <span></span>
                </div>
                <div class="img fl pic_box">
                    <a href="http://www.wzsomt.com/" target="_blank">
                        <img src="" alt="wzsomt.com">
                    </a>
                    <span></span>
                </div>
            </div>
        </div>
        <div class="focontact">
            <div class="wide">
                <div class="fc fl">
                    <div class="fl">Contact Us:</div>
                    <div class="fl fcfollow">
                        <div class="follow_toolbox clearfix">
                            <ul>
                                <li>
                                    <a rel="nofollow" class="follow_facebook" href="https://www.facebook.com/ShoeslogoShoestp/?ref=bookmarks" target="_blank"
                                        title="Facebook">Facebook</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_twitter" href="https://twitter.com/shoes_logo" target="_blank" title="Twitter">Twitter</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_pinterest" href="https://www.pinterest.com/shoeslogo/" target="_blank" title="Pinterest">Pinterest</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_youtube" href="https://www.youtube.com/channel/UCUHObFxGQ_FaHCKolyZ9k0w" target="_blank"
                                        title="YouTube">YouTube</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_linkedin" href="http://www.linkedin.com/company/13430792/admin/updates/" target="_blank"
                                        title="LinkedIn">LinkedIn</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="fd fr">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号 &nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
        </div>
    </div>
	 
    <div id="racePop"  style=" display: none;position:absolute;left:50%;width: 400px;height: 400px;top: 400px;background-color: white; border-style: solid; border-width: 1px;" >
        <div class="div" style="width: 100%; height: 10%; overflow-y: auto;top: 200px;">
        	款式选择
        </div>
        <form id="">
        
        <div>
        	<ul id="colorspec">
        	</ul>
        </div>
        <div id="specsizes">
        </div>
        </form>
        <div> <button onclick="comti" >提交</button><button onclick="colorcancel()">取消</button></div>
    </div>
	</body>

<script >
	
	
	
	var colorSpecs = {}; //
	var carts = {};		//查询当前采购商下的所有购物车
	var shops = {};		//所有产品下所对应的商家
	var products = {};	//购物车下的规格
	var specs = {};		//所有购物车下的所有规格
	function renderShop() {
		var f = $("#lib_cart form .cartFrom");
		f.empty();
		$.each(shops, function(index, d) {
			f.append(
					'<div><input type="checkbox" onclick="CheckAll(this)" /> '+d.name+'</div>'
			        +'<table id="shop_'+d.id+'" class="table_'+index+'">'
			        +' <thead>'
			        +' <tr>'
			        +'  <td width="1%" class="first"></td>'
			        +'  <td width="16%">Item</td>'
			        +'  <td width="16%">描述</td>'
			        +'  <td width="16%">尺寸</td>'
			        +'  <td width="16%">Price</td>'
			        +'  <td width="20%" class="quantity">Quantity</td>'
			        +'  <td width="16%">Total</td>'
			        +'  </tr>'
			        +'  </thead>'
			        +' <tbody>'
			        +' </tbody>'
			        +'<tfoot class="tfoot_'+index+'">'
			        +' <tr class="shopping_cart_total">'
			        +'  <td colspan="6" align="right">'
			        +'    <label>Subtotal</label> ('
			        +'    <span class="countnums">1 items</span>) : </td>'
			        +'  <td>'
			        +'    <strong  class="countprice"></strong>'
			        +'  </td>'
			        +' </tr>'
			        +'</tfoot>'
			        +'</table>'
			        +'</div>')
		})
	}
	
	function CheckAll(btn){
		var checkbox = $(btn).parent().next("table").find("input[type='checkbox']");
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
	
	
	
	
	function renderProduct() {
		$.each(products, function(index, d) {
			var proId = d.id;
			var colorList = '';
			
			var specId = new Array();
			var i = 0;
			for (var color in colorSpecs[d.id]){
				
				colorList +='<img src="'+colorSpecs[d.id][color][0].img+'" width="30" height="30" >';
				specId[i] = colorSpecs[d.id][color][0].id;
				i++;
			}
			
			$("#shop_"+d.shopId+" tbody").append(
					'<tr id="product_'+d.id+'">'
			        +'<td class="last">'
			        +'    <input type="checkbox" name="select" value="'+proId+'" class="va_m"> '
			        +'</td>'
			        +'<td>'
			        +'	<img src="'+d.img+'">'
			        +'</td>'
			        +'<td class="prList last">'
			        +'    <h4>'+d.name+'</h4>'
			        +'    <p>'+d.code+'</p>'
			        +'<a href="#" onclick="addspecform('+d.id+')">添加颜色规格</a>'
			        +'    <p class="remark">'+colorList+'</p>'
			        +'</td>'
			        +'<td></td>'
			        +'<td class="prPrice last">'
			        +'    <p price="'+d.price+'" discount="100">$'+d.price+'</p>'
			        +'</td>'
			        +'<td class="prQuant last" start="1">'
			        +' 1shuang'
			        +'</td>'
			        +'<td class="prAmount last">'
			        +'    <p price="">$ <span >0.0</span></p>'
			        +'<a href="#"  datadel="'+d.id+'" onclick="delpdt(this)">删除</a>'
			        +'</td>'
			        +'</tr>');
		})
	}
	function renderCart() {
		$.each(carts, function(index, c) {
			renderSpec(specs[c.spec],index);
		})
	}
	function renderSpec(spec,index) {
		if(spec) {
			var s = $("[id^=spec"+spec.productId+"_"+spec.color+"_]:last");
			if(s.length == 0) {
				$("#product_"+spec.productId).after(
					'<tr id="spec'+spec.productId+'_'+spec.color+'_'+spec.id+'" >'
		        	+'<td class="last"></td>'
		        	+'<td class="color" rowspan="1">'
		        	+'	<img src="'+spec.img+'">'
		        	+'</td>'
		        	+'<td class="color" rowspan="1">'
		        	+'		<h4>color : '+spec.color+' </h4>'
		        	+'</td>'
		        	+'<td>Number<span name="specsize" >:'+spec.size+'</span><input type="hidden" name="cartid"/></td>'
		        	+'<td name="prPrice">$<span>'+spec.price+'</span></td>'
		        	+'<td>'
		        	+'	<img src="../images/shou.png" name="reduce" onclick="reducenum(this)">'
		        	+'  <input class="qty" type="text" onKeyUp="keyup(this)" name="qty" value="0" maxlength="4">'
		        	+'  <img src="../images/shou.png" name="add" onclick="addnum(this)">'
		        	+'</td>'
		        	+'<td>$<span name="amtTotal">0.0</span></td>'
		        	+'</tr>');
			} else {
				var s2 = $("[id^=spec"+spec.productId+"_"+spec.color+"_]:first");
				s2.children(".color").attr("rowspan", parseInt(s2.children(".color").attr("rowspan"))+1)
				s.after(
					'<tr id="spec'+spec.productId+'_'+spec.color+'_'+spec.id+'">'
                	+'<td class="last">  </td>'
		        	+'<td>Number<span>:'+spec.size+'</span><input type="hidden" name="cartid"/></td>'
		        	+'<td name="prPrice">$<span>'+spec.price+'</span></td>'
                	+'<td>'
                	+'	<img src="../images/shou.png" name="reduce" onclick="reducenum(this)" >'
                	+'    <input class="qty" type="text" onKeyUp="keyup(this)" name="qty" value="0" maxlength="4">'
                	+'    <img src="../images/shou.png" name="add" onclick="addnum(this)">'
                	+'</td>'
                	+'<td>$<span name="amtTotal">0.0</span></td>'
                	+'</tr>');
			}
		} else {
			$.each(carts, function(index, c) {
				if($("[id^=spec"+c.product+"_"+c.color+"_]").length == 0) {
					renderColor(colorSpecs[c.product][c.color])
				}
			})
		}
	}

	function show() {
		//console.log(colorSpecs)
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
			var spec_dom = $("#spec"+productId+"_"+color+"_"+cart.spec);
			console.log(cart.id);
			spec_dom.find("input[name=cartid]").val(cart.id);
			spec_dom.find("input[name=qty]").val(cart.num);
			var price=parseInt(spec_dom.find("td[name=prPrice] span").text());
			var num=parseInt(cart.num);
			var count=price*num;
			spec_dom.find("td:last span").text(count);
		})
	}
	function renderCount() {
		var totals=0.0;
		$("#lib_cart form .cartFrom tr[id^=product_]").each(function(i,val) {
			var nums=0;
			var id = $(this).attr("id").substring("product_".length);
			var total = 0;
			var num = 0;
			var product_dom = $(this);
			$(this).siblings("[id^=spec"+id+"_]").each(function() {
				total += getAmtTotal($(this));
				num += getNum($(this));
			})
			product_dom.find(".prQuant").text(num);
			product_dom.find(".prAmount").children().children("span").text(total);
		})
		var tables = $("table[class^=table_]");
		for(var i =0;i<tables.length;i++){
			var qty = $(tables[i]).find("input[name=qty]");
			var totalNums = 0;
			var totalprice=0;
			for(var j =0;j<qty.length;j++){
				 totalNums = totalNums + Number($(qty[j]).val());
				 totalprice = totalprice + Number($(qty[j]).parent().next().children("span").text());

			}
			$(".table_" + (i+1)).find(".countnums").html(totalNums+" items");
			$(".table_" + (i+1)).find(".countprice").html(totalprice);

		}
	}
	function getNum(spec_dom) {
		return parseInt(spec_dom.find("input[name=qty]").val());
	}
	function getAmtTotal(spec_dom) {
		return parseInt(spec_dom.find("td span[name=amtTotal]").text());
	}

	$(document).ready(function () {
		$.ajax({
    		type:"post",
    		url:"/home/usr_UsrCart_list",
    		dataType:'json',
    		success:function(data){

    			if(data == null){
    				$("#cartBox").prop("display", "inline-block");
    				return
    			} ;
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


            function keyup(btn){
            	var input= $(btn).val();
            	if( input==""){
            		input=0;
            	}
       			var add=parseInt(input);
       			var price=parseInt($(btn).parent().prev().children("span").text());
       			var count=add*price;
       		$(btn).parent().next().children("span").text(count);
					renderCount();
				};

</script>
 <script type="text/javascript">
    	function delpdt(btn)
    	{
    		var r=confirm("确定删除该商品吗?")
    		  if (r==true)
    		    {
    				var  productid = $(btn).attr('datadel');
    				$("#lib_cart form .cartFrom tr[id^=product_]").each(function(){
						var id=$(this).attr("id").substring("product_".length);
						$(this).siblings("[id^=spec"+id+"_]").each(function(){
							var specid=$(this).attr("id").substring("spec".length).split('_')[0];
							if(productid==specid)
							{
								var specids=$(this).attr("id").substring("spec".length).split('_')[2];
								$.each(carts,function(index,cart){

									if(specids==carts[index].spec)
									{
										$.ajax({
					    	    			type:'post',
					    	    			url:'/home/usr_UsrCart_del?bean.pkey='+carts[index].id,
					    	    			dataType : "json",
					    	    			success : function(data){

					    	    			}

					    	    		})
									}
								})

							}
						})
    				})
    				location.reload();
    		    }
    	}
    	function addnum(btn){
    		var input= $(btn).prev().val();
    		 var add=parseInt(input)+1;
    		 $(btn).prev().val(add);
    		var price=parseInt($(btn).parent().prev().children("span").text());
    		var count=add*price;
    		$(btn).parent().next().children("span").text(count);
    		renderCount();
    	}
    	function reducenum(btn){
    	 	var input= $(btn).next().val();
    		if(parseInt(input)>0){
    			var reducenum=parseInt(input)-1;
       		 	$(btn).next().val(reducenum);
       		 var price=parseInt($(btn).parent().prev().children("span").text());
     		var count=reducenum*price;
     		$(btn).parent().next().children("span").text(count);
     		renderCount();
    		}else{
    			alert("最少数量不能小于0");
    		}

    	}
    	function addspecform(btn){
    		$("#racePop").show();
				var colorList = '';
 			//console.log(colorSpecs);
 			$("#colorspec").empty();
    		$.each(products, function(index, d) {
    			if(d.id==btn)
    			{
    				var booleannum=0;
    				for (var color in colorSpecs[d.id]){
    					$("#lib_cart form .cartFrom tr[id^=product_]").each(function(){
    						var id=$(this).attr("id").substring("product_".length);
    						$(this).siblings("[id^=spec"+id+"_]").each(function(){
    							var specid=$(this).attr("id").split('_')[2];
    							if(colorSpecs[d.id][color][0].id==specid )
    							{
    								booleannum=1;
    							}
    						})
    					})
    					if(booleannum==1)
    					{
    						colorList=colorList+'<li><input type="checkbox" checked="checked" value="'+colorSpecs[d.id][color][0].id+'"/> <img src="'+colorSpecs[d.id][color][0].img+'" width="30" height="30" >颜色："'+color+'"</li> ';
    					}else{
    						colorList=colorList+'<li><input type="checkbox" value="'+colorSpecs[d.id][color][0].id+'"/> <img src="'+colorSpecs[d.id][color][0].img+'" width="30" height="30" >颜色："'+color+'"</li> ';
    					}
    					booleannum=0;
    				    }
    			}
    		})
    		$("#colorspec").append(colorList);
    	}
    	function colorcancel(){
    		$("#racePop").hide();
    	}

    	function specsizelist(btn){
    		var str="<ul>";
    		$.each(products, function(index, d) {
    			if(d.id==btn)
    			{
    				for (var color in colorSpecs[d.id]){
    					$("#lib_cart form .cartFrom tr[id^=product_]").each(function(){
    						var id=$(this).attr("id").substring("product_".length);
    						$(this).siblings("[id^=spec"+id+"_]").each(function(){
    							var specid=$(this).attr("id").split('_')[2];
    							if(colorSpecs[d.id][color][0].id==specid )
    							{
    									str=str+"<li><input type='checkbox'  checked='checked' value='"+colorSpecs[d.id][color][0].id+"'/></li>";
    							}
    						})
    					})
    				    }
    			}
    		})
			$("#specsizes").append("");
    	}
    	function jumpcart(){

    		var cartsnext =  new Array();

			var checkz = $(".cartFrom").find("tr[id^=product] input[type=checkbox]:checked");
    		var baba = $(checkz[0]).parents("table");
    		var blean=0;
    		for(var i =0;i<checkz.length;i++){
    			if(!$.contains(baba[0],checkz[i])){
    				blean=1;
    			}
    			var proid = $(checkz[i]).val();//产品ID
    			var spec = $("tr[id^=spec"+proid+"]");
    			for(var j = 0;j <spec.length;j++){
    				var map={};

    				var cartid = $(spec[j]).find("input[name=cartid]").val();
    				var sId = $(spec[j]).attr("id").split('_')[2];
    				var count = $(spec[j]).find(".qty").val();
    				map['id']=cartid;
    				map['num']=count;
    				map['spec']=sId;
    				cartsnext.push(map);

    			}
    		}
    		if(blean==0)
    		{
    			console.log(cartsnext);

    		}else{
    			alert("一次只能购买一家店铺的商品");
    		}
    	} 	
      </script>
    <!-- Google Tag Manager -->
    <script>
        (function (w, d, s, l, i) {
            w[l] = w[l] || [];
            w[l].push({
                'gtm.start': new Date().getTime(),
                event: 'gtm.js'
            });
            var f = d.getElementsByTagName(s)[0],
                j = d.createElement(s),
                dl = l != 'dataLayer' ? '&l=' + l : '';
            j.async = true;
            j.src =
                'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
            f.parentNode.insertBefore(j, f);
        })(window, document, 'script', 'dataLayer', 'GTM-KNPHSJ6');
    </script>
    <!-- End Google Tag Manager -->
    <script type="text/javascript">
        $(window).resize(function () {
            $(window).webDisplay(2);
        });
        $(window).webDisplay(2);
        var stpshop_config = {
            "domain": "https://www.shoestp.com",
            "date": "2018/07/16 11:11:32",
            "lang": "en",
            "currency": "USD",
            "currency_symbols": "$",
            "currency_rate": "1.0000",
            "FbAppId": "718848724966585",
            "FbPixelOpen": "0",
            "UserId": "224",
            "TouristsShopping": "0",
            "PaypalExcheckout": ""
        };
        var ueeshop_config = stpshop_config;
    </script>
</html>