/*
 * 广州联雅网络
 */

jQuery(function($){
	
	$.fn.extend({
		//总价格整理
		get_price:function(count){
			var p=parseFloat($("#ItemPrice").val()),//目前单价
				old=parseFloat($("#ItemPrice").attr('old')),//目前市场价
				curP=parseFloat($("#ItemPrice").attr("initial")),//产品会员价
				salesP=parseFloat($("#ItemPrice").attr("sales")),//是否开启产品促销
				disCount=parseInt($("#ItemPrice").attr("discount")),//产品促销折扣
				attr_hide=$.evalJSON($("#attr_hide").val()),//属性
				attr_len=$("#attr_hide").val().split(",").length,
				ext_attr=$.evalJSON($("#ext_attr").val()),//扩展属性
				IsCombination=parseInt($("#IsCombination").val()),//是否开启规格组合
				price=0,
				wholesalePrice=0,
				wholesaleDiscount=0,
				_value=0,
				i, s='';
			if($(".wholesale_list").length) var wholesale_attr=$.evalJSON($(".wholesale_list").attr("data"));
			
			if(wholesale_attr && !salesP){//加入批发价
				for(k in wholesale_attr){
					if(count<parseInt(k)){
						break;
					}else{
						wholesalePrice=p=parseFloat(wholesale_attr[k]);
						wholesaleDiscount=parseFloat($('.wholesale_list dd[data-num='+k+'] .wprice').attr('data-discount'));
					}
				}
				if(!wholesalePrice || curP<wholesalePrice) p=curP;
			}
			$("#ItemPrice").val(p.toFixed(2));
			
			$('.wholesale_list dd').each(function(){
				_value=$(this).find('.wprice').attr('data-price');
				$(this).find('.wprice').text(ueeshop_config.currency_symbols+parseFloat(_value).toFixed(2));
			});
			
			if(attr_len && ext_attr && ext_attr!='[]'){//加入属性价格
				if(IsCombination==1){//规格组合
					i=0;
					for(k in attr_hide){
						s+=(!i?'':'_')+attr_hide[k];
						i+=1;
					}
					if(ext_attr[s] && attr_len==$('.attr_show').length){
						if(parseInt(ext_attr[s][4])){ //加价
							price=parseFloat(ext_attr[s][0]);
						}else{ //价格
							p=parseFloat(ext_attr[s][0]);
							wholesaleDiscount && (p*=1-wholesaleDiscount);
							$('.prod_info_wholesale .pw_column').each(function(){
								_value=$(this).find('.pw_td:eq(1)').attr('data-discount');
								$(this).find('.pw_td:eq(1)').text(parseInt(_value*100)+'% Off');
							});
						}
					}
				}else{
					var ext_value='';
					for(k in attr_hide){//循环已勾选的属性参数
						ext_value=ext_attr[attr_hide[k]];
						if(ext_value) price+=parseFloat(ext_value[0]);//固定是加价
					}
				}
			}
			
			cPrice=(p+price).toFixed(2);
		
			if(salesP && disCount){//促销折扣(会员价+属性价格)
				cPrice=cPrice*(disCount/100);
			}
			
			if(!isNaN(cPrice)) $('.cur_price').html('<span>'+ueeshop_config.currency+' '+ueeshop_config.currency_symbols+'</span>'+$('html').currencyFormat((cPrice*parseFloat(ueeshop_config.currency_rate)).toFixed(2), ueeshop_config.currency));
			if($('.price_0').length && $('.last_price .save_price').length){
				var dis=(old-cPrice)/old*100;
				if(dis>0){
					$('.last_price .save_price').show();
					$('.last_price .save_p').text(ueeshop_config.currency_symbols+$('html').currencyFormat((old-cPrice).toFixed(2), ueeshop_config.currency));
					$('.last_price .save_style').text('('+parseInt((dis>0 && dis<1)?1:dis)+'% Off)');
				}else{
					$('.last_price .save_price').hide();
				}
			}
			
			$CId=$('#CId').val();
		},
		
		//购买数量增减
		set_amount:function(e){
			var t=this,
				n=t.find("#quantity");
				
			t.on("blur", "#quantity", function(){
				e=$.evalJSON($(".prod_info_qty").attr("data"));
				if(!e) e=$.extend({min:1,max:999,count:1});
				var num=parseInt($(this).val(), 10);
				if(!/^\d+$/.test($(this).val())){
					//$('html').tips_box('Quantity entered must be a number!', 'error');
					$(this).val(e.count);
				}
				//return $(this).val()==""?e.count:isNaN(num)||e.min>num||num>e.max?($('html').tips_box(lang_obj.products.warning_number, 'error'), n.val(e.count), t.get_price(e.count), !1):(e.count=num, void 0);
				if($(this).val()==""){
					return e.count;
				}else{
					var Max=parseInt($('#quantity').attr('data-stock'));
					if(isNaN(num) || e.min>num || num>Max || num>e.max){
						if(num<e.min){ //低过起订量
							e.count=e.min;
							$('html').tips_box(lang_obj.products.warning_MOQ, 'error');
						}else if(num>Max){ //高过最大购买数量
							e.count=Max;
							$('html').tips_box(lang_obj.products.warning_Max.replace('%num%', Max), 'error');
						}else if(num>e.max){ //高过库存
							e.count=e.max;
							$('html').tips_box(lang_obj.products.warning_stock.replace('%num%', e.count), 'error');
						}else{ //不是数字
							$('html').tips_box(lang_obj.products.warning_number, 'error');
						}
					}else{
						e.count=num;
						return void 0;
					}
					n.val(e.count);
					t.get_price(e.count);
					return !1;
				}
			}).on("keyup", "#quantity", function(){
				t.get_price($(this).val());
			}).on("click", ".add, .cut", function(){
				e=$.evalJSON($(".prod_info_qty").attr("data"));
				if(!e) e=$.extend({min:1,max:999,count:1});
				var num=parseInt(n.val(), 10);
				var value=$(this).hasClass('add')?1:-1;
				var Max=parseInt($('#quantity').attr('data-stock'));
				num = num?num:1;
				num += value;
				if(num<e.min){ //低过起订量
					num=e.min;
					$('html').tips_box(lang_obj.products.warning_MOQ, 'error');
				}else if(num>Max){ //高过最大购买数量
					num=Max;
					$('html').tips_box(lang_obj.products.warning_Max.replace('%num%', Max), 'error');
				}else if(num>e.max){ //高过库存
					num=e.max;
					$('html').tips_box(lang_obj.products.warning_stock.replace('%num%', e.count), 'error');
				}
				n.val(num);
				t.get_price(num);
			});
		},
		
		//检查当前属性库存的情况
		check_stock:function(){
			var attr_len=$('.attr_show').length,
				ext_attr=$.evalJSON($("#ext_attr").val()),//扩展属性
				$attrStock=parseInt($("#attrStock").val()),
				tagName=this.get(0).tagName.toLowerCase();
			
			if(attr_len && $attrStock){ //开启了0是库存为空的设定
				var ext_ary=new Object, ary=new Object, cur, stock_ary=new Object;
				for(k in ext_attr){
					ary=k.split('_');
					for(k2 in ary){
						if(!stock_ary[ary[k2]]) stock_ary[ary[k2]]=0;
					}
					if(ext_attr[k][1]>0){
						for(k2 in ary){
							if(ary.length!=attr_len) continue;
							stock_ary[ary[k2]]+=1;
						}
					}
				}
				for(k in stock_ary){
					if(stock_ary[k]<1){
						if(this.find('span[value='+k+']').length) this.find('span[value='+k+']').addClass('out_stock out_stock_fixed');
					}else{
						if(this.find('span[value='+k+']').length) this.find('span[value='+k+']').removeClass('out_stock out_stock_fixed');
					}
				}
			}
		}
	});
	
	set_amount=$('.prod_info_qty').set_amount();
	
	//检查当前所有属性库存的情况
	if($('attr_show').length){
		$('.attr_show').check_stock();
	}
	
	//属性事件
	var num, attr_id,
		attr_ary		= new Object,
		goods_form		= $('#goods_form'),
		attr_select		= $(".attr_select", goods_form),
		cart_tips		= $('#cart_tips'),
		cart_arrt_tips	= $('#cart_arrt_tips'),
		attr_hide		= $("#attr_hide"),
		attr_len		= $(".gr .select").length,
		ext_attr		= $.evalJSON($("#ext_attr").val()),//扩展属性
		$attrStock		= parseInt($("#attrStock").val());
	
	var o={
		goods_form: $('#goods_form'),
		prod_info_name: $('.prod_info_name'),
		attr_show: $('.attr_show'),
		prod_info_qty: $('.prod_info_qty'),
		quantity: $('#quantity'),
		prod_info_actions: $('.prod_info_actions'),
		cart_tips: $('#cart_tips'),
		cart_arrt_tips: $('#cart_arrt_tips'),
		attr_hide: $("#attr_hide"),
		ext_attr: $("#ext_attr"),
		attrStock: $("#attrStock"),
		is_combination: $('#IsCombination')
	};
	
	var VId, attr_id, attr_ary=new Object,
		attr_len=o.attr_show.length,
		ext_attr=$.evalJSON(o.ext_attr.val()),//扩展属性
		$attrStock=parseInt(o.attrStock.val()),
		//attrSelected=parseInt($("ul.attributes").attr('default_selected')),//默认选择
		//$sku_box=$(".prod_info_sku"),//SKU显示
		$defaultStock=parseInt(o.quantity.attr('data-stock')),//产品默认库存
		$IsCombination=parseInt(o.is_combination.val());//是否开启规格组合
	
	o.attr_show.on("click ontouchstart", "span", function(e){//增加ipad触屏事件
		e.preventDefault();
		if($(this).hasClass("out_stock")){return false;}
		var $this=$(this),
			$obj=$this.parents(".attr_show").find("input");
		
		if($this.hasClass("selected")){//取消操作
			$this.removeClass("selected");
			VId='';
		}else{//勾选操作
			$this.parent().find('span').removeClass('form_select_tips');
			$this.addClass("selected").siblings().removeClass("selected");
			VId=$(this).attr("value");
		}
		$obj.val(VId);
		attr_id=$obj.attr("attr");
		if(attr_hide.val() && attr_hide.val()!='[]'){
			attr_ary=$.evalJSON(attr_hide.val());
		}
		if(VId){
			attr_ary[attr_id]=VId;
		}else{//选择默认选项，清除对应ID
			delete attr_ary[attr_id];
		}
		attr_hide.val($.toJSON(attr_ary));
		
		//库存显示
		var i=stock=0,
			cur_attr='';
		for(k in attr_ary){
			cur_attr+=(i?'_':'')+attr_ary[k];
			++i;
		}
		if(cur_attr && ext_attr[cur_attr]){
			stock=(!$attrStock && ext_attr[cur_attr][1]<1)?999:ext_attr[cur_attr][1];
			stock=parseInt($('input[name=SId]').val())?$('input[name=SId]').attr('stock'):stock;
			$('#inventory_number').text(stock);
		}else{
			$('#inventory_number').text(0);//还原库存
		}
		if($IsCombination==0){//关闭规格组合，固定显示默认库存
			stock=$defaultStock;
			$('#inventory_number').text($defaultStock);
		}
		
		if($attrStock && $IsCombination==1){
			if(attr_hide.val()=='[]' || attr_hide.val()=='{}'){//组合属性都属于默认选项
				$('.attr_show').check_stock(); //检查当前所有属性库存的情况
				$('#inventory_number').text($defaultStock);//还原库存
				stock=$defaultStock;
			}else if(ext_attr && ext_attr!='[]'){//判断组合属性库存状态
				var select_ary=new Array, i=-1, ext_ary=new Object, ary=new Object, cur, no_stock_ary=new Object;
				for(k in attr_ary){
					select_ary[++i]=attr_ary[k];
				}
				if(select_ary.length == attr_len-1){ //勾选数 比 属性总数 少一个
					var no_attrid=0, attrid=0, _select_ary, key;
					$('.attr_show').each(function(){
						attrid=$(this).find('.attr_value').attr('attr');
						if(!attr_ary[attrid]){
							no_attrid=attrid; //没有勾选的属性ID
						}
					});
					$('#attr_'+no_attrid).siblings('span').each(function(){
						value=$(this).attr('value');
						_select_ary=new Array;
						for(k in select_ary){
							_select_ary[k]=select_ary[k];
						}
						_select_ary[select_ary.length]=value;
						_select_ary.sort(function(a, b){ return a - b });
						key=_select_ary.join('_');
						if(ext_attr[key][1]==0){
							if($('span[value='+value+']').length) $('span[value='+value+']').addClass('out_stock');
						}else{
							if($('span[value='+value+']').length) $('span[value='+value+']').removeClass('out_stock');
						}
						if(VId==''){ //取消操作
							$('.attr_show').each(function(){
								if($(this).find('.attr_value').attr('attr')!=attr_id){
									$(this).find('span.out_stock').not('.out_stock_fixed').removeClass('out_stock');
								}
							});
						}
					});
				}else if(select_ary.length == attr_len && attr_len!=1){ //勾选数 跟 属性总数 一致
					for(k in ext_attr){
						ary=k.split('_');
						for(k2 in ary){
							if(!no_stock_ary[ary[k2]]) no_stock_ary[ary[k2]]=0;
						}
						cur=0;
						for(k2 in select_ary){
							if(global_obj.in_array(select_ary[k2], ary) && ary.length==attr_len){ //找出包含自身的关联项数据，不一致的属性数量数据也排除掉
								++cur;
							}
						}
						if(cur && cur>=(select_ary.length-1) && select_ary.length==attr_len){ //“数值里已有的选项数量”跟“已勾选的选项数量”一致
							if(ext_attr[k][1]==0){
								for(k2 in ary){
									if(global_obj.in_array(ary[k2], select_ary)) continue;
									if(!no_stock_ary[ary[k2]]){
										no_stock_ary[ary[k2]]=1;
									}else{
										no_stock_ary[ary[k2]]+=1;
									}
								}
							}
						}
					}
					for(k in no_stock_ary){
						if(!global_obj.in_array(k, select_ary) && no_stock_ary[k]>0){
							if($('span[value='+value+']').length) $('span[value='+k+']').addClass('out_stock');
						}else{
							if($('span[value='+value+']').length) $('span[value='+k+']').removeClass('out_stock');
						}
					}
				}else{ //勾选数 大于 1
					$('.attr_show').each(function(){
						$(this).find('span.out_stock').not('.out_stock_fixed').removeClass('out_stock');
					});
				}
			}
		}
		
		qty_data=$.evalJSON(o.prod_info_qty.attr("data"));
		if(parseFloat(qty_data.max)!=stock){//更新属性库存
			if(!stock || stock<1) stock=$defaultStock;
			qty_data.max=stock;
			o.prod_info_qty.attr("data", $.toJSON(qty_data));
		}
		var Max=parseInt($("#quantity").attr('data-stock'));
		if(parseInt(o.quantity.val())>Max){ //最大购买量
			$('html').tips_box(lang_obj.products.warning_MOQ.replace('%num%', Max), 'error');
			o.quantity.val(Max);
		}else if(parseInt(o.quantity.val())>parseInt(qty_data.max)){ //库存
			$('html').tips_box(lang_obj.products.warning_stock.replace('%num%', qty_data.max), 'error');
			o.quantity.val(qty_data.max);
		}
		o.quantity.get_price(o.quantity.val());
		
		if($(this).parent().find('.attr_value').hasClass('colorid')){//颜色图片属性
			$.ajax({
				url:"/static/themes/default/mobile/products/goods_detail_pic.php",
				async:false,
				type:'get',
				data:{"ProId":$("#ProId").val(), "ColorId":$(".colorid").length?$(".colorid").val():$(".attr_value").val()},
				dataType:'html',
				success:function(result){
					if(result){
						$(".detail_pic").html(result);
						goods_pic();
						//small_pic();
					}
				}
			});
		}
	});
	
	//加入购物车
	var addcart=true;
	$('#buynow_button').on('click', function(){
		if(addcart){
			var attr_null=0;
			o.goods_form.submit(function(){ return false; });
			o.attr_show.find(".attr_value").each(function(){
				if(!$(this).val()){
					attr_null++;
				}
			});
			if(attr_null){
				$(window).scrollTop(o.prod_info_name.offset().top);
				$('html').tips_box(lang_obj.cart.plz_sel_para, 'error');
				return;
			}
			addcart=false;
			$.post('/?do_action=cart.additem', o.goods_form.serialize()+'&IsBuyNow=1&back=1', function(data){
				addcart=true;
				if(data.ret==1){
					parseInt(ueeshop_config.FbPixelOpen)==1 && $('html').fbq_addtocart();
					window.top.location.href=data.msg;
				}else{
					window.top.location.href='/account/';
				}
			}, 'json');
		}
	});
	
	$('#addtocart_button').on('click', function(){
		if(addcart){
			var attr_null=0;
			o.goods_form.submit(function(){ return false; });
			o.attr_show.find(".attr_value").each(function(){
				if(!$(this).val()){
					attr_null++;
				}
			});
			if(attr_null){
				$(window).scrollTop(o.prod_info_name.offset().top);
				$('html').tips_box(lang_obj.cart.plz_sel_para, 'error');
				return false;
			}
			var offset	= $('#foot_menu .cart_icon').offset(),//$('header aside .i3').offset()
				btnLeft	= $(this).offset().left+$(this).outerWidth(true)/3,
				btnTop	= $(this).offset().top-$(this).outerHeight(true)-30,
				flyer	= $('<div></div>');
				addcart	= false;
			$.post('/?do_action=cart.additem', o.goods_form.serialize()+'&back=1', function(data){
				if(data.ret==1){
					parseInt(ueeshop_config.FbPixelOpen)==1 && $('html').fbq_addtocart();
					flyer.fly({start:{left:btnLeft, top:btnTop, width:50, height:50}, end:{left:offset.left, top:offset.top, width:20, height:20}}, function(){
						$('header .i3 .cart_count').text(data.msg.qty);
						//$('html').tips_box(lang_obj.cart.cart_tips, 'success');
						$('#tips_cart').show().find('.tips_cart_count').text(data.msg.qty);
						$('#tips_cart').find('.tips_cart_total').text(ueeshop_config.currency+' '+ueeshop_config.currency_symbols+$('html').currencyFormat(data.msg.price.toFixed(2), ueeshop_config.currency));
						global_obj.div_mask();
						addcart=true;
					});
				}else{
					addcart=true;
					if(data.msg){
						$('html').tips_box(data.msg, 'error');
					}else{
						window.location.href='/account/';
					}
				}
			}, 'json');
		}
	});
	
	$('html').on('click', '#div_mask, .btn_return', function(){ //提示窗关闭事件
		$('#tips_cart').hide();
		global_obj.div_mask(1);
	});
	
	$(document).scroll(function(){
		var $winTop			= $(window).scrollTop(),
			$actionsTop		= o.prod_info_actions.offset().top,
			$actionsHeight	= o.prod_info_actions.outerHeight();
		
		if($winTop>$actionsTop+$actionsHeight && $('#goods_cart_btn:hidden').length){
			$('#goods_cart_btn').slideDown();
		}
		if($winTop<$actionsTop+$actionsHeight && $('#goods_cart_btn:visible').length){
			$('#goods_cart_btn').slideUp();

		}
	});
	//加入购物车 结束
	
	//添加收藏夹
	$(".add_favorite").on('click', function(){
		var ProId=$(this).attr("data");
		$.get('/account/favorite/add'+ProId+'.html', function(data){
			if(data.ret==1 || data.ret==0){
				if(data.ret==1){
					$('html').tips_box(lang_obj.user.favorite_success, 'success');
					if(parseInt(ueeshop_config.FbPixelOpen)==1){
						//When a product is added to a wishlist.
						fbq('track', 'AddToWishlist', {content_ids:'['+data.msg.Num+']', content_name:data.msg.Name, currency:data.msg.Currency});
					}
				}else{
					$('html').tips_box(lang_obj.user.favorite_saved, 'success');
				}
			}else{
				window.top.location.href='/account/';
			}
		}, 'json');
	});
	
	//产品详细页折扣倒计时
	$(".discount_count").find(".discount_time").each(function(){
		var time=new Date();
		$(this).genTimer({
			beginTime: ueeshop_config.date,
			targetTime: $(this).attr("endTime"),
			callback: function(e){
				this.html(e)
			}
		});
	});
	
	$.ajax({
		url:"/static/themes/default/mobile/products/goods_detail_pic.php",
		async:false,
		type:'get',
		data:{"ProId":$("#ProId").val(), "ColorId":$(".colorid").length?$(".colorid").attr("attr")+''+$(".colorid").val():$(".attr_value").attr("attr")+''+$(".attr_value").val()},
		dataType:'html',
		success:function(result){
			if(result){
				$(".detail_pic").html(result);
			}
		}
	});
	
	/*
	//小图拨动
	function small_pic(){
		var goods_small_pic = $('.goods_small_pic');
		var win_w =$(window).width()>=640?640:$(window).width();
		var pic_w = win_w*(90/640);
		var mar_w = win_w*(19/640);
		var pic = $('.goods_small_pic .pic');
		var list = $('.goods_small_pic .list');
		pic.css({'height':pic_w, 'width':pic_w, 'margin-left':mar_w, 'margin-right':mar_w});
		goods_small_pic.css('display', 'block');
		var listW = Math.ceil(pic.outerWidth(true))*pic.length;
		list.width(listW);
		touch_nav(list, listW, win_w);
	}
	*/
	
	//切换图片
	function goods_pic(){
		//切换图片
		var goods_pic	= $('.goods_pic'),
			olist		= $('.goods_pic ul'),
			oitem		= $('li', olist),
			pic			= $('img', oitem),
			oitemLen	= oitem.length,
			boxW		= $(window).width(),
			small_pic	= $('.goods_small_pic .pic');
		var point = $('.goods_pic .point span');
        olist.css({'width':boxW*oitemLen, 'display':'block'});
		oitem.css('width', boxW);
        
		if(oitemLen>1){
			//********** 拨动切换 **************
			var startX		= 0,
				endX		= 0,
				disX		= 0,//偏移量
				basicX		= boxW*0.15,//偏移量小于此值时还原
				i			= 0,
				startML		= 0,
				str			= '',
				startPos	= {},
				MovePos		= {},
				isScrolling	= 0;
			
			olist.get(0).ontouchstart=function(e){
				startX=e.touches[0].pageX;
				this.style.MozTransitionDuration=this.style.webkitTransitionDuration=0;
				startPos={x:e.touches[0].pageX, y:e.touches[0].pageY, time:+new Date};
				isScrolling=0;
			};
			olist.get(0).ontouchmove=function(e){
				goods_pic.css('background-image', 'none');
				if(e.targetTouches.length>1 || e.scale && e.scale!==1) return;
				MovePos={x:e.touches[0].pageX-startPos.x, y:e.touches[0].pageY-startPos.y};
				isScrolling=Math.abs(MovePos.x)<Math.abs(MovePos.y)?1:0;
				if(isScrolling==0){
					e.preventDefault();
					disX=e.touches[0].pageX-startX;
					startML=-i*boxW;
					this.style.MozTransform=this.style.webkitTransform='translate3d('+(startML+disX)+'px,0,0)';
					this.style.msTransform=this.style.OTransform='translateX('+(startML+disX)+'px)';
				}
			};
			olist.get(0).ontouchend=function(e){
				var _x=Math.abs(disX);
				if(_x>=basicX){
					if(disX>0){//右移
						i--;
						if(i<0){
							i=0;
						}
					}else{//左移
						i++;
						if(i>=oitemLen){
							i=oitemLen-1;
						}
					}
				}
				point.eq(i).addClass('on').siblings('span').removeClass('on');
				//small_pic.eq(i).addClass('on').siblings('.pic').removeClass('on');
				this.style.MozTransitionDuration=this.style.webkitTransitionDuration='0.3s';
				this.style.MozTransform=this.style.webkitTransform='translate3d('+ -(i*boxW) +'px,0,0)';
				this.style.msTransform=this.style.OTransform='translateX('+ -(i*boxW) +'px)';
				startX=disX=_x=0;
			};
			//*********** 拨动切换 结束 ***********
			
			//*********** 点击切换 **********
			/*small_pic.on('click', function(e){
				i=small_pic.index(this);
				small_pic.eq(i).addClass('on').siblings('.pic').removeClass('on');
				olist.get(0).style.MozTransitionDuration=olist.get(0).style.webkitTransitionDuration='0.3s';
				olist.get(0).style.MozTransform=olist.get(0).style.webkitTransform='translate3d('+ -(i*boxW) +'px,0,0)';
				olist.get(0).style.msTransform=olist.get(0).style.OTransform='translateX('+ -(i*boxW) +'px)';
			});*/
		}
		
		if(window.innerWidth){
			windowWidth=window.innerWidth;
		}else if((document.body) && (document.body.clientWidth)){
			windowWidth=document.body.clientWidth;
		}
		if(windowWidth>414) windowWidth=414;
		$('.goods_pic li').css({'height':windowWidth, 'line-height':windowWidth+'px'}).find('img').css({'max-height':windowWidth});
	}
	(function(){
		goods_pic();
		$(window).resize(function(){
			goods_pic();
		});
	})();
	
	$('.wrapper .detail_desc img').each(function(){
		$(this).removeAttr('width').removeAttr('height').css({'max-width':'', 'width':'auto', 'height':'auto'});
	});
	
	/************************* 产品弹窗 Start *************************/
	/*
	$('#detail_whole').on('click', function(){
		var H=$(window).height();
		$('#detail_whole_layer').css({'height':H}).addClass('show').fadeIn(500).animate('', 500, function(){
			$(this).css({'position':'absolute'});
			$(document.body).css({'height':H, 'overflow':'hidden'});
			$('#header_fix, .crumb, .detail_pic, .goods_info, .detail_desc, footer, #goods_cart_btn').hide();
		});
	});
	*/
	$('#detail_shipping').on('click', function(){
		var H=$(window).height();
		$('#detail_shipping_layer').css({'height':H}).addClass('show').fadeIn(500).animate('', 500, function(){
			$(this).css({'position':'absolute'});
			$(document.body).css({'height':H, 'overflow':'hidden'});
			$('#header_fix, .crumb, .detail_pic, .goods_info, .detail_desc, footer, #goods_cart_btn').hide();
			$CId=$('#CId').val();
			get_shipping_methods($CId);
		});
	});
	
	$('.prod_layer').on('click', '.layer_back', function(){
		var W=$(window).width(),
			$obj=$('.prod_layer');
		if($obj.hasClass('show')){//已开启
			$obj.removeClass('show').fadeOut(500).css({'position':'fixed', 'height':'100%'});
			$(document.body).css({'height':'auto', 'overflow':'auto'});
			$('#header_fix, .crumb, .detail_pic, .goods_info, .detail_desc, footer, #goods_cart_btn').show();
			$('body,html').scrollTop($('.detail_list').offset().top-200);
		}
	});
	/************************* 产品弹窗 End *************************/
	
	/************************* 运费查询 Start *************************/
	//选择国家操作
	$('body').on('change', 'form[name=shipping_cost_form] select[name=CId]', function(){
		get_shipping_methods($(this).val());
	});
	
	//选择快递操作
	$('body').on('click', '#shipping_method_list>li', function(){
		$(this).addClass('current').siblings().removeClass('current');
		$('form[name=shipping_cost_form] input[name=ShippingSId]').val($(this).attr('sid'));
		$('form[name=shipping_cost_form] input[name=ShippingMethodType]').val($(this).attr('ShippingType'));
		$('form[name=shipping_cost_form] input[name=ShippingPrice]').val(parseFloat($(this).attr('price')).toFixed(2));
		$('form[name=shipping_cost_form] input[name=ShippingExpress]').val($(this).attr('method'));
		$('form[name=shipping_cost_form] input[name=ShippingBrief]').val($(this).attr('brief'));
	});
	
	//提交运费查询
	$('body').on('submit', 'form[name=shipping_cost_form]', function(){
		var obj=$('form[name=shipping_cost_form]'),
			btn=$('#excheckout_button');
		btn.attr('disabled', 'disabled').blur();
		if($('#shipping_method_list>li.current').length==0 || (!obj.find('input[name=ShippingSId]').val() && obj.find('input[name=ShippingMethodType]').val()=='')){
			$('html').tips_box('Please select a shipping method!', 'error');
			btn.removeAttr('disabled');
			return false;
		}
		var shipping_price=$('form[name=shipping_cost_form] input[name=ShippingPrice]').val();
		if(shipping_price>0){
			$('#detail_shipping_cost').css('display', '').text(ueeshop_config.currency_symbols+shipping_price);
			$('#detail_shipping_freeshipping').css('display', 'none');
		}else{
			$('#detail_shipping_freeshipping').css('display', '');
			$('#detail_shipping_cost').css('display', 'none').text(' ');
		}
		$('#detail_shipping_txt>span').text($('form[name=shipping_cost_form] input[name=ShippingBrief]').val());
		var country_name=$('form[name=shipping_cost_form] select[name=CId] option:selected').text();
		var express_name=$('form[name=shipping_cost_form] input[name=ShippingExpress]').val();
		$('#CId').val($('form[name=shipping_cost_form] select[name=CId]').val());
		$('#CountryName').val(country_name);
		$('#ShippingId').val($('form[name=shipping_cost_form] input[name=ShippingSId]').val());
		$('#detail_shipping_city').text(country_name+' Via '+express_name).attr('title', country_name+' Via '+express_name).removeAttr('disabled');
		
		btn.removeAttr('disabled');
		$('#detail_shipping_layer .layer_back').click();
		return false;
	});
	
	function get_shipping_methods(CId, Method){
		$.post('/?do_action=cart.get_shipping_methods', 'CId='+CId+'&Type=shipping_cost&ProId='+$('#ProId').val()+'&Qty='+$('input[name=Qty]').val()+'&Attr='+$('#attr_hide').val(), function(data){
			if(data.ret==1){
				var v=data.msg.info;					
				var str=shipType=shipMethod='';
				var shipPrice=0;
				var SId=parseInt($('#ShippingId').val());
				for(i=0;i<v.length;i++){
					if((!SId && i==0) || SId==v[i].SId){
						var sed='class="current"';
						SId=v[i].SId;
						if(Method){
							shipType=v[i].type;
							shipMethod=v[i].Name;
							ShippingInsurance=1;
							shipPrice=parseFloat(v[i].ShippingPrice);
							insurance=parseFloat(v[i].InsurancePrice);
							shipBrief=v[i].Brief;
						}
					}else{
						var sed='';
					}
					str+='<li name="'+v[i].Name.toUpperCase()+'" sid="' + v[i].SId + '" method="' + v[i].Name  + '" price="'+ v[i].ShippingPrice + '" brief="'+ v[i].Brief + '" insurance="'+ v[i].InsurancePrice + '" ShippingType="' + v[i].type + '" ' + sed + '><em></em>';
						str+='<span class="name">' + v[i].Name  + '</span>';
						if(v[i].Name.toUpperCase()=='DHL' && v[i].Shipping!=1000 && v[i].IsAPI==1){
							if(v[i].ShippingPrice>0){
								str+='<span class="price">--</span>';
							}else str+='<span class="price">'+lang_obj.products.free_shipping+'</span>';
						}else{
							str+='<span class="price">' + (v[i].ShippingPrice>0?ueeshop_config.currency_symbols + v[i].ShippingPrice:lang_obj.products.free_shipping) + '</span>';
						}
						str+='<span class="txt">' + v[i].Brief + '</span>';
					str+='</li>';
				}
				
				if(Method){
					if(!shipMethod){
						shipType=v[0].type;
						shipMethod=v[0].Name;
						ShippingInsurance=1;
						shipPrice=parseFloat(v[0].ShippingPrice);
						insurance=parseFloat(v[0].InsurancePrice);
						shipBrief=v[0].Brief;
					}
					shipPrice=shipPrice.toFixed(2);
					if(shipPrice>0){
						$('#detail_shipping_cost').css('display', '').text(ueeshop_config.currency_symbols+shipPrice);
						$('#detail_shipping_freeshipping').css('display', 'none');
					}else{
						$('#detail_shipping_freeshipping').css('display', '');
						$('#detail_shipping_cost').css('display', 'none').text(' ');
					}
					$('#detail_shipping_txt>span').text(shipBrief);
					$('#detail_shipping_city').text($('#CountryName').val()+' Via '+shipMethod).attr('title', $('#CountryName').val()+' Via '+shipMethod);
					$('#ShippingId').val(SId);
				}else{
					if(str!=''){
						//var checkObj=$(str).find('li.current');
						var checkObj=$(str);
						if(checkObj.attr('method')!=shipMethod){
							shipType=checkObj.attr('ShippingType');
							shipMethod=checkObj.attr('method');
							ShippingInsurance=1;
							shipPrice=parseFloat(checkObj.attr('price'));
							insurance=parseFloat(checkObj.attr('insurance'));
							shipBrief=checkObj.attr('brief');
						}else{
							shipType=v[0].type;
							shipMethod=v[0].Name;
							ShippingInsurance=1;
							shipPrice=parseFloat(v[0].ShippingPrice);
							insurance=parseFloat(v[0].InsurancePrice);
							shipBrief=v[0].Brief;
						}
					}else{
						str+='<li><strong>'+lang_obj.products.no_optional+'!</strong></li>';
					}
					
					$('form[name=shipping_cost_form] input[name=ShippingSId]').val(SId);
					$('form[name=shipping_cost_form] input[name=ShippingExpress]').val(shipMethod);
					$('form[name=shipping_cost_form] input[name=ShippingMethodType]').val(shipType);
					$('form[name=shipping_cost_form] input[name=ShippingPrice]').val(shipPrice.toFixed(2));
					$('form[name=shipping_cost_form] input[name=ShippingBrief]').val(shipBrief);
					$('#shipping_method_list').html(str);
				}
			}else{
				$('#shipping_method_list').html('');
			}
		}, 'json');
	}
	
	$CId=$('#CId').val();
	get_shipping_methods($CId, 1);
	/************************* 运费查询 End *************************/
	
	/************************* 产品选项卡 Start *************************/
	$('.detail_desc .t').click(function(){
		if($(this).parent().hasClass('detail_close')){
			$(this).parent().removeClass('detail_close').children('.text').show();
		}else{
			$(this).parent().addClass('detail_close').children('.text').hide();
		}
	});
	/************************* 产品选项卡 End *************************/
});

