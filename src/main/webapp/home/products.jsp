<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="v3/header.jsp"/>
<link rel="stylesheet" href="/home/v3/static/css/productList.css">

<body>
<jsp:include page="v3/nav.jsp"></jsp:include>
<div id="new_navs">
		<index-top></index-top>
</div>

<script src="/home/v3/static/js/index-top.js">

</script>
<script>	 new Vue({
            el: "#new_navs"
        })</script>
<div id="productList" class="clearfix w1240">
	<!--分级导航-->
	<div class="topNav">
		<div class="h1"><a href="/">Home</a><i class="el-icon-arrow-right"></i></div>
		<blcok v-if="breadcrumbnav && breadcrumbnav.length>0">
		<div class="h1" v-for="(item,index) in breadcrumbnav" for-key="index">
			<a :href="'/home/pdt_PdtProduct?cated='+item.pkey" :class="breadcrumbnav.length-1==index?'now':''">{{item.name}}</a>
			<i class="el-icon-arrow-right" v-show="breadcrumbnav.length-1!=index"></i>
		</div>
		</blcok>
		<div class="h1"><a class="now" href="/" v-if="!breadcrumbnav || breadcrumbnav.length<=0"> All product</a></div>
	</div>
	<!--分级导航 end-->

	<!--页面左部分类导航-->
	<div class="leftNav fl">
		<h1>Related categories</h1>
		<div class="leftNav-box" v-for="(item,index) in classLists" for-key="index">
			<p class="b" :class="[item.value==cated?'active':'']"
					@click="categorySearch" :data-cated="item.value">
					<img class="leftNav-icon1" src="/home/v3/static/images/ico/icon_nx.png" alt="{{item.label}}" v-if="index==0"/>
					<img class="leftNav-icon1" src="/home/v3/static/images/ico/icon_nvx.png" alt="{{item.label}}" v-if="index==1"/>
					<img class="leftNav-icon1" src="/home/v3/static/images/ico/icon_tx.png" alt="{{item.label}}" v-if="index==2"/>
					{{item.label}}
			</p>
			<div class="h2" v-for="(item2,indextwo) in item.children" for-key="indextwo">
				<p :class="[item2.value==cated?'active':'']"><span @click="categorySearch" :data-cated="item2.value">{{item2.label}}</span><img class="pl-icon2" :class="[item2.xiala==2?'':'pl-icon22']" src="/home/v3/static/images/icon_down.png" alt=""  @click="xiala" :data-index="index" :data-indextwo="indextwo"/></p>
				<el-collapse-transition>
					<ul v-show="item2.xiala==2">
						<li v-for="item3 in item2.children" :class="[item3.value==cated?'active':'']"
              @click="categorySearch" :data-cated="item3.value">
              <!-- <a :href="'home/pdt_PdtProduct?cated='+item3.value">{{item3.label}}</a> -->
              {{item3.label}}
            </li>
					</ul>
			    </el-collapse-transition>
			</div>
		</div>

	</div>
	<!--页面左部分类导航 end-->


	<!--页面右部列表-->
	<div class="rightList fr">
		<div class="top">
			<div class="top-box">
				<p>Supplier Level<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt="" /></p><div class="i1"></div>
				<ul>
					<li data-selelv = "1" @click="seleLevel">
						<div class="s" :class="[selelv==1?'sele':'']"></div>Level 1
					</li>
					<li data-selelv = "2" @click="seleLevel">
						<div class="s" :class="[selelv==2?'sele':'']"></div>Level 2
					</li>
					<li data-selelv = "3" @click="seleLevel">
						<div class="s" :class="[selelv==3?'sele':'']"></div>Level 3
					</li>
				</ul>
			</div>

			<%-- <div class="top-box">
				<p>Export Countries<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt="" /></p><div class="i1"></div>
				<ul>
					<li v-for="(item,index) in 16" :key="index" :data-selecount="index+1" @click="seleCount">
						<div class="s" :class="[selecount==index+1?'sele':'']"></div>Safety Shoes{{index}}
					</li>
				</ul>
			</div>

			<div class="top-box">
				<p>Offline store<img class="pl-icon2" src="/home/v3/static/images/ico/icon_down.png" alt="" /></p><div class="i1"></div>
				<ul>
					<li v-for="(item,index) in 16" :key="index" :data-selestore="index+1" @click="seleStore">
						<div class="s" :class="[selestore==index+1?'sele':'']"></div>Safety Shoes{{index}}
					</li>
				</ul>
			</div> --%>

			<div class="top-box2" style="margin-left: 20px;">Min Order :
				<input class="w63" type="text" @blur="lessthan222" @keyup.enter="lessthan222" v-model="lessthan" placeholder="less than"  onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>
			</div>
			<div class="i0"></div>
			<div class="top-box2">Price :
				<input type="text" @blur="min222" @keyup.enter="min222" v-model="min" placeholder="min."  onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/> -
				<input type="text" @blur="min222" @keyup.enter="min222" v-model="max" placeholder="max."  onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>
			</div>

			<div class="btn-search" @click="search">search</div>
		</div>


			<div class="common">
				<!--产品-->
				<div class="common-box ripple slideInRight" v-for="(item,index) in productLists" :key="index" :style="{'animation-delay': index/10 + 's'}">
					<div class="common-boxslide">
						<div class="likebai" @click="shoucang" :data-num = "index" :data-id = "item.pdtId">
							<img src="/home/v3/static/images/ico/likebai.png" v-show="!item.eshrine"/>
							<img src="/home/v3/static/images/ico/likesele.png" v-show="item.eshrine"/>
						</div>
						<div class="block">
						    <el-carousel :arrow="item.picture.split(',').length > 1 ? 'hover':'never'"  height="197px" indicator-position="none" :autoplay="false">
						      <el-carousel-item v-for="item2 in item.picture.split(',')" :key="item">
						        <div class="h3">
								    <a :href="'/'+item.rewrite" target="_blank"><img :src="'https://image.shoestp.com/'+item2"/></a>
								</div>
						      </el-carousel-item>
						    </el-carousel>
  						</div>
					</div>
					<a :href="'/'+item.rewrite" class="common-boxtitle" target="_blank">
						<h1>
							<div class="ootit" v-show="item.pdtType"><img class="mtf4" src="/home/v3/static/images/ico/icon_o2o.png" alt="O2O"/>O2O</div>
							{{item.pdtName}}
						</h1>
						<div>
							<div class="fl">
								<h2>US <span>{{sysConfig.currency_symbol}}{{ item.price }}</span></h2>
								<div class="h3">Min.Order: {{item.minOrder}} pairs</div>
							</div>
							<div class="fr" style="width: 196px;">
								<div class="">
                  <div class="h3">Inner Material:</div> {{item.inner?item.inner:'No data'}}
                </div>
								<div class="">
                  <div class="h3">Sole Material:</div> {{item.sole?item.sole:'No data'}}
                </div>
								<div class="">
                  <div class="h3">Upper Material:</div> {{item.upper?item.upper:'No data'}}
                </div>
								<div class="">
                  <div class="h3">Appropriate Season:</div> {{item.season?item.season:'No data'}}
                </div>
								<div class="">
                  <div class="h3">Closed Way:</div> {{item.closed?item.closed:'No data'}}
                </div>
							</div>
						</div>
					</a>
					<div class="common-boxspan fr">
						<a class="h1" :href="'/home/usr_UsrSupplier_gtSupIndex?pkey='+item.supId" target="_blank"><%--<div class="year">{{item.enter}}YRS</div>--%>{{item.supName}}</a>
						<div>
							<img class="mr6 icon01" src="/home/v3/static/images/ico/icon_cert.png" alt="Certificate" />
							Certificate
							<div class="i"></div>
							<img class="mr6" src="/home/v3/static/images/ico/icon_svs.png" alt="SVS" />
							<!--<img class="mr6" src="./images/icon_svs_2.png" alt="SVS" />-->
							<!--<img class="mr6" src="./images/icon_svs_3.png" alt="SVS" />-->
							SVS
							<div class="i"></div>

						</div>

						<div>
							<div class="ww42">R&D：</div><el-rate v-model="5.0" disabled></el-rate>
						</div>
						<div>
							<div class="ww42">Output：</div><el-rate v-model="5.0" disabled></el-rate>
						</div>
						<div>
							<div class="ww42">Scale：</div><el-rate v-model="5.0" disabled></el-rate>
						</div>
						<div>
							{{item.originCountry}} ( {{item.originProvince}} )
						</div>

						<!-- <a class="btn" href="javascript:;" @click="addRFQ" :data-id = "item.pdtId">Contact Supplier</a> -->
						<a class="btn" target="_blank" :href="'/home/usr_UsrConsult_productPublishView?product_id='+item.pdtId" :data-id = "item.pdtId">Contact Supplier</a>
					</div>
				</div>
				<!--产品 end-->

			</div>

			<block v-show="productLists.length>0">
				<el-pagination
				    layout="prev, pager, next"
				    :total="allpage"
				    prev-text="Previous"
				    next-text="Next"
				    @current-change="current_change">
				 </el-pagination>
			 </block>

			 <div class="noData" v-show="noData">
			 	<h1>NO RESULT</h1>
			 	<p>There is no relevant content for the time being.</p>
			 </div>

		</div>

	</div>
	<!--页面右部列表  end-->

