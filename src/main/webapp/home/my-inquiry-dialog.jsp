<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->

    <script type="text/javascript" async="" src="/home/static/js/js.js">
    </script>
    <script type="text/javascript" async="" src="/home/static/js/conversion_async.js">
    </script>
    <script type="text/javascript" async="" src="/home/static/js/analytics.js">
    </script>
<!--     <script type="text/javascript" async="" src="/home/static/js/tracking.js"> -->
<!--     </script> -->
    <meta name="keywords" content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description" content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js">
    </script>
    <script type="text/javascript" src="/home/static/js/lang/en.js">
    </script>
    <script type="text/javascript" src="/home/static/js/main.js">
    </script>
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
    <link rel="stylesheet" href="/home/static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="/home/static/css/swiper.min.css" type="text/css">
    <script src="/home/static/js/swiper.min.js" type="text/javascript">
    </script>
    <script src="/home/static/js/lazyload.min.js" type="text/javascript">
    </script>

    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  	<link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.6/lib/theme-chalk/index.css">
  	<script src="https://unpkg.com/element-ui@2.4.6/lib/index.js"></script>
    <%-- <link href="/home/static/themes/default/mobile/css/layer-mobile.css" rel="stylesheet" type="text/css">
    <script src="/home/static/themes/default/mobile/js/layer-mobile.js"></script> --%>
    <script type="text/javascript" src="./static/js/layer.js"></script>
    <link rel="stylesheet" href="./static/css/layer.css" type="text/css">
    <script type="text/javascript" src="/home/static/js/qs.js"></script>

    <style type="text/css">

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
        textarea{
          padding: 6px 10px;
          box-sizing: border-box;
        }
        .img-contain{
          width: 100%;
          height: 100%;
          object-fit: contain;
        }
        #lib_user_contact_supplier .contact_supplier_main{
          height: auto;
          padding-bottom: 44px;
        }
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_content{
          min-height: 320px;
          max-height: 640px;
        	display: none;
        }
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_content.active{
        	display: block;
        }
        #lib_user_contact_supplier .oneself .user_goods_pic,
        #lib_user_contact_supplier .oneself .user_goods_item000{
        	float:right;
        }
        #lib_user_contact_supplier .other_party .user_goods_pic,
        #lib_user_contact_supplier .other_party .user_goods_item000{
        	float:left;
        }
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_content .user_goods_info{
        	height:auto;
        	 word-wrap:break-word;
        }
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_content .user_goods_info img{
        	 height:80px;
        	 width: 80px;
           /* margin: 5px; */
           object-fit: contain;
        }
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_input_box{
          height: 130px;
        }
        .contact_supplier_btn span{
        	cursor: pointer;
        }
        /* 内容min-高度减小 */
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_content .inquiry_content_main .inquiry_content{
          min-height: 30px;
          word-wrap: break-word;
        }
        #lib_user_contact_supplier .contact_supplier_main .contact_supplier_content .inquiry_content_main{
          margin-top: 0;
        }
        /* upload按钮改回默认样式 */
        .el-upload--picture-card {
          position: absolute;
          left: 10px;
          bottom: 6px;
          width: auto;
          height: auto;
          line-height: normal;
          border-width: 0;
          border-radius: 0;
          box-sizing: border-box;
          background-color: #fbfdff;
        }
        /* 上传图片预览的大小 */
        .el-upload-list--picture-card .el-upload-list__item{
          width: 100px;
          height: 100px;
        }
        
        .uploadBtn{
        	display: inline-block;
		    color: #606266;
		    background: #fff;
		    padding: 7px 14px;
		    text-align: center;
		    border: 2px solid #dcdfe6;
        }
        
        .uploadBtn:hover{
			border-color:#c6e2ff;		    
		    background: #ecf5ff;
        }
    </style>
    <link href="/home/static/css/row_4.css" rel="stylesheet" type="text/css">
</head>

