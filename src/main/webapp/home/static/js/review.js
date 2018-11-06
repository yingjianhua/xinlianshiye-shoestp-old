/*
Powered by shoestp
*/
(function ($) {
    //点赞
    // $('.likeWrapper a').bind('click', function(){
    // 	var e=$(this);
    // 	$.ajax({
    // 		url:e.attr('href'),
    // 		type:'get',
    // 		dataType:'json',
    // 		success:function(result){
    // 			if(result.ret==1){
    // 				e.find('span').text(result.msg);
    // 			}else{
    // 				window.top.location='/account/sign-up.html';
    // 			}
    // 		}
    // 	});
    // 	return false;
    // });
    //
    //评论回复显藏效果
    $('.reply_btn').bind('click', function () {
        $(this).parents('.reply').find('.write_reply').toggleClass("hide");
        $(this).parents('.reply').find('.w_review_replys').toggleClass("hide");
        return false;
    });
    // $('.write_reply textarea').focus(function () {
    //     if ($(this).val() == 'Add your reply here...') {
    //         $(this).val('');
    //     }
    //     $(this).next('.errors').text('');
    // }).blur(function () {
    //     if ($(this).val() == '') {
    //         $(this).val('Add your reply here...');
    //     }
    // }).click(function () {
    //     if ($(this).parent().next().text().length > 0) {
    //         $(this).parent().next().text('');
    //     }
    // });

    //评论回复
    // $('.write_reply .textbtn').bind('click',function(){
    // 	var e=$(this),
    // 		value=e.parents('form').find('textarea').val();
    // 	e.prev('.error').text('');
    // 	if(!value || value=='Add your reply here...' || value.replace(/(\s|　)/g,'')==''){
    // 		e.prev('.error').text('Please input your reply.');
    // 		return false;
    // 	}
    // 	if(value.length>5000){
    // 		e.prev('.error').text('Is your Review Content correct? Our system requires a maximum of 5000 characters.');
    // 		return false;
    // 	}
    // 	e.attr('disabled', true);
    // 	$.ajax({
    // 		url:'/?do_action=user.submit_review',
    // 		data:e.parents('form').find('input[type=hidden], textarea'),
    // 		type:'post',
    // 		dataType:'json',
    // 		complete:function(xhr, status){
    // 			e.attr('disabled',false);
    // 		},
    // 		success:function(result){
    // 			if(result && result.msg.ok){
    // 				e.parents('form').find('textarea').val('Add your reply here...');
    // 				e.prev('.error').text('Return the contents of submitted successfully, waiting for the audit.');
    // 			}else{
    // 				e.prev('.error').text('Please refresh this page and try again.');
    // 			}
    // 		}
    // 	});
    // 	return false
    // });

    //字长判断
    $.fn.checkCharLength = function (content) {
        var e = $(this);
        e.change(function (event) {
            var curLength = e.val().length;
            var maxlength = e.attr('maxlength');
            if (curLength > maxlength) {
                e.val($.trim(e.val()).substr(0, maxlength)).trigger('change');
                return;
            }
            $(content).text(maxlength - curLength).parent().toggleClass('red', curLength > maxlength);
        }).keyup(function () {
            e.trigger('change');
        });
    }

    $('#review_content').checkCharLength('#review_content_char');

    //star
    $('#review_form .star').mousemove(function (e) {
        var star = Math.floor((e.clientX - $(this).offset().left) / 20 + 1),
            cl = $(this).attr('class');
        $(this).attr('class', cl.replace(/star_h\d/, 'star_h' + star));
    }).mouseout(function (e) {
        var cl = $(this).attr('class');
        $(this).attr('class', cl.replace(/star_h\d/, 'star_h0'));
    }).click(function (e) {
        var star = Math.floor((e.clientX - $(this).offset().left) / 20 + 1),
            cl = $(this).attr('class');
        $(this).next().val(star);
        $(this).attr('class', cl.replace(/star_b\d/, 'star_b' + star));
        $(this).attr('data', star);
    });

    //评论提交
    $("#Submit").on("click", function () {
        var json = {
            "overall_rating": parseInt($("ul.rating span.overall_rating").attr("data")),
            "usefulness": parseInt($("ul.rating span.usefulness").attr("data")),
            "build_quality": parseInt($("ul.rating span.build_quality").attr("data")),
            "ease_of_use": parseInt($("ul.rating span.ease_of_use").attr("data")),
            "reference_price": parseInt($("ul.rating span.reference_price").attr("data"))
        }
      var id=$("input[type='hidden'][name='id']").val();  
      $.ajax({
            url: '/home/pdt_PdtProduct_addcomment',
            type: 'POST',
            data: {
                id: $("input[type='hidden'][name='id']").val(),
                comment: $("#review_content").val(),
                images: $("input[type='hidden'][name='images']").val(),
                satisfaction: JSON.stringify(json)
            },
            success: function (data) {
            	console.log(data);
                if (data.ret != 1) {
                	layer.msg(data.msg,{icon:2,time:2000});
                } else {
                    layer.msg(data.msg,{icon:1,time:2000});
                    setTimeout(function(){window.location.href='/home/pdt_PdtProduct_gtProductsInfo?id='+id;},1000);
                }
            },
            error: function () {

            }
        })
    })
    $("input.file[type='file']").on("change", function () {
        var f = new FormData();
        f.append("file", this.files[0])
        $.ajax({
            url: '/home/pdt_PdtProduct_upload',
            type: 'POST',
            data: f,
            async: false,
            cache: false,
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success: function (data) {
                if (data.ret == 1) {
                    var images = $("input[type='hidden'][name='images']").val();
                    if(images != ""){
                    	images = images.split(",")
                    }else{
                    	images = [];
                    }
                    layer.msg(lang_obj.my_inquiry_publish.submitsuccess,{icon:1,time:2000});
                    images.push(data.result.url)
                    $("input[type='hidden'][name='images']").val(images);
                }
            },
            error: function () {
            	layer.msg(lang_obj.mobile.Failed_to_send,{icon:2,time:2000});
            }
        })

    })
    // $('#review_form').submit(function () {
    //     var flag = 0,
    //         e = $(this),
    //         error = {
    //             rating_0: 'Your price is required.',
    //             rating_1: 'Your ease of use is required.',
    //             rating_2: 'Your build quality is required.',
    //             rating_3: 'Your usefulness is required.',
    //             rating_4: 'Your overall rating is required.',
    //             review_title: 'Your title is required.'
    //         };
    //
    //     $(".error_info", e).html("");
    //     if (global_obj.check_form($('*[notnull]', e))) flag = 1;
    //     $('*[notnull], e').each(function () {
    //         var id = $(this).attr('id');
    //         if (!$.trim($(this).val()) && error[id]) {
    //             $(this).next().html(error[id]);
    //             flag = 1;
    //         }
    //         /*if(id=="review_content" && $(this).val().length<30){
    //             $(this).next().html("Please write at least 30 characters.");
    //             flag=1;
    //         }else */
    //         if (id == "review_content" && $(this).val().length > 5000) {
    //             $(this).next().html("Remaining characters:Please don\'t exceed 5,000 characters.");
    //             flag = 1;
    //         }
    //     });
    //
    //     if (flag) return false;
    //
    //     var iframe = window.frames["reviews_img"];
    //     var doc = iframe.contentDocument ? iframe.contentDocument : iframe.document;
    //     if (!$(doc).parent('div:hidden').length) {//submit the images
    //         doc.forms[0].submit();
    //         return false;
    //     }
    //
    //     e.find("input:submit").attr('disabled', true);
    // });

    //Facebook分享
    function t(e, t, n, r, i, s, o) {
        n = n || 640;
        r = r || 400;
        i = (window.screen.width - n) / 2;
        s = (window.screen.height - r) / 2;
        o || (o = ",menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=yes,resizable=yes");
        return window.open(e, t, "width=" + n + ", height=" + r + ", screenX=" + i + ", screenY=" + s + ", top=" + s + ", left=" + i + o);
    }

    $.fn.facebookShareDialog = function (n) {
        var $this = this,
            obj = {
                app_id: ueeshop_config.FbAppId,
                redirect_uri: ueeshop_config.domain + "/?m=user&do_action=facebook_callback",
                display: "popup",
                link: n.fblink.replace(/^http:\/\//, "http://"),
                picture: n.fbpicture.replace(/^https/, "http"),
                name: n.fbname,
                caption: "By " + n.fbuser,
                actions: $.toJSON({
                    name: "Read All Reviews",
                    link: n.fblink2.replace(/^http:\/\//, "http://")
                }),
                description: ''
            };

        //$('.review_item .content').html("http://www.facebook.com/dialog/feed?"+jQuery.param(obj));
        t("http://www.facebook.com/dialog/feed?" + jQuery.param(obj), "facebook", 400, 350);
        return false;
    }
    $('.fb_share').click(function () {
        return;
        var $this = $(this),
            o = $this.data("share");
        $this.facebookShareDialog(o);
        return false;
    });

})(jQuery);