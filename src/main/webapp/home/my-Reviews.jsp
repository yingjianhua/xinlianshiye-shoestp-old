<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <style>
        .review-item{
          display: block;
          padding: 20px;
          color: #666;
          border-bottom: 1px dashed #ddd;
        }
        .review-item:last-child{
          border-bottom-width: 0;
        }
        .review-item .goods-pic{
          float: left;
          width: 100px;
          height: 100px;
          object-fit: contain;
         }
        .review-item .review-info-wrap{
          margin-left: 120px;
        }
        .review-item .review-info-wrap .review-info-header{
          position: relative;
          border-bottom: 1px dotted #ddd;
        }
        .review-item .review-info-wrap .review-star{
          width: 100px;
          height: 16px;
          margin-bottom: 15px;
          background: url("static/images/global/star.png") no-repeat;
          background-position: -100px -61px;
        }
        .review-item .review-info-wrap .review-star.star_01{
          background-position: -80px -61px;
        }
        .review-item .review-info-wrap .review-star.star_02{
          background-position: -60px -61px;
        }
        .review-item .review-info-wrap .review-star.star_03{
          background-position: -40px -61px;
        }
        .review-item .review-info-wrap .review-star.star_04{
          background-position: -20px -61px;
        }
        .review-item .review-info-wrap .review-star.star_05{
          background-position: 0 -61px;
        }
        .review-item .review-info-header .review-time{
          position: absolute;
          right: 10px;
          bottom: 4px;
        }
        .review-item .review-content{
          padding: 10px;
        }
        .review-item .review-reply{
          padding: 10px;
          color: #999;
          border-radius: 6px;
        }
        .review-item .review-reply:before{
          content:"掌柜回复：";
          display: inline-block;
        }
    </style>
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
						<a href="/home/odr_OdrOrder_orders" title="My Orders"><s:text name="my_Reviews"/>
							<i></i>
						</a>
					</li>
				</ul>
			</div>

			<%@ include file="/home/template/account/lib-user-menu.jsp" %>
			<div id="lib_user_main">
				<h1 class="lib_user_title"><s:text name="my_Reviews"/></h1>
				
				<c:if test="${fn:length(commentView.items) > 0 }">
				  <div class="review-list">
		            <c:forEach items="${commentView.items}" var="v">
		              <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${v.id}" class="review-item clearfix">
		                <%-- <div class="disno">${v.id}</div> --%>
		                <c:forTokens items="${v.image}" delims="," end="0" var="image">
		                	<img class="goods-pic" src="${envConfig.imageBaseUrl}${image}" alt="">
		                </c:forTokens>
		                <div class="review-info-wrap">
		                	<p style="margin-bottom: 28px;">${v.name}</p>
		                  <div class="review-info-header">
		                    <div class="review-star star_0${v.star}"></div>
		                    <div class="review-time">${v.time}</div>
		                  </div>
		                  <div class="review-content">${v.content}</div>
		                  <c:if test="${not empty v.reply}">
		                    <div class="review-reply">${v.reply}</div>
		                  </c:if>
		                </div>
		              </a>
		            </c:forEach>
		          </div>

				<div class="blank20"></div>
				<div id="turn_page">
					<li>
						<font <s:if test="commentView.currentPage > 1">onclick="location.href='/home/pdt_PdtProduct_selectComment?start=${commentView.start-8}'"</s:if>class="page_button <s:if test="commentView.currentPage == 1">page_noclick</s:if> prev">
							<em class="icon_page_prev"></em><s:text name="previous"/>
						</font>
					</li>
					<li>
						<font class="page_item_current">${commentView.currentPage}</font>
					</li>
					<li class="page_last">
						<font <s:if test="commentView.currentPage < commentView.totalPage">onclick="location.href='/home/pdt_PdtProduct_selectComment?start=${commentView.start+8}'"</s:if>class="page_button <s:if test="commentView.currentPage == commentView.totalPage">page_noclick</s:if> next"><s:text name="next"/><em class="icon_page_next"></em>
						</font>
					</li>
				</div>
				</c:if>
				<c:if test="${fn:length(commentView.items) == 0 }">
					<a style="margin: 10px 20px;display: block;"><s:text name="No_Comment"></s:text></a>
				</c:if>
				
			</div>
		</div>
	</div>
	<%@ include file="/home/template/new-foot.jsp" %>
	<div id="hj_top" style="opacity: 1;">
		<img src="/home/static/images/hj_top.png"/>
	</div>
</body>

</html>
