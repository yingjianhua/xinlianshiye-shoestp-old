<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)https://www.shoestp.com/cart/checkout.html -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->
    <script type="text/javascript" async="" src="./static/js/js.js"></script>
    <script type="text/javascript" async="" src="./static/js/tracking.js"></script>
    <script type="text/javascript" async="" src="./static/js/analytics.js"></script>
    <script async="" src="./static/js/gtm.js"></script>
    <script>
        (function (w, d, s, l, i) {
            w[l] = w[l] || [];
            w[l].push({
                'gtm.start': new Date().getTime(),
                event: 'gtm.js'
            });
            var f = d.getElementsByTagName(s)[0],
                j = d.createElement(s),
                dl = l != 'dataLayer' ? '&l=' + l : '';
            j.async = true;
            j.src =
                'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
            f.parentNode.insertBefore(j, f);
        })(window, document, 'script', 'dataLayer', 'GTM-KNPHSJ6');
    </script>
    <!-- End Google Tag Manager -->
    <link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="./static/js/en.js"></script>
    <script type="text/javascript" src="./static/js/global.js"></script>
    <script type="text/javascript" src="./static/js/global(1).js"></script>
    <script type="text/javascript" src="./static/js/user.js"></script>
    <script type="text/javascript" src="./static/js/main.js"></script>

    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->

    <link rel="stylesheet" href="./static/css/animate.min.css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js"></script>
    <script src="./static/js/lazyload.min.js"></script>
    <link href="./static/css/cart.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/cart.js"></script>
    <script type="text/javascript" src="./static/js/tool_tips_web.js"></script>
</head>

