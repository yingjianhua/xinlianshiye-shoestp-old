<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->

    <script type="text/javascript" async="" src="/home/static/js/js.js">
    </script>
    <script type="text/javascript" async="" src="/home/static/js/conversion_async.js">
    </script>
    <script type="text/javascript" async="" src="/home/static/js/analytics.js">
    </script>
    <!--     <script type="text/javascript" async="" src="/home/static/js/tracking.js"> -->
    <!--     </script> -->
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market--Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/user.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/home/static/js/jquery-1.7.2.min.js">
    </script>
    <script type="text/javascript" src="/home/static/js/lang/en.js">
    </script>
    <script type="text/javascript" src="/home/static/js/main.js">
    </script>
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
    <link rel="stylesheet" href="/home/static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="/home/static/css/swiper.min.css" type="text/css">
    <script src="/home/static/js/swiper.min.js" type="text/javascript">
    </script>
    <script src="/home/static/js/lazyload.min.js" type="text/javascript">
    </script>

    <script type="text/javascript" src="/home/static/themes/default/mobile/js/qs.js"></script>
    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

    <style type="text/css">
        table img {
            object-fit: contain;
        }

        /* 分页居中 */
        .el-pagination {
            text-align: center;
        }

        .el-pagination.is-background .el-pager li:not(.disabled).active {
            background-color: #333;
        }

        .myinquirylist-bottomnav {
            height: 60px;
            border-top: 1px solid #eee;
        }

        .myinquirylist-bottomnav a {
            float: right;
            width: 114px;
            height: 40px;
            margin: 10px;
            line-height: 40px;
            text-align: center;
            color: #fff;
            background: #ddd;
            border-radius: 6px;
            font-size: 16px;
        }

        .myinquirylist-bottomnav2 a {
            background: #043d81 !important;
        }

        #lib_user_inquiry {
            min-height: 400px;
        }

        #lib_user_inquiry #turn_page {
            margin: 20px 0 50px 0;
        }

        /* 新消息的标签位置 */
        #lib_user_inquiry table .inquiry-message .new-inquiry-message {
            top: -12px;
            left: 16px;
        }

        /* 询盘标题 - 文字截取显示 */
        .inquiry-title a {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 4;
            -webkit-box-orient: vertical;
        }

        /* 询盘商 - select不能无限长度 */
        .supplier-select {
            min-width: 150px;
            max-width: 200px;
        }
    </style>
    <link href="/home/static/css/row_4.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<%@ include file="/home/template/web-top.jsp" %>
