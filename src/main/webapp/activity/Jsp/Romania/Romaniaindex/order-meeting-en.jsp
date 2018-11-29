<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0,
maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta content="telephone=no" name="format-detection">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit">
	<meta  name="keywords" content="shoes purchasing, Men’s leather shoes, boy’s shoes, Men’s casual shoes,
		Men’s Sneakers, Children sandals, Men’s shoes, Women’s shoes, Women’s high heels, Women’s sandals, shoe companies,
		shoe suppliers, sneaker manufacturers, shoe factories, shoe manufacturing companies, sneaker manufacturer,
		china footwear manufacturer, sport shoes manufacturer, footwear factory.">
	<meta  name="discription" content="We will have a showroom in Romanian offline shoes purchasing. During this show,
		you can experience hundreds of styles of shoes locally and get your choosing shoes with factory direct price.
		Also, you can receive professional advice from customization. In this purchasing, nearly 500 different styles of Men’s shoes,
		women’s shoes and kid’s shoes will be displayed. If you reserve your seat online, you will get a secret gift when you come.
		Several free samples of shoes will also be supplied in this show. ">
	<title>Romanian Offline Shoes Purchasing</title>
	<link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/reset.css" media="all">
	<link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/index.css" media="all">
	<link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/swiper.min.css" media="all">
	<link rel="stylesheet" href="/activity/Jsp/Romania/Romaniaindex/css/element-ui/element-ui.css">
	<link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
	<link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
	<link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
	<link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="/home/static/css/animate.min.css">
	<link rel="stylesheet" href="/home/static/css/swiper.min.css" type="text/css">
	<link href="/home/static/css/index.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="/home/static/css/xl-style.css">
	<link href="/home/static/css/stp.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="/home/static/css/color.css" type="text/css">
	<script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/home/static/js/swiper.min.js"></script>
	<script type="text/javascript" src="/home/static/js/lazyload.min.js"></script>
	<script src="/activity/Jsp/Romania/Romaniaindex/js/jquery-1.7.2.min.js"></script>
	<script src="/activity/Jsp/Romania/Romaniaindex/js/swiper.min.js"></script>
	<script src="/activity/Jsp/Romania/Romaniaindex/js/vue.js"></script>
	<script src="/activity/Jsp/Romania/Romaniaindex/js/axios.min.js"></script>
	<script src="/activity/Jsp/Romania/Romaniaindex/js/qs.js"></script>
	<script src="/activity/Jsp/Romania/Romaniaindex/js/element-ui.js"></script>
</head>

