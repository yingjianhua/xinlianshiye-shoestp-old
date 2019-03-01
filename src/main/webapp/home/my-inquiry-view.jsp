<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->

    <script type="text/javascript" async="" src="./static/js/js.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/conversion_async.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/analytics.js">
    </script>
<!--     <script type="text/javascript" async="" src="./static/js/tracking.js"> -->
<!--     </script> -->
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market--Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js">
    </script>
    <script type="text/javascript" src="./static/js/lang/en.js">
    </script>
    <script type="text/javascript" src="./static/js/main.js">
    </script>
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js" type="text/javascript">
    </script>
    <script src="./static/js/lazyload.min.js" type="text/javascript">
    </script>

    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">

    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/qs.js"></script>
    <style>
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
        .my-normal-img{
            width: 100%;
            height: 100%;
            object-fit: contain;
        }
        .inquiry_info_list img{
        	width: 100%;
        	height: 100%;
        	object-fit: contain;
        }
    </style>
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
                        <a href="/home/usr_UsrConsult_listView" title="My Inquiry/RFQ"><s:text name="my_Inq"/>
                            <i></i>
                        </a>
                    </li>
                </ul>
            </div>

            <%@ include file="template/account/lib-user-menu.jsp" %>

            <div id="lib_user_main" v-cloak>
                <h1 class="lib_user_title"><s:text name="viewInquiry"/></h1>
                <div id="lib_user_inquiry_view">
                    <div class="blank20"></div>

                    <div class="inquiry_view_main">
                        <div class="clearfloat inquiry_view_tab">
                            <div class="fl"><img src="./static/images/RFQ.png" alt=""></div>
                            <div class="fl">
                                <p><s:text name="requestQuotation"/></p>
                                <p><s:text name="Global.One_Request"/></p>
                            </div>
                        </div>
                        <div class="inquiry_view_title">{{inqueryInfo.title}}</div>
                        <div class="clearfloat">
                            <div class="fl inquiry_img">
                                <img v-if="inqueryInfo.image" class="my-normal-img" :src="'${envConfig.imageBaseUrl}'+inqueryInfo.image.split(',')[0]" alt="">
                            </div>
                            <div class="fl inquiry_par">
                                <div v-if="inqueryInfo.productNum"><s:text name="my-inquiry-view.Article_ID"/>:
                                  <span>{{inqueryInfo.productNum}}</span>
                                </div>

                                <div>
                                  <s:text name="Global.Quantity"/>:
                                  <span>{{inqueryInfo.quantity}}</span>
                                  <s:text name="Global.Double"/>
                                </div>

                                <div class="clearfloat"><s:text name="order.supplier"/>:
                                    <span>{{inqueryInfo.supplierCount}}</span>
                                    <a v-if="inqueryInfo.supplierCount > 0"
                                      class="inquiry-message fr" :href="'/home/usr_UsrConsultMessage_dialogView?inquiry_id='+inquiry_id">
                                        <i class="new-inquiry-message" v-show="inqueryInfo.haveNewMsg"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="inquiry_time">
                            {{inqueryInfo.createTime}}
                        </div>
                        <div class="inquiry_info">
                            <div class="inquiry_info_p">
                                <p>{{inqueryInfo.content}}</p>
                            </div>
                            <template v-if="inqueryInfo.image && inqueryInfo.image.split(',').length>1">
                              <ul class="inquiry_info_list clearfloat">
                                <li v-for="(imgUrl,index) in inqueryInfo.image.split(',')" v-if="index>0">
                                  <img :src="'${envConfig.imageBaseUrl}'+imgUrl" alt="">
                                </li>
                              </ul>
                          </template>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
        <index-bottom></index-bottom>
    </div>

    <%--<%@ include file="/home/template/new-foot.jsp" %>--%>

    <div id="hj_top" style="opacity: 0;">
        <img src="/home/static/images/hj_top.png">
    </div>

    <script type="text/javascript">
      var vm = new Vue({
        el:"#main",
        data:{
          inqueryInfo:{   //è¯¢çä¿¡æ¯
            // image: "./static/images/loading.gif"
          },
          inquiry_id: -1
        },
        methods:{
           getQueryString: (name) => {
      			let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      			let reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
  		    	let r = window.location.search.substr(1).match(reg);
  			    let q = window.location.pathname.substr(1).match(reg_rewrite);
  			    if(r != null){
  			        return unescape(r[2]);
  			    }else if(q != null){
  			        return unescape(q[2]);
  			    }else{
  			        return null;
  			    }
    			}

        },
        mounted(){
          // å°åä¸ä¼ è¿æ¥çè¯¢çid
          this.inquiry_id = this.getQueryString("inquiry_id");
          // è·ååååè¡¨
          axios.post('/home/usr_UsrConsult_detail',Qs.stringify({
              id: this.inquiry_id
          }))
          .then((res)=>{
            console.log("suc",res)
            // æªç»å½ç¶æ
            if( res.data.ret == -1 ){
              window.location.href='/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_detailView?inquiry_id='+this.inquiry_id;
            }else if( res.data.ret == 1 ){
              this.inqueryInfo = res.data.result;
            }
          })
          .catch((err)=>{
            console.log("get goodsList err");
          });
        }
      })
    </script>
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>
</body>

</html>
