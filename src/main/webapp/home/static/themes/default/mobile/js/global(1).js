/*
 * 广州联雅网络
 */

(function($){

	window.touch_nav=function(pos, posW, win_w){
		var startPos = {};
		var MovePos = {};
		var isScrolling = 0;
		var _mL = 0;

		pos.get(0).ontouchstart = function (e){
			start(e);
		};
		pos.get(0).ontouchmove = function (e){
			move(e);
		}
		pos.get(0).ontouchend = function (e){
			end(e);
		}

		function start(e){
			startPos = {x:e.touches[0].pageX,y:e.touches[0].pageY,time:+new Date};
			isScrolling = 0;
			_mL = parseFloat(pos.css('margin-left'));
		}
		function move(e){
			if(e.targetTouches.length > 1 || e.scale && e.scale !== 1) return;
			MovePos = {x:e.touches[0].pageX - startPos.x,y:e.touches[0].pageY - startPos.y};
			isScrolling = Math.abs(MovePos.x) < Math.abs(MovePos.y) ? 1:0;
			if(isScrolling==1){
				pos.get(0).ontouchmove = function (){};
			}else{
				pos.get(0).ontouchmove = function (e2){
					e2.preventDefault();
					var marL = e2.touches[0].pageX-startPos.x;
					pos.css('margin-left', marL+_mL);
				};
			}
		}
		function end(e){
			_mL = parseFloat(pos.css('margin-left'));
			if (_mL>0){
				pos.animate({marginLeft:0}, 200);
			}else if (posW+_mL<win_w){
				if (posW>win_w){
					pos.animate({marginLeft:win_w-posW}, 200);
				}else{
					pos.animate({marginLeft:0}, 200);
				}
			}
			pos.get(0).ontouchmove = function (e){
				move(e);
			}
		}
	}

	window.touch_move = function (obox, olist, oitem){
		var boxW = obox.width();
		var itemL = oitem.size();
		oitem.css({'width':boxW});
		olist.css({'width':itemL*boxW+1});
		//************************
		var startX = 0;
		var endX = 0;
		var disX = 0;//偏移量
		var basicX = boxW*0.2;//偏移量小于此值时还原
		var i = 0;
		var startML = 0;
		var str = '';
		var startPos = {};
		var MovePos = {};
		var isScrolling = 0;
		olist.get(0).ontouchstart = function (e){
			startX = e.touches[0].pageX;
			this.style.MozTransitionDuration = this.style.webkitTransitionDuration = 0;

			startPos = {x:e.touches[0].pageX,y:e.touches[0].pageY,time:+new Date};
			isScrolling = 0;

		};
		olist.get(0).ontouchmove = function (e){
			if(e.targetTouches.length > 1 || e.scale && e.scale !== 1) return;
			MovePos = {x:e.touches[0].pageX - startPos.x,y:e.touches[0].pageY - startPos.y};
			isScrolling = Math.abs(MovePos.x) < Math.abs(MovePos.y) ? 1:0;
			if(isScrolling==0){
				e.preventDefault();
				disX = e.touches[0].pageX-startX;
				startML = -i*boxW;
				this.style.MozTransform = this.style.webkitTransform = 'translate3d(' + (startML+disX) + 'px,0,0)';
				this.style.msTransform = this.style.OTransform = 'translateX(' + (startML+disX) + 'px)';
			}

		};
		olist.get(0).ontouchend = function (e){
			var _x = Math.abs(disX);
			if (_x>=basicX){
				if (disX>0){//右移
					i--;
					if (i<0){
						i = 0;
					}
				}else{//左移
					i++;
					if (i>=itemL){
						i = itemL-1;
					}
				}
			}
			this.style.MozTransitionDuration = this.style.webkitTransitionDuration = '0.3s';
			this.style.MozTransform = this.style.webkitTransform = 'translate3d(' + -(i*boxW) + 'px,0,0)';
			this.style.msTransform = this.style.OTransform = 'translateX(' + -(i*boxW) + 'px)';
			startX = disX = _x = 0;
		};
	}//touch_move

	Number.prototype.formatMoney=function(places, decimal, thousand){
		places=!isNaN(places=Math.abs(places))?places:2;
		thousand=thousand || ',';
		decimal=decimal || '.';
		var number=this,
			negative=number<0?'-':'',
			i=parseInt(number=Math.abs(+number || 0).toFixed(places), 10)+'',
			j=(j=i.length)>3?j%3:0;
		return negative+(j?i.substr(0, j)+thousand:'')+i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)+(places?decimal+Math.abs(number-i).toFixed(places).slice(2):'');
	};

	//提示框
	$.fn.tips_box=function(tips, type, callback){ //type success:提示成功 error:提示失败 confirm:选择框
		var type=(typeof(arguments[1])=='undefined')?'confirm':arguments[1],
			html='';
		$('#div_mask, .win_alert').remove();//优先清空多余的弹出框
		if(type=='success'){ //提示成功
			html='<div class="tips_success">'+tips+'</div>';
			$('body').prepend(html);
			setTimeout(function(){
				$('.tips_success').fadeOut(1000, function(){ $(this).remove(); });
			}, 1000);
		}else if(type=='error'){ //提示失败
			html='<div class="tips_error attr_null">'+tips+'</div>';
			$('body').prepend(html);
			setTimeout(function(){
				$('.tips_error').fadeOut(1000, function(){ $(this).remove(); });
			}, 1000);
		}else{ //选择框
			global_obj.div_mask();
			html='<div class="tips_confirm">';
				html+='<div class="tips_header ui_border_b"><button class="btn_close">x</button></div>';
				html+='<div class="tips_body"><div class="tips_text">'+tips+'</div></div>';
				html+='<div class="tips_footer">';
					html+='<button class="btn_global btn_cancel">'+lang_obj.global.n_y[0]+'</button><button class="btn_global btn_sure FontBgColor">'+lang_obj.global.n_y[1]+'</button>';
				html+='</div>';
			html+='</div>';
			$('body').prepend(html);
			if(type=='confirm'){
				$('.tips_confirm').delegate('.btn_close, .btn_cancel', 'click', function(){
					$('#div_mask').fadeOut(500, function(){ $(this).remove(); });
					$('.tips_confirm').fadeOut(500, function(){ $(this).remove(); });
				}).delegate('.btn_sure', 'click', function(){
					$.isFunction(callback) && callback();
					$('.tips_confirm .btn_close').click();
				});
			}
		}
		return false;
	},

	//返回顶部
	$.fn.toTop=function(){
		if($(window).scrollTop()>60){
			this.fadeIn().css('display', 'block');
		}else{
			this.fadeOut();
		}
	}

	//loading加载效果
	$.fn.loading=function(e){
		e=$.extend({opacity:.5, size:"big"}, e);
		$(this).each(function(){
			if($(this).hasClass("masked")) return;
			var obj=$(this);
			var l=$('<div class="loading"></div>').css("opacity", 0);
			obj.addClass("masked").append(l);
			var lb=$('<div class="loading_msg loading_big"></div>').appendTo(obj);
			lb.css({
				top: (obj.height() / 2 - (lb.height() + parseInt(lb.css("padding-top")) + parseInt(lb.css("padding-bottom"))) / 2)*0.01+'rem',
				left: (obj.width() / 2 - (lb.width() + parseInt(lb.css("padding-left")) + parseInt(lb.css("padding-right"))) / 2)*0.01+'rem'
			});
		});
		return this;
	}
	//取消loading加载效果
	$.fn.unloading=function(){
		$(this).each(function(){
			$(this).find(".loading_msg, .loading").remove();
			$(this).removeClass("masked");
		});
	}

	//购买流程，跳转到会员登录框或者访客继续付款
	$.fn.loginOrVisitors=function(){
		var obj=$(this), result;
		if(ueeshop_config['TouristsShopping']==0 && ueeshop_config['UserId']==0/* && global_obj.getCookie('loginOrVisitors')!='ok'*/){ //通过
			result=false;
		}else{ //不通过
			result=true;
		}
		return result;
	}

	//计算秒杀价格
	$.fn.seckillPrice=function(){
		var $seckillData='0';
		$('html .price_data').each(function(){
			$proid=$(this).attr('keyid');
			if($proid) $seckillData+=','+$proid;
		});
		if($seckillData!='0'){
			$.post('/', 'do_action=action.seckill&ProId='+$seckillData, function(data){
				if(data.ret==1){
					for(k in data.msg){
						$('.price_data[keyid='+k+']').text(data.msg[k]);
						$('.price_data[keyid='+k+']').parents('.item').find('.icon_seckill').show().siblings('.icon_discount, .icon_discount_foot').hide();
					}
				}
			}, 'json');
		}
	}

	//货币格式显示
	$.fn.currencyFormat=function(price, currency){
		var result=0;
		price=parseFloat(price);
		switch(currency){
			case 'USD':
			case 'EUR':
			case 'GBP':
			case 'CAD':
			case 'AUD':
			case 'CHF':
			case 'HKD':
			case 'ILS':
			case 'MXN':
				result=price.formatMoney(2, '.', ','); break;
			case 'RUB':
				result=price.formatMoney(2, ',', ' '); break;
			case 'BRL':
				result=price.formatMoney(2, ',', '.'); break;
			case 'CLP':
			case 'NOK':
			case 'DKK':
				result=price.formatMoney(0, '', '.'); break;
			case 'JPY':
			case 'SEK':
			case 'KRW':
				result=price.formatMoney(0, '', ','); break;
			default:
				result=price.formatMoney(2, '.', ','); break;
		}
		return result;
	}

	$.fn.extend({
		//添加购物车抛物线插件
		fly:function(t, callback){
			var e = this,
				t = $.extend({autoPlay:!0, vertex_Rtop:20, speed:1.2, start:{}, end:{}, onEnd:$.noop}, t),
				f = $(e),
				obj = {
					init:function(t){
						obj.setOptions(t);
						!!t.autoPlay && obj.move(t);
					},
					setOptions:function(t){
						var c=t,
							s=c.start,
							d=c.end;

						f.css({
							"border-top-left-radius": "50%",
							"border-top-right-radius": "50%",
							"border-bottom-right-radius": "50%",
							"border-bottom-left-radius": "50%",
							"width": 50,
							"height": 50,
							"background-image": "url("+$('.big_pic .normal').attr('src')+")",
							"background-size": "100%",
							"background-repeat": "no-repeat",
							"margin-top": 0,
							"margin-left": 0,
							"position": "absolute",
							"z-index": "1000000"
						}).appendTo("body"),
						null!=d.width && null!=d.height && $.extend(!0, s, {width:f.width(), height:f.height()});

						var h = Math.min(s.top, d.top) - Math.abs(s.left - d.left) / 3;
						h < c.vertex_Rtop && (h = Math.min(c.vertex_Rtop, Math.min(s.top, d.top)));

						var i = Math.sqrt(Math.pow(s.top - d.top, 2) + Math.pow(s.left - d.left, 2)),
							j = Math.ceil(Math.min(Math.max(Math.log(i) / .05 - 75, 30), 100) / c.speed),
							k = s.top == h ? 0 : -Math.sqrt((d.top - h) / (s.top - h)),
							l = (k * s.left - d.left) / (k - 1),
							m = d.left == l ? 0 : (d.top - h) / Math.pow(d.left - l, 2);

						$.extend(!0, c, {count:-1, steps:j, vertex_left:l, vertex_top:h, curvature:m});
					},
					move:function(t){
						var s = t.start,
							len = t.count,
							step = t.steps,
							d = t.end,
							h = s.left + (d.left - s.left) * len / step,
							i = 0 == t.curvature ? s.top + (d.top - s.top) * len / step: t.curvature * Math.pow(h - t.vertex_left, 2) + t.vertex_top;
						if(null != d.width && null != d.height){
							var j = step / 2,
								k = d.width - (d.width - s.width) * Math.cos(j > len ? 0 : (len - j) / (step - j) * Math.PI / 2),
								l = d.height - (d.height - s.height) * Math.cos(j > len ? 0 : (len - j) / (step - j) * Math.PI / 2);
							f.css({width:k+"px", height:l+"px", "font-size":Math.min(k, l)+"px"});
						}

						if(len==-1){
							f.css({left:h+"px", top:i+"px"});
							t.count++;
							obj.move(t);
						}else{
							f.animate({left:h+"px", top:i+"px"}, 5, function(){
								t.count++;
								if(len<step){
									obj.move(t);
								}else{
									t='';
									obj.destory();
								}
							});
						}
					},
					destory:function(){
						f.remove();
						callback();
					}
				}
			obj.init(t);
		}
	});

	$(function(){
		var $to_top=$('.btn_top');
		$to_top.toTop();
		$(window).scroll(function(){
			$to_top.toTop();
		});
		$to_top.on('tap', function(){
			$('html, body').animate({'scrollTop':0}, 400);
		});

		if($('#detail_top').length){
			if($('#header_fill').length){//fixed
				$('#header_fix').css({'top':$('#detail_top').outerHeight()-1});
			}else{
				$('#header_fix').css({'marginTop':$('#detail_top').outerHeight()-1});
			}
		}

		/******************* 导航/底部菜单 start *******************/
		var $container=$('.pop_up_container'),
			$height;
		$('.header_top .menu').on('tap click', function(){
			console.log("in in in")
			$height=$(window).height();
			$('.nav_side').css('visibility', 'visible').addClass('show');
			$('html, html body').css({'height':$height, 'overflow':'hidden'});
			setTimeout(function (){
				$('.menu_list').css('overflow-y', 'scroll');
				$container.css('overflow-y', 'scroll');
			}, 300);
			$('.nav_side .menu_list').css({'max-height':$(window).outerHeight()-$('.nav_side .close').outerHeight(true)-$('.nav_side .search').outerHeight()});
		});

		//搜索框
		$('header .search>form').submit(function(){
			var o=$(this).find('input[type=search]');
			o.removeClass('form_null');
			if(o.val()==''){
				o.addClass('form_null');
				return false;
			}
		});

		//头部导航
		$container.find('.close').on('tap click', function(){
			var o=$(this).parents('.pop_up_container').parent();
			o.removeClass('show');
			setTimeout(function(){
				o.css('visibility', 'hidden');
			}, 40);
			$('.menu_list').css('overflow-y', 'inherit');
			$container.css('overflow-y', 'inherit');
			// if(!o.hasClass('category_side'))
			if( o.hasClass('nav_side') ){
				$('html, html body').css({'height':'auto', 'overflow':'auto'});
			}
		});
		$container.find('.btn_all_category').on('tap click', function(){
			$('.category_side').css('visibility', 'visible').addClass('show');
			var menu_list = $('.menu_list');
			menu_list.css('overflow-y', 'inherit');
			$container.css('overflow-y', 'inherit');
			setTimeout(function (){//不能删
				menu_list.css('overflow-y', 'scroll');
				$container.css('overflow-y', 'scroll');
			}, 300);
			$('.category_side .menu_list').css({'max-height':$(window).outerHeight()-$('.category_side .close').outerHeight(true)-$('.category_side .search').outerHeight()});
		});
		$container.find('.menu_list .item a').on('tap', function(e){
			e.stopPropagation();
        });
		$container.find('div.menu_list .son>a').on('tap', function(){
			//$('html').tips_box("queren")
			var par = $(this).parent();
			if(par.hasClass('open')){
				par.removeClass('open');
				par.find('.menu_son').stop(true, false).slideUp();
			}else{
				par.addClass('open').children('.menu_son').stop(true, false).slideDown();
				par.siblings('.son').removeClass('open').find('.menu_son').stop(true, false).slideUp();
			}
        });
		$container.find('.menu_list .isub .navsub .next').on('tap click', function(){ //下级弹窗
			if($(this).hasClass('on')){
				$(this).removeClass('on');
			}else{
				$('body,html').scrollTop(0);
				$(this).addClass('on');
			}
		});
		$container.find('.menu_list .isub .navsub .nextwd').on('tap click', function(e){
			e.stopPropagation();
		});
		$container.find('.menu_list .isub .navsub .nextwd .nclose').on('tap click', function(){
			$(this).parent().parent().parent().removeClass('on');
		});
		//二级弹窗js
		$container.find('.menu_list .navsub .subitem .nextwd .next0').on('tap click', function(){
			if ($(this).hasClass('on')){
				$(this).removeClass('on');
				$(this).siblings('.next1').stop(true, false).slideUp();
			}else{
				$(this).addClass('on');
				$(this).siblings('.next1').stop(true, false).slideDown();
			}
		});
		$container.find('.menu_list .navsub .subitem .nextwd .t1bg').on('tap click', function(){
			if ($(this).hasClass('on')){
				$(this).removeClass('on');
				$(this).siblings('.next2').stop(true, false).slideUp();
			}else{
				$(this).addClass('on');
				$(this).siblings('.next2').stop(true, false).slideDown();
			}
		});

		//选择语言
		$('.lang_cr .lang').on('tap', function(e){
			$height=$(window).height();
			$('.language_side').css('visibility', 'visible').addClass('show');
			$('html, html body').css({'height':$height, 'overflow':'hidden'});
			$('.language_side .menu_list').css({'max-height':$(window).outerHeight()-$('.language_side .close').outerHeight(true)});
		});
		$('.language_side .menu_list a').on('tap', function(){
			$.get('/home/plt_PltConfig_changeLanguage', 'request_locale='+$(this).attr("lang"), function(data){
				if(data.ret==1)
					location.href = location.href;
			}, 'json');
			return false;
		});

		//选择货币
		$('footer .list_currency>a[class!=noclick], .lang_cr .currency').on('tap', function(e){
			$height=$(window).height();
			$('.currency_side').css('visibility', 'visible').addClass('show');
			$('html, html body').css({'height':$height, 'overflow':'hidden'});
			setTimeout(function (){
				$('.menu_list').css('overflow-y', 'scroll');
				$container.css('overflow-y', 'scroll');
			}, 300);
			$('.currency_side .menu_list').css({'max-height':$(window).outerHeight()-$('.currency_side .close').outerHeight(true)});
		});
		$('.currency_side .menu_list a').on('tap', function(){
			var val=$(this).attr('data');
			$.post('/home/plt_PltConfig_changeCurrency', 'currency='+val, function(data){
				if(data.ret==1)
					window.top.location.reload();
			}, 'json');
			return false;
		});
		/******************* 导航/底部菜单 end *******************/

		//面包屑
		var page_title = $('.page_title');
		var pos = $('.page_title .pos');
		if (pos.length){
			var win_w = _w;
			var posW = parseInt($('.column', pos).outerWidth(true))+parseInt(page_title.css('padding-left'))*2;
			if (posW>page_title.width()){
				touch_nav(pos, posW, win_w);
			}
		}

		//秒杀价格
		$('html').seckillPrice();

		//浮动在线客服
		$('#float_chat .btn_chat').on('click', function(e){
			$('#float_chat .inner_chat').css('margin-top', function(){
				global_obj.div_mask();
				$(this).css('display', 'block');
				return -($(this).outerHeight(true)/2);
			});
		});
		$('#float_chat .chat_close').on('click', function (e){
			global_obj.div_mask(1);
			$('#float_chat .inner_chat').css('display', 'none');
		});
		var live_t = setInterval(function (){
			$('#livechat-compact-container').css('bottom', '4rem');
		}, 500);

		//询盘
        // // $('body').on('click','.inquiry_btn',function(){	//加入询盘篮
        // 	if ($('.inquiry_tips').length){
        // 		return;
        // 	}
        // 	$.post('?do_action=action.add_inquiry', 'ProId='+$(this).attr('data'), function(data){
        // 		if(data.inq_type){
        // 			window.location.href="/inquiry.html";
        // 		}else{
        // 			tips = lang_obj.global.inqadd;
        // 			if(data.status==-1){
        // 				tips = lang_obj.global.already;
        // 			}
        // 			var html = '<div class="inquiry_tips">\
        // 							<div class="c">\
        // 								<div class="title">'+tips+'</div>\
        // 								<a href="/inquiry.html">'+lang_obj.global.inquery+'</a><br><a href="javascript:void(0);" class="close">'+lang_obj.global.continues+'</a>\
        // 							</div>\
        // 						</div>\
        // 						<div class="inquiry_tipsbg"></div>';
        // 			$('body').append(html);
        // 			$('.inquiry_tips .close,.inquiry_tipsbg').off('click').on('click', function (){
        // 				$('.inquiry_tips').remove();
        // 				$('.inquiry_tipsbg').remove();
        // 			});
        // 		}
        // 	},'json');
        // });

	});

})(jQuery);

$(function (){
	//新增
	$('.wrapper').on('click', 'a[data-vip=1]', function (){
		var obj=$('body'),
			w=$(window);
		var signin_html='<div id="signin_module">';
			signin_html=signin_html+'<div class="box_bg"></div>';
			signin_html=signin_html+'<div id="lb-wrapper">';
				signin_html=signin_html+'<div class="pop_qxtitle">You do not have permission to view this product</div>';
				signin_html=signin_html+'<div class="clean popbtn_box"><a class="pop_qxbtn qxclose">Browse other</a><a class="pop_qxbtn" href="/account/setting/">Application</a><div>';
			signin_html=signin_html+'</div>';//id="lb-wrapper"
		signin_html=signin_html+'</div>';//signin_module

		obj.find('#signin_module').length && obj.find('#signin_module').remove();
		obj.prepend(signin_html);
		global_obj.div_mask();
		$('.popbtn_box .qxclose').click(function (){
			$('#signin_module').remove();
			global_obj.div_mask(1);
		});
		return false;
	});
});
