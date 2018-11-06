/*
Powered by shoestp
*/

$.fn.extend({
    //放大镜插件
    magnify: function (t) {
        t = $.extend({
            blankHeadHeight: 0,
            detailWidth: 348,
            detailHeight: 348,
            detailLeft: 458,
            featureImgRect: "350x350",
            large: "v"
        }, t);

        var n = !1,
            win_left = $(window).scrollLeft(),
            win_top = $(window).scrollTop(),
            u = $("img", this).width(),
            a = $("img", this).height(),
            c = $(this).children("a"),
            narmal_pic = $(this).find(".normal"),
            h = '<div class="detail_img_box" style="width:' + t.detailWidth + 'px;height:' + t.detailHeight + 'px;left:' + t.detailLeft + 'px;"><img class="detail_img" onerror="$.imgOnError(this)"></div><div class="rect_mask"></div>';
        $(h).appendTo(this);

        var d = this.find(".detail_img_box"),
            v = d.find("img"),
            m = this.find(".rect_mask"),
            g = this,
            w = function ($) {
                d.hide();
                m.hide();
                m.css("top", "-9999px");
                d.css("top", "-9999px");
                n = !1
            };

        $(this).mouseleave(w).mousemove(function (h) {
            if (!n) {
                if (!c.attr("href")) return;
                var p = c.attr("href");
                v.attr("src", p);
                s = $(this).offset().left;
                o1 = $(this).offset().top;
                o2 = $(this).parent().parent().offset().top;
                v_top = o1 - o2;
                d.css({top: t.blankHeadHeight - v_top});
                n = !0
            }
            d.css({
                'width': (v.width() < t.detailWidth ? v.width() : t.detailWidth),
                'height': (v.height() < t.detailHeight ? v.height() : t.detailHeight)
            });
            u = narmal_pic.width();
            a = narmal_pic.height();
            f = u * (t.detailWidth / v.width() > 1 ? 1 : t.detailWidth / v.width());
            l = a * (t.detailHeight / v.height() > 1 ? 1 : t.detailHeight / v.height());
            m.css({"width": f, "height": l});
            d.css({left: t.detailLeft - parseInt(d.parent().parent().css("left"))});
            if (h.clientX + win_left > u + s) return $(this).trigger("mouseleave");
            var g = h.clientX + win_left - s,
                w = h.clientY + win_top - o1;
            g < f / 2 ? g = 0 : g > u - f / 2 ? g = u - f : g -= f / 2;
            w < l / 2 ? w = 0 : w > a - l / 2 ? w = a - l : w -= l / 2;
            m.css({left: g, top: w});
            v.css({
                left: -(t.detailWidth / f) * g,
                top: -(t.detailHeight / l) * w,
                "max-width": "inherit",
                "max-height": "inherit"
            });
            d.show();
            m.show()
        });

        $(window).on("scroll", function (t) {
            win_left = $(window).scrollLeft();
            win_top = $(window).scrollTop();
        });
    },

    //购买数量增减
    set_amount: function (e) {
        var t = this,
            n = t.find(".qty_num");

        t.on("blur", ".qty_num", function () {
            e = JSON.parse($(".quantity_box").attr("data"));
            if (!e) e = $.extend({min: 1, max: 999, count: 1});
            var num = parseInt($(this).val(), 10);
            if (!/^\d+$/.test($(this).val())) {
                alert('111Quantity entered must be a number!');
                $(this).val(num).focus();
            }
            //return $(this).val()==""?e.count:isNaN(num)||e.min>num||num>e.max?(global_obj.win_alert(lang_obj.products.warning_number), n.val(e.count), t.get_price(e.count), !1):(e.count=num, void 0);
            if ($(this).val() == "") {
                return e.count;
            } else {
                var Max = parseInt($('#quantity').attr('stock'));
                if (isNaN(num) || e.min > num || num > Max || num > e.max) {
                    if (num < e.min) { //低过起订量
                        e.count = e.min;
                        global_obj.win_alert(lang_obj.products.warning_MOQ);
                    } else if (num > Max) { //高过最大购买数量
                        e.count = Max;
                        global_obj.win_alert(lang_obj.products.warning_Max.replace('%num%', Max));
                    } else if (num > e.max) { //高过库存
                        e.count = e.max;
                        global_obj.win_alert(lang_obj.products.warning_stock.replace('%num%', e.count));
                    } else { //不是数字
                        global_obj.win_alert(lang_obj.products.warning_number);
                    }
                } else {
                    e.count = num;
                    return void 0;
                }
                n.val(e.count);
                t.get_price(e.count);
                return !1;
            }
        }).on("keyup", ".qty_num", function () {
            t.get_price($(this).val());
        }).on('keypress', ".qty_num", function (e) { //回车事件
            if (e.keyCode == 13) {
                $('#addtocart_button').click();
                return false;
            }
        });
    },

    //总价格整理
    get_price: function (count) {
        var p = parseFloat($("#ItemPrice").val()),//目前单价
            old = parseFloat($("#ItemPrice").attr('old')),//目前市场价
            curP = parseFloat($("#ItemPrice").attr("initial")),//产品商城价
            salesP = parseFloat($("#ItemPrice").attr("sales")),//是否开启产品促销
            disCount = parseInt($("#ItemPrice").attr("discount")),//产品促销折扣
            attr_hide = $.evalJSON($("#attr_hide").val()),//属性
            attr_len = $("#attr_hide").val().split(",").length,
            ext_attr = $.evalJSON($("#ext_attr").val()),//扩展属性
            IsCombination = $('ul.attributes').attr('data-combination'),//是否开启规格组合
            cPrice = price = wholesalePrice = wholesaleDiscount = _value = 0,
            ary = new Array,
            i, s = '';
        if ($(".prod_info_wholesale").length) var wholesale_attr = $.evalJSON($(".prod_info_wholesale").attr("data"));

        if (wholesale_attr) {//加入批发价
            for (k in wholesale_attr) {
                if (count < parseInt(k)) {
                    break;
                } else {
                    wholesalePrice = p = parseFloat(wholesale_attr[k]);
                    wholesaleDiscount = parseFloat($('.prod_info_wholesale .pw_column[data-num=' + k + '] .pw_td:eq(1)').attr('data-discount'));
                }
            }
            if (!wholesalePrice || (!salesP && curP < wholesalePrice)) p = curP;
        }
        $("#ItemPrice").val(p.toFixed(2));

        $('.prod_info_wholesale .pw_column').each(function () {
            _value = $(this).find('.pw_td:eq(1)').attr('data-price');
            $(this).find('.pw_td:eq(1)').text(ueeshop_config.currency_symbols + parseFloat(_value).toFixed(2));
        });

        if (attr_len && ext_attr && ext_attr != '[]') {//加入属性价格
            if (IsCombination == 1) {//规格组合
                i = 0;
                for (k in attr_hide) {
                    ary[i] = attr_hide[k];
                    i += 1;
                }
                ary.sort(function (a, b) {
                    return a - b
                });
                s = ary.join('_');
                if (ext_attr[s] && attr_len == $("ul.attributes li").length) {
                    if (parseInt(ext_attr[s][4])) { //加价
                        price = parseFloat(ext_attr[s][0]);
                    } else { //价格
                        p = parseFloat(ext_attr[s][0]);
                        wholesaleDiscount && (p *= 1 - wholesaleDiscount);
                        $('.prod_info_wholesale .pw_column').each(function () {
                            _value = $(this).find('.pw_td:eq(1)').attr('data-discount');
                            $(this).find('.pw_td:eq(1)').text(parseInt(_value * 100) + '% Off');
                        });
                    }
                }
            } else {
                var ext_value = '';
                for (k in attr_hide) {//循环已勾选的属性参数
                    ext_value = ext_attr[attr_hide[k]];
                    if (ext_value) price += parseFloat(ext_value[0]);//固定是加价
                }
            }
        }

        cPrice = (p + price).toFixed(2);
        cOld = (old + price).toFixed(2);

        if (salesP && disCount) {//促销折扣(会员价+属性价格)
            cPrice = cPrice * (disCount / 100);
        }

        var num = String(cPrice * parseFloat(ueeshop_config.currency_rate).toFixed(3)),
            num_to = String((cOld * parseFloat(ueeshop_config.currency_rate)).toFixed(3)),
            cur_price = num.lastIndexOf('.') > 0 && num.length - num.lastIndexOf('.') - 1 > 2 ? num.substring(0, num.lastIndexOf('.') + 3) : parseFloat(num).toFixed(2),
            del_price = num_to.lastIndexOf('.') > 0 && num_to.length - num_to.lastIndexOf('.') - 1 > 2 ? num_to.substring(0, num_to.lastIndexOf('.') + 3) : parseFloat(num_to).toFixed(2);
        $('#cur_price').text(ueeshop_config.currency_symbols + $('html').currencyFormat(cur_price, ueeshop_config.currency));
        $('.prod_info_price .price_0 del').text(ueeshop_config.currency + ' ' + ueeshop_config.currency_symbols + $('html').currencyFormat(del_price, ueeshop_config.currency));

        if ($('.price_0').length && $('.price_1 .save_price').length) {
            var dis = (old - cPrice) / old * 100;
            if (dis > 0) {
                $('.price_1 .save_price').show();
                $('.price_1 .save_p').text(ueeshop_config.currency_symbols + $('html').currencyFormat(((old - cPrice) * parseFloat(ueeshop_config.currency_rate)).toFixed(2), ueeshop_config.currency));
                $('.price_1 .save_style').text('(' + Math.round((dis > 0 && dis < 1) ? 1 : dis) + '% Off)');
            } else {
                $('.price_1 .save_price').hide();
            }
        }

        $CId = $('#CId').val();
        get_shipping_methods($CId, 1);

        //处理组合那一块
        $('.group_promotion .promotion_body .master .prod_price>input').attr('curprice', cur_price);
        $('.group_promotion .promotion_body.gp_list_purchase .group_curprice .price_data').attr('data', cur_price);
        $('.group_promotion .promotion_body.gp_list_purchase').each(function () {
            var $totalPrice = 0;
            $(this).find('input:checkbox:checked').each(function () {
                $totalPrice += parseFloat($(this).attr('curprice'));
            });
            $(this).find('.group_curprice .price_data').text($('html').currencyFormat($totalPrice.toFixed(2), ueeshop_config.currency));
        });
    },

    //检查当前属性库存的情况
    check_stock: function () {
        var attr_len, ext_attr,
            $attrStock = parseInt($("#attrStock").val()),
            tagName = this.get(0).tagName.toLowerCase(),
            $defaultStock = parseInt($("#quantity").attr('stock'));//产品默认库存
        if (tagName == 'ul') { //主产品属性
            attr_len = this.find('li').length;
            if (attr_len && $("#ext_attr").length) ext_attr = $.evalJSON($("#ext_attr").val());//扩展属性
        } else { //组合产品属性
            attr_len = this.find('dd').length;
            if (attr_len && this.find(".ext_attr").length) ext_attr = $.evalJSON(this.find(".ext_attr").val());//扩展属性
        }
        if (attr_len && $defaultStock == 0) {//产品总库存为0，就是缺货状态，所有属性不能勾选
            if (tagName == 'dl' || this.find('li select').length) { //下拉
                this.find('option').addClass('hide hide_fixed').get(0).disabled = true;
            } else { //按钮
                this.find('span').addClass('out_stock out_stock_fixed');
            }
        }
        if (attr_len && $attrStock && $defaultStock > 0) { //开启了0是库存为空的设定
            var ext_ary = new Object, ary = new Object, cur, stock_ary = new Object;
            for (k in ext_attr) {
                ary = k.split('_');
                for (k2 in ary) {
                    if (!stock_ary[ary[k2]]) stock_ary[ary[k2]] = 0;
                }
                if (ext_attr[k][1] > 0) {
                    for (k2 in ary) {
                        if (ary.length != attr_len) continue;
                        stock_ary[ary[k2]] += 1;
                    }
                }
            }
            if (tagName == 'dl' || this.find('li select').length) { //下拉
                for (k in stock_ary) {
                    if (stock_ary[k] < 1) {
                        if (this.find('option[value=' + k + ']').length) this.find('option[value=' + k + ']').addClass('hide hide_fixed').get(0).disabled = true;
                    } else {
                        if (this.find('option[value=' + k + ']').length) this.find('option[value=' + k + ']').removeClass('hide hide_fixed').get(0).disabled = false;
                    }
                }
            } else { //按钮
                for (k in stock_ary) {
                    if (stock_ary[k] < 1) {
                        if (this.find('span[value=' + k + ']').length) this.find('span[value=' + k + ']').addClass('out_stock out_stock_fixed');
                    } else {
                        if (this.find('span[value=' + k + ']').length) this.find('span[value=' + k + ']').removeClass('out_stock out_stock_fixed');
                    }
                }
            }
        }
    },

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
                        "background-image": "url(" + $('.big_pic .normal').attr('src') + ")",
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

(function ($) {
    var k = !1,
        a = function () {
            b();
            h();
            d();
            f();
            g();
            m();
            n();
        },
        b = function () {//价格显示
            set_amount = $(".quantity_box").set_amount();

            //检查当前所有属性库存的情况
            if ($('ul.attributes').attr('data-combination') == 1) {//规格组合
                $('ul.attributes').check_stock();
            }
            $('.group_promotion .promotion_body li dl.attribute[data-combination=1]').each(function () {
                $(this).check_stock();
            });
        },
        c = function () {//大图定位
            var $bigPic = $(".detail_pic"),
                $picShell = $bigPic.find(".pic_shell");
            $bigBox = $picShell.find(".big_box");

            /*
	 	 *【ueeshop】【2017/3/22】旧版产品属性程序
		pleft=($picShell.width()-$bigBox.width())/2;
		ptop=($picShell.height()-$bigBox.height())/2;
		if($bigBox.height()>$picShell.height()){
			$bigBox.css({height:$picShell.height()});
			$bigBox.find(".magnify").css({height:$picShell.height()});
			pleft=($picShell.width()-$bigBox.find('.magnify .big_pic img').width())/2;
			ptop=($picShell.height()-$bigBox.height())/2;
			$bigBox.css({width:$bigBox.find('.magnify .big_pic img').width()});
		}
		$bigBox.css({left:pleft, top:ptop});
		*/
            $bigBox.css({width: $picShell.width(), height: $picShell.height()});
            $bigBox.css({height: $bigBox.find('.magnify .big_pic img').height()});
            pleft = ($picShell.width() - $bigBox.find('.magnify .big_pic img').width()) / 2;
            ptop = ($picShell.height() - $bigBox.height()) / 2;
            $bigBox.css({width: $bigBox.find('.magnify .big_pic img').width(), left: pleft, top: ptop});
        },
        h = function () {//大图loading
            $(".detail_left").height(350).loading();
            // 载入当前大图
            // $.ajax({
            // 	url:"/static/themes/default/products/detail/"+($(".detail_left").hasClass("prod_gallery_x")?"goods_detail_pic_row.php":"goods_detail_pic.php"),
            // 	async:false,
            // 	type:'get',
            // 	data:{"ProId":$("#ProId").val(), "ColorId":$(".colorid").length?$(".colorid").val():$(".attr_value").val()},
            // 	dataType:'html',
            // 	success:function(result){
            // 		if(result){
            // 			$(".detail_left").html(result);
            // 		}
            // 	}
            // });

            $(".detail_left").height("auto").unloading();
            if (!$('#shopbox_outer').length) {//不是产品详细弹出框访问
                n_data = JSON.parse($(".detail_pic .magnify").attr("data"));
                if ($(window).width() >= 1250) {
                    magnify = $(".magnify").magnify($.extend({
                        detailWidth: 453,
                        detailHeight: 453,
                        detailLeft: 465
                    }, n_data));
                } else {
                    magnify = $(".magnify").magnify($.extend({
                        detailWidth: 390,
                        detailHeight: 390,
                        detailLeft: 345
                    }, n_data));
                }
            } else {
                $(".big_pic").attr('href', 'javascript:;');
            }
            $(".big_pic img").load(function () {
                c();
            });
            d();

            if (!$('#shopbox_outer').length) {
                $(window).resize(function () {//网站宽度变动，更新宽度
                    if ($(window).width() >= 1250) {
                        magnify = $(".magnify").magnify($.extend({
                            detailWidth: 453,
                            detailHeight: 453,
                            detailLeft: 465
                        }, n_data));
                    } else {
                        magnify = $(".magnify").magnify($.extend({
                            detailWidth: 390,
                            detailHeight: 390,
                            detailLeft: 345
                        }, n_data));
                    }
                });
            }
        },
        d = function () {//小图列表
            var $bigPic = $(".detail_pic"),
                $small = $bigPic.find('.small_carousel'),
                r, k;

            if ($(".detail_left").hasClass("prod_gallery_x")) {
                $small.carousel({itemsPerMove: 1, height: 378, width: 74, duration: 200, vertical: 1, step: 1});
            } else {
                $small.carousel({itemsPerMove: 1, height: 91, width: 318, duration: 200, vertical: !1, step: 1});
            }

            /*
	 	 *【ueeshop】【2017/3/22】旧版产品属性程序
		$bigPic.on("click",".item a",function(t){
			r=$bigPic.find(".current");
			var i=$(this).parent();
			if(!i.hasClass("current")){
				r.removeClass("current");
				r=i;
				r.addClass("current");
				$bigPic.find(".big_pic").attr("href", $('#shopbox_outer').length?'javascript:;':$(this).find("img").attr("mask"));
				$bigPic.find(".normal").attr("src", $(this).find("img").attr("normal")).load(function(){
					if(!k) c();
					k=!0;
				});
				k=!1;
			}
			return false;
		});
		*/
            $bigPic.on("click", ".item a", function (t) {
                r = $bigPic.find(".current");
                var i = $(this).parent();
                if (!i.hasClass("current")) {
                    r.removeClass("current");
                    r = i;
                    r.addClass("current");
                    $bigPic.find(".big_pic").attr("href", $('#shopbox_outer').length ? 'javascript:;' : $(this).find("img").attr("mask"));
                    $bigPic.find(".normal").attr("src", $(this).find("img").attr("normal"));
                }
                return false;
            });
        },
        f = function () {//产品属性、其他执行事件
            /*
	 	 *【ueeshop】【2017/3/22】旧版产品属性程序
		var num, attr_id, attr_ary=new Object,
			attr_hide=$("#attr_hide"),
			attr_len=$("ul.attributes li").length,
			ext_attr=$.evalJSON($("#ext_attr").val()),//扩展属性
			$attrStock=parseInt($("#attrStock").val()),
			attrSelected=parseInt($("ul.attributes").attr('default_selected')),//默认选择
			$sku_box=$(".prod_info_sku"),//SKU显示
			$defaultStock=parseInt($("#quantity").attr('stock'));//产品默认库存

		$(".attributes").on("change", "select", function(){
			num=$(this).val();
			attr_id=$(this).attr("attr");
			if(attr_hide.val() && attr_hide.val()!='[]'){
				attr_ary=$.evalJSON(attr_hide.val());
			}
			if(num){
				attr_ary[attr_id]=num;
			}else{//选择默认选项，清除对应ID
				delete attr_ary[attr_id];
			}
			attr_hide.val($.toJSON(attr_ary));

			//库存显示
			var i=stock=0,
				cur_attr='';
			for(k in attr_ary){
				cur_attr+=(i?'_':'')+k+attr_ary[k];
				++i;
			}
			if(cur_attr && ext_attr[cur_attr]){
				stock=(!$attrStock && ext_attr[cur_attr][1]<1)?999:ext_attr[cur_attr][1];
				stock=parseInt($('input[name=SId]').val())?$('input[name=SId]').attr('stock'):stock;
				$('#inventory_number').text(stock);
			}else{
				$('#inventory_number').text($defaultStock);//还原库存
			}

			//SKU显示
			var SKU='';
			if($sku_box){
				SKU=(ext_attr[cur_attr] && ext_attr[cur_attr][3])?ext_attr[cur_attr][3]:$sku_box.attr('sku');
				$sku_box.children('span').text(SKU);
			}

			if($attrStock){
				if(attr_len==2 && (attr_hide.val()=='[]' || attr_hide.val()=='{}')){//组合属性都属于默认选项
					$('.attributes li select').each(function(){
						$(this).find('option').each(function(){
							$(this).removeClass('hide');
							$(this)[0].disabled=false;
						});
					});
				}else if(attr_len==2 && ext_attr && ext_attr!='[]'){//判断组合属性库存状态
					$obj=$(this).parent().siblings().children('select');
					$obj_id=$obj.attr('attr');
					$obj.find('option').each(function(){
						$liFirst=ext_attr[attr_id+num+'_'+$obj_id+$(this).val()];
						$liSec=ext_attr[$obj_id+$(this).val()+'_'+attr_id+num];
						if($(this).val() && $liFirst){
							if($liFirst[1]<1){
								$(this).addClass('hide');
								$(this)[0].disabled=true;
							}else{
								$(this).removeClass('hide');
								$(this)[0].disabled=false;
							}
						}else if($(this).val() && $liSec){
							if($liSec[1]<1){
								$(this).addClass('hide');
								$(this)[0].disabled=true;
							}else{
								$(this).removeClass('hide');
								$(this)[0].disabled=false;
							}
						}
					});
				}
			}

			$qtyBox=$(".quantity_box");
			qty_data=$.evalJSON($qtyBox.attr("data"));
			//$qtyBox.get_price(qty_data.min);//初始化总价格
			$qtyBox.get_price($("#quantity").val());//初始化总价格
			if(qty_data.max!=stock){//更新属性库存
				if(!stock) stock=$defaultStock;
				qty_data.max=stock;
				$qtyBox.attr("data", $.toJSON(qty_data));
			}

			if($(this).hasClass('colorid')){//颜色图片属性
				h();
				$('.FontPicArrowColor').css('border-color', 'transparent transparent '+$('.FontColor').css('color')+' transparent');
				$('.FontPicArrowXColor').css('border-color', 'transparent transparent transparent '+$('.FontColor').css('color'));
			}
		}).on("click touchstart", "span", function(e){//增加ipad触屏事件
			e.preventDefault();
			if($(this).hasClass("out_stock")){return false;}
			var $this=$(this),
				$obj=$this.parents(".attr_show").find("input"),
				$attrStock=parseInt($("#attrStock").val());

			if($this.hasClass("selected")){//取消操作
				$this.removeClass("selected");
				num='';
			}else{//勾选操作
				$this.parent().find('span').removeClass('form_select_tips').addClass('GoodBorderColor');
				$this.addClass("selected").siblings().removeClass("selected");
				num=$(this).attr("value");
			}
			$obj.val(num);
			attr_id=$obj.attr("attr");
			if(attr_hide.val() && attr_hide.val()!='[]'){
				attr_ary=$.evalJSON(attr_hide.val());
			}
			if(num){
				attr_ary[attr_id]=num;
			}else{//选择默认选项，清除对应ID
				delete attr_ary[attr_id];
			}
			attr_hide.val($.toJSON(attr_ary));

			//库存显示
			var i=stock=0,
				cur_attr='';
			for(k in attr_ary){
				cur_attr+=(i?'_':'')+k+attr_ary[k];
				++i;
			}
			if(cur_attr && ext_attr[cur_attr]){
				stock=(!$attrStock && ext_attr[cur_attr][1]<1)?999:ext_attr[cur_attr][1];
				stock=parseInt($('input[name=SId]').val())?$('input[name=SId]').attr('stock'):stock;
				$('#inventory_number').text(stock);
			}else{
				$('#inventory_number').text($defaultStock);//还原库存
			}

			//SKU显示
			var SKU='';
			if($sku_box){
				SKU=(ext_attr[cur_attr] && ext_attr[cur_attr][3])?ext_attr[cur_attr][3]:$sku_box.attr('sku');
				$sku_box.children('span').text(SKU);
			}

			if($attrStock){
				if(attr_len==2 && (attr_hide.val()=='[]' || attr_hide.val()=='{}')){//组合属性都属于默认选项
					$(".attributes li span").removeClass('out_stock');
				}else if(attr_len==2 && ext_attr && ext_attr!='[]'){//判断组合属性库存状态
					$ob=$(this).parent().siblings();
					$ob_id=$ob.children('input').attr('attr');
					$ob.find('span').each(function(){
						$liFirst=ext_attr[attr_id+num+'_'+$ob_id+$(this).attr('value')];
						$liSec=ext_attr[$ob_id+$(this).attr('value')+'_'+attr_id+num];
						if($liFirst){
							if($liFirst[1]<1){
								$(this).addClass('out_stock');
							}else{
								$(this).removeClass('out_stock');
							}
						}else if($liSec){
							if($liSec[1]<1){
								$(this).addClass('out_stock');
							}else{
								$(this).removeClass('out_stock');
							}
						}
					});
				}
			}

			$qtyBox=$(".quantity_box");
			qty_data=$.evalJSON($qtyBox.attr("data"));
			//$qtyBox.get_price(qty_data.min);//初始化总价格
			$qtyBox.get_price($("#quantity").val());//初始化总价格
			if(qty_data.max!=stock){//更新属性库存
				if(!stock) stock=$defaultStock;
				qty_data.max=stock;
				$qtyBox.attr("data", $.toJSON(qty_data));
			}

			if($obj.hasClass('colorid')){//颜色图片属性
				h();
				$('.FontPicArrowColor').css('border-color', 'transparent transparent '+$('.FontColor').css('color')+' transparent');
				$('.FontPicArrowXColor').css('border-color', 'transparent transparent transparent '+$('.FontColor').css('color'));
			}
		});

		//购物车属性以选择按钮显示，默认执行第一个选项
		if(attrSelected && $(".attributes .attr_show").length){
			$(".attributes .attr_show").each(function(){
				$(this).find("span").not(".out_stock").eq(0).click();
			});
		}
		if(attrSelected && $(".attributes li select").length){
			$(".attributes li select").each(function(){
				$(this).find("option[value!='']").not(".hide").eq(0).attr("selected", "selected").change();
				$(this).find("option:eq(0)").remove();
			});
		}
		*/
            var VId, attr_id, attr_ary = new Object,
                attr_hide = $("#attr_hide"),
                attr_len = $("ul.attributes li").length,
                // ext_attr = JSON.parse($("#ext_attr").val()),//扩展属性
                $attrStock = parseInt($("#attrStock").val()),
                attrSelected = parseInt($("ul.attributes").attr('default_selected')),//默认选择
                $sku_box = $(".prod_info_sku"),//SKU显示
                $defaultStock = parseInt($("#quantity").attr('stock')),//产品默认库存
                $IsCombination = $('ul.attributes').attr('data-combination');//是否开启规格组合

            $(".attributes").on("change", "select", function () {
                VId = $(this).val();
                attr_id = $(this).attr("attr");
                if (attr_hide.val() && attr_hide.val() != '[]') {
                    attr_ary = $.evalJSON(attr_hide.val());
                }
                if (VId) {
                    attr_ary[attr_id] = VId;
                } else {//选择默认选项，清除对应ID
                    delete attr_ary[attr_id];
                }
                attr_hide.val($.toJSON(attr_ary));

                //库存显示
                var i = stock = 0,
                    ary = new Array,
                    cur_attr = '';
                for (k in attr_ary) {
                    ary[i] = attr_ary[k];
                    ++i;
                }
                ary.sort(function (a, b) {
                    return a - b
                });
                cur_attr = ary.join('_');
                if (cur_attr && ext_attr[cur_attr]) {
                    stock = (!$attrStock && ext_attr[cur_attr][1] < 1) ? 999 : ext_attr[cur_attr][1];
                    stock = parseInt($('input[name=SId]').val()) ? $('input[name=SId]').attr('stock') : stock;
                    $('#inventory_number').text(stock);
                } else {
                    $('#inventory_number').text(0); //清空库存数字
                }
                if ($IsCombination == 0) {//关闭规格组合，固定显示默认库存
                    stock = $defaultStock;
                    $('#inventory_number').text($defaultStock);
                }

                //SKU显示
                var SKU = '';
                if ($sku_box) {
                    SKU = (ext_attr[cur_attr] && ext_attr[cur_attr][3]) ? ext_attr[cur_attr][3] : $sku_box.attr('sku');
                    $sku_box.children('span').text(SKU);
                    $IsCombination == 0 && $sku_box.children('span').text($sku_box.attr('sku'));//关闭规格组合，固定显示自身SKU
                    SKU ? $sku_box.show() : $sku_box.hide();
                }

                if ($attrStock && $IsCombination == 1) {
                    if (attr_hide.val() == '[]' || attr_hide.val() == '{}') {//组合属性都属于默认选项
                        $('ul.attributes').check_stock(); //检查当前所有属性库存的情况
                        $('#inventory_number').text($defaultStock);//还原库存
                        stock = $defaultStock;
                    } else if (ext_attr && ext_attr != '[]') {//判断组合属性库存状态
                        var select_ary = new Array, i = -1, ext_ary = new Object, ary = new Object, cur,
                            no_stock_ary = new Object;
                        for (k in attr_ary) {
                            select_ary[++i] = attr_ary[k];
                        }
                        if (select_ary.length == attr_len - 1) { //勾选数 比 属性总数 少一个
                            var no_attrid = 0, attrid = 0, _select_ary, key;
                            $('ul.attributes li').each(function () {
                                attrid = $(this).children('select').attr('attr');
                                if (!attr_ary[attrid]) {
                                    no_attrid = attrid; //没有勾选的属性ID
                                }
                            });
                            $('ul.attributes #attr_' + no_attrid).find('option:gt(0)').each(function () {
                                value = $(this).attr('value');
                                _select_ary = new Array;
                                for (k in select_ary) {
                                    _select_ary[k] = select_ary[k];
                                }
                                _select_ary[select_ary.length] = value;
                                _select_ary.sort(function (a, b) {
                                    return a - b
                                });
                                key = _select_ary.join('_');
                                if (ext_attr[key][1] == 0) {
                                    if ($('ul.attributes option[value=' + value + ']').length) $('ul.attributes option[value=' + value + ']').addClass('hide').get(0).disabled = true;
                                } else {
                                    if ($('ul.attributes option[value=' + value + ']').length) $('ul.attributes option[value=' + value + ']').removeClass('hide').get(0).disabled = false;
                                }
                                if (VId == '') { //取消操作
                                    $('ul.attributes li').each(function () {
                                        if ($(this).children('select').attr('attr') != attr_id) {
                                            $(this).find('option.hide').not('.hide_fixed').removeClass('hide').get(0).disabled = false;
                                        }
                                    });
                                }
                            });
                        } else if (select_ary.length == attr_len && attr_len != 1) { //勾选数 跟 属性总数 一致
                            for (k in ext_attr) {
                                ary = k.split('_');
                                for (k2 in ary) {
                                    if (!no_stock_ary[ary[k2]]) no_stock_ary[ary[k2]] = 0;
                                }
                                cur = 0;
                                for (k2 in select_ary) {
                                    if (global_obj.in_array(select_ary[k2], ary) && ary.length == attr_len) { //找出包含自身的关联项数据，不一致的属性数量数据也排除掉
                                        ++cur;
                                    }
                                }
                                if (cur && cur >= (select_ary.length - 1) && select_ary.length == attr_len) { //“数值里已有的选项数量”跟“已勾选的选项数量”一致
                                    if (ext_attr[k][1] == 0) {
                                        for (k2 in ary) {
                                            if (global_obj.in_array(ary[k2], select_ary)) continue;
                                            if (!no_stock_ary[ary[k2]]) {
                                                no_stock_ary[ary[k2]] = 1;
                                            } else {
                                                no_stock_ary[ary[k2]] += 1;
                                            }
                                        }
                                    }
                                }
                            }
                            for (k in no_stock_ary) {
                                if (!global_obj.in_array(k, select_ary) && no_stock_ary[k] > 0) {
                                    if ($('ul.attributes option[value=' + k + ']').length) $('ul.attributes option[value=' + k + ']').addClass('hide').get(0).disabled = true;
                                } else {
                                    if ($('ul.attributes option[value=' + k + ']').length) $('ul.attributes option[value=' + k + ']').removeClass('hide').get(0).disabled = false;
                                }
                            }
                        } else { //勾选数 大于 1
                            $('ul.attributes li').each(function () {
                                $(this).find('option.hide').not('.hide_fixed').removeClass('hide').attr('disabled', false);
                            });
                        }
                    }
                }

                $qtyBox = $(".quantity_box");
                qty_data = $.evalJSON($qtyBox.attr("data"));
                if (qty_data.max != stock) {//更新属性库存
                    if (!stock || stock < 1) stock = $defaultStock;
                    qty_data.max = stock;
                    $qtyBox.attr("data", $.toJSON(qty_data));
                }
                var Max = parseInt($("#quantity").attr('stock'));
                if (parseInt($("#quantity").val()) > Max) { //最大购买量
                    global_obj.win_alert(lang_obj.products.warning_MOQ.replace('%num%', Max));
                    $("#quantity").val(Max);
                } else if (parseInt($("#quantity").val()) > parseInt(qty_data.max)) { //库存
                    global_obj.win_alert(lang_obj.products.warning_stock.replace('%num%', qty_data.max));
                    $("#quantity").val(qty_data.max);
                }
                $qtyBox.get_price($("#quantity").val());

                if ($(this).hasClass('colorid')) {//颜色图片属性
                    h();
                    $('.FontPicArrowColor').css('border-color', 'transparent transparent ' + $('.FontColor').css('color') + ' transparent');
                    $('.FontPicArrowXColor').css('border-color', 'transparent transparent transparent ' + $('.FontColor').css('color'));
                }
            }).on("click touchstart", "span", function (e) {//增加ipad触屏事件
                e.preventDefault();
                if ($(this).hasClass("out_stock")) {
                    return false;
                }
                var $this = $(this),
                    $obj = $this.parents(".attr_show").find("input"),
                    $attrStock = parseInt($("#attrStock").val());

                if ($this.hasClass("selected")) {//取消操作
                    $this.removeClass("selected");
                    VId = '';
                } else {//勾选操作
                    $this.parent().find('span').removeClass('form_select_tips').addClass('GoodBorderColor');
                    $this.addClass("selected").siblings().removeClass("selected");
                    VId = $(this).attr("value");
                }
                $obj.val(VId);
                attr_id = $obj.attr("attr");

                if (attr_hide.val() && attr_hide.val() != '[]') {
                    attr_ary = $.evalJSON(attr_hide.val());
                }
                if (VId) {
                    attr_ary[attr_id] = VId;
                } else {//选择默认选项，清除对应ID
                    delete attr_ary[attr_id];
                }
                // attr_hide.val($.toJSON(attr_ary));

                //库存显示
                var i = stock = 0,
                    ary = new Array,
                    cur_attr = '';
                for (k in attr_ary) {
                    ary[i] = attr_ary[k];
                    ++i;
                }
                ary.sort(function (a, b) {
                    return a - b
                });
                cur_attr = ary.join('_');
                // if (cur_attr && ext_attr[cur_attr]) {
                //     stock = (!$attrStock && ext_attr[cur_attr][1] < 1) ? 999 : ext_attr[cur_attr][1];
                //     stock = parseInt($('input[name=SId]').val()) ? $('input[name=SId]').attr('stock') : stock;
                //     $('#inventory_number').text(stock);
                // } else {
                //     $('#inventory_number').text(0);//清空库存数字
                // }
                if ($IsCombination == 0) {//关闭规格组合，固定显示默认库存
                    stock = $defaultStock;
                    $('#inventory_number').text($defaultStock);
                }

                //SKU显示
                // var SKU = '';
                // if ($sku_box) {
                // SKU = (ext_attr[cur_attr] && ext_attr[cur_attr][3]) ? ext_attr[cur_attr][3] : $sku_box.attr('sku');
                // $sku_box.children('span').text(SKU);
                // $IsCombination == 0 && $sku_box.children('span').text($sku_box.attr('sku'));//关闭规格组合，固定显示自身SKU
                // SKU ? $sku_box.show() : $sku_box.hide();
                // }

                if ($attrStock && $IsCombination == 1 && attr_len > 1) {
                    if (attr_hide.val() == '[]' || attr_hide.val() == '{}') {//组合属性都属于默认选项
                        $('ul.attributes').check_stock(); //检查当前所有属性库存的情况
                        $('#inventory_number').text($defaultStock);//还原库存
                        stock = $defaultStock;
                    } else if (ext_attr && ext_attr != '[]') {//判断组合属性库存状态
                        var select_ary = new Array, i = -1, ext_ary = new Object, ary = new Object, cur,
                            no_stock_ary = new Object;
                        for (k in attr_ary) {
                            select_ary[++i] = attr_ary[k];
                        }
                        if (select_ary.length == attr_len - 1) { //勾选数 比 属性总数 少一个
                            var no_attrid = 0, attrid = 0, _select_ary, key;
                            $('ul.attributes li').each(function () {
                                attrid = $(this).children('.attr_value').attr('attr');
                                if (!attr_ary[attrid]) {
                                    no_attrid = attrid; //没有勾选的属性ID
                                }
                            });
                            $('ul.attributes #attr_' + no_attrid).siblings('span').each(function () {
                                value = $(this).attr('value');
                                _select_ary = new Array;
                                for (k in select_ary) {
                                    _select_ary[k] = select_ary[k];
                                }
                                _select_ary[select_ary.length] = value;
                                _select_ary.sort(function (a, b) {
                                    return a - b
                                });
                                key = _select_ary.join('_');
                                if (ext_attr[key][1] == 0) {
                                    if ($('ul.attributes span[value=' + value + ']').length) $('ul.attributes span[value=' + value + ']').addClass('out_stock');
                                } else {
                                    if ($('ul.attributes span[value=' + value + ']').length) $('ul.attributes span[value=' + value + ']').removeClass('out_stock');
                                }
                                if (VId == '') { //取消操作
                                    $('ul.attributes li').each(function () {
                                        if ($(this).children('.attr_value').attr('attr') != attr_id) {
                                            $(this).find('span.out_stock').not('.out_stock_fixed').removeClass('out_stock');
                                        }
                                    });
                                }
                            });
                        } else if (select_ary.length == attr_len && attr_len != 1) { //勾选数 跟 属性总数 一致
                            for (k in ext_attr) {
                                ary = k.split('_');
                                for (k2 in ary) {
                                    if (!no_stock_ary[ary[k2]]) no_stock_ary[ary[k2]] = 0;
                                }
                                cur = 0;
                                for (k2 in select_ary) {
                                    if (global_obj.in_array(select_ary[k2], ary) && ary.length == attr_len) { //找出包含自身的关联项数据，不一致的属性数量数据也排除掉
                                        ++cur;
                                    }
                                }
                                if (cur && cur >= (select_ary.length - 1) && select_ary.length == attr_len) { //“数值里已有的选项数量”跟“已勾选的选项数量”一致
                                    if (ext_attr[k][1] == 0) {
                                        for (k2 in ary) {
                                            if (global_obj.in_array(ary[k2], select_ary)) continue;
                                            if (!no_stock_ary[ary[k2]]) {
                                                no_stock_ary[ary[k2]] = 1;
                                            } else {
                                                no_stock_ary[ary[k2]] += 1;
                                            }
                                        }
                                    }
                                }
                            }
                            for (k in no_stock_ary) {
                                if (!global_obj.in_array(k, select_ary) && no_stock_ary[k] > 0) {
                                    if ($('ul.attributes span[value=' + k + ']').length) $('ul.attributes span[value=' + k + ']').addClass('out_stock');
                                } else {
                                    if ($('ul.attributes span[value=' + k + ']').length) $('ul.attributes span[value=' + k + ']').removeClass('out_stock');
                                }
                            }
                        } else { //勾选数 大于 1
                            $('ul.attributes li').each(function () {
                                $(this).find('span.out_stock').not('.out_stock_fixed').removeClass('out_stock');
                            });
                        }
                    }
                }

                $qtyBox = $(".quantity_box");
                // qty_data = $.evalJSON($qtyBox.attr("data"));
                // if (qty_data.max != stock) {//更新属性库存
                //     if (!stock || stock < 1) stock = $defaultStock;
                //     qty_data.max = stock;
                //     $qtyBox.attr("data", $.toJSON(qty_data));
                // }
                // var Max = parseInt($("#quantity").attr('stock'));
                // if (parseInt($("#quantity").val()) > Max) { //最大购买量
                //     global_obj.win_alert(lang_obj.products.warning_MOQ.replace('%num%', Max));
                //     $("#quantity").val(Max);
                // } else if (parseInt($("#quantity").val()) > parseInt(qty_data.max)) { //库存
                //     global_obj.win_alert(lang_obj.products.warning_stock.replace('%num%', qty_data.max));
                //     $("#quantity").val(qty_data.max);
                // }
                // $qtyBox.get_price($("#quantity").val());
                //
                // if ($obj.hasClass('colorid')) {//颜色图片属性
                //     h();
                //     $('.FontPicArrowColor').css('border-color', 'transparent transparent ' + $('.FontColor').css('color') + ' transparent');
                //     $('.FontPicArrowXColor').css('border-color', 'transparent transparent transparent ' + $('.FontColor').css('color'));
                // }
            });

            //购物车属性以选择按钮显示，默认执行第一个选项
            if (attrSelected && $(".attributes .attr_show").length) {
                $(".attributes .attr_show").each(function () {
                    $(this).find("span").not(".out_stock").eq(0).click();
                });
            }
            if (attrSelected && $(".attributes li select").length) {
                $(".attributes li select").each(function () {
                    $(this).find("option[value!='']").not(".hide").eq(0).attr("selected", "selected").change();
                    $(this).find("option:eq(0)").remove();
                });
            }

            //购物车属性提示框，关闭事件
            $('.attr_sure_close').click(function () {
                if ($('.attributes_tips').length) {
                    $('.attributes').removeClass('attributes_tips');
                }
            });

            $('.prod_info_currency li').delegate('a', 'click', function () {
                var v = $(this).attr('data');
                $.post('/', 'do_action=action.currency&currency=' + v, function (data) {
                    if (data.ret == 1) {
                        window.top.location.reload();
                    }
                }, 'json');
            });

            $(".prod_info_pdf").click(function () {//PDF打印
                $("#export_pdf").attr("src", "http://pdfmyurl.com?url=" + window.location.href.replace(/^http[s]?:\/\//, ""));
            });

            $(".prod_info_data a").click(function () {
                $("body, html").animate({scrollTop: $(".prod_description").offset().top}, 500);
                $(".prod_description .pd_title span[data=" + $(this).attr("data") + "]").click();
            });

            $(".prod_description ul span").on("click", function () {
                $(".prod_description .desc").eq($(this).parent().index()).removeClass("hide").siblings().addClass("hide");
                $(this).parent().addClass("current").siblings().removeClass("current");
            });

            $('.share_toolbox .share_s_btn').on('click', function () {//分享
                var $obj = $('.share_toolbox');
                if (!$(this).hasClass('share_s_more')) {
                    $(this).shareThis($(this).attr('data'), $obj.attr('data-title'), $obj.attr('data-url'));
                }
            });
        },
        g = function () {//提交验证
            $('#addtocart_button, #buynow_button').show();//等待页面加载完才显示，以防客户直接打开出错误的页面。
            //到货通知
            $('#arrival_button').on('click', function () {
                $.post('/', 'do_action=action.arrival_notice&ProId=' + $('#ProId').val(), function (data) {
                    if (data.ret) {
                        var excheckout_html = '<div id="prompt_box">';
                        excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="prompt_close">×</a>';
                        excheckout_html += '<div id="prompt_content">';
                        if (data.ret == 1) {
                            excheckout_html += '<div class="cart_view"><p>' + lang_obj.cart.arrival_info_0 + '</p></div>';
                        } else if (data.ret == 2) {
                            excheckout_html += '<div class="cart_view"><p>' + lang_obj.cart.arrival_info_1 + '</p></div>';
                        } else {
                            excheckout_html += '<div class="cart_view"><p>' + lang_obj.cart.arrival_info_2 + '</p></div>';
                        }
                        excheckout_html += '<p class="footRegion">';
                        excheckout_html += '<a href="javascript:;" class="btn btn-success" id="prompt_button">' + lang_obj.cart.return_shopping + '</a>';
                        excheckout_html += '</p>';
                        excheckout_html += '</div>';
                        excheckout_html += '</div>';

                        $('#prompt_box').length && $('#prompt_box').remove();
                        $('body').prepend(excheckout_html);
                        $('#prompt_box').css({left: $(window).width() / 2 - 220});
                        if (!$('#div_mask').length) global_obj.div_mask();
                    }
                }, 'json');
            });

            //Buy Now
            $('#buynow_button').on('click', function () {
                var subObj = $("#goods_form"),
                    $attr_name = '',
                    result = 0;
                subObj.find("select").each(function () {
                    //$(this).css("border","1px #ccc solid");
                    if (!$(this).val()) {
                        //$(this).css("border","1px red solid");
                        if (!$attr_name) $attr_name = $(this).parents('li').attr('name');
                        result = 1;
                    }
                });
                subObj.find("input.attr_value").each(function () {
                    //$(this).siblings('span').removeClass('form_select_tips').addClass('GoodBorderColor');;
                    if (!$(this).val()) {
                        //$(this).siblings('span').removeClass('GoodBorderColor').addClass('form_select_tips');
                        if (!$attr_name) $attr_name = $(this).parents('li').attr('name');
                        result = 1;
                    }
                });

                $('.attributes').removeClass('attributes_tips');
                if (result) {
                    //global_obj.win_alert(lang_obj.products.warning_attribute+': '+$attr_name);
                    $('.attributes').addClass('attributes_tips');
                    return false;
                }

                $.post('/', $('#goods_form').serialize() + '&do_action=cart.additem&IsBuyNow=1&back=1', function (data) {
                    data = $.evalJSON(data);
                    if (data.ret == 1) {
                        parseInt(ueeshop_config.FbPixelOpen) == 1 && $('html').fbq_addtocart();
                        if ($('#shopbox_outer').length) {//产品详细弹出框
                            $(window.parent.window).get(0).location.href = data.msg;
                        } else {//产品详细页
                            window.location.href = data.msg;
                        }
                    } else {
                        $('#buynow_button').attr('disabled', false);
                        if ($('#shopbox_outer').length) {//产品详细弹出框
                            var parentObj = $(window.parent.document);
                            parentObj.find('#shopbox').shopboxHide();
                        }
                    }
                });
                return false;
            });

            $("#goods_form").submit(function () {
                return false
            });
            $('#addtocart_button').on('click', function () {
                if ($("input.carts").val() != null && $("input.carts").val().length > 1) {

                    $.ajax({
                        url: "/home/usr_UsrCart_InsCart",
                        data: {
                            carts: $("input.carts").val()
                        },
                        method: "POST",
                        success: function (data) {
                            if (data)
                                if (data.ret != 1) {
                                    alert(data.msg)
                                }
                        }
                    })
                } else {
                    alert(lang_obj.user.address_tips.Please_add_item)
                }

                // var subObj=$("#goods_form"),
                // 	$attr_name='',
                // 	result=0;
                //
                // if($(this).attr('disabled')) return false;
                // subObj.find("select").each(function(){
                // 	//$(this).css("border","1px #ccc solid");
                // 	if(!$(this).val()){
                // 		//$(this).css("border","1px red solid");
                // 		if(!$attr_name) $attr_name=$(this).parents('li').attr('name');
                // 		result=1;
                // 	}
                // });
                // subObj.find(".attr_value").each(function(){
                // 	//$(this).siblings('span').removeClass('form_select_tips').addClass('GoodBorderColor');;
                // 	if(!$(this).val()){
                // 		//$(this).siblings('span').removeClass('GoodBorderColor').addClass('form_select_tips');
                // 		if(!$attr_name) $attr_name=$(this).parents('li').attr('name');
                // 		result=1;
                // 	}
                // });
                // $('.attributes').removeClass('attributes_tips');
                // if(result){
                // 	//global_obj.win_alert(lang_obj.products.warning_attribute+': '+$attr_name);
                // 	$('.attributes').addClass('attributes_tips');
                // 	return false;
                // }
                //
                // $(this).attr('disabled', true);
                // var offset = $('.cart_inner').offset(),
                // 	btnLeft = $(this).offset().left+$(this).outerWidth(true)/3,
                // 	btnTop = $(this).offset().top-$(this).outerHeight(true)-30,
                // 	flyer = $('<div class="addtocart_flyer"></div>');
                //
                // $.post('/', $('#goods_form').serialize() + '&do_action=cart.additem&back=1', function (data) {
                //     data = $.evalJSON(data);
                //     if (data.ret == 1) {
                //         parseInt(ueeshop_config.FbPixelOpen) == 1 && $('html').fbq_addtocart();
                //         if ($('#shopbox_outer').length) {//产品详细弹出框
                //             var parentObj = $(window.parent.document);
                //             parentObj.find('#shopbox').shopboxHide();
                //
                //             var excheckout_html = '<div id="shipping_cost_choose">';
                //             excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
                //             excheckout_html += '<div id="choose_content">';
                //             excheckout_html += '<div class="cart_view"><p>' + lang_obj.cart.additem_0 + '</p>' + ((lang_obj.cart.additem_1).replace('%num%', data.msg.qty)) + '<b class="FontColor">' + ueeshop_config.currency_symbols + data.msg.price + '</b></div>';
                //             excheckout_html += '<p class="footRegion">';
                //             excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">' + lang_obj.cart.return_shopping + '</a><a href="/cart/" class="btn btn-success" id="excheckout_button">' + lang_obj.cart.proceed_checkout + '</a>';
                //             excheckout_html += '</p>';
                //             excheckout_html += '</div>';
                //             excheckout_html += '</div>';
                //
                //             parentObj.find('#shipping_cost_choose').length && parentObj.find('#shipping_cost_choose').remove();
                //             parentObj.find('body').prepend(excheckout_html);
                //             parentObj.find('#shipping_cost_choose').css({left: $(window.parent.window).width() / 2 - 220});
                //
                //             //关闭提示框
                //             parentObj.find('body').delegate('#choose_close, #div_mask, #exback_button', 'click', function () {
                //                 if (parentObj.find('#shipping_cost_choose').length) {
                //                     parentObj.find('#shipping_cost_choose').remove();
                //                     parentObj.find('#div_mask').remove();
                //                 }
                //             });
                //         } else {
                //             flyer.fly({
                //                 start: {left: btnLeft, top: btnTop, width: 50, height: 50},
                //                 end: {left: offset.left + 30, top: offset.top, width: 20, height: 20}
                //             }, function () {
                //                 $('#addtocart_button').attr('disabled', false);
                //                 $('.top_cart .cart_count').text(data.msg.qty);
                //                 //$('.header_cart .cart_count_price').text(data.msg.price);
                //                 //$('.header_cart .cart_note').html('');
                //
                //                 var excheckout_html = '<div id="shipping_cost_choose">';
                //                 excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
                //                 excheckout_html += '<div id="choose_content">';
                //                 excheckout_html += '<div class="cart_view"><p>' + lang_obj.cart.additem_0 + '</p>' + ((lang_obj.cart.additem_1).replace('%num%', data.msg.qty)) + '<b class="FontColor">' + ueeshop_config.currency_symbols + data.msg.price + '</b></div>';
                //                 excheckout_html += '<p class="footRegion">';
                //                 excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">' + lang_obj.cart.return_shopping + '</a><a href="/cart/" class="btn btn-success" id="excheckout_button">' + lang_obj.cart.proceed_checkout + '</a>';
                //                 excheckout_html += '</p>';
                //                 excheckout_html += '</div>';
                //                 excheckout_html += '</div>';
                //
                //                 $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
                //                 $('body').prepend(excheckout_html);
                //                 $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});
                //                 global_obj.div_mask();
                //             });
                //         }
                //     } else {
                //         $('#addtocart_button').attr('disabled', false);
                //         if ($('#shopbox_outer').length) {//产品详细弹出框
                //             var parentObj = $(window.parent.document);
                //             parentObj.find('#shopbox').shopboxHide();
                //         }
                //     }
                // });
                return false;
            });
        },
        m = function () {//组合产品
            $('.gp_list_promotion').each(function () {//组合促销其中一个产品没有库存都要下架
                if (parseInt($(this).find('.not_prod_number').attr('value')) > 0) {
                    $(this).remove();
                    $('.gp_title span[data=promotion][data-id=' + $(this).attr('data-id') + ']').parent().remove();
                }
            });
            if (!$('.gp_list li').length) $('.group_promotion').remove();

            $('.group_promotion .gp_title span').off().on('click', function () {
                var $obj = $('.promotion_body[data-id=' + $(this).attr('data-id') + ']');
                var suits_ulW = $obj.find('.suits li').outerWidth(true) * ($obj.find('.suits li').size() + 5);
                var suitsW = $('.group_promotion .suits').outerWidth(true);
                $obj.removeClass('hide').siblings().addClass('hide');
                $(this).parent().parent().find('span').removeClass('FontColor');
                $(this).addClass('FontColor').parent().addClass('current FontBorderColor').siblings().removeClass('current FontBorderColor');
                $obj.find('.suits ul').css({'width': suits_ulW});
                $obj.find('.suits').css({'overflow-x': (suits_ulW > suitsW ? 'scroll' : 'hidden')});
            });
            $('.group_promotion .gp_title li:eq(0)>span').click();

            $(window).resize(function () {//网站宽度变动，更新宽度
                $('.group_promotion .gp_title span').each(function () {
                    var $obj = $('#gp_list_' + $(this).attr('data'));
                    var suits_ulW = $obj.find('.suits li').outerWidth(true) * ($obj.find('.suits li').size() + 5);
                    $obj.find('.suits ul').css({'width': suits_ulW});
                });
            });

            /*
	 	 *【ueeshop】【2017/3/22】旧版产品属性程序
		$(".group_promotion .attribute").on("change", "select", function(){
			var num, attr_id, cur_attr, attr_ary=new Object, s='',
				i=stock=attr_price=0,
				$parent=$(this).parents('.attribute'),
				$attr_hide=$parent.children(".attr_hide"),
				ext_attr=$.evalJSON($parent.children(".ext_attr").val());//扩展属性
				attr_len=$parent.children("dd").length,//当前属性数量
				$attrStock=parseInt($("#attrStock").val()),
				price=parseFloat($parent.parent().find('.price_data').attr('data')),
				moq=parseInt($parent.parent().find('input:checkbox').attr('moq')),
				disCount=parseInt($parent.parent().find('input:checkbox').attr("discount")),//促销折扣;

			num=$(this).val();
			attr_id=$(this).attr("attr");
			if($attr_hide.val() && $attr_hide.val()!='[]'){
				attr_ary=$.evalJSON($attr_hide.val());
			}
			if(num){
				attr_ary[attr_id]=num;
			}else{//选择默认选项，清除对应ID
				delete attr_ary[attr_id];
			}
			$attr_hide.val($.toJSON(attr_ary));

			if(attr_len<2 && attr_ary){//加入属性价格
				for(k in attr_ary){
					if(attr_ary[k]){
						attr_price=parseFloat(ext_attr[k+attr_ary[k]][0]);
					}else attr_price=0;
				}
			}else{
				i=0;
				for(k in attr_ary){
					s+=(!i?'':'_')+k+attr_ary[k];
					i+=1;
				}
				if(ext_attr[s] && ext_attr[s][0]){
					attr_price=parseFloat(ext_attr[s][0]);
				}else attr_price=0;
			}
			price=price+attr_price;
			if(disCount){//促销折扣(会员价+属性价格)
				price=price*(disCount/100);
			}
			$parent.parent().find('input:checkbox').attr('attrprice', attr_price).attr('curprice', (moq*price*parseFloat(ueeshop_config.currency_rate)));//标注属性价格

			var _num=String(price*parseFloat(ueeshop_config.currency_rate).toFixed(3)),
				price=_num.lastIndexOf('.')>0 && _num.length-_num.lastIndexOf('.')-1>2?_num.substring(0, _num.lastIndexOf('.')+3):parseFloat(_num).toFixed(2);
			$parent.parent().find('.price_data').text($('html').currencyFormat(price, ueeshop_config.currency));//价格+属性价格

			if($attrStock){
				if(attr_len==2 && ($attr_hide.val()=='[]' || $attr_hide.val()=='{}')){//组合属性都属于默认选项
					$parent.find('select').each(function(){
						$(this).find('option').each(function(){
							$(this).removeClass('hide');
							$(this)[0].disabled=false;
						});
					});
				}else if(attr_len==2 && ext_attr && ext_attr!='[]'){//判断组合属性库存状态
					$obj=$(this).parent().siblings().children('select');
					$obj_id=$obj.attr('attr');
					$obj.find('option').each(function(){
						$liFirst=ext_attr[attr_id+num+'_'+$obj_id+$(this).val()];
						$liSec=ext_attr[$obj_id+$(this).val()+'_'+attr_id+num];
						if($(this).val() && $liFirst){
							if($liFirst[1]<1){
								$(this).addClass('hide');
								$(this)[0].disabled=true;
							}else{
								$(this).removeClass('hide');
								$(this)[0].disabled=false;
							}
						}else if($(this).val() && $liSec){
							if($liSec[1]<1){
								$(this).addClass('hide');
								$(this)[0].disabled=true;
							}else{
								$(this).removeClass('hide');
								$(this)[0].disabled=false;
							}
						}
					});
				}
			}

			//处理组合总价格显示
			$parent.parents('.promotion_body').each(function(){
				var $totalPrice=0;
				$(this).find('input:checkbox:checked').each(function(){
					$totalPrice+=parseFloat($(this).attr('curprice'));
				});
				$(this).find('.group_curprice .price_data').text($('html').currencyFormat($totalPrice.toFixed(2), ueeshop_config.currency));
			});
		});
		*/
            $(".group_promotion .attribute").on("change", "select", function () {
                var VId, attr_id, cur_attr, attr_ary = new Object, s = '', ary = new Array,
                    i = stock = attr_price = 0,
                    $parent = $(this).parents('.attribute'),
                    $attr_hide = $parent.children(".attr_hide"),
                    ext_attr = $.evalJSON($parent.children(".ext_attr").val());//扩展属性
                attr_len = $parent.children("dd").length,//当前属性数量
                    $attrStock = parseInt($("#attrStock").val()),
                    price = parseFloat($parent.parent().find('.price_data').attr('data')),
                    moq = parseInt($parent.parent().find('input:checkbox').attr('moq')),
                    disCount = parseInt($parent.parent().find('input:checkbox').attr("discount")),//促销折扣
                    IsCombination = $parent.attr('data-combination');//是否开启规格组合

                VId = $(this).val();
                attr_id = $(this).attr("attr");
                if ($attr_hide.val() && $attr_hide.val() != '[]') {
                    attr_ary = $.evalJSON($attr_hide.val());
                }
                if (VId) {
                    attr_ary[attr_id] = VId;
                } else {//选择默认选项，清除对应ID
                    delete attr_ary[attr_id];
                }
                $attr_hide.val($.toJSON(attr_ary));
                if (attr_len && ext_attr && ext_attr != '[]') {//加入属性价格
                    if (IsCombination == 1) {//规格组合
                        i = 0;
                        for (k in attr_ary) {
                            ary[i] = attr_ary[k];
                            i += 1;
                        }
                        ary.sort(function (a, b) {
                            return a - b
                        });
                        s = ary.join('_');
                        if (ext_attr[s] && attr_len == $parent.children('dd').length) {
                            if (parseInt(ext_attr[s][4])) { //加价
                                attr_price = parseFloat(ext_attr[s][0]);
                            } else { //价格
                                price = parseFloat(ext_attr[s][0]);
                            }
                        }
                    } else {
                        var ext_value = '';
                        for (k in attr_ary) {//循环已勾选的属性参数
                            ext_value = ext_attr[attr_ary[k]];
                            if (ext_value) price += parseFloat(ext_value[0]);//固定是加价
                        }
                    }
                }
                price = price + attr_price;
                if (disCount) {//促销折扣(会员价+属性价格)
                    price = price * (disCount / 100);
                }
                $parent.parent().find('input:checkbox').attr('attrprice', attr_price).attr('curprice', (moq * price * parseFloat(ueeshop_config.currency_rate)));//标注属性价格

                var _num = String(price * parseFloat(ueeshop_config.currency_rate).toFixed(3)),
                    price = _num.lastIndexOf('.') > 0 && _num.length - _num.lastIndexOf('.') - 1 > 2 ? _num.substring(0, _num.lastIndexOf('.') + 3) : parseFloat(_num).toFixed(2);
                $parent.parent().find('.price_data').text($('html').currencyFormat(price, ueeshop_config.currency));//价格+属性价格

                if ($attrStock) {
                    if ($attr_hide.val() == '[]' || $attr_hide.val() == '{}') {//组合属性都属于默认选项
                        $parent.check_stock(); //检查当前所有属性库存的情况
                    } else if (ext_attr && ext_attr != '[]') {//判断组合属性库存状态
                        var select_ary = new Array, i = -1, ext_ary = new Object, ary = new Object, cur,
                            no_stock_ary = new Object;
                        for (k in attr_ary) {
                            select_ary[++i] = attr_ary[k];
                        }
                        if (select_ary.length == attr_len - 1) { //勾选数 比 属性总数 少一个
                            var no_attrid = 0, attrid = 0, _select_ary, key;
                            $parent.find('dd').each(function () {
                                attrid = $(this).children('select').attr('attr');
                                if (!attr_ary[attrid]) {
                                    no_attrid = attrid; //没有勾选的属性ID
                                }
                            });
                            $parent.find('#attr_' + no_attrid).find('option:gt(0)').each(function () {
                                value = $(this).attr('value');
                                _select_ary = new Array;
                                for (k in select_ary) {
                                    _select_ary[k] = select_ary[k];
                                }
                                _select_ary[select_ary.length] = value;
                                _select_ary.sort(function (a, b) {
                                    return a - b
                                });
                                key = _select_ary.join('_');
                                if (ext_attr[key][1] == 0) {
                                    $parent.find('option[value=' + value + ']').addClass('hide').get(0).disabled = true;
                                } else {
                                    $parent.find('option[value=' + value + ']').removeClass('hide').get(0).disabled = false;
                                }
                                if (VId == '') { //取消操作
                                    $parent.find('li').each(function () {
                                        if ($(this).children('select').attr('attr') != attr_id) {
                                            $(this).find('option.hide').not('.hide_fixed').removeClass('hide').get(0).disabled = false;
                                        }
                                    });
                                }
                            });
                        } else if (select_ary.length == attr_len && attr_len != 1) { //勾选数 跟 属性总数 一致
                            for (k in ext_attr) {
                                ary = k.split('_');
                                for (k2 in ary) {
                                    if (!no_stock_ary[ary[k2]]) no_stock_ary[ary[k2]] = 0;
                                }
                                cur = 0;
                                for (k2 in select_ary) {
                                    if (global_obj.in_array(select_ary[k2], ary) && ary.length == attr_len) { //找出包含自身的关联项数据，不一致的属性数量数据也排除掉
                                        ++cur;
                                    }
                                }
                                if (cur && cur >= (select_ary.length - 1) && select_ary.length == attr_len) { //“数值里已有的选项数量”跟“已勾选的选项数量”一致
                                    if (ext_attr[k][1] == 0) {
                                        for (k2 in ary) {
                                            if (global_obj.in_array(ary[k2], select_ary)) continue;
                                            if (!no_stock_ary[ary[k2]]) {
                                                no_stock_ary[ary[k2]] = 1;
                                            } else {
                                                no_stock_ary[ary[k2]] += 1;
                                            }
                                        }
                                    }
                                }
                            }
                            for (k in no_stock_ary) {
                                if (!global_obj.in_array(k, select_ary) && no_stock_ary[k] > 0) {
                                    $parent.find('option[value=' + k + ']').addClass('hide').get(0).disabled = true;
                                } else {
                                    $parent.find('option[value=' + k + ']').removeClass('hide').get(0).disabled = false;
                                }
                            }
                        } else { //勾选数 大于 1
                            $parent.find('dd').each(function () {
                                $(this).find('option.hide').not('.hide_fixed').removeClass('hide').attr('disabled', false);
                            });
                        }
                    }
                }

                //处理组合总价格显示
                $parent.parents('.promotion_body').each(function () {
                    var $totalPrice = 0;
                    $(this).find('input:checkbox:checked').each(function () {
                        $totalPrice += parseFloat($(this).attr('curprice'));
                    });
                    $(this).find('.group_curprice .price_data').text($('html').currencyFormat($totalPrice.toFixed(2), ueeshop_config.currency));
                });
            });

            $('.group_promotion').on('click', 'input:checkbox', function () {
                var $obj = $(this).parents('.promotion_body'),
                    $totalPrice = 0,
                    $num = 0;

                $obj.find('input:checkbox:checked').each(function () {
                    $totalPrice += parseFloat($(this).attr('curprice'));
                    $num += 1;
                });
                $obj.find('.group_nums').text($num - 1);
                $obj.find('.group_curprice .price_data').text($('html').currencyFormat($totalPrice.toFixed(2), ueeshop_config.currency));
            });

            $('.group_promotion').on('click', '.gp_btn', function () {
                var $obj = $(this).parents('.promotion_body'),
                    $PId = $obj.find('input[name=PId]').val(),
                    $data_id = $obj.attr('data-id'),
                    id_where = attr_where = '', attr_obj = new Object, $num = 0, $data;

                if ($obj.hasClass('gp_list_promotion')) {//组合促销
                    var Attr = $obj.find('.master_attr_hide').val();
                } else {//组合购买
                    //判断主产品
                    var subObj = $('#goods_form'),
                        result = 0;
                    subObj.find('select').each(function () {
                        //$(this).css('border','1px #ccc solid');
                        if (!$(this).val()) {
                            //$(this).css('border','1px red solid');
                            result = 1;
                        }
                    });
                    subObj.find('.attr_value').each(function () {
                        //$(this).siblings('span').removeClass('form_select_tips').addClass('GoodBorderColor');;
                        if (!$(this).val()) {
                            //$(this).siblings('span').removeClass('GoodBorderColor').addClass('form_select_tips');
                            result = 1;
                        }
                    });
                    $('.attributes').removeClass('attributes_tips');
                    if (result) {
                        $('.attributes').addClass('attributes_tips');
                        $('body,html').animate({scrollTop: subObj.find('.attributes').offset().top}, 500);
                        return false;
                    }

                    //判断组合产品
                    var result_to = 0;
                    $obj.find('input:checkbox:checked').each(function () {
                        id_where += ($num ? ',' : '') + parseInt($(this).attr('proid'));
                        $num += 1;
                        //已勾选的产品，再判断其下拉属性是否已选
                        if ($(this).parents('li').find('select').length) {
                            $(this).parents('li').find('select').each(function () {
                                $(this).css('border', '1px #ccc solid');
                                if (!$(this).val()) {
                                    $(this).css('border', '1px red solid');
                                    result_to = 1;
                                }
                            });
                        }
                        //获取产品勾选的属性
                        if ($(this).parents('li').find('.attr_hide').val()) {
                            attr_obj[$(this).attr('proid')] = $.evalJSON($(this).parents('li').find('.attr_hide').val());
                        }
                    });
                    if (attr_obj) attr_where = $.toJSON(attr_obj);//合并所有产品属性
                    if (result_to) return false;

                    var Attr = $('#attr_hide').val();
                }
                debugger
                $.ajax({
                    url: '/',
                    async: false,
                    type: 'post',
                    data: {
                        'ProId': id_where,
                        'PId': $PId,
                        'Attr': Attr,
                        'ExtAttr': attr_where,
                        'products_type': ($obj.hasClass('gp_list_promotion') ? 4 : 3),
                        'do_action': 'cart.additem',
                        'back': 1
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result.ret == 1) {
                            window.location = '/cart/';
                        } else {
                            if ($('#shopbox_outer').length) {//产品详细弹出框
                                var parentObj = $(window.parent.document);
                                parentObj.find('#shopbox').shopboxHide();

                                user_obj.set_form_sign_in('parent');
                            } else {
                                user_obj.set_form_sign_in();
                                $('form[name=signin_form]').append('<input type="hidden" name="comeback" value="global_obj.div_mask(1);$(\'#signin_module\').remove();$(\'.promotion_body[data-id=' + $data_id + '] .gp_btn\').click();" />');
                            }
                        }
                    }
                });
                return false;
            });
        }
    n = function () {
        /**** 运费查询 Start ****/
        $('#shipping_cost_button').click(function () {
            if ($(this).attr('disabled')) return false;
            $(this).blur().attr('disabled', 'disabled');
            $.ajax({
                type: "POST",
                url: "/?do_action=cart.get_excheckout_country",
                dataType: "json",
                success: function (data) {
                    if (data.ret == 1) {
                        var c = data.msg.country;
                        var country_select = '';
                        var defaultCId = 226;
                        var CId = parseInt($('#CId').val());
                        for (i = 0; i < c.length; i++) {
                            if ((!CId && c[i].IsDefault == 1) || CId == c[i].CId) defaultCId = c[i].CId;
                            var s = defaultCId == c[i].CId ? 'selected' : '';
                            var f = c[i].FlagPath ? ' path="' + c[i].FlagPath + '"' : '';
                            var d = $.evalJSON(c[i].CountryData);
                            country_select = country_select + '<option value="' + c[i].CId + '" acronym="' + c[i].Acronym + '" ' + f + s + '>' + (d ? d[ueeshop_config.lang] : c[i].Country) + '</option>';
                        }
                        var ProductPrice = $('form[name=prod_info_form] input[name=ItemPrice]').val();
                        var excheckout_html = '<div id="shipping_cost_choose">';
                        excheckout_html = excheckout_html + '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
                        excheckout_html = excheckout_html + '<div id="choose_content"><form name="shipping_cost_form" target="_blank" method="POST" action="">';
                        excheckout_html = excheckout_html + '<label>' + lang_obj.cart.select_y_country + ': </label>';
                        excheckout_html = excheckout_html + '<select name="CId"><option value="0">' + lang_obj.products.select_country + '</option>' + country_select + '</select>';
                        excheckout_html = excheckout_html + '<ul id="shipping_method_list"></ul>';
                        excheckout_html = excheckout_html + '<p class="footRegion">';
                        excheckout_html = excheckout_html + '<input class="btn btn-success" id="excheckout_button" type="submit" value="OK" />';
                        excheckout_html = excheckout_html + '</p>';
                        excheckout_html = excheckout_html + '<input type="hidden" name="ProductPrice" value="' + ProductPrice + '" /><input type="hidden" name="ShippingMethodType" value="" /><input type="hidden" name="ShippingPrice" value="0" /><input type="hidden" name="ShippingExpress" value="" /><input type="hidden" name="ShippingBrief" value="" /></form></div>';
                        excheckout_html = excheckout_html + '</div>';

                        $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
                        $('body').prepend(excheckout_html);
                        $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});

                        global_obj.div_mask();
                        get_shipping_methods(defaultCId);
                    } else {
                        global_obj.win_alert(lang_obj.products.sign_in, function () {
                            window.top.location = '/account/login.html';
                        });
                    }
                }
            });
        });

        //选择国家操作
        $('html').on('change', 'form[name=shipping_cost_form] select[name=CId]', function () {
            get_shipping_methods($(this).val());
        });

        //选择快递操作
        $('html').on('click', 'form[name=shipping_cost_form] input[name=SId]', function () {
            var price = parseFloat($(this).attr('price'));
            var insurance = parseFloat($(this).attr('insurance'));

            $('form[name=shipping_cost_form] input[name=ShippingMethodType]').val($(this).attr('ShippingType'));
            $('#shipping_method_list li.insurance span.price').text(ueeshop_config.currency_symbols + insurance.toFixed(2));

            $('form[name=shipping_cost_form] input[name=ShippingExpress]').val($(this).attr('method'));
            $('form[name=shipping_cost_form] input[name=ShippingBrief]').val($(this).attr('brief'));
            $('form[name=shipping_cost_form] input[name=ShippingPrice]').val(price.toFixed(2));
        });

        //关闭运费查询
        $('html').on('click', '#choose_close, #div_mask, #exback_button', function () {
            if ($('#shipping_cost_choose').length) {
                $('#shipping_cost_choose').remove();
                global_obj.div_mask(1);
                $('#shipping_cost_button').removeAttr('disabled');
            }
        });

        //提交运费查询
        $('html').on('submit', 'form[name=shipping_cost_form]', function () {
            var obj = $('form[name=shipping_cost_form]');
            obj.find('input[type=submit]').attr('disabled', 'disabled').blur();
            if (!obj.find('input[name=SId]:checked').val() && obj.find('input[name=ShippingMethodType]').val() == '') {
                alert('Please select a shipping method!');
                $('#excheckout_button').removeAttr('disabled');
                return false;
            }

            var shipping_price = $('form[name=shipping_cost_form] input[name=ShippingPrice]').val();
            if (shipping_price > 0) {
                $('#shipping_cost_price').css('display', '').text(ueeshop_config.currency_symbols + shipping_price);
                $('#shipping_cost_freeshipping').css('display', 'none');
            } else {
                $('#shipping_cost_freeshipping').css('display', '');
                $('#shipping_cost_price').css('display', 'none').text(' ');
            }
            $('#delivery_time_processing').text($('form[name=shipping_cost_form] input[name=ShippingBrief]').val());
            var $selectObj = $('form[name=shipping_cost_form] select[name=CId] option:selected');
            var country_name = $selectObj.text();
            var acronym_name = $selectObj.attr('acronym');
            var flagpath = $selectObj.attr('path');
            var express_name = $('form[name=shipping_cost_form] input[name=ShippingExpress]').val();
            $('#CId').val($('form[name=shipping_cost_form] select[name=CId]').val());
            $('#CountryName').val(country_name);
            $('#CountryAcronym').val(acronym_name);
            $('#ShippingId').val($('form[name=shipping_cost_form] input[name=SId]:checked').val());

            $('#shipping_cost_choose').hide();
            if (flagpath) {
                $('#shipping_flag').removeClass().html('<img src="' + flagpath + '" />');
            } else {
                $('#shipping_flag').removeClass().addClass('icon_flag flag_' + acronym_name.toLowerCase());
            }
            $('#shipping_cost_button').text(country_name + ' Via ' + express_name).attr('title', country_name + ' Via ' + express_name).removeAttr('disabled');
            global_obj.div_mask(1);

            return false;
        });

        $CId = $('#CId').val();
        get_shipping_methods($CId, 1);
        /**** 运费查询 End ****/
    },
        get_shipping_methods = function (CId, Method) {
            $.post('/?do_action=cart.get_shipping_methods', 'CId=' + CId + '&Type=shipping_cost&ProId=' + $('#ProId').val() + '&Qty=' + $('#quantity').val() + '&Attr=' + $('#attr_hide').val(), function (data) {
                if (data.ret == 1) {
                    var v = data.msg.info;
                    var str = shipType = shipMethod = '';
                    var shipPrice = 0;
                    var SId = parseInt($('#ShippingId').val());
                    for (i = 0; i < v.length; i++) {
                        if ((v[i].type == '' && ((!SId && i == 0) || SId == v[i].SId)) || (v[i].type != '' && i == 0)) {
                            var sed = 'checked';
                            SId = v[i].SId;
                            if (Method) {
                                shipType = v[i].type;
                                shipMethod = v[i].Name;
                                ShippingInsurance = 1;
                                shipPrice = parseFloat(v[i].ShippingPrice);
                                insurance = parseFloat(v[i].InsurancePrice);
                                shipBrief = v[i].Brief;
                            }
                        } else {
                            var sed = '';
                        }
                        str = str + '<li name="' + v[i].Name.toUpperCase() + '"><label for="__SId__' + i + '">';
                        str = str + '<span><input type="radio" id="__SId__' + i + '" name="SId" value="' + v[i].SId + '" method="' + v[i].Name + '" price="' + v[i].ShippingPrice + '" brief="' + v[i].Brief + '" insurance="' + v[i].InsurancePrice + '" ShippingType="' + v[i].type + '" ' + sed + ' /></span>';
                        str = str + '<strong>' + v[i].Name + '</strong>';
                        str = str + '<span>' + v[i].Brief + '</span>';
                        if (v[i].Name.toUpperCase() == 'DHL' && v[i].Shipping != 1000 && v[i].IsAPI == 1) {
                            if (v[i].ShippingPrice > 0) {
                                str = str + '<span class="price">--</span>';
                            } else str = str + '<span class="price">' + lang_obj.products.free_shipping + '</span>';
                        } else {
                            str = str + '<span class="price">' + (v[i].ShippingPrice > 0 ? ueeshop_config.currency_symbols + v[i].ShippingPrice : lang_obj.products.free_shipping) + '</span>';
                        }
                        str = str + '</label></li>';
                    }

                    if (Method) {
                        if (!shipMethod) {
                            shipType = v[0].type;
                            shipMethod = v[0].Name;
                            ShippingInsurance = 1;
                            shipPrice = parseFloat(v[0].ShippingPrice);
                            insurance = parseFloat(v[0].InsurancePrice);
                            shipBrief = v[0].Brief;
                        }
                        shipPrice = shipPrice.toFixed(2);
                        if (shipPrice > 0) {
                            $('#shipping_cost_price').css('display', '').text(ueeshop_config.currency_symbols + shipPrice);
                            $('#shipping_cost_freeshipping').css('display', 'none');
                        } else {
                            $('#shipping_cost_freeshipping').css('display', '');
                            $('#shipping_cost_price').css('display', 'none').text(' ');
                        }
                        $('#delivery_time_processing').text(shipBrief);
                        $('#shipping_flag').addClass('flag_' + $('#CountryAcronym').val().toLowerCase());
                        $('#shipping_cost_button').text($('#CountryName').val() + ' Via ' + shipMethod).attr('title', $('#CountryName').val() + ' Via ' + shipMethod);
                        $('#ShippingId').val(SId);
                    } else {
                        if (str != '') {
                            var checkObj = $(str).find('input[name=SId]:checked');
                            if (checkObj.attr('method') != shipMethod) {
                                shipType = checkObj.attr('ShippingType');
                                shipMethod = checkObj.attr('method');
                                ShippingInsurance = 1;
                                shipPrice = parseFloat(checkObj.attr('price'));
                                insurance = parseFloat(checkObj.attr('insurance'));
                                shipBrief = checkObj.attr('brief');
                            } else {
                                shipType = v[0].type;
                                shipMethod = v[0].Name;
                                ShippingInsurance = 1;
                                shipPrice = parseFloat(v[0].ShippingPrice);
                                insurance = parseFloat(v[0].InsurancePrice);
                                shipBrief = v[0].Brief;
                            }
                        } else {
                            str = str + '<li><strong>' + lang_obj.products.no_optional + '!</strong></li>';
                        }

                        $('form[name=shipping_cost_form] input[name=ShippingExpress]').val(shipMethod);
                        $('form[name=shipping_cost_form] input[name=ShippingMethodType]').val(shipType);
                        $('form[name=shipping_cost_form] input[name=ShippingPrice]').val(shipPrice.toFixed(2));
                        $('form[name=shipping_cost_form] input[name=ShippingBrief]').val(shipBrief);
                        $('#shipping_method_list').html(str);
                    }
                } else {
                    $('#shipping_method_list').html('');
                }
            }, 'json');
        }

    a();

    $('.FontPicArrowColor').css('border-color', 'transparent transparent ' + $('.FontColor').css('color') + ' transparent');
    $('.FontPicArrowXColor').css('border-color', 'transparent transparent transparent ' + $('.FontColor').css('color'));
    $('.prod_description .desc>table').css('width', '100%');

})(jQuery);