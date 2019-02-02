<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/home/v2/template/header.jsp"></jsp:include>
    <script src="./js/config.js"></script>
    <link rel="stylesheet" href="css/index.css">
<jsp:include page="/home/v3/nav.jsp"></jsp:include>

<div id="linIntroduceO2O">
    <o2o-top></o2o-top>
    <div class="bannercon">
        <!--<img src="images/introducebanner.png" alt="">-->
        <div class="bannertitle">
            <img src="images/introduceh1.png" alt="">
        </div>
    </div>
    <div class="introduceCon">
        <div class="introduce">
            <h3>{{introducemsg[0].title}}</h3>
            <div class="linhen"></div>
            <h5>Customer could better know the qualitu of a shoe.</h5>
            <div class="introducebody">
                <img class="ripple" :class="top >= srcolltop[0].value ? 'slideInRight' : ''"
                     :src="introducemsg[0].img" alt="">
                <div class="introducemsg ripple"
                     :class=" top >= srcolltop[0].value ? 'slideInLeft' : '' ">
                    <img src="images/introduceicon_ind.png" alt="">
                    <p>{{introducemsg[0].msg}}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="introduceCon">
        <div class="introduce">
            <h3>{{introducemsg[1].title}}</h3>
            <div class="linhen"></div>
            <h5>Customer could better know the qualitu of a shoe.</h5>
            <div class="introducebody">
                <img class="ripple"  :class=" top >= srcolltop[1].value ? ' slideInLeft' : '' "
                     :src="introducemsg[1].img" alt="">
                <div class="introducemsg ripple"
                     :class="top >= srcolltop[1].value? 'slideInRight ' : ''">
                    <img src="images/introduceicon_ind.png" alt="">
                    <p>{{introducemsg[1].msg}}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="introduceCon">
        <div class="introduce">
            <h3>{{introducemsg[2].title}}</h3>
            <div class="linhen"></div>
            <h5>Customer could better know the qualitu of a shoe</h5>
            <div class="introducebody">
                <img class="ripple"  :class="top >= srcolltop[2].value? 'slideInRight' : ''"
                     :src="introducemsg[2].img" alt="">
                <div class="introducemsg ripple"
                     :class=" top >= srcolltop[2].value? 'slideInLeft' : '' ">
                    <img src="images/introduceicon_ind.png" alt="">
                    <p>{{introducemsg[2].msg}}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="bannercon noback">
        <div class="bannertitle bannertitle2">
            <img src="images/introduceh2.png" alt="">
        </div>
    </div>
    <div class="introduceMakitCon">
        <h3>Marketing Channel</h3>
        <a href="highClassO2O.jsp"><span class="buttonr">New Design</span></a>
        <a href="O2Oinputform.jsp"><span class="buttonl">Bargain District</span></a>
    </div>
    <o2o-bottom></o2o-bottom>
</div>
<div class="linIntroduceO2Ofixedimg"></div>
<!--<img class='linIntroduceO2Ofixedimg' src="images/introducebanner2.png" alt="">-->
<script src="components/O2O-top.js"></script>
<script src="components/O2O-bottom.js"></script>
<script>
    new Vue({
        el: '#linIntroduceO2O',
        data: {
            introducemsg: [
                {
                    title: 'Why do customer needs O2O',
                    img: 'images/introduce1.png',
                    msg: 'If a company plan to do both e-commerce and physical (“brick-and-mortar”) stores, then the O2O marketing strategy should is be considering to use. Capitalizes on both presences to drive increased sales. This can work in both directions, but online to offline allows you to reach an arguably broader audience of prospective new customers. E-commerce sales continue to rise year-on-year, while mobile accounts for an increasing of online sales too.  For selling shoes, it is important to make customers touch the real shoes sample, so that the Customer could better know the qualitu of a shoe.'
                },
                {
                    title: 'What kinds of O2O are we?',
                    img: 'images/introduce2.png',
                    msg: 'Shoestp.com is the only e-commerce trading platform for footwear in China. Our main trading mode is B2B. Nowadays, most O2O models coordinate with the online retail business but don\'t forget that B2B also needs O2O to match development. For the shoe industries, almost all wholesalers still need to go to the local factory and check the samples, or they need the factory to send samples to them. This tradtional model will greatly increase the selection time, which would lead to buyers miss great bussiness opportuity. Therefore, we launched O2O for B2B to save the time for wholesalers going abroad to look for samples or waiting for samples delivery, making them more convenient and faster.'
                },
                {
                    title: 'Where is the O2O market of us',
                    img: 'images/introduce3.png',
                    msg: 'For the different grade and price of footwear, we will promote new design O2O and bargain district O2O. The new design O2O is focused on the latest design by  original design manufacturers in China. We collect their shoes directly and displayed them in offline stores, so that wholesalers could touch the latest designs without leaving their regions. The bargain district O2O area is targeted at developing country markets such as Africa, parts of India, Latin America, etc. In order to facilitate customers to search online shoes and offline to check the real thing at same time, so that they know more about the products they buy.'
                }
            ],

            top: 0,

            //高度
            srcolltop: [
                {
                    key: false,
                    value: 30
                },
                {
                    key: false,
                    value: 600
                },
                {
                    key: false,
                    value: 1300
                }
            ]
        },
        methods: {
            getScrollOffset: function () {
                var y = 0
                if (window.pageXOffset) {
                    y = window.pageYOffset
                } else {
                    y = document.body.scrollTop + document.documentElement.scrollTop
                }
                this.$set(this, 'top', y)
                for (let i = 0; i < 3; i++) {
                    if (y >= this.srcolltop[0].value && !this.srcolltop[0].key) {
                        this.$set(this.srcolltop[0], 'value', !true)
                    }
                    if (y >= this.srcolltop[1].value && !this.srcolltop[1].key) {
                        this.$set(this.srcolltop[1], 'value', !true)
                    }
                    if (y >= this.srcolltop[2].value && !this.srcolltop[2].key) {
                        this.$set(this.srcolltop[2], 'value', !true)
                    }
                }
                console.log(this.top)
            }
        },
        mounted() {
            this.getScrollOffset();
            window.onmousewheel = document.onmousewheel = this.getScrollOffset;
        }
    })
</script>
</body>
</html>