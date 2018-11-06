<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us" class="hb-loaded">
<head>
		<%@include file="/mobile/template/style_import.jsp" %>
		<style type="text/css">
			#address_btn{bottom:3.4rem}
			#user{min-height: 445px;}
		</style>
</head>
<body huaban_collector_injected="true">
<%@include file="/mobile/template/header.jsp" %>
<div class="wrapper">
	<div id="user">
		<div class="address_box">
			<div class="title ui_border_b">
				<s:text name="address.Your_Billing_Address"/>
			</div>
			<div class="txt" id="address_list">
			<c:if test="${date==1}">
					<div class="address_row" data-aid="393" data-cid="2">
						<div class="info" data-url="/account/address/?Form=1&amp;AId=393">
							<a onclick="setaddress(1)" class="btn_edit edit_address_info" style="margin-top: 0.6rem;"><em></em></a>
							<%-- <strong>, ,</strong>
							<p>
								, , , ,
							</p>
							<p>
								<s:text name="supplier.telephone" />: ,
							</p> --%>
							<p>
								<s:text name="address.Add_A_New_Billing_Address" />
							</p>
						</div>
					</div>
					<div class="divide_20px">
					</div>
			</c:if>
			<c:if test="${date==0}">
				<s:iterator value="billAddr" var="line">
					<div class="address_row" data-aid="393" data-cid="2">
						<div class="info" data-url="/account/address/?Form=1&amp;AId=393">
							<a onclick="setPkey(${line.id})" class="btn_edit edit_address_info"><em></em></a>
							<strong>${line.name} ${line.surname}</strong>
							<p>
								${line.address}, ${line.city},${line.region},${line.emailCode},${line.country}
							</p>
							<p>
								<s:text name="supplier.telephone" />: ${line.phoneNumber}
							</p>
						</div>
					</div>
					<div class="divide_20px">
					</div>
				</s:iterator>
			</c:if>
			</div>
		</div>

		<div class="address_box">
			<div class="title ui_border_b">
				<s:text name="address.Your_Shipping_Address"/>
			</div>
			<div class="txt" id="address_list">
				<c:if test="${data==0}">
					<s:iterator value="shippingAddr" var="line">
						<div class="address_row" data-aid="877" data-cid="226">
							<div class="info" data-url="">
								<a onclick="setPkey(${line.id})" class="btn_edit edit_address_info"><em></em></a>
								<strong>${line.name} ${line.surname}</strong>
								<p>
									${line.address}, ${line.city},${line.region},${line.emailCode},${line.country}
								</p>
								<p>
									<s:text name="supplier.telephone" />: ${line.phoneNumber}
								</p>
							</div>
							<div class="button ui_border_tb">
								<div class="fl selected">
								<c:if test="${line.isdefault == 0}">
										<a href="javascript:;" class="" data-aid="877" version="${line.rowVersion}" adid="${line.id}" onclick="setDefault(this)"><i class="FontBgColor" ></i></i><s:text name="address.Set_As_Default_Address" /></a>
									</c:if>
									<c:if test="${line.isdefault == 1}">
										<a href="javascript:;" class="FontColor" data-aid="877" onclick="Defaultaddrss()"><i class="FontBgColor"></i><s:text name="address.Default_Address" /><!-- Default address --></a>
									</c:if>
								</div>
								<div class="fr menu">
									<a class="del_address_info" onclick="deleaddress(${line.id})"><!-- Delete --><s:text name="user.delete" /></a>
								</div>
							</div>
						</div>

						<div class="address_divide">
						</div>
					</s:iterator>
				</c:if>
				
			<div id="address_btn" class="clean">
				<a href="/home/usr_UsrPurchase_userIndex" class="btn btn_back"><em><i></i></em></a>
				<a onclick="setaddress(0)" class="btn add_address FontBgColor"><s:text name="Global.Add_New_Shipping_Address" /></a>
			</div>
			
			</div>
		</div>
	</div>
	<footer>
			<%-- <div id="prolist_mask_footer">
			</div>
			<div class="footer_top clean">
			</div> --%>
			<ul class="footer_list ui_border_b clean" style="display:none;">
				<li class="fl" style="border-right:1px solid #ddd;">
				<a href="/home/usr_UsrPurchase_userIndex" class="clean">
				<span class="list_left"><s:text name="sign_in" /></span>
				<span class="list_right"><em><i></i></em></span>
				</a>
				</li>
			</ul>
			<nav></nav>
			<section class="font_col border_col copyright">
				Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号
			</section>
		</footer>
		<div style="height:3rem;"></div>
</div>
<form action="/home/odr_OdrOrder_toSettlementPage" method="post" id="toSettlementPage">
 	<input type="hidden" value='${jsonCarts}' name="jsonCarts"/>
 </form>
<%@ include file="/mobile/template/foot_menu.jsp" %>


<script type="text/javascript">
	window.onload = function(){
		getParam();
	}

	function setDefault(btn){
		layer.open({
	    content: '<s:text name="defaultaddress"/>'
	    ,btn: ['<s:text name="Global.Yes"/>', '<s:text name="Global.No"/>']
	    ,yes: function(index){
				var id=	$(btn).attr("adid")
				var rowV=$(btn).attr("rowVersion")
				$.ajax({
					url:'/home/usr_UsrPurchaseLine_setdefault',
					type:'post',
					data:{"bean.pkey":id,"bean.rowVersion":rowV},
					dataType:'json',
					success:function(data){
						if(data.ret == 1){
							layer.open({content: lang_obj.addressfrom.Successfully_modified,skin: 'msg'});
							setTimeout(function(){
								 //刷新页面
								if($("#toSettlementPage input[name='jsonCarts']").val() != ""){
									$("#toSettlementPage").submit();
									//window.location.href = window.location.origin + getParam();
								}else{
									location.reload();
								}
							},1500);
						}else{
							layer.open({content: data.msg,skin: 'msg',time: 2});
						}
					}
				})
	      layer.close(index);
	    }
	  });
	}

	function getParam(){
		var str = window.location.href.substring(window.location.href.indexOf("?")+1);
		var strArr = str.split("&");
		var paramArr = [];
		var param = {};
		for(var i=0;i<strArr.length;i++){
			param.key = strArr[i].split("=")[0];
			param.value = strArr[i].split("=")[1]+"="+strArr[i].split("=")[2];
			paramArr.push(param);
		}
		return paramArr[0].value;
	}

	function deleaddress(id){
		layer.open({
	    content: '<s:text name="deletethisaddress"/>'
	    ,btn: ['<s:text name="Global.Yes"/>', '<s:text name="Global.No"/>']
	    ,yes: (index)=>{
				$.ajax({
					url:'/home/usr_UsrPurchaseLine_del',
					type:'post',
					data:{"bean.pkey":id},
					dataType:'json',
					success:(data)=>{
						setTimeout("emailshow()",500);
						location.reload();
					}
				})
	      layer.close(index);
	    }
	  });
	}

	function Defaultaddrss(){
		layer.open({
			content: '<s:text name="defaultaddressalready"/>'
			,skin: 'msg'
			,time: 2
		 });
	}

	function setPkey(key){
	 	sessionStorage.setItem("addkey", key);
		window.location.href="/home/usr_UsrPurchaseLine_addressform";
	}

	function setaddress(key){
		sessionStorage.setItem("set", key);
		window.location.href="/home/usr_UsrPurchaseLine_addressform";
	}

</script>
</body>
</html>
