<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
        <link href="./static/css/style(2).css" rel="stylesheet" type="text/css">
          <style type="text/css">
            .home_box{width:93.4%; height:14rem; overflow:hidden; margin:.75rem auto;}
            .home_box .small_pro{width:49.72%;}
            .home_box .small_pro .c{width:91.6%; margin:0 auto; padding-top:.75rem; background-color:#fff;}
            .home_box .small_pro .c .img{height:9rem; min-height:9rem; width:100%; text-align:center; background:url(../../../images/loading.gif) no-repeat center; background-size:11.7%; position:relative;}
            .home_box .small_pro .c .img img{vertical-align:middle; max-height:100%;}
            .home_box .small_pro .c .img .icon_discount{width:38px; height:25px; color:#fff; line-height:12px; padding-top:5px; background:#E25505; position:absolute; top:0; right:0;}
            .home_box .small_pro .c .img .icon_discount b{font-size:16px;}
            .home_box .small_pro .c .img .icon_discount_foot{width:0; height:0; border-left:19px transparent solid; border-right:19px transparent solid; border-top:5px #E25505 solid; position:absolute; right:0; top:30px;}
            .home_box .small_pro .c .proname{height:2rem; overflow:hidden; padding-top:.625rem; line-height:1rem;}
            .home_box .small_pro .c .proname a{font-size:.75rem; color:#333;}
            .home_box .small_pro .c .price{line-height:1rem; padding-top:.125rem; padding-bottom:.625rem; font-size:.75rem; color:#696969;}
            .home_box .small_pro .c .price span, .home_box .small_pro .c .price span span{font-size:.75rem; color:#d70d0d;}
          </style>
    </head>

    <body>
 		    <%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div class="crumb clean">
                <a href="/">
                    <span class="icon_crumb_home">
                    </span>
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/pdt_PdtProduct">
                    Product List
                </a>
            </div>
            <div id="filter" class="clean ui_border_b">
                <ul class="prod_sort fl">
                    <li class="dropdown">
                        <a href="javascript:;" class="dropdown_toggle">
                            Sort By
                            <i class="icon_sort">
                            </i>
                            <em>
                            </em>
                        </a>
                    </li>
                </ul>
                <ul class="dropdown_menu ui_border_t">
                    <li class="ui_border_b li_a_list">
                        <a href="../mobile/products.jsp?" class="current">
                            Default
                        </a>
                    </li>
                    <li class="ui_border_b li_a_list">
                        <a href="../mobile/products.jsp?MostPopular">
                            Most Popular
                        </a>
                    </li>
                    <li class="ui_border_b li_a_list">
                        <a href="../mobile/products.jsp?Sales">
                            Sales
                        </a>
                    </li>
                    <li class="ui_border_b li_a_list">
                        <a href="../mobile/products.jsp?Favorites">
                            Favorites
                        </a>
                    </li>
                    <li class="ui_border_b li_a_list">
                        <a href="../mobile/products.jsp?New">
                            New
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
                    <div class="btn_global btn_view">
                        <button class="btn_global FontBgColor">
                            Load More
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
                            Sign In
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
        <div align="center">
            <!-- Google Tag Manager (noscript) -->
            <noscript>
                &lt;iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KNPHSJ6"
                height="0" width="0" style="display:none;visibility:hidden"&gt;&lt;/iframe&gt;
            </noscript>
            <!-- End Google Tag Manager (noscript) -->
        </div>
        <script type="text/javascript" src="./static/js/products.js"></script>
        <script src="./static/js/get_dynamic_config.js"></script>
        <script src="./static/js/get_static_config.0.379.2.2.1342.14.2.22.9.8.2.6.285.js"></script>
        <script src="./static/js/localization.en.0.043117e7a56a2e3ea008a802da2a0076_9f9568ee0491374470a5f78170eb7aed.js">
        </script>
        <div id="lc_invite_layer" style="text-align: left; display: none; z-index: 16777261;">
        </div>
        <div id="lc_overlay_layer" style="background-color: rgb(0, 0, 0); position: fixed; left: 0px; top: 0px; z-index: 16777260; display: none; width: 100%; height: 100%;">
        </div>
           <script type="text/javascript">

    var _page = 1;
    var _orderfld = "MostPopular"
    var _order = true
    var _showItem = 20
   	var page = 1;
   	var limit = 8;
   	var key = "";
    $(document).ready(function () {
    	key_value();
    	if(key!=null && key != ""){
    		orderMoreButton(key,_order,page, limit)
    	}else{
    		MoreButton(page, limit);
    	}
    })
    $(".li_a_list").click(function(){
        	key_value();
           	$(".ui_border_radius").empty();
    });


    $(function(){
    	var pages = 1;
    	$("button[name=MoreButton]").click(function(){
    		key_value();
    		page = page+pages;
    		if(key!=null && key !=""){
    			orderMoreButton(key,_order,page, limit)
    		}else{
	    		MoreButton(page, limit);

    		}
    	})
    })

    function key_value(){
    	var htmlvalue = location.href.split("?")[1];
        if( htmlvalue != null && htmlvalue != "" ){
        	if(htmlvalue == "MostPopular" ){
        		 key = htmlvalue;
        		 _order = "";
        	}
           	if(htmlvalue == "Sales" ){
           		 key = htmlvalue;
           		_order = "";
           	}
           	if(htmlvalue == "Favorites" ){
           		 key = htmlvalue;
           		_order = true;
           	}
           	if(htmlvalue == "New"){
           		 key = htmlvalue;
           		_order = "";
           	}
           	if(htmlvalue == "Default" ){
           		 key = htmlsvalue;
           		_order = "";
           	}
        }
    }

    function MoreButton(page, limit){
    	$.ajax({
    		type:'post',
    		async:false,
    		url:'../home/pdt_PdtProduct_gtProductsIndexListAjax',
    		data:{
                page: page,
                limit: limit,
    		},
    		dataType:'json',
    		success:function(data){
    			if(data.items.length>0){
        			$.each(data.items,function(key,value){
        				$(".ui_border_radius").append('<div class="small_pro fl ui_border_r">'+
    									         '      <div class="c">'+
    									         '           <div class="img pic_box">'+
    									         '               <a href="../mobile/goods-info.jsp?id='+value.pkey+'"'+
    									         '               title="'+value.name+'">'+
    									         '                   <img src="${envConfig.imageBaseUrl}'+value.name.split(",")[0]+'">'+
    									         '                   <span>'+
    									         '                  </span>'+
    									         '               </a>'+
    									         '           </div>'+
    									         '           <div class="proname">'+
    									         '               <a href="../home/pdt_PdtProduct_gtProductsInfo?id='+value.pkey+'"'+
    									         '               title="'+value.name+'">'+
    									         '                   '+value.name+''+
    									         '               </a>'+
    									         '           </div>'+
    									         '           <div class="price">'+
    									         '              <span>'+
    									         '                   $'+
    									         '                   <span class="price_data" keyid="'+value.pkey+'">'+
    									         '                       '+value.cur_price+''+
    									         '                   </span>'+
    									         '              </span>'+
    									         '               USD'+
    									         '           </div>'+
    									         '       </div>'+
    									         '   </div>');
        			})
    			} else {
            layer.open({
              content: "没有产品了"
              ,skin: 'msg'
              ,time: 2
            });
    			}
    		}
    	})
    }

    function orderMoreButton(orderfld,order,page, limit){
    	$.ajax({
    		type:'post',
    		async:false,
    		url:'../home/pdt_PdtProduct_gtProductsIndexListAjax',
    		data:{
                orderfld: orderfld,
                order: order,
                page: page,
                limit: limit,
    		},
    		dataType:'json',
    		success:function(data){
    			if(data.items.length>0){
					$.each(data.items,function(key,value){
        				$(".ui_border_radius").append('<div class="small_pro fl ui_border_r">'+
    									         '      <div class="c">'+
    									         '           <div class="img pic_box">'+
    									         '               <a href="../mobile/goods-info.jsp?id='+value.pkey+'"'+
//     									         '               <a href="/home/pdt_PdtProduct_gtProductsInfo?id='+value.pkey+'"'+
// 		  										 '               <a href="/home/pdt_PdtProduct_gtProductsInfo?id='+value.pkey+'"'+
    									         '               title="'+value.name+'">'+
    									         '                   <img src="${envConfig.imageBaseUrl}'+value.name.split(",")[0]+'">'+
    									         '                   <span>'+
    									         '                  </span>'+
    									         '               </a>'+
    									         '           </div>'+
    									         '           <div class="proname">'+
    									         '               <a href="../home/pdt_PdtProduct_gtProductsInfo?id='+value.pkey+'"'+
    									         '               title="'+value.name+'">'+
    									         '                   '+value.name+''+
    									         '               </a>'+
    									         '           </div>'+
    									         '           <div class="price">'+
    									         '              <span>'+
    									         '                   $'+
    									         '                   <span class="price_data" keyid="'+value.pkey+'">'+
    									         '                       '+value.cur_price+''+
    									         '                   </span>'+
    									         '              </span>'+
    									         '               USD'+
    									         '           </div>'+
    									         '       </div>'+
    									         '   </div>'
						);
					})
    			} else {
            layer.open({
              content: "没有产品了"
              ,skin: 'msg'
              ,time: 2
            });
    			}
    		}
    	})
    };


    </script>
</body>
</html>
