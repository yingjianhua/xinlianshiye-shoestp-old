<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<!-- Global site tag (gtag.js) - Google Analytics -->

<meta name="keywords" content="Terms conditions">
<meta name="description" content="Terms &amp; conditions">
<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
<link href="./static/css/global.css" rel="stylesheet" type="text/css">
<link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
<link href="./static/css/user.css" rel="stylesheet" type="text/css">
<link href="./static/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./static/css/animate.min.css">
<link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
<link rel="stylesheet" href="./static/css/color.css" type="text/css">
<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
<style>
.bo{
  font-weight: bold;
}
</style>

</head>
<body class="lang_en w_1200">

   <%@ include file="/home/template/web-top.jsp" %>

   <%@ include file="/home/template/new-header.jsp" %>

<!-- .new_header -->
<div id="main" class="wide">
<div class="blank20">
</div>
<div class="side_left fl">
	<div class="help_menu">

	</div>
</div>
<div class="side_right fr right_main">
	<div class="main_title">

	</div>
	<div class="main_content editor_txt">

	</div>
</div>
<div class="blank25">
</div>
</div>
﻿

<%@ include file="/home/template/new-foot.jsp" %>

</body>
<script type="text/javascript">


	var value = "";
	$(document).ready(function () {
		listAll();
		listmenu()
		mark()
	})
	function listmenu(){
		$.each(listpage,function(i,val){
			var str="<div class='help_title'>"
					+"<div >"+val.name+"</div><ul class='help_list'>";
					$.each(val.pages,function(j,jal){
						str=str+"<li>"
			 				+"<a href='/home/cnt_CntSglPageCategory_gosglpage?pkey="+jal.id+"' title='"+jal.title+"' id="+jal.id+">"+jal.title+"</a>"
			 				+"</li>";
					})
					 str=str+"</ul></div>";
					 	$(".help_menu").append(str);
		})


	}

	function listAll(){
		var pkey= "${pkey}";
		$.ajax({
			type:'post',
			async:false,
			url:'/home/cnt_CntSglPage_findpage',
			data:{"bean.pkey":pkey},
			dataType:'json',
			success:function(data){
				$(".main_title").append(data.result.title);
				$(".main_content.editor_txt").append(data.result.descrip);
			}
		})
	}
	function gup(name) {
	    var regexS = "[\\?&]"+name+"=([^&#]*)";  //匹配name参数对
	        var regex = new RegExp( regexS );
	        var results = regex.exec( window.location.href );//过滤超链接
	         if( results == null ) {
	             return "";
	   } else {
	          return results[1];
	   }
	 }
	function mark(){
		var id=gup('pkey')
		$("#"+id+"").addClass('bo');
	}
</script>

</html>