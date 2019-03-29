<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="/home/v3/static/css/reg/index.css"/>
<<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta content="telephone=no" name="format-detection">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<title></title>
	<style>
		.content-warp{
			width: 645px;
			margin: 0 auto;
			margin-top: 200px;
			/* padding: 36px 32px; */
			color: #2d2d2d;
			background: #fff;
		}
		/* 头部样式 */
		.header{
			padding: 36px 32px 16px;
			border-bottom: 1px solid #efefef;
		}
		.header .logo{
			height: 28px;
			object-fit: contain;
			vertical-align: middle;
		}
		.header .header-tip{
			display: inline-block;
			margin-left: 12px;
			line-height: 28px;
			vertical-align: middle;
			font-size: 16px;
			color: #2d2d2d;
		}

		/* 中间样式 */
		.main{
			padding-top: 60px;
			padding-bottom: 70px;
			text-align: center;
			font-size: 14px;
			font-weight: bold;
			border-bottom: 1px solid #efefef;
		}
		.main .main-tips{
			/* padding: 30px 6px 30px 10px; */
			/* margin-bottom: 36px; */
			line-height: 23px;
			/* border: solid 1px #ddd; */
			/* box-shadow: 0px 2px 4px 0px	rgba(0, 24, 84, 0.19); */
		}
		.main .main-tips .icon{
			position: relative;
			top: -1px;
			width: 15px;
			height: 15px;
			object-fit: contain;
			vertical-align: middle;
		}
		.main .count-down-tips{
			display: inline-block;
			margin-top: 24px;
			cursor: pointer;
			font-size: 12px;
			font-weight: normal;
			color: #2d2d2d;
			text-decoration: none;
		}
		.main .count-down-tips:hover{
			text-decoration: underline;
		}
		.main .btn-complete:hover{
			background-color: #35c;
		}
	</style>
</head>
<body class="">
	<div class="content-warp">
		<div class="header">
			<img class="logo" alt="logo"
				src="data:image/png;base64,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" >

			<div class="header-tip">You have new notifications</div>
		</div>

		<div class="main">
			<div class="main-tips">
				<img class="icon" alt="!"
					src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA4RpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTM4IDc5LjE1OTgyNCwgMjAxNi8wOS8xNC0wMTowOTowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo5OWY3NWMwNy04ZWVhLTJlNDAtOGU4Ny1hYmJjOTQxODA2YjkiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RjUzQzcyOTY0NTgwMTFFOTkxRDRFRTgwMkY2NURBQUQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RjUzQzcyOTU0NTgwMTFFOTkxRDRFRTgwMkY2NURBQUQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6ZmY4MWU4MzEtMWMwYi1hYzQyLTkwOWQtOTE5M2E5M2I1Nzg2IiBzdFJlZjpkb2N1bWVudElEPSJhZG9iZTpkb2NpZDpwaG90b3Nob3A6NmE0MGNhNzEtNDUzZi0xMWU5LWFjYjMtZWE2Zjg2MTVmZjAwIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+9UNfnAAAAPhJREFUeNpifOrqwoAEGIHYFojtgVgdiPmB+CMQ3wTig0B8GIj/wxSzIGmUAOJyIFZhQAUgA8ygOBCIO4H4BUiCCUljD7pGsaXLbEEYSUgFqk4Cphnk1DKoDcQAfqh6RpBmOyBWxaXy7/t337AIg9TbgTQ7MpAHHJnw2UoAKII08+GS/f/j+288mvlAmj/hkv33Ha/mT6B4vg3Exthk3+bknMWj+T7I5v24ZEFxLDp3rgUO6f0gzYegtmMFjBycrFiEQeoPMULTtiQQdxOZUEBpvRSIn8OS53OowB0CGu/ANKJnDJBAEVKuUoFG4yeopkNQDM9VAAEGAIdIOjAyICZKAAAAAElFTkSuQmCC">

				Link has expired or is invalid, can't jump！
			</div>

			<a class="count-down-tips" href="/"><span id="count-down">5</span>s later， Automatic jump to mailbox verification page</a>
		</div>

	</div>
	<script>
		var countDownNum = 5;
		var timer = setInterval(function(){
			countDownNum--;
			document.getElementById("count-down").innerText = countDownNum;
			console.log(countDownNum)
			if(countDownNum <= 0){
				window.location.href="/"
				clearInterval(timer)
			}
		},1000)
	</script>
</body>
</html>
