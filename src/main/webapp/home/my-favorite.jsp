<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v3/static/css/user/ureset.css"/>
<link rel="stylesheet" href="/home/v3/static/css/user/uindex.css"/>

<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<script src="./static/js/qs.js" type="text/javascript">
</script>
</head>

<body>
<jsp:include page="v3/nav-nobody.jsp"></jsp:include>
<script src="/home/v3/static/js/index-top.js"></script>
<div id="personalCenter" class="clearfix" v-cloak>
    <index-top></index-top>
      <div class="user-menu fl">
            <div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account
        </div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a style="color:#10389c;" href="/home/usr_UsrFavorites_myfavorite">My Favourites <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
    </div>
    <div class="favorite-main fr">
        <div class="title flexSt">
            <h1>My Favourite</h1>
            <ul class="flexSt">
                <li :class="catPkeyIndex == index?'activeLi':''" v-for="(item, index) in catPkeyList" :key="index"
                    @click="catClick(item.catPkey,index)">{{item.name}}
                </li>
            </ul>
        </div>
                <div class="favorite-list clearfix">
                    <template v-if="favoriteList.length > 0">
                    <div class="favorite-item" v-for="(item, index) in favoriteList" :key="index">
                        <input type="checkbox" @change="singleChecked" :value="item.id" v-model="checkedCode">
                        <div class="porduct-img">
                            <a :href="'/home/pdt_PdtProduct_gtProductsInfo?id=' + item.pdtPkey" target="_blank">
                                <img :src="image(item.img,185)" alt="">
                            </a>
                        </div>
                        <div class="porduct-name">
                            <a :href="'/home/pdt_PdtProduct_gtProductsInfo?id=' + item.pdtPkey" target="_blank" class="text">
                                <div class="icon-o2o" v-if="item.groupLine == 4"><img src="/home/v3/static/images/ico/icon_o2o.png" alt="O2O">O2O</div>{{item.name}}
                            </a>
                        </div>
                        <div class="porduct-price">
                            <span style="color: #e54544;">{{sysConfig.currency_symbol}} {{item.amt}}</span>
                        </div>
                        <div class="porduct-minOrder">
                            Min.Order: {{item.min_order}} pairs
                        </div>
                        <div class="porduct-btn-list flexSb">
                            <div class="porduct-remove-btn porduct-btn">
                                <a href="javascript:void(0)" @click="remove(item.id)">Remove</a>
                            </div>
                            <div class="porduct-inquiry-btn porduct-btn">
                                <a href="javascript:void(0)" @click="restore(item.id)" v-if="catPkey == -1">Restore</a>
                                <!-- <a :href="'/home/usr_UsrFavorites_myfavorite?product_id='+item.pdtPkey" target="_blank" v-else>Inquiry</a> -->
                                <a :href="'/home/usr_UsrConsult_productPublishView?product_id='+item.pdtPkey+'&backUrl='+window.location.href" target="_blank" v-else>Inquiry</a>
                            </div>
                        </div>
                    </div>
                    </template>
                      <template v-else>
                        <div style="height:566px;text-align:center;line-height:566px;font-size:32px;font-weight:bold;">
                            No valid information
                        </div>
                    </template>
                </div>


            <template v-if="favoriteList.length > 0">
            <div class="page-box">
                <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        prev-text="< Previous"
                        next-text="Next >"
                        :current-page="currentPage"
                        layout="prev, pager, next"
                        page-size="8"
                        :total="totalCount">
                </el-pagination>
            </div>
            <!-- 正常  和  回收站的按钮 -->
            <div class="bottom-btn-list flexSe">
                <input type="checkbox" v-model='isAllChecked' @change='chooseAll'>
                <div class="bottom-btn bottom-remove-btn" @click="removeAll">
                    <a href="javascript:void(0)">{{catPkey == -1?'Empty recycle bin':'Clear'}}</a>
                </div>
            </div>
        </template>
    </div>
</div>
<script src="/home/v3/static/js/index-bottom.js"></script>
<div id="bottom" style="margin-top: 25px">
    <index-bottom></index-bottom>
</div>

<script>
    new Vue({
        el: "#bottom"
    })
</script>

