<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/home/v3/header.jsp"></jsp:include>
    		<link rel="stylesheet" href="css/reset.css" />
    <link rel="stylesheet" href="css/index.css" />
    <script src="./js/tagcloud.js"></script>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
	<main id="svsApp">
      <header class="top-bg">
          <div class="">
            <nav class="w_1200 clearfix">
                <div class="clearfix logo fl">
                  <img class="fl" src="./images/top-logo.png" alt="">
                  <div class="fl font">SVS Certification System</div>
                </div>
                <div class="fr nav-item">
                    <a href="javascript:void(0)">Supplier</a>
                    <a href="javascript:void(0)">Feature</a>
                    <a href="javascript:void(0)">Advantage</a>
                    <a href="javascript:void(0)">Benefits</a>
                </div>
            </nav>
            <div class="w_1200 top-font slideInLeft ripple">
                <h1>SVS Certification System</h1>
                <p>SVS is a Certification System for the factory and the SVS based on key aspect from factories <br>
                    which are shoes buyers’s most care for. SVS Certification System is taking into account <br>
                    aspects such as genuine manufacturer, production capacity, quality control, <br>
                   research & development capability, International business capacity <br>
                   and major cooperation client by factories in the past, etc.</p>
            </div>
          </div>
        </header>
        <section class="service">
          <div class="w_1200 clearfix">
              <div class="service-box">
                  <div class="service-item ripple">
                      <h2 class="ripple slideInUp">Send Professionals</h2>
                      <p class="ripple slideInUp">the Shoestp as a third party will send
                          professionals to conduct on-site audits</p>
                          <img class="ripple slideInUp" src="./images/label1.png" alt="">
                  </div>
                  <div class="service-item ripple">
                      <h2 class="ripple slideInUp">Classify Factories</h2>
                      <p class="ripple slideInUp">Second, according to the factory strength,
                          Shoestp will classify factories.</p>
                          <img class="ripple slideInUp" src="./images/label2.png" alt="">
                  </div>
                  <div class="service-item ripple">
                      <h2 class="ripple slideInUp">Classification of Suppliers</h2>
                      <p class="ripple slideInUp">Third, a more detailed classification of suppliers
                          from the three dimensions of R&D, Output,
                          and Scale.</p>
                          <img class="ripple slideInUp" src="./images/label3.png" alt="" style=" margin-top: 20px;">
                  </div>
              </div>
          </div>
        </section>
        <section class="advantage">
            <div class="w_1200">
                <h1>SVS Advantage</h1>
                <div class="flexSb">
                  <div class="advantage-item ripple" :class="scrollTop1?'slideInRight':''">
                    <p>First,  SVS includes factors that buyers are much
                        concerned about when selecting suppliers</p>
                    <img src="./images/advantage1.png" alt="">
                  </div>
                  <div class="shadow-line">
                    <img src=".//images/shadow-line.png" alt="">
                  </div>
                  <div class="advantage-item ripple" :class="scrollTop1?'slideInLeft':''">
                      <p>First,  SVS includes factors that buyers are much
                          concerned about when selecting suppliers</p>
                      <img src="./images/advantage2.png" alt="">
                    </div>
                </div>
            </div>
        </section>
        <section class="benefits">
            <div class="w_1370 flexSb">
                <div class="font ripple" :class="scrollTop2?'slideInDown':''">
                    <h1>The Benefits of SVS</h1>
                    <p>SVS Certification System will make buyers find
                        their target Supplier more faster, accurate and
                        time-saving than other way.</p>
                </div>
                <div class="">
                  <img src="./images/bannerbg3.png" alt="">
                </div>
            </div>
        </section>
        <section class="supplier">
          <div class="supplier-font">
            <h1 class="ripple" :class="scrollTop3?'slideInLeft':''">What is SVS supplier？</h1>
            <p class="ripple" :class="scrollTop3?'slideInLeft':''">Our SVS suppliers that have been verified by relevant personnel and approved by the platform.</p>
            <h2 class="ripple" :class="scrollTop3?'slideInRight':''">Why establish SVS Certification system</h2>
            <p class="ripple" :class="scrollTop3?'slideInRight':''">Shoestp is committed to improving the efficiency of buyers and making buyers picking suppliers <br>
                fastly and accurately. We creat SVS is for make buyers to get better buying experence.</p>
          </div>
          <div class="supplier-btn ripple" :class="scrollTop3?'bounce':''">
              Joining suppliers
              <img src="./images/right-arrow.png" alt="">
          </div>
        </section>
        <section class="tagcloud">
            <div v-for="item in tagcloud"><p>{{item.content}}</p><p>-----来自{{item.name}}</p></div>
        </section>
        <o2o-bottom></o2o-bottom>
  </main>
    <script src="components/O2O-bottom.js"></script>
  <script>
      new Vue({
			el: "#svsApp",
			data:{
        scrollTop1:false,
        scrollTop2:false,
        scrollTop3:false,
        scrollTop:'',
          tagcloud:[
            {content:"checking on # 1600159090 When will I get them?",name:"Da***ar"},
            {content:"Window shopping ",name:"Ph***a"},
            {content:"Wholesale shoes ",name:"Ni***rd"},
            {content:"hello",name:"Je***al"},
            {content:"Prices",name:"Ga***on"},
            {content:"Boots and booties",name:"As***ey"},
            {content:"are you wholesale",name:"mo***il"},
            {content:"Inquiry about importing your products.",name:"Go***si"},
            {content:"Interested in selling your merchandise",name:"Ka***nt"},
            {content:"Wish to purchase wholesale shoes",name:"God***en"},
            {content:"Shoes",name:"Dee***bi"},
            {content:"Search",name:"Ray***th"},
            {content:"I am interested in obtaining a catalog for my online shoe store. Please send me any available information to start.",name:"Ten***er"},
            {content:"Shoues",name:"Za***ya"},
            {content:"Want to buy women shoes",name:"Omo***ka"},
            {content:"Need wholesale information",name:"Shi***ks"},
          ]

			},
			mounted () {
        window.addEventListener('scroll', this.handleScroll)
      },
			methods:{
				handleScroll () {
          var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
          if(scrollTop >= 330){
            this.scrollTop1 = true;
          }
          if(scrollTop >= 900){
            this.scrollTop2 = true;
          }
          if(scrollTop >= 1400){
            this.scrollTop3 = true;
          }
        },
			}
		})
  </script>
  <script>
      /*3D标签云*/
    tagcloud({
      selector: ".tagcloud", //元素选择器
      fontsize: 17, //基本字体大小, 单位px
      radius: 150, //滚动半径, 单位px
      mspeed: "slow", //滚动最大速度, 取值: slow, normal(默认), fast
      ispeed: "fast", //滚动初速度, 取值: slow, normal(默认), fast
      direction: 135, //初始滚动方向, 取值角度(顺时针360): 0对应top, 90对应left, 135对应right-bottom(默认)...
      keep: false //鼠标移出组件后是否继续随鼠标滚动, 取值: false, true(默认) 对应 减速至初速度滚动, 随鼠标滚动
    });
  </script>
</body>
</html>