<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/reset.css"/>
<link rel="stylesheet" href="css/index.css"/>
<script src="./js/tagcloud.js"></script>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>

<main id="svsApp">
    <header class="top-bg">
        <div class="">
            <nav class="w_1200 clearfix">
                <div class="clearfix logo fl">
                    <a href="https://www.shoestp.com/" class="fl" target="_blank"><img class="fl"
                                                                                       src="./images/top-logo.png"
                                                                                       alt=""></a>
                    <div class="fl font">SVS Certification System</div>
                </div>
                <div class="fr nav-item">
                    <a href="javascript:void(0)" @click="myanimate('supplier')">Supplier</a>
                    <a href="javascript:void(0)">Feature</a>
                    <a href="javascript:void(0)" @click="myanimate('advantage')">Advantage</a>
                    <a href="javascript:void(0)" @click="myanimate('benefits')">Benefits</a>
                </div>
            </nav>
            <div class="w_1200 top-font fadeInLeft ripple">
                <h1>SVS Certification System</h1>
                <p>
                    SVS is a Certification System for the factory.<br>
                    SVS based on key aspects which shoes buyers'most care for, <br>
                    such as actual manufactor, production capacity, product quality, R&D capacity, trade capacity, trade
                    show, etc.
                </p>
            </div>
        </div>
    </header>
    <section class="service">
        <div class="w_1200 clearfix">
            <div class="service-box">
                <div class="service-item ripple">
                    <h2 class="ripple slideInUp">On-Site Audits</h2>
                    <p class="ripple slideInUp">Shoestp will send professionals to conduct on-site audits as a third party.</p>
                    <img class="ripple slideInUp" src="./images/label1.png" alt="">
                </div>
                <div class="service-item ripple">
                    <h2 class="ripple slideInUp">Classify Factories</h2>
                    <p class="ripple slideInUp">Shoestp will classify factories by their actual strength.</p>
                    <img class="ripple slideInUp" src="./images/label2.png" alt="">
                </div>
                <div class="service-item ripple">
                    <h2 class="ripple slideInUp">ROS</h2>
                    <p class="ripple slideInUp">Shoestp will provide a more detailed classification of suppliers in three aspects of R&D, Output, and Scale.</p>
                    <img class="ripple slideInUp" src="./images/label3.png" alt="" style=" margin-top: 20px;">
                </div>
            </div>
        </div>
    </section>
    <section class="certification-process">
        <div class="w_1200">
            <div class="title">
                <h1>SVS Certification Process</h1>
                <p>The Auditors on-site checking ensures that factory information is true and objective.</p>
            </div>
            <div>
                <img src="./images/certification-process.png" alt="">
            </div>
        </div>
    </section>
    <section class="advantage" id="advantage">
        <div class="w_1200">
            <h1>SVS Advantage</h1>
            <div class="flexSb">
                <div class="advantage-item ripple" :class="scrollTop1?'fadeInRight':''">
                    <p>SVS includes factors that buyers are much
                        concerned about when selecting suppliers</p>
                    <img src="./images/advantage1.png" alt="">
                </div>
                <div class="shadow-line">
                    <img src=".//images/shadow-line.png" alt="">
                </div>
                <div class="advantage-item ripple" :class="scrollTop1?'fadeInLeft':''">
                    <p>SVS shows the performance of <br>
                        manufacturers objectively in all aspects</p>
                    <img src="./images/advantage2.png" alt="">
                </div>
            </div>
        </div>
    </section>
    <section class="benchmarks">
        <div class="w_1200">
            <h1 style="padding-top:65px;">How Do You Benefit From SVS</h1>
            <h1 style="color: #2f4597;margin-bottom:50px;">The 7 SVS benchmarks</h1>
            <div class="benchmarks-main">
                <ul class="benchmarks-list clearfix">
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_manu.png" alt="">
                            <div>Actual Manufacturer</div>
                        </div>
                        <div class="fr benchmarks-info">
                            Business license <br>
                            Warehouse management system<br>
                            Number of employees<br>
                            Export license
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_proCap.png" alt="">
                            <div>Production Capacity</div>
                        </div>
                        <div class="fr benchmarks-info">
                            Sewing machines <br>
                            Number of production lines<br>
                            Annual export value
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_proq.png" alt="">
                            <div>Product Quality</div>
                        </div>
                        <div class="fr benchmarks-info">
                            ISO certificate<br>
                            Third-party certification<br>
                            Test equipment and facilities
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_R&D.png" alt="">
                            <div>R&D Capacity</div>
                        </div>
                        <div class="fr benchmarks-info">
                            R&D department<br>
                            Patent certificate<br>
                            Annual Number of New shoes
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_tradecap.png" alt="">
                            <div>Trade Capacity</div>
                        </div>
                        <div class="fr benchmarks-info">
                            Foreign trade team<br>
                            Staff number of trade team<br>
                            Sales experience
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_tradeshows.png" alt="">
                            <div>Trade Shows</div>
                        </div>
                        <div class="fr benchmarks-info">
                            Exhibitions attended in the past three years
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="fl benchmarks-name">
                            <img src="./images/icon_majorC.png" alt="">
                            <div>Major Client</div>
                        </div>
                        <div class="fr benchmarks-info">
                            Client who cooperated currently or in the<br>
                            past two years
                        </div>
                    </li>
                    <li class="clearfix">

                    </li>
                </ul>
            </div>
        </div>
    </section>
    <section class="benefits" id="benefits">
        <div class="w_1370 flexSb">
            <div class="font ripple" :class="scrollTop2?'fadeInDown':''">
                <h1>The Benefits of SVS</h1>
                <p>SVS Certification System helps buyer find their target Supplier more faster, accurate and time-saving than any other way.</p>
            </div>
            <div class="">
                <img src="./images/bannerbg3.png" alt="">
            </div>
        </div>
    </section>
    <section class="supplier" id="supplier">
        <div class="supplier-font">
            <h1 class="ripple" :class="scrollTop3?'fadeInLeft':''">Who Can Be SVS Supplier？</h1>
            <p class="ripple" :class="scrollTop3?'fadeInLeft':''">All SVS suppliers have been verified and approved by relevant personnel of platform.</p>
            <!-- <h2 class="ripple" :class="scrollTop3?'fadeInRight':''">Why establish SVS Certification system</h2>
            <p class="ripple" :class="scrollTop3?'fadeInRight':''">Shoestp is committed to improving the efficiency of buyers and making buyers picking suppliers <br>
                fastly and accurately. We creat SVS is for make buyers to get better buying experence.</p> -->
        </div>
        <div class="supplier-btn ripple" :class="scrollTop3?'bounce':''">
            <a href="./svsSuplies.jsp" target="_blank" style="width:100%;height:100%">
                Look SVS Suppliers Now
                <img src="./images/right-arrow.png" alt="">
            </a>
        </div>
        <div style="color:#1989fa;text-align:center;margin:20px 0;font-size:18px;">
            We recommend 50 qualified suppliers to you.
        </div>
    </section>
    <section class="tagcloud">
        <div v-for="item in tagcloud"><p>{{item.content}}</p>
            <p>-----from {{item.name}}</p></div>
    </section>
    <index-bottom></index-bottom>
