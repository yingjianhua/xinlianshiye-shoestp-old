<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link href="./static/css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <script src="./static/js/iconfont.js"></script>
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>

    <style type="text/css">
        .clearfix:after {
            content: "";
            height: 0;
            line-height: 0;
            display: block;
            visibility: hidden;
            clear: both;
        }

        .clearfix {
            zoom: 1;
        }

        /* 报名按钮 */

        .icon-white-baoming {
            width: 24px;
            height: 31px;
        }

        .icon-baoming-copy {
            width: 31px;
            height: 37px;
        }

        /* 白色定位 灰色定位*/

        .icon-location-copy {
            width: 45px;
            height: 55px;
            position: absolute;
            top: 38px;
            right: 32px;
        }

        /* 正在报名 */

        .now-signUp {
            width: 1200px;
            margin: 0 auto;
            height: 451px;
            background-color: #a1c6e0;
            box-sizing: border-box;
        }

        .now-signUp-title {
            text-align: center;
            height: 115px;
        }

        .now-signUp-content {
            padding: 0 43px;
        }

        .now-signUp-content-left {
            float: left;
            position: relative;
            background: url('/home/static/images/show2.png') 100% 100% no-repeat;
            width: 518px;
            height: 279px;
        }

        .now-signUp-content-right {
            float: right;
        }

        .now-signUp-content-right>div {
            position: relative;
            width: 548px;
            height: 128px;
        }

        .now-signUp-content-right>div:first-child {
            background: url('/home/static/images/show3.png') 100% 100% no-repeat;
        }

        .now-signUp-content-right>div:nth-child(2) {
            background: url('/home/static/images/show4.png') 100% 100% no-repeat;
        }

        .signUp-shadow {
            -moz-box-shadow: 0px 5px 10px #95B6D8;
            -webkit-box-shadow: 0px 5px 10px #95B6D8;
            box-shadow: 0px 5px 10px #95B6D8;
        }

        .red-sign-button {
            width: 125px;
            height: 31px;
            background-color: #b84a5f;
            border-radius: 15px;
            padding: 0 18px;
            box-sizing: border-box;
            position: absolute;
        }

        .sign-button1,
        .sign-button2,
        .sign-button3 {
            width: 150px;
            height: 37px;
            box-sizing: border-box;
            border-radius: 18px;
            position: absolute;
            padding: 0 18px;
        }

        .sign-button1,
        .sign-button3,
        .exhibition-list .exhibition-item:nth-child(even) {
            background-color: #ffffff;
        }

        .sign-button2 {
            background-color: #ffad42;
        }

        /* 展会 */

        .exhibition {
            height: 1800px;
        }

        .exhibition-title {
            height: 140px;
            text-align: center;
            min-width: 1200px;
        }

        .exhibition-list .exhibition-item hr {
            opacity: 0.15;
            width: 151px;
            height: 1px;
            margin-top: 20px;
        }

        .exhibition-list .exhibition-item:nth-child(even) .exhibition-time,
        .exhibition-list .exhibition-item:nth-child(even) .exhibition-index {
            color: #ffad42;
        }

        .exhibition-list .exhibition-item:nth-child(odd) hr {
            background-color: rgba(255, 255, 255, 0.15);
        }

        .exhibition-list .exhibition-item:nth-child(even) hr {
            background-color: rgba(255, 173, 66, 0.15);
        }

        .exhibition-list {
            width: 1200px;
            margin: 0 auto;
        }

        .exhibition-list .exhibition-item {
            box-sizing: border-box;
            width: 400px;
            height: 400px;
            padding: 43px 36px 30px 30px;
            position: relative;
            float: left;
        }

        .exhibition-time {
            font-size: 29px;
            line-height: 39px;
            height: 39px;
        }

        .exhibition-index {
            height: 76px;
            font-size: 58px;
            line-height: 76px;
        }

        .exhibition-list .exhibition-item:nth-child(odd) ul,
        .exhibition-index,
        .exhibition-time,
        .red-sign-button a {
            color: #ffffff;
        }

        .exhibition-list .exhibition-item:nth-child(even) li {
            color: #ffad42;
        }

        .exhibition-list .exhibition-item:nth-child(even) li span {
            color: #262626;
        }

        .exhibition-list .exhibition-item ul {
            margin-top: 20px;
        }

        .exhibition-list .exhibition-item ul,
        .exhibition-list .exhibition-item li {
            list-style-type: disc;
            font-size: 14px;
        }

        .exhibition-list .exhibition-item ul {
            padding-left: 20px;
        }
    </style>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<%@ include file="/home/template/web-top.jsp" %>
