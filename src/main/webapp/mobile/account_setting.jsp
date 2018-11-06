<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- saved from url=(0040)https://www.shoestp.com/account/setting/ -->
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
            <style>
        .psdModal{
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            z-index: 999;
            width: 70%;
           	height:1.5rem;
           	line-height:1.5rem;
           	opacity:0.8;
           	padding:1rem;
           	text-align:center;
            margin: 0 auto;
            transform: translate(-50%, -50%);
            font-size: 0.8125rem;
            border-radius: 0.1875rem;
            background: #666;
            color:#FFF;
        }
 		 </style>
        <script type="text/javascript">
          /*   $(function() {
                user_obj.user_setting()

                var curr = new Date().getFullYear(),
                opt = {};
                opt.date = {
                    preset: 'date'
                };
                opt.datetime = {
                    preset: 'datetime',
                    minDate: new Date(2012, 3, 10, 9, 22),
                    maxDate: new Date(2014, 7, 30, 15, 44),
                    stepMinute: 5
                };
                opt.time = {
                    preset: 'time'
                };
                opt.tree_list = {
                    preset: 'list',
                    labels: ['Region', 'Country', 'City']
                };
                opt.image_text = {
                    preset: 'list',
                    labels: ['Cars']
                };
                opt.select = {
                    preset: 'select'
                };
                $('input[name=Birthday]').scroller('destroy').scroller($.extend(opt['date'], {
                    theme: 'sense-ui',
                    mode: 'scroller',
                    display: 'modal',
                    lang: ''
                }));
            }); */
        </script>
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
                <a href="/home/usr_UsrPurchase_userIndex">
                    <s:text name="my_account" />
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="javascript:;">
                    <s:text name="user.settingTitle" />
                </a>
            </div>
            <div id="user" class="user_login">
                <div class="blank10">
                </div>
                <form  class="user_login_form" id="reg_form">
                <input type="hidden" class="pkey" id="pkey" name="bean.pkey" value="${usrPurchase.pkey}">
                    <div class="rows">
                        <div class="form_name clean">
                            <div class="box">
                                <label class="field">
                                    <s:text name="mobile.first_name" />
                                </label>
                                <input type="text" class="box_input" name="bean.name" value="${usrPurchase.name}" placeholder="${usrPurchase.name}"
                                data-field="First Name">
                                <p class="error">
                                </p>
                            </div>
                            <div class="box">
                                <label class="field">
                                    <s:text name="mobile.your_last_name" />
                                </label>
                                <input type="text" class="box_input" name="bean.surname"  value="${usrPurchase.surname}" placeholder="${usrPurchase.surname}"
                                data-field="Last Name">
                                <p class="error">
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="rows">
                        <label class="field">
                            <s:text name="supplier.telephone" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="text" class="box_input" name="bean.telphone"   value="${usrPurchase.telphone}"
                            data-field="Telephone" notnull="" id="telphoneVal">
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    <div class="rows">
                        <label class="field">
                            <s:text name="Company" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="text" class="box_input" name="bean.company"  value="${usrPurchase.company}"
                            data-field="Company" notnull=""  id="companyVal" >
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    <div class="rows">
                        <label class="field">
                            <s:text name="order.address" />
                        </label>
                        <div class="input clean">
                            <input type="text" class="box_input" name="bean.address" value="${usrPurchase.address}" placeholder="${usrPurchase.address}">
                        </div>
                    </div>
                    <div class="rows">
                        <span class="fc_red">
                            <s:text name="vip1" />
                            <br>
                            
                        </span>
                    </div>
                    <div class="user_login_btn">
                        <div class="btn_global btn_sign_up btn_submit BuyNowBgColor">
                            <s:text name="confirm" />
                        </div>
                        <a href="javascript:history.go(-1);" class="btn_global btn btn_back" id="btn_back">
                            <s:text name="goBack" />
                        </a>
                    </div>
                    <div class="blank25">
                    </div>
                    <input type="hidden" name="ajax_submit" value="1">
                    <input type="hidden" name="do_action" value="user.mod_profile">
                </form>
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
                            <s:text name="sign_in" />
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

        <%-- <div align="center">
        </div>
        <div class="dw-hidden" role="alert">
        </div> --%>
        <div class="psdModal" id="tip">
   		</div>

  <script type="text/javascript">
 	 function isPhoneNo(phone) { 
	  var pattern = /^1[34578]\d{9}$/; 
	  return pattern.test(phone); 
	 }
 	
  	$(".btn_submit").on("click",function(){
  		if($("#companyVal").val()==''){
  		$("#tip").html(" <s:text name="Companyname"/>");
		$('.psdModal').show().delay(3000).fadeOut();
		return;
  		}
  		if($("#telphoneVal").val()==''){
  	  		$("#tip").html(" <s:text name="phonenumber"/>");
  			$('.psdModal').show().delay(3000).fadeOut();
  			return;
  	  	}
  		if(isPhoneNo($.trim($('#telphoneVal').val()))==false){
  		$("#tip").html(" <s:text name="Pleaseinput"/>");
  		$('.psdModal').show().delay(3000).fadeOut();
  		return;	
  		}
  		$.ajax({
  			url:"/home/usr_UsrPurchase_updPurchaseInf",
  			data:$("#reg_form").serialize(),
  			type:"post",
  			dataType:"json",
  			success:function(data){
  				if(data.ret=='1'){
  				$("#tip").html("UPDATE SUCCESS!");
  				$('.psdModal').show().delay(3000).fadeOut();
  				setTimeout(function () {window.location="/home/usr_UsrPurchase_userIndex";},1000);    		
  				}else{
  				$("#tip").html("UPDATE FAIL!");
  	  			$('.psdModal').show().delay(3000).fadeOut();	
  	  			setTimeout(function () {location.reload();},3000);    					
  				}
  			}
  		})
  	})
  </script>
</body>
</html>
