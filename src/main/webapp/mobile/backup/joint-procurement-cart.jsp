<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <script type="text/javascript" src="/mobile/static/js/cart.js"></script>
        <style>
            .clearfloat:after{display:block;clear:both;content:"";visibility:hidden;height:0}
            .clearfloat{zoom:1}
            .cart_btn{
                position: fixed;
                bottom: 3.4rem;
            }
            .cart_list{
                margin: 0 0.625rem;
            }
            .cart_list .cart-item{
                position: relative;
                padding-bottom: 0.825rem;
                padding-top: 0.825rem;
                border-bottom: 1px dashed #a9a9a9;
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
                width: 4.15rem;
                height: 1.25rem;
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
                font-size: 1rem;
                line-height: 0.825rem;
                color: #d60707;
            }
            .cart_list .cart-item .cart-goods-info ul{
                margin: 1rem 0;
            }
            .cart_list .cart-item .cart-goods-info ul li{
                float: left;
                width: 2.8125rem;
                height: 1.95rem;
                background-color: #ffffff;
                border: solid 1px #d2d2d2;
                margin:0 0.625rem 0.625rem 0;
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
                width: 1.625rem;
                height: 1.25rem;
                background-color: #ecebeb;
            }
            .cart_list .cart-message-item .cart-goods-info ul li .size{
                width: 2.6rem;
                height: 1rem;
                line-height: 1rem;
                text-align: center;
                background-color: #ffffff;
                border-radius: 1rem;
                border: solid 1px #d2d2d2;
            }
            .cart_list .cart-message-item .cart-goods-info ul li div{
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
                padding: 0.3rem 0;
            }
            .cart_list .cart-message-item .cart-goods-info ul li div span{
                display: inline-block;

                height: 1.25rem;
                line-height: 1.25rem;
                text-align: center;
                background-color: #fafafa;
                font-size: 0.75rem;
                overflow:hidden;
            }
            .cart_list .cart-item .cart-goods-info ul li img{
                width: 100%;
              height: 100%;
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
                background: url(/mobile/static/images/double-arrow.png) center center no-repeat;
            }
            .rotate-arrow{
                transform: rotate(180deg);
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
            width: 3.9375rem;
            height: 1.625rem;
            margin-left: 1.5rem;
            }
           .cart-btn-list .cart-btn-list-shopping{
            width: 8.2rem;
            height: 1.625rem;
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
               width:14.1875rem;
               height:21.875rem;
               margin:-10.9375rem 0 0 -7.09375rem;
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
          /*  border:0.0625rem  solid #e23435; */
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
          }
          .xmg-modal .xmg-box .xmg-bottom .xmg-btn{
          width:3.75rem;
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
          /* border:0.0625rem  solid #e23435; */
          }
        </style>
    </head>

    <body>
		<%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div class="crumb clean">
                <a href="/">
                    <span class="icon_crumb_home">
                    </span>
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/usr_UsrCart_cartshopping">
                    Hungary purchase center
                </a>
            </div>

            <span id="cart">
                <!-- 商品容器 -->
                <c:forEach items="${MapData.productList }" var="datalist" varStatus="status">
                		<c:if test="${status.index != 0 }">
                			<c:set var="newProductId" scope="session" value="${datalist.pdtProduct.pkey }"/>
                		</c:if>
	                	<c:if test="${newProductId != ProductId ||status.index == 0}">
	                	<div class="cart_list">
		                    <!-- 商品基本信息 -->
		                    <div class="cart-item clearfloat">
		                        <div class="fl cart-checkbox">
		                            <input type="checkbox" class="check-p"  data="${datalist.pdtProduct.pkey }">
		                        </div>
		                        <div class="fl cart-goods-img">
		                            <img src="/mobile/static/images/3db2607a62.jpg.240x240.jpg" alt="">
		                        </div>
		                        <div class="fl cart-goods-info">
		                            <p><a href="#" id="title-name">
		                                   ${datalist.pdtProduct.name }
		                            </a></p>
		                            <ul class="clearfloat">
		                            	<c:forEach items="${datalist.specList }" var="specPrice">
		                            	 <li class="img-title" data="${specPrice.color }">
			                                <img src="/mobile/static/images/3db2607a62.jpg.240x240.jpg" alt="" >
			                             </li>
		                            	</c:forEach>
		                            </ul>
		                            <div class="clearfloat">
		                                <span class="fl price">$10.00</span>
		                                <span class="fr bursting" data="${datalist.specList[0].pkey }"><s:text name="cart.spec"/></span>
		                            </div>
		                        </div>
		                        <div class="close close-p"  data="${datalist.pdtProduct.pkey }">
		                            x
		                        </div>
		                        <span class="cart-icon-arrow rotate-arrow"></span>
		                    </div>
		                    <!-- 商品颜色and尺码 -->
		                    <div class="cart-message-list">
		                    	<c:forEach items="${datalist.specList }" var="speclist" varStatus="datalistStatus">

		                    		<c:if test="${datalistStatus.index != 0 }">
		                    			<c:set var="newcolorId" scope="session" value="${speclist.color }"/>
		                    		</c:if>
		                    		<c:if test="${datalistStatus.index == 0 }">
		                    			<c:set var="colorId" scope="session" value="${speclist.color }"/>
		                    		</c:if>
		                    		<c:if test="${newcolorId != colorId || datalistStatus.index == 0}">
			                            <div class="cart-message-item clearfloat">
			                                    <div class="cart-checkbox fl">
			                                        <input type="checkbox" class="check-o" >
			                                    </div>
			                                    <div class="cart-goods-img fl">
			                                        <img src="/mobile/static/images/3db2607a62.jpg.240x240.jpg" alt="">
			                                    </div>
			                                    <div class="cart-goods-info fl">
			                                        <h2>Color :
			                                        	<span>
															<c:forEach items="${datalist.colorList }" var="colorlist">
																<c:if test="${datalistStatus.index == 0 && datalist.pdtProduct.pkey == speclist.product}">
																	<c:if test="${speclist.color == colorlist.pkey }">
																		${colorlist.name }
																		<input type="hidden" value="${speclist.color}" class="color-title">
																	</c:if>
																</c:if>
																<c:if test="${datalistStatus.index != 0 && datalist.pdtProduct.pkey == speclist.product}">
																	<c:if test="${speclist.color == colorlist.pkey }">
																		${colorlist.name }
																		<input type="hidden" value="${speclist.color}" class="color-title">
																	</c:if>
																</c:if>
															</c:forEach>
														</span>
			                                        </h2>
			                                        <ul>
			                                        	<c:forEach items="${datalist.specList }" var="newspeclist" varStatus="newdatalistStatus">
			                                        		<c:if test="${colorId == newspeclist.color && datalistStatus.index == 0}">
			                                        		   <li>
						                                          <span class="size">
																	 <c:forEach items="${datalist.sizeList }" var="sizelist">
																	 	<c:if test="${newspeclist.size == sizelist.pkey }">
																			${sizelist.name }
																		</c:if>
																	 </c:forEach>
																   </span>
						                                           <span class="price">${newspeclist.price }</span>
						                                           <div class="product-number">
						                                              <span style="font-size: 33px;margin-right: 12%" class="reduceNum" data="${newspeclist.pkey }" data1="${datalist.pdtProduct.pkey }">-</span>
						                                              <c:forEach items="${MapData.listCart }" var="listcart">
						                                              	<c:if test="${listcart.purchase == 2 && listcart.spec == newspeclist.pkey }">
						                                              		<span style="overflow:hidden;">
							                                              		<input maxlength="5"
							                                              		type="text"  style="text-align:center;width: 50px" value="${listcart.qty }" class="number-spec">
						                                              		</span>
						                                              	</c:if>
						                                              </c:forEach>
						                                              <span style="font-size: 20px;margin-left: 3%" class="increaseNum" data="${newspeclist.pkey }" data1="${datalist.pdtProduct.pkey }">+</span>
						                                            </div>
				                                            	</li>
			                                        		</c:if>
			                                        		<c:if test="${newcolorId == newspeclist.color &&  datalistStatus.index != 0}">
			                                        			<li>
						                                          <span class="size">
																	 <c:forEach items="${datalist.sizeList }" var="sizelist">
																	 	<c:if test="${newspeclist.size == sizelist.pkey }">
																			${sizelist.name }
																		</c:if>
																	 </c:forEach>
																   </span>
						                                           <span class="price">${newspeclist.price }</span>
						                                           <div class="product-number">
						                                              <span style="font-size: 33px;margin-right: 12%" class="reduceNum" data="${newspeclist.pkey }" data1="${datalist.pdtProduct.pkey }">-</span>
						                                              <c:forEach items="${MapData.listCart }" var="listcart">
						                                              	<c:if test="${listcart.purchase == 2 && listcart.spec == newspeclist.pkey }">
						                                              		<span style="overflow:hidden;">
							                                              		<input maxlength="5"
							                                              		type="text"  style="text-align:center;width: 50px" value="${listcart.qty }" class="number-spec">
						                                              		</span>
						                                              	</c:if>
						                                              </c:forEach>
						                                              <span style="font-size: 20px;margin-left: 3%" class="increaseNum" data="${newspeclist.pkey }" data1="${datalist.pdtProduct.pkey }">+</span>
						                                            </div>
				                                            	</li>
			                                        		</c:if>
			                                        	</c:forEach>
			                                        </ul>
			                                    </div>
			                                </div>
		                                </c:if>
		                                <c:set var="colorId" scope="session" value="${speclist.color }"/>
		                           </c:forEach>

			                    </div>
			                </div>

		            	</c:if>
		       			<c:set var="ProductId" scope="session" value="${datalist.pdtProduct.pkey }"/>
               </c:forEach>