//倒计时插件
$.fn.genTimer=function(e){
	function u(e){
		var t=Math.floor(e/n),
			r=Math.floor((e-t*n)/36e5),
			i=Math.floor((e-t*n-r*1e3*60*60)/6e4),
			s=Math.floor((e-t*n-r*1e3*60*60-i*1e3*60)/1e3);
		return {hours:("0"+r).slice(-2), minutes:("0"+i).slice(-2), seconds:("0"+s).slice(-2), dates:t}
	}
	
	var t={
			beginTime:new Date,
			day_label:"day",
			days_label:"days",
			unitWord:{days:" ", hours:":", minutes:":", seconds:""},
			type:"day",
			callbackOnlyDatas:!1
		},
		n=864e5,
		r=$.extend({}, t, e),
		i=this;
		
	r.targetTime=r.targetTime.replace(/\-/g, "/");
	var s=new Date(r.targetTime)-new Date(r.beginTime),
	o=function(){
		if(s<0){
			r.callback.call(i, r.callbackOnlyDatas ? {hours:"00", minutes:"00", seconds:"00",dates:0}: "00"+r.unitWord.hours+"00"+r.unitWord.minutes+"00");
			clearInterval(i.interval);
		}else{
			var e=u(s);
			if(r.callbackOnlyDatas) r.callback.call(i, e);
			else if(r.type=="day") s>=n*2 ? r.callback.call(i, '<span class="day_count">'+e.dates+'</span>'+r.unitWord.days+'<span class="day_hours">'+e.hours+'</span>'+r.unitWord.hours+'<span class="day_miniutes">'+e.minutes+'</span>'+r.unitWord.minutes+'<span class="day_senconds">'+e.seconds+r.unitWord.seconds+'</span>') : s>=n ? r.callback.call(i, '<span class="day_count">'+e.dates+'</span>'+r.unitWord.days+'<span class="day_hours">'+e.hours+'</span>'+r.unitWord.hours+'<span class="day_miniutes">'+e.minutes+'</span>'+r.unitWord.minutes+'<span class="day_senconds">'+e.seconds+r.unitWord.seconds+'</span>') : r.callback.call(i, '<span class="day_hours">'+e.hours+'</span>'+r.unitWord.hours+'<span class="day_miniutes">'+e.minutes+'</span>'+r.unitWord.minutes+'<span class="day_senconds">'+e.seconds+r.unitWord.seconds+'</span>');
			else if(r.type=="diffNoDay"){
				var t=e.hours;
				s>=n && (t=Number(e.dates*24)+Number(e.hours));
				r.callback.call(i, '<span class="hours">'+t+'</span><span class="miniutes">'+r.unitWord.hours+e.minutes+'</span><span class="senconds">'+r.unitWord.minutes+e.seconds+r.unitWord.seconds+"</span>");
			}else{
				var t=e.hours;
				s>=n && (t=Number(e.dates*24)+Number(e.hours));
				r.callback.call(i, '<span class="seconds">'+t+r.unitWord.hours+e.minutes+r.unitWord.minutes+e.seconds+r.unitWord.seconds+"</span>");
			}
		}
		s-=1e3
	};
	i.interval=setInterval(o, 1e3);
	o();
	return this
}