</main>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el: "#svsApp",
        data: {
            scrollTop1: false,
            scrollTop2: false,
            scrollTop3: false,
            scrollTop: '',
            tagcloud: [
                {content: "checking on # 1600159090 When will I get them?", name: "Da***ar"},
                {content: "Window shopping ", name: "Ph***a"},
                {content: "Wholesale shoes ", name: "Ni***rd"},
                {content: "hello", name: "Je***al"},
                {content: "Prices", name: "Ga***on"},
                {content: "Boots and booties", name: "As***ey"},
                {content: "are you wholesale", name: "mo***il"},
                {content: "Inquiry about importing your products.", name: "Go***si"},
                {content: "Interested in selling your merchandise", name: "Ka***nt"},
                {content: "Wish to purchase wholesale shoes", name: "God***en"},
                {content: "Shoes", name: "Dee***bi"},
                {content: "Search", name: "Ray***th"},
                {
                    content: "I am interested in obtaining a catalog for my online shoe store. Please send me any available information to start.",
                    name: "Ten***er"
                },
                {content: "Shoues", name: "Za***ya"},
                {content: "Want to buy women shoes", name: "Omo***ka"},
                {content: "Need wholesale information", name: "Shi***ks"},
            ]

        },
        mounted() {
            window.addEventListener('scroll', this.handleScroll)
        },
        methods: {
            handleScroll() {
                var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
                if (scrollTop >= 1200) {
                    this.scrollTop1 = true;
                }
                if (scrollTop >= 2600) {
                    this.scrollTop2 = true;
                }
                if (scrollTop >= 3100) {
                    this.scrollTop3 = true;
                }
            },
            myanimate: function (id) {
                document.getElementById(id).scrollIntoView(true);
            }
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
