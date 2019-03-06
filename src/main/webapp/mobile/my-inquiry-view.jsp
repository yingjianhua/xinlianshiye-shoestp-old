<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">

<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <style>
        /* 询盘详情 begin */
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
        /* .wrapper{
          min-height: 360px;
        } */
        .user-inquiry .detail-inquiry {
            margin: 0 .625rem;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-title div:nth-child(2){
            padding: 2%;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-title div:nth-child(2) b {
            font-size: 1.125rem;
            font-weight: bold;
        }

        .user-inquiry .detail-inquiry .detail-inquiry-title div:nth-child(2) p {
            font-size: 0.75rem;
            color: #969696;
            margin-top: 1%;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-name{
            padding: 0 20%;
            text-align: center;
            color: #8f8f8f;
            margin: 5% 0;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-info-img{
            position: relative;
            width: 25%;
            padding-top: 25%;
            border: 1px solid #dedede;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-info-img img{
          position: absolute;
          left: 0;
          top: 0;
          width: 100%;
          height: 100%;
          object-fit: contain;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-info{
            width: 60%;
            padding:0.6rem 0.9rem;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-info div{
            line-height: 200%;
            width: 100%;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-info div span:first-child{
            color: #888888;
            display: inline-block;
            width: 33%;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-info div span:nth-child(2){
            display: inline-block;
            width: 30%;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-time{
            text-align: right;
            line-height: 200%;
        }
        .user-inquiry .detail-inquiry .inquiry-icon-message{
            position: relative;
            display: inline-block;
            background: url(./static/images/icon_review.gif) center center no-repeat;
            width: 1.3125rem;
            height: 1.125rem;
        }
        .user-inquiry .detail-inquiry .inquiry-icon-message .msg-tip:first-child{
            position: absolute;
            display: inline-block;
            bottom: 100%;
            left: 70%;
            width: auto;
            line-height: 1.125rem;
            padding: 0 2px;
            border-radius: 2px;
            color: #fff;
            background: #f10;
        }
        .user-inquiry .detail-inquiry .inquiry-icon-message .msg-tip:after{
          content:"";
            position: absolute;
            display: inline-block;
            width: 0;
            height: 0;
            bottom: -0.25rem;
            left: 0.25rem;
            border: 0.25rem solid transparent;
            border-left-color: #f10;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-content{
            border: 1px solid #e3e3e3;
            padding: 1rem 0.625rem;
            position: relative;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-content .detail-inquiry-content-img{
            height: 4.25rem;
            font-size: 0;
            border-top: 1px dashed #e3e3e3;
            padding: 4% 0 2px 0;
            margin-top: 3%;
            width: 100%;
            overflow-x: auto;
            white-space:nowrap;
            overflow-y: hidden;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-content .detail-inquiry-content-img img{
            width: 4.25rem;
            height: 4.25rem;
            border: 1px solid #e3e3e3;
            margin-left: 2.4%;
            object-fit: contain;
        }
        .user-inquiry .detail-inquiry .detail-inquiry-content .detail-inquiry-content-img img:first-child{
            margin-left: 0;
        }
        /* 询盘详情 end */
    </style>
</head>

<body>
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
                    <s:text name="my_account" />
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/usr_UsrConsult_listView">
                <!-- <a href="/home/odr_OdrOrder_orders"> -->
                    <!-- Inquiry list -->
                    <s:text name="inq_list" />
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a :href="'/home/usr_UsrConsult_detailView?inquiry_id='+info.id">
                    <!-- View Inquiry -->
                    <s:text name="inq" />
                </a>
            </div>
            <div class="user-inquiry">
                <div class="detail-inquiry">
                    <div class="detail-inquiry-title clearfloat">
                        <div class="fl">
                            <img src="./static/images/RFQ.png" alt="">
                        </div>
                        <div class="fl">
                            <b><!-- Request for Quotation --><s:text name="inq" /></b>
                            <p><!-- ne Request，Multiple Quotes --><s:text name="multipleQuotes" /></p>
                        </div>
                    </div>
                    <p class="detail-inquiry-name">{{inqueryInfo.title}}</p>
                    <div class="detail-inquiry-info-box clearfloat" style="margin-bottom: 0.4rem;">
                        <!-- 主图 -->
                        <div class="detail-inquiry-info-img fl">
                            <img :src="'${envConfig.imageBaseUrl}'+inqueryInfo.image.split(',')[0]" alt="">
                        </div>
                        <!-- 购买参数 -->
                        <div class="detail-inquiry-info fl">
                            <div v-if="inqueryInfo.productNum">
                                <span>Aricle no：</span>
                                <span>{{inqueryInfo.productNum}}</span>
                            </div>
                            <div>
                                <span><!-- Quaritity --><s:text name="order_line.qty" />：</span>
                                <span>{{inqueryInfo.quantity}} <s:text name="pair" /></span>
                            </div>
                            <div>
                                <span><s:text name="order.supplier" />:</span>
                                <span>{{inqueryInfo.supplierCount}}</span>
                                <a v-if="inqueryInfo.supplierCount > 0"
                                  class="inquiry-icon-message" :href="'/home/usr_UsrConsultMessage_dialogView?inquiry_id='+inquiry_id">
                                  <span class="msg-tip" v-show="inqueryInfo.haveNewMsg"><!-- new --><s:text name="new" /></span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- 时间 -->
                    <p class="detail-inquiry-time">
                      {{inqueryInfo.createTime}}
                      <%-- <span>14:56</span> --%>
                    </p>
                    <!-- 询盘内容 -->
                    <div class="detail-inquiry-content">
                        <p>{{inqueryInfo.content}}</p>
                        <div class="detail-inquiry-content-img"  v-if="inqueryInfo.image && inqueryInfo.image.split(',').length>1">
                            <img :src="'${envConfig.imageBaseUrl}'+imgUrl" alt="" v-for="(imgUrl,index) in inqueryInfo.image.split(',')" v-if="index>0">
                        </div>
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
    <script type="text/javascript">
      var vm = new Vue({
        el:"#user",
        data:{
          inqueryInfo:{   //询盘信息
            image: "./static/images/loading.gif"
          },
          info:{},
          inquiry_id: -1  // 询盘 - id
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
          // 地址上传过来的询盘id
          this.inquiry_id = this.getQueryString("inquiry_id");
          // 获取商品列表
          axios.get('/home/usr_UsrConsult_detail',{
            params:{
              id: this.inquiry_id
            }
          })
          .then((res)=>{
            console.log("suc",res)
            // 未登录状态
            if( res.data.ret == -1 ){
              window.location.href='/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_detailView?inquiry_id='+this.inquiry_id;
            }else if( res.data.ret == 1 ){
              this.inqueryInfo = res.data.result;
              this.info = res.config.params;
            }
          })
          .catch((err)=>{
            console.log("get goodsList err");
          });
        }
      })
    </script>
</body>
</html>
