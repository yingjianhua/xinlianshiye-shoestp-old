/*
 * 广州联雅网络
 */

(function ($){	
	//产品列表页效果
	var t=window.location.pathname,
		i=window.location.search,
   		n=window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	
	$('.dropdown_toggle').on('click', function(){
		var $this=$(this),
			$menu=$('.dropdown_menu');
		if($menu.is(':visible')){
			$this.removeClass('current');
			$menu.hide();
			$('#prolist_mask, #prolist_mask_footer').hide();
			$('html').removeClass('html_overflow');
		}else{
			$this.addClass('current');
			$menu.show();
			$('#prolist_mask, #prolist_mask_footer').show();
			$('html').addClass('html_overflow');
		}
	});
	/*$(document).on('touchstart', function(e){
		var $obj=$('.dropdown_toggle').parent();
		if(!(e.target==$obj[0] || $.contains($obj[0], e.target)) && $('.dropdown_menu:visible').length){
			$('.dropdown_menu').hide();
		}
	});*/
	$('.dropdown_modal').on('click', function(){
		$('.modal_side').css('visibility', 'visible').addClass('show');
		$('html').addClass('html_overflow');
		$('.modal_side .menu_list').css({'max-height':$(window).outerHeight()-$('.modal_side .close').outerHeight(true)-$('.modal_side .menu_button').outerHeight(true)});
	});
	$('.attr_son span').on('click', function(){
		var e=$(this).attr('id'),
			x=$(this).children('strong').text(),
			o=$(this).parents('.item').find('.current_list');
		'' === i && (i='?');
		if(-1 === i.indexOf('Narrow')){
			i += '&Narrow=';
		}
        if(-1 === i.indexOf(e)){
			'' === i ? i = '': i += '+', i += e;
			o.append('<span data-id="'+e+'">'+x+'</span>');
		}else{
            var t=i.indexOf(e),
            	n=i.indexOf('+', t);
			-1 === n && (n=i.length);
            var a=i.substring(t - 1, n);
			-1 !== a.indexOf('=') && (a=a.replace('=', ''));
            i=i.replace(a, '');
			o.find('span[data-id="'+e+'"]').remove();
        }
		console.log(i);
		$(this).toggleClass('current');
	});
	$('.refine_search').on('click', function(){
        if(i){
			-1 === i.indexOf('+') ? (i=i.replace('&Narrow=', '')) : (i=i.replace('Narrow=+', 'Narrow='));
            window.location.replace(t+i);
        }else{
        	window.location.replace(t);
		}
    }),
    $('.clear_all').on('click', function(){
        i=i.replace(/\&Narrow\=(.*)+/, ''),
        window.location.replace(t+i);
    })
	$('.img a img').load(function(){
		$(this).parent().parent('.img').css('background-image', 'none');
	});
	
	ajax_product_list({"page":0, "Data":$("#prolist").attr("data-pro")}, 1);
	
	/*
	$(document).scroll(function(){
		var $winTop		= $(window).scrollTop(),
			$winH		= $(window).height(),
			$listTop	= $("#prolist").offset().top,
			$listHeight	= $("#prolist").outerHeight(),
			$loadNum	= $listTop+$listHeight-$winH-30,
			$Num		= parseInt($("#prolist").attr('data-number'));
		
		if($winTop>=$loadNum && $Num==0){
			$("#prolist").attr('data-number', '1');
			var page=parseInt($("#prolist").attr("data-page"));
			if(!isNaN(page)){
				var Total=parseInt($("#prolist").attr('data-total'));
				if(page>=Total){
					return false;
				}
				$("#prolist").attr('data-page', page+1);
				ajax_product_list({"page":page+1, "Data":$("#prolist").attr("data-pro")}, 0);
			}
		}
	});
	*/
	function ajax_product_init(){
		$('#prolist .btn_view').off().on('tap', function(){
			var $Num=parseInt($("#prolist").attr('data-number'));
			if($Num==0){
				$(this).remove();
				$("#prolist").attr('data-number', '1');
				var page=parseInt($("#prolist").attr("data-page"));
				if(!isNaN(page)){
					var Total=parseInt($("#prolist").attr('data-total'));
					if(page>=Total){
						return false;
					}
					$("#prolist").attr('data-page', page+1);
					ajax_product_list({"page":page+1, "Data":$("#prolist").attr("data-pro")}, 0);
				}
			}
		});
		
		if($("#prolist .content_more").length){
			setTimeout(function(){
				$("#prolist .content_more").fadeOut();
			}, 2000);
		}
	}
	
	function ajax_product_list(data, clear){
		clear && $("#prolist").html('');
		$("#prolist").loading();
		$(".loading_msg").css({"top":0, "position":"initial", "width":"auto", "height":'4rem', "background-position":"center"});
		setTimeout(function(){
			$.ajax({
				url:"/?m=ajax&a=products_list",
				async:false,
				type:'post',
				data:data,
				dataType:'html',
				success:function(result){
					if(result){
						$("#prolist").append(result).unloading();
						$("#prolist").attr('data-number', '0');
						ajax_product_init();
					}
				}
			});
		}, 300);
	}
})(jQuery);