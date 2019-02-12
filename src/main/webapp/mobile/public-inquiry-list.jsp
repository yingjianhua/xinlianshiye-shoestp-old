<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/mobile/template/style_import.jsp" %>
      <!--   <link href="../../mobile/static//css/style(2).css" rel="stylesheet" type="text/css"> -->
        <style type="text/css">
            .header_top .icon {
                margin-left: 5.3%;
                width: 5.3%;
                height: 2.6rem;
                line-height: 2.6rem;
            }
            .pro_list {
                margin: .625rem .625rem 0;
            }

            .pro_list .item {
                height: 8rem;
                overflow: hidden;
                margin-bottom: .625rem;
                padding-bottom: .75rem;
            }

            .pro_list .item .img {
                width: 5rem;
                height: 5rem;
                margin-left: .625rem;
                margin-top: 1rem;
                text-align: center;
                background: url(../../../images/loading.gif) no-repeat center;
                background-size: 20.1%;
                position: relative;
            }

            .pro_list .item .img img {
                max-height: 100%;
                vertical-align: middle;
            }

            .pro_list .item .img .icon_discount {
                width: 38px;
                height: 25px;
                color: #fff;
                line-height: 12px;
                padding-top: 5px;
                background: #E25505;
                position: absolute;
                top: 0;
                right: 0;
            }

            .pro_list .item .img .icon_discount b {
                font-size: 16px;
            }

            .pro_list .item .img .icon_discount_foot {
                width: 0;
                height: 0;
                border-left: 19px transparent solid;
                border-right: 19px transparent solid;
                border-top: 5px #E25505 solid;
                position: absolute;
                right: 0;
                top: 30px;
            }

            .pro_list .item .desc {
                margin-left: 6.5rem;
            }

            .pro_list .item .desc .name {
                line-height: 1rem;
                padding: .625rem;
                padding-left: 0;
            }

            .pro_list .item .desc .name a {
                font-size: .75rem;
                color: #333;
            }

            .pro_list .item .desc .price,
            .pro_list .item .desc .price span {
                line-height: 1.5rem;
                margin-top: .75rem;
                font-size: 1rem;
                color: #d60707;
            }

            .ui_border_b {
                border-bottom-width: 0;
            }
        </style>
        <style type="text/css">
            .new_inqview {
              margin-top: 0.6rem;
            }

            .new_inqview .na {
                display: inline-block;
                padding: 0 2%;
                height: 1.4rem;
                line-height: 1.4rem;
                background: #005AB0;
                color: #fff;
                border-radius: 3px;
            }
            #consultList .el-pagination{
                display:-webkit-box;
                display: -moz-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
                -webkit-justify-content:center;
                justify-content:center;
                -moz-box-pack:center;
                -webkit--moz-box-pack:center;
                box-pack:center;
            }
        </style>
    </head>

    <body>
       <%@ include file="/mobile/template/header.jsp" %>
       <div class="wrapper" id="consultList">
            <template v-if="inquiryList.items && inquiryList.items.length > 0">
            <div class="pro_list">
                <template  v-for="(item,index) in inquiryList.items">
                    <div class="item clean" style="height:auto; min-height:7rem; border-bottom:1px dashed #ccc;">
                        <div class="img fl pic_box" style="background:none;">
                            <!-- <img v-if="{{item.image}}" :src="{{item.image}}">
                                    <img v-else src="../../mobile/static//images/inqlogo.png"> -->
                            <%-- <img src="../../mobile/static//images/inqlogo.png"> --%>
                            <img  :src="'${envConfig.imageBaseUrl}'+(item.image?item.image.split(',')[0]:'../../mobile/static/images/loading.gif')">
                            <span>
                            </span>
                        </div>
                        <div class="desc">
                            <div class="name ui_border_b">
                                {{item.createTime}}
                                <br> Name: {{item.name}}
                                <br> Email: *******
                                <br> Message: {{item.content}}
                            </div>
                            <!-- <div class="new_inqview">
                                <a :href="'/mobile/view-inquiry.jsp?inquiry_id='+item.id" class="na">
                                    View Detail
                                </a>
                            </div> -->
                        </div>
                    </div>
                </template>
            </div>

            <!-- 当前页 class -->
            <!-- cur -->
            <!-- <div class="turn_page"> -->
            <!-- <a href="#" class="btn prev"  @click="prevOrNext(-1)">
                    &nbsp;
                </a>
                <a href="#" v-for="(item,index) in totalPages" :key="index" :class="{cur: item === currentPage}" @click="select(item)">
                    {{item}}
                </a>
                <a href="#" class="btn next" @click="prevOrNext(1)">
                    &nbsp;
                </a> -->

                <el-pagination background layout="prev, pager, next" @current-change="handleCurrentChange" small
                    :total="totalCount">
                </el-pagination>
            <!-- </div> -->

            <div class="blank10">
            </div>
           </template>
           <div v-else style="text-align: center;padding: 1rem;height: 200px;line-height: 200px;">
           	暂无询盘信息
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
            //    window.onload=function(){
            // 	   $.ajax({
            // 			type:'post',
            // 			url:'http://'+window.location.host+'/home/pdt_PdtConsult_list',
            // 			dataType:'json',
            // 			success:function(data){
            // 		    $("#consultList").html("");
            // 		   $.each(data.items,function(i,item){

            // 			var content=item.content;
            // 			if(item.content==null){
            // 			 content='暂无';
            // 			 };
            // 			 var photo='';
            // 			 if(item.photo==null){
            // 			 photo='.../../mobile/static//images/none.jpg';
            // 			 }else{
            // 			 photo=item.photo;
            // 			 }

            // 			  $("#consultList").append("<div class='item clean' style='height:auto; min-height:7rem; border-bottom:1px dashed #ccc;'>"
            // 	                    +"<div class='img fl pic_box' style='background:none;'>"
            // 	                    +"<img src='../../mobile/static/images/inqlogo.png'>"
            // 	                    +"<span>"
            // 	                    +"</span>"
            // 	                    +"</div>"
            // 	                    +"<div class='desc'>"
            // 	                    +"<div class='name ui_border_b'>Time:"+item.time+"<br>Name:"+item.name+"<br>Email:"+item.email+"<br>Message:"+item.msg+";	"
            // 	                    +"email me."
            // 	                    +"</div>"
            // 	                    +"<div class='new_inqview'>"
            // 	                    +"<a href='#' target='_blank' class='na'>View Detail</a>"
            // 	                    +"</div>"
            // 	                    +"</div>"
            // 	                    +"</div>");
            // 			});
            // 			}
            // 		})
            //    }

            var app = new Vue({
                el: '#consultList',
                data: {
                        // 询盘列表
                        inquiryList: [],
                        // 每页显示几个
                        limit: 8,
                        // 当前页数
                        currentPage: 1,
                        // totalPage:6,
                        // 分页起始
                        start: 0,
                        // 商品总数
                        totalCount:1
                },
                methods: {
                    getInquiryList(start,limit,currentPage) {
                        axios.get('/home/usr_UsrConsult_pagePublic',{ params: { //请求参数
                                    start: start,
                                    limit: limit
                                }}
                            )
                            .then((res) => {
                                this.inquiryList = res.data.result;
                                this.totalCount = res.data.result.totalCount;
                                // console.log(this.inquiryList)
                                // this.totalPage = res.data.result.totalPage;
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    },
                    handleCurrentChange(val) {
                        var start = (val-1) * this.limit + 1;
                        this.getInquiryList(start,this.limit);
                    }
                },
                created() {
                    this.getInquiryList(this.start,this.limit);
                },
                computed: {

                }
            })
        </script>
    </body>
    </html>
