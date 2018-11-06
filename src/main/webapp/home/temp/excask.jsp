<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../static/js/jquery-1.7.2.min.js"></script>
    <script src="../../seller/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="../../seller/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" href="../static/css/xunpanPC.css">
		<link href="../static/css/global.css" rel="stylesheet" type="text/css">
    <link href="../static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="../static/css/user.css" rel="stylesheet" type="text/css">
    <link href="../static/css/style.css" rel="stylesheet" type="text/css">
    	<script type="text/javascript" src="./static/js/layer.js"></script>
<link rel="stylesheet" href="./static/css/layer.css" type="text/css">
</head>
<body class="w_1200">
<div id="main" class="wide">
		<style>
				.layui-table,.layui-table th{
					text-align: center;
				}
				.layui-table select{
					width: 110px;
					padding: 0 5px;
				}
				.clearfloat:after{display:block;clear:both;content:"";visibility:hidden;height:0}
				 .clearfloat{zoom:1}
				 .fl{
					 	float: left;
				 }
				 .fr{
					 float: right;
				 }
		</style>
	<div id="lib_user" class="clearfix">
			<div id="lib_user_crumb" class="widget">
					<ul class="crumb_box clearfix">
							<li class="home">
									<a href="https://www.shoestp.com/" title="Home">Home
											<i></i>
									</a>
							</li>
							<li class="crumb1">
									<a href="https://www.shoestp.com/account/" title="My Account">My Account
											<i></i>
									</a>
							</li>
							<li class="crumb2 root">
									<a href="https://www.shoestp.com/account/review/" title="My Inquiry/RFQ">My Inquiry/RFQ
											<i></i>
									</a>
							</li>
					</ul>
			</div>
			<div id="lib_user_menu">
					<h3 class="title">My Account</h3>
					<ul>
							<li>
									<a href="https://www.shoestp.com/account/">Basic Information</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/orders/">My Orders</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/review/">My Reviews</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/favorite/">My Favorite</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/coupon/">My Coupons</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/address/">Manage Address Book</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/address/">My Inquiry/RFQ</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/setting/">Account Settings</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/inbox/">My Inbox</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/message/">System Message</a>
							</li>
							<li>
									<a href="https://www.shoestp.com/account/logout.html">Sign Out</a>
							</li>
					</ul>
			</div>
			<div id="lib_user_main">
					<!-- <h1 class="lib_user_title">My Inquiry/RFQ</h1> -->
					<div class="lib_user_title clearfloat">
							<span class="fl">My Inquiry/RFQ</span>
							<span class="fr"><img src="../static/images/ico/icon-edit.png" alt="" style="margin-right: 5px">Release inquiry</span>
					</div>
					<div id="lib_user_review">
							<table class="layui-table" lay-skin="line">
									<colgroup>
										<col width="50">
										<col width="200">
										<col width="50">
										<col width="200">
										<col width="200">
										<col width="50">
										<col width="50">
										<col width="100">
									</colgroup>
									<thead>
										<tr>
											<th><input type="checkbox" name="" title="" lay-skin="primary" lay-filter=""></th>
											<th>title</th>
											<th>picture</th>
											<th>inquiry time</th>
											<th>supplier</th>
											<th>Quantity</th>
											<th>communication</th>
											<th>operate</th>
										</tr> 
									</thead>
									<tbody id="lineShow">
										
									</tbody>
								</table>
								<!-- 分页 -->
								<div class="blank20"></div>
							<div id="turn_page">
									<li class="page-first">
											<font class="page_noclick">
												<em class="icon_page_prev"></em>
												Previous
											</font>
										</li>
										<li>
											<font class="page_item_current">1</font>
										</li>
										<li>
											<a href="" class="page_item">2</a>
										</li>
										<li>
												<a href="" class="page_item">3</a>
										</li>
										<li>
												<a href="" class="page_item">4</a>
										</li>
										<li>
												<a href="" class="page_item">5</a>
										</li>
										<li class="page_last">
											<a href="" class="page_button">
												Next
												<em class="icon_page_next"></em>
											</a>
											</li>
							</div>
					</div>
			</div>
	</div>
</div>



<script src="../static/js/index.js">

</script>
<script type="text/javascript">
   window.onload=function(){
	   $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/pdt_PdtConsult_selAskForPur',
			dataType:'json',
			success:function(data){
			  $.ajax({
					type:'post',
					url:'http://'+window.location.host+'/home/pdt_PdtConsultServe_selectByPur',
					dataType:'json',
					success:function(askData){
					
					$("#lineShow").html("");
					$.each(data.items,function(i,dataitem){
					var selectSup='';		
					$.each(askData.items,function(i,askitem){
					if(askitem.purchase==dataitem.purchase&&askitem.consult==dataitem.pkey){
						selectSup="<option value ='"+askitem.supplier.pkey+"'>"+askitem.supplier.name+"</option>"+selectSup;
						}
				  	 });		
				var pic='';
				if(dataitem.product==null){
				var picArray=dataitem.image.split(',');	
				pic=picArry[0];
				}else{
				pic=dataitem.product.picture;
				}		
				$("#lineShow").append("<tr>"
				+"<td><input type='checkbox' name='' title='' lay-skin='primary'></td>"
				+"<td>"+dataitem.name+"</td>"
				+"<td>"
				+"<img src='"+pic+"' alt='' style='width: 36px;height: 36px;'>"
				+"</td>"
				+"<td>"+dataitem.time+"</td>"
				+"<td>"
				+"<select id='sel"+dataitem.pkey+"'>"+selectSup+"</select>"
			    +"</td>"
			    +"<td>"+dataitem.count+"</td>"
			    +"<td>"
			    +"<span><img src='../static/images/ico/icon-message.png' alt=''></span>"
			    +"</td>"
			    +"<td>"
			    +"<a href='http://"+window.location.host+"/home/asklist/askDec.html?askid="+dataitem.pkey+"'><span><img src='../static/images/ico/icon-look-order.png' alt=''></span></a>"
			    +"<span style='margin-left: 10px;'><img src='../static/images/ico/icon-delete.png' alt='' onclick=del("+dataitem.pkey+","+dataitem.rowVersion+")></span>"
			    +"</td>"
			    +"</tr>");
					});
					
					}
				})
			
			}
		})
   }
   function del(pkey,rowVersion){
	   if($('#sel'+pkey).children().length<=0){
	   if(confirm("确认删除？")){	   
	   $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/pdt_PdtConsult_del?pkey='+pkey+'&rowVersion='+rowVersion+'',
			dataType:'json',
			success:function(askData){
			console.log(askData.success);
			if(askData.success==true){
			layer.msg("删除成功", {icon: 1});
			location.reload();
			}else{	
			layer.msg("删除失败", {icon: 2});
			}
		  }		
	   })
	   }
	   }else{
		   layer.msg("供应商已接单无法删除!", {icon: 2});
	   }
	   
   }
</script>
</body>
</html>