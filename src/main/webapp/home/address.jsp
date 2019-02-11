<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<!-- Global site tag (gtag.js) - Google Analytics -->
<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
<%-- <script type="text/javascript" async="" src="./static/js/tracking.js"></script> --%>
<script type="text/javascript" async="" src="./static/js/analytics.js"></script>
<script async="" src="./static/js/gtm.js"></script>

<meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
<meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
<link href="./static/css/global.css" rel="stylesheet" type="text/css">
<link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
<link href="./static/css/user.css" rel="stylesheet" type="text/css">
<link href="./static/css/style.css" rel="stylesheet" type="text/css">
<link href="./static/css/color.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./static/js/main.js"></script>
<link rel="stylesheet" href="./static/css/color.css" type="text/css">
<link rel="stylesheet" href="./static/css/animate.min.css">
<link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
<script type="text/javascript" src="./static/js/layer.js"></script>
<link rel="stylesheet" href="./static/css/layer.css" type="text/css">

  <style media="screen">
  ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
    color: #aaa;
    font-weight: normal;
  }
  :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #aaa;
    font-weight: normal;
  }
  ::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #aaa;
    font-weight: normal;
  }
  :-ms-input-placeholder { /* Internet Explorer 10-11 */
    color: #aaa;
    font-weight: normal;
  }

  /* 删除、修改 icon垂直居中 */
  #lib_user_address .addr_item img{
    vertical-align: middle;
  }
  .por{
    position: relative;
  }
  /* 设置默认按钮设置最大值 - 防止换行 */
  /* .addr_item:hover .btn-default-addr{
    max-width: 100px;
    white-space: nowrap;
    text-overflow:ellipsis;
    overflow:hidden;
  } */
  /* 地址操作按钮组 */
  .addr-operate-wrap{
    position: absolute;
    right: 0;
    top: -1px;
    opacity: 0;
    transition: all 0.2s;
    background: #fff;
  }
  .addr_item:hover .addr-operate-wrap{
    opacity: 1;
  }

  .addr-operate-wrap .icon-edit,.addr-operate-wrap .icon-delete{margin-left: 6px;}
  </style>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
 	 <%@ include file="/home/template/web-top.jsp" %>
