/*
Powered by shoestp
*/

$(function () {
    /****** 导航分类下拉 start ******/
    if ($('#nav').attr('page') != 'index') {
        $('#nav').on('mouseover', '.nav_menu', function () {
            $(this).find('.index_cate').fadeIn(400);
        });
        $('#nav').on('mouseleave', '.nav_menu', function () {
            $(this).find('.index_cate').fadeOut(400);
        });
    }

    $('#nav').delegate('.nav_categories>ul>li', 'mouseover', function () {
        $(this).find('h2>a').addClass('FontColor').next('em').addClass('NavArrowHoverColor');
        var json = $.evalJSON($(this).attr('data'));
        if (json.length) {
            var index = $(this).addClass('hover').index();
            if (!$(this).find('.nav_subcate').length) {
                var html = '<div class="nav_subcate">';
                for (i = 0; i < json.length; i++) {
                    html = html + '<dl' + (i >= 3 ? ' class="tline"' : '') + '><dt><a href="' + json[i].url + '" title="' + json[i].text + '">' + json[i].text + '</a></dt>';
                    if (json[i].children) {
                        var jsonchild = json[i].children;
                        html = html + '<dd>';
                        for (j = 0; j < jsonchild.length; j++) {
                            html = html + '<a href="' + jsonchild[j].url + '" title="' + jsonchild[j].text + '">' + jsonchild[j].text + '</a>';
                        }
                        html = html + '</dd>';
                    }
                    html = html + '</dl>';
                    if ((i + 1) % 3 == 0) {
                        html = html + '<div class="blank12"></div>';
                    }
                }
                html = html + "</div>";
                $(this).append(html);
            }
            if (index <= 11) {
                $(this).find('.nav_subcate').css('top', (-index * 40 - 8) + 'px');
            } else {
                $(this).find('.nav_subcate').css('bottom', -40 + 'px');
            }
        }
        $(this).find('em').css('border-color', 'transparent transparent transparent ' + $('.CategoryBgColor').css('background-color'));
    });
    $('#nav').delegate('.nav_categories>ul>li', 'mouseleave', function () {
        $(this).removeClass('hover').find('h2>a').removeClass('FontColor').next('em').css('border-color', 'transparent transparent transparent #ccc').parent().parent().find('.nav_subcate').remove();
    });
    $('#nav .nav_item li').addClass('NavBorderColor2').children('a').addClass('NavBorderColor1 NavHoverBgColor');

    /****** 导航分类下拉 end ******/

    /****** 导航显示 Start ******/
    function navShow() {
        var $obj = $('.new_nav_item'),
            navItemWidth = 0,
            navWidth = $obj.width();
        $obj.css('overflow', 'visible').children('li').each(function () {
            navItemWidth += $(this).outerWidth();
            if (navItemWidth > navWidth) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    }

    navShow();
    $(window).resize(function () {
        navShow();
    });
    /****** 导航显示 End ******/

    /*新增*/
    var hj_top = $('#hj_top');
    hj_top.stop(true, true).css({'opacity': 0});
    $(window).scrollTop(0);
    $(window).scroll(function () {
        var wh = $(this).height();
        if ($(this).scrollTop() > 10) {
            if (!hj_top.is(':animated')) {
                hj_top.stop(true, true).animate({bottom: '15%', opacity: 1}, 200);
            }
        } else {
            hj_top.stop(true, true).css({'opacity': 0, 'bottom': '10%'});
        }
    });

    hj_top.click(function () {
        var wh = $(window).height();
        hj_top.stop(true, true).animate({bottom: wh + 50, opacity: 0}, 400, function () {
            $(this).css({'bottom': '10%'});
        });
        $("body, html").stop(true, true).animate({scrollTop: 0}, 400);
    });

    //新版面
    var newsupmore = $('#newsupmore');
    newsupmore.click(function (e) {//supplier
        var self = this;
        var limit = 6
        var page = parseInt($(self).attr('data-page'));
        //var total = parseInt($(self).attr('data-total'));
        var cateid = $(self).attr('data-cateid');
        $(self).hide(0);//隐藏
        page++;
        $.get('./usr_UsrSupplier_gtSupplierAndPdtListAjax', {
            page: page,
            limit: limit,
            'Cated': cateid
        }, function (data) {
            if (data.items.length > 3) {
                // if (page * limit < data.total) {
                // }
                $.each(data.items, function (i, v) {
                    // console.log(v)
                    var _div = "<div class=\"list_item fl\" style=\"margin-bottom:0\">\n" +
                        "                        <div class=\"ht clean\">\n" +
                        "                            <div class=\"fl pic\">\n" +
                        "                                <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                        "                                   target=\"_blank\">\n" +
                        "                                    <img src=\"" + stpshop_config.imageBaseUrl+ v.logo + "\">\n" +
                        "                                </a>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"info fr\">\n" +
                        "                                <div class=\"name\">\n" +
                        "                                    <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                        "                                       target=\"_blank\">" + v.name + "</a>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"ti2 clean\">\n" +
                        "                                    <div class=\"ti2_left fl\">\n" +
                        "                                        <div class=\"ti2p\">\n" +
                        "                                            "+lang_obj.supplier.products+"\n" +
                        "                                        </div>\n" +
                        "                                        <div class=\"ti2n\">\n" +
                        "                                            <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                        "                                               target=\"_blank\">" + v.proDuctCount + "</a>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                    <!-- .ti2_left -->\n" +
                        "                                    <div class=\"ti2_right fr\">\n" +
                        "                                        <div class=\"ti2p\">\n" +
                        "                                            "+lang_obj.supplier.productsStyle+"\n" +
                        "                                        </div>\n" +
                        "                                        <div class=\"ti2i\">\n" +
                        //    I18N暂无
                        "                                                " + (v.prodpattern === null ? "" : v.prodpattern) + "\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                    <!-- .ti2_right -->\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <!-- .ht -->\n" +
                        "                        <div class=\"probox clean\">\n"
                    var prbox = "";
                    typeof
                        $.each(v.proDuctDtos, function (i, v) {
                            prbox +=
                                "                                <div class=\"item fl\">\n" +
                                "                                    <a href=\"/" + v.rewrite + "\"\n" +
                                "                                       target=\"_blank\">\n" +
                                "                                        <img src=\""+ stpshop_config.imageBaseUrl+ v.picture + "\">\n" +
                                "                                    </a>\n" +
                                "                                </div>\n"

                        })
                    _div += prbox + " </div>\n" + "                    </div>"
                    $('#main_suplist_pro').append(_div);
                })
                $(self).attr('data-page', page);
                $(self).show(0);//隐藏
            } else {
                $(self).hide(0);//隐藏
            }
        }, "json");
    });

    $('#scren_cate .ct .i').click(function (e) {
        var limit = 6
        var cateid = parseInt($(this).attr('cateid'));
        var name = $(this).html();
        var scatid = parseInt(newsupmore.attr('data-cateid'));//当前cateid
        $('#scren_cate .t').html(name);
        var up = true;
        if (cateid != scatid) {
            page = 0;
            up = false;

            $('#main_suplist_pro').html('');
            newsupmore.attr('data-cateid', cateid);
        }
        page++;
        // window.location.href = "./usr_UsrSupplier_gtSupplier?Cated=" + cateid
        $.get('./usr_UsrSupplier_gtSupplierAndPdtListAjax', {
            page: page,
            limit: limit,
            'Cated': cateid
        }, function (data) {
            if (data) {
                // if (up) {
                //     $('#main_suplist_pro').append(data);
                // } else {
                $.each(data.items, function (i, v) {
                    var _div = "<div class=\"list_item fl\" style=\"margin-bottom:0\">\n" +
                        "                        <div class=\"ht clean\">\n" +
                        "                            <div class=\"fl pic\">\n" +
                        "                                <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                        "                                   target=\"_blank\">\n" +
                        "                                    <img src=\"" + stpshop_config.imageBaseUrl+ v.logo  + "\">\n" +
                        "                                </a>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"info fr\">\n" +
                        "                                <div class=\"name\">\n" +
                        "                                    <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                        "                                       target=\"_blank\">" + v.name + "</a>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"ti2 clean\">\n" +
                        "                                    <div class=\"ti2_left fl\">\n" +
                        "                                        <div class=\"ti2p\">\n" +
                        "                                            "+lang_obj.supplier.products+"\n" +
                        "                                        </div>\n" +
                        "                                        <div class=\"ti2n\">\n" +
                        "                                            <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                        "                                               target=\"_blank\">" + v.proDuctCount + "</a>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                    <!-- .ti2_left -->\n" +
                        "                                    <div class=\"ti2_right fr\">\n" +
                        "                                        <div class=\"ti2p\">\n" +
                        "                                            "+lang_obj.supplier.productsStyle+"\n" +
                        "                                        </div>\n" +
                        "                                        <div class=\"ti2i\">\n" +
                        "                                                " + v.prodpattern + "\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                    <!-- .ti2_right -->\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <!-- .ht -->\n" +
                        "                        <div class=\"probox clean\">\n"
                    var prbox = "";
                    $.each(v.proDuctDtos, function (i, v) {
                        prbox +=
                            "                                <div class=\"item fl\">\n" +
                            "                                    <a href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + v.pkey + "\"\n" +
                            "                                       target=\"_blank\">\n" +
                            "                                        <img src=\"" + v.picture + "\">\n" +
                            "                                    </a>\n" +
                            "                                </div>\n"

                    })
                    _div += prbox + " </div>\n" + "                    </div>"
                    $('#main_suplist_pro').append(_div);
                })
            }
            //$(self).show(0);//显示
            // }
        }, "json");
    });

});


function index_scr(sdiv, sbox, sitem, left_btn, right_btn, minNum, auto) {
    var odiv = $(sdiv);
    var obox = $(sbox, odiv);
    var oitem = $(sitem, odiv);
    var left_btn = $(left_btn);
    var right_btn = $(right_btn);
    var iW = oitem.outerWidth(true);
    var Tid = '';
    var Tspace = 8000;
    var speed = 500;//滚动速度
    var minNum = parseInt(minNum) ? parseInt(minNum) : 1;
    var auto = auto ? true : false;

    if (oitem.length <= minNum) {
        return;
    }

    var scr = function () {
        obox.stop(true, true).animate({'margin-left': -iW}, speed, function () {
            obox.css('margin-left', function () {
                $(sitem, this).eq(0).appendTo(obox);
                return 0;
            })
        });
    }
    obox.css('width', oitem.length * iW);//设置大容器宽度
    if (auto) {
        Tid = setInterval(scr, Tspace);
    }
    right_btn.click(function (e) {
        clearInterval(Tid);
        scr();
        if (auto) {
            Tid = setInterval(scr, Tspace);
        }
    });

    left_btn.click(function (e) {
        clearInterval(Tid);
        obox.prepend(function () {
            $(this).css('margin-left', -iW);
            return $(sitem, this).last();
        }).stop(true, true).animate({'margin-left': 0}, speed);
        if (auto) {
            Tid = setInterval(scr, Tspace);
        }
    })
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    var q = window.location.pathname.substr(1).match(reg_rewrite);
    if (r != null) {
        return unescape(r[2]);
    } else if (q != null) {
        return unescape(q[2]);
    } else {
        return null;
    }
}
