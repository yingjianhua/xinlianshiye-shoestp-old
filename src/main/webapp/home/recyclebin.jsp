<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0024)https://www.shoestp.com/ -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->
    <script async="" src="./static/js/gtm.js" type="text/javascript">
    </script>

    <!-- End Google Tag Manager -->
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js">
    </script>

    <style type="text/css">
        #turn_page {
            float: left;
            /* text-align: center; */
            height: 35px;
            line-height: 35px;
            clear: both;
        }

        .pro_item {
            position: relative;
        }

        .pro_item input {
            position: absolute;
            top: 10px;
            right: 10px;
            /*width:10px;
            height:10px;*/
            border: 1px solid #000;
            cursor: pointer;
            z-index: 1;
            zoom: 140%;
        }

        .lib_user_title h1 {
            float: left;
        }

        .lib_user_title ul li {
            float: left;
            font-weight: bold;
            font-size: 14px;
            margin: 0 6px;
        }

        .lib_user_title ul li.on a {
            color: #0050a8;
        }

        .xmg-myfcaozuonav {
            float: right;
            height: 35px;
            line-height: 35px;
        }

        .xmg-myfcaozuonav a {
            float: left;
            height: 35px;
            padding: 0 14px;
            line-height: 35px;
            margin-left: 20px;
            background: #666;
            color: #fff;
            font-size: 20px;
            border-radius: 5px;
        }

        .xmg-myfcaozuonav input {
            float: left;
            /* margin-right: 30px; */
            margin-top: 6px;
            /*width:10px;
            height:10px;*/
            border: 1px solid #000;
            cursor: pointer;
            zoom: 140%;
        }

        #lib_user_favorite .pro_item .pro_view {
            padding-top: 8px;
            padding-bottom: 8px;
            text-align: center;
        }

        #lib_user_favorite .pro_item .pro_view {
            padding-top: 12px;
            padding-bottom: 10px;
            text-align: center;
        }

        #lib_user_favorite .pro_item .pro_btn {
            margin: 0 10px;
            /*width: 70px;*/
            padding: 3px;
            display: block;
            /* float: left; */
            display: inline-block;
            height: 20px;
            line-height: 20px;
            text-align: center;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
            background: #0050a8;
            font-size: 12px;
        }

        #lib_user_favorite .pro_item .pro_btn2 {
            background: #db0000;
        }
    </style>
    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">
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
                    <a href="/home/usr_UsrFavorites_myfavorite" title="My Favorite"><s:text name="my_fav"/>
                        <i></i>
                    </a>
                </li>
            </ul>
        </div>
        <%@ include file="/home/template/account/lib-user-menu.jsp" %>
        <div id="lib_user_main">
            <div class="lib_user_title">
                <h1><s:text name="recycleBin"/></h1>
            </div>
            <div id="lib_user_favorite" class="index_pro_list clearfix">

                <div id="pdtList">
                    <c:forEach items="${favoritesViews}" var="favorite">
                        <dl class="pro_item fl">
                            <input type="checkbox" name="ckOne" onclick="checkOne()" data="${favorite.id}"/>
                            <dt>
                                <a class="pic_box" href="" title="${favorite.name}"
                                   target="_blank">
                                    <img src="${envConfig.imageBaseUrl}${favorite.img}">
                                    <span></span>
                                </a>
                            </dt>
                            <dd class="pro_name">
                                <a href="" title="${favorite.name}" target="_blank">${favorite.name}</a>
                            </dd>
                            <dd class="pro_price">
                                <em class="currency_data FontColor">${env.currency.symbols}</em>
                                <span class="price_data FontColor" data="${favorite.amt}"
                                      keyid="3413">${favorite.amt}</span>
                            </dd>

                            <dd class="pro_view">
                                <a class="pro_btn" onclick="deleteThis(${favorite.id})"><s:text name="cart.remove"/></a>
                                <a class="pro_btn pro_btn2" onclick="restore(${favorite.id})"><s:text
                                        name="recovery"/></a>
                            </dd>
                        </dl>
                    </c:forEach>
                </div>

                <div class="blank20"></div>

                <%--<div id="turn_page">--%>
                <%--<c:if test="${fn:length(favoritesViews) > 0}">--%>
                <%--<li id="front" style="width: 98px;">--%>
                <%--<font class="page_noclick">--%>
                <%--<em class="icon_page_prev"></em><s:text name="previous"/></font>--%>
                <%--</li>--%>
                <%--<c:forEach begin="1" end="${pageCount}" var="page">--%>
                <%--<c:if test="${pageNumber == page}">--%>
                <%--<li>--%>
                <%--<font class="page_item_current">${page}</font>--%>
                <%--</li>--%>
                <%--</c:if>--%>
                <%--<c:if test="${pageNumber != page}">--%>
                <%--<li onclick="toThisPage(${page})">--%>
                <%--<font>${page}</font>--%>
                <%--</li>--%>
                <%--</c:if>--%>
                <%--</c:forEach>--%>
                <%--<li id="back" class="page_last" style="width: 98px;">--%>
                <%--<font class="page_noclick"><s:text name="next"/>--%>
                <%--<em class="icon_page_next"></em>--%>
                <%--</font>--%>
                <%--</li>--%>
                <%--</c:if>--%>
                <%--</div>--%>
                <c:if test="${fn:length(favoritesViews) > 0}">
                    <div id="turn_page">
                        <li id="front" style="width:118px;">
                            <font class="page_noclick" style="<c:if test='${pageNumber != 1}'>cursor:pointer;</c:if>">
                                <em class="icon_page_prev"></em><s:text name="previous"/></font>
                        </li>
                        <c:choose>
                            <c:when test="${pageCount <= 6}">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="${pageCount}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="begin" value="${pageNumber - 1}"/>
                                <c:set var="end" value="${pageNumber + 3}"/>
                                <c:if test="${begin -1 <= 0}">
                                    <c:set var="begin" value="1"/>
                                    <c:set var="end" value="6"/>
                                </c:if>
                                <c:if test="${end > pageCount}">
                                    <c:set var="begin" value="${pageCount - 5}"/>
                                    <c:set var="end" value="${pageCount}"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="i" begin="${begin}" end="${end}">
                            <c:choose>
                                <c:when test="${i == pageNumber}">
                                    <li>
                                        <font class="page_item_current">${i}</font>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li onclick="toThisPage(${i})">
                                        <font>${i}</font>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <!-- <li>
                            <font class="page_item_current">1</font>
                        </li> -->
                        <li id="back" class="page_last" style="width:98px;">
                            <font class="page_noclick"
                                  style="<c:if test='${pageNumber != pageCount}'>cursor:pointer;</c:if>"><s:text
                                    name="next"/>
                                <em class="icon_page_next"></em>
                            </font>
                        </li>

                    </div>
                    <div class="xmg-myfcaozuonav">
                        <input type="checkbox" name="ckAll" value="全选/反选" onclick="checkAll()" id="all"/>
                        <a href="javascript:;" id="empty"><s:text name="Emptying"/></a>
                        <a href="javascript:;" id="addToCart" style="background:#db0000;"><s:text
                                name="addToCartTo"/></a>
                    </div>
                </c:if>
                <%--    <div class="xmg-myfcaozuonav">
                       <input type="checkbox" name="ckAll" value="全选/反选" onclick="checkAll()" id="all"/>
                       <a href="javascript:;" id="empty"><s:text name="emptyRecycleBin"/></a>
                   </div>
    --%>
                <!-- 有商品时候 end -->
            </div>
        </div>
    </div>