<body>
<jsp:include page="../../../../home/template/web-top.jsp"></jsp:include>
	<div id="app">
		<div class="banner_01 banner-wrap">
			<div class="w1200 tc txt-box por">
				<div class="h4">2018.12.1-2018.12.19</div>
				<div class="h1 ">ROMANIAN</div>
				<div class="h3">OFFLINE SHOES PURCHASING</div>
				<div class="h5">
					Exhibition Hall Opens All Year Round
					<br>
					Magazinul Universal Dragonul Rosu Strada Drumul Garii 12, Bucuresti 077190
				</div>
				<img src="/activity/Jsp/Romania/Romaniaindex/images/flag.png" alt="" class="flag-pic">
			</div>
		</div>

		<!-- 4张分类 -->
		<div class="banner_02 w1200 banner-wrap">
			<ul class="clearfix">
				<li class="cat-item fl">
					<img src="/activity/Jsp/Romania/Romaniaindex/images/category_01.png" alt="" class="img-cover cat-pic scale">
					<div class="hover-box">
						<div class="bar"></div>
						<div class="title h5">MEN'S SHOES</div>
						<a href="" class="btn-more-simple fs_26">VIEW MORE > </a>
					</div>
				</li>
				<li class="cat-item fl">
					<img src="/activity/Jsp/Romania/Romaniaindex/images/category_02.png" alt="img-cover" class="img-cover cat-pic scale">
					<div class="hover-box">
						<div class="bar"></div>
						<div class="title h5">WOMEN'S SHOES</div>
						<a href="" class="btn-more-simple fs_26">VIEW MORE > </a>
					</div>
				</li>
				<li class="cat-item fl">
					<img src="/activity/Jsp/Romania/Romaniaindex/images/category_03.png" alt="img-cover" class="img-cover cat-pic scale">
					<div class="hover-box">
						<div class="bar"></div>
						<div class="title h5">CHILDREN'S SHOES</div>
						<a href="" class="btn-more-simple fs_26">VIEW MORE > </a>
					</div>
				</li>
				<li class="cat-item fl flexible flex-center">
					<img src="/activity/Jsp/Romania/Romaniaindex/images/category_04.png" alt="img-cover" class="img-cover cat-pic">
					<div class="txt_04 fs_60" style="width: 60%;">
						<a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-en.html">
							<img src="/activity/Jsp/Romania/Romaniaindex/images/category_04-tetx.png" alt="scale's text" class="scale" style="width: 100%;">
						</a>
					</div>
				</li>
			</ul>
		</div>

		<!-- 轮播 -->
		<div class="banner_03 banner-wrap">
			<div class="title tc bold fs_60">Recommended Products</div>


			<div class="swiper-container">
				<div class="swiper-wrapper">
						<div class="swiper-slide" v-for="carousel in carouselList">
							<div class="img-wrap">
								<a href="javascript: void(0);">
									<img :src="carousel.url" alt="show-pic" class="show-pic">
									<img :src="carousel.hoverUrl" alt="hover-show-pic" class="hover-show-pic">
								</a>
							</div>
						</div>
						<!-- <div class="swiper-slide">
							<div class="img-wrap">
								<a href="">
									<img src="./images/carousel_03.png" alt="" class="show-pic">
									<img src="./images/carousel_02.png" alt="" class="hover-show-pic">
								</a>
							</div>
						</div> -->
				</div>

				<!-- 如果需要导航按钮 -->
		    <div class="swiper-btn-prev">← precedente</div>
		    <div class="swiper-btn-next">successivo →</div>
			</div>
		</div>

		<!-- 男鞋区 -->
		<div class="banner_04 banner-wrap">
			<div class="w1200 por">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_04_bg-loop.png" alt="bg-loop" class="bg-loop">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_04_bg-shoes.png" alt="bg-shoes" class="bg-shoes">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_04_bg-men.png" alt="bg-men" class="bg-men">

				<!-- 内容区域 -->
				<div class="content-wrap">
					<div class="title-box tr">
						<h2 class="zone-title">
							MEN'S ZONE
						</h2>
						<a class="btn-more">
							view more
						</a>
					</div>

					<!-- 产品展示列表 -->
					<ul class="goods-list clearfix">
						<li class="goods-item" v-for="(shoes,index) in manShoesList">
							<img :src="image(shoes.img)" alt="goods-pic" class="goods-pic">
							<h5 class="goods-title fs_32">
								<!-- <b>Jiansha</b> Gentleman Kid Suede Genuine Leather Lace Up Casul Dress Shoes Shoes -->
								{{shoes.name}}
							</h5>
							<div class="tools-box tr">
								<a class="icon-letter-wrap inline-block pointer" :href="'https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html?shoesId='+shoes.id">
									<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-letter.png" alt="icon-letter" class="icon-letter pointer">
									<span class="tips fs_22">INQUIRY</span>
								</a>
								<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_off.png" alt="icon-heart" class="icon-heart pointer"
									v-if="!shoes.isFavorite" @click="collect(shoes.id,'manShoesList',index)">
								<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_on.png" alt="icon-heart" class="icon-heart pointer"
									v-else @click="collect(shoes.id,'manShoesList',index)">
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<!-- 女鞋区 -->
		<div class="banner_05 banner-wrap">
			<div class="w1200 por">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_05_bg-loop.png" alt="bg-loop" class="bg-loop">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_05_bg-shoes.png" alt="bg-shoes" class="bg-shoes">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_05_bg-women.png" alt="bg-men" class="bg-men">

				<!-- 内容区域 -->
				<div class="content-wrap">
					<div class="title-box">
						<h2 class="zone-title">
							WOMEN'S ZONE
						</h2>
						<a class="btn-more">
							view more
						</a>
					</div>

					<!-- 产品展示列表 -->
					<ul class="goods-list clearfix">
						<li class="goods-item" v-for="(shoes,index) in womanShoesList">
							<img :src="image(shoes.img)" alt="goods-pic" class="goods-pic">
							<h5 class="goods-title fs_32">
								{{shoes.name}}
							</h5>
							<div class="tools-box tr">
								<a class="icon-letter-wrap inline-block pointer" :href="'https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html?shoesId='+shoes.id">
									<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-letter.png" alt="icon-letter" class="icon-letter pointer">
									<span class="tips fs_22">INQUIRY</span>
								</a>
								<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_off.png" alt="icon-heart" class="icon-heart pointer"
									v-if="!shoes.isFavorite" @click="collect(shoes.id,'womanShoesList',index)">
								<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_on.png" alt="icon-heart" class="icon-heart pointer"
									v-else @click="collect(shoes.id,'womanShoesList',index)">
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<!-- 童鞋区 -->
		<div class="banner_06 banner-wrap">
			<div class="w1200 por">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_06_bg-loop.png" alt="bg-loop" class="bg-loop">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_06_bg-shoes.png" alt="bg-shoes" class="bg-shoes">
				<img src="/activity/Jsp/Romania/Romaniaindex/images/banner_06_bg-child.png" alt="bg-child" class="bg-men">

				<!-- 内容区域 -->
				<div class="content-wrap">
					<div class="title-box tr">
						<h2 class="zone-title">
							CHILDREN'S ZONE
						</h2>
						<a class="btn-more">
							view more
						</a>
					</div>

					<!-- 产品展示列表 -->
					<ul class="goods-list clearfix">
						<li class="goods-item" v-for="(shoes,index) in childrenShoesList">
							<img :src="image(shoes.img)" alt="goods-pic" class="goods-pic">
							<h5 class="goods-title fs_32">
								{{shoes.name}}
							</h5>
							<div class="tools-box tr">
								<a class="icon-letter-wrap inline-block pointer" :href="'https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html?shoesId='+shoes.id">
									<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-letter.png" alt="icon-letter" class="icon-letter pointer">
									<span class="tips fs_22">INQUIRY</span>
								</a>
								<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_off.png" alt="icon-heart" class="icon-heart pointer"
									v-if="!shoes.isFavorite" @click="collect(shoes.id,'childrenShoesList',index)">
								<img src="/activity/Jsp/Romania/Romaniaindex/images/icon/icon-heart_on.png" alt="icon-heart" class="icon-heart pointer"
									v-else @click="collect(shoes.id,'childrenShoesList',index)">
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<script>
		const imgurl = {
			"imageBaseUrl":"https://image.shoestp.com"
		};

		new Vue({
			el:'#app',
			data:{
				isLogin: false, //全局判断是否登录
				manShoesList:[],
				womanShoesList:[],
				childrenShoesList:[],
				carouselList:[
					{
						url: "/activity/Jsp/Romania/Romaniaindex/images/carousel_01.png",
						hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel_01_on.png",
					},
					{
						url: "/activity/Jsp/Romania/Romaniaindex/images/carousel_02.png",
						hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel_02_on.png",
					},
					{
						url: "/activity/Jsp/Romania/Romaniaindex/images/carousel_03.png",
						hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel_03_on.png",
					},
					{
						url: "/activity/Jsp/Romania/Romaniaindex/images/carousel_04.png",
						hoverUrl: "/activity/Jsp/Romania/Romaniaindex/images/carousel_04_on.png",
					},
				]
			},
			mounted(){
				console.log("mounted")
				this.initCarousel();
				this.getThreeShoesCateList();
			},
			methods:{
				image(v, params) {
          if (!v) {
              return ""
          }
          if (!params) {
              params = ""
          }
          return imgurl.imageBaseUrl + v + params
        },

				// 轮播
				initCarousel(){
					var mySwiper = new Swiper ('.swiper-container', {
						// 如果需要前进后退按钮
				    navigation: {
				      nextEl: '.swiper-btn-next',
				      prevEl: '.swiper-btn-prev',
				    },

						loop: true, // 循环模式选项
						autoplay:{
							delay: 5000
						},
						slidesPerView : 3,
						spaceBetween : 200,
						// on:{
						// 	transitionStart: function(e){
						// 		console.log("11:")
						// 		console.log(this)
						// 	},
						// },
					})
				},
				// 获取男、女、童鞋子列表
				getThreeShoesCateList(){
					axios.get('/home/prm_PrmGroupPurchase_groupshoplist?lang=en')
				  .then( (res) => {
				    console.log("鞋子列表suc");
				    console.log(res);
						if(res.data.ret != 1) {
							this.$alert('Product acquisition failed, please check the network')
							return
						};
						this.manShoesList = res.data.result.manshoes;
						this.womanShoesList = res.data.result.womanshoes;
						this.childrenShoesList = res.data.result.childrenshoes;
				  })
				  .catch( (error) => {
						console.log("fail");
				    console.log(error);
				  });
				},
				// 收藏
				collect(goodsId,whichShoesList,shoesIndex){
					if( !this.isLogin ){
						this.$message.error('这里需要进行登录弹窗操作');
						return
					}
					axios.post('/home/usr_UsrFavorites_addFavorite', Qs.stringify({
				    pdtPkey: goodsId,
				  },{allowDots: true}))
				  .then( (res) => {
						console.log("收藏suc");
				    console.log(res);
						if(res.data.ret != 1){
							if(res.data.msg){
								this.$message.error(res.data.msg);
							}else{
								this.$message.error('Collection failed, please check if you have logged in');
							}
							return
						}
						let isFavorite = this[whichShoesList][shoesIndex].isFavorite;
						this[whichShoesList][shoesIndex].isFavorite = !isFavorite;
				  })
				  .catch( (err) => {
				    this.$message.error('Request failed, please check the network')
				  });

				},
			}
		})
	</script>

	<!-- 轮播 -->
	<script>

  </script>

	<!-- 代码追踪 -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-113892728-1"></script>
	<script>
	    window.dataLayer = window.dataLayer || [];

	    function gtag() {
	        dataLayer.push(arguments);
	    }

	    gtag('js', new Date());

	    gtag('config', 'UA-113892728-1');
	</script>


	<!--Start of Tawk.to Script-->
	<script type="text/javascript">
		var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
		(function(){
			var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
			s1.async=true;
			s1.src='https://embed.tawk.to/5b8fcb48f31d0f771d8476f6/default';
			s1.charset='UTF-8';
			s1.setAttribute('crossorigin','*');
			s0.parentNode.insertBefore(s1,s0);
		})();
	</script>
	<!--End of Tawk.to Script-->
</body>

</html>
