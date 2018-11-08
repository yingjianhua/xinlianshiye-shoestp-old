<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <!--         <link href="../home/static/themes/default/mobile/css/style(2).css" rel="stylesheet" type="text/css"> -->

</head>
<style type="text/css">
    .FontBgColor {
        background-color: #333333;
    }

    /*             overflow:hidden; */
    .home_box {
        width: 93.4%;
        margin: .75rem auto;
    }

    .home_box .small_pro {
        width: 48.40%;
        border: 1px solid #ddd;
        margin: 0 5px 5px 0;
    }

    .home_box .small_pro:nth-child(even) {
        margin-right: 0;
    }

    .home_box .small_pro .c {
        width: 91.6%;
        margin: 0 auto;
        padding-top: .75rem;
        background-color: #fff;
    }

    .home_box .small_pro .c .img {
        height: 9rem;
        min-height: 9rem;
        width: 100%;
        text-align: center;
        background: url(../home/static/themes/default/mobile/images/loading.gif) no-repeat center;
        background-size: 11.7%;
        position: relative;
    }

    .home_box .small_pro .c .img img {
        vertical-align: middle;
        height: 100%;
        width: 100%;
        object-fit: contain;
    }

    .home_box .small_pro .c .img .icon_discount {
        width: 38px;
        height: 25px;
        color: #fff;
        line-height: 12px;
        padding-top: 5px;
        background: #E25505;
        position: absolute;
        top: 0;
        right: 0;
    }

    .home_box .small_pro .c .img .icon_discount b {
        font-size: 16px;
    }

    .home_box .small_pro .c .img .icon_discount_foot {
        width: 0;
        height: 0;
        border-left: 19px transparent solid;
        border-right: 19px transparent solid;
        border-top: 5px #E25505 solid;
        position: absolute;
        right: 0;
        top: 30px;
    }

    .home_box .small_pro .c .proname {
        height: 2rem;
        overflow: hidden;
        padding-top: .625rem;
        line-height: 1rem;
    }

    .home_box .small_pro .c .proname a {
        font-size: .75rem;
        color: #333;
    }

    .home_box .small_pro .c .price {
        line-height: 1rem;
        padding-top: .125rem;
        padding-bottom: .625rem;
        font-size: .75rem;
        color: #696969;
    }

    .home_box .small_pro .c .price span, .home_box .small_pro .c .price span span {
        font-size: .75rem;
        color: #d70d0d;
    }
</style>
<body>
<%@include file="/mobile/template/header.jsp" %>
<div class="wrapper">

    <div class="pop_up modal_side" style="visibility: hidden;">
        <div class="pop_up_container clean" style="overflow-y: inherit;">
            <a class="fr close" href="#"><em></em></a>
            <div class="clear">
            </div>
            <div class="menu_list" style="max-height: 552px; overflow-y: inherit;" id="menu_list">

            </div>
            <div class="menu_button clean">
                <button type="button" class="btn btn_global btn_default clear_all"><!-- Clear All --><s:text
                        name="mobile.clear_all"/></button>
                <button type="button" class="btn btn_global btn_primary refine_search FontBgColor" name="Apply">
                    <!-- Apply --><s:text name="mobile.apply"/>
                </button>
            </div>
        </div>
    </div>


    <div class="crumb clean" id="PdtCat">
        <a href="/">
                    <span class="icon_crumb_home">
                    </span>
        </a>
        <em>
            <i>
            </i>
        </em>
        <a href="/home/pdt_PdtProduct" id="ProductList">
            <!-- Product List --><s:text name="products_list"/>
        </a>
    </div>
    <div id="filter" class="clean ui_border_b">
        <ul class="prod_sort fl">
            <li class="dropdown">
                <a href="javascript:void(0);" class="dropdown_toggle">
                    <span id="sortBy"><!-- Sort By --><s:text name="shop-productCenter.Sort_By"/></span>
                    <i class="icon_sort">
                    </i>
                    <em>
                    </em>
                </a>
            </li>
            <li class="dropdown"><a href="javascript:void(0)" class="dropdown_modal"><!-- Refine --><s:text
                    name="mobile.refine"/><i
                    class="icon_refine"></i></a></li>
        </ul>
        <ul class="dropdown_menu ui_border_t" id="cated">
            <%--<li class="ui_border_b li_a_list">--%>
                <%--<a href="javascript:void(0);" id="sort_1" action="MostPopular" class="current"><!-- Default --><s:text--%>
                        <%--name="global.default"/></a> <!-- class="current" -->--%>
            <%--</li>--%>
            <li class="ui_border_b li_a_list">
                <a href="javascript:void(0);" class="current" id="sort_2" action="MostPopular">
                    <!-- Most Popular --><s:text name="most_popular"/>
                </a>
            </li>
            <li class="ui_border_b li_a_list">
                <a href="javascript:void(0);" id="sort_3" action="Sales">
                    <!-- Sales --><s:text name="products.sales"/>
                </a>
            </li>
            <li class="ui_border_b li_a_list">
                <a href="javascript:void(0);" id="sort_4" action="Favorites">
                    <!-- Favorites --><s:text name="mobile.best_deals"/>
                </a>
            </li>
            <li class="ui_border_b li_a_list">
                <a href="javascript:void(0);" id="sort_5" action="New">
                    <!-- New --><s:text name="product.is_new"/>
                </a>
            </li>
        </ul>
    </div>
    <div id="pro_box" class="clean">
        <div>
            <div class="pro_list">
                <div class="home_box clean ui_border_radius">

                </div>
            </div>
            <script>
                $('html').seckillPrice();
            </script>
            <div class="btn_global btn_view" id="MoreButton">
                <button class="btn_global FontBgColor" name="MoreButton">
                    <!-- Load More --><s:text name="mobile.load_more"/>
                </button>
            </div>
        </div>
        <div id="prolist_mask">
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
                            <!-- Sign In --><s:text name="sign_in"/>
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
</body>

