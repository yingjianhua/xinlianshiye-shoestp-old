<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="./static/css/page_cart1.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="./static/js/layer.js"></script>
		<script src="./static/js/jquery.SuperSlide.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <style>
      body{
        /* 显示详情时高度加大，极可能出现滚动条，页面会向左缩动滚动条的宽度 - 以此防止 */
        overflow-y: scroll;
      }
      .jp-or-np{
        height:36px;
        line-height:36px;
        border-top:1px dashed #e5e5e5;
        margin-top:8px;
      }
      .jp-or-np dl,
      .jp-or-np dt,
      .jp-or-np dd,
      .jp-or-np dd input{
        float:left;
      }
      .jp-or-np dd input{
        margin:10px 10px 0 0;
      }
      .jp-or-np dd span{
        color:#e5e5e5;
      }
      .jp-or-np dd{
        margin-right:10px;
      }
      .jp-or-np dd b{
        float:left;
        min-width:25px;
        height:20px;
        padding-left:39px;
        padding-right:20px;
        line-height:20px;
        border-radius:6px;
        background:#043d81;
        color:#fff;
        margin:7px 0 0 0 ;
      }
      .jp-or-np dd b.jp{
        background:#043d81 url(./static/images/jpcartjptb.png) no-repeat 10px -1px;
      }
      .jp-or-np dd b.np{
        background:#043d81 url(./static/images/jpcartnptb.png) no-repeat 10px -1px;
      }
      .jp-or-np .xmg-tishi{
        position:relative;
        float:left;
      }
      .jp-or-np .xmg-tishi:hover .xmg-tishi111{
        display:block;
      }
      .jp-or-np .xmg-tishi .xmg-tishi111{
        position:absolute;
        right:-6px;
        top:33px;
        display:none;
        width:192px;
        height:38px;
        padding:2px 0 2px 8px;
        background:#fff;
        border:1px solid #e5e5e5;
        line-height:19px;
        box-shadow: 0px 0px 14px 0px rgba(55, 55, 55, 0.33);
        color:#666;
        border-radius:6px;
      }
      .jp-or-np .xmg-tishi .xmg-tishi111::after{
            position: absolute;
            top: -6px;
            right: 6px;
            content: '';
            width: 0;
            height: 0;
            border-width: 0px 5px 7px;
            border-style: solid;
            border-color: transparent transparent #fff;
      }
      .jp-or-np .xmg-tishi .xmg-tishi000{
        border-radius:50%;
        width:15px;
        height:15px;
        background:#666;
        text-align: center;
        line-height:15px;
        margin-top:10px;
        color:#fff;
        font-size:10px;
        cursor:pointer;
      }
      i.jpcartjptb222,
      i.jpcartnptb222{
        width:26px;
        height:23px;
        display:inline-block;
        margin-bottom:-8px;
      }
      i.jpcartnptb222{
        background:url(./static/images/jpcartnptb222.png) no-repeat 0 0;
      }
      i.jpcartjptb222{
        background:url(./static/images/jpcartjptb222.png) no-repeat 0 0;
      }
      /* 加减按钮 */
      span.minus,span.plus{
        cursor: pointer;
      }
    </style>
</head>

