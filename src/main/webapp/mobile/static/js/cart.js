/*
 * Powered by ueeshop.com		http://www.ueeshop.com
 * 广州联雅网络科技有限公司		020-83226791
 */
function trimAll(str) {
		     return str.replace(/\s+/g, "");
		    }
(function($, _w){
	_w.cart_obj={
		cart_list:function(){
			$('.qty_box .cut, .qty_box .add').on('tap', function(){
				var value	= $(this).hasClass('add')?1:-1,
					obj		= $(this).siblings('.qty').find('input'),
					qty		= Math.abs(parseInt(obj.val())),
					CId		= obj.attr('data-cid'),
					ProId	= obj.attr('data-proid'),
					start	= obj.attr('data-start'),
					s_qty	= $(obj).parent().parent().siblings('input[name="S_Qty[]"]').val();
				if(!qty || qty<0 || qty<start){
					$('html').tips_box(lang_obj.products.warning_number, 'error');
				}
				qty=qty?qty:1;
				qty+=value;
				qty=qty>0?qty:1;
				qty<start && (qty=start);
				if(s_qty==qty) return false;
				var query_string='&Qty='+qty+'&CId='+CId+'&ProId='+ProId;
				var cid_str='&CIdAry=';
				if($('.cart_list input[name=select]:checked').length){//部分已选
					cid_str+='0';
					$('.cart_list input[name=select]:checked').each(function(index, element){
						cid_str+=','+$(element).val();
					});
				}
				cart_obj.modify_cart_result(obj, query_string+cid_str, 1);
			});
			$('.qty_box .qty input').on('keyup paste', function(){
				p=/[^\d]/g;
				$(this).val($(this).val().replace(p, ''));
			}).on('blur', function(){
				var obj		= $(this),
					qty 	= Math.abs(parseInt(obj.val())),
					CId 	= obj.attr('data-cid'),
					ProId	= obj.attr('data-proid'),
					start	= obj.attr('data-start'),
					s_qty	= $(obj).parent().parent().siblings('input[name="S_Qty[]"]').val();
				if(!qty || qty<0 || qty<start){
					$('html').tips_box(lang_obj.products.warning_number, 'error');
				}
				qty=qty?qty:1;
				qty=qty>0?qty:1;
				qty<start && (qty=start);
				if(s_qty==qty) return false;
				var query_string='&Qty='+qty+'&CId='+CId+'&ProId='+ProId;
				var cid_str='&CIdAry=';
				if($('.cart_list input[name=select]:checked').length){//部分已选
					cid_str+='0';
					$('.cart_list input[name=select]:checked').each(function(index, element){
						cid_str+=','+$(element).val();
					});
				}
				cart_obj.modify_cart_result(obj, query_string+cid_str, 0);
			});
			$('.cart_list .item .del').on('tap', function(){ //购物车产品删除
				var url=$(this).attr('url');
				$('html').tips_box(lang_obj.cart.del_confirm, 'confirm', function(){
					$.get(url, function(data){
						if(data){
							window.location.reload();
						}
					});
				});
				return false;
			});
			$('.cart_list .check').on('tap', function(){ //购物车产品勾选
				if($(this).find('input[name=select]:checked').length){
					$(this).find('input[name=select]')[0].checked=false;
				}else{
					$(this).find('input[name=select]')[0].checked=true;
				}
				cart_obj.select_cart_result();
				return false;
			});
			$('.cart_list input[name=select]').on('click', function(){ //购物车产品勾选
				$(this).parent('.check').click();
				return false;
			});
			$('.cart_btn .checkout').on('tap', function(){ //Checkout
				/*if($('.cart_list .item.null').length){//检查是否存在错误产品
					$('html').tips_box(lang_obj.cart.attribute_error, 'error');
					return false;
				}*/
				var $this=$(this), Data=new Object;
				$('#cart .cart_list input[name=Remark\\[\\]]').each(function(){
					Data[$(this).attr('data-cid')]=$(this).val();
				});
				
				$this.addClass('processing').text(lang_obj.cart.processing_str+'...');
				var $checked_len=$('.cart_list input[name=select]:checked').length,
					$checkout_len=$('.cart_list input[name=select]').length,
					$query='';
				if($checked_len){//部分已选
					if($checked_len!=$checkout_len){ //部分已选，不是全选
						var $CId='0';
						$('.cart_list input[name=select]:checked').each(function(index, element){
							$CId+='.'+$(element).val();
						});
						$query='?CId='+$CId;
					}
					setTimeout(function(){
						$this.removeClass('processing').text($this.attr('data-name'));
						if($(this).loginOrVisitors()){
							$.post('/?do_action=cart.checkout_submit&t='+Math.random(), Data, function(data){
								if(data.ret==1){
									window.location.href='/cart/checkout.html'+$query;
									return false;
								};
							}, 'json');
						}else{
							window.location.href='/account/';
						}
					}, 500);
				}else{
					$('html').tips_box(lang_obj.cart.checked_error, 'error');
					$this.removeClass('processing').text($this.attr('data-name'));
				}
				return false;
			});
			$('.cart_btn .paypal_checkout_button').on('tap', function(){ //Paypal快捷支付
				var $this=$(this);
				/*if($('.cart_list .item.null').length){ //检查是否存在错误产品
					$('html').tips_box(lang_obj.cart.attribute_error, 'error');
					return false;
				}*/
				$this.addClass('processing').text(lang_obj.cart.processing_str+'...');
				var $checked_len=$('.cart_list input[name=select]:checked').length,
					$checkout_len=$('.cart_list input[name=select]').length,
					$query='';
				if($('.cart_list input[name=select]:checked').length){//部分已选
					if($checked_len!=$checkout_len){ //部分已选，不是全选
						var $CId='0';
						$('.cart_list input[name=select]:checked').each(function(index, element){
							$CId+='.'+$(element).val();
						});
						$query='?CId='+$CId;
					}
					setTimeout(function(){
						$this.removeClass('processing').text('');
						if($(this).loginOrVisitors()){
							window.top.location.href='/cart/quick.html'+$query;
						}else{
							window.top.location.href='/account/login.html?&jumpUrl='+decodeURIComponent('/cart/quick.html'+$query);
						}
					}, 500);
				}else{
					$('html').tips_box(lang_obj.cart.checked_error, 'error');
					$this.removeClass('processing').text('');
				}
				return false;
			});
		},
		
		//结算
		cart_checkout:function(){
			var CountryId=$('input[name=order_shipping_address_cid]').val()?$('input[name=order_shipping_address_cid]').val():$('form[name=paypal_excheckout]').find('option:selected').val();
			get_shipping_method_from_country(CountryId);
			var cart_price = cart_obj.cart_price_init();
			$('#ot_fee').text($('html').currencyFormat(cart_price.feePrice.toFixed(2), ueeshop_config.currency));
			$('#ot_total').text($('html').currencyFormat((cart_price.totalAmount+cart_price.free_Price).toFixed(2), ueeshop_config.currency));
			
			$('select[name=_payment_method]').on('change', function(e){
				var index=$('select[name=_payment_method] option:selected').index();
				$('.payment_list>li').hide().eq(index).show();
				$('#PlaceOrderFrom input[name=order_payment_method_pid]').val($(this).val());
				
				var fee=parseFloat($('.payment_list>li').eq(index).attr('fee'));
				var affix=parseFloat($('.payment_list>li').eq(index).attr('affix'));
				if(isNaN(fee)) fee=0;
				if(isNaN(affix)) affix=0;
				
				var amount=parseFloat($('#PlaceOrderFrom').attr('amountPrice'));	//产品总价
				var userPrice=parseFloat($('#PlaceOrderFrom').attr('userPrice'));	//会员优惠
				var discountPrice=parseFloat($('input[name=order_discount_price]').val());	//满额减价
				var cutprice=parseFloat($('input[name=order_coupon_code]').attr('cutprice'));	//折扣
				var price=parseFloat($('input[name=order_shipping_price]').val());	//运费
				var insurance=parseFloat($('input[name=order_shipping_insurance]').attr('price'));	//运费保险
				
				var totalAmount=amount-userPrice+price+insurance-cutprice-discountPrice;	//最终价格
				var feePrice=totalAmount*(fee/100)+affix;	//付款手续费
				
				$('#ot_fee').text($('html').currencyFormat(feePrice.toFixed(2), ueeshop_config.currency)).attr({'fee':fee, 'affix':affix});
				$('#ot_total').text($('html').currencyFormat((totalAmount*(1+fee/100)+affix).toFixed(2), ueeshop_config.currency));
				
				if(fee>0){
					$('#serviceCharge').show();
				}else{
					$('#serviceCharge').hide();
				}
            });
			
			/******************************** 收货地址 Start ********************************/
			var notnull			= $('#address_from input[notnull],#address_from select[notnull]'),
				address_list	= $('#address_list'),
				address_row		= $('.address_row', address_list),
				address_from	= $('#address_from'), //地址表单
				edit_btn		= $('.edit_address_info', address_row);//修改地址按钮
				
			if(address_perfect){
				$('#address_list .address_row.cur .edit_address_info').click();
				$('#useAddressBack').hide();
			}
			
			$('#country').change(function(e){
				var CId = $(this).find(':selected').val();
				user_obj.get_state_from_country(CId);
            });
			
			//提交地址
			var address_rq_mark=true;
			$('#useAddress').on('tap', function(){
				if(address_rq_mark && !$('#useAddress').hasClass('disabled')){
					$('#useAddress').addClass('disabled');
					address_rq_mark=false;
					notnull.removeClass('null');
					setTimeout(function(){
						var CountNull=0;
						notnull.each(function(index, element) {
							if($(element).val()==''){
								$(element).addClass('null');
								CountNull++;
							}
						});
						var Email=$('#address_from input[name=Email]');
						if(Email.length && /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(Email.val())==false){
							Email.addClass('null');
							CountNull++;
						}
						if(CountNull){//检查表单
							address_rq_mark = true;
							$('#useAddress').removeClass('disabled');
							return;
						}
						var obj=$('#address_from form');
						var typeAddr=parseInt(obj.find('input[name=typeAddr]').val())==1?1:0;
						
						if(typeAddr==1){
							checkout_no_login();
						}else{
							$.post('/account/', obj.serialize()+'&do_action=user.addressbook_mod', function(data){
								if(data.ret==1){
									window.location.reload();
								}else{
									address_rq_mark = true;
									$('#useAddress').removeClass('disabled');
								}
							}, 'json');
							
						}
					}, 10);
				}
			});
			
			/******************************** 收货地址 End ********************************/
			
			/******************************** 运费 Start ********************************/
			var shipping_list=$('#shipping_list');
			/*
			shipping_list.on('tap', '.shipping_row', function(){
				var $this=$(this);
				$('input[name=order_shipping_method_sid]').val($this.attr('data-sid'));
				$('input[name=SId]').length && $('input[name=SId]').val($this.attr('data-sid'));
				$('.shipping_row', shipping_list).removeClass('current');
				$this.addClass('current');
				set_shipping_method($this.attr('data-sid'), $this.attr('data-price'), $this.attr('data-shippingtype'), $this.attr('data-insurance'));
			});
			*/
			shipping_list.on('tap', 'dd', function(){
				$('#shipping_list dl').each(function(){
					if(!$(this).find('dd').size()){
						$('html').tips_box(lang_obj.cart.no_delivery, 'error');
						return false;	
					}
					if(!$(this).find('input:checked').size()){
						$(this).find('input:eq(0)').attr("checked","checked");
					}
				});
				var $this=$(this).find('input');
				$('input[name=order_shipping_method_sid]').val($this.val());
				$('input[name=SId]').length && $('input[name=SId]').val($this.val());
				//$(this).parent().find('input').removeAttr('checked');
				//$(this).find('input').attr('checked', 'checked');
				var price=0;
				var insurance=0;
				$('#shipping_list input:checked').each(function(){
					price+=parseFloat($(this).attr('price'));
					insurance+=parseFloat($(this).attr('insurance'));
				});
				set_shipping_method($this.val(), price, $this.attr('shippingtype'), insurance);
			});
			
			$('#shipping_insurance input[name=_shipping_insurance]').on('change', function(){ //选择保险
				var v=$(this).is(':checked')?1:0;
				show_shipping_price(v);
				var insurance=(v==1?parseFloat($('#shipping_list .current').attr('data-insurance')):0); //保险费
				if(isNaN(insurance)) insurance=0;
				$('input[name=order_shipping_insurance]').val(v).attr('price', insurance);
				$('input[name=ShippingInsurancePrice]').length && $('input[name=ShippingInsurancePrice]').val(insurance);
				var cart_price=cart_obj.cart_price_init();
				$('#ot_fee').text($('html').currencyFormat(cart_price.feePrice.toFixed(2), ueeshop_config.currency));
				$('#shipping_charges span').text($('html').currencyFormat(cart_price.price.toFixed(2), ueeshop_config.currency));
				$('#shipping_and_insurance span').text($('html').currencyFormat(cart_price.shippingPrice.toFixed(2), ueeshop_config.currency));
				$('#ot_total').text($('html').currencyFormat((cart_price.totalAmount+cart_price.feePrice).toFixed(2), ueeshop_config.currency));
			});
			
			function set_shipping_method(SId, price, type, insurance, Name){ //选择运费
				if(SId==-1){
					$('#shipping_list').html('<span class="no_delivery">Sorry! No delivery!</span>');
				}
				$('input[name=order_shipping_method_sid]').val(SId);
				$('input[name=SId]').length && $('input[name=SId]').val(SId);
				$('input[name=order_shipping_method_type]').val(type);
				$('input[name=ShippingMethodType]').length && $('input[name=ShippingMethodType]').val(type);
				$('input[name=order_shipping_price]').val(price);
				$('input[name=ShippingPrice]').length && $('input[name=ShippingPrice]').val(price);
				$('input[name=ShippingExpress]').length && $('input[name=ShippingExpress]').val(Name);
				
				var v=$('#shipping_insurance input[name=_shipping_insurance]').is(':checked')?1:0;
				$('#shipping_insurance span').text(insurance);
				var insurance=v==1?insurance:0;
				$('input[name=order_shipping_insurance]').val(v).attr('price', insurance);
				$('input[name=ShippingInsurancePrice]').length && $('input[name=ShippingInsurancePrice]').val(insurance);
				if($('input[name=ShippingInsurance]').length){
					if(insurance){
						$('input[name=ShippingInsurance]').val(1);
					}else $('input[name=ShippingInsurance]').val(0);
				}
				var cart_price = cart_obj.cart_price_init();
				
				$('#ot_fee').text($('html').currencyFormat(cart_price.feePrice.toFixed(2), ueeshop_config.currency));
				$('#shipping_charges span').text($('html').currencyFormat(cart_price.price.toFixed(2), ueeshop_config.currency));
				$('#shipping_and_insurance span').text($('html').currencyFormat(cart_price.shippingPrice.toFixed(2), ueeshop_config.currency));
				$('#ot_total').text($('html').currencyFormat((cart_price.totalAmount+cart_price.feePrice).toFixed(2), ueeshop_config.currency));
				show_shipping_price(v);
				
				var minPrice=maxPrice=0;
				totalAmount=parseFloat((cart_price.totalAmount-cart_price.feePrice).toFixed(2));
				$('select[name=_payment_method] option:gt(0)').each(function(){
					minPrice=parseFloat($(this).attr('min'));
					maxPrice=parseFloat($(this).attr('max'));
					if(maxPrice?(totalAmount>=minPrice && totalAmount<=maxPrice):(totalAmount>=minPrice)){
						$(this).css('display', 'block');
						$(this)[0].disabled=false;
					}else{
						$(this).css('display', 'none');
						$(this)[0].disabled=true;
						if($(this).parent().val()==$(this).attr('value')){
							$('select[name=_payment_method]').val(-1).change();
						}
					}
				});
			}
			
			function get_shipping_method_from_country(CId){	//change shipping method
				if(!CId){
					set_shipping_method(-1, 0, '', 0);
					return false;
				}
				var shipping_list = $('#shipping_list');
				if (shipping_list.data('shipping_methods'+CId)){
					var data = shipping_list.data('shipping_methods'+CId);
					set_shipping_price(data);
				}else{
					/*
					$.post('/?do_action=cart.get_shipping_methods', "CId="+CId, function(data){
						if(data.ret==1){
							shipping_list.data('shipping_methods'+CId, data);//缓存
							set_shipping_price(data);
						}else{
							$('#shipping_list').html('');
							set_shipping_method(-1, 0, '', 0);
						}
					}, 'json');
					*/
					$.post('/?do_action=cart.get_shipping_methods_new', "CId="+CId, function(data){
						if(data.ret==1){
							shipping_list.data('shipping_methods'+CId, data);//缓存
							set_shipping_price(data);
						}else{
							$('#shipping_list').html('');
							set_shipping_method(-1, 0, '', 0);
						}
					}, 'json');
				}// if end
				/*
				function set_shipping_price (data){
					var v=data.msg.info;					
					var str='';
					for(i=0; i<v.length; i++){
						str +=	'<div class="shipping_row clean'+(i+1==v.length?'':' ui_border_b')+'" data-price="'+v[i].ShippingPrice+'" data-sid="'+v[i].SId+'" data-insurance="'+v[i].InsurancePrice+'" data-shippingtype="'+v[i].type+'">';
						str +=		'<div><i class="FontBgColor"></i>'+v[i].Name+'</div>';
						str +=		'<div>'+v[i].Brief+'</div>';
						str +=		'<div>'+(v[i].ShippingPrice>0?ueeshop_config.currency_symbols + $('html').currencyFormat(v[i].ShippingPrice, ueeshop_config.currency):'Free Shipping')+'</div>';
						str +=	'</div>';
					}
					$('#shipping_list').html(str);
					$('#shipping_list .shipping_row').eq(0).addClass('current');
					
					if(str==''){
						set_shipping_method(-1, 0, '', 0);
					}else{
						set_shipping_method(v[0].SId, v[0].ShippingPrice, v[0].type, v[0].InsurancePrice, v[0].Name);
					}
				}
				*/
				function set_shipping_price (data){
					var info=data.msg.info;
					var html='';
					for(var key in info){
						html+='<dl>';
							html+='<dt>'+info[key].Name+'</dt>';
							if(info[key].Data){
								for(var kkey in info[key].Data){
									html+='<dd><input type="radio" name="_shipping_method['+key+']" value="'+info[key].Data[kkey].SId+'" price="'+info[key].Data[kkey].ShippingPrice+'" insurance="'+info[key].Data[kkey].InsurancePrice+'" ShippingType="'+info[key].Data[kkey].type+'" cid="'+CId+'" />'+info[key].Data[kkey].Name+'<span>('+(info[key].Data[kkey].ShippingPrice>0 ? ueeshop_config.currency_symbols + $('html').currencyFormat(info[key].Data[kkey].ShippingPrice, ueeshop_config.currency) : lang_obj.products.free_shipping)+')</span></dd>';
								}
							}
						html+='</dl>';
					}
					$('#shipping_list').html(html);
					
					if($('#shipping_list dl dd').size()){
						$('#shipping_list dl dd:eq(0)').trigger('tap');
					}else{
						$('html').tips_box(lang_obj.cart.no_delivery, 'error');
					}
					
					if(html==''){
						set_shipping_method(-1, 0, '', 0);
					}
				}
			}//get_shipping_method_from_country 结束
			
			function show_shipping_price(v){
				if(v){//有保险
					$('#shipping_charges').hide(0);
					$('#shipping_and_insurance').show(0);
				}else{
					$('#shipping_charges').show(0);
					$('#shipping_and_insurance').hide(0);
				}
			}
			/******************************** 运费 End ********************************/
			
			/******************************** 优惠券 Start ********************************/
			var coupon_ajax_mark=true,
				couponCode=$('input[name=order_coupon_code]').val();
			
			if(couponCode!='') ajax_get_coupon_info(couponCode);
			
			$('#coupon_apply').on('tap', function(){
				var code=$('input[name=couponCode]').val();
				if(code && coupon_ajax_mark){
					ajax_get_coupon_info(code);
				}else{
					$('input[name=couponCode]').addClass('null');
					setTimeout(function(){
						$('input[name=couponCode]').removeClass('null');
					}, 2000);
				}
			});
			
			$('#removeCoupon').on('tap', function (){
				$('#couponSavings, #code_valid').hide();
				$('.code_input').slideDown(200);
				$('#code_valid strong').text('');
				$('input[name=order_coupon_code]').val('').attr('cutprice', '0.00');
				var cart_price=cart_obj.cart_price_init();
				$('#ot_fee').text($('html').currencyFormat(cart_price.feePrice.toFixed(2), ueeshop_config.currency));
				$('#couponSavings .value span').text($('html').currencyFormat(cart_price.cutprice.toFixed(2), ueeshop_config.currency));
				$('#ot_total').text($('html').currencyFormat((cart_price.totalAmount+cart_price.feePrice).toFixed(2), ueeshop_config.currency));
				coupon_ajax_mark=true;
				$('#selcouponid_box').css('display', 'block');
			});
			
			function ajax_get_coupon_info(code){
				coupon_ajax_mark=false;
				var price=parseFloat($('#PlaceOrderFrom').attr('amountPrice'));
				var userprice=parseFloat($('#PlaceOrderFrom').attr('userprice'));
				var order_discount_price=parseFloat($('input[name=order_discount_price]').val());
				$.post('/?do_action=cart.ajax_get_coupon_info', {coupon:code, price:price, order_discount_price:order_discount_price, userprice:userprice}, function(data){
					if(data.msg.status==1){
						$('input[name=couponCode]').val('');
						$('.code_input').hide(0);
						$('#couponSavings').slideDown(200);
						$('input[name=order_coupon_code]').val(data.msg.coupon).attr('cutprice', data.msg.cutprice);
						var cart_price=cart_obj.cart_price_init();
						$('#couponSavings .value span').text(cart_price.cutprice.toFixed(2));
						$('#code_valid').slideDown(200);
						$('#code_valid strong').eq(0).text(data.msg.coupon);
						$('#code_valid strong').eq(1).text(ueeshop_config.currency + $('html').currencyFormat(cart_price.cutprice.toFixed(2), ueeshop_config.currency));
						$('#code_valid strong').eq(2).text(data.msg.end);
						$('#ot_fee').text($('html').currencyFormat(cart_price.feePrice.toFixed(2), ueeshop_config.currency));
						$('#ot_total').text($('html').currencyFormat((cart_price.totalAmount+cart_price.feePrice).toFixed(2), ueeshop_config.currency));
						coupon_ajax_mark=true;
						//新增
						$('#selcouponid_box').css('display', 'none');
						$('#selcouponid').find('option').removeAttr('selected').eq(0).attr('selected', 'selected');
					}else{
						$('#selcouponid_box').css('display', 'block');
						$('html').tips_box((lang_obj.cart.coupon_tips_to).replace('%coupon%', code), 'error');
						coupon_ajax_mark=true;
					}
				}, 'json');
			}
			/******************************** 优惠券 End ********************************/
			
			/******************* Paypal Checkout Start *******************/
			$('#select_country select[name=CId]').on('change', function(){
				get_shipping_method_from_country($(this).val());//运费
			});
			
			//提交
			$('#paypal_checkout').on('click', function(){
				var obj=$('#PlaceOrderFrom');
				$(this).attr('disabled', 'disabled').blur();
				if(obj.find('input[name=order_products_attribute_error]').val()==1){//检查是否存在错误产品
					$('html').tips_box(lang_obj.cart.attribute_error, 'error');
					window.location.href='/cart/';
					return false;
				}
				if(parseInt(obj.find('input[name=SId]').val())<1 && obj.find('input[name=ShippingMethodType]').val()==''){
					$('html').tips_box(lang_obj.cart.shipping_method_tips, 'error');
					$(this).removeAttr('disabled');
					return false;
				}
				obj.submit();
			});
			/******************* Paypal Checkout End *******************/
			
			//提交订单
			$('#cart_checkout').on('click touchstart', function(e){
				e.preventDefault();
				var express = $("#express").val();
				if(express==-1){
					$('html').tips_box('请选择邮寄方式', 'error');
					return false;
				}
				var payType = $("#payType").val();
				if(payType==-1){
					$('html').tips_box('请选择支付方式', 'error');
					return false;
				}
				if ($(this).hasClass('processing')){
					return false;
				}
				var btnTxt=$(this).text();
				$(this).addClass('processing').text(lang_obj.cart.processing_str+'...');
				
				if($('#PlaceOrderFrom input[name=order_products_attribute_error]').val()==1){//检查是否存在错误产品
					$('html').tips_box(lang_obj.cart.attribute_error, 'error');
					$(this).removeClass('processing').text(btnTxt);
					window.location.href='/cart/';
					return false;
				}

				$('#PlaceOrderFrom').submit(function(e){return false;});
				var Attr='';
				if($('#PlaceOrderFrom').attr('nologin')){
					Attr=$('#PlaceOrderFrom').attr('nologin');
				}
				if($('#address_from:visible').length){
					$('html').tips_box('Please save your shipping address data, then your order submission!', 'error');
					$(this).removeClass('processing').text(btnTxt);
					return false;
				}
				var strSpec = "";
				$(".strSpec").each(function (index,e){
            		//console.log(e.attr("data"));
					var $e1 = $(e);
					if(index!=$(".strSpec").length - 1){
						strSpec = strSpec + $e1.attr("data") + ",";
					}else{
						strSpec = strSpec + $e1.attr("data");
					}
					
            	})
            	var address = $("#nation").text() + "," + $("#region").text() + ","  + $("#city").text() + ","  + $("#address1").text();
		        var delivery = $("#express option:selected").text(); 
				console.log(strSpec)
            	console.log(address)
            	console.log(delivery);
            	console.log(payType);
				$.post('/home/odr_OdrOrder_appAddOrder',
						{strSpec:strSpec,address:address,delivery:delivery,payType:payType}, function(data){
					if(data.data == "1"){
						sessionStorage.setItem("orderNumber", data.number);
						console.log(sessionStorage.getItem("orderNumber"));
					}else if(data.data == "-1"){
						$('html').tips_box("商品不为同一供应商", 'error');
						window.location.href = "/";
					}else if(data.data == "-2"){
						$('html').tips_box("未找到商品", 'error');
						window.location.href = "/";
					}else if(data.data == "-3"){
						$('html').tips_box("请选择地址", 'error');
					}
				}, 'json');
			});
		},
		
		checkout_no_login:function(json){
			$.post('/?do_action=cart.set_no_login_address', json, function(data){
				if(data.ret==1){
					$('#PlaceOrderFrom').attr('nologin', data.msg.info);
					/*var html='<div class="address_row" data-aid="0" data-cid="'+data.msg.v.CId+'">';
							html+='<strong>'+data.msg.v.FirstName+' '+data.msg.v.LastName+' ('+data.msg.v.Country+')</strong>';
							html+='<p>'+(data.msg.v.StateName ? data.msg.v.StateName : data.msg.v.State)+', '+data.msg.v.City+', '+data.msg.v.AddressLine1+' '+(data.msg.v.AddressLine2 ? data.msg.v.AddressLine2+' ' : '');
							html+='<p>'+data.msg.v.ZipCode+'</p>';
						html+='</div>';
					*/
					get_shipping_method_from_country(data.msg.v.CId);
					$('#PlaceOrderFrom input[name=order_shipping_address_aid]').val(0).next('input[name=order_shipping_address_cid]').val(data.msg.v.CId);
					$('input[name=ShoppingCId]').length && $('input[name=ShoppingCId]').val(data.msg.v.CId);
					//cart_obj.checkout_init(parseInt(data.msg.v.CId)).get_shipping_method_from_country();
				}
				address_rq_mark=true;
			}, 'json');
		},
		
		modify_cart_result:function(obj, query_string, is_tips){
			if(cart_obj.update_cart_mark){
				cart_obj.update_cart_mark=false;
				$.post('/?do_action=cart.modify&t='+Math.random(), query_string, function(data){
					if(data.ret==1){
						cart_obj.update_cart_mark=true;
						var price=$(obj).parents('.item').find('.price');
						if(is_tips && data.msg.qty==obj.val()){ //提示
							$('html').tips_box(lang_obj.products.warning_number, 'error');
						}
						obj.val(data.msg.qty);
						obj.parent().parent().siblings('input[name="S_Qty[]"]').val(data.msg.qty);
						price.html(ueeshop_config.currency_symbols+$('html').currencyFormat(data.msg.price, ueeshop_config.currency));
						
						$('.cutprice_p').text('-'+ueeshop_config.currency+' '+ueeshop_config.currency_symbols+$('html').currencyFormat(data.msg.cutprice, ueeshop_config.currency));
						/*if(data.msg.cutprice){ //控制全场满减的显示
							$('.cart_total .savings').show();
						}else{
							$('.cart_total .savings').hide();
						}*/
						$('.cart_btn .total').html('<span>'+ueeshop_config.currency+' '+ueeshop_config.currency_symbols+'</span>'+$('html').currencyFormat(data.msg.total_price, ueeshop_config.currency));
					}
				}, 'json');
			}
		},
		
		select_cart_result:function(){
			var $CId='0';
			$('.cart_list input[name=select]:checked').each(function(){
				$CId+=','+$(this).parents('.item').find("input[name=CId\\[\\]]").val();
			});
			$.post('/?do_action=cart.select&t='+Math.random(), 'CId='+$CId, function(data){
				if(data.ret==1){
					$('.cutprice_p').text('-'+ueeshop_config.currency+' '+ueeshop_config.currency_symbols+$('html').currencyFormat(data.msg.cutprice, ueeshop_config.currency));
					/*if(data.msg.cutprice){ //控制全场满减的显示
						$('.cart_total .savings').show();
					}else{
						$('.cart_total .savings').hide();
					}*/
					$('.cart_btn .title b').text(data.msg.total_count);
					$('.cart_btn .total').html('<span>'+ueeshop_config.currency+' '+ueeshop_config.currency_symbols+'</span>'+$('html').currencyFormat(data.msg.total_price, ueeshop_config.currency));
				}
			}, 'json');
		},
		
		update_cart_mark:true,
		
		cart_price_init:function(){//返回价格
			var amount=parseFloat($('#PlaceOrderFrom').attr('amountPrice'));	//产品总价
			var userPrice=parseFloat($('#PlaceOrderFrom').attr('userPrice'));	//会员优惠
			var discountPrice=parseFloat($('input[name=order_discount_price]').val());	//满额减价
			var cutprice=parseFloat($('input[name=order_coupon_code]').attr('cutprice'));	//折扣
			var price=parseFloat($('input[name=order_shipping_price]').val());	//运费
			var insurance=$('#shipping_insurance input[name=_shipping_insurance]').is(':checked')?parseFloat($('input[name=order_shipping_insurance]').attr('price')):0;	//运费保险
			var shippingPrice=price+insurance;	//运费、保险总价
			var fee=parseFloat($('#ot_fee').attr('fee'));
			var affix=parseFloat($('#ot_fee').attr('affix'));
			if(isNaN(fee)) fee=0;
			if(isNaN(affix)) affix=0;
			
			var totalAmount=amount-userPrice+shippingPrice-cutprice-discountPrice;	//最终价格
			var feePrice=totalAmount*(fee/100)+affix;	//付款手续费
			
			return {totalAmount:totalAmount, amount:amount, userPrice:userPrice, discountPrice:discountPrice, cutprice:cutprice, price:price, insurance:insurance, shippingPrice:shippingPrice, feePrice:feePrice};
		}
//		,complete:function(){ //线下付款
//			var pay_form	= $('#pay_form'),
//				rq_mark		= true,
//				notnull		= $('*[notnull]', pay_form);
//			$('#paybtn').on('click', function(){
//				if(rq_mark){
//					notnull.removeClass('null');
//					setTimeout(function(){
//						var status=0;
//						notnull.each(function(index, element){
//							if($(element).val()==''){
//								$(element).addClass('null');
//								status=1;
//							}else{
//								$(element).removeClass('null');
//							}
//						});
//						
//						var reg={'Length':/^.*/};
//						var tips={'Length':lang_obj.format.length};
//						pay_form.find('*[format]').each(function(){
//							var o=$(this);
//							var s=o.attr('format').split('|');
//							if((s[0]=='Length' && $.trim(o.val()).length!=parseInt(s[1])) || (s[0]!='Length' && reg[s[0]].test($.trim(o.val()))===false)){
//								$('html').tips_box(tips[s[0]].replace('%num%', s[1]), 'error');
//								o.addClass('null');
//								status=1;
//							}else{
//								o.removeClass('null');
//							}
//						});
//						
//						if(status){
//							return false;
//						}
//						//通过验证，提交数据
//						rq_mark=false;
//						
//						var firstName=trimAll($("#pay_form input[name='FirstName']").val())
//						var lastName=trimAll($("#pay_form input[name='LastName']").val())
//						var Name=firstName+" "+lastName
//						var SentMoney=trimAll($("#pay_form input[name='SentMoney']").val())
//					    var MTCNNumber=trimAll($("#pay_form input[name='MTCNNumber']").val())
//						var Contents=trimAll($("#pay_form textarea[name='Contents']").val())
//						var payContent='{"Name":"'+Name+'","SentMoney":"'+SentMoney+'","MTCNNumber":"'+MTCNNumber+'",""Contents":'+Contents+'"}'
//					    console.log(payContent)
//						
//						$.post('/home/odr_OdrOrder_appConfirmPay',$.param({'currency':1})+'&'+$.param({'payContent':payContent})+'&'+pay_form.serialize(), function(data){
//							console.log(data)
//							if(data.success){
//								window.location.href=window.location.href;
//							}else{
//								rq_mark=true;
//							}
//						}, 'json');
//					}, 10);
//				}
//			});
//		}
	};
})(jQuery, window);
