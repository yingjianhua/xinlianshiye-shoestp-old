<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"/>
<body>
<jsp:include page="v3/nav.jsp"></jsp:include>
<script src="/home/v3/static/js/swiper.min.js"></script>
<main id="shoesTp">
    <index-top></index-top>
    <div class="indexbannercon">
        <el-carousel indicator-position="outside" height="224px" arrow="never">
            <el-carousel-item>
                <a href="/country/Romania-Pantofi-en-gros/romania-index-ro.html" target="_blank"><img
                        src="/home/v3/static/images/ljxbanner1.jpg" alt=""></a>
            </el-carousel-item>
            <el-carousel-item>
                <a href="/home/pdt_PdtProduct" target="_blank"><img src="/home/v3/static/images/ljxbanner2.jpg"
                                                                                           alt=""></a>
            </el-carousel-item>
            <el-carousel-item>
                <a href="/home/usr_UsrConsult_publicListView" target="_blank"><img
                        src="/home/v3/static/images/ljxbanner3.jpg" alt=""></a>
            </el-carousel-item>
        </el-carousel>
    </div>
    <!-- 林铁垣 -->
    <div id="lty">
        <div class="sanduan w1240 clearfix">
            <div class="box">
                <h1>Categories</h1>

                <div class="boxList" v-for="(item,index) in classLists" for-key="index">
                    <a class="h2" :href="'/home/pdt_PdtProduct?cated='+item.value" target="_blank">
                        <img class="leftNav-icon1" src="/home/v3/static/images/icon_nx.png" alt="{{item.label}}" v-if="index==0"/>
                        <img class="leftNav-icon1" src="/home/v3/static/images/icon_nvx.png" alt="{{item.label}}" v-if="index==1"/>
                        <img class="leftNav-icon1" src="/home/v3/static/images/icon_ntx.png" alt="{{item.label}}" v-if="index==2"/>
                        {{item.label}}
                        <i class="el-icon-arrow-right fr"></i>
                    </a>
                    <ul class="clearfix">
                        <li v-for="(item2,indextwo) in item.children" for-key="indextwo">
                            <a :href="'/home/pdt_PdtProduct?cated='+item2.value" target="_blank">{{item2.label}}</a>
                        </li>
                    </ul>
                </div>

            </div>

            <div class="box">
                <a href="/html/SVS/svs.jsp" target="_blank">
                <h1>SVS Certification System</h1>
                <div style="font-size: 13px;"><%--SVS Certification System will make buyers find their<br/> target Supplier
                    more
                    faster, accurate and time-saving<br/> than other way.--%>
                    The buyers could find their target supplier more faster, accurate and time-saving by SVS Certification System.
                </div>
                <div class="h3">
                    <img src="/home/v3/static/images/icion_svs_1d.png" alt=""/>
                    <img src="/home/v3/static/images/icion_svs_2d.png" alt=""/>
                    <img src="/home/v3/static/images/icion_svs_3d.png" alt=""/>
                </div>
                <div class="h4">
                    <ul>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs01.png"/>Genuine Manufacturer</li>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs03.png"/>Production Capacity</li>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs05.png"/>Product Quality</li>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs07.png"/>International Business Capacity</li>
                    </ul>
                    <ul>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs02.png" style="    margin: 0 6px 0 -2px;"/>R&D
                            Capacity
                        </li>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs04.png"/>Major Client</li>
                        <li><img class="fl" src="/home/v3/static/images/icon_svs06.png"/>Exhibition</li>
                    </ul>
                </div>
                </a>
                <a href="/html/SVS/svs.jsp" class="btn-see-all svs_learn_more">Learn More</a>
            </div>

            <div class="box nomr">
                <h1>RFQ</h1>
                <div class="fs14">Customization Service</div>
                <div class="h5">
                    <div class="boxListInput01">
                        <input type="text" v-model="RFQ_title" placeholder="What are you looking for"/>
                    </div>
                    <div class="boxListInput01">
                        <input class="w180" v-model="RFQ_quantity" type="text" placeholder="Quantity"/>
                        <div class="boxListSelect">
                            {{chooes}} <i class="el-icon-arrow-down fr"></i>
                            <ul>
                                <li :data-name="'Bag'" @click="chooesbtn">Pairs</li>
                                <li :data-name="'Bag'" @click="chooesbtn">Forty-Foot Container</li>
                                <li :data-name="'Bag'" @click="chooesbtn">Twenty-Foot Container</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <span>Select template type:</span>
                <ul class="clearfix h6">
                    <li><a href="/home/usr_UsrConsult_publishView">Request price</a></li>
                    <li><a href="/home/usr_UsrConsult_publishView">Request a sample</a></li>
                    <li class="nomr"><a href="/home/usr_UsrConsult_publishView">Request quotation details</a></li>
                </ul>

                <a :href="'/home/usr_UsrConsult_publishView?title='+encodeURIComponent(RFQ_title)+'&quantity='+RFQ_quantity"
                   class="boxListBtn01" target="_blank">Request For Quotation</a>
            </div>

        </div>

    </div>
    <!-- 徐世奇 -->
    <div id="mix" class="w_1240">
        <div id="xsq">
            <div class="wide">
                <!-- 展厅显示 -->
                <div class="goods-category-wrap">
                    <div class="goods-category-item stop-swiping">
                        <div class="flex-left-right">
                            <div class="descript-box">
                                <div class="big-title">O2O Show Room</div>
                                <div class="content-text">
                                    From our exhibitors,
                                    <br>
                                    selected by analysts
                                </div>
                                <a href="/html/o2o/introduceO2O.jsp" target="_blank"
                                   class="btn-see-all">Learn More</a>
                            </div>

                            <div class="right-goods-out-wrap swiper-container swiper-category-list">
                                <div class="slide-title">
                                    <a class="fr more">More ></a>
                                    Romanian Show Room
                                </div>

                                <!-- 展厅的具体商品 - 轮播数据 -->
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide" v-for="showRoomItem in showRoomGoodsList">
                                        <ul class="right-goods-wrap">
                                            <li class="item" v-for="(goods, index) in showRoomItem" v-if="index < 4">
                                                <a :href="goods.rewrite" target="_blank">
                                                    <img :src="image(goods.image)" :alt="goods.title" class="goods-pic">
                                                    <div class="goods-info-wrap">
                                                        <div class="goods-name">
                                                            <div class="ellipsis_2">
                                                                {{goods.title}}
                                                            </div>
                                                        </div>
                                                        <div class="goods-price">
                                                            US <span>{{sysConfig.currency_symbol}} {{goods.price}}  </span>
                                                        </div>
                                                        <div class="min-order">
                                                            Min.Order:{{goods.min_order}} pairs
                                                        </div>
                                                    </div>
                                                </a>
                                                <!-- <div class="hover-show">
													<div class="btn-group">
														<a class="btn-inquiry btn-blue" :href="'/home/usr_UsrConsult_productPublishView?product_id='+goods.id" target="_blank">
															Inquiry
														</a>
													</div>
												</div> -->
												<div class="hover-show">
													<div class="hover-text-wrap">
														<a :href="goods.rewrite" target="_blank" class="goods-name">
															<div class="ellipsis_2">
																{{goods.title}}
															</div>
														</a>
														<div class="goods-price">
															US <span>{{sysConfig.currency_symbol}}{{goods.price}}</span>
														</div>
														<div class="min-order">
															Min.Order:{{goods.min_order || 0}} pairs
														</div>
													</div>

													<div class="btn-group">
														<a class="btn-inquiry btn-blue":href="'/home/usr_UsrConsult_productPublishView?product_id='+goods.id" target="_blank">
															Inquiry
														</a>
													</div>
												</div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- 展厅的具体商品 - 轮播数据 - end -->
                            </div>
                        </div>


                        <!-- 轮播 - 缩略图 -->
                        <div class="por">
                            <div class="swiper-button-next swiper-button-white"></div>
                            <div class="swiper-button-prev swiper-button-white"></div>
                            <div class="swiper-container swiper-container-thumbs gallery-thumbs">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide" v-for="showRoom in showRoomPicList">
                                        <img :src="showRoom.imgUrl" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- 展厅显示 - end -->

                <!-- 询盘列表 -->
                <div class="RFQ-list-wrap">
                    <h3 class="RFQ-title">
                        RFQ List
                        <a class="fr more">More ></a>
                    </h3>

                    <ul class="RFQ-list">
                        <li class="RFQ-item" v-for="item in RFQList">
                            <img :src="item.country_logo" alt="" class="flag" :title="item.country">
                            <div class="summary">
                                <div class="ellipsis_1 descript" :title="item.title">{{item.title}}</div>
                                <div class="time">{{item.create_date}}</div>
                            </div>
                            <div class="order">
                                order: <span>{{item.quantity}}Pairs</span>
                            </div>
                            <div class="btn-quote">
                                Read More
                            </div>
                        </li>
                    </ul>
                </div>
                <!-- 询盘列表 - end -->
            </div>
        </div>
    </div>
    <!-- 夏超 -->
    <section id="xc" class="w_1240">
        <!-- 第一段 -->
        <div class="section1">
            <div class="title clearfix">
                <div class="fl clearfix">
                    <span class="fl">CrowdFunding</span>
                    <ul class="fl clearfix">
                        <li>Lower price in small number</li>
                        <li>Fashion Design will be published</li>
                    </ul>
                </div>
                <a target="_blank" href="https://www.shoestp.com/home/Activity_Romania_classifyactivity" class="fr">More
                    ></a>
            </div>
            <div class="product-list clearfix">
                <template v-for="(item, index) in CrowdFundingList" :key="index">
                    <div class="product-item">
                        <div class="product-img">
                            <a :href="item.url" target="_blank">
                                <img :src="item.imgUrl" alt="">
                            </a>
                        </div>
                        <div class="product-content">
                            <div class="product-info ellipsis_2">
                                <a :href="item.url" target="_blank">
                                    {{item.title}}
                                </a>
                            </div>
                            <div style="margin-top:20px;">
                                <div class="clearfix" style="color: #232323;">
                                    <span style="color: #e54544;" class="font-bold">{{item.salesVolume}}</span>
                                    <span class="font-bold" style="color: #b0b0b0;">sales volume</span>
                                    <span class="fr font-bold" style=" font-size: 14px;">{{item.percentage}}%</span>
                                </div>
                                <el-progress style="margin:10px 0" :percentage="item.percentage" color="#1a4091"
                                             :show-text="false"></el-progress>
                                <div>
                                    <img style="width:14px;" src="/home/v3/static/images/icon_sj.png" alt="">
                                    <span style=" font-size: 12px;color: #b0b0b0;">Remaining {{item.endTime}} days</span>
                                </div>
                            </div>
                            <!-- <div class="like-btn">
                                <img src="/home/v3/static/images/icon_like.png" alt="" style="width:21px;">
                            </div> -->
                        </div>
                    </div>
                </template>
            </div>
        </div>
        <!-- 第二段 -->
        <div class="section2 clearfix">
            <!-- 左边 -->
            <div class="product-list clearfix">
                <div class="default-item fir-item">
                    <h3>Recently Viewed</h3>
                    <p style="opacity: 0.5;margin-top: 15px;line-height: 20px;">
                        The goods you’ve <br>
                        serched at last time
                    </p>
                    <a href="/home/pdt_PdtProduct?orderfld=MostPopular" class="see-all fir-see-all" target="_blank">See
                        All</a>
                </div>
                <template v-for="(item, index) in MostPopular" :key="index">
                    <div class="product-item">
                        <div class="product-img">
                            <a :href="'/'+item.rewrite" target="_blank">
                                <img :src="image(item.picture)" alt="">
                            </a>
                        </div>
                        <div class="product-content">
                            <div class="product-info ellipsis_2">
                                <a :href="'/'+item.rewrite" target="_blank">
                                    {{item.pdtName}}
                                </a>
                            </div>
                            <div class="product-price">
                                <span style="color: #232323;">US</span>
                                <span style="color: #e54544;">{{sysConfig.currency_symbol}}{{item.price}}</span>
                            </div>
                            <div class="product-pairs">
                                Min.Order: {{item.minOrder}} pairs
                            </div>
                        </div>
                        <div class="inquiry-btn">
							<a :href="'/'+item.rewrite" target="_blank" style="color:#666;text-align: left;">
								<div class="ellipsis_2" style="font-size:12px;line-height: 20px;">{{item.name}}</div>
								<div style="font-size:18px;margin-top:10px;">
									<span style="color: #232323;">US</span>
									<span style="color: #e54544;">{{sysConfig.currency_symbol}}{{item.price}}</span>
								</div>
								<div style="font-size:12px;">
									Min.Order: {{item.minOrder}} pairs
								</div>
							</a>
							<a class="inquiry-a" :href="'/home/usr_UsrConsult_publishView?product_id='+item.id" target="_blank">
								Inquiry
							</a>
						</div>
                    </div>
                </template>
            </div>
            <!-- 右边 -->
            <div class="product-list clearfix">
                <div class="default-item sec-item">
                    <h3>Recommendation</h3>
                    <p style="margin-top: 15px;line-height: 20px;">
                        The similar goods you <br>
                        may like
                    </p>
                    <a href="/home/pdt_PdtProduct?orderfld=Sales" class="see-all sec-see-all" target="_blank">See
                        All</a>
                </div>
                <template v-for="(item, index) in HotSale" :key="index">
                    <div class="product-item">
                        <div class="product-img">
                            <a :href="'/'+item.rewrite" target="_blank">
                                <img :src="image(item.picture)" alt="">
                            </a>
                        </div>
                        <div class="product-content">
                            <div class="product-info ellipsis_2">
                                <a :href="'/'+item.rewrite" target="_blank">
                                    {{item.pdtName}}
                                </a>
                            </div>
                            <div class="product-price">
                                <span style="color: #232323;">US</span>
                                <span style="color: #e54544;">{{sysConfig.currency_symbol}}{{item.price}}</span>
                            </div>
                            <div class="product-pairs">
                                Min.Order: {{item.minOrder}} pairs
                            </div>
                        </div>
                        <div class="inquiry-btn">
							<a :href="'/'+item.rewrite" target="_blank" style="color:#666;text-align: left;">
								<div class="ellipsis_2" style="font-size:12px;line-height: 20px;">{{item.name}}</div>
								<div style="font-size:18px;margin-top:10px;">
									<span style="color: #232323;">US</span>
									<span style="color: #e54544;">{{sysConfig.currency_symbol}}{{item.price}}</span>
								</div>
								<div style="font-size:12px;">
									Min.Order: {{item.minOrder}} pairs
								</div>
							</a>
							<a class="inquiry-a" :href="'/home/usr_UsrConsult_publishView?product_id='+item.id" target="_blank">
								Inquiry
							</a>
						</div>
                    </div>
                </template>
            </div>
        </div>
    </section>
    <!-- 林华力 -->
    <div id="lhl_index">
        <!--News-->
        <div class="index_News">
            <p class="sub_title">Don't Miss <a href="javascript:void(0);" class="fr sub_title_more" style="display: none">More ></a></p>
            <div class="news_content">
                <div class="news_detail por" v-for="item,index in newsList">
                    <a :href="item.url" target="_blank">
                        <img :src="item.image" name="News_img" alt="">
                        <div class="news_laste" v-if="index != 2">
                            <span class="news_laste_text">Laste</span>
                        </div>
                        <h4 class="detail_title">{{item.title}}</h4>
                        <div class="detail_info">
                            <img src="/home/v3/static/images/icon_sj.png" name="news_icon" alt="">
                            <span class="detail-text">{{item.time}}</span>
                            <img src="/home/v3/static/images/icon_wz.png" name="news_icon" alt="">
                            <span class="detail-text">{{item.location}}</span>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <!--Our Strength-->
        <div class="index_strength">
            <h2 class="strength_title">Our Strength</h2>
            <div class="strength_content" v-for="item in strength">
                <div class="str_icon">
                    <img :src="item.icon" name="icon_our" alt="">
                </div>
                <h4 class="str_title">{{item.title}}</h4>
                <div class="str_text" v-for="por in item.info">
                    <img src="/home/v3/static/images/icon_right_red.png" name="icon_right_red" alt="">
                    <P class="str_text_p">{{por}}</P>
                </div>
            </div>
        </div>

    </div>
    <index-bottom></index-bottom>