</div>

<%@ include file="/home/template/new-foot.jsp" %>
<div id="hj_top" style="opacity: 0;">
    <img src="./static/images/hj_top.png">
</div>

<style>
    .xmg-myf-tc {
        display: none;
    }

    .xmg-myf-tc .xmg-hsbg {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: #000;
        opacity: 0.5;
        z-index: 998;
    }

    .xmg-myf-tc .xmg-box {
        position: fixed;
        top: 50%;
        left: 50%;
        width: 320px;
        height: 130px;
        background: #fff;
        z-index: 999;
        margin: -65px 0 0 -160px;
        border-radius: 6px;
        box-shadow: 0px 0px 8px 0px rgba(0, 0, 0, 0.5);
        overflow: hidden;
    }

    .xmg-myf-tc .xmg-box .xmg-close {
        position: absolute;
        right: 10px;
        top: 0px;
        font-family: Arial;
        font-size: 28px;
        color: #999;
    }

    .xmg-myf-tc .xmg-box .xmg-close:hover {
        color: #666;
        text-decoration: none;
    }

    .xmg-myf-tc .xmg-box h1 {
        font-size: 16px;
        height: 32px;
        color: #666;
        font-weight: bold;
        text-align: center;
        padding: 20px 0;
    }

    .xmg-myf-tc .xmg-box .xmg-bottom {
        height: 58px;
        line-height: 58px;
        background: #eeeeee;
        text-align: center;
    }

    .xmg-myf-tc .xmg-box .xmg-bottom a {
        display: inline-block;
        /*  width: 66px; */
        padding: 0 5px;
        height: 26px;
        border-radius: 6px;
        text-align: center;
        line-height: 26px;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        margin: 0 10px;
        background-image: -moz-linear-gradient(0deg, rgb(209, 77, 71) 0%, rgb(244, 93, 86) 100%);
        background-image: -webkit-linear-gradient(0deg, rgb(209, 77, 71) 0%, rgb(244, 93, 86) 100%);
        background-image: -ms-linear-gradient(0deg, rgb(209, 77, 71) 0%, rgb(244, 93, 86) 100%);
    }

