<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0024)https://www.shoestp.com/ -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">
</head>

<body class="lang_en w_1200">
<%@ include file="/home/template/web-top.jsp" %>
<%@ include file="/home/template/new-header.jsp" %>

<div id="main" class="wide">
    <div id="lib_user" class="clearfix">
        <div id="lib_user_crumb" class="widget">
            <ul class="crumb_box clearfix">
                <li class="home">
                    <a href="/" title="Home"><s:text name="home"/>
                        <i></i>
                    </a>
                </li>
                <li class="crumb1">
                    <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="my_account"/>
                        <i></i>
                    </a>
                </li>
                <li class="crumb2 root">
                    <a href="/home/usr_UsrMessages_systemMessage" title="Inbox"><s:text name="system_messages"/>
                        <i></i>
                    </a>
                </li>
            </ul>
        </div>

        <%@ include file="/home/template/account/lib-user-menu.jsp" %>
        <style>
            .review-item {
                display: block;
                padding: 20px;
                color: #666;
                border-bottom: 1px dashed #ddd;
            }

            .review-item:last-child {
                border-bottom-width: 0;
            }

            .review-item .goods-pic {
                float: left;
                width: 100px;
                height: 100px;
                object-fit: contain;
            }

            .review-item .review-info-wrap {
                margin-left: 120px;
            }

            .review-item .review-info-wrap .review-info-header {
                position: relative;
                border-bottom: 1px dotted #ddd;
            }

            .review-item .review-info-wrap .review-star {
                width: 100px;
                height: 16px;
                margin-bottom: 15px;
                background: url("static/images/global/star.png") no-repeat;
                background-position: -100px -61px;
            }

            .review-item .review-info-wrap .review-star.star_01 {
                background-position: -80px -61px;
            }

            .review-item .review-info-wrap .review-star.star_02 {
                background-position: -60px -61px;
            }

            .review-item .review-info-wrap .review-star.star_03 {
                background-position: -40px -61px;
            }

            .review-item .review-info-wrap .review-star.star_04 {
                background-position: -20px -61px;
            }

            .review-item .review-info-wrap .review-star.star_05 {
                background-position: 0 -61px;
            }

            .review-item .review-info-header .review-time {
                position: absolute;
                right: 10px;
                bottom: 4px;
            }

            .review-item .review-content {
                padding: 10px;
            }

            .review-item .review-reply {
                padding: 10px;
                color: #999;
                border-radius: 6px;
            }

            .review-item .review-reply:before {
                content: "掌柜回复：";
                display: inline-block;
            }
        </style>
        <div id="lib_user_main">
            <script type="text/javascript">$(document).ready(function () {
                var obj = $("#sysmessage"),
                    page = obj.attr('page');
                $.ajax({
                    url: "/home/usr_UsrMessages_sysMessageList",
                    data: {
                        page: page
                    },
                    success: function (data) {
                        var html = '';
                        $("div.turn_page", obj).html("")
                        $.each(data.result.items, function (i, v) {
                            html += "     <li><i class='fl " + (v.status == 1 ? "read" : "") + "'></i><a class=\"fl\" href=\"javascript:void(0);\"  type='" + $(obj).attr("id") + "' id='" + v.id + "'\n" +
                                "                                                                  title='" + v.title + "'>" + v.title + "</a><span class=\"time fr\">" + v.sendTime + "</span>\n" +
                                "</li>";
                        })
                        $("ul", obj).html(html)
                    }

                })
                $("#sysmessage").on("click", "li a", function () {
                    $("#sysmessage").toggle(function () {
                        $("#read").toggle()
                    })
                    $.ajax({
                        url: "/home/usr_UsrMessages_viewMessage",
                        data: {
                            id: $(this).attr("id")
                        },
                        success: function (data) {
                            for (var key in data.result) {
                                var value = data.result[key];
                                if (value != null) {
                                    $("." + key, $("#read")).val(value)
                                }
                            }
                        }
                    })
                })
                $("#read .back").on("click", function () {
                    $("#read").toggle(function () {
                        $("#sysmessage").toggle()
                    })
                })
            });</script>
            <h1 class="lib_user_title"><s:text name="system_messages"/></h1>
            <div id="lib_user_inbox" class="clearfix">
                <div class="inbox_menu">
                    <div class="menu_content">
                        <div class="menu" id="sysmessage">
                            <div page="1" type="0">
                                <ul class="msg_list">
                                </ul>
                                <div class="blank20"></div>
                                <div class="turn_page">
                                </div>
                            </div>
                        </div>
                        <div class="menu inbox_form hide" id="read">
                            <span class="back"> <a href="javascript:void(0);"><s:text name="goBack"/></a></span>
                            <input type="hidden" class="id"/>
                            <div class="rows">
                                <label><s:text name="messages.send_time"/>:</label>
                                <span class="input"><input type="text" value="" class="sendTime"
                                                           readonly/> </span>
                            </div>
                            <div class="rows">
                                <label><s:text name="user.subject"/>:</label>
                                <span class="input"><input type="text" name="Subject" value="" class="form_input title"
                                                           size="50" maxlength="100" readonly></span>
                            </div>
                            <div class="rows">
                                <label><s:text name="user.content"/>:</label>
                                <span class="input"><textarea name="Content" class="form_text content"
                                                              readonly></textarea></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/home/template/new-foot.jsp" %>
<div id="hj_top" style="opacity: 1;">
    <img src="/home/static/images/hj_top.png"/>
</div>
</body>
</html>
