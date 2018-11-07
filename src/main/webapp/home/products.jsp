<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
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
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/layer.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <style media="screen">
        /* 商品筛选 内容过长时换行问题 */
        .w_1200 .prod_sort a {
            padding: 0 17px 0 28px;
            position: relative;
            overflow: hidden;
            height: 39px;
            white-space: nowrap;
            box-sizing: border-box;
        }

        /* 前缀icon */
        .prod_sort em {
            width: 14px;
            height: 13px;
            margin-right: 2px;
            position: absolute;
            top: 14px;
            left: 10px;
        }

        /* 后缀icon */
        .prod_sort i {
            width: 9px;
            height: 11px;
            margin-left: 7px;
            position: absolute;
            top: 14px;
            right: 6px;
        }

        /* 筛选显示的内容 */
        .w_1200 .prod_sort a span {
            display: inline-block;
            max-width: 90px;
            min-width: 60px;
            white-space: nowrap;
            overflow: hidden;
        }
    </style>
</head>

<body class="lang_en w_1200">
<jsp:include page="template/web-top.jsp"></jsp:include>
<jsp:include page="template/new-header.jsp"></jsp:include>
<div id="main" class="wide">
    <div id="location">
        <s:text name="products.position"/>:
        <a href="/"><s:text name="home"/></a> &gt; <a href="/home/pdt_PdtProduct"><s:text
            name="all_products"/></a>
    </div>
    <div class="pro_left fl">
        <div class="side_category">
            <div class="cate_title">
                <s:text name="relatedCategories"/>
            </div>
            <dl class="cate_menu">


            </dl>
            <div class="blank6"></div>
        </div>
        <div id="what_hot" class="sidebar">
            <h2 class="b_title FontColor">
                <s:text name="whats_hot"/>
            </h2>
            <div class="b_list">
            </div>
            <%--热榜--%>
            <a class="b_bottom FontColor" href="/home/pdt_PdtProduct?onlyFld=Hot"><s:text name="read_more"/><s:text
                    name=""/>&gt;&gt;</a>
        </div>
        <%--特价界面--%>
        <%--<div id="special_offer" class="sidebar">--%>
        <%--<h2 class="b_title FontColor">--%>
        <%--Special Offers--%>
        <%--</h2>--%>
        <%--<div class="b_list"></div>--%>
        <%--<a class="b_bottom FontColor" href="https://www.shoestp.com/Special-Offer/">View More&gt;&gt;</a>--%>
        <%--</div>--%>
        <div class="blank15"></div>
        <div style="overflow:hidden;">
            <!-- swf_obj.js文件找不到 -->
            <!-- <script type="text/javascript" src="./static/swf_obj.js">
            </script> -->
            <div id="swfContents_7"></div>
            <!--             <script type="text/javascript"> -->
            <%--//                 var xmlData = '<list><\/list>';--%>
            <%--//                 var flashvars = {--%>
            <%--//                     xmlData: xmlData--%>
            <%--//                 };--%>
            <%--//                 var params = {--%>
            <%--//                     menu: false,--%>
            <%--//                     wmode: 'transparent'--%>
            <%--//                 };--%>
            <%--//                 var attributes = {};--%>
            <%--//                 swfobject.embedSWF('/static/images/swf/ad.swf', 'swfContents_7', '', '', '9', 'expressInstall.swf',--%>
            <%--//                     flashvars, params, attributes);--%>
            <!--             </script> -->
        </div>
        <div class="blank20"></div>
    </div>
    <div class="pro_right fr">
        <div id="filter">
            <div class="prod_sort fl">
                <a href="javascript:void(0)" action="MostPopular">
                    <em class="sort_icon_popular"></em>
                    <span><s:text name="most_popular"/></span>
                    <i class="sort_icon_arrow"></i>
                </a>
                <a href="javascript:void(0)" action="Sales">
                    <em class="sort_icon_sales"></em>
                    <span><s:text name="sales"/></span>
                    <i class="sort_icon_arrow"></i>
                </a>
                <a href="javascript:void(0)" action="Favorites">
                    <em class="sort_icon_favorites"></em>
                    <span><s:text name="fav"/></span>
                    <i class="sort_icon_arrow"></i>
                </a>
                <a href="javascript:void(0)" action="New">
                    <em class="sort_icon_new"></em>
                    <span><s:text name="newArrival"/></span>
                    <i class="sort_icon_arrow"></i>

                </a>
                <a href="javascript:void(0)" action="Price" title="Reference Price">
                    <em class="sort_icon_price"></em>
                    <i class="sort_icon_arrow"></i>
                </a>
            </div>
            <div class="prod_price fl">
                    <span class="pp_inputbox">
                        <em class="currency_data">${env.currency.symbols}</em>
                        <input type="text" id="minprice" class="min_box" autocomplete="off" value=""
                               onkeyup='this.value=this.value.replace(/\D/gi,"")'>
                    </span>
                <span class="pp_heng">-</span>
                <span class="pp_inputbox">
                        <em class="currency_data">${env.currency.symbols}</em>
                        <input type="text" id="maxprice" class="max_box" autocomplete="off" value=""
                               onkeyup='this.value=this.value.replace(/\D/gi,"")'>
                    </span>
                <input type="button" id="submit_btn" class="pp_btn" value="Go">
                <input type="hidden" class="no_price_url" value="?">
            </div>
            <div class="prod_menu fr">
                <div class="page">
                    <div class="cur">
                    </div>
                    <ul>
                    </ul>
                </div>
                <a href="#" class="page_item pre"></a>
                <a href="#" class="page_item next"></a>
            </div>
        </div>
        <div id="prod_list" class="list prod_list clearfix" effects="6">

            <div class="clear"></div>
        </div>
        <div class="blank20"></div>
        <div id="turn_page">
            <ul>

            </ul>
        </div>
        <!--         找不到row_4.js -->
        <!--         <script type="text/javascript" src="./static/row_4.js"> -->
        <!--         </script> -->
    </div>
    <div class="blank25"></div>
