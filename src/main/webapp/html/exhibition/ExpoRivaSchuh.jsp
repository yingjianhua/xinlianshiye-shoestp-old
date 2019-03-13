<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<script src="/html/exhibition/js/config.js"></script>
<link rel="stylesheet" href="/html/exhibition/css/lhl-index.css">
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
<div id="lhl-garda">
	<!--header banner-->
	<div class="garda-banner">
		<div class="banner-title">
			<h2 class="banner-title-h2">Expo Riva Schuh</h2>
			<p class="banner-title-time">12-15 / Jan / 2019</p>
			<p class="banner-title-address">Riva del Garda Fiere,<br>Riva del Garda,Italy</p>
		</div>
		<div class="banner-image">
			<img src="/html/exhibition/images/ERS-banner.png" alt="" name="ERS-banner">
			<div class="banner-image-video">
				<video src="/html/exhibition/images/Making%20of%20sport%20shoes.MP4" tabindex="-1" controlslist="nodownload" poster="/html/exhibition/images/ERS-video.png" controls preload="auto" name="video"></video>
			</div>
		</div>
	</div>
	<!--About-->
	<div class="garda-about">
		<h2 class="garda-about-h2">About</h2>
		<p class="garda-about-text">
			Expo Riva Schuh is today’s leading international exhibition for volume production footwear at a mid-range
			price point. It is Europe’s most important event for this market segment. Expo Riva Schuh is a meeting place
			for international business: an event where companies and buyers from around the world meet and interact.
		</p>
		<p class="garda-about-text">
			There are companies from Italy and Europe, as well as the increasingly important presence of companies from
			India, China, and Brazil - the world leaders in footwear production. Expo Riva Schuh is the first event in
			the international fair calendar. This means visitors get a thorough preview of offerings and can plan orders
			well in advance. For exhibitors, it represents the first opportunity to test out collections and fine-tune
			production plans.
		</p>
		<p class="garda-about-text">
			Expo Riva Schuh has always placed business first, through the strength of its role as a European crossroads
			focommercial exchange for the volume footwear sector. This results in statistics that have made Expo Riva
			Schuh the leading fair in this product category, both at national and international level.
		</p>
		<!--<img src="images/ERS-Gardabags.png" alt="" name="ERS-Gardabags">-->
	</div>
	<div class="garda-suppliersList">
		<img src="/html/exhibition/images/ERS-suppliersList01.png" alt="" name="ERS-suppliersList">
		<img src="/html/exhibition/images/ERS-suppliersList02.png" alt="" name="ERS-suppliersList">
		<img src="/html/exhibition/images/ERS-suppliersList03.png" alt="" name="ERS-suppliersList">
		<img src="/html/exhibition/images/ERS-suppliersList04.png" alt="" name="ERS-suppliersList">
	</div>
	<!--Information-->
	<div class="garda-detail">
		<div class="garda-detail_edition garda_center">
			<img src="/html/exhibition/images/ERS-edition.png" alt="" name="ERS-edition">
			<!--<img src="images/ERS-shoesList.png" alt="" name="ERS-shoesList">-->
		</div>
		<!--Basic Information-->
		<div class="garda-detail_basic garda_center">
			<h2 class="garda_h2">Basic Information</h2>
			<div class="garda-detail_div" :class="index%2 == 0 ? 'garda_yellow' : 'garda_blue'"
			     v-for="item,index in information">
				<img :src="item.image" alt="" name="ERS-Information">
				<h2 class="garda-detail_div_h4">
					<img :src="item.icon" alt="" name="ERS-icon">
					{{item.title}}
				</h2>
				<p class="garda-detail_div_text" v-for="pro in item.content">{{pro}}</p>
			</div>
		</div>
		<!--Editions-->
		<!-- <div class="garda-editions garda_center">
			<h2 class="garda_h2">Editions</h2>
			<div class="garda-editions_div">
				<div class="linedition1">
					<div class="hen"></div>
					<div class="timecon">
						<div class="time">
							<div class="point"></div>
							<div class="timemsg">
								<span class="month">(91st Edition)<br>12-15 Jan</span>
								<h4 class="year">2019</h4>
							</div>
						</div>
						<div class="time">
							<div class="point"></div>
							<div class="timemsg">
								<span class="month">16-19 Jun</span>
								<h4 class="year">2018</h4>
							</div>
						</div>
						<div class="time">
							<div class="point"></div>
							<div class="timemsg">
								<span class="month">13-16 Jan</span>
								<h4 class="year">2018</h4>
							</div>
						</div>
						<div class="time">
							<div class="point"></div>
							<div class="timemsg">
								<span class="month">10-13 Jun</span>
								<h4 class="year">2011</h4>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div> -->
		<!--Exhibitor Profile-->
		<div class="garda-profile garda_center">
			<h2 class="garda_h2">Exhibitor Profile</h2>
			<p class="garda-text">
				Expo Riva Schuh will be attended by various exhibitors who will offer a wide range of product services.
				This event will invite exhibitors from different parts of the country as well as from abroad linked to
				foot wear industry. Exhibitors profile consists of marketing experts and promoters, foot wear business
				owners, distributors of the foot wears products, manufacturer of the foot wears product and all the
				professionals from foot wear industry. Dealers who are involved in the industry will be a part of the
				show.
			</p>
		</div>
		<!--Selected Suppliers List-->
		<div class="garda-suppliers garda_center">
			<h2 class="garda_h2">Selected Suppliers List</h2>
			<img src="/html/exhibition/images/ERS-suppliers01.png" alt="" name="ERS-suppliers">
			<img src="/html/exhibition/images/ERS-suppliers02.png" alt="" name="ERS-suppliers">
			<img src="/html/exhibition/images/ERS-suppliers03.png" alt="" name="ERS-suppliers">
		</div>
		<!--Buyers' Lounge-->
		<div class="garda-lounge garda_center">
			<div class="garda-servizio">
				<h2 class="garda_h2">Buyers' Lounge</h2>
				<p class="garda-text">
					12, 13 and 14 January 2019 10.30 a.m. - 5.00 p.m. | 15 January 10:00 a.m. – 2.00 p.m <br>
					Garda Taste Area, hall C4 and NEW Garda Taste Area in the hall A1 <br>
					At an event such as Expo Riva Schuh, business always comes first, but that doesn’t mean that we
					haven’t taken the chance to launch a new idea aimed at reinforcing that very aspect… inside Hall C4
					will be the new Buyers' Lounge where visitors will be welcome, offering a space where they can
					relax, develop contacts and network.
				</p>
			</div>
			<img src="/html/exhibition/images/ERS-ourbooth.png" alt="" name="ERS-ourbooth">
		</div>
		<!--Venue Map & Directions-->
		<div class="garda-map garda_center">
			<h2 class="garda_h2">Venue Map & Directionse</h2>
			<div class="garda-map_content">
				<iframe src="http://www.google.cn/maps/embed?pb=!1m18!1m12!1m3!1d2776.6443163117033!2d10.838571916360769!3d45.898426379108294!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x478216c3cc7855f9%3A0x501438a33f51bd82!2sRiva+del+Garda+Fiere!5e0!3m2!1szh-CN!2scn!4v1548678441090"
				        width="692" height="564" frameborder="0" style="border:0" allowfullscreen></iframe>
				<div class="garda-map_image" @click="dialogTableGardabags = true">
					<img src="/html/exhibition/images/ERS-Gardabags.png" name="gardabags" alt="">
				</div>
			</div>
			<h4 class="garda-map_text">Riva del Garda Fiere Via Baltera, 38066 Riva del Garda TN, Italy</h4>
		</div>
	</div>

	<el-dialog title="收货地址" :visible.sync="dialogTableGardabags">
		<img src="/html/exhibition/images/ERS-Gardabags.png" name="dialog-Gardabags" alt="">
	</el-dialog>
	    <index-bottom></index-bottom>
