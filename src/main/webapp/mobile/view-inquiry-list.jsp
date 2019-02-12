<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->
    <script type="text/javascript" async="" src="./static/js/js">
    </script>
    <script type="text/javascript" async="" src="./static/js/tracking.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/conversion_async.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/analytics.js">
    </script>
    <!-- End Google Tag Manager -->
    <link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-min.js">
    </script>
    <script type="text/javascript" src="./static/js/global.js">
    </script>
    <script type="text/javascript" src="./static/js/rye-touch.js">
    </script>
    <script type="text/javascript" src="./static/js/global(1).js">
    </script>
    <link href="./static/css/style(1).css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/en.js">
    </script>
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/user.js">
    </script>
    <script src="./static/js/saved_resource">
    </script>
    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <link href="/home/static/themes/default/mobile/css/layer-mobile.css" rel="stylesheet" type="text/css">
    <script src="/home/static/themes/default/mobile/js/layer-mobile.js"></script>
    <script type="text/javascript" async="" src="/home/static/themes/default/mobile/js/qs.js"></script>

    <style type="text/css">
        #livechat-compact-container {
            transform: translateY(-50%) !important;
            -webkit-transform: translateY(-50%) !important;
        }
    </style>
</head>

<body>



    <style type="text/css">
        #header_fix {
            overflow: visible;
        }

        .header_top {
            width: 100%;
            max-width: 100%;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 100;
        }
    </style>
    <%@ include file="/mobile/template/header.jsp" %>
    <div class="wrapper">
        <div id="user">
            <div class="crumb clean">
                <a href="/">
                    <span class="icon_crumb_home">
                    </span>
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/usr_UsrPurchase_userIndex">
                    <!-- My Account -->
                     <s:text name="my_account"/>
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/usr_UsrConsult_listView">
                    <!-- Inquiry list -->
                    <s:text name="inquiry"/>
                </a>
            </div>
            <div class="user-inquiry-list">
                <style>
                    /* 询盘列表 begin */
                    .clearfloat:after {
                        display: block;
                        clear: both;
                        content: "";
                        visibility: hidden;
                        height: 0
                    }

                    .clearfloat {
                        zoom: 1
                    }
                    .user-inquiry-list .inquiry-list{
                        margin: 0 0.625rem;
                    }
                    .user-inquiry-list .inquiry-list ul{
                        margin: 5% 0;
                    }
                    .user-inquiry-list .inquiry-list ul li{
                        margin-top: 5%;
                    }
                    /* .user-inquiry-list .inquiry-list ul li input[type=checkbox]{
                        zoom: 150%;
                    } */
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-img {
                        width: 26%;
                        font-size: 0;
                        border: 1px solid #dedede;
                        margin-left: 2%;
                        overflow: hidden;
                    }
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-info{
                        width: 65%;
                        margin-left: 2%;
                    }
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-name p{
                        line-height: 0.9rem;
                        font-size: 12px;
                        width: 80%;
                        overflow: hidden;
                        display: -webkit-box;
                        -webkit-box-orient: vertical;
                        -webkit-line-clamp: 4;
                        -ms-text-overflow: ellipsis;
                        text-overflow: ellipsis;
                        white-space: normal;
                        word-wrap:break-word;
                        word-break:break-all;
                        word-wrap:break-word;
                        position: relative;
                        max-height: 3.5rem;
                    }
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time{
                        line-height: 0.9rem;
                    }
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time p{
                        line-height: 180%;
                    }
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time p,.user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time span{
                        color: #919191;
                    }
                    .user-inquiry-list .inquiry-list ul li .inquiry-list-info select{
                        border-radius:5px;
                        -webkit-border-radius:5px;
                        -moz-border-radius:5px;
                        padding: 1% 0;
                        width: 60%;
                        margin: 1% 0;
                    }
                    .user-inquiry-list .inquiry-list ul li i{
                        display: inline-block;
                    }
                    .user-inquiry-list .inquiry-list ul li .icon-close{
                        width: 14px;
                        height: 14px;
                        overflow: hidden;
                        background: url(./static/images/nav_close.png) center center no-repeat;
                        -webkit-background-size:  14px;
                        background-size:  14px;
                        margin: 0 3px;
                    }
                    .user-inquiry-list .inquiry-list ul li .icon-messages{
                        background: url(./static/images/icon_review.gif) center center no-repeat;
                        width: 1.3125rem;
                        height: 1.125rem;
                    }
                    .user-inquiry-list .inquiry-list .more-button{
                        width: 5rem;
                        height: 1.875rem;
                        background-color: #d7d7d7;
                        color: #fff;
                        line-height: 1.875rem;
                        text-align: center;
                        border-radius: 10px;
                        margin: 5% auto;
                    }
                    /* 询盘列表 end */
                </style>


                <div id="myinquirylist">

                <div class="inquiry-list">
                    <ul>
                        <li class="clearfloat"  v-for='inquirylistdd in inquirylist[0].result.items'>
                            <input type="checkbox" class="fl">
                            <div class="inquiry-list-img fl">
                            	<a :href="/mobile/66666.jsp?inquiry_id=inquirylistdd.id">
	                                <img :src="'${envConfig.imageBaseUrl}'+inquirylistdd.image" alt="" v-if="inquirylistdd.image">
	                                <img src="./static/images/pic_180_03.jpg"  alt="" v-if="!inquirylistdd.image">
                                </a>
                            </div>
                            <div class="fl inquiry-list-info">
                                <div class="inquiry-list-name clearfloat">
                                    <p class="fl">
                                    	<a :href="/mobile/66666.jsp?inquiry_id=inquirylistdd.id">
                                           {{inquirylistdd.title}}
                                        </a>
                                    </p>
                                    <i class="icon-close fr"></i>
                                </div>
                                <select name="" id="">
                                    <option value=""  v-for='inquirylistcc in inquirylistdd.relations'>{{inquirylistcc.supplierName}}</option>
                                </select>
                                <div class="inquiry-list-time clearfloat">
                                    <p class="fl">{{inquirylistdd.createTime}}</p>
                                    <i class="icon-messages fr"></i>
                                </div>
                            </div>
                        </li>
                        <li class="clearfloat">
                            <input type="checkbox" class="fl">
                            <div class="inquiry-list-img fl">
                                <img src="./static/images/pic_180_03.jpg" alt="">
                            </div>
                            <div class="fl inquiry-list-info">
                                <div class="inquiry-list-name clearfloat">
                                    <p class="fl">
                                            DABOWEN LADIES FASHION MID-HIGH RIVET SHOES LACE-UP YOUTH TREND CASUAL FLAT LOAFER LOAFER LOAFER
                                    </p>
                                    <i class="icon-close fr"></i>
                                </div>
                                <select name="" id="">
                                    <option value="">ASHOP</option>
                                    <option value="">BSHOP</option>
                                    <option value="">CSHOP</option>
                                    <option value="">NONE</option>
                                </select>
                                <div class="inquiry-list-time clearfloat">
                                    <p class="fl">2018-04-25 <span>14:31:35</span></p>
                                    <i class="icon-messages fr"></i>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <!-- 加载更多按钮 -->
                    <div class="more-button" @click="dianjifenye">
                        <!-- Load more -->
                        <s:text name="mobile.load_more"/>
                    </div>
                </div>
            </div>
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
                        <!-- Sign In -->
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
            Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
            <%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
        </section>
    </footer>
    <%@ include file="/mobile/template/foot_menu.jsp" %>
    </div>

