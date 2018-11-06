<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

<meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
<meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
<link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
<link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
<link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
<link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/home/static/js/main.js"></script>
<link rel="stylesheet" href="/home/static/css/animate.min.css">
<link rel="stylesheet" href="/home/static/css/swiper.min.css" type="text/css">
<script type="text/javascript" src="./static/js/layer.js"></script>
<link rel="stylesheet" href="./static/css/layer.css" type="text/css">
<style>
#lib_user .textbtn {
    clear: left;
    float: left;
    margin-left: 428px;
    }
#lib_user input{
	width:242px;
}
#lib_user input.red{
	border:1px solid red;
}
#lib_user .span{
	line-height:28px;
}
#lib_user .span.green{
	color:green;
}
</style>
</head>

<body class="lang_en w_1200">
    <%@ include file="/home/template/web-top.jsp" %>
    <%@ include file="/home/template/new-header.jsp" %>
<!-- .new_header -->
<div id="main" class="wide">
<div id="lib_user" class="clearfix">
	<div id="lib_user_crumb" class="widget">
		<ul class="crumb_box clearfix">
			<li class="home"><a href="/" title="Home"><s:text name="Global.Home"/><i></i></a></li>
			<li class="crumb1"><a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="Global.My_Account"/><i></i></a></li>
			<li class="crumb2 root"><a href="/home/usr_UsrPurchase_usrSetting" title="Account Settings"><s:text name="Global.Account_Settings"/><i></i></a></li>
		</ul>
	</div>
	<%@ include file="/home/template/account/lib-user-menu.jsp" %>
	<div id="lib_user_main">
		<link href="/home/static/css/daterangepicker.css" rel="stylesheet" type="text/css">
		<h1 class="lib_user_title"><s:text name="Global.Account_Settings"/></h1>
		<div id="lib_user_setting">
			<h3><%-- <s:text name="vip0"/> --%></h3>
			<form id="frm_profile">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tbody>
				<tr>
					<th width="172" nowrap="nowrap">
						<s:text name="account_setting.Gender"/>:
					</th>
					 <td>
						<select name="bean.sex" notnull="">
								<option value="0" disabled selected style="display: none;"  ><s:text name="Global.Please_Choose"/></option>
								<option <c:if test="${usrPurchase.sex == 1}">selected="selected"</c:if> value="1"><s:text name="account_setting.Men"/></option>
								<option <c:if test="${usrPurchase.sex == 2}">selected="selected"</c:if> value="2"><s:text name="account_setting.Ms"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="Global.First_Name"/>:
					</th>
					<td>
						<input name="bean.name" id="name" class="form_input" type="text" size="40" maxlength="40" value="${usrPurchase.name}" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="Global.Surname"/>:
					</th>
					<td>
						<input name="bean.surname" id="surname" class="form_input" type="text" size="40" maxlength="40" value="${usrPurchase.surname}" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="Global.Phone"/>:
					</th>
					<td>
						<input type="text" name="bean.telphone" id="telphone" value="${usrPurchase.telphone}" size="30" maxlength="20" class="form_input amount" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="Global.Company"/>:
					</th>
					<td>
						<input type="text" name="bean.company" id="company" value="${usrPurchase.company}" size="30" maxlength="50" class="form_input" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<s:text name="Global.Address"/>:
					</th>
					<td>
						<input type="text" name="bean.address" value="${usrPurchase.address}" size="30" maxlength="50" class="form_input">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						&nbsp;
					</th>
					<td>

					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						&nbsp;
					</th>
					<td>
						<span class="fz_14px fc_red"><%-- <s:text name="vip1"/> --%></span>
					</td>
				</tr>
				</tbody>
				</table>

			</form>

			<button type="submit" class="textbtn" id="improvePersonalInformation"><s:text name="Global.Save"/></button>
			<span class="span"></span>
			<input type="hidden" name="do_action" value="user.mod_profile">
			<div class="line">
			</div>
			<h3><s:text name="account_setting.Change_Email_Address"/></h3>
			<form id="frm_email">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tbody>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="account_setting.Existing_Password"/>:
					</th>
					<td>
						<input name="bean.pkey"  value="${usrPurchase.pkey}" class="form_input" type="hidden" size="40" notnull="">
						<input name="pwd" id="pwd" class="form_input" type="password" size="40" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="account_setting.New_Email_Address"/>:
					</th>
					<td>
						<input name="newEmail" id="email" value="${usrPurchase.email}" class="form_input" type="text" size="40" maxlength="100" format="Email" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						&nbsp;
					</th>
					<td>

					</td>
				</tr>
				</tbody>
				</table>

			</form>
			<button type="submit" class="textbtn" id="ChangeYourEmailAddress"><s:text name="Global.Save"/></button>
			<input type="hidden" name="do_action" value="user.mod_email">
			<div class="line">
			</div>
			<h3><s:text name="account_setting.Change_Password"/></h3>
			<form id="frm_password">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tbody>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="account_setting.Existing_Password"/>:
					</th>
					<td>
					    <input name="bean.pkey" value="${usrPurchase.pkey}"    class="form_input" type="hidden" size="40" notnull="">
						<input name="oldPwd" id="oldPwd" class="form_input" type="password" size="40" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="Global.New_Password"/>:
					</th>
					<td>
						<input name="newPwd" id="newPwd" class="form_input" type="password" size="40" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						<span class="fc_red">*</span><s:text name="Global.Confirm_Password"/>:
					</th>
					<td>
						<input name="NewPassword2" id="newPwd2" class="form_input" type="password" size="40" notnull="">
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">
						&nbsp;
					</th>
					<td>

					</td>
				</tr>
				</tbody>
				</table>
			</form>
			<button type="submit" class="textbtn" id="ChangeYourPassword"><s:text name="Global.Save"/> </button>
			<input type="hidden" name="do_action" value="user.mod_password">
			<div class="clear">
			</div>
		</div>
		<script type="text/javascript">
	var frm_profile = $('#frm_profile');
	frm_profile.find('button:submit').click(function(){
		if(global_obj.check_form(frm_profile.find('*[notnull]'))){return false;};
	});
	var frm_email = $('#frm_email');
	frm_email.find('button:submit').click(function(){
		if(global_obj.check_form(frm_email.find('*[notnull]'), frm_email.find('*[format]'))){return false;};
	});
	var frm_password = $('#frm_password');
	frm_password.find('button:submit').click(function(){
		if(global_obj.check_form(frm_password.find('*[notnull]'))){return false;};
	});
	(function(){
		$('form#frm_profile input[name=Birthday]').daterangepicker({
			showDropdowns:true,
			singleDatePicker:true,
			timePicker:false,
			format:'MM/DD/YYYY'
		});
	});
		</script>
	</div>