<div id="Pcolor-item" style="display:none">
	 <li class="red">
         <a href="javascript:;">
             <img class="product-img" src="/mobile/static/images/3db2607a62.jpg.240x240.jpg" alt="" />
             <i style="display:inline" data="" data1="" status=""><img src="/mobile/static/images/xmg-gouxuan_03.png" alt="" /></i>
          </a>
          <span>Blue</span>
     </li>
</div>

            <!-- 选择颜色 弹框 -->
            <div class="xmg-modal color-modal">
                <div class="xmg-box">
                    <a class="xmg-close" href="javascript:;">×</a>
                    <h1>Style selection</h1>
                    <div class="xmg-com">
                         <ul id="Pcolor-gather">
                         <!--  <li class="red">
                                <a href="javascript:;">

                                        <img src="/mobile/static/images/3db2607a62.jpg.240x240.jpg" alt="" />

                                    <i style="display:none"><img src="/mobile/static/images/xmg-gouxuan_03.png" alt="" /></i>
                                </a>
                                <span>Blue</span>
                            </li> -->

                        </ul>
                    </div>
                    <div class="xmg-bottom">
                        <a class="xmg-btn xmg-btn-ok" href="javascript:;">Sure</a>
                        <a class="xmg-btn xmg-btn-close" href="javascript:;">Cancel</a>
                    </div>
                </div>
            </div>