<script type="text/javascript">
	var myinquirylist = new Vue({
	  el: '#myinquirylist',
	  data: {
  		start:0,
    	limit:6,
    	nengfou:1,
		inquirylist:[{
		      "ret":1,
		      "result":{
		      	"totalCount":6,
		      	"totalPage":2199,
		      	"currentPage":2,
		      	"start":0,
		      	"limit":2,
		      	"items":[
		      	{
		            "id":1,
		            "title":"要一双鞋子",
		            "supplierCount":4,
		            "count":1,
		            "createTime":"2018.08.07",
		            "relations":[
		               {
		                  "supplierName":"中国供应站"
		               }

		            ]

		         }
		            ,
		         {
		            "id":3,
		            "title":"要十双人字拖",
		            "image":"图片地址",
		            "supplierCount":2,
		            "count":3,
		            "createTime":"2018.08.09",
		            "relations":[
		            	{
		                  "supplierName":"俄罗斯供应站"
		               }
		                  ,
		               {
		                  "supplierName":"中国供应站"
		               }
		               ]
		         }
		         ]
		      }
			}]

		  },

		  mounted(){
		  		this.inquirylistload();
			  },
		  methods:{
		  	inquirylistload:function(){
		  		axios.post('/home/usr_UsrConsult_pagePrivate',Qs.stringify({
		    		start:this.start,
		    		limit:this.limit
				}, {allowDots: true}))
				.then((res)=>{
					this.inquirylist222.push(...res.data.result)

		       		if(res.data.result.length<=0){
		       			layer.open({
		    			content: '没有更多了'
		    			,skin: 'msg'
		    			,time: 2 //2秒后自动关闭
		 				 });
		 				 this.nengfou=0
		       		}
		            if(res.data.ret== -1 ){
		            	window.location.href='/home/usr_UsrPurchase_sign?jumpUrl=/mobile/my-inquiry-list.jsp';
		            }
				})
				.catch((res)=>{

				});
		  	},
		  	dianjifenye:function(e){
		  		if(this.nengfou=1){
		  			this.start= this.start*1 + this.limit*1
					this.inquirylistload();
		  		}else{
		  			layer.open({
		    			content: '没有更多了'
		    			,skin: 'msg'
		    			,time: 2 //2秒后自动关闭
		 				 });
		  		}
		  	}
		  }
		})
</script>


</body>

</html>
