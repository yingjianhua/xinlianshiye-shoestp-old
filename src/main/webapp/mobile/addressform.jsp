<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
    </head>
    <body>
		    <%@include file="/mobile/template/header.jsp" %>
        <div class="wrapper">
            <div id="user">
                <%-- 当前使用的语言 --%>
                <input type="hidden" id="lang" name="" value="${env.curLanguage}">
                <form id="addressshow" method="post" class="user_address_form">
                    <input type="hidden" name="addressType" id="addressType" value="0">
                    <div class="title">
                        <s:text name="purchase_line.addrsstype.0" />
                    </div>
                    <div class="rows"><!-- 名字 -->
                        <div class="form_name clean">
                            <div class="box">
                                <label class="field">
                                   <!--  First Name  -->
                                   <s:text name="mobile.first_name" />
                                    <span class="fc_red">
                                        *
                                    </span>
                                </label>
                                <input type="text" id="adname" class="box_input" name="bean.name" data-field="First Name"
                                notnull="" value="">
                                <p id="addname" style="display:none; color:red;" ><!-- Please enter your first name. --><s:text name="verification.firstname" /></p>
                                 <input type="hidden" id="pkey" class="box_input" name="bean.pkey" data-field="First Name">
                                <p class="error">
                                </p>
                            </div>
                            <div class="box">
                                <label class="field">
                                    <!-- Last Name -->
                                    <s:text name="mobile.last_name" />
                                    <span class="fc_red">
                                        *
                                    </span>
                                </label>
                                <input type="text" class="box_input" id="adsurname" name="bean.surname" data-field="Last Name"
                                notnull="" value="">
                                <p id="addsurname" style="display:none; color:red;" ><!-- Please enter your last name. --><s:text name="verification.lastname" /></p>
                                <p class="error">
                                </p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="rows"><!-- 国家 -->
                        <label class="field">
                            <!-- Destination Country/Region -->
                            <s:text name="user.region" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <div class="box_select">


                                <select class="addr_select" id="selCountry" name="bean.country">
                                  <c:forEach items="${countries}" var="line">
                										<option value="${line.pkey}">${line.name}</option>
                									</c:forEach>
                                </select>

                            </div>
                        </div>
                    </div>
                    
                     <div class="rows" id="zoneId" style="display: block;"><!-- 省份 -->
                        <label class="field">
                            <!-- State / Province / Region -->
                            <s:text name="Global.Province" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <div id="addregion1">
	                            <div class="box_select">
		                                <select class="addr_select" id="setpro" name="">
		                                    <s:iterator value="provinces " var="line">
                														<option value="${line.pkey}">${line.name}</option>
                												</s:iterator>
		                                </select>

	                            </div>
                            </div>
                            <%-- <br> --%>

                            <input id="province_upload" name="province" type="hidden" value="${provinces[0].pkey}" />

                            <p id="province_upload_tip" style="display:none; color:red;margin-top: 10px;" >
                              Please select your province.
                              If no matching province were found,Please contact the manager
                            </p>
                        </div>
                    </div>
                    
                    <div class="rows"><!-- 城市 -->
                        <label class="field">
                            <!-- City -->
                            <s:text name="supplier.city" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="text" name="bean.city" id="adcity" class="box_input" data-field="City" notnull="" value="">
                            <p id="addcity" style="display:none; color:red;" ><!-- Please enter your city. --><s:text name="verification.city" /></p>
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    
                    <div class="rows"><!-- 地址详情 -->
                        <label class="field">
                            <!-- Address Line -->
                            <s:text name="purchase_line.address" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="text" class="box_input" id="adaddress" name="bean.address" data-field="Address Line 1"
                            notnull="" value="">
                            <p id="addaddress" style="display:none; color:red;" ><!-- Please enter shipping address. --><s:text name="verification.shippingaddress" /></p>
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    
                    

                   

                    <%-- <div class="rows" id="state" style="display:none;">
                        <label class="field">
                            <!-- State / Province / Region -->
                            <s:text name="review.state" />
                        </label>
                        <div class="input clean">
                            <input type="text" class="box_input" name="State" disabled="disabled">
                        </div>
                    </div>
                    <div class="rows" id="taxCode" style="display:none;">
                        <label class="field">
                            CPF or CNPJ code
                        </label>
                        <div class="input clean">
                            <div class="box_select">
                                <select name="tax_code_type" class="addr_select" id="taxCodeOption" disabled="disabled">
                                    <option value="1">
                                        CPF (personal order)
                                    </option>
                                    <option value="2">
                                        CNPJ (company order)
                                    </option>
                                </select>
                            </div>
                            <div class="blank10">
                            </div>
                            <input type="text" name="tax_code_value" id="taxCodeValue" class="box_input"
                            placeholder="CPF or CNPJ code" disabled="disabled">
                        </div>
                    </div>
                    <div class="rows" id="tariffCode" style="display:none;">
                        <label class="field">
                            Personal or VAT ID
                        </label>
                        <div class="input clean">
                            <div class="box_select">
                                <select name="tax_code_type" class="addr_select" id="tariffCodeOption"
                                disabled="disabled">
                                    <option value="1">
                                        Personal ID number (personal order)
                                    </option>
                                    <option value="2">
                                        VAT ID number (company order)
                                    </option>
                                </select>
                            </div>
                            <div class="blank10">
                            </div>
                            <input type="text" name="tax_code_value" id="tariffCodeValue" class="box_input"
                            placeholder="Personal or VAT ID" disabled="disabled">
                        </div>
                    </div> --%>
                    <div class="rows"><!-- 邮政编码 -->
                        <label class="field">
                            <!-- ZIP / Postal Code -->
                            <s:text name="Global.Postal_Code" />

                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <input type="text" class="box_input" id="ademailcode" name="bean.emailcode" data-field="ZIP / Postal Code"
                            notnull="" value="">
                            <p id="addemail" style="display:none; color:red;" ><!-- Please enter a ZIP / postal code. --><s:text name="verification.ZIP" /></p>
                            <p class="error">
                            </p>
                        </div>
                    </div>
                    <div class="rows"><!-- 电话号码 -->
                        <label class="field">
                            <!-- Phone Number -->
                            <s:text name="order.phone" />
                            <span class="fc_red">
                                *
                            </span>
                        </label>
                        <div class="input clean">
                            <div class="box_input_group">
                                <input type="text" class="box_input input_group" name="bean.phonenumber" id="adphonenumber" data-field="Phone Number"
                                notnull="" value="">
                                <p id="addphonenumber" style="display:none; color:red;" ><!-- Please enter your phone number. --><s:text name="verification.phone" /></p>
                                <p class="error">
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="address_button">
                        <input type="button" value="<s:text name="user.save" />" class="btn_global btn btn_save FontBgColor"  onclick="modify()" style="display:none" id="d1">
                        <input type="button" value="<s:text name="user.save" />" class="btn_global btn btn_save FontBgColor"  onclick="AddBillingAddress()" style="display:none" id="d0">
                        <input type="button" value="<s:text name="user.back" />" class="btn_global btn btn_back" onclick="goBack()">
                    </div>
                    <input type="hidden" name="edit_address_id" value="466" id="addressId">
                    <input type="hidden" name="typeAddr" value="0">
                    <input type="hidden" name="back_url" value="/home/usr_UsrPurchaseLine_addmanagement">
                </form>
                <script>
                    $(function() {
                        //user_obj.set_default_address(466, 0);
                    });
                </script>
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
        <form action="/home/odr_OdrOrder_toSettlementPage" method="post" id="toSettlementPage">
		 	<input type="hidden" value='${jsonCarts}' name="jsonCarts"/>
		 </form>
        <%@ include file="/mobile/template/foot_menu.jsp" %>
        <script type="text/javascript">
        var updpkey=0;
        var key = "";
        var sid="";
        $(function (){
          $("#d0").css("display","none");
          $("#d1").css("display","block");
          // set值在添加操作下才有 - 0：shipping  1：billing
          sid = sessionStorage.getItem("set");
          if(sid==1){
            $("#d0").css("display","block");
            $("#d1").css("display","none");
          }

          key = sessionStorage.getItem("addkey");
          // 有值是edit操作 - 否则是添加操作
          if(key!=""){
            updaddress();
            updpkey=1;
            $("#pkey").val(key);
          }else{
            empty();
          }
          sessionStorage.setItem("addkey","");
          sessionStorage.setItem("set","");
        })
        function empty(){
          $("#adname").val("");
          $("#adsurname").val("");
          $("#ademailcode").val("");
          $("#adcity").val("");
          $("#adaddress").val("");
          $("#adphonenumber").val("");
        }

        // edit操作时将信息赋值显示在页面上
        function updaddress(){
          $.ajax({
            url:'/home/usr_UsrPurchaseLine_inquire',
            type:'post',
            data:{"pkey":key},
            dataType:'json',
            success:function(data){

							//alert(data.pv.pkey)
							$("#adname").val(data.us.name);
							$("#adsurname").val(data.us.surname);
							$("#ademailcode").val(data.us.emailcode);
							$("#adcity").val(data.us.city);
							$("#adaddress").val(data.us.address);
							$("#adphonenumber").val(data.us.phonenumber);
							$("#province_upload").val(data.pv.pkey);
							$("#selCountry option[value="+data.co.pkey+"]").attr("selected","selected");
              $("#setpro").empty();

              if( data.lis.length > 0 ){
                var str="";
                $.each(data.lis,function(i,val){
                  // name之前为多语言的对象字段 => 现只取选中的当前语言字段
                  str+="<option value='"+val.pkey+"'>"+JSON.parse(val.name)[ $("#lang").val() ]+"</option>";
                })
                $("#setpro").append(str);
                $("#setpro").val(data.pv.pkey);
              }
						}
					})
				}


    	 function emailshow(){
    		//location.reload();
    		window.location.href="/home/usr_UsrPurchaseLine_addmanagement";
    	}


    		function modify(){
    			if($("#adname").val().trim()=="" || $("#adsurname").val().trim()=="" || $("#province_upload").val().trim()=="" || $("#ademailcode").val().trim()=="" || $("#adcity").val().trim()=="" || $("#adaddress").val().trim()=="" || $("#adphonenumber").val().trim()==""){


    				if( $("#adname").val().trim()==""){
    					$("#addname").css("display","block");
    				}else{
    					$("#addname").css("display","none");
    				}

    				if( $("#adsurname").val().trim()==""){
    					$("#addsurname").css("display","block");
    				}else{
    					$("#addsurname").css("display","none");
    				}

    				if( $("#ademailcode").val().trim()==""){
    					$("#addemail").css("display","block");
    				}else{
    					$("#addemail").css("display","none");
    				}

    				if( $("#province_upload").val().trim()==""){
    					$("#province_upload_tip").css("display","block");
    				}else{
    					$("#province_upload_tip").css("display","none");
    				}

    				if( $("#adcity").val().trim()==""){
    					$("#addcity").css("display","block");
    				}else{
    					$("#addcity").css("display","none");
    				}

    				if( $("#adaddress").val().trim()==""){
    					$("#addaddress").css("display","block");
    				}else{
    					$("#addaddress").css("display","none");
    				}

    				if( $("#adphonenumber").val().trim()==""){
    					$("#addphonenumber").css("display","block");
    				}else{
    					$("#addphonenumber").css("display","none");
    				}
    			}else{
            // 0：添加操作
	    			if(updpkey==0){
  							$("#inpkey").val("");
  							$("#inprowV").val("");
	    					$.ajax({
	    						 	type:"post",
	    						 	url:"/home/usr_UsrPurchaseLine_AddToAddress",
	    						 	data:$('#addressshow').serialize(),
	    						 	dataType:"JSON",
	    						 	success:function(data){
	    						 		if(data.ret == 1){
	    						 			if($("#toSettlementPage input[name='jsonCarts']").val() != ""){
		    						 			layer.open({content: lang_obj.addressfrom.Inserted_successfully,skin: 'msg'});
		    						 			setTimeout(function(){
		    						 				$("#toSettlementPage").submit();
		    						 			},1500);

		    						 		}else{
	                        					layer.open({content: lang_obj.addressfrom.Inserted_successfully,skin: 'msg',time: 2});
		    									setTimeout("emailshow()",1500);
		    						 		}
	    						 		}else{
	    						 			layer.open({content:data.msg,skin: 'msg'});
	    						 		}


	    						 	},
	    						 });
              // 1：修改操作
	    				}else{
	    					$.ajax({
	    						 	type:"post",
	    						 	url:"/home/usr_UsrPurchaseLine_UpdAddress",
	    						 	data:$('#addressshow').serialize(),
	    						 	dataType:"JSON",
	    						 	success:function(data){
	    						 		if(data.ret == 1){
	    						 			if($("#toSettlementPage input[name='jsonCarts']").val() != ""){
	    						 				layer.open({content: lang_obj.addressfrom.Inserted_successfully,skin: 'msg'});
		    						 			setTimeout(function(){
		    						 				$("#toSettlementPage").submit();
		    						 			},1500);
		    						 		}else{
	    						 				$("#inpkey").val("");
	    										$("#inprowV").val("");
	    										updpkey=0;
	                        					layer.open({content: lang_obj.addressfrom.Inserted_successfully,skin: 'msg',time: 2});
		    									setTimeout("emailshow()",1500);
		    						 		}
	    						 		}else{
	    						 			layer.open({content:data.msg,skin: 'msg'});
	    						 		}
	    						 	}
	    						 });

	    				}

    			}
    		}

        // 更改国家下拉框时
    		$("#selCountry").change(function (){
    			var c = $("#selCountry option:selected").val();
    			$.ajax({
    			 	type:"post",
    			 	url:"/home/usr_UsrPurchaseLine_listProvinces",
    			 	data:{"countryId":c},
    			 	dataType:"JSON",
    			 	success:function(data){
              // 更改国家后，省份自动更新 - 未匹配到则显示自动输入框
              $("#setpro").empty();
    			 		if(data.items.length>0){
		    			 		// $("#addregion1").css("display","block");
  					 			$.each(data.items,function(i,val){
  					 				$("#setpro").append("<option   value='"+val.pkey+"'>"+val.name+"</option>");
  					 			})
                  $("#province_upload").val( $("#setpro option:first-child").val() )
    			 		  }else{
		    			 		// $("#addregion1").css("display","none");
                  $("#setpro").append("<option   value='-1'>" + "No match province" + "</option>");
                  $("#province_upload").val( null );
                }
    			 	  }
    			 	});
    			})

        // 更改省市下拉框时
    		$("#setpro").change(function (){
    			var provSelectVal = $("#setpro option:selected").val();
          $("#province_upload").val(provSelectVal)
  			})

    		function AddBillingAddress(){
    			if($("#adname").val().trim()=="" || $("#adsurname").val().trim()=="" || $("#province_upload").val().trim()=="" || $("#ademailcode").val().trim()=="" || $("#adcity").val().trim()=="" || $("#adaddress").val().trim()=="" || $("#adphonenumber").val().trim()==""){
    				if( $("#adname").val().trim()==""){
    					$("#addname").css("display","block");
    				}else{
    					$("#addname").css("display","none");
    				}

    				if( $("#adsurname").val().trim()==""){
    					$("#addsurname").css("display","block");
    				}else{
    					$("#addsurname").css("display","none");
    				}

    				if( $("#ademailcode").val().trim()==""){
    					$("#addemail").css("display","block");
    				}else{
    					$("#addemail").css("display","none");
    				}

    				if( $("#province_upload").val().trim()==""){
    					$("#province_upload_tip").css("display","block");
    				}else{
    					$("#province_upload_tip").css("display","none");
    				}

    				if( $("#adcity").val().trim()==""){
    					$("#addcity").css("display","block");
    				}else{
    					$("#addcity").css("display","none");
    				}

    				if( $("#adaddress").val().trim()==""){
    					$("#addaddress").css("display","block");
    				}else{
    					$("#addaddress").css("display","none");
    				}

    				if( $("#adphonenumber").val().trim()==""){
    					$("#addphonenumber").css("display","block");
    				}else{
    					$("#addphonenumber").css("display","none");
    				}
    			}else{
          // 区分添加modify1 or modify时的字段 - 添加billing时是1，其余为0
          $("#addressType").val(1);
    			$("#inpkey").val("");
      		$("#inprowV").val("");
      		$.ajax({
    			 	type:"post",
    			 	url:"/home/usr_UsrPurchaseLine_AddToAddress",
    			 	data:$('#addressshow').serialize(),
    			 	dataType:"JSON",
    			 	success:function(data){
                		layer.open({content: lang_obj.addressfrom.Inserted_successfully,skin: 'msg',time: 2});
    					setTimeout("emailshow()",1500);
    			 	},
    		  });
    		}
      	 }


  	     function goBack(){
      			if(window.location.href.indexOf("jumpUrl") != -1){
  		 			window.location.href = window.location.origin + getParam();
  		 		}else{
  	    	 		window.location.href="/home/usr_UsrPurchaseLine_addmanagement";
  		 		}
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


        </script>
    </body>
</html>