</main>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
   	new Vue({
			el: "#shoesTp",
			data: {
				CrowdFundingList: [{
						url: "https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=977",
						imgUrl: "/home/v3/static/images/goods1.jpg",
						title: "Leather shoes male Korean version of the trend of leather shoes men's casual shoes autumn men's shoes tide",
						salesVolume: "600",
						percentage: "120",
						endTime: "1"
					},
					{
						url: "https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=870",
						imgUrl: "/home/v3/static/images/goods2.jpg",
						title: "Dingtai Boots Nice High Heel Shoes Womens Winter Boots",
						salesVolume: "420",
						percentage: "140",
						endTime: "1"
					},
					{
						url: "https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1145",
						imgUrl: "/home/v3/static/images/goods3.jpg",
						title: "Students wild winter hip hop Gaobang men's shoes high-top shoes men's Korean version of the tide shoes",
						salesVolume: "50",
						percentage: "10",
						endTime: "1"
					},
					{
						url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1054",
            imgUrl:"/home/v3/static/images/goods11.jpg",
            title:"Classic Fashion Cheap Flat Shoes Men Casual",
            salesVolume:"550",
            percentage:"69",
            endTime:"1"
					},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=845",imgUrl:"/home/v3/static/images/goods5.jpg",title:"Wholesale Summer New Design Beaches sandals Men's sandals",salesVolume:"500",percentage:"83",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1073",imgUrl:"/home/v3/static/images/goods6.jpg",title:"Ins tide shoes men's winter old shoes Korean version of ulzzang new color matching white shoes street shooting men's shoes leather shoes",salesVolume:"300",percentage:"60",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1143",imgUrl:"/home/v3/static/images/goods7.jpg",title:"High heels female autumn 2018 new rivet pointed 12 stiletto single shoes wild Baotou Roman sandals summer",salesVolume:"650",percentage:"81",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1026",imgUrl:"/home/v3/static/images/goods8.jpg",title:"New Style Ballet Shoes Wholesale Women Shoes Leather Flat Shoes",salesVolume:"200",percentage:"40",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1094",imgUrl:"/home/v3/static/images/goods9.jpg",title:"2019 summer soft composite bottom bow toe sandals Velcro",salesVolume:"600",percentage:"75",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1133",imgUrl:"/home/v3/static/images/goods10.jpg",title:"2018 new round head British wind small shoes low heel tassel fashion shoes patent leather wild children's shoes",salesVolume:"400",percentage:"66",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=822",imgUrl:"/home/v3/static/images/goods11.jpg",title:"Women Slippers Shoes Summer Female Fashion Shoes beach Shoes Slippers",salesVolume:"550",percentage:"110",endTime:"1"},
					// {url:"https://www.shoestp.com/home/prm_PrmGroupPurchase_getGroupPdt?pkey=1054",imgUrl:"/home/v3/static/images/goods12.jpg",title:"Classic Fashion Cheap Flat Shoes Men Casual",salesVolume:"550",percentage:"69",endTime:"1"},
				],
				// 林华立数据
				newsList: [{
					url: '/home/rfq_RFQConsult_ExpoRivaSchuhshow' +
                        '',
					image: '/home/v3/static/images/zh_img.png',
					title: 'Expo Riva Schuh',
					time: 'Jan 12-15, 2019',
					location: 'Italy'
				}, {
					url: '/home/rfq_RFQConsult_exhibitionshow',
					image: '/home/v3/static/images/zh_img2.png',
					title: 'FOOTWEAR SOURCING AT MAGIC',
					time: 'February 4-7, 2019',
					location: 'Las Vegas'
				}, {
					url: '/home/rfq_RFQConsult_guangjiaohuishow',
					image: '/home/v3/static/images/zh_img3.png',
					title: 'China Import and Export Fair',
					time: 'Apr.15-May.5, 2019',
					location: 'Guangzhou'
				}],
				strength: [{
					icon: '/home/v3/static/images/icon_our1.png',
					title: 'Variety of suppliers at here',
					info: ['Different type of shoes', 'Different price level', 'Different function shoes']
				}, {
					icon: '/home/v3/static/images/icon_our2.png',
					title: 'The lowest price in the whole network',
					info: ['We provide O2O-low price area to buyers', 'We pick quality shoes with price as lower as possible']
				}, {
					icon: '/home/v3/static/images/icon_our3.png',
					title: 'SVS Suppliers Standard',
					info: ['Including factors that buyers are much concerned about',
						'Showing the performance of manufacturers objectively '
					]
				}, {
					icon: '/home/v3/static/images/icon_our4.png',
					title: 'Offer many bussiness events',
					info: ['We could agent the three biggest exhibition;', 'We provide O2O progrom;',
						'We have choiceness of suppliers for promting excellent manufacture'
					]
				}],
				MostPopular: [], //左边数据
				HotSale: [], //右边数据
				// 林铁垣
				RFQ_title: "",
				RFQ_quantity: null,
				chooes: "Pairs",
				classLists: [],
				// 徐世奇
				showRoomGoodsList: [], // 获取展会 详情列表信息
					showRoomPicList: [ //展会轮播 缩略图片列表
						{
							imgUrl: "/home/v3/static/images/show-room_01.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_02.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_03.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_04.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_05.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_06.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_07.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_08.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_09.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_10.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_11.png",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_12.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_13.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_14.jpg",
						},
						{
							imgUrl: "/home/v3/static/images/show-room_15.jpg",
						},
					],

					RFQList: [ //询盘列表
						{
							id: 1,
							country: "Egypt",
							country_logo: "/home/v3/static/images/flag-egypt.png",
							title: "Do you have some sandals styles? pls kindly send me the photos.",
							quantity: 600,
							create_date: "03-02-2019",
						},
						{
							id: 2,
							country: "Netherlands",
							country_logo: "/home/v3/static/images/flag-netherlands.png",
							title: "I'm looking for some new Autumn-Winter styles,do you have？",
							quantity: 1200,
							create_date: "03-02-2019",
						},
						{
							id: 3,
							country: "Brazil",
							country_logo: "/home/v3/static/images/flag-brazil.jpg",
							title: "Do you have some sandals styles? pls kindly send me the photos.",
							quantity: 500,
							create_date: "03-02-2019",
						},
						{
							id: 4,
							country: "Singapore",
							country_logo: "/home/v3/static/images/flag-singapore.jpg",
							title: "We are looking for very cheap shoes styles, do you have?",
							quantity: 1500,
							create_date: "03-02-2019",
						},
						{
							id: 5,
							country: "Philippines",
							country_logo: "/home/v3/static/images/flag-philippines.jpg",
							title: "I want some kids boots, for girl.",
							quantity: 800,
							create_date: "03-02-2019",
						},
						{
							id: 6,
							country: "South Africa",
							country_logo: "/home/v3/static/images/flag-south-africa.png",
							title: "If my order is big, will your price become cheap? Price on your website is too high.",
							quantity: 2000,
							create_date: "03-02-2019",
						},
					],
			},
			mounted() {
				this.getMostPopular();
				this.getHotSale();
				this.classList();
				// 获取展会列表信息
				this.getShowRoomGoodsList();
			},
			methods: {
				image(v, params) {
					if (!v) {
						return ""
					}
					if (!params) {
						params = ""
					}
					return sysConfig.baseImageUrl + v + params
				},
				getMostPopular() {
					var self = this;
					axios.get('/home/pdt_PdtProduct_gtProductsIndexListAjax', {
							params: {
								orderfld: "MostPopular",
								order: true,
								page: 1,
								limit: 5,
								cated: -1,
								v: 3,
							}
						})
						.then(function (res) {
							console.log("getMostPopular");
							console.log(res);
							self.MostPopular = res.data.result.items;
							console.log(self.MostPopular)
						})
						.catch(function (error) {
							console.log(error);
						});
				},
				getHotSale() {
					var self = this;
					axios.get('/home/pdt_PdtProduct_gtProductsIndexListAjax', {
							params: {
								orderfld: "Sales",
								order: true,
								page: 1,
								limit: 5,
								cated: -1,
								v: 3,
							}
						})
						.then(function (res) {
							console.log(res);
							self.HotSale = res.data.result.items;
						})
						.catch(function (error) {
							console.log(error);
						});
				},
				// 林铁垣
				classList(e) { // 获取左边分类
					axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax', {
							params: {
								page: 1,
								limit: 5
							}
						})
						.then((res) => {
							this.classLists = res.data.result
						})
						.catch((error) => {
							console.log("err")
							//                      this.$message.error(error);
						});
				},
				chooesbtn(e) {
					this.chooes = e.currentTarget.dataset.name
				},
				// 徐世奇
					// 获取展会鞋子列表
					getShowRoomGoodsList() {
					// axios.get('/home/pdt_PdtProduct_getRandomPdt', {
					axios.get('/home/o2o_O2oRegistration_o2oList', {
							params: {
								start: 0,
								limit: 16 * 4
							}
						})
						.then((res) => {
							console.log("鞋子列表suc");
							console.log(res);
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							var shoesList = res.data.result;
							var showRoomGoodsList = [];
							// 将展会信息分组-循环显示 - 为了轮播
							for (var i = 0; i < 15; i++) {
								showRoomGoodsList.push(shoesList.splice(0, 4))
							}
							this.showRoomGoodsList = showRoomGoodsList;

							this.$nextTick(() => {
								this.galleryThumbs = new Swiper('#xsq .gallery-thumbs', {
									spaceBetween: 10,
									slidesPerView: 7,
									loop: true,
									freeMode: true,
									// loopedSlides: 1, //looped slides should be the same
									watchSlidesVisibility: true,
									watchSlidesProgress: true,
								});


								this.goodsCategorySwiper = new Swiper('#xsq .swiper-category-list', {
									spaceBetween: 10,
									speed: 1000,
									loop: true,
									// loopedSlides: 1, //looped slides should be the same
									navigation: {
										nextEl: '.swiper-button-next',
										prevEl: '.swiper-button-prev',
									},
									thumbs: {
										swiper: this.galleryThumbs,
									},
								});
							})

						})
						.catch((error) => {
							console.log(error);
						});
				},
			}
		})


</script>
<style>
    .svs_learn_more{
        display: block !important;
        background-color: rgb(26,64,145);
        font-size: 18px;
        padding: 8px;
        border-radius: 2px;
        text-align: center;
        color: #ffffff !important;
        width: 131px;
        margin: 44px auto;
    }
</style>
</body>
</html>
