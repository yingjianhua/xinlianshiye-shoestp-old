Vue.component('index-top', {
    template: `<div>
    <div class="o2otop-placeholder"></div>
    <div id="o2otop">
        <div class="o2otopcon">
            <!--<img src="/home/v3/static/images/o2otoplogo.png" alt="">-->
            <a href="/home/usr_UsrPurchase" target="view_window"><img src="/home/v3/static/images/o2otoplogo.png" class="logo"
                    alt="logo"></a>
            <div class="topsearch">
                <el-select v-model="select" placeholder="Product" @change="selected">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
                <input type="text" v-model="input">
                <div class="searchbotton" @click="submit"><img src="/home/v3/static/images/indexiconserch.png" alt=""></div>
            </div>
            <!--  站内信 有登录后看到 -->
            <dl v-if="sysConfig.user" class="o2otoplikes o2otopmessage" href="/home/usr_UsrFavorites_myfavorite">
              <dt style="z-index:90;">
                <img src="/home/v3/static/images/o2otopmessage.png" alt="">
                <div class="o2otoplikenum">
                    <div style="height: 20px;">
                        <p v-if="sysConfig.user">{{}}</p>
                        <p v-if="!sysConfig.user"> 0</p>
                    </div>
                    <p>Messages</p>
                </div>
              </dt>
              <dd>
                <template v-if="PMMessageList.length >= 1">
                    <p class="message-title">You have {{countNoRead}} new messages</p>
                    <ul>
                        <li v-for="(item,index) in PMMessageList" :key="index" @click="msgClick(item.pkey,index)">
                            <a href="javascript:void(0)">
                                <i class="message-icon" :class="item.read?'message-icon-new':'message-icon-old'"></i>
                                <div>
                                    <p :class="item.read?'ellipsis_2':''" :style="item.read?'color:#999999;':''">{{item.content}}</p>
                                    <div>
                                        <img src="/home/v3/static/images/o2otopmessagetime.png" alt="">
                                        <!-- <span>{{item.time | timeDistance}}</span> -->
                                        <span>{{item.time | formatMsgTime}}</span> 
                                        <!-- <span>{{getFormatTime(item.time)}}</span> -->
                                    </div>
                                </div>
                            </a>
                        </li>
                        <div class="more-btn" @click="moreClick">
                            查看更多
                        </div>
                    </ul>
                </template>
                <template v-else>
                    <div style="line-height: 100px;text-align: center;">
                        暂无消息
                    </div>
                </template>
                 
              </dd>
            </dl>
            <!-- 站内信 没有登录看到的  -->
            <a class="o2otoplikes" href="" v-if="!sysConfig.user">
                <img src="/home/v3/static/images/o2otopmessage.png" alt="" style="margin-right:10px;">
                <div class="o2otoplikenum">
                    <div style="height: 20px;">
                    </div>
                    <p>Messages</p>
                </div>
            </a>
            <dl class="o2otoplikes">
                <dt>
                    <img src="/home/v3/static/images/indextopren.png" alt="">
                    <div class="o2otoplikenum">
                        <div style="height:  20px;">
                        </div>
                        <p>My Shosetp</p>
                    </div>
                </dt>
               
                <!--  没有登录看到的  -->
                <dd v-if="!sysConfig.user">
                    <div style="font-size:16px;margin:  18px  0;">Get started now</div>
                    <div class="login-btn"><a href="/home/usr_UsrPurchase_sign" target="_blank">Login</a></div>
                    <div style="text-align:center;font-size:16px;margin:  8px  0;">or</div>
                    <div class="registered-btn"><a href="/home/usr_UsrPurchase_sign" target="_blank">Registration</a></div>
                    <div style="color:#777777;text-align:center;margin:  15px  0;">Continue with：</div>
                    <div class="clearfix  other-login-list">
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/facebook.png" alt="facebook">
                            </a>
                        </div>
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/google.png" alt="google">
                            </a>
                        </div>
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/in.png" alt="in">
                            </a>
                        </div>
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/twitter.png" alt="twitter">
                            </a>
                        </div>
                    </div>
                    <ul>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Shoestp <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrMessages_center" target="_blank">RFQ List <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrConsult_listView" target="_blank">My Inquiry <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Account <i class="el-icon-arrow-right"></i></a></li>
                        <li style="font-size:15px;font-weight: bold;"><a href="/home/usr_UsrSupplier_supplierEntry" target="_blank">我要开店 <i class="el-icon-arrow-right"></i></a></li>
                    </ul>
                    <div class="after-login"><a href="/home/usr_UsrConsult_publishView" target="_blank">Submit RFQ</a></div>
                    <div class="hours  after-login">Get multiple quotes within 24 hours！</div>
                </dd>
                <!--  登陆后看到的  -->
                <dd v-if="sysConfig.user">
                    <ul>
                        <li class="flexSb user-name"><div class="ellipsis_1">Hi {{sysConfig.user.name}}</div><a href="/home/usr_UsrPurchase_signOut" target="_blank">sign out</a></li>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Shoestp <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="//home/usr_UsrMessages_center" target="_blank">RFQ List <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrConsult_listView" target="_blank">My Inquiry <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Account <i class="el-icon-arrow-right"></i></a></li>
                        <li style="font-size:15px;font-weight: bold;"><a href="/home/usr_UsrSupplier_supplierEntry" target="_blank">我要开店 <i class="el-icon-arrow-right"></i></a></li>
                    </ul>
                    <div class="after-login"><a href="/home/usr_UsrConsult_publishView" target="_blank">Submit RFQ</a></div>
                    <div class="hours after-login">Get multiple quotes within 24 hours！</div>
                </dd>
            </dl>

            <a class="o2otoplikes" href="/home/usr_UsrFavorites_myfavorite">
                <img src="/home/v3/static/images/o2otopfavoroite.png" alt="">
                <div class="o2otoplikenum">
                    <div style="height: 20px;">
                        <p v-if="sysConfig.user">{{sysConfig.user.favorite_count }}</p>
                        <p v-if="!sysConfig.user"> 0</p>
                    </div>
                    <p>Favorites</p>
                </div>
            </a>
        </div>
    </div>
</div>`,
    props: {

    },
    data() {
        return {
            msgStart:0, //消息列表分页 起始
            msgLimit:8, //消息列表分页 每页数量
            PMmoreSwitch:true, // 加载更多消息开关
            select: 0,
            input: '',
            options: [{
                    value: 0,
                    label: 'Product'
                },
                {
                    value: 1,
                    label: 'Suppiler'
                }
            ],
            sysConfig: {},
            PMMessageList:[],
        }
    },
    mounted() {
        Vue.set(this.$data, 'input', unescape(decodeURIComponent(getParams('Keyword', getParams('keyWord', getParams('keyword', ''))))))
        this.getconfig();
        this.getPMmessage(this.msgStart,this.msgLimit);
    },
    methods: {
        submit: function () {
            if (this.input) {
                window.location = '/home/pdt_PdtProduct?Keyword=' + this.input + '&v=2&searchtype=' + this.select;
            } else {
                return false;
            }
        },
        selected: function (res) {

        },
        getconfig: function () {
            var self = this
            axios.get('/home/plt_PltConfig_getSysConfig').then(function (res) {
                if (res.data.ret == 1) {
                    if (res.data.result.user) {
                        self.$set(self, 'user', res.data.result.user)
                        self.$set(self, 'sysConfig', res.data.result)
                    }
                }
            })
        },
        getPMmessage(start,limit){
            var self = this
            // axios.get('http://192.168.1.48:889/mock/5c6a1556af4d250024d48c6d/home/home/pm_PMMessage_list').then(function (res) {
            // axios.get('/home/pm_PMMessage_list').then(function (res) {
            axios.get(
                'http://192.168.1.48:889/mock/5c6a1556af4d250024d48c6d/home/home/pm_PMMessage_list', {
                // '/home/pm_PMMessage_list', {
                    params: {
                        start,
                        limit,
                    }
                }
            )
            .then(function (res) {
                console.log("PMMMMMMMMMMMMMMMMMM" + res)
                console.log(res)
                if (res.data.ret == 1) {
                    self.countNoRead = res.data.result.countNoRead;
                    // self.PMMessageList.push(...res.data.result.items);
                    self.PMMessageList.push(...res.data.result);
                    if(res.data.result.length <= 0){
                       self.PMmoreSwitch == false; 
                       self.$message('没有更多了');
                       return;
                    }
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        msgClick(pkey,i){   // 点击消息 
            var self = this;
            axios.post('/home/pm_PMMessage_read', Qs.stringify({
                    message:pkey,
                }))
                .then(function (res) {
                    console.log(res);
                    if (res.data.ret != 1) {
                        self.$message.error(res.data.msg);
                        return
                    };
                    self.$message.success("已读");
                    self.$set(self.PMMessageList[i],"read",false)
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        moreClick(){    // 点击 加载更多消息
            var self = this;
            if(this.PMmoreSwitch){
                self.msgStart = self.msgStart  + self.msgLimit
                self.getPMmessage(self.msgStart,self.msgLimit);
            }else{
                self.$message('没有更多了');
            }
        },
         //获取当前时间戳
        getUnix:function(){
            var date = new Date();
            return date.getTime();
        },
        //获取今天0点0分0秒的时间戳
        getTodayUnix:function(){
            var date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            date.setMilliseconds(0);
            return date.getTime();
        },
        //获取今年1月1日0点0分0秒的时间戳
        getYearUnix:function(){
            var date = new Date();
            date.setMonth(0);
            date.setDate(1);
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            date.setMilliseconds(0);
            return date.getTime();
        },
        //获取标准年月日
        getLastDate:function(time){
            var date = new Date(time);
            var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
            var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
            return date.getFullYear() + '-' + month + '-' + day;
        },
        //转换时间
        getFormatTime:function(timestamp){
            var now = this.getUnix(); // 当前时间戳
            var today = this.getTodayUnix(); // 今天0点的时间戳
            var year = this.getYearUnix(); // 今年0点的时间戳
            var timer = (now - timestamp) / 1000;  // 转换为秒级时间戳
            var tip = '';

            if(timer <= 0){
                tip = '刚刚';
            }else if(Math.floor(timer/60) <= 0){
                tip = '刚刚';
            }else if(timer < 3600){
                tip = Math.floor(timer/60) + '分钟前';
            }else if(timer >= 3600 && (timestamp - today >= 0)){
                tip = Math.floor(timer/3600) + '小时前';
            }else if(timer/86400 <= 31){
                tip = Math.ceil(timer/86400) + '天前';
            }else{
                tip = this.getLastDate(timestamp);
            }
            return tip;
        }
         
    },
    filters: {
        formatMsgTime (dateTimeStamp) {
            var minute =  60;
            var hour = minute * 60;
            var day = hour * 24;
            var halfamonth = day * 15;
            var month = day * 30;
            var now = new Date().getTime();
            var diffValue = (now - dateTimeStamp) / 1000;
            if(diffValue < 0){return;}
            var monthC =diffValue/month;
            var weekC =diffValue/(7*day);
            var dayC =diffValue/day;
            var hourC =diffValue/hour;
            var minC =diffValue/minute;
            if(monthC>=1){
                result="" + parseInt(monthC) + "月前";
            }
            else if(weekC>=1){
                result="" + parseInt(weekC) + "周前";
            }
            else if(dayC>=1){
                result=""+ parseInt(dayC) +"天前";
            }
            else if(hourC>=1){
                result=""+ parseInt(hourC) +"小时前";
            }
            else if(minC>=1){
                result=""+ parseInt(minC) +"分钟前";
            }else
            result="刚刚";
            return result;
            },
            timeDistance(value){
                let nowt = Math.round(new Date() / 1000)
                let times = (nowt - value / 1000);
                if(times>31536000){
                    return `${parseInt(times/31536000)}年前`
                }else if(times>=86400&times<31536000){
                    return `${parseInt(times/86400)}天前`;
                }else if(times>=3600&times<86400){
                    return `${parseInt(times/3600)}小时前`;
                }else if(times>60&times<3600){
                    return `${parseInt(times/60)}分钟前`;
                }else if(times<60){return '刚刚'}
    
            },
    }
})