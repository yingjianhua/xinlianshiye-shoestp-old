<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"></jsp:include>

<link rel="stylesheet" href="/home/v3/static/css/user/ureset.css" />
<link rel="stylesheet" href="/home/v3/static/css/user/uindex.css" />

<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css" />
<link rel="stylesheet" href="/home/v3/static/css/user/userIndeax.css" />


<style>
	/*左侧公用列表*/
	#shoesTp .user-menu {
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
		width: 180px;
		height: 285px;
		flex: none;
		background-color: #ffffff;
		border: solid 1px #dedede;
		padding: 8px 24px;
	}

	#shoesTp .user-menu .user-menu-title {
		height: 44px;
		line-height: 44px;
		font-weight: bold;
        font-size: 16px;
	}

	#shoesTp .user-menu .user-menu-item {
		height: 44px;
		line-height: 44px;
		border-bottom: solid 1px #dddddd;
		font-size: 14px;
	}

	#shoesTp .user-menu .user-menu-item:last-child {
		border: none;
	}

	#shoesTp .user-menu .user-menu-item a {
		display: -webkit-box;
		display: -ms-flexbox;
		display: flex;
		-webkit-box-pack: justify;
		-ms-flex-pack: justify;
		justify-content: space-between;
		-webkit-box-align: center;
		-ms-flex-align: center;
		align-items: center;
		color: #232323;
		text-decoration: none;
	}

	/*商家询盘中-上传图片时样式不直接用el的样式*/
	.sup-inquiry-upload-wrap .el-upload-list{
		display: none;
	}
