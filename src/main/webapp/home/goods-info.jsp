<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<head>
    <title>
        ${seoView.title}
    </title>
    <c:if test="${seoView.keyWord!=null}">
        <meta name="keyword" content="${seoView.keyWord}"/>
    </c:if>
    <c:if test="${seoView.description!=null}">
        <meta name="description" content="${seoView.description}"/>
    </c:if>
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="/static/js/plugins/goodsInfo/jquery.SuperSlide.2.1.1.js"></script>
    <link href="./static/css/goodsInfoNew.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/main.js"></script>
    <script type="text/javascript" src="./static/js/lightbox.min.js"></script>
    <script type="text/javascript" src="./static/js/layer.js"></script>
    <script type="text/javascript" src="/home/static/js/vue.min.js"></script>
    <script type="text/javascript" src="/home/static/js/qs.js"></script>
    <script type="text/javascript" src="/home/static/js/axios.min.js"></script>
</head>
<body id="goodsInfo" class="lang_en w_1200">
<jsp:include page="template/web-top.jsp"></jsp:include>
<jsp:include page="template/new-header.jsp"></jsp:include>
<div id="app">

    <!--头部-->
    <div class="xmgHead wide-wrap">
        <div class="w1200">
            <div class="xmgImg fl" v-if="_logo.length>0">
                <a :href="'/home/usr_UsrSupplier_gtSupIndex?pkey='+goodsInfo.supId">
                    <img :src="_logo" :alt="goodsInfo.supName"/>
                </a>
            </div>
            <div class="mxgP fl">
                <i v-if="_logo.length>0"></i>

                <p><a :href="'/home/usr_UsrSupplier_gtSupIndex?pkey='+goodsInfo.supId">{{goodsInfo.supName}}</a>
                </p>
                <%--<a href="#" target="_blank">--%>
                <%--<img class="xmgIcon1" src="./static/images/certification-o.png" alt="#"/>--%>
                <%--</a>--%>
                <%--<a href="#" target="_blank">--%>
                <%--<img class="xmgIcon2" src="./static/images/defence-safe-o.png" alt="#"/>--%>
                <%--</a>--%>
                <c:if test="${supView.isAuth == 1}">
                    <a id="c1" data="${supView.pkey }">
                        <img class="icon" src="/home/static/images/certification-o.png" alt="">
                    </a>

                    <a id="c2" data="${supView.pkey }">
                        <img class="icon" src="/home/static/images/defence-safe-o.png" alt="">
                    </a>
                </c:if>
                <c:if test="${not empty supView.authAge && supView.authAge > 0}">
                    <a class="icon-years-limit" id="c3" data="${supView.pkey }">
                        <img class="icon" src="/home/static/images/doller_pic_o.png" alt="">
                        <span class="years-num">${supView.authAge}</span>
                        <span class="years-unit">YRS</span>
                    </a>
                </c:if>
            </div>
            <a class="xmgBtn fr"
               :href="'/home/usr_UsrConsult_publishView?supplierId='+goodsInfo.supId"
               target="_blank"></a>
        </div>
    </div>
    <!--菜单-->
    <div class="xmgTabNav w1200">
        <ul>
            <li>
                <a :href="'/home/usr_UsrSupplier_gtSupIndex?pkey='+goodsInfo.supId"><s:text
                        name="Global.Home"/></a>
            </li>
            <li>
                <a :href="'/home/usr_UsrSupplier_gtSupPro?pkey='+goodsInfo.supId"><s:text
                        name="shop-header.Product_Center"/></a>
            </li>
            <li>
                <a :href="'/home/usr_UsrSupplier_gtSupInfo?pkey='+goodsInfo.supId"><s:text
                        name="Global.Company"/></a>
            </li>
            <li>
                <a :href="'/home/usr_UsrSupplier_gtSupContact?pkey='+goodsInfo.supId"><s:text
                        name="contactUs"/></a>
            </li>
            <%--<li>--%>
            <%--<a :href="'/home/usr_UsrSupplier_gtSupGroup?pkey='+goodsInfo.supId"><s:text--%>
            <%--name="group_order.activity"/></a>--%>
            <%--</li>--%>
        </ul>
    </div>

    <!--页面路径-->
    <div class="xmgLuyou w1200">
        <ul>
            <li><s:text name="Global.Your_Location"/>:</li>
            <li><a href="/"><s:text name="Global.Home"/></a></li>
            <li v-for="(v,index) in goodsInfo.breadcrumbNav" v-if="goodsInfo.breadcrumbNav"
                :key="v.pkey"
                :class="(index===goodsInfo.breadcrumbNav.length-1)?'xmgLast':''">
                <a :href="'/home/pdt_PdtProduct?cated='+v.pkey">{{v.name}}</a>
            </li>
        </ul>
    </div>


    <div class="w1200">

        <div class="xmgSeleBOX clearfloat">
            <!--轮播放大镜-->
            <div class="xmgL fl">
                <div id="picBoxs">
                    <div id="small-box">
                        <div id="mark"></div>
                        <div id="float-box"></div>
                        <img :src="_pdtPic+'?x-oss-process=image/resize,m_pad,h_500,w_500'"/>
                    </div>
                    <div id="big-box">
                        <img :src="_pdtPic"/>
                    </div>
                </div>
                <div class="picScroll-top">
                    <a class="next">〉</a>
                    <a class="prev">〈</a>
                    <div class="bd">
                        <ul class="picList">
                            <li :class="index===0?'select':''"
                                v-for="(v,index) in goodsInfo.pdtImg">
                                <div class="pic">
                                    <img :src="image(v)+'?x-oss-process=image/resize,m_pad,h_120,w_120'"
                                         :originImage="image(v)"/>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!--选择颜色规格数量-->
            <div class="xmgR fr">
                <h2>{{goodsInfo.pdtName}}</h2>
                <div class="xmgT2"><s:text name="products.itemCode"/>: {{goodsInfo.itemCode}}</div>

                <div class="xmgT3 info_review clearfloat">
                    <a class="write_review track"
                       :href="'/home/pdt_PdtProduct_writeComment?id='+goodsInfo.pdtId"><s:text
                            name="user.writeReview"/></a>
                    <div class="fr">
                        <b class="fl"><s:text name="products.share"/>:</b>
                        <ul class="fl">
                            <li>
                                <a href="javascript:;" class="share_s_facebook"
                                   @click="shareThis('facebook')">facebook</a>
                            </li>
                            <li>
                                <a href="javascript:;" class="share_s_twitter"
                                   @click="shareThis('twitter')">twitter</a>
                            </li>
                            <li>
                                <a href="javascript:;" class="share_s_googleplus"
                                   @click="shareThis('googleplus')">googleplus</a>
                            </li>
                            <li>
                                <a href="javascript:;" class="share_s_pinterest"
                                   @click="shareThis('pinterest')">pinterest</a>
                            </li>
                            <li class="xmgMoreBtn">
                                <a href="javascript:;" class="xmgMore"><s:text name="more"/></a>
                                <div class="xmgHover">
                                    <a href="javascript:;" id="linkedin"
                                       @click="shareThis('linkedin')">
                                        <i class="share_s_linkedin"></i>
                                        <span>linkedin</span>
                                    </a>
                                    <a href="javascript:;" id="vk" @click="shareThis('vk')">
                                        <i class="share_s_vk"></i>
                                        <span>vk</span>
                                    </a>
                                    <a href="javascript:;" id="google" @click="shareThis('google')">
                                        <i class="share_s_google"></i>
                                        <span>google</span>
                                    </a>
                                    <a href="javascript:;" id="digg" @click="shareThis('digg')">
                                        <i class="share_s_digg"></i>
                                        <span>digg</span>
                                    </a>
                                    <a href="javascript:;" id="reddit" @click="shareThis('reddit')">
                                        <i class="share_s_reddit"></i>
                                        <span>reddit</span>
                                    </a>
                                    <a href="javascript:;" id="stumbleupon"
                                       @click="shareThis('stumbleupon')">
                                        <i class="share_s_stumbleupon"></i>
                                        <span>stumbleupon</span>
                                    </a>
                                    <a href="javascript:;" id="delicious"
                                       @click="shareThis('delicious')">
                                        <i class="share_s_delicious"></i>
                                        <span>delicious</span>
                                    </a>
                                </div>
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="xmgT4 info_price clearfloat">
                    <p><s:text name="Global.Proposed_Price"/>:</p>
                    <div class="xmgLeft">
                        <dl>
                            <dt>
                                <a href="javascript:;">USD
                                    <div class="arrow">
                                        <em></em>
                                        <i></i>
                                    </div>
                                </a>
                            </dt>
                            <dd>
                                <ul>
                                    <c:forEach items="${env.currencys}" var="c">
                                        <li>
                                            <a href="javascript:;" data="${c.id }"
                                               @click="changecCys(${c.id })">${c.shortName }</a>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </dd>
                        </dl>
                        <strong id="cur_price" class="price">{{goodsInfo.currency_symbol}}{{goodsInfo.price}}</strong>
                    </div>
                </div>
                <div class="xmgForm" name="xmgForm" id="goodsForm">
                    <ul>
                        <li class="attr_show" name="Color">
                            <h5><s:text name="Global.Colour"/>:</h5>
                            <span :class="index===0?'seleColor selected':'seleColor'" :title="k"
                                  v-for="(v,k,index) in goodsInfo.spec"
                                  @click="selectSpec=index">
                                    <em class="icon_selected"></em>
                                      <em class="icon_selected_bg"></em>
                                    <a href="javascript:;" :alt="k">
                                    <p v-if="!v[0].img">{{k}}</p>
                                 <img v-if="v[0].img"
                                      :src="image(v[0].img,'?x-oss-process=image/resize,m_pad,h_120,w_120')"
                                 />
                        </a>
								</span>
                        </li>
                    </ul>

                    <div class="xmgP">MOQ: {{goodsInfo.min_oq}}</div>


                    <div class="xmgSeleSize">
                        <div class="xmgSsBOX">
                            <form action="/home/odr_OdrOrder_toSettlementPage" method="post"
                                  id="buynow">
                                <input type="hidden" name="jsonCarts" :value="_jsonCarts"
                                       class="carts">
                                <input type="hidden" name="enterType" value="0">
                                <input type="hidden" name="pid" :value="goodsInfo.pdtId">
                            </form>

                            <h5><s:text name="Global.Size"/>:</h5>
                            <ul>
                                <li v-for="(v,index) in _specList" :specid="v.id">
                                    <span>{{v.size}}</span>
                                    <span>{{v.price}}</span>
                                    <div class="column">
                                        <a href="javascript:void(0);"
                                           :class="(_spceCat(v.id)-1<0)?'disable sub':'sub'"
                                           @click="subSpec(v.id)">-</a>
                                        <input type="text" class="buy" v-model="status.cat[v.id]"
                                               onkeyup='this.value=this.value.replace(/[^0-9]/g,"")'>
                                        <a href="javascript:void(0);" class="add"
                                           @click="addSpec(v.id)">+</a>
                                    </div>
                                </li>
                            </ul>

                        </div>

                        <div class="click_list" v-if="_specList && _specList.length>3"><i></i></div>
                    </div>
                    <!-- 展开尺寸表 和 闭合尺寸表 -->


                    <div class="xmgActions">
                        <input type="button" value="<s:text name="inquiry"/>" class="addRFQ"
                               @click="addRFQ">
                        <input type="button" value="<s:text name='Global.Add_To_Cart'/>"
                               class="addtoCart"
                               @click="addtoCart">
                        <input type="button" value="<s:text name='Global.Buy_Now'/>" class="buyNow"
                               @click="buy">
                        <a href="javascript:;" :class="goodsInfo.favorite?'fav_ed_btn':''"
                           @click="addfav">
                            <span>  &nbsp;</span>
                        </a>
                    </div>

                </div>


            </div>
        </div>

        <!--你可能会喜欢-->
        <%--        <div class="maylikeNew">
                    <h3 class="bTitle"><s:text name="Global.You_May_Like"/></h3>
                    <div class="mayProlist clearfloat">
                        <dl class="fl" v-for="(v,index) in goodsInfo.youMayLike">
                            <dt>
                                <a class="picBox" :href="'/'+v.rewrite" target="_blank">
                                    <img :src="image(v.image)"
                                         :title="v.name"
                                         :alt="v.name">
                                    <span></span>
                                </a>
                            </dt>
                            <dd class="proInfo">
                                <div class="proName">
                                    <a :href="'/'+v.rewrite" target="_blank">{{v.name}}</a>
                                </div>
                                <div class="proPrice">
                                    <em>{{goodsInfo.currency_symbol}}</em>
                                    <span>{{v.price}}</span>
                                </div>
                            </dd>
                        </dl>
                    </div>

                </div>--%>

        <!--产品详情-->
        <div class="prodDescLeft" style="width:100%;">
            <div class="prodDescription">
                <ul class="pdTitle">
                    <li class="current">
                        <span><s:text name="Global.Product_Details"/></span>
                    </li>
                </ul>
                <div class="pdContent editorTxt">
                    <div class="desc">
                        <div class="itemSpecifics clearfloat">
                            <div class="title"><s:text name="Global.Product_Specifications"/></div>
                            <div class="clean">
                                <div class="fl" style="width:50%;"
                                     v-for="(v,k,index) in goodsInfo.specifications">
                                    <span><strong>{{k}}:</strong><label
                                            v-for="vv,index in v">{{vv}}</label></span>
                                </div>
                            </div>

                        </div>
                        <div class="article" v-html="goodsInfo.description"></div>
                    </div>
                </div>
            </div>
        </div>

        <!--产品评分-->
        <div id="review_box">
            <div class="widget prod_write_review">
                <div class="review_title">
                    <span><s:text name="Global.Product_Evaluation"/></span>
                </div>
                <div class="average_rating">
                    <h6><s:text name="Global.Average_Evaluation"/>:</h6>
                    <p>
                        <span :class="'star star_b'+goodsInfo.satisfaction"></span>
                        <strong>{{goodsInfo.satisfaction}}</strong>
                        <a class="review_nums" href="#">
                            <s:text name="goods-info.Comments">
                                <s:param>
                                    {{goodsInfo.commentTotal}}
                                </s:param>
                            </s:text>
                        </a>
                    </p>
                    <a class="review_nums"
                       :href="'/home/pdt_PdtProduct_writeComment?id='+goodsInfo.pdtId"></a>
                    <div>
                        <a class="review_nums" href="#"></a>
                        <a :href="'/home/pdt_PdtProduct_writeComment?id='+goodsInfo.pdtId"
                           class="write_review_btn"
                           rel="nofollow"><s:text name="Global.Write_Evaluation"/></a>
                    </div>
                </div>
                <%--评论详情--%>
                <div class="widget pro`d_recent_review"
                     v-if="isLogin && goodsInfo.comment&& goodsInfo.comment.length>0">
                    <h6><s:text name="products.recentReview"/></h6>
                    <div class="widget review_item" v-for="(d,index) in goodsInfo.comment">
                        <ul class="user fl">
                            <li><span :class="'star star_s'+d.product_satisfaction"></span></li>
                            <li><span class="by_text"><s:text
                                    name="by"/></span><span>{{d.name}}</span></li>
                            <li><i class="icon_level"><img
                                    :src="image(d.usrMemberLevelIco)"></i><strong
                                    class="level_text FontColor">{{d.usrMemberLevelName}}</strong>
                            </li>
                            <li><span class="time">{{d.commenttime}}</span></li>
                        </ul>
                        <div class="like fr">
                            <div class="vote">
                                <p><s:text name="products.helpful"/></p>
                                <div class="likeWrapper">
                                    <a href="javascript:void(0)" class="like" @click="vote(d.id,1)"><span
                                            class="icon_agree">({{d.useful_number}})</span></a>
                                    <span class="gap">|</span>
                                    <a href="javascript:void(0)" class="unlike"
                                       @click="vote(d.id,0)"><span
                                            class="icon_oppose">({{d.unuseful_number}})</span></a>
                                </div>
                            </div>
                            <a :href="'https://api.addthis.com/oexchange/0.8/forward/facebook/offer?url='+encodeURIComponent(window.location.href)+'&t='+goodsInfo.pdtName"
                               class="fb_share" target="_blank">
                                <em class="icon_facebook_mini"></em><s:text
                                    name="products.shareFB"/>
                            </a>
                        </div>
                        <div class="main">
                            <div class="content">good</div>
                            <div class="pic_list">
                                <div class="clear"></div>
                            </div>
                            <div class="reply">
                                <div class="edit"><a href="javascript:;" class="reply_btn"
                                                     @click="changeShow(d.id)"><s:text
                                        name="reply"/></a></div>
                                <div class="w_review_replys" v-show="_isShowReply(d.id)">
                                    <span class="arrow"></span>
                                    <div class="review_reply">
                                        <p>{{d.reply}}</p>
                                        <p class="writer"><span class="light_gray"><s:text
                                                name="By"/></span> <cite
                                                class="replier">{{goodsInfo.supName}}</cite>
                                        </p>
                                    </div>
                                </div>
                                <div class="write_reply hide">
                                    <div class="textarea_holder"><textarea class="default"
                                                                           name="ReviewComment">Add your reply here...</textarea>
                                    </div>
                                    <p class="error"></p>
                                    <button class="btn textbtn">Reply</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--查看所有评论--%>
                <div class="prod_review_view"
                     v-if="isLogin && goodsInfo.comment&& goodsInfo.comment.length>0">
                    <div class="blank12"></div>
                    <a class="customer_btn clearfix"
                       :href="'/home/pdt_PdtProduct_viewComments?id='+goodsInfo.pdtId">
                        {{lang_obj.mobile.all_reviews}}</a>
                    <div class="blank12"></div>
                </div>
                <div class="prod_review_view" v-if="!isLogin">
                    <div class="review_sign"><s:text name="groupPurchaseGoodsInfo.After_Login"/>
                    </div>
                    <div class="blank12"></div>

                    <div id="turn_page"></div>
                    <div class="blank12"></div>
                </div>
            </div>
        </div>
        <div style="padding: 15px"></div>
    </div>

