<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="us">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <style type="text/css">
        /* 提示框的字体颜色更改 */
        /* .layui-m-layercont{
          color: #fff;
        } */
    </style>
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
        footer{
            margin-bottom: 3rem;
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
            height: 4rem;
            font-size: 0;
            border: 1px solid #dedede;
            margin-left: 2%;
            text-align: center;
            overflow: hidden;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-img img{
            width: 100%;
            height: 100%;
            object-fit: contain;
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
            width: 1.4rem;
            height: 1.4rem;
            line-height: 1.4rem;
            font-size: 2rem;
            text-align: right;
            overflow: hidden;
         /*    background: url(./static/images/nav_close.png) center center no-repeat; */
            -webkit-background-size:  14px;
            background-size:  14px;
        }
        .user-inquiry-list .inquiry-list ul li .icon-messages{
        	position: relative;
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
        .user-inquiry-list .inquiry-list .myinquirylist-bottomnav{
          width:100%;
          height:3rem;
          background:#f7f7f7;
          position:fixed;
          bottom:3.4rem;
          left:0;
          z-index: 9;
        }
        .user-inquiry-list .inquiry-list .myinquirylist-bottomnav a{
          float:right;
          height:3rem;
          line-height:3rem;
        font-size:1rem;
          background:#ddd;
          text-align: center;
          width:35%;
          color:#fff;
        }
        .user-inquiry-list .inquiry-list .myinquirylist-bottomnav2 a{
          background:#043d81!important;
        }
        .user-inquiry-list .inquiry-list .myinquirylist-bottomnav input{
              margin: 1rem 0 0 0.625rem;
        }
        .user-inquiry-list .inquiry-list .myinquirylist-bottomnav p{
                 margin: 0.94rem 0 0 1rem;
      			font-size: 1rem;color:#999;
        }
        /* 询盘列表 end */

        /* new消息标签 */
        .inquiry-list-info .new-inquiry-message {
		    position: absolute;
		    display: inline-block;
		    bottom: 110%;
		    left: 30%;
		    width: auto;
		    line-height: 1.125rem;
		    padding: 0 2px;
		    border-radius: 2px;
		    color: #fff;
		    background: #f10;
	    }
	    /* new消息标签  小勾勾 */
	    .inquiry-list-info .new-inquiry-message:after {
		    content: "";
		    position: absolute;
		    display: inline-block;
		    width: 0;
		    height: 0;
		    bottom: -0.25rem;
		    left: 0.25rem;
		    border: 0.25rem solid transparent;
		    border-left-color: #f10;
		}
		.btn-to-public{
			display: inline-block;
			height: 1.8rem;
			line-height: 1.8rem;
			padding: 0 0.4rem;
			margin-right: 0.625rem;
			text-align: center;
			color: #fff;
			background: #043d81;
		}
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
                   <!--  My Account -->
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
            </div>
            <div class="user-inquiry-list">



                <div id="myinquirylist" v-cloak>
                 <div style="text-align: right;">
                 <a class="btn-to-public" href="/home/usr_UsrConsult_publishView">
                    <!-- my-inquiry-list.Publish_Query -->
                    <s:text name="my-inquiry-list.Publish_Query" />
              	  </a>
              	</div>  
                <div class="inquiry-list">

                    <ul v-if="inquirylist.length">
                        <li class="clearfloat"  v-for='(inquirylistdd,index) in inquirylist'>
                            <input type="checkbox" class="fl yigegexuan" :value="inquirylistdd.id" v-model="checkedNames" :data-index="index">
                            <div class="inquiry-list-img fl">
                            	<a :href="'/home/usr_UsrConsult_detailView?inquiry_id='+inquirylistdd.id">
	                                <img :src="'${envConfig.imageBaseUrl}'+inquirylistdd.image.split(',')[0]" alt="" v-if="inquirylistdd.image">
	                                <!-- <img src="./static/images/pic_180_03.jpg"  alt="" v-if="!inquirylistdd.image"> -->
                                </a>
                            </div>
                            <div class="fl inquiry-list-info">
                                <div class="inquiry-list-name clearfloat">
                                    <p class="fl">
                                    	<a :href="'/home/usr_UsrConsult_detailView?inquiry_id='+inquirylistdd.id">
                                           {{inquirylistdd.title}}
                                        </a>
                                    </p>
                                    <i class="icon-close fr" :data-ddid="inquirylistdd.id" :data-index="index" @click="clickdele">&times;</i>
                                </div>
<%--                                 <select name="" id="" v-if="inquirylistdd.relations.length"> --%>
<!--                                     <option value=""  v-for='inquirylistcc in inquirylistdd.relations'>{{inquirylistcc.supplierName}}</option> -->
<%--                                 </select> --%>
 								<p v-if="inquirylistdd.relations && inquirylistdd.relations.length">
		                                  		{{inquirylistdd.relations[0].supplierName}}
		                                  		</p>
		                                  		 <p v-else>no supplier</p>
                                <div class="inquiry-list-time clearfloat">
                                    <p class="fl">{{inquirylistdd.createTime}}</p>
                                    <a class="icon-messages fr" v-if="inquirylistdd.supplierCount > 0" :href="'/home/usr_UsrConsultMessage_dialogView?inquiry_id='+inquirylistdd.id">
                                    	<i class="new-inquiry-message" v-show="inquirylistdd.haveNewMsg"><!-- new --><s:text name="new" /></i>
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ul>

                    <p v-if="!inquirylist.length" style="padding:8rem 0;text-align: center;font-size:2rem; color:#999;"><s:text name="mobile.no_data" /><!-- NO DATA --></p>
                    <!-- 加载更多按钮 -->
                    <div class="more-button" @click="dianjifenye"  v-if="inquirylist.length && isLoadingBtnShow"  id="moreBtn">
                        <!-- Load more -->
                        <s:text name="mobile.load_more" />
                    </div>


                    <div class="myinquirylist-bottomnav" :class="[checkedNames.length>0 ? 'myinquirylist-bottomnav2':'']" >
                    	<input id="quan" type="checkbox" class="fl" @click="checkedAll"><p class="fl"><!-- All --><s:text name="all" /></p><a @click="clickdeleall"><!-- Clear --><s:text name="clear" /></a>
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
                    <a href="https://www.shoestp.com/account/" class="clean">
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
	var myinquirylist = new Vue({
	  el: '#myinquirylist',
	  data: {
	  	checkedNames:[],
  		start:0,
    	limit:10,
    	nengfou:1,
    	isLoadingBtnShow:true,
		inquirylist:[]

		 },

		  watch: { // 监视双向绑定的数据数组
                checkedNames: {
                    handler(){ // 数据数组有变化将触发此函数
                        if(this.checkedNames.length!= 0 && this.checkedNames.length == this.inquirylist.length){
                        	$("#quan").prop("checked",true)
                        }else {
//                             document.querySelector('#quan').checked = false;
                            $("#quan").prop("checked",false)
                        }
                    },
                    deep: true // 深度监视
                }
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
					if(res.data.result.items.length<this.limit){
						this.isLoadingBtnShow = false;
					}
					this.inquirylist.push(...res.data.result.items)

		       		if(res.data.result.items.length<=0 && this.start!= 0){
		       			layer.open({
		    			content: lang_obj.mobile.No_Data
		    			,skin: 'msg'
              ,style:"bottom: auto"
		    			,time: 2 //2秒后自动关闭
		 				 });
		 				 this.nengfou=0
		       		}
		            if(res.data.ret== -1 ){
		            	window.location.href='/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_listView';
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
		    			content: lang_obj.mobile.No_Data
		    			,skin: 'msg'
		    			,time: 2 //2秒后自动关闭
		 				 });
		  		}
		  	},
		  	 checkedAll: function(e) {
		  	 	var checkObj = document.querySelectorAll('.yigegexuan'); // 获取所有checkbox项
                    if(e.target.checked){ // 判定全选checkbox的勾选状态
                        for(var i=0;i<checkObj.length;i++){
                            if(!checkObj[i].checked){ // 将未勾选的checkbox选项push到绑定数组中
                                this.checkedNames.push(checkObj[i].value*1);
                            }
                        }
		  	 	}else{
		  	 		this.checkedNames=[]
		  	 	}

			  },
			 clickdele:function(e){
			 	let goods_index = e.target.dataset.index;
        let currentTarget = $(e.currentTarget);
        layer.open({
          content: lang_obj.cart.del_confirm
          ,btn: [lang_obj.global.n_y[1], lang_obj.global.n_y[0]]
          ,yes: (index)=>{
            axios.post('/home/usr_UsrConsult_delete',Qs.stringify({
    		    		id:currentTarget.data("ddid")*1
    				}, {allowDots: true}))
    				.then((res)=>{
    					if(res.data.ret== 1 ){
    						layer.open({
    		    			content: lang_obj.cart.batch_remove_success
    		    			,skin: 'msg'
    		    			,time: 2 //2秒后自动关闭
    		 				 });
    		 			}
    		 			// this.start-=1;
              // this.inquirylist.splice(goods_index,1)
              this.start=0
      				this.limit=4
    		 			this.inquirylist=[]
    					this.inquirylistload();
    				})
    				.catch((res)=>{
    				});
            layer.close(index);
          }
        });
			 },

			 clickdeleall:function(e){
         // no goods is selected,do nothing
         if( this.checkedNames.length == 0 ) return;
         layer.open({
           content: lang_obj.cart.del_confirm
           ,btn: [lang_obj.global.n_y[1], lang_obj.global.n_y[0]]
           ,yes: (index)=>{
             axios.post('/home/usr_UsrConsult_deletes',Qs.stringify({
     		    		ids:this.checkedNames.join(",")
     				}, {allowDots: true}))
     				.then((res)=>{
     					if(res.data.ret== 1 ){
                this.checkedNames=[];
     						layer.open({
     		    			content: lang_obj.manage.photo.move_success
     		    			,skin: 'msg'
     		    			,time: 2 //2秒后自动关闭
     		 				 });
     		 			}
     					this.start=0
       				this.limit=4
     		 			this.inquirylist=[]
     					this.inquirylistload();
     				})
     				.catch((res)=>{
     				});
             layer.close(index);
           }
         });
			 },
		  }
		})
</script>

</body>
</html>