<div id="Psize-item" style="display:none">
	<li class="red">
       <b href="javascript:;">
         <span></span>
         <i style="display:inline" data="" data1=""  status=""><img src="/mobile/static/images/xmg-gouxuan_03.png" alt="" /></i>
       </b>
     </li>
</div>
            <div class="xmg-modal size-modal">
                <div class="xmg-box">
                    <a class="xmg-close" href="javascript:;">×</a>
                    <h1>Size selection</h1>
                    <div class="xmg-com">
                        <ul id="Psize-gather">
                           <!--  <li class="red">
                                <b href="javascript:;">

                                    28EU-35EU

                                    <i><img src="/mobile/static/images/xmg-gouxuan_03.png" alt="" /></i>
                                </b>
                            </li>  -->
                        </ul>
                    </div>
                    <div class="xmg-bottom">
                        <a class="xmg-btn" href="javascript:;" id="sub">Sure</a>
                        <a class="xmg-btn xmg-btn-close" href="javascript:;">Cancel</a>
                    </div>
                </div>
            </div>


                    <div class="cart-btn-list">
                        <div>

                                    <input type="checkbox" id="closeAll" class="cart-btn-list-checkbox">

                            <span class="cart-btn-list-remove">
                                Remove
                            </span>
                        </div>
                        <span  class="cart-btn-list-shopping">
                            Continue Shopping
                        </span>
                    </div>
                <div class="cart_btn clean">
                    <div class="cart_info clean ui_border_t">
                        <p class="title" id="totalNumber">
                            <b id="items">

                            </b>
                            items
                        </p>
                        <p class="total">
                            <span>
                                USD $
                            </span>
                            <span id="totalprice"> </span>
                        </p>
                    </div>
                    <div class="btn checkout BuyNowBgColor btn_all" data-name="Checkout" id="submit">
                        Checkout
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
                            Sign In
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
		<%@ include file="/mobile/template/foot_menu.jsp" %>
    <!-- 点击箭头 展开 闭合 颜色 尺码 -->
        <script>
            var arrow_this = $('.cart-icon-arrow');
            $('.cart-icon-arrow').bind('click',function(){
    $(this).parent().siblings('.cart-message-list').slideToggle(function(){
                })
                $(this).toggleClass('rotate-arrow');
            })
        </script>
         <script type="text/javascript">
         var colorpkeyList = new Array;
         var colornameList = new Array;
         var sizepkeyList = new Array;
         var sizenameList = new Array;
         <c:forEach items="${MapData.pdtColorAll}" var="pdtcolorall" varStatus="pcolorstatus">
          //colorpkeyList ["${pcolorstatus.index}"] = "${pdtcolorall.pkey}";
          colornameList ["${pdtcolorall.pkey}"] = "${pdtcolorall.name}";
 </c:forEach>
 <c:forEach items="${MapData.pdtSizeAll}" var="pdtsizeall" varStatus="psizestatus">
  //sizepkeyList ["${psizestatus.index}"] = "${pdtsizeall.pkey}";
  sizenameList ["${pdtsizeall.pkey}"] = "${pdtsizeall.name}";
 </c:forEach>
            $('.color-modal .xmg-box .xmg-close,.color-modal .xmg-btn-close').click(function(){
                $('.color-modal').removeClass('xmg-show');
            })
            $('.size-modal .xmg-box .xmg-close,.size-modal .xmg-btn-close').click(function(){
                $('.size-modal').removeClass('xmg-show');
            })
            $('.bursting').click(function(){
              $("#Pcolor-gather").text("");
              var singleSpecNum = $(this).attr("data");
              //var pcoloritem = $("#Pcolor-item").html();

            //	$("#Pcolor-gather").append(pcoloritem);
            //	$("#Pcolor-gather").append(pcoloritem);
              $.post("/home/usr_UsrCart_getSpecColor",{singleSpecNum:singleSpecNum},function (data){
                var da = JSON.parse(data);
                $(da.specColor).each(function (index,element){
                if(index==0){
                  $("#Pcolor-item").find("i").css("display","inline");
                  $("#Pcolor-item").find("i").attr("status","1");
                }else{
                  $("#Pcolor-item").find("i").css("display","none");
                }
                var pcolor = colornameList[element.color];
                /* for(var i=0;i<colornameList.length;i++){
                        if(element.peky == i){
                          pcolor = colornameList[i];
                        }
                      } */
                /*var psize = "";
                for(var i=0;i<sizenameList.length;i++){
                        if(element.size == i){
                          psize = sizenameList[i];
                        }
                      }  */
                      $("#Pcolor-item").find("i").attr("data",element.pkey);
                      $("#Pcolor-item").find("i").attr("data1",element.color);
              $("#Pcolor-item").find(".product-img").attr("src",element.pics);
              $("#Pcolor-item").find("li span").text(pcolor);
              var pcoloritem = $("#Pcolor-item").html();
              $("#Pcolor-gather").append(pcoloritem);
              })

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
                $('.color-modal').removeClass('xmg-show');
                $('.size-modal').addClass('xmg-show');
            })
            $(".xmg-btn-ok").click(function (){
              $("#Psize-gather").text("");
               var data = $("#Pcolor-gather").find("i[status='1']").attr("data");
               var data1 = $("#Pcolor-gather").find("i[status='1']").attr("data1");
               console.log(data);
               console.log(data1);
               $.post("/home/usr_UsrCart_getSpecSize",{singleSpecNum:data},function (data){
                 var da = JSON.parse(data);
                 $(da.specSize).each(function (index,element){
                  if(index==0){
                  $("#Psize-item").find("i").css("display","inline");
                  $("#Psize-item").find("i").attr("status","1");
                }else{
                  $("#Psize-item").find("i").css("display","none");
                }
                  console.log(sizenameList[element.size])
                  var psize = sizenameList[element.size];
                  //console.log(element.size)
                /* for(var i=0;i<sizenameList.length;i++){
                        if(element.size == i){
                          psize = sizenameList[i];
                        }
                      } */
                      $("#Psize-item").find("i").attr("data",element.pkey);
                      $("#Psize-item").find("i").attr("data1",da.sup.pkey);
                $("#Psize-item").find("span").text(psize);
                var psizeitem = $("#Psize-item").html();
                $("#Psize-gather").append(psizeitem);
                 })
               })
            })
        </script>
        <script type="text/javascript">
			 $("#Pcolor-gather").on("click",".red",function(){
				$("#Pcolor-gather").find("i").css("display","none");
				$("#Pcolor-gather").find("i").attr("status","0");
				$(this).find("i").css("display","inline");
				$(this).find("i").attr("status","1");
			})
        	$("#Psize-gather").on("click",".red",function(){
				$("#Psize-gather").find("i").css("display","none");
				$("#Psize-gather").find("i").attr("status","0");
				$(this).find("i").css("display","inline");
				$(this).find("i").attr("status","1");
			})
			$("#sub").click(function (){
				var data = $("#Psize-gather").find("i[status='1']").attr("data");
				var data1 = $("#Psize-gather").find("i[status='1']").attr("data1");
				var supplierNum = parseInt(data1);
				console.log("aaa"+supplierNum);
				$.post("/home/usr_UsrCart_InsusrCart",{singleSpecNum:data,supplierNum:supplierNum,qty:1},function (data){
					var da = JSON.parse(data);
					if(da.data=="-1"){
						//购物车已有该商品
            layer.open({
							content: "购物车已有该商品"
							,skin: 'msg'
							,time: 2
						});
					}else if(da.data="1"){
            layer.open({
							content: "添加成功"
							,skin: 'msg'
							,time: 2
						});
						location.reload(true);
					}
				})
			})
			//减少
			$(".product-number").on("click",".reduceNum",function (){
				var number = $(this).next().find("input").val();
				var newnumber = parseInt(number)-1;
				$(this).next().find("input").val(newnumber);
				var singleSpecNum = $(this).attr("data");
				$.post("/home/usr_UsrCart_updateCart",{singleSpecNum:singleSpecNum,qty:newnumber},function (data){
					var da = JSON.parse(data);
					if(da.data == 1){
						console.log(111)
					}
				})
			})
			//增加
			$(".product-number").on("click",".increaseNum",function (){
				var number = $(this).prev().find("input").val();
				var newnumber = parseInt(number)+1;
				$(this).prev().find("input").val(newnumber);
				var singleSpecNum = $(this).attr("data");
				$.post("/home/usr_UsrCart_updateCart",{singleSpecNum:singleSpecNum,qty:newnumber},function (data){
					var da = JSON.parse(data);
					if(da.data == 1){
						console.log(111)
					}
				})
			})
			//右上角x删除
			$(".cart_list").on("click",".close-p",function (){
				var b = confirm("你确认要删除吗?");
				if(b){
					var product = $(this).attr("data");
					var numberArray = $(".reduceNum[data1="+ product +"]");
					var Array = $(numberArray);
					$(numberArray).each(function (index,element){
						//var singleSpecNum
						var e = $(element);
						$.post("/home/usr_UsrCart_deleteCart",{singleSpecNum:e.attr("data")},function (data){

						})
					})
					$(this).parent().parent().remove();
				}
			})
			$(".cart-checkbox").on("click",".check-p",function(){
				if(!$(this).is(':checked')){
					$("#closeAll").prop("checked",false);
					$(this).parent().parent().parent().find("[type='checkbox']").prop("checked",false);
				}else{
					$(this).parent().parent().parent().find("[type='checkbox']").prop("checked",true);
				}
				countTotalItem();
				countTotalPrice();
			})
			$(".cart-checkbox").on("change",".check-o",function(){
				countTotalItem();
				countTotalPrice();
				if(!$(this).is(':checked')){
					$("#closeAll").prop("checked",false);
					$(this).parent().parent().parent().prev().find("[type='checkbox']").prop("checked",false);
				}
			})
			$("#closeAll").change(function (){
				if($(this).is(':checked')){
					$("input[type='checkbox']").prop("checked",true);
				}else{
					$("input[type='checkbox']").prop("checked",false);
				}
				var aa=$("adadxczxx");
				$(aa).each(function (index,x){
					var ss=$(x);
					if(ss==null){
						console.log(11)
					}else{
						console.log(22)
					}
				});
				countTotalItem();
				countTotalPrice();
			})
			//选中删除
			$(".cart-btn-list-remove").click(function (){
				var n1 = $(".check-p[type='checkbox']:checked").length;
				var n2 = $(".check-o[type='checkbox']:checked").length;
				if(!$("#closeAll").is(':checked')&&(n1+n2)<=0){
          layer.open({
            content: "无法删除"
            ,skin: 'msg'
            ,time: 2
          });
					return;
				}
				if($("[type='checkbox']").length==1){
          layer.open({
            content: "无法删除"
            ,skin: 'msg'
            ,time: 2
          });
					return;
				}
				var b = confirm("你确认要删除吗?");
				if(b){
					var cAll = $("#closeAll");
					//删除全部
					if(cAll.is(':checked')){
						console.log("选中全部了")
						var numberArray = $(".reduceNum");
						var Array = $(numberArray);
						$(numberArray).each(function (index,element){
							//var singleSpecNum
							var e = $(element);
							$.post("/home/usr_UsrCart_deleteCart",{singleSpecNum:e.attr("data")},function (data){

							})
						})
						$(".cart_list").remove();
						countTotalItem();
						countTotalPrice();
						return;
					 }
					//console.log(11)
					//$("body").remove();
					//一级复选
					$.each($(".check-p[type='checkbox']:checked"),function(index,e){
						var productObj = $(e);
						var product = $(e).attr("data");
						var numberArray = $(".reduceNum[data1="+ product +"]");
						var Array = $(numberArray);
						$(numberArray).each(function (index,element){
							//var singleSpecNum
							var e = $(element);
							$.post("/home/usr_UsrCart_deleteCart",{singleSpecNum:e.attr("data")},function (data){

							})
						})
						productObj.parent().parent().parent().remove();
						countTotalItem();
						countTotalPrice();
					})
					//二级复选
					$.each($(".check-o[type='checkbox']:checked"),function(index,e){
						var el = $(e);
						var b = el.parent().parent().parent().prev().find("[type='checkbox']");
						if(!b.is(':checked')){
							var reduceNumArray =el.parent().parent().find(".reduceNum");
							$(reduceNumArray).each(function (index1,elment){
								var elmet1 = $(elment);
								var singleSpecNum = elmet1.attr("data");
								if(singleSpecNum!=null){
									$.post("/home/usr_UsrCart_deleteCart",{singleSpecNum:singleSpecNum},function (data){

									})
								}
								if((index1+1) == reduceNumArray.length){
									var color = el.parent().nextAll(".cart-goods-info").find(".color-title").val();
									var imgArray = el.parent().parent().parent().prev().find(".img-title");
									$(imgArray).each(function(index,elment){
										var e = $(elment);
										if(e.attr("data") == color){
											e.remove();
										}
									})

									var mitem = el.parent().parent().parent().find(".cart-message-item");
									if(mitem.length!=1){
										el.parent().parent().remove();
									}else{
										el.parent().parent().parent().parent().remove();
									}
									countTotalItem();
									countTotalPrice();
								}
							})
						}
					})
				}

			})
			$(function (){
				countTotalPrice();
				countTotalItem();
			})
			//总价
			function countTotalPrice(){
				 //totalprice
				var totalprice = 0.00;
				$(".check-o").each(function (index,elment){
					if($(elment).is(':checked')){
						var num = $(elment).parent().nextAll(".cart-goods-info").find(".number-spec");
						$(num).each(function (index,e){
							var price = $(e).parent().parent().prev().text();
							totalprice += $(e).val()*1*price;
						})
					}
				})
				var p = formatCurrency(totalprice);
				$("#totalprice").text(p);
			 }
			//项数
			function countTotalItem(){
				var item = 0;
				$(".check-o").each(function (index,elment){
					if($(elment).is(':checked')){
						var num = $(elment).parent().nextAll(".cart-goods-info").find(".number-spec");
						$(num).each(function (index,e){
							item += $(e).val()*1;
						})
					}
				})
				$("#items").text(item);
			}
			 /**
			 * 将数值四舍五入(保留2位小数)后格式化成金额形式
			 *
			 * @param num 数值(Number或者String)
			 * @return 金额格式的字符串,如'1,234,567.45'
			 * @type String
			 */
			function formatCurrency(num) {
			    num = num.toString().replace(/\$|\,/g,'');
			    if(isNaN(num))
			    num = "0";
			    sign = (num == (num = Math.abs(num)));
			    num = Math.floor(num*100+0.50000000001);
			    cents = num%100;
			    num = Math.floor(num/100).toString();
			    if(cents<10)
			    cents = "0" + cents;
			    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
			    num = num.substring(0,num.length-(4*i+3))+','+
			    num.substring(num.length-(4*i+3));
			    return (((sign)?'':'-') + num + '.' + cents);
			}
			 //提交
			 $("#submit").click(function (){
				 var strSpec = "";
				 if($(".check-o[type='checkbox']:checked").length==0){
           layer.open({
             content: "请选择"
             ,skin: 'msg'
             ,time: 2
           });
					 return;
				 }
				 $.each($(".check-o[type='checkbox']:checked"),function(index,e){
					var reduceNum = $(e).parent().nextAll(".cart-goods-info").find(".reduceNum");
					$(reduceNum).each(function (index,elment){
						strSpec += $(elment).attr("data")+",";
					})
				 })
				 if(strSpec != null || strSpec != ""){
					 var st = strSpec.charAt(strSpec.length - 1);
					 if(st==","){
						 strSpec = strSpec.substring(0,strSpec.length-1)
						  $.post("/home/usr_UsrCart_cartDateVerify",{strSpec:strSpec},function (data){
								var da = JSON.parse(data);
								if(da.data == 1){
									console.log(da.item)
									window.location.href="/home/usr_UsrCart_cartToDeliveryInformation?strSpec=" + da.item;
								}else{
                  layer.open({
      							content: "商品不为同一商店,无法提交"
      							,skin: 'msg'
      							,time: 3
      						});
								}
						 })
					 }else{

					 }
				 }
			 })
        </script>
    </body>
</html>