<script type="text/javascript">
    function getParms(name, defaultValue) {
        var url = window.location.href;
        var l = url.lastIndexOf(name)
        if (l != -1) {
            var ll = url.indexOf("&");
            if (ll == -1) {
                ll = url.length
            }
            url = url.substring(l, ll);
            var result = url.split("=")
            if (result.length == 2) {
                return result[1];
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

    var page = 1;
    var limit = 8;
    var orderfld = getParms("orderfld", "MostPopular");
    var order = false;
    var cated = "";
    var keyword = "";
    var onlyFld = "";
    var spec = "";
    var i = "";
    var html = "";
    $(document).ready(function () {
        key_value();
        orderMoreButton(page, limit, orderfld, order, cated, spec, onlyFld, keyword);
        menu_list();
        specButton();

    })


    var sort = 0;
    $(function () {
        // if (sort == null || sort == "") {
        //     $("#sort_1").attr("class", "current");
        // } else {
        //     $("#" + sort).attr("class", "current");
        // }
        $(".li_a_list").click(function () {
            var sort_type = parseInt($(this).find("a").attr("id"));
            if (sort_type === sort) {
                return;
            }
            $("li a", $(this).parents("ul.dropdown_menu")).each(function (n) {
                $(this).removeClass("current")
            })
            $("a", this).addClass("current");
            key_value();
            $("div.home_box.clean.ui_border_radius").empty();
            $("#sortBy").text($("a", this).text().replace(/[\r\n]/g, ""))
            orderfld = $("a",this).attr("action");
            locationhtml(orderfld, cated, spec, onlyFld)
            $('.dropdown_toggle').click();
        });
        var pages = 1;
        $("button[name=MoreButton]").click(function () {
            key_value();
            page = page + pages;
            orderMoreButton(page, limit, orderfld, order, cated, spec);
        })


        $("button[name=Apply]").click(function () {
            key_value();
            if (i != null) {
                spec = i.split("=")[1].split("+").sort(function (a, b) {
                    return a - b
                }).join(",").replace(',', '')
            }
            $("div.home_box.clean.ui_border_radius").empty();
            orderMoreButton(page, limit, orderfld, order, cated, spec, onlyFld);
            // locationhtml(orderfld, cated, spec)
            $("a.fr.close").click()

        })
    })

    function key_value() {
        var html = window.location.href;
        if (html.length > 0) {
            var html = html.split("?")
            if (html.length > 1)
                $.each(html[1].split("&"), function (key, value) {//spec == Narrow
                    if (value.indexOf("orderfld") != -1) {
                        if (value.split("=")[1] != "Default") {
                            orderfld = value.split("=")[1];
                            order = true;
                        } else {
                            orderfld = "";
                        }
                    }
                    if (value.indexOf("cated") != -1) {
                        cated = value.split("=")[1];
                    }
                    if (value.indexOf("spec") != -1) {
                        spec = value.split("=")[1].split("+").sort(function (a, b) {
                            return a - b
                        }).join(",").replace(',', '');
                    }
                    if (value.indexOf("Keyword") != -1) {
                        keyword = value.split("=")[1];
                    }
                    if (value.indexOf("onlyFld") != -1) {
                        onlyFld = value.split("=")[1];
                    }
                })
        }
    }

    function locationhtml(orderfld, cated, spec, onlyFld) {
        orderMoreButton(page, limit, orderfld, order, cated, spec, onlyFld)
    }


    function orderMoreButton(page, limit, orderfld, order, cated, spec, onlyFld, keyword) {
        $.ajax({
            type: 'post',
            async: false,
            url: '/home/pdt_PdtProduct_gtProductsIndexListAjax',
            data: {
                orderfld: orderfld,
                order: order,
                page: page,
                limit: limit,
                cated: cated,
                spec: spec,
                Keyword: keyword,
//                 isNew : is_new,
                onlyFld: onlyFld,
            },
            dataType: 'json',
            success: function (data) {
                if (data.breadcrumbnav != null) {
                    $("#PdtCat").empty();
                    $("#PdtCat").append(
                        '	<a href="/">' +
                        '       <span class="icon_crumb_home">' +
                        '       </span>' +
                        '   </a>' +
                        '     <em>' +
                        ' 	    <i>' +
                        ' 	    </i>' +
                        ' 	</em>'
                        + "<a href=\"/home/pdt_PdtProduct\" id=\"ProductList\">\n" +
                        "            <s:text name="products_list" />\n" +
                        "        </a>"
                    )
                    $.each(data.breadcrumbnav, function (key, value) {
                        $("#PdtCat").append(
                            '     <em>' +
                            ' 	    <i>' +
                            ' 	    </i>' +
                            ' 	</em>' +
                            ' 	<a href="/home/pdt_PdtProduct?cated=' + value.pkey + '">' +
                            ' 	    ' + value.name + ' ' +
                            ' 	</a>'
                        );
                    })
                }
                if (data.items != null) {
                    if (data.items.length > 0) {
                        // $("div.home_box.clean.ui_border_radius").empty();
                        $.each(data.items, function (key, value) {
                            $(".ui_border_radius").append(
                                '<div class="small_pro fl ui_border_r">' +
                                '      <div class="c">' +
                                '           <div class="img pic_box">' +
                                '               <a href="/home/pdt_PdtProduct_gtProductsInfo?id=' + value.id + '"' +
                                '               title="' + value.name + '">' +
                                '                   <img src="${envConfig.imageBaseUrl}' + value.image + '?x-oss-process=image/resize,m_pad,h_160,w_160">' +
                                '                   <span>' +
                                '                  </span>' +
                                '               </a>' +
                                '           </div>' +
                                '           <div class="proname">' +
                                '               <a href="/home/pdt_PdtProduct_gtProductsInfo?id=' + value.id + '"' +
                                '               title="' + value.name + '">' +
                                '                   ' + value.name + '' +
                                '               </a>' +
                                '           </div>' +
                                '           <div class="price">' +
                                '              <span>' +
                                '                   ${env.currency.symbols}' +
                                '                   <span class="price_data" keyid="' + value.id + '">' +
                                '                       ' + value.price + '' +
                                '                   </span>' +
                                '              </span>' +
                                '               USD' +
                                '           </div>' +
                                '       </div>' +
                                '   </div>'
                            );
                        })
                    } else {
                        layer.open({
                            content: 'I18N 没有产品了'
                            , skin: 'msg'
                            , time: 2
                        });
                        // $("#MoreButton").empty();
                        // $("#MoreButton").append("拉到底了");
                    }

                }
            }
        })
    };

    function menu_list() {
        $.ajax({
            type: 'post',
            async: false,
            url: '/home/pdt_PdtAttr_AttrList',
            data: '',
            dataType: 'json',
            success: function (attrData) {
                $.each(attrData.result, function (key, attrValue) {
                    $("#menu_list").prepend(
                        '	<div class="item son">' +
                        '		<a href="javascript:void(0);" title="' + attrValue.name + '">' +
                        '		<strong>' + attrValue.name + '</strong>' +
                        '		<span class="current_list">' +
                        '		</span>' +
                        '		</a>' +
                        '		<div class="icon">' +
                        '			<em><i></i></em>' +
                        '		</div>' +
                        '		<div class="menu_son attr_son spec" id="AttributeLine' + attrValue.id + '">' +
                        '		</div>' +
                        '	</div>'
                    );
                    $.each(attrValue.items, function (key, attrLineValue) {
                        $('#AttributeLine' + attrValue.id).append(
                            '<span id="' + attrLineValue.id + '"><strong>' + attrLineValue.name + ' </strong><em>X</em></span>'
                        )
                    })
                })
            }
        });
        $("div.item.son").on("click", function () {
            $("div.spec", this).toggle()
        })

    }


    function specButton() {
        $(document).on('click', '.spec span', function () {
            var e = $(this).attr('id'),
                x = $(this).children('strong').text(),
                o = $(this).parents('.item').find('.current_list');
            '' === i && (i = '?');
            if (-1 === i.indexOf('Narrow')) {
                i += '&Narrow=';
            }


            if (-1 === i.indexOf(e)) {
                if ($("span", $("span.current_list", $(this).parents("div.item.son"))).length < 1) {
                    '' === i ? i = '' : i += '+', i += e;
                    o.append('<span data-id="' + e + '">' + x + '</span>');
                    $(this).toggleClass('current');
                }
            } else {
                var t = i.indexOf(e),
                    n = i.indexOf('+', t);
                -1 === n && (n = i.length);
                var a = i.substring(t - 1, n);
                -1 !== a.indexOf('=') && (a = a.replace('=', ''));
                i = i.replace(a, '');
                o.find('span[data-id="' + e + '"]').remove();
            }
            if ($("span", $("span.current_list", $(this).parents("div.item.son"))).length < 1 && $(this).hasClass("current"))
                $(this).toggleClass('current');
        });

    }


</script>


<script type="text/javascript" async="" src="../home/static/themes/default/mobile/js/products.js?id=668"></script>

</html>
