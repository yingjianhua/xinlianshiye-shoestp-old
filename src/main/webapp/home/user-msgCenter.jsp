<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"></jsp:include>

    <link rel="stylesheet" href="/home/v3/static/css/user/ureset.css" />
    <link rel="stylesheet" href="/home/v3/static/css/user/uindex.css" />

    <link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css" />
    <link rel="stylesheet" href="/home/v3/static/css/user/userIndeax.css" />


    <style>
        #shoesTp .user-menu {
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

</style>
</head>
<body >
<jsp:include page="v3/nav-nobody.jsp"
></jsp:include>
<div id="shoesTp"  class=" w_1240 bg-gray  page-personal-center">
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
        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favoutities <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
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
												<i class="el-icon-plus prefix-icon"></i>
												Submit RFQ
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
												O2O Inquires
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
								<el-collapse :value="(inquiryList[0] && inquiryList[0].relations && inquiryList[0].relations.length)?0:-1" v-if="inquiryList.length">
								  <el-collapse-item :name="inquiryIndex"
										v-for="(inquiry,inquiryIndex) in inquiryList"
										:key="inquiry.pkey">
										<!-- 询盘列表头部 -->
								    <template slot="title">
											<!-- 没有联系人时，click.stop -->
											<div class="collapse-title"
												@click.stop = "(inquiry.type==1 && isScale)?viewInquiryDetail($event):''"
												:data-inquiry-index="inquiryIndex"
												v-if="inquiry.relations && inquiry.relations.length==0">
									      <img class="goods-pic" alt="goods's pic"
													:src="image(inquiry.images[0]) + (inquiry.type==3?'?x-oss-process=image/resize,w_50,h_50/blur,r_5,s_20':'')">
												<div class="goods-info">
													<div class="goods-name">
														<div class="ellipsis_1">{{inquiry.title}}</div>
													</div>
													<div class="status">{{inquiry.type}}</div>
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
									      <img class="goods-pic" alt="goods's pic"
													:src="image(inquiry.images[0]) + (inquiry.type==3?'?x-oss-process=image/resize,w_50,h_50/blur,r_5,s_20':'')"
													:data-inquiry-index="inquiryIndex">
												<div class="goods-info"
													:data-inquiry-index="inquiryIndex">
													<div class="goods-name">
														<div class="ellipsis_1">{{inquiry.title}}</div>
													</div>
													<div class="status">{{inquiry.type}}</div>
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
											<li class="contact-item"
												v-for="(relation,relationsIndex) in inquiry.relations"
												v-if="!relation.isDeleteInLocal"
												:key="relation.supplier.pkey">
												<el-badge is-dot :hidden="!relation.quotation.isNew" class="short-name">
													<div class="short-name"
														:data-inquiry-id="inquiry.pkey"
														:data-supplier-index="relationsIndex"
														:data-inquiry-index="inquiryIndex"
														@click="isScale?contactSupplier($event):''">
														{{relation.supplier.name[0]}}
													</div>
												</el-badge>

												<div class="contacter-info"
													:data-inquiry-id="inquiry.pkey"
													:data-supplier-index="relationsIndex"
													:data-inquiry-index="inquiryIndex"
													@click="isScale?contactSupplier($event):''">
													<div class="name ellipsis_1">
														{{relation.supplier.name}}
													</div>
													<img class="country" :src="image(relation.supplier.country.flag)" alt="">
												</div>

												<transition name="el-fade-in">
													<div class="show-group"
														v-show="!isScale">
														<div class="goods-name ellipsis_1">
																{{relation.quotation.title}}
														</div>
														<img class="goods-pic" :src="image(relation.quotation.images[0])" alt="goods's pic">
														<div class="goods-spec">
															{{relation.quotation.minPrice}}-{{relation.quotation.maxPrice}} {{relation.quotation.currency.shortName}}
														</div>
														<div class="sample">
															{{relation.quotation.sample?"Have sample":"No sample"}}
														</div>

														<div class="my-btn-normal btn-contact"
															:data-inquiry-id="inquiry.pkey"
															:data-supplier-index="relationsIndex"
															:data-inquiry-index="inquiryIndex"
															@click="contactSupplier">Contact Supplier</div>
													</div>
												</transition>

												<div class="more">
													<transition name="el-fade-in">
														<div v-show="isScale" class="time">2019-1-8</div>
													</transition>
													<el-popover popper-class="my-popover-operate"
													v-model="inquiryList[inquiryIndex].relations[relationsIndex].showPopover">
													<ul class="pop-operate-list">
														<li class="operate-item"
															:data-quotation-pkey="relation.quotation.pkey"
															:data-inquiry-index="inquiryIndex"
															:data-relations-index="relationsIndex"
															@click="closeQuotation">Delete</li>
														<li class="operate-item"
															:data-inquiry-index="inquiryIndex"
															:data-relations-index="relationsIndex"
															:data-supplier-pkey="relation.supplier.pkey"
															@click="addContact">Add Contact</li>
													</ul>
													<div slot="reference">
														<img class="icon-more" src="./images/icon_more.png" alt="">
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
					<div class="chat-wrap" v-show="isScale && showChatBox">
						<div class="chat-header">
							<div class="name" v-if="supplierDetail.company">
								<!-- 有头像显示头像，没有头像显示首字母 -->
								<img :src="image(supplierDetail.logo)" alt="" class="short-name"
									:class="{isShowMore: isShowMore}"
									v-if="supplierDetail.logo">
								<div class="short-name" v-else
									:class="{isShowMore: isShowMore}">
									{{supplierDetail.company[0]}}
								</div>

								<div class="full-name" :class="{isShowMore: isShowMore}">
									{{supplierDetail.company}}
								</div>
							</div>
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
											<li class="basic-item" v-if="supplierDetail.country">
												<div class="label">Country / Region:</div>
												<div class="content">
													<img  alt="" class="pic-flag" :src="image(inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag)"
														v-if="inquiryList[nowInquiryIndex] &&
														inquiryList[nowInquiryIndex].relations &&  inquiryList[nowInquiryIndex].relations[nowSupplierIndex] && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag">
													{{supplierDetail.country}}
												</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.businessType">
												<div class="label">Business type:</div>
												<div class="content">{{supplierDetail.businessType}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.yearEstablished">
												<div class="label">Year established:</div>
												<div class="content">
													{{supplierDetail.yearEstablished | dataFormat('yyyy-MM-dd')}}
												</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.mainProducts">
												<div class="label">Main products:</div>
												<div class="content">{{supplierDetail.mainProducts}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.location">
												<div class="label">Location:</div>
												<div class="content">{{supplierDetail.location}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.employeeCount">
												<div class="label">EmployeeCount:</div>
												<div class="content">{{supplierDetail.employeeCount}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.annualRevenue">
												<div class="label">Annual revenue:</div>
												<div class="content">{{supplierDetail.annualRevenue}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.mainMarket">
												<div class="label">Main markets:</div>
												<div class="content">{{supplierDetail.mainMarket}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.transactionCount">
												<div class="label">TransactionCount:</div>
												<div class="content">{{supplierDetail.transactionCount}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.transactionAmount">
												<div class="label">Transaction amount:</div>
												<div class="content">{{supplierDetail.transactionAmount}}</div>
											</li>
										</ul>
										<!-- supplier信息 - end -->

										<!-- 普通询盘基础信息 -->
										<div class="basic-details-wrap normal-inquiry-drop-down-wrap">
											<div class="box-title">Basic information</div>
											<div class="row-item product-info-box">
												<img class="product-pic" alt="product's pic"
													v-if="inquiryDetail.images"
												  :src="image(inquiryDetail.images[0]) + (inquiryList[nowInquiryIndex].type==3?'?x-oss-process=image/resize,w_50,h_50/blur,r_5,s_20':'')">
												<div class="product-descript">
													{{inquiryDetail.title}}
												</div>
											</div>
											<div class="row-item row-title">
												<span class="">Quantity:</span>
												<span class="content">
													{{inquiryDetail.quantity}} {{inquiryDetail.unit}}
												</span>
											</div>
											<div class="row-item" v-if="inquiryDetail.detail">
												<div class="row-title">Message:</div>
												<!-- <el-scrollbar style="height: 100%;" :native="false" wrapStyle="" wrapClass="" viewClass="" viewStyle="" noresize="false" tag="div"> -->
													<div class="content msg-content">
															{{inquiryDetail.detail}}
													</div>
											<!-- </el-scrollbar> -->
											</div>
											<div class="row-item goods-photos" v-if="inquiryDetail.images && inquiryDetail.images.length">
												<div class="row-title">Photos:</div>
												<ul class="photos-list">
													<li class="photo-item"
													v-for="picUrl in inquiryDetail.images"
													:key="picUrl">
														<img class="product-pic" alt="product's pic"
															:src="image(picUrl) + (inquiryList[nowInquiryIndex].type==3?'?x-oss-process=image/resize,w_50,h_50/blur,r_5,s_20':'')">
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
											<li class="basic-item" v-if="supplierDetail.country">
												<div class="label">Country / Region:</div>
												<div class="content">
													<img  alt="" class="pic-flag" :src="image(inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag)"
														v-if="inquiryList[nowInquiryIndex] &&
														inquiryList[nowInquiryIndex].relations && inquiryList[nowInquiryIndex].relations[nowSupplierIndex] && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag">
													{{supplierDetail.country}}
												</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.businessType">
												<div class="label">Business type:</div>
												<div class="content">{{supplierDetail.businessType}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.yearEstablished">
												<div class="label">Year established:</div>
												<div class="content">
													{{supplierDetail.yearEstablished | dataFormat('yyyy-MM-dd')}}
												</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.mainProducts">
												<div class="label">Main products:</div>
												<div class="content">{{supplierDetail.mainProducts}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.location">
												<div class="label">Location:</div>
												<div class="content">{{supplierDetail.location}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.employeeCount">
												<div class="label">EmployeeCount:</div>
												<div class="content">{{supplierDetail.employeeCount}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.annualRevenue">
												<div class="label">Annual revenue:</div>
												<div class="content">{{supplierDetail.annualRevenue}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.mainMarket">
												<div class="label">Main markets:</div>
												<div class="content">{{supplierDetail.mainMarket}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.transactionCount">
												<div class="label">TransactionCount:</div>
												<div class="content">{{supplierDetail.transactionCount}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.transactionAmount">
												<div class="label">Transaction amount:</div>
												<div class="content">{{supplierDetail.transactionAmount}}</div>
											</li>
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
														{{inquiryDetail.title}}
													</div>
												</li>
												<li class="info-item product-pic-wrap"
													v-if="inquiryDetail.images && inquiryDetail.images.length">
													<div class="label">Product Photos:</div>
													<div class="content">
														<img class="product-pic"  alt=""
															:src="image(imgUrl)"
															v-for="imgUrl in inquiryDetail.images"
															:key="imgUrl">
													</div>
												</li>
												<li class="info-item">
													<div class="label">Product Detail:</div>
													<div class="content">
														{{inquiryDetail.detail}}
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
														<span class="c10389c">{{inquiryDetail.quantity}}</span>
														{{inquiryDetail.unit}}
													</div>
													<div class="label" v-if="inquiryDetail.price">Price:</div>
													<div class="content" v-if="inquiryDetail.price">
														{{inquiryDetail.price}}
													</div>
												</li>
												<li class="info-item" v-if="inquiryDetail.unit">
													<div class="label">Currency Unit:</div>
													<div class="content">{{inquiryDetail.unit}}</div>
												</li>
												<li class="info-item" v-if="inquiryDetail.valieDate">
													<div class="label">Quote Validity Period:</div>
													<div class="content">
														{{inquiryDetail.valieDate | dataFormat('yyyy-MM-dd')}}
													</div>
												</li>
												<li class="info-item" v-if="inquiryDetail.paymentTerms">
													<div class="label">Payment Terms:</div>
													<div class="content">{{inquiryDetail.paymentTerms}}</div>
												</li>
												<li class="info-item" v-if="inquiryDetail.shippingTerms">
													<div class="label">Shipping terms:</div>
													<div class="content">{{inquiryDetail.shippingTerms}}</div>
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
											<li class="basic-item" v-if="supplierDetail.country">
												<div class="label">Country / Region:</div>
												<div class="content">
													<img  alt="" class="pic-flag" :src="image(inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag)"
														v-if="inquiryList[nowInquiryIndex] &&
														inquiryList[nowInquiryIndex].relations &&  inquiryList[nowInquiryIndex].relations[nowSupplierIndex] && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag">
													{{supplierDetail.country}}
												</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.businessType">
												<div class="label">Business type:</div>
												<div class="content">{{supplierDetail.businessType}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.yearEstablished">
												<div class="label">Year established:</div>
												<div class="content">
													{{supplierDetail.yearEstablished | dataFormat('yyyy-MM-dd')}}
												</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.mainProducts">
												<div class="label">Main products:</div>
												<div class="content">{{supplierDetail.mainProducts}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.location">
												<div class="label">Location:</div>
												<div class="content">{{supplierDetail.location}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.employeeCount">
												<div class="label">EmployeeCount:</div>
												<div class="content">{{supplierDetail.employeeCount}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.annualRevenue">
												<div class="label">Annual revenue:</div>
												<div class="content">{{supplierDetail.annualRevenue}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.mainMarket">
												<div class="label">Main markets:</div>
												<div class="content">{{supplierDetail.mainMarket}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.transactionCount">
												<div class="label">TransactionCount:</div>
												<div class="content">{{supplierDetail.transactionCount}}</div>
											</li>
											<li class="basic-item" v-if="supplierDetail.transactionAmount">
												<div class="label">Transaction amount:</div>
												<div class="content">{{supplierDetail.transactionAmount}}</div>
											</li>
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

											<div v-if="!inquiryDetail.images || (inquiryDetail.images && inquiryDetail.images.length < 5)">
												<el-upload
												  class="upload-wrap"
													:limit="inquiryDetail.images?(5 - inquiryDetail.images.length):5"
													:on-success="addFileListSupplierSuccess"
												  action="/home/usr_Purchase_upload"
												  list-type="picture">

													<span class="upload">Upload Files</span> (Maximum of 5 phots)
												</el-upload>
											</div>

											<ul class="goods-pic-wrap" v-if="inquiryDetail.images && inquiryDetail.images.length">
												<li class="goods-pic-item"
													v-for="picUrl in inquiryDetail.images"
													:key="picUrl">
													 <img class="product-pic" :src="image(picUrl)" alt="">
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
														 <img class="product-pic" :src="image(product.image.split(',')[0])" alt="">
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
									<div class="chater-info">
										LOCATION：
										<img  alt="" class="pic-flag" :src="image(inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag)"
											v-if="inquiryList[nowInquiryIndex] &&
											inquiryList[nowInquiryIndex].relations && inquiryList[nowInquiryIndex].relations[nowSupplierIndex] && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country && inquiryList[nowInquiryIndex].relations[nowSupplierIndex].supplier.country.flag">
										{{supplierDetail.location}}
										<!-- IP:115.218.107.* -->
										<div>Message only sent to you</div>
									</div>
									<!-- 聊天框内容 -->
									<ul class="chat-list">
										<li class="chat-item" :class="{mine: msg.p2S}"
											v-for="(msg,i) in chatMsgObj.msgs"
											:key="msg.sendTime">
											<!-- 有头像显示头像，没有头像显示首字母 -->
											<template v-if="msg.p2S">
												<img class="pic-head" alt="head's pic"
													v-if="chatMsgObj.another.avatar"
												  :src="image(chatMsgObj.another.avatar)">
												<div class="pic-head" v-else>
													{{chatMsgObj.another.name[0]}}
												</div>
											</template>
											<template v-else>
												<img class="pic-head" alt="head's pic"
													v-if="chatMsgObj.myself.avatar"
												  :src="image(chatMsgObj.myself.avatar)">
												<div class="pic-head" v-else>
													{{chatMsgObj.myself.name[0]}}
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
															@click="(msg.type==4 && !msg.personalShow.validDate)?alertPersonalShowTime(msg.personalShow.linkUrl):''"
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
									@focus="isShowMore = false"
									@keyup.enter.native="sendMsg"
									v-model.trim="sendMsgValue" placeholder="Please input the message"></el-input>
								<el-button class="btn-send" type="primary" size="small" @click="sendMsg">send</el-button>
							</div>
						</div>
					</div>
				</transition>
				<!-- 右侧聊天框 - end -->

				<!-- view信息 - 询盘详情 + 报价详情 -->
				<transition name="slide-right">
					<div class="inquiry-detail-wrap" v-show="isScale && showRFQDeailBox">
						<!-- 第一个框 - 询盘详情 -->
						<div class="inquiry-overview">
							<img class="inquiry-main-pic" alt=""
								v-if="inquiryDetail.images"
							  :src="image(inquiryDetail.images[0])">
							<div class="content-box-wrap">
								<div class="content-box1">
									<div class="content-box">
										<h3 class="content-header">{{inquiryDetail.title}}</h3>
										<ul class="content">
											<li class="item">
												Quantity required：{{inquiryDetail.quantity}} {{inquiryDetail.unit}}
											</li>
											<li class="item">
												Expiration time：{{inquiryDetail.valieDate | dataFormat('yyyy-MM-dd hh:mm:ss')}}
											</li>
											<li class="item" v-if="inquiryDetail.paymentTerms">
												Payment：{{inquiryDetail.paymentTerms}}
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
										<el-button size="mini"
											:disabled="(inquiryDetail.verifyStatus == 3 && quotationDetailList.length) || (inquiryDetail.status == 4)"
											@click="showEditAddInformationDialog">
											Edit
										</el-button>
										<el-button size="mini"
											:disabled="inquiryDetail.status == 4"
											@click="closeInquiry">Close</el-button>
									</div>
								</div>

								<div class="content-box">
									<h3 class="content-header">Product detailed specifications:</h3>
									<div class="content">
										{{inquiryDetail.detail}}
									</div>
								</div>

								<div class="content-box" v-if="inquiryDetail.images && inquiryDetail.images.length > 0">
									<h3 class="content-header">Attach files:</h3>
									<ul class="content attach-file-list">
										<li class="attach-file-item" v-for="picUrl in inquiryDetail.images">
											<img :src="image(picUrl)" alt="product's pic" class="inquiry-pic">
											<!-- <p class="ellipsis_1">goods thiods things</p> -->
										</li>
									</ul>
								</div>
							</div>
						</div>

						<!-- 第二个框 报价详情 -->
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
										<li class="item" v-if="quotationDetail.images.length">
											<span class="title">Product image or file:</span>
											<div class="text">
												<img class="pic-item" alt="goods' pic"
													:src="image(goodsPic)"
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
											<div class="text">

												<img class="company-book-item"  alt="product's book"
													:src="image(productBook.url)"
													v-for="productBook in quotationDetail.throwaways">
											</div>
										</li>
									</ul>
								</div>

							</div>
						</template>

						<el-collapse-transition>
							<el-button size="" class="view-more"
								v-show="inquiryList[nowInquiryIndex] && inquiryList[nowInquiryIndex].relations.length && quotationDetailListIndex < (inquiryList[nowInquiryIndex].relations.length-1)"
								@click="viewQuotationMoew">View More</el-button>
						</el-collapse-transition>
					</div>
				</transition>
				<!-- view信息 - 询盘详情 + 报价详情 - end -->
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
				    v-model="addInformationForm.information">
			    </el-input>
					<div class="font-num">{{addInformationForm.information.length || 0}}/100</div>
			  </el-form-item>

		    <el-form-item label="Valid to " >
			      <el-date-picker type="date" placeholder="Select the end date"
							style="width: 100%;"
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
						<img class="goods-pic" :src="image(goods.picture.split(',')[0])" alt="goods's pic">
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

					//询盘列表信息
					inquiresType: null,	// inquires筛选选中的值 1.RFQ 2.普通询盘 3.私人 4.店铺询盘 其余为All
					isInquiresOptionShow: false,	// inquires是否显示下拉筛选
					inquiresOptionUnreadInfo: {},	// inquires下拉 对应的未读信息数量
					isUnread: false, //unread是否选中
					inquiryKeyword: "", //搜索信息
					inquiryList: [], //询盘列表信息
					inquiryLisPageStart: 0, //询盘列表 分页
					inquiryLisPageLimit: 10, //询盘列表 分页
					isInquiryLisLoading: false, //询盘列表 加载开关
					isInquiryLoadOver: false, //询盘列表 是否已加载完全

					// 列表选择 - 及详情
					nowInquiryIndex: 0, //当前显示询盘信息是哪一个
					nowSupplierIndex: 0, //当前显示对话框供应商所在下标 - sendMsg用
					inquiryDetail: {},  //当前 view 显示询盘信息详情
					supplierDetail: {},  //当前 view 显示供应商信息详情
					quotationDetailList: [],  //当前 view 显示询盘信息页面下的 报价详情
					quotationDetailListIndex: 0,  //view 的报价详情 已加载的下标数 - view more

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
					addProductPageLimit: 10, //add product中 分页
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
					isAllRead: false,  //信息是否已读
					sendMsgValue: "", //发送的内容

					testObj:{
						status
					}
			},
			mounted() {
				// 获取询盘列表
				this.getInquiryList();
			},
			methods: {
				image(v, params) {
					if (!v) {
						return ""
					}
					if (!params) {
						params = ""
					}
					return "https://image.shoestp.com" + v + params
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
				getInquiryList() {
					// 正在加载 or 已加载完全
					if(this.isInquiryLisLoading || this.isInquiryLoadOver) return;
					this.isInquiryLisLoading = true;
					axios.get('/home/rfq_RFQConsult_pageMine', {
							params: {
								keyword: this.inquiryKeyword,
								t: this.inquiresType,
								unread: this.isUnread,
								start: this.inquiryLisPageStart,
								limit: this.inquiryLisPageLimit
							}
						})
						.then((res) => {
							this.isInquiryLisLoading = false;
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							// 加载至最后一页
							if (res.data.result.items.length < this.inquiryLisPageLimit) {
								this.isInquiryLoadOver = true;
							};
							this.inquiryList.push(...res.data.result.items);

							// 添加下拉加载事件
							this.$nextTick(() => {
								this.scrollLoadMore("#inquiry-collapse-list",this.loadMoreInquiryList)
              });
						})
						.catch((error) => {
							this.isInquiryLisLoading = false;
							console.log(error);
						});
				},

				// 加载下一页询盘列表
				loadMoreInquiryList(){
					this.inquiryLisPageStart += this.inquiryLisPageLimit;
					this.getInquiryList();
				},

				//reset 询盘信息
				resetInquiryOptions(){
					this.showChatBox = false;
					this.showRFQDeailBox = false;
					this.nowInquiryIndex = 0;
					this.nowSupplierIndex = 0;
					this.quotationDetailList = [];
					this.quotationDetailListIndex = 0;

					this.inquiryLisPageStart = 0;
					this.inquiryList = [];
					this.isInquiryLisLoading = false;
					this.isInquiryLoadOver = false;
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
						console.log("显示下拉选项 点击事件 suc");
						console.log(res);
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
							return
						};
						this.inquiresOptionUnreadInfo = res.data.result;
					})
					.catch((error) => {
						console.log(error);
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
					console.log("viewInquiryDetail")
					e.stopPropagation();
					var inquiryIndex = e.currentTarget.dataset.inquiryIndex;
					this.isScale = true;
					this.showChatBox = false;
					this.showRFQDeailBox = true;
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
					axios.get('/home/rfq_RFQConsult_detail', {
							params:{
								consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
							}
						})
						.then((res) => {
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							this.inquiryDetail = res.data.result;

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
							console.log(error);
						});
				},

				//获取功能供应商详情
				getSupplierDetail(){
					console.log("getSupplierDetail")
					axios.get('/home/usr_UsrSupplier_getDetail', {
							params:{
								supplierPkey: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].supplier.pkey,
							}
						})
						.then((res) => {
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							this.supplierDetail = res.data.result;

						})
						.catch((error) => {
							console.log(error);
						});
				},

				//供应商 上传图片 - upload成功
				addFileListSupplierSuccess(res, file){
					console.log("addFileListSupplierSuccess")
					console.log(res)
					console.log(res.ret)
					console.log(file)
					if (res.ret != 1) {
						this.$message.error(res.msg);
						return
					};

					axios.post('/home/rfq_RFQConsult_addImage', Qs.stringify({
						consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
						images: res.result.url,
					}))
					.then((res) => {
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
							return
						};
						this.$message({
							message: 'Add image success!',
							type: 'success'
						});
					})
					.catch((error) => {
						console.log(error);
					});
				},

				//获取报价详情
				getQuotationDetail(){
					console.log("getQuotationDetail")
					var nowInquiry = this.inquiryList[this.nowInquiryIndex];
					if( !nowInquiry.relations || !nowInquiry.relations.length ) return;

					var quotationPkey = nowInquiry.relations[this.quotationDetailListIndex].quotation.pkey;
					axios.get('/home/rfq_RFQConsult_quotationDetail', {
							params:{
								quotationPkey: quotationPkey,
							}
						})
						.then((res) => {
							this.btnLoading = false;
							console.log("获取报价详情 suc");
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							this.quotationDetailList.push(res.data.result);
						})
						.catch((error) => {
							this.btnLoading = false;
							console.log(error);
						});
				},

				//获取更多报价详情
				viewQuotationMoew(){
					console.log("click")
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
					this.addInformationForm.validDate = "";
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
									console.log("确认修改 suc");
									if (res.data.ret != 1) {
										this.$message.error(res.data.msg);
										return
									};
									this.$message({
					          message: 'Add more information success',
					          type: 'success'
					        });
									// 重新获取询盘列表
									this.resetInquiryOptions();
									this.getInquiryList();
								})
								.catch((error) => {
									console.log(error);
								});
	          } else {
	            console.log('error submit!!');
	            return false;
	          }
	        });
				},

				//关闭询盘
				closeInquiry(e){
					this.$confirm('Are you sure to close the inquiry?', 'tip', {
	          confirmButtonText: 'sure',
	          cancelButtonText: 'cancel',
	          type: 'warning'
	        }).then(() => {

						axios.post('/home/rfq_RFQConsult_close', Qs.stringify({
							consultPkey: this.inquiryList[this.nowInquiryIndex].pkey,
						}))
						.then((res) => {
							console.log(res);
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							this.$message({
								message: 'Close inquiry success',
								type: 'success'
							});
							// // 重新获取询盘列表
							// window.location.reload()
							this.getInquiryDetail();
						})
						.catch((error) => {
							console.log(error);
						});

	        }).catch((error) => {
						console.log(error);
					});
				},

				//关闭报价
				closeQuotation(e){
					var inquiryIndex = e.currentTarget.dataset.inquiryIndex;
					var relationsIndex = e.currentTarget.dataset.relationsIndex;
					var quotationPkey = e.currentTarget.dataset.quotationPkey;

					axios.post('/home/rfq_RFQConsult_deleteQuotation', Qs.stringify({
							quotationPkey: quotationPkey,
						}))
						.then((res) => {
							console.log("关闭询盘 suc");
							if (res.data.ret != 1) {
								this.$message.error(res.data.msg);
								return
							};
							this.$message({
			          message: 'Close quotation success',
			          type: 'success'
			        });

							// 重新获取询盘列表
							this.resetInquiryOptions();
							this.getInquiryList();
							// 本地显示时 静态删除 - 不请求后台刷新
							// this.$set(this.inquiryList[inquiryIndex].relations[relationsIndex],"isDeleteInLocal",true)
							// if(this.nowInquiryIndex==inquiryIndex && this.nowSupplierIndex==relationsIndex){
							// 	this.nowSupplierIndex = -1;
							// 	this.showChatBox = false;
							// }
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
						console.log("添加至联系人 suc");
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
							return
						};
						this.$message({
		          message: 'Add contact success',
		          type: 'success'
		        });
					})
					.catch((error) => {
						console.log(error);
					});
				},

				test(){
					console.log("test")
				},

				// 点击联系相应的供应商
				contactSupplier(e){
					console.log("contactSupplier")
					// 显示顶部show more信息
					this.timeoutTimer = setTimeout(()=>{
						this.isShowMore = true;
						clearTimeout(this.timeoutTimer);
					}, this.showChatBox?200:1000)

					this.isScale = true;
					this.showChatBox = true;
					this.showRFQDeailBox = false;

					var dataset = e.currentTarget.dataset;
					var inquiryId = dataset.inquiryId;
					var supplierIndex = dataset.supplierIndex;
					var inquiryIndex = dataset.inquiryIndex;
					if(this.nowSupplierIndex==supplierIndex && this.nowInquiryIndex == inquiryIndex && Object.keys(this.supplierDetail).length != 0) return;
					this.nowSupplierIndex = supplierIndex;
					this.nowInquiryIndex = inquiryIndex;

					// 左侧列表将点击项 设置为已读状态
					this.$set(this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation, "isNew",false)

					//获取chat列表
					this.getChatInfo();
					//获取询盘详情
					this.getInquiryDetail();
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
					axios.get('/testHome/pdt_PdtProduct_gtProductsIndexListAjax', {
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
						console.log("获取商品列表 suc");
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
							return
						};
						this.addProductGoodsListObj = res.data.result;
					})
					.catch((error) => {
						console.log(error);
					});
				},

				//分页加载 - 更改页数
				addProductPageChange(page){
					console.log("addProductPageChange")
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
					console.log(this.addProductSelectedPdtIds)
					axios.post('/home/rfq_RFQConsult_addProductRequest', Qs.stringify({
						products: this.addProductSelectedPdtIds.join(),
						consultPkey: this.inquiryList[this.nowInquiryIndex].pkey
					}))
					.then((res) => {
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
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
						console.log(error);
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
				getChatInfo(){
					this.isAllRead = false;
					if(this.chatMsgObj.msgs){
						this.chatMsgObj.msgs.forEach((msg,index)=>{
							if(msg.type==4){
								clearInterval( msg.personalShow.timer );
							}
						})
					}

					axios.get('/home/rfq_RFQConsult_pageMsgs', {
						params:{
							relationPkey: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation.pkey,
						}
					})
					.then((res) => {
						console.log("获取询盘留言列表 - suc");
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
							return
						};
						let chatMsgObj = res.data.result;
						chatMsgObj.msgs.forEach((msg,index)=>{
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
						this.chatMsgObj = chatMsgObj;

						this.sendMsgValue = "";

						// 下拉置底
						this.$nextTick(()=>{
							var scrollHeight = this.$refs.chatContent.scrollHeight;
							this.$refs.chatContentScroll.wrap.scrollTop = scrollHeight;
						})
					})
					.catch((error) => {
						console.log(error);
					});
				},

				// 倒计时函数
				countDown( maxtime, timeObj, fn )  {
						maxtime = maxtime/1000;
						clearInterval( timeObj.timer );

		        timeObj.timer = setInterval(()=> {
		               if( !!maxtime ){
		                   var day = Math.floor(maxtime / 86400),
		                   hour = Math.floor((maxtime % 86400) / 3600),
				               minutes = Math.floor((maxtime % 3600) / 60),
				               seconds = Math.floor(maxtime%60),
				               msg = "距离结束还有"+(day*24+hour)+"时"+minutes+"分"+seconds+"秒";
											 // timeObj.countDown = (day*24+hour) + ":" +minutes+":"+seconds;
											 this.$set(timeObj,"countDown",(day*24+hour) + ":" +minutes+":"+seconds)
		                // fn( msg );
		                --maxtime;
		            } else {
		                clearInterval( timeObj.timer );
		                // fn("时间到，结束!");
		            }
		        }, 1000);
		    },

				// 发送消息
				sendMsg(){
					console.log("sendMsg")
					if(!this.sendMsgValue) return;

					axios.post('/home/rfq_RFQConsult_sendMessage', Qs.stringify({
						content: this.sendMsgValue,
						relationPkey: this.inquiryList[this.nowInquiryIndex].relations[this.nowSupplierIndex].quotation.pkey,
					}))
					.then((res) => {
						console.log("发送消息 suc");
						if (res.data.ret != 1) {
							this.$message.error(res.data.msg);
							return
						};
						this.getChatInfo();
					})
					.catch((error) => {
						console.log(error);
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
							content = "<img src='"+this.image(content.imageUrl)+"'/>";
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
				alertPersonalShowTime(linkUrl) {
	        this.$confirm('After the link click on start the countdown, you will have 48 hours to view this private business of goods throughout the hall.', '', {
	          confirmButtonText: 'Start',
	          cancelButtonText: 'Cancel',
						customClass: "my-confirm-class",
						cancelButtonClass: "el-button--medium",
						confirmButtonClass: "el-button--medium",
	        }).then(() => {
						window.open(linkUrl);
						setTimeout(()=>{
							//获取chat列表 - 此时获取数据时将返回倒计时
							this.getChatInfo();
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
							text = "Inquiry";
							break;
						case 3:
							text = "Personal Inquiry";
							break;
						case 4:
							text = "Store Inquiry";
							break;
						default:
							text = "All Inquires";
							break;
					}
					return text;
				},

				// 审核状态 转换 对应文字
				verifyStatus2Text: function (status) {
					var text = "All Inquires";
					switch(status){
						case 1:
							text = "Under Review"; //审核中
							break;
						case 2:
							text = "Audit Not Passed"; //审核不通过
							break;
						case 3:
							text = "Approved"; //审核通过
							break;
						default:
							text = "Under Review";
							break;
					}
					return text;
				},

			}
		})

	</script>
</body>
</html>