<div id="main">
    <index-top></index-top>
    <div class="wide">
        <div id="lib_user" class="clearfix">
            <div id="lib_user_crumb" class="widget">
                <ul class="crumb_box clearfix">
                    <li class="home">
                        <a href="/" title="Home"><s:text name="Global.Home"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb1">
                        <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text name="Global.My_Account"/>
                            <i></i>
                        </a>
                    </li>
                    <li class="crumb2 root">
                        <a href="/home/usr_UsrConsult_listView" title="My Inquiry/RFQ"><s:text
                                name="my-inquiry-publish.View_Inquiry"/>
                            <i></i>
                        </a>
                    </li>
                </ul>
            </div>

            <%@ include file="template/account/lib-user-menu.jsp" %>


            <div id="myinquirylist">

                <div id="lib_user_main">
                    <div class="lib_user_inquiry_title flex-spbe">
                        <span><s:text name="my-inquiry-list.Query_List"/></span>
                        <div class="release_inquiry_btn flex-spbe fr">
                            <a href="/home/usr_UsrConsult_publishView">
                                <i class="release_inquiry"></i>
                                <s:text name="my-inquiry-list.Publish_Query"/>
                            </a>
                        </div>
                    </div>
                    <div id="lib_user_inquiry">
                        <div class="blank20"></div>
                        <!-- <div id="turn_page"></div> -->
                        <table>
                            <thead>
                            <tr>
                                <th>
                                    <input id="quan" type="checkbox" @click="checkedAll"
                                           v-if="inquirylist.items && inquirylist.items.length">
                                </th>
                                <th class="table-title"><s:text name="my-inquiry-list.Title"/></th>
                                <th class=""><s:text name="my-inquiry-list.Picture"/></th>
                                <th><s:text name="my-inquiry-list.Query_Time"/></th>
                                <th><s:text name="Global.Supplier"/></th>
                                <th><s:text name="cart3.Product_Quantity"/></th>
                                <th><s:text name="my-inquiry-list.Communication"/></th>
                                <th><s:text name="Global.Operating"/></th>
                            </tr>
                            </thead>


                            <tbody>
                            <template v-if="inquirylist.items && inquirylist.items.length">
                                <tr v-for='inquirylistdd in inquirylist.items'>
                                    <td><input type="checkbox" class="yigegexuan" :value="inquirylistdd.id"
                                               v-model="checkedNames"></td>
                                    <td class="inquiry-title"><a
                                            :href="'/home/usr_UsrConsult_detailView?inquiry_id='+inquirylistdd.id">
                                        {{inquirylistdd.title}}
                                    </a></td>
                                    <td>
                                        <a :href="'/home/usr_UsrConsult_detailView?inquiry_id='+inquirylistdd.id">
                                            <img :src="'${envConfig.imageBaseUrl}'+inquirylistdd.image.split(',')[0]"
                                                 alt="">
                                        </a>
                                    </td>
                                    <td>{{inquirylistdd.createTime}}</td>
                                    <td>
                                        <!--                                       <select name="" class="supplier-select" v-if="inquirylistdd.relations && inquirylistdd.relations.length"> -->
                                        <!-- 		                                    <option value="111"  v-for='inquirylistcc in inquirylistdd.relations'>{{inquirylistcc.supplierName}}</option> -->
                                        <!-- 		                                  </select> -->
                                        <p v-if="inquirylistdd.relations && inquirylistdd.relations.length">
                                            {{inquirylistdd.relations[0].supplierName}}
                                        </p>
                                        <p v-else>no supplier</p>
                                    </td>
                                    <td>{{inquirylistdd.quantity}}</td>
                                    <td>
                                        <a v-if="inquirylistdd.relations && inquirylistdd.relations.length"
                                           :href="'/home/usr_UsrConsultMessage_dialogView?inquiry_id='+inquirylistdd.id">
                                            <div class="inquiry-message">
                                                <i class="new-inquiry-message" v-show="inquirylistdd.haveNewMsg"></i>
                                            </div>
                                        </a>
                                        <p v-else>no consult</p>
                                    </td>
                                    <td class="flex-spbe">
                                        <a :href="'/home/usr_UsrConsult_detailView?inquiry_id='+inquirylistdd.id">
                                            <div class="inquiry-view"></div>
                                        </a>
                                        <a href="#">
                                            <div class="inquiry-delete" :data-ddid="inquirylistdd.id"
                                                 @click="clickdele"></div>
                                        </a>
                                    </td>
                                </tr>
                            </template>

                            </tbody>

                        </table>

                        <template v-if="!inquirylist.items || !inquirylist.items.length">
                            <p style="padding:167px 0;text-align: center;font-size:30px;font-weight: bold;color: #999;">
                                <s:text name="mobile.no_data"/></p>
                        </template>


                        <div class="myinquirylist-bottomnav"
                             :class="[checkedNames.length>0 ? 'myinquirylist-bottomnav2':'']">
                            <a href="javascript:;" @click="clickdeleall"><s:text name="my-favorite.Empty"/></a>
                        </div>

                        <!-- åé¡µ -->
                        <%-- <div id="turn_page" v-if="inquirylist.items && inquirylist.items.length">
                            <ul>
                                <li  v-if="inquirylist.currentPage !=1" :data-yeshu="inquirylist.currentPage-1" @click="dianjifenye">
                                    <a class="page_button">
                                        <em class="icon_page_prev"></em> Previous</a>
                                </li>
                                <li  v-if="inquirylist.currentPage ==1">
                                    <a class="page_button">
                                        <em class="icon_page_prev"></em> Previous</a>
                                </li>
                                <li v-if="inquirylist.currentPage>=6" :data-yeshu="1" @click="dianjifenye">
                                    <a class="page_item" >1</a>
                                </li>
                                <li v-if="inquirylist.currentPage>=6">
                                    <font class="page_item">...</font>
                                </li>
                                <li  v-if="inquirylist.currentPage-3 >0" :data-yeshu="inquirylist.currentPage-3" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage-3}}</a>
                                </li>
                                <li  v-if="inquirylist.currentPage-2 >0" :data-yeshu="inquirylist.currentPage-2" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage-2}}</a>
                                </li>
                                <li  v-if="inquirylist.currentPage-1 >0" :data-yeshu="inquirylist.currentPage-1" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage-1}}</a>
                                </li>
                                <li>
                                    <font class="page_item_current">{{inquirylist.currentPage}}</font>
                                </li>
                                <li :data-yeshu="inquirylist.currentPage+1" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage+1}}</a>
                                </li>
                                <li :data-yeshu="inquirylist.currentPage+2" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage+2}}</a>
                                </li>
                                <li v-if="inquirylist.currentPage < 4" :data-yeshu="inquirylist.currentPage+3" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage+3}}</a>
                                </li>
                                <li v-if="inquirylist.currentPage < 4" :data-yeshu="inquirylist.currentPage+4" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage+4}}</a>
                                </li>
                                <li v-if="inquirylist.currentPage < 4" :data-yeshu="inquirylist.currentPage+5" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.currentPage+5}}</a>
                                </li>
                                <li>
                                    <font class="page_item">...</font>
                                </li>
                                <li :data-yeshu="inquirylist.totalPage" @click="dianjifenye">
                                    <a class="page_item">{{inquirylist.totalPage}}</a>
                                </li>
                                <li class="page_last" :data-yeshu="inquirylist.currentPage+1" @click="dianjifenye" v-if="inquirylist.currentPage!=inquirylist.totalPage">
                                    <a class="page_button">Next
                                        <em class="icon_page_next"></em>
                                    </a>
                                </li>
                                <li class="page_last" v-if="inquirylist.currentPage==inquirylist.totalPage">
                                    <a class="page_button">Next
                                        <em class="icon_page_next"></em>
                                    </a>
                                </li>
                            </ul>
                        </div> --%>

                        <el-pagination
                                background
                                layout="prev, pager, next"
                                @current-change="jumpPage"
                                :page-count="pageTotalCount">
                        </el-pagination>


                    </div>

                </div>
            </div>
        </div>
    </div>


