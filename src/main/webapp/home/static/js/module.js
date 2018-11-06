$.fn.extend({
    //添加购物车抛物线插件
    fly: function (t, callback) {
        var e = this,
            t = $.extend({autoPlay: !0, vertex_Rtop: 20, speed: 1.2, start: {}, end: {}, onEnd: $.noop}, t),
            f = $(e),
            obj = {
                init: function (t) {
                    obj.setOptions(t);
                    !!t.autoPlay && obj.move(t);
                },
                setOptions: function (t) {
                    var c = t,
                        s = c.start,
                        d = c.end;

                    f.css({
                        "border-top-left-radius": "50%",
                        "border-top-right-radius": "50%",
                        "border-bottom-right-radius": "50%",
                        "border-bottom-left-radius": "50%",
                        "width": 50,
                        "height": 50,
                        "background-image": "url(/home/static/images/ico/icon_Shopping-Cart.png)",
                        "background-size": "100%",
                        "background-repeat": "no-repeat",
                        "margin-top": 0,
                        "margin-left": 0,
                        "position": "absolute",
                        "z-index": "1000000"
                    }).appendTo("body"),
                    null != d.width && null != d.height && $.extend(!0, s, {width: f.width(), height: f.height()});

                    var h = Math.min(s.top, d.top) - Math.abs(s.left - d.left) / 3;
                    h < c.vertex_Rtop && (h = Math.min(c.vertex_Rtop, Math.min(s.top, d.top)));

                    var i = Math.sqrt(Math.pow(s.top - d.top, 2) + Math.pow(s.left - d.left, 2)),
                        j = Math.ceil(Math.min(Math.max(Math.log(i) / .05 - 75, 30), 100) / c.speed),
                        k = s.top == h ? 0 : -Math.sqrt((d.top - h) / (s.top - h)),
                        l = (k * s.left - d.left) / (k - 1),
                        m = d.left == l ? 0 : (d.top - h) / Math.pow(d.left - l, 2);

                    $.extend(!0, c, {count: -1, steps: j, vertex_left: l, vertex_top: h, curvature: m});
                },
                move: function (t) {
                    var s = t.start,
                        len = t.count,
                        step = t.steps,
                        d = t.end,
                        h = s.left + (d.left - s.left) * len / step,
                        i = 0 == t.curvature ? s.top + (d.top - s.top) * len / step : t.curvature * Math.pow(h - t.vertex_left, 2) + t.vertex_top;
                    if (null != d.width && null != d.height) {
                        var j = step / 2,
                            k = d.width - (d.width - s.width) * Math.cos(j > len ? 0 : (len - j) / (step - j) * Math.PI / 2),
                            l = d.height - (d.height - s.height) * Math.cos(j > len ? 0 : (len - j) / (step - j) * Math.PI / 2);
                        f.css({width: k + "px", height: l + "px", "font-size": Math.min(k, l) + "px"});
                    }

                    if (len == -1) {
                        f.css({left: h + "px", top: i + "px"});
                        t.count++;
                        obj.move(t);
                    } else {
                        f.animate({left: h + "px", top: i + "px"}, 5, function () {
                            t.count++;
                            //$('.search_r').html($.toJSON(t));
                            if (len < step) {
                                obj.move(t);
                            } else {
                                t = '';
                                obj.destory();
                            }
                        });
                    }
                },
                destory: function () {
                    f.remove();
                    callback();
                }
            }
        obj.init(t);
    }
});

function carWindow(data, _self, msg){
	   $('#addtocart_button').attr('disabled', false);
       // $('.top_cart .cart_count').text('购物车的总数量');//购物车的总数量

       var excheckout_html = '<div id="shipping_cost_choose">';
       excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
       excheckout_html += '<div id="choose_content">';
       excheckout_html += '<div class="cart_view">' +
           '<p>' + msg + '</p>' +
           // ((lang_obj.cart.additem_1).replace('%num%', '购物车的总数量')) + '<b class="FontColor">' + ueeshop_config.currency_symbols + '加入购物车的商品的价格' + '</b>' +
           '</div>';
       excheckout_html += '<p class="footRegion">';
       if (msg.indexOf( lang_obj.global.inqadd ) != -1) {
           excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">' + lang_obj.global.continues + '</a><a href="/home/usr_UsrConsult_publishView?product_id=' + data.result.id + '" class="btn btn-success" id="excheckout_button">' + lang_obj.global.inquery + '</a>';
       } else {
           excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">' + lang_obj.cart.return_shopping + '</a><a href="/home/usr_UsrCart_cartshopping" class="btn btn-success" id="excheckout_button">' + lang_obj.cart.proceed_checkout + '</a>';
       }

       excheckout_html += '</p>';
       excheckout_html += '</div>';
       excheckout_html += '</div>';

       $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
       $('body').prepend(excheckout_html);
       $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});
       global_obj.div_mask();
}

