<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/lang/${env.curLanguage }.js"></script>
    <script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/home/static/js/main.js"></script>
</head>

<body class="lang_en w_1200">
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<jsp:include page="template/web-top.jsp"></jsp:include>
<%--<jsp:include page="template/new-header.jsp"></jsp:include>--%>

<script type="text/javascript">
    $(function () {
        $('.n_searico').click(function (e) {
            e.stopPropagation();
            $(this).css('display', 'none');
            $('.new_nav_item').css('display', 'none');
            $('.n_search').fadeIn();
        });
        $('.n_search').click(function (e) {
            e.stopPropagation();
        });
        $('body').click(function () {
            $('.n_search').css('display', 'none');
            $('.new_nav_item').fadeIn();
            $('.n_searico').fadeIn();
        });
    });
</script>
<div id="main">
    <index-top></index-top>
    <div class="wide">
        <div id="location">
            <s:text name="Global.Your_Location"/>: <a href="/"><s:text name="Global.Home"/></a> &gt;
            <c:forEach var="d" items="${commentViewPageView.breadcrumbNav}" varStatus="status">
                <a href="/home/pdt_PdtProduct?cated=${d.pkey}">${d.name}</a>
                <c:if test="${!status.last}">
                    &gt;
                </c:if>
            </c:forEach>
        </div>
        <div id="pro_review">
            <div class="left fl">
                <div class="title"><s:text name="comment_write.Product_Review"/></div>
                <!-- 要购买才能评论 start -->
                <s:if test="commentViewPageView.commentPermission==false">
                    <p class="error">${commentViewPageView.commentPermissionMessage}</p>
                </s:if>
                <s:else>
                    <form id="review_form">
                        <label><s:text name="comment_write.Rating"/>:</label>
                        <ul class="rating">
                            <li>
                                <b><s:text name="Global.Proposed_Price"/>:</b>
                                <span class="star star_b0 star_h0 reference_price"></span>
                                <p class="error_info"></p>
                            </li>
                            <li>
                                <b><s:text name="comment_write.Ease_Of_Use"/>:</b>
                                <span class="star star_b0 star_h0 ease_of_use"></span>
                                <p class="error_info"></p>
                            </li>
                            <li>
                                <b><s:text name="comment_write.Work_Quality"/>:</b>
                                <span class="star star_b0 star_h0 build_quality"></span>
                                <p class="error_info"></p>
                            </li>
                            <li>
                                <b><s:text name="comment_write.Usefulness"/>:</b>
                                <span class="star star_b0 star_h0 usefulness"></span>
                                <p class="error_info "></p>
                            </li>
                            <li>
                                <b><s:text name="comment_write.Overview"/>:</b>
                                <span class="star star_b0 star_h0 overall_rating"></span>
                                <p class="error_info"></p>
                            </li>
                        </ul>

                        <label><s:text name="comment_write.Comments"/>:</label>
                        <div>
                    <textarea name="Content" class="review_content" id="review_content" maxlength="5000"
                              notnull=""></textarea>
                            <p class="error_info"></p>
                            <p><s:text name="comment_write.Number_Of_Remaining_Characters"/>:
                                <b id="review_content_char">5000</b> <s:text
                                        name="comment_write.Please_Do_Not_Exceed_5000_Characters"/></p>
                        </div>

                        <div>
                            <table border="0">
                                <tbody>
                                <tr>
                                    <td><input type="file" class="file"></td>
                                </tr>
                                <tr>
                                    <td><input type="file" class="file"></td>
                                </tr>
                                <tr>
                                    <td><input type="file" class="file"></td>
                                </tr>
                                </tbody>
                            </table>
                            <p class="upfile_condition"><s:text name="comment_write.Please_Files"/></p>
                        </div>
                        <input type="hidden" name="images" id="images" value="">
                        <input type="button" class="button" id="Submit" value="<s:text name="Global.Submit"/>">
                        <input type="hidden" name="id" value="${commentViewPageView.pdtProduct.id}">
                    </form>
                </s:else>
                <!-- 购买后出现评论框 end  -->
            </div>
            <div class="right fr">
                <h2 class="info_title"><s:text name="Global.Write_Evaluation"/></h2>
                <div class="goods_view">
                    <dl class="clearfix">
                        <dt class="fl">
                            <a class="pic_box"
                               href="/home/pdt_PdtProduct_gtProductsInfo?id=${commentViewPageView.pdtProduct.id}"
                               title="${commentViewPageView.pdtProduct.name}">
                                <img src="${envConfig.imageBaseUrl}${commentViewPageView.pdtProduct.image}?x-oss-process=image/resize,m_fixed,h_240,w_240"
                                     alt="${commentViewPageView.pdtProduct.name}">
                                <span></span>
                            </a>
                        </dt>
                        <dd>
                            <p class="name">
                                <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${commentViewPageView.pdtProduct.id}"
                                   title="${commentViewPageView.pdtProduct.name}">${commentViewPageView.pdtProduct.name}</a>
                            </p>
                            <p class="price">
                                <em class="currency_data FontColor">${env.currency.symbols}</em>
                                <span class="price_data FontColor"
                                      data="${commentViewPageView.pdtProduct.price}">${commentViewPageView.pdtProduct.price}</span>
                            </p>
                        </dd>
                    </dl>
                    <div class="clearfix">
                        <p class="rating"><s:text name="Global.Average_Evaluation"/>:</p>
                        <span class="star star_b${commentViewPageView.productAvg}"></span>
                        <%-- <strong class="score">${commentViewPageView.productAvg}</strong> --%>
                        <a href="/home/pdt_PdtProduct_viewComments?id=${commentViewPageView.pdtProduct.id}"
                           class="nums">(${commentViewPageView.total} <s:text name="comment_write.Comment"/>)</a>
                    </div>
                </div>
            </div>
            <div class="blank12"></div>
        </div>
        <div class="blank12"></div>
    </div>
    <index-bottom></index-bottom>
</div>
<%--<jsp:include page="template/new-foot.jsp"></jsp:include>--%>

<div id="hj_top" style="opacity: 0;">
    <img src="./static/images/hj_top.png">
</div>
<script type='text/javascript' src='/home/static/js/review.js'></script>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el: '#main'
    })
</script>
</body>

</html>
