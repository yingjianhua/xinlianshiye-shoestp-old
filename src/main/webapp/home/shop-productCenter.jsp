<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>
        <c:if test="${supView.webSizeTitle !=''}">
            ${supView.webSizeTitle}
        </c:if>
        <c:if test="${supView.webSizeTitle ==''}">
            An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
        </c:if>
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link href="./static/css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <!-- 新引入的css -->
    <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
    <!-- 轮播插件 -->
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/jquery.SuperSlide.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <script src="./static/js/layer.js" type="text/javascript"></script>
</head>

<body class="lang_en w_1200">
<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/new-header.jsp" %>
<%@ include file="/home/template/shop-header.jsp" %>
<c:if test="${supView.productPageOn==1}">
    <div class="clean">
        <c:if test="${supView.productPageDiy != null && supView.productPageDiy != ''}">
            ${supView.productPageDiy}
        </c:if>
    </div>
</c:if>
<div>
    <%--    <div class="clean">
           <c:if test="${supplier.productPageDiy != null && supplier.productPageDiy != ''}">
               ${supplier.productPageDiy}
           </c:if>
       </div> --%>
    <div class="wide">
        <div class="enterprise-info-wrap">
            <h3 class="enterprise-info-title clean">
                <s:text name="hottestItems"/>
                <div class="fr">
                    <ul class="tool-group">
                        <li class="tool-item">
                            <span class="tool-name"><s:text name="sort_by"/></span>
                            <div class="tool-content tool-drop-down-wrap">
                                <div class="tool-drop-down-btn">
                    <span class="select-text" id="sort">
                    		<s:text name="most_popular"/>
                    </span>
                                    <i class="icon icon-arrow-down"></i>
                                </div>

                                <div class="tool-drop-down-content">
                                    <ul>
                                        <li>
                                            <a href="javascript:chooseThisSort(0);"><s:text
                                                    name="most_popular"/></a>
                                        </li>
                                        <li>
                                            <a href="javascript:chooseThisSort(1);"><s:text
                                                    name="sales"/></a>
                                        </li>
                                        <li>
                                            <a href="javascript:chooseThisSort(2);"><s:text
                                                    name="fav"/></a>
                                        </li>
                                        <li>
                                            <a href="javascript:chooseThisSort(3);"><s:text
                                                    name="new"/></a>

                                        </li>
                                        <li>
                                            <a href="javascript:chooseThisSort(4);"><s:text
                                                    name="price"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>

                        <li class="tool-item">
                            <span class="tool-name"><s:text name="attr.category"/></span>
                            <div class="tool-content tool-drop-down-wrap">
                                <div class="tool-drop-down-btn">
                    <span class="select-text" id="category">
						<s:text name="allCategories"/>
					</span>
                                    <i class="icon icon-arrow-down"></i>
                                </div>

                                <div class="tool-drop-down-content">
                                    <ul class="category">
                                        <li><a onclick="chooseThisCat('')"><s:text
                                                name="allCategories"/></a></li>
                                        <c:forEach items="${topDiyCat}" var="cat">
                                            <li>
                                                <a onclick="chooseThisCat(${cat.pkey},'${cat.name}')">${cat.name}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </h3>

            <!-- 商品展示 -->
            <%--  <c:if test="${pageAll != 0}"> --%>
            <div class="goods-wrap" id="products">
            </div>
            <!-- 页面跳转 - 按钮组 -->
            <ul id="turn_page">
            </ul>
            <%--  </c:if> --%>
            <%--  <c:if test="${pageAll == 0}">

             </c:if> --%>
            <!-- 没有商品时显示 -->
            <div style="border-top: 1px dotted #ccc;text-align: center;display: none;" id="noGoods">
                <p style="padding: 215px 0;text-align: center;display:  inline-block;">
                    <em style="width: 20px;height: 1px;background-color: #000;display: block;float: left;margin-top: 8px;margin-right: 20px;"></em>
                    未发布此类产品
                    <em style="width: 20px;height: 1px;background-color: #000;display: block;float: right;margin-top: 8px;margin-left: 20px;"></em>
                </p>
            </div>
            <div class="blank25"></div>
        </div>
    </div>
</div>

<%@ include file="/home/template/new-foot.jsp" %>

