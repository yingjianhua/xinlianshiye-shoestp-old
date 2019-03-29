<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
    <script src="./js/config.js"></script>
    <link rel="stylesheet" href="css/index.css">
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
<style>
     #o2obottom .o2obottomlinks ul{
        color: black;
    }
</style>
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
            <h5>Customer could better know the quality of a shoe.</h5>
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
            <h5>Customer could better know the quality of a shoe.</h5>
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
            <h5>Customer could know better about the quality of shoes.</h5>
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
        <a href="/home/pdt_PdtProduct_o2oList"><span class="buttonr">Bargain District</span></a>
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
                    title: 'Why does customer need O2O?',
                    img: 'images/introduce1.png',
                    msg: 'If a company plans to have both e-commerce and physical (“brick-and-mortar”) stores, then the O2O marketing strategy that capitalizes on both presences to drive increased sales should be considered. This can work in both directions, but online to offline allows you to reach an arguably broader audience of prospective new customers. E-commerce sales continue to rise year-on-year, while mobile accounts for an increasing of online sales too. In the shoes business, it is important to reassure customers with the quality by providing them with the shoes sample.'
                },
                {
                    title: 'What kind of O2O are we?',
                    img: 'images/introduce2.png',
                    msg: 'Shoestp.com is the only e-commerce trading platform for footwear in China. Our main trading mode is B2B. Nowadays, most O2O models coordinate with the online retail business but don\'t forget that B2B also needs O2O to match the development. For the shoe industries, almost all wholesalers still need to checking the samples by going to the local factory, otherwise they need to have the factory send them samples. This traditional model takes buyers a long time to make decisions, which leads to the situation where buyers miss great business opportunities. Therefore, we launched O2O for B2B to save the time for wholesalers to go abroad to look for samples or wait for samples delivery, which makes it more convenient and faster.'
                },
                {
                    title: 'Where is the O2O market for us?',
                    img: 'images/introduce3.png',
                    msg: 'According to the different levels of grade and price of footwear, we will promote new design and bargain district for O2O. The newest design for O2O is from the latest design by manufacturers in China. We collect the shoes from their factories and display them in the offline stores, so the wholesalers could see the latest designs in person and even without leaving their regions. The bargain district O2O area is targeted at markets in developing countries, such as South Africa, India, Mexico, Brazil, etc. The customers can know better about the product they buy by searching online and check the real shoes offline.'
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
