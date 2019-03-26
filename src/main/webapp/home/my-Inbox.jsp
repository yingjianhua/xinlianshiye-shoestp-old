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
                    <li class="home">
                        <a href="/" title="Home"><s:text name="Global.Home"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb1">
                        <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="Global.My_Account"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb2 root">
                        <a href="/home/usr_UsrMessages_inbox" title="Inbox"><s:text name="Global.My_Inbox"/>
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
                    user_obj.inbox_init()
                });</script>
                <h1 class="lib_user_title"><s:text name="Global.My_Inbox"/></h1>
                <div id="lib_user_inbox" class="clearfix" type="outbox">
                    <div class="inbox_menu">
                        <ul class="menu_title">
                            <li><a href="javascript:void(0);" hidefocus="true" class="current"><s:text
                                    name="my-Inbox.Inbox"/></a></li>
                            <li><a href="javascript:void(0);" hidefocus="true"><s:text name="my-Inbox.Outbox"/></a></li>
                            <li><a href="javascript:void(0);" hidefocus="true" class=""><s:text
                                    name="my-Inbox.Write_Pieces"/></a></li>
                            <li><a href="javascript:void(0);" hidefocus="true" class="hide"><s:text
                                    name="my-Inbox.Read_Inbox"/></a></li>
                            <li><a href="javascript:void(0);" hidefocus="true" class="hide"><s:text
                                    name="my-Inbox.Reply_Inbox"/></a></li>
                        </ul>
                        <div class="menu_content">
                            <div class="menu">
                                <div id="inbox_list" page="1" type="0">
                                    <ul class="msg_list">
                                    </ul>
                                    <div class="blank20"></div>
                                    <div class="turn_page">
                                    </div>
                                </div>
                            </div>
                            <div class="menu hide">
                                <div id="Outbox_list" page="1" type="1">
                                    <ul class="msg_list">

                                    </ul>
                                    <div class="blank20"></div>
                                    <div class="turn_page">
                                    </div>
                                </div>

                            </div>
                            <div class="menu inbox_form hide">
                                <div class="rows">
                                    <label><s:text name="Global.Theme"/>:</label>
                                    <span class="input"><input type="text" name="Subject" value="" class="form_input"
                                                               size="50" maxlength="100" notnull=""></span>
                                    <div class="clear"></div>
                                </div>
                                <div class="rows">
                                    <label><s:text name="Global.Content"/>:</label>
                                    <span class="input"><textarea name="Content" class="form_text"
                                                                  notnull=""></textarea></span>
                                    <div class="clear"></div>

                                </div>
                                <div class="rows">
                                    <label></label>
                                    <span class="input">
							<input type="button" class="submit_btn" id="submit_button"
                                   value="<s:text name='send_Out'/>">
						</span>
                                </div>
                            </div>
                            <div class="menu inbox_form hide" id="read">
                                <input type="hidden" class="id"/>
                                <div class="rows">
                                    <label><s:text name="Global.Sending_Time"/>:</label>
                                    <span class="input"><input type="text" value="" class="sendTime"
                                                               readonly/> </span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="Global.Theme"/>:</label>
                                    <span class="input"><input type="text" name="Subject" value=""
                                                               class="form_input title"
                                                               size="50" maxlength="100" readonly></span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="Global.Content"/>:</label>
                                    <span class="input"><textarea name="Content" class="form_text content"
                                                                  readonly></textarea></span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="my-Inbox.Reading_Time"/>:</label>
                                    <span class="input "><input type="text" value="" class="readTime"
                                                                readonly/> </span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="my-Inbox.Content"/>:</label>
                                    <span class="input"><textarea name="Content" class="form_text reply"
                                                                  readonly></textarea></span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="my-Inbox.Response_Time"/>:</label>
                                    <span class="input"><input type="text" value="" class="replyTime"
                                                               readonly/> </span>
                                </div>
                            </div>
                            <div class="menu inbox_form hide" id="reply">
                                <input type="hidden" class="id"/>

                                <div class="rows">
                                    <label><s:text name="Global.Sending_Time"/>:</label>
                                    <span class="input"><input type="text" value="" class="sendTime"
                                                               readonly/> </span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="Global.Theme"/>:</label>
                                    <span class="input"><input type="text" name="Subject" value=""
                                                               class="form_input title"
                                                               size="50" maxlength="100" readonly></span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="Global.Content"/>:</label>
                                    <span class="input"><textarea name="Content" class="form_text content"
                                                                  readonly></textarea></span>
                                </div>
                                <div class="rows">
                                    <label><s:text name="Global.Content"/>:</label>
                                    <span class="input"><textarea name="Content" class="form_text reply"
                                    ></textarea></span>
                                </div>
                                <input type="button" class="submit_btn" id="ButtonReply"
                                       value="<s:text name='my-Inbox.Reply'/>">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <index-bottom></index-bottom>
</div>
<%--<%@ include file="/home/template/new-foot.jsp" %>--%>
<div id="hj_top" style="opacity: 1;">
    <img src="/home/static/images/hj_top.png"/>
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:"#main"
    })
</script>
</body>
</html>