</div>
<jsp:include page="template/new-foot.jsp"></jsp:include>
<%--<script type="text/javascript" src="./static/js/module.js"></script>--%>
<script type="text/javascript" src="./static/js/review.js"></script>
<script type="text/javascript" src="./static/js/lightbox.min.js"></script>
<%-- <script type="text/javascript" src="./static/js/addthis_widget.js"></script> --%>
</body>
<script>
    function getParms(name, defaultValue) {
        var url = window.location.href;
        var l = url.lastIndexOf(name)
        if (l != -1) {
            var ll = url.indexOf("&");
            if (ll == -1 || l > ll) {
                ll = url.length
            }
            url = url.substring(l, ll);
            var result = url.split("=")
            if (result.length == 2) {
                switch (typeof defaultValue) {
                    case "number":
                        return parseInt(result[1]);
                    case "boolean":
                        return Boolean(result[1])
                    default:
                        return result[1];
                }
            }
        } else {
            if (defaultValue == 'NONE') {
                return null;
            }
            if (defaultValue == null) {
                return -1;
            }
            return defaultValue
        }
        return -1;
    }

    var _page = getParms("page", 1);
    var _orderfld = getParms("orderfld", "MostPopular")
    var _order = getParms("order", true)
    var _showItem = parseInt(getParms("limit", 20))
    var _where = getParms("where", "");
    var cated = parseInt(getParms("cated"))
    var _keyword = getParms("keyword", getParms("Keyword", ""))
    var _spec = getParms("Narrow", "")

    function InitList(orderfld, order, page, limit) {
        $.ajax({
            url: "/home/pdt_PdtProduct_gtProductsIndexListAjax",
            data: {
                orderfld: orderfld,
                order: order,
                page: page,
                limit: limit,
                cated: cated,
                where: _where,
                keyword: _keyword,
                spec: _spec
            }, dataType: "json", success: function (result) {
                if (result) {
                    $("#prod_list").html("");
                    $("#turn_page ul").html("")
                    getPage($("#turn_page ul"), page, result.total, limit)
                    if (result.breadcrumbnav) {
                        $("#location").html("       <s:text name='products.position'/>:\n" +
                            "        <a href=\"/\"><s:text name='home'/></a> ")
                        /*  &gt;<a href=\"/home/pdt_PdtProduct\">Products</a> */
                        $.each(result.breadcrumbnav, function (i, v) {
                            if (i != result.breadcrumbnav.length)
                            /* $("#location").append(" > ") */
                                $("#location").append("&gt;<a href=\"/home/pdt_PdtProduct?cated=" + v.pkey + "\">" + v.name + "</a>")
                        })
                    }
                    $.each(result.items, function (i, v) {
                        var appendStr = "";
                        if (i % 4 == 0) {
                            appendStr += "<div class=\"prod_box prod_box_6 fl first\">\n"
                        } else {
                            appendStr += "<div class=\"prod_box prod_box_6 fl\">\n"
                        }
                        appendStr +=
                            "                <div class=\"prod_box_pic\">\n" +
                            "                    <a class=\"pic_box\"\n" +


                            "                       href=\"/" + v.rewrite + "\"\n" +
                            "                       title=\"" + v.name + "\" target=\"_blank\">\n" +
                            "                        <img class=\"thumb\" src=\"${envConfig.imageBaseUrl}" + v.image + "\" style=\"opacity: 1;\">\n" +
                            "                        <em class=\"thumb_hover\" style=\"opacity: 0;\">\n" +
                            "                            <img src=\"${envConfig.imageBaseUrl}" + v.image + "?x-oss-process=image/resize,m_fixed,h_150,w_150\">\n" +
                            "                        </em>\n" +
                            "                    </a>\n" +
                            "                    <em class=\"icon_seckill DiscountBgColor\">Sale</em>\n" +
                            "                </div>\n" +
                            "                <div class=\"prod_box_info\">\n" +
                            "                    <div class=\"prod_box_inner\">\n" +
                            "                        <h3 class=\"prod_name\">\n" +
                            // "                            <a href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + v.id + "\"\n" +
                            "                            <a href=\"/" + v.rewrite + "\"\n" +
                            "                               title=\"" + v.name + "\"\n" +
                            "                               target=\"_blank\">" + v.name + "</a>\n" +
                            "                        </h3>\n" +
                            "                        <div class=\"prod_price\">\n" +
                            "                            <em class=\"currency_data PriceColor\">${env.currency.symbols}</em>\n" +
                            "                            <span class=\"price_data PriceColor\" data=\"" + v.price + "\" keyid=\"" + v.id + "\">" + v.price + "</span>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"free_shipping\"></div>\n" +
                            "                        <div class=\"prod_view\">\n" +
                            "                                <span class=\"favorite add_favorite1\" data=\"" + v.id + "\">\n" +
                            "                                    <i class=\"icon_heart i_co\" style=\"background-position:-705px -70px\"></i><span class='collect'></span></span>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "            </div>"
                        $("#prod_list").append(appendStr);

                    })
                    $.post("/home/usr_UsrFavorites_getAllFavorite", null, function (data) {
                        var da = data;
                        if (da.ret == -1) {
                            return;
                        }
                        $(da.fa).each(function (i, e) {
                            $(".i_co").each(function (index, el) {
                                if ($(el).parent().attr("data") == e.product) {
                                    $(el).css("background-position", "");
                                }
                            })
                        })
                    }, "json")
                }
            }
        })
    }

    function InitHotList() {
        $.ajax({
            url: "./pdt_PdtProduct_gtProductsIndexHotListAjax",
            data: {
                page: 1,
                limit: 5,
            }, dataType: "json", success: function (result) {
                if (result) {
                    $.each(result.result, function (i, v) {
                        $("#what_hot div.b_list").append(
                            "      <dl class=\"pro_item clearfix\">\n" +
                            "                    <dt class=\"fl\">\n" +
                            "                        <a class=\"pic_box\"\n" +
                            // "                           href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + v.id + "\"\n" +
                            "                           href=\"/" + v.rewrite + "\"\n" +
                            "                           title=\"" + v.name + "\" target=\"_blank\">\n" +
                            "                            <img src=\"${envConfig.imageBaseUrl}" + v.image + "?x-oss-process=image/resize,m_fixed,h_85,w_85\"\n" +
                            "                                 alt=\"" + v.name + "\">\n" +
                            "                        </a>\n" +
                            "                    </dt>\n" +
                            "                    <dd class=\"fl pro_info\">\n" +
                            "                        <div class=\"pro_name\">\n" +

                            // "                            <a href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + v.id + "\"\n" +
                            "                            <a href=\"/" + v.rewrite + "\"\n" +
                            "                               title=\"" + v.name + "\"\n" +
                            "                               target=\"_blank\">" + v.name + "</a>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"pro_price\">\n" +
                            "                            <em class=\"currency_data PriceColor\">${env.currency.symbols}</em>\n" +
                            "                            <span class=\"price_data PriceColor\" data=\"" + v.price + "\">" + v.price + "</span>\n" +
                            "                        </div>\n" +
                            "                    </dd>\n" +
                            "                </dl>"
                        );
                    })
                }
            }
        })
    }

    function InitCategories() {
        $.ajax({
            url: "./pdt_PdtProduct_gtProductsIndexCategoriesListAjax",
            data: {
                page: 1,
                limit: 5,
            }, dataType: "json", success: function (result) {
                if (result) {
                    $.each(result.items, function (i, v) {
                        var _html = "";

                        $.each(
                            v.items, function (i, v) {
                                _html += "<dd>\n" +
                                    "<a class=\"catalog " + ((v.pkey == cated) ? "current" : "") + "\" href=\"/home/pdt_PdtProduct?cated=" + v.pkey + "\">" + v.name + "</a>\n" +
                                    "</dd>\n";
                            }
                        )
                        $("dl.cate_menu").append(
                            "    <dd class=\"first\">\n" +
                            "                    <a href=\"/home/pdt_PdtProduct?cated=" + v.pkey + ((v.pkey == cated) ? "\" class='current'" : "") + "\">" + v.name + "</a>\n" +
                            "                    <dl class=\"catalog_" + v.pkey + "\">\n"
                            + _html +
                            "                    </dl>\n" +
                            "                </dd>"
                        );
                    })
                }
            }
        })
    }

    var last;

    function getPage(div, curr, count, size) {
        $("div.page ul").html("")
        // last = parseInt((count / size))
        last = Math.ceil((count / size));
        last = last > 0 ? last : 1;
        // count % size != 0 ? last++ : last
        curr = parseInt(curr)
        if (curr === 1) {
            $(div).append("<li><font class='page_noclick'><em class=\"icon_page_prev\"></em> <s:text name="Global.Previous_Page"/></font></li>")
        } else {
            $(div).append("<li><font><a href='javascript:void(0)' action='" + (curr - 1) + "' class='page_button'><em class=\"icon_page_prev\"></em> <s:text name="Global.Previous_Page"/></a></font></li>")
        }
        for (i = 1; i <= last; i++) {
            if (curr < 4) {
                if (curr == i) {
                    $(div).append("<li><font class='page_item_current'>" + i + "</font></li>")
                } else {
                    $(div).append("<li><a href='javascript:void(0)' action='" + i + "'>" + i + "</a></li>")
                }
                if (i > 4) {
                    $(div).append("<li><font>...</font></li>")
                    $(div).append("<li><a href='javascript:void(0)' action='" + last + "'>" + (last) + "</a></li>")
                    break;
                }
            } else {
                if (i == (curr + 3)) {
                    $(div).append("<li><font>...</font></li>")
                    $(div).append("<li><a href='javascript:void(0)' action='" + last + "'>" + last + "</a></li>")
                    break;
                }
                if (curr == i) {
                    $(div).append("<li><font class='page_item_current'>" + i + "</font></li>")
                    continue
                }
                if (curr > i && (curr - i <= 2)) {
                    $(div).append("<li><a href='javascript:void(0)' action='" + i + "'>" + i + "</a></li>")
                }
                if (curr < i && (i - curr <= 2)) {
                    $(div).append("<li><a href='javascript:void(0)' action='" + i + "'>" + i + "</a></li>")
                }
            }
        }
        for (i = 1; i <= last; i++) {
            $("div.page ul").append("<li><a action='" + i + "' href=\"javascript:void(0)\">" + i + "/" + last + "</a></li>")
        }
        if (curr === last) {
            $(div).append("<li><font class='page_noclick'><s:text name="next" /><em class='icon_page_next'></em></font></li>")
        } else {
            $(div).append("<li><font ><a href='javascript:void(0)' action='" + (curr + 1) + "' class='page_button'> <s:text name="next"/><em class='icon_page_next'></em></a></font></li>")
        }
        $("div.page .cur").text(curr + "/" + last)
        // _page = parseInt(_page)
        // $("a.page_item.pre").attr("href", "/home/pdt_PdtProduct?page=" + (_page == 1 ? 1 : _page - 1))
        // $("a.page_item.next").attr("href", "/home/pdt_PdtProduct?page=" + (_page == last ? _page : _page + 1))
        $("#turn_page ul a,div.page ul a").on("click", function () {
            _page = parseInt($(this).attr("action"))
            InitList(_orderfld, _order, _page, size)
            // getPage($("#turn_page ul"), _page, count, size)
        })
    }

    $(document).ready(function () {
        $("#filter div.prod_sort a").bind("click", function () {
            if (_orderfld === $(this).attr("action")) {
                _order = !_order
            } else {
                _order = true
            }
            _orderfld = $(this).attr("action");
            _where = $("#minprice").val() + "-" + $("#maxprice").val()
            // $("#minprice").val("");
            // $("#maxprice").val("");
            _page = 1
            InitList(_orderfld, _order, _page, _showItem)


            $(this).addClass('cur').siblings().removeClass('cur');
            $("div.prod_sort.fl i").removeClass('sort_icon_arrow_up').removeClass("sort_icon_arrow_down");
            $("i", this).addClass(!_order ? 'sort_icon_arrow_up' : 'sort_icon_arrow_down');
        })
        // 顶部的上一页按钮
        $("a.page_item.pre").bind("click", function () {
            _page = _page <= 1 ? 1 : _page - 1;
            InitList(_orderfld, _order, _page, _showItem)
        })
        // 顶部的下一页按钮
        $("a.page_item.next").bind("click", function () {
            _page = _page >= last ? _page : _page + 1;
            InitList(_orderfld, _order, _page, _showItem)
        })
        $("#submit_btn").bind("click", function () {
            _orderfld = "Price";
            min = $("#minprice").val().trim();
            max = $("#maxprice").val().trim();
            _page = 1;
            if (eval(min) > eval(max) && max.length > 0) {
                // alert("I18n" + "最高价格不能低于最低价格")
                layer.msg("最高价格不能低于最低价格", function () {
                });
                return;
            }
            if (min < 0 || 0 > max) {
                // alert("I18n" + "不能为负数")
                layer.msg("不能为负数", function () {
                });
                return;

            }
            _where = min + "-" + max
            InitList(_orderfld, _order, 1, _showItem)
        })

        InitList(_orderfld, true, _page, _showItem)
        InitHotList();
        InitCategories();
        $(".prod_sort.fl a").each(function (i,v) {
            if ($(v).attr('action')==_orderfld){
                $(v).addClass('cur')
            }
        })

    })
</script>

</html>