</style>
</head>
<body class="bg-gray  page-personal-center">
<jsp:include page="v3/nav-nobody.jsp"
></jsp:include>
<div id="shoesTp"  class=" w_1240 ">
	<index-top></index-top>
	<main class="main">

		<div class="wide por clearfix flex-layout">
			<!-- 左侧 My-Accoung 列表 -->
			<%--<div class="por"--%>
			<%--style="width: 180px;height: 285px;	background-color: #ffffff;border: solid 1px #dddddd;z-index:9;flex:none;">--%>
			<%--left's content--%>
			<%--<br>--%>
			<%--isScale:{{isScale}}--%>
			<%--<br>--%>
			<%--type:{{inquiryList[nowInquiryIndex] && inquiryList[nowInquiryIndex].type}}--%>
			<%--<br>--%>
			<%--nowInquiryIndex:{{nowInquiryIndex}}--%>
			<%--<br>--%>
			<%--nowSupplierIndex:{{nowSupplierIndex}}--%>
			<%--<br>--%>
			<%--isInquiryLoadOver:{{isInquiryLoadOver}}--%>
			<%--<br>--%>
			<%--addProductCurrentPage:{{addProductCurrentPage}}--%>
			<%--<br>--%>
			<%--isUnread:{{isUnread}}--%>
			<%--<br>--%>
			<%--addProductSelectedPdtIds:{{addProductSelectedPdtIds}}--%>
			<%--<br>--%>
			<%--<div @click="showEditAddInformationDialog">showEditAddInformationDialog</div>--%>
			<%--<br>--%>
			<%--<br>--%>
			<%--inquiryKeyword:{{inquiryKeyword}}--%>
			<%--<div style="position: absolute;left: 95%;z-index: -1;" @click="alertPersonalShowTime">--%>
			<%--<img src="/home/v3/static/images/user/icon_gogo.png" alt="">--%>
			<%--</div>--%>
			<%--</div>--%>
			<div class="user-menu fl">
				<div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account
				</div>
				<div class="user-menu-item"><a href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
				<div class="user-menu-item"><a href="/home/usr_UsrMessages_center" style="color:#10389c;">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
				<div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
				<div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favourites <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
				<div class="user-menu-item"><a href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
			</div>

			<!-- 中间 All-Inquires 列表 -->
			<div class="inquires-list-wrap" :class="{isScale: isScale}">
				<!-- z-index定位会覆盖border，so又嵌套了一层 -->
				<div style="border: solid 1px #ddd;">
					<div class="inquires-header">
						<div class="inquires-header1">
							<div class="chooes-inquiry-option">
								<!-- options下拉选项 -->
								<el-popover popper-class="my-popover-inquiry"
											width="210" :visible-arrow="false"
											@show="showInquiresOption"
											v-model="isInquiresOptionShow">
									<ul class="pop-inquires-list">
										<li class="inquires-item title">My Services</li>
										<li class="inquires-item sub">
											<a target="_blank" href="/home/usr_UsrConsult_publishView" style="color: #7f7f7f;">
												<i class="el-icon-plus prefix-icon"></i>
												Submit RFQ
											</a>
										</li>
										<li class="inquires-item title">Inquiries</li>
										<li class="inquires-item sub" @click="chooesInquiryType()">
											All Inquires
											<el-badge v-if="inquiresOptionUnreadInfo.all"  :value="inquiresOptionUnreadInfo.all"></el-badge>
										</li>
										<li class="inquires-item sub" @click="chooesInquiryType(1)">
											RFQ
											<el-badge v-if="inquiresOptionUnreadInfo.t1" :value="inquiresOptionUnreadInfo.t1"></el-badge>
										</li>
										<li class="inquires-item sub" @click="chooesInquiryType(2)">
											Product Inquires
											<el-badge v-if="inquiresOptionUnreadInfo.t2" :value="inquiresOptionUnreadInfo.t2"></el-badge>
										</li>
										<li class="inquires-item sub" @click="chooesInquiryType(3)">
											<el-tooltip content="New Design" placement="right">
												<div>O2O Inquires</div>
											</el-tooltip>
											<el-badge v-if="inquiresOptionUnreadInfo.t3" :value="inquiresOptionUnreadInfo.t3"></el-badge>
										</li>
										<li class="inquires-item sub" @click="chooesInquiryType(4)">
											Supplier Inquires
											<el-badge v-if="inquiresOptionUnreadInfo.t4" :value="inquiresOptionUnreadInfo.t4"></el-badge>
										</li>
										<!-- <li class="inquires-item sub" @click="chooesInquiryType(5)">Trash</li> -->
									</ul>
									<div slot="reference">
										<b>{{inquiresType | optionsStatus2Text}}</b>
										<i class="el-icon-arrow-down" :class="{rotate: isInquiresOptionShow}"></i>
									</div>
								</el-popover>
							</div>

							<div class="is-read">
								<el-checkbox v-model="isUnread" @change="isReadChange">Unread</el-checkbox>
							</div>
						</div>

						<!-- header2 占位符 -->
						<div class="inquires-header2-placeholder"></div>

						<div class="inquires-header2">
							<el-input size="medium" placeholder="Please input the keywords"
									  @keyup.enter.native="searchInInquiryList"
									  v-model.trim="inquiryKeyword" class="input-with-select">
								<el-button slot="append" icon="el-icon-search" class="btn-search"
										   @click="searchInInquiryList"
								></el-button>
							</el-input>
						</div>
					</div>

					<!-- 手风琴效果 - 下拉加载事件 -->
					<div class="collapse-box" id="inquiry-collapse-list">
                        <%--this.relationPkey?this.nowInquiryIndex:--%>
						<el-collapse :value="showAccordionContacterIndex!==null?showAccordionContacterIndex:(inquiryList[0] && inquiryList[0].relations && inquiryList[0].relations.length)?0:-1" v-if="inquiryList.length">
							<el-collapse-item :class="{active: nowInquiryIndex==inquiryIndex}" :name="inquiryIndex"
											  v-for="(inquiry,inquiryIndex) in inquiryList"
                                              v-if="(inquiry.type==1 || !inquiry.isDeleteInLocal )"
											  :key="inquiry.pkey">
								<!-- 询盘列表头部 -->
								<template slot="title">
									<!-- 没有联系人时，click.stop -->
									<div class="collapse-title"
										 @click.stop = "(inquiry.type==1 && isScale)?viewInquiryDetail($event):''"
										 :data-inquiry-index="inquiryIndex"
										 v-if="inquiry.relations && inquiry.relations.length==0">
										<%--普通产品询盘(2)和私人展厅产品询盘(3)首图显示productImage--%>
										<template v-if="inquiry.type == 2 || inquiry.type == 3">
											<img class="goods-pic" alt="goods's pic"
												 :src="inquiry.productImage?(util_function_obj.image(inquiry.productImage,50,50, (inquiry.type==3?'/blur,r_5,s_20':''))):'/home/v3/static/images/user_no_img.png'">
										</template>
										<template v-else>
											<img class="goods-pic" alt="goods's pic"
												 :src="(inquiry.images && inquiry.images[0])?(util_function_obj.image(inquiry.images[0],50,50)):'/home/v3/static/images/user_no_img.png'">
										</template>
										<%--RFQ信息显示一行，其余显示两行--%>
										<div class="goods-info" :class="{'two-row': inquiry.type != 1}">
											<div class="goods-name">
												<div :class="inquiry.type == 1 ? 'ellipsis_1' : 'ellipsis_2'">{{inquiry.title}}</div>
											</div>
											<div class="status" v-if="inquiry.type==1">{{inquiry.verifyStatus | inquiryStatus2Text}}</div>
										</div>
										<transition name="el-fade-in">
											<div class="my-btn-normal btn-view"
												 v-show="!isScale && inquiry.type==1"
												 :data-inquiry-index="inquiryIndex"
												 @click.stop="viewInquiryDetail">view</div>
										</transition>
										<!-- 遮盖箭头 - 防止箭头的默认点击事件 -->
										<div class="mask"></div>
									</div>

									<div class="collapse-title" v-else
										 @click="(inquiry.type==1 && isScale)?viewInquiryDetail($event):''"
										 :data-inquiry-index="inquiryIndex">
										<%--普通产品询盘(2)和私人展厅产品询盘(3)首图显示productImage--%>
										<template v-if="inquiry.type == 2 || inquiry.type == 3">
											<img class="goods-pic" alt="goods's pic"
												 :src="inquiry.productImage?(util_function_obj.image(inquiry.productImage,50,50, (inquiry.type==3?'/blur,r_5,s_20':''))):'/home/v3/static/images/user_no_img.png'">
										</template>
										<template v-else>
											<img class="goods-pic" alt="goods's pic"
												 :src="(inquiry.images && inquiry.images[0])?(util_function_obj.image(inquiry.images[0],50,50)):'/home/v3/static/images/user_no_img.png'">
										</template>
										<%--<img class="goods-pic" alt="goods's pic"--%>
											 <%--:src="(inquiry.images && inquiry.images[0])?(util_function_obj.image(inquiry.images[0],50,50, (inquiry.type==3?'/blur,r_5,s_20':''))):'/home/v3/static/images/user_no_img.png'"--%>
											 <%--:data-inquiry-index="inquiryIndex">--%>
										<%--RFQ信息显示一行，其余显示两行--%>
										<div class="goods-info"  :class="{'two-row': inquiry.type != 1}"
											 :data-inquiry-index="inquiryIndex">
											<div class="goods-name">
												<div :class="inquiry.type == 1 ? 'ellipsis_1' : 'ellipsis_2'">{{inquiry.title}}</div>
											</div>
                                            <div class="status" v-if="inquiry.type==1">{{inquiry.verifyStatus | inquiryStatus2Text(true)}}</div>
										</div>
										<transition name="el-fade-in">
											<div class="my-btn-normal btn-view"
												 v-show="!isScale && inquiry.type==1"
												 :data-inquiry-index="inquiryIndex"
												 @click.stop="viewInquiryDetail">view</div>
										</transition>
									</div>
								</template>

								<!-- 每一个下拉的内容 -->
								<ul class="contact-list" v-if="inquiry.relations && inquiry.relations.length">
									<li class="contact-item" :class="{active: nowInquiryIndex==inquiryIndex && nowSupplierIndex==relationsIndex}"
										v-for="(relation,relationsIndex) in inquiry.relations"
										v-if="!relation.isDeleteInLocal"
										:key="relation.supplier.pkey">
										<el-badge is-dot :hidden="!(relation.unread || relation.quotation.isNew)" class="short-name">
											<img class="short-name"
												 :src="(relation.supplier && relation.supplier.logo)?util_function_obj.image(relation.supplier.logo, 50, 50):'/home/v3/static/images/supplier_no_img.png'"
												 :data-inquiry-id="inquiry.pkey"
												 :data-supplier-index="relationsIndex"
												 :data-inquiry-index="inquiryIndex"
												 @click="isScale?contactSupplier($event):''">
											<%--<div class="short-name" v-else--%>
												 <%--:data-inquiry-id="inquiry.pkey"--%>
												 <%--:data-supplier-index="relationsIndex"--%>
												 <%--:data-inquiry-index="inquiryIndex"--%>
												 <%--@click="isScale?contactSupplier($event):''">--%>
												<%--{{relation.supplier && relation.supplier.name && relation.supplier.name[0]}}--%>
											<%--</div>--%>
										</el-badge>

										<div class="contacter-info" :class="{'show-long-name': inquiry.type!=1 && !isScale}"
											 :data-inquiry-id="inquiry.pkey"
											 :data-supplier-index="relationsIndex"
											 :data-inquiry-index="inquiryIndex"
											 @click="isScale?contactSupplier($event):''">
											<div class="name ellipsis_1">
												{{relation.supplier.name}}
											</div>
											<img class="country"  v-if="relation.supplier.country && relation.supplier.country.flag"
												 :src="util_function_obj.image(relation.supplier.country.flag, 24, 14)" alt="flag">
										</div>

										<transition name="el-fade-in">
											<div class="show-group"
												 v-show="!isScale">
												<template v-if="inquiry.type==1">
													<div class="goods-name ellipsis_1">
														{{relation.quotation.title}}
													</div>
													<img class="goods-pic"
														 :src="(relation.quotation && relation.quotation.images && relation.quotation.images[0])?(util_function_obj.image(relation.quotation.images[0].url, 44)):'/home/v3/static/images/user_no_img.png'" alt="goods's pic">
													<div class="goods-spec">
														{{relation.quotation.minPrice}}-{{relation.quotation.maxPrice}} {{relation.quotation.currency.shortName}}
													</div>
													<div class="sample">
														{{relation.quotation.sample?"Have sample":"No sample"}}
													</div>
												</template>

												<div class="my-btn-normal btn-contact"
													 :data-inquiry-id="inquiry.pkey"
													 :data-supplier-index="relationsIndex"
													 :data-inquiry-index="inquiryIndex"
													 @click="contactSupplier">Contact Supplier</div>
											</div>
										</transition>

										<div class="more">
											<transition name="el-fade-in">
												<div v-show="isScale" class="time">
													{{ relation.quotation.createDate | dataFormat('yyyy-MM-dd')}}
												</div>
											</transition>
											<el-popover popper-class="my-popover-operate"
														v-model="inquiryList[inquiryIndex].relations[relationsIndex].showPopover">
												<ul class="pop-operate-list">
													<li class="operate-item"
														:data-quotation-pkey="relation.quotation.pkey"
														:data-inquiry-index="inquiryIndex"
														:data-relations-index="relationsIndex"
														@click="deleteInquiry">Delete</li>
													<li class="operate-item"
														:data-inquiry-index="inquiryIndex"
														:data-relations-index="relationsIndex"
														:data-supplier-pkey="relation.supplier.pkey"
														@click="addContact">Add Contact</li>
												</ul>
												<div slot="reference">
													<img class="icon-more" src="/home/v3/static/images/user/icon_more.png" alt="">
												</div>
											</el-popover>
										</div>

									</li>
								</ul>
							</el-collapse-item>
						</el-collapse>

						<!-- 没有数据时显示 -->
						<div class="no-data-wrap" v-else>
							<template v-if="isInquiryLisLoading">
								<i class="el-icon-loading"></i>
							</template>
							<template v-else>
								No Data Searched
							</template>
						</div>
					</div>
				</div>

				<!-- 向右滑动 - 放大 - 图片 -->
				<transition name="el-zoom-in-center">
					<img src="/home/v3/static/images/user/icon_gogo.png" alt="" class="icon-gogo"
						 v-show="isScale" @click="scale">
				</transition>
			</div>

			<!-- 右侧聊天框 -->
			<transition name="slide-right">
				<div class="chat-wrap" v-show="isScale && showChatBox && inquiryList.length">
					<div class="chat-header">
						<a class="name" target="_blank"
						   :href="'/home/usr_UsrSupplier_gtSupInfo?pkey=' + supplierDetail.pkey">
                            <!-- 有头像显示头像，没有头像显示首字母 -->
                            <img :src="supplierDetail.logo?util_function_obj.image(supplierDetail.logo, 50, 50):'/home/v3/static/images/supplier_no_img.png'" alt="" class="short-name"
                                 :class="{isShowMore: isShowMore}">
                            <%--<div class="short-name" v-else-if="supplierDetail.name"--%>
                                 <%--:class="{isShowMore: isShowMore}">--%>
                                <%--{{supplierDetail.name && supplierDetail.name[0]}}--%>
                            <%--</div>--%>
                            <%--<div class="short-name" v-else--%>
                                 <%--:class="{isShowMore: isShowMore}">--%>
                                <%--H--%>
                            <%--</div>--%>

							<div class="full-name" :class="{isShowMore: isShowMore, ellipsis_5: isShowMore, ellipsis_1: !isShowMore}">
								{{supplierDetail.name}}
							</div>
						</a>
						<div class="grow"></div>

						<div class="more" @click="chatShowMore">
							Show More <i class="el-icon-arrow-down" :class="{rotate:isShowMore}"></i>
						</div>

						<!-- show more更多信息 -->
						<el-collapse-transition>
							<div class="chater-more-info" v-if="isShowMore">
								<!-- 普通询盘 、 私人展厅 -->
								<div class="chater-more-info-inner" v-if="inquiryList[nowInquiryIndex] && (inquiryList[nowInquiryIndex].type==2 || inquiryList[nowInquiryIndex].type==3)">
									<!-- 头像 -->
									<div class="chater-header"></div>
									<!-- supplier信息 -->
									<ul class="basic-info-wrap">
										<li class="box-title">Basic information</li>
										<li class="basic-item">
											<div class="label">Country / Region:</div>
											<div class="content">
												<img  alt="" class="pic-flag" :src="util_function_obj.image(supplierDetail.countryFlag,26,18)">
												{{supplierDetail.country}}
											</div>
										</li>
										<li class="basic-item">
											<div class="label">Business type:</div>
											<div class="content">{{supplierDetail.businessType}}</div>
										</li>
										<li class="basic-item">
											<div class="label">Year established:</div>
											<div class="content">
												{{supplierDetail.yearEstablished | dataFormat('yyyy-MM-dd')}}
											</div>
										</li>
										<%--<li class="basic-item" v-if="supplierDetail.mainProducts">--%>
											<%--<div class="label">Main products:</div>--%>
											<%--<div class="content">{{supplierDetail.mainProducts}}</div>--%>
										<%--</li>--%>
										<%--<li class="basic-item" v-if="supplierDetail.location">--%>
											<%--<div class="label">Location:</div>--%>
											<%--<div class="content">{{supplierDetail.location}}</div>--%>
										<%--</li>--%>
										<li class="basic-item">
											<div class="label">EmployeeCount:</div>
											<div class="content">{{supplierDetail.employeeCount}}</div>
										</li>
										<li class="basic-item">
											<div class="label" style="width: 135px;">Annual export volume:</div>
											<div class="content">11{{supplierDetail.annualRevenue}}</div>
										</li>
										<li class="basic-item">
											<div class="label">Main markets:</div>
											<div class="content">{{supplierDetail.mainMarket}}</div>
										</li>
										<%--<li class="basic-item" v-if="supplierDetail.transactionCount">--%>
											<%--<div class="label">TransactionCount:</div>--%>
											<%--<div class="content">{{supplierDetail.transactionCount}}</div>--%>
										<%--</li>--%>
										<%--<li class="basic-item" v-if="supplierDetail.transactionAmount">--%>
											<%--<div class="label">Transaction amount:</div>--%>
											<%--<div class="content">{{supplierDetail.transactionAmount}}</div>--%>
										<%--</li>--%>
									</ul>
									<!-- supplier信息 - end -->

									<!-- 普通询盘基础信息 -->
									<div class="basic-details-wrap normal-inquiry-drop-down-wrap">
										<div class="box-title">Inquiry information</div>
										<div class="row-item product-info-box">
											<%--<img class="product-pic" alt="product's pic"--%>
												 <%--:src="(inquiryDetail.images && inquiryDetail.images[0]) ? (util_function_obj.image(inquiryDetail.images[0], 50, 50, (inquiryList[nowInquiryIndex].type==3?'/blur,r_5,s_20':''))) : '/home/v3/static/images/user_no_img.png'">--%>
											<img class="product-pic" alt="product's pic"
												 :src="(inquiryDetail.productImage) ? (util_function_obj.image(inquiryDetail.productImage, 50, 50, (inquiryList[nowInquiryIndex].type==3?'/blur,r_5,s_20':''))) : '/home/v3/static/images/user_no_img.png'">
											<div class="product-descript">
												{{inquiryDetail.title}}
											</div>
										</div>
										<div class="row-item">
											<span class="row-title-left">Quantity:</span>
											<span class="content row-content-right">
													{{inquiryDetail.quantity}} {{inquiryDetail.unit}}
												</span>
										</div>
										<div class="row-item" v-if="inquiryDetail.detail">
											<div class="row-title-left">Message:</div>
											<!-- <el-scrollbar style="height: 100%;" :native="false" wrapStyle="" wrapClass="" viewClass="" viewStyle="" noresize="false" tag="div"> -->
											<div class="content msg-content row-content-right">
												{{inquiryDetail.detail}}
											</div>
											<!-- </el-scrollbar> -->
										</div>
										<div class="row-item goods-photos" v-if="inquiryDetail.images && inquiryDetail.images.length">
											<div class="row-title-left">Photos:</div>
											<ul class="photos-list row-content-right">
												<li class="photo-item"
													v-for="picUrl in inquiryDetail.images"
													v-if="picUrl"
													:key="picUrl">
													<img class="product-pic" alt="product's pic"
														 :src="util_function_obj.image(picUrl, 50, 50)">
												</li>
											</ul>
										</div>
									</div>
									<!-- 普通询盘基础信息 - end -->
								</div>

								<!-- RFQ -->
								<div class="chater-more-info-inner"	v-if="inquiryList[nowInquiryIndex] && (inquiryList[nowInquiryIndex].type==1)">
									<!-- 头像 -->
									<div class="chater-header"></div>
									<!-- supplier信息 -->
                                    <ul class="basic-info-wrap">
                                        <li class="box-title">Basic information</li>
                                        <li class="basic-item">
                                            <div class="label">Country / Region:</div>
                                            <div class="content">
                                                <img  alt="" class="pic-flag" :src="util_function_obj.image(supplierDetail.countryFlag, 26, 18)">
                                                {{supplierDetail.country}}
                                            </div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label">Business type:</div>
                                            <div class="content">{{supplierDetail.businessType}}</div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label">Year established:</div>
                                            <div class="content">
                                                {{supplierDetail.yearEstablished | dataFormat('yyyy-MM-dd')}}
                                            </div>
                                        </li>
                                        <%--<li class="basic-item" v-if="supplierDetail.mainProducts">--%>
                                        <%--<div class="label">Main products:</div>--%>
                                        <%--<div class="content">{{supplierDetail.mainProducts}}</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="basic-item" v-if="supplierDetail.location">--%>
                                        <%--<div class="label">Location:</div>--%>
                                        <%--<div class="content">{{supplierDetail.location}}</div>--%>
                                        <%--</li>--%>
                                        <li class="basic-item">
                                            <div class="label">EmployeeCount:</div>
                                            <div class="content">{{supplierDetail.employeeCount}}</div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label" style="width: 135px;">Annual export volume:</div>
                                            <div class="content">11{{supplierDetail.annualRevenue}}</div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label">Main markets:</div>
                                            <div class="content">{{supplierDetail.mainMarket}}</div>
                                        </li>
                                        <%--<li class="basic-item" v-if="supplierDetail.transactionCount">--%>
                                        <%--<div class="label">TransactionCount:</div>--%>
                                        <%--<div class="content">{{supplierDetail.transactionCount}}</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="basic-item" v-if="supplierDetail.transactionAmount">--%>
                                        <%--<div class="label">Transaction amount:</div>--%>
                                        <%--<div class="content">{{supplierDetail.transactionAmount}}</div>--%>
                                        <%--</li>--%>
                                    </ul>
									<!-- supplier信息 - end -->

									<!-- RFQ基础信息 -->
									<div class="basic-details-wrap when-rfq">
										<div class="box-title c10389c" style="margin-bottom: 6px;">
											A Qutoation from sdx
										</div>
										<div class="box-title" style="margin-bottom: 2px;">
											Product Information
										</div>
										<ul class="product-info-wrap">
											<li class="info-item">
												<div class="label">Product Name:</div>
												<div class="content">
													{{quotationDetail.title}}
												</div>
											</li>
											<li class="info-item product-pic-wrap"
												v-if="quotationDetail.images && quotationDetail.images.length">
												<div class="label">Product Photos:</div>
												<div class="content">
													<img class="product-pic"  alt=""
														 :src="util_function_obj.image(imgUrl, 50, 50)"
														 v-if="imgUrl"
														 v-for="imgUrl in quotationDetail.images"
														 :key="imgUrl">
												</div>
											</li>
											<li class="info-item" v-if="quotationDetail.description">
												<div class="label">Product Detail:</div>
												<div class="content">
													{{quotationDetail.description}}
												</div>
											</li>
										</ul>

										<div class="box-title" style="margin-bottom: 4px;">
											Price Information
										</div>
										<ul class="price-info-wrap">
											<li class="info-item first-row">
												<div class="label">Quantity:</div>
												<div class="content">
													<span class="c10389c">{{quotationDetail.quantity}}</span> pairs
													<%--{{quotationDetail.currency && quotationDetail.currency.shortName || "pairs"}}--%>
												</div>
												<div class="label">Price:</div>
												<div class="content">
													{{quotationDetail.minPrice}}-{{quotationDetail.maxPrice}}
												</div>
											</li>
											<li class="info-item"
												v-if="quotationDetail.currency && quotationDetail.currency.shortName">
												<div class="label">Currency Unit:</div>
												<div class="content">{{quotationDetail.currency.shortName}}</div>
											</li>
											<li class="info-item" v-if="quotationDetail.validDate">
												<div class="label">Quote Validity Period:</div>
												<div class="content">
													{{quotationDetail.validDate | dataFormat('yyyy-MM-dd')}}
												</div>
											</li>
											<li class="info-item" v-if="quotationDetail.paymentTerms">
												<div class="label">Payment Terms:</div>
												<div class="content">{{quotationDetail.paymentTerms}}</div>
											</li>
											<li class="info-item" v-if="quotationDetail.shippingTerms">
												<div class="label">Shipping terms:</div>
												<div class="content">{{quotationDetail.shippingTerms}}</div>
											</li>
										</ul>



										<div class="box-title" style="margin-bottom: 4px;margin-top: 12px;">
											Quotation Supplemental Information
										</div>
										<ul class="price-info-wrap">
											<li class="info-item first-row">
												<div class="label">Whether to provide samples: </div>
												<div class="content">
													<span class="roundCheck checked" v-if="quotationDetail.sample"></span>
													{{quotationDetail.sample?"Yes":"No"}}
												</div>
											</li>

											<li class="info-item upDown" v-if="quotationDetail.companyProfile">
												<div class="label">Company Profile:</div>
												<div class="content">{{quotationDetail.companyProfile}}</div>
											</li>
											<li class="info-item upDown"
												v-if="quotationDetail.throwaways && quotationDetail.throwaways.length">
												<div class="label">Company Product Book:</div>
												<div class="content">
													<template
															v-for="productBook in quotationDetail.throwaways">
														<img class="company-book-item"  alt="product's book"
															 v-if="isImg(productBook.url)"
															 :src="util_function_obj.image(productBook.url, 200, 110)">
														<div v-else>
															<a :href="util_function_obj.image(productBook.url)" class="book-link">《{{productBook.name}}》</a>
														</div>
													</template>
												</div>
											</li>
										</ul>
									</div>
									<!-- RFQ基础信息 - end -->
								</div>

								<!-- 商家询盘 -->
								<div class="chater-more-info-inner" v-if="inquiryList[nowInquiryIndex] && (inquiryList[nowInquiryIndex].type==4)">
									<!-- 头像 -->
									<div class="chater-header"></div>
									<!-- supplier信息 -->
                                    <ul class="basic-info-wrap">
                                        <li class="box-title">Basic information</li>
                                        <li class="basic-item">
                                            <div class="label">Country / Region:</div>
                                            <div class="content">
                                                <img  alt="" class="pic-flag" :src="util_function_obj.image(supplierDetail.countryFlag, 26, 18)">
                                                {{supplierDetail.country}}
                                            </div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label">Business type:</div>
                                            <div class="content">{{supplierDetail.businessType}}</div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label">Year established:</div>
                                            <div class="content">
                                                {{supplierDetail.yearEstablished | dataFormat('yyyy-MM-dd')}}
                                            </div>
                                        </li>
                                        <%--<li class="basic-item" v-if="supplierDetail.mainProducts">--%>
                                        <%--<div class="label">Main products:</div>--%>
                                        <%--<div class="content">{{supplierDetail.mainProducts}}</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="basic-item" v-if="supplierDetail.location">--%>
                                        <%--<div class="label">Location:</div>--%>
                                        <%--<div class="content">{{supplierDetail.location}}</div>--%>
                                        <%--</li>--%>
                                        <li class="basic-item">
                                            <div class="label">EmployeeCount:</div>
                                            <div class="content">{{supplierDetail.employeeCount}}</div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label" style="width: 135px;">Annual export volume:</div>
                                            <div class="content">11{{supplierDetail.annualRevenue}}</div>
                                        </li>
                                        <li class="basic-item">
                                            <div class="label">Main markets:</div>
                                            <div class="content">{{supplierDetail.mainMarket}}</div>
                                        </li>
                                        <%--<li class="basic-item" v-if="supplierDetail.transactionCount">--%>
                                        <%--<div class="label">TransactionCount:</div>--%>
                                        <%--<div class="content">{{supplierDetail.transactionCount}}</div>--%>
                                        <%--</li>--%>
                                        <%--<li class="basic-item" v-if="supplierDetail.transactionAmount">--%>
                                        <%--<div class="label">Transaction amount:</div>--%>
                                        <%--<div class="content">{{supplierDetail.transactionAmount}}</div>--%>
                                        <%--</li>--%>
                                    </ul>
									<!-- supplier信息 - end -->

									<!-- 商家询盘 基础信息 -->
									<div class="basic-details-wrap store-inquiry-drop-down-wrap">
										<div class="box-title">Name of Inquiry:</div>
										<ul class="product-info-wrap">
											<li class="info-item">
												<div class="label wrap">{{inquiryDetail.title}}</div>
											</li>
											<li class="info-item">
												<div class="label">Purchase Quanity:</div>
												<div class="content">
													{{inquiryDetail.price}} {{inquiryDetail.unit}}
												</div>
											</li>

											<li class="info-item" v-if="inquiryDetail.extraRequest">
												<div class="label">Extra Request:</div>
												<div class="content">{{inquiryDetail.extraRequest}}</div>
											</li>

											<li class="info-item" v-if="inquiryDetail.detail">
												<div class="label">Message:</div>
												<!-- <el-scrollbar style="height: 100%;" :native="false" wrapStyle="" wrapClass="" viewClass="" viewStyle="" noresize="false" tag="div"> -->
												<!-- <div class="content msg-content"> -->
												<div class="content">
													{{inquiryDetail.detail}}
												</div>
												<!-- </el-scrollbar> -->
											</li>
										</ul>

										<%--少于5张时显示上传按钮--%>
										<div v-if="!inquiryDetail.images || (inquiryDetail.images && inquiryDetail.images.length < 5)">
											<el-upload
													class="upload-wrap sup-inquiry-upload-wrap"
													:on-success="addFileListSupplierSuccess"
													:before-upload="beforeAddFileSupplierUpload"
													action="/home/usr_Purchase_upload"
													list-type="picture">

												<span class="upload">Upload Photos</span> (Maximum of 5 phots)
											</el-upload>
										</div>

										<%--否则只显示名称--%>
										<div v-else style="margin-bottom: 10px;">
											Upload Photos:
										</div>

										<ul class="goods-pic-wrap" v-if="inquiryDetail.images && inquiryDetail.images.length">
											<li class="goods-pic-item"
												v-for="picUrl in inquiryDetail.images"
												v-if="picUrl"
												:key="picUrl">
												<img class="product-pic" :src="util_function_obj.image(picUrl,50)" alt="">
											</li>
										</ul>

										<!-- 当添加了商品时的显示 -->
										<div class="when-have-add"
											 v-if="inquiryDetail.productRequest && inquiryDetail.productRequest.length">
											<div class="box-title" style="margin-top: 12px;margin-bottom: 8px;">
												<span style="margin-right: 12px;">Product Details:</span>

												<el-button class="btn-add" type="primary" size="mini"
														   v-if="addProductSelectedPdtIds.length < 50"
														   @click="showAddProductDialog">
													Add Product
												</el-button>
											</div>
											<ul class="goods-pic-wrap">
												<li class="goods-pic-item"
													v-for="product in inquiryDetail.productRequest"
													:key="product.image">
													<img class="product-pic" :src="(product.image && product.image.split(',') && product.image.split(',')[0])?(util_function_obj.image(product.image.split(',')[0],50)):'/home/v3/static/images/user_no_img.png'" alt="">
													<div class="goods-name ellipsis_1">
														{{product.name}}
													</div>
												</li>
											</ul>
										</div>

										<!-- 没有add商品时显示 -->
										<div v-else>
											<div class="box-title" style="margin-top: 12px;margin-bottom: 8px;">
												Add Product
											</div>
											<div class="add-product-wrap">
												<div class="tip-title">
													Product Details
												</div>
												<div class="tip">
													<div>
														Your product request is currently empty.
														<br>
														Start adding now.
													</div>
												</div>
												<el-button class="btn-add" type="primary" size="mini"
														   @click="showAddProductDialog">
													Add Product
												</el-button>
											</div>
										</div>

									</div>
									<!-- 商家询盘 基础信息 - end -->
								</div>
							</div>
						</el-collapse-transition>
					</div>

					<div class="chat-box">
						<!-- 聊天内容框 -->
						<el-scrollbar ref="chatContentScroll" style="height: 100%;" :native="false"
									  noresize="false" tag="section">
							<div class="chat-content" ref="chatContent">
								<!-- 聊天框顶部supplier信息 -->
								<div @click="loadMoreChatInfo"
									 v-if="!isChatMsgListLoadOver && chatMsgList.length > 0"
									 style="text-align: center;position: relative;top: -12px;">
									<span style="color: #66b1ff;cursor: pointer;">Load more</span>
								</div>
								<div class="chater-info">
									LOCATION：
									<img  alt="" class="pic-flag" :src="util_function_obj.image(supplierDetail.countryFlag, 26, 18)"
										  v-if="supplierDetail.countryFlag">
									{{supplierDetail.location}}
									<!-- IP:115.218.107.* -->
									<div>Message only sent to you</div>
								</div>
								<!-- 聊天框内容 -->
								<ul class="chat-list">
									<li class="chat-item" :class="{mine: msg.p2S}"
										v-for="(msg,i) in chatMsgList"
										:key="msg.sendTime">
										<!-- 有头像显示头像，没有头像显示首字母 -->
										<template v-if="!msg.p2S">
											<img class="pic-head"
												 v-if="chatMsgObj.another.avatar"
												 :src="util_function_obj.image(chatMsgObj.another.avatar, 50)">
											<div class="pic-head" v-else-if="chatMsgObj.another.name">
												{{chatMsgObj.another.name[0]}}
											</div>
											<div class="pic-head" v-else>
												H
											</div>
										</template>
										<template v-else>
											<img class="pic-head"
												 v-if="chatMsgObj.myself.avatar"
												 :src="util_function_obj.image(chatMsgObj.myself.avatar, 50)">
											<div class="pic-head" v-else-if="chatMsgObj.myself.name">
												{{chatMsgObj.myself.name[0]}}
											</div>
											<div class="pic-head" v-else>
												M
											</div>
										</template>

										<div class="chat-item-info-box">
											<div class="time">
													<span class="mr5" v-if="!msg.p2S">
														{{chatMsgObj.another.name}}
													</span>
												{{msg.sendTime}}
											</div>
											<div class="content-msg-wrap">
												<!-- showAlert样式为 私人展厅第一次点击时弹出提示框 -->
												<el-badge is-dot :hidden="!(!msg.p2S && !msg.hadRead) || isAllRead">
													<div class="content-msg"
														 :class="{showAlert:msg.type==4 && !msg.personalShow.validDate}"
														 @click="(msg.type==4 && !msg.personalShow.validDate)?alertPersonalShowTime(msg.personalShow.linkUrl,msg.personalShow):''"
														 v-html="msg.content">
													</div>
												</el-badge>
												<!-- 倒计时 -->
												<div class="count-down"
													 v-if="msg.type==4 && msg.personalShow.validDate">
													<!-- 超时后的提示信息 -->
													<template v-if="msg.personalShow.countDown < 0">
														<span class="time">The link have expired</span>
													</template>
													<!-- 没有超时时显示倒计时信息 -->
													<template v-else>
														Countdown:
														<span class="time">{{msg.personalShow.countDown}}</span>
													</template>
												</div>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</el-scrollbar>
						<!-- 聊天内容框 - end -->
						<div class="send-msg-wrap">
							<el-input class="msg-input"
									  @focus="sendMsgInputFocus"
									  @keyup.enter.native="sendMsg"
									  v-model.trim="sendMsgValue" placeholder="Please input the message"></el-input>

							<el-upload style="margin-right: 10px;"
									   :show-file-list="false"
									   :on-success="chatAddImgSuc"
									   action="/home/usr_Purchase_upload">
								<el-button slot="trigger" icon="el-icon-plus" size="small" circle></el-button>
							</el-upload>

							<%--<el-button icon="el-icon-plus"  size="small" circle></el-button>--%>
							<el-button class="btn-send" type="primary" size="small" @click="sendMsg">send</el-button>
						</div>
					</div>
				</div>
			</transition>
			<!-- 右侧聊天框 - end -->

			<!-- view信息 - 询盘详情 + 报价详情 -->
			<transition name="slide-right">
				<div class="inquiry-detail-wrap"
                     v-show="isScale && showRFQDeailBox && !showChatBox && inquiryList.length"
                     v-cloak
                >
					<!-- 第一个框 - 询盘详情 -->
					<div class="inquiry-overview">
						<img class="inquiry-main-pic" alt=""
							 v-if="inquiryDetail.images"
							 :src="(inquiryDetail.images && inquiryDetail.images[0])?(util_function_obj.image(inquiryDetail.images[0], 100)):'/home/v3/static/images/user_no_img.png'">
						<div class="content-box-wrap">
							<div class="content-box1">
								<div class="content-box">
									<h3 class="content-header">{{inquiryDetail.title}}</h3>
									<ul class="content">
										<li class="item">
											Quantity required：{{inquiryDetail.quantity}} {{inquiryDetail.unit}}
										</li>
                                        <li class="item" v-if="inquiryDetail.price">
                                            Price：{{inquiryDetail.price}} {{inquiryDetail.currency}}
                                        </li>
										<li class="item">
											Expiration time：{{inquiryDetail.valieDate | dataFormat('yyyy-MM-dd hh:mm:ss')}}
										</li>
										<li class="item" v-if="inquiryDetail.paymentTerms">
											Payment：{{inquiryDetail.paymentTerms}}
										</li>
                                        <li class="item" v-if="inquiryDetail.destination">
                                            Destination：{{inquiryDetail.destination}}
                                        </li>
										<li class="item" v-if="inquiryDetail.shippingTerms">
											Shipping：{{inquiryDetail.shippingTerms}}
										</li>
									</ul>
								</div>
								<div class="operate-box">
									<div class="status">
										<template v-if="inquiryDetail.status == 4">Closed</template>
										<template v-else>
											{{inquiryDetail.verifyStatus | verifyStatus2Text}}
										</template>
									</div>
									<!-- 审核通过且有报价时不能编辑 -->
									<!-- 修改3次后不能编辑 -->
									<el-button size="mini"
											   :disabled="(inquiryDetail.verifyStatus == 3 && quotationDetailList.length) || (inquiryDetail.status == 4) || (inquiryDetail.changeCount && inquiryDetail.changeCount >=3)"
											   @click="showEditAddInformationDialog">
										Edit
									</el-button>
									<el-button size="mini"
											   :disabled="inquiryDetail.status == 4"
											   @click="closeRFQ">Close</el-button>
								</div>
							</div>

							<div class="content-box">
								<h3 class="content-header">Product detailed specifications:</h3>
								<div class="content">
									{{inquiryDetail.detail}}
                                    <template v-if="inquiryDetail.moreInformation">
										<div v-for="moreInfo in inquiryDetail.moreInformation">
											{{moreInfo}}
										</div>
									</template>
								</div>
							</div>

							<div class="content-box" v-if="inquiryDetail.images && inquiryDetail.images.length > 0">
								<h3 class="content-header">Attach files:</h3>
								<ul class="content attach-file-list">
									<li class="attach-file-item" v-for="picUrl in inquiryDetail.images" v-if="picUrl">
										<img v-if="isImg(picUrl)" :src="util_function_obj.image(picUrl, 100)" alt="product's pic" class="inquiry-pic">
										<!-- <p class="ellipsis_1">goods thiods things</p> -->
									</li>
									<%--<li class="attach-file-item" v-for="picUrl in inquiryDetail.images" v-if="picUrl">--%>
										<%--<a href="">else</a>--%>
									<%--</li>--%>
								</ul>
							</div>
						</div>
					</div>

					<!-- 第二个框 报价详情 -->
                    <transition name="el-zoom-in-top">
                        <template v-if="quotationDetailList.length > 0">
                            <div class="quotation-detail-box"
                                 v-for="(quotationDetail, quotationIndex) in quotationDetailList">
                                <div class="inquiry-detail-header"
                                     v-if="quotationIndex == 0 && inquiryList[nowInquiryIndex] && inquiryList[nowInquiryIndex].relations">
                                    All {{inquiryList[nowInquiryIndex].relations.length}} quotations comparison
                                </div>
                                <div class="content-box content-box01">
                                    <h3 class="content-header">Product Information</h3>
                                    <ul class="content">
                                        <li class="item" v-if="quotationDetail.title">
                                            <span class="title">Product name:</span>
                                            <span class="text product-name">{{quotationDetail.title}}</span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.description">
                                            <span class="title">Product details:</span>
                                            <span class="text">{{quotationDetail.description}}</span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.images && quotationDetail.images.length">
                                            <span class="title">Product image or file:</span>
                                            <div class="text">
                                                <img class="pic-item" alt="goods' pic"
                                                     :src="util_function_obj.image(goodsPic.url, 100)"
                                                     v-for="goodsPic in quotationDetail.images">
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                                <div class="content-box content-box02">
                                    <h3 class="content-header">Product Information</h3>
                                    <ul class="content">
                                        <li class="item" v-if="quotationDetail.quantity">
                                            <span class="title">Quantity: </span>
                                            <span class="text">
                                                    <span class="product-quantity">{{quotationDetail.quantity}}</span>
                                                    pairs
                                                </span>
                                        </li>
                                        <li class="item">
                                            <span class="title">Prices:</span>
                                            <span class="text product-name">
                                                    {{quotationDetail.minPrice}}-{{quotationDetail.maxPrice}}
                                                </span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.currency && quotationDetail.currency.shortName">
                                            <span class="title">Currency Unit:</span>
                                            <span class="text">{{quotationDetail.currency.shortName}}</span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.validDate">
                                            <span class="title">Quote validity period:</span>
                                            <span class="text">{{quotationDetail.validDate | dataFormat('yyyy-MM-dd hh:mm:ss')}}</span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.shippingTerms">
                                            <span class="title">Shipping terms:</span>
                                            <span class="text">{{quotationDetail.shippingTerms}}</span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.paymentTerms">
                                            <span class="title">Payment method:</span>
                                            <span class="text">{{quotationDetail.paymentTerms}}</span>
                                        </li>
                                    </ul>
                                </div>

                                <div class="content-box content-box03">
                                    <h3 class="content-header">Quotation supplemental information</h3>
                                    <ul class="content">
                                        <li class="item">
                                            <span class="title">Whether to provide samples: </span>
                                            <span class="text">
                                                    <span class="roundCheck checked" v-if="quotationDetail.sample"></span>
                                                    {{quotationDetail.sample?"Yes":"No"}}
                                                </span>
                                        </li>
                                        <li class="item" v-if="quotationDetail.companyProfile">
                                            <div class="title">Company Profile:</div>
                                            <div class="text company-profile">
                                                {{quotationDetail.companyProfile}}
                                            </div>
                                        </li>
                                        <li class="item" v-if="quotationDetail.throwaways && quotationDetail.throwaways.length">
                                            <div class="title">Company product book:</div>
                                            <div class="text books">
                                                <template
                                                    v-for="productBook in quotationDetail.throwaways">
                                                    <img class="company-book-item"  alt="product's book"
                                                         v-if="isImg(productBook.url)"
                                                         :src="util_function_obj.image(productBook.url, 200, 110)">
                                                    <div v-else>
                                                        <a :href="util_function_obj.image(productBook.url)" class="book-link">《{{productBook.name}}》</a>
                                                    </div>
                                                </template>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </template>
                    </transition>

                    <transition name="el-zoom-in-top">
						<el-button size="" class="view-more"
								   v-show="inquiryList[nowInquiryIndex] && inquiryList[nowInquiryIndex].relations.length && quotationDetailListIndex < (inquiryList[nowInquiryIndex].relations.length-1)"
								   @click="viewQuotationMoew">View More</el-button>
                    </transition>
				</div>
			</transition>
			<!-- view信息 - 询盘详情 + 报价详情 - end -->

            <!-- 没有搜索到信息时的 右侧占位图 -->
            <transition name="slide-right">
                <div class="no-data-wrap-in-right" v-show="inquiryList.length==0 && isScale && !isInquiryLisLoading">
                    <img class="img-placoholder" src="/home/v3/static/images/user/no_data2.png" alt="">
                </div>
            </transition>
            <!-- 没有搜索到信息时的 右侧占位图 -->
		</div>
	</main>

	<!-- 修改弹框 -->
	<el-dialog class="edit-form-dialog"
			   title="Add more information"
			   :visible.sync="isEditAddInformationDialogShow" >
		<div class="notice">
			Notice: Each RFQ is only allowed to add information three times
		</div>
		<el-form ref="addInformationForm" :model="addInformationForm" :inline="true">
			<el-form-item label="" class="textarea-wrap" prop="information"
						  :rules="[
						{ required: true, message: 'Please input the message', trigger: 'blur' },
						{ min: 1, max: 100, message: 'The message\' length should be within 1-100', trigger: 'blur' }
			    ]"
			>
				<el-input type="textarea" resize="none"
						  v-model.trim="addInformationForm.information">
				</el-input>
				<div class="font-num">{{addInformationForm.information.length || 0}}/100</div>
			</el-form-item>

			<el-form-item label="Valid to " >
				<el-date-picker type="date" placeholder="Select the end date"
								style="width: 100%;"
								value-format="yyyy-MM-dd HH:mm:ss"
								v-model="addInformationForm.validDate"
								:picker-options="validDatePickerOptions">
				</el-date-picker>
			</el-form-item>
		</el-form>

		<div slot="footer" class="dialog-footer">
			<el-button class="submit" type="primary" size="mini"
					   ref="addInformationForm"
					   @click="addInformationConfirm" >
				Submit
			</el-button>
			<el-button @click="cancelEditAddInformationDialog" size="mini">
				Cancel
			</el-button>
		</div>
	</el-dialog>
	<!-- 修改弹框 - end -->

	<!-- 添加商品 add product 弹框 -->
	<el-dialog class="add-product-dialog"
			   :show-close="false"
			   custom-class="add-product-dialog"
			   :visible.sync="addProductDialogVisible">
		<!-- 头部 -->
		<div class="content-header">
			<div class="drop-down-select">
				<b>ALL</b> <!-- <i class="el-icon-arrow-down"></i> -->
			</div>

			<el-input size="medium" placeholder="Please input the keywords" class="input-with-select"
					  style="width: 440px;"
					  @keyup.enter.native="searchInAddProductDialog"
					  v-model.trim="addProductKeyword">
				<el-button slot="append" icon="el-icon-search" class="btn-search"
						   @click="searchInAddProductDialog"
				></el-button>
			</el-input>
		</div>

		<!-- 中间的内容 -->
		<div class="content-body">
			<ul class="catogery-list">
				<li class="catogery-item"
					:class="{active: parentItem.value==addProductCatogeryValue}"
					v-for="(parentItem,parentIndex) in addProductCatogeryList"
					:data-cate-value="parentItem.value"
					@click="selectAddProductCatogery">
					{{parentItem.name}}
					<!-- <el-popover
                    placement="right-start"
                        :visible-arrow="false"
                        popper-class="add-product-sub-catogery-popover"
                        :value="parentIndex==2?true:false"
                    trigger="hover">
                        <ul class="sub-catogery-list">
                            <li class="sub-catogery-item" v-for="(subItem,subIndex) in parentItem.child">
                                {{subItem.name}}
                            </li>
                        </ul>

                        <div slot="reference">
                            {{parentItem.name}} <i class="el-icon-arrow-right"></i>
                        </div>
                  </el-popover> -->
				</li>

			</ul>
			<ul class="goods-list" v-if="addProductGoodsListObj.items && addProductGoodsListObj.items.length">
				<li class="goods-item"
					:class="{active: addProductSelectedPdtIds.indexOf(goods.pdtId)!=-1}"
					v-for="goods in addProductGoodsListObj.items"
					:data-product-id="goods.pdtId"
					@click="selectAddProductGoods">
					<img class="goods-pic" :src="(goods.picture && goods.picture.split(',') && goods.picture.split(',')[0])?util_function_obj.image(goods.picture.split(',')[0],100):'/home/v3/static/images/user_no_img.png'" alt="goods's pic">
					<div class="goods-name ellipsis_1">
						{{goods.pdtName}}
					</div>
				</li>
			</ul>
			<div class="tips" v-else>
				Goods that do no match for the keyWord
			</div>
		</div>

		<!-- 底部的内容 -->
		<div class="content-footer">
			<el-button type="primary" size="mini" class="btn-confirm"
					   @click="confirmAddProduct">
				Confirm
			</el-button>
			<el-button size="mini" class="btn-cancel"
					   @click="cancelAddProduct">
				Cancel
			</el-button>
			<div class="select-num">
				Selected
				<span class="c10389c">
						{{addProductSelectedPdtIds.length}}
					</span>/50
			</div>
			<div v-if="addProductGoodsListObj.totalCount">
				<el-pagination
						small
						prev-text="Previous"
						next-text="Next"
						layout="prev, pager, next"
						:page-size="addProductPageLimit"
						:current-page.sync="addProductCurrentPage"
						:total="addProductGoodsListObj.totalCount"
						@current-change="addProductPageChange">
				</el-pagination>
			</div>
		</div>
	</el-dialog>
	<!-- 修改弹框 - end -->

