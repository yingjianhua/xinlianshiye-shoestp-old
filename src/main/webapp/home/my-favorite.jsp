<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market--Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js">
    </script>
    <script type="text/javascript" src="./static/js/lang/en.js">
    </script>
    <script type="text/javascript" src="./static/js/main.js">
    </script>
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js" type="text/javascript">
    </script>
    <script src="./static/js/lazyload.min.js" type="text/javascript">
    </script>
    <script type="text/javascript">
        /*  $(function () {
             products_list_obj.init();
         }); */
    </script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <script src="./static/js/layer.js" type="text/javascript"></script>
    <!-- <script src="./static/js/saved_resource" type="text/javascript">
    </script> -->
    <style type="text/css">
        #turn_page {
            /* float: left; */
            /* text-align: center; */
            height: 35px;
            line-height: 35px;
            margin-bottom: 15px;
            clear: both;
        }

        #turn_page li {
            cursor: pointer;
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
            margin-right: 30px;
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
            width: 80px;
            margin: 0 5px;
            padding: 5px;
            /* float: left; */
            display: inline-block;
            height: 20px;
            line-height: 20px;
            white-space: nowrap;
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

        .bo {
            text-decoration: underline
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
                    <a href="/" title="Home"><s:text name="Global.Home"/>
                        <i></i>
                    </a>
                </li>
                <li class="crumb1">
                    <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="Global.My_Account"/>
                        <i></i>
                    </a>
                </li>
                <li class="crumb2 root">
                    <a href="/home/usr_UsrFavorites_myfavorite" title="My Favorite"><s:text name="Global.My_Favorites"/>
                        <i></i>
                    </a>
                </li>
            </ul>
        </div>
        <%@ include file="/home/template/account/lib-user-menu.jsp" %>
        <div id="lib_user_main">
            <div class="lib_user_title">
                <h1><s:text name="Global.My_Favorites"/></h1>
                <ul>
                    <c:forEach items="${catList}" var="cat">
                        <li>
                            <a onclick="toThisCategory(${cat.pkey})" id="se${cat.pkey}">${cat.name}</</a>
                        </li>
                    </c:forEach>
                    <li><a href="/home/usr_UsrFavorites_myRecycle"><s:text name="Global.Recycle_Bin"/></a></li>
                </ul>

            </div>
            <div id="lib_user_favorite" class="index_pro_list clearfix">
                <c:if test="${fn:length(favoritesViews) <= 0}">
                    <s:text name="my-favorite.No_Items"/>
                    <!-- <a href="">Let's go shopping now...</a> -->
                </c:if>
                <c:if test="${fn:length(favoritesViews) > 0}">
                    <c:forEach items="${favoritesViews}" var="favorite">
                        <dl class="pro_item fl">
                            <input type="checkbox" name="ckOne" onclick="checkOne()" data="${favorite.id}"/>
                            <dt>
                                <a class="pic_box"
                                   href="<c:if test='${favorite.groupLine == 0}'>/home/pdt_PdtProduct_gtProductsInfo?id=${favorite.pdtPkey }</c:if><c:if test='${favorite.groupLine != 0}'>/home/prm_PrmGroupPurchase_getGroupPdt?pkey=${favorite.groupLine}</c:if>"
                                   title="${favorite.name}" target="_blank">
                                    <img src="${envConfig.imageBaseUrl}${favorite.img}">
                                    <span></span>
                                </a>
                            </dt>
                            <dd class="pro_name">
                                <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${favorite.pdtPkey }"
                                   title="${favorite.name}" target="_blank">${favorite.name}</a>
                            </dd>
                            <dd class="pro_price">
                                <em class="currency_data FontColor">$</em>
                                <span class="price_data FontColor" data="${favorite.amt}">${favorite.amt}</span>
                            </dd>

                            <dd class="pro_view">
                                <a class="pro_btn" onclick="recycleThis(${favorite.id})"><s:text
                                        name="cart.remove"/></a>
                                <a class="pro_btn pro_btn2" onclick="addcart(${favorite.id})"><s:text
                                        name="my-inquiry-publish.View_Inquiry"/></a>
                            </dd>
                        </dl>
                    </c:forEach>
                </c:if>
                <div class="blank20"></div>
                <c:if test="${fn:length(favoritesViews) > 0}">
                    <div id="turn_page">
                        <li id="front">
                            <font class="page_noclick" style="<c:if test='${pageNumber != 1}'>cursor:pointer;</c:if>">
                                <em class="icon_page_prev"></em><s:text name="Global.Previous_Page"/></font>
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
                        <li id="back" class="page_last">
                            <font class="page_noclick"
                                  style="<c:if test='${pageNumber != pageCount}'>cursor:pointer;</c:if>"><s:text
                                    name="Global.Next_Page"/>
                                <em class="icon_page_next"></em>
                            </font>
                        </li>

                    </div>
                    <div class="xmg-myfcaozuonav">
                        <input type="checkbox" name="ckAll" value="全选/反选" onclick="checkAll()" id="all"/>
                        <a href="javascript:;" id="empty"><s:text name="my-favorite.Empty"/></a>
                        <a href="javascript:;" id="addToCart" style="background:#db0000;"><s:text
                                name="my-inquiry-publish.View_Inquiry"/></a>
                    </div>
                </c:if>
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
        width: 66px;
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

<div class="xmg-myf-tc xmg-myf-tc333">
    <div class="xmg-hsbg"></div>
    <div class="xmg-box">
        <a class="xmg-close">×</a>
        <h1><s:text name="my-favorite.Confirm_Delete"/></h1>
        <div class="xmg-bottom">
            <a href="javascript:;" class="ok" id="recAll"><s:text name="Global.Determine"/></a>
            <a href="javascript:;" class="cancel"><s:text name="Global.Cancel"/></a>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function () {
        mark()
        var ckAll = document.getElementById("all");
        if (ckAll) {
            ckAll.checked = false;
            var ckone = $("input[name=ckOne]");
            for (var i = 0; i < ckone.length; i++) {
                $(ckone[i]).attr("checked", false);
            }
        }
    }
    // $("#recAll").on("click",function(){
    // 	var pkeys = chooseChecked();
    // 	return;
    // 	$.ajax({
    // 	url:'/home/usr_UsrFavorites_recycleFavorite',
    // 	type:'post',
    // 	data:{"pkeys":pkeys},
    // 	dataType:'json',
    // 	success:function(data){
    // 		if(data.success == true){
    // 			location.reload();
    // 			$(".xmg-myf-tc").hide();
    // 		}else{
    //       layer.msg(data.msg, function(){});
    // 			$(".xmg-myf-tc").hide();
    // 		}
    // 	}
    // 	})
    // })

    $("#addToCart").on("click", function () {
        var pkeys = chooseChecked();

        var result = pkeys.split(",");
        for (var i = 0; i < result.length; i++) {
            addcart(result[i])
        }
        /*    	$.ajax({
                    url:'/home/usr_UsrFavorites_addSinglePdt',
                    type:'post',
                    data:{"favoritesPkeys":pkeys},
                    dataType:'json',
                    success:function(data){
                        if(data.success == true){
                            if(data.errCount > 0){
                                  layer.msg(data.errCount+"个产品还未发布规格", function(){});
                            }else{
                                  layer.msg('

















































        <%--<s:text name="my-favorite.Added_Successfully"/>', {icon: 1});--%>
    				}
    			}else{
            layer.msg(data.msg, function(){});
    			}
    		}
    	})*/
    })

    function recycleThis(id) {
        layer.confirm('<s:text name="my-favorite.Confirm_Delete"/>', {
            btn: ['<s:text name="Global.Determine"/>', '<s:text name="Global.Cancel"/>'],
            icon: 5,
            title: '<s:text name="Global.Tips"/>'
        }, function (index) {
            $.ajax({
                url: '/home/usr_UsrFavorites_recycleFavorite',
                type: 'post',
                data: {"pkey": id},
                dataType: 'json',
                success: function (data) {
                    if (data.ret == 1) {
                        layer.msg("<s:text name='Global.Successfully_Deleted'/>", {icon: 1, time: 2000}, function () {
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

    function addcart(id) {

        $.ajax({
            url: '/home/pdt_PdtConsultPdtList_add',
            type: 'post',
            data: {"product": id},
            dataType: 'json',
            success: function (data) {
                if (data.ret && data.ret == 1) {
                    layer.msg('<s:text name="my-inquiry-publish.View_Inquiry"/>', {icon: 1});
                } else {
                    layer.msg(getMessage(data.msg), {icon: 2, time: 2000});
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
        return pkeys;
    }


    $("#recAll").on("click", function () {
        var pkeys = chooseChecked();
        if (pkeys == "" || pkeys == undefined) {
            layer.msg("<s:text name='my-favorite.No_Product_Selected'/>", function () {
            });
            return;
        }
        $.ajax({
            url: '/home/usr_UsrFavorites_recycleFavorite',
            type: 'post',
            data: {"pkeys": pkeys},
            dataType: 'json',
            success: function (data) {
                if (data.ret == 1) {
                    $(".xmg-myf-tc").hide();
                    layer.msg("<s:text name='Global.Successfully_Deleted'/>", {icon: 1, time: 2000}, function () {
                        location.reload();
                    })
                } else {
                    layer.msg(data.msg, function () {
                    });
                    $(".xmg-myf-tc").hide();
                }
            }
        })
    })

    function toThisCategory(category) {
        window.location.href = window.location.pathname + "?category=" + category + "&pageNumber=${pageNumber}";
    }

    function toThisPage(page) {
        window.location.href = window.location.pathname + "?category=${category}&pageNumber=" + page;
    }

    function getPic(str) {
        return str.split(",")[0];
    }

    function checkAll() {   //全选 和反选  以及 单选
        //1.获取全选/反选元素
        var all = document.getElementById("all");
        //2.查找列表中每一项的元素
        var ckOnes = document.getElementsByName("ckOne");
        for (var i = 0; i < ckOnes.length; i++) {
            ckOnes[i].checked = all.checked;//把全选框的状态循环赋给所有的单选框！！！！！！
        }
        var favoritePkey = 0;
        var favoriteVer = 0;
        $.each($('input:checkbox:checked'), function () {
            if ($(this).val() != '<s:text name="my-favorite.Select_All"/>') {
                var list = $(this).val().split('/')
                if (favoritePkey == 0) {
                    favoritePkey = list[0];
                    favoriteVer = list[1]
                } else {
                    favoritePkey = favoritePkey + "," + list[0];
                    favoriteVer = favoriteVer + "," + list[1]
                }
            }

        });
        $("#delPkeyList").val(favoritePkey);
        $("#rowVersionList").val(favoriteVer);
    }

    $("#front").on("click", function () {
        var page = ${pageNumber};
        page--;
        if (page <= 0) {
            layer.msg("<s:text name='my-favorite.Already_The_First_Page'/>", function () {
            });
        } else {
            window.location.href = window.location.pathname + "?category=${category}&pageNumber=" + page;
        }
    })

    $("#back").on("click", function () {
        var page = ${pageNumber};
        page++;
        if (page > ${pageCount}) {
            layer.msg("<s:text name='my-favorite.Already_The_Last_Page'/>", function () {
            });
        } else {
            window.location.href = window.location.pathname + "?category=${category}&pageNumber=" + page;
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

    $("#empty").click(function () {
        var i = 0;
        $("input[name=ckOne]").each(function (index, e) {
            if (i != 2) {
                if (!$(e).is(':checked')) {
                    i = 1;
                } else {
                    i = 2;
                }
            }
        })
        if (i == 1) {
            layer.msg("<s:text name='my-favorite.No_Product_Selected'/>", function () {
            });
            return;
        }
        $(".xmg-myf-tc333").show();
    })
    $(".xmg-myf-tc .xmg-box .xmg-close,.xmg-myf-tc .xmg-box .xmg-bottom a.cancel").click(function () {
        $(".xmg-myf-tc333").hide();
    })

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
        $("#se" + id + "").addClass('bo');
    }
</script>
<!-- End of LiveChat code -->

</body>

</html>
