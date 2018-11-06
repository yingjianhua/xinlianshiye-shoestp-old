<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <link href="/home/static/themes/default/mobile/css/view-inquiry.css" rel="stylesheet" type="text/css">
  	<link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.6/lib/theme-chalk/index.css">
  	<script src="https://unpkg.com/element-ui@2.4.6/lib/index.js"></script>
    <style>
		  /* .layui-m-layer-msg .layui-m-layercont{color:#fff;} */
      /* 以下两个为隐藏头像后，位置相应更改 */
      .dialog-list .dialog-item .dialog-wrap{
        margin-left: 0;
        margin-right: 2rem;
      }
      .dialog-list .dialog-item.mine .dialog-wrap{
        margin-right: 0;
        margin-left: 2rem;
      }
      /* 对话按钮位置 - 防止遮挡住发送按钮 */
      .placeholder-btn-dialog{
        height: 0.2rem;
        width: 48px;
        flex-shrink: 0;
      }
      /* upload按钮改回默认样式 */
      .el-upload--picture-card {
        position: fixed;
        left: 0.75rem;
        bottom: 4.4rem;
        z-index: 9;
        width: auto;
        height: auto;
        line-height: normal;
        border-width: 0;
        border-radius: 2rem;
        box-sizing: border-box;
        background-color: #fbfdff;
      }
      /* 上传图片预览的大小 */
      .el-upload-list--picture-card .el-upload-list__item{
        width: 2rem;
        height: 2rem;
        margin: 0.5rem 0 0rem 0.75rem;
      }
      .el-upload-list--picture-card .el-upload-list__item:first-child{
        margin-left: 1rem;
      }
      .el-upload--picture-card i {
        font-size: 20px;
      }

      .chooseImg-wrap{
        position: fixed;
        bottom: 7.3rem;
        left: 0;
        right: 0;
        z-index: 99;
        /* padding: 0.75rem 0.75rem 0; */
        background: #fff;
        box-shadow: -2px -2px 2px #eee;
      }
	  </style>
</head>

<body>
  <%@ include file="/mobile/template/header.jsp" %>
  <div id="liuyanbox">
    <div class="wrapper">
      <div class="crumb clean">
          <a href="#">
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
              <!-- Inquiry list -->
              <s:text name="inq_list" />
          </a>
          <em>
              <i>
              </i>
          </em>
          <a href="#">
              <!-- View Inquiry -->
              <s:text name="inquiry" />
          </a>
      </div>




      <div class="main"  v-if="liouyanlist.length">
        <div class="my-panel-wrap" >
          <!-- æçpanelæ¿ - çå¤´ -->
          <div class="panel-title flex">

          <div v-cloak class="panel-title-item"  :class="[nowSupplierId == liouyandd.id ? 'active':'']"  v-for='(liouyandd, index) in liouyanlist' :data-supplier-id="liouyandd.id" @click="changeSupplier">
          	{{liouyandd.supplierName}}
          	<span class="msg-tip" v-if='liouyandd.haveNewMsg'><!-- new --><s:text name="new" /></span>
          </div>


          </div>

          <!-- æçpanelæ¿ - æ¿åå®¹ -->
          <div class="panel-content flex-grow">

            <div class="panel-content-item dialog-list"  :class="[nowSupplierId == liouyancc.id ? 'active':'']" v-for='(liouyancc, index) in liouyanlist'>
              <!-- æ·»å æ ·å¼mine - æçå¯¹è¯æ¡ - æ ·å¼ä¸å -->


              <div class="dialog-item mine">
                <!-- 对话框 - 头像 -->
                <!-- <div class="user-header">
                  <img src="./static/images/939dad0217.png" alt="">
                </div> -->
                <!-- 对话框 - 内容 -->
                <div class="dialog-wrap">
                  <div class="time">{{inquiryInfo.createTime}}</div>
                  <div class="dialog-content">
                    <!-- 发送 产品信息时显示产品 -->
                    <div class="send-goods-msg">
                      <!-- 产品的简要信息 -->
                      <div class="goods-brif-info">
                        <div class="goods-name">
                          {{inquiryInfo.title}}
                        </div>
                        <div class="clean" style="position: relative;min-height: 3.6875rem;">
                          <div class="goods-pic">
                            <img v-if="inquiryImgs" :src="'${envConfig.imageBaseUrl}'+inquiryImgs[0]" alt="">
                          </div>
                          <div class="goods-order-info">
                            <div class="row-info clean">
                              <div class="name">Article no:</div>
                              <div class="content">{{inquiryInfo.productNum}}</div>
                            </div>
                            <div class="row-info clean">
                              <div class="name">Quantity:</div>
                              <div class="content">{{inquiryInfo.quantity}} Pair</div>
                            </div>
                          </div>
                        </div>
                        <img src="./static/images/double-arrow-white.png" alt="" class="icon-slide active">
                      </div>
                      <!-- 产品点开后的详细介绍信息 -->
                      <div class="goods-detaile-info">
                        <div class="goods-pic-wrap" v-if="inquiryImgs && inquiryImgs.length>1">
                          <div class="goods-pic-item" v-for="(imgUrl,index) in inquiryImgs" v-if="index>0">
                            <div class="goods-pic-box">
                              <img :src="'${envConfig.imageBaseUrl}'+imgUrl" alt="">
                            </div>
                          </div>
                        </div>
                        <div class="goods-detaile-descript">
                          {{inquiryInfo.content}}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>





              <div class="dialog-item" :class="[liouyanss.p2S == true ? 'mine':'']"  v-for='liouyanss in liouyancc.msgs'>
                <!-- å¯¹è¯æ¡ - å¤´å -->
                <%-- 隐藏头像 --%>
                <%-- <div class="user-header">
                  <img src="/home/static/themes/default/mobile/images/939dad0217.png" alt="">
                </div> --%>

                <!-- å¯¹è¯æ¡ - åå®¹ -->
                <!--6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666-->
                <div class="dialog-wrap">
                  <div class="time">
                  {{liouyanss.sendTime}}
                  </div>
                  <div class="dialog-content">
                    <%-- {{liouyanss.content}} --%>
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
          </div>
        </div>
      </div>


      <%-- 上传图片按钮 --%>
      <div class="chooseImg-wrap">
        <el-upload
          action="/home/usr_UsrConsult_upload"
          list-type="picture-card"
          ref="uploadImgs"
          :limit="5"
          :on-success="handlePictureCardPreview"
          :on-remove="handleRemove"
          >
          <el-button size="small" type="normal" circle icon="el-icon-plus"></el-button>
        </el-upload>
      </div>

      <div class="send-wrap flex" v-if="liouyanlist.length">
        <div class="placeholder-btn-dialog"></div>
        <div class="input-wrap flex-grow">
          <input type="text" v-model = "inputContent" style="width: 100%;height: 100%;">
        </div>
        <input type="submit" value="send" @click='sendCommon'>
        <%-- <div class="placeholder-btn-dialog"></div> --%>
      </div>

      <div v-else style="height: 6rem;line-height: 6rem;color: #999;text-align: center;font-size: 18px;">
        <!-- There is no supplier's consult. -->
        <s:text name="supplier's_consult" />
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
    </footer>
	<%@ include file="/mobile/template/foot_menu.jsp" %>