//data参数
function cartFly(data, _self, msg) {
    var offset = $('.cart_inner').offset(),
        btnLeft = $(_self).offset().left + $(_self).outerWidth(true) / 3,
        btnTop = $(_self).offset().top - $(_self).outerHeight(true) - 30,
        flyer = $('<div class="addtocart_flyer"></div>');
    flyer.fly({
        start: {left: btnLeft, top: btnTop, width: 50, height: 50},
        end: {left: offset.left + 30, top: offset.top, width: 20, height: 20}
    }, function () {
        $('#addtocart_button').attr('disabled', false);
        // $('.top_cart .cart_count').text('购物车的总数量');//购物车的总数量

        var excheckout_html = '<div id="shipping_cost_choose">';
        excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
        excheckout_html += '<div id="choose_content">';
        excheckout_html += '<div class="cart_view">' +
            '<p>' + msg + '</p>' +
            // ((lang_obj.cart.additem_1).replace('%num%', '购物车的总数量')) + '<b class="FontColor">' + ueeshop_config.currency_symbols + '加入购物车的商品的价格' + '</b>' +
            '</div>';
        excheckout_html += '<p class="footRegion">';
        if (msg.indexOf( lang_obj.global.inqadd ) != -1) {
            excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">' + lang_obj.global.continues + '</a><a href="/home/usr_UsrConsult_publishView?product_id=' + data.result.id + '" class="btn btn-success" id="excheckout_button">' + lang_obj.global.inquery + '</a>';
        } else {
            excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">' + lang_obj.cart.return_shopping + '</a><a href="/home/usr_UsrCart_cartshopping" class="btn btn-success" id="excheckout_button">' + lang_obj.cart.proceed_checkout + '</a>';
        }

        excheckout_html += '</p>';
        excheckout_html += '</div>';
        excheckout_html += '</div>';

        $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
        $('body').prepend(excheckout_html);
        $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});
        global_obj.div_mask();
    });
}

$(document).ready(function () {
    $('body').on('click', '#shipping_cost_choose', function () {
        $(this).remove();
        $('#div_mask').length && $('#div_mask').remove();
    })
    $(".prod_description ul span").on("click", function () {
        $(".prod_description .desc").eq($(this).parent().index()).removeClass("hide").siblings().addClass("hide");
        $(this).parent().addClass("current").siblings().removeClass("current");
    });


    $('#addtocart_button').on('click', function () {
        var _self = this;
        var b = 0;
        $(".column input.buy").each(function (i, e) {
            // var num = parseInt($(e).val(), 10);
            //
            // if () {
            //     b = 1;
            // }
            b = maxoq > 0 ? $(this).parent().attr("stock") : Math.min($(this).parent().attr("stock"), maxoq)
        })
        if ($("input.carts").val() != null && $("input.carts").val().length > 1) {
            if (b == 1) {
                layer.msg("I18N"+ lang_obj.user.address_tips.Please_add_item, {time: 3000});
                return;
            }
            var json = JSON.parse($("input.carts").val());
            var postData = {};
            var i = 0;
            for (var key in json) {
                postData["specList[" + i + "].spec"] = parseInt(key)
                postData["specList[" + i + "].qty"] = parseInt(json[key])
                i++;
            }
            $.ajax({
                    url: "/home/usr_UsrCart_boughtPro",
                    data: postData,
                    method: "POST",
                    dataType: "json",
                    success: function (data) {
                        if (data.ret == -1) {
                            $(".SignInButton").click();
                            return;
                        }
                        if (data.success == true) {
                        	cartFly(data, _self, lang_obj.cart.additem_0)
                            var sourceQty = Number($(".cart_count").text());
                            $(".cart_count").text(sourceQty + data.newQty);
                        } else {
                        	layer.msg(data.msg, {time: 3000});
                        }
                    }, error: function () {
                    }
                }
            )
        }
        else {
            layer.msg(lang_obj.user.address_tips.Please_add_item, {icon: 2, time: 3000});
        }
    })
    $('.share_toolbox .share_s_btn').on('click', function () {//分享
        var $obj = $('.share_toolbox');
        if (!$(this).hasClass('share_s_more')) {
            $(this).shareThis($(this).attr('data'), $obj.attr('data-title'), $obj.attr('data-url'));
        }
    });

    $("#add_to_inquiry").on("click", function () {
        var _self = this;
        $.ajax({
            url: "/home/pdt_PdtConsultPdtList_add",
            data: {
                product: $("#productId").val()
            },
            method: "POST",
            success: function (data) {
                if (data)
                    if (data.ret == -1) {
                        user_obj.set_form_sign_in('', '', 1);
                    } else {
                    	carWindow(data, _self, lang_obj.global.inqadd)
                    }
            }
        })
    })
    $('html').on('click', '#choose_close, #div_mask, #exback_button', function () {
        if ($('#shipping_cost_choose').length) {
            $('#shipping_cost_choose').remove();
            global_obj.div_mask(1);
            $('#shipping_cost_button').removeAttr('disabled');
        }
    });


})
