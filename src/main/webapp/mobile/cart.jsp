<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<input type="button" id="btn" value="aaaaaaaa" >
<div id="bb"></div>
<p class="ss"></p>


<script type="text/javascript" src="./static/js/jquery-min.js"></script>
<script type="text/javascript">
$("#btn").click(function (){
	//window.location.href="/home/usr_UsrCart_cartToDeliveryInformation?strSpec=1,2";getCartDate
	window.location.href="/home/usr_UsrCart_getCartDate";
	var odrOrder={
			_name:"1",
			_main:22,
			_orderNum:"1111",
			_delivery:"222"
	}
	 /*  $.post("/home/usr_UsrCart_InsusrCart",{singleSpecNum:"1",supplierNum:1,qty:120},function(){
		
	}) */ 
})
	$("#btn").on("tap",function (){
		var strSpec="1,4"
	 	$.post("/home/usr_UsrCart_cartDateVerify",{strSpec:strSpec},function (data){
	 		var da = JSON.parse(data);
			if(da==null||da=="[]"){
				return;
			}
			 console.log(da);
			 //规格验证是否为同一家
			 /*if(da.data=="no"){
				alert("商品不相同");
			}else{
				alert(666);	
			} */	
			var ss=$("p");
			var $sc=ss;
			//查询所有颜色
			/* $(da.specColor).each(function (index,element){
				//console.log(element.pics);			
				//$sc.text(element.pics);
				$("#bb").append("<p class='ss'>"+ element.pics +"</p>");
				//$("#bb").append($sc);
				//console.log(ss.text());
			}) */
			if(da.data==1){
				window.location.href="/home/usr_UsrCart_cartToDeliveryInformation?strSpec=1,4";
			}else{
				alert("不为同一商店无法提交");
			}
		}) 
		
	})

	$("#btn1").on("click",function (){
		
		getSpecSize();
	})
	function getSpecSize(){
		$.post("/home/usr_UsrCart_getSpecSize",{singleSpecNum:"1"},function (data){
			var da = JSON.parse(data);
			if(da==null||da=="[]"){
				return;
			}
			$(da.specSize).each(function (index,element){
				$("#bb").append("<p class='ss'>"+ element.size +"</p>");
				
			})
		})
	}
</script>
</body>
</html>