<script>
    new Vue({
        el: "#personalCenter",
        data: {
            catPkeyList: [        // 分类类目 男 女 童 回收站
                {catPkey: null, name: "All Collections"},
                {catPkey: 373, name: "Men’s Collections"},
                {catPkey: 380, name: "Women’s Collections"},
                {catPkey: 387, name: "Kid’s Collections"},
                {catPkey: -1, name: "Recycle Bin"},
            ],
            currentPage: 1,       // 当前页
            catPkey: null,        // 默认类目  null 所有
            catPkeyIndex: null,   // 默认类目  索引
            start: 0,             // 请求数据 起始
            limit: 8,             // 请求数据 数量
            totalCount: null,     // 总收藏条数
            favoriteList: [],     // 收藏列表
            checkedCode: [],     // 用于保存被选中的数据
            isAllChecked: false,  // 是否全选
        },
        mounted() {
            this.getFavoriteList('', this.start, this.limit);
        },
        methods: {
            catClick(catPkey, index) {        // 点击分类
                this.isAllChecked = false;  // 取消全选
                this.checkedCode = [];      // 清空选中数据
                this.currentPage = 1;       // 当前页 1
                this.catPkeyIndex = index;  // 当前分类索引
                this.catPkey = catPkey;     // 当前分类ID
                console.log(this.catPkey)
                this.getFavoriteList(this.catPkey, 0, this.limit);
            },
            singleChecked: function () {  // 用户单选
                //判断每一个CheckBox是否选中，全选中让全选按钮选中
                if (this.favoriteList.length == this.checkedCode.length) {
                    this.isAllChecked = true;
                } else {  // 只要有一个checkbox不选中，让全选按钮不选中
                    this.isAllChecked = false;
                }
            },
            chooseAll: function (e) {  // 用户全选
                var self = this;
                if (self.checkedCode.length !== 0) {
                    self.checkedCode = [];
                }
                if (self.isAllChecked) {
                    // console.log(self.checkedCode)
                    self.favoriteList.forEach(function (item) {
                        self.checkedCode.push(item.id)
                    }, self)
                } else {
                    self.checkedCode = [];
                }
            },
            remove(pkey) {  // 商品单个移除到回收站  和  回收站单个商品永久删除
                if(!sysConfig || !sysConfig.user){
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }
                var self = this;
                if (self.catPkey == -1) {
                    // 回收站里
                    confimName = 'Whether to delete the item'
                    url = '/home/usr_UsrFavorites_delFavorite'
                } else {
                    // 正常分类
                    confimName = 'Whether to move the item to the recycle bin'
                    url = '/home/usr_UsrFavorites_recycleFavorite'
                }
                self.$confirm(confimName,  {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    customClass: "my-custom-element-alert-class fs-content-18",
                }).then(() => {
                    //  确定按钮
                    axios.post(url, Qs.stringify({
                        pkey,
                    }))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret == -1) {
                                util_function_obj.alertWhenNoLogin(self);
                                return
                            } else if (res.data.ret != 1) {
                                self.$message.error(res.data.msg || "Network error, please refresh the page and try again");
                                return
                            }
                            self.$message.success("Successfully deleted");
                            let index = self.findall(self.checkedCode,pkey)
                            self.checkedCode.splice(index, 1)
                            self.getFavoriteList(self.catPkey, self.start, self.limit);

                        })
                        .catch(function (error) {
                            self.$message.error("Network error, please refresh the page and try again");
                            console.log(error);
                        });
                }).catch(() => {
                    // 取消
                });
            },
            removeAll() {  // 删除其他分类多个商品   和   删除回收站多个商品
                console.log(this.checkedCode)
                var self = this;
                var confimName;
                var url;
                if (self.checkedCode.length <= 0) {
                    // console.log("没有选择项")
                    self.$message.error("Please select the item you want to delete");
                    return;
                }
                console.log(self.checkedCode)
                if (self.catPkey == -1) {
                    confimName = 'Whether to delete the item'
                    url = '/home/usr_UsrFavorites_delFavorite'
                } else {
                    confimName = 'Whether to move the item to the recycle bin'
                    url = '/home/usr_UsrFavorites_recycleFavorite'
                }
                console.log(confimName)
                self.$confirm(confimName, {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    customClass: "my-custom-element-alert-class fs-content-18",
                }).then(() => {
                    //  确定按钮
                    axios.post(url, Qs.stringify({
                        pkeys: self.checkedCode.join(),
                    }))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret == -1) {
                                util_function_obj.alertWhenNoLogin(self);
                                return
                            } else if (res.data.ret != 1) {
                                self.$message.error(res.data.msg || "Network error, please refresh the page and try again");
                                return
                            }
                                self.$message.success("Successfully deleted");
                                self.isAllChecked = false;  // 取消全选
                                self.checkedCode = [];      // 清空选中数据
                                self.getFavoriteList(self.catPkey, self.start, self.limit);

                        })
                        .catch(function (error) {
                            self.$message.error("Network error, please refresh the page and try again");
                            console.log(error);
                        });
                }).catch(() => {
                    // 取消
                });
            },
            restore(pkey) {  // 回收站 商品  还原到 收藏夹
                var self = this;
                self.$confirm("Whether to restore to favorites", {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    customClass: "my-custom-element-alert-class fs-content-18",
                }).then(() => {
                    //  确定按钮
                    axios.post("/home/usr_UsrFavorites_restoreFavorite", Qs.stringify({
                        pkey,
                    }))
                        .then(function (res) {
                            // console.log(res);
                            if (res.data.ret == -1) {
                                util_function_obj.alertWhenNoLogin(self);
                                return
                            } else if (res.data.ret != 1) {
                                self.$message.error(res.data.msg || "Network error, please refresh the page and try again");
                                return
                            }
                                self.$message.success("Successful recovery");
                                let index = self.findall(self.checkedCode,pkey)
                                self.checkedCode.splice(index, 1)
                                self.getFavoriteList(self.catPkey, self.start, self.limit);

                        })
                        .catch(function (error) {
                            self.$message.error("Network error, please refresh the page and try again");
                            console.log(error);
                        });
                }).catch(() => {
                    // 取消
                });
            },
            handleCurrentChange(val) {  // 点击页数 跳转
                this.currentPage = val
                this.start = (this.currentPage - 1) * this.limit;
                this.getFavoriteList(this.catPkey, this.start, this.limit);
            },
            getFavoriteList(catPkey, start, limit) { // 获取收藏列表
                var self = this;
                axios.post('/home/usr_UsrFavorites_myfavoriteAjax', Qs.stringify({
                    catPkey,
                    start,
                    limit,
                }))
                    .then(function (res) {
                        if (res.data.ret == -1) {
                            util_function_obj.alertWhenNoLogin(self);
                            return
                        } else if (res.data.ret != 1) {
                            self.$message.error(res.data.msg || "Network error, please refresh the page and try again");
                            return
                        }
                        self.totalCount = res.data.result.totalCount
                        self.favoriteList = res.data.result.items;

                    })
                    .catch(function (error) {
                        self.$message.error("Network error, please refresh the page and try again");
                        console.log(error);
                    });
            },
            image(url, w, h, param) {
					var postfixUrl = ""; //后缀
					if (!url) {
						return ""
					}
					if (w && h) {
						postfixUrl = "?x-oss-process=image/resize,w_" + w + ",h_" + h;
					}else if(w){
						postfixUrl = "?x-oss-process=image/resize,w_" + w + ",h_" + w;
					}
					if(param){
						postfixUrl += param;
					}
					return "https://image.shoestp.com" + url + postfixUrl;
                },
              findall(a,x){ // 找出某个元素在数组里的索引值
                var results=[],
                len=a.length,
                pos=0;
                while(pos<len){
                    pos=a.indexOf(x,pos);
                if(pos===-1){//未找到就退出循环完成搜索
                    break;
                }
                    results.push(pos);//找到就存储索引
                    pos+=1;//并从下个位置开始搜索
                }
                return results;
            }
        },
        watch: {
            checkedCode: {
                handler: function(val, oldVal) {
                    if (val.length === this.favoriteList.length) {
                        this.isAllChecked = true;
                    } else {
                        this.isAllChecked = false;
                    }
                },
                deep: true
            }
        },
    })
</script>
</body>

</html>
