/*
Powered by shoestp
*/
var address_perfect=0;

var user_obj={
	/******************* 登录或注册 Start *******************/
	sign_in_init:function(){
		var cancelback=(typeof(arguments[0])=='undefined')?'':arguments[0];//取消返回函数
		var url = (typeof(arguments[1])=='undefined')?'':arguments[1];
		$('body').off().on('click', '.SignInButton', function(){ //点击登录链接，显示登录框
			user_obj.set_form_sign_in('', url, 1);
		})
		.on('click', '#signin_close, #div_mask', function(){ //关闭登录
			cancelback && cancelback();
			if($('#signin_module').length && $('#signin_close').length && !ueeshop_config['_login']){
				$('#signin_module').remove();
				global_obj.div_mask(1);
			}
		})
		.on('submit', '#signin_module form[name=signin_form]', function(){ //会员登录
			if(global_obj.check_form($(this).find('*[notnull]'))){return false;};
			var Email=$(this).find('input[name=email]').val();
			var r=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			console.log(r.test(Email))
			if(!r.test(Email)){
				alert(lang_obj.format.email);
				return false;
			}
			$(this).find('button:submit').attr('disabled', true);
			
			$.post('/home/usr_UsrPurchase_signIn', $(this).serialize(), function(data){
				$('#signin_module form[name=signin_form] button:submit').removeAttr('disabled');
				if(data.ret!=1){
					if (data.ret==-1){//刷新水印
						$('#w_login_vcode img').attr('src', '/servlet/verify.img');
					}
					var val = parseInt($('#w_vcode_num').val())+1;
					$('#w_vcode_num').val(val);//密码输入错误3次，显示验证码
					if (val>3){
						$('#w_login_vcode').show();
					}
					$('#error_login_box').html(data.msg[0]).show();
				}else{
					if($('input[name=comeback]').length){
						var $callback=$('input[name=comeback]').val();
						$callback && eval($callback);
					}else{
						window.location.href = (typeof(data.msg)=='undefined'?window.location.href:data.msg[0]);
					}
				}
			}, 'json');
			
			return false;
		});
	},
	
	set_form_sign_in:function(type){//生成登录框
		var Url=(typeof(arguments[1])=='undefined')?'':arguments[1];
		var Model=(typeof(arguments[2])=='undefined')?0:arguments[2];
		var addStr='',
			obj=$('body'),
			w=$(window),
			regUrl='/home/usr_UsrPurchase_sign';
			
		if(type=='parent'){//父框架元素
			obj=$(window.parent.document).find('body');
			w=$(window.parent.window);
		}
		
		if(obj.find('#attr_hide').val()){
			addStr=obj.find('#attr_hide').serialize()?obj.find('#attr_hide').serialize():obj.find('#attr_hide').val();
			addStr=Object(addStr);
		}
		var signin_html='<div id="signin_module">';
		signin_html=signin_html+'<div class="box_bg"></div>'+(Model?'<a class="noCtrTrack" id="signin_close">×</a>':'');
		signin_html=signin_html+'<div id="lb-wrapper"><form name="signin_form" class="login" method="POST">';
		signin_html=signin_html+'<h3>'+lang_obj.signIn.title+'</h3>';
		signin_html=signin_html+'<div id="error_login_box" class="error_note_box">'+lang_obj.signIn.error_note+'</div>';
		signin_html=signin_html+'<div class="row"><label for="Email">'+lang_obj.signIn.email+'</label><input name="email" class="lib_txt" type="text" maxlength="100" format="Email" notnull /></div>';
		signin_html=signin_html+'<div class="row"><label for="Password">'+lang_obj.signIn.password+'</label><input name="password" class="lib_txt" type="password" notnull /></div>';
		signin_html=signin_html+'<div class="row" id="w_login_vcode" style="display:none;"><label for="vcode">'+lang_obj.global.vcode+'</label><div class="clean"><input name="checkCode" class="lib_txt fl" type="text" maxlength="4" style="text-transform:uppercase; width:80px; margin-right:10px;" /><img src="/servlet/verify.img" onclick="this.src=\'/servlet/verify.img?name=login&length=4&charset=en&r=\'+Math.random();" class="fl" style="cursor:pointer;"></div></div>';
		signin_html=signin_html+'<div class="row">'+lang_obj.signIn.forgot+'</div>';
		signin_html=signin_html+'<div class="row protect"><input class="ckb" type="checkbox" name="IsStay" value="1" checked="checked" /> '+lang_obj.signIn.stay_note+'</div>';
		signin_html=signin_html+'<div class="row"><button class="signbtn signin FontBgColor FontBorderColor" type="submit">'+lang_obj.signIn.sign_in+'</button>'+(!obj.find('form.register').length?'<a href="'+($('#addtocart_button').length?'/home/usr_UsrPurchase_sign?AddToCart=1'+(obj.find('#attr_hide').val()?'&'+addStr:''):'/home/usr_UsrPurchase_sign')+'" class="signbtn signup">'+lang_obj.signIn.join_fee+'</a>':'')+'</div>';					
		signin_html=signin_html+'<input type="hidden" name="do_action" value="user.login" />';
		signin_html=signin_html+'<input type="hidden" name="vcodeNum" id="w_vcode_num" value="1" />';
		if(obj.find('input[name=jumpUrl]').length) 
			signin_html=signin_html+'<input type="hidden" name="jumpUrl" value="'+$('input[name=jumpUrl]').serialize().replace('jumpUrl=', '')+'" />';
		if(Url) 
			signin_html=signin_html+'<input type="hidden" name="jumpUrl" value="'+Url+'" />';
		signin_html=signin_html+'</form></div>';
		signin_html=signin_html+'</div>';
		
		obj.find('#signin_module').length && obj.find('#signin_module').remove();
		obj.prepend(signin_html);
		obj.find('#signin_module').css({left:w.width()/2-220});
		global_obj.div_mask();
	},
	
	sign_up_init:function(){
		var frm_register=$('#signup form.register');
		frm_register.submit(function(){return false;});
		frm_register.find('button:submit').click(function(){
			var status=0;
			if(global_obj.check_form(frm_register.find('*[notnull]'), frm_register.find('*[format]'), 1)){
				status+=1;
			}else status+=0;
			
			if(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test($('#Email').val())==false){
				$('#Email').next().show();
				status+=1;
			}else{
				$('#Email').next().hide();
				status+=0;
			}
			
			if($('#Password').val()!=$('#Password2').val()){
				$('#Password2').next().show();
				status+=1;
			}else{
				$('#Password2').next().hide();
				status+=0;
			}
			if(status) return false;
			$(this).attr('disabled', true);
			
			$.post('/', frm_register.serialize(), function(data){
				frm_register.find('button:submit').attr('disabled', false);
				if(data.ret!=1){
					if (data.ret==-1){//刷新水印
						$('#Code').siblings('img').attr('src', '/inc/class/v_code.class.php?name=register&length=4&charset=en&r='+Math.random());
					}
					$('#error_register_box').html(data.msg[0]).show();
					$("body, html").animate({scrollTop:$("#error_register_box").offset().top}, 500);
				}else{
					//When a sign up is completed, such as signup for trial.
					parseInt(ueeshop_config.FbPixelOpen)==1 && fbq("track", "Lead");
					window.location=data.msg[0];
				}
			}, 'json');
		});
		
		$('.amount').keydown(function(e){
			var value=$(this).val();
			var key=window.event?e.keyCode:e.which;
			if((key>95 && key<106) || (key>47 && key<60) || (key==109 && value.indexOf("-")<0) || (key==110 && value.indexOf(".")<0) || (key==190 && value.indexOf(".")<0)){
			}else if(key!=8){
				if(window.event){//IE
					e.returnValue=false;
				}else{//Firefox
					e.preventDefault();
				}
				return false;
			}
		});
		
		$('#send_email_btn').on('click', function(){
			$email=$(this).attr('email');
			$uid=$(this).attr('uid');
			$.post('/', 'do_action=action.verification_mail&Email='+$email+'&UserId='+$uid, function(data){
				if(data.ret==1){
					alert(lang_obj.user.send_email_ture);
				}else{
					alert(lang_obj.user.send_email_false);
				}
			}, 'json');
		});
	}, 
	
	user_login_binding:function(){
		var frm_binding=$('#lib_user_binding form.login');
		frm_binding.submit(function(){return false;});
		frm_binding.find('button:submit').click(function(){
			if(global_obj.check_form(frm_binding.find('*[notnull]'))){return false;};
			$(this).attr('disabled', true);
			
			$.post('/', frm_binding.serialize()+'&do_action=user.user_oauth_binding', function(data){
				frm_binding.find('button:submit').attr('disabled', false);
				if(data.ret!=1){
					$('#error_login_box').html(data.msg[0]).show();
				}else{
					window.location=data.msg;
				}
			}, 'json');
		});
	},
	
	forgot_init:function (){
		var frm_register=$('#signup form.register');
		frm_register.submit(function(){return false;});
		frm_register.find('.fotgotbtn').click(function(){//发送忘记密码邮件
			if(global_obj.check_form(frm_register.find('*[notnull]'), frm_register.find('*[format]'), 1)){
				status=1;
			}else status=0;
			
			if(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test($('#Email').val())==false){
				$('#Email').next().show();
				status=1;
			}else{
				$('#Email').next().hide();
				status=0;
			}
			
			if(status==1) return false;
			$(this).attr('disabled', true);
			
			$.post('/account/', frm_register.serialize(), function(data){
				frm_register.find('.fotgotbtn').attr('disabled', false);
				if(data.ret!=1){
					$('#error_register_box').html(data.msg[0]).show();
				}else{
					window.location=data.msg[0];
				}
			}, 'json');
		});
		
		frm_register.find('.resetbtn').click(function(){//发送忘记密码邮件
			if(global_obj.check_form(frm_register.find('*[notnull]'), frm_register.find('*[format]'), 1)){
				status=1;
			}else status=0;
			
			if($('#Password').val() && $('#Password2').val()){
				if($('#Password').val()!=$('#Password2').val()){
					$('#Password2').next().show();
					status=1;
				}else{
					$('#Password2').next().hide();
					status=0;
				}
			}else{
				status=1;
			}
			
			if(status==1) return false;
			$(this).attr('disabled', true);
			
			$.post('/account/', frm_register.serialize(), function(data){
				frm_register.find('.resetbtn').attr('disabled', false);
				if(data.ret!=1){
					$('#error_register_box').html(data.msg[0]).show();
				}else{
					window.location=data.msg[0];
				}
			}, 'json');
		});
	},
	/******************* 登录或注册 End *******************/
	
	/******************* 会员首页 Start *******************/
	user_index_init:function(){
		$(".index_boxes.favorites").carousel(
				{itemsPerMove:1,height:174,width:($(window).width()>=1250?568:470),duration:200,vertical:!1,step:1}
			);
		
		$('.remove_newsletter').click(function(){
			var $email=$(this).attr('email');
			global_obj.win_alert(lang_obj.global.del_confirm, function(){
				$.post('/', 'do_action=user.cancel_newsletter&Email='+$email, function(data){
					if(data.ret==1){
						window.location.reload();
					}else{
						global_obj.win_alert(data.msg);
					}
				}, 'json');
			}, 'confirm');
		});
		//申请分销会员按钮
		$('#apply_btn').click(function (){
			$.post('/', {'do_action':'user.apply'}, function (data){
				if (data.ret==1){
					window.location.href=window.location.href;
				}
			}, 'json');
		});
	},
	/******************* 会员首页 End *******************/
	
	order_init:function(){
		//编辑支付方式
		$('.edit_pay_btn').click(function(){
			if($(this).attr('disabled')) return false;
			$(this).blur().attr('disabled', 'disabled');
			var $OId=$(this).attr('oid');
			$.ajax({
				type: "POST",
				url: "/?do_action=cart.get_payment_methods",
				dataType: "json",
				data:{'OId':$OId},
				success: function(data){
					if(data.ret==1){
						var c=data.msg.info,
							defaultPId=0,
							payment_select='',
							pay_content='',
							total=data.msg.total_price,
							PId=parseInt($('.edit_pay_btn').attr('pid'));
							total=parseFloat(total.replace(data.msg.currency_symbols, '', total));
						for(i=0; i<c.length; i++){
							if(PId==c[i].PId) defaultPId=c[i].PId;
							var s=defaultPId==c[i].PId?'selected':'';
							payment_select+='<option value="'+c[i].PId+'" fee="'+c[i].AdditionalFee+'" '+s+'>'+c[i].Name+'</option>';
							var w=defaultPId==c[i].PId?'':'style="display:none;"';
							pay_content+='<div class="pay_contents_'+c[i].PId+'" '+w+'>'+c[i].Description+'</div>';
							defaultPId==c[i].PId && (total+=parseFloat(c[i].AdditionalFee));
						}
						
						var pay_html='<div id="pay_choose">';
							pay_html+='<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
							pay_html+='<div id="choose_content"><form name="pay_edit_form" method="POST" action="">';
								pay_html+='<label>'+lang_obj.orders.choose_payment+': </label>';
								pay_html+='<select name="PId">'+payment_select+'</select>';
								pay_html+='<div class="pay_content">'+pay_content+'</div>';
								pay_html+='<p class="footRegion">';
									pay_html+='<input class="btn btn-success" id="pay_button" type="submit" value="OK" /><span class="choose_price">'+lang_obj.orders.order_total+': <span>'+data.msg.total_price+'</span></span>';
								pay_html+='</p>';
							pay_html+='<input type="hidden" name="TotalPrice" value="'+total+'" /><input type="hidden" name="Symbols" value="'+data.msg.currency_symbols+'" /><input type="hidden" name="OId" value="'+$OId+'" /></form></div>';
						pay_html+='</div>';
						
						$('#pay_choose').length && $('#pay_choose').remove();
						$('body').prepend(pay_html);
						$('#pay_choose').css({left:$(window).width()/2-220});
						global_obj.div_mask();
						
						//提交编辑支付方式
						$('form[name=pay_edit_form]').submit(function(){ return false; });
						$('#pay_button').click(function(){
							var obj=$('form[name=pay_edit_form]');
							$(this).attr('disabled', 'disabled').blur();
							
							$.post('/?do_action=cart.orders_payment_update', obj.serialize(), function(data){
								window.location.reload();
							});
							return false;
						});
					}else{
						global_obj.win_alert(lang_obj.products.sign_in, function(){window.top.location='/account/login.html';});
					}
				}
			});
		});
		
		//关闭编辑支付方式
		$('body').on('click', '#choose_close, #div_mask, #exback_button', function(){
			if($('#pay_choose').length){
				$('#pay_choose').remove();
				global_obj.div_mask(1);
				$('.edit_pay_btn').removeAttr('disabled');
			}
		});
		
		//选择支付方式
		$('body').on('change', 'form[name=pay_edit_form] select[name=PId]', function(){
			var PId=$(this).val(),
				Fee=parseFloat($(this).children('option:selected').attr('fee')),
				total_price=parseFloat($('input[name=TotalPrice]').val());
			
			$('.choose_price>span').text($('input[name=Symbols]').val()+parseFloat(total_price+Fee).toFixed(2));
			$('.pay_content>div.pay_contents_'+PId).css('display', 'block').siblings().css('display', 'none');
		});
		
		$('#cancelForm').submit(function(){
			if(global_obj.check_form($(this).find('*[notnull]'))){
				return false;
			}else{
				var result=window.confirm(lang_obj.user.order_cancel);
				if(result){
					$.post('/?do_action=user.cancel_order', $('#cancelForm').serialize(), function(data){
						window.location.href='/account/orders/';
					});
				}
			}
			return false;
		});
		
		$('.confirm_receiving').click(function(){
			if(!confirm(lang_obj.user.sure)){
				return false;
			}else{
				$.post('/?do_action=user.confirm_receiving', {OId:$(this).attr('oid')}, function(data){
					window.location.reload();
				});
			};
		});
		/*
		$('.payment2btn').click(function(){
			if($('form[name=paypal_checkout_form]').length){ //Paypal支付方式
				$('#paypal_checkout_button').click();
			}else{
				window.open($(this).attr('href'));
			}
			return false;
		});
		*/
	},
	
	user_address:function(){
		$('.chzn-container-single .chzn-search').css('height', $('.chzn-container-single .chzn-search input').height());
		!address_perfect && user_obj.set_default_address(0);
		
		$('a.chzn-single').off().on('click', function(){
//			$.post("/home/plt_PltProvince_getAllProByCtr",function(data){
//				  var da = JSON.parse(data);
//				$.each(da.provinces, function (index, value) {
//					/* if (value.defualt==1) {
//						$("#sldCountry").text(value.name)
//						$("#sldCountryHiden").val(value.pkey)
//					} */
//					 $("#getProvinces").append('<li class="group-option active-result" value='+value.pkey+'>'+value.name+'</li>')
//		            })  
//			  })
			$(this).parent().next('p.errorInfo').text('');
			if($(this).hasClass('chzn-single-with-drop')){
				$(this).blur().removeClass('chzn-single-with-drop').next().css({'left':'-9000px'}).parent().removeClass('chzn-container-active').css('z-index', '0').find('li.result-selected').removeClass('highlighted');
			}else{
				$(this).blur().addClass('chzn-single-with-drop').next().css({'left':'0', 'top':'27px'}).parent().addClass('chzn-container-active').css('z-index', '10').find('li.result-selected').addClass('highlighted');
				if(!$('#country_chzn li.group-result:eq(0)').next('li.group-option').length) $('#country_chzn li.group-result').hide();
			}
		});
		
		$('.chzn-results li.group-option').live('mouseover', function(){
			$(this).parent().find('li').removeClass('highlighted');
			$(this).addClass('highlighted');
		}).live('mouseout', function(){
			$(this).removeClass('highlighted');
		});
		
		$('#country_chzn li.group-option').click(function(){	//Select Country
			 $("#sldProvince").text("")
			 $("#getProvinces").text("")
			 $("#sldProvinceHiden").val("")
			 var id=this.value
			$.post("/home/plt_PltProvince_getAllProByCtr",{"countryPkey":id},function(data){
				  var da = JSON.parse(data);
				$.each(da.provinces, function (index, value) {
					 $("#getProvinces").append('<li class="group-option active-result" value='+value.pkey+'>'+value.name+'</li>')
		            })  
			  })
			var obj=$('#country_chzn li.group-option').removeClass('result-selected').index($(this));
			var s_cid=$('select[name=country_id]').val();
			$(this).addClass('result-selected').parent().parent().css({'left':'-9000px'}).parent().removeClass('chzn-container-active').children('a').removeClass('chzn-single-with-drop').find('span').text($(this).text()).parent().parent().prev().find('option').eq(obj+1).attr('selected', 'selected');
			$("#sldCountryHiden").val(this.value);
			var cid=$('select[name=country_id]').val();
			(s_cid!=cid) && user_obj.get_state_from_country(cid);	//change country
		});
		
		$('#zoneId li.group-option').live('click', function(){
			$("#sldProvinceHiden").val(this.value);
			var obj=$('#zoneId li.group-option').removeClass('result-selected').index($(this));
			$(this).addClass('result-selected').parent().parent().css({'left':'-9000px'}).parent().removeClass('chzn-container-active').children('a').removeClass('chzn-single-with-drop').find('span').text($(this).text()).parent().parent().prev().find('option').eq(obj+1).attr('selected', 'selected');
		});
			
		$(document).click(function(e){ 
			e = window.event || e; // 兼容IE7
			obj = $(e.srcElement || e.target);
			if (!$(obj).is("#country_chzn, #country_chzn *")) { 
				$('#country_chzn').removeClass('chzn-container-active').css('z-index', '0').children('a').blur().removeClass('chzn-single-with-drop').end().children('.chzn-drop').css({'left':'-9000px'}).find('input').val('').parent().next().find('.group-option').addClass('active-result');
			} 
			if (!$(obj).is("#zoneId .chzn-container, #zoneId .chzn-container *")) { 
				$('#zoneId .chzn-container').removeClass('chzn-container-active').css('z-index', '0').children('a').blur().removeClass('chzn-single-with-drop').end().children('.chzn-drop').css({'left':'-9000px'}).find('input').val('').parent().next().find('.group-option').addClass('active-result');
			} 
		});
		
		//JS search result from tagert tags
		jQuery.expr[':'].Contains = function(a,i,m){
			return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase())>=0;
		};
		function filterList(input, list) {
			$(input)
			.change( function () {
				var filter = $(this).val();
				if(filter) {
					$matches = $(list).find('li:Contains(' + filter + ')');
					$('li', list).not($matches).removeClass('active-result');
					$matches.addClass('active-result');
				}else {
					$(list).find("li").addClass('active-result');
				}
				return false;
			})
			.keyup( function () {
				$(this).change();
			});
		}
		filterList("#country_chzn .chzn-search input", $("#country_chzn .chzn-results"));
		filterList("#zoneId .chzn-search input", $("#zoneId .chzn-results"));
		


		/*$('button[id=useAddress]').click(function(){	//会员处理地址信息
		});*/
		function trimAll(str) {
		     return str.replace(/\s+/g, "");
		    }
		$('.editAddr').on('click', 'button[id=useAddress]', function(){
			
			if(!check_form_address()){return false;}
			$(this).attr('disabled', 'disabled');
			var obj=$('.editAddr form');
			var typeAddr=parseInt(obj.find('input[name=typeAddr]').val())==1?1:0;
			if(typeAddr==1){
				cart_obj.checkout_no_login();
			}else{
				var firstName= trimAll($("#setAddressId input[name='FirstName']").val())
				var lastName= trimAll($("#setAddressId input[name='LastName']").val())
				var ZipCode= trimAll($("#setAddressId input[name='ZipCode']").val())
				var country=$("#sldCountryHiden").val()
				var state=$("#sldProvinceHiden").val()
				var City=trimAll($("#setAddressId input[name='City']").val())
				var AddressLine1=trimAll($("#setAddressId input[name='AddressLine1']").val())
				var AddressLine2=trimAll($("#setAddressId input[name='AddressLine2']").val())
				var AddressLine=AddressLine1+" "+AddressLine2
				var PhoneNumber=$("#setAddressId input[name='PhoneNumber']").val()
				var pkey=$('#setAddressId #usrpurphaseLinepkey').val()
				$('#setAddressId #usrpurphaseLinepkey').val(0)
				$.post('/home/usr_UsrPurchaseLine_addAddress',$.param({'bean.pkey':pkey})+'&'+ $.param({'bean.addrsstype':0})+'&'+ $.param({'bean.country':country})+'&'+$.param({'bean.name':firstName})+'&'+$.param({'bean.surname':lastName})+'&'+$.param({'bean.emailcode':ZipCode})+'&'+$.param({'bean.region':state})+'&'+$.param({'bean.city':City})+'&'+$.param({'bean.address':AddressLine})+'&'+$.param({'bean.phonenumber':PhoneNumber}), function(data){
					if(data.success){
						window.top.location.reload();
					}
				}, 'json');
				
			}
			$(this).removeAttr('disabled');
			return false;
		});


		
		$('input[name=Email], input[name=FirstName], input[name=LastName], input[name=PhoneNumber]').focus(function(){$(this).next().next('p.errorInfo').text('');});
		$('input[name=AddressLine1], input[name=City], input[name=ZipCode]').focus(function(){$(this).next('p.errorInfo').text('');});
		/*$('select[name=tax_code_type]').change(function(){
			maxlen=$(this).val()==1?11:($(this).val()==2?14:12);
			$(this).next('input[name=tax_code_value]').attr('maxlength', maxlen).val('');
		});*/
		function set_tax_code_value(obj, v){
			maxlen=obj.val()==1?11:(obj.val()==2?14:12);
			obj.next('input[name=tax_code_value]').attr('maxlength', maxlen);
			v==1 && obj.next('input[name=tax_code_value]').val('');
		}
		$('select[name=tax_code_type]').change(function(){set_tax_code_value($(this), 1);});
		set_tax_code_value($('select[name=tax_code_type]'));
		
		$('select[name=country_id]').change(function(){ //使用谷歌浏览器的自动表单填写功能，出现country_id自动选择，相关联效果不能自动实现
			var name=$('select[name=country_id] option:selected').text(),
				cid=$('select[name=country_id]').val();
			$('#country_chzn li.group-option').each(function(){
				if($(this).text()==name){
					$(this).click();
					user_obj.get_state_from_country(cid); //已经自动选择国家选项，需要执行加载省份
				}
			});
		});
		
		function check_form_address(){
			typeAddr=$('.editAddr form input[name=typeAddr]').val();
			firstname=$('.editAddr form input[name=FirstName]');
			lastname=$('.editAddr form input[name=LastName]');
			address=$('.editAddr form input[name=AddressLine1]');
			city=$('.editAddr form input[name=City]');
			state_obj=$('.editAddr form #sldProvince');
			country=$('.editAddr form #sldCountry');
			taxcode=$('.editAddr form #taxCodeValue');
			tariff=$('.editAddr form #tariffCodeValue');
			zipcode=$('.editAddr form input[name=ZipCode]');
			phone=$('.editAddr form input[name=PhoneNumber]');
			firstnameTips=lastnameTips=addressTips=cityTips=stateTips=countryTips=taxTips=tariffTips=zipTips=phoneTips='';
			
			if(typeAddr==1){
				email=$('.editAddr form input[name=Email]');
				emailTips='';
			
				if(email.val()=='')
					emailTips=lang_obj.user.address_tips.email;
				else if(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(email.val())===false)
					emailTips=lang_obj.user.address_tips.email_format;
					
				email.size() && email.next('p.errorInfo').text(emailTips);
			}
			
			if(firstname.val()=='')
				firstnameTips=lang_obj.user.address_tips.firstname;
			else if(ueeshop_config.lang!='zh_TW' && ueeshop_config.lang!='jp' && firstname.val().length<2)
				firstnameTips=lang_obj.user.address_tips.firstname_length;

			if(lastname.val()=='')
				lastnameTips=lang_obj.user.address_tips.lastname;
			else if(ueeshop_config.lang!='zh_TW' && ueeshop_config.lang!='jp' && lastname.val().length<2)
				lastnameTips=lang_obj.user.address_tips.lastname_length;

			if(address.val()=='')
				addressTips=lang_obj.user.address_tips.address;
			else if(ueeshop_config.lang!='zh_TW' && ueeshop_config.lang!='jp' && address.val().length<5)
				addressTips=lang_obj.user.address_tips.address_length

			if(city.val()=='')
				cityTips=lang_obj.user.address_tips.city;
			else if(ueeshop_config.lang!='zh_TW' && ueeshop_config.lang!='jp' && city.val().length<3)
				cityTips=lang_obj.user.address_tips.city_length;

			if(country.text()=='')
				countryTips=lang_obj.user.address_tips.country;
			if(typeof(state_obj.attr("disabled"))=="undefined" && state_obj.val()==-1)	//typeof($("#aid").attr("rel"))=="undefined"
				stateTips=lang_obj.user.address_tips.state;
			else if(state_obj.text()=='')
				stateTips=lang_obj.user.address_tips.state;
			if(typeof(taxcode.attr("disabled"))=="undefined"){
				str=taxcode.prev().val()==1?'CPF':'CNPJ';
				taxlen=taxcode.attr('maxlength');
				if(taxcode.val()=='')
					taxTips=(lang_obj.user.address_tips.taxcode).replace('%str%', str);
				else if(taxcode.val().length<taxlen)
					taxTips=(lang_obj.user.address_tips.taxcode_length).replace('%str%', str).replace('%taxlen%', taxlen);
			}
			
			if(typeof(tariff.attr("disabled"))=="undefined"){
				str=tariff.prev().val()==3?'Personal ID':'VAT ID';
				if(tariff.val()=='')
					tariffTips=(lang_obj.user.address_tips.tariff).replace('%str%', str);
				else if(tariff.val().length<12)
					tariffTips=(lang_obj.user.address_tips.tariff_length).replace('%str%', str);
			}

			if(zipcode.val()=='')
				zipTips=lang_obj.user.address_tips.zip;
			else if(ueeshop_config.lang!='zh_TW' && ueeshop_config.lang!='jp' && zipcode.val().length<4)
				zipTips=lang_obj.user.address_tips.zip_length;

			if(phone.val()=='')
				phoneTips=lang_obj.user.address_tips.phone;
			else if(!(/^[0-9\-]+$/g.test(phone.val())))//phone.val()==''
				phoneTips=lang_obj.user.address_tips.phone_format;
			else if(phone.val().replace('-', '').length<7)
				phoneTips=lang_obj.user.address_tips.phone_length;
			
			
			firstname.size() && firstname.next().next('p.errorInfo').text(firstnameTips);
			lastname.size() && lastname.next().next('p.errorInfo').text(lastnameTips);
			address.size() && address.next('p.errorInfo').text(addressTips);
			city.size() && city.next('p.errorInfo').text(cityTips);
			state_obj.size() && state_obj.parent(".chzn-single").parent().next('p.errorInfo').text(stateTips);
			country.size() && country.parent(".chzn-single").parent().next('p.errorInfo').text(countryTips);
			taxcode.size() && taxcode.next().next('p.errorInfo').text(taxTips);
			tariff.size() && tariff.next().next().next('p.errorInfo').text(tariffTips);
			zipcode.size() && zipcode.next('p.errorInfo').text(zipTips);
			phone.size() && phone.next().next('p.errorInfo').text(phoneTips);
			
			if(typeAddr==1){if(email.size() && emailTips) return false;}//email 不存在导致js提交错误
			if(firstnameTips==''&&lastnameTips==''&&addressTips==''&&cityTips==''&&stateTips==''&&countryTips==''&&taxTips==''&&tariffTips==''&&zipTips==''&&phoneTips=='')
				return true;
			else
				return false;
		}
	},
	
	get_state_from_country:function(cid){
		$.ajax({
			url:"/",
			async:false,
			type:"POST",
			data:{"CId": cid, do_action:'user.select_country'},
			dataType:"json",
			success: function(data){
				if(data.ret==1){
					d=data.msg.contents;
					if(d==-1){
						$('#zoneId').css({'display':'none'}).find('select').attr('disabled', 'disabled');
						$('#state').css({'display':'table-row'}).find('input').removeAttr('disabled');
					}else{
						$('#zoneId').css({'display':'table-row'}).find('select').removeAttr('disabled');
						$('#state').css({'display':'none'}).find('input').attr('disabled', 'disabled');
						str='';
						var vselect='<option value="-1"></option>';
						var vli='';
						for(i=0;i<d.length;i++){
							vselect+='<option value="'+d[i]['SId']+'">'+d[i]['States']+'</option>';
							vli+='<li class="group-option active-result">'+d[i]['States']+'</li>';
						}
						$('#zoneId select').html(vselect);
						$('#zoneId ul').html(vli);
						$('#zoneId .chzn-container a span').text('Please select---');
					}
					$('#countryCode').val('+'+data.msg.code);
					$('#phoneSample span').text(data.msg.code);
					if(data.msg.cid==30){
						$('#taxCode').css({'display':'table-row'}).find('select, input').removeAttr('disabled');
						$('#tariffCode').css({'display':'none'}).find('select, input').attr('disabled', 'disabled').parent().find('p.errorInfo').text('');
					}else if(data.msg.cid==211){
						$('#tariffCode').css({'display':'table-row'}).find('select, input').removeAttr('disabled');
						$('#taxCode').css({'display':'none'}).find('select, input').attr('disabled', 'disabled').parent().find('p.errorInfo').text('');
					}else{
						$('#taxCode').css({'display':'none'}).find('select, input').attr('disabled', 'disabled').parent().find('p.errorInfo').text('');
						$('#tariffCode').css({'display':'none'}).find('select, input').attr('disabled', 'disabled').parent().find('p.errorInfo').text('');
					}
				}
			}
		});
	},
	
	set_default_address:function(AId){
		$.ajax({
			url:"/",
			async:false,
			type:'post',
			data:{'do_action':'user.get_addressbook', 'AId':AId},
			dataType:'json',
			success:function(data){
				if(data.ret==1){
					$('input[name=edit_address_id]').val(data.msg.address.AId);
					$('input[name=FirstName]').val(data.msg.address.FirstName).parent().next().find('input[name=LastName]').val(data.msg.address.LastName)
					$('input[name=AddressLine1]').val(data.msg.address.AddressLine1);
					$('input[name=AddressLine2]').val(data.msg.address.AddressLine2);
					$('input[name=City]').val(data.msg.address.City);
					
					var index=$('select[name=country_id]').find('option[value='+data.msg.address.CId+']').eq(0).attr('selected', 'selected').index();
					$('#country_chzn a span').text(data.msg.country.Country);
					$('#country_chzn ul.chzn-results li.group-option').eq(index).addClass('result-selected');
					user_obj.get_state_from_country(data.msg.address.CId);
					if(data.msg.address.CId==30||data.msg.address.CId==211){
						$('select[name=tax_code_type]').find('option[value='+data.msg.address.CodeOption+']').attr('selected', 'selected');
						$('input[name=tax_code_value]').attr('maxlength', (data.msg.address.CodeOption==1?11:(data.msg.address.CodeOption==2?14:12))).val(data.msg.address.TaxCode);
					}
					
					if(data.msg.country.HasState==1){
						$('#zoneId div a span').text(data.msg.address.StateName);
						var sindex=$('select[name=Province]').find('option[value='+data.msg.address.SId+']').attr('selected', 'selected').index();
						$('#zoneId ul.chzn-results li.group-option').eq(sindex-1).addClass('result-selected');
					}else{
						$('input[name=State]').val(data.msg.address.State);
					}
					
					$('input[name=ZipCode]').val(data.msg.address.ZipCode);
					$('input[name=CountryCode]').val('+'+data.msg.address.CountryCode).next().find('input[name=PhoneNumber]').val(data.msg.address.PhoneNumber);
					
				}else if(data.ret==2){
					$('input[name=edit_address_id]').val('');
					$('.editAddr input[name=FirstName]').val('').parent().next().find('input[name=LastName]').val('');
					$('input[name=AddressLine1]').val('');
					$('input[name=AddressLine2]').val('');
					$('input[name=City]').val('');
					$('input[name=tax_code_value]').val('');
					$('input[name=State]').val('');
					$('input[name=ZipCode]').val('');
					$('input[name=CountryCode]').val('').next().find('input[name=PhoneNumber]').val('');

					var index=$('select[name=country_id]').find('option[value='+data.msg.country.CId+']').eq(0).attr('selected', 'selected').index();
					$('#country_chzn a span').text(data.msg.country.Country);
					$('#country_chzn ul.chzn-results li.group-option').eq(index).addClass('result-selected');
					user_obj.get_state_from_country(data.msg.country.CId);
				}else{
					global_obj.win_alert(data.msg.error);
				}
			}
		});
	},
	
	address_init:function(){
		$('#edit_billing_addr').click(function(){
			var addrId=$(this).attr('addrid');
			$('#addressForm .errorInfo').html('');
			user_obj.set_default_address(addrId);
			$('#addressForm').slideUp('fast', function(){
				$('.billing_addr, .shipping_addr').slideUp('fast', function(){
					$('#addressForm').children('.big').text($('.billing_addr').children('.big').text());
					$('#addressForm').slideDown('fast');
				});
			});
			return false;
		});
		
		$('#addAddress').click(function(){
			
			
			$('#addressForm .errorInfo').html('');
			user_obj.set_default_address(0);
			$('#addressForm').slideUp('fast', function(){
				$('.billing_addr, .shipping_addr').slideUp('fast', function(){
					$('#addressForm').children('.big').text($('.shipping_addr').children('.big').text());
					$('#addressForm').slideDown('fast');
					
					
					$("#adname").val("");
					$("#adsurname").val("");
					$("#ademailcode").val("");
					$("#adcity").val("");
					$("#adaddress").val("");
					$("#adphonenumber").val("");
				});
			});
			return false;
		});
		
		$('.edit_shipping_addr').click(function(){
			var addrId=$(this).attr('addrid');
			$('#addressForm').slideUp('fast', function(){
				$('#addressForm .errorInfo').html('');
				user_obj.set_default_address(addrId);
				$('.billing_addr, .shipping_addr').slideUp('fast', function(){
					$('#addressForm').children('.big').text($('.shipping_addr').children('.big').text());
					$('#addressForm').slideDown('fast');
				});
			});
			return false;
		});
		
		$('#cancelAddr').click(function(){
			$('#addressForm').slideUp('fast', function(){
				$('.billing_addr, .shipping_addr').slideDown('fast');
			});
			return false;
		});
		
		$('.del_shipping_addr').click(function() {
			return window.confirm(lang_obj.user.delete_shipping);
		});
	},
	
	inbox_init:function(){
		$('.inbox_menu ul a').off().on('click', function(){
			$('.inbox_menu .menu').eq($(this).parent().index()).removeClass('hide').siblings().addClass('hide');
			$(this).addClass('current').parent().siblings().children('a').removeClass('current');
		});
		
		$('#inbox_form').submit(function(){
			if(global_obj.check_form($('#inbox_form *[notnull]'))){return false};
		});
		
		user_obj.inbox_ajax_list('inbox_list');
		user_obj.inbox_ajax_list('outbox_list');
	},
	
	inbox_click:function(){
		$('#turn_page').off().on('click', 'a', function(){
			var name=$(this).attr('data'),
				obj=$('#'+name),
				page=$(this).attr('page');
			
			obj.attr('page', page);
			user_obj.inbox_ajax_list(name);
			return false;
		});
	},
	
	inbox_ajax_list:function(name){
		var obj=$('#'+name),
			page=obj.attr('page');
		
		$.post('/', {Name:name, Page:page, do_action:'user.get_inbox_list'}, function(data){
			data=$.evalJSON(data);
			if(data.ret==1){
				obj.html(data.msg);
				user_obj.inbox_click();
			}
		});
	}
};

$(function (){
	//新增
	$('.wide').on('click', 'a[data-vip=1]', function (){
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
		obj.find('#signin_module').css({left:w.width()/2-220});
		global_obj.div_mask();
		$('.popbtn_box .qxclose').click(function (){
			$('#signin_module').remove();
			global_obj.div_mask(1);
		});
		return false;
	});
});