</style>


<div class="xmg-myf-tc xmg-myf-tc111">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <a class="xmg-close" href="javascript:;">×</a>
        <h1>The goods have been<br/> restored to the favorites!</h1>
        <div class="xmg-bottom">
            <a href="javascript:;">Sure</a>
            <a href="javascript:;" class="cancel">Cancel</a>
        </div>
    </div>
</div>

<div class="xmg-myf-tc xmg-myf-tc222">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <a class="xmg-close" href="javascript:;">×</a>
        <h1>The completely deleted goods<br/> can't be retrieved again!</h1>
        <div class="xmg-bottom">
            <a href="javascript:;">Sure</a>
            <a href="javascript:;" class="cancel">Cancel</a>
        </div>
    </div>
</div>

<div class="xmg-myf-tc xmg-myf-tc333">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <a class="xmg-close">×</a>
        <h1><s:text name="determineEmptying"/></h1>
        <div class="xmg-bottom">
            <a href="javascript:;" class="ok"><s:text name="cpop_t4"/></a>
            <a href="javascript:;" class="cancel"><s:text name="user.cancel"/></a>
        </div>
    </div>
</div>

<script type="text/javascript">
    function deleteThis(id) {
        layer.confirm('<s:text name="removeStyle"/>', {icon: 5, title: '<s:text name="tips"/>'}, function (index) {
            $.ajax({
                url: '/home/usr_UsrFavorites_delFavorite',
                type: 'post',
                data: {"pkey": id},
                dataType: 'json',
                success: function (data) {
                    if (data.ret == 1) {
                        layer.msg(lang_obj.cart.Delete_Success, {icon: 1, time: 2000}, function () {
                            location.reload();
                        })
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 2000});
                    }
                }
            })
            layer.close(index);
        });
    }

    function restore(id) {
        $.ajax({
            url: '/home/usr_UsrFavorites_restoreFavorite',
            type: 'post',
            data: {"pkey": id},
            dataType: 'json',
            success: function (data) {
                if (data.ret == 1) {
                    layer.msg(lang_obj.mobile.Successful_recovery, {icon: 1, time: 2000}, function () {
                        location.reload();
                    })
                } else {
                    layer.msg(data.msg, {icon: 2, time: 2000});
                }
            }
        })
    }

    function chooseChecked() {
        var ckOnes = document.getElementsByName("ckOne");
        var pkeys = '';
        for (var i = 0; i < ckOnes.length; i++) {
            if (ckOnes[i].checked == true) {
                if (pkeys == '') {
                    pkeys += $(ckOnes[i]).attr("data");
                } else {
                    pkeys += "," + $(ckOnes[i]).attr("data");

                }
            }
        }

        if (pkeys == "" || typeof(pkeys) == "undefined") {
            for (var i = 0; i < ckOnes.length; i++) {
                if (pkeys == '') {
                    pkeys += $(ckOnes[i]).attr("data");
                } else {
                    pkeys += "," + $(ckOnes[i]).attr("data");

                }
            }
        }
        return pkeys;
    }

    $(".ok").on("click", function () {
        var pkeys = chooseChecked();
        $.ajax({
            url: '/home/usr_UsrFavorites_delFavorite',
            type: 'post',
            data: {"pkeys": pkeys},
            dataType: 'json',
            success: function (data) {
                $(".xmg-myf-tc333").hide();
                if (data.ret == 1) {
                    layer.msg(lang_obj.cart.Delete_Success, {icon: 1, time: 2000}, function () {
                        location.reload();
                    })
                } else {
                    layer.msg(data.msg, {icon: 2, time: 2000});
                }
            }
        })
    })

    $("#empty").click(function () {
        if (document.getElementById("all").checked) {
            $(".xmg-myf-tc333").show();
        } else {
            layer.msg(lang_obj.manage.sales.check_choose, {icon: 2, time: 2000});
        }


    })
    $(".xmg-myf-tc333 .xmg-box .xmg-close,.xmg-myf-tc333 .xmg-box .xmg-bottom a.cancel").click(function () {
        $(".xmg-myf-tc333").hide();
    })

    $(".xmg-myf-tc 333.xmg-box .xmg-close").click(function () {
        $(".xmg-myf-tc333").hide();
    })

    $("#lib_user_favorite .pro_item .pro_btn.recovery").click(function () {
        $(".xmg-myf-tc111").show();
    })
    $(".xmg-myf-tc111 .xmg-box .xmg-close,.xmg-myf-tc111 .xmg-box .xmg-bottom a.cancel").click(function () {
        $(".xmg-myf-tc111").hide();
    })

    $("#lib_user_favorite .pro_item .pro_btn.delete").click(function () {
        $(".xmg-myf-tc222").show();
    })
    $(".xmg-myf-tc222 .xmg-box .xmg-close,.xmg-myf-tc222 .xmg-box .xmg-bottom a.cancel").click(function () {
        $(".xmg-myf-tc222").hide();
    })


    function checkAll() {   //全选 和反选  以及 单选
        //1.获取全选/反选元素
        var all = document.getElementById("all");
        //2.查找列表中每一项的元素
        var ckOnes = document.getElementsByName("ckOne");
        for (var i = 0; i < ckOnes.length; i++) {
            ckOnes[i].checked = all.checked;//把全选框的状态循环赋给所有的单选框！！！！！！
        }
    }

    function toThisPage(page) {
        window.location.href = window.location.pathname + "?pageNumber=" + page;
    }

    $("#front").on("click", function () {
        var page = ${pageNumber};
        page--;
        if (page <= 0) {
            alert(lang_obj.mobile.First_page);
        } else {
            window.location.href = window.location.pathname + "?pageNumber=" + page;
        }
    })

    $("#back").on("click", function () {
        var page = ${pageNumber};
        page++;
        if (page > ${pageCount}) {
            alert(lang_obj.mobile.The_last_page);
        } else {
            window.location.href = window.location.pathname + "?pageNumber=" + page;
        }
    })


    function checkOne() {//单选
        //1.获取全选/反选元素
        var all = document.getElementById("all");
        //2.查找列表中每一项的元素
        var ckOnes = document.getElementsByName("ckOne");
        //定义一个变量，统计状态为选中的单选框的个数
        var count = 0;
        for (var i = 0; i < ckOnes.length; i++) {//循环各个单选
            var ckOne = ckOnes[i];
            //判断选中的
            if (ckOne.checked == true) {//循环得到单选的状态，如果单选状态为true的话，就让count++
                count++;//这里得到了到底有几个单选框被选中
            }
        }
        if (count == ckOnes.length) {//判断count  如果count的数量等于单选框的数量的话，也就是说每一个都选中了的话，就让全选框状态为true
            all.checked = true;
        } else {
            all.checked = false;//否则的话 全选框状态为 不选
        }
    }
</script>
<!-- End of LiveChat code -->

</body>

</html>