<div id="app">
    <index-top></index-top>
</div>
    <div class="wide">
        <div style="width:100%;margin:0 auto;background: #fbfbfb;overflow: hidden;">
            <!-- <svg class="icon-baoming" aria-hidden="true">
            <use xlink:href="#icon-baoming-copy"></use>
        </svg> -->
            <div style="width:1200px;margin:0 auto;min-width:1200px;">
                <img alt="" class="lazy" src="/home/static/images/show1.png" style="width: 100%;" />
            </div>
            <!-- 正在报名 start -->
            <div class="now-signUp">
                <div class="now-signUp-title" style="color: #ffffff;">
                    <div style="font-size: 25px;padding-top:35px">Registering
                        <div style="font-size: 14px;">正在报名中</div>
                    </div>
                </div>
                <div class="now-signUp-content clearfix">
                    <div class="now-signUp-content-left signUp-shadow">
                        <div style="position: absolute;right:50px;bottom:105px;text-align: center;font-size:17px">
                            <div>美国拉斯维加斯展</div>
                            <div style="color: #4c4c4c;">2018.8.12-8.15</div>
                            <div style="color: #4c4c4c;">2019.2月</div>
                        </div>
                        <div class="red-sign-button clearfix" style="bottom: 50px; right: 50px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="margin-right:10px;float: left;line-height: 31px;text-align: center;">我要报名</div>
                                <svg class="icon-white-baoming" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="now-signUp-content-right">
                        <div style="margin-bottom:23px;" class="signUp-shadow">
                            <div style="position: absolute;left:300px;top:15px;text-align: left;font-size:17px">
                                <div>广交会</div>
                                <div style="color: #4c4c4c;">2018.10.31-11.4</div>
                            </div>
                            <div class="red-sign-button clearfix" style="bottom: 15px; right: 40px;">
                                <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                    <div style="margin-right:10px;float: left;line-height: 31px;text-align: center;">我要报名</div>
                                    <svg class="icon-white-baoming" aria-hidden="true">
                                        <use xlink:href="#icon-baoming"></use>
                                    </svg>
                                </a>
                            </div>
                        </div>
                        <div class="signUp-shadow">
                            <div style="position: absolute;left:300px;top:20px;text-align: left;font-size:17px">
                                <div>意大利加达国际鞋展</div>
                                <div style="color: #4c4c4c;">2019.1月</div>
                            </div>
                            <div class="red-sign-button clearfix" style="bottom: 15px; right: 40px;">
                                <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                    <div style="margin-right:10px;float: left;line-height: 31px;text-align: center;">我要报名</div>
                                    <svg class="icon-white-baoming" aria-hidden="true">
                                        <use xlink:href="#icon-baoming"></use>
                                    </svg>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 正在报名 end  -->
            <!-- 展会一览 start -->
            <div class="exhibition">
                <div class="exhibition-title">
                    <div style="font-size:25px;padding-top:40px;color:#7b7b7b;">Exhibition Overview
                        <div style="font-size: 14px;">展会一览</div>
                    </div>
                </div>
                <div class="exhibition-list clearfix">
                    <div class="exhibition-item" style="background-color: #a1d5e0;">
                        <div class="exhibition-time">
                            <span>January</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            01
                        </div>
                        <hr />
                        <ul>
                            <li>
                                <span>意大利加达国际鞋展</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location-copy"></use>
                        </svg>
                        <div class="sign-button1 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #a1d5e0;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming-copy"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item">
                        <div class="exhibition-time">
                            <span>February</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            02
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>美国拉斯维加斯展</span>
                            </li>
                            <li>
                                <span>意大利米兰国际春季皮革制品展览会</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location"></use>
                        </svg>
                        <div class="sign-button2 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #ffffff;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item" style="background-color: #b5dea6">
                        <div class="exhibition-time">
                            <span>March</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            03
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>香港亚太皮革箱包展</span>
                            </li>
                            <li>
                                <span>俄罗斯莫斯科国际鞋类、箱包展会</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location-copy"></use>
                        </svg>
                        <div class="sign-button3 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #97de7d;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming-copy-copy"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item">
                        <div class="exhibition-time">
                            <span>April</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            04
                        </div>
                        <hr />
                        <ul>
                            <li>
                                <span>日本东京春秋季时尚鞋展</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location"></use>
                        </svg>
                        <div class="sign-button2 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #ffffff;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item" style="background-color: #a1d5e0;">
                        <div class="exhibition-time">
                            <span>May</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            05
                        </div>
                        <hr />
                        <ul>
                            <li>
                                <span>广交会</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location-copy"></use>
                        </svg>
                        <div class="sign-button1 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #a1d5e0;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming-copy"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item">
                        <div class="exhibition-time">
                            <span>June</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            06
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>意大利加达春秋国际鞋展</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location"></use>
                        </svg>
                        <div class="sign-button2 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #ffffff;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item" style="background-color: #b5dea6">
                        <div class="exhibition-time">
                            <span>July</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            07
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>越南国际鞋类及皮革展</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location-copy"></use>
                        </svg>
                        <div class="sign-button3 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #97de7d;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming-copy-copy"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item">
                        <div class="exhibition-time">
                            <span>August</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            08
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>第二十四届印度新德里皮革及鞋机鞋材博览会</span>
                            </li>
                            <li style="margin-bottom: 25px">
                                <span>美国拉斯维加斯春秋季鞋展</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location"></use>
                        </svg>
                        <div class="sign-button2 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #ffffff;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item" style="background-color: #67bacc">
                        <div class="exhibition-time">
                            <span>September</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            09
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>越南国际鞋类及皮革展</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location-copy"></use>
                        </svg>
                        <div class="sign-button3 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #67bacc;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming-copy-copy1"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item">
                        <div class="exhibition-time">
                            <span>October</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            10
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>巴西圣保罗国际安全及防护展览会</span>
                            </li>
                            <li style="margin-bottom: 25px">
                                <span>日本东京国际鞋类展览会</span>
                            </li>
                            <li style="margin-bottom: 25px">
                                <span>土耳其国际成品鞋及箱包配饰展AYMOD</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location"></use>
                        </svg>
                        <div class="sign-button2 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #ffffff;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item" style="background-color: #9ce1b9">
                        <div class="exhibition-time">
                            <span>November</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            11
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 25px">
                                <span>广交会</span>
                            </li>
                            <li style="margin-bottom: 25px">
                                <span>法国劳保展（两年一次）</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location-copy"></use>
                        </svg>
                        <div class="sign-button3 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #97de7d;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming-copy-copy"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="exhibition-item">
                        <div class="exhibition-time">
                            <span>December</span>
                            <span>2019</span>
                        </div>
                        <div class="exhibition-index">
                            12
                        </div>
                        <hr />
                        <ul>
                            <li style="margin-bottom: 30px">
                                <span>暂无</span>
                            </li>
                        </ul>
                        <svg class="icon-location-copy" aria-hidden="true">
                            <use xlink:href="#icon-location"></use>
                        </svg>
                        <div class="sign-button2 clearfix" style="bottom: 30px; right: 42px;">
                            <a href="http://jsform3.com/f/5b0cec7175a03c1a4db80ae7" target="_blank">
                                <div style="float: left;line-height: 37px;text-align: center;color: #ffffff;padding:0 15px 0 12px;">预约报名</div>
                                <svg class="icon-baoming-copy" aria-hidden="true">
                                    <use xlink:href="#icon-baoming"></use>
                                </svg>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 展会一览 end -->
            <!-- 底部 -->
            <div style="width:100%;margin:0 auto;min-width:1200px;">
                <img src="/home/static/images/show5.png" alt="" style="width:100%;vertical-align: middle;">
            </div>
        </div>
        <index-bottom></index-bottom>
    </div>
    <%--<jsp:include page="template/new-foot.jsp"></jsp:include>--%>

<div id="hj_top" style="opacity: 0; bottom: 10%;">
    <img src="./static/images/hj_top.png">
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:"#app"
    })
</script>
</body>
</html>
