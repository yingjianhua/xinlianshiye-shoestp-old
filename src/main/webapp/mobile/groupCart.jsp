<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <script type="text/javascript" src="/home/static/themes/default/mobile/js/cart.js"></script>
        <style>
            .clearfloat:after{display:block;clear:both;content:"";visibility:hidden;height:0}
            .clearfloat{zoom:1}
            .cart_btn{
                position: fixed;
                bottom: 3.4rem;
            }
            .cart_list{
                margin: 0 0.625rem;
                border-top: 1px dotted #ccc;
            }
            .cart_list .cart-item{
                position: relative;
                padding-bottom: 0.825rem;
                padding-top: 0.825rem;
                /* border-bottom: 1px dashed #a9a9a9; */
            }

            /* 单个采购、联合采购check选 */
            .xmg-jpcart-nav222{
                 height:2rem;
                 line-height:2rem;
                 background:#e6e6e6;
            }
             .xmg-jpcart-nav222 dl, .xmg-jpcart-nav222 dt, .xmg-jpcart-nav222 dd{
                 float:left;
            }
             .xmg-jpcart-nav222 dt{
                 font-size:0.5625rem;
                 color:#999;
            }
             .xmg-jpcart-nav222 dd{
                 margin-right:0.6rem;
                 font-size:0.4375rem;
            }
             .xmg-jpcart-nav222 dd b{
                float: left;
                width: 3.6rem;
                height: 1.6rem;
                padding-left: 1.8rem;
                line-height: 1.6rem;
                border-radius: 2rem;
                color: #043d81;
                margin: 0.2rem 0 0 0;
                box-sizing: border-box;
                border: 1px solid #043d81;
            }
             .xmg-jpcart-nav222 dd b.jp{
                 background:url(./static/images/jpcartjptb222.png) no-repeat 0.46rem 0.2rem;
                background-size: 1.2rem 1.1rem;
            }
             .xmg-jpcart-nav222 dd b.np{
                 background:url(./static/images/jpcartnptb222.png) no-repeat 0.46rem 0.2rem;
                background-size: 1.2rem 1.1rem;
            }
             .xmg-jpcart-nav222 dd.on b{
                 color:#fff;
            }
             .xmg-jpcart-nav222 dd.on b.jp{
                background: #043d81 url(./static/images/jpcartjptb.png) no-repeat 0.46rem 0.2rem;
                background-size: 1.2rem 1.1rem;
            }
             .xmg-jpcart-nav222 dd.on b.np{
                background: #043d81 url(./static/images/jpcartnptb.png) no-repeat 0.46rem 0.2rem;
                background-size: 1.2rem 1.1rem;
            }
             .xmg-jpcart-nav222 .xmg-tishi{
                 position:relative;
                 float:left;
                 margin-right:0.625rem;
            }
            .xmg-jpcart-nav222 .xmg-tishi .xmg-tishi000{
                border-radius:50%;
                width:1.2rem;
                height:1.2rem;
                background:#666;
                text-align: center;
                line-height:1.2rem;
                margin-top:0.4375rem;
                color:#fff;
                font-size:0.4375rem;
           }

           .xmg-jpcart-tc-nav000 .xmg-tishibg{
           	 display:none;
               position:fixed;
               top:0;
               left:0;
               z-index:9998;
               width:100%;
               height:100%;
               background:rgba(0,0,0,0.5);
          }
           .xmg-jpcart-tc-nav000 .xmg-tishi111{
               position:fixed;
               right: 0.68rem;
               top: 8.2rem;
               display:none;
               width:13.125rem;
               height:3.2rem;
               padding:0.625rem;
               background:#fff;
               border:1px solid #e5e5e5;
               font-size:0.75rem;
               font-weight:bold;
               line-height:1.6rem;
               box-shadow: 0rem 0rem 0.4375rem 0rem rgba(55, 55, 55, 0.33);
               color:#666;
               border-radius:0.1875rem;
               z-index:9999;
               text-align:center;
          }
           .xmg-jpcart-tc-nav000 .xmg-tishi111:after{
           	position: absolute;
              top: -0.6rem;
              right: 0.6rem;
              content: '';
              width: 0;
              height: 0;
              border-width: 0 0.5rem 0.7rem;
              border-style: solid;
              border-color: transparent transparent #fff;
           }
           .xmg-jpcart-tc-nav000.show .xmg-tishibg,
           .xmg-jpcart-tc-nav000.show .xmg-tishi111{
                display:block;
           }

            /* cart-message-item */
            .cart_list .cart-item .cart-goods-img,.cart_list .cart-message-item .cart-goods-img{
                width: 15%;
            }
            .cart_list .cart-item .cart-checkbox,.cart_list .cart-message-item .cart-checkbox{
                width: 10%;
            }
            .cart_list .cart-item .cart-goods-info,.cart_list .cart-message-item .cart-goods-info{
                width: 60%;
            }
            .cart_list .cart-item .cart-goods-info .bursting{
                display: block;
                /* width: 4.15rem; */
                height: 1.25rem;
                margin-top: 0.5rem;
                background-color: #ffffff;
                border-radius: 1.25rem;
                line-height: 1.25rem;
                text-align: center;
                border: solid 1px #043d81;
                color: #043d81;
            }
            .cart_list .cart-item .cart-goods-info p{
                line-height: 1rem;
                overflow: hidden;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                -ms-text-overflow: ellipsis;
                text-overflow: ellipsis;
                white-space: normal;
                word-wrap:break-word;
                word-break:break-all;
                word-wrap:break-word;
                position: relative;
            }
            .cart_list .cart-item .cart-goods-img img{
                width: 100%;
              /* height: 100%; */
            }
            .cart_list .cart-item .cart-goods-info{
                margin-left: 1rem;
            }
            .cart_list .cart-goods-info .price{
                margin: 0.5rem 0;
                font-size: 1rem;
                /* line-height: 0.825rem; */
                line-height: 1.2rem;
                color: #d60707;
            }
            .cart_list .cart-goods-info .item-total-count{
                width: 3rem;
                margin: 0.5rem 0;
                line-height: 1.2rem;
                color: #999;
                /* padding: 0 0.5rem; */
                text-align: center;
                background-color: #eee;
            }
            .cart_list .cart-item .cart-goods-info .img-wrap{
                /* margin: 1rem 0; */
                margin: 0.5rem 0 0;
                overflow: auto;
            }
            .cart_list .cart-item .cart-goods-info .img-wrap ul{
              display: -webkit-box;
              display: -moz-box;
              display: -ms-flexbox;
              display: -webkit-flex;
              display: flex;
            }
            .cart_list .cart-item .cart-goods-info .img-wrap li{
                flex-shrink: 0;
                width: 2.8125rem;
                height: 1.95rem;
                background-color: #ffffff;
                border: solid 1px #d2d2d2;
                margin:0 0.625rem 0.1rem 0;
            }
            .cart_list .cart-message-item{
                padding: 0.9rem 0;
            }
            .cart_list .cart-message-item .cart-goods-info{
                margin-left: 1rem;
            }
            .cart_list .cart-message-item .cart-goods-info h2{
                color: #043d81;
                margin-bottom: 0.3rem;
            }
            .cart_list .cart-message-item .cart-goods-info ul li input{
                width: 3rem;
                height: 1.25rem;
                background-color: #ecebeb;
            }
            .cart_list .cart-message-item .cart-goods-info ul li .size{
                display: inline-block;
                min-width: 2.6rem;
                height: 1.2rem;
                line-height: 1.2rem;
                padding: 0 0.5rem;
                text-align: center;
                background-color: #ffffff;
                border-radius: 1rem;
                border: solid 1px #d2d2d2;
            }
            .cart_list .cart-message-item .cart-goods-info ul li div{
                position: absolute;
                right: 1.5rem;
                top: 2rem;
                width: 31%;
                font-size: 0;
                display:-webkit-box;
                display: -moz-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
                -webkit-justify-content:center;
                justify-content:center;
                -moz-box-pack:center;
                -webkit--moz-box-pack:center;
                box-pack:center;
                align-items:center;
                -webkit-align-items:center;
                box-align:center;
                -moz-box-align:center;
                -webkit-box-align:center;
            }
            .cart_list .cart-message-item .cart-goods-info ul li{
                position: relative;
                /* display:-webkit-box;
                display: -moz-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
                -webkit-justify-content:space-between;
                justify-content:space-between;
                -moz-box-pack:space-between;
                -webkit--moz-box-pack:space-between;
                box-pack:space-between;
                align-items:center;
                -webkit-align-items:center;
                box-align:center;
                -moz-box-align:center;
                -webkit-box-align:center; */
                padding: 0.3rem 0;
                width: 120%;
                /* white-space: nowrap; */
            }
            .cart_list .cart-message-item .cart-goods-info ul li div span{
                display: inline-block;
                width: 1.25rem;
                height: 1.25rem;
                line-height: 1.25rem;
                text-align: center;
                background-color: #fafafa;
                font-size: 0.75rem;
            }
            .cart_list .cart-item .cart-goods-info ul li img{
                width: 100%;
                height: 100%;
                object-fit: contain;
            }
            .cart_list .cart-item .close{
                position: absolute;
                right: 6px;
                top: 7px;
                font-size: 1.5rem;
                cursor: pointer;
            }
            .cart_list .cart-item .cart-icon-arrow{
                position: absolute;
                right: 6px;
                bottom: 4px;
                display: inline-block;
                width: 12px;
                height: 16px;
                background: url(/home/static/themes/default/mobile/images/double-arrow.png) center center no-repeat;
                transform: rotate(180deg);
            }
            .cart_list .cart-item .cart-icon-arrow.rotate-arrow{
                transform: rotate(360deg);
            }
           .cart-message-list{
                display: none;
           }
           .cart-btn-list{
            display:-webkit-box;
                display: -moz-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
                -webkit-justify-content:space-between;
                justify-content:space-between;
                -moz-box-pack:space-between;
                -webkit--moz-box-pack:space-between;
                box-pack:space-between;
                align-items:center;
                -webkit-align-items:center;
                box-align:center;
                -moz-box-align:center;
                -webkit-box-align:center;
                width: 95%;
                padding: 0 0.625rem;
                height: 3.875rem;
                position: fixed;
                bottom: 6.4rem;
                left: 0;
                z-index: 99;
                background-color: #ffffff;
            }
           .cart-btn-list  span{
                display: inline-block;
                line-height: 1.625rem;
                text-align: center;
                border-radius: 1.625rem;
                border: solid 1px #043d81;
                color: #043d81;
            }
           .cart-btn-list .cart-btn-list-remove{
            /* width: 3.9375rem; */
            height: 1.625rem;
            margin-left: 1.5rem;
            padding: 0 0.8rem;
            }
           .cart-btn-list .cart-btn-list-shopping{
            /* width: 8.2rem; */
            height: 1.625rem;
            padding: 0 0.8rem;
            }
            footer{
                margin-bottom: 6.4rem;
            }
        </style>
        <style>
             .xmg-show{
            display:block!important;
        }
        .xmg-modal{
            display:none;
            width: 100%;
            height: 100%;
            position: fixed;
            left: 0;
            top: 0;
            background-color: rgba(0,0,0,0.5);
            z-index: 9999;
        }
        .xmg-modal .xmg-box{
             position:fixed;
             left:50%;
             top:50%;
             width:16.1875rem;
             height:21.875rem;
             margin:-10.9375rem 0 0 -8.09375rem;
             background:#fff;
             border-radius:0.1875rem;
             overflow:hidden;
        }
        .xmg-modal .xmg-box .xmg-com{
          height:15.8125rem;
          overflow: auto;
          padding:0 0.9375rem;
          }

          .xmg-modal .xmg-box .xmg-close{
          position:absolute;
          top:0.5625rem;
          right:0.5625rem;
          font-size:1.2rem;
          color:#333;
          display:block;
          }
          .xmg-modal .xmg-box h1{
          height:2.6875rem;
          line-height:2.6875rem;
          font-size:0.6875rem;
          font-weight:bold;
          padding-bottom:0.4375rem;
          padding-left: 1.21875rem;
          }

          .color-modal .xmg-box .xmg-com ul li{
          float:left;
          width:3.53125rem;
          margin:0 0.28125rem;
          text-align: center;
          margin-bottom: 0.5625rem ;
          }
          .xmg-modal .xmg-box .xmg-com ul li a{
          position:relative;
          width:3.53125rem;
          height:3.53125rem;
          line-height:3.53125rem;
          text-align: center;
          box-sizing: border-box;
          border:0.03125rem  solid #e5e5e5;
          text-align: center;
          display:block;
          }
          .xmg-modal .xmg-box .xmg-com ul li.red a{
          border:0.0625rem  solid #e23435;
          }

          .size-modal .xmg-box .xmg-com ul li.red b i,
          .xmg-modal .xmg-box .xmg-com ul li.red a i{
          display:block;
          }
          .size-modal .xmg-box .xmg-com ul li b i,
          .xmg-modal .xmg-box .xmg-com ul li a i{
          display:none;
          position: absolute;
          right:0;
          bottom:0;
          width:0.96875rem;
          height:1.21875rem;
          line-height: 1.21875rem;
          }
          .xmg-modal .xmg-box .xmg-com ul li a i img{
          width:100%;
          height:100%;
          display:inline-block;
          }
          .xmg-modal .xmg-box .xmg-com ul li a>img{
          max-width:100%;
          max-height:100%;
          display:inline-block;
          }
          .xmg-modal .xmg-box .xmg-com ul li span{
          height:1.15625rem;
          line-height:1.15625rem;
          font-size:0.625rem;
          color:#757575;
          }
          .xmg-modal .xmg-box .xmg-bottom{
          height:3.375rem;
          background:#dddbdb;
          text-align: center;
          white-space: nowrap;
          }
          .xmg-modal .xmg-box .xmg-bottom .xmg-btn{
          /* width:3.75rem; */
          height:1.46875rem;
          text-align: center;
          line-height:1.46875rem;
          border-radius:0.1875rem;
          margin:0 0.5625rem;
          display:inline-block;
          background:#000;
          font-size:0.6875rem;
          color:#fff;
          margin-top:0.875rem;
          font-weight:bold;
          padding: 0 0.8rem;
          }

          .size-modal .xmg-box{
          width:20.9375rem;
          margin-left:-10.46875rem;
          }
          .size-modal .xmg-box .xmg-com ul li b{
          position: relative;
          float:left;
          height:1.9375rem;
          line-height:1.9375rem;
          box-sizing:border-box;
          padding: 0 0.565rem;
          margin:0 0.28125rem;
          border:0.03125rem  solid #e5e5e5;
          margin-bottom: 0.5625rem ;

          }
          .size-modal .xmg-box .xmg-com ul li.red b{
          border:0.0625rem  solid #e23435;
          }
        </style>
    </head>

    <body>
        <%@ include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div class="crumb clean">
                <a href="/">
                    <span class="icon_crumb_home">
                    </span>
                </a>
                <em>
							<i></i>
						</em>
						<a href="/home/usr_UsrPurchase_userIndex"><!-- My Account --><s:text name="my_account" /></a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/usr_UsrCart_cartshopping">
                    <!-- Shopping Cart -->
                    <s:text name="mobile.shopping_cart" />
                </a>
            </div>

            <%-- 单个采购、联合采购check选  --%>
            <div class="xmg-jpcart-nav222" style="display:none">
              <div class="fr">
                <div class="fr">
                <dl>
                <dt><s:text name="Global.Filter"/>：</dt>
                <dd><b class="jp" data="1">JP</b></dd>
                <dd><b class="np" data="0">NP</b></dd>
                <dd>
                  <div class="xmg-tishi">
                    <div class="xmg-tishi000">?</div>
                  </div>
                </dd>
                </dl>

                </div>
              </div>
            </div>
            
            <div id="cartEmpty" style="text-align: center;margin-top: 244px;display:none;">
			    <a href="/"><s:text name="empty" />&nbsp;&nbsp;<s:text name="Hang_out" /></a>
			</div>

            <%-- 提示选 --%>
            <div class="xmg-jpcart-tc-nav000">
            	<div class="xmg-tishibg"></div>
        			<div class="xmg-tishi111">
        				<p>JP：<s:text name="product.product_type.2"/></p>
        				<p>NP：<s:text name="product.product_type.1"/></p>
        			</div>
            </div>

            <span id="cart" style="display:none">
            <!-- 选择颜色 弹框 -->
            <div class="xmg-modal color-modal">
                <div class="xmg-box">
                    <a class="xmg-close" href="javascript:;">×</a>
                    <h1><!-- Style selection --> <s:text name="plesaeSelect" /></h1>
                    <div class="xmg-com">
                        <ul id="colorList">
                        </ul>
                    </div>
                    <div class="xmg-bottom">
                        <a class="xmg-btn xmg-btn-ok" href="javascript:;"><!-- Sure --><s:text name="Global.Determine" /></a>
                        <a class="xmg-btn xmg-btn-close" href="javascript:;"><!-- Cancel --><s:text name="cart.cancel" /></a>
                    </div>
                </div>
            </div>

            <div class="xmg-modal size-modal">
                <div class="xmg-box">
                    <a class="xmg-close" href="javascript:;">×</a>
                    <h1><!-- Size selection --><s:text name="plesaeSelect" /></h1>
                    <div class="xmg-com" id="sizeList">
                    </div>
                    <div class="xmg-bottom">
                        <a class="xmg-btn" href="javascript:;" id="submitSpec" onclick="chooseThisSpec()"><!-- Sure --><s:text name="mobile.confirm" /></a>
                        <a class="xmg-btn xmg-btn-close" href="javascript:;"><!-- Cancel --><s:text name="cart.cancel" /></a>
                    </div>
                </div>
            </div>
                    <div class="cart-btn-list">
                        <div>

                            <input type="checkbox" class="cart-btn-list-checkbox" onchange="chooseAll(this)">

                            <span id="remove" class="cart-btn-list-remove">
                                <!-- Remove -->
                                <s:text name="user.remove" />
                            </span>
                        </div>
                        <span class="cart-btn-list-shopping">
                            <!-- Continue Shopping -->
                            <s:text name="cart.continue" />
                        </span>
                    </div>
                <div class="cart_btn clean">
                    <div class="cart_info clean ui_border_t">
                        <p class="title">
                            <b>
                            </b>
                            <!-- items -->
                            <s:text name="Global.Double" />
                        </p>
                        <p class="total" style="white-space: nowrap;">
                            <span>
                                ${env.currency.shortName} ${env.currency.symbols}
                            </span>
                        </p>
                    </div>
                    <div class="btn checkout BuyNowBgColor btn_all" data-name="Checkout">
                        <!-- Checkout -->
                        <s:text name="Proceed_To_Checkout" />
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
            <nav>
            </nav>
            <section class="font_col border_col copyright">
                Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1
                浙公网安备 33030402000493号
            </section>
        </footer>
        <form action="/home/odr_OdrOrder_toSettlementPage" method="post" id="buynow">
         <input type="hidden" name="jsonCarts" value="" class="carts"/>
         <input type="hidden" name="enterType" value='1' />
     </form>
        <%@ include file="/mobile/template/foot_menu.jsp" %>

    <script type="text/javascript">
    	window.onload = function(){
    		$.ajax({
    			url:'/home/usr_UsrCart_getCartData',
    			type:'post',
    			dataType:'json',
    			success:function(data){
    				if(data.suppliers <= 0){
    					$(".xmg-jpcart-nav222").hide();
    					$("#cart").hide();
    					$("#cartEmpty").show();
    					return;
    				}
   					$(".xmg-jpcart-nav222").show();
   					$("#cart").show();
    				//渲染商家
    				renderSupplier(data.suppliers)
    				//渲染产品
    				renderProduct(data.products);
    				//渲染颜色
    				renderColor(data.color);
    				//渲染规格
    				renderSpec(data.spec);

            // 计算每个商品的总数量
            computedPerGoodsTotalCound();

    				statistics();
    				 var arrow_this = $('.cart-icon-arrow');
                     $('.cart-icon-arrow').click(function(){
                         $(this).parent().siblings('.cart-message-list').slideToggle(function(){

                         })
                         $(this).toggleClass('rotate-arrow');
                     })
                     $(".cart_list:first-child .cart-item:first-child .cart-icon-arrow").trigger("click");
    				 $('.color-modal .xmg-box .xmg-close,.color-modal .xmg-btn-close').click(function(){
    					 $("#submitSpec").attr("pdt-data","");
    					 $("#submitSpec").attr("type-data","");
    					 $("#submitSpec").attr("group-data","");
                         $('.color-modal').removeClass('xmg-show');
                     })
                     $('.size-modal .xmg-box .xmg-close,.size-modal .xmg-btn-close').click(function(){
                    	 $("#submitSpec").attr("pdt-data","");
                    	 $("#submitSpec").attr("type-data","");
                    	 $("#submitSpec").attr("group-data","");
                         $('.size-modal').removeClass('xmg-show');
                     })
                     $('.bursting').click(function(){
                    	 var pdtId = $(this).attr("pdt-data");
                    	 $.ajax({
                    		 url:'/home/usr_UsrCart_getColorAndSize',
                    		 type:'post',
                    		 data:{"pdtPkey":pdtId},
                    		 dataType:'json',
                    		 success:function(data){
                    			 renderColor2(data.color);
                    			 renderSize(data.size);
                    			 $("#submitSpec").attr("pdt-data",pdtId);

                    		 }
                    	 })
                         $('.color-modal').addClass('xmg-show');
                     })
                     $('.color-modal .xmg-box .xmg-com li').click(function(){
                         $('.color-modal .xmg-box .xmg-com li').removeClass('red')
                         $(this).addClass('red')
                     })
                     $('.size-modal .xmg-box .xmg-com li').click(function(){
                         $('.size-modal .xmg-box .xmg-com li').removeClass('red')
                         $(this).addClass('red')
                     })
                     $('.color-modal .xmg-box .xmg-btn-ok').click(function(){
                         let color = $("#colorList").children(".red").attr("colorId");
                         if( !color ) return;
                         $('.color-modal').removeClass('xmg-show');
                         $('.size-modal').addClass('xmg-show');
                     })

                     $(".add").on("click",function(){
                 		var count = $(this).siblings(".qty").val();
                 		count++;
                 		$(this).siblings(".qty").val(count);
                 		statistics();
                 		$(this).prev().change();
                 	})

                 	$(".reduce").on("click",function(){
                 		var count = $(this).siblings(".qty").val();
                 		count--;
                 		if(count <= 0){
                 			return;
                 		}
                 		$(this).siblings(".qty").val(count);
                 		statistics();
                 		$(this).next().change();
                 	})

    			}
    		})
    	}

    	function keyup(btn){
	 	       	var input= $(btn).val();
	 	        if(/[^\d]/.test(input)){//替换非数字字符
	 	            var temp_amount=$(btn).val().replace(/[^\d]/g,'');
	 	            $(btn).val(temp_amount);
	 	          }
	 	       	if( input==null||input==""){
	 	       		$(btn).val(1);
	 	       	}
	   			var add=parseInt(input);
	   			var price=parseInt($(btn).parent().prev().html());
	   			var count=add*price;
	   			$(btn).parent().next().children("span").text(count);
	   			statistics();

          // item 个数更改时，计算单个商品的总数 -- 也可以调用 computedPerGoodsTotalCound
          // var itemList = $(btn).closest(".cart-message-list").find(".cart-message-item");
          // for( var i=0,sum=0,len = itemList.length; i<len;i++ ){
          //   for(var j=0,subItemList=itemList.eq(i).find(".cart-goods-info .cartPkey");j< subItemList.length;j++ ){
          //     // sum += parseInt( itemList.eq(i).find("input.qty").val().trim() );
          //     sum += parseInt( subItemList.eq(j).find("input.qty").val().trim() );
          //   }
          // }
          // $(btn).closest(".cart_list").find(".item-total-count").text( sum );
          computedPerGoodsTotalCound();


	 			var cartPkey = $(btn).parent().parent().attr("data");
	 			var qty = $(btn).val();

	 			$.ajax({
	 				url:'/home/usr_UsrCart_updateQty',
	 				type:'post',
	 				data:{"pkey":cartPkey,"qty":qty}
	 			})
	 		};

    	function chooseThisSpec(){
    		var color = $("#colorList").children(".red").attr("colorId");
    		var size = $("#sizeList ul").children(".red").attr("sizeId");
    		var group = $("#submitSpec").attr("group-data");
    		if(group == "null"){
    			group = 0;
    		}
    		if(size == "" || color == "" || size == undefined || color == undefined){
          layer.open({
            content: lang_obj.cart.plz_sel_para
            ,skin: 'msg'
            ,time: 2
          });
    			return;
    		}

    		$.ajax({
    			url:'/home/usr_UsrCart_addSpecToCart',
    			type:'post',
    			data:{"sizePkey":size,"colorPkey":color,"type":$("#submitSpec").attr("type-data"),"pdtPkey":$("#submitSpec").attr("pdt-data"),"groupPkey":group},
    			dataType:'json',
    			success:function(data){
    				if(data.ret == 1){
    					$('.size-modal').removeClass('xmg-show');
	    				layer.open({content: lang_obj.goods_info.Added_successfully,skin: 'msg'});
	    				setTimeout(function(){
	    					window.location.reload();
	    				},1500)
    				}else{
			            layer.open({content: data.msg,skin: 'msg',time: 2});
    				}
    			}
    		})
    	}

    	function renderSize(sizes){
    		var div = '';
    		$.each(sizes,function(i,val){
    			div += '<ul style="display:none;" id="color'+i+'">';
    				$.each(val,function(j,value){
    					div += '<li onclick="chooseThisSize(this)" sizeId="'+value.sizeId+'">'+
				                  '<b href="javascript:;">'+
				                     value.size+
				                     '<i><img src="/home/static/themes/default/mobile/images/xmg-gouxuan_03.png" alt="" /></i>'+
				                  '</b>'+
				                '</li>';
    				})
    			div +='</ul>'
    		})
    		$("#sizeList").html(div);
    	}

    	function renderColor2(colors){
    		var div = '';
    		$.each(colors,function(i,val){
    			div += '<li onclick="chooseThisColor(this)" colorId="'+val.id+'">'+//选中时添加class="red"
					        '<a href="javascript:;">'+
					       		'<img src="${envConfig.imageBaseUrl}'+val.img+'" alt="" />'+
					            '<i><img src="/home/static/themes/default/mobile/images/xmg-gouxuan_03.png" alt="" /></i>'+
					        '</a>'+
					        '<span>'+val.color+'</span>'+
					    '</li>'
    		})

    		$("#colorList").html(div);
    	}

    	function chooseThisSize(btn){
    		$(btn).siblings().removeClass("red");
    		$(btn).addClass("red");
    	}

    	function chooseThisColor(btn){
    		$(btn).siblings().removeClass("red");
    		$(btn).addClass("red");
    		var color = $(btn).attr("colorId");
    		$("#color"+color).show();
    	}

    	function renderSupplier(suppliers){
    		var div = '';
    		$.each(suppliers,function(i,val){
    			div += '<p style="padding: 8px 0 8px 11px;font-weight:  bold;" id="sup_'+val.id+'">'+lang_obj.mobile.Shop+val.name+'</p><div id="detail_'+val.id+'"></div>';
    		})
    		$("#cart").prepend(div);
    	}

    	function renderProduct(products){
    		var div = '';
    		$.each(products,function(i,val){
    			 var img = '';
				  $.each(getPic(val.imgs),function(j,pic){
					  img += '<li>'+
		          				'<img src="${envConfig.imageBaseUrl}'+pic+'" alt="">'+
		        			'</li>';
				  })
    			div = '<!-- 商品容器 -->'+
                    	'<div class="cart_list type_'+val.type+'" >'+
                		'<!-- 商品基本信息 -->'+
                			'<div class="cart-item clearfloat">'+
                    			'<div class="fl cart-checkbox">'+
                        			'<input type="checkbox" onchange="selectThisPdt(this)" pdt-data="'+val.id+'" id="proId_'+val.id+'">'+
                    			'</div>'+
                    			'<div class="fl cart-goods-img">'+
                        			'<img src="${envConfig.imageBaseUrl}'+val.img+'" alt="">'+
                   			 	'</div>'+
                    			'<div class="fl cart-goods-info">'+
                        			'<p><a href="/home/pdt_PdtProduct_gtProductsInfo?id='+val.id+'">'+val.name+'</a></p>'+
                       				'<div class="img-wrap">'+
                         				'<ul class="img-flex-wrap">'+
                              			img+
                          			'</ul>'+
                        			'</div>'+
		                        '<div class="clearfloat">'+
                                '<p class="bursting" pdt-data="'+val.id+'"><s:text name="cart.spec"/></p>'+
		                            '<span class="fl price">${env.currency.symbols}'+Math.floor(val.price * 100) / 100+'</span>'+

		                            '<div class="fr item-total-count">Count66</div>'+
		                        '</div>'+
                    		'</div>'+
		                    '<div class="close" onclick="delProduct('+val.id+')">'+
		                        '&times;'+
		                   ' </div>'+
		                    '<span class="cart-icon-arrow rotate-arrow"></span>'+
		                '</div>'+
		                '<!-- 商品颜色and尺码 -->'+
		                '<div class="cart-message-list" id="product'+val.id+'">'+
		                '</div>';
				  $("#detail_"+val.supId).append(div);
    		})
    	}

    	function selectThisPdt(btn){
    		var pdtId = $(btn).attr("pdt-data");
    		var specs = $(".pdt_"+pdtId);
    		if($(btn).prop("checked") == true){
	    		for(var i=0;i<specs.length;i++){
	    			$(specs[i]).prop("checked",true);
	    		}
    		}else{
    			for(var i=0;i<specs.length;i++){
	    			$(specs[i]).prop("checked",false);
	    		}
    			 $(".cart-btn-list-checkbox").attr("checked",false);
    		}
    		statistics();
    	}

    	function chooseAll(btn){
   		 var checkboxs = $("input[type=checkbox]");
   		 if($(btn).prop("checked") == true){
   			 for(var i=0;i<checkboxs.length;i++){
   				 $(checkboxs[i]).prop("checked",true);
   			 }
   		 }else{
   			for(var i=0;i<checkboxs.length;i++){
  				 $(checkboxs[i]).prop("checked",false);
  			 }
   		 }
   		statistics();
   	 }

    	function renderColor(colors){
    		var div = '';
    		$.each(colors,function(i,val){
    			div = ' <div class="cart-message-item clearfloat">'+
				            '<div class="cart-checkbox fl">'+
				                '<input class="pdt_'+val.proId+'" type="checkbox" onchange="selectThisSpec(this);" id="proId'+val.proId+'">'+
				            '</div>'+
				            '<div class="cart-goods-img fl">'+
				               '<img src="${envConfig.imageBaseUrl}'+val.img+'" alt="">'+
				            '</div>'+
				            '<div class="cart-goods-info fl">'+
				                '<h2>Color : <span>'+val.color+'</span></h2>'+
				                '<ul id="pdt'+val.proId+'_color'+val.id+'"></ul>'
				            '</div>'+
				        '</div>';

				 $("#product"+val.proId).append(div);
    		})
    	}

    	function renderSpec(specs){
    		var div = '';
    		$.each(specs,function(i,val){
    			div =  '<li spec-data="'+val.id+'" data="'+val.cartId+'" class="cartPkey">'+
			               '<p class="size">'+val.size+'</p>'+
			               '<p class="price">${env.currency.symbols}'+Math.floor(val.price * 100) / 100+'</p>'+
			               '<div class="goods-num">'+
			                  '<span class="reduce">-</span>'+
			                  '<input type="text" class="qty" value="'+val.qty+'" onchange="keyup(this)" style="font: menu;text-align:center;">'+
			                  '<span class="add">+</span>'+
			               '</div>'+
			            '</li>';
			    $("#pdt"+val.productId+"_color"+val.colorId).append(div);
    		})
    	}

      // 计算每个商品的总数量
      function computedPerGoodsTotalCound(){
        var $_cart_list = $(".cart_list");
        for( var i=0; i<$_cart_list.length;i++  ){
          var sum = 0;
          var goods_color_item = $_cart_list.eq(i).find(".cart-message-item");
          for( var j=0,goods_color_len=goods_color_item.length;j<goods_color_len;j++  ){
            var goods_spec_item = goods_color_item.eq(j).find(".cartPkey");
            for( var k =0, goods_spec_len=goods_spec_item.length;k<goods_spec_len;k++ ){
              sum += parseInt( goods_spec_item.eq(k).find("input.qty").val().trim() );
            }
          }
          $_cart_list.eq(i).find(".item-total-count").text( sum );
        }
      }

    	$(function(){

    	})
    	 function getPic(str){
			  return str.split(",");
		  }

    	 function delProduct(btn){
         layer.open({
          content: '<s:text name="del_ask"/>'
          ,btn: ['<s:text name="Global.Yes"/>', '<s:text name="Global.No"/>']
          ,yes: function(index){
            $.ajax({
      				url:'/home/usr_UsrCart_delCartByPdt',
      				type:'post',
      				data:{"pdtPkeys":btn},
      				dataType:'json',
      				success:function(data){
      					// $('html').tips_box('Delete success', 'success');
                layer.open({content: "<s:text name='Successfully_Deleted'/>",skin: 'msg'});
      					setTimeout(function () {location.reload();},2000);
      				}
      			})
            layer.close(index);
          }
        });
  		 }
    	 
    	 function selectThisSpec(btn){
    		 if($(btn).attr("checked") == true){
    			 statistics();
    		 }else{
    			 $(".cart-btn-list-checkbox").attr("checked",false);
    			 statistics();
    		 }
    	 }

    	 function statistics(){
    		 var items = 0;
    		 var totalPrice = 0;
    		 var selected = $("input[id^=proId]:checked");
    		 for(var i=0;i<selected.length;i++){
    			 var spec = $(selected[i]).parent().siblings(".cart-goods-info").children("ul[id^=pdt]").children("li");
    			 //items += spec.length;
    			 for(var j=0;j<spec.length;j++){
   				 	var price = Number($(spec[j]).children(".price").text().substring(1));
   				 	var qty = Number($(spec[j]).children(".goods-num").children(".qty").val());
   				 	items += qty;
   				 	totalPrice += price * qty;
    			 }
    		 }

    		 $(".title b").html(items);
    		 var div = '<span>${env.currency.shortName} ${env.currency.symbols}</span>';
    		 $(".total").html(div + totalPrice.toFixed(2));

    	 }



    	 $(".checkout").on("click",function(){
    		 var checks = $(".cart-message-item .cart-checkbox input[id^=proId]:checked");
    		 var carts = '';
    		 for(var i=0;i<checks.length;i++){
    			 var cart = $(checks[i]).parent().siblings(".cart-goods-info").find(".cartPkey");
    			 for(var j=0;j<cart.length;j++){
    				 if(carts == ''){
    					 carts += $(cart[j]).attr("data");
    				 }else{
    					 carts += "," + $(cart[j]).attr("data");
    				 }
    			 }
    		 }

    		 if(carts == "" || carts == undefined){
    			 $('html').tips_box(lang_obj.cart.checked_error, 'error');
    			 return;
    		 }
    		 var checkPkeys = carts.split(",");
    		 var record = [];
    		 var patrn = /[^\d]/;
    		 var param = {};
    		 for(var i = 0 ; i < checkPkeys.length;i++){
    			 var spec = $("ul[id^=pdt]").children("li[data="+checkPkeys[i]+"]");
    			 var specId = $(spec).attr("spec-data");
    			 var qty = $(spec).children(".goods-num").children(".qty").val();
    			 param[specId]= Number(qty);
    			 if(qty == "" || patrn.test(qty)){
    				 $('html').tips_box(lang_obj.global.correct_message, 'error');
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
    				 if(data.success == true){
    					 $("#buynow input[name='jsonCarts']").val(JSON.stringify(param));
    					 $("#buynow").submit();
			    		// window.location.href = "/home/usr_UsrCart_showAdvCart?cartPkeys="+carts;
    				 }else{
    					 $('html').tips_box(getMessage(data.msg), 'error');
    				 }
    			 }
    		 })
    	  })

    	 $("#remove").on("click",function(){
         var pdt = $("input[id^=proId_]:checked");
         var spec = $("input[class^=pdt]:checked");
         if( pdt.length == 0 && spec.length == 0) {
        	 $('html').tips_box(lang_obj.cart.checked_error, 'error');
        	 return;
         }
         layer.open({
          content: '<s:text name="del_ask" />'
          ,btn: ['<s:text name="Global.Yes" />', '<s:text name="Global.No" />']
          ,yes: function(index){
            var pdtPkeys = "";
           for(var i=0;i<pdt.length;i++){
             var pdtPkey = $(pdt[i]).attr("id").split("_")[1];
             if(pdtPkeys == ""){
               pdtPkeys += pdtPkey;
             }else{
               pdtPkeys += "," + pdtPkey;
             }
           }
          
           var specPkeys = "";
           for(var i=0;i<spec.length;i++){
             var specs = $(spec[i]).parent().siblings(".cart-goods-info").find("li");
             for(var j=0;j<specs.length;j++){
               var specPkey = $(specs[j]).attr("data");
               if(specPkeys == ""){
                 specPkeys += specPkey;
               }else{
                 specPkeys += "," + specPkey;
               }
             }
           }
           
           if(specPkeys == "" && pdtPkeys){
        	   $('html').tips_box(lang_obj.cart.checked_error, 'error');
        	   return;
           }
           
            $.ajax({
             url:'/home/usr_UsrCart_delCartByPdt',
             type:'post',
             data:{"pdtPkeys":pdtPkeys,"cartPkeys":specPkeys},
             dataType:'json',
             success:function(data){
               if(data.ret == 1){
                 window.location.reload();
               }else{
                 $('html').tips_box(getMessage(data.msg), 'error');
               }
             }
           })
            layer.close(index);
          }
        });
	  })

    // 联合采购check点击事件
	  $(".cart-btn-list-shopping").on("click",function(){
		  window.location.href = "/";
	  })
    $('.xmg-jpcart-nav222 .xmg-tishi').click(function(){
      $('.xmg-jpcart-tc-nav000').addClass('show');
    })
    $('.xmg-jpcart-tc-nav000 .xmg-tishibg').click(function(){
      $('.xmg-jpcart-tc-nav000').removeClass('show');
    })
    $('.xmg-jpcart-nav222 dd b').click(function(){
	      $(this).parent().toggleClass('on');
	      var on = $("dl .on");
	      if(on.length != 0 && on.length != 2){
		      var p = $("p[id^=sup]");
	    	  for(var i=0;i<on.length;i++){
		    	  var type = $(on[i]).children("b").attr("data");
		    	 	  $(".cart-btn-list-checkbox").attr("checked",false);
		    		  $(".cart_list").hide();
			    	  $(".type_"+type).show();
		      }
	    	  var hiddenCartItems = $(".cart_list:hidden");
	    	  for(var i=0;i<hiddenCartItems.length;i++){
				  $(hiddenCartItems[i]).find("input[type=checkbox]").attr("checked",false);
			  }
	    	  for(var i=0;i<p.length;i++){
	    		  var next = $(p[i]).next().children(":visible");
	    		  if(next.length == 0){
					  $(p[i]).hide();
				  }else{
					  $(p[i]).show();
				  }
	    	  }
	      }else{
	    	  $(".cart_list").show();
	    	  $("p[id^=sup]").show();
	      }
    })
    </script>
    </body>
</html>