</div>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script src="/home/v3/static/js/index-top.js"></script>
<script>
    new Vue({
        el: '#lhl-garda',
        data: {
            dialogTableGardabags: false,
            information: [
                {
                    image: 'images/ERS-Information01.png',
                    icon: 'images/ERS-Info-icon01.png',
                    title: 'Timings',
                    content: ['9:00 AM - 6:00 PM\n(Jan 12 - Jan 14) (General)', '9:00 AM - 4:00 PM\n(Jan 15) (General)']
                }, {
                    image: 'images/ERS-Information02.png',
                    icon: 'images/ERS-Info-icon02.png',
                    title: 'Entry Fees',
                    content: ['Free* \nFor Industry Professionals']
                }, {
                    image: 'images/ERS-Information03.png',
                    icon: 'images/ERS-Info-icon03.png',
                    title: 'Participants',
                    content: ['13000 Visitors', '1455 Exhibitors']
                }, {
                    image: 'images/ERS-Information04.png',
                    icon: 'images/ERS-Info-icon04.png',
                    title: 'Frequency',
                    content: ['Twice A Year']
                },
            ]
        },
        mounted() {

        },
        methods: {

        }
    })
</script>
<%--<jsp:include page="/home/v2/template/foot.jsp"></jsp:include>--%>
</body>
</html>
