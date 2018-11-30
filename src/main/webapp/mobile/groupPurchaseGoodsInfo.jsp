<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <link href="/home/static/themes/default/mobile/css/goods.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="/home/static/themes/default/mobile/js/goods.js"></script>
        <style>
            .detail_desc table{border-collapse:collapse; width:100%;}
            .detail_desc table td{border:1px solid #ccc;}
            .prod_info_line .attr_show span img{width:3.5rem;height:3.5rem;display:inline-block;}
            .prod_info_line .attr_show span.xmg-color{
            	padding: 0;
            	line-height:0.1rem;
            }
            .shop_sign{ height:6rem; padding-left:1.3rem; background:url(/home/static/themes/default/mobile/images/header_bg.jpg)
            no-repeat;} .shop_sign .logo{ margin-right:0.9rem; border-radius:50%; margin-top:1.3rem;}
            .shop_sign .logo img{ border-radius:50%; width:3.5rem; height:3.5rem;}
            .shop_sign .info{ width:17.6rem; margin-top:1.8rem;} .shop_sign .info .txt{
            font-size:14px; margin-bottom:0.4rem; color:#fff;} .shop_sign .info .para
            .icon-years-limit-group{ padding:3px 10px 3px 3px; background-image:linear-gradient(to
            left, #5780f1, #85a5ff); border-radius:24px; display:inline-block; margin-right:0.5rem;}
            .shop_sign .info .para .icon-years-limit-group img{ vertical-align:middle;
            width:0.8rem; height:0.8rem;} .shop_sign .info .para .icon-years-limit-group
            .years-num{ color:#fff;} .shop_sign .info .para .icon-years-limit-group
            .years-unit{ color:#fff;} .shop_sign .info .para .icon img{ max-height:1.1rem;
            margin-right:0.5rem; vertical-align:text-bottom;}
        </style>
        <style>
          .xmg-huodong{
          height: 5.0625rem;
          background:#f9f9f9;
          }
          .xmg-huodong .xmg-l, .xmg-huodong .xmg-r{
          width:50%;
          float:left;
          text-align: center;
          font-size:0.6875rem;
          }
          .xmg-huodong .xmg-l .xmg-t p{
          margin-top:0.1rem;
          font-size:0.6875rem;
          }
          .xmg-huodong .xmg-l .xmg-t img{
          width:1.34375rem;
          height:1.34375rem;
          margin-top:0.8rem;
          }
          .xmg-huodong .xmg-l .xmg-b {
          font-size:0.6875rem;
          margin-top:0.3125rem;
          }
          .xmg-huodong .xmg-l .xmg-b span{
          color:#d80000;
          font-size:0.6875rem;
          }
          .xmg-huodong .xmg-r .xmg-t{
          margin-top:1.5rem;
          color:#ff8700;
          font-size:1.2rem;
          }
          .xmg-huodong .xmg-r .xmg-b{
          margin-top:0.3125rem;
          color:#999;
          text-decoration: line-through;
          }
          table{
              width: 100% !important;
          }
        </style>
        <style>
            .shop_nav{ background:#fff;} .shop_nav ul{ height:2.5rem; overflow:hidden;}
            .shop_nav ul li{ width:25%; float:left; line-height:2.5rem; height:2.5rem;
            text-align:center; box-sizing:border-box; border-bottom:4px solid transparent;}
            .shop_nav ul li.active{ border-bottom:4px solid #2b55c5;} .shop_nav ul
            li a{ color:#666; font-size:0.8rem;}
        </style>
    </head>

    <body>

        <%@ include file="/mobile/template/header.jsp" %>
        <!-- 公司头部 - 含背景图 -->
        <div class="shop_sign">
            <!-- 公司logo -->
            <div class="logo fl">
                <img src="<c:if test='${not empty supView.logo}'>${envConfig.imageBaseUrl}${supView.logo}</c:if><c:if test='${empty supView.logo}'>/home/static/images/headimg.jpg</c:if>" alt="logo">
            </div>
            <!-- 公司简介信息 -->
            <div class="info fr">
                <div class="txt">
                    <!-- Wenzhou Qili Shoe Industry Co., Ltd -->
                    <s:text name="home" />
                </div>
                <!-- 图标组 -->
                <div class="para">
                	<c:if test="${not empty supView.authAge && supView.authAge > 0}">
                    <!-- 自制年限图标 -->
                    <div class="icon icon-years-limit-group">
                		<a href="/m/spain/company.html#data" target="_blank">
                    		<img src="/home/static/themes/default/mobile/images/doller_pic_o.png" alt="">
                    		<span class="years-num">${supView.authAge}</span>
                    		<span class="years-unit">YRS</span>
                		</a>
              		</div>
              		</c:if>
              		<c:if test="${supView.isAuth == 1}">
	                    <a class="icon" href="https://www.shoestp.com/m/qili_123/company.html#data"
	                    target="_blank">
	                        <img src="/home/static/themes/default/mobile/images/defence-safe-o.png" alt="defence-safe">
	                    </a>
	                    <a class="icon" href="https://www.shoestp.com/m/qili_123/company.html#data"
	                    target="_blank">
	                        <img src="/home/static/themes/default/mobile/images/certification-o.png" alt="certification">
	                    </a>
                    </c:if>
                </div>
            </div>
            <div class="clear">
            </div>
        </div>
        <!-- 顶部导航条 -->

        <div class="shop_nav">
            <ul style="display:flex;">
                <li class="">
                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supView.pkey}">
                        <!-- HOME -->
                        <s:text name="home" />
                    </a>
                </li>
                <li class="">
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
        <div class="wrapper">
            <div class="crumb clean">
                <a href="/">
                    <span class="icon_crumb_home">
                    </span>
                </a>
                <c:forEach begin="1" end="${fn:length(pdtCatList)}" varStatus="index">
					<c:if test="${!index.last}">
						<em>
		                    <i>
		                    </i>
		                </em>
		                <a href="">
		                    ${pdtCatList[fn:length(pdtCatList) - index.index].name}
		                </a>
					</c:if>
					<c:if test="${index.last}">
						<a href="">
                    		${pdtCatList[fn:length(pdtCatList) - index.index].name}
                		</a>
					</c:if>
				</c:forEach>
            </div>
            <div class="detail_pic clean ui_border_b">
                <div class="goods_pic">
                    <ul class="clean" style="width: 1875px; display: block;">
                    	<c:forTokens items="${product.picture}" delims="," var="pic">
	                    	<li class="fl pic_box" style="width: 375px; height: 375px; line-height: 375px;">
	                            <img src="${envConfig.imageBaseUrl}${pic}" style="max-height: 375px;">
	                            <span>
	                            </span>
	                        </li>
                    	</c:forTokens>
                    </ul>
                    <div class="point">
                    	<c:set value="${fn:split(product.picture,',')}" var="picsLength"/>
                    	<c:forEach begin="0" end="${fn:length(picsLength)}">
	                        <span class="on">
	                        </span>
                    	</c:forEach>
                    </div>
                </div>
                <div class="big_pic" style="display:none;">
                    <img src="/home/static/themes/default/mobile/images/a1ba4ef841.jpg.240x240.jpg" class="normal">
                </div>
                <!-- 抛物线的div -->
            </div>
            <div class="goods_info clean">
                <form id="goods_form" action=""
                method="post">
                    <div class="prod_info_name">
                        ${product.name}
                    </div>
                    <div class="prod_info_brief">
                        <!-- Item Code --><s:text name="itemCosts" />: ${product.sku}
                        <a href="" target="_blank" class="gongying_info">
                            ${supView.name}
                        </a>
                    </div>
                    <div class="clean prod_info_price">
                        <div class="box_price clean price_1 last_price">
                            <div class="fl title">
                                <!-- Reference Price -->
                                <s:text name="price" />:
                            </div>
                            <div class="fl">
                <%--                <div class="price cur_price">
                                    <span>
                                        ${env.currency.shortName } ${env.currency.symbols}
                                    </span>
                                    ${product.curPrice}
                                </div>--%>
                            </div>
                        </div>
                    </div>



                    <div class="xmg-huodong">

                    		<div class="xmg-l">
                    			<div class="xmg-t">
                    				<img src="/home/static/themes/default/mobile/images/xmg-jishitb.png" alt="" />
                    				<c:if test="${groupPurchase.status == 1}">
                    				<p><!-- SALE ENDS --><s:text name="shoppingjl_start"/></p>
									</c:if>
									<c:if test="${groupPurchase.status == 2}">
									<p><!-- SALE ENDS --><s:text name="shoppingjl_end"/></p>
									</c:if>
                    			</div>
                    			<div class="xmg-b">
                    				<span id="days"></span>DAYS <span id="time"></span>
                    			</div>
                    		</div>
                    		<%--<div class="xmg-r">
                    			<div class="xmg-t">${env.currency.symbols}${product.curPrice}</div>
                    					<c:forEach items="${colorToSpec}" var="colorSpec">
                                    		<c:forEach items="${colorSpec.value}" var="spec">
                                    			<div class="xmg-t" color="${colorSpec.key.pkey}" size="${spec.sizeId}" style="display:none;">${env.currency.symbols}${spec.price}</div>
                                    		</c:forEach>
                                    	</c:forEach>
                    			&lt;%&ndash; <div class="xmg-b"> ${env.currency.shortName }  ${env.currency.symbols}${product.curPrice}</div> &ndash;%&gt;
                    		</div>--%>

                    </div>


                    <div class="clean prod_info_line ui_border_t">
                    	<div class="clean rows attr_show none" name="Color">
                            <div class="title">
                                <!-- Color -->
                                <s:text name="color" />:
                            </div>
                            <div class="txt">
                            	<c:forEach items="${colorShow}" var="color">
	                                <span data="${color.key.pkey}" onclick="chooseThisColor(${color.key.pkey})" class="xmg-color" title="${color.key.name}">
	                                	<c:if test="${color.value !=  ''}">
											<a>
												<img class="lazy" src="./static/images/blank.gif" data-original="${envConfig.imageBaseUrl}${color.value}" alt="${color.key.name}">
											</a>
										</c:if>
										<c:if test="${color.value == '' }">
											<a style="display: block;padding: 11px;">
												${color.key.name}
											</a>
										</c:if>
	                                    <%-- <img src="${envConfig.imageBaseUrl}${color.value}" alt="" /> --%>
	                                    <em>
	                                        X
	                                    </em>
	                                </span>
                            	</c:forEach>

                                <input type="hidden" name="id[165]" id="attr_165" attr="165" value=""
                                class="attr_value colorid">
                            </div>
                        </div>
                        <div class="clean rows attr_show none" name="Size">
                            <div class="title">
                               <!--  Size -->
                               <s:text name="product.size_attr" />:
                            </div>
                            <div class="txt">
                            	<c:forEach items="${colorToSpec}" var="specMap">
                            		<c:forEach items="${specMap.value}" var="spec">
                            			 <span value="490" data="${spec.sizeId}" onclick="chooseThisSize(${specMap.key.pkey},${spec.sizeId})" class="size${specMap.key.pkey }" title="${spec.size}" style="display:none;">
			                                   	${spec.size}
			                                    <em>
			                                        X
			                                    </em>
			                                </span>
                            		</c:forEach>
                            	</c:forEach>
                                <input type="hidden" name="id[164]" id="attr_164" attr="164" value=""
                                class="attr_value">
                            </div>
                        </div>
                        <div class="clean rows">
                            <%-- <div class="title">
                                <!-- MOQ -->
                                <s:text name="products.moq" />:
                            </div>
                            <div class="txt">
                            	<fmt:formatNumber type="number" maxFractionDigits="0" value="${product.minOq}" />
                            </div> --%>
                        </div>
                       <%-- <div class="clean rows prod_info_qty" data="{&quot;min&quot;:${product.minOq},&quot;max&quot;:${product.maxOq}}">
                            <div class="title">
                                <!-- QTY -->
                                <s:text name="order_line.qty" />:
                            </div>
                            <div class="txt">
                                <div class="cut">
                                    -
                                </div>
                                <div class="qty">
                                    <input type="text" name="Qty" value="0" id="quantity1">
                                </div>
                                <div class="add">
                                    +
                                </div>
                                <div class="stock">
                               			<b>
                                 				<fmt:formatNumber type="number" maxFractionDigits="0" value="${product.minOq}" /> <s:text name="groupPurchaseGoodsInfo.Minimum_Order_Quantity"/>
                               			</b>
                               			
                                    	&lt;%&ndash; <c:forEach items="${colorToSpec}" var="colorSpec">
                                    		<c:forEach items="${colorSpec.value}" var="spec">
                                    			
                                    				<b color="${colorSpec.key.pkey}" size="${spec.sizeId}" style="display:none;">
                                    				<c:if test="${spec.storeCount < product.stock}">
                                    					<span>Sole Out</span>
                                    				</c:if>
                                    				<c:if test="${spec.storeCount > product.stock}">
			                                    		<fmt:formatNumber type="number" maxFractionDigits="0" value="${spec.storeCount}" />
			                                    		 <!-- in Stock -->
			                                    		 <s:text name="product.stock" />
                                    				</c:if>
			                                    	</b>
                                    		</c:forEach>
                                    	</c:forEach> &ndash;%&gt;
                                   	
                                </div>
                            </div>
                            <a href="javascript:;" class="add_favorite fl" style="line-height:2.1875rem; height:2.1875rem; font-size:1rem; color:#666; margin-left:6%;"
                            data="${product.pkey}">
                                <img src='<c:if test="${favorite == null || favorite == ''}">/home/static/themes/default/mobile/images/like.png</c:if><c:if test="${favorite != null}">/home/static/themes/default/mobile/images/liked.png</c:if>' style="vertical-align:middle;">
                                <number class="collect">${product.favoriteCount}</number>
                            </a>
                            <a href="javascript:void(0);" class="fl" style="height:2.18rem; margin-left:2%;">
                                <img src="/home/static/themes/default/mobile/images/norton.png" style="max-height:100%;">
                            </a>
                        </div>--%>
                    </div>
                    <div class="widget prod_info_actions clean">
                  <%--      <div class="btn_add" style="width:100%;">
                            <input type="button" value="<s:text name="addToCartTo" />" data="${product.pkey}" class="btn_global add_btn addtocart AddtoCartBgColor"
                            id="addtocart">
                        </div>--%>
                        <div class="blank15">
                        </div>
                        <a id="addInquiry" target="_blank" class="btn_global add_btn BuyNowBgColor inquiry_btn"
                        style="background:#333; border-color:#333;" data="${product.pkey}">
                            <!-- Inquiry/RFQ -->
                            <s:text name="inquiry" />
                        </a>
                        <div class="clear">
                        </div>
                    </div>
                    <input type="hidden" id="ProId" name="ProId" value="5972">
                    <input type="hidden" id="ItemPrice" name="ItemPrice" value="10.00" initial="10.00"
                    sales="0" discount="" old="0">
                    <input type="hidden" name="Attr" id="attr_hide" value="{}">
                    <input type="hidden" id="ext_attr" value="{&quot;504&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;505&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;507&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;508&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;510&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;511&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;240&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;255&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;513&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;515&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;219&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;241&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;493&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;495&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;497&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;498&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;499&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;500&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;374&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;281&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;471&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;470&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;472&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;473&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;474&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;475&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;476&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;478&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;479&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;480&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;481&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;482&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;483&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;484&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;485&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;308&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;309&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;477&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;274&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;532&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;337&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;468&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;469&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;236&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;238&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;218&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;237&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;534&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;490&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;],&quot;492&quot;:[&quot;0.00&quot;,&quot;0&quot;,&quot;0&quot;,&quot;&quot;,&quot;0&quot;]}">
                    <input type="hidden" name="products_type" value="0">
                    <input type="hidden" name="SId" value="0">
                    <input type="hidden" name="TId" value="0">
                    <input type="hidden" id="CId" value="226">
                    <input type="hidden" id="CountryName" value="United States">
                    <input type="hidden" id="ShippingId" value="251">
                    <input type="hidden" id="attrStock" value="0">
                    <input type="hidden" id="IsCombination" value="0">
                </form>
            </div>
            <div class="detail_list">
            </div>
            <div class="prod_info_divide">
            </div>
            <div class="prod_info_detail ui_border_b">
                <section class="detail_desc detail_close">
                    <div class="t">
                        <!-- Specifications -->
                        <s:text name="products.specifics" />
                        <em>
                            <i>
                            </i>
                        </em>
                    </div>
                    <div class="text ui_border_t">
                    	<c:forEach items="${proAttribute}" var="attr">
								<div class="specifics_text">
		                            <strong>
		                                ${attr.key.name}:
		                            </strong>
		                            ${attr.value.name}
		                        </div>
						</c:forEach>

                    </div>
                </section>
                <section class="detail_desc" deep="2">
                    <div class="t ui_border_t">
                        <!-- Products Details -->
                        <s:text name="products" />
                        <em>
                            <i>
                            </i>
                        </em>
                    </div>
                    <div class="text editor_txt ui_border_t">
                    ${product.description}

                    </div>
                </section>
<%--                 <section class="detail_desc detail_close"> --%>
<!--                     <div class="t ui_border_t"> -->
<!--                         Company Profile -->
<%--                         <s:text name="Company" /> --%>
<!--                         <em> -->
<!--                             <i> -->
<!--                             </i> -->
<!--                         </em> -->
<!--                     </div> -->
<!--                     <div class="text editor_txt ui_border_t"> -->
<!--                         &nbsp;&nbsp; -->
<%--                         <strong> --%>
<!--                             Company basic information: -->
<%--                         </strong> --%>
<!--                         <table border="1" cellpadding="0" cellspacing="0"> -->
<!--                             <tbody> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Business Type -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<%--                                         ${supView.businessTyp} --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         Verified -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Main product -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<%--                                         ${supView.mainProd} --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         &nbsp; -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Trademarks -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         <font face="arial, sans-serif"> -->
<!--                                             Juyue&nbsp; -->
<!--                                         </font> -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         Verified -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Locations -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<%--                                         ${supView.companyAddr} --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         &nbsp; -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Total Employees -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<%--                                         ${supView.totalEmployees} --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         &nbsp; -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Total Annual Revenue -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         &nbsp; -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         &nbsp; -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Year Established -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<%--                                         ${supView.companyEstablishTime} --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         &nbsp; -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td> -->
<%--                                         <strong> --%>
<!--                                             Business Scope -->
<%--                                         </strong> --%>
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         Western Europe and North America, southeast Asia and other countries and -->
<!--                                         regions -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         Verified -->
<!--                                     </td> -->
<!--                                 </tr> -->
<!--                             </tbody> -->
<!--                         </table> -->
<!--                         <br> -->
<!--                         <br> -->
<!--                         <br> -->
<!--                         &nbsp; -->
<!--                     </div> -->
<%--                 </section> --%>

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
        <div class="pop_up language_side">
            <div class="pop_up_container clean">
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <div class="menu_list">
                </div>
            </div>
        </div>
        <div class="pop_up currency_side">
            <div class="pop_up_container clean">
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <div class="menu_list">
                    <div class="item">
                        <a href="javascript:;" data="USD">
                            <img src="/home/static/themes/default/mobile/images/53092c531f.jpg" alt="${env.currency.shortName}">
                            ${env.currency.shortName}
                        </a>
                    </div>
                </div>
            </div>
        </div>
       <%@ include file="/mobile/template/foot_menu.jsp" %>

        <div id="tips_cart">
            <p>
                <!-- Adding to cart succeed! -->
                <s:text name="Addingtocartsucceed"/>
            </p>
            <p>
                <span class="tips_cart_count">
                </span>
                <!-- items in cart. -->
                <s:text name="itemsincart"/>
            </p>
            <p>
                <!-- Subtotal -->
                <s:text name="cart.subtotal"/>:
                
                <span class="tips_cart_total">
                </span>
            </p>
            <div class="blank5">
            </div>
            <a href="/home/usr_UsrCart_cartshopping" class="btn_global btn_check">
               <!--  Proceed to Checkout -->
                <s:text name="mobile.checkout"/>
            </a>
            <a href="javascript:;" class="btn_global btn_return">
                <!-- Return to Shopping -->
                <s:text name="mobile.continue_shop"/>
            </a>
        </div>


	<script type="text/javascript">
		var time = '${groupPurchase.endTime}';
		var startTime = '${groupPurchase.startTime}';
		var actState = '${groupPurchase.status}';
		window.onload = function(){

			var endTime = new Date(time);
			var startTime2 = new Date(startTime);
			var nowTime = new Date();
			//时间戳相减除以1000 = 秒
			var differenceTime;
			if(actState == 2){
				differenceTime = endTime - nowTime;
			}else if(actState == 1){
				differenceTime = startTime2 - nowTime;
			}
			
			var day = Math.floor(differenceTime/86400000);
			var hour = Math.floor(differenceTime%86400000/3600000);
			var minute = Math.floor(differenceTime%86400000%3600000/60000);
			var second = Math.floor(differenceTime%86400000%3600000%60000%60);
			countDown(day,hour,minute,second);
			$("div[name=Color] .txt span:first").click();
		}
		
		function chooseThisSize(colorId,sizeId){
			$("div[color="+colorId+"][size="+sizeId+"]").show();
			$("div[color="+colorId+"][size="+sizeId+"]").siblings(".xmg-t").hide();
			/* $("b[color="+colorId+"][size="+sizeId+"]").show();
			$("b[color="+colorId+"][size="+sizeId+"]").siblings("b").hide(); */
		}

		function chooseThisColor(id){
			$(".size"+id).siblings().hide();
			$(".size"+id).siblings().removeClass("selected")
			$(".size"+id).show();
		}

		$("#addtocart").on("click",function(){
			var size = $("div[name=Size] .txt .selected").attr("data");
			var color = $("div[name=Color] .txt .selected").attr("data");
			if(size == undefined || color == undefined || size == "" || color == ""){
				$('html').tips_box("请选择规格", 'error');
				return;
			}
			var qty = $("#quantity1").val();

			var pdt = $(this).attr("data");
			$.ajax({
				url:'/home/usr_UsrCart_addSpecToCart',
				type:'post',
				data:{"pdtPkey":pdt,"colorPkey":color,"sizePkey":size,"qty":qty},
				dataType:'json',
				success:function(data){
					if(data.ret == 1){
						$('html').tips_box("添加成功", 'success');
					}else if(data.ret == -1){
						window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl="+window.location.pathname+window.location.search;
					}else{
						$('html').tips_box(data.msg, 'error');
					}
				}
			})

		})

		$("#addInquiry").on("click",function(){
			$.ajax({
			    url: "/home/pdt_PdtConsultPdtList_add",
			    data: {
			      "product": $(this).attr("data")
			    },
			    method: "POST",
			    success: function(data) {
			      if (data)
			        if (data.ret == -1) {
			          window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl="+window.location.pathname+window.location.search;
			        } else if(data.ret == 1){
			        	$('html').tips_box("添加成功", 'success');
			        }else{
			        	$('html').tips_box(data.msg, 'error');
			        }
			    }
			})
		})
		
		 $(".add").on("click",function(){
       		var count = $(this).siblings(".qty").children("input[name=Qty]").val();
       		count++;
       		$(this).siblings(".qty").children("input[name=Qty]").val(count);
       	})

       	$(".cut").on("click",function(){
       		var count = $(this).siblings(".qty").children("input[name=Qty]").val();
       		count--;
       		if(count <= 0){
       			return;
       		}
       		$(this).siblings(".qty").children("input[name=Qty]").val(count);
       	})

		function countDown(day,hour,minute,second){
			setInterval(function(){
				second--;
				if(second < 0){
					second = 59;
					minute--;
					if(minute < 0 ){
						minute = 59;
						hour--;
						if(hour <=0 ){
							day--;
							if(day < 0){
								$("#days").parent().hide();
							}
						}
					}
				}
				$("#days").text(day);
				$("#time").text(hour+":"+minute+":"+second);
			},1000);
		}
	</script>
  </body>
</html>
