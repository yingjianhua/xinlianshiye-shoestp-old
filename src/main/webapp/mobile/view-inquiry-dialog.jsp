<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="us">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->
    <script type="text/javascript" async="" src="/home/static/themes/default/mobile/js/js">
    </script>
    <script type="text/javascript" async="" src="/home/static/themes/default/mobile/js/conversion_async.js">
    </script>
    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <!-- End Google Tag Manager -->
    <link rel="shortcut icon" href="https://www.shoestp.com/u_file/1707/photo/0379568f09.ico">
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B marketââ-Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="/home/static/themes/default/mobile/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/themes/default/mobile/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/jquery-min.js">
    </script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/global.js">
    </script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/rye-touch.js">
    </script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/global(1).js">
    </script>
    <link href="/home/static/themes/default/mobile/css/style(1).css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/en.js">
    </script>
    <link href="/home/static/themes/default/mobile/css/user.css" rel="stylesheet" type="text/css">
    <link href="/home/static/themes/default/mobile/css/view-inquiry.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/user.js">
    </script>
    <script src="/home/static/themes/default/mobile/js/saved_resource">
    </script>
    <link href="/home/static/themes/default/mobile/css/layer-mobile.css" rel="stylesheet" type="text/css">
    <script src="/home/static/themes/default/mobile/js/layer-mobile.js">
    </script>
    <script type="text/javascript" async="" src="/home/static/themes/default/mobile/js/qs.js">
    </script>
    <style>
		.layui-m-layer-msg .layui-m-layercont{color:#fff;}
	</style>
</head>

<body>
	<div id="liuyanbox">
    <%@ include file="/mobile/template/header.jsp" %>
    <div class="wrapper">
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
          <em>
              <i>
              </i>
          </em>
          <a href="/home/odr_OdrOrder_orders">
              <!-- View Inquiry -->
              <s:text name="inquiry_set"/>
          </a>
      </div>




      <div class="main">
        <div class="my-panel-wrap">
          <!-- æçpanelæ¿ - çå¤´ -->
          <div class="panel-title flex">

          <div class="panel-title-item"  :class="[index == 0 ? 'active':'']"   v-for='(liouyandd, index) in liouyanlist[0].result'>
          	{{liouyandd.supplierName}}
          	<span class="msg-tip" v-if='liouyandd.haveNewMsg==true'><!-- new --><s:text name="new"/></span>
          </div>


          </div>

          <!-- æçpanelæ¿ - æ¿åå®¹ -->
          <div class="panel-content flex-grow">

            <div class="panel-content-item dialog-list"  :class="[index == 0 ? 'active':'']"   v-for='(liouyancc, index) in liouyanlist[0].result'>
              <!-- æ·»å æ ·å¼mine - æçå¯¹è¯æ¡ - æ ·å¼ä¸å -->

              <div class="dialog-item" :class="[liouyanss.p2S == true ? 'mine':'']"  v-for='liouyanss in liouyancc.msgs'>
                <!-- å¯¹è¯æ¡ - å¤´å -->
                <div class="user-header">
                  <img src="/home/static/themes/default/mobile/images/939dad0217.png" alt="">
                </div>

                <!-- å¯¹è¯æ¡ - åå®¹ -->
                <!--6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666-->
                <div class="dialog-wrap">
                  <div class="time">
                  {{liouyanss.sendTime}}
                  </div>
                  <div class="dialog-content">
                    {{liouyanss.content}}
                  </div>
                </div>
              </div>






              <!--<div class="dialog-item mine">

                <div class="user-header">
                  <img src="/home/static/themes/default/mobile/images/939dad0217.png" alt="">
                </div>

                <div class="dialog-wrap">
                  <div class="time">2018-3-25 14:56</div>
                  <div class="dialog-content">

                    <div class="send-goods-msg">

                      <div class="goods-brif-info">
                        <div class="goods-name">
                          Latest men casual waxed buffalo leather dress shoes
                        </div>
                        <div class="clean" style="position: relative;min-height: 3.6875rem;">
                          <div class="goods-pic">
                            <img src="/home/static/themes/default/mobile/images/1ed13be5ce.jpg.500x500.jpg" alt="">
                          </div>
                          <div class="goods-order-info">
                            <div class="row-info clean">
                              <div class="name">Article no:</div>
                              <div class="content">KL-038</div>
                            </div>
                            <div class="row-info clean">
                              <div class="name">Quantity:</div>
                              <div class="content">10000 Pair</div>
                            </div>
                          </div>
                        </div>
                        <img src="/home/static/themes/default/mobile/images/double-arrow-white.png" alt="" class="icon-slide">
                      </div>

                      <div class="goods-detaile-info">
                        <div class="goods-pic-wrap">
                          <div class="goods-pic-item">
                            <div class="goods-pic-box">
                              <img src="/home/static/themes/default/mobile/images/1ed13be5ce.jpg.500x500.jpg" alt="">
                            </div>
                          </div>
                          <div class="goods-pic-item">
                            <div class="goods-pic-box">
                              <img src="/home/static/themes/default/mobile/images/3.png" alt="">
                            </div>
                          </div>
                          <div class="goods-pic-item">
                            <div class="goods-pic-box">
                              <img src="/home/static/themes/default/mobile/images/b041514fd2.jpg" alt="">
                            </div>
                          </div>
                          <div class="goods-pic-item">
                            <div class="goods-pic-box">
                              <img src="/home/static/themes/default/mobile/images/RFQ.png" alt="">
                            </div>
                          </div>
                        </div>
                        <div class="goods-detaile-descript">
                          Aranese electronics manufacturer Hon
                          Hai Precision(akaReuters reports. The
                          $10 billion plant will take four years to
                          be completed, and receive tax
                          incentives of $3 billion over 20 years.
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>-->

            </div>






          </div>
        </div>
      </div>


      <div class="send-wrap flex">
        <div class="input-wrap flex-grow">
          <input type="text" v-model = "inputContent">
        </div>
        <input type="submit" value="send" @click='sendCommon'>
      </div>
    </div>


 </div>

    <footer>
        <div id="prolist_mask_footer">
        </div>
        <ul class="footer_list ui_border_b clean" style="display:none;">
            <li class="fl" style="border-right:1px solid #ddd;">
                <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                    <span class="list_left">
                        <!-- Sign In -->
                        <s:text name="sign_in"/>
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
    </footer>
    <%@ include file="/mobile/template/foot_menu.jsp" %>
</body>

<script type="text/javascript">
var liuyanbox666 = new Vue({
  el: '#liuyanbox',
  data: {
	liouyanlist:[],
	inputContent:"",
	inquiry_id:'',
  },

  mounted(){
  		this.liouyanload();
	  },
  methods:{




  	liouyanload:function(){

			function getQueryString(name) {
    			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    			var reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
		    	var r = window.location.search.substr(1).match(reg);
			    var q = window.location.pathname.substr(1).match(reg_rewrite);
			    if(r != null){
			        return unescape(r[2]);
			    }else if(q != null){
			        return unescape(q[2]);
			    }else{
			        return null;
			    }
			}
			this.inquiry_id = getQueryString("inquiry_id");

  		axios.post('/home/usr_UsrConsultMessage_list',Qs.stringify({
    		id:this.inquiry_id
		}, {allowDots: true}))
		.then((res)=>{
            this.liouyanlist = res.data.result;
            console.log(this.liouyanlist)
            if(res.data.ret== -1 ){
            	window.location.href='/home/usr_UsrPurchase_sign?jumpUrl=/mobile/view-inquiry-dialog.jsp';
            }
		})
		.catch((res)=>{

		});
  	},

	 sendCommon: function(){

	    axios.post('/home/usr_UsrConsultMessage_send2Supplier',Qs.stringify({
	    	content:this.inputContent,
	    	relation:this.inquiry_id
		}, {allowDots: true}))
		.then((res)=>{
			if(res.data.ret== 1 ){
				this.liouyanload();
			}else{
				layer.open({
    			content: '发送失败,'+ res.data.msg
    			,skin: 'msg'
    			,time: 2 //2秒后自动关闭
 				 });
			}
		})
		.catch((res)=>{
				console.log("err")
		});
	  }
	}
})



</script>
		<script>


          $(".my-panel-wrap .panel-title").delegate(".panel-title-item", "click", function(){
            $(this).addClass("active").siblings().removeClass("active");
            let targetIndex = $(this).index();
            $(this).closest(".my-panel-wrap").find(".panel-content .panel-content-item").eq(targetIndex).addClass("active").siblings().removeClass("active");
          })

          $(".goods-brif-info").delegate(".icon-slide", "click", function(){
            $(this).toggleClass("active");
            $(this).closest(".send-goods-msg").find(".goods-detaile-info").slideToggle();
          })

        </script>

</html>
