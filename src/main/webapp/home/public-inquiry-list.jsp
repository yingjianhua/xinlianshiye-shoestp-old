<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta content="telephone=no" name="format-detection">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="renderer" content="webkit">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>
            An Online B2B market--Shoestp.com,gathering 300 professional shoes manufacture companies
        </title>
        <link href="./static/css/global.css" rel="stylesheet" type="text/css">
        <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
        <link href="./static/css/style.css" rel="stylesheet" type="text/css">
        <link href="./static/css/user.css" rel="stylesheet" type="text/css">
        <link href="./static/css/color.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js">
        </script>
        <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script type="text/javascript" src="/home/static/js/qs.js"></script>
        <!-- 引入样式 -->
        <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
        <!-- 引入组件库 -->
        <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    </head>
    <body class="w_1200">
        <style>
            .el-pagination.is-background .el-pager li:not(.active):hover{
                color: #333;
            }
            .el-pagination.is-background .el-pager li:not(.disabled).active{
                background-color: #333;
            }
        </style>
        <%@ include file="/home/template/web-top.jsp" %>
        <%@ include file="/home/template/new-header.jsp" %>
        <div class="wide" id="inquire_list">
            <ul id="listAsk" class="clearfloat">
                <template v-for="(item,index) in inquiryList.items">
                    <li class='fl clearfloat'>
                        <div class='clearfloat'>
                            <div class='img fl'>
                                <img v-if="item.image" :src="'${envConfig.imageBaseUrl}'+item.image.split(',')[0]">
                                <img v-else src="/home/static/images/none.jpg">
                            </div>
                            <div class='info fr'>
                                <div class='clearfloat'>
                                    <div class='fl'>
                                        <i class='icon-name'></i>&nbsp;&nbsp;
                                        <span><s:text name="user.name"/>:&nbsp;&nbsp;
                                            <span>{{item.name}}</span>
                                        </span>
                                    </div>
                                    <div class='fr'>
                                        <span>{{item.createTime}}</span>
                                    </div>
                                </div>
                                <div class='email'>
                                    <i class='icon-email'></i>&nbsp;&nbsp;
                                    <span><s:text name="consult.email"/>:&nbsp;&nbsp;
                                        <span>*******</span>
                                    </span>
                                </div>
                                <div class='detail'>{{item.content}}</div>
                                <div>
                                    <div class='btn_detail'>
                                        <a href="/seller/admin/index/index.html" target="_blank">
                                            <span>View Detail</span>&nbsp;&nbsp;
                                            <i class='icon-detail'></i>
                                       </a>
                                    </div>
                                    <div class='nation'>
                                        <span><%-- <s:text name="ip_from"/>  --%>{{item.countryName}}：</span>
                                        
                                        <i class='icon-nation'>
                                         <img v-if="item.countryFlag" :src="'${envConfig.imageBaseUrl}'+item.countryFlag" />
                                            <!-- <img v-else src="/home/static/images/nation.png"> -->
                                        </i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </template>
            </ul>
            <el-pagination
            	v-show="totalCount > 0"
                background
                layout="prev, pager, next"
                :total="totalCount"
                @current-change="handleCurrentChange"
                style="text-align: center">
            </el-pagination>
            <div v-show="totalCount == 0" style="text-align: center;height: 600px;line-height: 600px;">
            	<s:text name="No_EnquiryInformation"/>
            </div>
        </div>
        <%@ include file="/home/template/new-foot.jsp" %>
    </body>
    <script>
        var app = new Vue({
                el: '#inquire_list',
                data: {
                        // 询盘列表
                        inquiryList: [],
                        // 每页显示几个
                        limit: 8,
                        // 当前页数
                        currentPage: 1,
                        // 分页起始
                        start: 0,
                        // 商品总数
                        totalCount:0
                },
                methods: {
                    getInquiryList(start,limit,currentPage) {
                        axios.post('/home/usr_UsrConsult_pagePublic',Qs.stringify({ //请求参数
                                    start: start,
                                    limit: limit
                                })
                            )
                            .then((res) => {
                                this.inquiryList = res.data.result;
                                this.totalCount = res.data.result.totalCount;
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    },
                    handleCurrentChange(val) {
                        var start = (val-1) * this.limit;
                        this.getInquiryList(start,this.limit);
                    },
                },
                created() {
                    this.getInquiryList(this.start,this.limit);
                },
                computed: {

                }
            })
    </script>

    </html>