</div>
<script>

    function carWindow(data, msg) {
        $('#addtocart_button').attr('disabled', false);
        var excheckout_html = '<div id="shipping_cost_choose">';
        excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
        excheckout_html += '<div id="choose_content">';
        excheckout_html += '<div class="cart_view">' +
            '<p>' + msg + '</p>' +
            '</div>';
        excheckout_html += '<p class="footRegion">';
        if (msg.indexOf(lang_obj.global.inqadd) != -1) {
            excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">'
                + lang_obj.global.continues + '</a><a href="/home/usr_UsrConsult_publishView?product_id='
                + data.result.id + '" class="btn btn-success" id="excheckout_button">'
                + lang_obj.global.inquery + '</a>';
        } else {
            excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">'
                + lang_obj.cart.return_shopping
                + '</a><a href="/home/usr_UsrCart_cartshopping" class="btn btn-success" id="excheckout_button">'
                + lang_obj.cart.proceed_checkout + '</a>';
        }

        excheckout_html += '</p>';
        excheckout_html += '</div>';
        excheckout_html += '</div>';

        $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
        $('body').prepend(excheckout_html);
        $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});
        global_obj.div_mask();

    }

    $('html').on('click', '#choose_close, #div_mask, #exback_button', function () {
        if ($('#shipping_cost_choose').length) {
            $('#shipping_cost_choose').remove();
            global_obj.div_mask(1);
            $('#shipping_cost_button').removeAttr('disabled');
        }
    });
    $.fn.extend({
        //添加购物车抛物线插件
        fly: function (t, callback) {
            var e = this,
                t = $.extend(
                    {autoPlay: !0, vertex_Rtop: 20, speed: 1.2, start: {}, end: {}, onEnd: $.noop}, t),
                f = $(e),
                obj = {
                    init: function (t) {
                        obj.setOptions(t);
                        !!t.autoPlay && obj.move(t);
                    },
                    setOptions: function (t) {
                        var c = t,
                            s = c.start,
                            d = c.end;
                        f.css({
                            "border-top-left-radius": "50%",
                            "border-top-right-radius": "50%",
                            "border-bottom-right-radius": "50%",
                            "border-bottom-left-radius": "50%",
                            "width": 50,
                            "height": 50,
                            "background-image": "url(/home/static/images/ico/icon_Shopping-Cart.png)",
                            "background-size": "100%",
                            "background-repeat": "no-repeat",
                            "margin-top": 0,
                            "margin-left": 0,
                            "position": "absolute",
                            "z-index": "1000000"
                        }).appendTo("body"),
                        null != d.width && null != d.height && $.extend(!0, s,
                            {width: f.width(), height: f.height()});

                        var h = Math.min(s.top, d.top) - Math.abs(s.left - d.left) / 3;
                        h < c.vertex_Rtop && (h = Math.min(c.vertex_Rtop, Math.min(s.top, d.top)));

                        var i = Math.sqrt(Math.pow(s.top - d.top, 2) + Math.pow(s.left - d.left, 2)),
                            j = Math.ceil(Math.min(Math.max(Math.log(i) / .05 - 75, 30), 100) / c.speed),
                            k = s.top == h ? 0 : -Math.sqrt((d.top - h) / (s.top - h)),
                            l = (k * s.left - d.left) / (k - 1),
                            m = d.left == l ? 0 : (d.top - h) / Math.pow(d.left - l, 2);

                        $.extend(!0, c, {count: -1, steps: j, vertex_left: l, vertex_top: h, curvature: m});
                    },
                    move: function (t) {
                        var s = t.start,
                            len = t.count,
                            step = t.steps,
                            d = t.end,
                            h = s.left + (d.left - s.left) * len / step,
                            i = 0 == t.curvature ? s.top + (d.top - s.top) * len / step : t.curvature
                                * Math.pow(h - t.vertex_left, 2) + t.vertex_top;
                        if (null != d.width && null != d.height) {
                            var j = step / 2,
                                k = d.width - (d.width - s.width) * Math.cos(
                                    j > len ? 0 : (len - j) / (step - j) * Math.PI / 2),
                                l = d.height - (d.height - s.height) * Math.cos(
                                    j > len ? 0 : (len - j) / (step - j) * Math.PI / 2);
                            f.css({width: k + "px", height: l + "px", "font-size": Math.min(k, l) + "px"});
                        }

                        if (len == -1) {
                            f.css({left: h + "px", top: i + "px"});
                            t.count++;
                            obj.move(t);
                        } else {
                            f.animate({left: h + "px", top: i + "px"}, 5, function () {
                                t.count++;
                                //$('.search_r').html($.toJSON(t));
                                if (len < step) {
                                    obj.move(t);
                                } else {
                                    t = '';
                                    obj.destory();
                                }
                            });
                        }
                    },
                    destory: function () {
                        f.remove();
                        callback();
                    }
                }
            obj.init(t);
        }
    });

    function cartFly(data, msg) {
        var _self = $("input.addtoCart")
        var offset = $('.cart_inner').offset(),
            btnLeft = $(_self).offset().left + $(_self).outerWidth(true) / 3,
            btnTop = $(_self).offset().top - $(_self).outerHeight(true) - 30,
            flyer = $('<div class="addtocart_flyer"></div>');
        flyer.fly({
            start: {left: btnLeft, top: btnTop, width: 50, height: 50},
            end: {left: offset.left + 30, top: offset.top, width: 20, height: 20}
        }, function () {
            $('#addtocart_button').attr('disabled', false);
            // $('.top_cart .cart_count').text('购物车的总数量');//购物车的总数量

            var excheckout_html = '<div id="shipping_cost_choose">';
            excheckout_html += '<div class="box_bg"></div><a class="noCtrTrack" id="choose_close">×</a>';
            excheckout_html += '<div id="choose_content">';
            excheckout_html += '<div class="cart_view">' +
                '<p>' + msg + '</p>' +
                // ((lang_obj.cart.additem_1).replace('%num%', '购物车的总数量')) + '<b class="FontColor">' + ueeshop_config.currency_symbols + '加入购物车的商品的价格' + '</b>' +
                '</div>';
            excheckout_html += '<p class="footRegion">';
            if (msg.indexOf(lang_obj.global.inqadd) != -1) {
                excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">'
                    + lang_obj.global.continues
                    + '</a><a href="/home/usr_UsrConsult_publishView?product_id=' + data.result.id
                    + '" class="btn btn-success" id="excheckout_button">' + lang_obj.global.inquery
                    + '</a>';
            } else {
                excheckout_html += '<a href="javascript:;" class="btn btn-success" id="exback_button">'
                    + lang_obj.cart.return_shopping
                    + '</a><a href="/home/usr_UsrCart_cartshopping" class="btn btn-success" id="excheckout_button">'
                    + lang_obj.cart.proceed_checkout + '</a>';
            }

            excheckout_html += '</p>';
            excheckout_html += '</div>';
            excheckout_html += '</div>';

            $('#shipping_cost_choose').length && $('#shipping_cost_choose').remove();
            $('body').prepend(excheckout_html);
            $('#shipping_cost_choose').css({left: $(window).width() / 2 - 220});
            global_obj.div_mask();
        });
    }
