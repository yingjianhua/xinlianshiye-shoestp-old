<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title><c:if test="${supView.webSizeTitle !=''}">
        ${supView.webSizeTitle}
    </c:if>
        <c:if test="${supView.webSizeTitle ==''}">
            An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
        </c:if></title>
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
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <!-- 轮播插件 -->
    <script type="text/javascript" src="./static/js/jquery.SuperSlide.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <script src="./static/js/layer.js" type="text/javascript"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>
    <style type="text/css">
        /* 这个页面用这个背景颜色 */
        body {
            background-color: #fff;
        }
    </style>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/shop-header.jsp" %>


<!-- 以下div为防止底部公用部分超出1200px后隐藏 -->
<div>
    <index-top></index-top>
    <div class="wide">
        <div class="procurement-head">
            <div class="wide clearfloat">
                <!-- 头部 -->
                <div class="procurement-head-left">
                    <ul class="clearfloat">
                        <li><s:text name="Global.Joint_Procurement"/></li>
                        <c:if test="${not empty unionList && fn:length(unionList) > 0}">
                            <li class="fca8" id="actTitle">(<%-- ${prm.title} --%>)</li>
                            <li style="color: #11d702;" id="actState"></li>
                        </c:if>
                        <!-- 正在进行 -->
                        <%-- <c:choose>
                           <c:when test="${prm.status == 1}">
                               <li style="color: #11d702;"><s:text name="About_To_Begin"/></li>
                           </c:when>
                           <c:when test="${prm.status == 2}">
                               <li style="color: #11d702;"><s:text name="groupPurchase.Processing"/></li>
                           </c:when>
                           <c:when test="${prm.status == 3}">
                               <li style="color: #11d702;"><s:text name="Coming_To_An_End"/></li>
                           </c:when>
                           <c:when test="${prm.status == 4}">
                               <li style="color: #11d702;"><s:text name="Ended"/></li>
                           </c:when>
                       </c:choose> --%>
                        <!--  <li style="color: #11d702;">Underway</li> -->
                        <!-- 即将开始 -->
                        <!-- <li style="color: #db7700;;">Begin in a minute</li> -->
                        <!-- 已经结束 -->
                        <!-- <li style="color: #db7700;">It's already over</li> -->
                    </ul>
                </div>
                <div class="procurement-head-right">
                    <span class="fca8"><s:text name="groupPurchase.Activity_Selection"/></span>
                    <select name="" id="activityList">
                        <c:if test="${not empty unionList && fn:length(unionList) > 0}">
                            <c:forEach items="${unionList}" var="union">
                                <%-- <c:if test="${union.pkey == prm.pkey}">
                                      <option value="${union.pkey}" selected="selected">${union.title}</option>
                                  </c:if>
                                <c:if test="${union.pkey != prm.pkey}"> --%>
                                <option value="${union.pkey}" state="${union.status}"
                                        data='{"title":"${union.title}","state":"${union.status}","pkey":"${union.pkey}"}'>${union.title}</option>
                                <%-- </c:if> --%>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty unionList || fn:length(unionList) < 0}">
                            <option><s:text name="groupPurchase.No_Activity"/></option>
                        </c:if>
                    </select>
                    <ul>
                        <li><a onclick="chooseThisTime(1)"><s:text name="groupPurchase.Processing"/></a>
                        </li>
                        <li><a onclick="chooseThisTime(2)"><s:text name="groupPurchase.Latest"/></a>
                        </li>
                        <li><a onclick="chooseThisTime(3)"><s:text name="groupPurchase.Other"/></a></li>
                        <!--  class="fca8" -->
                    </ul>
                </div>
            </div>
            <!-- 头部 - end -->
        </div>
        <c:if test="${not empty unionList && fn:length(unionList) > 0}">
            <!-- 分类 排序 begin -->
            <div class="procurement-classify">
                <div class="wide">
                    <div class="classify">
                        <span onclick='chooseThisCat(-1)' data="-1"><s:text name="All_Categories"/></span>
                        <c:forEach items="${catList}" var="cat">
                    <span style="margin:16px 15px 0 0" onclick='chooseThisCat(${cat.id})'
                          data="${cat.id}">${cat.name}</span>
                        </c:forEach>
                    </div>
                    <div class="paixu">
                        <a onclick="chooseThisSort(1)">
                            <em><s:text name="Global.Price"/></em>
                            <!-- 默认排序 -->
                            <i class="sort_icon_arrow"></i>
                            <!-- 升序 -->
                            <!-- <i class="sort_icon_arrow_up"></i> -->
                            <!-- 降序 -->
                            <!-- <i class="sort_icon_arrow_down"></i> -->
                        </a>
                        <a onclick="chooseThisSort(2)">
                            <em><s:text name="Customer_Evaluation"/> </em>
                            <!-- 默认排序 -->
                            <i class="sort_icon_arrow"></i>
                            <!-- 升序 -->
                            <!-- <i class="sort_icon_arrow_up"></i> -->
                            <!-- 降序 -->
                            <!-- <i class="sort_icon_arrow_down"></i> -->
                        </a>
                        <a onclick="chooseThisSort(3)">
                            <em><s:text name="products.Most_Popular"/></em>
                            <!-- 默认排序 -->
                            <i class="sort_icon_arrow"></i>
                            <!-- 升序 -->
                            <!-- <i class="sort_icon_arrow_up"></i> -->
                            <!-- 降序 -->
                            <!-- <i class="sort_icon_arrow_down"></i> -->
                        </a>
                    </div>
                </div>
            </div>
            <!-- 没有商品时显示 -->
            <div style="border-top: 1px dotted #ccc;text-align: center;display:none;" id="noGoods">
                <p style="padding: 215px 0;text-align: center;display:  inline-block;">
                    <em style="width: 20px;height: 1px;background-color: #000;display: block;float: left;margin-top: 8px;margin-right: 20px;"></em>
                    <s:text name="beselected"/>
                    <em style="width: 20px;height: 1px;background-color: #000;display: block;float: right;margin-top: 8px;margin-left: 20px;"></em>
                </p>
            </div>
            <!-- 分类 排序 end -->

            <!-- 商品列表 begin -->
            <div class="procurement-goodsList">
                <div class="wide clearfloat" id="proList">


                    <div class="blank20"></div>
                    <!-- 分页 -->
                        <%-- <div id="procurement_page">
                          <li class="page-first">
                            <font class="page_noclick">
                              <em class="icon_page_prev"></em>
                            </font>
                          </li>
                          <li class="page_last">
                            <a class="page_button">
                              <em class="icon_page_next"></em>
                            </a>
                            </li>
                        </div> --%>
                    <ul id="turn_page" style="margin-bottom: 28px;">


                    </ul>
                </div>
            </div>
        </c:if>
        <c:if test="${empty unionList || fn:length(unionList) <= 0}">
            <!-- 没有活动时显示 -->
            <div style="border-top: 1px dotted #ccc;text-align: center;" id="noActivity">
                <p style="padding: 215px 0;text-align: center;display:  inline-block;">
                    <em style="width: 20px;height: 1px;background-color: #000;display: block;float: left;margin-top: 8px;margin-right: 20px;"></em>
                    <s:text name="groupPurchase.No_Activity"/>
                    <em style="width: 20px;height: 1px;background-color: #000;display: block;float: right;margin-top: 8px;margin-left: 20px;"></em>
                </p>
            </div>
        </c:if>
        <!-- 商品列表 end -->

        <%--<%@ include file="/home/template/new-foot.jsp" %>--%>
        <index-bottom></index-bottom>
    </div>
    <script type="text/javascript">
        var pkey;
        var page = 1;
        var category = -1;
        var sort = -1;
        var type = 1;
        var allPage = 0;
        var _self = this;

        window.onload = function () {
            var opting = $("#activityList option[state=2]");
            if (opting.length == 0) {
                opting = $("#activityList [value]:eq(0)");
            }
            category = getParam("cat", -1)
            getActInfo(opting)
        }

        function chooseThisTime(time) {
            if ($("#activityList option:eq(0)").attr("state") == undefined) {
                layer.msg('<s:text name="groupPurchase.No_Activity"/>', {icon: 2});
                return;
            }
            var opting;
            switch (time) {
                case 1:
                    opting = $("#activityList option[state=2]");
                    break;
                case 2:
                    opting = $("#activityList option[value=" + _self.pkey + "]").prev();
                    break;
                case 3:
                    opting = $("#activityList option[value=" + _self.pkey + "]").next();
                    break;
            }
            if (opting.length == 0) {
                layer.msg('<s:text name="groupPurchase.No_Activity"/>', {icon: 2});
                return;
            }
            getActInfo(opting)
        }

        function getActInfo(opt) {
            $(opt).attr("selected", "selected")
            var data = JSON.parse($(opt).attr("data"));
            $("#actTitle").html("(" + data.title + ")");
            switch (Number(data.state)) {
                case 1:
                    $("#actState").html('<s:text name="About_To_Begin"/>');
                    break;
                case 2:
                    $("#actState").html('<s:text name="groupPurchase.Processing"/>');
                    break;
                case 3:
                    $("#actState").html('<s:text name="Coming_To_An_End"/>');
                    break;
                case 4:
                    $("#actState").html('<s:text name="Ended"/>');
                    break;
            }
            _self.pkey = Number(data.pkey)
            getData();
        }

        $("#activityList").on("change", function () {
            _self.pkey = $(this).val();
            var options = $(this).children();
            for (var i = 0; i < options.length; i++) {
                var data = JSON.parse($(options[i]).attr("data"));
                if (data.pkey == _self.pkey) {
                    $("#actTitle").html("(" + data.title + ")");
                    switch (Number(data.state)) {
                        case 1:
                            $("#actState").html('<s:text name="About_To_Begin"/>');
                            break;
                        case 2:
                            $("#actState").html('<s:text name="groupPurchase.Processing"/>');
                            break;
                        case 3:
                            $("#actState").html('<s:text name="Coming_To_An_End"/>');
                            break;
                        case 4:
                            $("#actState").html('<s:text name="Ended"/>');
                            break;
                    }
                }
            }
            getData();
        })

        function chooseThisCat(cat) {
            var span = $(".classify span");
            for (var i = 0; i < span.length; i++) {
                var data = $(span[i]).attr("data");
                if (data == cat) {
                    $(span).removeClass("classifyActive");
                    $(span[i]).addClass("classifyActive");
                }
            }
            _self.category = cat;
            _self.page = 1;
            getData();
        }

        function chooseThisSort(sort) {
            switch (_self.type) {
                case 1:
                    _self.type = 0;
                    break;
                case 0:
                    _self.type = 1;
                    break;
            }
            _self.sort = sort;
            getData();
        }

        function chooseThisPage(page) {
            _self.page = page;
            getData();
        }

        function getData() {
            var param = {
                "id": _self.pkey,
                "page": _self.page,
                "limit": 8,
                "category": _self.category,
                "sort": _self.sort,
                "type": _self.type
            };
            $.ajax({
                url: '/home/prm_PrmGroupPurchase_getActProduct',
                type: 'post',
                data: param,
                dataType: 'JSON',
                success: function (data) {
                    if (data.items.length == 0) {
                        $("#noGoods").show();
                        $(".procurement-goodsList").hide();
                        return;
                    } else {
                        $("#noGoods").hide();
                        $(".procurement-goodsList").show();
                    }
                    renderProduct(data.items);
                    renderPage(data.page, data.pageAll);
                    _self.allPage = data.pageAll;
                }
            })
        }

        function renderPage(nowPage, allPage) {
            var frontFlag = false;
            var backFlag = false;
            var pageShow = '<li class="page-first" onclick="lastPage()">' +
                '<a class="page_button">' +
                '<em class="icon_page_prev"></em>' +
                '</a>' +
                '</li>';
            for (var i = 1; i <= allPage; i++) {
                if (nowPage == i) {
                    pageShow += '<li><font class="page_item_current" onclick="chooseThisPage(' + i + ')">'
                        + i + '</font></li>';
                } else if (i > 1 && i < nowPage - 2) {
                    if (frontFlag == true) continue;
                    pageShow += '<li><a>...</a></li>';
                    frontFlag = true;
                } else if (i > nowPage + 2 && i < allPage) {
                    if (backFlag == true) continue;
                    pageShow += '<li><a>...</a></li>';
                    backFlag = true;
                } else {
                    pageShow += '<li><a onclick="chooseThisPage(' + i + ')">' + i + '</a></li>';
                }

            }
            pageShow += '<li class="page_last" onclick="nextPage()">' +
                '<a class="page_button">' +
                '<em class="icon_page_next"></em>' +
                '</a>' +
                '</li>';

            $("#turn_page").html(pageShow);
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

        function renderProduct(items) {
            $(".procurement-item").remove();
            var div = '';
            $.each(items, function (i, val) {
                div = div + '<div class="procurement-item">' +
                    '<a href="/home/prm_PrmGroupPurchase_getGroupPdt?pkey=' + val.id
                    + '" target="_blank">' +
                    '<div class="procurement-img" style="height:auto;">' +
                    '<img src="' + getProPic(val.image) + '"/>' +
                    '</div>' +
                    '<div class="procurement-info">' +
                    val.name +
                    '</div>' +
                    '</a>' +
                    /*      ' <div class="procurement-price">' +
                         // '<div class="line1">' +
                        //  '<span>MOQ:' + val.count + '</span>' +
                          //'<span class="fr">${env.currency.symbols} '+val.sourcePrice+'</span>'+
             // '</div>' +
              '<div class="line2">' +
              '<span class="star ' + judgeReview(val.reviewRating) + '"></span>(<span>'
              + val.reviewCount + '</span>)' +
              '<span class="fr" style="font-size: 16px;color: #db0000;">${env.currency.symbols} '
              + val.curPrice + '</span>' +
              '</div>' +
              '</div>' +*/
                    '<div class="procurement-button">' +
                    '<span class="bga8" onclick="addThisToFavorite(' + val.productId
                    + ')" style=font-size:11px>' + lang_obj.mobile.Collection + '</span>' +
                    '<span class="bgdb" onclick="addThisToCart(' + val.productId + ');">'
                    + lang_obj.mobile.Add_To_Shopping_Cart + '</span>' +
                    '</div>' +
                    '</div>';
            });
            $("#proList").prepend(div);
        }

        /*评分判断*/
        function judgeReview(score) {
            score = Math.ceil(score);
            if (score == 0) {
                return "star_b0";
            }
            if (score == 1) {
                return "star_b1";
            }
            if (score == 2) {
                return "star_b2";
            }
            if (score == 3) {
                return "star_b3";
            }
            if (score == 4) {
                return "star_b4";
            }
            if (score == 5) {
                return "star_b5";
            }
        }

        /*获取产品图片*/
        function getProPic(str) {
            if (str == '') {
                return '/home/static/images/noImage.png';
            } else {
                if (str.indexOf(",") != -1) {
                    return '${envConfig.imageBaseUrl}' + str.split(",")[0];
                } else {
                    return '${envConfig.imageBaseUrl}' + str;
                }
            }
        }

        //活动状态
        function toGetStatus(state) {
            var status = '';
            switch (state) {
                case 0:
                    status = "未开始";
                    break;
                case 1:
                    status = "即将开始";
                    break;
                case 2:
                    status = "进行中";
                    break;
                case 3:
                    status = "即将结束";
                    break;
                case 4:
                    status = "已结束";
                    break;

            }
            return status;
        }

        function addThisToCart(data) {
            var i = parseInt($(".cart_count").text())
            $.ajax({
                url: '/home/usr_UsrCart_boughtPro',
                type: 'post',
                data: {"pdtPkey": data},
                dataType: 'json',
                success: function (data) {
                    if (data.success == true) {
                        layer.msg(lang_obj.goods_info.Added_successfully, {icon: 1});
                        if (data.catPkeys != "" && data.catPkeys != null) {
                            $(".cart_count").text(i + 1)
                        }
                    } else if (data.ret == -1) {
                        $(".SignInButton").click();
                    } else {
                        layer.msg(data.msg, function () {
                        });
                    }
                }
            })
        }

        function addThisToFavorite(data) {
            var i = parseInt($(".fav_count").text())

            $.ajax({
                url: '/home/usr_UsrFavorites_addFavorite',
                type: 'post',
                data: {"pdtPkey": data, "addType": 1},
                dataType: 'json',
                success: function (data) {
                    if (data.ret == -1) {
                        $(".SignInButton").click();
                        return;
                    }
                    if (data.type == 0) {
                        layer.msg(lang_obj.mobile.Collection1, {icon: 1});
                        $(".fav_count").text(i + 1)
                    } else if (data.type == 1) {
                        layer.msg(lang_obj.mobile.Collection2, {icon: 1});
                        $(".fav_count").text(i - 1)
                    } else {
                        layer.msg(lang_obj.mobile.Collection3, {icon: 2});
                    }
                }
            })
        }

        function gup(name) {
            var regexS = "[\\?&]" + name + "=([^&#]*)";  //匹配name参数对
            var regex = new RegExp(regexS);
            var results = regex.exec(window.location.href);//过滤超链接
            if (results == null) {
                return "";
            } else {
                return results[1];
            }
        }

        function mark() {
            var id = gup('category')
            if (id == null || id == "") {
                $("#0").addClass("classifyActive");
            } else {
                $("#" + id + "").addClass('classifyActive')
            }
        }

        function getParam(str) {
            var url = window.location.href;
            var param = {};
            var paramStr = url.substring(url.indexOf("?") + 1);
            var paramArr = paramStr.split("&");
            for (var i = 0; i < paramArr.length; i++) {
                var params = paramArr[i].split("=");
                param[params[0]] = params[1];
            }
            return param[str];
        }
    </script>
    ${supView.traceCode}
    <script>
        new Vue({
            el:"#main"
        })
    </script>
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>
</body>

</html>