<script type="text/javascript">
var liuyanbox666 = new Vue({
  el: '#liuyanbox',
  data: {
    imgsToUpload:[],       // 需要upload的img - 显示在页面上
    myImgLabelBefore:"<IMG_START>", //自定义的imgs前缀标签
    myImgLabelAfter:"<IMG_END>",    //自定义的imgs后缀标签
  	liouyanlist:[{result:[]}],
  	inputContent:"",
  	inquiry_id:'',
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
  	liouyanload:function(){

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
              $(".panel-content").scrollTop(300000);
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
    },
    // elementui - 删除操作
    handleRemove(file, fileList) {
      // 清空imgs数组
      this.imgsToUpload=[];
      // 删除图片后，将授予的图片地址放入数组
      $.each(fileList,(i,v)=>{
        this.imgsToUpload.push(v.response.result.url)
      })
    },

	 sendCommon: function(){
      // 将上传的图片 以字符串的形式拼接在txt后面"<IMG_START><IMG_END>"
      let textImgs = (this.imgsToUpload && this.imgsToUpload.length)? (this.myImgLabelBefore+ this.imgsToUpload.join(",") +this.myImgLabelAfter):"";
      // 没有数据时,不触发
      if( $.trim(this.inputContent + textImgs) == "") return;
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
      				layer.open({
          			content: '获取失败,'+ res.data.msg
          			,skin: 'msg'
          			,time: 2 //2秒后自动关闭
       				});
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

  // $(".my-panel-wrap .panel-title").delegate(".panel-title-item", "click", function(){
  //   $(this).addClass("active").siblings().removeClass("active");
  //   let targetIndex = $(this).index();
  //   $(this).closest(".my-panel-wrap").find(".panel-content .panel-content-item").eq(targetIndex).addClass("active").siblings().removeClass("active");
  // })

  $(".goods-brif-info").delegate(".icon-slide", "click", function(){
    $(this).toggleClass("active");
    $(this).closest(".send-goods-msg").find(".goods-detaile-info").stop().slideToggle();
  })

</script>

</body>
</html>