<div id="foot">
    <index-bottom></index-bottom>
</div>


<script src="/home/v3/static/js/index-bottom.js"></script>

<script>
	  function getParams(name, defaultValue) {
        var url = window.location.href;
        var l = url.lastIndexOf(name)
        if (l != -1) {
            var ll = url.indexOf("&",l);
            if (ll == -1 || l > ll) {
                ll = url.length
            }
            url = url.substring(l, ll);
            var result = url.split("=")
            if (result.length == 2) {
                switch (typeof defaultValue) {
                    case "number":
                        return parseInt(result[1]);
                    case "boolean":
                        return Boolean(result[1])
                    default:
                        return result[1];
                }
            }
        } else {
            if (defaultValue == 'NONE') {
                return null;
            }
            if (defaultValue == null) {
                return -1;
            }
            return defaultValue
        }
        return -1;
    }


    new Vue(
        {
            el:"#foot"
        }
    )
    new Vue({
        el: '#productList',
        data: {
        	selelv:"",
        	selecount:"",
        	selestore:"",
        	pName:'',
        	lessthan:'',
        	min:'',
        	max:'',
        	currentPage:1,
        	allpage:'',
        	noData:false,
        	productLists:[],
        	breadcrumbnav:[],
        	page:0,
			limit:8,
            classLists:[]
        },
        methods: {
			// 读取链接带参
        	GetQueryString(name){
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  unescape(r[2]); return null;
			},

			classList(e) { // 获取左边分类
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax', {
                    params: {
						page:1,
						limit:5
                    }
                })
                    .then((res) => {
                    	console.log(res.data.result)
                        this.classLists = res.data.result
                        for (var i in this.classLists) {
				            var children = this.classLists[i].children;
				            for (var j in children) {
				              this.$set(this.classLists[i].children[j],"xiala",2)
				            }
				         }
                    })
                    .catch((error) => {
                    	console.log("err")
                    });
            },
            // 获取左边分类
            productList(e) {
                axios.get('/home/pdt_PdtProduct_gtProductsIndexListAjax?v=3', {
                    params: {
						start:this.page,
						limit:this.limit,
						cate:this.cated,
						min:this.min,
						max:this.max,
						mOrder:this.lessthan,
						pName:this.pName,
						level:this.selelv,
						export:this.selecount,
						o2oAddress:this.selestore,
						orderfld:getParams("orderfld","MostPopular")
                    }
                })
                    .then((res) => {
                    	console.log(res.data.result)
                        this.productLists = res.data.result.items
                        this.allpage = res.data.result.totalCount
                        this.breadcrumbnav= res.data.result.breadcrumbnav
                        if(res.data.result.items.length<=0){
                        	this.noData=true
                        }else{
                        	this.noData=false
                        }
                    })
                    .catch((error) => {
                    	console.log("err")
                    });
            },
            // 左边列表下拉功能
            xiala(e) {
                var i = e.currentTarget.dataset.index;
                var j = e.currentTarget.dataset.indextwo;
                if(this.classLists[i].children[j].xiala==2){
                	this.$set(this.classLists[i].children[j],"xiala",1)
                }else{
					this.$set(this.classLists[i].children[j],"xiala",2)
                }
            },
             // 添加收藏呵取消收藏
            shoucang:function(e){
                // var index = e.currentTarget.dataset.num;
                // if(this.productLists[index].eshrine==false){
                // 	axios.get('/home/usr_UsrFavorites_addFavorite', {
	              //       params: {
	              //       	pdtPkey:e.currentTarget.dataset.id
	              //       }
	              //   })
                //     .then((res) => {
                //     	if(ret!=-1){
                //     		this.$set(this.productLists[index],"eshrine",true)
                //     	}
                //     })
                //     .catch((error) => {
								//
                //     });
								//
                // }else{
                // 	axios.get('/home/usr_UsrFavorites_addFavorite', {
	              //       params: {
	              //       	pdtPkey:e.currentTarget.dataset.id
	              //       }
	              //   })
                //     .then((res) => {
                //     	this.$set(this.productLists[index],"eshrine",false)
                //     })
                //     .catch((error) => {
								//
                //     });
                // }
								if (!isLogin) {
									 // user_obj.set_form_sign_in('', window.location.href, 1);
									 // return
									 user_obj.set_form_sign_in('', window.location.href, 1);
							 } else {

									axios.get('/home/usr_UsrFavorites_addFavorite', {
											params: {
												pdtPkey:e.currentTarget.dataset.id
											}
									})
									.then((res) => {
										this.$set(this.productLists[index],"eshrine",!this.productLists[index].eshrine)
										/* this.productLists[index].eshrine = !this.productLists[index].eshrine; */
									})
									.catch((error) => {

									});
							}
      		},

			//   页数加载
            current_change:function(currentPage){
                this.page = (currentPage-1)*this.limit;
                this.productList();
                document.body.scrollTop = 0
            	document.documentElement.scrollTop = 0
      		},

      		//   最小购买量失去焦点时触发
      		lessthan222:function(){
      			// if(this.lessthan!=''){
      			// 	this.productList();
      			// }
      		},
      		//   最小金额最大金额 失去焦点时触发
      		min222:function(){
      			// if(this.min>=0 && this.max>0){
      			// 	if(this.max>this.min){
      			// 		this.productList();
      			// 	}else{
      			// 		this.$message({
				    //       message: 'Max must be greater than Min',
				    //       type: 'warning'
				    //     });
      			// 	}
      			// }
      		},
      		//   商家等级筛选
      		seleLevel:function(e){
      			if(this.selelv==e.currentTarget.dataset.selelv){
      				this.selelv=""
      			}else{
      				this.selelv=e.currentTarget.dataset.selelv;
      			}
      			this.productList();
      		},
      		//   出口国筛选
      		seleCount:function(e){
      			if(this.selecount==e.currentTarget.dataset.selecount){
      				this.selecount=""
      			}else{
      				this.selecount=e.currentTarget.dataset.selecount;
      			}
      			this.productList();
      		},
      		//   展会在哪国筛选
      		seleStore:function(e){
      			if(this.selestore==e.currentTarget.dataset.selestore){
      				this.selestore=""
      			}else{
      				this.selestore=e.currentTarget.dataset.selestore;
      			}
      			this.productList();
      		},
					// 点击后才实现搜索
          search(){
            if(this.min>=0 && this.max>0 && this.max<this.min){
              this.$message({
                message: 'Max must be greater than Min',
                type: 'warning'
              });
            }else{
              this.productList();
            }
          },
					// 点击左侧分类时跳转
          categorySearch(e){
            this.cated = e.currentTarget.dataset.cated;
            this.page= 0;
            this.productList();
          },
      		//   添加到询盘
      		 addRFQ(e) {
		        if (!isLogin) {
		          user_obj.set_form_sign_in('', window.location.href, 1);
		          return
		        }
		        axios({
		          url: "/home/pdt_PdtConsultPdtList_add",
		          method: "post",
		          data: Qs.stringify({
		            product: e.currentTarget.dataset.id
		          })
		        }).then(function (data) {
		          if (data.data) {
		            if (data.data.ret && data.data.ret === 1) {
		              carWindow(data.data, lang_obj.global.inqadd)
		            }
		          }
		        })
		      },

        },
        mounted() {
        	this.pName = this.GetQueryString("Keyword")
        	this.cated = this.GetQueryString("cated")
			this.classList();
			this.productList();


        },
        watch : {
        	 // 输入监听
        	lessthan:function(val) {
            	this.lessthan = val;
        	},
        	min:function(val) {
            	this.min = val;
        	},
        	max:function(val) {
            	this.max = val;
        	},
    	}

    })
</script>

</body>

</html>