<body class="lang_en w_1200">

    <%@ include file="/home/template/web-top.jsp" %>

    <%@ include file="/home/template/new-header.jsp" %>


    <div id="main" class="wide">
        <div id="lib_user" class="clearfix">
            <div id="lib_user_crumb" class="widget">
                <ul class="crumb_box clearfix">
                    <li class="home">
                        <a href="/home" title="Home"><s:text name="Global.Home"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb1">
                        <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="Global.My_Account"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb2 root">
                        <a href="/home/usr_UsrConsult_listView" title="My Inquiry/RFQ"><s:text name="my-inquiry-publish.View_Inquiry"/>
                            <i></i>
                        </a>
                    </li>
                </ul>
            </div>

            <%@ include file="template/account/lib-user-menu.jsp" %>

            <div   id="liuyanbox">
            <div id="lib_user_main">
                <h1 class="lib_user_title"><s:text name="my-inquiry-dialog.Contact_Supplier"/></h1>
                <div id="lib_user_contact_supplier" v-if="liouyanlist.length>0">

                    <ul class="contact_supplier_tab clearfloat" v-cloak>
                        <li  :class="[nowSupplierId == liouyandd.id ? 'tab_active':'']"  v-for='(liouyandd,index) in liouyanlist' :data-supplier-id="liouyandd.id" @click="changeSupplier">
                        	{{liouyandd.supplierName}}
                        	<i class="new-message" v-if='liouyandd.haveNewMsg'></i>
                        </li>
                    </ul>

                    <div class="contact_supplier_main">
                        <!-- 聊天内容 start -->
                        <div class="contact_supplier_content clearfloat" :class="[nowSupplierId == liouyancc.id ? 'active':'']"   v-for='(liouyancc, index) in liouyanlist'>

                                <div class="user_goods_main oneself">
                                        <div class="user_goods_content clearfloat">
                                            <div class="fl">
                                                <div class="user_goods_time">{{inquiryInfo.createTime}}</div>
                                                <div class="user_live_box">
                                                        <div class="user_goods_info" style="border: none;overflow: inherit;">
                                                                <div class="goods_info_head clearfloat">
                                                                    <div class="fl goods_info_title">
                                                                            {{inquiryInfo.title}}
                                                                    </div>
                                                                    <div class="fr clearfloat goods_info_rfq">
                                                                        <div class="fl">
                                                                            <img src="./static/images/RFQ.png" alt="">
                                                                        </div>
                                                                        <div class="fl">
                                                                            <p>Request for Quotation</p>
                                                                            <p>One Request，Multiple Quotes</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="goods_info_body clearfloat">
                                                                    <div class="fl">
                                                                        <img class="img-contain" v-if="inquiryImgs" :src=" '${envConfig.imageBaseUrl}'+inquiryImgs[0]" alt="">
                                                                    </div>
                                                                    <div class="fl">
                                                                        <div>
                                                                            Article no：<span>{{inquiryInfo.productNum}}</span>
                                                                        </div>
                                                                        <div>
                                                                            Quantity  ：<span>{{inquiryInfo.quantity}}</span> Pair
                                                                        </div>
                                                                        <div>
                                                                            <div class="clearfloat">
                                                                                Supplier ：<span>{{inquiryInfo.supplierCount}}</span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <!-- 展开关闭 按钮 -->
                                                                    <div class="goods_box_btn rotate">

                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- 询盘内容 -->
                                                            <div class="inquiry_content_main">
                                                                <div class="inquiry_content">
                                                                    <p>
                                                                            {{inquiryInfo.content}}

                                                                    </p>
                                                                </div>
                                                                <div class="inquiry_content_img" v-if="inquiryImgs && inquiryImgs.length>1">
                                                                    <ul class="clearfloat">
                                                                        <li v-for="(imgUrl,index) in inquiryImgs" v-if="index>0">
                                                                            <img  class="img-contain" :src="'${envConfig.imageBaseUrl}'+imgUrl" alt="">
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


							<div v-for='liouyanss in liouyancc.msgs'>
							<p style="clear: both;text-align:  center;padding: 10px;font-size: 12px;">{{liouyanss.sendTime}}</p>
                            <!-- 对方回复 start -->
                            <div :class="[liouyanss.p2S == true ? 'oneself':'other_party']"  style="margin-top:0;">
                                <div class="user_goods_content clearfloat">
                                    <%-- 人物头像 - 暂时隐藏 --%>
                                    <%-- <div class="user_goods_pic fl">
                                            <img src="/home/static/images/ico/bg3_1.png" alt="">
                                    </div> --%>
                                    <div class="user_goods_item000">
                                            <%-- <div class="user_goods_time">{{liouyanss.sendTime}}</div> --%>
                                            <div class="user_goods_info">
                                                <%-- 将内容转换为 图片+文字 --%>
                                                <%-- 提取img拼接字符串并循环显示 --%>
                                                <div class="" v-if="textToImgsContent(liouyanss.content,0)">
                                                  <img :src="'${envConfig.imageBaseUrl}'+imgSrc" v-for="imgSrc in textToImgsContent(liouyanss.content,0)" alt="">
                                                </div>
                                                <%-- 提取img拼接字符串 剩余的txt --%>
                                                <div class="">
                                                  {{textToImgsContent(liouyanss.content,1)}}
                                                </div>
                                            </div>
                                        </div>
                                </div>
                            </div>
                            <!-- 对方回复 end -->
                            </div>
                            
                            
                            
                        </div>
                        <!-- 聊天内容 end -->

                        <div class="border_hr"></div>

                        <div class="chooseImg">
                          <el-upload
                            action="/home/usr_UsrConsult_upload"
                            list-type="picture-card"
                            ref="uploadImgs"
                            :limit="5"
                            :on-success="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            >
                            <button class="uploadBtn" type="button" :disabled="noUpload" ref="uploadBtn">Upload imgs</button>
                          </el-upload>
                        </div>

                        <form action="">
                            <div class="contact_supplier_input_box">
                                <textarea name="" id="" cols="" rows="" v-model="inputContent"></textarea>
                                <i class="input_box_edit"></i>
                            </div>
                            <div class="contact_supplier_btn">
                                <span class="send_out"  @click='sendCommon'><s:text name="my-inquiry-dialog.Send"/></span>
                                <span class="return"  @click='returnCommon'><s:text name="my-favorite.Empty"/></span>
                            </div>
                        </form>
                    </div>

                </div>
                <div v-else style="height: 400px;line-height: 400px;color: #999;text-align: center;font-size: 24px;">
                  <s:text name="my-inquiry-dialog.No_Consultation_With_The_Supplier"/>
                </div>
            </div>
            </div>
        </div>
    </div>

    <%@ include file="/home/template/new-foot.jsp" %>

    <div id="hj_top" style="opacity: 0;">
        <img src="/home/static/images/hj_top.png">
    </div>


  <script type="text/javascript">
    var liuyanbox666 = new Vue({
      el: '#liuyanbox',
      data: {
        imgsToUpload:[],       // 需要upload的img - 显示在页面上
        myImgLabelBefore:"<IMG_START>", //自定义的imgs前缀标签
        myImgLabelAfter:"<IMG_END>",    //自定义的imgs后缀标签
        noUpload:false,
      	liouyanlist:[],      //对话框数据 - 后台传过来
      	inputContent:"",     //输入框里的content
      	inquiry_id:'',       //地址传过来的参数
        nowSupplierId: -1,     //当前显示的供应商id
        inquiryInfo:[],         //询盘商品信息
      },
      computed:{
        inquiryImgs: function(){
            if( this.inquiryInfo.image ){
                return this.inquiryInfo.image.split(",");
            }else{
                return null;
            }
        }
      },
      mounted:function(){
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

    		this.liouyanload();
        this.inquiryDetail();
      },
      methods:{
      	liouyanload(){
      		axios.post('/home/usr_UsrConsultMessage_list',Qs.stringify({
        		id:this.inquiry_id
      		}, {allowDots: true}))
      		.then((res)=>{
            if(res.data.ret== -1 ){
            	window.location.href='/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsultMessage_dialogView?inquiry_id='+this.inquiry_id;
            }else{
              this.liouyanlist = res.data.result;
              if( this.nowSupplierId == -1 ){
                this.nowSupplierId = res.data.result[0].id; //一进来时显示第一个
              }
              this.$nextTick(()=>{
                // 显示最下面的信息
                $(".contact_supplier_content.active").scrollTop(300000);
              })
            }
      		})
      		.catch((res)=>{

      		});
      	},

        // 点击 查看其余供应商
        changeSupplier(e){
          this.nowSupplierId = $(e.currentTarget).data("supplierId");
        },

        // 图片上传成功时的预览
        handlePictureCardPreview(response, file, fileList){
          if( response.ret != 1 ) return;
          this.imgsToUpload.push(response.result.url);
          if(this.imgsToUpload.length == 5){
	          this.$refs.uploadBtn.style.backgroundColor = "#c2c2c2";
			  this.noUpload = true;
          }
        },
        // elementui - 删除操作
        handleRemove(file, fileList) {
          // 清空imgs数组
          this.imgsToUpload=[];
          // 删除图片后，将授予的图片地址放入数组
          $.each(fileList,(i,v)=>{
            this.imgsToUpload.push(v.response.result.url)
          })
          if(this.imgsToUpload.length < 5){
        	  this.$refs.uploadBtn.style.backgroundColor = "#fff";
        	  this.noUpload = false;
          }
        },

    	  sendCommon(){
          let textImgs = (this.imgsToUpload && this.imgsToUpload.length)? (this.myImgLabelBefore+ this.imgsToUpload.join(",") +this.myImgLabelAfter):"";
          // 没有数据时,不触发
          if( $.trim(this.inputContent + textImgs) == "") return;
          // 将上传的图片 以字符串的形式拼接在txt后面"<IMG_START><IMG_END>"
    	    axios.post('/home/usr_UsrConsultMessage_send2Supplier',Qs.stringify({
    	    	content:this.inputContent + textImgs,
    	    	relation:this.nowSupplierId
      		}, {allowDots: true}))
      		.then((res)=>{
      			if(res.data.ret== 1 ){
      				this.liouyanload();
              this.imgsToUpload= [];
              this.$refs.uploadImgs.clearFiles();
              this.inputContent="";
      			}else{
              layer.msg(lang_obj.mobile.Failed_to_send+ res.data.msg, {icon: 2});
      			}
      		})
      		.catch((res)=>{
      				console.log("err")
      		});
    	  },

        // 服务器返回的msg内容，提取其中的imgs
        // type为0时，返回img的拼接字符串
        // type为1时，返回img之外的文字
        textToImgsContent(txt,type){
          if( !txt ) return null;

          let firstIndex = txt.search(this.myImgLabelBefore);
          if( type==0 ){
            if( firstIndex == -1 ) return null;
            let lastIndex = txt.search(this.myImgLabelAfter);
            // 获取上传前拼接的img字符串
            let imgsTxt = txt.slice(firstIndex+this.myImgLabelBefore.length,lastIndex);
            return imgsTxt.split(',');
          }else if( type==1 ){
            if( firstIndex == -1 ) return txt;
            return txt.slice(0,firstIndex);
          }
        },

      	returnCommon(){
      	 	this.inputContent=""
      	},
        //   一进来就显示的询盘详情
        // /home/usr_UsrConsult_detail
        inquiryDetail(){
    	    axios.post('/home/usr_UsrConsult_detail',Qs.stringify({
    	    	id:this.inquiry_id
      		}, {allowDots: true}))
      		.then((res)=>{
      			if(res.data.ret == 1 ){
                    this.inquiryInfo = res.data.result;
      			}else{
              layer.msg(lang_obj.mobile.Acquisition_failed+ res.data.msg, {icon: 2});
      			}
      		})
      		.catch((res)=>{
      				console.log("err")
      		});
    	  },

    	}
    })



  </script>

  <script>

  //
  //        $(".my-panel-wrap .panel-title").delegate(".panel-title-item", "click", function(){
  //          $(this).addClass("active").siblings().removeClass("active");
  //          let targetIndex = $(this).index();
  //          $(this).closest(".my-panel-wrap").find(".panel-content .panel-content-item").eq(targetIndex).addClass("active").siblings().removeClass("active");
  //        })
  //
  //        $(".goods-brif-info").delegate(".icon-slide", "click", function(){
  //          $(this).toggleClass("active");
  //          $(this).closest(".send-goods-msg").find(".goods-detaile-info").slideToggle();
  //        })
      $('#liuyanbox').on('click','.goods_box_btn',function(){
          $(this).parent().parent().siblings('.inquiry_content_main').stop().slideToggle();
          $(this).toggleClass('rotate')
      })
      // $('#lib_user_contact_supplier').on('click','.contact_supplier_tab li',function(){
      //     $(this).addClass('tab_active').siblings().removeClass('tab_active');
      //     let targetIndex = $(this).index();
      // 	  $(".contact_supplier_content").eq(targetIndex).addClass("active").siblings().removeClass("active");
      // })
  </script>

</body>

</html>