</script>
<script>

    var app = new Vue({
        el: "#app",
        data: {
            goodsInfo:${goodsInfo},
            selectSpec: 0,
            isLogin:${env.login!=null},
            status: {
                cat: {}
            },
            isShowReply: {},
            temp: {
                catlength: 0
            }
        }, mounted() {
            for (var key in this.goodsInfo.spec) {
                for (var value in this.goodsInfo.spec[key]) {
                    this.$set(this.status.cat, this.goodsInfo.spec[key][value].id, 0)
                }
            }
            for (var key in this.goodsInfo.comment) {
                this.$set(this.isShowReply, this.goodsInfo.comment[key].id, false)
            }
        }, methods: {
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return stpshop_config.imageBaseUrl + v + params
            },
            addfav() {
                if (!isLogin) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                }
                var self = this
                axios({
                    url: "/home/usr_UsrFavorites_addFavorite",
                    data: Qs.stringify({
                        pdtPkey: self.goodsInfo.pdtId
                    }), method: "post"
                }).then(function (data) {
                    if (data.data) {
                        if (data.data.ret && data.data.ret == 1) {
                            self.goodsInfo.favorite = data.data.type !== 1
                            self.goodsInfo.favorite_count = data.data.number
                        }
                    }
                })
            },
            addRFQ() {
                if (!isLogin) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                }
                axios({
                    url: "/home/pdt_PdtConsultPdtList_add",
                    method: "post",
                    data: Qs.stringify({
                        product: this.goodsInfo.pdtId
                    })
                }).then(function (data) {
                    if (data.data) {
                        if (data.data.ret && data.data.ret === 1) {
                            carWindow(data.data, lang_obj.global.inqadd)
                        }
                    }
                })
            },
            buy() {
                if (!isLogin) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                }
                if (this.temp.catlength > 0) {
                    axios({
                        url: "/home/usr_UsrCart_judgeBuyNow",
                        method: "post",
                        data: Qs.stringify({
                            jsonCarts: this._jsonCarts,
                            enterType: 0,
                            pid: this.goodsInfo.pdtId
                        })
                    }).then(function (data) {
                        switch (data.data.ret) {
                            case 1: {
                                document.getElementById("buynow").submit()
                            }
                                break;
                            case 0: {
                                layer.msg(getMessage(data.data.msg), {icon: 2, time: 2000});
                            }
                        }
                    })
                } else {
                    layer.msg(lang_obj.user.address_tips.Please_add_item, {icon: 2});
                }
            },
            changeShow(id) {
                // console.log(this.isShowReply)
                this.isShowReply[id] = !this.isShowReply[id]
                this._isShowReply(id)
                // this.$set(this.isShowReply, id, !this.isShowReply[id])
            },
            addtoCart() {
                if (!isLogin) {
                    user_obj.set_form_sign_in('', window.location.href, 1);
                    return
                }
                var temp = {}
                var i = 0
                for (var key in this.status.cat) {
                    if (this.status.cat[key] && this.status.cat[key] !== 0) {
                        temp["specList[" + i + "].spec"] = key
                        temp["specList[" + i + "].qty"] = this.status.cat[key]
                        i++
                    }
                }
                if (i > 0) {
                    axios({
                        url: "/home/usr_UsrCart_boughtPro",
                        method: "Post",
                        data: Qs.stringify(temp)
                    }).then(function (data) {
                        if (data.data.success) {
                            //TODO
                            $("div.fr.top_cart.top_cart0 i").text(
                                data.data.newQty + parseInt($("div.fr.top_cart.top_cart0 i").text()))
                            cartFly(data.data, lang_obj.cart.additem_0)
                        }
                    })
                } else {
                    layer.msg(lang_obj.user.address_tips.Please_add_item, {icon: 2});
                }
            },
            _spceCat(id) {
                if (this.status.cat[id])
                    return this.status.cat[id];
                return 0;
            },
            addSpec(id) {
                var i = this.status.cat[id];
                if (i) {
                    i = i * 1
                } else {
                    i = 0;
                }
                i++
                this.$set(this.status.cat, id, i)
            },
            subSpec(id) {
                var i = this.status.cat[id];
                if (i) {
                    i = i * 1
                } else {
                    i = 0;
                }
                if (i - 1 < 0) {
                    return
                }
                i--
                this.$set(this.status.cat, id, i)
            }, _isShowReply(id) {
                return this.isShowReply[id]
            }, changecCys(v) {
                axios({
                    url: "/home/plt_PltConfig_changeCurrency",
                    method: "post",
                    data: Qs.stringify({
                        currency: v
                    })
                }).then(function () {
                    window.top.location.reload();
                })
            }, vote(id, type) {
                if (sessionStorage)
                    if (sessionStorage['vote.' + id + '_' + type] == 1) return
                var self = this
                axios({
                    url: "/home/pdt_PdtProduct_vote",
                    method: "post",
                    data: Qs.stringify({
                        type: type,
                        id: id
                    })
                }).then(function (data) {
                    if (data.data.ret && data.data.ret === 1) {
                        for (var key in self.goodsInfo.comment) {
                            if (self.goodsInfo.comment[key].id === id) {
                                if (type == 1) {
                                    self.goodsInfo.comment[key].useful_number++
                                } else {
                                    self.goodsInfo.comment[key].unuseful_number++
                                }
                            }
                        }
                    }
                    if (sessionStorage)
                        sessionStorage['vote.' + id + '_' + type] = 1
                })
            }, shareThis(type) {
                var image = back_url = encode_url = "";
                url = window.location.href;
                if (url.indexOf("#") > 0) {
                    url = url.substring(0, url.indexOf("#"));
                }
                image = this.goodsInfo.pdtImg[0]
                var e_url = encodeURIComponent(url);
                var title = encodeURIComponent(this.goodsInfo.pdtName);
                switch (type) {
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
                        back_url = "http://pinterest.com/pin/create/button/?url=" + e_url + "&media=" + image
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
                window.open(back_url, "bookmarkWindow");
            }
        }, computed: {
            _logo() {
                if (!this.goodsInfo.logo) return ""
                return this.image(this.goodsInfo.logo, "?x-oss-process=image/resize,m_pad,h_130,w_165");
            },
            _jsonCarts() {
                var temp = {}
                var i = 0
                for (var key in this.status.cat) {
                    if (this.status.cat[key] && this.status.cat[key] !== 0) {
                        temp[key] = this.status.cat[key]
                        i++
                    }
                }
                this.temp.catlength = i;
                return JSON.stringify(temp)
            },

            _specList() {
                var i = 0
                var temp;
                var s = this.selectSpec > 0 ? this.selectSpec : 0;
                for (var key in this.goodsInfo.spec) {
                    if (i === s) {
                        temp = key
                        break
                    }
                    i++
                }
                return this.goodsInfo.spec[temp];
            },
            _pdtPic() {
                if (this.selectSpec > 0) {
                    if (this._specList && this._specList.length > 0)
                        if (this._specList[0].img && this._specList[0].img.length > 0) {
                            return this.image(this._specList[0].img)
                        }
                }
                if (this.goodsInfo.pdtImg && this.goodsInfo.pdtImg.length > 0)
                    if (this.goodsInfo.pdtImg[0].length > 0)
                        return this.image(this.goodsInfo.pdtImg[0])
                console.log("图片没有图片")
                return ""
            }
        }
    })
    //		选择规格下拉
    $("#goodsInfo .xmgSeleBOX .xmgR .xmgSeleSize .click_list").click(function () {
        var that = $("#goodsInfo .xmgSeleBOX .xmgR .xmgSeleSize .xmgSsBOX");
        if (that.hasClass("heightAuto")) {
            that.removeClass("heightAuto");
        } else {
            that.addClass("heightAuto");
        }
    })
    //		选择颜色
    $(".seleColor").click(function () {
        $(".seleColor").removeClass("selected ");
        $(this).addClass("selected ")
    })
    //		选择要浏览的图
    $(".picScroll-top .bd ul li").hover(function () {
        $(".picScroll-top .bd ul li").removeClass("select");
        $(this).addClass("select")
//			alert($(this).find("img").attr("src"))
        var thissrc = $(this).find("img").attr("originImage");
        $("#small-box").find("img").removeAttr('src').attr("src",
            thissrc + "?x-oss-process=image/resize,m_pad,h_500,w_500");
        $("#big-box").find("img").removeAttr('src').attr("src", thissrc);
    })

    //		轮播
    jQuery(".picScroll-top").slide({mainCell: ".bd ul", autoPage: true, effect: "left", vis: 5});

    //		放大镜
    window.onload = function () {
        var objBox = document.getElementById("picBoxs");
        var objSmallBox = document.getElementById("small-box");
        var objMark = document.getElementById("mark");
        var objFloatBox = document.getElementById("float-box");
        var objBigBox = document.getElementById("big-box");
        var objBigBoxImage = objBigBox.getElementsByTagName("img")[0];

        objMark.onmouseover = function () {
            objFloatBox.style.display = "block"
            objBigBox.style.display = "block"
        }

        objMark.onmouseout = function () {
            objFloatBox.style.display = "none"
            objBigBox.style.display = "none"
        }

        objMark.onmousemove = function (ev) {
            var _event = ev || window.event;  //兼容多个浏览器的event参数模式
            var left = _event.clientX - objBox.offsetLeft - objSmallBox.offsetLeft
                - objFloatBox.offsetWidth / 2 + document.body.scrollLeft;
            var top = _event.clientY - objBox.offsetTop - objSmallBox.offsetTop - objFloatBox.offsetHeight
                / 2 + document.body.scrollTop;
            //设置边界处理，防止移出小图片
            if (left < 0) {
                left = 0;
            } else if (left > (objMark.offsetWidth - objFloatBox.offsetWidth)) {
                left = objMark.offsetWidth - objFloatBox.offsetWidth;
            }
            if (top < 0) {
                top = 0;
            } else if (top > (objMark.offsetHeight - objFloatBox.offsetHeight)) {
                top = objMark.offsetHeight - objFloatBox.offsetHeight;

            }

            objFloatBox.style.left = left + "px";   //oSmall.offsetLeft的值是相对什么而言
            objFloatBox.style.top = top + "px";

            //求其比值
            var percentX = left / (objMark.offsetWidth - objFloatBox.offsetWidth);
            var percentY = top / (objMark.offsetHeight - objFloatBox.offsetHeight);

            //方向相反，小图片鼠标移动方向与大图片相反，故而是负值
            objBigBoxImage.style.left = -percentX * (objBigBoxImage.offsetWidth - objBigBox.offsetWidth)
                + "px";
            objBigBoxImage.style.top = -percentY * (objBigBoxImage.offsetHeight - objBigBox.offsetHeight)
                + "px";
        }
    }


</script>
<c:if test="${not empty supView.traceCode && fn:length(supView.traceCode)>0}">
    ${supView.traceCode}
</c:if>
<jsp:include page="template/new-foot.jsp"></jsp:include>


