<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta content="telephone=no" name="format-detection">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="renderer" content="webkit">
        <!-- Google Tag Manager -->
        <!-- End Google Tag Manager -->
        <link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>
            Inquiry
        </title>
        
        <script src="../../seller/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" src="../../home/static/js/jquery-3.2.1.js">
        </script>
        <link href="../../home/static/css/global.css" rel="stylesheet" type="text/css">
        <link href="../../home/static/css/style.css" rel="stylesheet" type="text/css">
        <link href="../../home/static/css/style(1).css" rel="stylesheet" type="text/css">
        <link href="../../home/static/css/xunpanMC.css" rel="stylesheet" type="text/css">
        <link href="../../home/static/css/default.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css"  href="../../seller/static/admin/layui/css/layui.css"/>
        <style type="text/css">
            #livechat-compact-container {transform: translateY(-50%)!important;-webkit-transform:
            translateY(-50%)!important;}
        </style>
        <style>
          body{
            margin: 0;
        }
        .modal-box{
         position: fixed;
         top:0;
         left:0;
	     width:100%;
	     height:100%;
	     background:rgba(197, 189, 189, 0.164);
	     display: none;
        }
        .inquiry-modal{
            position: fixed;
            top: 30%;
            left: 50%;
    		transform: translate(-50%);
            width: 96%;
            margin:0 auto;
            background-color:#fff;
        }
        .inquiry-modal .inquiry-modal-box{
                overflow:auto;
                height: 209.688px;
                width: 96%;
            margin: 0 auto;
        }
        .inquiry-modal .inquiry-modal-item{
            display:-webkit-box;
            display: -moz-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-justify-content:space-between;  
            justify-content:space-between;
            -moz-box-pack:space-between;
            -webkit--moz-box-pack:space-between;
            box-pack:space-between;
            align-items:center;
            -webkit-align-items:center;
            box-align:center;
            -moz-box-align:center;
            -webkit-box-align:center;
            border-bottom: 1px dashed #e5e5e5;
            padding: 3% 0;
            width: 100%;
            height: 80px;
        }
        .inquiry-modal .inquiry-modal-item .inquiry-modal-item-img{
            width: 35%;
            height: 80px;
            border: 1px solid #e5e5e5;
        }
        .inquiry-modal .inquiry-modal-item .inquiry-modal-item-img img{
            object-fit: cover;
            width: 100%;
            height: 100%;
        }
        .inquiry-modal .inquiry-modal-item input{
            width: 6%;
        }
        .inquiry-modal .inquiry-modal-item .inquiry-modal-item-right{
            width: 50%;
        }
        .inquiry-modal .inquiry-modal-item .inquiry-modal-item-right .inquiry-modal-item-info{ 
            overflow: hidden;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3;
            -ms-text-overflow: ellipsis;
            text-overflow: ellipsis;
            white-space: normal;
            word-wrap:break-word;
            word-break:break-all;
            word-wrap:break-word;
            position: relative;
            max-height: 63px;
        }
        .inquiry-modal .inquiry-modal-item .inquiry-modal-item-right i,.inquiry-modal .inquiry-modal-title{
            text-align: right;
            height: 25px;
            width: 96%;
            margin: 0 auto;
        }
        .inquiry-modal .inquiry-modal-item .inquiry-modal-item-right i,.inquiry-modal .inquiry-modal-title i{
            display: inline-block;
            width: 14px;
            height: 14px;
            overflow: hidden;
            background: url(./static/images/normal.png) -349px -115px no-repeat;
            float: right;
            margin: 4px 3px;
        }
        .inquiry-modal .inquiry-modal-button{
            height: 40px;
            background-color: #ffffff;
            line-height: 40px;
            margin-top: 5%;
            border-top: 1px solid #f7f7f7;
        }
        .inquiry-modal .inquiry-modal-button .inquiry-submit,.inquiry-modal .inquiry-modal-button input{
            width: 5%;
        }
        .inquiry-modal .inquiry-modal-button .RFQ-title{
            width: 60%;
            display: inline-block;
            font-size: 0.8rem;
        }
        .inquiry-modal .inquiry-modal-button .inquiry-submit,.inquiry-modal .inquiry-modal-button .inquiry-cancel{
            padding: 1%;
            background-color: #c1c0c0;
            line-height: 20px;
            width: 15%;
            font-size: 0.8rem;
            color: #fff;
        }
    </style>
    </head>
    
    <body>
        <style type="text/css">
            #header_fix{overflow:visible;} .header_top{width:100%; max-width:100%;
            position:fixed; top:0; left:0; z-index:100;}
        </style>
        <style type="text/css">
            .FontColor,a.FontColor,a.FontColor:hover,a:hover{color:#333333;} .FontBgColor{background-color:#333333;}
            .FontBorderColor{border-color:#333333;} .FontBorderHoverColor:hover, a.FontBorderHoverColor:hover{border-color:#333333;}
            .NavBgColor{background-color:#333333;} .NavHoverBgColor:hover{background-color:#424242;}
            .NavBorderColor1{border-color:#333333;} .NavBorderColor2{border-color:#333333;}
            .CategoryBgColor{background-color:#424242;} .PriceColor{color:#101010;}
            .AddtoCartBgColor{background-color:#D80000;} .BuyNowBgColor{background-color:#FF9900;}
            .ReviewBgColor{background-color:#FF9900;} .DiscountBgColor{background-color:#D80000;}
            .DiscountBorderColor{border-color:#D80000;} .ProListBgColor{background-color:#FF9900;}
            .ProListHoverBgColor:hover{background-color:#FF9900;} .GoodBorderColor{border-color:#626262;}
            .GoodBorderHoverColor:hover{border-color:#D80000;} .GoodBorderColor.selected{border-color:#D80000;}
            .GoodBorderBottomHoverColor{border-bottom-color:#D80000;}
        </style>
        <header>
            <div class="header_top clean FontBgColor">
                <div class="head_bg_col clean">
                    <div class="fl icon menu pic_box">
                        <a href="javascript:;">
                            <img src="./static/images/header_icon_0_2.png" alt="">
                        </a>
                        <span>
                        </span>
                    </div>
                    <div class="logo fl pic_box">
                        <a href="https://www.shoestp.com/">
                            <img src="./static/images/de968c6924.png" alt="">
                        </a>
                        <span>
                        </span>
                    </div>
                    <div class="fr dun icon pic_box">
                        <a href="https://www.shoestp.com/info/_i0037.html">
                            <img src="./static/images/guanj.png" alt="">
                        </a>
                        <span>
                        </span>
                    </div>
                    <div class="inq">
                        <a href="https://www.shoestp.com/inquiry.html">
                            Inquiry/RFQ
                        </a>
                    </div>
                </div>
            </div>
            <div class="pop_up nav_side">
                <div class="pop_up_container nav_container clean">
                    <div class="fl mtitle">
                        Menu
                    </div>
                    <a class="fr close" href="javascript:;">
                        <em>
                        </em>
                    </a>
                    <div class="clear">
                    </div>
                    <nav class="menu_list">
                        <div class="ui_border_b item">
                            <a href="https://www.shoestp.com/">
                                Home
                            </a>
                        </div>
                        <div class="ui_border_b item son">
                            <a href="javascript:;" class="btn_all_category" rel="nofollow">
                                All Categories
                            </a>
                            <div class="icon">
                                <em>
                                    <i>
                                    </i>
                                </em>
                            </div>
                        </div>
                        <div class="ui_border_b item">
                            <a href="https://www.shoestp.com/products/">
                                Products
                            </a>
                        </div>
                        <div class="ui_border_b item">
                            <a href="https://www.shoestp.com/info/global-purchase-point_i0096.html">
                                Purchasing center
                            </a>
                        </div>
                        <div class="ui_border_b item">
                            <a href="https://www.shoestp.com/New-Arrival/">
                                New Arrival
                            </a>
                        </div>
                        <div class="ui_border_b item">
                            <a href="https://www.shoestp.com/m/romania/">
                                România
                            </a>
                        </div>
                        <div class="lang_cr clean">
                            <div class="fl box lang">
                                Language: English
                            </div>
                            <div class="fr box currency">
                                Currency: USD
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            <div class="pop_up category_side">
                <div class="pop_up_container nav_container clean">
                    <div class="fl category_title">
                        All Categories
                    </div>
                    <a class="fr close close2" href="javascript:;">
                        <em>
                        </em>
                    </a>
                    <div class="clear">
                    </div>
                    <div class="menu_list">
                        <div class="ui_border_b item son">
                            <a href="javascript:;" title="Mens Collections">
                                Mens Collections
                            </a>
                            <div class="icon">
                                <em>
                                    <i>
                                    </i>
                                </em>
                            </div>
                            <ul class="ui_border_t menu_son">
                                <li class="item son">
                                    <a href="javascript:;" title="Mens shoes">
                                        Mens shoes
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/dress-shoes_0396" title="Dress shoes">
                                                Dress shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/mens-leather-shoes_0527" title="Mens Leather Shoes">
                                                Mens Leather Shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/casual-shoes_0374" title="Casual Shoes">
                                                Casual Shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/loafersampslip-ons_0400" title="Loafers&amp;Slip-ons">
                                                Loafers&amp;Slip-ons
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/sports-shoes_0377" title="Sports Shoes">
                                                Sports Shoes
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="item son">
                                    <a href="javascript:;" title="Mens boots">
                                        Mens boots
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/safety-shoes_0379" title="Safety Shoes">
                                                Safety Shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/dress-boots_0487" title="Dress  boots">
                                                Dress boots
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="item">
                                    <a href="https://www.shoestp.com/c/mens-sandals_0375" title="Mens sandals">
                                        Mens sandals
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="ui_border_b item son">
                            <a href="javascript:;" title="Womens Collections">
                                Womens Collections
                            </a>
                            <div class="icon">
                                <em>
                                    <i>
                                    </i>
                                </em>
                            </div>
                            <ul class="ui_border_t menu_son">
                                <li class="item son">
                                    <a href="javascript:;" title="Womens shoes">
                                        Womens shoes
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/sports-shoes_0381" title="Sports shoes">
                                                Sports shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/casual-shoes_0492" title="Casual shoes">
                                                Casual shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/loafers_0397" title="Loafers">
                                                Loafers
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/heels_0383" title="Heels">
                                                Heels
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/flats_0384" title="Flats">
                                                Flats
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/dress-shoes_0398" title="Dress shoes">
                                                Dress shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/wedges_0491" title="Wedges">
                                                Wedges
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="item son">
                                    <a href="javascript:;" title="Womens boots">
                                        Womens boots
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/ankle-boots_0382" title="Ankle Boots">
                                                Ankle Boots
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/mid-calf-boots_0493" title="Mid calf boots">
                                                Mid calf boots
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/knee-high-boots_0494" title="Knee high boots">
                                                Knee high boots
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/casual-boots_0522" title="Casual boots">
                                                Casual boots
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="item son">
                                    <a href="javascript:;" title="Womens sandals">
                                        Womens sandals
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/flat-sandals_0501" title="Flat sandals">
                                                Flat sandals
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/wedge-sandals_0502" title="Wedge sandals">
                                                Wedge sandals
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/heel-sandals_0521" title="Heel sandals">
                                                Heel sandals
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/slippers_0523" title="Slippers">
                                                Slippers
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="ui_border_b item son">
                            <a href="javascript:;" title="Kids Collections">
                                Kids Collections
                            </a>
                            <div class="icon">
                                <em>
                                    <i>
                                    </i>
                                </em>
                            </div>
                            <ul class="ui_border_t menu_son">
                                <li class="item son">
                                    <a href="javascript:;" title="Boys Collections">
                                        Boys Collections
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/boys-boots_0512" title="Boys boots">
                                                Boys boots
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/boys-sports-shoes_0515" title="Boys sports shoes">
                                                Boys sports shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/boys-casual-shoes_0516" title="Boys casual shoes">
                                                Boys casual shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/boys-slippers_0517" title="Boys slippers">
                                                Boys slippers
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/boys-sandals_0518" title="Boys sandals">
                                                Boys sandals
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="item son">
                                    <a href="javascript:;" title="Girls Collections">
                                        Girls Collections
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                    <ul class="menu_son menu_grandson">
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/girls-boots_0388" title="Girls boots">
                                                Girls boots
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/girls-sports-shoes_0389" title="Girls sports shoes">
                                                Girls sports shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/girls-casual-shoes_0391" title="Girls casual shoes">
                                                Girls casual shoes
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/girls-sandals_0390" title="Girls sandals">
                                                Girls sandals
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/girls-slippers_0392" title="Girls slippers">
                                                Girls slippers
                                            </a>
                                        </li>
                                        <li class="item">
                                            <a href="https://www.shoestp.com/c/girls-dress-shoes_0510" title="Girls dress shoes">
                                                Girls dress shoes
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="item">
                                    <a href="https://www.shoestp.com/c/babies-collections_0507" title="Babies Collections">
                                        Babies Collections
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                </li>
                                <li class="item">
                                    <a href="https://www.shoestp.com/c/waterproof-bootsamprain-boots_0519"
                                    title="Waterproof boots&amp;Rain boots">
                                        Waterproof boots&amp;Rain boots
                                    </a>
                                    <div class="icon">
                                        <em>
                                            <i>
                                            </i>
                                        </em>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="header_fill">
            </div>
            <div id="float_chat">
                <div class="float_list">
                    <a href="javascript:;" class="btn_global btn_top" style="display: none;">
                        Top
                    </a>
                </div>
            </div>
        </header>
        <main>
            <div class="box">
                <div class="main">
                    <div class="goods-box">
                        <ul>
                        </ul>
                      
                    </div>
                    <div class="main_left">
                        <header>
                            <div>
                                <img src="./static/images/RFQ.png" alt="">
                            </div>
                            <div>
                                <h2 style="font-size:18px">
                                    Request for Quotation
                                </h2>
                                <p class="color666">
                                    One Request，Multiple Quotes
                                </p>
                            </div>
                        </header>
                        <div>
                            <h1>
                                Tell suppliers what you need
                            </h1>
                            <p class="font-bold" style="margin-bottom:10px">
                                Complete Your RFQ
                            </p>
                            <p class="color666">
                                The more specific your information, the more accurately we can match your
                                request to the right suppliers
                            </p>
                        </div>
                        <form action="https://www.shoestp.com/inquiry.html" method="post" enctype="multipart/form-data"
                         name="inquiry" id="inquiry_form">
                            <div class="form_header marginBottom">
                                <input name="title" value="" type="text" placeholder="Title" class="inputHeight"
                                style="width:70%" notnull="" id="title">
                                 <input name="" value="Select Product" type="button" class="inputHeight"
                                style="width:30%" notnull="" onclick="productList()">
                              </div>
                              <div class="form_header marginBottom">
                                <input name="LastName" value="" type="text" placeholder=" Name" class="inputHeight marginRight"
                                style="width:40%" notnull=""  id="purName">
                                <input name="Email" value="" type="text" placeholder=" Email" class="inputHeight"
                                style="width:60%" notnull=""  id="email">
                            </div>
                            <div class="form_header marginBottom">
                                <select name="Country" notnull="" class="inputHeight" id="selCountry">
                                </select>
                            </div>
                            <div class="form_main marginBottom">
                                <textarea name="Message" id="content" class="form_textarea" placeholder=" Product Detailed Specifications.Describe about your perferred unit price,destination port and the payment methods." notnull="">
                                </textarea>
                            <div class="clean">
                             <div class="flex">
                                 	<div id="photo-wrap"></div>
                                 	<div class="layui-upload-drag my-add-icon" id="photo" style="text-align:center;">
                                 	</div>
                                 </div>
                            </div>
                            </div>
                            <div class="form_footer marginBottom">
                                <input name="Subject" type="text" placeholder=" Enter quantity" class="inputHeight"
                                style="width:30%;margin-right:0.5rem;" id="number">
                                <input name="VCode" type="text" placeholder=" Verification code:" class="inputHeight"
                                style="margin-right:0.3rem;width:36%">
                                <div class="form_code" style="width:32%">
                                    <img src="./static/images/v_code.class.php" onclick="this.src=&quot;/inc/class/v_code.class.php?name=inquiry&amp;length=4&amp;charset=en&amp;r=&quot;+Math.random();"
                                    style="cursor:pointer;">
                                </div>
                            </div>
                             </form>
      
     		 
                            <div>
                                <div>
                                    <input name="Submit" type="button" class="submit" id="sub_btn" value="Submit" onclick="inquiries()">
                                </div>
                            </div>
                            <input name="Site" value="en" type="hidden" class="inputHeight marginRight">
                            <input type="hidden" name="do_action" value="action.submit_inquiry">
                       
                        <div class="footer">
                            <div>
                                <h2>
                                    How to Get Quotes quickly?
                                </h2>
                                <div class="color666">
                                    <span>
                                        Submit RFQ
                                    </span>
                                    →
                                    <span>
                                        Compare Quotes
                                    </span>
                                    →
                                    <span>
                                        Contact Supplier
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="main_right">
                        <div class="main_rightT color666">
                            <h2 style="margin-top:0;color:black">
                                About Posting Buy Offer
                            </h2>
                            <p>
                                Here, you can ask the price and delivery
                            </p>
                            <p>
                                deadline of products in our website.Besides,
                            </p>
                            <p>
                                you can post buy offers or customize the products.
                            </p>
                            <p>
                                After receiving your requirements,
                            </p>
                            <p>
                                we will match the suitable shoes factories for you.
                            </p>
                        </div>
                        <div class="main_rightB color666">
                            <h2 style="color:black">
                                Contact us
                            </h2>
                            <p>
                                TEL：+86-577-85887585 Fax：+86-577-89711316
                            </p>
                            <p>
                                Mail：marketing@shoestp.com
                            </p>
                            <p>
                                Thanks for your interest in our website
                            </p>
                            <p>
                                Any further suggestions are sincerely
                            </p>
                            <p>
                                welcomed by us xiuchuan@shoestp.cn
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            
             <!--产品弹框  -->                    
     <div class="modal-box"  id="ptdShow">
     	 <div class="inquiry-modal">
        <div class="inquiry-modal-title">
            <i></i>
        </div>
        <div id="showList">
        <div class="inquiry-modal-box">
            <div class="inquiry-modal-item">
                <input type="checkbox">
                <div class="inquiry-modal-item-img">
                    <img src="./0ad186f491.jpg" alt="">
                </div>
                <div class="inquiry-modal-item-right">
                    <div class="inquiry-modal-item-info">内容内容内容内容内容内容内容内容容内容内容内容内容内容内内容内容内容内容内容</div>
                   <div style="margin-top: 5px;">
                        <span class="inquiry-modal-item-name">LN-7105</span>
                    <i></i>
                   </div>
                </div>
            </div>
        </div>
        </div>
        <div class="inquiry-modal-button">
            <input type="checkbox" id="pdtTitle">
            <span class="RFQ-title">Use products title as RFQ title</span>
            <span class="inquiry-submit" onclick="submitpdt()">Submit</span>
            <span class="inquiry-cancel"  onclick="hidePdt()">Cancel</span>
        </div>
   		</div>
     </div>
   		<!--产品弹框结束  -->
            
        </main>
        <footer>
            <div id="prolist_mask_footer">
            </div>
            <div class="footer_top clean">
            </div>
            <ul class="footer_list ui_border_b clean" style="display:none;">
                <li class="fl" style="border-right:1px solid #ddd;">
                    <a href="https://www.shoestp.com/account/" class="clean">
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
        <div class="pop_up language_side">
            <div class="pop_up_container clean">
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <div class="menu_list">
                </div>
            </div>
        </div>
        <div class="pop_up currency_side">
            <div class="pop_up_container clean">
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <div class="menu_list">
                    <div class="item">
                        <a href="javascript:;" data="USD">
                            <img src="./static/images/53092c531f.jpg" alt="USD">
                            USD
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="foot_blank">
        </div>
        <div id="foot_menu" class="clean">
            <div class="fl item">
                <div class="pic">
                    <a href="https://www.shoestp.com/">
                        <img src="./static/images/ficon0.png">
                    </a>
                </div>
                <div class="name ">
                    <a href="https://www.shoestp.com/">
                        home
                    </a>
                </div>
            </div>
            <div class="fl item">
                <div class="pic">
                    <a href="https://www.shoestp.com/catalog/">
                        <img src="./static/images/ficon1.png">
                    </a>
                </div>
                <div class="name ">
                    <a href="https://www.shoestp.com/">
                        category
                    </a>
                </div>
            </div>
            <div class="fl item cart_icon">
                <div class="pic">
                    <a href="https://www.shoestp.com/products/">
                        <img src="./static/images/ficon2.png">
                    </a>
                </div>
                <div class="name ">
                    <a href="https://www.shoestp.com/products/">
                        discover
                    </a>
                </div>
            </div>
            <div class="fl item">
                <div class="pic">
                    <a href="https://www.shoestp.com/account/">
                        <img src="./static/images/ficon3.png">
                    </a>
                </div>
                <div class="name ">
                    <a href="https://www.shoestp.com/account/">
                        account
                    </a>
                </div>
            </div>
        </div>
        <div align="center">
            <!-- End of LiveChat code -->
            <!-- Google Tag Manager (noscript) -->
            <noscript>
                &lt;iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KNPHSJ6"
                height="0" width="0" style="display:none;visibility:hidden"&gt;&lt;/iframe&gt;
            </noscript>
            <!-- End Google Tag Manager (noscript) -->
        </div>
    <form id="consultSer" class="layui-hide" style="none">
      <input type="text"  name="bean.title"  id="stitle"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.name"  id="sname"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.email"  id="semail"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.image"   id="simage"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.county"  id="scounty"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.content"  id="scontent"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.product"  id="sproduct"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.pdtNum"  id="spdtNum"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.supplier"  id="ssupplier"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.consult"  id="sconsult"  class="inputHeight marginRight" style="width:40%">
       <input type="text"  name="bean.purchase"  id="spurchase"  class="inputHeight marginRight" style="width:40%">
      	
	</form>
	<form id="consult" class="layui-hide" style="none">
      <input type="text"  name="bean.title"  id="btitle"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.name"  id="bname"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.email"  id="bemail"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.image"  id="bimage"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.county"  id="bcounty"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.content"  id="bcontent"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.product"  id="bproduct"  class="inputHeight marginRight" style="width:40%">
      <input type="text"  name="bean.prodNum"  id="bproductnum"  class="inputHeight marginRight" style="width:40%">
	</form>
     <script type="text/javascript">
        window.onload=function(){
     	   $.ajax({
     			type:'post',
     			url:'http://'+window.location.host+'/home/plt_PltCountry_list',
     			dataType:'json',
     			success:function(data){
     			$.each(data.items,function(i,item){
     			$("#selCountry").append("<option value='"+item.pkey+"'>"+item.name+"</option>");
     			});
     			}
     		})
        }
 function inquiries(){
        console.log("jinlai");
     	var name=$("#purName").val();
        var email=$("#email").val();
        var title=$("#title").val();
        var content=$("#content").val();
        var title=$("#title").val();
        $("#bcounty").val($("#selCountry").val());
        if(name==''||name==null){
        layer.msg("名字不能为空");	        
          return false;	
        }
        if(title==''){
        layer.msg("标题不能为空");
         return false;
        }else{
        $("#title").val(title);
        }
        if(title==''||title==null){
        layer.msg("标题不能为空");	        
        return false;	
        }
        if(email==''||email==null){
        layer.msg("邮箱不能为空");	        
            return false;	
        }
        if(content==''||content==null){
        layer.msg("详细描述不能为空");	        
            return false;	
        }
        var url='http://'+window.location.host+'/home/pdt_PdtConsult_ins';
        var serurl='';
        var data='';
        var serdata='';
        $("#btitle").val($("#title").val());
        $("#bname").val($("#purName").val());
        $("#bemail").val($("#email").val());
        $("#bcounty").val($("#selCountry").val());
        $("#btime").val($("#time").val());
        $("#bproductnum").val($("#number").val());
        data=$("#consult").serialize();
        if($("#ssupplier").val()!=''){
         serurl='http://'+window.location.host+'/home/pdt_PdtConsultServe_InsConsultSer';	
         $("#stitle").val($("#title").val());
         $("#sname").val($("#purName").val());
         $("#semail").val($("#email").val());
         $("#scounty").val($("#selCountry").val());
         $("#stime").val($("#time").val());
         $("#spdtNum").val($("#number").val());
        }
        $.ajax({
   			type:'post',
   			url:url,
   			dataType:'json',
   			data:data,
   			success:function(data){
   			if(serurl!='')
   			  {
   			 var purchase=data['bean.purchase'].split("##");	
   			 $("#spurchase").val(purchase[0]);	
   			 $("#sconsult").val(data['bean.pkey']);	
   				 $.ajax({
   		   			type:'post',
   		   			url:serurl,
   		   			dataType:'json',
   		   			data:$("#consultSer").serialize(),
   		   			success:function(serdata){
   		   			console.log(serdata);	
   		   			}
   		        }) 
   			 
   			  }	
   			}
        })
       } 
       function productList(){
    	   $("#ptdShow").css('display','inline');
    	   $.ajax({
   			type:'post',
   			url:'http://'+window.location.host+'/home/pdt_PdtConsultPdtList_selCstPdt',
   			dataType:'json',
   			success:function(data){
   			  $("#showList").html("");
   			  $.each(data.items,function(i,item){
   				 $("#showList").append(
   					+"<div class='inquiry-modal-box'>"
   					+"<div class='inquiry-modal-item'>"
   		         	+"<input type='radio' name='pdt'  value="+item.product.pkey+","+item.product.name+","+item.product.supplier+">"
   		         	+"<div class='inquiry-modal-item-img'>"
   		         	+"<img src='"+item.product.picture+"' alt=''>"
   		         	+"</div>"
   		         	+"<div class='inquiry-modal-item-right'>"
   		         	+"<div class='inquiry-modal-item-info'>"+item.product.name+"</div>"
   		         	+"<div style='margin-top: 5px;'>"
   		         	+"<span class='inquiry-modal-item-name'>"+item.product.code+"</span>"
   		            +"<i></i></div></div></div></div>");	
   				})
   			}
    	  })
       }
       
 layui.use('upload', function(){
 		  var $ = layui.jquery
 	      ,upload = layui.upload;	
 		  upload.render({
 			    elem: '#photo'
 			    ,url: 'http://'+window.location.host+'/seller/sys_SysAccessory_uploadImage?widthLimit=0'
 			    ,multiple: true
            	    ,before: function(obj){
            	      //预读本地文件示例，不支持ie8
            	      obj.preview(function(index, file, result){
            	       $('#photo-wrap').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" width="150" height="150" >')
            	      });
            	    }
 			    ,done: function(res){
 			    	 if($("#bimage").val()==''){
 	                	 $("#bimage").val(res.url);	 
 	                	 }else{
 	                     $("#bimage").val($("#bimage").val()+","+res.url);
 	                  }		
 			    	 if($("#simage").val()==''){
 	                	 $("#simage").val(res.url);	 
 	                	 }else{
 	                     $("#simage").val($("#simage").val()+","+res.url);	
 	                  }	
 			    }
 			  });
 		});

       function hidePdt(){      
       $("#ptdShow").css('display','none');  
       }
       function submitpdt(){
    	   if($("input[name='pdt']:checked").val()!=null){
    	    var array=($("input[name='pdt']:checked").val()).split(',');
    	    $("#bproduct").val(array[0]);
    	    $("#sproduct").val(array[0]);
    	    var arraySup=array[2].split('##');
    	    $("#ssupplier").val(arraySup[0]);
    	    } 	  
    	   if($('#pdtTitle').is(':checked')){
    		$("#title").val(array[1]);   
    	   }
       }
        </script>
    </body>
</html>