<body class="lang_en w_1200">

    <style type="text/css">
        .FontColor,
        a.FontColor,
        a.FontColor:hover,
        a:hover {
            color: #333333;
        }

        .FontBgColor {
            background-color: #333333;
        }

        .FontBorderColor {
            border-color: #333333;
        }

        .FontBorderHoverColor:hover,
        a.FontBorderHoverColor:hover {
            border-color: #333333;
        }

        .NavBgColor {
            background-color: #333333;
        }

        .NavHoverBgColor:hover {
            background-color: #424242;
        }

        .NavBorderColor1 {
            border-color: #333333;
        }

        .NavBorderColor2 {
            border-color: #333333;
        }

        .CategoryBgColor {
            background-color: #424242;
        }

        .PriceColor {
            color: #101010;
        }

        .AddtoCartBgColor {
            background-color: #D80000;
        }

        .BuyNowBgColor {
            background-color: #FF9900;
        }

        .ReviewBgColor {
            background-color: #FF9900;
        }

        .DiscountBgColor {
            background-color: #D80000;
        }

        .DiscountBorderColor {
            border-color: #D80000;
        }

        .ProListBgColor {
            background-color: #FF9900;
        }

        .ProListHoverBgColor:hover {
            background-color: #FF9900;
        }

        .GoodBorderColor {
            border-color: #626262;
        }

        .GoodBorderHoverColor:hover {
            border-color: #D80000;
        }

        .GoodBorderColor.selected {
            border-color: #D80000;
        }

        .GoodBorderBottomHoverColor {
            border-bottom-color: #D80000;
        }
    </style>
    <script type="text/javascript">
        $(window).resize(function () {
            $(window).webDisplay(2);
        });
        $(window).webDisplay(2);
        var stpshop_config = {
            "domain": "https://www.shoestp.com",
            "date": "2018/07/16 11:11:32",
            "lang": "en",
            "currency": "USD",
            "currency_symbols": "$",
            "currency_rate": "1.0000",
            "FbAppId": "718848724966585",
            "FbPixelOpen": "0",
            "UserId": "224",
            "TouristsShopping": "0",
            "PaypalExcheckout": ""
        };
        var ueeshop_config = stpshop_config;
    </script>
	<%@ include file="/home/template/web-top.jsp" %>
	<%@ include file="/home/template/new-header.jsp" %>
    <div id="main" class="wide">
         <script type="text/javascript"> 
             var address_count = 1;
             var address_perfect = 0;
             $(document).ready(function () {
                 cart_obj.checkout_init()
             });
             $(this).loginOrVisitors('/cart/checkout.html', 0, function () {
                 ueeshop_config['_login'] = 1;
                 return false;
             });
         </script> 
        <style>
            .tdshipping .td_title {
                text-align: right;
            }

            .tdshipping li {
                height: 25px;
                line-height: 25px;
                cursor: default;
            }

            .tdshipping li input {
                vertical-align: middle;
            }

            .tdshipping li span {
                color: #c80201;
                font-size: 14px;
            }
        </style>
        <div id="lib_cart">
            <div class="step">
                <div class="step_1"></div>
            </div>
            <div class="cartBox" id="addressObj">
                <h2>Shipping Address</h2>
                <div class="contents address">
                    <ul >
                    <div id="showads">
                    
                        <li id="lib_address" style="display: list-item;">
                            <a id="addAddress" href="javascript:;" class="textbtn" onclick="showaddform()">Add a New Shipping Address</a>
                        </li>
                    </div>
                         <div  id="addshowform" style="display: none;">
                                <form id="addform" >
                                    <a onclick="showads()"  class="cancel" href="javascript:;" style="display:;">Cancel</a>
                                    <p>
                                        <span class="required">*</span>&nbsp;
                                        <span class="indicates">Indicates required fields.</span>
                                    </p>
                                    <table class="tb-shippingAddr">
                                        <tbody>
                                            <tr>
                                                <th><label>Your Name:</label></th>
                                                <td class="recipient">
                                                    <div>
                                                        <input type="text" id="FirstName" name="bean.name" maxlength="32" class="elmbBlur">
                                                        <p> <span class="required">*</span>&nbsp;First Name</p><p class="errorInfo"></p>
                                                    </div>
                                                    <div>
                                                        <input type="text" id="LastName" name="bean.surname" maxlength="32" class="elmbBlur">
                                                        <p><span class="required">*</span>&nbsp;Last Name</p><p class="errorInfo"></p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr><th><span class="required">*</span><label>ZIP / Postal Code:</label></th><td><input type="text" id="emailcode" name="bean.emailcode" maxlength="10" class="elmbBlur">p class="errorInfo"></p> </td> </tr>
                                            <tr><th><span class="required">*</span><label>Destination Country/Region:</label></th><td><select name="bean.country"  placeholder="Please Choose Your Country"  id="country"></select><p class="errorInfo"></p></td></tr>
                                            <tr id="taxCode" style="display: none;">
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>CPF or CNPJ code:</label>
                                                </th>
                                                <td>
                                                    <select name="tax_code_type" class="taxCodeOption" id="taxCodeOption" disabled="disabled">
                                                        <option value="1" selected="selected">CPF (personal order)</option>
                                                        <option value="2">CNPJ (company order)</option>
                                                    </select>
                                                    <input type="text" name="tax_code_value" id="taxCodeValue" maxlength="11" class="taxCodeValue elmbBlur" disabled="disabled">
                                                    <p class="lightGray">Please only input numbers, no dots, dashes or other characters.</p>
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                          
                                            <tr id="state" style="display: table-row;">
                                                <th>
                                                    <label>State / Province / Region:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.region" maxlength="32" class="elmbBlur">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>City:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.city" maxlength="30" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Address:</label>
                                                </th>
                                                <td>
                                                    <input type="text" id="address" name="bean.address" maxlength="100" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Phone Number:</label>
                                                </th>
                                                <td>
                                 
                                                    <div class="left editableSelect hasLayout">
                                                        <input type="text" id="phonenumber" name="bean.phonenumber" class="phoneNum elmbBlur" maxlength="15" autocomplete="off">
                                                        <ul id="otherPhones"></ul>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <a href="javascript:void(0);" class="lightGray askTips" title="We ask for your phone number just in case we need to reach you regarding your order.">Why ask?</a>
                                                    <p id="phoneSample" class="lightGray clearfix">Example: +
                                                        <span>376</span> 9549031647-535</p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>
                                                  
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
<!--                                     <input type="reset" id="resetAddr" style="display:none;"> -->
<!--                                     <input type="hidden" name="typeAddr" value="0"> -->
                                </form>
                              	  <button class="useAddress" type="submit" class="textbtn" onclick="useaddress()" >Use This Shipping Address</button>
                            </div>
                            
                            
                              <div  id="updshowform" style="display: none;">
                                <form id="updform" >
                                    <input id="updpkey" type="hidden" name="bean.pkey" value="465">
                                    <a onclick="showads()" class="cancel" href="javascript:;" style="display:;">Cancel</a>
                                    <p>
                                        <span class="required">*</span>&nbsp;
                                        <span class="indicates">Indicates required fields.</span>
                                    </p>
                                    <table class="tb-shippingAddr">
                                        <tbody>
                                            <tr>
                                                <th>
                                                    <label>Your Name:</label>
                                                </th>
                                                <td class="recipient">
                                                    <div>
                                                        <input type="text"  id="updname" name="bean.name" maxlength="32" class="elmbBlur">
                                                        <p>
                                                            <span class="required">*</span>&nbsp;First Name</p>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <div>
                                                        <input type="text"  id="updsurname" name="bean.surname" maxlength="32" class="elmbBlur">
                                                        <p>
                                                            <span class="required">*</span>&nbsp;Last Name</p>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>ZIP / Postal Code:</label>
                                                </th>
                                                <td>
                                                    <input type="text"  id="updemailcode" name="bean.emailcode" maxlength="10" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Destination Country/Region:</label>
                                                </th>
                                                <td>
                                                	<select name="bean.country"  placeholder="Please Choose Your Country"  id="updcountry">
                                                	</select>
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr id="state" style="display: table-row;">
                                                <th>
                                                    <label>State / Province / Region:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.region" id="updregion" maxlength="32" class="elmbBlur">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>City:</label>
                                                </th>
                                                <td>
                                                    <input type="text" name="bean.city" id="updcity" maxlength="30" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Address:</label>
                                                </th>
                                                <td>
                                                    <input type="text"  id="updaddress" name="bean.address" maxlength="100" class="elmbBlur">
                                                    <p class="errorInfo"></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    <span class="required">*</span>
                                                    <label>Phone Number:</label>
                                                </th>
                                                <td>
                                 
                                                    <div class="left editableSelect hasLayout">
                                                        <input type="text"  id="updphonenumber" name="bean.phonenumber" class="phoneNum elmbBlur" maxlength="15" autocomplete="off">
                                                        <ul id="otherPhones"></ul>
                                                        <p class="errorInfo"></p>
                                                    </div>
                                                    <a href="javascript:void(0);" class="lightGray askTips" title="We ask for your phone number just in case we need to reach you regarding your order.">Why ask?</a>
                                                    <p id="phoneSample" class="lightGray clearfix">Example: +
                                                        <span>376</span> 9549031647-535</p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>
                                                <input type="checkbox" id="updisdefault"  >设置默认 </input>
                                                <input  type="text"   style="display: none" name="bean.isdefault" id="isdefault" />
                                                  <input type="hidden" id="updrowVersion" name="bean.rowVersion" value="1">
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                 
                                </form>
                              	  <button class="useAddress" type="submit" class="textbtn" onclick="updform()" >Use This Shipping Address</button>
                            </div>
                            
                    </ul>
                      
                    <div class="clear"></div>
                </div>
            </div>
            <form id="PlaceOrderFrom" method="post" action="https://www.shoestp.com/cart/" amountprice="14000.00" userprice="0" userratio="100">
                <div class="cartFrom">
                    <table width="100%" align="center" cellpadding="12" cellspacing="0" border="0" class="itemFrom">
                        <thead>
                            <tr>
                                <td width="50%" class="first">Item</td>
                                <td width="16%">Price</td>
                                <td width="16%" class="quantity">Quantity</td>
                                <td width="18%">Total</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Shop：温州市德帮鞋业有限公司</td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr cid="108">
                                <td class="prList">
                                    <dl>
                                        <dt>
                                            <a href="https://www.shoestp.com/debang-block-heel-puleather-knee-high-winter-warm-boots-for-women_p6041.html">
                                                <img src="./static/images/c0b8f9ba88.jpg.240x240.jpg" alt="Debang Block Heel Puleather Knee High Winter Warm Boots For Women"
                                                    name="Debang Block Heel Puleather Knee High Winter Warm Boots For Women">
                                            </a>
                                        </dt>
                                        <dd>
                                            <h4>
                                                <a href="https://www.shoestp.com/debang-block-heel-puleather-knee-high-winter-warm-boots-for-women_p6041.html"
                                                    title="Debang Block Heel Puleather Knee High Winter Warm Boots For Women">Debang Block Heel Puleather Knee High Winter Warm Boots For Women</a>
                                            </h4>
                                            <p>TOPDB1283</p>
                                            <p>Size: Women(US/EU)6/36</p>
                                            <p>Color: Black</p>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dd>
                                            <p class="remark">Remark:
                                                <input type="text" name="Remark[]" value="" maxlength="200" data="" cid="108"
                                                    proid="6041">
                                            </p>
                                        </dd>
                                    </dl>
                                </td>
                                <td class="prPrice">
                                    <p>$14.00</p>
                                </td>
                                <td class="prQuant">
                                    <p>1000</p>
                                </td>
                                <td class="prAmount">
                                    <p>$14,000.00</p>
                                </td>
                            </tr>
                            <tr class="tdshipping shipping_276">
                                <td class="td_title last" colspan="3">
                                    <ul>
                                        <li>
                                            <input type="radio" name="_shipping_method[276]" value="257" price="0" insurance="0"
                                                shippingtype="" cid="5" checked="checked">Free mail:</li>
                                    </ul>
                                </td>
                                <td class="td_price last">
                                    <ul>
                                        <li>
                                            <span>Free Shipping</span>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="edit_shopping_cart">
                        <a>Edit Shopping Cart</a>
                    </div>
                </div>
                <div class="cartBox" id="paymentObj">
                    <h2>Payment Method</h2>
                    <div class="contents payment">
                        <h3>Choose payment method:</h3>
                        <select name="_payment_method">
                            <option value="-1">Please select your payment method</option>
                            <option value="10" min="0" max="0" style="display: block;">T/T</option>
                        </select>
                        <div class="blank12"></div>
                        <ul>
                            <li style="display:block;">Please select your payment method</li>
                            <li class="editor_txt" id="payment_contents_10" fee="0.00" affix="0.00">
                                <p>INTERMEDIARY BANK SWIFT： ICBKUS33
                                    <br> INDUSTRIAL AND COMMERCIAL BANK OF CHINA(NEW YORK)
                                    <br> BENEFICIARY BANK&nbsp; &nbsp;SWIFT： OHBKCNSH
                                    <br> ZHEJIANG WENZHOU OUHAI RURAL COMMERCIAL BANK COMPANY LIMITED
                                    <br> BENEFICIARY BANK ADD ：FUSEN BUILDING FLOOR 1-3 STATION ROAD，WENZHOU，ZHEJIANG,CHINA
                                    <br> ZIP CODE:325000
                                    <br>
                                    <br> BENEFICIARY ：WENZHOU NEW-UNION INDUSTRIAL CO LTD&nbsp;
                                    <br> BENEFICAIRY ADD&nbsp; : &nbsp;NO.51 FUHAO RD,QUXI ST,WENZHOU,ZHEJIANG,CHINA.325000&nbsp;
                                    <br> BENEFICIARY A/C NO. ：201000169314392 &nbsp;</p>
                            </li>
                        </ul>
                        <div class="new-coupon">
                            <p id="new-coupon-valid">
                                <span class="valid" style="display:none;">The coupon code "
                                    <strong></strong>" is valid! Your discount is
                                    <span class="red"></span> (expiration date:
                                    <strong></strong>)
                                    <br>
                                </span>
                            </p>
                            <p>
                                <a href="javascript:;" class="u" id="removeCoupon">Remove</a>
                            </p>
                            <p id="new-cp">
                                <a href="javascript:;" id="to-use-coupon">Apply Coupon Code
                                    <i> </i>
                                </a>
                            </p>
                            <p id="link-error">
                                <span class="netError red" style="display: none;"></span>
                            </p>
                        </div>
                        <div class="blank6"></div>
                        <div class="clean selcoupon">
                            <select name="SelCId" class="select">
                                <option value="">Please select</option>
                            </select>
                        </div>
                        <script>
                            $(function () {
                                $('.selcoupon .select').change(function () {
                                    var val = $(this).val();
                                    if (val != '') {
                                        cart_obj.cart_init.ajax_get_coupon_info(val);
                                    }
                                });
                            });
                        </script>
                    </div>
                </div>
                <div class="NoteBox">
                    <h2>Note: If you choose Paypal as payment method, please leave us your Paypal email address right here for
                        payment confirmation on our end.</h2>
                    <div class="notes">
                        <textarea name="Note"></textarea>
                    </div>
                </div>
                <div class="CartAmountSum">
                    <table id="subTotal" cellpadding="0" cellspacing="0" border="0" width="100%">
                        <!-- Grand Total -->
                        <tfoot>
                            <tr id="cartAmount" style="display: table-row;">
                                <th width="100%">Grand Total:
                                    <em>$</em>
                                </th>
                                <td>
                                    <strong id="ot_total">14,000.00</strong>
                                </td>
                            </tr>
                        </tfoot>
                        <tbody>

                            <tr style="display: table-row;">
                                <!-- subtotal -->
                                <th>Subtotal( 1000 items ):
                                    <em>$</em>
                                </th>
                                <td>
                                    <strong id="ot_subtotal">14,000.00</strong>
                                </td>
                            </tr>


                            <tr id="shippingCharges" style="">
                                <!-- Shipping Charges-->
                                <th>(+) Shipping Charges:
                                    <em>$</em>
                                </th>
                                <td>
                                    <strong id="ot_shipping">0.00</strong>
                                </td>
                            </tr>

                            <tr id="shippingInsuranceCombine" style="display: none;">
                                <!-- Shipping Insurance combine-->
                                <th>(+) Shipping Charges &amp; Insurance:
                                    <em>$</em>
                                </th>
                                <td>
                                    <strong id="ot_combine_shippnig_insurance">0.00</strong>
                                </td>
                            </tr>

                            <tr id="couponSavings" style="display: none;">
                                <!-- Coupon Savings -->
                                <th>(-) Coupon Savings:
                                    <em>- $</em>
                                </th>
                                <td>
                                    <strong id="ot_coupon">0.00</strong>
                                </td>
                            </tr>

                            <tr id="serviceCharge" style="display: none;">
                                <!-- Service Charge -->
                                <th>(+) Service Charge:
                                    <em>$</em>
                                </th>
                                <td>
                                    <strong id="ot_fee">0.00</strong>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <fieldset id="submitCart" class="clearfix" style="display: block;">
                        <a href="javascript:;" class="fr">
                            <input type="button" id="orderFormSubmit" class="litb-btn placeOrderBtn">
                        </a>
                        <p class="clearfix">Every order you place with us is safe and secure.</p>
                    </fieldset>
                </div>
                <input type="hidden" name="order_coupon_code" value="" cutprice="0">
                <input type="hidden" name="order_discount_price" value="0">
                <input type="hidden" name="order_shipping_address_aid" value="465">
                <input type="hidden" name="order_shipping_address_cid" value="5">
                <input type="hidden" name="order_shipping_method_sid" value="257">
                <input type="hidden" name="order_shipping_method_type" value="">
                <input type="hidden" name="order_shipping_price" value="0">
                <input type="hidden" name="order_shipping_insurance" value="0" price="0">
                <input type="hidden" name="order_payment_method_pid" value="-1">
            </form>
        </div>
    </div>

    <div id="new_foot">
        <div class="wide">
            <div class="foot_list fl">
                <div class="title">Help</div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/about-payment_a0061.html" title="About Payment" target="_blank">About Payment</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/faq_a0060.html" title="FAQ" target="_blank">FAQ</a>
                </div>
            </div>
            <div class="foot_list fl">
                <div class="title">Terms &amp; conditions</div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/privacy-policy_a0065.html" title="Privacy Policy" target="_blank">Privacy Policy</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/terms-amp-conditions_a0064.html" title="Terms &amp; conditions" target="_blank">Terms &amp; conditions</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/return-policy_a0063.html" title="Return Policy" target="_blank">Return Policy</a>
                </div>
            </div>
            <div class="foot_list fl">
                <div class="title">ABOUT US</div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/about-shoestpcom_a0067.html" title="About shoestp.com" target="_blank">About shoestp.com</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/shoestp-cooperation-program_a0066.html" title="ShoeSTP Cooperation Program"
                        target="_blank">ShoeSTP Cooperation Program</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/art/contact-us_a0062.html" title="Contact Us" target="_blank">Contact Us</a>
                </div>
                <div class="item">
                    <a href="https://www.shoestp.com/manage/" title="供应商注册登陆" target="_blank">供应商注册登陆</a>
                </div>
            </div>
            <div class="foot_letter fr">
                <div class="t">Subscribe to Our Newsletter</div>
                <div class="t0">Get information of our latest products and promotions</div>
                <div class="letter_form">
                    <form id="newsletter_form">
                        <input type="text" value="" name="Email" class="text" notnull="" format="Email">
                        <input type="submit" value="Subscribe" class="btn button">
                    </form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="folink">
            <div class="wide clean">
                <div class="img fl pic_box">
                    <a href="https://www.shoeslogo.com/" target="_blank">
                        <img src="./static/images/854464db07.png" alt="shoeslogo.com">
                    </a>
                    <span></span>
                </div>
                <div class="img fl pic_box">
                    <a href="http://www.shoesmat.com/" target="_blank">
                        <img src="./static/images/0f12682298.png" alt="shoesmat.com">
                    </a>
                    <span></span>
                </div>
                <div class="img fl pic_box">
                    <a href="http://www.shoesrd.com/" target="_blank">
                        <img src="./static/images/3c27f32d85.png" alt="shoesrd.com">
                    </a>
                    <span></span>
                </div>
                <div class="img fl pic_box">
                    <a href="http://www.wzsomt.com/" target="_blank">
                        <img src="./static/images/d30276cc0d.png" alt="wzsomt.com">
                    </a>
                    <span></span>
                </div>
            </div>
        </div>
        <div class="focontact">
            <div class="wide">
                <div class="fc fl">
                    <div class="fl">Contact Us:</div>
                    <div class="fl fcfollow">
                        <div class="follow_toolbox clearfix">
                            <ul>
                                <li>
                                    <a rel="nofollow" class="follow_facebook" href="https://www.facebook.com/ShoeslogoShoestp/?ref=bookmarks"
                                        target="_blank" title="Facebook">Facebook</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_twitter" href="https://twitter.com/shoes_logo" target="_blank"
                                        title="Twitter">Twitter</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_pinterest" href="https://www.pinterest.com/shoeslogo/" target="_blank"
                                        title="Pinterest">Pinterest</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_youtube" href="https://www.youtube.com/channel/UCUHObFxGQ_FaHCKolyZ9k0w"
                                        target="_blank" title="YouTube">YouTube</a>
                                </li>
                                <li>
                                    <a rel="nofollow" class="follow_linkedin" href="http://www.linkedin.com/company/13430792/admin/updates/"
                                        target="_blank" title="LinkedIn">LinkedIn</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="fd fr">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号 &nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
            </div>
        </div>
    </div>
    <div id="hj_top" style="opacity: 0;">
        <img src="./static/images/hj_top.png">
    </div>

 
    <script type="text/javascript" src="./static/js/review.js"></script>
    <script type="text/javascript" src="./static/js/lightbox.min.js"></script>
   <!--  <script type="text/javascript" src="./static/js/addthis_widget.js"></script> -->
 
