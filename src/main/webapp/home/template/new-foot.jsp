<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="new_foot" style="width:100%">
    <div class="wide" style="width:1200px;">
        <div class="foot_letter fr">
            <div class="t"><s:text name="new-foot.Subscribe_To_Our_Newsletter"/></div>
            <div class="t0"><s:text name="new-foot.Get_Information_Of_Our_Latest_Products_And_Promotions"/></div>
            <div class="letter_form">
                <form id="newsletter_form">
                    <input type="text" value="" id="Email" class="text" notnull="" format="Email">
                    <input type="button" value=
                    <s:text name="new-foot.Subscribe"/> onclick="subsribe()" class="btn button">
                </form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="folink">
        <div class="wide clean">
            <div class="img fl pic_box">
                <a href="https://www.shoeslogo.com/" target="_blank"><img src="/home/static/images/854464db07.png"
                                                                          alt="shoeslogo.com"></a><span></span>
            </div>
            <div class="img fl pic_box">
                <a href="http://www.shoesmat.com/" target="_blank"><img src="/home/static/images/0f12682298.png"
                                                                        alt="shoesmat.com"></a><span></span>
            </div>
            <div class="img fl pic_box">
                <a href="http://www.shoesrd.com/" target="_blank"><img src="/home/static/images/3c27f32d85.png"
                                                                       alt="shoesrd.com"></a><span></span>
            </div>
            <div class="img fl pic_box">
                <a href="http://www.wzsomt.com/" target="_blank"><img src="/home/static/images/d30276cc0d.png"
                                                                      alt="wzsomt.com"></a><span></span>
            </div>
        </div>
    </div>
    <div class="focontact">
        <div class="wide">
            <div class="fc fl">
                <div class="fl"><s:text name="new-foot.Share_It"/>:</div>
                <div class="fl fcfollow">
                    <div class="follow_toolbox clearfix">
                        <ul>
                            <li>
                                <a rel="nofollow" class="follow_facebook" href="#" target="_blank"
                                   title="facebook">Facebook</a>
                            </li>
                            <li>
                                <a rel="nofollow" class="follow_twitter" href="#" target="_blank" title="twitter">Twitter</a>
                            </li>
                            <li>
                                <a rel="nofollow" class="follow_pinterest" href="#" target="_blank" title="pinterest">Pinterest</a>
                            </li>
                            <li>
                                <a rel="nofollow" class="follow_linkedin" href="#" target="_blank"
                                   title="linkedin">LinkedIn</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="fd fr">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备
                33030402000493号 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </div>
</div>
<div id="showe" style="display:none;">
    <span id="showtext"
          style="background-color: #20b3a6;position: fixed;top: 38%;left: 47%;opacity: 0.8;padding: 20px;border-radius:  14px;color: #fff;font-size: 24px;"></span>
</div>

