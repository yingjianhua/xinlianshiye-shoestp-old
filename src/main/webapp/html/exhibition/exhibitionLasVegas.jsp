<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/home/v2/template/header.jsp"></jsp:include>
<script src="./js/config.js"></script>
<link rel="stylesheet" href="css/index.css">
<style>
    .wide {
        width: 1240px;
        margin: 0 auto;
    }
</style>

<jsp:include page="/home/v2/template/nav.jsp"></jsp:include>
<div id="linExhibitionLasVages">
    <div class="lasvagesbannercon">
        <img class='lasvagesbanner' src="images/bannerlasvages.png" alt="">
    </div>
    <div class="lasvagesdetailconout">
        <div class="lasvagesdetailcon">
            <div class="h2con">
                <h2>DETAIL</h2>
                <div class="hen"></div>
            </div>
            <div class="lasvagesdetail">
                <video controls autoplay loop>
                    <source src="images/video.MP4"/>
                </video>
                <div class="detailcon">
                    <h3>FOOTWEAR SOURCING AT MAGIC</h3>
                    <div class="hen"></div>
                    <p>FOOTWEAR SOURCING AT MAGIC is the only show created for brands and retailers to source their footwear
                        production directly from factories around the globe. Conveniently located alongside our
                        international footwear show, FN PLATFORM, FOOTWEAR SOURCING AT MAGIC allows designers, product
                        development teams, and private label marketers to connect, shop, and expand their businesses on a
                        global scale.</p>
                </div>
            </div>
            <h3 class="h31">LAS VEGAS CONVENTION CENTER</h3>
            <h3 class="h32">3150 Paradise Rd. Las Vegas, NV 89109</h3>
            <div class="lasvagescon">
                <div class="lasvagescon1">
                    <div class="top">
                        <img src="images/lasvagescon1.png" alt="">
                        <h4>SHOW HOURS</h4>
                    </div>
                    <p>MONDAY, FEBRUARY 4<br>
                        12:00PM - 6:00PM<br>
                        (SOURCING AT MAGIC ADB<br>
                        FOOTWEAR SOURCING AT<br>
                        MAGIC ONLY)<br><br>

                        TUESDAY, FEBRUARY 5<br>
                        9:00AM - 6:00PM<br><br>

                        WEDNESDAY, FEBRUARY 6<br>
                        9:00AM - 6:00PM<br><br>

                        THURSDAY, FEBRUARY 7<br>
                        9:00AM - 4:00PM<br>
                        (SOURCING AT MAGIC ONLY)<br>
                        LAS VEGAS CONVENTION CENTER:<br>
                        9:00AM - 5:00PM</p>
                </div>
                <div class="lasvagescon1">
                    <div class="top">
                        <img src="images/lasvagescon2.png" alt="">
                        <h4>ATTENDEE<br>REGISTRATION HOURS</h4>
                    </div>
                    <p>SUNDAY, FEBRUARY 3<br>
                        8:00AM - 6:00PM<br><br>

                        MONDAY, FEBRUARY 4<br>
                        8:00AM - 8:00PM<br><br>

                        TUESDAY, FEBRUARY 5<br>
                        8:00AM - 6:00PM<br><br>

                        WEDNESDAY, FEBRUARY 6<br>
                        8:00AM - 6:00PM<br><br>

                        THURSDAY, FEBRUARY 7<br>
                        8:00AM - 5:00PM</p>
                </div>
                <div class="lasvagescon2">
                    <p>PARTICIPANTS<br><br>
                        6000 VISITORS<br>
                        500 EXHIBITORS<br>
                        ESTIMATED COUNT<br><br>
                        ENTRY FEES<br><br>
                        FREE*<br>
                        FOR INDUSTRY<br>
                        PROFESSIONALS<br><br>
                        CATEGORY & TYPE<br><br>
                        TRADE SHOW<br>
                        APPAREL & CLOTHING</p>
                </div>
            </div>
            <div class="h2con">
                <h2>GALLERY</h2>
                <div class="hen"></div>
            </div>
            <img class="img1" src="images/lasvagesimgs1.png" alt="">
        </div>
    </div>
    <div class="lasvagesimgscon">
        <img src="images/lasvagesimgs2.png" alt="">
        <h2>Venue Map & Directions</h2>
        <div class="map">
            <iframe
                src="http://www.google.cn/maps/embed?pb=!1m18!1m12!1m3!1d145836.0008093548!2d-115.12423013907598!3d36.12882713541526!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x80c8c46640af22e7%3A0xa93a4afe2fe7b046!2sLas+Vegas+Convention+Center!5e0!3m2!1szh-CN!2scn!4v1548672922122"
                width="1040" height="474" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
        <h2>LAS VEGAS AND MANDALAY BAY CONVENTION CENTERS<br>LAS VEGAS<br> USA</h2>
    </div>
</div>
<script>
    new Vue({
        el: '#linExhibitionLasVages',
        data: {

        },
        methods: {

        },
        mounted() {

        }
    })
</script>
<jsp:include page="/home/v2/template/foot.jsp"></jsp:include>

</body>
</html>