</div>
<%@ include file="/home/template/new-foot.jsp" %>
<div id="hj_top" style="opacity: 0;">
    <img src="/home/static/images/hj_top.png">
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script type="text/javascript">
    var myinquirylist = new Vue({
        el: '#main',
        data: {
            myinquirylistbottomnav: 0,
            checkedNames: [],
            // start: 0,
            inquirylist: [],
            limit: 8,  //分页 - 每页个数
            pageTotalCount: 1, //总页数
            currentPage: 1 //当前页数

        },
        watch: { // 监视双向绑定的数据数组
            checkedNames: {
                handler() { // 数据数组有变化将触发此函数
                    if (this.checkedNames.length == this.inquirylist.items.length) {
                        // document.querySelector('#quan').checked = true;
                        $("#quan").prop("checked", true);
                    } else {
                        // document.querySelector('#quan').checked = false;
                        $("#quan").prop("checked", false);
                    }
                },
                deep: true // 深度监视
            }
        },
        mounted() {
            this.inquirylistload();
        },
        methods: {
            jumpPage(e) {
                this.currentPage = e;
                this.inquirylistload();
            },
            inquirylistload: function () {
                axios.post('/home/usr_UsrConsult_pagePrivate', Qs.stringify({
                    start: (this.currentPage - 1) * this.limit,
                    limit: this.limit
                }, {allowDots: true}))
                    .then((res) => {
                        console.log("res")
                        console.log(res)
                        this.checkedNames = [];
                        this.inquirylist = res.data.result;
                        this.pageTotalCount = res.data.result.totalPage
                        if (res.data.ret == -1) {
                            window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_detailView?inquiry_id=' + this.inquiry_id;
                        }
                    })
                    .catch((res) => {

                    });
            },
            // dianjifenye:function(e){
            // 	this.start= ($(e.currentTarget).data("yeshu") -1)*this.limit
            // this.inquirylistload();
            // },

            checkedAll: function (e) {
                var checkObj = document.querySelectorAll('.yigegexuan'); // 获取所有checkbox项
                if (e.target.checked) { // 判定全选checkbox的勾选状态
                    for (var i = 0; i < checkObj.length; i++) {
                        if (!checkObj[i].checked) { // 将未勾选的checkbox选项push到绑定数组中
                            this.checkedNames.push(checkObj[i].value * 1);
                        }
                    }
                } else {
                    this.checkedNames = []
                }

            },
            clickdele: function (e) {
                var currentTargetId = $(e.currentTarget).data("ddid");
                layer.confirm('<s:text name="my-inquiry-list.Delete_Inquiry"/>', {
                    icon: 7,
                    title: '<s:text name="Global.Tips"/>'
                }, (index) => {
                    //do something
                    axios.post('/home/usr_UsrConsult_delete', Qs.stringify({
                        id: currentTargetId * 1
                    }, {allowDots: true}))
                        .then((res) => {
                            if (res.data.ret == 1) {
                                layer.msg('<s:text name="Global.Successfully_Deleted"/>', {icon: 1, time: 1000});
                                setTimeout(() => {
                                    this.inquirylistload();
                                }, 1000)
                            }
                        })
                        .catch((res) => {
                        });

                    layer.close(index);
                });
            },

            clickdeleall: function (e) {
                if (this.checkedNames.length == 0) return;
                layer.confirm('<s:text name="my-inquiry-list.Delete_All_Queries"/>', {
                    icon: 7,
                    title: '<s:text name="Global.Tips"/>'
                }, (index) => {
                    axios.post('/home/usr_UsrConsult_deletes', Qs.stringify({
                        ids: this.checkedNames.join(",")
                    }, {allowDots: true}))
                        .then((res) => {
                            if (res.data.ret == 1) {
                                layer.msg('<s:text name="Global.Successfully_Deleted"/>', {icon: 1, time: 1000});
                                setTimeout(() => {
                                    this.inquirylistload();
                                }, 1000)
                            }
                        })
                        .catch((res) => {
                        });

                    layer.close(index);
                });
            },
        }
    })

</script>

</body>

</html>