</div>
</div>
﻿
    <%@ include file="/home/template/new-foot.jsp" %>
<div id="hj_top" style="opacity: 0;">
<img src="/home/static/images/hj_top.png">
</div>
﻿

</body>

<script>

$.ajax({
	type:'post',
	async: false,
	url:'/plt_PltConfig_enabledLanguage',
	data:'',
	dataType: 'json',
	success:function(data){
		$.each(data,function(key,value){
// 			if(value.isEnabled){

				$("#current").append(
				'<a data-lang="'+value.shortName+'" class="current">'+value.displayName+'</a>'
				)
				//id="PdtName"
// 			}
		})
	}
});



</script>
<script type="text/javascript">
	$("#improvePersonalInformation").on("click",function(){
		var num=0;
		$(".span").html("");
		$("#name").removeClass("red");
		$("#surname").removeClass("red");
		$("#telphone").removeClass("red");
		$("#company").removeClass("red");
		if($("#name").val()==null||$("#name").val()==""){
			$("#name").addClass("red");
			num+=1;
		}
		if($("#surname").val()==null||$("#surname").val()==""){
			$("#surname").addClass("red");
			num+=1;
		}
		if($("#telphone").val()==null||$("#telphone").val()==""){
			$("#telphone").addClass("red");
			num+=1;
		}
		if($("#company").val()==null||$("#company").val()==""){
			$("#company").addClass("red");
			num+=1;
		}
		if(num==0){
			$.ajax({
			url:"/home/usr_UsrPurchase_updPurchaseInf",
			data:$("#frm_profile").serialize(),
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.ret==1){
					layer.msg(lang_obj.addressfrom.Successfully_modified,{icon:1,time:3000});
				}
			}
		})
		}


	})
	$("#ChangeYourEmailAddress").on("click",function(){
		var num=0;
		var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		$("#pwd").removeClass("red");
		$("#email").removeClass("red");
		if($("#pwd").val()==null||$("#pwd").val()==""){
			$("#pwd").addClass("red");
			num+=1;
		}
		if($("#email").val()==null||$("#email").val()==""){
			$("#email").addClass("red");
			num+=1;
		}
		if(!reg.test($("#email").val())){
      layer.msg('Email format error', {icon: 2});
			num+=1;
		}
		if(num==0){
			$.ajax({
				url:"/home/usr_UsrPurchase_upEmail",
				data:$("#frm_email").serialize(),
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.ret==1){
						layer.msg(lang_obj.signIn.email+" "+lang_obj.addressfrom.Successfully_modified,{icon:1,time:3000},function(){
							location.reload();
						});
					}else{
            			layer.msg(getMessage(data.msg), {icon: 2});
					}
				}
			})
		}
	})
	$("#ChangeYourPassword").on("click",function(){
		$("#oldPwd").removeClass("red");
		$("#newPwd").removeClass("red");
		$("#newPwd2").removeClass("red");
		var num=0;
		if($("#oldPwd").val()==null||$("#oldPwd").val()==""){
			$("#oldPwd").addClass("red");
			num+=1;
		}
		if($("#newPwd").val()==null||$("#newPwd").val()==""){
			$("#newPwd").addClass("red");
			num+=1;
		}
		if($("#newPwd2").val()==null||$("#newPwd2").val()==""){
			$("#newPwd2").addClass("red");
			num+=1;
		}
		if($("#newPwd").val()!=$("#newPwd2").val()){
			num+=1;
      		layer.msg('The passwords entered do not match', {icon: 2});
		}
		if(num==0){
			$.ajax({
				url:"/home/usr_UsrPurchase_updPwd",
				data:$("#frm_password").serialize(),
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.ret==1){
						layer.msg(lang_obj.signIn.password+" "+lang_obj.addressfrom.Successfully_modified,{icon:1,time:3000},function(){
							location.reload();
						});
					}else{
            			layer.msg(getMessage(data.msg), {icon: 2});
					}
				}
			})
		}
	})
</script>

</html>
