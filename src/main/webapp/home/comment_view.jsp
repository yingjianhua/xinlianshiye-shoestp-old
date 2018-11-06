<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="Cheap Wholesale Shoes,Fashion Shoes,Shoes For men,Cheap Shoes">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link href="./static/css/lightbox.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <style type="text/css">
     .pic_list img{
        	max-width: 108px;
            height: 108px;
            object-fit: contain;
		    margin-right: 15px;
		    border: 1px solid #ddd;
		    padding: 5px;
        }
    </style>
</head>

<body class="lang_en w_1200">

<jsp:include page="template/web-top.jsp"></jsp:include>
<jsp:include page="template/new-header.jsp"></jsp:include>

<div id="main" class="wide">
    <div id="location">
        Your position: <a href="/">Home</a> &gt;
        <c:forEach var="d" items="${commentViewPageView.breadcrumbNav}" varStatus="status">
            <a href="/home/pdt_PdtProduct?cated=${d.pkey}">${d.name}</a>
            <c:if test="${!status.last}">
                &gt;
            </c:if>
        </c:forEach>

    </div>
    <div id="pro_detail">
        <div class="review_left fl">
            <div class="goods review_goods">
                <h2 class="goods_title">Customer Reviews</h2>
                <div class="goods_view">
                    <dl class="clearfix">
                        <dt class="fl">
                            <a class="pic_box"
                               href="/home/pdt_PdtProduct_gtProductsInfo?id=${commentViewPageView.pdtProduct.id}"
                               title="${commentViewPageView.pdtProduct.name}">
                                <img src="${envConfig.imageBaseUrl}${commentViewPageView.pdtProduct.image}"> <span></span>
                            </a>
                        </dt>
                        <dd>
                            <p class="name">
                                <a
                                        href="/home/pdt_PdtProduct_gtProductsInfo?id=${commentViewPageView.pdtProduct.id}"
                                        title="${commentViewPageView.pdtProduct.name}">${commentViewPageView.pdtProduct.name}</a>
                            </p>
                            <p class="price">
                                <em class="currency_data FontColor">$</em> <span
                                    class="price_data FontColor"
                                    data="${commentViewPageView.pdtProduct.price}">${commentViewPageView.pdtProduct.price}</span>
                            </p>
                        </dd>
                    </dl>
                </div>
            </div>

            <div id="review_box">
                <div class="widget prod_write_review">
                    <div class="review_title">
                        <span>Customer Reviews</span>
                    </div>
                    <div class="average_rating">
                        <h6>Average rating:</h6>
                        <p>
                            <span class="star star_b"></span> <strong></strong> <a
                                class="review_nums"
                                href="/home/pdt_PdtProduct_viewComments?id=${commentViewPageView.pdtProduct.id}">(based
                            on <span>${commentViewPageView.total}</span>
                            reviews)
                        </a>
                        </p>
                        <div>
                            <a href="/home/pdt_PdtProduct_writeComment?id=${commentViewPageView.pdtProduct.id}"
                               class="write_review_btn ReviewBgColor" rel="nofollow">Write
                                a review</a>
                        </div>
                    </div>
                </div>
                <div class="widget prod_recent_review">
                    <h6>Recent Reviews</h6>
                    <c:forEach var="d" items="${commentViewPageView.pdtCommentViews}">
                        <div class="widget review_item">
                            <input type="hidden" class="commentId" value="${d.id}"/>
                            <ul class="user fl">
                                <li><span class="star star_s${d.product_satisfaction}"></span></li>
                                <li><span class="by_text">By</span> <span>${d.name}</span></li>
                               <%--  <li><i class="icon_level"> <img
                                        src="${envConfig.imageBaseUrl}${d.usrMemberLevelIco}">
                                </i> <strong class="level_text FontColor">${d.usrMemberLevelName}</strong></li> --%>
                                <li><span class="time">${d.commenttime}</span></li>
                            </ul>
                            <div class="like fr">
                                <div class="vote">
                                    <p>Was this review helpful?</p>
                                    <div>
                                        <a href="javascript:void(0)" type="1">
                                            <span class="icon_agree">(${d.useful_number})</span>
                                        </a>
                                        <span>|</span>
                                        <a href="javascript:void(0)" type="0">
                                            <span class="icon_oppose">(${d.unuseful_number})</span>
                                        </a>
                                    </div>
                                </div>
                                <a
                                        href="https://api.addthis.com/oexchange/0.8/forward/facebook/offer?url=https://"
                                        class="fb_share" target="_blank"

                                >
                                    <em class="icon_facebook_mini"></em>Share to facebook
                                </a>
                            </div>
                            <div class="main">
                                <div class="content">${d.comment}</div>
                                  <div class="pic_list">
                                    <div>
                                    	<c:forEach items="${d.images}" var="picUrl">
                                    	<img src="${picUrl}" alt="" />
                                    	</c:forEach>
                                     </div>	
                                    </div>
                                <c:if test="${d.reply!=null && fn:length(d.reply)>0}">
                                    <div class="reply">
                                            <%--<a href="javascript:;" class="reply_btn" reply="0">Reply</a>--%>
                                            <%--</div>--%>
                                        <div class="edit"><a href="javascript:void(0);" class="reply_btn">Reply
                                        </a>
                                            <div class="w_review_replys hide">
                                                <span class="arrow"></span>
                                                <div class="review_reply">
                                                    <p>商家回复:${d.reply}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="prod_review_view">
                    <div class="blank12"></div>
                    <%--<div id="turn_page" class="my-page">--%>
                    <%--<li><font class="page_noclick"> <em--%>
                    <%--class="icon_page_prev"></em>Previous--%>
                    <%--</font></li>--%>
                    <%--<c:forEach var="d" items="${commentViewPageView.total}" varStatus="index">--%>
                    <%--<li><font class="page_item_current">${index.current}</font></li>--%>
                    <%--</c:forEach>--%>
                    <%--<li class="page_last"><font class="page_noclick"--%>
                    <%--style="width: 67px;">Next <em class="icon_page_next"></em>--%>
                    <%--</font></li>--%>
                    <%--</div>--%>
                    <%--<div class="blank12"></div>--%>
                </div>
            </div>
        </div>
        <div class="review_right fr">
            <c:if test="${fn:length(commentViewPageView.likeViews)>0}">
                <div id="may_like" class="sidebar">
                    <h2 class="b_title">You May Like</h2>
                    <c:forEach var="d" items="${commentViewPageView.likeViews}">
                        <div class="b_list">
                            <dl class="pro_item clearfix">
                                <dt class="fl">
                                    <a class="pic_box"
                                       href="/home/pdt_PdtProduct_gtProductsInfo?id=${d.id}"
                                       title="${d.name}">
                                        <img src="${envConfig.imageBaseUrl}${d.image}?x-oss-process=image/resize,h_240"
                                             alt="${d.name}">
                                        <span></span>
                                    </a>
                                </dt>
                                <dd class="fl pro_info">
                                    <div class="pro_name">
                                        <a
                                                href=""/home/pdt_PdtProduct_gtProductsInfo?id=${d.id}""
                                        title="${d.name}">${d.name}</a>
                                    </div>
                                    <div class="pro_price">
                                        <em class="currency_data FontColor">${env.currency.symbols}</em> <span
                                            class="price_data FontColor" data="${d.price}">${d.price}</span>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </c:forEach>


                </div>
            </c:if>

        </div>
        <div class="blank12"></div>
    </div>
    <div class="blank12"></div>
</div>

<jsp:include page="template/new-foot.jsp"></jsp:include>
<div id="hj_top" style="opacity: 0;">
    <img src="./static/images/hj_top.png">
</div>
</body>
<script type="text/javascript" src="./static/js/review.js"></script>

<script type="text/javascript">
    $(".reply_btn").on("click", function () {
        $(".write_reply").toggleClass('hide');
    })
    $("div.vote a").on("click", function () {
        console.log($(this).attr("type"))
        $.ajax({
            url: "/home/pdt_PdtProduct_vote",
            data: {
                type: $(this).attr("type"),
                id: $("input.commentId", $(this).parents("div.review_item")).val()
            }
        })
    })
</script>


<style type="text/css">
    .my-page li {
        width: auto !important;
    }
</style>
</html>