</body>
   <script type="text/javascript" >
	   window.onload = function(){
		   $.ajax({
		 		type:"post",
		 		url:'/home/plt_PltCountry_list',
		 		data:'',
		 		dataType:'JSON',
		 		success:function(data){
		 			$.each(data.items,function(i,val){
		 				$("#country").append("<option value='"+val.pkey+"'>"+val.name+"</option>");
		 				$("#updcountry").append("<option value='"+val.pkey+"'>"+val.name+"</option>");
		 			})
		 		}
		 		
		 	})
		 	$.ajax({
		 		type:"post",
		 		url:'/home/usr_UsrPurchaseLine_select',
		 		data:'',
		 		dataType:'JSON',
		 		success:function(data){
		 			var strl="";
		 			$.each(data.items,function(i,val){
		 				strl=strl+"<li  class='cur' style='display: list-item;'>"
		 			 			+ "<input type='radio' name='shipping_address_id' id='address_465' value='"+val.pkey+"' cid='5'";
		 			 			if(val.isdefault==1)
		 			 			{
		 			 				strl=strl+"checked='checked'";
		 			 			}
		 			 			
		 			 		strl=strl+">"
		 			 			+" <label><strong>"+val.name+"&nbsp;&nbsp;"+val.surname+"</strong>("+val.address+","+val.city+","+val.region+","+val.country.split("##")[1]+","+val.emailcode+")</label>"
		 			 			+"   <a href='javascript:;' class='edit_address_info' onclick='updshowform("+val.pkey+")'>Edit</a>"
		 			 			+" </li>";
		 			})
		 			$("#lib_address").before(strl);
		 		
		 		}
		 		
		 	})  
	   };
	   function useaddress()
	   {
		$.ajax({
			type:"post",
			url:'/home/usr_UsrPurchaseLine_ins',
			data:$('#addform').serialize(),
			dataType:'JSON',
	 		success:function(){
	 			location.reload(); 
	 		}
		})  
	   }
	   function updshowform(updpkey){
		   
		   $.ajax({
			   type:"post",
				url:'/home/usr_UsrPurchaseLine_load',
				data:{"pkey":updpkey},
				dataType:'JSON',
		 		success:function(data){
		 			var obj = document.getElementById("updcountry")
		 			//遍历option
		 			for(var i=0;i<obj.length;i++){
		 				if(obj[i].value==data.country.split("##")[0	]){
		 					obj[i].selected=true;  //相等则选中
		 				}
		 			}
		 			if(data.isdefault==1){
		 				$("#updisdefault").prop("checked",true);
		 			
		 			}else{
		 				$("#updisdefault").prop("checked",false);
		 			
		 			}
		 			$("#updpkey").val(data.pkey);
		 			$("#updname").val(data.name);
		 			$("#updsurname").val(data.surname);
		 			$("#updemailcode").val(data.emailcode);
		 			$("#updregion").val(data.region);
		 			$("#updcity").val(data.city);
		 			$("#updaddress").val(data.address);
		 			$("#updphonenumber").val(data.phonenumber);
		 			$("#updmain").val(data.purchase);
		 			$("#updrowVersion").val(data.rowVersion);
		 			$("#updshowform").css("display","inline-block");
		    			$("#addshowform").css("display","none");
		    		 	$("#showads").css("display","none");
		    			
		 		}   
		   })
	   }
	   function updform()
	   {
			
			if($("#updisdefault").attr('checked'))
			{
				$("#isdefault").val("1");
				console.log($("#updisdefault").val()+"选中");
			}else
			{
				$("#isdefault").val("0");
				console.log($("#updisdefault").val()+"未选中");
			}
		  console.log($('#updform').serialize());
		   $.ajax({
			   type:"post",
			   url:"/home/usr_UsrPurchaseLine_upd",
			   data:$('#updform').serialize(),
				dataType:'JSON',
			   success : function(data){
				   location.reload(); 
			   }
		   })
	   }
	   function showaddform(){
       	$("#addshowform").css("display","inline-block");
       	$("#showads").css("display","none");
       	$("#updshowform").css("display","none");
    		
       }
	   function showads(){
		   $("#showads").css("display","inline-block");
	       	$("#addshowform").css("display","none");
	       	$("#updshowform").css("display","none");
	   }
   </script>
</html>