<div align="center">
</div>
<script type="text/javascript">
    var pkey = '${supView.pkey}';
    var page = 1;
    var cated = -1;
    var rankingBasis = -1;
    var type = 1;
    var allPage = 0;
    window.onload = function () {
        getData();
    }

    function getData() {
        var param = {
            "pkey": pkey,
            "page": page,
            "limit": 8,
            "cated": cated,
            "rankingBasis": rankingBasis,
            "basis": type
        };
        var self = this;
        $.ajax({
            url: '/home/pdt_PdtProduct_getProductBySup',
            type: 'post',
            data: param,
            dataType: 'JSON',
            success: function (data) {
                if (data.items.length == 0) {
                    $("#products").html('');
                    $("#turn_page").html("");
                    $("#noGoods").show();
                } else {
                    $("#noGoods").hide();
                    renderProduct(data.items);
                    renderPage(data.page, data.pageAll);
                    self.allPage = data.pageAll;
                }
            }
        })
    }

    function chooseThisPage(page) {
        this.page = page;
        getData();
    }

    function chooseThisCat(cat, catName) {
        this.cated = cat;
        $("#category").text(catName);
        this.page = 1;
        getData();
    }

    function chooseThisSort(sort) {
        switch (type) {
            case 1:
                type = 0;
                break;
            case 0:
                type = 1;
                break;
        }
        this.rankingBasis = sort;
        var sortName = '<s:text name="most_popular"/>';
        switch (sort) {
            case 1:
                sortName = '<s:text name="sales"/>';
                break;
            case 2:
                sortName = '<s:text name="fav"/>';
                break;
            case 3:
                sortName = '<s:text name="new"/>';
                break;
            case 4:
                sortName = '<s:text name="price"/>';
                break;
            default:
                sortName = '<s:text name="most_popular"/>';
        }
        $("#sort").text(sortName)
        getData();
    }

    function renderPage(nowPage, allPage) {
        var frontFlag = false;
        var backFlag = false;
        var pageShow = '<li onclick="lastPage();" >' +
            '<a class="page_button" style="cursor:pointer;">&nbsp;' +
            '<em class="icon_page_prev" style="display: block;margin-top: -23px;margin-left:1px;"></em>'
            +
            '</a>' +
            '</li>';
        for (var i = 1; i <= allPage; i++) {
            if (nowPage == i) {
                pageShow += '<li class="page_number">' +
                    '<font class="page_item_current">' + i + '</font>' +
                    '</li>';
            } else if (i > 1 && i < nowPage - 2) {
                if (frontFlag == true) continue;
                pageShow += '<li class="page_number">' +
                    '<font class="page_item">...</font>' +
                    '</li>';
                frontFlag = true;
            } else if (i > nowPage + 2 && i < allPage) {
                if (backFlag == true) continue;
                pageShow += '<li class="page_number">' +
                    '<font class="page_item">...</font>' +
                    '</li>';
                backFlag = true;
            } else {
                pageShow += '<li class="page_number">' +
                    '<a onclick="chooseThisPage(' + i + ')" class="page_item">' + i + '</a>' +
                    '</li>';
            }

        }
        pageShow += '<li class="page_last" onclick="nextPage();" >' +
            '<a class="page_button" style="cursor:pointer;">&nbsp;' +
            '<em class="icon_page_next" style="display: block;margin-top: -23px;margin-left:1px;"></em>'
            +
            '</a>' +
            '</li>';

        $("#turn_page").html(pageShow);
    }

    function renderProduct(items) {
        $("#products").html('');
        $.each(items, function (i, val) {
            var div = '<div class="goods-box" style="width: 284px">' +
                '<div class="goods-item">' +
                '<div class="goods-pic pic_box">' +
                '<a href="/' + val.rewrite + '" title="' + val.pdt.name + '"' +
                'target="_blank">' +
                '<img src="' + getFirstPic(val.pdt.picture) + '" >' +
                '</a>' +
                '<span></span>' +
                '</div>' +
                '<h5 class="goods-title">' +
                '<a href="/' + val.rewrite + '" title="' + val.pdt.name + '"' +
                'target="_blank">' + val.pdt.name + '</a>' +
                '</h5>' +
                '<div class="goods-price">' +
                '${env.currency.symbols}' + val.pdt.curPrice + '</div>' +
                '<div class="btn btn-enter">' +
                '<a href="/' + val.rewrite
                + '" class="btn btn-enter" target="_blank"><s:text name="show_now"/></a>' +
                '</div>' +
                '</div>' +
                '</div>';
            $("#products").append(div);
        })
    }

    function getFirstPic(str) {
        if (str == '') {
            return '/home/static/images/noImage.png';
        } else {
            if (str.indexOf(",") != -1) {
                return '${envConfig.imageBaseUrl}' + str.split(",")[0]
                    + '?x-oss-process=image/resize,m_pad,h_256,w_256';
            } else {
                return '${envConfig.imageBaseUrl}' + str + '?x-oss-process=image/resize,m_pad,h_256,w_256';
            }
        }
    }

    function nextPage() {
        this.page = ++this.page;
        if (this.page > this.allPage) {
            this.page = --this.page;
            layer.msg(lang_obj.mobile.The_last_page);
            return;
        }
        getData();
    }

    function lastPage() {
        this.page = --this.page;
        if (page <= 0) {
            this.page = ++this.page;
            layer.msg(lang_obj.mobile.First_page);
            return;
        }
        getData();
    }
</script>
<script type="text/javascript">
    // 点击选择下拉框按钮，显示下拉内容
    $(".tool-group .tool-drop-down-btn").click(function () {
        $(".tool-group .tool-drop-down-content").fadeOut();
        $(".tool-group .tool-drop-down-btn").removeClass("active");
        $(this).toggleClass("active")
            .siblings(".tool-drop-down-content").fadeToggle();
    });
    // 获得点击事件
    $(document).click(function (e) {
        //console.log(6)
        var target = $(e.target);
        // 如果点击的是 选择下拉框中，则不干什么；如果点击的是其余地方，则将显示出来的下拉内容隐藏掉
        if (target.closest(".tool-drop-down-wrap").length !== 0) return;
        $(".tool-group .tool-drop-down-content").fadeOut();
        $(".tool-group .tool-drop-down-btn").removeClass("active");
        // e.stopPropagation();
    });
</script>

${supView.traceCode}
</body>

</html>