<body class="lang_en w_1200">

	<%@ include file="/home/template/web-top.jsp" %>
	<%@ include file="/home/template/new-header.jsp" %>
	<a rel="nofollow" href="javascript:;" class="SignInButton FontColor" style="display:none;"></a>
	<div id="main" class="wide">
		<div id="lib_cart">
			<div class="step">
        <div class="step-content active">
          1. <s:text name="Shopping_Cart"/>
        </div>
        <div class="step-content">
          2. <s:text name="Checkout"/>
        </div>
        <div class="step-content">
          3. <s:text name="Complete"/>
        </div>
			</div>
			<div class="cartHeader">
				<a href="/" name="continue_shopping" class="textbtn fl" title="Continue Shopping"><s:text name="Global.Continue_Shopping"/></a>
				<button class="checkoutBtn fr"><s:text name="Proceed_To_Checkout"/></button>
			</div>

			<div class="jp-or-np">
				<div class="fr">
					<dl>
						<dt><s:text name="Global.Filter"/>:</dt>
						<dd><input type="checkbox" class="chooseType" data="1"><b class="jp">JP</b></dd>
						<dd><span>|</span></dd>
						<dd><input type="checkbox" class="chooseType" data="0"><b class="np">NP</b></dd>
						<dd>
							<div class="xmg-tishi">
								<div class="xmg-tishi000">?</div>
								<div class="xmg-tishi111">
									<p>JP:<s:text name="groupCart.Joint_Purchase_Of_Goods"/></p>
									<p>NP:<s:text name="groupCart.General_Goods"/></p>
								</div>
							</div>
						</dd>
					</dl>

				</div>
			</div>

      <!-- 商品列表 之前是table -->
			<form name="shopping_cart">
        <!-- 单个商品列表 -->
        <div class="cart-list">
          <div class="cart-list-shop-item">

            <div class="shop-item-cart-list">
              <!-- 表头 -->
              <div class="cart-list-header flex-center">
				<div class="checkbox w_one tc">
                   <input type="checkbox" onchange="chooseAllShop(this)" id="check_1">
                </div>
                <div class="name w_two"><s:text name="Global.Product"/></div>
                <div class="price w_three tc"><s:text name="Global.Price"/></div>
                <div class="quantity w_four tc"><s:text name="Global.Quantity"/></div>
                <div class="total w_five tc"><s:text name="Global.Total_Price"/></div>
              </div>

              <!-- 仿tbody -->
              <div class="cart-list-content" id="table_body">


              </div>
              <!-- 仿tbody -->
            </div>
          </div>
        </div>
    	</form>

      <!-- 模态框 -->
      <div class="modal"></div>
      <!-- 弹框 - 删除单个全部商品 -->
      <div class="pop-frame pop-frame-delete-product">
        <div class="pop-frame-content">
          <!-- 关闭按钮 -->
          <div class="pop-frame-close">&times;</div>
          <s:text name="groupCart.Remove_Shopping_Cart"/>
        </div>
        <div class="pop-frame-footer">
          <div class="btn-red btn-confirm" onclick="delProduct(this)" id="pdtDelModel"><s:text name="Global.Determine"/></div>
          <div class="btn-red btn-cancel"><s:text name="Global.Cancel"/></div>
        </div>
      </div>
      <!-- 弹框 - 删除单个全部商品 - end -->

      <!-- 弹框 - 删除单个商品的某个属性 -->
      <div class="pop-frame pop-frame-delete-style">
        <div class="pop-frame-content">
          <!-- 关闭按钮 -->
          <div class="pop-frame-close">&times;</div>
          <s:text name="my-favorite.Confirm_Delete"/>
        </div>
        <div class="pop-frame-footer">
          <div class="btn-red btn-confirm" onclick="delSpec(this)" id="specDelModel"><s:text name="Global.Determine"/></div>
          <div class="btn-red btn-cancel"><s:text name="Global.Cancel"/></div>
        </div>
      </div>
      <!-- 弹框 - 删除单个商品的某个属性 - end -->

      <!-- 弹框 - 添加商品的某个属性 -->
      <div class="pop-frame pop-frame-add-style" style="display:none">
        <div class="pop-frame-content flex-start">
          <!-- 关闭按钮 -->
          <div class="pop-frame-close">&times;</div>

          <!-- 类型选择框 -->
          <div class="style-selection-wrap">
            <div class="header-title">
              <s:text name="Global.Colour"/>
            </div>
            <div class="style-selection-content">
              <div class="style-list style-list-left flex-center" id="colorChoose">


              </div>
            </div>
          </div>

          <!-- 颜色选择框 -->
          <div class="style-selection-wrap">
            <div class="header-title">
              <s:text name="Global.Size"/>
            </div>
            <div class="style-selection-content" id="sizeChoose">
            </div>
          </div>
        </div>
        <div class="pop-frame-footer">
          <div class="btn-red btn-confirm" onclick="addSpec(this)" id="addSpec"><s:text name="Global.Determine"/></div>
          <div class="btn-red btn-cancel"><s:text name="Global.Cancel"/></div>
        </div>
      </div>
      <!-- 弹框 - 添加商品的某个属性 - end -->

			<div class="cartFooter">
				<a  href="/" name="continue_shopping" class="textbtn fl" title="Continue Shopping"><s:text name="cart.continue"/></a>
				<a id="remove" class="textbtn fl" title="Remove"><s:text name="Global.Delete"/></a>
				<button class="checkoutBtn fr"><s:text name="Proceed_To_Checkout"/></button>
				<div class="clear"></div>
			</div>
			 <!-- 没有商品时候  start-->
			 <div class="cartBox" style="display:none;margin: 134px 0;">
                <h2><s:text name="Global.Shopping_Cart"/></h2>
                <div class="contents empty">
                    <h3><s:text name="groupCart.Your_Shopping_Cart_Is_Empty"/></h3>
                    <div class="cartDraft">
                    <s:text name="groupCart.Looking_For_Your_Item">
                    	<s:param>
					        <s:property value = "'/home/odr_OdrOrder_orders'"/>
					     </s:param>
                    </s:text>
                        <!-- <a href="/home/odr_OdrOrder_orders">Click here</a> to go to My Orders where you can view and complete your draft order. --></div>
                    <ul>
                        <li>

                            <span class="roundRedDot">•
                            </span><s:text name="groupCart.Right">
                            	<s:param value="'/home/pdt_PdtProduct?cated=373'"/>
                            	<s:param value="'/home/pdt_PdtProduct?cated=380'"/>
                            	<s:param value="'/home/pdt_PdtProduct?cated=387'"/>
                            </s:text>
                            <%-- <c:forEach items="${catList}" var="cat">
                            	<a href="/home/pdt_PdtProduct?cated=${cat.pkey }">${cat.name}</a>
                            </c:forEach> --%>
                           </li>
                        <li>
                            <span class="roundRedDot">•</span> <s:text name="groupCart.Fill_Cart"/></li>
                    </ul>
                    <p>
                        <a href="/" name="continue_shopping" class="continueShoppingBtn" title="Continue Shopping">
                            <b><s:text name="cart.checkout"/></b>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 没有商品时候 end -->
			<div class="clear" style="display:none;"></div>

		</div>
	</div>
	<form action="/home/odr_OdrOrder_toSettlementPage" method="post" id="buynow">
         <input type="hidden" name="jsonCarts" value="" class="carts"/>
         <input type="hidden" name="enterType" value='1' />
         <input type="hidden" name="pid" value="${id}"/>
     </form>
	 <%@ include file="/home/template/new-foot.jsp" %>
	<div id="hj_top" style="opacity: 0;">
		<img src="./static/images/hj_top.png">
	</div>

  <script type="text/javascript">
  $.ajax({
		 url:'/home/usr_UsrCart_getCartData',
		 type:'post',
		 dataType:'json',
		 success:function(data){
			 if(data.suppliers.length == 0){
				 $(".cartFooter").hide();
				 $(".cartHeader").hide();
				 $("form[name=shopping_cart]").hide();
				 $(".cartBox").show();
				 $("#new_foot").css("position","relative");
				 $("#new_foot").css("bottom","0");
				 $(".jp-or-np").hide();
				 return;
			 }
			//渲染店铺
			renderShop(data.suppliers);
			//渲染产品
			renderProduct(data.products);
			//渲染颜色;
			renderColor(data.color);
			//渲染规格
			renderSpec(data.spec);
			renderCount();
			var specLine = $(".cart-list-item-base-info-wrap").next("div[id^=product]").find(".color-goods-item");
 	  	for(var i=0;i<specLine.length;i++){
 	  		var spec ={};
 	  		spec.color = $(specLine[i]).attr("color-data");
 	  		spec.size = $(specLine[i]).attr("size-data");
 	  		specArr.push(spec);
 	  	}

			// 点击下拉or收回
		    $(".cart-list-item-base-info-wrap").delegate(".slide", "click", function(e){
		      // 按钮转向
		      $(this).toggleClass("turn-around");
		       // 内容收拢
		      $(this).closest(".cart-list-item").find(".cart-list-item-detail-info-wrap").stop().slideToggle();
		   })
        // 显示第一个购物车中的详情
        $(".cart-list-shop-item:first-child .shop-item-cart-list:first-child .cart-list-shop-name").next().find(".slide").trigger("click");

		      // 原先轮播
		      // $(".index_boxes.favorites").carousel({itemsPerMove:1,height:30,width:200,duration:200,vertical:!1,step:1});
		      $(".index_boxes.favorites").slide({mainCell:".viewport ul",autoPage:true,effect:"left",vis:4,trigger:"click",effect:"leftLoop"});

		      // 删除单个全部商品时，提示
		      $(".cart-list-item-base-info-wrap").delegate(".close", "click", function(){
		    	  //product
		    	  var product = $(this).attr("pdt-data");
		    	  var type = $(this).attr("type-data");
		    	  $("#pdtDelModel").attr("pdt-data",product);
		    	  $("#pdtDelModel").attr("type-data",type);
		        $(".modal,.pop-frame-delete-product").fadeIn();
		      })
		      // 删除单个商品的某个属性时，提示
		      $(".cart-list-item-detail-info-item .color-goods-item").delegate(".close", "click", function(){
		    	  var cart = $(this).attr("cart-data");
		    	  $("#specDelModel").attr("cart-data",cart);
		        $(".modal,.pop-frame-delete-style").fadeIn();
		      })
		      // 添加商品，提示
		      $(".cart-list-item-base-info-wrap .goods-descript").delegate(".bursting", "click", function(){
		    	  	var pdtData = $(this).attr("pdt-data");
		    	  	$.ajax({
		    	  		url:'/home/usr_UsrCart_getColorAndSize',
		    	  		type:'post',
		    	  		data:{"pdtPkey":pdtData},
		    	  		dataType:'json',
		    	  		success:function(data){
		    	  			renderSpecColor(data.color);
		    	  			renderSize(data.size);
		    	  			$("#colorChoose .style-item:first").click();
		    	  			$("#sizeChoose .style-item:first").click();
		    	  			$("#addSpec").attr("pdt-data",pdtData);
		    	  		}
		    	  	})
		        	$(".modal,.pop-frame-add-style").fadeIn();
		      })
		      // 取消弹框 and 模态框
		      $(".pop-frame .pop-frame-close,.pop-frame .btn-cancel").click(function(){
		    	$("#pdtDelModel").attr("pdt-data","");
		    	$("#specDelModel").attr("cart-data","");
		    	$("#addSpec").attr("pdt-data","");
		    	$("#addSpec").attr("color-data","");
		    	$("#addSpec").attr("size-data","");
		        $(".modal,.pop-frame,.pop-frame-add-style").fadeOut();
		      })

		      // 新添商品时,颜色\尺码选中事件
		      $(".pop-frame-add-style .style-selection-wrap").delegate(".style-item", "click", function(){
		    	  if($(this).hasClass("haveSelected") && $(this).parent().attr("colorid") != undefined){

		    	  }else{
			        $(this).addClass("selected").siblings(".style-item").removeClass("selected");
		    	  }
		      })

		 }
	 })

		  var specArr = [];
		  function renderSize(sizes){
			  var div = '';
			  $.each(sizes,function(i,val){
				  var size = '';
				  $.each(val,function(j,value){
					  size += '<div onclick="chooseThisSize(this);" data="'+value.sizeId+'" '+judgeSelectAbled(value.colorId,value.sizeId)+' class="style-item '+judgeSelectSize(value.colorId,value.sizeId)+'">'+
			           			'<div class="style-name">'+value.size+'</div>'+
			              	  '</div>';
				  })

				  div += '<div class="style-list style-list-right flex-center" style="display:none;" colorId="'+i+'">'+
				  				size +
		                 '</div>';
			  })
			  $("#sizeChoose").html(div);
		  }

		  function chooseThisSize(btn){
			  var size = $(btn).attr("data");
			  $("#addSpec").attr("size-data",size);
		  }

		  function chooseThisColor(color){
			$("div[colorId="+color+"]").show();
			$("div[colorId="+color+"]").siblings().hide();
			$("#addSpec").attr("color-data",color);
		  }

		  function judgeSelectAbled(colorId,sizeId){
			  var flag = false;
			  for(var i=0;i<specArr.length;i++){
				  if(specArr[i].color == colorId){
					if(specArr[i].size == sizeId){
						flag = true;
						break;
					}
				  }
			  }
			  if(flag == true){
				  return "noClick='true'";
			  }else{
				  return "";
			  }
		  }

		  function judgeSelectSize(colorId,sizeId){
			  var flag = false;
			  for(var i=0;i<specArr.length;i++){
				  if(specArr[i].color == colorId){
					if(specArr[i].size == sizeId){
						flag = true;
						break;
					}
				  }
			  }
			  if(flag == true){
				  return "haveSelected";
			  }else{
				  return "";
			  }
		  }

		  function judgeSelectedColor(id){
			  var flag = false;
			  for(var i=0;i<specArr.length;i++){
				if(specArr[i].color == id){
					flag = true;
					break;
				}
			  }
			  if(flag == true){
				  return "haveSelected";
			  }else{
				  return "";
			  }
		  }

		  function getStyle(str){
		  	if(str==''){
		  		return "style='height:28px'";
		  	}else{
		  		return ''
		  	}
		  }

		  function renderSpecColor(colors){
			  var div = '';
			  $.each(colors,function(i,val){
				  div += '<div class="style-item '+judgeSelectedColor(val.id)+'" onclick="chooseThisColor('+val.id+');" color-data="'+val.id+'">'+
			              	'<div class="style-pic" '+getStyle(val.img)+'>';
			              	if(val.img!=""){
		                  	 div +='<img src="${envConfig.imageBaseUrl}'+val.img+'">';
			              	}
			                if(val.img==""){
			                 div += val.color;
			              	}
			              	 div +='</div>';
			              	 if(val.img != ""){
			              	 	div +='<div class="style-name">'+val.color+'</div>';
			              	 }
		              	div += '</div>';
			  });
		      $("#colorChoose").html(div);
		  }

		  //渲染店铺
		  function renderShop(suppliers){
			  var groupDiv = '';
			  $.each(suppliers,function(i,val){
					  groupDiv = '<div class="cart-list-shop-name flex-center shop" id="sup'+val.id+'" style="border-top: 1px dotted #ccc;">'+
							            '<div class="checkbox w_one tc">'+
							      			'<input type="checkbox" id="'+val.id+'" class="check_2" onchange="selectThisShop(this)">'+
							    		'</div>'+
							    		'<span class="shop-name" style="cursor:pointer;" onclick="toThisShop('+val.id+')">'+val.name+'</span>'+
							  		'</div><div id="detail_'+val.id+'"></div>';
					  $("#table_body").after(groupDiv);
			  })

		  }

		  function toThisShop(id){
			  window.location.href = "/home/usr_UsrSupplier_gtSupIndex?pkey="+id;
		  }
		  //选择店铺前的选框
		  function selectThisShop(btn){
			  var shopId = $(btn).attr("id");
			  if($(btn).attr("checked") == "checked"){
				  $(".shop_"+shopId).children(".cart-list-item-base-info-wrap").children(".checkbox").children().attr("checked",true);
			  }else{
				  $(".shop_"+shopId).children(".cart-list-item-base-info-wrap").children(".checkbox").children().attr("checked",false);
				  $("#check_1").attr("checked",false);
			  }
			  var status = 0;
			  $(".check_2").each(function(n,e){
				  if(!$(e).is(":checked")){
					  status = 1;
				  }
			  })
			  if(status == 0){
				  $("#check_1").attr("checked",true);
			  }
		  }

		  function specSelectThisShop(btn){
			  if($(btn).attr("checked") == "checked"){
				  var i = 0;
				  var cli = $(btn).parent().parent().parent();
				  var c = $(cli).attr("class");
				  $(".cart-list-item").each(function (index,e){
					  if($(e).attr("class") == c){
						  var check = $(e).find(".check_3").is(":checked");
						  if(!check){
							  i = -1;
						  }
					  }
					  if((index+1) == $(".cart-list-item").length && i != -1){
						  i = 1;
					  }
				  })
				  if(i == 1){
					  var str = c.split("shop_")[1].split(" ")[0];
					  $("#sup"+str).find("input").attr("checked",true);
				  }
				  var status = 0;
				  $(".check_2").each(function(n,e){
					  if(!$(e).is(":checked")){
						  status = 1;
					  }
				  })
				  if(status == 0){
					  $("#check_1").attr("checked",true);
				  }
			  }else{
				  var c = $(btn).parent().parent().parent().attr("class");
				  var str = c.split("shop_")[1].split(" ")[0];
				  $("#sup"+str).find("input").attr("checked",false);
				  $("#check_1").attr("checked",false);
			  }
		  }

		  function renderProduct(products){
			  var groupSupPdt = '';
			  $.each(products,function(i,val){
					  var img = '';
					  $.each(getPic(val.imgs),function(j,pic){
						  img += '<li>'+
			                          '<span class="photo">'+
			                          	'<a title="'+val.name+'" target="_blank">'+
			                            	'<img src="${envConfig.imageBaseUrl}'+pic+'" alt="'+val.name+'">'+
			                          	'</a>'+
			                          '</span>'+
		                         '</li>';
					  })
					  groupSupPdt = '<div class="cart-list-item shop_'+val.supId+' type_'+val.type+'"><!-- 单个商品 基础信息 -->'+
					                  '<div class="cart-list-item-base-info-wrap flex-center" pdt="'+val.id+'" minOq="'+val.minOq+'">'+
				                    		'<!-- 单个商品 选中状态 -->'+
				                    		'<div class="checkbox w_one tc">'+
				                     			 '<input type="checkbox" value="" class="check_3" onchange="specSelectThisShop(this)" id="proId_'+val.id+'">'+
				                   			'</div>'+
				                    		'<div class="w_two flex-start">'+
				                      			'<!-- 商品图片 -->'+
				                      			'<div class="goods-pic">'+
				                        			'<img src="${envConfig.imageBaseUrl}'+val.img+'">'+
				                      			'</div>'+
				                      			'<!-- 商品描述 -->'+
				                      			'<div class="goods-descript">'+
				                        			'<div class="goods-name" style="cursor:pointer;"><a style="color: #0050a8;" target="_blank" href="'+getPdtHref(val.type,val.id,val.line)+'">'+
				                         				val.name+
				                       				'</a></div>'+
				                        			'<div class="color-or-type">'+
				                          				val.sku+' <i class="'+getIcon(val.type)+'"></i>'+
				                        			'</div>'+
				                    				'<!-- 自带轮播  -->'+
				                        			'<div class="index_boxes index_item favorites horizontal">'+
				                         				'<div class="viewport">'+
				                            				'<ul class="list">'+
				                            					img+
				                            				'</ul>'+
				                          				'</div>';
              groupSupPdt += getPic(val.imgs).length<=3?'':'<a href="javascript:void(0);" hidefocus="true" class="btn btn_l left prev"></a><a href="javascript:void(0);" hidefocus="true" class="btn btn_r right next"></a>';
        			groupSupPdt += '</div>'+
				                        			'<div class="bursting" pdt-data="'+val.id+'"><span class="f30">+</span> <s:text name="Global.Add_To"/></div>'+
				                    				'<!--  -->'+
				                      				'</div>'+
				                   				'</div>'+
				                   			'<div class="goods-price w_three tc">'+
				                   			'${env.currency.symbols}'+ Math.floor(val.price * 100) / 100+
				                    		'</div>'+
				                    		'<!-- 商品数量 -->'+
				                    		'<div class="goods-num w_four tc" id="qty_'+val.id+'">'+
				                    		'</div>'+
				                    		'<div class="total-price w_five tc" id="total_'+val.id+'">'+
				                    		'</div>'+
				                    		'<!-- 操作按钮 -->'+
				                    		'<!-- 单个商品的 整体删除操作 -->'+
				                    		'<div class="close" pdt-data="'+val.id+'">&times;</div>'+
				                    		'<div class="slide"></div>'+
				                 	'</div>'+
				                 	'<div class="cart-list-item-detail-info-wrap" id="product_'+val.id+'" style="display:none;"></div>'+
					                  '<p id="countError'+val.id+'" style="display:none;text-align: right;margin-right: 3em;margin-bottom: 1em;color:red"><s:text name="groupCart.Less_Than_Minimum"/></p></div>';
				      $("#detail_"+val.supId).append(groupSupPdt);
				  }
			  );
		  }


 		  function renderColor(color){
			  var color1 = '';
			  $.each(color,function(i,val){
				  color1 = '<div class="cart-list-item-detail-info-item flex-start">'+
		                      '<!-- 商品颜色图片 -->'+
		                  	  '<div class="goods-pic">'+
		                    		getImg(val.img)+
		                  	  '</div>'+
		                  	'<!-- 同规格 - 商品数量 and 价格信息 -->'+
		                  	  '<div class="goods-spec-wrap">'+
		                    		'<!-- 规格or颜色信息 -->'+
		                    		'<div class="spec-or-color"><s:text name="Global.Colour"/>:'+val.color+'</div>'+
		                    		'<!-- 同颜色下 不同型号的商品 item-list -->'+
		                    		'<div class="color-goods-list" id="spec'+val.proId+'_color'+val.id+'">'+//以下放规格商品

		                    		'</div>'+
		                       '</div>'+
		                  '</div>';
		          $("#product_"+val.proId).append(color1);
			  })
		  }

 		 function getImg(img){
			  if(img != '' && img != undefined && img != null){
         		return '<img src="${envConfig.imageBaseUrl}'+img+'">';
			  }else{
				return '<img src="./static/images/noImage.png">';
			  }
		  }
		  function renderSpec(specs){
			  var spec = '';
			  $.each(specs,function(i,val){
				  var num = val.qty;
				  /* if(num < val.minoq){
					  num = val.minoq;
				  }else if(num > val.maxoq){
					  num = val.maxoq;
				  } */
				  spec = '<div class="color-goods-item flex-center cartPkey" spec-data="'+val.id+'" data='+val.cartId+' size-data="'+val.sizeId+'" color-data="'+val.colorId+'">'+
			                 '<!-- 商品规格 -->'+
			                 '<div class="goods-spec">'+
			                   	'<s:text name="Global.Size"/>: '+ val.size +
			                 '</div>'+
			                 '<!-- 商品价格 -->'+
			                 '<div class="goods-price w_three">'+
			                          '${env.currency.symbols} '+ Math.floor(val.price * 100) / 100 +
			                        '</div>'+
			                        '<!-- 商品数量 -->'+
			                        '<div class="goods-num flex-center w_four">'+
			                          '<span class="minus" name="reduce" onclick="reducenum(this)">-</span> <input type="text" class="qty pdtQty_'+val.productId+'"  onblur="keyup(this)" maxlength="4" value="'+num+'"> <span class="plus" name="add" onclick="addnum(this)">+</span>'+
			                        '</div>'+
			                        '<input type="hidden" id="minOq_'+val.id+'" value="'+val.storeCount+'"/>'+
			                        '<!-- 单个商品总价 -->'+
			                        '<div class="total-price w_five pdtPrice_'+val.productId+'">'+
			                        	'${env.currency.symbols}' + val.qty * Math.floor(val.price * 100) / 100+
			                        '</div>'+
			                        '<!-- 关闭 -->'+
			                        '<div class="close" cart-data="'+val.cartId+'">×</div>'+
			              '</div>';

			      $("#spec"+val.productId+"_color"+val.colorId).append(spec);
			  })

		  }

		  function getIcon(type){
			 if(type == 1){
				 return "jpcartjptb222";
			 }else{
				 return "jpcartnptb222";
			 }

		  }
		  function getPdtHref(type,id,line){
			  if(type == 0){
				  return "/home/pdt_PdtProduct_gtProductsInfo?id="+id;
			  }else{
				  return "/home/prm_PrmGroupPurchase_getGroupPdt?pkey="+line;
			  }
		  }

		  function getPic(str){
			  return str.split(",");
		  }

	  $(".chooseType").on("change",function(){
		  var checks = $(".chooseType:checked");
		  $(".cart-list-item").hide();
		  for(var i=0;i<checks.length;i++){
			  var type = $(checks[i]).attr("data");
			  $(".type_"+type).show();
		  }
		  if(checks.length == 0){
			  $(".cart-list-item").show();
		  }
		  var hiddenCartItems = $(".cart-list-item:hidden");
		  for(var i=0;i<hiddenCartItems.length;i++){
			  $(hiddenCartItems[i]).find("input[type=checkbox]").attr("checked",false);
			  judgeCancelChoose(hiddenCartItems[i])
		  }
		  var shop = $("div[id^=sup]");
		  for(var i=0;i<shop.length;i++){
			  var next = $(shop[i]).next().children(":visible");
			  if(next.length == 0){
				  $(shop[i]).hide();
			  }else{
				  $(shop[i]).show();
			  }
		  }
	  })

	  //传入一个checkbox,判断其父级是否需要取消勾选
	  function judgeCancelChoose(btn){
		  var c = $(btn).attr("class");
		  var str = c.split("shop_")[1].split(" ")[0];
		  $("#sup"+str).find("input").attr("checked",false);
		  $("#check_1").attr("checked",false);
	  }


	  //增加
	  function addnum(btn){
		 /*  var bool = verifyNum($(btn).prev(),1)
			if(bool == 1){
				return;
			} */
	    		var input= $(btn).prev().val();
	    		 var add=parseInt(input)+1;
	    		 if(add > 9999){
	    			 return;
	    		 }
	    		 $(btn).prev(".qty").val(add);
	    		var price=parseInt($(btn).parent().prev().html());
	    		var count=add*price;
	    		$(btn).parent().next().children("span").text(count);
	    		renderCount();
	    		var cartPkey = $(btn).parent().parent().attr("data");
				var qty = $(btn).prev().val();
				updNum(cartPkey,qty);
	  }

	  //直接修改
	  function keyup(btn){
		  /* var bool = verifyNum(btn,0)
			if(bool == 1){
				return;
			} */
	       	var input= $(btn).val();
	        if(/[^\d]/.test(input)){//替换非数字字符
	            var temp_amount=$(btn).val().replace(/[^\d]/g,'');
	            $(btn).val(temp_amount);
	          }
	       	if( input==null||input==""||input==0){
	       		$(btn).val(1);
	       	}
  			var add=parseInt(input);
  			var price=parseInt($(btn).parent().prev().html());
  			var count=add*price;
  			$(btn).parent().next().children("span").text(count);
			renderCount();

			var cartPkey = $(btn).parent().parent().attr("data");
			var qty = $(btn).val();
			updNum(cartPkey,qty);
		};

		//减少
		function reducenum(btn){
			/* var bool = verifyNum($(btn).next(),3)
			if(bool == 1){
				return;
			} */
    	 	var input= $(btn).next().val();
    		if(parseInt(input)>1){
	    		var reducenum=parseInt(input)-1;
	       		$(btn).next(".qty").val(reducenum);
	       		var price=parseInt($(btn).parent().prev().html());
	     		var count=reducenum*price;
	     		$(btn).parent().next().children("span").text(count);
	     		renderCount();
    		}else{
          layer.msg(lang_obj.cart.NotOne, {icon: 2});
    		}
    		var cartPkey = $(btn).parent().parent().attr("data");
			var qty = $(btn).next().val();
			updNum(cartPkey,qty);
    	}


		/* function verifyNum(btn,type){
			var num = $(btn).val();
			var maxnum = $(btn).next().attr("data");
			var minnum = $(btn).prev().attr("data");
			if(parseInt(num,10) > parseInt(maxnum)){
				$(btn).val(maxnum);
				if(type == 0){
					return 2;
				}
				updNum($(btn).parent().parent().attr("data"),maxnum);
				return 1;
			}else if(parseInt(num,10) < parseInt(minnum)){
				$(btn).val(minnum);
				if(type == 0){
					return 2;
				}
				updNum($(btn).parent().parent().attr("data"),minnum);
				return 1;
			}else if(parseInt(num,10) == parseInt(maxnum) && type == 1){
				return 1;
			}else if(parseInt(num,10) == parseInt(maxnum) && type == 3){
				return 2;
			}else if(parseInt(num,10) == parseInt(minnum) && type == 1){
				return 2;
			}else if(parseInt(num,10) == parseInt(minnum) && type == 3){
				return 1;
			}
		} */

		function updNum(pkey,q){
			$.ajax({
				url:'/home/usr_UsrCart_updateQty',
				type:'post',
				data:{"pkey":pkey,"qty":q}
			})
		}

		function renderAllCount(){
			var allGoods = $(".cart-list-item-base-info-wrap");
			for(var i=0;i<allGoods.length;i++){
				var pdt = $(allGoods[i]).attr("pdt");
				var everyPdtQty = $(".pdtQty_"+pdt);
				var everyPdtPrice = $(".pdtPrice_"+pdt);
				var allCount = 0;
				var allPrice = 0;
				for(var j=0;j<everyPdtQty.length;j++){
					allCount += Number($(everyPdtQty[j]).val());
				}
				var minOq = $(allGoods[i]).attr("minOq");
				if(minOq > allCount){
					$("#countError"+pdt).show();
				}else{
					$("#countError"+pdt).hide();

				}
				var symbols = '${env.currency.symbols}';
				for(var j=0;j<everyPdtPrice.length;j++){
					allPrice += Number($(everyPdtPrice[j]).html().substring($(everyPdtPrice[j]).html().indexOf(symbols) + symbols.length).trim());
				}
				$(allGoods[i]).children(".goods-num").html(allCount);
				$(allGoods[i]).children(".total-price").html('${env.currency.symbols}' + allPrice.toFixed(2));
			}


		}

		function renderCount(){
			var goods = $(".color-goods-item");
			for(var i=0;i<goods.length;i++){
				var qty = Number($(goods[i]).children(".goods-num").children(".qty").val());
				var price = Number($(goods[i]).children(".goods-price").html().split(" ")[1]);
				$(goods[i]).children(".total-price").html('${env.currency.symbols}' + (qty * price).toFixed(2));
			}
			renderAllCount();
		}

	function delProduct(btn){
		$.ajax({
			url:'/home/usr_UsrCart_delCartByPdt',
			type:'post',
			data:{"pdtPkeys":$(btn).attr("pdt-data")},
			dataType:'json',
			success:function(data){
				if(data.ret == 1){
					$(".pop-frame .pop-frame-close").click();
					layer.msg(lang_obj.cart.Delete_Success,{icon:1,time:2000},function(){
						window.location.reload();
					})
				}else{
          			layer.msg(data.msg, {icon: 2});
				}
			}
		})
	}

	function delSpec(btn){
		$.ajax({
			url:'/home/usr_UsrCart_delCartBySpec',
			type:'post',
			data:{"pkey":$(btn).attr("cart-data")},
			dataType:'json',
			success:function(data){
				if(data.ret == 1){
					$(".pop-frame .pop-frame-close").click();
					layer.msg(lang_obj.cart.Delete_Success,{icon:1,time:2000},function(){
						window.location.reload();
					})
				}else{
          			layer.msg(data.msg, {icon: 2});
				}
			}
		})
	}

	function addSpec(btn){
		var size = $(btn).attr("size-data");
		var color = $(btn).attr("color-data");
		var pdt = $(btn).attr("pdt-data");
		if(size == "" || color == "" || size == undefined || color == undefined){
      layer.msg(lang_obj.cart.ColorAndSize, {icon: 7});
			return;
		}
		$.ajax({
			url:'/home/usr_UsrCart_addSpecToCart',
			type:'post',
			data:{"sizePkey":size,"colorPkey":color,"pdtPkey":pdt},
			dataType:'json',
			success:function(data){
				if(data.ret == 1){
					$(".pop-frame .pop-frame-close").click();
					layer.msg(lang_obj.cart.cart_tips,{icon:1,time:2000},function(){
						location.reload();
					})
				}else{
          			layer.msg(data.msg, {icon: 2});
				}
			}
		})
	}

	  $(".checkoutBtn").on("click",function(){
		 var checks = $(".cart-list input[id^=proId]:checked");
		 var carts = '';
		 for(var i=0;i<checks.length;i++){
			 var cart = $(checks[i]).parent().parent().siblings("div[id^=product_]").find(".cartPkey");
			 for(var j=0;j<cart.length;j++){
				 if(carts == ''){
					 carts += $(cart[j]).attr("data");
				 }else{
					 carts += "," + $(cart[j]).attr("data");
				 }
			 }
		 }

		 if(carts == "" || carts == undefined){
       layer.msg(lang_obj.cart.checked_error, {icon: 7});
			 return;
		 }

		 var checkPkeys = carts.split(",");
		 var record = [];
		 var patrn = /[^\d]/;
		 var param = {};
		 for(var i = 0 ; i < checkPkeys.length;i++){
			 var spec = $("div[id^=spec]").children("div[data="+checkPkeys[i]+"]");
			 var specId = $(spec).attr("spec-data");
			 var qty = $(spec).children(".goods-num").children(".qty").val();
			 param[specId] = Number(qty);
			 if(qty == "" || patrn.test(qty)){
         		layer.msg(lang_obj.global.correct_message, {icon: 7});
				 return;
			 }
			 var cartPkey = checkPkeys[i];
			 var obj = {};
			 obj.cartPkey = cartPkey;
			 obj.qty = qty;
			 record.push(obj);
		 }
		 $.ajax({
			 url:'/home/usr_UsrCart_cartSubmit',
			 type:'post',
			 data:{"cartPkeys":carts},
			 dataType:'json',
			 success:function(data){
				 console.log(data)
				 if(data.success == true || data.ret == 1){
					 $("#buynow input[name='jsonCarts']").val(JSON.stringify(param));
					 $("#buynow").submit();
					 //window.location.href = "/home/usr_UsrCart_showAdvCart?cartPkeys="+carts;
				 }else if(data.ret == -1){
					 console.log("111111")
					 $(".SignInButton").click();
				 }else{
           			layer.msg(getMessage(data.msg), {icon: 2});
				 }
			 }
		 })
		 //window.location.href = "/home/usr_UsrCart_showAdvCart?cartPkeys="+carts;


	  })

	  function chooseAllShop(btn){
		  if($(btn).attr("checked") == "checked"){
			  $(".shop").children(".checkbox").children().prop("checked",true);
			  $(".shop").children(".checkbox").children().change();
		  }else{
			  $(".shop").children(".checkbox").children().prop("checked",false);
			  $(".shop").children(".checkbox").children().change();
		  }
	  }

	  $("#remove").on("click",function(){
      layer.confirm('<s:text name="Are_You_Sure"/>', {btn: ['<s:text name="confirm"/>', '<s:text name="Global.Cancel"/>', ],icon: 7, title:'tips'}, function(index){
        //do something
        var pdt = $("input[id^=proId_]:checked");
        var pdtPkeys = "";
        for(var i=0;i<pdt.length;i++){
          var pdtPkey = $(pdt[i]).attr("id").split("_")[1];
          if(pdtPkeys == ""){
            pdtPkeys += pdtPkey;
          }else{
            pdtPkeys += "," + pdtPkey;
          }
        }
        if(pdtPkeys == ""){
        	layer.msg(lang_obj.cart.checked_error,{icon:2,time:2000});
        	return;
        }
        $.ajax({
          url:'/home/usr_UsrCart_delCartByPdt',
          type:'post',
          data:{"pdtPkeys":pdtPkeys},
          dataType:'json',
          success:function(data){
            if(data.ret == 1){
              window.location.reload();
            }else{
              layer.msg(lang_obj.cart.FailToRemove, {icon: 2});
            }
          }
        })

        layer.close(index);
      });
	  })

  </script>
</body>

</html>