<script>
    $(document).ready(function () {
        $("div.focontact div.follow_toolbox.clearfix a").hover(
            function () {
                $(this).css("margin-top", "-4px")
            }, function () {
                $(this).css("margin-top", "0px")
            }
        )
        $("div.focontact div.follow_toolbox.clearfix a").each(function (i, v) {
            var image = back_url = encode_url = "";
            url = window.location.href;
            if (url.indexOf("#") > 0) {
                url = url.substring(0, url.indexOf("#"));
            }
            var e_url = encodeURIComponent(url);
            title = $(v).attr("title")
            switch (title) {
                case "delicious":
                    back_url = "https://delicious.com/post?title=" + title + "&url=" + e_url;
                    break;
                case "digg":
                    back_url = "http://digg.com/submit?phase=2&url=" + e_url + "&title=" + title
                        + "&bodytext=&topic=tech_deals";
                    break;
                case "reddit":
                    back_url = "http://reddit.com/submit?url=" + e_url + "&title=" + title;
                    break;
                case "furl":
                    back_url = "http://www.furl.net/savedialog.jsp?t=" + title + "&u=" + e_url;
                    break;
                case "rawsugar":
                    back_url = "http://www.rawsugar.com/home/extensiontagit/?turl=" + e_url + "&tttl="
                        + title;
                    break;
                case "stumbleupon":
                    back_url = "http://www.stumbleupon.com/submit?url=" + e_url + "&title=" + title;
                    break;
                case "blogmarks":
                    break;
                case "facebook":
                    back_url = "http://www.facebook.com/share.php?src=bm&v=4&u=" + e_url + "&t=" + title;
                    break;
                case "technorati":
                    back_url = "http://technorati.com/faves?sub=favthis&add=" + e_url;
                    break;
                case "spurl":
                    back_url = "http://www.spurl.net/spurl.php?v=3&title=" + title + "&url=" + e_url;
                    break;
                case "simpy":
                    back_url = "http://www.simpy.com/simpy/LinkAdd.do?title=" + title + "&href=" + e_url;
                    break;
                case "ask":
                    break;
                case "google":
                    back_url = "http://www.google.com/bookmarks/mark?op=edit&output=popup&bkmk=" + e_url
                        + "&title=" + title;
                    break;
                case "netscape":
                    back_url = "http://www.netscape.com/submit/?U=" + e_url + "&T=" + title + "&C=";
                    break;
                case "slashdot":
                    back_url = "http://slashdot.org/bookmark.pl?url=" + url + "&title=" + title;
                    break;
                case "backflip":
                    back_url = "http://www.backflip.com/add_page_pop.ihtml?title=" + title + "&url="
                        + e_url;
                    break;
                case "bluedot":
                    back_url = "http://bluedot.us/Authoring.aspx?u=" + e_url + "&t=" + title;
                    break;
                case "kaboodle":
                    back_url = "http://www.kaboodle.com/za/selectpage?p_pop=false&pa=url&u=" + e_url;
                    break;
                case "squidoo":
                    back_url = "http://www.squidoo.com/lensmaster/bookmark?" + e_url;
                    break;
                case "twitter":
                    back_url = "https://twitter.com/intent/tweet?status=" + title + ":+" + e_url;
                    break;
                case "pinterest":
                    back_url = "http://pinterest.com/pin/create/button/?url=" + e_url + "&media="
                        + "&description=" + title;
                    break;
                case "vk":
                    back_url = "http://vk.com/share.php?url=" + url;
                    break;
                case "bluedot":
                    back_url = "http://blinkbits.com/bookmarklets/save.php?v=1&source_url=" + e_url
                        + "&title=" + title;
                    break;
                case "blinkList":
                    back_url = "http://blinkbits.com/bookmarklets/save.php?v=1&source_url=" + e_url
                        + "&title=" + title;
                    break;
                case "linkedin":
                    back_url = "http://www.linkedin.com/cws/share?url=" + e_url + "&title=" + title;
                    break;
                case "googleplus":
                    back_url = "https://plus.google.com/share?url=" + e_url;
                    break;
            }
            $(v).attr("href", back_url)

        })
    })
</script>

<script type="text/javascript">
    var listpage = {};
    $.ajax({
        type: 'post',
        async: false,
        url: '/home/cnt_CntSglPageCategory_listAll',
        dataType: 'json',
        success: function (data) {
            if (data.ret == 1) {
                listpage = data.result;
                $.each(data.result, function (i, val) {
                    var str = "<div class='foot_list fl'>"
                        + "<div class='title_" + val.id + " title'>" + val.name + "</div>";
                    $.each(val.pages, function (j, jal) {
                        str = str + "<div class='item'>"
                            + "<a href='/home/cnt_CntSglPageCategory_gosglpage?pkey=" + jal.id + "' target='_blank'>" + jal.title + "</a>"
                            + "</div>";
                    })
                    str = str + "</div>";
                    $("#new_foot div.wide .foot_letter.fr").before(str);
                })
                console.log("${sessionScope["SESSION_MSG"].isSupplier()}");
                if ("${sessionScope["SESSION_MSG"].isSupplier()}") {
                    $(".title_3").parent().append("<div class='item'><a href='/home/usr_UsrSupplier_supplierEntry' target='_blank'>" + lang_obj.addressfrom.Merchants_Settled + "</a></div>");
                } else {
                    $(".title_3").parent().append("<div class='item'><a href='/seller/admin/index/login.html' target='_blank'>" + lang_obj.addressfrom.Merchant_Side + "</a></div>");
                }
                $(".title_3").parent().append("<div class='item'><a href='/seller/admin/index/login.html' target='_blank'>店铺管理</a></div>")
            }
        }
    })


    function subsribe() {
        if (/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test($('#Email').val()) == false) {
            $("#showtext").text(lang_obj.format.email);
            $("#showe").fadeIn();
            setTimeout("emailshow()", 2000);
        } else {
            $("#showtext").text(lang_obj.newsletter.success);
            $.ajax({
                type: 'post',
                url: '/home/usr_UsrSubscribe_ins',
                data: {"bean.email": $('#Email').val()},
                success: function () {
                }
            })
            $("#showe").fadeIn();
            setTimeout("emailshow()", 2000);

        }
    }

    function emailshow() {
        $("#showe").fadeOut();
    }


</script>
<%--新的平台统计代码--%>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-113892728-1"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'UA-113892728-1');
</script>