<div id="main">
	<index-top></index-top>
	<div class="wide">
		<div id="lib_user" class="clearfix">
			<div id="lib_user_crumb" class="widget">
				<ul class="crumb_box clearfix">
					<li class="home"><a href="/" title="Home"><s:text name="Global.Home"/><i></i></a></li>
					<li class="crumb1"><a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="Global.My_Account"/><i></i></a></li>
					<li class="crumb2 root"><a href="/home/usr_UsrPurchaseLine_addmanagement" title="Manage Address Book"><s:text name="address.Management_Address_Book"/><i></i></a></li>
				</ul>
			</div>
			<%@include file="/home/template/account/lib-user-menu.jsp" %>
			<div id="lib_user_main">
				<script type="text/javascript">
                    $(document).ready(function(){
                        user_obj.address_init();
                    });
				</script>
				<h1 class="lib_user_title"><s:text name="address.Management_Address_Book"/></h1>
				<div id="lib_user_address" class="index_pro_list clearfix">

					<div class="shipping_addr">
						<h3 class="big"><s:text name="address.Your_Billing_Address"/></h3>
						<div class="clearfix addr_list">
							<c:if test="${date=='1'}">
								<p class="big">
									<a  class="textbtn addAddress"  onclick="addresstype(1)"><s:text name="address.Add_A_New_Billing_Address"/></a>
								</p>
							</c:if>
							<div class="clear">
							</div>
							<c:if test="${date=='0'}">
								<s:iterator value="billAddr" var="line">
									<ul class="addr_item fl">
										<li><strong>${line.name} ${line.surname}</strong></li>
										<li>${line.address}</li>
										<li>${line.city},${line.region},${line.emailCode}</li>
										<li>&nbsp;</li>
										<li>${line.country}</li>
										<li><span class="phone_icon"><s:text name="Global.Phone"/>: ${line.phoneNumber}</span></li>
										<li><a class="edit_shipping_addr textbtn" onclick="updaddress(${line.id})" addrid="444"><s:text name="Global.Edit"/></a></li>
									</ul>
								</s:iterator>
							</c:if>
						</div>
					</div>

					<div class="shipping_addr">
						<h3 class="big"><s:text name="address.Your_Shipping_Address"/></h3>
						<div class="clearfix addr_list">
							<p class="big">
								<input type="hidden" value="" id="testaddress"></input>
								<a  class="textbtn addAddress"   onclick="addresstype(0)"><s:text name="Global.Add_New_Shipping_Address"/></a>
							</p>
							<div class="clear">
							</div>
							<c:if test="${data==0}">
								<s:iterator value="shippingAddr" var="line">
									<ul class="addr_item fl">
										<li><strong>${line.name} ${line.surname}</strong></li>
										<li>${line.address}</li>
										<li>${line.city},${line.region},${line.emailCode}</li>
										<li>&nbsp;</li>
										<li>${line.country}</li>
										<li><span class="phone_icon"><s:text name="Global.Phone"/>: ${line.phoneNumber}</span></li>
										<li class="por">
											<c:if test="${line.isdefault == 0}">
												<a class="del_shipping_addr textbtn btn-default-addr" version="${line.rowVersion}" adid="${line.id}" onclick="setDefault(this)" ><s:text name="address.Set_As_Default_Address"/></a>
											</c:if>
											<c:if test="${line.isdefault == 1}">
												<a class="del_shipping_addr textbtn btn-default-addr" onclick="setDefault()" ><s:text name="address.Default_Address"/></a>
											</c:if>
											<div class="addr-operate-wrap">
												<a class="icon-edit edit_shipping_addr" onclick="updaddress(${line.id})" addrid="444">
													<img src="/home/static/images/ico/icon-edit2.png" alt="">
												</a>
												<a class="icon-delete" onclick="deleaddress(${line.id})">
													<img src="/home/static/images/ico/icon-delete2.png" alt="">
												</a>
											</div>
										</li>
									</ul>
								</s:iterator>
							</c:if>
						</div>
					</div>

					<div id="addressForm">
						<h3 class="big"><s:text name="address.Your_Billing_Address"/></h3>
						<script type="text/javascript">$(document).ready(function(){user_obj.user_address()});</script>
						<div class="editAddr">
							<form id="addressshow">
								<%-- 区分添加modify1 or modify时的字段 --%>
								<input id="addressType" type="hidden" name="addressType" value="0">
								<input id="addressId" type="hidden" name="edit_address_id" value="">
								<a id="cancelAddr" class="cancel" href="javascript:;" style="display:;"><s:text name="Global.Cancel"/></a>
								<p>
									<span class="required" style="color:red">*</span>&nbsp;<span class="indicates"><s:text name="Global.Indicates_Required_Fields"/></span>
								</p>
								<table class="tb-shippingAddr">
									<tbody>
									<tr>
										<th>
										</th>
										<td>
										</td>
									</tr>
									<tr>
										<th>
											<label><s:text name="Global.Name"/>:</label>
										</th>
										<td class="recipient">
											<div>
												<input type="text" id="adname" name="bean.name" maxlength="32" class="elmbBlur"  value="">
												<p>
													<span class="required">*</span>&nbsp;<s:text name="Global.First_Name"/>
												</p>
												<p id="addname" style="display:none; color:red;" ><s:text name="address.Please_Enter_Your_Name"/></p>
												<p class="errorInfo">
												</p>
											</div>
											<div>
												<input type="text" id="adsurname" name="bean.surname" maxlength="32" class="elmbBlur" value="">
												<p>
													<span class="required">*</span>&nbsp;<s:text name="Global.Surname"/>
												</p>
												<p id="addsurname" style="display:none; color:red;" ><s:text name="address.Please_Enter_Your_Last_Name"/></p>
												<p class="errorInfo">
												</p>
											</div>
										</td>
									</tr>
									<tr>
										<th>
											<span class="required">*</span><label><s:text name="Global.Postal_Code"/>:</label>
										</th>
										<td>
											<input type="text" id="ademailcode" name="bean.emailcode" maxlength="10" class="elmbBlur" value="">
											<p id="addemail" style="display:none; color:red;" ><s:text name="address.Please_Enter_Your_Zip_Code"/></p>
											<p class="errorInfo">
											</p>
										</td>
									</tr>
									<tr>
										<th>
											<span class="required">*</span><label><s:text name="Global.Country"/>:</label>
										</th>
										<td>
											<select  id="country" placeholder="Please Choose Your Country" style="display:none;" class="chzn-done" value="">
												<option value="-1"></option>
												<optgroup label="---------">
												</optgroup>
											</select>
											<input id="countryin" name="bean.country" type="hidden"  value="${countries[0].pkey}" />
											<div id="country_chzn" class="chzn-container chzn-container-single" style="width:310px">
												<a href="javascript:void(0)"  class="chzn-single">
													<span id="selCountry"  value="${countries[0].pkey}">${countries[0].name}</span>
													<div>
														<b></b>
													</div>
												</a>
												<div class="chzn-drop" style="left: -9000px; width: 308px;">
													<div class="chzn-search clearfix" style="height: 20px;">
														<input type="text" autocomplete="off" class="">
													</div>
													<ul class="chzn-results" id="countrylist">
														<s:iterator value="countries" var="line">
															<li class="group-option active-result" id="${line.pkey}" onclick="listProv(${line.pkey})" >${line.name}</li>
														</s:iterator>
													</ul>

												</div>
											</div>
											<p class="errorInfo">
											</p>
										</td>
									</tr>
									<tr id="taxCode" style="display: none;">
										<th>
											<span class="required">*</span><label>CPF or CNPJ code:</label>
										</th>
										<td>
											<select name="tax_code_type" class="taxCodeOption" id="taxCodeOption" disabled="disabled">
												<option value="1" selected="selected">CPF (personal order)</option>
												<option value="2">CNPJ (company order)</option>
											</select>
											<input type="text" name="tax_code_value" id="taxCodeValue" maxlength="11" class="taxCodeValue elmbBlur" disabled="disabled">
											<p class="lightGray">
												Please only input numbers, no dots, dashes or other characters.
											</p>
											<p class="errorInfo">
											</p>
										</td>
									</tr>
									<tr id="tariffCode" style="display: none;">
										<th>
											<span class="required">*</span><label>Personal or VAT ID</label>
										</th>
										<td>
											<select name="tax_code_type" class="tariffCodeOption" id="tariffCodeOption" disabled="disabled">
												<option value="3" selected="selected">My personal details</option>
												<option value="4">VAT ID number (company order)</option>
											</select>
											<input type="text" name="tax_code_value" id="tariffCodeValue" maxlength="11" class="tariffCodeValue elmbBlur" disabled="disabled">
											<a href="javascript:void(0);" class="lightGray askTipsTariff" title="Your Personal ID/VAT ID number is required to ensure successful delivery of your order."><s:text name="Global.Doubt"/></a>
											<p class="lightGray">
												Please only input numbers, no dots, dashes or other characters.
											</p>
											<p class="errorInfo">
											</p>
										</td>
									</tr>
									<tr id="zoneId" style="display: table-row;">
										<th>
											<span class="required">*</span><label><s:text name="Global.Province"/>:</label>
										</th>
										<td>
											<select  id="usrsregion" placeholder="Please select---" class="chzn-done" style="display:none;" value="">
												<option value="-1"></option>
												<optgroup label="---------">
												</optgroup>
											</select>
											<input id="inpkey" name="bean.pkey" type="hidden"  value="" />
											<input id="inprowV" name="bean.rowVersion" type="hidden"  value="" />
											<input id="provincesin" name="province" type="hidden"  value="${provinces[0].pkey}" />

											<div  id="showProvice">
												<div    class="chzn-container chzn-container-single" style="width:310px">
													<a href="javascript:void(0)" class="chzn-single" tabindex="0">
														<span id="setpro"  value="${provinces[0].pkey}">${provinces[0].name}</span>
														<span id="addregion" style="display:none; color:red;" >Please select your state/province/region.</span>
														<div>
															<b></b>
														</div>
													</a>

													<div class="chzn-drop" style="left: -9000px; width: 308px;">
														<div class="chzn-search clearfix" style="height: 20px;">
															<input type="text" autocomplete="off" tabindex="-1" class="">
														</div>
														<ul class="chzn-results" id="provin" >
															<s:iterator value="provinces" var="line">
																<li class="group-option active-result" name="shepiguai" id="${line.pkey}" onclick="proin(${line.pkey})">${line.name}</li>
															</s:iterator>
														</ul>
													</div>
												</div>
											</div>
											<%-- 自己输入省份 --%>
											<%-- <div id="showProviceInput">
                                                                 <input type="text" name=""  id="newProvince" maxlength="32" class="elmbBlur"
                                                   oninput="inputMyAddrByMyself(this)" placeholder="<s:text name="manualinput"/>"/>
                                                            </div>
                                            <p id="provinceErrTip" style="display:none; color:red;" >Please complete your province.</p>
                                                            <p class="errorInfo">
                                                            </p> --%>
										</td>
									</tr>
									<tr id="state" style="display: none;">
										<th>
											<label>State / Province / Region:</label>
										</th>
										<td>
											<input type="text" name="" maxlength="32" class="elmbBlur" disabled="disabled">
										</td>
									</tr>
									<tr>
										<th>
											<span class="required">*</span><label><s:text name="Global.City"/>:</label>
										</th>
										<td>
											<input type="text" name="bean.city" id="adcity" maxlength="30" class="elmbBlur" value="">
											<p id="addcity" style="display:none; color:red;" ><s:text name="address.Please_Enter_Your_City"/></p>
											<p class="errorInfo">
											</p>
										</td>
									</tr>
									<tr>
										<th>
											<span class="required">*</span><label><s:text name="Global.Detailed_Address"/>:</label>
										</th>
										<td>
											<input type="text" name="bean.address" id="adaddress" maxlength="100" class="elmbBlur" value="">
											<p id="addaddress" style="display:none; color:red;" ><s:text name="address.Please_Enter_Your_Full_Address"/></p>
											<p class="lightGray">
												<s:text name="address.For_Example"/>
											</p>
										</td>
									</tr>
									<tr>

									</tr>
									<tr>
										<th>
											<span class="required">*</span><label><s:text name="Global.Phone"/>:</label>
										</th>
										<td>
											<!-- <input id="countryCode" class="left countryCode" name="CountryCode" type="text" value="" readonly=""> -->
											<div class="left editableSelect hasLayout">
												<input type="text" name="bean.phonenumber" id="adphonenumber" class="phoneNum elmbBlur" maxlength="15" autocomplete="off" value="">
												<p id="addphonenumber" style="display:none; color:red;" ><s:text name="address.Please_Enter_Your_Phone_Number"/></p>
												<ul id="otherPhones">
												</ul>
												<p class="errorInfo">
												</p>
											</div>
											<a href="javascript:void(0);" class="lightGray askTips" title="We ask for your phone number just in case we need to reach you regarding your order."><s:text name="user.whyAsk"/></a>
											<p id="phoneSample" class="lightGray clearfix">
												<s:text name="Global.For_Example"/>: +<span>1</span> 9549031647-535
											</p>
										</td>
									</tr>
									<tr>
										<th>
										</th>
										<td>
											<button  type="button" class="textbtn" id="d0" onclick="modify()" style="display:none"><s:text name="Global.Save"/></button>
											<button  type="button" class="textbtn" id="d1" onclick="modify1()" style="display:none"><s:text name="Global.Save"/></button>
										</td>
									</tr>
									</tbody>
								</table>
								<input type="reset" id="resetAddr" style="display:none;">
								<input type="hidden" name="typeAddr" value="0">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<form action="/home/odr_OdrOrder_toSettlementPage" method="post" id="toSettlementPage">
 	<input type="hidden" value='${jsonCarts}' name="jsonCarts"/>
 </form>