</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<div id="bottom" style="margin-top: 25px">
	<index-bottom></index-bottom>
</div>
<script>
	new Vue({
		el: "#bottom"
	})
</script>

<script>
	new Vue({
		el: "#shoesTp",
		data: {
			throttleTimer: null,	// 节流函数计时器
			timeoutTimer: null,	// 计时器 - 全局用
			isScale: false, //是否缩小状态
			showChatBox: false, //是否显示对话框box
			showRFQDeailBox: false, //是否显示RFQ详情box
			isShowMore: false, //是否显示更多 - 对话框顶部信息
			btnLoading: false, //按钮是否可以点击 - 报价详情 - more
            showAccordionContacterIndex: null, //从联系人列表过来时，手风琴列表显示对应的弹框

			//询盘列表信息
			inquiresType: null,	// inquires筛选选中的值 1.RFQ 2.普通询盘 3.私人 4.店铺询盘 其余为All
			isInquiresOptionShow: false,	// inquires是否显示下拉筛选
			inquiresOptionUnreadInfo: {},	// inquires下拉 对应的未读信息数量
			isUnread: false, //unread是否选中
            lastRelation: null, //从联系人处点击过来时，左侧列表直接加载至该联系人位置 - 跳转过来时才用该参数，否则不传该参数
			inquiryKeyword: "", //搜索信息
			inquiryList: [], //询盘列表信息
			inquiryLisPageStart: 0, //询盘列表 分页
			inquiryLisPageLimit: 10, //询盘列表 分页
			isInquiryLisLoading: false, //询盘列表 加载开关
			isInquiryLoadOver: false, //询盘列表 是否已加载完全

			// 列表选择 - 及详情
			nowInquiryIndex: 0, //当前显示询盘信息是哪一个
			nowSupplierIndex: 0, //当前显示对话框供应商所在下标 - sendMsg用
			consultPkey: 0,  //跟上面俩个下标相关，对应的值 - inquiryList[this.nowInquiryIndex].pkey
			supplierPkey: 0, //跟上面俩个下标相关，对应的值 - supplier.pkey
			relationPkey: 0, //跟上面俩个下标相关，对应的值 - quotation.pkey,

			inquiryDetail: {},  //当前 view 显示询盘信息详情
			supplierDetail: {},  //当前 view 显示供应商信息详情
			quotationDetailList: [],  //当前 view 显示询盘信息页面下的 报价详情
			quotationDetailListIndex: 0,  //view 的报价详情 已加载的下标数 - view more
			quotationDetail: {}, //show more时直显示1个的对象

			// edit => 添加信息 的弹窗form
			isEditAddInformationDialogShow: false, //是否显示修改弹窗
			addInformationForm: {
				validDate: '',
				information: ''
			},
			validDatePickerOptions:{		//valid date时间选择器options
				disabledDate(time) {
					// return time.getTime() < (Date.now()-24*60*60*1000);
					return time.getTime() < Date.now();
				},
			},

			//添加商品时的弹窗里的信息
			addProductDialogVisible: false, //是否显示添加商品弹窗
			addProductKeyword: "", //add product弹窗中方 搜索信息
			addProductCatogeryValue: 0, //add product中的选中分类value值 - 有的话搜索条件添加lose=1
			addProductPageStart: 0, //add product中 分页
			addProductPageLimit: 8, //add product中 分页
			addProductCurrentPage: 1, //add product中 分页 - element插件需要
			addProductGoodsListObj:{}, //添加商品时的 产品列表 - 含分页信息
			addProductSelectedPdtIds: [], //add product中 选中的商品id
			addProductSelectedPdtIdsOld: [], //add product中 选中的商品id - 取消选中时的备份
			addProductCatogeryList:[ //添加商品时的 分类列表 - 静态
				{name:"Men Shoes",value:373},
				{name:"Women Shoes",value:380},
				{name:"Child Shoes",value:387},
			],

			// 聊天信息
			chatMsgObj: {},  //聊天窗口信息
			chatMsgList: [], //聊天信息

			chatMsgListPageStart: 0, //聊天信息列表 分页
			chatMsgListPagePrePkey: null, //聊天信息列表 分页 - 防止信息重复获取 - load more
            chatMsgListPageNextPkey: null, //聊天信息列表 分页 - 防止信息重复获取 - 发消息时获取最新消息
			chatMsgListPageLimit: 20, //聊天信息列表 分页
			isChatMsgListLoading: false, //聊天信息列表 加载开关
			isChatMsgListLoadOver: false, //聊天信息列表 是否已加载完全

			isAllRead: false,  //信息是否已读
			sendMsgValue: "", //发送的内容
			sendMsgImgValue: "", //发送的图片地址

			isFromContactList: false, //显示联系人 聊天框（从联系人列表跳转过来时为true）
			testObj:{}
		},
		mounted() {
			//获取联系人列表跳转过来时携带的参数
			let whichPageFrom = sessionStorage.getItem("whichPageFrom")
			// let supplierPkey = sessionStorage.getItem("supplierPkey")
			// let consultPkey = sessionStorage.getItem("consultPkey")
			let relationPkey = sessionStorage.getItem("relationPkey")

			// 从联系人那边跳转过来时，显示聊天框
			// if(whichPageFrom=="contactsList" && supplierPkey && consultPkey && relationPkey){
			if(whichPageFrom=="contactsList" && relationPkey){
				console.log("从别的地方跳过来的")
				this.isFromContactList = true;
				this.isScale = true;
				this.showChatBox = true;
				// this.supplierPkey = supplierPkey;
				// this.consultPkey = consultPkey;
				// this.relationPkey = relationPkey;
				this.lastRelation = relationPkey; //从联系人跳转过来时才有 - 获取需询盘列表时需要
			}
			// 获取询盘列表
			this.getInquiryList();
		},
		methods: {
			// image(v, params) {
			// 	if (!v) {
			// 		return ""
			// 	}
			// 	if (!params) {
			// 		params = ""
			// 	}
			// 	return "https://image.shoestp.com" + v + params
			// },
			//
			// // 读取链接带参
			// GetQueryString(name){
			// 	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			// 	var r = window.location.search.substr(1).match(reg);
			// 	if(r!=null)return  unescape(r[2]); return null;
			// },

			// 时间戳转换时间
			dataFormatMethod: function (value, fmt) {
				if( !value ) return;
				let getDate = new Date(value);
				let o = {
					'M+': getDate.getMonth() + 1,
					'd+': getDate.getDate(),
					'h+': getDate.getHours(),
					'm+': getDate.getMinutes(),
					's+': getDate.getSeconds(),
					'q+': Math.floor((getDate.getMonth() + 3) / 3),
					'S': getDate.getMilliseconds()
				};
				if (/(y+)/.test(fmt)) {
					fmt = fmt.replace(RegExp.$1, (getDate.getFullYear() + '').substr(4 - RegExp.$1.length))
				}
				for (let k in o) {
					if (new RegExp('(' + k + ')').test(fmt)) {
						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
					}
				}
				return fmt;
			},

			// 大小缩小事件
			scale(){
				this.isScale=!this.isScale
			},

			// 节流节流
			throttle(cb, ms){
				var self = this;
				let startTime = Date.now()
				return function(){
					args = arguments;
					let currentTime = Date.now()
					let remaining = ms - (currentTime - startTime)
					clearTimeout(self.throttleTimer)
					if(remaining <= 0) {
						cb.apply(self, args);
						startTime = Date.now()
					}else {
						self.throttleTimer = setTimeout(() => {
							cb.apply(self, args);
					}, remaining)
					}
				}
			},

			// 滚动到底 实现加载 or fn
			scrollLoadMore(selector,callback){
				let self=this;
				const el = document.querySelector(selector);
				const offsetHeight = el.offsetHeight;
				el.onscroll = () => {
					const scrollTop = el.scrollTop;
					const scrollHeight = el.scrollHeight;
					if ((offsetHeight + scrollTop) - scrollHeight >= -1) {
						this.throttle(callback,1000)()
					}
				};
			},

			// 获取询盘列表
            //参数 isShowFirstOne: 为了功能-搜索后显示第一个
			getInquiryList() {
			    // 在缩小状态 - 且是重新加载时（reset使inquiryLisPageStart=0），显示第一个
                let isShowFirstOne = false;
                // 缩小情况下，且从第一个开始加载时（第一次加载），显示第一个
			    if( (util_function_obj.GetQueryString("showFirstInquiry") || this.isScale) && this.inquiryLisPageStart==0){
                    isShowFirstOne = true;
                }

				// 正在加载 or 已加载完全
				if(this.isInquiryLisLoading || this.isInquiryLoadOver) return;
				this.isInquiryLisLoading = true;

				// 参数拼接 - unread未勾选时不传
				var postData={
					keyword: this.inquiryKeyword,
					t: this.inquiresType,
					start: this.inquiryLisPageStart,
					limit: this.inquiryLisPageLimit
				}
				if( this.isUnread ){
					postData.unread = true;
				}
				//从联系人跳转过来时，该参数使列表加载至包含该联系人处 => 使该联系人处于当前列表处
				if( this.lastRelation ){
					postData.lastRelation = this.lastRelation;
				}
				axios.get('/home/rfq_RFQConsult_pageMine', {
					params: postData
				})
						.then((res) => {
					if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get inquiry list error,please try again later");
					return
				};

				this.isInquiryLisLoading = false;

				this.inquiryList.push(...res.data.result.items);

				// 判断是否加载完成（加载至最后一页）
				if (res.data.result.items.length < this.inquiryLisPageLimit) {
					this.isInquiryLoadOver = true;
				}else{
                    // 添加下拉加载事件
                    this.$nextTick(() => {
                        this.scrollLoadMore("#inquiry-collapse-list",this.loadMoreInquiryList)
                    });
                }

                // 从联系人列表中跳转过来时
                if(this.lastRelation){
                    // 不再执行默认展开第一个
                    isShowFirstOne = false;

                    // 确保下一页加载正确
                    if( res.data.result.limit > this.inquiryLisPageLimit ){
                        this.inquiryLisPageStart +=  (res.data.result.limit - this.inquiryLisPageLimit)
                    }

                    // 寻找目标对象（联系人列表中点击的对象）与询盘列表对应的下标
                    var resData = res.data.result.items;
                    listForEach:
                        for(var i = resData.length-1; i>=0; i--){
                            var relations = resData[i].relations;
                            if(relations && relations.length > 0){
                                for(var j=0,len2=relations.length;j<len2;j++){
                                    var relation = relations[j];
                                    if( relation.quotation && relation.quotation.pkey ){
                                        if( relation.quotation.pkey == this.lastRelation ){
                                            this.nowInquiryIndex = i;
                                            this.nowSupplierIndex = j;
                                            this.showAccordionContacterIndex = i;
                                            break listForEach;
                                        }
                                    }
                                }
                            }
                        }

					// 请求成功时！才清除其余页面跳转过来时的数据
					// 否则如果未登录 - 登录刷新后数据将消失
					this.removeOtherPageInfo();

                    // 找到联系人目标后，显示对应的联系人聊天框及相应内容
                    this.contactSupplier({
                        currentTarget:{
                            dataset:{
                                supplierIndex: this.nowSupplierIndex,
                                inquiryIndex: this.nowInquiryIndex
                            }
                        }
                    })
                }

				//为了功能-搜索后显示第一个
				if(isShowFirstOne){
				    this.showFirstOne(res.data.result.items[0]);
                }
			})
			.catch((error) => {
					this.isInquiryLisLoading = false;
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			// 清除其余页面跳转过来时的数据(联系人列表过来时保存的sessionStorage)
			removeOtherPageInfo(){
				sessionStorage.removeItem("whichPageFrom")
				// sessionStorage.removeItem("supplierPkey")
				// sessionStorage.removeItem("consultPkey")
				sessionStorage.removeItem("relationPkey")

				this.lastRelation = null;
				this.isFromContactList = false;
			},

            // 一进来后显示第一个
            showFirstOne(firstInquiryObj){
                // RFQ时显示询盘详情
                if(firstInquiryObj.type==1){
                    this.showChatBox = false;
                    this.showRFQDeailBox = true;
                    this.isScale = true;

                    //获取询盘详情
                    this.getInquiryDetail();
                    //获取报价详情 - 第一个
                    this.quotationDetailList=[];
                    this.quotationDetailListIndex=0;
                    this.getQuotationDetail();
                }else{
                    this.showChatBox = true;
                    this.showRFQDeailBox = false;
                    //获取chat列表
                    this.getChatInfo();
                    //获取询盘详情
                    this.getInquiryDetail();
                    //获取功能供应商详情
                    this.getSupplierDetail();
                }
            },

			// 加载下一页询盘列表
			loadMoreInquiryList(){
				this.inquiryLisPageStart += this.inquiryLisPageLimit;
				this.getInquiryList();
			},

			//reset 询盘信息
			resetInquiryOptions(){
				// this.showChatBox = false;
				// this.showRFQDeailBox = false;
				this.nowInquiryIndex = 0;
				this.nowSupplierIndex = 0;

				this.quotationDetailList = [];
				this.quotationDetailListIndex = 0;

				this.inquiryLisPageStart = 0;
				this.inquiryList = [];
				this.isInquiryLisLoading = false;
				this.isInquiryLoadOver = false;
                // 重置聊天信息
                this.resetChatMsg();
			},

			// 搜索事件
			searchInInquiryList(){
				this.resetInquiryOptions();
				this.getInquiryList();
			},

			//显示下拉选项 点击事件
			showInquiresOption(){
				axios.get('/home/rfq_RFQConsult_unreadCount')
						.then((res) => {
				if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get Inquiry's options error,please try again later");
					return
				};
				this.inquiresOptionUnreadInfo = res.data.result;
			})
			.catch((error) => {
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			//选择inquiry下拉type选项
			chooesInquiryType(type){
				type = type?type:null;
				this.isInquiresOptionShow = false;
				this.inquiresType = type;
				this.resetInquiryOptions();
				this.getInquiryList();
			},

			//unread 选中事件
			isReadChange(){
				this.resetInquiryOptions();
				this.getInquiryList();
			},

			// 点view 查看询盘详情及报价
			viewInquiryDetail(e){
				e.stopPropagation();
				var inquiryIndex = e.currentTarget.dataset.inquiryIndex;
				this.isScale = true;
				this.showChatBox = false;
				this.showRFQDeailBox = true;
				this.isFromContactList = false; //从联系人列表跳转过来时的值
				// 获取当前点击的inquiry下标
				this.nowInquiryIndex = inquiryIndex;
				//获取询盘详情
				this.getInquiryDetail();
				//获取报价详情 - 第一个
				this.quotationDetailList=[];
				this.quotationDetailListIndex=0;
				this.getQuotationDetail();
			},

			//获取询盘详情
			getInquiryDetail(){
				//先清空之前的数据
				this.inquiryDetail={};
				axios.get('/home/rfq_RFQConsult_detail', {
					params:{
						consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
					}
				})
						.then((res) => {
					if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get inquiry's detail error,please try again later");
					return
				};

				this.inquiryDetail = res.data.result;
				this.addInformationForm.validDate = this.dataFormatMethod(res.data.result.valieDate,"yyyy-MM-dd hh:mm:ss");

				// 获取已add的商品id集合
				if(res.data.result.productRequest && res.data.result.productRequest.length){
					this.addProductSelectedPdtIds=[];
					res.data.result.productRequest.forEach((val,index)=>{
						this.addProductSelectedPdtIds.push(val.pkey)
					})
					// 备份信息 - cancel时用
					this.addProductSelectedPdtIdsOld = [...this.addProductSelectedPdtIds];
				}
			})
			.catch((error) => {
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			// 判断传回来的数据是否是图片
			isImg(addrUrl){
				// 获取地址后缀
				let imgSuffix = addrUrl.substring(addrUrl.lastIndexOf(".") + 1, addrUrl.length);
				// 图片后缀数组 - 判断后缀是否在该数组内
				let imgSuffixArr = ["BMP","JPG","JPEG","PNG","GIF"];

				if(imgSuffix){
					imgSuffix = imgSuffix.toUpperCase();
					if( imgSuffixArr.indexOf(imgSuffix) != -1 ){
						return true
					}
					return false;
				}
				return false;
			},

			//获取功能供应商详情
			getSupplierDetail(){
				//先清空之前的数据
				this.supplierDetail={};

				axios.get('/home/usr_UsrSupplier_getDetail', {
					params:{
						supplierPkey: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].supplier.pkey
					}
				})
						.then((res) => {
					if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get supplier's detail error,please try again later");
					return
				};
				this.supplierDetail = res.data.result;

			})
			.catch((error) => {
					this.$message.error(error || 'Network error,please try again later');
			});
			},

			//供应商 上传图片 - upload之前
			beforeAddFileSupplierUpload(file){
				if (!sysConfig || !sysConfig.user) {
					util_function_obj.alertWhenNoLogin(this);
					return false
				}
				let size = file.size / 1024;
				if (size > 500) {
					this.$message.error('Image size cannot exceed 500k');
					return false;
				}
				return true;
			},

			//供应商 上传图片 - upload成功
			addFileListSupplierSuccess(resUpload, file){
				if (resUpload.ret != 1) {
					this.$message.error(resUpload.msg || "Images upload error,please try again later");
					return
				};
				axios.post('/home/rfq_RFQConsult_addImage', Qs.stringify({
					consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
					images: resUpload.result.url,
				}))
						.then((res) => {
					if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Images upload error,please try again later");
					return
				};
				// 图片上传成功后直接将图片正常显示出来，而不是el的样式显示出来
				this.inquiryDetail.images.push(resUpload.result.url);
				this.$message({
					message: 'Add image success!',
					type: 'success'
				});
			})
			.catch((error) => {
					this.$message.error(error || 'Network error,please try again later');
			});
			},

			//获取报价详情 - 显示在show more中时，直接显示聊天对象的报价信息
			getQuotationDetail(showMoreQuotationPkey){
				this.quotationDetail = {};
				var nowInquiry = this.inquiryList[this.nowInquiryIndex];
				if( !nowInquiry.relations || !nowInquiry.relations.length ) return;

				var quotationPkey = showMoreQuotationPkey || nowInquiry.relations[this.quotationDetailListIndex].quotation.pkey;
				axios.get('/home/rfq_RFQConsult_quotationDetail', {
					params:{
						quotationPkey: quotationPkey,
					}
				})
						.then((res) => {
					this.btnLoading = false;
				if(res.data.ret == -1){
					util_function_obj.alertWhenNoLogin(this);
					return false;
				}else if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get quotation's detail error,please try again later");
					return
				};

				// view more时有多个是push进数组，show more时直显示1个，所以赋值给对象
				if(showMoreQuotationPkey){
					this.quotationDetail=res.data.result;
				}else{
					this.quotationDetailList.push(res.data.result);
				}
			})
			.catch((error) => {
					this.btnLoading = false;
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			//获取更多报价详情
			viewQuotationMoew(){
				if(this.quotationDetailListIndex>=(this.inquiryList[this.nowInquiryIndex].relations.length-1)) return;
				this.btnLoading = true;
				this.quotationDetailListIndex++;
				this.getQuotationDetail();
			},

			// 显示修改弹窗 - 添加额外信息
			showEditAddInformationDialog (){
				this.isEditAddInformationDialogShow = true;
			},

			// 取消修改弹窗 - 添加额外信息
			cancelEditAddInformationDialog (){
				this.isEditAddInformationDialogShow = false;
				this.addInformationForm.information = "";
				// this.addInformationForm.validDate = "";
			},

			//确认修改 - 添加额外信息
			addInformationConfirm(){
				this.$refs.addInformationForm.validate((valid) => {
					if (valid) {
						axios.post('/home/rfq_RFQConsult_addInformation', Qs.stringify({
							consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
							information: this.addInformationForm.information,
							validDate: this.addInformationForm.validDate,
						}))
								.then((res) => {
									if(res.data.ret == -1){
										util_function_obj.alertWhenNoLogin(this);
										return false;
									}else if (res.data.ret != 1) {
							this.$message.error(res.data.msg || "Add information error,please try again later");
							return
						};
						this.$message({
							message: 'Add more information success',
							type: 'success'
						});
						this.isEditAddInformationDialogShow = false;
						this.addInformationForm.information = "";
						// 重新获取询盘列表
						// this.resetInquiryOptions();
						// this.getInquiryList();

						//获取询盘详情
						this.getInquiryDetail();
						//获取报价详情 - 第一个
						this.quotationDetailList=[];
						this.quotationDetailListIndex=0;
						this.getQuotationDetail();
					})
					.catch((error) => {
							this.isEditAddInformationDialogShow = false;
						this.$message.error(error || 'Network error,please try again later');
					});
					} else {
						console.log('error submit!!');
				return false;
			}
			});
			},

			//关闭RFQ
			closeRFQ(e){
				this.$confirm('Are you sure to close the inquiry?', {
					confirmButtonText: 'sure',
					cancelButtonText: 'cancel',
					customClass: "my-custom-element-alert-class",
					center: true,
				}).then(() => {
					axios.post('/home/rfq_RFQConsult_close', Qs.stringify({
						consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
					}))
							.then((res) => {
				if(res.data.ret == -1){
					util_function_obj.alertWhenNoLogin(this);
					return false;
				}else if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Close RFQ error,please try again later");
					return
				};
				this.$message({
					message: 'Close inquiry success',
					type: 'success'
				});
				//获取询盘详情
				this.getInquiryDetail();
				//获取报价详情 - 第一个
				this.quotationDetailList=[];
				this.quotationDetailListIndex=0;
				this.getQuotationDetail();
			})
			.catch((error) => {
					this.$message.error(error || 'Network error,please try again later');
			});

			}).catch((error) => {
					console.log(error);
			});
			},

			//删除询盘
			deleteInquiry(e){
				var inquiryIndex = e.currentTarget.dataset.inquiryIndex;
				var relationsIndex = e.currentTarget.dataset.relationsIndex;
				var quotationPkey = e.currentTarget.dataset.quotationPkey;

				axios.post('/home/rfq_RFQConsult_deleteQuotation', Qs.stringify({
					quotationPkey: quotationPkey,
				}))
						.then((res) => {
				if(res.data.ret == -1){
					util_function_obj.alertWhenNoLogin(this);
					return false;
				}else if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Delete inquiry error,please try again later");
					return
				};
				this.$message({
					message: 'Close quotation success',
					type: 'success'
				});

				// 重新获取询盘列表
				// this.resetInquiryOptions();
				// this.getInquiryList();

				//如果出错，下面代码先注释吧
				// 本地显示时 静态删除 - 不请求后台刷新
                //如果删除的是当前对话框or当前显示的RFQ详情，delete后刷新页面
                if(this.nowInquiryIndex == inquiryIndex && this.nowSupplierIndex == relationsIndex){
                    this.resetInquiryOptions();
                    this.getInquiryList();
                // 否则本地显示时 静态删除 - 不请求后台刷新
                }else{
                    this.$set(this.inquiryList[inquiryIndex].relations[relationsIndex],"isDeleteInLocal",true);
                    this.$set(this.inquiryList[inquiryIndex],"isDeleteInLocal",true);
                }
			})
			.catch((error) => {
					console.log(error);
			});
			},

			//添加至联系人
			addContact(e){
				var dataset = e.currentTarget.dataset;
				var inquiryIndex = dataset.inquiryIndex;
				var relationsIndex = dataset.relationsIndex;
				var supplierPkey = dataset.supplierPkey;
				this.inquiryList[inquiryIndex].relations[relationsIndex].showPopover=false;

				axios.post('/home/rfq_RFQContact_add', Qs.stringify({
					supplierPkey: supplierPkey,
				}))
						.then((res) => {
				if(res.data.ret == -1){
					util_function_obj.alertWhenNoLogin(this);
					return false;
				}else if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Add contact error,please try again later");
					return
				};
				this.$message({
					message: 'Add contact success',
					type: 'success'
				});
			})
			.catch((error) => {
					this.$message.error(error || 'Network error,please try again later');
			});
			},

			test(){
				console.log("test")
			},

			// 点击联系相应的供应商
			contactSupplier(e){
				// 显示顶部show more信息
				clearTimeout(this.timeoutTimer);
				this.timeoutTimer = setTimeout(()=>{
					// 聊天信息大于8条时，不再自动显示show more
					if( this.chatMsgList.length < 8 ){
						this.isShowMore = true;
					}else{
						this.isAllRead = true;
					}
					clearTimeout(this.timeoutTimer);
				}, this.showChatBox?200:1000)

				this.isScale = true;
				this.isFromContactList = false; //从联系人列表跳转过来时的值

				var dataset = e.currentTarget.dataset;
				// var inquiryId = dataset.inquiryId;
				var supplierIndex = dataset.supplierIndex;
				var inquiryIndex = dataset.inquiryIndex;
				// 当前点击的就是当前显示的，不触发点击事件 - 首次加载显示第一个，此时也可以点第一个，so != 0
				if(this.nowSupplierIndex==supplierIndex && this.nowInquiryIndex == inquiryIndex && this.nowInquiryIndex != 0 && Object.keys(this.supplierDetail).length != 0 && this.showChatBox) return;
				this.showChatBox = true;
				this.showRFQDeailBox = false;
				// 重置聊天信息
				this.resetChatMsg();
				this.nowSupplierIndex = supplierIndex;
				this.nowInquiryIndex = inquiryIndex;
				// 左侧列表将点击项 设置为已读状态
				this.$set(this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation, "isNew",false)
				this.$set(this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex], "unread",false)
				//获取chat列表
				this.getChatInfo();
				// 如果是RFQ，show more显示报价详情、其余显示询盘详情
				if(this.inquiryList[inquiryIndex].type==1){
					// RFQ时获取报价详情
					this.getQuotationDetail(this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation.pkey);
				}else{
					//获取询盘详情
					this.getInquiryDetail();
				}
				//获取功能供应商详情
				this.getSupplierDetail();
			},

			// 显示添加商品的dialog
			showAddProductDialog(){
				this.addProductDialogVisible = true;
				this.getAddProductList();
			},

			// 获取add product商品列表
			getAddProductList(){
				axios.get('/home/pdt_PdtProduct_gtProductsIndexListAjax', {
					params: {
						v: 3,
						lose: this.addProductCatogeryValue?1:0,
						cate: this.addProductCatogeryValue,
						pName: this.addProductKeyword,
						supplier: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].supplier.pkey,
						// IsO2o: 1,
						start: this.addProductPageStart,
						limit: this.addProductPageLimit
					}
				})
						.then((res) => {
				if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get product's list error,please try again later");
					return
				};
				this.addProductGoodsListObj = res.data.result;
			})
			.catch((error) => {
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			//分页加载 - 更改页数
			addProductPageChange(page){
				this.addProductPageStart = (page-1) * this.addProductPageLimit;
				this.getAddProductList();
			},

			//add product中选中某catogery
			selectAddProductCatogery(e){
				this.addProductCurrentPage = 1; //reset - 分页插件显示第一页
				this.addProductPageStart = 0; //reset
				this.addProductKeyword = ""; //reset
				this.addProductCatogeryValue = e.currentTarget.dataset.cateValue;
				this.getAddProductList();
			},

			//add product商品搜索
			searchInAddProductDialog(){
				this.addProductCurrentPage = 1; //reset - 分页插件显示第一页
				this.addProductPageStart = 0; //reset
				this.addProductCatogeryValue = 0;  //reset 不以分类为搜索条件
				this.getAddProductList();
			},

			//选中add product商品
			selectAddProductGoods(e){
				var selectPdtId = Number(e.currentTarget.dataset.productId);
				var selectPdtIndex = this.addProductSelectedPdtIds.indexOf(selectPdtId);
				if( selectPdtIndex != -1 ){
					this.addProductSelectedPdtIds.splice(selectPdtIndex,1)
				}else if( this.addProductSelectedPdtIds.length >= 50 ){
					return
				}else{
					this.addProductSelectedPdtIds.push(selectPdtId);
				}
			},

			//确认选中add product商品
			confirmAddProduct(e){
				axios.post('/home/rfq_RFQConsult_addProductRequest', Qs.stringify({
					products: this.addProductSelectedPdtIds.join(),
					consultPkey: this.inquiryList[this.nowInquiryIndex].pkey
				}))
						.then((res) => {
					if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Add products error,please try again later");
					return
				};

				this.$message({
					message: 'Add products success!',
					type: 'success'
				});

				this.resetAddProduct();
				this.getInquiryDetail();
			})
			.catch((error) => {
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			//取消 add product弹窗
			cancelAddProduct(e){
				this.resetAddProduct();
				this.addProductSelectedPdtIds = [...this.addProductSelectedPdtIdsOld]; //取消选中显示初始值备份
			},

			//reset AddProduct弹窗
			resetAddProduct(){
				this.addProductDialogVisible = false;
				this.addProductCurrentPage = 1; //reset - 分页插件显示第一页
				this.addProductPageStart = 0; //reset
				this.addProductCatogeryValue = 0;  //reset 不以分类为搜索条件
				this.addProductKeyword = ""; //reset
			},

			// 聊天框顶部 show more 按钮点击
			chatShowMore(){
				this.isShowMore = !this.isShowMore;

				clearTimeout(this.timeoutTimer);
				this.timeoutTimer = setTimeout(()=>{
					this.isAllRead = true
				}, 1000)
			},

			//获取询盘留言列表 - 对话框信息
			//参数为 searchMore：搜索上一页，从preMessagePkey开始查询
			//       searchLast：发消息时，获取最新消息，从nextMessagePkey开始查询
			getChatInfo({searchMore=false,searchLast=false}={}){
				this.isAllRead = false;
			    // 私人展厅倒计时取消 - 获取信息后重置
				// if(this.chatMsgList && (!searchMore && !searchLast)){
				// 	this.chatMsgList.forEach((msg,index)=>{
				// 		if(msg.type==4){
				// 		clearInterval( msg.personalShow.timer );
				// 	}
				// })
				// }
				this.isChatMsgListLoading = true;

				axios.get('/home/rfq_RFQConsult_pageMsgs', {
					params:{
						start: 0,
                        preMessagePkey:  searchMore?this.chatMsgListPagePrePkey:null,
                        nextMessagePkey: searchLast?this.chatMsgListPageNextPkey:null,
						limit: this.chatMsgListPageLimit,
						relationPkey: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation.pkey,
					}
				})
						.then((res) => {
					this.isChatMsgListLoading = false;
				if(res.data.ret == -1){
					util_function_obj.alertWhenNoLogin(this);
					return false;
				}else if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Get chat information error,please try again later");
					return
				};

				// 是否全部加载完毕 - 不以加载最新消息为判断
				if (!searchLast && (res.data.result.msgs.length < this.chatMsgListPageLimit)) {
					this.isChatMsgListLoadOver = true;
				};

				// 保存最后一个 prePkey，用以load more
                if(!searchLast){
                    this.chatMsgListPagePrePkey = res.data.result.msgs[res.data.result.msgs.length - 1].pkey || null;
                }
                // 保存最新一个 nextPkey，用以发送消息时获取最新代码
				if(!searchMore){
                    this.chatMsgListPageNextPkey = res.data.result.msgs[0].pkey || null;
                }

				let chatMsgList = res.data.result.msgs;
				// this.chatMsgList.push(...res.data.result.msgs);
				chatMsgList.forEach((msg,index)=>{
					let txtContent = JSON.parse(msg.content);
				if(msg.type==2){
					console.log(2)
					console.log(txtContent)
				}
				msg.content = this.msgContentSwitch(txtContent,msg.type)
				// 传回私人展厅信息时 - 保存私人展厅信息
				if(msg.type==4){
					msg.personalShow={
						alertMsg: txtContent.alertMsg,
						linkUrl: txtContent.url.indexOf("http") != -1 ? txtContent.url : ("http://" + txtContent.url),
						productId: txtContent.productId,
						validDate: txtContent.validDate,
					}
					// 倒计时
					if(msg.personalShow.validDate){
						var diffTime = txtContent.validDate - Date.now();
						if(diffTime<=0){
							msg.personalShow.countDown = -1;
							return;
						}
						this.countDown(diffTime, msg.personalShow)
					}
				}
			})
                // 发消息时会获取最新消息 - 信息加载在前面
                if(searchLast){
                    this.chatMsgList.unshift(...chatMsgList);
                // 其余全部加载信息到后面
                }else{
                    this.chatMsgList.push(...chatMsgList);
                }

				// 第一次加载时取双方的name信息
				// 第一次加载时滚动置底 - 之后加载more时不滚动置底
                // 搜索最新消息后 - 发送消息后，页面显示最下面
				if(this.chatMsgListPageStart==0 || searchLast){
				this.chatMsgObj = res.data.result;
					// 下拉置底
					this.$nextTick(()=>{
						var scrollHeight = this.$refs.chatContent.scrollHeight;
						this.$refs.chatContentScroll.wrap.scrollTop = scrollHeight;
					})
				}else{
					this.$nextTick(()=>{
						this.$refs.chatContentScroll.wrap.scrollTop = chatMsgList.length * 91;
				})
				}
				this.sendMsgValue = "";
				this.sendMsgImgValue = "";

			})
			.catch((error) => {
					this.isChatMsgListLoading = false;
				this.$message.error(error || 'Network error,please try again later');
			});
			},

			// 加载更多聊天信息
			loadMoreChatInfo(){
				if(this.isChatMsgListLoading || this.isChatMsgListLoadOver) return;
				this.chatMsgListPageStart += this.chatMsgListPageLimit;
				this.getChatInfo({searchMore:true});
			},

			//reset 聊天信息
			resetChatMsg(){
				this.chatMsgListPageStart = 0;
				this.chatMsgListPagePrePkey = null;
				this.chatMsgListPageNextPkey = null;
                // 私人展厅倒计时取消 - 获取信息后重置
                if(this.chatMsgList){
                    this.chatMsgList.forEach((msg,index)=>{
                        if(msg.type==4){
                            clearInterval( msg.personalShow.timer );
                        }
                    })
                }
				this.chatMsgList = [];
				this.chatMsgObj = {};
				this.isChatMsgListLoading = false;
				this.isChatMsgListLoadOver = false;
			},

			// 倒计时函数
			countDown( maxtime, timeObj, fn )  {
				maxtime = maxtime/1000;
				clearInterval( timeObj.timer );

				var formatNumber = function (n) {
					n = n.toString();
					return n[1] ? n : '0' + n;
				};
				timeObj.timer = setInterval(()=> {
					if( !!maxtime ){
					var day = Math.floor(maxtime / 86400),
							hour = Math.floor((maxtime % 86400) / 3600),
							minutes = Math.floor((maxtime % 3600) / 60),
							seconds = Math.floor(maxtime%60),
							msg = "距离结束还有"+(day*24+hour)+"时"+minutes+"分"+seconds+"秒";
					// timeObj.countDown = (day*24+hour) + ":" +minutes+":"+seconds;
					this.$set(timeObj,"countDown",( formatNumber(day*24+hour) ) + ":" + formatNumber(minutes) +":"+ formatNumber(seconds) );
					// fn( msg );
					--maxtime;
				} else {
					clearInterval( timeObj.timer );
					// fn("时间到，结束!");
				}
			}, 1000);
			},

			// 聊天时发送图片
			chatAddImgSuc(res, file, fileList){
				if (res.ret != 1) {
					this.$message.error(res.msg || "Images upload error,please try again later");
					return
				}else{
					this.sendMsgImgValue = res.result.url;
					this.sendMsg();
				}
				// axios.post('', Qs.stringify({
				//
				// }))
				// 		.then((res) => {
				// 	if (res.data.ret != 1) {
				// 	this.$message.error(res.data.msg || "Images upload error,please try again later");
				// 	return
				// };
				// this.$message({
				// 	message: 'Add image success!',
				// 	type: 'success'
				// });
			},

			// 聚焦时视为消息已读
			sendMsgInputFocus(){
				this.isShowMore = false;
				this.isAllRead = true;
			},

			// 发送消息
			sendMsg(){
				if(!this.sendMsgValue && !this.sendMsgImgValue) return;

				axios.post('/home/rfq_RFQConsult_sendMessage', Qs.stringify({
					content: this.sendMsgValue,
					imageUrl: this.sendMsgImgValue,
					relationPkey: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation.pkey,
				}))
						.then((res) => {
				if(res.data.ret == -1){
					util_function_obj.alertWhenNoLogin(this);
					return false;
				}else if (res.data.ret != 1) {
					this.$message.error(res.data.msg || "Send message error,please try again later");
					return
				};
				// 因为只请求了新的数据，老数据并没有重新请求，so老数据需要手动更新是否已读信息
				//将之前的信息全部更新为已读信息
				this.chatMsgList.forEach((chatMsgItem,index)=>{
					chatMsgItem.hadRead = true;
				})
				this.getChatInfo({searchLast: true});
			})
			.catch((error) => {
					this.$message.error(error || 'Network error,please try again later');
			});

			},

			// 消息列表content转换
			msgContentSwitch(content, type){
				// content = JSON.parse(content);
				switch(type){
					case 1:
						content = content.content;
						break;
					case 2:
						content = "<img src='" + util_function_obj.image(content.imageUrl, 500, 200)+"'/>";
						break;
					case 3:
					case 4:
						let linkUrl = content.url.indexOf("http") != -1 ? content.url : ("http://" + content.url)
						content = `
								<a href=${"${linkUrl}"} target="_blank">
									${"${content.url}"}
								</a>
							`;
						break;
					default:
						break;
				}
				return content;
			},

			//第一次点击type=4的私人展厅
			alertPersonalShowTime(linkUrl, itemObj) {
				this.$confirm('After the link click on start the countdown, you will have 72 hours to view this private business of goods throughout the hall.', '', {
					confirmButtonText: 'Start',
					cancelButtonText: 'Cancel',
					customClass: "my-custom-element-alert-class",
					cancelButtonClass: "el-button--medium",
					confirmButtonClass: "el-button--medium",
				}).then(() => {
					window.open(linkUrl);
				setTimeout(()=>{
					//获取chat列表 - 此时获取数据时将返回倒计时
					// this.getChatInfo();

					//本地自己计算个倒计时 - 获取数据时也会起 - 不刷新页面
					var diffTime = 72*60*60*1000; //倒计时72小时
					this.$set(itemObj,'validDate',Date.now()+diffTime);
					this.countDown(diffTime, itemObj);
			},1000)
			});
			},

		},
		filters:{
			// 时间戳转换时间
			dataFormat: function (value, fmt) {
				if( !value ) return;
				let getDate = new Date(value);
				let o = {
					'M+': getDate.getMonth() + 1,
					'd+': getDate.getDate(),
					'h+': getDate.getHours(),
					'm+': getDate.getMinutes(),
					's+': getDate.getSeconds(),
					'q+': Math.floor((getDate.getMonth() + 3) / 3),
					'S': getDate.getMilliseconds()
				};
				if (/(y+)/.test(fmt)) {
					fmt = fmt.replace(RegExp.$1, (getDate.getFullYear() + '').substr(4 - RegExp.$1.length))
				}
				for (let k in o) {
					if (new RegExp('(' + k + ')').test(fmt)) {
						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
					}
				}
				return fmt;
			},

			// options转换对应文字
			optionsStatus2Text: function (status) {
				var text = "All Inquires";
				switch(status){
					case 1:
						text = "RFQ";
						break;
					case 2:
						text = "Product Inquires";
						break;
					case 3:
						text = "O2O Inquires";
						break;
					case 4:
						text = "Supplier Inquires";
						break;
					default:
						text = "All Inquires";
						break;
				}
				return text;
			},
			// Inquiry的status转换对应文字
			inquiryStatus2Text: function (status, haveQuotation) {
				var text = "";
				switch(status){
					case 1:
						text = "Verifying";
						break;
					case 2:
						text = "Reject";
						break;
					case 3:
						text = haveQuotation?"Compare all quotation":"No quotation";
						break;
					default:
						text = "";
						break;
				}
				return text;
			},

			// 审核状态 转换 对应文字
			verifyStatus2Text: function (status) {
				var text = "All Inquires";
				switch(status){
					case 1:
						text = "Verifying"; //审核中
						break;
					case 2:
						text = "Reject"; //审核不通过
						break;
					case 3:
						text = "Approved"; //审核通过
						break;
					default:
						text = "";
						break;
				}
				return text;
			},

		}
	})

</script>
</body>
</html>
