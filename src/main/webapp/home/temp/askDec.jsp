<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../static/js/jquery-3.2.1.js"></script>
    <link rel="stylesheet" type="text/css"  href="../../seller/static/admin/layui/css/layui.css"/>
    <script src="../../seller/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css"  href="../../seller/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" href="../static/css/xunpanPC.css">
    <style>
    	
    </style>
</head>
<body class="p15">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>询盘信息</legend>
</fieldset>

	<header class="ask-header">
      <div>
          <img src="../static/images/image/RFQ.png" alt="">
      </div>
      <div>
          <h2 style="font-size:22px">Request for Quotation</h2>
          <p class="color666">One Request，Multiple Quotes</p>
      </div>
  </header>
  <div>
      <h1 class="title_h3 tc p15" style="margin-bottom: 15px; id="category">latest men casual waxed buffalo leather dress shoes</h1>
  </div>

	<div class="layui-upload-drag fl" id="productLogo" style="margin-left: 15px;"></div>
	  
	<div class="goodsAsk-details ml190">
		<div class="goodsAsk-details-item">
		    <label class="goodsAsk-details-name">Articl no:</label>
		    <div class="goodsAsk-details-content">
		    	<div id="articlNo"></div>
		    </div>
		 </div>
		 <div class="goodsAsk-details-item">
		    <label class="goodsAsk-details-name">Quantity:</label>
		    <div class="goodsAsk-details-content">
		      <div id="quantity"></div>
		    </div>
		 </div>
		 <div class="goodsAsk-details-item">
		    <label class="goodsAsk-details-name">Supplier:</label>
		    <div class="goodsAsk-details-content">
		      <div id="supplier"></div>
		    </div>
		 </div>
	</div>
	
	 <div class="tr" id="time" style="color: #777;"></div>
	 
	 <div class="text_pic-wrap">
		 <div class="textarea" id="desc"></div>
		 
		 <hr />
		 
		 <div id="produImg"></div>
	 </div>
	 
<form id="selpkey" style="display:none;">
	<input type="text"  name="bean.pkey" id="pkey" />
</form>
<script src="../static/js/index.js">

</script>
<script type="text/javascript">
function getUrlParam(name)
{
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
var r = window.location.search.substr(1).match(reg);  //匹配目标参数
if (r!=null) return unescape(r[2]); return null; //返回参数值
} 

   window.onload=function(){
		var askid=0;
		if(getUrlParam('askid')==null){
		askid=0;
		}else{
		askid=getUrlParam('askid');
		}
	   $("#pkey").val(askid);	
	   $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/pdt_PdtConsult_selAskForPkey',
			dataType:'json',
			data:$("#selpkey").serialize(),
			success:function(data){
			console.log(data);
			$('#produImg').html("");
			$('#productLogo').html("");
			$("#supplier").html(data.items[0].count);
			$("#articlNo").html(data.items[0].title);
			$("#quantity").html(data.items[0].pronum);
			$("#time").html(data.items[0].time);
			$("#desc").html(data.items[0].content);
			$("h1").html(data.items[0].category.name);
			//$("#productLogo").html("<img src='../../../"+ data.items[0].product.picture +"' alt='' class='layui-upload-img' width='150' height='150' >");
			$("#productLogo").html("<img src='../../../"+ data.items[0].product.picture +"'  alt='' class='layui-upload-img' width='150' height='150' >");
			var photo=data.items[0].photo.split(',');
			$.each(photo,function(i,item){
				//$('#produImg').append('<img src="../../../'+ item +'" alt="" class="layui-upload-img" width="100" height="100" >')
			$('#produImg').append('<img src="../../../'+ item +'"  alt="" class="layui-upload-img" width="100" height="100" ><img src="http://chuantu.biz/t6/347/1532399002x-1404817724.jpg"  alt="" class="layui-upload-img" width="100" height="100" >')
			});
			}
			  
		})
   }

</script>
</body>
</html>