<input type="hidden"  id="hid"></input>
<%@ include file="/home/template/new-foot.jsp" %>
</body>
<script type="text/javascript">

	function emailshow(){
		location.reload();
	}
	 function listProv(id){
			 $.ajax({
			 	type:"post",
			 	url:"/home/usr_UsrPurchaseLine_listProvinces",
			 	data:{"countryId":id},
			 	dataType:"JSON",
			 	success:function(data){
			 	$("#showProvice").css("display","block");
			 	if(data.items=="" || data.items.length <= 0){
				 		$("#provin").empty();
				 		// $("#setpro").empty();
				 		// $("#showProvice").css("display","none");
			            $("#provincesin").val(null);
			            $("#setpro").val(-1);
			            $("#setpro").text(lang_obj.addressfrom.No_Province);
			 			return false;
		 		}
		 		var str="";
		 		$("#provin").empty();
		 		// $("#setpro").empty();
		 		$("#provincesin").val(data.items[0].pkey);
		 		$("#setpro").val(data.items[0].pkey);
		 		$("#setpro").text(data.items[0].name);
		 		$.each(data.items,function(i,val){
		 			str+="<li class='group-option active-result' id="+val.pkey+"  onclick='proin("+val.pkey+")'>"+val.name+"</li>";
		 		})
        // 末尾添加一个可以选择自己填写的选项
		 		$("#provin").append(str);

			 	}
			 });
			 $("#countryin").val(id);


	 }
	 var updpkey=0;
	function modify(){
    // 区分添加modify1 or modify时的字段
    $("#addressType").val(0);
		if( ($("#setpro").text().trim()=="" ) || $("#adname").val().trim()=="" || $("#adsurname").val().trim()=="" || $("#ademailcode").val().trim()=="" || $("#provincesin").val().trim()=="" || $("#adcity").val().trim()=="" || $("#adaddress").val().trim()=="" || $("#adphonenumber").val().trim()==""){
        // layer.msg('<s:text name="Global.Please_Complete_The_Form"/>', {icon: 5});
        // 省市没有输入时
        if( $("#setpro").text().trim()=="" ){
					$("#provinceErrTip").css("display","block");
          return;
				}else{
					$("#provinceErrTip").css("display","none");
				}

				if( $("#adname").val().trim()==""){
					$("#addname").css("display","block");
          return;
				}else{
					$("#addname").css("display","none");
				}
				if( $("#adsurname").val().trim()==""){
					$("#addsurname").css("display","block");
          return;
				}else{
					$("#addsurname").css("display","none");
				}

				if( $("#ademailcode").val().trim()==""){
					$("#addemail").css("display","block");
          return;
				}else{
					$("#addemail").css("display","none");
				}

				if( $("#provincesin").val().trim()==""){
          layer.msg('No matching province were found,Please contact the manager', {icon: 5});
					$("#addregion").css("display","block");
          return;
				}else{
					$("#addregion").css("display","none");
				}

				if( $("#adcity").val().trim()==""){
					$("#addcity").css("display","block");
          return;
				}else{
					$("#addcity").css("display","none");
				}

				if( $("#adaddress").val().trim()==""){
					$("#addaddress").css("display","block");
          return;
				}else{
					$("#addaddress").css("display","none");
				}

				if( $("#adphonenumber").val().trim()==""){
					$("#addphonenumber").css("display","block");
          return;
				}else{
					$("#addphonenumber").css("display","none");
				}
		}else{
				if(updpkey==0){
						$("#inpkey").val("");
						$("#inprowV").val("");
            $.ajax({
              type:"post",
              url:"/home/usr_UsrPurchaseLine_AddToAddress",
              data:$('#addressshow').serialize(),
              dataType:"JSON",
              success:function(data){
            	  if($("#toSettlementPage input[name='jsonCarts']").val() != ""){
						$("#toSettlementPage").submit();
            	  }else{
		                layer.msg(lang_obj.goods_info.Added_successfully, {icon: 1});
		                setTimeout("emailshow()",1000);
            	  }
              },
              error:function(err){
                console.log("modify 0 - err",err)
              }
            });
				}else{
          $.ajax({
            type:"post",
            url:"/home/usr_UsrPurchaseLine_UpdAddress",
            data:$('#addressshow').serialize(),
            dataType:"JSON",
            success:function(data){
                  $("#inpkey").val("");
                  $("#inprowV").val("");
                  updpkey=0;
                  layer.msg(lang_obj.addressfrom.Successfully_modified, {icon: 1});
                  setTimeout("emailshow()",1000);
                }
             });

					}
			}
		}

	function modify1(){
    // 区分添加modify1 or modify时的字段
    $("#addressType").val(1);
		if( $("#setpro").text().trim()=="" || $("#adname").val().trim()=="" || $("#adsurname").val().trim()=="" || $("#ademailcode").val().trim()=="" || $("#provincesin").val().trim()=="" || $("#adcity").val().trim()=="" || $("#adaddress").val().trim()=="" || $("#adphonenumber").val().trim()==""){
      // layer.msg('<s:text name="Global.Please_Complete_The_Form"/>', {icon: 5});
      // 省市没有输入时
      if( $("#setpro").text().trim()=="" ){
        $("#provinceErrTip").css("display","block");
        return;
      }else{
        $("#provinceErrTip").css("display","none");
      }

			if( $("#adname").val().trim()==""){
				$("#addname").css("display","block");
        return;
			}else{
				$("#addname").css("display","none");
			}

			if( $("#adsurname").val().trim()==""){
				$("#addsurname").css("display","block");
        return;
			}else{
				$("#addsurname").css("display","none");
			}

			if( $("#ademailcode").val().trim()==""){
				$("#addemail").css("display","block");
        return;
			}else{
				$("#addemail").css("display","none");
			}

			if( $("#provincesin").val().trim()==""){
        layer.msg('No matching province were found,Please contact the manager', {icon: 5});
				$("#addregion").css("display","block");
        return;
			}else{
				$("#addregion").css("display","none");
			}

			if( $("#adcity").val().trim()==""){
				$("#addcity").css("display","block");
        return;
			}else{
				$("#addcity").css("display","none");
			}

			if( $("#adaddress").val().trim()==""){
				$("#addaddress").css("display","block");
        return;
			}else{
				$("#addaddress").css("display","none");
			}

			if( $("#adphonenumber").val().trim()==""){
				$("#addphonenumber").css("display","block");
        return;
			}else{
				$("#addphonenumber").css("display","none");
			}
	}else{
			$.ajax({
				type:"post",
				url:"/home/usr_UsrPurchaseLine_AddToAddress",
				data:$('#addressshow').serialize(),
				dataType:"JSON",
				success:function(data){
					if(data.ret == 1){
						if($("#toSettlementPage input[name='jsonCarts']").val() != ""){
							$("#toSettlementPage").submit();
							//window.location.href = window.location.search.substring(window.location.search.indexOf("=")+1);
						}else if(window.location.search.indexOf("jumpUrl") != -1){
							window.location.href = window.location.search.substring(window.location.search.indexOf("=")+1);
						}else{
		           			layer.msg(lang_obj.addressfrom.Inserted_successfully, {icon: 1});
							setTimeout("emailshow()",1000);
						}
					}else{
						layer.msg(getMessage(data.msg), {icon: 5});
					}
				}
			});
		}
	}
	function proin(id){
    // 赋值下拉选的值
		$("#provincesin").val(id);
	}
	function deleaddress(id){
    layer.confirm(lang_obj.user.delete_shipping, {btn: [lang_obj.global.confirm, lang_obj.global.cancel],icon: 7}, function(index){
      $.ajax({
				url:'/home/usr_UsrPurchaseLine_del',
				type:'post',
				data:{"bean.pkey":id},
				dataType:'json',
				success:function(data){
            layer.msg('<s:text name="Global.Successfully_Deleted"/>', {icon: 1});
						setTimeout("emailshow()",1000);
				}
			})
      layer.close(index);
    });
	}

	function addresstype(id){
		initializing();
		$("#provincesin").val("");
		$("#countryin").val("");
		if(id==1){
			$("#d1").css("display","block");
			$("#d0").css("display","none");
		}else{
			$("#d1").css("display","none");
			$("#d0").css("display","block");

		}
	}

	var ids="";
	function initializing(){
		$.ajax({
		 	type:"post",
		 	url:"/home/usr_UsrPurchaseLine_initializing",
		 	data:{"coun":ids},
		 	dataType:"JSON",
		 	success:function(data){
		 		$("#countryin").val(data.pc[0].pkey);
		 		$("#provincesin").val(data.pr[0].pkey);
		 	}
		 });

	}
	function setDefault(btn){
		if(btn==null){
      		layer.msg('<s:text name="address.This_Address_Is_The_Default_Address"/>', {btn:["<s:text name='yes'/>","<s:text name='no'/>"],icon: 7});
		  return false;
		}
		var id=	$(btn).attr("adid")
		var rowV=$(btn).attr("rowVersion")

    layer.confirm("<s:text name='address.Do_You_Want_To_Set_It_As_The_Default_Address'/>", {btn:["<s:text name='yes'/>","<s:text name='no'/>"],icon: 7}, function(index){
      $.ajax({
  			url:'/home/usr_UsrPurchaseLine_setdefault',
  			type:'post',
  			data:{"bean.pkey":id,"bean.rowVersion":rowV},
  			dataType:'json',
  			success:function(data){
  					//刷新页面
            	layer.msg('<s:text name="address.Set_Successfully"/>', {icon: 1,time:1500},function(){
  					location.reload();
            	});
  			}
  		})
      layer.close(index);
    });
	}
	var updpkey=0;
	function updaddress(id){
		$("#d0").css("display","block");
		$("#d1").css("display","none");
		$.ajax({
			url:'/home/usr_UsrPurchaseLine_load',
			type:'post',
			data:{"pkey":id},
			dataType:'json',
			success:function(data){
					var countrypkey=data.country.split("##")[0];
									updpkey=data.pkey;
									$("#inpkey").val(data.pkey);
									$("#inprowV").val(data.rowVersion)
									$("#adname").val(data.name);
									$("#adsurname").val(data.surname);
									$("#ademailcode").val(data.emailcode);
									$("#countryin").val(countrypkey);
									listProv(data.country.split("##")[0]);
									$("#selCountry").val(countrypkey);
									$("#adcity").val(data.city);
									$("#adaddress").val(data.address);
									$("#adphonenumber").val(data.phonenumber);
									$("#selCountry").empty();
									$("#countrylist .group-option").each(function(){
											var eid=	$(this).attr("id");
											if(eid==countrypkey){
												$("#selCountry").append($(this).text());
											}

									})
                  setTimeout(function(){

                    $("#setpro").empty();
                    $("#provin .group-option.active-result").each(function(){
                      var eid=	$(this).attr("id");
                      if(eid==data.region.split("##")[0]){
                        $("#setpro").html($(this).text());
                        $(this).addClass("result-selected")
                      }
                    })
                    $("#provincesin").val(data.region.split("##")[0]);
                  },500)
			}
		})
	}

</script>
<script>
    new Vue({
        el:"#main"
    })
</script>
<script src="/home/v3/static/js/index-top.js"></script>

</html>
