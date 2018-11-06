<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>           
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script type="text/javascript" src="/mobile/static/js/jquery-min.js"></script>
    	    <link href="/mobile/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/mobile/static/css/style.css" rel="stylesheet" type="text/css">
    <style type="text/css">
    .project-box {
        background-color: #f9f9f9;
        width: 100%;
    }
   
        .project-box {
            height: auto;
        }
        .project-none-item img,
        .project-img img {
            width: 100%;
        }
        .project-item {
            width: 100%;
            height: auto;
            margin: 0 10px 10px 0;
        }
        .project-img {
            width: 100%;
        }
        .project-title {
            width: 80%;
            margin: 10px auto;
        }
        .project-main {
            width: 80%;
            margin: 0 auto;
        }
        .project-info {
            height: 85px;
        }
        .project-info>div:first-child {
            margin-top: 0 !important;
            height: 50px !important;
        }
    
</style>
</head>
<body>
<%@include file="/mobile/template/header.jsp" %>

    <div class="project-box">
        <div class="project-title"><!-- The Magazine List --><s:text name="Magazine.list"/></div>
    
            <div class="project-main">
            <div class="project-content clearfix">&nbsp;
           <c:forEach items="${map['list']}" var="p">
			<div class="project-item">
                <a href="/home/cnt_CntMagazine_content?contenturl=${p.contenturl}" target="_blank">
                    <div class="project-img">
                        <img src="${envConfig.imageBaseUrl}${p.specialPictures}" alt="">
                    </div>
                    <div class="project-info">
                        <div style="height: 66px;">
                            <div style="font-size:15px;color:#333">${p.name}</div>
                            <div style="font-size:12px;color:#999999">${p.content}</div>
                        </div>
                        <div style=" display: flex;justify-content: space-between;align-items: center;height: 34px;border-top: 1px solid #eeeeee;">
                            <div>
                                <img src="/home/static/themes/default/mobile/images/off-label.png" alt="" class="icon-label"/>
                                <span class="num">${p.cycle}</span>
                            </div>
                            <div>
                                <img src="/home/static/themes/default/mobile/images/time.png" alt="" />
                                <span style="font-size: 14px;color:#999999;">${p.time}</span>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
          </c:forEach>
            </div>
        </div>
    </div>
    	<%@ include file="/mobile/template/foot_menu.jsp" %>
</body>
<script>
//     var noneItem =
//         `<div class="project-none-item">
//             <img src="/home/static/themes/default/mobile/images/none-project.png" alt="">
//         </div>`
//     $(".project-content").html(item)
 //   $(".project-content").append(noneItem)
//     $('.project-content .project-item').hover(function () {
//         $(this).find('.project-info').find('.icon-label').attr("src", label.onlabel)
//     }, function () {
//         $(this).find('.project-info').find('.icon-label').attr("src", label.offlabel)
//     })

//     function autoWidth() {
//         var $width = window.screen.width;
//         if ($width <= 450) {
//             $(".project-item a").attr('target', '');
//         } else if ($width > 450) {
//             $(".project-item a").attr('target', '_blank');
//         }
//     }
//     autoWidth();
//     $(window).resize(function () {
//         autoWidth();
//     })
</script>
</html>